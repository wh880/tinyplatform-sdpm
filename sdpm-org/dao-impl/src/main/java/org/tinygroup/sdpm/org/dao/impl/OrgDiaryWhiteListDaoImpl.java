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
import org.tinygroup.sdpm.org.dao.OrgDiaryWhiteListDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryWhiteList;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.org.dao.constant.OrgDiaryWhiteListTable.ORG_DIARY_WHITE_LISTTABLE;
import static org.tinygroup.sdpm.org.dao.constant.OrgUserTable.ORG_USERTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.tinysqldsl.formitem.SubSelect.subSelect;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * @generated
 */
@Repository
public class OrgDiaryWhiteListDaoImpl extends TinyDslDaoSupport implements OrgDiaryWhiteListDao {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public OrgDiaryWhiteList add(OrgDiaryWhiteList orgDiaryWhiteList) {
        return getDslTemplate().insertAndReturnKey(orgDiaryWhiteList, new InsertGenerateCallback<OrgDiaryWhiteList>() {
            public Insert generate(OrgDiaryWhiteList t) {
                Insert insert = insertInto(ORG_DIARY_WHITE_LISTTABLE).values(
                        ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_ID.value(t.getOrgDiaryWhiteListId()),
                        ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_FIRST_ACCOUNT.value(t.getOrgDiaryWhiteListFirstAccount()),
                        ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_SECOND_ACCOUNT.value(t.getOrgDiaryWhiteListSecondAccount()));
                return insert;
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public int edit(OrgDiaryWhiteList orgDiaryWhiteList) {
        if (orgDiaryWhiteList == null || orgDiaryWhiteList.getOrgDiaryWhiteListId() == null) {
            return 0;
        }
        return getDslTemplate().update(orgDiaryWhiteList, new UpdateGenerateCallback<OrgDiaryWhiteList>() {
            public Update generate(OrgDiaryWhiteList t) {
                Update update = update(ORG_DIARY_WHITE_LISTTABLE).set(
                        ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_FIRST_ACCOUNT.value(t.getOrgDiaryWhiteListFirstAccount()),
                        ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_SECOND_ACCOUNT.value(t.getOrgDiaryWhiteListSecondAccount())).where(
                        ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_ID.eq(t.getOrgDiaryWhiteListId()));
                return update;
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public int deleteByKey(Integer pk) {
        if (pk == null) {
            return 0;
        }
        return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
            public Delete generate(Serializable pk) {
                return delete(ORG_DIARY_WHITE_LISTTABLE).where(ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_ID.eq(pk));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(ORG_DIARY_WHITE_LISTTABLE).where(ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_ID.in(t));
            }
        }, pks);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public OrgDiaryWhiteList getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, OrgDiaryWhiteList.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(ORG_DIARY_WHITE_LISTTABLE).where(ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_ID.eq(t));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public List<OrgDiaryWhiteList> query(OrgDiaryWhiteList orgDiaryWhiteList, final OrderBy... orderArgs) {
        if (orgDiaryWhiteList == null) {
            orgDiaryWhiteList = new OrgDiaryWhiteList();
        }
        return getDslTemplate().query(orgDiaryWhiteList, new SelectGenerateCallback<OrgDiaryWhiteList>() {

            @SuppressWarnings("rawtypes")
            public Select generate(OrgDiaryWhiteList t) {
                Select select = selectFrom(ORG_DIARY_WHITE_LISTTABLE).where(
                        and(
                                ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_FIRST_ACCOUNT.eq(t.getOrgDiaryWhiteListFirstAccount()),
                                ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_SECOND_ACCOUNT.eq(t.getOrgDiaryWhiteListSecondAccount())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public Pager<OrgDiaryWhiteList> queryPager(int start, int limit, OrgDiaryWhiteList orgDiaryWhiteList, final OrderBy... orderArgs) {
        if (orgDiaryWhiteList == null) {
            orgDiaryWhiteList = new OrgDiaryWhiteList();
        }
        return getDslTemplate().queryPager(start, limit, orgDiaryWhiteList, false, new SelectGenerateCallback<OrgDiaryWhiteList>() {

            public Select generate(OrgDiaryWhiteList t) {
                Select select = Select.selectFrom(ORG_DIARY_WHITE_LISTTABLE).where(
                        and(
                                ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_FIRST_ACCOUNT.eq(t.getOrgDiaryWhiteListFirstAccount()),
                                ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_SECOND_ACCOUNT.eq(t.getOrgDiaryWhiteListSecondAccount())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public int[] batchInsert(boolean autoGeneratedKeys, List<OrgDiaryWhiteList> orgDiaryWhiteLists) {
        if (CollectionUtil.isEmpty(orgDiaryWhiteLists)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, orgDiaryWhiteLists, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(ORG_DIARY_WHITE_LISTTABLE).values(
                        ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_FIRST_ACCOUNT.value(new JdbcNamedParameter("orgDiaryWhiteListFirstAccount")),
                        ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_SECOND_ACCOUNT.value(new JdbcNamedParameter("orgDiaryWhiteListSecondAccount")));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public int[] batchInsert(List<OrgDiaryWhiteList> orgDiaryWhiteLists) {
        return batchInsert(true, orgDiaryWhiteLists);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public int[] batchUpdate(List<OrgDiaryWhiteList> orgDiaryWhiteLists) {
        if (CollectionUtil.isEmpty(orgDiaryWhiteLists)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(orgDiaryWhiteLists, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(ORG_DIARY_WHITE_LISTTABLE).set(
                        ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_FIRST_ACCOUNT.value(new JdbcNamedParameter("orgDiaryWhiteListFirstAccount")),
                        ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_SECOND_ACCOUNT.value(new JdbcNamedParameter("orgDiaryWhiteListSecondAccount"))).where(
                        ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_ID.eq(new JdbcNamedParameter("orgDiaryWhiteListId")));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public int[] batchDelete(List<OrgDiaryWhiteList> orgDiaryWhiteLists) {
        if (CollectionUtil.isEmpty(orgDiaryWhiteLists)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(orgDiaryWhiteLists, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(ORG_DIARY_WHITE_LISTTABLE).where(and(
                        ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_ID.eq(new JdbcNamedParameter("orgDiaryWhiteListId")),
                        ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_FIRST_ACCOUNT.eq(new JdbcNamedParameter("orgDiaryWhiteListFirstAccount")),
                        ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_SECOND_ACCOUNT.eq(new JdbcNamedParameter("orgDiaryWhiteListSecondAccount"))));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    private Select addOrderByElements(Select select, OrderBy... orderBies) {
        if (orderBies == null) {
            return select;
        }
        List<OrderByElement> orderByElements = new ArrayList<OrderByElement>();
        for (int i = 0; orderBies[i] != null && i < orderBies.length; i++) {
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

    @Override
    public List<OrgUser> findUserListByAccount(String userAccount) {
        Select select = Select.selectFrom(ORG_USERTABLE).
                where(ORG_USERTABLE.ORG_USER_ACCOUNT.inExpression(
                        subSelect(
                                Select.select
                                        (ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_SECOND_ACCOUNT)
                                        .from(ORG_DIARY_WHITE_LISTTABLE).where
                                        (ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_FIRST_ACCOUNT.eq
                                                (userAccount)))));
        return getDslSession().fetchList(select, OrgUser.class);
    }

    @Override
    public List<OrgDiaryWhiteList> findOneByAccounts(String firstAccount, String secondAccount) {
        Select select = Select.selectFrom(ORG_DIARY_WHITE_LISTTABLE).where(and(
                ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_FIRST_ACCOUNT.eq(firstAccount),
                ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_SECOND_ACCOUNT.eq(secondAccount)
        ));
        return getDslSession().fetchList(select, OrgDiaryWhiteList.class);
    }

    @Override
    public Integer deleteByAccounts(String firstAccount, String secondAccout) {
        Delete delete = Delete.delete(ORG_DIARY_WHITE_LISTTABLE).where(and(
                ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_FIRST_ACCOUNT.eq(firstAccount),
                ORG_DIARY_WHITE_LISTTABLE.ORG_DIARY_WHITE_LIST_SECOND_ACCOUNT.eq(secondAccout)
        ));
        return getDslSession().execute(delete);
    }
}
