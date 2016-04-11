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

package org.tinygroup.sdpm.service.dao.impl;


import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.service.dao.ServiceSlaDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceSla;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.Join;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.product.dao.constant.ProductTable.PRODUCTTABLE;
import static org.tinygroup.sdpm.service.dao.constant.ServiceClientTable.SERVICE_CLIENTTABLE;
import static org.tinygroup.sdpm.service.dao.constant.ServiceSlaTable.SERVICE_SLATABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class ServiceSlaDaoImpl extends TinyDslDaoSupport implements ServiceSlaDao {

    public static final Integer DELETE_YES = 1;
    public static final Integer DELETE_NO = 0;

    public ServiceSla add(ServiceSla serviceSla) {
        return getDslTemplate().insertAndReturnKey(serviceSla, new InsertGenerateCallback<ServiceSla>() {
            public Insert generate(ServiceSla t) {
                Insert insert = insertInto(SERVICE_SLATABLE).values(
                        SERVICE_SLATABLE.SLA_ID.value(t.getSlaId()),
                        SERVICE_SLATABLE.SERVICE_LEVEL.value(t.getServiceLevel()),
                        SERVICE_SLATABLE.SERVICE_DEADLINE.value(t.getServiceDeadline()),
                        SERVICE_SLATABLE.CLIENT_ID.value(t.getClientId()),
                        SERVICE_SLATABLE.PRODUCT_ID.value(t.getProductId()),
                        SERVICE_SLATABLE.SLA_TITLE.value(t.getSlaTitle()),
                        SERVICE_SLATABLE.SLA_SPEC.value(t.getSlaSpec()),
                        SERVICE_SLATABLE.SERVICE_REPLY_TIME_LIMIT.value(t.getServiceReplyTimeLimit()),
                        SERVICE_SLATABLE.SERVICE_REVIEW_TIME_LIMIT.value(t.getServiceReviewTimeLimit()),
                        SERVICE_SLATABLE.SERVICE_EFFORT_LIMIT.value(t.getServiceEffortLimit()),
                        SERVICE_SLATABLE.SERVICE_REQUEST_LIMIT.value(t.getServiceRequestLimit()),
                        SERVICE_SLATABLE.SERVICE_TS_ONSITE_LIMIT.value(t.getServiceTsOnsiteLimit()),
                        SERVICE_SLATABLE.SERVICE_SPECIALIST.value(t.getServiceSpecialist()),
                        SERVICE_SLATABLE.SLA_STATUS.value(t.getSlaStatus()),
                        SERVICE_SLATABLE.SLA_CREATED_BY.value(t.getSlaCreatedBy()),
                        SERVICE_SLATABLE.SLA_CREATE_DATE.value(t.getSlaCreateDate()),
                        SERVICE_SLATABLE.SLA_REVIEWED_BY.value(t.getSlaReviewedBy()),
                        SERVICE_SLATABLE.SLA_REVIEW_DATE.value(t.getSlaReviewDate()),
                        SERVICE_SLATABLE.SLA_CLOSED_BY.value(t.getSlaClosedBy()),
                        SERVICE_SLATABLE.SLA_CLOSE_DATE.value(t.getSlaCloseDate()),
                        SERVICE_SLATABLE.SLA_OPEN_COUNT.value(t.getSlaOpenCount()),
                        SERVICE_SLATABLE.DELETED.value(t.getDeleted()),
                        SERVICE_SLATABLE.CILENT_PRODUCT_VISION.value(t.getCilentProductVision()));
                return insert;
            }
        });
    }

    public int edit(ServiceSla serviceSla) {
        if (serviceSla == null || serviceSla.getSlaId() == null) {
            return 0;
        }
        return getDslTemplate().update(serviceSla, new UpdateGenerateCallback<ServiceSla>() {
            public Update generate(ServiceSla t) {
                Update update = update(SERVICE_SLATABLE).set(
                        SERVICE_SLATABLE.SERVICE_LEVEL.value(t.getServiceLevel()),
                        SERVICE_SLATABLE.SERVICE_DEADLINE.value(t.getServiceDeadline()),
                        SERVICE_SLATABLE.CLIENT_ID.value(t.getClientId()),
                        SERVICE_SLATABLE.PRODUCT_ID.value(t.getProductId()),
                        SERVICE_SLATABLE.SLA_TITLE.value(t.getSlaTitle()),
                        SERVICE_SLATABLE.SLA_SPEC.value(t.getSlaSpec()),
                        SERVICE_SLATABLE.SERVICE_REPLY_TIME_LIMIT.value(t.getServiceReplyTimeLimit()),
                        SERVICE_SLATABLE.SERVICE_REVIEW_TIME_LIMIT.value(t.getServiceReviewTimeLimit()),
                        SERVICE_SLATABLE.SERVICE_EFFORT_LIMIT.value(t.getServiceEffortLimit()),
                        SERVICE_SLATABLE.SERVICE_REQUEST_LIMIT.value(t.getServiceRequestLimit()),
                        SERVICE_SLATABLE.SERVICE_TS_ONSITE_LIMIT.value(t.getServiceTsOnsiteLimit()),
                        SERVICE_SLATABLE.SERVICE_SPECIALIST.value(t.getServiceSpecialist()),
                        SERVICE_SLATABLE.SLA_STATUS.value(t.getSlaStatus()),
                        SERVICE_SLATABLE.SLA_CREATED_BY.value(t.getSlaCreatedBy()),
                        SERVICE_SLATABLE.SLA_CREATE_DATE.value(t.getSlaCreateDate()),
                        SERVICE_SLATABLE.SLA_REVIEWED_BY.value(t.getSlaReviewedBy()),
                        SERVICE_SLATABLE.SLA_REVIEW_DATE.value(t.getSlaReviewDate()),
                        SERVICE_SLATABLE.SLA_CLOSED_BY.value(t.getSlaClosedBy()),
                        SERVICE_SLATABLE.SLA_CLOSE_DATE.value(t.getSlaCloseDate()),
                        SERVICE_SLATABLE.SLA_OPEN_COUNT.value(t.getSlaOpenCount()),
                        SERVICE_SLATABLE.DELETED.value(t.getDeleted()),
                        SERVICE_SLATABLE.CILENT_PRODUCT_VISION.value(t.getCilentProductVision())).where(
                        SERVICE_SLATABLE.SLA_ID.eq(t.getSlaId()));
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
                return delete(SERVICE_SLATABLE).where(SERVICE_SLATABLE.SLA_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(SERVICE_SLATABLE).where(SERVICE_SLATABLE.SLA_ID.in(t));
            }
        }, pks);
    }

    /*仿照上面的代码，增加：根据sla表里面的productId关联产品表的productId，查找产品名称*/
    public ServiceSla getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, ServiceSla.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(SERVICE_SLATABLE).join(Join.leftJoin(PRODUCTTABLE, SERVICE_SLATABLE.PRODUCT_ID.eq(PRODUCTTABLE.PRODUCT_ID))).where(SERVICE_SLATABLE.SLA_ID.eq(t));
            }
        });
    }

    public List<ServiceSla> query(ServiceSla serviceSla, final OrderBy... orderBies) {
        if (serviceSla == null) {
            serviceSla = new ServiceSla();
        }
        return getDslTemplate().query(serviceSla, new SelectGenerateCallback<ServiceSla>() {

            @SuppressWarnings("rawtypes")
            public Select generate(ServiceSla t) {
                Select select = selectFrom(SERVICE_SLATABLE).where(
                        and(
                                SERVICE_SLATABLE.SERVICE_LEVEL.eq(t.getServiceLevel()),
                                SERVICE_SLATABLE.SERVICE_DEADLINE.eq(t.getServiceDeadline()),
                                SERVICE_SLATABLE.CLIENT_ID.eq(t.getClientId()),
                                SERVICE_SLATABLE.PRODUCT_ID.eq(t.getProductId()),
                                SERVICE_SLATABLE.SLA_TITLE.eq(t.getSlaTitle()),
                                SERVICE_SLATABLE.SLA_SPEC.eq(t.getSlaSpec()),
                                SERVICE_SLATABLE.SERVICE_REPLY_TIME_LIMIT.eq(t.getServiceReplyTimeLimit()),
                                SERVICE_SLATABLE.SERVICE_REVIEW_TIME_LIMIT.eq(t.getServiceReviewTimeLimit()),
                                SERVICE_SLATABLE.SERVICE_EFFORT_LIMIT.eq(t.getServiceEffortLimit()),
                                SERVICE_SLATABLE.SERVICE_REQUEST_LIMIT.eq(t.getServiceRequestLimit()),
                                SERVICE_SLATABLE.SERVICE_TS_ONSITE_LIMIT.eq(t.getServiceTsOnsiteLimit()),
                                SERVICE_SLATABLE.SERVICE_SPECIALIST.eq(t.getServiceSpecialist()),
                                SERVICE_SLATABLE.SLA_STATUS.eq(t.getSlaStatus()),
                                SERVICE_SLATABLE.SLA_CREATED_BY.eq(t.getSlaCreatedBy()),
                                SERVICE_SLATABLE.SLA_CREATE_DATE.eq(t.getSlaCreateDate()),
                                SERVICE_SLATABLE.SLA_REVIEWED_BY.eq(t.getSlaReviewedBy()),
                                SERVICE_SLATABLE.SLA_REVIEW_DATE.eq(t.getSlaReviewDate()),
                                SERVICE_SLATABLE.SLA_CLOSED_BY.eq(t.getSlaClosedBy()),
                                SERVICE_SLATABLE.SLA_CLOSE_DATE.eq(t.getSlaCloseDate()),
                                SERVICE_SLATABLE.SLA_OPEN_COUNT.eq(t.getSlaOpenCount()),
                                SERVICE_SLATABLE.DELETED.eq(t.getDeleted()),
                                SERVICE_SLATABLE.CILENT_PRODUCT_VISION.eq(t.getCilentProductVision())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public Pager<ServiceSla> queryPager(int start, int limit, ServiceSla serviceSla, final OrderBy... orderBies) {
        if (serviceSla == null) {
            serviceSla = new ServiceSla();
        }
        return getDslTemplate().queryPager(start, limit, serviceSla, false, new SelectGenerateCallback<ServiceSla>() {

            public Select generate(ServiceSla t) {
                Select select = MysqlSelect.selectFrom(SERVICE_SLATABLE).join(Join.leftJoin(SERVICE_CLIENTTABLE, SERVICE_SLATABLE.CLIENT_ID.eq(SERVICE_CLIENTTABLE.CLIENT_ID))).where(
                        and(
                                SERVICE_SLATABLE.SERVICE_LEVEL.eq(t.getServiceLevel()),
                                SERVICE_SLATABLE.SERVICE_DEADLINE.eq(t.getServiceDeadline()),
                                SERVICE_SLATABLE.CLIENT_ID.eq(t.getClientId()),
                                SERVICE_SLATABLE.PRODUCT_ID.eq(t.getProductId()),
                                SERVICE_SLATABLE.SLA_TITLE.eq(t.getSlaTitle()),
                                SERVICE_SLATABLE.SLA_SPEC.eq(t.getSlaSpec()),
                                SERVICE_SLATABLE.SERVICE_REPLY_TIME_LIMIT.eq(t.getServiceReplyTimeLimit()),
                                SERVICE_SLATABLE.SERVICE_REVIEW_TIME_LIMIT.eq(t.getServiceReviewTimeLimit()),
                                SERVICE_SLATABLE.SERVICE_EFFORT_LIMIT.eq(t.getServiceEffortLimit()),
                                SERVICE_SLATABLE.SERVICE_REQUEST_LIMIT.eq(t.getServiceRequestLimit()),
                                SERVICE_SLATABLE.SERVICE_TS_ONSITE_LIMIT.eq(t.getServiceTsOnsiteLimit()),
                                SERVICE_SLATABLE.SERVICE_SPECIALIST.eq(t.getServiceSpecialist()),
                                SERVICE_SLATABLE.SLA_STATUS.eq(t.getSlaStatus()),
                                SERVICE_SLATABLE.SLA_CREATED_BY.eq(t.getSlaCreatedBy()),
                                SERVICE_SLATABLE.SLA_CREATE_DATE.eq(t.getSlaCreateDate()),
                                SERVICE_SLATABLE.SLA_REVIEWED_BY.eq(t.getSlaReviewedBy()),
                                SERVICE_SLATABLE.SLA_REVIEW_DATE.eq(t.getSlaReviewDate()),
                                SERVICE_SLATABLE.SLA_CLOSED_BY.eq(t.getSlaClosedBy()),
                                SERVICE_SLATABLE.SLA_CLOSE_DATE.eq(t.getSlaCloseDate()),
                                SERVICE_SLATABLE.SLA_OPEN_COUNT.eq(t.getSlaOpenCount()),
                                SERVICE_SLATABLE.DELETED.eq(t.getDeleted()),
                                SERVICE_SLATABLE.CILENT_PRODUCT_VISION.eq(t.getCilentProductVision())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<ServiceSla> serviceSlas) {
        if (CollectionUtil.isEmpty(serviceSlas)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, serviceSlas, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(SERVICE_SLATABLE).values(
                        SERVICE_SLATABLE.SERVICE_LEVEL.value(new JdbcNamedParameter("serviceLevel")),
                        SERVICE_SLATABLE.SERVICE_DEADLINE.value(new JdbcNamedParameter("serviceDeadline")),
                        SERVICE_SLATABLE.CLIENT_ID.value(new JdbcNamedParameter("clientId")),
                        SERVICE_SLATABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
                        SERVICE_SLATABLE.SLA_TITLE.value(new JdbcNamedParameter("slaTitle")),
                        SERVICE_SLATABLE.SLA_SPEC.value(new JdbcNamedParameter("slaSpec")),
                        SERVICE_SLATABLE.SERVICE_REPLY_TIME_LIMIT.value(new JdbcNamedParameter("serviceReplyTimeLimit")),
                        SERVICE_SLATABLE.SERVICE_REVIEW_TIME_LIMIT.value(new JdbcNamedParameter("serviceReviewTimeLimit")),
                        SERVICE_SLATABLE.SERVICE_EFFORT_LIMIT.value(new JdbcNamedParameter("serviceEffortLimit")),
                        SERVICE_SLATABLE.SERVICE_REQUEST_LIMIT.value(new JdbcNamedParameter("serviceRequestLimit")),
                        SERVICE_SLATABLE.SERVICE_TS_ONSITE_LIMIT.value(new JdbcNamedParameter("serviceTsOnsiteLimit")),
                        SERVICE_SLATABLE.SERVICE_SPECIALIST.value(new JdbcNamedParameter("serviceSpecialist")),
                        SERVICE_SLATABLE.SLA_STATUS.value(new JdbcNamedParameter("slaStatus")),
                        SERVICE_SLATABLE.SLA_CREATED_BY.value(new JdbcNamedParameter("slaCreatedBy")),
                        SERVICE_SLATABLE.SLA_CREATE_DATE.value(new JdbcNamedParameter("slaCreateDate")),
                        SERVICE_SLATABLE.SLA_REVIEWED_BY.value(new JdbcNamedParameter("slaReviewedBy")),
                        SERVICE_SLATABLE.SLA_REVIEW_DATE.value(new JdbcNamedParameter("slaReviewDate")),
                        SERVICE_SLATABLE.SLA_CLOSED_BY.value(new JdbcNamedParameter("slaClosedBy")),
                        SERVICE_SLATABLE.SLA_CLOSE_DATE.value(new JdbcNamedParameter("slaCloseDate")),
                        SERVICE_SLATABLE.SLA_OPEN_COUNT.value(new JdbcNamedParameter("slaOpenCount")),
                        SERVICE_SLATABLE.DELETED.value(new JdbcNamedParameter("deleted")),
                        SERVICE_SLATABLE.CILENT_PRODUCT_VISION.value(new JdbcNamedParameter("cilentProductVision")));
            }
        });
    }

    public int[] batchInsert(List<ServiceSla> serviceSlas) {
        return batchInsert(true, serviceSlas);
    }

    public int[] batchUpdate(List<ServiceSla> serviceSlas) {
        if (CollectionUtil.isEmpty(serviceSlas)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(serviceSlas, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(SERVICE_SLATABLE).set(
                        SERVICE_SLATABLE.SERVICE_LEVEL.value(new JdbcNamedParameter("serviceLevel")),
                        SERVICE_SLATABLE.SERVICE_DEADLINE.value(new JdbcNamedParameter("serviceDeadline")),
                        SERVICE_SLATABLE.CLIENT_ID.value(new JdbcNamedParameter("clientId")),
                        SERVICE_SLATABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
                        SERVICE_SLATABLE.SLA_TITLE.value(new JdbcNamedParameter("slaTitle")),
                        SERVICE_SLATABLE.SLA_SPEC.value(new JdbcNamedParameter("slaSpec")),
                        SERVICE_SLATABLE.SERVICE_REPLY_TIME_LIMIT.value(new JdbcNamedParameter("serviceReplyTimeLimit")),
                        SERVICE_SLATABLE.SERVICE_REVIEW_TIME_LIMIT.value(new JdbcNamedParameter("serviceReviewTimeLimit")),
                        SERVICE_SLATABLE.SERVICE_EFFORT_LIMIT.value(new JdbcNamedParameter("serviceEffortLimit")),
                        SERVICE_SLATABLE.SERVICE_REQUEST_LIMIT.value(new JdbcNamedParameter("serviceRequestLimit")),
                        SERVICE_SLATABLE.SERVICE_TS_ONSITE_LIMIT.value(new JdbcNamedParameter("serviceTsOnsiteLimit")),
                        SERVICE_SLATABLE.SERVICE_SPECIALIST.value(new JdbcNamedParameter("serviceSpecialist")),
                        SERVICE_SLATABLE.SLA_STATUS.value(new JdbcNamedParameter("slaStatus")),
                        SERVICE_SLATABLE.SLA_CREATED_BY.value(new JdbcNamedParameter("slaCreatedBy")),
                        SERVICE_SLATABLE.SLA_CREATE_DATE.value(new JdbcNamedParameter("slaCreateDate")),
                        SERVICE_SLATABLE.SLA_REVIEWED_BY.value(new JdbcNamedParameter("slaReviewedBy")),
                        SERVICE_SLATABLE.SLA_REVIEW_DATE.value(new JdbcNamedParameter("slaReviewDate")),
                        SERVICE_SLATABLE.SLA_CLOSED_BY.value(new JdbcNamedParameter("slaClosedBy")),
                        SERVICE_SLATABLE.SLA_CLOSE_DATE.value(new JdbcNamedParameter("slaCloseDate")),
                        SERVICE_SLATABLE.SLA_OPEN_COUNT.value(new JdbcNamedParameter("slaOpenCount")),
                        SERVICE_SLATABLE.DELETED.value(new JdbcNamedParameter("deleted")),
                        SERVICE_SLATABLE.CILENT_PRODUCT_VISION.value(new JdbcNamedParameter("cilentProductVision"))).where(
                        SERVICE_SLATABLE.SLA_ID.eq(new JdbcNamedParameter("slaId")));
            }
        });
    }

    public int[] batchDelete(List<ServiceSla> serviceSlas) {
        if (CollectionUtil.isEmpty(serviceSlas)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(serviceSlas, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(SERVICE_SLATABLE).where(and(
                        SERVICE_SLATABLE.SLA_ID.eq(new JdbcNamedParameter("slaId")),
                        SERVICE_SLATABLE.SERVICE_LEVEL.eq(new JdbcNamedParameter("serviceLevel")),
                        SERVICE_SLATABLE.SERVICE_DEADLINE.eq(new JdbcNamedParameter("serviceDeadline")),
                        SERVICE_SLATABLE.CLIENT_ID.eq(new JdbcNamedParameter("clientId")),
                        SERVICE_SLATABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
                        SERVICE_SLATABLE.SLA_TITLE.eq(new JdbcNamedParameter("slaTitle")),
                        SERVICE_SLATABLE.SLA_SPEC.eq(new JdbcNamedParameter("slaSpec")),
                        SERVICE_SLATABLE.SERVICE_REPLY_TIME_LIMIT.eq(new JdbcNamedParameter("serviceReplyTimeLimit")),
                        SERVICE_SLATABLE.SERVICE_REVIEW_TIME_LIMIT.eq(new JdbcNamedParameter("serviceReviewTimeLimit")),
                        SERVICE_SLATABLE.SERVICE_EFFORT_LIMIT.eq(new JdbcNamedParameter("serviceEffortLimit")),
                        SERVICE_SLATABLE.SERVICE_REQUEST_LIMIT.eq(new JdbcNamedParameter("serviceRequestLimit")),
                        SERVICE_SLATABLE.SERVICE_TS_ONSITE_LIMIT.eq(new JdbcNamedParameter("serviceTsOnsiteLimit")),
                        SERVICE_SLATABLE.SERVICE_SPECIALIST.eq(new JdbcNamedParameter("serviceSpecialist")),
                        SERVICE_SLATABLE.SLA_STATUS.eq(new JdbcNamedParameter("slaStatus")),
                        SERVICE_SLATABLE.SLA_CREATED_BY.eq(new JdbcNamedParameter("slaCreatedBy")),
                        SERVICE_SLATABLE.SLA_CREATE_DATE.eq(new JdbcNamedParameter("slaCreateDate")),
                        SERVICE_SLATABLE.SLA_REVIEWED_BY.eq(new JdbcNamedParameter("slaReviewedBy")),
                        SERVICE_SLATABLE.SLA_REVIEW_DATE.eq(new JdbcNamedParameter("slaReviewDate")),
                        SERVICE_SLATABLE.SLA_CLOSED_BY.eq(new JdbcNamedParameter("slaClosedBy")),
                        SERVICE_SLATABLE.SLA_CLOSE_DATE.eq(new JdbcNamedParameter("slaCloseDate")),
                        SERVICE_SLATABLE.SLA_OPEN_COUNT.eq(new JdbcNamedParameter("slaOpenCount")),
                        SERVICE_SLATABLE.DELETED.eq(new JdbcNamedParameter("deleted")),
                        SERVICE_SLATABLE.CILENT_PRODUCT_VISION.eq(new JdbcNamedParameter("cilentProductVision"))));
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

    public List<ServiceSla> getListByClientId(Integer clientId) {
        {
            Select select;
            //Select select = selectFrom(USERTABLE).where(USERTABLE.NAME.eq("xdy"));
            // MysqlSelect select1;
            //select1 = selectFrom(USERTABLE).limit(1, 10);
            select = selectFrom(SERVICE_SLATABLE).join(Join.leftJoin(PRODUCTTABLE, SERVICE_SLATABLE.PRODUCT_ID.eq(PRODUCTTABLE.PRODUCT_ID))).where(and(SERVICE_SLATABLE.CLIENT_ID.eq(clientId),
                    SERVICE_SLATABLE.DELETED.eq(DELETE_NO)));
            return getDslSession().fetchList(select, ServiceSla.class);
        }
    }

    /*在queryPage方法的基础上修改得来，加入参数treeId*/
    public Pager<ServiceSla> queryPagerTree(int start, int limit, ServiceSla serviceSla, final Integer treeId, final Condition conditionl, final OrderBy... orderBies) {
        if (serviceSla == null) {
            serviceSla = new ServiceSla();
        }
        return getDslTemplate().queryPager(start, limit, serviceSla, false, new SelectGenerateCallback<ServiceSla>() {

            public Select generate(ServiceSla t) {
                Select select = MysqlSelect.selectFrom(SERVICE_SLATABLE).join(Join.leftJoin(SERVICE_CLIENTTABLE, SERVICE_SLATABLE.CLIENT_ID.eq(SERVICE_CLIENTTABLE.CLIENT_ID))).where(
                        and(
                                conditionl,
                                SERVICE_SLATABLE.SERVICE_LEVEL.eq(t.getServiceLevel()),
                                SERVICE_SLATABLE.SERVICE_DEADLINE.eq(t.getServiceDeadline()),
                                SERVICE_SLATABLE.CLIENT_ID.eq(t.getClientId()),
                                SERVICE_SLATABLE.PRODUCT_ID.eq(treeId),
                                SERVICE_SLATABLE.SLA_TITLE.eq(t.getSlaTitle()),
                                SERVICE_SLATABLE.SLA_SPEC.eq(t.getSlaSpec()),
                                SERVICE_SLATABLE.SERVICE_REPLY_TIME_LIMIT.eq(t.getServiceReplyTimeLimit()),
                                SERVICE_SLATABLE.SERVICE_REVIEW_TIME_LIMIT.eq(t.getServiceReviewTimeLimit()),
                                SERVICE_SLATABLE.SERVICE_EFFORT_LIMIT.eq(t.getServiceEffortLimit()),
                                SERVICE_SLATABLE.SERVICE_REQUEST_LIMIT.eq(t.getServiceRequestLimit()),
                                SERVICE_SLATABLE.SERVICE_TS_ONSITE_LIMIT.eq(t.getServiceTsOnsiteLimit()),
                                SERVICE_SLATABLE.SERVICE_SPECIALIST.eq(t.getServiceSpecialist()),
                                SERVICE_SLATABLE.SLA_STATUS.eq(t.getSlaStatus()),
                                SERVICE_SLATABLE.SLA_CREATED_BY.eq(t.getSlaCreatedBy()),
                                SERVICE_SLATABLE.SLA_CREATE_DATE.eq(t.getSlaCreateDate()),
                                SERVICE_SLATABLE.SLA_REVIEWED_BY.eq(t.getSlaReviewedBy()),
                                SERVICE_SLATABLE.SLA_REVIEW_DATE.eq(t.getSlaReviewDate()),
                                SERVICE_SLATABLE.SLA_CLOSED_BY.eq(t.getSlaClosedBy()),
                                SERVICE_SLATABLE.SLA_CLOSE_DATE.eq(t.getSlaCloseDate()),
                                SERVICE_SLATABLE.SLA_OPEN_COUNT.eq(t.getSlaOpenCount()),
                                SERVICE_SLATABLE.DELETED.eq(t.getDeleted()),
                                SERVICE_SLATABLE.CILENT_PRODUCT_VISION.eq(t.getCilentProductVision())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public Integer softDelete(Integer id) {
        return getDslTemplate().update(id, new UpdateGenerateCallback<Integer>() {
            public Update generate(Integer id) {
                Update update = update(SERVICE_SLATABLE).set(
                        SERVICE_SLATABLE.DELETED.value(DELETE_YES)).where(
                        SERVICE_SLATABLE.SLA_ID.eq(id));
                return update;
            }
        });

    }

    /* sla必填项校验*/
    public ServiceSla judge(String clientName) {
        Select select;
        try {
            select = selectFrom(SERVICE_SLATABLE).where(SERVICE_SLATABLE.CILENT_PRODUCT_VISION.eq(clientName));
            return getDslSession().fetchOneResult(select, ServiceSla.class);
        } catch (Exception e) {
            return null;
        }
    }

    public int[] softDeleteBatch(List<ServiceSla> list) {
        if (CollectionUtil.isEmpty(list)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(list, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(SERVICE_SLATABLE).set(
                        SERVICE_SLATABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
                        SERVICE_SLATABLE.SLA_ID.eq(new JdbcNamedParameter("slaId")));
            }
        });
    }
}
