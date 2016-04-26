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
import org.tinygroup.sdpm.org.dao.OrgDiaryDetailDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryDetail;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.org.dao.constant.OrgDiaryDetailTable.ORG_DIARY_DETAILTABLE;
import static org.tinygroup.sdpm.system.dao.constant.SystemActionTable.SYSTEM_ACTIONTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
@Repository
public class OrgDiaryDetailDaoImpl extends TinyDslDaoSupport implements OrgDiaryDetailDao {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OrgDiaryDetail add(OrgDiaryDetail orgDiaryDetail) {
        return getDslTemplate().insertAndReturnKey(orgDiaryDetail, new InsertGenerateCallback<OrgDiaryDetail>() {
            public Insert generate(OrgDiaryDetail t) {
                Insert insert = insertInto(ORG_DIARY_DETAILTABLE).values(
                        ORG_DIARY_DETAILTABLE.ORG_DETAIL_ID.value(t.getOrgDetailId()),
                        ORG_DIARY_DETAILTABLE.ORG_DIARY_ID.value(t.getOrgDiaryId()),
                        ORG_DIARY_DETAILTABLE.ORG_USER_ID.value(t.getOrgUserId()),
                        ORG_DIARY_DETAILTABLE.ORG_DETAIL_DATE.value(t.getOrgDetailDate()),
                        ORG_DIARY_DETAILTABLE.ORG_DETAIL_TYPE.value(t.getOrgDetailType()),
                        ORG_DIARY_DETAILTABLE.ORG_DETAIL_CONTENT.value(t.getOrgDetailContent()),
                        ORG_DIARY_DETAILTABLE.ACTION_ID.value(t.getActionId()));
                return insert;
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int edit(OrgDiaryDetail orgDiaryDetail) {
        if (orgDiaryDetail == null || orgDiaryDetail.getOrgDetailId() == null) {
            return 0;
        }
        return getDslTemplate().update(orgDiaryDetail, new UpdateGenerateCallback<OrgDiaryDetail>() {
            public Update generate(OrgDiaryDetail t) {
                Update update = update(ORG_DIARY_DETAILTABLE).set(
                        ORG_DIARY_DETAILTABLE.ORG_DIARY_ID.value(t.getOrgDiaryId()),
                        ORG_DIARY_DETAILTABLE.ORG_USER_ID.value(t.getOrgUserId()),
                        ORG_DIARY_DETAILTABLE.ORG_DETAIL_DATE.value(t.getOrgDetailDate()),
                        ORG_DIARY_DETAILTABLE.ORG_DETAIL_TYPE.value(t.getOrgDetailType()),
                        ORG_DIARY_DETAILTABLE.ORG_DETAIL_CONTENT.value(t.getOrgDetailContent()),
                        ORG_DIARY_DETAILTABLE.ACTION_ID.value(t.getActionId())).where(
                        ORG_DIARY_DETAILTABLE.ORG_DETAIL_ID.eq(t.getOrgDetailId()));
                return update;
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int deleteByKey(Integer pk) {
        if (pk == null) {
            return 0;
        }
        return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
            public Delete generate(Serializable pk) {
                return delete(ORG_DIARY_DETAILTABLE).where(ORG_DIARY_DETAILTABLE.ORG_DETAIL_ID.eq(pk));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(ORG_DIARY_DETAILTABLE).where(ORG_DIARY_DETAILTABLE.ORG_DETAIL_ID.in(t));
            }
        }, pks);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OrgDiaryDetail getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, OrgDiaryDetail.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(ORG_DIARY_DETAILTABLE).where(ORG_DIARY_DETAILTABLE.ORG_DETAIL_ID.eq(t));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<OrgDiaryDetail> query(OrgDiaryDetail orgDiaryDetail, final OrderBy... orderArgs) {
        if (orgDiaryDetail == null) {
            orgDiaryDetail = new OrgDiaryDetail();
        }
        return getDslTemplate().query(orgDiaryDetail, new SelectGenerateCallback<OrgDiaryDetail>() {

            @SuppressWarnings("rawtypes")
            public Select generate(OrgDiaryDetail t) {
                Select select = selectFrom(ORG_DIARY_DETAILTABLE).where(
                        and(
                                ORG_DIARY_DETAILTABLE.ORG_DIARY_ID.eq(t.getOrgDiaryId()),
                                ORG_DIARY_DETAILTABLE.ORG_USER_ID.eq(t.getOrgUserId()),
                                ORG_DIARY_DETAILTABLE.ORG_DETAIL_DATE.eq(t.getOrgDetailDate()),
                                ORG_DIARY_DETAILTABLE.ORG_DETAIL_TYPE.eq(t.getOrgDetailType()),
                                ORG_DIARY_DETAILTABLE.ORG_DETAIL_CONTENT.eq(t.getOrgDetailContent()),
                                ORG_DIARY_DETAILTABLE.ACTION_ID.eq(t.getActionId())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Pager<OrgDiaryDetail> queryPager(int start, int limit, OrgDiaryDetail orgDiaryDetail, final OrderBy... orderArgs) {
        if (orgDiaryDetail == null) {
            orgDiaryDetail = new OrgDiaryDetail();
        }
        return getDslTemplate().queryPager(start, limit, orgDiaryDetail, false, new SelectGenerateCallback<OrgDiaryDetail>() {

            public Select generate(OrgDiaryDetail t) {
                Select select = Select.selectFrom(ORG_DIARY_DETAILTABLE).where(
                        and(
                                ORG_DIARY_DETAILTABLE.ORG_DIARY_ID.eq(t.getOrgDiaryId()),
                                ORG_DIARY_DETAILTABLE.ORG_USER_ID.eq(t.getOrgUserId()),
                                ORG_DIARY_DETAILTABLE.ORG_DETAIL_DATE.eq(t.getOrgDetailDate()),
                                ORG_DIARY_DETAILTABLE.ORG_DETAIL_TYPE.eq(t.getOrgDetailType()),
                                ORG_DIARY_DETAILTABLE.ORG_DETAIL_CONTENT.eq(t.getOrgDetailContent()),
                                ORG_DIARY_DETAILTABLE.ACTION_ID.eq(t.getActionId())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int[] batchInsert(boolean autoGeneratedKeys, List<OrgDiaryDetail> orgDiaryDetails) {
        if (CollectionUtil.isEmpty(orgDiaryDetails)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, orgDiaryDetails, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(ORG_DIARY_DETAILTABLE).values(
                        ORG_DIARY_DETAILTABLE.ORG_DIARY_ID.value(new JdbcNamedParameter("orgDiaryId")),
                        ORG_DIARY_DETAILTABLE.ORG_USER_ID.value(new JdbcNamedParameter("orgUserId")),
                        ORG_DIARY_DETAILTABLE.ORG_DETAIL_DATE.value(new JdbcNamedParameter("orgDetailDate")),
                        ORG_DIARY_DETAILTABLE.ORG_DETAIL_TYPE.value(new JdbcNamedParameter("orgDetailType")),
                        ORG_DIARY_DETAILTABLE.ORG_DETAIL_CONTENT.value(new JdbcNamedParameter("orgDetailContent")),
                        ORG_DIARY_DETAILTABLE.ACTION_ID.value(new JdbcNamedParameter("actionId")));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int[] batchInsert(List<OrgDiaryDetail> orgDiaryDetails) {
        return batchInsert(true, orgDiaryDetails);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int[] batchUpdate(List<OrgDiaryDetail> orgDiaryDetails) {
        if (CollectionUtil.isEmpty(orgDiaryDetails)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(orgDiaryDetails, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(ORG_DIARY_DETAILTABLE).set(
                        ORG_DIARY_DETAILTABLE.ORG_DIARY_ID.value(new JdbcNamedParameter("orgDiaryId")),
                        ORG_DIARY_DETAILTABLE.ORG_USER_ID.value(new JdbcNamedParameter("orgUserId")),
                        ORG_DIARY_DETAILTABLE.ORG_DETAIL_DATE.value(new JdbcNamedParameter("orgDetailDate")),
                        ORG_DIARY_DETAILTABLE.ORG_DETAIL_TYPE.value(new JdbcNamedParameter("orgDetailType")),
                        ORG_DIARY_DETAILTABLE.ORG_DETAIL_CONTENT.value(new JdbcNamedParameter("orgDetailContent")),
                        ORG_DIARY_DETAILTABLE.ACTION_ID.value(new JdbcNamedParameter("actionId"))).where(
                        ORG_DIARY_DETAILTABLE.ORG_DETAIL_ID.eq(new JdbcNamedParameter("orgDetailId")));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int[] batchDelete(List<OrgDiaryDetail> orgDiaryDetails) {
        if (CollectionUtil.isEmpty(orgDiaryDetails)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(orgDiaryDetails, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(ORG_DIARY_DETAILTABLE).where(and(
                        ORG_DIARY_DETAILTABLE.ORG_DETAIL_ID.eq(new JdbcNamedParameter("orgDetailId")),
                        ORG_DIARY_DETAILTABLE.ORG_DIARY_ID.eq(new JdbcNamedParameter("orgDiaryId")),
                        ORG_DIARY_DETAILTABLE.ORG_USER_ID.eq(new JdbcNamedParameter("orgUserId")),
                        ORG_DIARY_DETAILTABLE.ORG_DETAIL_DATE.eq(new JdbcNamedParameter("orgDetailDate")),
                        ORG_DIARY_DETAILTABLE.ORG_DETAIL_TYPE.eq(new JdbcNamedParameter("orgDetailType")),
                        ORG_DIARY_DETAILTABLE.ORG_DETAIL_CONTENT.eq(new JdbcNamedParameter("orgDetailContent")),
                        ORG_DIARY_DETAILTABLE.ACTION_ID.eq(new JdbcNamedParameter("actionId"))));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    private Select addOrderByElements(Select select, OrderBy... orderBies) {
        if (orderBies == null || orderBies.length == 0) {
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
    public Integer batchDeleteByDiaryId(Integer diaryid) {
        if (diaryid == null) {
            return 0;
        }
        Delete delete = Delete.delete(ORG_DIARY_DETAILTABLE).
                where(ORG_DIARY_DETAILTABLE.ORG_DIARY_ID.eq(diaryid));
        return getDslSession().execute(delete);
    }

    @Override
    public List<OrgDiaryDetail> findByDiaryId(Integer diaryid) {
        Select select = Select.selectFrom(ORG_DIARY_DETAILTABLE).
                where(ORG_DIARY_DETAILTABLE.ORG_DIARY_ID.eq(diaryid));
        Select select1 = Select.select(ORG_DIARY_DETAILTABLE.ALL, SYSTEM_ACTIONTABLE.ACTION_OBJECT_ID.as("objectId", true), SYSTEM_ACTIONTABLE.ACTION_OBJECT_TYPE.as("objectType", true))
                .from(ORG_DIARY_DETAILTABLE, SYSTEM_ACTIONTABLE).where(and(ORG_DIARY_DETAILTABLE.ACTION_ID.eq(SYSTEM_ACTIONTABLE.ACTION_ID),
                        ORG_DIARY_DETAILTABLE.ORG_DIARY_ID.eq(diaryid)));
        return getDslSession().fetchList(select1, OrgDiaryDetail.class);
    }

    @Override
    public List<OrgDiaryDetail> findListByDiaryList(List<Integer> list) {
        Select select = Select.selectFrom(ORG_DIARY_DETAILTABLE).where(ORG_DIARY_DETAILTABLE.ORG_DIARY_ID
                .in(list.toArray()));
        return getDslSession().fetchList(select, OrgDiaryDetail.class);
    }
}
