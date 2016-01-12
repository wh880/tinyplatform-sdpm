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

import static org.tinygroup.sdpm.org.dao.constant.OrgUserTable.ORG_USERTABLE;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.org.dao.constant.OrgDiaryTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.base.Alias;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.base.FragmentSql;
import org.tinygroup.tinysqldsl.expression.FragmentExpressionSql;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.formitem.SubSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiary;
import org.tinygroup.sdpm.org.dao.OrgDiaryDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
@Repository
public class OrgDiaryDaoImpl extends TinyDslDaoSupport implements OrgDiaryDao {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OrgDiary add(OrgDiary orgDiary) {
        return getDslTemplate().insertAndReturnKey(orgDiary, new InsertGenerateCallback<OrgDiary>() {
            public Insert generate(OrgDiary t) {
                Insert insert = insertInto(ORG_DIARYTABLE).values(
                        ORG_DIARYTABLE.ORG_DIARY_ID.value(t.getOrgDiaryId()),
                        ORG_DIARYTABLE.ORG_USER_ID.value(t.getOrgUserId()),
                        ORG_DIARYTABLE.ORG_DIARY_BEGIN_DATE.value(t.getOrgDiaryBeginDate()),
                        ORG_DIARYTABLE.ORG_DIARY_END_DATE.value(t.getOrgDiaryEndDate()),
                        ORG_DIARYTABLE.ORG_DIARY_YEAR.value(t.getOrgDiaryYear()),
                        ORG_DIARYTABLE.ORG_DIARY_WEEK.value(t.getOrgDiaryWeek()),
                        ORG_DIARYTABLE.ORG_DIARY_CREATE_DATE.value(t.getOrgDiaryCreateDate()),
                        ORG_DIARYTABLE.ORG_DIARY_SUMMARY.value(t.getOrgDiarySummary()),
                        ORG_DIARYTABLE.ORG_DIARY_STATUS.value(t.getOrgDiaryStatus()),
                        ORG_DIARYTABLE.ORG_DIARY_MODIFY_DATE.value(t.getOrgDiaryModifyDate()));
                return insert;
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int edit(OrgDiary orgDiary) {
        if (orgDiary == null || orgDiary.getOrgDiaryId() == null) {
            return 0;
        }
        return getDslTemplate().update(orgDiary, new UpdateGenerateCallback<OrgDiary>() {
            public Update generate(OrgDiary t) {
                Update update = update(ORG_DIARYTABLE).set(
                        ORG_DIARYTABLE.ORG_USER_ID.value(t.getOrgUserId()),
                        ORG_DIARYTABLE.ORG_DIARY_BEGIN_DATE.value(t.getOrgDiaryBeginDate()),
                        ORG_DIARYTABLE.ORG_DIARY_END_DATE.value(t.getOrgDiaryEndDate()),
                        ORG_DIARYTABLE.ORG_DIARY_YEAR.value(t.getOrgDiaryYear()),
                        ORG_DIARYTABLE.ORG_DIARY_WEEK.value(t.getOrgDiaryWeek()),
                        ORG_DIARYTABLE.ORG_DIARY_CREATE_DATE.value(t.getOrgDiaryCreateDate()),
                        ORG_DIARYTABLE.ORG_DIARY_SUMMARY.value(t.getOrgDiarySummary()),
                        ORG_DIARYTABLE.ORG_DIARY_STATUS.value(t.getOrgDiaryStatus()),
                        ORG_DIARYTABLE.ORG_DIARY_MODIFY_DATE.value(t.getOrgDiaryModifyDate())).where(
                        ORG_DIARYTABLE.ORG_DIARY_ID.eq(t.getOrgDiaryId()));
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
                return delete(ORG_DIARYTABLE).where(ORG_DIARYTABLE.ORG_DIARY_ID.eq(pk));
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
                return delete(ORG_DIARYTABLE).where(ORG_DIARYTABLE.ORG_DIARY_ID.in(t));
            }
        }, pks);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public OrgDiary getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, OrgDiary.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(ORG_DIARYTABLE).where(ORG_DIARYTABLE.ORG_DIARY_ID.eq(t));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<OrgDiary> query(OrgDiary orgDiary, final OrderBy... orderArgs) {
        if (orgDiary == null) {
            orgDiary = new OrgDiary();
        }
        return getDslTemplate().query(orgDiary, new SelectGenerateCallback<OrgDiary>() {

            @SuppressWarnings("rawtypes")
            public Select generate(OrgDiary t) {
                Select select = selectFrom(ORG_DIARYTABLE).where(
                        and(
                                ORG_DIARYTABLE.ORG_USER_ID.eq(t.getOrgUserId()),
                                ORG_DIARYTABLE.ORG_DIARY_BEGIN_DATE.eq(t.getOrgDiaryBeginDate()),
                                ORG_DIARYTABLE.ORG_DIARY_END_DATE.eq(t.getOrgDiaryEndDate()),
                                ORG_DIARYTABLE.ORG_DIARY_YEAR.eq(t.getOrgDiaryYear()),
                                ORG_DIARYTABLE.ORG_DIARY_WEEK.eq(t.getOrgDiaryWeek()),
                                ORG_DIARYTABLE.ORG_DIARY_CREATE_DATE.eq(t.getOrgDiaryCreateDate()),
                                ORG_DIARYTABLE.ORG_DIARY_SUMMARY.eq(t.getOrgDiarySummary()),
                                ORG_DIARYTABLE.ORG_DIARY_STATUS.eq(t.getOrgDiaryStatus()),
                                ORG_DIARYTABLE.ORG_DIARY_MODIFY_DATE.eq(t.getOrgDiaryModifyDate())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Pager<OrgDiary> queryPager(int start, int limit, OrgDiary orgDiary, final OrderBy... orderArgs) {
        if (orgDiary == null) {
            orgDiary = new OrgDiary();
        }
        return getDslTemplate().queryPager(start, limit, orgDiary, false, new SelectGenerateCallback<OrgDiary>() {

            public Select generate(OrgDiary t) {
                Select select = MysqlSelect.selectFrom(ORG_DIARYTABLE).where(
                        and(
                                ORG_DIARYTABLE.ORG_USER_ID.eq(t.getOrgUserId()),
                                ORG_DIARYTABLE.ORG_DIARY_BEGIN_DATE.eq(t.getOrgDiaryBeginDate()),
                                ORG_DIARYTABLE.ORG_DIARY_END_DATE.eq(t.getOrgDiaryEndDate()),
                                ORG_DIARYTABLE.ORG_DIARY_YEAR.eq(t.getOrgDiaryYear()),
                                ORG_DIARYTABLE.ORG_DIARY_WEEK.eq(t.getOrgDiaryWeek()),
                                ORG_DIARYTABLE.ORG_DIARY_CREATE_DATE.eq(t.getOrgDiaryCreateDate()),
                                ORG_DIARYTABLE.ORG_DIARY_SUMMARY.eq(t.getOrgDiarySummary()),
                                ORG_DIARYTABLE.ORG_DIARY_STATUS.eq(t.getOrgDiaryStatus()),
                                ORG_DIARYTABLE.ORG_DIARY_MODIFY_DATE.eq(t.getOrgDiaryModifyDate())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int[] batchInsert(boolean autoGeneratedKeys, List<OrgDiary> orgDiarys) {
        if (CollectionUtil.isEmpty(orgDiarys)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, orgDiarys, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(ORG_DIARYTABLE).values(
                        ORG_DIARYTABLE.ORG_USER_ID.value(new JdbcNamedParameter("orgUserId")),
                        ORG_DIARYTABLE.ORG_DIARY_BEGIN_DATE.value(new JdbcNamedParameter("orgDiaryBeginDate")),
                        ORG_DIARYTABLE.ORG_DIARY_END_DATE.value(new JdbcNamedParameter("orgDiaryEndDate")),
                        ORG_DIARYTABLE.ORG_DIARY_YEAR.value(new JdbcNamedParameter("orgDiaryYear")),
                        ORG_DIARYTABLE.ORG_DIARY_WEEK.value(new JdbcNamedParameter("orgDiaryWeek")),
                        ORG_DIARYTABLE.ORG_DIARY_CREATE_DATE.value(new JdbcNamedParameter("orgDiaryCreateDate")),
                        ORG_DIARYTABLE.ORG_DIARY_SUMMARY.value(new JdbcNamedParameter("orgDiarySummary")),
                        ORG_DIARYTABLE.ORG_DIARY_STATUS.value(new JdbcNamedParameter("orgDiaryStatus")),
                        ORG_DIARYTABLE.ORG_DIARY_MODIFY_DATE.value(new JdbcNamedParameter("orgDiaryModifyDate")));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int[] batchInsert(List<OrgDiary> orgDiarys) {
        return batchInsert(true, orgDiarys);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int[] batchUpdate(List<OrgDiary> orgDiarys) {
        if (CollectionUtil.isEmpty(orgDiarys)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(orgDiarys, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(ORG_DIARYTABLE).set(
                        ORG_DIARYTABLE.ORG_USER_ID.value(new JdbcNamedParameter("orgUserId")),
                        ORG_DIARYTABLE.ORG_DIARY_BEGIN_DATE.value(new JdbcNamedParameter("orgDiaryBeginDate")),
                        ORG_DIARYTABLE.ORG_DIARY_END_DATE.value(new JdbcNamedParameter("orgDiaryEndDate")),
                        ORG_DIARYTABLE.ORG_DIARY_YEAR.value(new JdbcNamedParameter("orgDiaryYear")),
                        ORG_DIARYTABLE.ORG_DIARY_WEEK.value(new JdbcNamedParameter("orgDiaryWeek")),
                        ORG_DIARYTABLE.ORG_DIARY_CREATE_DATE.value(new JdbcNamedParameter("orgDiaryCreateDate")),
                        ORG_DIARYTABLE.ORG_DIARY_SUMMARY.value(new JdbcNamedParameter("orgDiarySummary")),
                        ORG_DIARYTABLE.ORG_DIARY_STATUS.value(new JdbcNamedParameter("orgDiaryStatus")),
                        ORG_DIARYTABLE.ORG_DIARY_MODIFY_DATE.value(new JdbcNamedParameter("orgDiaryModifyDate"))).where(
                        ORG_DIARYTABLE.ORG_DIARY_ID.eq(new JdbcNamedParameter("orgDiaryId")));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int[] batchDelete(List<OrgDiary> orgDiarys) {
        if (CollectionUtil.isEmpty(orgDiarys)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(orgDiarys, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(ORG_DIARYTABLE).where(and(
                        ORG_DIARYTABLE.ORG_DIARY_ID.eq(new JdbcNamedParameter("orgDiaryId")),
                        ORG_DIARYTABLE.ORG_USER_ID.eq(new JdbcNamedParameter("orgUserId")),
                        ORG_DIARYTABLE.ORG_DIARY_BEGIN_DATE.eq(new JdbcNamedParameter("orgDiaryBeginDate")),
                        ORG_DIARYTABLE.ORG_DIARY_END_DATE.eq(new JdbcNamedParameter("orgDiaryEndDate")),
                        ORG_DIARYTABLE.ORG_DIARY_YEAR.eq(new JdbcNamedParameter("orgDiaryYear")),
                        ORG_DIARYTABLE.ORG_DIARY_WEEK.eq(new JdbcNamedParameter("orgDiaryWeek")),
                        ORG_DIARYTABLE.ORG_DIARY_CREATE_DATE.eq(new JdbcNamedParameter("orgDiaryCreateDate")),
                        ORG_DIARYTABLE.ORG_DIARY_SUMMARY.eq(new JdbcNamedParameter("orgDiarySummary")),
                        ORG_DIARYTABLE.ORG_DIARY_STATUS.eq(new JdbcNamedParameter("orgDiaryStatus")),
                        ORG_DIARYTABLE.ORG_DIARY_MODIFY_DATE.eq(new JdbcNamedParameter("orgDiaryModifyDate"))));
            }
        });
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
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
    public OrgDiary getByTimeAndUser(String orgUserId, Integer orgDiaryYear, Integer orgDiaryWeek) {
        Select select = Select.selectFrom(ORG_DIARYTABLE).where(
                and(ORG_DIARYTABLE.ORG_USER_ID.eq(orgUserId),
                        ORG_DIARYTABLE.ORG_DIARY_YEAR.eq(orgDiaryYear),
                        ORG_DIARYTABLE.ORG_DIARY_WEEK.eq(orgDiaryWeek)));
        return getDslSession().fetchOneResult(select, OrgDiary.class);
    }

    @Override
    public List<OrgDiary> findListByUser(List<String> list) {
        Select select = Select.selectFrom(ORG_DIARYTABLE).
                where(ORG_DIARYTABLE.ORG_USER_ID.in(list.toArray()));
        return getDslSession().fetchList(select, OrgDiary.class);
    }

    @Override
    public List<OrgDiary> findListByUserId(String orgUserId) {
        Select select = Select.selectFrom(ORG_DIARYTABLE).where(
                ORG_DIARYTABLE.ORG_USER_ID.eq(orgUserId));
        return getDslSession().fetchList(select, OrgDiary.class);
    }

    @Override
    public List<OrgDiary> findListOneWeek(Integer year, Integer week) {
        Select select = Select.selectFrom(ORG_DIARYTABLE).where(
                and(ORG_DIARYTABLE.ORG_DIARY_YEAR.eq(year),
                        ORG_DIARYTABLE.ORG_DIARY_WEEK.eq(week)));
        return getDslSession().fetchList(select, OrgDiary.class);
    }

    @Override
    public List<OrgDiary> findSubordinateOneWeek(List<String> list,Integer year,Integer week) {
        Select select = Select.selectFrom(ORG_DIARYTABLE).
                where(and(ORG_DIARYTABLE.ORG_USER_ID.in(list.toArray()),
                        ORG_DIARYTABLE.ORG_DIARY_YEAR.eq(year),
                        ORG_DIARYTABLE.ORG_DIARY_WEEK.eq(week)));
        return getDslSession().fetchList(select, OrgDiary.class);
    }

    public List<String> findUser(List<String> list,Integer year,Integer week){
        Select select=Select.select(ORG_DIARYTABLE.ORG_USER_ID).from(ORG_DIARYTABLE).
                where(and(ORG_DIARYTABLE.ORG_DIARY_YEAR.eq(year),
                        ORG_DIARYTABLE.ORG_DIARY_WEEK.eq(week),
                        ORG_DIARYTABLE.ORG_USER_ID.in(list.toArray())
                ));
        return getDslSession().fetchList(select,String.class);
    }

}