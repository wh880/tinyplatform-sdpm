/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p/>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.gnu.org/licenses/gpl.html
 * <p/>
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
import org.tinygroup.sdpm.service.dao.ServiceReviewDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceReview;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.service.dao.constant.ServiceReviewTable.SERVICE_REVIEWTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class ServiceReviewDaoImpl extends TinyDslDaoSupport implements ServiceReviewDao {

    public ServiceReview add(ServiceReview serviceReview) {
        return getDslTemplate().insertAndReturnKey(serviceReview, new InsertGenerateCallback<ServiceReview>() {
            public Insert generate(ServiceReview t) {
                Insert insert = insertInto(SERVICE_REVIEWTABLE).values(
                        SERVICE_REVIEWTABLE.REVIEW_ID.value(t.getReviewId()),
                        SERVICE_REVIEWTABLE.CLIENT_REQUEST_ID.value(t.getClientRequestId()),
                        SERVICE_REVIEWTABLE.REVIEW_SPEC.value(t.getReviewSpec()),
                        SERVICE_REVIEWTABLE.REQUESTER.value(t.getRequester()),
                        SERVICE_REVIEWTABLE.REVIEWER.value(t.getReviewer()),
                        SERVICE_REVIEWTABLE.REVIEW_DATE.value(t.getReviewDate()),
                        SERVICE_REVIEWTABLE.REVIEW_RESULT.value(t.getReviewResult()),
                        SERVICE_REVIEWTABLE.REVIEW_SCORE.value(t.getReviewScore()),
                        SERVICE_REVIEWTABLE.REVIEW_TYPE.value(t.getReviewType()));
                return insert;
            }
        });
    }

    public int edit(ServiceReview serviceReview) {
        if (serviceReview == null || serviceReview.getReviewId() == null) {
            return 0;
        }

        return getDslTemplate().update(serviceReview, new UpdateGenerateCallback<ServiceReview>() {
            public Update generate(ServiceReview t) {
                Update update = update(SERVICE_REVIEWTABLE).set(
                        SERVICE_REVIEWTABLE.CLIENT_REQUEST_ID.value(t.getClientRequestId()),
                        SERVICE_REVIEWTABLE.REVIEW_SPEC.value(t.getReviewSpec()),
                        SERVICE_REVIEWTABLE.REQUESTER.value(t.getRequester()),
                        SERVICE_REVIEWTABLE.REVIEWER.value(t.getReviewer()),
                        SERVICE_REVIEWTABLE.REVIEW_DATE.value(t.getReviewDate()),
                        SERVICE_REVIEWTABLE.REVIEW_RESULT.value(t.getReviewResult()),
                        SERVICE_REVIEWTABLE.REVIEW_SCORE.value(t.getReviewScore()),
                        SERVICE_REVIEWTABLE.REVIEW_TYPE.value(t.getReviewType())).where(
                        SERVICE_REVIEWTABLE.REVIEW_ID.eq(t.getReviewId()));
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
                return delete(SERVICE_REVIEWTABLE).where(SERVICE_REVIEWTABLE.REVIEW_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(SERVICE_REVIEWTABLE).where(SERVICE_REVIEWTABLE.REVIEW_ID.in(t));
            }
        }, pks);
    }

    public ServiceReview getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, ServiceReview.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(SERVICE_REVIEWTABLE).where(SERVICE_REVIEWTABLE.REVIEW_ID.eq(t));
            }
        });
    }

    public List<ServiceReview> query(ServiceReview serviceReview, final OrderBy... orderBies) {
        if (serviceReview == null) {
            serviceReview = new ServiceReview();
        }
        return getDslTemplate().query(serviceReview, new SelectGenerateCallback<ServiceReview>() {

            @SuppressWarnings("rawtypes")
            public Select generate(ServiceReview t) {
                Select select = selectFrom(SERVICE_REVIEWTABLE).where(
                        and(
                                SERVICE_REVIEWTABLE.CLIENT_REQUEST_ID.eq(t.getClientRequestId()),
                                SERVICE_REVIEWTABLE.REVIEW_SPEC.eq(t.getReviewSpec()),
                                SERVICE_REVIEWTABLE.REQUESTER.eq(t.getRequester()),
                                SERVICE_REVIEWTABLE.REVIEWER.eq(t.getReviewer()),
                                SERVICE_REVIEWTABLE.REVIEW_DATE.eq(t.getReviewDate()),
                                SERVICE_REVIEWTABLE.REVIEW_RESULT.eq(t.getReviewResult()),
                                SERVICE_REVIEWTABLE.REVIEW_SCORE.eq(t.getReviewScore()),
                                SERVICE_REVIEWTABLE.REVIEW_TYPE.eq(t.getReviewType())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public Pager<ServiceReview> queryPager(int start, int limit, ServiceReview serviceReview, final OrderBy... orderBies) {
        if (serviceReview == null) {
            serviceReview = new ServiceReview();
        }
        return getDslTemplate().queryPager(start, limit, serviceReview, false, new SelectGenerateCallback<ServiceReview>() {

            public Select generate(ServiceReview t) {
                Select select = MysqlSelect.selectFrom(SERVICE_REVIEWTABLE).where(
                        and(
                                SERVICE_REVIEWTABLE.CLIENT_REQUEST_ID.eq(t.getClientRequestId()),
                                SERVICE_REVIEWTABLE.REVIEW_SPEC.eq(t.getReviewSpec()),
                                SERVICE_REVIEWTABLE.REQUESTER.eq(t.getRequester()),
                                SERVICE_REVIEWTABLE.REVIEWER.eq(t.getReviewer()),
                                SERVICE_REVIEWTABLE.REVIEW_DATE.eq(t.getReviewDate()),
                                SERVICE_REVIEWTABLE.REVIEW_RESULT.eq(t.getReviewResult()),
                                SERVICE_REVIEWTABLE.REVIEW_SCORE.eq(t.getReviewScore()),
                                SERVICE_REVIEWTABLE.REVIEW_TYPE.eq(t.getReviewType())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<ServiceReview> serviceReviews) {
        if (CollectionUtil.isEmpty(serviceReviews)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, serviceReviews, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(SERVICE_REVIEWTABLE).values(
                        SERVICE_REVIEWTABLE.CLIENT_REQUEST_ID.value(new JdbcNamedParameter("clientRequestId")),
                        SERVICE_REVIEWTABLE.REVIEW_SPEC.value(new JdbcNamedParameter("reviewSpec")),
                        SERVICE_REVIEWTABLE.REQUESTER.value(new JdbcNamedParameter("requester")),
                        SERVICE_REVIEWTABLE.REVIEWER.value(new JdbcNamedParameter("reviewer")),
                        SERVICE_REVIEWTABLE.REVIEW_DATE.value(new JdbcNamedParameter("reviewDate")),
                        SERVICE_REVIEWTABLE.REVIEW_RESULT.value(new JdbcNamedParameter("reviewResult")),
                        SERVICE_REVIEWTABLE.REVIEW_SCORE.value(new JdbcNamedParameter("reviewScore")),
                        SERVICE_REVIEWTABLE.REVIEW_TYPE.value(new JdbcNamedParameter("reviewType")));
            }
        });
    }

    public int[] batchInsert(List<ServiceReview> serviceReviews) {
        return batchInsert(true, serviceReviews);
    }

    public int[] batchUpdate(List<ServiceReview> serviceReviews) {
        if (CollectionUtil.isEmpty(serviceReviews)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(serviceReviews, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(SERVICE_REVIEWTABLE).set(
                        SERVICE_REVIEWTABLE.CLIENT_REQUEST_ID.value(new JdbcNamedParameter("clientRequestId")),
                        SERVICE_REVIEWTABLE.REVIEW_SPEC.value(new JdbcNamedParameter("reviewSpec")),
                        SERVICE_REVIEWTABLE.REQUESTER.value(new JdbcNamedParameter("requester")),
                        SERVICE_REVIEWTABLE.REVIEWER.value(new JdbcNamedParameter("reviewer")),
                        SERVICE_REVIEWTABLE.REVIEW_DATE.value(new JdbcNamedParameter("reviewDate")),
                        SERVICE_REVIEWTABLE.REVIEW_RESULT.value(new JdbcNamedParameter("reviewResult")),
                        SERVICE_REVIEWTABLE.REVIEW_SCORE.value(new JdbcNamedParameter("reviewScore")),
                        SERVICE_REVIEWTABLE.REVIEW_TYPE.value(new JdbcNamedParameter("reviewType"))).where(
                        SERVICE_REVIEWTABLE.REVIEW_ID.eq(new JdbcNamedParameter("reviewId")));
            }
        });
    }

    public int[] batchDelete(List<ServiceReview> serviceReviews) {
        if (CollectionUtil.isEmpty(serviceReviews)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(serviceReviews, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(SERVICE_REVIEWTABLE).where(and(
                        SERVICE_REVIEWTABLE.REVIEW_ID.eq(new JdbcNamedParameter("reviewId")),
                        SERVICE_REVIEWTABLE.CLIENT_REQUEST_ID.eq(new JdbcNamedParameter("clientRequestId")),
                        SERVICE_REVIEWTABLE.REVIEW_SPEC.eq(new JdbcNamedParameter("reviewSpec")),
                        SERVICE_REVIEWTABLE.REQUESTER.eq(new JdbcNamedParameter("requester")),
                        SERVICE_REVIEWTABLE.REVIEWER.eq(new JdbcNamedParameter("reviewer")),
                        SERVICE_REVIEWTABLE.REVIEW_DATE.eq(new JdbcNamedParameter("reviewDate")),
                        SERVICE_REVIEWTABLE.REVIEW_RESULT.eq(new JdbcNamedParameter("reviewResult")),
                        SERVICE_REVIEWTABLE.REVIEW_SCORE.eq(new JdbcNamedParameter("reviewScore")),
                        SERVICE_REVIEWTABLE.REVIEW_TYPE.eq(new JdbcNamedParameter("reviewType"))));
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

    public ServiceReview findByRequestId(Integer id) {
        return getDslTemplate().getByKey(id, ServiceReview.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(SERVICE_REVIEWTABLE).where(SERVICE_REVIEWTABLE.CLIENT_REQUEST_ID.eq(t));
            }
        });
    }

    public int[] batchUpdateReview(List<ServiceReview> list, final String name) {
        if (CollectionUtil.isEmpty(list)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(list, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(SERVICE_REVIEWTABLE).set(
                        SERVICE_REVIEWTABLE.REVIEWER.value(new JdbcNamedParameter(name))).where(
                        SERVICE_REVIEWTABLE.CLIENT_REQUEST_ID.eq(new JdbcNamedParameter("ReviewId")));
            }
        });
    }
}
