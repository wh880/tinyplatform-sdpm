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
import org.tinygroup.sdpm.system.dao.SystemEffortDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.tinygroup.sdpm.system.dao.constant.SystemEffortTable.SYSTEM_EFFORTTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class SystemEffortDaoImpl extends TinyDslDaoSupport implements SystemEffortDao {

    public SystemEffort add(SystemEffort systemEffort) {
        return getDslTemplate().insertAndReturnKey(systemEffort, new InsertGenerateCallback<SystemEffort>() {
            public Insert generate(SystemEffort t) {
                Insert insert = insertInto(SYSTEM_EFFORTTABLE).values(
                        SYSTEM_EFFORTTABLE.EFFORT_ID.value(t.getEffortId()),
                        SYSTEM_EFFORTTABLE.EFFORT_PROJECT.value(t.getEffortProject()),
                        SYSTEM_EFFORTTABLE.EFFORT_PRODUCT.value(t.getEffortProduct()),
                        SYSTEM_EFFORTTABLE.EFFORT_WORK.value(t.getEffortWork()),
                        SYSTEM_EFFORTTABLE.EFFORT_CONSUMED.value(t.getEffortConsumed()),
                        SYSTEM_EFFORTTABLE.EFFORT_DATE.value(t.getEffortDate()),
                        SYSTEM_EFFORTTABLE.EFFORT_LEFT.value(t.getEffortLeft()),
                        SYSTEM_EFFORTTABLE.EFFORT_BEGIN.value(t.getEffortBegin()),
                        SYSTEM_EFFORTTABLE.EFFORT_END.value(t.getEffortEnd()),
                        SYSTEM_EFFORTTABLE.EFFORT_OBJECT_ID.value(t.getEffortObjectId()),
                        SYSTEM_EFFORTTABLE.EFFORT_OBJECT_TYPE.value(t.getEffortObjectType()),
                        SYSTEM_EFFORTTABLE.EFFORT_ACCOUNT.value(t.getEffortAccount()));
                return insert;
            }
        });
    }

    public int edit(SystemEffort systemEffort) {
        if (systemEffort == null || systemEffort.getEffortId() == null) {
            return 0;
        }
        return getDslTemplate().update(systemEffort, new UpdateGenerateCallback<SystemEffort>() {
            public Update generate(SystemEffort t) {
                Update update = update(SYSTEM_EFFORTTABLE).set(
                        SYSTEM_EFFORTTABLE.EFFORT_PROJECT.value(t.getEffortProject()),
                        SYSTEM_EFFORTTABLE.EFFORT_PRODUCT.value(t.getEffortProduct()),
                        SYSTEM_EFFORTTABLE.EFFORT_WORK.value(t.getEffortWork()),
                        SYSTEM_EFFORTTABLE.EFFORT_CONSUMED.value(t.getEffortConsumed()),
                        SYSTEM_EFFORTTABLE.EFFORT_DATE.value(t.getEffortDate()),
                        SYSTEM_EFFORTTABLE.EFFORT_LEFT.value(t.getEffortLeft()),
                        SYSTEM_EFFORTTABLE.EFFORT_BEGIN.value(t.getEffortBegin()),
                        SYSTEM_EFFORTTABLE.EFFORT_END.value(t.getEffortEnd()),
                        SYSTEM_EFFORTTABLE.EFFORT_OBJECT_ID.value(t.getEffortObjectId()),
                        SYSTEM_EFFORTTABLE.EFFORT_OBJECT_TYPE.value(t.getEffortObjectType()),
                        SYSTEM_EFFORTTABLE.EFFORT_ACCOUNT.value(t.getEffortAccount())).where(
                        SYSTEM_EFFORTTABLE.EFFORT_ID.eq(t.getEffortId()));
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
                return delete(SYSTEM_EFFORTTABLE).where(SYSTEM_EFFORTTABLE.EFFORT_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(SYSTEM_EFFORTTABLE).where(SYSTEM_EFFORTTABLE.EFFORT_ID.in(t));
            }
        }, pks);
    }

    public SystemEffort getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, SystemEffort.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(SYSTEM_EFFORTTABLE).where(SYSTEM_EFFORTTABLE.EFFORT_ID.eq(t));
            }
        });
    }

    public List<SystemEffort> query(SystemEffort systemEffort, final OrderBy... orderArgs) {
        if (systemEffort == null) {
            systemEffort = new SystemEffort();
        }
        return getDslTemplate().query(systemEffort, new SelectGenerateCallback<SystemEffort>() {

            @SuppressWarnings("rawtypes")
            public Select generate(SystemEffort t) {
                Select select = selectFrom(SYSTEM_EFFORTTABLE).where(
                        and(
                                SYSTEM_EFFORTTABLE.EFFORT_PROJECT.eq(t.getEffortProject()),
                                SYSTEM_EFFORTTABLE.EFFORT_PRODUCT.eq(t.getEffortProduct()),
                                SYSTEM_EFFORTTABLE.EFFORT_WORK.eq(t.getEffortWork()),
                                SYSTEM_EFFORTTABLE.EFFORT_CONSUMED.eq(t.getEffortConsumed()),
                                SYSTEM_EFFORTTABLE.EFFORT_DATE.eq(t.getEffortDate()),
                                SYSTEM_EFFORTTABLE.EFFORT_LEFT.eq(t.getEffortLeft()),
                                SYSTEM_EFFORTTABLE.EFFORT_BEGIN.eq(t.getEffortBegin()),
                                SYSTEM_EFFORTTABLE.EFFORT_END.eq(t.getEffortEnd()),
                                SYSTEM_EFFORTTABLE.EFFORT_OBJECT_ID.eq(t.getEffortObjectId()),
                                SYSTEM_EFFORTTABLE.EFFORT_OBJECT_TYPE.eq(t.getEffortObjectType()),
                                SYSTEM_EFFORTTABLE.EFFORT_ACCOUNT.eq(t.getEffortAccount())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public Pager<SystemEffort> queryPager(int start, int limit, SystemEffort systemEffort, final OrderBy... orderArgs) {
        if (systemEffort == null) {
            systemEffort = new SystemEffort();
        }
        return getDslTemplate().queryPager(start, limit, systemEffort, false, new SelectGenerateCallback<SystemEffort>() {

            public Select generate(SystemEffort t) {
                Select select = MysqlSelect.selectFrom(SYSTEM_EFFORTTABLE).where(
                        and(
                                SYSTEM_EFFORTTABLE.EFFORT_PROJECT.eq(t.getEffortProject()),
                                SYSTEM_EFFORTTABLE.EFFORT_PRODUCT.eq(t.getEffortProduct()),
                                SYSTEM_EFFORTTABLE.EFFORT_WORK.eq(t.getEffortWork()),
                                SYSTEM_EFFORTTABLE.EFFORT_CONSUMED.eq(t.getEffortConsumed()),
                                SYSTEM_EFFORTTABLE.EFFORT_DATE.eq(t.getEffortDate()),
                                SYSTEM_EFFORTTABLE.EFFORT_LEFT.eq(t.getEffortLeft()),
                                SYSTEM_EFFORTTABLE.EFFORT_BEGIN.eq(t.getEffortBegin()),
                                SYSTEM_EFFORTTABLE.EFFORT_END.eq(t.getEffortEnd()),
                                SYSTEM_EFFORTTABLE.EFFORT_OBJECT_ID.eq(t.getEffortObjectId()),
                                SYSTEM_EFFORTTABLE.EFFORT_OBJECT_TYPE.eq(t.getEffortObjectType()),
                                SYSTEM_EFFORTTABLE.EFFORT_ACCOUNT.eq(t.getEffortAccount())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<SystemEffort> systemEfforts) {
        if (CollectionUtil.isEmpty(systemEfforts)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, systemEfforts, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(SYSTEM_EFFORTTABLE).values(
                        SYSTEM_EFFORTTABLE.EFFORT_PROJECT.value(new JdbcNamedParameter("effortProject")),
                        SYSTEM_EFFORTTABLE.EFFORT_PRODUCT.value(new JdbcNamedParameter("effortProduct")),
                        SYSTEM_EFFORTTABLE.EFFORT_WORK.value(new JdbcNamedParameter("effortWork")),
                        SYSTEM_EFFORTTABLE.EFFORT_CONSUMED.value(new JdbcNamedParameter("effortConsumed")),
                        SYSTEM_EFFORTTABLE.EFFORT_DATE.value(new JdbcNamedParameter("effortDate")),
                        SYSTEM_EFFORTTABLE.EFFORT_LEFT.value(new JdbcNamedParameter("effortLeft")),
                        SYSTEM_EFFORTTABLE.EFFORT_BEGIN.value(new JdbcNamedParameter("effortBegin")),
                        SYSTEM_EFFORTTABLE.EFFORT_END.value(new JdbcNamedParameter("effortEnd")),
                        SYSTEM_EFFORTTABLE.EFFORT_OBJECT_ID.value(new JdbcNamedParameter("effortObjectId")),
                        SYSTEM_EFFORTTABLE.EFFORT_OBJECT_TYPE.value(new JdbcNamedParameter("effortObjectType")),
                        SYSTEM_EFFORTTABLE.EFFORT_ACCOUNT.value(new JdbcNamedParameter("effortAccount")));
            }
        });
    }

    public int[] batchInsert(List<SystemEffort> systemEfforts) {
        return batchInsert(true, systemEfforts);
    }

    public int[] batchUpdate(List<SystemEffort> systemEfforts) {
        if (CollectionUtil.isEmpty(systemEfforts)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(systemEfforts, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(SYSTEM_EFFORTTABLE).set(
                        SYSTEM_EFFORTTABLE.EFFORT_PROJECT.value(new JdbcNamedParameter("effortProject")),
                        SYSTEM_EFFORTTABLE.EFFORT_PRODUCT.value(new JdbcNamedParameter("effortProduct")),
                        SYSTEM_EFFORTTABLE.EFFORT_WORK.value(new JdbcNamedParameter("effortWork")),
                        SYSTEM_EFFORTTABLE.EFFORT_CONSUMED.value(new JdbcNamedParameter("effortConsumed")),
                        SYSTEM_EFFORTTABLE.EFFORT_DATE.value(new JdbcNamedParameter("effortDate")),
                        SYSTEM_EFFORTTABLE.EFFORT_LEFT.value(new JdbcNamedParameter("effortLeft")),
                        SYSTEM_EFFORTTABLE.EFFORT_BEGIN.value(new JdbcNamedParameter("effortBegin")),
                        SYSTEM_EFFORTTABLE.EFFORT_END.value(new JdbcNamedParameter("effortEnd")),
                        SYSTEM_EFFORTTABLE.EFFORT_OBJECT_ID.value(new JdbcNamedParameter("effortObjectId")),
                        SYSTEM_EFFORTTABLE.EFFORT_OBJECT_TYPE.value(new JdbcNamedParameter("effortObjectType")),
                        SYSTEM_EFFORTTABLE.EFFORT_ACCOUNT.value(new JdbcNamedParameter("effortAccount"))).where(
                        SYSTEM_EFFORTTABLE.EFFORT_ID.eq(new JdbcNamedParameter("effortId")));
            }
        });
    }

    public int[] batchDelete(List<SystemEffort> systemEfforts) {
        if (CollectionUtil.isEmpty(systemEfforts)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(systemEfforts, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(SYSTEM_EFFORTTABLE).where(and(
                        SYSTEM_EFFORTTABLE.EFFORT_ID.eq(new JdbcNamedParameter("effortId")),
                        SYSTEM_EFFORTTABLE.EFFORT_PROJECT.eq(new JdbcNamedParameter("effortProject")),
                        SYSTEM_EFFORTTABLE.EFFORT_PRODUCT.eq(new JdbcNamedParameter("effortProduct")),
                        SYSTEM_EFFORTTABLE.EFFORT_WORK.eq(new JdbcNamedParameter("effortWork")),
                        SYSTEM_EFFORTTABLE.EFFORT_CONSUMED.eq(new JdbcNamedParameter("effortConsumed")),
                        SYSTEM_EFFORTTABLE.EFFORT_DATE.eq(new JdbcNamedParameter("effortDate")),
                        SYSTEM_EFFORTTABLE.EFFORT_LEFT.eq(new JdbcNamedParameter("effortLeft")),
                        SYSTEM_EFFORTTABLE.EFFORT_BEGIN.eq(new JdbcNamedParameter("effortBegin")),
                        SYSTEM_EFFORTTABLE.EFFORT_END.eq(new JdbcNamedParameter("effortEnd")),
                        SYSTEM_EFFORTTABLE.EFFORT_OBJECT_ID.eq(new JdbcNamedParameter("effortObjectId")),
                        SYSTEM_EFFORTTABLE.EFFORT_OBJECT_TYPE.eq(new JdbcNamedParameter("effortObjectType")),
                        SYSTEM_EFFORTTABLE.EFFORT_ACCOUNT.eq(new JdbcNamedParameter("effortAccount"))));
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

    public List<SystemEffort> findBetweenDate(SystemEffort t, Date beginDate, Date endDate) {
        if (t == null) {
            t = new SystemEffort();
        }
        Select select = selectFrom(SYSTEM_EFFORTTABLE).where(
                and(
                        SYSTEM_EFFORTTABLE.EFFORT_PROJECT.eq(t.getEffortProject()),
                        SYSTEM_EFFORTTABLE.EFFORT_PRODUCT.eq(t.getEffortProduct()),
                        SYSTEM_EFFORTTABLE.EFFORT_WORK.eq(t.getEffortWork()),
                        SYSTEM_EFFORTTABLE.EFFORT_CONSUMED.eq(t.getEffortConsumed()),
                        SYSTEM_EFFORTTABLE.EFFORT_DATE.between(beginDate, endDate),
                        SYSTEM_EFFORTTABLE.EFFORT_LEFT.eq(t.getEffortLeft()),
                        SYSTEM_EFFORTTABLE.EFFORT_OBJECT_ID.eq(t.getEffortObjectId()),
                        SYSTEM_EFFORTTABLE.EFFORT_OBJECT_TYPE.eq(t.getEffortObjectType()),
                        SYSTEM_EFFORTTABLE.EFFORT_ACCOUNT.eq(t.getEffortAccount())));
        return getDslSession().fetchList(select, SystemEffort.class);
    }

    public Pager<SystemEffort> findByDate(int start, int limit,
                                          SystemEffort effort, final Date startDate, final Date endDate,
                                          final OrderBy... orderArgs) {
        if (effort == null) {
            effort = new SystemEffort();
        }
        return getDslTemplate().queryPager(start, limit, effort, false, new SelectGenerateCallback<SystemEffort>() {

            public Select generate(SystemEffort t) {
                Select select = MysqlSelect.selectFrom(SYSTEM_EFFORTTABLE).where(
                        and(
                                SYSTEM_EFFORTTABLE.EFFORT_PROJECT.eq(t.getEffortProject()),
                                SYSTEM_EFFORTTABLE.EFFORT_PRODUCT.eq(t.getEffortProduct()),
                                SYSTEM_EFFORTTABLE.EFFORT_WORK.eq(t.getEffortWork()),
                                SYSTEM_EFFORTTABLE.EFFORT_CONSUMED.eq(t.getEffortConsumed()),
                                SYSTEM_EFFORTTABLE.EFFORT_DATE.between(startDate, endDate),
                                SYSTEM_EFFORTTABLE.EFFORT_LEFT.eq(t.getEffortLeft()),
                                SYSTEM_EFFORTTABLE.EFFORT_BEGIN.eq(t.getEffortBegin()),
                                SYSTEM_EFFORTTABLE.EFFORT_END.eq(t.getEffortEnd()),
                                SYSTEM_EFFORTTABLE.EFFORT_OBJECT_ID.eq(t.getEffortObjectId()),
                                SYSTEM_EFFORTTABLE.EFFORT_OBJECT_TYPE.eq(t.getEffortObjectType()),
                                SYSTEM_EFFORTTABLE.EFFORT_ACCOUNT.eq(t.getEffortAccount())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    @Override
    public List<SystemEffort> findByUserAndDate(String userAccount, String beginDate, String endDate) {
        Select select=Select.selectFrom(SYSTEM_EFFORTTABLE).
                where(and(SYSTEM_EFFORTTABLE.EFFORT_ACCOUNT.eq(userAccount),
                        SYSTEM_EFFORTTABLE.EFFORT_DATE.between(beginDate,endDate)));
        return getDslSession().fetchList(select,SystemEffort.class);
    }

    @Override
    public List<SystemEffort> findListByIdList(List<Integer> list) {
        Select select=Select.selectFrom(SYSTEM_EFFORTTABLE).
                where(SYSTEM_EFFORTTABLE.EFFORT_ID.in(list.toArray()));
        return getDslSession().fetchList(select,SystemEffort.class);
    }
}
