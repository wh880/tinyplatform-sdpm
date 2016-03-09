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

package org.tinygroup.sdpm.org.dao.impl;

import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.org.dao.OrgRoleDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.base.FragmentSql;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.formitem.SubSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.org.dao.constant.OrgRoleTable.ORG_ROLETABLE;
import static org.tinygroup.sdpm.org.dao.constant.OrgRoleUserTable.ORG_ROLE_USERTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class OrgRoleDaoImpl extends TinyDslDaoSupport implements OrgRoleDao {

    public OrgRole add(OrgRole orgRole) {
        return getDslTemplate().insertAndReturnKey(orgRole, new InsertGenerateCallback<OrgRole>() {
            public Insert generate(OrgRole t) {
                Insert insert = insertInto(ORG_ROLETABLE).values(
                        ORG_ROLETABLE.ORG_ROLE_ID.value(t.getOrgRoleId()),
                        ORG_ROLETABLE.ORG_ROLE_NAME.value(t.getOrgRoleName()),
                        ORG_ROLETABLE.ORG_ROLE_TYPE.value(t.getOrgRoleType()),
                        ORG_ROLETABLE.ORG_ROLE_REMARKS.value(t.getOrgRoleRemarks()),
                        ORG_ROLETABLE.DELETED.value(t.getDeleted()));
                return insert;
            }
        });
    }

    public int edit(OrgRole orgRole) {
        if (orgRole == null || orgRole.getOrgRoleId() == null) {
            return 0;
        }
        return getDslTemplate().update(orgRole, new UpdateGenerateCallback<OrgRole>() {
            public Update generate(OrgRole t) {
                Update update = update(ORG_ROLETABLE).set(
                        ORG_ROLETABLE.ORG_ROLE_NAME.value(t.getOrgRoleName()),
                        ORG_ROLETABLE.ORG_ROLE_TYPE.value(t.getOrgRoleType()),
                        ORG_ROLETABLE.ORG_ROLE_REMARKS.value(t.getOrgRoleRemarks()),
                        ORG_ROLETABLE.DELETED.value(t.getDeleted())).where(
                        ORG_ROLETABLE.ORG_ROLE_ID.eq(t.getOrgRoleId()));
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
                return delete(ORG_ROLETABLE).where(ORG_ROLETABLE.ORG_ROLE_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(ORG_ROLETABLE).where(ORG_ROLETABLE.ORG_ROLE_ID.in(t));
            }
        }, pks);
    }

    public OrgRole getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, OrgRole.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(ORG_ROLETABLE).where(ORG_ROLETABLE.ORG_ROLE_ID.eq(t));
            }
        });
    }

    public List<OrgRole> query(OrgRole orgRole, final OrderBy... orderBies) {
        if (orgRole == null) {
            orgRole = new OrgRole();
        }
        return getDslTemplate().query(orgRole, new SelectGenerateCallback<OrgRole>() {

            @SuppressWarnings("rawtypes")
            public Select generate(OrgRole t) {
                Select select = selectFrom(ORG_ROLETABLE).where(
                        and(
                                ORG_ROLETABLE.ORG_ROLE_NAME.eq(t.getOrgRoleName()),
                                ORG_ROLETABLE.ORG_ROLE_TYPE.eq(t.getOrgRoleType()),
                                ORG_ROLETABLE.ORG_ROLE_REMARKS.eq(t.getOrgRoleRemarks()),
                                ORG_ROLETABLE.DELETED.eq(t.getDeleted())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public Pager<OrgRole> queryPager(int start, int limit, OrgRole orgRole, final OrderBy... orderBies) {
        if (orgRole == null) {
            orgRole = new OrgRole();
        }
        return getDslTemplate().queryPager(start, limit, orgRole, false, new SelectGenerateCallback<OrgRole>() {

            public Select generate(OrgRole t) {
                Select select = MysqlSelect.selectFrom(ORG_ROLETABLE).where(
                        and(
                                ORG_ROLETABLE.ORG_ROLE_NAME.eq(t.getOrgRoleName()),
                                ORG_ROLETABLE.ORG_ROLE_TYPE.eq(t.getOrgRoleType()),
                                ORG_ROLETABLE.ORG_ROLE_REMARKS.eq(t.getOrgRoleRemarks()),
                                ORG_ROLETABLE.DELETED.eq(t.getDeleted())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<OrgRole> orgRoles) {
        if (CollectionUtil.isEmpty(orgRoles)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, orgRoles, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(ORG_ROLETABLE).values(
                        ORG_ROLETABLE.ORG_ROLE_NAME.value(new JdbcNamedParameter("orgRoleName")),
                        ORG_ROLETABLE.ORG_ROLE_TYPE.value(new JdbcNamedParameter("orgRoleType")),
                        ORG_ROLETABLE.ORG_ROLE_REMARKS.value(new JdbcNamedParameter("orgRoleRemarks")),
                        ORG_ROLETABLE.DELETED.value(new JdbcNamedParameter("deleted")));
            }
        });
    }

    public int[] batchInsert(List<OrgRole> orgRoles) {
        return batchInsert(true, orgRoles);
    }

    public int[] batchUpdate(List<OrgRole> orgRoles) {
        if (CollectionUtil.isEmpty(orgRoles)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(orgRoles, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(ORG_ROLETABLE).set(
                        ORG_ROLETABLE.ORG_ROLE_NAME.value(new JdbcNamedParameter("orgRoleName")),
                        ORG_ROLETABLE.ORG_ROLE_REMARKS.value(new JdbcNamedParameter("orgRoleRemarks")),
                        ORG_ROLETABLE.ORG_ROLE_TYPE.value(new JdbcNamedParameter("orgRoleType")),
                        ORG_ROLETABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
                        ORG_ROLETABLE.ORG_ROLE_ID.eq(new JdbcNamedParameter("orgRoleId")));
            }
        });
    }

    public int[] batchDelete(List<OrgRole> orgRoles) {
        if (CollectionUtil.isEmpty(orgRoles)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(orgRoles, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(ORG_ROLETABLE).where(and(
                        ORG_ROLETABLE.ORG_ROLE_ID.eq(new JdbcNamedParameter("orgRoleId")),
                        ORG_ROLETABLE.ORG_ROLE_NAME.eq(new JdbcNamedParameter("orgRoleName")),
                        ORG_ROLETABLE.ORG_ROLE_TYPE.eq(new JdbcNamedParameter("orgRoleType")),
                        ORG_ROLETABLE.ORG_ROLE_REMARKS.eq(new JdbcNamedParameter("orgRoleRemarks")),
                        ORG_ROLETABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
            }
        });
    }

    private Select addOrderByElements(Select select, OrderBy... orderBies) {
        List<OrderByElement> orderByElements = new ArrayList<OrderByElement>();
        for (int i = 0; orderBies != null && i < orderBies.length; i++) {
            OrderByElement tempElement = orderBies[i].getOrderByElement();
            if (tempElement != null) {
                orderByElements.add(tempElement);
            }
        }
        if (orderByElements.size() > 0) {
            select.orderBy(orderByElements.toArray(new OrderByElement[0]));
        }
        return select;
    }

    public List<OrgRole> getRolesByIds(String... ids) {
        SelectGenerateCallback<Serializable[]> callback = new SelectGenerateCallback<Serializable[]>() {

            public Select generate(Serializable[] serializables) {
                return selectFrom(ORG_ROLETABLE).where(ORG_ROLETABLE.ORG_ROLE_ID.in(serializables));
            }
        };
        return getDslSession().fetchList(callback.generate(ids), OrgRole.class);
    }

    public List<OrgRole> findRoleByUserId(String userId) {
        Select subSelect = Select.select(ORG_ROLE_USERTABLE.ORG_ROLE_ID).from(ORG_ROLE_USERTABLE)
                .where(ORG_ROLE_USERTABLE.ORG_USER_ID.eq(userId));
        Select select = selectFrom(ORG_ROLETABLE)
                .where(and(
                        ORG_ROLETABLE.ORG_ROLE_ID.inExpression(SubSelect.subSelect(subSelect)),
                        ORG_ROLETABLE.DELETED.eq(Project.DELETE_NO)
                ));
        return getDslSession().fetchList(select, OrgRole.class);
    }

    public List<OrgRole> roleInCondition(String condition, String type, Integer limit) {
        Condition con = null;
        if (!StringUtil.isBlank(type)) {
            con = ORG_ROLETABLE.ORG_ROLE_TYPE.eq(type);
        }
        Select select = MysqlSelect.select(ORG_ROLETABLE.ORG_ROLE_ID,
                FragmentSql.fragmentSelect("CONCAT (CASE org_role_type WHEN 0 THEN '系统角色' WHEN 1 THEN '产品角色' ELSE '项目角色' END,'-',org_role_name) AS orgRoleName")).from(
                ORG_ROLETABLE
        ).where(and(ORG_ROLETABLE.ORG_ROLE_NAME.eq(condition), ORG_ROLETABLE.DELETED.eq(0), con)).limit(0, limit);
        return getDslSession().fetchList(select, OrgRole.class);
    }
}
