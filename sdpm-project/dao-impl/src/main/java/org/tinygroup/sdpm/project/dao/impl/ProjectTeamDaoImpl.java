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
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.project.dao.ProjectTeamDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.Join;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.org.dao.constant.OrgRoleMenuTable.ORG_ROLE_MENUTABLE;
import static org.tinygroup.sdpm.org.dao.constant.OrgRoleTable.ORG_ROLETABLE;
import static org.tinygroup.sdpm.project.dao.constant.ProjectTeamTable.PROJECT_TEAMTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class ProjectTeamDaoImpl extends TinyDslDaoSupport implements ProjectTeamDao {

    public List<ProjectTeam> findByProjectId(Integer projectId) {
        Select select = selectFrom(PROJECT_TEAMTABLE).where(PROJECT_TEAMTABLE.PROJECT_ID.eq(projectId));
        return getDslSession().fetchList(select, ProjectTeam.class);
    }

    public List<ProjectTeam> findByProductId(Integer productId) {
        Select select = selectFrom(PROJECT_TEAMTABLE).where(PROJECT_TEAMTABLE.PRODUCT_ID.eq(productId));
        return getDslSession().fetchList(select, ProjectTeam.class);
    }


    public List<String> getMenuByUserId(Integer projectId, Integer productId, String userId) {
        List<Integer> roleIdListByProjectIdAndUserId = getRoleIdListByUserId(projectId, productId, userId);
        if (CollectionUtil.isEmpty(roleIdListByProjectIdAndUserId)) {
            return new ArrayList<String>();
        }
        Select select = Select.select(ORG_ROLE_MENUTABLE.ORG_ROLE_MENU_ID).from(ORG_ROLE_MENUTABLE).where(ORG_ROLE_MENUTABLE.ORG_ROLE_ID.in(roleIdListByProjectIdAndUserId.toArray()));
        return getDslSession().fetchList(select, String.class);
    }

    public List<Integer> getRoleIdListByUserId(Integer projectId, Integer productId, String userId) {
        List<Integer> roleIdList = new ArrayList<Integer>();
        ProjectTeam projectTeam = new ProjectTeam();
        projectTeam.setProjectId(projectId);
        projectTeam.setTeamUserId(userId);
        projectTeam.setProductId(productId);
        List<ProjectTeam> list = query(projectTeam);
        if (!CollectionUtil.isEmpty(list)) {
            projectTeam = list.get(0);
            String teamRole = projectTeam.getTeamRole();
            if (!StringUtil.isBlank(teamRole)) {
                String[] split = teamRole.split(",");
                for (String roleId : split) {
                    if (StringUtil.isBlank(roleId)) {
                        continue;
                    }
                    roleIdList.add(Integer.valueOf(roleId));
                }
            }
        }
        return roleIdList;
    }


    public ProjectTeam add(ProjectTeam projectTeam) {
        return getDslTemplate().insertAndReturnKey(projectTeam, new InsertGenerateCallback<ProjectTeam>() {
            public Insert generate(ProjectTeam t) {
                Insert insert = insertInto(PROJECT_TEAMTABLE).values(
                        PROJECT_TEAMTABLE.ID.value(t.getId()),
                        PROJECT_TEAMTABLE.PRODUCT_ID.value(t.getProductId()),
                        PROJECT_TEAMTABLE.PROJECT_ID.value(t.getProjectId()),
                        PROJECT_TEAMTABLE.TEAM_USER_ID.value(t.getTeamUserId()),
                        PROJECT_TEAMTABLE.TEAM_ROLE.value(t.getTeamRole()),
                        PROJECT_TEAMTABLE.TEAM_JOIN.value(t.getTeamJoin()),
                        PROJECT_TEAMTABLE.TEAM_DAYS.value(t.getTeamDays()),
                        PROJECT_TEAMTABLE.TEAM_HOURS.value(t.getTeamHours()));
                return insert;
            }
        });
    }

    public int edit(ProjectTeam projectTeam) {
        if (projectTeam == null || projectTeam.getId() == null) {
            return 0;
        }
        return getDslTemplate().update(projectTeam, new UpdateGenerateCallback<ProjectTeam>() {
            public Update generate(ProjectTeam t) {
                Update update = update(PROJECT_TEAMTABLE).set(
                        PROJECT_TEAMTABLE.PRODUCT_ID.value(t.getProductId()),
                        PROJECT_TEAMTABLE.PROJECT_ID.value(t.getProjectId()),
                        PROJECT_TEAMTABLE.TEAM_USER_ID.value(t.getTeamUserId()),
                        PROJECT_TEAMTABLE.TEAM_ROLE.value(t.getTeamRole()),
                        PROJECT_TEAMTABLE.TEAM_JOIN.value(t.getTeamJoin()),
                        PROJECT_TEAMTABLE.TEAM_DAYS.value(t.getTeamDays()),
                        PROJECT_TEAMTABLE.TEAM_HOURS.value(t.getTeamHours())).where(
                        PROJECT_TEAMTABLE.ID.eq(t.getId()));
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
                return delete(PROJECT_TEAMTABLE).where(PROJECT_TEAMTABLE.ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(PROJECT_TEAMTABLE).where(PROJECT_TEAMTABLE.ID.in(t));
            }
        }, pks);
    }

    public ProjectTeam getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, ProjectTeam.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(PROJECT_TEAMTABLE).where(PROJECT_TEAMTABLE.ID.eq(t));
            }
        });
    }

    public List<ProjectTeam> query(ProjectTeam projectTeam, final OrderBy... orderBies) {
        if (projectTeam == null) {
            projectTeam = new ProjectTeam();
        }
        return getDslTemplate().query(projectTeam, new SelectGenerateCallback<ProjectTeam>() {

            @SuppressWarnings("rawtypes")
            public Select generate(ProjectTeam t) {
                Select select = selectFrom(PROJECT_TEAMTABLE).where(
                        and(
                                PROJECT_TEAMTABLE.PRODUCT_ID.eq(t.getProductId()),
                                PROJECT_TEAMTABLE.PROJECT_ID.eq(t.getProjectId()),
                                PROJECT_TEAMTABLE.TEAM_USER_ID.eq(t.getTeamUserId()),
                                PROJECT_TEAMTABLE.TEAM_ROLE.eq(t.getTeamRole()),
                                PROJECT_TEAMTABLE.TEAM_JOIN.eq(t.getTeamJoin()),
                                PROJECT_TEAMTABLE.TEAM_DAYS.eq(t.getTeamDays()),
                                PROJECT_TEAMTABLE.TEAM_HOURS.eq(t.getTeamHours())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public Pager<ProjectTeam> queryPager(int start, int limit, ProjectTeam projectTeam, final OrderBy... orderBies) {
        if (projectTeam == null) {
            projectTeam = new ProjectTeam();
        }
        return getDslTemplate().queryPager(start, limit, projectTeam, false, new SelectGenerateCallback<ProjectTeam>() {

            public Select generate(ProjectTeam t) {
                Select select = MysqlSelect.select(PROJECT_TEAMTABLE.ALL, ORG_ROLETABLE.ORG_ROLE_NAME.as("roleName")).from(PROJECT_TEAMTABLE).join(
                        Join.leftJoin(ORG_ROLETABLE, ORG_ROLETABLE.ORG_ROLE_ID.eq(PROJECT_TEAMTABLE.TEAM_ROLE))
                ).where(
                        and(
                                PROJECT_TEAMTABLE.PRODUCT_ID.eq(t.getProductId()),
                                PROJECT_TEAMTABLE.PROJECT_ID.eq(t.getProjectId()),
                                PROJECT_TEAMTABLE.TEAM_USER_ID.eq(t.getTeamUserId()),
                                PROJECT_TEAMTABLE.TEAM_ROLE.eq(t.getTeamRole()),
                                PROJECT_TEAMTABLE.TEAM_JOIN.eq(t.getTeamJoin()),
                                PROJECT_TEAMTABLE.TEAM_DAYS.eq(t.getTeamDays()),
                                PROJECT_TEAMTABLE.TEAM_HOURS.eq(t.getTeamHours())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<ProjectTeam> projectTeams) {
        if (CollectionUtil.isEmpty(projectTeams)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, projectTeams, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(PROJECT_TEAMTABLE).values(
                        PROJECT_TEAMTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
                        PROJECT_TEAMTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
                        PROJECT_TEAMTABLE.TEAM_USER_ID.value(new JdbcNamedParameter("teamUserId")),
                        PROJECT_TEAMTABLE.TEAM_ROLE.value(new JdbcNamedParameter("teamRole")),
                        PROJECT_TEAMTABLE.TEAM_JOIN.value(new JdbcNamedParameter("teamJoin")),
                        PROJECT_TEAMTABLE.TEAM_DAYS.value(new JdbcNamedParameter("teamDays")),
                        PROJECT_TEAMTABLE.TEAM_HOURS.value(new JdbcNamedParameter("teamHours")));
            }
        });
    }

    public int[] batchInsert(List<ProjectTeam> projectTeams) {
        return batchInsert(true, projectTeams);
    }

    public int[] batchUpdate(List<ProjectTeam> projectTeams) {
        if (CollectionUtil.isEmpty(projectTeams)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(projectTeams, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(PROJECT_TEAMTABLE).set(
                        PROJECT_TEAMTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
                        PROJECT_TEAMTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
                        PROJECT_TEAMTABLE.TEAM_USER_ID.value(new JdbcNamedParameter("teamUserId")),
                        PROJECT_TEAMTABLE.TEAM_ROLE.value(new JdbcNamedParameter("teamRole")),
                        PROJECT_TEAMTABLE.TEAM_JOIN.value(new JdbcNamedParameter("teamJoin")),
                        PROJECT_TEAMTABLE.TEAM_DAYS.value(new JdbcNamedParameter("teamDays")),
                        PROJECT_TEAMTABLE.TEAM_HOURS.value(new JdbcNamedParameter("teamHours"))).where(
                        PROJECT_TEAMTABLE.ID.eq(new JdbcNamedParameter("id")));
            }
        });
    }

    public int[] batchDelete(List<ProjectTeam> projectTeams) {
        if (CollectionUtil.isEmpty(projectTeams)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(projectTeams, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(PROJECT_TEAMTABLE).where(and(
                        PROJECT_TEAMTABLE.ID.eq(new JdbcNamedParameter("id")),
                        PROJECT_TEAMTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
                        PROJECT_TEAMTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectId")),
                        PROJECT_TEAMTABLE.TEAM_USER_ID.eq(new JdbcNamedParameter("teamUserId")),
                        PROJECT_TEAMTABLE.TEAM_ROLE.eq(new JdbcNamedParameter("teamRole")),
                        PROJECT_TEAMTABLE.TEAM_JOIN.eq(new JdbcNamedParameter("teamJoin")),
                        PROJECT_TEAMTABLE.TEAM_DAYS.eq(new JdbcNamedParameter("teamDays")),
                        PROJECT_TEAMTABLE.TEAM_HOURS.eq(new JdbcNamedParameter("teamHours"))));
            }
        });
    }

    private Select addOrderByElements(Select select, OrderBy... orderByArgs) {
        List<OrderByElement> orderByElements = new ArrayList<OrderByElement>();
        for (int i = 0; orderByArgs != null && i < orderByArgs.length; i++) {
            OrderByElement tempElement = orderByArgs[i].getOrderByElement();
            if (tempElement != null) {
                orderByElements.add(tempElement);
            }
        }
        if (orderByElements.size() > 0) {
            select.orderBy(orderByElements.toArray(new OrderByElement[0]));
        }
        return select;
    }
}
