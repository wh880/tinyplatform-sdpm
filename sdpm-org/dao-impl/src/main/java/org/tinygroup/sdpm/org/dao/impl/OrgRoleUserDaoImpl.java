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
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.org.dao.OrgRoleUserDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.org.dao.constant.OrgRoleUserTable.ORG_ROLE_USERTABLE;
import static org.tinygroup.sdpm.org.dao.constant.OrgUserTable.ORG_USERTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class OrgRoleUserDaoImpl extends TinyDslDaoSupport implements OrgRoleUserDao {

    public OrgRoleUser add(OrgRoleUser orgRoleUser) {
        return getDslTemplate().insertAndReturnKey(orgRoleUser, new InsertGenerateCallback<OrgRoleUser>() {
            public Insert generate(OrgRoleUser t) {
                Insert insert = insertInto(ORG_ROLE_USERTABLE).values(
                        ORG_ROLE_USERTABLE.ID.value(t.getId()),
                        ORG_ROLE_USERTABLE.ORG_ROLE_ID.value(t.getOrgRoleId()),
                        ORG_ROLE_USERTABLE.ORG_USER_ID.value(t.getOrgUserId()),
                        ORG_ROLE_USERTABLE.ORG_USER_REAL_NAME.value(t.getOrgUserRealName()));
                return insert;
            }
        });
    }

    public int edit(OrgRoleUser orgRoleUser) {
        if (orgRoleUser == null || orgRoleUser.getId() == null) {
            return 0;
        }
        return getDslTemplate().update(orgRoleUser, new UpdateGenerateCallback<OrgRoleUser>() {
            public Update generate(OrgRoleUser t) {
                Update update = update(ORG_ROLE_USERTABLE).set(
                        ORG_ROLE_USERTABLE.ORG_ROLE_ID.value(t.getOrgRoleId()),
                        ORG_ROLE_USERTABLE.ORG_USER_ID.value(t.getOrgUserId()),
                        ORG_ROLE_USERTABLE.ORG_USER_REAL_NAME.value(t.getOrgUserRealName())).where(
                        ORG_ROLE_USERTABLE.ID.eq(t.getId()));
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
                return delete(ORG_ROLE_USERTABLE).where(ORG_ROLE_USERTABLE.ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(ORG_ROLE_USERTABLE).where(ORG_ROLE_USERTABLE.ID.in(t));
            }
        }, pks);
    }

    public int deleteByRoleId(Integer roleId) {
        Delete delete = delete(ORG_ROLE_USERTABLE).where(ORG_ROLE_USERTABLE.ORG_ROLE_ID.eq(roleId));
        return getDslSession().execute(delete);
    }

    public List<OrgRoleUser> getRolesByIds(String... ids) {
        SelectGenerateCallback<Serializable[]> callback = new SelectGenerateCallback<Serializable[]>() {

            public Select generate(Serializable[] serializables) {
                return selectFrom(ORG_ROLE_USERTABLE).where(ORG_ROLE_USERTABLE.ORG_ROLE_ID.in(serializables));
            }
        };
        return getDslSession().fetchList(callback.generate(ids),OrgRoleUser.class);
    }

    public OrgRoleUser getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, OrgRoleUser.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(ORG_ROLE_USERTABLE).where(ORG_ROLE_USERTABLE.ID.eq(t));
            }
        });
    }

    public List<OrgRoleUser> getByRoleId(Integer roleId) {
        Select select = selectFrom(ORG_ROLE_USERTABLE).where(
                ORG_ROLE_USERTABLE.ORG_ROLE_ID.eq(roleId));
        return getDslSession().fetchList(select, OrgRoleUser.class);
    }

    public List<OrgRoleUser> query(OrgRoleUser orgRoleUser, final OrderBy... orderBies) {
        if (orgRoleUser == null) {
            orgRoleUser = new OrgRoleUser();
        }
        return getDslTemplate().query(orgRoleUser, new SelectGenerateCallback<OrgRoleUser>() {

            @SuppressWarnings("rawtypes")
            public Select generate(OrgRoleUser t) {
                Select select = selectFrom(ORG_ROLE_USERTABLE).where(
                        and(
                                ORG_ROLE_USERTABLE.ORG_ROLE_ID.eq(t.getOrgRoleId()),
                                ORG_ROLE_USERTABLE.ORG_USER_ID.eq(t.getOrgUserId()),
                                ORG_ROLE_USERTABLE.ORG_USER_REAL_NAME.eq(t.getOrgUserRealName())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public Pager<OrgRoleUser> queryPager(int start, int limit, OrgRoleUser orgRoleUser, final OrderBy... orderBies) {
        if (orgRoleUser == null) {
            orgRoleUser = new OrgRoleUser();
        }
        return getDslTemplate().queryPager(start, limit, orgRoleUser, false, new SelectGenerateCallback<OrgRoleUser>() {

            public Select generate(OrgRoleUser t) {
                Select select = MysqlSelect.selectFrom(ORG_ROLE_USERTABLE).where(
                        and(
                                ORG_ROLE_USERTABLE.ORG_ROLE_ID.eq(t.getOrgRoleId()),
                                ORG_ROLE_USERTABLE.ORG_USER_ID.eq(t.getOrgUserId()),
                                ORG_ROLE_USERTABLE.ORG_USER_REAL_NAME.eq(t.getOrgUserRealName())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<OrgRoleUser> orgRoleUsers) {
        if (CollectionUtil.isEmpty(orgRoleUsers)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, orgRoleUsers, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(ORG_ROLE_USERTABLE).values(
                        ORG_ROLE_USERTABLE.ORG_ROLE_ID.value(new JdbcNamedParameter("orgRoleId")),
                        ORG_ROLE_USERTABLE.ORG_USER_ID.value(new JdbcNamedParameter("orgUserId")),
                        ORG_ROLE_USERTABLE.ORG_USER_REAL_NAME.value(new JdbcNamedParameter("orgUserRealName")));
            }
        });
    }

    public int[] batchInsert(List<OrgRoleUser> orgRoleUsers) {
        return batchInsert(true, orgRoleUsers);
    }

    public int[] batchUpdate(List<OrgRoleUser> orgRoleUsers) {
        if (CollectionUtil.isEmpty(orgRoleUsers)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(orgRoleUsers, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(ORG_ROLE_USERTABLE).set(
                        ORG_ROLE_USERTABLE.ORG_ROLE_ID.value(new JdbcNamedParameter("orgRoleId")),
                        ORG_ROLE_USERTABLE.ORG_USER_ID.value(new JdbcNamedParameter("orgUserId")),
                        ORG_ROLE_USERTABLE.ORG_USER_REAL_NAME.value(new JdbcNamedParameter("orgUserRealName"))).where(
                        ORG_ROLE_USERTABLE.ID.eq(new JdbcNamedParameter("id")));
            }
        });
    }

    public int[] batchDelete(List<OrgRoleUser> orgRoleUsers) {
        if (CollectionUtil.isEmpty(orgRoleUsers)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(orgRoleUsers, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(ORG_ROLE_USERTABLE).where(and(
                        ORG_ROLE_USERTABLE.ID.eq(new JdbcNamedParameter("id")),
                        ORG_ROLE_USERTABLE.ORG_ROLE_ID.eq(new JdbcNamedParameter("orgRoleId")),
                        ORG_ROLE_USERTABLE.ORG_USER_ID.eq(new JdbcNamedParameter("orgUserId")),
                        ORG_ROLE_USERTABLE.ORG_USER_REAL_NAME.eq(new JdbcNamedParameter("orgUserRealName"))));
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
}
