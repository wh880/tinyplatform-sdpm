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
import org.tinygroup.sdpm.org.dao.OrgGitCommitInfoDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgGitCommitInfo;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.tinygroup.sdpm.org.dao.constant.OrgGitCommitInfoTable.ORG_GIT_COMMIT_INFO_TABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * @generated
 */
@Repository
public class OrgGitCommitInfoDaoImpl extends TinyDslDaoSupport implements OrgGitCommitInfoDao {

    public List<OrgGitCommitInfo> findOrgGitCommitInfoByEmailAndDate(String email, final Date beginDate, final Date endDate) {
        OrgGitCommitInfo orgGitCommitInfo = new OrgGitCommitInfo();
        orgGitCommitInfo.setOrgGitAuthorEmail(email);
        return getDslTemplate().query(orgGitCommitInfo, new SelectGenerateCallback<OrgGitCommitInfo>() {

            @Override
            public Select generate(OrgGitCommitInfo orgGitCommitInfo) {
                return selectFrom(ORG_GIT_COMMIT_INFO_TABLE).where(and(
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_AUTHOR_EMAIL.eq(orgGitCommitInfo.getOrgGitAuthorEmail()),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_TIME.between(beginDate, endDate)
                )).orderBy(OrderByElement.asc(ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_TIME));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public OrgGitCommitInfo add(OrgGitCommitInfo orgGitCommitInfo) {
        return getDslTemplate().insertAndReturnKey(false, orgGitCommitInfo, new InsertGenerateCallback<OrgGitCommitInfo>() {
            public Insert generate(OrgGitCommitInfo t) {
                Insert insert = insertInto(ORG_GIT_COMMIT_INFO_TABLE).values(
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_ID.value(t.getOrgGitCommitId()),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_TIME.value(t.getOrgGitCommitTime()),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_REPOSITORY_NAME.value(t.getOrgGitRepositoryName()),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_AUTHOR_EMAIL.value(t.getOrgGitAuthorEmail()),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_MESSAGE.value(t.getOrgGitCommitMessage()),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_URL.value(t.getOrgGitCommitUrl()),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_TYPE.value(t.getOrgGitType()));
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
    public int edit(OrgGitCommitInfo orgGitCommitInfo) {
        if (orgGitCommitInfo == null || orgGitCommitInfo.getOrgGitCommitId() == null) {
            return 0;
        }
        return getDslTemplate().update(orgGitCommitInfo, new UpdateGenerateCallback<OrgGitCommitInfo>() {
            public Update generate(OrgGitCommitInfo t) {
                Update update = update(ORG_GIT_COMMIT_INFO_TABLE).set(
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_TIME.value(t.getOrgGitCommitTime()),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_REPOSITORY_NAME.value(t.getOrgGitRepositoryName()),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_AUTHOR_EMAIL.value(t.getOrgGitAuthorEmail()),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_MESSAGE.value(t.getOrgGitCommitMessage()),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_URL.value(t.getOrgGitCommitUrl()),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_TYPE.value(t.getOrgGitType())).where(
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_ID.eq(t.getOrgGitCommitId()));
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
                return delete(ORG_GIT_COMMIT_INFO_TABLE).where(ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_ID.eq(pk));
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
                return delete(ORG_GIT_COMMIT_INFO_TABLE).where(ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_ID.in(t));
            }
        }, pks);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public OrgGitCommitInfo getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, OrgGitCommitInfo.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(ORG_GIT_COMMIT_INFO_TABLE).where(ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_ID.eq(t));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public int deleteByKey(String pk) {
        if (pk == null) {
            return 0;
        }
        return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
            public Delete generate(Serializable pk) {
                return delete(ORG_GIT_COMMIT_INFO_TABLE).where(ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_ID.eq(pk));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public int deleteByKeys(String... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(ORG_GIT_COMMIT_INFO_TABLE).where(ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_ID.in(t));
            }
        }, pks);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public OrgGitCommitInfo getByKey(String pk) {
        return getDslTemplate().getByKey(pk, OrgGitCommitInfo.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(ORG_GIT_COMMIT_INFO_TABLE).where(ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_ID.eq(t));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public List<OrgGitCommitInfo> query(OrgGitCommitInfo orgGitCommitInfo, final OrderBy... orderArgs) {
        if (orgGitCommitInfo == null) {
            orgGitCommitInfo = new OrgGitCommitInfo();
        }
        return getDslTemplate().query(orgGitCommitInfo, new SelectGenerateCallback<OrgGitCommitInfo>() {

            @SuppressWarnings("rawtypes")
            public Select generate(OrgGitCommitInfo t) {
                Select select = selectFrom(ORG_GIT_COMMIT_INFO_TABLE).where(
                        and(
                                ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_TIME.eq(t.getOrgGitCommitTime()),
                                ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_REPOSITORY_NAME.eq(t.getOrgGitRepositoryName()),
                                ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_AUTHOR_EMAIL.eq(t.getOrgGitAuthorEmail()),
                                ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_MESSAGE.eq(t.getOrgGitCommitMessage()),
                                ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_URL.eq(t.getOrgGitCommitUrl()),
                                ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_TYPE.eq(t.getOrgGitType())));
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
    public Pager<OrgGitCommitInfo> queryPager(int start, int limit, OrgGitCommitInfo orgGitCommitInfo, final OrderBy... orderArgs) {
        if (orgGitCommitInfo == null) {
            orgGitCommitInfo = new OrgGitCommitInfo();
        }
        return getDslTemplate().queryPager(start, limit, orgGitCommitInfo, false, new SelectGenerateCallback<OrgGitCommitInfo>() {

            public Select generate(OrgGitCommitInfo t) {
                Select select = Select.selectFrom(ORG_GIT_COMMIT_INFO_TABLE).where(
                        and(
                                ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_TIME.eq(t.getOrgGitCommitTime()),
                                ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_REPOSITORY_NAME.eq(t.getOrgGitRepositoryName()),
                                ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_AUTHOR_EMAIL.eq(t.getOrgGitAuthorEmail()),
                                ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_MESSAGE.eq(t.getOrgGitCommitMessage()),
                                ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_URL.eq(t.getOrgGitCommitUrl()),
                                ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_TYPE.eq(t.getOrgGitType())));
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
    public int[] batchInsert(boolean autoGeneratedKeys, List<OrgGitCommitInfo> orgGitCommitInfos) {
        if (CollectionUtil.isEmpty(orgGitCommitInfos)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, orgGitCommitInfos, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(ORG_GIT_COMMIT_INFO_TABLE).values(
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_ID.value(new JdbcNamedParameter("orgGitCommitId")),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_TIME.value(new JdbcNamedParameter("orgGitCommitTime")),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_REPOSITORY_NAME.value(new JdbcNamedParameter("orgGitRepositoryName")),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_AUTHOR_EMAIL.value(new JdbcNamedParameter("orgGitAuthorEmail")),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_MESSAGE.value(new JdbcNamedParameter("orgGitCommitMessage")),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_URL.value(new JdbcNamedParameter("orgGitCommitUrl")),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_TYPE.value(new JdbcNamedParameter("orgGitType")));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public int[] batchInsert(List<OrgGitCommitInfo> orgGitCommitInfos) {
        return batchInsert(false, orgGitCommitInfos);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public int[] batchUpdate(List<OrgGitCommitInfo> orgGitCommitInfos) {
        if (CollectionUtil.isEmpty(orgGitCommitInfos)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(orgGitCommitInfos, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(ORG_GIT_COMMIT_INFO_TABLE).set(
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_TIME.value(new JdbcNamedParameter("orgGitCommitTime")),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_REPOSITORY_NAME.value(new JdbcNamedParameter("orgGitRepositoryName")),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_AUTHOR_EMAIL.value(new JdbcNamedParameter("orgGitAuthorEmail")),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_MESSAGE.value(new JdbcNamedParameter("orgGitCommitMessage")),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_URL.value(new JdbcNamedParameter("orgGitCommitUrl")),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_TYPE.value(new JdbcNamedParameter("orgGitType"))).where(
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_ID.eq(new JdbcNamedParameter("orgGitCommitId")));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public int[] batchDelete(List<OrgGitCommitInfo> orgGitCommitInfos) {
        if (CollectionUtil.isEmpty(orgGitCommitInfos)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(orgGitCommitInfos, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(ORG_GIT_COMMIT_INFO_TABLE).where(and(
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_ID.eq(new JdbcNamedParameter("orgGitCommitId")),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_TIME.eq(new JdbcNamedParameter("orgGitCommitTime")),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_REPOSITORY_NAME.eq(new JdbcNamedParameter("orgGitRepositoryName")),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_AUTHOR_EMAIL.eq(new JdbcNamedParameter("orgGitAuthorEmail")),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_MESSAGE.eq(new JdbcNamedParameter("orgGitCommitMessage")),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_COMMIT_URL.eq(new JdbcNamedParameter("orgGitCommitUrl")),
                        ORG_GIT_COMMIT_INFO_TABLE.ORG_GIT_TYPE.eq(new JdbcNamedParameter("orgGitType"))));
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
