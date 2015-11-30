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
import org.tinygroup.sdpm.system.dao.SystemHistoryDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.system.dao.constant.SystemHistoryTable.SYSTEM_HISTORYTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class SystemHistoryDaoImpl extends TinyDslDaoSupport implements SystemHistoryDao {

    public SystemHistory add(SystemHistory systemHistory) {
        return getDslTemplate().insertAndReturnKey(systemHistory, new InsertGenerateCallback<SystemHistory>() {
            public Insert generate(SystemHistory t) {
                Insert insert = insertInto(SYSTEM_HISTORYTABLE).values(
                        SYSTEM_HISTORYTABLE.HISTORY_ID.value(t.getHistoryId()),
                        SYSTEM_HISTORYTABLE.HISTORY_ACTION.value(t.getHistoryAction()),
                        SYSTEM_HISTORYTABLE.HISTORY_FIELD.value(t.getHistoryField()),
                        SYSTEM_HISTORYTABLE.HISTORY_NEW.value(t.getHistoryNew()),
                        SYSTEM_HISTORYTABLE.HISTORY_OLD.value(t.getHistoryOld()),
                        SYSTEM_HISTORYTABLE.HISTORY_DIFF.value(t.getHistoryDiff()));
                return insert;
            }
        });
    }

    public int edit(SystemHistory systemHistory) {
        if (systemHistory == null || systemHistory.getHistoryId() == null) {
            return 0;
        }
        return getDslTemplate().update(systemHistory, new UpdateGenerateCallback<SystemHistory>() {
            public Update generate(SystemHistory t) {
                Update update = update(SYSTEM_HISTORYTABLE).set(
                        SYSTEM_HISTORYTABLE.HISTORY_ACTION.value(t.getHistoryAction()),
                        SYSTEM_HISTORYTABLE.HISTORY_FIELD.value(t.getHistoryField()),
                        SYSTEM_HISTORYTABLE.HISTORY_NEW.value(t.getHistoryNew()),
                        SYSTEM_HISTORYTABLE.HISTORY_OLD.value(t.getHistoryOld()),
                        SYSTEM_HISTORYTABLE.HISTORY_DIFF.value(t.getHistoryDiff())).where(
                        SYSTEM_HISTORYTABLE.HISTORY_ID.eq(t.getHistoryId()));
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
                return delete(SYSTEM_HISTORYTABLE).where(SYSTEM_HISTORYTABLE.HISTORY_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(SYSTEM_HISTORYTABLE).where(SYSTEM_HISTORYTABLE.HISTORY_ID.in(t));
            }
        }, pks);
    }

    public SystemHistory getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, SystemHistory.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(SYSTEM_HISTORYTABLE).where(SYSTEM_HISTORYTABLE.HISTORY_ID.eq(t));
            }
        });
    }

    public List<SystemHistory> query(SystemHistory systemHistory, final OrderBy... orderBies) {
        if (systemHistory == null) {
            systemHistory = new SystemHistory();
        }
        return getDslTemplate().query(systemHistory, new SelectGenerateCallback<SystemHistory>() {

            @SuppressWarnings("rawtypes")
            public Select generate(SystemHistory t) {
                Select select = selectFrom(SYSTEM_HISTORYTABLE).where(
                        and(
                                SYSTEM_HISTORYTABLE.HISTORY_ACTION.eq(t.getHistoryAction()),
                                SYSTEM_HISTORYTABLE.HISTORY_FIELD.eq(t.getHistoryField()),
                                SYSTEM_HISTORYTABLE.HISTORY_NEW.eq(t.getHistoryNew()),
                                SYSTEM_HISTORYTABLE.HISTORY_OLD.eq(t.getHistoryOld()),
                                SYSTEM_HISTORYTABLE.HISTORY_DIFF.eq(t.getHistoryDiff())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public Pager<SystemHistory> queryPager(int start, int limit, SystemHistory systemHistory, final OrderBy... orderBies) {
        if (systemHistory == null) {
            systemHistory = new SystemHistory();
        }
        return getDslTemplate().queryPager(start, limit, systemHistory, false, new SelectGenerateCallback<SystemHistory>() {

            public Select generate(SystemHistory t) {
                Select select = MysqlSelect.selectFrom(SYSTEM_HISTORYTABLE).where(
                        and(
                                SYSTEM_HISTORYTABLE.HISTORY_ACTION.eq(t.getHistoryAction()),
                                SYSTEM_HISTORYTABLE.HISTORY_FIELD.eq(t.getHistoryField()),
                                SYSTEM_HISTORYTABLE.HISTORY_NEW.eq(t.getHistoryNew()),
                                SYSTEM_HISTORYTABLE.HISTORY_OLD.eq(t.getHistoryOld()),
                                SYSTEM_HISTORYTABLE.HISTORY_DIFF.eq(t.getHistoryDiff())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<SystemHistory> systemHistorys) {
        if (CollectionUtil.isEmpty(systemHistorys)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, systemHistorys, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(SYSTEM_HISTORYTABLE).values(
                        SYSTEM_HISTORYTABLE.HISTORY_ACTION.value(new JdbcNamedParameter("historyAction")),
                        SYSTEM_HISTORYTABLE.HISTORY_FIELD.value(new JdbcNamedParameter("historyField")),
                        SYSTEM_HISTORYTABLE.HISTORY_NEW.value(new JdbcNamedParameter("historyNew")),
                        SYSTEM_HISTORYTABLE.HISTORY_OLD.value(new JdbcNamedParameter("historyOld")),
                        SYSTEM_HISTORYTABLE.HISTORY_DIFF.value(new JdbcNamedParameter("historyDiff")));
            }
        });
    }

    public int[] batchInsert(List<SystemHistory> systemHistorys) {
        return batchInsert(true, systemHistorys);
    }

    public int[] batchUpdate(List<SystemHistory> systemHistorys) {
        if (CollectionUtil.isEmpty(systemHistorys)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(systemHistorys, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(SYSTEM_HISTORYTABLE).set(
                        SYSTEM_HISTORYTABLE.HISTORY_ACTION.value(new JdbcNamedParameter("historyAction")),
                        SYSTEM_HISTORYTABLE.HISTORY_FIELD.value(new JdbcNamedParameter("historyField")),
                        SYSTEM_HISTORYTABLE.HISTORY_NEW.value(new JdbcNamedParameter("historyNew")),
                        SYSTEM_HISTORYTABLE.HISTORY_OLD.value(new JdbcNamedParameter("historyOld")),
                        SYSTEM_HISTORYTABLE.HISTORY_DIFF.value(new JdbcNamedParameter("historyDiff"))).where(
                        SYSTEM_HISTORYTABLE.HISTORY_ID.eq(new JdbcNamedParameter("historyId")));
            }
        });
    }

    public int[] batchDelete(List<SystemHistory> systemHistorys) {
        if (CollectionUtil.isEmpty(systemHistorys)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(systemHistorys, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(SYSTEM_HISTORYTABLE).where(and(
                        SYSTEM_HISTORYTABLE.HISTORY_ID.eq(new JdbcNamedParameter("historyId")),
                        SYSTEM_HISTORYTABLE.HISTORY_ACTION.eq(new JdbcNamedParameter("historyAction")),
                        SYSTEM_HISTORYTABLE.HISTORY_FIELD.eq(new JdbcNamedParameter("historyField")),
                        SYSTEM_HISTORYTABLE.HISTORY_NEW.eq(new JdbcNamedParameter("historyNew")),
                        SYSTEM_HISTORYTABLE.HISTORY_OLD.eq(new JdbcNamedParameter("historyOld")),
                        SYSTEM_HISTORYTABLE.HISTORY_DIFF.eq(new JdbcNamedParameter("historyDiff"))));
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
