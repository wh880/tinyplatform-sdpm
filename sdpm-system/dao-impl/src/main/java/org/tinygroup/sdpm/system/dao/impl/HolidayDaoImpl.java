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

package org.tinygroup.sdpm.system.dao.impl;

import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.system.dao.HolidayDao;
import org.tinygroup.sdpm.system.dao.pojo.Holiday;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.base.FragmentSql;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.system.dao.constant.HolidayTable.HOLIDAYTABLE;
import static org.tinygroup.sdpm.system.dao.constant.SystemActionTable.SYSTEM_ACTIONTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.tinysqldsl.select.Join.leftJoin;

@Repository
public class HolidayDaoImpl extends TinyDslDaoSupport implements HolidayDao {

    public Holiday add(Holiday holiday) {
        return getDslTemplate().insertAndReturnKey(holiday, new InsertGenerateCallback<Holiday>() {
            public Insert generate(Holiday t) {
                Insert insert = insertInto(HOLIDAYTABLE).values(
                        HOLIDAYTABLE.HOLIDAY_ID.value(t.getHolidayId()),
                        HOLIDAYTABLE.HOLIDAY_NAME.value(t.getHolidayName()),
                        HOLIDAYTABLE.HOLIDAY_ACCOUNT.value(t.getHolidayAccount()),
                        HOLIDAYTABLE.HOLIDAY_DATE.value(t.getHolidayDate()),
                        HOLIDAYTABLE.HOLIDAY_TYPE.value(t.getHolidayType()),
                        HOLIDAYTABLE.HOLIDAY_DELETED.value(t.getHolidayDeleted()),
                        HOLIDAYTABLE.COMPANY_ID.value(t.getCompanyId()),
                        HOLIDAYTABLE.HOLIDAY_DETAIL.value(t.getHolidayDetail()),
                        HOLIDAYTABLE.HOILIDAY_REMARK.value(t.getHoilidayRemark()));
                return insert;
            }
        });
    }

    public int edit(Holiday holiday) {
        if (holiday == null || holiday.getHolidayId() == null) {
            return 0;
        }
        return getDslTemplate().update(holiday, new UpdateGenerateCallback<Holiday>() {
            public Update generate(Holiday t) {
                Update update = update(HOLIDAYTABLE).set(
                        HOLIDAYTABLE.HOLIDAY_NAME.value(t.getHolidayName()),
                        HOLIDAYTABLE.HOLIDAY_ACCOUNT.value(t.getHolidayAccount()),
                        HOLIDAYTABLE.HOLIDAY_DATE.value(t.getHolidayDate()),
                        HOLIDAYTABLE.HOLIDAY_TYPE.value(t.getHolidayType()),
                        HOLIDAYTABLE.HOLIDAY_DELETED.value(t.getHolidayDeleted()),
                        HOLIDAYTABLE.COMPANY_ID.value(t.getCompanyId()),
                        HOLIDAYTABLE.HOLIDAY_DETAIL.value(t.getHolidayDetail()),
                        HOLIDAYTABLE.HOILIDAY_REMARK.value(t.getHoilidayRemark())).where(
                        HOLIDAYTABLE.HOLIDAY_ID.eq(t.getHolidayId()));
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
                return delete(HOLIDAYTABLE).where(HOLIDAYTABLE.HOLIDAY_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(HOLIDAYTABLE).where(HOLIDAYTABLE.HOLIDAY_ID.in(t));
            }
        }, pks);
    }

    public Holiday getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, Holiday.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(HOLIDAYTABLE).where(HOLIDAYTABLE.HOLIDAY_ID.eq(t));
            }
        });
    }

    public List<Holiday> getByKeys(Integer... pks) {
        List<Holiday> list = new ArrayList<Holiday>();
        if (pks == null || pks.length == 0) {

            return list;
        }
        for (int i = 0, n = pks.length; i < n; i++) {
            Holiday holiday = new Holiday();
            holiday = getByKey(pks[i]);
            list.add(holiday);
        }
        return list;
    }

    public List<Holiday> query(Holiday holiday, final OrderBy... orderArgs) {
        if (holiday == null) {
            holiday = new Holiday();
        }
        return getDslTemplate().query(holiday, new SelectGenerateCallback<Holiday>() {

            @SuppressWarnings("rawtypes")
            public Select generate(Holiday t) {
                Select select = selectFrom(HOLIDAYTABLE).where(
                        and(
                                HOLIDAYTABLE.HOLIDAY_NAME.eq(t.getHolidayName()),
                                HOLIDAYTABLE.HOLIDAY_ACCOUNT.eq(t.getHolidayAccount()),
                                HOLIDAYTABLE.HOLIDAY_DATE.eq(t.getHolidayDate()),
                                HOLIDAYTABLE.HOLIDAY_TYPE.eq(t.getHolidayType()),
                                HOLIDAYTABLE.HOLIDAY_DELETED.eq(t.getHolidayDeleted()),
                                HOLIDAYTABLE.COMPANY_ID.eq(t.getCompanyId()),
                                HOLIDAYTABLE.HOLIDAY_DETAIL.eq(t.getHolidayDetail()),
                                HOLIDAYTABLE.HOILIDAY_REMARK.eq(t.getHoilidayRemark())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public Pager<Holiday> queryPager(int start, int limit, Holiday holiday, final OrderBy... orderArgs) {
        if (holiday == null) {
            holiday = new Holiday();
        }
        final Holiday finalHoliday = holiday;
        return getDslTemplate().queryPager(start, limit, holiday, false, new SelectGenerateCallback<Holiday>() {
            public Select generate(Holiday t) {
                Column column = HOLIDAYTABLE.HOLIDAY_ID;
                Condition condition = null;
                if (orderArgs != null && orderArgs.length > 0 && SYSTEM_ACTIONTABLE.ACTION_DATE.toString().equals(((Column) orderArgs[0].getOrderByElement().getExpression()).getColumnName())) {
                    column = SYSTEM_ACTIONTABLE.ACTION_ID;
                    condition = SYSTEM_ACTIONTABLE.ACTION_OBJECT_TYPE.eq("holiday");
                }
                Column[] groupBy;
                if (!column.toString().equals(HOLIDAYTABLE.HOLIDAY_ID.toString())) {
                    groupBy = new Column[]{column, HOLIDAYTABLE.HOLIDAY_ID, HOLIDAYTABLE.HOLIDAY_NAME, SYSTEM_ACTIONTABLE.ACTION_ACTION, SYSTEM_ACTIONTABLE.ACTION_ACTOR, SYSTEM_ACTIONTABLE.ACTION_DATE};
                } else {
                    groupBy = new Column[]{column, HOLIDAYTABLE.HOLIDAY_NAME, SYSTEM_ACTIONTABLE.ACTION_ACTION, SYSTEM_ACTIONTABLE.ACTION_ACTOR, SYSTEM_ACTIONTABLE.ACTION_DATE};
                }
                Select select = MysqlSelect.select(HOLIDAYTABLE.HOLIDAY_ID, HOLIDAYTABLE.HOLIDAY_NAME, FragmentSql.fragmentSelect("holiday.*, system_action.action_action as actionAction ," +
                        "system_action.action_actor as actionActor," +
                        "system_action.action_date as actionDate")).from(HOLIDAYTABLE).join(leftJoin(
                        SYSTEM_ACTIONTABLE, SYSTEM_ACTIONTABLE.ACTION_OBJECT_ID.eq(HOLIDAYTABLE.HOLIDAY_ID))).where(
                        and(
                                condition,
                                HOLIDAYTABLE.HOLIDAY_NAME.eq(t.getHolidayName()),
                                HOLIDAYTABLE.HOLIDAY_ACCOUNT.eq(t.getHolidayAccount()),
                                HOLIDAYTABLE.HOLIDAY_DATE.eq(t.getHolidayDate()),
                                HOLIDAYTABLE.HOLIDAY_TYPE.eq(t.getHolidayType()),
                                HOLIDAYTABLE.HOLIDAY_DELETED.eq(t.getHolidayDeleted()),
                                HOLIDAYTABLE.COMPANY_ID.eq(t.getCompanyId()),
                                HOLIDAYTABLE.HOLIDAY_DETAIL.eq(t.getHolidayDetail()),
                                HOLIDAYTABLE.HOILIDAY_REMARK.eq(t.getHoilidayRemark()))).groupBy(groupBy);
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<Holiday> holidays) {
        if (CollectionUtil.isEmpty(holidays)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, holidays, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(HOLIDAYTABLE).values(
                        HOLIDAYTABLE.HOLIDAY_NAME.value(new JdbcNamedParameter("holidayName")),
                        HOLIDAYTABLE.HOLIDAY_ACCOUNT.value(new JdbcNamedParameter("holidayAccount")),
                        HOLIDAYTABLE.HOLIDAY_DATE.value(new JdbcNamedParameter("holidayDate")),
                        HOLIDAYTABLE.HOLIDAY_TYPE.value(new JdbcNamedParameter("holidayType")),
                        HOLIDAYTABLE.HOLIDAY_DELETED.value(new JdbcNamedParameter("holidayDeleted")),
                        HOLIDAYTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
                        HOLIDAYTABLE.HOLIDAY_DETAIL.value(new JdbcNamedParameter("holidayDetail")),
                        HOLIDAYTABLE.HOILIDAY_REMARK.value(new JdbcNamedParameter("hoilidayRemark")));
            }
        });
    }

    public int[] batchInsert(List<Holiday> holidays) {
        return batchInsert(true, holidays);
    }

    public int[] batchUpdate(List<Holiday> holidays) {
        if (CollectionUtil.isEmpty(holidays)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(holidays, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(HOLIDAYTABLE).set(
                        HOLIDAYTABLE.HOLIDAY_NAME.value(new JdbcNamedParameter("holidayName")),
                        HOLIDAYTABLE.HOLIDAY_ACCOUNT.value(new JdbcNamedParameter("holidayAccount")),
                        HOLIDAYTABLE.HOLIDAY_DATE.value(new JdbcNamedParameter("holidayDate")),
                        HOLIDAYTABLE.HOLIDAY_TYPE.value(new JdbcNamedParameter("holidayType")),
                        HOLIDAYTABLE.HOLIDAY_DELETED.value(new JdbcNamedParameter("holidayDeleted")),
                        HOLIDAYTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
                        HOLIDAYTABLE.HOLIDAY_DETAIL.value(new JdbcNamedParameter("holidayDetail")),
                        HOLIDAYTABLE.HOILIDAY_REMARK.value(new JdbcNamedParameter("hoilidayRemark"))).where(
                        HOLIDAYTABLE.HOLIDAY_ID.eq(new JdbcNamedParameter("holidayId")));
            }
        });
    }

    public int[] batchDelete(List<Holiday> holidays) {
        if (CollectionUtil.isEmpty(holidays)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(holidays, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(HOLIDAYTABLE).where(and(
                        HOLIDAYTABLE.HOLIDAY_ID.eq(new JdbcNamedParameter("holidayId")),
                        HOLIDAYTABLE.HOLIDAY_NAME.eq(new JdbcNamedParameter("holidayName")),
                        HOLIDAYTABLE.HOLIDAY_ACCOUNT.eq(new JdbcNamedParameter("holidayAccount")),
                        HOLIDAYTABLE.HOLIDAY_DATE.eq(new JdbcNamedParameter("holidayDate")),
                        HOLIDAYTABLE.HOLIDAY_TYPE.eq(new JdbcNamedParameter("holidayType")),
                        HOLIDAYTABLE.HOLIDAY_DELETED.eq(new JdbcNamedParameter("holidayDeleted")),
                        HOLIDAYTABLE.COMPANY_ID.eq(new JdbcNamedParameter("companyId")),
                        HOLIDAYTABLE.HOLIDAY_DETAIL.eq(new JdbcNamedParameter("holidayDetail")),
                        HOLIDAYTABLE.HOILIDAY_REMARK.eq(new JdbcNamedParameter("hoilidayRemark"))));
            }
        });
    }

    private Select addOrderByElements(Select select, OrderBy... orderBies) {
        List<OrderByElement> orderByElements = new ArrayList<OrderByElement>();
        for (int i = 0; orderBies != null && i < orderBies.length; i++) {
            OrderByElement tempElement = null;
            if (orderBies[i] != null) {
                tempElement = orderBies[i].getOrderByElement();
            }
            if (tempElement != null) {
                orderByElements.add(tempElement);
            }
        }
        if (orderByElements.size() > 0) {
            select.orderBy(orderByElements.toArray(new OrderByElement[0]));
        }
        return select;
    }

    public int softDelete(Holiday holiday) {
        // TODO Auto-generated method stub
        if (holiday == null || holiday.getHolidayId() == null) {
            return 0;
        }
        return getDslTemplate().update(holiday, new UpdateGenerateCallback<Holiday>() {
            public Update generate(Holiday t) {
                Update update = update(HOLIDAYTABLE).set(

                        HOLIDAYTABLE.HOLIDAY_DELETED.value(t.getHolidayDeleted())
                ).where(
                        HOLIDAYTABLE.HOLIDAY_ID.eq(t.getHolidayId()));
                return update;
            }
        });
    }

    public List<Holiday> findByKeys(Integer... ids) {
        // TODO Auto-generated method stub
        List<Holiday> list = new ArrayList<Holiday>();
        if (ids == null || ids.length == 0) {

            return list;
        }
        for (int i = 0, n = ids.length; i < n; i++) {
            Holiday holiday = getByKey(ids[i]);
            list.add(holiday);
        }
        return list;
    }

    public int[] batchsoftdelete(List<Holiday> list) {
        // TODO Auto-generated method stub
        if (CollectionUtil.isEmpty(list)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(list, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(HOLIDAYTABLE).set(
                        HOLIDAYTABLE.HOLIDAY_DELETED.value(new JdbcNamedParameter("holidayDeleted")),
                        HOLIDAYTABLE.HOILIDAY_REMARK.value(new JdbcNamedParameter("hoilidayRemark"))).where(
                        HOLIDAYTABLE.HOLIDAY_ID.eq(new JdbcNamedParameter("holidayId")));
            }
        });
    }

    @Override
    public Pager<Holiday> findByHolidayDeleted(Integer start, Integer limit, Holiday holiday, final OrderBy... orderArgs) {
        if (holiday == null) {
            holiday = new Holiday();
        }
        final Holiday finalHoliday = holiday;
        return getDslTemplate().queryPager(start, limit, holiday, false, new SelectGenerateCallback<Holiday>() {
            public Select generate(Holiday t) {
                Column column = HOLIDAYTABLE.HOLIDAY_ID;
                Condition condition = null;
                if (orderArgs != null && orderArgs.length > 0 && SYSTEM_ACTIONTABLE.ACTION_DATE.toString().equals(((Column) orderArgs[0].getOrderByElement().getExpression()).getColumnName())) {
                    column = SYSTEM_ACTIONTABLE.ACTION_ID;
                    condition = SYSTEM_ACTIONTABLE.ACTION_OBJECT_TYPE.eq("holiday");
                }
                Column[] groupBy;
                if (!column.toString().equals(HOLIDAYTABLE.HOLIDAY_ID.toString())) {
                    groupBy = new Column[]{column, HOLIDAYTABLE.HOLIDAY_ID, HOLIDAYTABLE.HOLIDAY_NAME};
                } else {
                    groupBy = new Column[]{column, HOLIDAYTABLE.HOLIDAY_NAME};
                }
                Select select = MysqlSelect.select(FragmentSql.fragmentSelect("holiday.*")).from(HOLIDAYTABLE).where(
                        and(
                                condition,
                                HOLIDAYTABLE.HOLIDAY_NAME.eq(t.getHolidayName()),
                                HOLIDAYTABLE.HOLIDAY_ACCOUNT.eq(t.getHolidayAccount()),
                                HOLIDAYTABLE.HOLIDAY_DATE.eq(t.getHolidayDate()),
                                HOLIDAYTABLE.HOLIDAY_TYPE.eq(t.getHolidayType()),
                                HOLIDAYTABLE.HOLIDAY_DELETED.eq(t.getHolidayDeleted()),
                                HOLIDAYTABLE.COMPANY_ID.eq(t.getCompanyId()),
                                HOLIDAYTABLE.HOLIDAY_DETAIL.eq(t.getHolidayDetail()),
                                HOLIDAYTABLE.HOILIDAY_REMARK.eq(t.getHoilidayRemark()))).groupBy(groupBy);
                return addOrderByElements(select, orderArgs);
            }
        });
    }


}
