/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/gpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tinygroup.sdpm.project.dao.impl;

import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.project.dao.ProjectDao;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.Join;
import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.tinysqldsl.selectitem.FragmentSelectItemSql;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.tinygroup.sdpm.project.dao.constant.ProjectStoryTable.PROJECT_STORYTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectTable.PROJECTTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectTaskTable.PROJECT_TASKTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectTeamTable.PROJECT_TEAMTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.select;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.or;
import static org.tinygroup.tinysqldsl.formitem.SubSelect.subSelect;

@Repository
public class ProjectDaoImpl extends TinyDslDaoSupport implements ProjectDao {

    public Pager<Project> findPageWithStatistics(int start, int limit, Project project, final Integer[] projectIds,
                                                 final OrderBy... args) {
        if (project == null) {
            project = new Project();
        }
        return getDslTemplate().queryPager(start, limit, project, false, new SelectGenerateCallback<Project>() {
            public Select generate(Project t) {
                Select select = MysqlSelect.select(PROJECTTABLE.ALL,
                        PROJECT_TASKTABLE.TASK_ESTIMATE.sum().as("estimate"),
                        PROJECT_TASKTABLE.TASK_CONSUMED.sum().as("consumed"),
                        PROJECT_TASKTABLE.TASK_LEFT.sum().as("allLeft"),
                        FragmentSelectItemSql.fragmentSelect("SUM(project_task.task_consumed)/(SUM(project_task.task_consumed)+SUM(project_task.task_left)) as percent")
                ).from(PROJECTTABLE)
                        .join(Join.leftJoin(PROJECT_TASKTABLE, PROJECT_TASKTABLE.TASK_PROJECT.eq(PROJECTTABLE.PROJECT_ID)))
                        .where(and(PROJECTTABLE.PROJECT_ID.in(projectIds), PROJECTTABLE.PROJECT_ID.eq(t.getProjectId())))
                        .groupBy(PROJECTTABLE.PROJECT_ID);
                return addOrderByElements(select, args);
            }
        });
    }

    public List<Project> findListByRelatedUser(Project project) {
        if (project == null) {
            return new ArrayList<Project>();
        }
        Select select = selectFrom(PROJECTTABLE).where(
                or(
                        PROJECTTABLE.PROJECT_OPENED_BY.eq(project.getProjectOpenedBy()),
                        PROJECTTABLE.PROJECT_DELETED.eq(Project.DELETE_NO),
                        PROJECTTABLE.PROJECT_PO.eq(project.getProjectPo()),
                        PROJECTTABLE.PROJECT_PM.eq(project.getProjectPm()),
                        PROJECTTABLE.PROJECT_QD.eq(project.getProjectQd()),
                        PROJECTTABLE.PROJECT_RD.eq(project.getProjectRd())
                )
        );
        return getDslSession().fetchList(select, Project.class);
    }

    public List<Project> findListWithStatistics(Project project, Date startDate, Date endDate) {
        Condition condition = null;
        if (startDate != null) {
            condition = and(PROJECTTABLE.PROJECT_OPENED_DATE.gte(startDate), PROJECTTABLE.PROJECT_OPENED_DATE.lte(endDate));
        }
        if (endDate != null) {
            condition = condition == null ? PROJECTTABLE.PROJECT_OPENED_DATE.lte(endDate) : and(condition, PROJECTTABLE.PROJECT_OPENED_DATE.lte(endDate));
        }
        if (project == null) {
            project = new Project();
        }
        Select select = MysqlSelect.select(PROJECTTABLE.ALL,
                PROJECT_TASKTABLE.TASK_ESTIMATE.sum().as("estimate"),
                PROJECT_TASKTABLE.TASK_CONSUMED.sum().as("consumed"),
                PROJECT_TASKTABLE.TASK_LEFT.sum().as("allLeft"),
                FragmentSelectItemSql.fragmentSelect("SUM(project_task.task_consumed)/(SUM(project_task.task_consumed)+SUM(project_task.task_left)) as percent")
        ).from(PROJECTTABLE).join(
                Join.leftJoin(PROJECT_TASKTABLE,
                        PROJECT_TASKTABLE.TASK_PROJECT.equal(PROJECTTABLE.PROJECT_ID)))
                .where(and(condition, PROJECTTABLE.PROJECT_DELETED.eq(Project.DELETE_NO), PROJECTTABLE.PROJECT_ID.eq(project.getProjectId())))
                .groupBy(PROJECTTABLE.PROJECT_ID);
        return getDslSession().fetchList(select, Project.class);
    }

    public Project add(Project project) {
        return getDslTemplate().insertAndReturnKey(project, new InsertGenerateCallback<Project>() {
            public Insert generate(Project t) {
                Insert insert = insertInto(PROJECTTABLE).values(
                        PROJECTTABLE.PROJECT_ID.value(t.getProjectId()),
                        PROJECTTABLE.PROJECT_IS_CAT.value(t.getProjectIsCat()),
                        PROJECTTABLE.PROJECT_CAT_ID.value(t.getProjectCatId()),
                        PROJECTTABLE.PROJECT_TYPE.value(t.getProjectType()),
                        PROJECTTABLE.PROJECT_NAME.value(t.getProjectName()),
                        PROJECTTABLE.PROJECT_CODE.value(t.getProjectCode()),
                        PROJECTTABLE.PROJECT_BEGIN.value(t.getProjectBegin()),
                        PROJECTTABLE.PROJECT_END.value(t.getProjectEnd()),
                        PROJECTTABLE.PROJECT_DAYS.value(t.getProjectDays()),
                        PROJECTTABLE.PROJECT_STATUS.value(t.getProjectStatus()),
                        PROJECTTABLE.PROJECT_STATGE.value(t.getProjectStatge()),
                        PROJECTTABLE.PROJECT_PRI.value(t.getProjectPri()),
                        PROJECTTABLE.PROJECT_DESC.value(t.getProjectDesc()),
                        PROJECTTABLE.PROJECT_OPENED_BY.value(t.getProjectOpenedBy()),
                        PROJECTTABLE.PROJECT_OPENED_DATE.value(t.getProjectOpenedDate()),
                        PROJECTTABLE.PROJECT_OPENED_VERSION.value(t.getProjectOpenedVersion()),
                        PROJECTTABLE.PROJECT_CLOSE_BY.value(t.getProjectCloseBy()),
                        PROJECTTABLE.PROJECT_CLOSE_DATE.value(t.getProjectCloseDate()),
                        PROJECTTABLE.PROJECT_CANCELED_BY.value(t.getProjectCanceledBy()),
                        PROJECTTABLE.PROJECT_CANCELED_DATE.value(t.getProjectCanceledDate()),
                        PROJECTTABLE.PROJECT_PO.value(t.getProjectPo()),
                        PROJECTTABLE.PROJECT_PM.value(t.getProjectPm()),
                        PROJECTTABLE.PROJECT_QD.value(t.getProjectQd()),
                        PROJECTTABLE.PROJECT_RD.value(t.getProjectRd()),
                        PROJECTTABLE.PROJECT_TEAM.value(t.getProjectTeam()),
                        PROJECTTABLE.PROJECT_ACL.value(t.getProjectAcl()),
                        PROJECTTABLE.PROJECT_WHITE_LIST.value(t.getProjectWhiteList()),
                        PROJECTTABLE.PROJECT_ORDER.value(t.getProjectOrder()),
                        PROJECTTABLE.PROJECT_DELETED.value(t.getProjectDeleted()));
                return insert;
            }
        });
    }

    public int edit(Project project) {
        if (project == null || project.getProjectId() == null) {
            return 0;
        }
        return getDslTemplate().update(project, new UpdateGenerateCallback<Project>() {
            public Update generate(Project t) {
                Update update = update(PROJECTTABLE).set(
                        PROJECTTABLE.PROJECT_IS_CAT.value(t.getProjectIsCat()),
                        PROJECTTABLE.PROJECT_CAT_ID.value(t.getProjectCatId()),
                        PROJECTTABLE.PROJECT_TYPE.value(t.getProjectType()),
                        PROJECTTABLE.PROJECT_NAME.value(t.getProjectName()),
                        PROJECTTABLE.PROJECT_CODE.value(t.getProjectCode()),
                        PROJECTTABLE.PROJECT_BEGIN.value(t.getProjectBegin()),
                        PROJECTTABLE.PROJECT_END.value(t.getProjectEnd()),
                        PROJECTTABLE.PROJECT_DAYS.value(t.getProjectDays()),
                        PROJECTTABLE.PROJECT_STATUS.value(t.getProjectStatus()),
                        PROJECTTABLE.PROJECT_STATGE.value(t.getProjectStatge()),
                        PROJECTTABLE.PROJECT_PRI.value(t.getProjectPri()),
                        PROJECTTABLE.PROJECT_DESC.value(t.getProjectDesc()),
                        PROJECTTABLE.PROJECT_OPENED_BY.value(t.getProjectOpenedBy()),
                        PROJECTTABLE.PROJECT_OPENED_DATE.value(t.getProjectOpenedDate()),
                        PROJECTTABLE.PROJECT_OPENED_VERSION.value(t.getProjectOpenedVersion()),
                        PROJECTTABLE.PROJECT_CLOSE_BY.value(t.getProjectCloseBy()),
                        PROJECTTABLE.PROJECT_CLOSE_DATE.value(t.getProjectCloseDate()),
                        PROJECTTABLE.PROJECT_CANCELED_BY.value(t.getProjectCanceledBy()),
                        PROJECTTABLE.PROJECT_CANCELED_DATE.value(t.getProjectCanceledDate()),
                        PROJECTTABLE.PROJECT_PO.value(t.getProjectPo()),
                        PROJECTTABLE.PROJECT_PM.value(t.getProjectPm()),
                        PROJECTTABLE.PROJECT_QD.value(t.getProjectQd()),
                        PROJECTTABLE.PROJECT_RD.value(t.getProjectRd()),
                        PROJECTTABLE.PROJECT_TEAM.value(t.getProjectTeam()),
                        PROJECTTABLE.PROJECT_ACL.value(t.getProjectAcl()),
                        PROJECTTABLE.PROJECT_WHITE_LIST.value(t.getProjectWhiteList()),
                        PROJECTTABLE.PROJECT_ORDER.value(t.getProjectOrder()),
                        PROJECTTABLE.PROJECT_DELETED.value(t.getProjectDeleted())).where(
                        PROJECTTABLE.PROJECT_ID.eq(t.getProjectId()));
                return update;
            }
        });
    }

    public int deleteByKey(Integer pk) {
        if (pk == null) {
            return 0;
        }
        return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
            public Delete generate(Serializable pk) {
                return delete(PROJECTTABLE).where(PROJECTTABLE.PROJECT_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(PROJECTTABLE).where(PROJECTTABLE.PROJECT_ID.in(t));
            }
        }, pks);
    }

    public Project getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, Project.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(PROJECTTABLE).where(PROJECTTABLE.PROJECT_ID.eq(t));
            }
        });
    }

    public List<Project> query(Project project, final OrderBy... orderArgs) {
        if (project == null) {
            project = new Project();
        }
        return getDslTemplate().query(project, new SelectGenerateCallback<Project>() {

            @SuppressWarnings("rawtypes")
            public Select generate(Project t) {
                Select select = selectFrom(PROJECTTABLE).where(
                        and(
                                PROJECTTABLE.PROJECT_IS_CAT.eq(t.getProjectIsCat()),
                                PROJECTTABLE.PROJECT_CAT_ID.eq(t.getProjectCatId()),
                                PROJECTTABLE.PROJECT_TYPE.eq(t.getProjectType()),
                                PROJECTTABLE.PROJECT_NAME.eq(t.getProjectName()),
                                PROJECTTABLE.PROJECT_CODE.eq(t.getProjectCode()),
                                PROJECTTABLE.PROJECT_BEGIN.eq(t.getProjectBegin()),
                                PROJECTTABLE.PROJECT_END.eq(t.getProjectEnd()),
                                PROJECTTABLE.PROJECT_DAYS.eq(t.getProjectDays()),
                                PROJECTTABLE.PROJECT_STATUS.eq(t.getProjectStatus()),
                                PROJECTTABLE.PROJECT_STATGE.eq(t.getProjectStatge()),
                                PROJECTTABLE.PROJECT_PRI.eq(t.getProjectPri()),
                                PROJECTTABLE.PROJECT_DESC.eq(t.getProjectDesc()),
                                PROJECTTABLE.PROJECT_OPENED_BY.eq(t.getProjectOpenedBy()),
                                PROJECTTABLE.PROJECT_OPENED_DATE.eq(t.getProjectOpenedDate()),
                                PROJECTTABLE.PROJECT_OPENED_VERSION.eq(t.getProjectOpenedVersion()),
                                PROJECTTABLE.PROJECT_CLOSE_BY.eq(t.getProjectCloseBy()),
                                PROJECTTABLE.PROJECT_CLOSE_DATE.eq(t.getProjectCloseDate()),
                                PROJECTTABLE.PROJECT_CANCELED_BY.eq(t.getProjectCanceledBy()),
                                PROJECTTABLE.PROJECT_CANCELED_DATE.eq(t.getProjectCanceledDate()),
                                PROJECTTABLE.PROJECT_PO.eq(t.getProjectPo()),
                                PROJECTTABLE.PROJECT_PM.eq(t.getProjectPm()),
                                PROJECTTABLE.PROJECT_QD.eq(t.getProjectQd()),
                                PROJECTTABLE.PROJECT_RD.eq(t.getProjectRd()),
                                PROJECTTABLE.PROJECT_TEAM.eq(t.getProjectTeam()),
                                PROJECTTABLE.PROJECT_ACL.eq(t.getProjectAcl()),
                                PROJECTTABLE.PROJECT_WHITE_LIST.eq(t.getProjectWhiteList()),
                                PROJECTTABLE.PROJECT_ORDER.eq(t.getProjectOrder()),
                                PROJECTTABLE.PROJECT_DELETED.eq(t.getProjectDeleted())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public Pager<Project> queryPager(int start, int limit, Project project, final OrderBy... orderArgs) {
        if (project == null) {
            project = new Project();
        }
        return getDslTemplate().queryPager(start, limit, project, false, new SelectGenerateCallback<Project>() {

            public Select generate(Project t) {
                Select select = MysqlSelect.selectFrom(PROJECTTABLE).where(
                        and(
                                PROJECTTABLE.PROJECT_IS_CAT.eq(t.getProjectIsCat()),
                                PROJECTTABLE.PROJECT_CAT_ID.eq(t.getProjectCatId()),
                                PROJECTTABLE.PROJECT_TYPE.eq(t.getProjectType()),
                                PROJECTTABLE.PROJECT_NAME.eq(t.getProjectName()),
                                PROJECTTABLE.PROJECT_CODE.eq(t.getProjectCode()),
                                PROJECTTABLE.PROJECT_BEGIN.eq(t.getProjectBegin()),
                                PROJECTTABLE.PROJECT_END.eq(t.getProjectEnd()),
                                PROJECTTABLE.PROJECT_DAYS.eq(t.getProjectDays()),
                                PROJECTTABLE.PROJECT_STATUS.eq(t.getProjectStatus()),
                                PROJECTTABLE.PROJECT_STATGE.eq(t.getProjectStatge()),
                                PROJECTTABLE.PROJECT_PRI.eq(t.getProjectPri()),
                                PROJECTTABLE.PROJECT_DESC.eq(t.getProjectDesc()),
                                PROJECTTABLE.PROJECT_OPENED_BY.eq(t.getProjectOpenedBy()),
                                PROJECTTABLE.PROJECT_OPENED_DATE.eq(t.getProjectOpenedDate()),
                                PROJECTTABLE.PROJECT_OPENED_VERSION.eq(t.getProjectOpenedVersion()),
                                PROJECTTABLE.PROJECT_CLOSE_BY.eq(t.getProjectCloseBy()),
                                PROJECTTABLE.PROJECT_CLOSE_DATE.eq(t.getProjectCloseDate()),
                                PROJECTTABLE.PROJECT_CANCELED_BY.eq(t.getProjectCanceledBy()),
                                PROJECTTABLE.PROJECT_CANCELED_DATE.eq(t.getProjectCanceledDate()),
                                PROJECTTABLE.PROJECT_PO.eq(t.getProjectPo()),
                                PROJECTTABLE.PROJECT_PM.eq(t.getProjectPm()),
                                PROJECTTABLE.PROJECT_QD.eq(t.getProjectQd()),
                                PROJECTTABLE.PROJECT_RD.eq(t.getProjectRd()),
                                PROJECTTABLE.PROJECT_TEAM.eq(t.getProjectTeam()),
                                PROJECTTABLE.PROJECT_ACL.eq(t.getProjectAcl()),
                                PROJECTTABLE.PROJECT_WHITE_LIST.eq(t.getProjectWhiteList()),
                                PROJECTTABLE.PROJECT_ORDER.eq(t.getProjectOrder()),
                                PROJECTTABLE.PROJECT_DELETED.eq(t.getProjectDeleted())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }


    public int[] batchInsert(boolean autoGeneratedKeys, List<Project> projects) {
        if (CollectionUtil.isEmpty(projects)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, projects, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(PROJECTTABLE).values(
                        PROJECTTABLE.PROJECT_IS_CAT.value(new JdbcNamedParameter("projectIsCat")),
                        PROJECTTABLE.PROJECT_CAT_ID.value(new JdbcNamedParameter("projectCatId")),
                        PROJECTTABLE.PROJECT_TYPE.value(new JdbcNamedParameter("projectType")),
                        PROJECTTABLE.PROJECT_NAME.value(new JdbcNamedParameter("projectName")),
                        PROJECTTABLE.PROJECT_CODE.value(new JdbcNamedParameter("projectCode")),
                        PROJECTTABLE.PROJECT_BEGIN.value(new JdbcNamedParameter("projectBegin")),
                        PROJECTTABLE.PROJECT_END.value(new JdbcNamedParameter("projectEnd")),
                        PROJECTTABLE.PROJECT_DAYS.value(new JdbcNamedParameter("projectDays")),
                        PROJECTTABLE.PROJECT_STATUS.value(new JdbcNamedParameter("projectStatus")),
                        PROJECTTABLE.PROJECT_STATGE.value(new JdbcNamedParameter("projectStatge")),
                        PROJECTTABLE.PROJECT_PRI.value(new JdbcNamedParameter("projectPri")),
                        PROJECTTABLE.PROJECT_DESC.value(new JdbcNamedParameter("projectDesc")),
                        PROJECTTABLE.PROJECT_OPENED_BY.value(new JdbcNamedParameter("projectOpenedBy")),
                        PROJECTTABLE.PROJECT_OPENED_DATE.value(new JdbcNamedParameter("projectOpenedDate")),
                        PROJECTTABLE.PROJECT_OPENED_VERSION.value(new JdbcNamedParameter("projectOpenedVersion")),
                        PROJECTTABLE.PROJECT_CLOSE_BY.value(new JdbcNamedParameter("projectCloseBy")),
                        PROJECTTABLE.PROJECT_CLOSE_DATE.value(new JdbcNamedParameter("projectCloseDate")),
                        PROJECTTABLE.PROJECT_CANCELED_BY.value(new JdbcNamedParameter("projectCanceledBy")),
                        PROJECTTABLE.PROJECT_CANCELED_DATE.value(new JdbcNamedParameter("projectCanceledDate")),
                        PROJECTTABLE.PROJECT_PO.value(new JdbcNamedParameter("projectPo")),
                        PROJECTTABLE.PROJECT_PM.value(new JdbcNamedParameter("projectPm")),
                        PROJECTTABLE.PROJECT_QD.value(new JdbcNamedParameter("projectQd")),
                        PROJECTTABLE.PROJECT_RD.value(new JdbcNamedParameter("projectRd")),
                        PROJECTTABLE.PROJECT_TEAM.value(new JdbcNamedParameter("projectTeam")),
                        PROJECTTABLE.PROJECT_ACL.value(new JdbcNamedParameter("projectAcl")),
                        PROJECTTABLE.PROJECT_WHITE_LIST.value(new JdbcNamedParameter("projectWhiteList")),
                        PROJECTTABLE.PROJECT_ORDER.value(new JdbcNamedParameter("projectOrder")),
                        PROJECTTABLE.PROJECT_DELETED.value(new JdbcNamedParameter("projectDeleted")));
            }
        });
    }

    public int[] batchInsert(List<Project> projects) {
        return batchInsert(true, projects);
    }

    public int[] batchUpdate(List<Project> projects) {
        if (CollectionUtil.isEmpty(projects)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(projects, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(PROJECTTABLE).set(
                        PROJECTTABLE.PROJECT_IS_CAT.value(new JdbcNamedParameter("projectIsCat")),
                        PROJECTTABLE.PROJECT_CAT_ID.value(new JdbcNamedParameter("projectCatId")),
                        PROJECTTABLE.PROJECT_TYPE.value(new JdbcNamedParameter("projectType")),
                        PROJECTTABLE.PROJECT_NAME.value(new JdbcNamedParameter("projectName")),
                        PROJECTTABLE.PROJECT_CODE.value(new JdbcNamedParameter("projectCode")),
                        PROJECTTABLE.PROJECT_BEGIN.value(new JdbcNamedParameter("projectBegin")),
                        PROJECTTABLE.PROJECT_END.value(new JdbcNamedParameter("projectEnd")),
                        PROJECTTABLE.PROJECT_DAYS.value(new JdbcNamedParameter("projectDays")),
                        PROJECTTABLE.PROJECT_STATUS.value(new JdbcNamedParameter("projectStatus")),
                        PROJECTTABLE.PROJECT_STATGE.value(new JdbcNamedParameter("projectStatge")),
                        PROJECTTABLE.PROJECT_PRI.value(new JdbcNamedParameter("projectPri")),
                        PROJECTTABLE.PROJECT_DESC.value(new JdbcNamedParameter("projectDesc")),
                        PROJECTTABLE.PROJECT_OPENED_BY.value(new JdbcNamedParameter("projectOpenedBy")),
                        PROJECTTABLE.PROJECT_OPENED_DATE.value(new JdbcNamedParameter("projectOpenedDate")),
                        PROJECTTABLE.PROJECT_OPENED_VERSION.value(new JdbcNamedParameter("projectOpenedVersion")),
                        PROJECTTABLE.PROJECT_CLOSE_BY.value(new JdbcNamedParameter("projectCloseBy")),
                        PROJECTTABLE.PROJECT_CLOSE_DATE.value(new JdbcNamedParameter("projectCloseDate")),
                        PROJECTTABLE.PROJECT_CANCELED_BY.value(new JdbcNamedParameter("projectCanceledBy")),
                        PROJECTTABLE.PROJECT_CANCELED_DATE.value(new JdbcNamedParameter("projectCanceledDate")),
                        PROJECTTABLE.PROJECT_PO.value(new JdbcNamedParameter("projectPo")),
                        PROJECTTABLE.PROJECT_PM.value(new JdbcNamedParameter("projectPm")),
                        PROJECTTABLE.PROJECT_QD.value(new JdbcNamedParameter("projectQd")),
                        PROJECTTABLE.PROJECT_RD.value(new JdbcNamedParameter("projectRd")),
                        PROJECTTABLE.PROJECT_TEAM.value(new JdbcNamedParameter("projectTeam")),
                        PROJECTTABLE.PROJECT_ACL.value(new JdbcNamedParameter("projectAcl")),
                        PROJECTTABLE.PROJECT_WHITE_LIST.value(new JdbcNamedParameter("projectWhiteList")),
                        PROJECTTABLE.PROJECT_ORDER.value(new JdbcNamedParameter("projectOrder")),
                        PROJECTTABLE.PROJECT_DELETED.value(new JdbcNamedParameter("projectDeleted"))).where(
                        PROJECTTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectId")));
            }
        });
    }

    public int[] batchDelete(List<Project> projects) {
        if (CollectionUtil.isEmpty(projects)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(projects, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(PROJECTTABLE).where(and(
                        PROJECTTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectId")),
                        PROJECTTABLE.PROJECT_IS_CAT.eq(new JdbcNamedParameter("projectIsCat")),
                        PROJECTTABLE.PROJECT_CAT_ID.eq(new JdbcNamedParameter("projectCatId")),
                        PROJECTTABLE.PROJECT_TYPE.eq(new JdbcNamedParameter("projectType")),
                        PROJECTTABLE.PROJECT_NAME.eq(new JdbcNamedParameter("projectName")),
                        PROJECTTABLE.PROJECT_CODE.eq(new JdbcNamedParameter("projectCode")),
                        PROJECTTABLE.PROJECT_BEGIN.eq(new JdbcNamedParameter("projectBegin")),
                        PROJECTTABLE.PROJECT_END.eq(new JdbcNamedParameter("projectEnd")),
                        PROJECTTABLE.PROJECT_DAYS.eq(new JdbcNamedParameter("projectDays")),
                        PROJECTTABLE.PROJECT_STATUS.eq(new JdbcNamedParameter("projectStatus")),
                        PROJECTTABLE.PROJECT_STATGE.eq(new JdbcNamedParameter("projectStatge")),
                        PROJECTTABLE.PROJECT_PRI.eq(new JdbcNamedParameter("projectPri")),
                        PROJECTTABLE.PROJECT_DESC.eq(new JdbcNamedParameter("projectDesc")),
                        PROJECTTABLE.PROJECT_OPENED_BY.eq(new JdbcNamedParameter("projectOpenedBy")),
                        PROJECTTABLE.PROJECT_OPENED_DATE.eq(new JdbcNamedParameter("projectOpenedDate")),
                        PROJECTTABLE.PROJECT_OPENED_VERSION.eq(new JdbcNamedParameter("projectOpenedVersion")),
                        PROJECTTABLE.PROJECT_CLOSE_BY.eq(new JdbcNamedParameter("projectCloseBy")),
                        PROJECTTABLE.PROJECT_CLOSE_DATE.eq(new JdbcNamedParameter("projectCloseDate")),
                        PROJECTTABLE.PROJECT_CANCELED_BY.eq(new JdbcNamedParameter("projectCanceledBy")),
                        PROJECTTABLE.PROJECT_CANCELED_DATE.eq(new JdbcNamedParameter("projectCanceledDate")),
                        PROJECTTABLE.PROJECT_PO.eq(new JdbcNamedParameter("projectPo")),
                        PROJECTTABLE.PROJECT_PM.eq(new JdbcNamedParameter("projectPm")),
                        PROJECTTABLE.PROJECT_QD.eq(new JdbcNamedParameter("projectQd")),
                        PROJECTTABLE.PROJECT_RD.eq(new JdbcNamedParameter("projectRd")),
                        PROJECTTABLE.PROJECT_TEAM.eq(new JdbcNamedParameter("projectTeam")),
                        PROJECTTABLE.PROJECT_ACL.eq(new JdbcNamedParameter("projectAcl")),
                        PROJECTTABLE.PROJECT_WHITE_LIST.eq(new JdbcNamedParameter("projectWhiteList")),
                        PROJECTTABLE.PROJECT_ORDER.eq(new JdbcNamedParameter("projectOrder")),
                        PROJECTTABLE.PROJECT_DELETED.eq(new JdbcNamedParameter("projectDeleted"))));
            }
        });
    }

    private Select addOrderByElements(Select select, OrderBy... orderArgs) {
        List<OrderByElement> orderByElements = new ArrayList<OrderByElement>();
        for (int i = 0; orderArgs != null && i < orderArgs.length; i++) {
            OrderByElement tempElement = orderArgs[i].getOrderByElement();
            if (tempElement != null) {
                orderByElements.add(tempElement);
            }
        }
        if (orderByElements.size() > 0) {
            select.orderBy(orderByElements.toArray(new OrderByElement[0]));
        }
        return select;
    }

    public List<Project> getProjectByStoryId(Integer storyId) {
        Select select = selectFrom(PROJECTTABLE).where(
                PROJECTTABLE.PROJECT_ID.inExpression(subSelect(select(PROJECT_STORYTABLE.PROJECT_ID).from(PROJECT_STORYTABLE).where(PROJECT_STORYTABLE.STORY_ID.eq(storyId))))
                        .and(PROJECTTABLE.PROJECT_DELETED.eq(Project.DELETE_NO))
        );
        return getDslSession().fetchList(select, Project.class);
    }

    public List<Project> findByIds(Integer... ids) {
        Select select = selectFrom(PROJECTTABLE).where(PROJECTTABLE.PROJECT_ID.in(ids));
        return getDslSession().fetchList(select, Project.class);
    }

    public List<Project> findListByTeamUserId(String userId, String acl) {
        Select select = selectFrom(PROJECTTABLE).where(
                and(
                        PROJECTTABLE.PROJECT_ID.inExpression(subSelect(
                                select(PROJECT_TEAMTABLE.PROJECT_ID).from(PROJECT_TEAMTABLE).where(PROJECT_TEAMTABLE.TEAM_USER_ID.eq(userId)))),
                        PROJECTTABLE.PROJECT_ACL.eq(acl),
                        PROJECTTABLE.PROJECT_DELETED.eq(Project.DELETE_NO),
                        PROJECTTABLE.PROJECT_STATGE.neq("3")
                )
        );
        return getDslSession().fetchList(select, Project.class);
    }

    public List<Project> projectInCondition(String condition, Integer... ids) {
        Select select = MysqlSelect.selectFrom(PROJECTTABLE).where(and(PROJECTTABLE.PROJECT_DELETED.eq(0), PROJECTTABLE.PROJECT_ID.in(ids), PROJECTTABLE.PROJECT_NAME.like(condition))).limit(0, 8);
        return getDslSession().fetchList(select, Project.class);
    }
}
