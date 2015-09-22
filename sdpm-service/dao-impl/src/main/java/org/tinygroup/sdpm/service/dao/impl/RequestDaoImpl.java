/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.tinygroup.sdpm.service.dao.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.service.dao.constant.RequestTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
	import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.sdpm.service.dao.pojo.Request;
import org.tinygroup.sdpm.service.dao.RequestDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;
@LogClass("reqest")
@Repository
public class RequestDaoImpl extends TinyDslDaoSupport implements RequestDao {
	@LogMethod("add")
	public Request add(Request request) {
		return getDslTemplate().insertAndReturnKey(request, new InsertGenerateCallback<Request>() {
			public Insert generate(Request t) {
				Insert insert = insertInto(REQUESTTABLE).values(
					REQUESTTABLE.CLIENT_REQUEST_ID.value(t.getClientRequestId()),
					REQUESTTABLE.PRODUCT_ID.value(t.getProductId()),
					REQUESTTABLE.MODULE_ID.value(t.getModuleId()),
					REQUESTTABLE.REQUEST_NO.value(t.getRequestNo()),
					REQUESTTABLE.REQUEST_TYPE.value(t.getRequestType()),
					REQUESTTABLE.REQUEST_PRE.value(t.getRequestPre()),
					REQUESTTABLE.REQUEST_TITLE.value(t.getRequestTitle()),
					REQUESTTABLE.REQUEST_KEYWORDS.value(t.getRequestKeywords()),
					REQUESTTABLE.REQUEST_SPEC.value(t.getRequestSpec()),
					REQUESTTABLE.REQUEST_IS_ABNORMAL.value(t.getRequestIsAbnormal()),
					REQUESTTABLE.CLIENT_ID.value(t.getClientId()),
					REQUESTTABLE.REQUESTER.value(t.getRequester()),
					REQUESTTABLE.REQUEST_SUBMIT_BY.value(t.getRequestSubmitBy()),
					REQUESTTABLE.REQUEST_SUBMIT_DATE.value(t.getRequestSubmitDate()),
					REQUESTTABLE.REQUEST_REPLY_DATE.value(t.getRequestReplyDate()),
					REQUESTTABLE.REQUEST_COMMITMENT_DATE.value(t.getRequestCommitmentDate()),
					REQUESTTABLE.REQUEST_REVIEWER.value(t.getRequestReviewer()),
					REQUESTTABLE.REQUEST_REVIEW_DATE.value(t.getRequestReviewDate()),
					REQUESTTABLE.REQUEST_LAST_EDITED_BY.value(t.getRequestLastEditedBy()),
					REQUESTTABLE.REQUEST_LAST_EDIT_DATE.value(t.getRequestLastEditDate()),
					REQUESTTABLE.REQUEST_RELEASE_DATE.value(t.getRequestReleaseDate()),
					REQUESTTABLE.REQUEST_CLOSED_BY.value(t.getRequestClosedBy()),
					REQUESTTABLE.REQUEST_CLOSE_DATE.value(t.getRequestCloseDate()),
					REQUESTTABLE.REQUEST_OPEN_COUNT.value(t.getRequestOpenCount()),
					REQUESTTABLE.REQUEST_STATUS.value(t.getRequestStatus()),
					REQUESTTABLE.REQUEST_TRANS_TO.value(t.getRequestTransTo()),
					REQUESTTABLE.REQUEST_TRANS_ID.value(t.getRequestTransId()),
					REQUESTTABLE.DELETED.value(t.getDeleted()),
					REQUESTTABLE.REPLY_SPEC.value(t.getReplySpec()),
					REQUESTTABLE.REPLIER.value(t.getReplier()),
					REQUESTTABLE.REPLY_DATE.value(t.getReplyDate()));
				return insert;
			}
		});
	}
	@LogMethod("edit")
	public int edit(Request request) {
		if(request == null || request.getClientRequestId() == null){
			return 0;
		}
		return getDslTemplate().update(request, new UpdateGenerateCallback<Request>() {
			public Update generate(Request t) {
				Update update = update(REQUESTTABLE).set(
					REQUESTTABLE.PRODUCT_ID.value(t.getProductId()),
					REQUESTTABLE.MODULE_ID.value(t.getModuleId()),
					REQUESTTABLE.REQUEST_NO.value(t.getRequestNo()),
					REQUESTTABLE.REQUEST_TYPE.value(t.getRequestType()),
					REQUESTTABLE.REQUEST_PRE.value(t.getRequestPre()),
					REQUESTTABLE.REQUEST_TITLE.value(t.getRequestTitle()),
					REQUESTTABLE.REQUEST_KEYWORDS.value(t.getRequestKeywords()),
					REQUESTTABLE.REQUEST_SPEC.value(t.getRequestSpec()),
					REQUESTTABLE.REQUEST_IS_ABNORMAL.value(t.getRequestIsAbnormal()),
					REQUESTTABLE.CLIENT_ID.value(t.getClientId()),
					REQUESTTABLE.REQUESTER.value(t.getRequester()),
					REQUESTTABLE.REQUEST_SUBMIT_BY.value(t.getRequestSubmitBy()),
					REQUESTTABLE.REQUEST_SUBMIT_DATE.value(t.getRequestSubmitDate()),
					REQUESTTABLE.REQUEST_REPLY_DATE.value(t.getRequestReplyDate()),
					REQUESTTABLE.REQUEST_COMMITMENT_DATE.value(t.getRequestCommitmentDate()),
					REQUESTTABLE.REQUEST_REVIEWER.value(t.getRequestReviewer()),
					REQUESTTABLE.REQUEST_REVIEW_DATE.value(t.getRequestReviewDate()),
					REQUESTTABLE.REQUEST_LAST_EDITED_BY.value(t.getRequestLastEditedBy()),
					REQUESTTABLE.REQUEST_LAST_EDIT_DATE.value(t.getRequestLastEditDate()),
					REQUESTTABLE.REQUEST_RELEASE_DATE.value(t.getRequestReleaseDate()),
					REQUESTTABLE.REQUEST_CLOSED_BY.value(t.getRequestClosedBy()),
					REQUESTTABLE.REQUEST_CLOSE_DATE.value(t.getRequestCloseDate()),
					REQUESTTABLE.REQUEST_OPEN_COUNT.value(t.getRequestOpenCount()),
					REQUESTTABLE.REQUEST_STATUS.value(t.getRequestStatus()),
					REQUESTTABLE.REQUEST_TRANS_TO.value(t.getRequestTransTo()),
					REQUESTTABLE.REQUEST_TRANS_ID.value(t.getRequestTransId()),
					REQUESTTABLE.DELETED.value(t.getDeleted()),
					REQUESTTABLE.REPLY_SPEC.value(t.getReplySpec()),
					REQUESTTABLE.REPLIER.value(t.getReplier()),
					REQUESTTABLE.REPLY_DATE.value(t.getReplyDate())).where(
					REQUESTTABLE.CLIENT_REQUEST_ID.eq(t.getClientRequestId()));
				return update;
			}
		});
	}
	@LogMethod("deleteByKey")
	public int deleteByKey(Integer pk){
		if(pk == null){
			return 0;
		}
		return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(REQUESTTABLE).where(REQUESTTABLE.CLIENT_REQUEST_ID.eq(pk));
			}
		});
	}
	@LogMethod("deleteByKeys")
	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(REQUESTTABLE).where(REQUESTTABLE.CLIENT_REQUEST_ID.in(t));
		}
		},pks);
	}
	@LogMethod("getByKey")
	public Request getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Request.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(REQUESTTABLE).where(REQUESTTABLE.CLIENT_REQUEST_ID.eq(t));
			}
		});
	}
	@LogMethod("query")
	public List<Request> query(Request request ,final OrderBy... orderBies) {
		if(request==null){
			request=new Request();
		}
		return getDslTemplate().query(request, new SelectGenerateCallback<Request>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Request t) {
				Select select = selectFrom(REQUESTTABLE).where(
				and(
					REQUESTTABLE.PRODUCT_ID.eq(t.getProductId()),
					REQUESTTABLE.MODULE_ID.eq(t.getModuleId()),
					REQUESTTABLE.REQUEST_NO.eq(t.getRequestNo()),
					REQUESTTABLE.REQUEST_TYPE.eq(t.getRequestType()),
					REQUESTTABLE.REQUEST_PRE.eq(t.getRequestPre()),
					REQUESTTABLE.REQUEST_TITLE.eq(t.getRequestTitle()),
					REQUESTTABLE.REQUEST_KEYWORDS.eq(t.getRequestKeywords()),
					REQUESTTABLE.REQUEST_SPEC.eq(t.getRequestSpec()),
					REQUESTTABLE.REQUEST_IS_ABNORMAL.eq(t.getRequestIsAbnormal()),
					REQUESTTABLE.CLIENT_ID.eq(t.getClientId()),
					REQUESTTABLE.REQUESTER.eq(t.getRequester()),
					REQUESTTABLE.REQUEST_SUBMIT_BY.eq(t.getRequestSubmitBy()),
					REQUESTTABLE.REQUEST_SUBMIT_DATE.eq(t.getRequestSubmitDate()),
					REQUESTTABLE.REQUEST_REPLY_DATE.eq(t.getRequestReplyDate()),
					REQUESTTABLE.REQUEST_COMMITMENT_DATE.eq(t.getRequestCommitmentDate()),
					REQUESTTABLE.REQUEST_REVIEWER.eq(t.getRequestReviewer()),
					REQUESTTABLE.REQUEST_REVIEW_DATE.eq(t.getRequestReviewDate()),
					REQUESTTABLE.REQUEST_LAST_EDITED_BY.eq(t.getRequestLastEditedBy()),
					REQUESTTABLE.REQUEST_LAST_EDIT_DATE.eq(t.getRequestLastEditDate()),
					REQUESTTABLE.REQUEST_RELEASE_DATE.eq(t.getRequestReleaseDate()),
					REQUESTTABLE.REQUEST_CLOSED_BY.eq(t.getRequestClosedBy()),
					REQUESTTABLE.REQUEST_CLOSE_DATE.eq(t.getRequestCloseDate()),
					REQUESTTABLE.REQUEST_OPEN_COUNT.eq(t.getRequestOpenCount()),
					REQUESTTABLE.REQUEST_STATUS.eq(t.getRequestStatus()),
					REQUESTTABLE.REQUEST_TRANS_TO.eq(t.getRequestTransTo()),
					REQUESTTABLE.REQUEST_TRANS_ID.eq(t.getRequestTransId()),
					REQUESTTABLE.DELETED.eq(t.getDeleted()),
					REQUESTTABLE.REPLY_SPEC.eq(t.getReplySpec()),
					REQUESTTABLE.REPLIER.eq(t.getReplier()),
					REQUESTTABLE.REPLY_DATE.eq(t.getReplyDate())));
		return addOrderByElements(select, orderBies);
			}
		});
	}
	@LogMethod("queryPager")
	public Pager<Request> queryPager(int start,int limit ,Request request ,final OrderBy... orderBies) {
		if(request==null){
			request=new Request();
		}
		return getDslTemplate().queryPager(start, limit, request, false, new SelectGenerateCallback<Request>() {

			public Select generate(Request t) {
				Select select = MysqlSelect.selectFrom(REQUESTTABLE).where(
				and(
					REQUESTTABLE.PRODUCT_ID.eq(t.getProductId()),
					REQUESTTABLE.MODULE_ID.eq(t.getModuleId()),
					REQUESTTABLE.REQUEST_NO.eq(t.getRequestNo()),
					REQUESTTABLE.REQUEST_TYPE.eq(t.getRequestType()),
					REQUESTTABLE.REQUEST_PRE.eq(t.getRequestPre()),
					REQUESTTABLE.REQUEST_TITLE.eq(t.getRequestTitle()),
					REQUESTTABLE.REQUEST_KEYWORDS.eq(t.getRequestKeywords()),
					REQUESTTABLE.REQUEST_SPEC.eq(t.getRequestSpec()),
					REQUESTTABLE.REQUEST_IS_ABNORMAL.eq(t.getRequestIsAbnormal()),
					REQUESTTABLE.CLIENT_ID.eq(t.getClientId()),
					REQUESTTABLE.REQUESTER.eq(t.getRequester()),
					REQUESTTABLE.REQUEST_SUBMIT_BY.eq(t.getRequestSubmitBy()),
					REQUESTTABLE.REQUEST_SUBMIT_DATE.eq(t.getRequestSubmitDate()),
					REQUESTTABLE.REQUEST_REPLY_DATE.eq(t.getRequestReplyDate()),
					REQUESTTABLE.REQUEST_COMMITMENT_DATE.eq(t.getRequestCommitmentDate()),
					REQUESTTABLE.REQUEST_REVIEWER.eq(t.getRequestReviewer()),
					REQUESTTABLE.REQUEST_REVIEW_DATE.eq(t.getRequestReviewDate()),
					REQUESTTABLE.REQUEST_LAST_EDITED_BY.eq(t.getRequestLastEditedBy()),
					REQUESTTABLE.REQUEST_LAST_EDIT_DATE.eq(t.getRequestLastEditDate()),
					REQUESTTABLE.REQUEST_RELEASE_DATE.eq(t.getRequestReleaseDate()),
					REQUESTTABLE.REQUEST_CLOSED_BY.eq(t.getRequestClosedBy()),
					REQUESTTABLE.REQUEST_CLOSE_DATE.eq(t.getRequestCloseDate()),
					REQUESTTABLE.REQUEST_OPEN_COUNT.eq(t.getRequestOpenCount()),
					REQUESTTABLE.REQUEST_STATUS.eq(t.getRequestStatus()),
					REQUESTTABLE.REQUEST_TRANS_TO.eq(t.getRequestTransTo()),
					REQUESTTABLE.REQUEST_TRANS_ID.eq(t.getRequestTransId()),
					REQUESTTABLE.DELETED.eq(t.getDeleted()),
					REQUESTTABLE.REPLY_SPEC.eq(t.getReplySpec()),
					REQUESTTABLE.REPLIER.eq(t.getReplier()),
					REQUESTTABLE.REPLY_DATE.eq(t.getReplyDate())));
		return addOrderByElements(select, orderBies);
			}
		});
	}
	@LogMethod("batchInsert")
	public int[] batchInsert(boolean autoGeneratedKeys ,List<Request> requests) {
		if (CollectionUtil.isEmpty(requests)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, requests, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(REQUESTTABLE).values(
					REQUESTTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					REQUESTTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
					REQUESTTABLE.REQUEST_NO.value(new JdbcNamedParameter("requestNo")),
					REQUESTTABLE.REQUEST_TYPE.value(new JdbcNamedParameter("requestType")),
					REQUESTTABLE.REQUEST_PRE.value(new JdbcNamedParameter("requestPre")),
					REQUESTTABLE.REQUEST_TITLE.value(new JdbcNamedParameter("requestTitle")),
					REQUESTTABLE.REQUEST_KEYWORDS.value(new JdbcNamedParameter("requestKeywords")),
					REQUESTTABLE.REQUEST_SPEC.value(new JdbcNamedParameter("requestSpec")),
					REQUESTTABLE.REQUEST_IS_ABNORMAL.value(new JdbcNamedParameter("requestIsAbnormal")),
					REQUESTTABLE.CLIENT_ID.value(new JdbcNamedParameter("clientId")),
					REQUESTTABLE.REQUESTER.value(new JdbcNamedParameter("requester")),
					REQUESTTABLE.REQUEST_SUBMIT_BY.value(new JdbcNamedParameter("requestSubmitBy")),
					REQUESTTABLE.REQUEST_SUBMIT_DATE.value(new JdbcNamedParameter("requestSubmitDate")),
					REQUESTTABLE.REQUEST_REPLY_DATE.value(new JdbcNamedParameter("requestReplyDate")),
					REQUESTTABLE.REQUEST_COMMITMENT_DATE.value(new JdbcNamedParameter("requestCommitmentDate")),
					REQUESTTABLE.REQUEST_REVIEWER.value(new JdbcNamedParameter("requestReviewer")),
					REQUESTTABLE.REQUEST_REVIEW_DATE.value(new JdbcNamedParameter("requestReviewDate")),
					REQUESTTABLE.REQUEST_LAST_EDITED_BY.value(new JdbcNamedParameter("requestLastEditedBy")),
					REQUESTTABLE.REQUEST_LAST_EDIT_DATE.value(new JdbcNamedParameter("requestLastEditDate")),
					REQUESTTABLE.REQUEST_RELEASE_DATE.value(new JdbcNamedParameter("requestReleaseDate")),
					REQUESTTABLE.REQUEST_CLOSED_BY.value(new JdbcNamedParameter("requestClosedBy")),
					REQUESTTABLE.REQUEST_CLOSE_DATE.value(new JdbcNamedParameter("requestCloseDate")),
					REQUESTTABLE.REQUEST_OPEN_COUNT.value(new JdbcNamedParameter("requestOpenCount")),
					REQUESTTABLE.REQUEST_STATUS.value(new JdbcNamedParameter("requestStatus")),
					REQUESTTABLE.REQUEST_TRANS_TO.value(new JdbcNamedParameter("requestTransTo")),
					REQUESTTABLE.REQUEST_TRANS_ID.value(new JdbcNamedParameter("requestTransId")),
					REQUESTTABLE.DELETED.value(new JdbcNamedParameter("deleted")),
					REQUESTTABLE.REPLY_SPEC.value(new JdbcNamedParameter("replySpec")),
					REQUESTTABLE.REPLIER.value(new JdbcNamedParameter("replier")),
					REQUESTTABLE.REPLY_DATE.value(new JdbcNamedParameter("replyDate")));
			}
		});
	}
	@LogMethod("batchInsert")
	public int[] batchInsert(List<Request> requests){
			return batchInsert(true ,requests);
	}
	@LogMethod("batchUpdate")
	public int[] batchUpdate(List<Request> requests) {
		if (CollectionUtil.isEmpty(requests)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(requests, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(REQUESTTABLE).set(
					REQUESTTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					REQUESTTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
					REQUESTTABLE.REQUEST_NO.value(new JdbcNamedParameter("requestNo")),
					REQUESTTABLE.REQUEST_TYPE.value(new JdbcNamedParameter("requestType")),
					REQUESTTABLE.REQUEST_PRE.value(new JdbcNamedParameter("requestPre")),
					REQUESTTABLE.REQUEST_TITLE.value(new JdbcNamedParameter("requestTitle")),
					REQUESTTABLE.REQUEST_KEYWORDS.value(new JdbcNamedParameter("requestKeywords")),
					REQUESTTABLE.REQUEST_SPEC.value(new JdbcNamedParameter("requestSpec")),
					REQUESTTABLE.REQUEST_IS_ABNORMAL.value(new JdbcNamedParameter("requestIsAbnormal")),
					REQUESTTABLE.CLIENT_ID.value(new JdbcNamedParameter("clientId")),
					REQUESTTABLE.REQUESTER.value(new JdbcNamedParameter("requester")),
					REQUESTTABLE.REQUEST_SUBMIT_BY.value(new JdbcNamedParameter("requestSubmitBy")),
					REQUESTTABLE.REQUEST_SUBMIT_DATE.value(new JdbcNamedParameter("requestSubmitDate")),
					REQUESTTABLE.REQUEST_REPLY_DATE.value(new JdbcNamedParameter("requestReplyDate")),
					REQUESTTABLE.REQUEST_COMMITMENT_DATE.value(new JdbcNamedParameter("requestCommitmentDate")),
					REQUESTTABLE.REQUEST_REVIEWER.value(new JdbcNamedParameter("requestReviewer")),
					REQUESTTABLE.REQUEST_REVIEW_DATE.value(new JdbcNamedParameter("requestReviewDate")),
					REQUESTTABLE.REQUEST_LAST_EDITED_BY.value(new JdbcNamedParameter("requestLastEditedBy")),
					REQUESTTABLE.REQUEST_LAST_EDIT_DATE.value(new JdbcNamedParameter("requestLastEditDate")),
					REQUESTTABLE.REQUEST_RELEASE_DATE.value(new JdbcNamedParameter("requestReleaseDate")),
					REQUESTTABLE.REQUEST_CLOSED_BY.value(new JdbcNamedParameter("requestClosedBy")),
					REQUESTTABLE.REQUEST_CLOSE_DATE.value(new JdbcNamedParameter("requestCloseDate")),
					REQUESTTABLE.REQUEST_OPEN_COUNT.value(new JdbcNamedParameter("requestOpenCount")),
					REQUESTTABLE.REQUEST_STATUS.value(new JdbcNamedParameter("requestStatus")),
					REQUESTTABLE.REQUEST_TRANS_TO.value(new JdbcNamedParameter("requestTransTo")),
					REQUESTTABLE.REQUEST_TRANS_ID.value(new JdbcNamedParameter("requestTransId")),
					REQUESTTABLE.DELETED.value(new JdbcNamedParameter("deleted")),
					REQUESTTABLE.REPLY_SPEC.value(new JdbcNamedParameter("replySpec")),
					REQUESTTABLE.REPLIER.value(new JdbcNamedParameter("replier")),
					REQUESTTABLE.REPLY_DATE.value(new JdbcNamedParameter("replyDate"))).where(
				REQUESTTABLE.CLIENT_REQUEST_ID.eq(new JdbcNamedParameter("clientRequestId")));
			}
		});
	}
	@LogMethod("batchDelete")
	public int[] batchDelete(List<Request> requests) {
		if (CollectionUtil.isEmpty(requests)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(requests, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(REQUESTTABLE).where(and(
				REQUESTTABLE.CLIENT_REQUEST_ID.eq(new JdbcNamedParameter("clientRequestId")),
				REQUESTTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
				REQUESTTABLE.MODULE_ID.eq(new JdbcNamedParameter("moduleId")),
				REQUESTTABLE.REQUEST_NO.eq(new JdbcNamedParameter("requestNo")),
				REQUESTTABLE.REQUEST_TYPE.eq(new JdbcNamedParameter("requestType")),
				REQUESTTABLE.REQUEST_PRE.eq(new JdbcNamedParameter("requestPre")),
				REQUESTTABLE.REQUEST_TITLE.eq(new JdbcNamedParameter("requestTitle")),
				REQUESTTABLE.REQUEST_KEYWORDS.eq(new JdbcNamedParameter("requestKeywords")),
				REQUESTTABLE.REQUEST_SPEC.eq(new JdbcNamedParameter("requestSpec")),
				REQUESTTABLE.REQUEST_IS_ABNORMAL.eq(new JdbcNamedParameter("requestIsAbnormal")),
				REQUESTTABLE.CLIENT_ID.eq(new JdbcNamedParameter("clientId")),
				REQUESTTABLE.REQUESTER.eq(new JdbcNamedParameter("requester")),
				REQUESTTABLE.REQUEST_SUBMIT_BY.eq(new JdbcNamedParameter("requestSubmitBy")),
				REQUESTTABLE.REQUEST_SUBMIT_DATE.eq(new JdbcNamedParameter("requestSubmitDate")),
				REQUESTTABLE.REQUEST_REPLY_DATE.eq(new JdbcNamedParameter("requestReplyDate")),
				REQUESTTABLE.REQUEST_COMMITMENT_DATE.eq(new JdbcNamedParameter("requestCommitmentDate")),
				REQUESTTABLE.REQUEST_REVIEWER.eq(new JdbcNamedParameter("requestReviewer")),
				REQUESTTABLE.REQUEST_REVIEW_DATE.eq(new JdbcNamedParameter("requestReviewDate")),
				REQUESTTABLE.REQUEST_LAST_EDITED_BY.eq(new JdbcNamedParameter("requestLastEditedBy")),
				REQUESTTABLE.REQUEST_LAST_EDIT_DATE.eq(new JdbcNamedParameter("requestLastEditDate")),
				REQUESTTABLE.REQUEST_RELEASE_DATE.eq(new JdbcNamedParameter("requestReleaseDate")),
				REQUESTTABLE.REQUEST_CLOSED_BY.eq(new JdbcNamedParameter("requestClosedBy")),
				REQUESTTABLE.REQUEST_CLOSE_DATE.eq(new JdbcNamedParameter("requestCloseDate")),
				REQUESTTABLE.REQUEST_OPEN_COUNT.eq(new JdbcNamedParameter("requestOpenCount")),
				REQUESTTABLE.REQUEST_STATUS.eq(new JdbcNamedParameter("requestStatus")),
				REQUESTTABLE.REQUEST_TRANS_TO.eq(new JdbcNamedParameter("requestTransTo")),
				REQUESTTABLE.REQUEST_TRANS_ID.eq(new JdbcNamedParameter("requestTransId")),
				REQUESTTABLE.DELETED.eq(new JdbcNamedParameter("deleted")),
				REQUESTTABLE.REPLY_SPEC.eq(new JdbcNamedParameter("replySpec")),
				REQUESTTABLE.REPLIER.eq(new JdbcNamedParameter("replier")),
				REQUESTTABLE.REPLY_DATE.eq(new JdbcNamedParameter("replyDate"))));
			}
		});
	}
	@LogMethod("addOrderByElements")
	private  Select addOrderByElements(Select select ,OrderBy... orderBies){
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
