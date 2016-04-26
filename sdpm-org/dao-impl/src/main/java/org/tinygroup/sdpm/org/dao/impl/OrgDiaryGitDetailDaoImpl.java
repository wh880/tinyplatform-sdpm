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
import org.tinygroup.sdpm.org.dao.OrgDiaryGitDetailDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryGitDetail;
import org.tinygroup.sdpm.org.dao.pojo.OrgGitCommitInfo;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.org.dao.constant.OrgDiaryGitDetailTable.ORG_DIARY_GIT_DETAIL_TABLE;
import static org.tinygroup.sdpm.org.dao.constant.OrgGitCommitInfoTable.ORG_GIT_COMMIT_INFO_TABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.select;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
@Repository
public class OrgDiaryGitDetailDaoImpl extends TinyDslDaoSupport implements OrgDiaryGitDetailDao {

    public List<OrgGitCommitInfo> getOrgGitCommitInfoByDiaryId(final Integer diaryId) {
        OrgGitCommitInfo orgGitCommitInfo = new OrgGitCommitInfo();
        return getDslTemplate().query(orgGitCommitInfo, new SelectGenerateCallback<OrgGitCommitInfo>() {

            @SuppressWarnings("rawtypes")
            public Select generate(OrgGitCommitInfo t) {
                Select select = select(ORG_GIT_COMMIT_INFO_TABLE.ALL).from(ORG_GIT_COMMIT_INFO_TABLE, ORG_DIARY_GIT_DETAIL_TABLE)
                        .where(and(ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_ID.eq(ORG_DIARY_GIT_DETAIL_TABLE.ORG_GIT_COMMIT_ID),
                                ORG_DIARY_GIT_DETAIL_TABLE.ORG_DIARY_ID.eq(diaryId))).orderBy(OrderByElement.desc(ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_TIME));
                return select;
            }
        });
    }

    public Integer DeleteByDiaryId(Integer diaryId) {
        if (diaryId == null) {
            return 0;
        }
        Delete delete = Delete.delete(ORG_DIARY_GIT_DETAIL_TABLE).where(
                and(ORG_DIARY_GIT_DETAIL_TABLE.ORG_DIARY_ID.eq(diaryId)));
        return getDslSession().execute(delete);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OrgDiaryGitDetail add(OrgDiaryGitDetail orgDiaryGitDetail) {
        return getDslTemplate().insertAndReturnKey(orgDiaryGitDetail, new InsertGenerateCallback<OrgDiaryGitDetail>() {
            public Insert generate(OrgDiaryGitDetail t) {
                Insert insert = insertInto(ORG_DIARY_GIT_DETAIL_TABLE).values(
                        ORG_DIARY_GIT_DETAIL_TABLE.ORG_DIARY_GIT_DETAIL_ID.value(t.getOrgDiaryGitDetailId()),
                        ORG_DIARY_GIT_DETAIL_TABLE.ORG_DIARY_ID.value(t.getOrgDiaryId()),
                        ORG_DIARY_GIT_DETAIL_TABLE.ORG_GIT_COMMIT_ID.value(t.getOrgGitCommitId()));
                return insert;
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int edit(OrgDiaryGitDetail orgDiaryGitDetail) {
        if (orgDiaryGitDetail == null || orgDiaryGitDetail.getOrgDiaryGitDetailId() == null) {
            return 0;
        }
        return getDslTemplate().update(orgDiaryGitDetail, new UpdateGenerateCallback<OrgDiaryGitDetail>() {
            public Update generate(OrgDiaryGitDetail t) {
                Update update = update(ORG_DIARY_GIT_DETAIL_TABLE).set(
                        ORG_DIARY_GIT_DETAIL_TABLE.ORG_DIARY_ID.value(t.getOrgDiaryId()),
                        ORG_DIARY_GIT_DETAIL_TABLE.ORG_GIT_COMMIT_ID.value(t.getOrgGitCommitId())).where(
                        ORG_DIARY_GIT_DETAIL_TABLE.ORG_DIARY_GIT_DETAIL_ID.eq(t.getOrgDiaryGitDetailId()));
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
                return delete(ORG_DIARY_GIT_DETAIL_TABLE).where(ORG_DIARY_GIT_DETAIL_TABLE.ORG_DIARY_GIT_DETAIL_ID.eq(pk));
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
                return delete(ORG_DIARY_GIT_DETAIL_TABLE).where(ORG_DIARY_GIT_DETAIL_TABLE.ORG_DIARY_GIT_DETAIL_ID.in(t));
            }
        }, pks);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OrgDiaryGitDetail getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, OrgDiaryGitDetail.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(ORG_DIARY_GIT_DETAIL_TABLE).where(ORG_DIARY_GIT_DETAIL_TABLE.ORG_DIARY_GIT_DETAIL_ID.eq(t));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<OrgDiaryGitDetail> query(OrgDiaryGitDetail orgDiaryGitDetail, final OrderBy... orderArgs) {
        if (orgDiaryGitDetail == null) {
            orgDiaryGitDetail = new OrgDiaryGitDetail();
        }
        return getDslTemplate().query(orgDiaryGitDetail, new SelectGenerateCallback<OrgDiaryGitDetail>() {

            @SuppressWarnings("rawtypes")
            public Select generate(OrgDiaryGitDetail t) {
                Select select = selectFrom(ORG_DIARY_GIT_DETAIL_TABLE).where(
                        and(
                                ORG_DIARY_GIT_DETAIL_TABLE.ORG_DIARY_ID.eq(t.getOrgDiaryId()),
                                ORG_DIARY_GIT_DETAIL_TABLE.ORG_GIT_COMMIT_ID.eq(t.getOrgGitCommitId())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Pager<OrgDiaryGitDetail> queryPager(int start, int limit, OrgDiaryGitDetail orgDiaryGitDetail, final OrderBy... orderArgs) {
        if (orgDiaryGitDetail == null) {
            orgDiaryGitDetail = new OrgDiaryGitDetail();
        }
        return getDslTemplate().queryPager(start, limit, orgDiaryGitDetail, false, new SelectGenerateCallback<OrgDiaryGitDetail>() {

            public Select generate(OrgDiaryGitDetail t) {
                Select select = Select.selectFrom(ORG_DIARY_GIT_DETAIL_TABLE).where(
                        and(
                                ORG_DIARY_GIT_DETAIL_TABLE.ORG_DIARY_ID.eq(t.getOrgDiaryId()),
                                ORG_DIARY_GIT_DETAIL_TABLE.ORG_GIT_COMMIT_ID.eq(t.getOrgGitCommitId())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int[] batchInsert(boolean autoGeneratedKeys, List<OrgDiaryGitDetail> orgDiaryGitDetails) {
        if (CollectionUtil.isEmpty(orgDiaryGitDetails)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, orgDiaryGitDetails, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(ORG_DIARY_GIT_DETAIL_TABLE).values(
                        ORG_DIARY_GIT_DETAIL_TABLE.ORG_DIARY_ID.value(new JdbcNamedParameter("orgDiaryId")),
                        ORG_DIARY_GIT_DETAIL_TABLE.ORG_GIT_COMMIT_ID.value(new JdbcNamedParameter("orgGitCommitId")));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int[] batchInsert(List<OrgDiaryGitDetail> orgDiaryGitDetails) {
        return batchInsert(true, orgDiaryGitDetails);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int[] batchUpdate(List<OrgDiaryGitDetail> orgDiaryGitDetails) {
        if (CollectionUtil.isEmpty(orgDiaryGitDetails)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(orgDiaryGitDetails, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(ORG_DIARY_GIT_DETAIL_TABLE).set(
                        ORG_DIARY_GIT_DETAIL_TABLE.ORG_DIARY_ID.value(new JdbcNamedParameter("orgDiaryId")),
                        ORG_DIARY_GIT_DETAIL_TABLE.ORG_GIT_COMMIT_ID.value(new JdbcNamedParameter("orgGitCommitId"))).where(
                        ORG_DIARY_GIT_DETAIL_TABLE.ORG_DIARY_GIT_DETAIL_ID.eq(new JdbcNamedParameter("orgDiaryGitDetailId")));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int[] batchDelete(List<OrgDiaryGitDetail> orgDiaryGitDetails) {
        if (CollectionUtil.isEmpty(orgDiaryGitDetails)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(orgDiaryGitDetails, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(ORG_DIARY_GIT_DETAIL_TABLE).where(and(
                        ORG_DIARY_GIT_DETAIL_TABLE.ORG_DIARY_GIT_DETAIL_ID.eq(new JdbcNamedParameter("orgDiaryGitDetailId")),
                        ORG_DIARY_GIT_DETAIL_TABLE.ORG_DIARY_ID.eq(new JdbcNamedParameter("orgDiaryId")),
                        ORG_DIARY_GIT_DETAIL_TABLE.ORG_GIT_COMMIT_ID.eq(new JdbcNamedParameter("orgGitCommitId"))));
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
}
