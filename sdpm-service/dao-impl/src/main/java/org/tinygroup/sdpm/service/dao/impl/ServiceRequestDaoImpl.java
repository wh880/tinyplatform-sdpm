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
import org.tinygroup.sdpm.service.dao.ServiceRequestDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceRequest;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.Join;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.service.dao.constant.ServiceClientTable.SERVICE_CLIENTTABLE;
import static org.tinygroup.sdpm.service.dao.constant.ServiceRequestTable.SERVICE_REQUESTTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class ServiceRequestDaoImpl extends TinyDslDaoSupport implements ServiceRequestDao {

    public ServiceRequest add(ServiceRequest serviceRequest) {
        return getDslTemplate().insertAndReturnKey(serviceRequest, new InsertGenerateCallback<ServiceRequest>() {
            public Insert generate(ServiceRequest t) {
                Insert insert = insertInto(SERVICE_REQUESTTABLE).values(
                        SERVICE_REQUESTTABLE.CLIENT_REQUEST_ID.value(t.getClientRequestId()),
                        SERVICE_REQUESTTABLE.PRODUCT_ID.value(t.getProductId()),
                        SERVICE_REQUESTTABLE.MODULE_ID.value(t.getModuleId()),
                        SERVICE_REQUESTTABLE.REQUEST_NO.value(t.getRequestNo()),
                        SERVICE_REQUESTTABLE.REQUEST_TYPE.value(t.getRequestType()),
                        SERVICE_REQUESTTABLE.REQUEST_PRE.value(t.getRequestPre()),
                        SERVICE_REQUESTTABLE.REQUEST_TITLE.value(t.getRequestTitle()),
                        SERVICE_REQUESTTABLE.REQUEST_KEYWORDS.value(t.getRequestKeywords()),
                        SERVICE_REQUESTTABLE.REQUEST_SPEC.value(t.getRequestSpec()),
                        SERVICE_REQUESTTABLE.REQUEST_IS_ABNORMAL.value(t.getRequestIsAbnormal()),
                        SERVICE_REQUESTTABLE.CLIENT_ID.value(t.getClientId()),
                        SERVICE_REQUESTTABLE.REQUESTER.value(t.getRequester()),
                        SERVICE_REQUESTTABLE.REQUEST_SUBMIT_BY.value(t.getRequestSubmitBy()),
                        SERVICE_REQUESTTABLE.REQUEST_SUBMIT_DATE.value(t.getRequestSubmitDate()),
                        SERVICE_REQUESTTABLE.REQUEST_REPLY_DATE.value(t.getRequestReplyDate()),
                        SERVICE_REQUESTTABLE.REQUEST_COMMITMENT_DATE.value(t.getRequestCommitmentDate()),
                        SERVICE_REQUESTTABLE.REQUEST_REVIEWER.value(t.getRequestReviewer()),
                        SERVICE_REQUESTTABLE.REQUEST_REVIEW_DATE.value(t.getRequestReviewDate()),
                        SERVICE_REQUESTTABLE.REQUEST_LAST_EDITED_BY.value(t.getRequestLastEditedBy()),
                        SERVICE_REQUESTTABLE.REQUEST_LAST_EDIT_DATE.value(t.getRequestLastEditDate()),
                        SERVICE_REQUESTTABLE.REQUEST_RELEASE_DATE.value(t.getRequestReleaseDate()),
                        SERVICE_REQUESTTABLE.REQUEST_CLOSED_BY.value(t.getRequestClosedBy()),
                        SERVICE_REQUESTTABLE.REQUEST_CLOSE_DATE.value(t.getRequestCloseDate()),
                        SERVICE_REQUESTTABLE.REQUEST_OPEN_COUNT.value(t.getRequestOpenCount()),
                        SERVICE_REQUESTTABLE.REQUEST_STATUS.value(t.getRequestStatus()),
                        SERVICE_REQUESTTABLE.REQUEST_TRANS_TO.value(t.getRequestTransTo()),
                        SERVICE_REQUESTTABLE.REQUEST_TRANS_ID.value(t.getRequestTransId()),
                        SERVICE_REQUESTTABLE.DELETED.value(t.getDeleted()),
                        SERVICE_REQUESTTABLE.REPLY_SPEC.value(t.getReplySpec()),
                        SERVICE_REQUESTTABLE.REPLIER.value(t.getReplier()),
                        SERVICE_REQUESTTABLE.REPLY_DATE.value(t.getReplyDate()));
                return insert;
            }
        });
    }

    public int edit(ServiceRequest serviceRequest) {
        if (serviceRequest == null || serviceRequest.getClientRequestId() == null) {
            return 0;
        }
        return getDslTemplate().update(serviceRequest, new UpdateGenerateCallback<ServiceRequest>() {
            public Update generate(ServiceRequest t) {
                Update update = update(SERVICE_REQUESTTABLE).set(
                        SERVICE_REQUESTTABLE.PRODUCT_ID.value(t.getProductId()),
                        SERVICE_REQUESTTABLE.MODULE_ID.value(t.getModuleId()),
                        SERVICE_REQUESTTABLE.REQUEST_NO.value(t.getRequestNo()),
                        SERVICE_REQUESTTABLE.REQUEST_TYPE.value(t.getRequestType()),
                        SERVICE_REQUESTTABLE.REQUEST_PRE.value(t.getRequestPre()),
                        SERVICE_REQUESTTABLE.REQUEST_TITLE.value(t.getRequestTitle()),
                        SERVICE_REQUESTTABLE.REQUEST_KEYWORDS.value(t.getRequestKeywords()),
                        SERVICE_REQUESTTABLE.REQUEST_SPEC.value(t.getRequestSpec()),
                        SERVICE_REQUESTTABLE.REQUEST_IS_ABNORMAL.value(t.getRequestIsAbnormal()),
                        SERVICE_REQUESTTABLE.CLIENT_ID.value(t.getClientId()),
                        SERVICE_REQUESTTABLE.REQUESTER.value(t.getRequester()),
                        SERVICE_REQUESTTABLE.REQUEST_SUBMIT_BY.value(t.getRequestSubmitBy()),
                        SERVICE_REQUESTTABLE.REQUEST_SUBMIT_DATE.value(t.getRequestSubmitDate()),
                        SERVICE_REQUESTTABLE.REQUEST_REPLY_DATE.value(t.getRequestReplyDate()),
                        SERVICE_REQUESTTABLE.REQUEST_COMMITMENT_DATE.value(t.getRequestCommitmentDate()),
                        SERVICE_REQUESTTABLE.REQUEST_REVIEWER.value(t.getRequestReviewer()),
                        SERVICE_REQUESTTABLE.REQUEST_REVIEW_DATE.value(t.getRequestReviewDate()),
                        SERVICE_REQUESTTABLE.REQUEST_LAST_EDITED_BY.value(t.getRequestLastEditedBy()),
                        SERVICE_REQUESTTABLE.REQUEST_LAST_EDIT_DATE.value(t.getRequestLastEditDate()),
                        SERVICE_REQUESTTABLE.REQUEST_RELEASE_DATE.value(t.getRequestReleaseDate()),
                        SERVICE_REQUESTTABLE.REQUEST_CLOSED_BY.value(t.getRequestClosedBy()),
                        SERVICE_REQUESTTABLE.REQUEST_CLOSE_DATE.value(t.getRequestCloseDate()),
                        SERVICE_REQUESTTABLE.REQUEST_OPEN_COUNT.value(t.getRequestOpenCount()),
                        SERVICE_REQUESTTABLE.REQUEST_STATUS.value(t.getRequestStatus()),
                        SERVICE_REQUESTTABLE.REQUEST_TRANS_TO.value(t.getRequestTransTo()),
                        SERVICE_REQUESTTABLE.REQUEST_TRANS_ID.value(t.getRequestTransId()),
                        SERVICE_REQUESTTABLE.DELETED.value(t.getDeleted()),
                        SERVICE_REQUESTTABLE.REPLY_SPEC.value(t.getReplySpec()),
                        SERVICE_REQUESTTABLE.REPLIER.value(t.getReplier()),
                        SERVICE_REQUESTTABLE.REPLY_DATE.value(t.getReplyDate())).where(
                        SERVICE_REQUESTTABLE.CLIENT_REQUEST_ID.eq(t.getClientRequestId()));
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
                return delete(SERVICE_REQUESTTABLE).where(SERVICE_REQUESTTABLE.CLIENT_REQUEST_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(SERVICE_REQUESTTABLE).where(SERVICE_REQUESTTABLE.CLIENT_REQUEST_ID.in(t));
            }
        }, pks);
    }

    public ServiceRequest getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, ServiceRequest.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(SERVICE_REQUESTTABLE).where(SERVICE_REQUESTTABLE.CLIENT_REQUEST_ID.eq(t));
            }
        });
    }

    public List<ServiceRequest> query(ServiceRequest serviceRequest, final OrderBy... orderBies) {
        if (serviceRequest == null) {
            serviceRequest = new ServiceRequest();
        }
        return getDslTemplate().query(serviceRequest, new SelectGenerateCallback<ServiceRequest>() {

            @SuppressWarnings("rawtypes")
            public Select generate(ServiceRequest t) {
                Select select = selectFrom(SERVICE_REQUESTTABLE).where(
                        and(
                                SERVICE_REQUESTTABLE.PRODUCT_ID.eq(t.getProductId()),
                                SERVICE_REQUESTTABLE.MODULE_ID.eq(t.getModuleId()),
                                SERVICE_REQUESTTABLE.REQUEST_NO.eq(t.getRequestNo()),
                                SERVICE_REQUESTTABLE.REQUEST_TYPE.eq(t.getRequestType()),
                                SERVICE_REQUESTTABLE.REQUEST_PRE.eq(t.getRequestPre()),
                                SERVICE_REQUESTTABLE.REQUEST_TITLE.eq(t.getRequestTitle()),
                                SERVICE_REQUESTTABLE.REQUEST_KEYWORDS.eq(t.getRequestKeywords()),
                                SERVICE_REQUESTTABLE.REQUEST_SPEC.eq(t.getRequestSpec()),
                                SERVICE_REQUESTTABLE.REQUEST_IS_ABNORMAL.eq(t.getRequestIsAbnormal()),
                                SERVICE_REQUESTTABLE.CLIENT_ID.eq(t.getClientId()),
                                SERVICE_REQUESTTABLE.REQUESTER.eq(t.getRequester()),
                                SERVICE_REQUESTTABLE.REQUEST_SUBMIT_BY.eq(t.getRequestSubmitBy()),
                                SERVICE_REQUESTTABLE.REQUEST_SUBMIT_DATE.eq(t.getRequestSubmitDate()),
                                SERVICE_REQUESTTABLE.REQUEST_REPLY_DATE.eq(t.getRequestReplyDate()),
                                SERVICE_REQUESTTABLE.REQUEST_COMMITMENT_DATE.eq(t.getRequestCommitmentDate()),
                                SERVICE_REQUESTTABLE.REQUEST_REVIEWER.eq(t.getRequestReviewer()),
                                SERVICE_REQUESTTABLE.REQUEST_REVIEW_DATE.eq(t.getRequestReviewDate()),
                                SERVICE_REQUESTTABLE.REQUEST_LAST_EDITED_BY.eq(t.getRequestLastEditedBy()),
                                SERVICE_REQUESTTABLE.REQUEST_LAST_EDIT_DATE.eq(t.getRequestLastEditDate()),
                                SERVICE_REQUESTTABLE.REQUEST_RELEASE_DATE.eq(t.getRequestReleaseDate()),
                                SERVICE_REQUESTTABLE.REQUEST_CLOSED_BY.eq(t.getRequestClosedBy()),
                                SERVICE_REQUESTTABLE.REQUEST_CLOSE_DATE.eq(t.getRequestCloseDate()),
                                SERVICE_REQUESTTABLE.REQUEST_OPEN_COUNT.eq(t.getRequestOpenCount()),
                                SERVICE_REQUESTTABLE.REQUEST_STATUS.eq(t.getRequestStatus()),
                                SERVICE_REQUESTTABLE.REQUEST_TRANS_TO.eq(t.getRequestTransTo()),
                                SERVICE_REQUESTTABLE.REQUEST_TRANS_ID.eq(t.getRequestTransId()),
                                SERVICE_REQUESTTABLE.DELETED.eq(t.getDeleted()),
                                SERVICE_REQUESTTABLE.REPLY_SPEC.eq(t.getReplySpec()),
                                SERVICE_REQUESTTABLE.REPLIER.eq(t.getReplier()),
                                SERVICE_REQUESTTABLE.REPLY_DATE.eq(t.getReplyDate())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public Pager<ServiceRequest> queryPager(int start, int limit, ServiceRequest serviceRequest, final OrderBy... orderBies) {
        if (serviceRequest == null) {
            serviceRequest = new ServiceRequest();
        }
        return getDslTemplate().queryPager(start, limit, serviceRequest, false, new SelectGenerateCallback<ServiceRequest>() {

            public Select generate(ServiceRequest t) {
                Select select = MysqlSelect.selectFrom(SERVICE_REQUESTTABLE).where(
                        and(
                                SERVICE_REQUESTTABLE.PRODUCT_ID.eq(t.getProductId()),
                                SERVICE_REQUESTTABLE.MODULE_ID.eq(t.getModuleId()),
                                SERVICE_REQUESTTABLE.REQUEST_NO.eq(t.getRequestNo()),
                                SERVICE_REQUESTTABLE.REQUEST_TYPE.eq(t.getRequestType()),
                                SERVICE_REQUESTTABLE.REQUEST_PRE.eq(t.getRequestPre()),
                                SERVICE_REQUESTTABLE.REQUEST_TITLE.eq(t.getRequestTitle()),
                                SERVICE_REQUESTTABLE.REQUEST_KEYWORDS.eq(t.getRequestKeywords()),
                                SERVICE_REQUESTTABLE.REQUEST_SPEC.eq(t.getRequestSpec()),
                                SERVICE_REQUESTTABLE.REQUEST_IS_ABNORMAL.eq(t.getRequestIsAbnormal()),
                                SERVICE_REQUESTTABLE.CLIENT_ID.eq(t.getClientId()),
                                SERVICE_REQUESTTABLE.REQUESTER.eq(t.getRequester()),
                                SERVICE_REQUESTTABLE.REQUEST_SUBMIT_BY.eq(t.getRequestSubmitBy()),
                                SERVICE_REQUESTTABLE.REQUEST_SUBMIT_DATE.eq(t.getRequestSubmitDate()),
                                SERVICE_REQUESTTABLE.REQUEST_REPLY_DATE.eq(t.getRequestReplyDate()),
                                SERVICE_REQUESTTABLE.REQUEST_COMMITMENT_DATE.eq(t.getRequestCommitmentDate()),
                                SERVICE_REQUESTTABLE.REQUEST_REVIEWER.eq(t.getRequestReviewer()),
                                SERVICE_REQUESTTABLE.REQUEST_REVIEW_DATE.eq(t.getRequestReviewDate()),
                                SERVICE_REQUESTTABLE.REQUEST_LAST_EDITED_BY.eq(t.getRequestLastEditedBy()),
                                SERVICE_REQUESTTABLE.REQUEST_LAST_EDIT_DATE.eq(t.getRequestLastEditDate()),
                                SERVICE_REQUESTTABLE.REQUEST_RELEASE_DATE.eq(t.getRequestReleaseDate()),
                                SERVICE_REQUESTTABLE.REQUEST_CLOSED_BY.eq(t.getRequestClosedBy()),
                                SERVICE_REQUESTTABLE.REQUEST_CLOSE_DATE.eq(t.getRequestCloseDate()),
                                SERVICE_REQUESTTABLE.REQUEST_OPEN_COUNT.eq(t.getRequestOpenCount()),
                                SERVICE_REQUESTTABLE.REQUEST_STATUS.eq(t.getRequestStatus()),
                                SERVICE_REQUESTTABLE.REQUEST_TRANS_TO.eq(t.getRequestTransTo()),
                                SERVICE_REQUESTTABLE.REQUEST_TRANS_ID.eq(t.getRequestTransId()),
                                SERVICE_REQUESTTABLE.DELETED.eq(t.getDeleted()),
                                SERVICE_REQUESTTABLE.REPLY_SPEC.eq(t.getReplySpec()),
                                SERVICE_REQUESTTABLE.REPLIER.eq(t.getReplier()),
                                SERVICE_REQUESTTABLE.REPLY_DATE.eq(t.getReplyDate())));
                return addOrderByElements(select, orderBies);
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<ServiceRequest> serviceRequests) {
        if (CollectionUtil.isEmpty(serviceRequests)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, serviceRequests, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(SERVICE_REQUESTTABLE).values(
                        SERVICE_REQUESTTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
                        SERVICE_REQUESTTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
                        SERVICE_REQUESTTABLE.REQUEST_NO.value(new JdbcNamedParameter("requestNo")),
                        SERVICE_REQUESTTABLE.REQUEST_TYPE.value(new JdbcNamedParameter("requestType")),
                        SERVICE_REQUESTTABLE.REQUEST_PRE.value(new JdbcNamedParameter("requestPre")),
                        SERVICE_REQUESTTABLE.REQUEST_TITLE.value(new JdbcNamedParameter("requestTitle")),
                        SERVICE_REQUESTTABLE.REQUEST_KEYWORDS.value(new JdbcNamedParameter("requestKeywords")),
                        SERVICE_REQUESTTABLE.REQUEST_SPEC.value(new JdbcNamedParameter("requestSpec")),
                        SERVICE_REQUESTTABLE.REQUEST_IS_ABNORMAL.value(new JdbcNamedParameter("requestIsAbnormal")),
                        SERVICE_REQUESTTABLE.CLIENT_ID.value(new JdbcNamedParameter("clientId")),
                        SERVICE_REQUESTTABLE.REQUESTER.value(new JdbcNamedParameter("requester")),
                        SERVICE_REQUESTTABLE.REQUEST_SUBMIT_BY.value(new JdbcNamedParameter("requestSubmitBy")),
                        SERVICE_REQUESTTABLE.REQUEST_SUBMIT_DATE.value(new JdbcNamedParameter("requestSubmitDate")),
                        SERVICE_REQUESTTABLE.REQUEST_REPLY_DATE.value(new JdbcNamedParameter("requestReplyDate")),
                        SERVICE_REQUESTTABLE.REQUEST_COMMITMENT_DATE.value(new JdbcNamedParameter("requestCommitmentDate")),
                        SERVICE_REQUESTTABLE.REQUEST_REVIEWER.value(new JdbcNamedParameter("requestReviewer")),
                        SERVICE_REQUESTTABLE.REQUEST_REVIEW_DATE.value(new JdbcNamedParameter("requestReviewDate")),
                        SERVICE_REQUESTTABLE.REQUEST_LAST_EDITED_BY.value(new JdbcNamedParameter("requestLastEditedBy")),
                        SERVICE_REQUESTTABLE.REQUEST_LAST_EDIT_DATE.value(new JdbcNamedParameter("requestLastEditDate")),
                        SERVICE_REQUESTTABLE.REQUEST_RELEASE_DATE.value(new JdbcNamedParameter("requestReleaseDate")),
                        SERVICE_REQUESTTABLE.REQUEST_CLOSED_BY.value(new JdbcNamedParameter("requestClosedBy")),
                        SERVICE_REQUESTTABLE.REQUEST_CLOSE_DATE.value(new JdbcNamedParameter("requestCloseDate")),
                        SERVICE_REQUESTTABLE.REQUEST_OPEN_COUNT.value(new JdbcNamedParameter("requestOpenCount")),
                        SERVICE_REQUESTTABLE.REQUEST_STATUS.value(new JdbcNamedParameter("requestStatus")),
                        SERVICE_REQUESTTABLE.REQUEST_TRANS_TO.value(new JdbcNamedParameter("requestTransTo")),
                        SERVICE_REQUESTTABLE.REQUEST_TRANS_ID.value(new JdbcNamedParameter("requestTransId")),
                        SERVICE_REQUESTTABLE.DELETED.value(new JdbcNamedParameter("deleted")),
                        SERVICE_REQUESTTABLE.REPLY_SPEC.value(new JdbcNamedParameter("replySpec")),
                        SERVICE_REQUESTTABLE.REPLIER.value(new JdbcNamedParameter("replier")),
                        SERVICE_REQUESTTABLE.REPLY_DATE.value(new JdbcNamedParameter("replyDate")));
            }
        });
    }

    public int[] batchInsert(List<ServiceRequest> serviceRequests) {
        return batchInsert(true, serviceRequests);
    }

    public int[] batchUpdate(List<ServiceRequest> serviceRequests) {
        if (CollectionUtil.isEmpty(serviceRequests)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(serviceRequests, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(SERVICE_REQUESTTABLE).set(
                        SERVICE_REQUESTTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
                        SERVICE_REQUESTTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
                        SERVICE_REQUESTTABLE.REQUEST_NO.value(new JdbcNamedParameter("requestNo")),
                        SERVICE_REQUESTTABLE.REQUEST_TYPE.value(new JdbcNamedParameter("requestType")),
                        SERVICE_REQUESTTABLE.REQUEST_PRE.value(new JdbcNamedParameter("requestPre")),
                        SERVICE_REQUESTTABLE.REQUEST_TITLE.value(new JdbcNamedParameter("requestTitle")),
                        SERVICE_REQUESTTABLE.REQUEST_KEYWORDS.value(new JdbcNamedParameter("requestKeywords")),
                        SERVICE_REQUESTTABLE.REQUEST_SPEC.value(new JdbcNamedParameter("requestSpec")),
                        SERVICE_REQUESTTABLE.REQUEST_IS_ABNORMAL.value(new JdbcNamedParameter("requestIsAbnormal")),
                        SERVICE_REQUESTTABLE.CLIENT_ID.value(new JdbcNamedParameter("clientId")),
                        SERVICE_REQUESTTABLE.REQUESTER.value(new JdbcNamedParameter("requester")),
                        SERVICE_REQUESTTABLE.REQUEST_SUBMIT_BY.value(new JdbcNamedParameter("requestSubmitBy")),
                        SERVICE_REQUESTTABLE.REQUEST_SUBMIT_DATE.value(new JdbcNamedParameter("requestSubmitDate")),
                        SERVICE_REQUESTTABLE.REQUEST_REPLY_DATE.value(new JdbcNamedParameter("requestReplyDate")),
                        SERVICE_REQUESTTABLE.REQUEST_COMMITMENT_DATE.value(new JdbcNamedParameter("requestCommitmentDate")),
                        SERVICE_REQUESTTABLE.REQUEST_REVIEWER.value(new JdbcNamedParameter("requestReviewer")),
                        SERVICE_REQUESTTABLE.REQUEST_REVIEW_DATE.value(new JdbcNamedParameter("requestReviewDate")),
                        SERVICE_REQUESTTABLE.REQUEST_LAST_EDITED_BY.value(new JdbcNamedParameter("requestLastEditedBy")),
                        SERVICE_REQUESTTABLE.REQUEST_LAST_EDIT_DATE.value(new JdbcNamedParameter("requestLastEditDate")),
                        SERVICE_REQUESTTABLE.REQUEST_RELEASE_DATE.value(new JdbcNamedParameter("requestReleaseDate")),
                        SERVICE_REQUESTTABLE.REQUEST_CLOSED_BY.value(new JdbcNamedParameter("requestClosedBy")),
                        SERVICE_REQUESTTABLE.REQUEST_CLOSE_DATE.value(new JdbcNamedParameter("requestCloseDate")),
                        SERVICE_REQUESTTABLE.REQUEST_OPEN_COUNT.value(new JdbcNamedParameter("requestOpenCount")),
                        SERVICE_REQUESTTABLE.REQUEST_STATUS.value(new JdbcNamedParameter("requestStatus")),
                        SERVICE_REQUESTTABLE.REQUEST_TRANS_TO.value(new JdbcNamedParameter("requestTransTo")),
                        SERVICE_REQUESTTABLE.REQUEST_TRANS_ID.value(new JdbcNamedParameter("requestTransId")),
                        SERVICE_REQUESTTABLE.DELETED.value(new JdbcNamedParameter("deleted")),
                        SERVICE_REQUESTTABLE.REPLY_SPEC.value(new JdbcNamedParameter("replySpec")),
                        SERVICE_REQUESTTABLE.REPLIER.value(new JdbcNamedParameter("replier")),
                        SERVICE_REQUESTTABLE.REPLY_DATE.value(new JdbcNamedParameter("replyDate"))).where(
                        SERVICE_REQUESTTABLE.CLIENT_REQUEST_ID.eq(new JdbcNamedParameter("clientRequestId")));
            }
        });
    }

    public int[] batchDelete(List<ServiceRequest> serviceRequests) {
        if (CollectionUtil.isEmpty(serviceRequests)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(serviceRequests, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(SERVICE_REQUESTTABLE).where(and(
                        SERVICE_REQUESTTABLE.CLIENT_REQUEST_ID.eq(new JdbcNamedParameter("clientRequestId")),
                        SERVICE_REQUESTTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
                        SERVICE_REQUESTTABLE.MODULE_ID.eq(new JdbcNamedParameter("moduleId")),
                        SERVICE_REQUESTTABLE.REQUEST_NO.eq(new JdbcNamedParameter("requestNo")),
                        SERVICE_REQUESTTABLE.REQUEST_TYPE.eq(new JdbcNamedParameter("requestType")),
                        SERVICE_REQUESTTABLE.REQUEST_PRE.eq(new JdbcNamedParameter("requestPre")),
                        SERVICE_REQUESTTABLE.REQUEST_TITLE.eq(new JdbcNamedParameter("requestTitle")),
                        SERVICE_REQUESTTABLE.REQUEST_KEYWORDS.eq(new JdbcNamedParameter("requestKeywords")),
                        SERVICE_REQUESTTABLE.REQUEST_SPEC.eq(new JdbcNamedParameter("requestSpec")),
                        SERVICE_REQUESTTABLE.REQUEST_IS_ABNORMAL.eq(new JdbcNamedParameter("requestIsAbnormal")),
                        SERVICE_REQUESTTABLE.CLIENT_ID.eq(new JdbcNamedParameter("clientId")),
                        SERVICE_REQUESTTABLE.REQUESTER.eq(new JdbcNamedParameter("requester")),
                        SERVICE_REQUESTTABLE.REQUEST_SUBMIT_BY.eq(new JdbcNamedParameter("requestSubmitBy")),
                        SERVICE_REQUESTTABLE.REQUEST_SUBMIT_DATE.eq(new JdbcNamedParameter("requestSubmitDate")),
                        SERVICE_REQUESTTABLE.REQUEST_REPLY_DATE.eq(new JdbcNamedParameter("requestReplyDate")),
                        SERVICE_REQUESTTABLE.REQUEST_COMMITMENT_DATE.eq(new JdbcNamedParameter("requestCommitmentDate")),
                        SERVICE_REQUESTTABLE.REQUEST_REVIEWER.eq(new JdbcNamedParameter("requestReviewer")),
                        SERVICE_REQUESTTABLE.REQUEST_REVIEW_DATE.eq(new JdbcNamedParameter("requestReviewDate")),
                        SERVICE_REQUESTTABLE.REQUEST_LAST_EDITED_BY.eq(new JdbcNamedParameter("requestLastEditedBy")),
                        SERVICE_REQUESTTABLE.REQUEST_LAST_EDIT_DATE.eq(new JdbcNamedParameter("requestLastEditDate")),
                        SERVICE_REQUESTTABLE.REQUEST_RELEASE_DATE.eq(new JdbcNamedParameter("requestReleaseDate")),
                        SERVICE_REQUESTTABLE.REQUEST_CLOSED_BY.eq(new JdbcNamedParameter("requestClosedBy")),
                        SERVICE_REQUESTTABLE.REQUEST_CLOSE_DATE.eq(new JdbcNamedParameter("requestCloseDate")),
                        SERVICE_REQUESTTABLE.REQUEST_OPEN_COUNT.eq(new JdbcNamedParameter("requestOpenCount")),
                        SERVICE_REQUESTTABLE.REQUEST_STATUS.eq(new JdbcNamedParameter("requestStatus")),
                        SERVICE_REQUESTTABLE.REQUEST_TRANS_TO.eq(new JdbcNamedParameter("requestTransTo")),
                        SERVICE_REQUESTTABLE.REQUEST_TRANS_ID.eq(new JdbcNamedParameter("requestTransId")),
                        SERVICE_REQUESTTABLE.DELETED.eq(new JdbcNamedParameter("deleted")),
                        SERVICE_REQUESTTABLE.REPLY_SPEC.eq(new JdbcNamedParameter("replySpec")),
                        SERVICE_REQUESTTABLE.REPLIER.eq(new JdbcNamedParameter("replier")),
                        SERVICE_REQUESTTABLE.REPLY_DATE.eq(new JdbcNamedParameter("replyDate"))));
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

    public Integer close(ServiceRequest serviceRequest) {
        if (serviceRequest == null || serviceRequest.getClientRequestId() == null) {
            return 0;
        }
        return getDslTemplate().update(serviceRequest, new UpdateGenerateCallback<ServiceRequest>() {
            public Update generate(ServiceRequest t) {
                Update update = update(SERVICE_REQUESTTABLE).set(
                        SERVICE_REQUESTTABLE.REQUEST_STATUS.value(t.CLOSE),
                        SERVICE_REQUESTTABLE.REQUEST_CLOSED_BY.value(t.getRequestClosedBy()),
                        SERVICE_REQUESTTABLE.REQUEST_CLOSE_DATE.value(t.getRequestCloseDate())).where(
                        SERVICE_REQUESTTABLE.CLIENT_REQUEST_ID.eq(t.getClientRequestId()));
                return update;
            }
        });
    }

    public Integer softDelete(Integer id) {
        return getDslTemplate().update(id, new UpdateGenerateCallback<Integer>() {
            public Update generate(Integer id) {
                Update update = update(SERVICE_REQUESTTABLE).set(
                        SERVICE_REQUESTTABLE.DELETED.value(ServiceRequest.DELETE_YES)).where(
                        SERVICE_REQUESTTABLE.CLIENT_REQUEST_ID.eq(id));
                return update;
            }
        });

    }

    public Integer saveReply(ServiceRequest serviceRequest) {
        if (serviceRequest == null || serviceRequest.getClientRequestId() == null) {
            return 0;
        }
        return getDslTemplate().update(serviceRequest, new UpdateGenerateCallback<ServiceRequest>() {
            public Update generate(ServiceRequest t) {
                Update update = update(SERVICE_REQUESTTABLE).set(
                        SERVICE_REQUESTTABLE.REQUEST_STATUS.value(t.FINISHED),
                        SERVICE_REQUESTTABLE.REPLY_SPEC.value(t.getReplySpec()),
                        SERVICE_REQUESTTABLE.REPLY_DATE.value(t.getReplyDate()),
                        SERVICE_REQUESTTABLE.REPLIER.value(t.getReplier())).where(
                        SERVICE_REQUESTTABLE.CLIENT_REQUEST_ID.eq(t.getClientRequestId()));
                return update;
            }
        });
    }

    public Pager<ServiceRequest> queryPagerBy(int start, int limit, ServiceRequest serviceRequest, final Integer statues, final OrderBy... orderArgs) {
        if (serviceRequest == null) {
            serviceRequest = new ServiceRequest();
        }
        return getDslTemplate().queryPager(start, limit, serviceRequest, false, new SelectGenerateCallback<ServiceRequest>() {

            public Select generate(ServiceRequest t) {
                Select select = MysqlSelect.selectFrom(SERVICE_REQUESTTABLE).join(
                        Join.leftJoin(SERVICE_CLIENTTABLE, SERVICE_REQUESTTABLE.CLIENT_ID.eq(SERVICE_CLIENTTABLE.CLIENT_ID))).where(
                        and(
                                SERVICE_REQUESTTABLE.PRODUCT_ID.eq(t.getProductId()),
                                SERVICE_REQUESTTABLE.MODULE_ID.eq(t.getModuleId()),
                                SERVICE_REQUESTTABLE.REQUEST_NO.eq(t.getRequestNo()),
                                SERVICE_REQUESTTABLE.REQUEST_TYPE.eq(t.getRequestType()),
                                SERVICE_REQUESTTABLE.REQUEST_PRE.eq(t.getRequestPre()),
                                SERVICE_REQUESTTABLE.REQUEST_TITLE.eq(t.getRequestTitle()),
                                SERVICE_REQUESTTABLE.REQUEST_KEYWORDS.eq(t.getRequestKeywords()),
                                SERVICE_REQUESTTABLE.REQUEST_SPEC.eq(t.getRequestSpec()),
                                SERVICE_REQUESTTABLE.REQUEST_IS_ABNORMAL.eq(t.getRequestIsAbnormal()),
                                SERVICE_REQUESTTABLE.CLIENT_ID.eq(t.getClientId()),
                                SERVICE_REQUESTTABLE.REQUESTER.eq(t.getRequester()),
                                SERVICE_REQUESTTABLE.REQUEST_SUBMIT_BY.eq(t.getRequestSubmitBy()),
                                SERVICE_REQUESTTABLE.REQUEST_SUBMIT_DATE.eq(t.getRequestSubmitDate()),
                                SERVICE_REQUESTTABLE.REQUEST_REPLY_DATE.eq(t.getRequestReplyDate()),
                                SERVICE_REQUESTTABLE.REQUEST_COMMITMENT_DATE.eq(t.getRequestCommitmentDate()),
                                SERVICE_REQUESTTABLE.REQUEST_REVIEWER.eq(t.getRequestReviewer()),
                                SERVICE_REQUESTTABLE.REQUEST_REVIEW_DATE.eq(t.getRequestReviewDate()),
                                SERVICE_REQUESTTABLE.REQUEST_LAST_EDITED_BY.eq(t.getRequestLastEditedBy()),
                                SERVICE_REQUESTTABLE.REQUEST_LAST_EDIT_DATE.eq(t.getRequestLastEditDate()),
                                SERVICE_REQUESTTABLE.REQUEST_RELEASE_DATE.eq(t.getRequestReleaseDate()),
                                SERVICE_REQUESTTABLE.REQUEST_CLOSED_BY.eq(t.getRequestClosedBy()),
                                SERVICE_REQUESTTABLE.REQUEST_CLOSE_DATE.eq(t.getRequestCloseDate()),
                                SERVICE_REQUESTTABLE.REQUEST_OPEN_COUNT.eq(t.getRequestOpenCount()),
                                SERVICE_REQUESTTABLE.REQUEST_STATUS.eq(statues),
                                SERVICE_REQUESTTABLE.REQUEST_TRANS_TO.eq(t.getRequestTransTo()),
                                SERVICE_REQUESTTABLE.REQUEST_TRANS_ID.eq(t.getRequestTransId()),
                                SERVICE_REQUESTTABLE.DELETED.eq(t.getDeleted()),
                                SERVICE_REQUESTTABLE.REPLY_SPEC.eq(t.getReplySpec()),
                                SERVICE_REQUESTTABLE.REPLIER.eq(t.getReplier()),
                                SERVICE_REQUESTTABLE.REPLY_DATE.eq(t.getReplyDate())));
                return addOrderByElements(select, orderArgs);
            }
        });
    }

    public Integer changeStatus(Integer id) {
        return getDslTemplate().update(id, new UpdateGenerateCallback<Integer>() {
            public Update generate(Integer id) {
                Update update = update(SERVICE_REQUESTTABLE).set(
                        SERVICE_REQUESTTABLE.REQUEST_STATUS.value(ServiceRequest.RETURNVISIT)).where(
                        SERVICE_REQUESTTABLE.CLIENT_REQUEST_ID.eq(id));
                return update;
            }
        });

    }

    public int[] batchUpdateReview(List<ServiceRequest> list) {
        if (CollectionUtil.isEmpty(list)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(list, new NoParamUpdateGenerateCallback() {
            public Update generate() {
                return update(SERVICE_REQUESTTABLE).set(
                        SERVICE_REQUESTTABLE.REQUEST_REVIEWER.value(new JdbcNamedParameter("requestReviewer"))).where(
                        SERVICE_REQUESTTABLE.CLIENT_REQUEST_ID.eq(new JdbcNamedParameter("clientRequestId")));
            }
        });

    }

    public int[] softDeleteBatch(List<ServiceRequest> list) {
        if (CollectionUtil.isEmpty(list)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(list, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(SERVICE_REQUESTTABLE).set(
                        SERVICE_REQUESTTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
                        SERVICE_REQUESTTABLE.CLIENT_REQUEST_ID.eq(new JdbcNamedParameter("clientRequestId")));
            }
        });
    }

    public int[] batchUpdateReply(List<ServiceRequest> list) {
        if (CollectionUtil.isEmpty(list)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(list, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(SERVICE_REQUESTTABLE).set(
                        SERVICE_REQUESTTABLE.REPLIER.value(new JdbcNamedParameter("replier"))).where(
                        SERVICE_REQUESTTABLE.CLIENT_REQUEST_ID.eq(new JdbcNamedParameter("clientRequestId")));
            }
        });
    }

}
