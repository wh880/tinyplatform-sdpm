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
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.project.dao.ProjectTaskDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.dao.pojo.TaskChartBean;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.Join;
import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.tinysqldsl.selectitem.FragmentSelectItemSql;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.org.dao.constant.OrgUserTable.ORG_USERTABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductStorySpecTable.PRODUCT_STORY_SPECTABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductStoryTable.PRODUCT_STORYTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectProductTable.PROJECT_PRODUCTTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectStoryTable.PROJECT_STORYTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectTable.PROJECTTABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectTaskTable.PROJECT_TASKTABLE;
import static org.tinygroup.sdpm.quality.dao.constant.QualityBugTable.QUALITY_BUGTABLE;
import static org.tinygroup.sdpm.system.dao.constant.SystemModuleTable.SYSTEM_MODULETABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.select;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.tinysqldsl.formitem.SubSelect.subSelect;

@Repository
public class ProjectTaskDaoImpl extends TinyDslDaoSupport implements ProjectTaskDao {

    public List<TaskChartBean> queryChartAssigned() {
        Select select = select(PROJECT_TASKTABLE.TASK_ASSIGNED_TO, FragmentSelectItemSql.fragmentSelect("count(*) as taskCount, org_user.ORG_USER_REAL_NAME as title"))
                .from(PROJECT_TASKTABLE)
                .join(Join.leftJoin(ORG_USERTABLE, ORG_USERTABLE.ORG_USER_ID.eq(PROJECT_TASKTABLE.TASK_ASSIGNED_TO)))
                .groupBy(PROJECT_TASKTABLE.TASK_ASSIGNED_TO, ORG_USERTABLE.ORG_USER_REAL_NAME);

        return getDslSession().fetchList(select, TaskChartBean.class);
    }

    public List<TaskChartBean> queryChartStatus() {
        Select select = select(FragmentSelectItemSql.fragmentSelect("count(*) as taskCount, project_task.task_status as title"))
                .from(PROJECT_TASKTABLE)
                .groupBy(PROJECT_TASKTABLE.TASK_STATUS);
        return getDslSession().fetchList(select, TaskChartBean.class);
    }

    public List<TaskChartBean> queryChartPri() {
        Select select = select(FragmentSelectItemSql.fragmentSelect("count(*) as taskCount, project_task.task_pri as title"))
                .from(PROJECT_TASKTABLE)
                .groupBy(PROJECT_TASKTABLE.TASK_PRI);

        return getDslSession().fetchList(select, TaskChartBean.class);
    }

    public List<TaskChartBean> queryChartDeadLine() {
        Select select = select(FragmentSelectItemSql.fragmentSelect("count(*) as taskCount, project_task.task_dead_line as title"))
                .from(PROJECT_TASKTABLE)
                .groupBy(PROJECT_TASKTABLE.TASK_DEAD_LINE);

        return getDslSession().fetchList(select, TaskChartBean.class);
    }

    public List<TaskChartBean> queryChartModule() {
        Select select = select(PROJECT_TASKTABLE.TASK_MOMODULE, FragmentSelectItemSql.fragmentSelect("count(*) as taskCount, system_module.module_name as title"))
                .from(PROJECT_TASKTABLE)
                .join(Join.leftJoin(SYSTEM_MODULETABLE, SYSTEM_MODULETABLE.MODULE_ID.eq(PROJECT_TASKTABLE.TASK_MOMODULE)))
                .groupBy(PROJECT_TASKTABLE.TASK_MOMODULE, SYSTEM_MODULETABLE.MODULE_NAME);

        return getDslSession().fetchList(select, TaskChartBean.class);
    }

    public List<TaskChartBean> queryChartProject() {
        Select select = select(PROJECT_TASKTABLE.TASK_PROJECT, FragmentSelectItemSql.fragmentSelect("count(*) as taskCount, project.project_name as title"))
                .from(PROJECT_TASKTABLE)
                .join(Join.leftJoin(PROJECTTABLE, PROJECT_TASKTABLE.TASK_PROJECT.eq(PROJECTTABLE.PROJECT_ID)))
                .groupBy(PROJECT_TASKTABLE.TASK_PROJECT, PROJECTTABLE.PROJECT_NAME);

        return getDslSession().fetchList(select, TaskChartBean.class);
    }

    public List<TaskChartBean> queryChartType() {
        Select select = select(FragmentSelectItemSql.fragmentSelect("count(*) as taskCount, project_task.task_type as title"))
                .from(PROJECT_TASKTABLE)
                .groupBy(PROJECT_TASKTABLE.TASK_TYPE);

        return getDslSession().fetchList(select, TaskChartBean.class);
    }

    public List<TaskChartBean> queryChartFinishedBy() {
        Select select = select(PROJECT_TASKTABLE.TASK_FINISHED_BY, FragmentSelectItemSql.fragmentSelect("count(*) as taskCount, org_user.ORG_USER_REAL_NAME as title"))
                .from(PROJECT_TASKTABLE, ORG_USERTABLE)
                .where(ORG_USERTABLE.ORG_USER_ID.eq(PROJECT_TASKTABLE.TASK_FINISHED_BY))
                .groupBy(PROJECT_TASKTABLE.TASK_FINISHED_BY, ORG_USERTABLE.ORG_USER_REAL_NAME);
        return getDslSession().fetchList(select, TaskChartBean.class);
    }


    public Integer getSumByStory(Integer storyId) {
        Select select = selectFrom(PROJECT_TASKTABLE).where(PROJECT_TASKTABLE.TASK_STORY.eq(storyId));
        return getDslSession().count(select);
    }


    public ProjectTask add(ProjectTask projectTask) {
        if (projectTask == null) {
            return null;
        }
        return getDslTemplate().insertAndReturnKey(projectTask, new InsertGenerateCallback<ProjectTask>() {
            public Insert generate(ProjectTask t) {
                Insert insert = insertInto(PROJECT_TASKTABLE).values(
                        PROJECT_TASKTABLE.TASK_RELATION_BUG.value(t.getTaskRelationBug()),
                        PROJECT_TASKTABLE.TASK_NO.value(t.getTaskNo()),
                        PROJECT_TASKTABLE.TASK_PROJECT.value(t.getTaskProject()),
                        PROJECT_TASKTABLE.TASK_STORY.value(t.getTaskStory()),
                        PROJECT_TASKTABLE.TASK_STORY_VERSION.value(t.getTaskStoryVersion()),
                        PROJECT_TASKTABLE.TASK_FROM_BUG.value(t.getTaskFromBug()),
                        PROJECT_TASKTABLE.TASK_NAME.value(t.getTaskName()),
                        PROJECT_TASKTABLE.TASK_TYPE.value(t.getTaskType()),
                        PROJECT_TASKTABLE.TASK_MOMODULE.value(t.getTaskModule()),
                        PROJECT_TASKTABLE.TASK_PRI.value(t.getTaskPri()),
                        PROJECT_TASKTABLE.TASK_ESTIMATE.value(t.getTaskEstimate()),
                        PROJECT_TASKTABLE.TASK_LEFT.value(t.getTaskLeft()),
                        PROJECT_TASKTABLE.TASK_CONSUMED.value(t.getTaskConsumed()),
                        PROJECT_TASKTABLE.TASK_DEAD_LINE.value(t.getTaskDeadLine()),
                        PROJECT_TASKTABLE.TASK_STATUS.value(t.getTaskStatus()),
                        PROJECT_TASKTABLE.TASK_MAILTO.value(t.getTaskMailto()),
                        PROJECT_TASKTABLE.TASK_DESC.value(t.getTaskDesc()),
                        PROJECT_TASKTABLE.TASK_OPEN_BY.value(t.getTaskOpenBy()),
                        PROJECT_TASKTABLE.TASK_OPENED_DATE.value(t.getTaskOpenedDate()),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_TO.value(t.getTaskAssignedTo()),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_DATE.value(t.getTaskAssignedDate()),
                        PROJECT_TASKTABLE.TASK_EST_STARED.value(t.getTaskEstStared()),
                        PROJECT_TASKTABLE.TASK_REAL_STARTED.value(t.getTaskRealStarted()),
                        PROJECT_TASKTABLE.TASK_FINISHED_BY.value(t.getTaskFinishedBy()),
                        PROJECT_TASKTABLE.TASK_FINISHED_DATE.value(t.getTaskFinishedDate()),
                        PROJECT_TASKTABLE.TASK_CANCELED_BY.value(t.getTaskCanceledBy()),
                        PROJECT_TASKTABLE.TASK_CANCELED_DATE.value(t.getTaskCanceledDate()),
                        PROJECT_TASKTABLE.TASK_CLOSED_BY.value(t.getTaskClosedBy()),
                        PROJECT_TASKTABLE.TASK_CLOSE_DATE.value(t.getTaskCloseDate()),
                        PROJECT_TASKTABLE.TASK_CLOSED_REASON.value(t.getTaskClosedReason()),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_BY.value(t.getTaskLastEditedBy()),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_DATE.value(t.getTaskLastEditedDate()),
                        PROJECT_TASKTABLE.TASK_DELETED.value(t.getTaskDeleted()));
                return insert;
            }
        });
    }


    public int edit(ProjectTask projectTask) {
        if (projectTask == null || projectTask.getTaskId() == null) {
            return 0;
        }
        return getDslTemplate().update(projectTask, new UpdateGenerateCallback<ProjectTask>()
        {
            public Update generate(ProjectTask t) {
                Update update = update(PROJECT_TASKTABLE).set(
                        PROJECT_TASKTABLE.TASK_RELATION_BUG.value(t.getTaskRelationBug()),
                        PROJECT_TASKTABLE.TASK_NO.value(t.getTaskNo()),
                        PROJECT_TASKTABLE.TASK_PROJECT.value(t.getTaskProject()),
                        PROJECT_TASKTABLE.TASK_STORY.value(t.getTaskStory()),
                        PROJECT_TASKTABLE.TASK_STORY_VERSION.value(t.getTaskStoryVersion()),
                        PROJECT_TASKTABLE.TASK_FROM_BUG.value(t.getTaskFromBug()),
                        PROJECT_TASKTABLE.TASK_NAME.value(t.getTaskName()),
                        PROJECT_TASKTABLE.TASK_TYPE.value(t.getTaskType()),
                        PROJECT_TASKTABLE.TASK_MOMODULE.value(t.getTaskModule()),
                        PROJECT_TASKTABLE.TASK_PRI.value(t.getTaskPri()),
                        PROJECT_TASKTABLE.TASK_ESTIMATE.value(t.getTaskEstimate()),
                        PROJECT_TASKTABLE.TASK_LEFT.value(t.getTaskLeft()),
                        PROJECT_TASKTABLE.TASK_CONSUMED.value(t.getTaskConsumed()),
                        PROJECT_TASKTABLE.TASK_DEAD_LINE.value(t.getTaskDeadLine()),
                        PROJECT_TASKTABLE.TASK_STATUS.value(t.getTaskStatus()),
                        PROJECT_TASKTABLE.TASK_MAILTO.value(t.getTaskMailto()),
                        PROJECT_TASKTABLE.TASK_DESC.value(t.getTaskDesc()),
                        PROJECT_TASKTABLE.TASK_OPEN_BY.value(t.getTaskOpenBy()),
                        PROJECT_TASKTABLE.TASK_OPENED_DATE.value(t.getTaskOpenedDate()),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_TO.value(t.getTaskAssignedTo()),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_DATE.value(t.getTaskAssignedDate()),
                        PROJECT_TASKTABLE.TASK_EST_STARED.value(t.getTaskEstStared()),
                        PROJECT_TASKTABLE.TASK_REAL_STARTED.value(t.getTaskRealStarted()),
                        PROJECT_TASKTABLE.TASK_FINISHED_BY.value(t.getTaskFinishedBy()),
                        PROJECT_TASKTABLE.TASK_FINISHED_DATE.value(t.getTaskFinishedDate()),
                        PROJECT_TASKTABLE.TASK_CANCELED_BY.value(t.getTaskCanceledBy()),
                        PROJECT_TASKTABLE.TASK_CANCELED_DATE.value(t.getTaskCanceledDate()),
                        PROJECT_TASKTABLE.TASK_CLOSED_BY.value(t.getTaskClosedBy()),
                        PROJECT_TASKTABLE.TASK_CLOSE_DATE.value(t.getTaskCloseDate()),
                        PROJECT_TASKTABLE.TASK_CLOSED_REASON.value(t.getTaskClosedReason()),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_BY.value(t.getTaskLastEditedBy()),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_DATE.value(t.getTaskLastEditedDate()),
                        PROJECT_TASKTABLE.TASK_DELETED.value(t.getTaskDeleted())).where(PROJECT_TASKTABLE.TASK_ID.eq(t.getTaskId()));
                return update;
            }
        });
    }

    public ProjectTask findTaskStory(Integer taskId) {
        Select select = select(PROJECT_TASKTABLE.ALL, PRODUCT_STORY_SPECTABLE.STORY_SPEC).from(PROJECT_TASKTABLE)
                .join(Join.leftJoin(
                        PROJECT_STORYTABLE,
                        and(
                                PROJECT_TASKTABLE.TASK_ID.eq(PROJECT_STORYTABLE.STORY_ID),
                                PROJECT_TASKTABLE.TASK_PROJECT.eq(PROJECT_STORYTABLE.PROJECT_ID)
                        )
                        )
                )
                .join(Join.leftJoin(
                        PRODUCT_STORYTABLE, PROJECT_TASKTABLE.TASK_STORY.eq(PRODUCT_STORYTABLE.STORY_ID)
                        )
                )
                .join(Join.leftJoin(
                        PRODUCT_STORY_SPECTABLE,
                        and(PRODUCT_STORY_SPECTABLE.STORY_ID.eq(PRODUCT_STORYTABLE.STORY_ID),
                                PROJECT_TASKTABLE.TASK_STORY_VERSION.eq(PRODUCT_STORY_SPECTABLE.STORY_VERSION)
                        )
                ))
                .where(
                        PROJECT_TASKTABLE.TASK_ID.eq(taskId)
                );
        return getDslSession().fetchOneResult(select, ProjectTask.class);
    }

    public int deleteByKey(Integer pk) {
        if (pk == null) {
            return 0;
        }
        return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
            public Delete generate(Serializable pk) {
                return delete(PROJECT_TASKTABLE).where(PROJECT_TASKTABLE.TASK_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(PROJECT_TASKTABLE).where(PROJECT_TASKTABLE.TASK_ID.in(t));
            }
        }, pks);
    }

    public ProjectTask getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, ProjectTask.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(PROJECT_TASKTABLE).where(PROJECT_TASKTABLE.TASK_ID.eq(t));
            }
        });
    }

    public List<ProjectTask> query(ProjectTask projectTask, final OrderBy... orderArgs) {
        if (projectTask == null) {
            projectTask = new ProjectTask();
        }
        return getDslTemplate().query(projectTask, new SelectGenerateCallback<ProjectTask>() {

            @SuppressWarnings("rawtypes")
            public Select generate(ProjectTask t) {
                Select select = selectFrom(PROJECT_TASKTABLE).where(
                        and(
                                PROJECT_TASKTABLE.TASK_RELATION_BUG.eq(t.getTaskRelationBug()),
                                PROJECT_TASKTABLE.TASK_PROJECT.eq(t.getTaskProject()),
                                PROJECT_TASKTABLE.TASK_NO.eq(t.getTaskNo()),
                                PROJECT_TASKTABLE.TASK_STORY.eq(t.getTaskStory()),
                                PROJECT_TASKTABLE.TASK_STORY_VERSION.eq(t.getTaskStoryVersion()),
                                PROJECT_TASKTABLE.TASK_MOMODULE.eq(t.getTaskModule()),
                                PROJECT_TASKTABLE.TASK_FROM_BUG.eq(t.getTaskFromBug()),
                                PROJECT_TASKTABLE.TASK_NAME.eq(t.getTaskName()),
                                PROJECT_TASKTABLE.TASK_TYPE.eq(t.getTaskType()),
                                PROJECT_TASKTABLE.TASK_PRI.eq(t.getTaskPri()),
                                PROJECT_TASKTABLE.TASK_ESTIMATE.eq(t.getTaskEstimate()),
                                PROJECT_TASKTABLE.TASK_CONSUMED.eq(t.getTaskConsumed()),
                                PROJECT_TASKTABLE.TASK_LEFT.eq(t.getTaskLeft()),
                                PROJECT_TASKTABLE.TASK_DEAD_LINE.eq(t.getTaskDeadLine()),
                                PROJECT_TASKTABLE.TASK_STATUS.eq(t.getTaskStatus()),
                                PROJECT_TASKTABLE.TASK_MAILTO.eq(t.getTaskMailto()),
                                PROJECT_TASKTABLE.TASK_DESC.eq(t.getTaskDesc()),
                                PROJECT_TASKTABLE.TASK_OPEN_BY.eq(t.getTaskOpenBy()),
                                PROJECT_TASKTABLE.TASK_OPENED_DATE.eq(t.getTaskOpenedDate()),
                                PROJECT_TASKTABLE.TASK_ASSIGNED_TO.eq(t.getTaskAssignedTo()),
                                PROJECT_TASKTABLE.TASK_ASSIGNED_DATE.eq(t.getTaskAssignedDate()),
                                PROJECT_TASKTABLE.TASK_EST_STARED.eq(t.getTaskEstStared()),
                                PROJECT_TASKTABLE.TASK_REAL_STARTED.eq(t.getTaskRealStarted()),
                                PROJECT_TASKTABLE.TASK_FINISHED_BY.eq(t.getTaskFinishedBy()),
                                PROJECT_TASKTABLE.TASK_FINISHED_DATE.eq(t.getTaskFinishedDate()),
                                PROJECT_TASKTABLE.TASK_CANCELED_BY.eq(t.getTaskCanceledBy()),
                                PROJECT_TASKTABLE.TASK_CANCELED_DATE.eq(t.getTaskCanceledDate()),
                                PROJECT_TASKTABLE.TASK_CLOSED_BY.eq(t.getTaskClosedBy()),
                                PROJECT_TASKTABLE.TASK_CLOSE_DATE.eq(t.getTaskCloseDate()),
                                PROJECT_TASKTABLE.TASK_CLOSED_REASON.eq(t.getTaskClosedReason()),
                                PROJECT_TASKTABLE.TASK_LAST_EDITED_BY.eq(t.getTaskLastEditedBy()),
                                PROJECT_TASKTABLE.TASK_LAST_EDITED_DATE.eq(t.getTaskLastEditedDate()),
                                PROJECT_TASKTABLE.TASK_DELETED.eq(t.getTaskDeleted())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public ProjectTask getProjectTaskTimeInfo(Integer projectId) {
        Select select = select(
                PROJECT_TASKTABLE.TASK_ESTIMATE.sum().as(PROJECT_TASKTABLE.TASK_ESTIMATE.getColumnName()),
                PROJECT_TASKTABLE.TASK_LEFT.sum().as(PROJECT_TASKTABLE.TASK_LEFT.getColumnName()),
                PROJECT_TASKTABLE.TASK_CONSUMED.sum().as(PROJECT_TASKTABLE.TASK_CONSUMED.getColumnName())
        )
                .from(PROJECT_TASKTABLE)
                .where(PROJECT_TASKTABLE.TASK_PROJECT.eq(projectId));
        return getDslSession().fetchOneResult(select, ProjectTask.class);
    }

    public Integer getMaxNo(Integer projectId) {
        Select select = select(PROJECT_TASKTABLE.TASK_NO.max())
                .from(PROJECT_TASKTABLE)
                .where(PROJECT_TASKTABLE.TASK_PROJECT.eq(projectId));
        return getDslSession().fetchOneResult(select, Integer.class);
    }

    public Pager<ProjectTask> queryPagerByStatus(int start, int limit, ProjectTask projectTask, final Condition condition, final OrderBy... orderArgs) {
        if (projectTask == null) {
            projectTask = new ProjectTask();
        }

        return getDslTemplate().queryPager(start, limit, projectTask, false, new SelectGenerateCallback<ProjectTask>() {

            public Select generate(ProjectTask t) {
                Select select = MysqlSelect.select(PROJECT_TASKTABLE.ALL, PROJECTTABLE.PROJECT_NAME)
                        .from(PROJECT_TASKTABLE)
                        .join(Join.leftJoin(PROJECTTABLE, PROJECT_TASKTABLE.TASK_PROJECT.eq(PROJECTTABLE.PROJECT_ID)))
                        .where(
                                and(
                                        condition,
                                        PROJECT_TASKTABLE.TASK_RELATION_BUG.eq(t.getTaskRelationBug()),
                                        PROJECT_TASKTABLE.TASK_PROJECT.eq(t.getTaskProject()),
                                        PROJECT_TASKTABLE.TASK_NO.eq(t.getTaskNo()),
                                        PROJECT_TASKTABLE.TASK_STORY.eq(t.getTaskStory()),
                                        PROJECT_TASKTABLE.TASK_STORY_VERSION.eq(t.getTaskStoryVersion()),
                                        PROJECT_TASKTABLE.TASK_MOMODULE.eq(t.getTaskModule()),
                                        PROJECT_TASKTABLE.TASK_FROM_BUG.eq(t.getTaskFromBug()),
                                        PROJECT_TASKTABLE.TASK_NAME.eq(t.getTaskName()),
                                        PROJECT_TASKTABLE.TASK_TYPE.eq(t.getTaskType()),
                                        PROJECT_TASKTABLE.TASK_PRI.eq(t.getTaskPri()),
                                        PROJECT_TASKTABLE.TASK_ESTIMATE.eq(t.getTaskEstimate()),
                                        PROJECT_TASKTABLE.TASK_CONSUMED.eq(t.getTaskConsumed()),
                                        PROJECT_TASKTABLE.TASK_LEFT.eq(t.getTaskLeft()),
                                        PROJECT_TASKTABLE.TASK_DEAD_LINE.eq(t.getTaskDeadLine()),
                                        PROJECT_TASKTABLE.TASK_STATUS.eq(t.getTaskStatus()),
                                        PROJECT_TASKTABLE.TASK_MAILTO.eq(t.getTaskMailto()),
                                        PROJECT_TASKTABLE.TASK_DESC.eq(t.getTaskDesc()),
                                        PROJECT_TASKTABLE.TASK_OPEN_BY.eq(t.getTaskOpenBy()),
                                        PROJECT_TASKTABLE.TASK_OPENED_DATE.eq(t.getTaskOpenedDate()),
                                        PROJECT_TASKTABLE.TASK_ASSIGNED_TO.eq(t.getTaskAssignedTo()),
                                        PROJECT_TASKTABLE.TASK_ASSIGNED_DATE.eq(t.getTaskAssignedDate()),
                                        PROJECT_TASKTABLE.TASK_EST_STARED.eq(t.getTaskEstStared()),
                                        PROJECT_TASKTABLE.TASK_REAL_STARTED.eq(t.getTaskRealStarted()),
                                        PROJECT_TASKTABLE.TASK_FINISHED_BY.eq(t.getTaskFinishedBy()),
                                        PROJECT_TASKTABLE.TASK_FINISHED_DATE.eq(t.getTaskFinishedDate()),
                                        PROJECT_TASKTABLE.TASK_CANCELED_BY.eq(t.getTaskCanceledBy()),
                                        PROJECT_TASKTABLE.TASK_CANCELED_DATE.eq(t.getTaskCanceledDate()),
                                        PROJECT_TASKTABLE.TASK_CLOSED_BY.eq(t.getTaskClosedBy()),
                                        PROJECT_TASKTABLE.TASK_CLOSE_DATE.eq(t.getTaskCloseDate()),
                                        PROJECT_TASKTABLE.TASK_CLOSED_REASON.eq(t.getTaskClosedReason()),
                                        PROJECT_TASKTABLE.TASK_LAST_EDITED_BY.eq(t.getTaskLastEditedBy()),
                                        PROJECT_TASKTABLE.TASK_LAST_EDITED_DATE.eq(t.getTaskLastEditedDate()),
                                        PROJECT_TASKTABLE.TASK_DELETED.eq(t.getTaskDeleted())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public Pager<ProjectTask> queryPagerByMe(int start, int limit, ProjectTask projectTask, final OrgUser user, final OrderBy... orderBies) {
        if (projectTask == null) {
            projectTask = new ProjectTask();
        }
        return getDslTemplate().queryPager(start, limit, projectTask, false, new SelectGenerateCallback<ProjectTask>() {

            public Select generate(ProjectTask t) {
                Select select = MysqlSelect.select(PROJECT_TASKTABLE.ALL, PROJECTTABLE.PROJECT_NAME)
                        .from(PROJECT_TASKTABLE)
                        .join(Join.leftJoin(PROJECTTABLE, PROJECT_TASKTABLE.TASK_PROJECT.eq(PROJECTTABLE.PROJECT_ID)))
                        .where(
                                and(
                                        PROJECT_TASKTABLE.TASK_RELATION_BUG.eq(t.getTaskRelationBug()),
                                        PROJECT_TASKTABLE.TASK_PROJECT.eq(t.getTaskProject()),
                                        PROJECT_TASKTABLE.TASK_STORY.eq(t.getTaskStory()),
                                        PROJECT_TASKTABLE.TASK_NO.eq(t.getTaskNo()),
                                        PROJECT_TASKTABLE.TASK_STORY_VERSION.eq(t.getTaskStoryVersion()),
                                        PROJECT_TASKTABLE.TASK_MOMODULE.eq(t.getTaskModule()),
                                        PROJECT_TASKTABLE.TASK_FROM_BUG.eq(t.getTaskFromBug()),
                                        PROJECT_TASKTABLE.TASK_NAME.eq(t.getTaskName()),
                                        PROJECT_TASKTABLE.TASK_TYPE.eq(t.getTaskType()),
                                        PROJECT_TASKTABLE.TASK_PRI.eq(t.getTaskPri()),
                                        PROJECT_TASKTABLE.TASK_ESTIMATE.eq(t.getTaskEstimate()),
                                        PROJECT_TASKTABLE.TASK_CONSUMED.eq(t.getTaskConsumed()),
                                        PROJECT_TASKTABLE.TASK_LEFT.eq(t.getTaskLeft()),
                                        PROJECT_TASKTABLE.TASK_DEAD_LINE.eq(t.getTaskDeadLine()),
                                        PROJECT_TASKTABLE.TASK_STATUS.eq(t.getTaskStatus()),
                                        PROJECT_TASKTABLE.TASK_MAILTO.eq(t.getTaskMailto()),
                                        PROJECT_TASKTABLE.TASK_DESC.eq(t.getTaskDesc()),
                                        PROJECT_TASKTABLE.TASK_OPEN_BY.eq(t.getTaskOpenBy()),
                                        PROJECT_TASKTABLE.TASK_OPENED_DATE.eq(t.getTaskOpenedDate()),
                                        PROJECT_TASKTABLE.TASK_ASSIGNED_TO.eq(t.getTaskAssignedTo()),
                                        PROJECT_TASKTABLE.TASK_ASSIGNED_DATE.eq(t.getTaskAssignedDate()),
                                        PROJECT_TASKTABLE.TASK_EST_STARED.eq(t.getTaskEstStared()),
                                        PROJECT_TASKTABLE.TASK_REAL_STARTED.eq(t.getTaskRealStarted()),
                                        PROJECT_TASKTABLE.TASK_FINISHED_BY.eq(user.getOrgUserId()),
                                        PROJECT_TASKTABLE.TASK_FINISHED_DATE.eq(t.getTaskFinishedDate()),
                                        PROJECT_TASKTABLE.TASK_CANCELED_BY.eq(t.getTaskCanceledBy()),
                                        PROJECT_TASKTABLE.TASK_CANCELED_DATE.eq(t.getTaskCanceledDate()),
                                        PROJECT_TASKTABLE.TASK_CLOSED_BY.eq(t.getTaskClosedBy()),
                                        PROJECT_TASKTABLE.TASK_CLOSE_DATE.eq(t.getTaskCloseDate()),
                                        PROJECT_TASKTABLE.TASK_CLOSED_REASON.eq(t.getTaskClosedReason()),
                                        PROJECT_TASKTABLE.TASK_LAST_EDITED_BY.eq(t.getTaskLastEditedBy()),
                                        PROJECT_TASKTABLE.TASK_LAST_EDITED_DATE.eq(t.getTaskLastEditedDate()),
                                        PROJECT_TASKTABLE.TASK_DELETED.eq(t.getTaskDeleted())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public Pager<ProjectTask> queryPager(int start, int limit, ProjectTask projectTask, final OrderBy... orderArgs) {
        if (projectTask == null) {
            projectTask = new ProjectTask();
        }
        return getDslTemplate().queryPager(start, limit, projectTask, false, new SelectGenerateCallback<ProjectTask>() {

            public Select generate(ProjectTask t) {
                Select select = MysqlSelect.select(PROJECT_TASKTABLE.ALL, PROJECTTABLE.PROJECT_NAME)
                        .from(PROJECT_TASKTABLE)
                        .join(Join.leftJoin(PROJECTTABLE, PROJECT_TASKTABLE.TASK_PROJECT.eq(PROJECTTABLE.PROJECT_ID)))
                        .where(
                                and(
                                        PROJECT_TASKTABLE.TASK_RELATION_BUG.eq(t.getTaskRelationBug()),
                                        PROJECT_TASKTABLE.TASK_PROJECT.eq(t.getTaskProject()),
                                        PROJECT_TASKTABLE.TASK_NO.eq(t.getTaskNo()),
                                        PROJECT_TASKTABLE.TASK_STORY.eq(t.getTaskStory()),
                                        PROJECT_TASKTABLE.TASK_STORY_VERSION.eq(t.getTaskStoryVersion()),
                                        PROJECT_TASKTABLE.TASK_MOMODULE.eq(t.getTaskModule()),
                                        PROJECT_TASKTABLE.TASK_FROM_BUG.eq(t.getTaskFromBug()),
                                        PROJECT_TASKTABLE.TASK_NAME.eq(t.getTaskName()),
                                        PROJECT_TASKTABLE.TASK_TYPE.eq(t.getTaskType()),
                                        PROJECT_TASKTABLE.TASK_PRI.eq(t.getTaskPri()),
                                        PROJECT_TASKTABLE.TASK_ESTIMATE.eq(t.getTaskEstimate()),
                                        PROJECT_TASKTABLE.TASK_CONSUMED.eq(t.getTaskConsumed()),
                                        PROJECT_TASKTABLE.TASK_LEFT.eq(t.getTaskLeft()),
                                        PROJECT_TASKTABLE.TASK_DEAD_LINE.eq(t.getTaskDeadLine()),
                                        PROJECT_TASKTABLE.TASK_STATUS.eq(t.getTaskStatus()),
                                        PROJECT_TASKTABLE.TASK_MAILTO.eq(t.getTaskMailto()),
                                        PROJECT_TASKTABLE.TASK_DESC.eq(t.getTaskDesc()),
                                        PROJECT_TASKTABLE.TASK_OPEN_BY.eq(t.getTaskOpenBy()),
                                        PROJECT_TASKTABLE.TASK_OPENED_DATE.eq(t.getTaskOpenedDate()),
                                        PROJECT_TASKTABLE.TASK_ASSIGNED_TO.eq(t.getTaskAssignedTo()),
                                        PROJECT_TASKTABLE.TASK_ASSIGNED_DATE.eq(t.getTaskAssignedDate()),
                                        PROJECT_TASKTABLE.TASK_EST_STARED.eq(t.getTaskEstStared()),
                                        PROJECT_TASKTABLE.TASK_REAL_STARTED.eq(t.getTaskRealStarted()),
                                        PROJECT_TASKTABLE.TASK_FINISHED_BY.eq(t.getTaskFinishedBy()),
                                        PROJECT_TASKTABLE.TASK_FINISHED_DATE.eq(t.getTaskFinishedDate()),
                                        PROJECT_TASKTABLE.TASK_CANCELED_BY.eq(t.getTaskCanceledBy()),
                                        PROJECT_TASKTABLE.TASK_CANCELED_DATE.eq(t.getTaskCanceledDate()),
                                        PROJECT_TASKTABLE.TASK_CLOSED_BY.eq(t.getTaskClosedBy()),
                                        PROJECT_TASKTABLE.TASK_CLOSE_DATE.eq(t.getTaskCloseDate()),
                                        PROJECT_TASKTABLE.TASK_CLOSED_REASON.eq(t.getTaskClosedReason()),
                                        PROJECT_TASKTABLE.TASK_LAST_EDITED_BY.eq(t.getTaskLastEditedBy()),
                                        PROJECT_TASKTABLE.TASK_LAST_EDITED_DATE.eq(t.getTaskLastEditedDate()),
                                        PROJECT_TASKTABLE.TASK_DELETED.eq(t.getTaskDeleted())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<ProjectTask> projectTasks) {
        if (CollectionUtil.isEmpty(projectTasks)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, projectTasks, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(PROJECT_TASKTABLE).values(
                        PROJECT_TASKTABLE.TASK_RELATION_BUG.value(new JdbcNamedParameter("taskRelationBug")),
                        PROJECT_TASKTABLE.TASK_PROJECT.value(new JdbcNamedParameter("taskProject")),
                        PROJECT_TASKTABLE.TASK_STORY.value(new JdbcNamedParameter("taskStory")),
                        PROJECT_TASKTABLE.TASK_NO.value(new JdbcNamedParameter("no")),
                        PROJECT_TASKTABLE.TASK_STORY_VERSION.value(new JdbcNamedParameter("taskStoryVersion")),
                        PROJECT_TASKTABLE.TASK_MOMODULE.value(new JdbcNamedParameter("taskModel")),
                        PROJECT_TASKTABLE.TASK_FROM_BUG.value(new JdbcNamedParameter("taskFromBug")),
                        PROJECT_TASKTABLE.TASK_NAME.value(new JdbcNamedParameter("taskName")),
                        PROJECT_TASKTABLE.TASK_TYPE.value(new JdbcNamedParameter("taskType")),
                        PROJECT_TASKTABLE.TASK_PRI.value(new JdbcNamedParameter("taskPri")),
                        PROJECT_TASKTABLE.TASK_ESTIMATE.value(new JdbcNamedParameter("taskEstimate")),
                        PROJECT_TASKTABLE.TASK_CONSUMED.value(new JdbcNamedParameter("taskConsumed")),
                        PROJECT_TASKTABLE.TASK_LEFT.value(new JdbcNamedParameter("taskLeft")),
                        PROJECT_TASKTABLE.TASK_DEAD_LINE.value(new JdbcNamedParameter("taskDeadLine")),
                        PROJECT_TASKTABLE.TASK_STATUS.value(new JdbcNamedParameter("taskStatus")),
                        PROJECT_TASKTABLE.TASK_MAILTO.value(new JdbcNamedParameter("taskMailto")),
                        PROJECT_TASKTABLE.TASK_DESC.value(new JdbcNamedParameter("taskDesc")),
                        PROJECT_TASKTABLE.TASK_OPEN_BY.value(new JdbcNamedParameter("taskOpenBy")),
                        PROJECT_TASKTABLE.TASK_OPENED_DATE.value(new JdbcNamedParameter("taskOpenedDate")),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_TO.value(new JdbcNamedParameter("taskAssignedTo")),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_DATE.value(new JdbcNamedParameter("taskAssignedDate")),
                        PROJECT_TASKTABLE.TASK_EST_STARED.value(new JdbcNamedParameter("taskEstStared")),
                        PROJECT_TASKTABLE.TASK_REAL_STARTED.value(new JdbcNamedParameter("taskRealStarted")),
                        PROJECT_TASKTABLE.TASK_FINISHED_BY.value(new JdbcNamedParameter("taskFinishedBy")),
                        PROJECT_TASKTABLE.TASK_FINISHED_DATE.value(new JdbcNamedParameter("taskFinishedDate")),
                        PROJECT_TASKTABLE.TASK_CANCELED_BY.value(new JdbcNamedParameter("taskCanceledBy")),
                        PROJECT_TASKTABLE.TASK_CANCELED_DATE.value(new JdbcNamedParameter("taskCanceledDate")),
                        PROJECT_TASKTABLE.TASK_CLOSED_BY.value(new JdbcNamedParameter("taskClosedBy")),
                        PROJECT_TASKTABLE.TASK_CLOSE_DATE.value(new JdbcNamedParameter("taskCloseDate")),
                        PROJECT_TASKTABLE.TASK_CLOSED_REASON.value(new JdbcNamedParameter("taskClosedReason")),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_BY.value(new JdbcNamedParameter("taskLastEditedBy")),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_DATE.value(new JdbcNamedParameter("taskLastEditedDate")),
                        PROJECT_TASKTABLE.TASK_DELETED.value(new JdbcNamedParameter("taskDeleted")));
            }
        });
    }

    public int[] batchInsert(List<ProjectTask> projectTasks) {
        return batchInsert(true, projectTasks);
    }

    public int[] batchUpdate(List<ProjectTask> projectTasks) {
        if (CollectionUtil.isEmpty(projectTasks)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(projectTasks, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(PROJECT_TASKTABLE).set(
                        PROJECT_TASKTABLE.TASK_RELATION_BUG.value(new JdbcNamedParameter("taskRelationBug")),
                        PROJECT_TASKTABLE.TASK_PROJECT.value(new JdbcNamedParameter("taskProject")),
                        PROJECT_TASKTABLE.TASK_STORY.value(new JdbcNamedParameter("taskStory")),
                        PROJECT_TASKTABLE.TASK_NO.value(new JdbcNamedParameter("no")),
                        PROJECT_TASKTABLE.TASK_STORY_VERSION.value(new JdbcNamedParameter("taskStoryVersion")),
                        PROJECT_TASKTABLE.TASK_MOMODULE.value(new JdbcNamedParameter("taskModel")),
                        PROJECT_TASKTABLE.TASK_FROM_BUG.value(new JdbcNamedParameter("taskFromBug")),
                        PROJECT_TASKTABLE.TASK_NAME.value(new JdbcNamedParameter("taskName")),
                        PROJECT_TASKTABLE.TASK_TYPE.value(new JdbcNamedParameter("taskType")),
                        PROJECT_TASKTABLE.TASK_PRI.value(new JdbcNamedParameter("taskPri")),
                        PROJECT_TASKTABLE.TASK_ESTIMATE.value(new JdbcNamedParameter("taskEstimate")),
                        PROJECT_TASKTABLE.TASK_CONSUMED.value(new JdbcNamedParameter("taskConsumed")),
                        PROJECT_TASKTABLE.TASK_LEFT.value(new JdbcNamedParameter("taskLeft")),
                        PROJECT_TASKTABLE.TASK_DEAD_LINE.value(new JdbcNamedParameter("taskDeadLine")),
                        PROJECT_TASKTABLE.TASK_STATUS.value(new JdbcNamedParameter("taskStatus")),
                        PROJECT_TASKTABLE.TASK_MAILTO.value(new JdbcNamedParameter("taskMailto")),
                        PROJECT_TASKTABLE.TASK_DESC.value(new JdbcNamedParameter("taskDesc")),
                        PROJECT_TASKTABLE.TASK_OPEN_BY.value(new JdbcNamedParameter("taskOpenBy")),
                        PROJECT_TASKTABLE.TASK_OPENED_DATE.value(new JdbcNamedParameter("taskOpenedDate")),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_TO.value(new JdbcNamedParameter("taskAssignedTo")),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_DATE.value(new JdbcNamedParameter("taskAssignedDate")),
                        PROJECT_TASKTABLE.TASK_EST_STARED.value(new JdbcNamedParameter("taskEstStared")),
                        PROJECT_TASKTABLE.TASK_REAL_STARTED.value(new JdbcNamedParameter("taskRealStarted")),
                        PROJECT_TASKTABLE.TASK_FINISHED_BY.value(new JdbcNamedParameter("taskFinishedBy")),
                        PROJECT_TASKTABLE.TASK_FINISHED_DATE.value(new JdbcNamedParameter("taskFinishedDate")),
                        PROJECT_TASKTABLE.TASK_CANCELED_BY.value(new JdbcNamedParameter("taskCanceledBy")),
                        PROJECT_TASKTABLE.TASK_CANCELED_DATE.value(new JdbcNamedParameter("taskCanceledDate")),
                        PROJECT_TASKTABLE.TASK_CLOSED_BY.value(new JdbcNamedParameter("taskClosedBy")),
                        PROJECT_TASKTABLE.TASK_CLOSE_DATE.value(new JdbcNamedParameter("taskCloseDate")),
                        PROJECT_TASKTABLE.TASK_CLOSED_REASON.value(new JdbcNamedParameter("taskClosedReason")),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_BY.value(new JdbcNamedParameter("taskLastEditedBy")),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_DATE.value(new JdbcNamedParameter("taskLastEditedDate")),
                        PROJECT_TASKTABLE.TASK_DELETED.value(new JdbcNamedParameter("taskDeleted")))
                        .where(PROJECT_TASKTABLE.TASK_ID.eq(new JdbcNamedParameter("taskId")));
            }
        });
    }

    public int[] batchDelete(List<ProjectTask> projectTasks) {
        if (CollectionUtil.isEmpty(projectTasks)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(projectTasks, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(PROJECT_TASKTABLE).where(and(
                        PROJECT_TASKTABLE.TASK_RELATION_BUG.eq(new JdbcNamedParameter("taskRelationBug")),
                        PROJECT_TASKTABLE.TASK_ID.eq(new JdbcNamedParameter("taskId")),
                        PROJECT_TASKTABLE.TASK_PROJECT.eq(new JdbcNamedParameter("taskProject")),
                        PROJECT_TASKTABLE.TASK_STORY.eq(new JdbcNamedParameter("taskStory")),
                        PROJECT_TASKTABLE.TASK_NO.eq(new JdbcNamedParameter("no")),
                        PROJECT_TASKTABLE.TASK_STORY_VERSION.eq(new JdbcNamedParameter("taskStoryVersion")),
                        PROJECT_TASKTABLE.TASK_MOMODULE.eq(new JdbcNamedParameter("taskModule")),
                        PROJECT_TASKTABLE.TASK_FROM_BUG.eq(new JdbcNamedParameter("taskFromBug")),
                        PROJECT_TASKTABLE.TASK_NAME.eq(new JdbcNamedParameter("taskName")),
                        PROJECT_TASKTABLE.TASK_TYPE.eq(new JdbcNamedParameter("taskType")),
                        PROJECT_TASKTABLE.TASK_PRI.eq(new JdbcNamedParameter("taskPri")),
                        PROJECT_TASKTABLE.TASK_ESTIMATE.eq(new JdbcNamedParameter("taskEstimate")),
                        PROJECT_TASKTABLE.TASK_CONSUMED.eq(new JdbcNamedParameter("taskConsumed")),
                        PROJECT_TASKTABLE.TASK_LEFT.eq(new JdbcNamedParameter("taskLeft")),
                        PROJECT_TASKTABLE.TASK_DEAD_LINE.eq(new JdbcNamedParameter("taskDeadLine")),
                        PROJECT_TASKTABLE.TASK_STATUS.eq(new JdbcNamedParameter("taskStatus")),
                        PROJECT_TASKTABLE.TASK_MAILTO.eq(new JdbcNamedParameter("taskMailto")),
                        PROJECT_TASKTABLE.TASK_DESC.eq(new JdbcNamedParameter("taskDesc")),
                        PROJECT_TASKTABLE.TASK_OPEN_BY.eq(new JdbcNamedParameter("taskOpenBy")),
                        PROJECT_TASKTABLE.TASK_OPENED_DATE.eq(new JdbcNamedParameter("taskOpenedDate")),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_TO.eq(new JdbcNamedParameter("taskAssignedTo")),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_DATE.eq(new JdbcNamedParameter("taskAssignedDate")),
                        PROJECT_TASKTABLE.TASK_EST_STARED.eq(new JdbcNamedParameter("taskEstStared")),
                        PROJECT_TASKTABLE.TASK_REAL_STARTED.eq(new JdbcNamedParameter("taskRealStarted")),
                        PROJECT_TASKTABLE.TASK_FINISHED_BY.eq(new JdbcNamedParameter("taskFinishedBy")),
                        PROJECT_TASKTABLE.TASK_FINISHED_DATE.eq(new JdbcNamedParameter("taskFinishedDate")),
                        PROJECT_TASKTABLE.TASK_CANCELED_BY.eq(new JdbcNamedParameter("taskCanceledBy")),
                        PROJECT_TASKTABLE.TASK_CANCELED_DATE.eq(new JdbcNamedParameter("taskCanceledDate")),
                        PROJECT_TASKTABLE.TASK_CLOSED_BY.eq(new JdbcNamedParameter("taskClosedBy")),
                        PROJECT_TASKTABLE.TASK_CLOSE_DATE.eq(new JdbcNamedParameter("taskCloseDate")),
                        PROJECT_TASKTABLE.TASK_CLOSED_REASON.eq(new JdbcNamedParameter("taskClosedReason")),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_BY.eq(new JdbcNamedParameter("taskLastEditedBy")),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_DATE.eq(new JdbcNamedParameter("taskLastEditedDate")),
                        PROJECT_TASKTABLE.TASK_DELETED.eq(new JdbcNamedParameter("taskDeleted"))));
            }
        });
    }

    private Select addOrderByElements(Select select, OrderBy... orderArgs) {
        if (orderArgs == null || orderArgs.length == 0) {
            return select;
        }
        List<OrderByElement> orderByElements = new ArrayList<OrderByElement>();
        for (int i = 0; i < orderArgs.length; i++) {
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

    /**
     * 根据产品id查询待关联的bug list
     */
    @Override
    public List<QualityBug> findRelationBugByProjectID(Integer projectId) {
        /*Select select = selectFrom(QUALITY_BUGTABLE)
                .where(QUALITY_BUGTABLE.BUG_ID
                        .inExpression(subSelect(select(PROJECT_PRODUCTTABLE.PRODUCT_ID)
                                .from(PROJECT_PRODUCTTABLE)
                                .where(PROJECT_PRODUCTTABLE.PROJECT_ID.eq(projectId)))));*/
        Select select = selectFrom(QUALITY_BUGTABLE).where(and(QUALITY_BUGTABLE.PROJECT_ID.eq(projectId),QUALITY_BUGTABLE.DELETED.eq(0)));
        return getDslSession().fetchList(select, QualityBug.class);
    }

    @Override
    public ProjectTask findTaskByTaskId(Integer taskId) {
        Select select = Select.selectFrom(PROJECT_TASKTABLE).where(PROJECT_TASKTABLE.TASK_ID.eq(taskId));
        return getDslSession().fetchOneResult(select, ProjectTask.class);
    }
}
