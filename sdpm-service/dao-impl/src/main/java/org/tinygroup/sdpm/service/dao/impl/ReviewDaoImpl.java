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
import static org.tinygroup.sdpm.service.dao.constant.ReviewTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;

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
import org.tinygroup.sdpm.service.dao.pojo.Review;
import org.tinygroup.sdpm.service.dao.ReviewDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;
@LogClass("review")
public class ReviewDaoImpl extends TinyDslDaoSupport implements ReviewDao {
	@LogMethod("add")
	public Review add(Review review) {
		return getDslTemplate().insertAndReturnKey(review, new InsertGenerateCallback<Review>() {
			public Insert generate(Review t) {
				Insert insert = insertInto(REVIEWTABLE).values(
					REVIEWTABLE.REVIEW_ID.value(t.getReviewId()),
					REVIEWTABLE.CLIENT_REQUEST_ID.value(t.getClientRequestId()),
					REVIEWTABLE.REVIEW_SPEC.value(t.getReviewSpec()),
					REVIEWTABLE.REQUESTER.value(t.getRequester()),
					REVIEWTABLE.REVIEWER.value(t.getReviewer()),
					REVIEWTABLE.REVIEW_DATE.value(t.getReviewDate()),
					REVIEWTABLE.REVIEW_RESULT.value(t.getReviewResult()),
					REVIEWTABLE.REVIEW_SCORE.value(t.getReviewScore()),
					REVIEWTABLE.REVIEW_TYPE.value(t.getReviewType()));
				return insert;
			}
		});
	}
	@LogMethod("edit")
	public int edit(Review review) {
		if(review == null || review.getReviewId() == null){
			return 0;
		}
		return getDslTemplate().update(review, new UpdateGenerateCallback<Review>() {
			public Update generate(Review t) {
				Update update = update(REVIEWTABLE).set(
					REVIEWTABLE.CLIENT_REQUEST_ID.value(t.getClientRequestId()),
					REVIEWTABLE.REVIEW_SPEC.value(t.getReviewSpec()),
					REVIEWTABLE.REQUESTER.value(t.getRequester()),
					REVIEWTABLE.REVIEWER.value(t.getReviewer()),
					REVIEWTABLE.REVIEW_DATE.value(t.getReviewDate()),
					REVIEWTABLE.REVIEW_RESULT.value(t.getReviewResult()),
					REVIEWTABLE.REVIEW_SCORE.value(t.getReviewScore()),
					REVIEWTABLE.REVIEW_TYPE.value(t.getReviewType())).where(
					REVIEWTABLE.REVIEW_ID.eq(t.getReviewId()));
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
				return delete(REVIEWTABLE).where(REVIEWTABLE.REVIEW_ID.eq(pk));
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
				return delete(REVIEWTABLE).where(REVIEWTABLE.REVIEW_ID.in(t));
		}
		},pks);
	}
	@LogMethod("getByKey")
	public Review getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Review.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(REVIEWTABLE).where(REVIEWTABLE.REVIEW_ID.eq(t));
			}
		});
	}
	@LogMethod("query")
	public List<Review> query(Review review ,final OrderBy... orderBies) {
		if(review==null){
			review=new Review();
		}
		return getDslTemplate().query(review, new SelectGenerateCallback<Review>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Review t) {
				Select select = selectFrom(REVIEWTABLE).where(
				and(
					REVIEWTABLE.CLIENT_REQUEST_ID.eq(t.getClientRequestId()),
					REVIEWTABLE.REVIEW_SPEC.eq(t.getReviewSpec()),
					REVIEWTABLE.REQUESTER.eq(t.getRequester()),
					REVIEWTABLE.REVIEWER.eq(t.getReviewer()),
					REVIEWTABLE.REVIEW_DATE.eq(t.getReviewDate()),
					REVIEWTABLE.REVIEW_RESULT.eq(t.getReviewResult()),
					REVIEWTABLE.REVIEW_SCORE.eq(t.getReviewScore()),
					REVIEWTABLE.REVIEW_TYPE.eq(t.getReviewType())));
		return addOrderByElements(select, orderBies);
			}
		});
	}
	@LogMethod("queryPager")
	public Pager<Review> queryPager(int start,int limit ,Review review ,final OrderBy... orderBies) {
		if(review==null){
			review=new Review();
		}
		return getDslTemplate().queryPager(start, limit, review, false, new SelectGenerateCallback<Review>() {

			public Select generate(Review t) {
				Select select = MysqlSelect.selectFrom(REVIEWTABLE).where(
				and(
					REVIEWTABLE.CLIENT_REQUEST_ID.eq(t.getClientRequestId()),
					REVIEWTABLE.REVIEW_SPEC.eq(t.getReviewSpec()),
					REVIEWTABLE.REQUESTER.eq(t.getRequester()),
					REVIEWTABLE.REVIEWER.eq(t.getReviewer()),
					REVIEWTABLE.REVIEW_DATE.eq(t.getReviewDate()),
					REVIEWTABLE.REVIEW_RESULT.eq(t.getReviewResult()),
					REVIEWTABLE.REVIEW_SCORE.eq(t.getReviewScore()),
					REVIEWTABLE.REVIEW_TYPE.eq(t.getReviewType())));
		return addOrderByElements(select, orderBies);
			}
		});
	}
	@LogMethod("batchInsert")
	public int[] batchInsert(boolean autoGeneratedKeys ,List<Review> reviews) {
		if (CollectionUtil.isEmpty(reviews)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, reviews, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(REVIEWTABLE).values(
					REVIEWTABLE.CLIENT_REQUEST_ID.value(new JdbcNamedParameter("clientRequestId")),
					REVIEWTABLE.REVIEW_SPEC.value(new JdbcNamedParameter("reviewSpec")),
					REVIEWTABLE.REQUESTER.value(new JdbcNamedParameter("requester")),
					REVIEWTABLE.REVIEWER.value(new JdbcNamedParameter("reviewer")),
					REVIEWTABLE.REVIEW_DATE.value(new JdbcNamedParameter("reviewDate")),
					REVIEWTABLE.REVIEW_RESULT.value(new JdbcNamedParameter("reviewResult")),
					REVIEWTABLE.REVIEW_SCORE.value(new JdbcNamedParameter("reviewScore")),
					REVIEWTABLE.REVIEW_TYPE.value(new JdbcNamedParameter("reviewType")));
			}
		});
	}
	@LogMethod("batchInsert")
	public int[] batchInsert(List<Review> reviews){
			return batchInsert(true ,reviews);
	}
	@LogMethod("batchUpdate")
	public int[] batchUpdate(List<Review> reviews) {
		if (CollectionUtil.isEmpty(reviews)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(reviews, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(REVIEWTABLE).set(
					REVIEWTABLE.CLIENT_REQUEST_ID.value(new JdbcNamedParameter("clientRequestId")),
					REVIEWTABLE.REVIEW_SPEC.value(new JdbcNamedParameter("reviewSpec")),
					REVIEWTABLE.REQUESTER.value(new JdbcNamedParameter("requester")),
					REVIEWTABLE.REVIEWER.value(new JdbcNamedParameter("reviewer")),
					REVIEWTABLE.REVIEW_DATE.value(new JdbcNamedParameter("reviewDate")),
					REVIEWTABLE.REVIEW_RESULT.value(new JdbcNamedParameter("reviewResult")),
					REVIEWTABLE.REVIEW_SCORE.value(new JdbcNamedParameter("reviewScore")),
					REVIEWTABLE.REVIEW_TYPE.value(new JdbcNamedParameter("reviewType"))).where(
				REVIEWTABLE.REVIEW_ID.eq(new JdbcNamedParameter("reviewId")));
			}
		});
	}
	@LogMethod("batchDelete")
	public int[] batchDelete(List<Review> reviews) {
		if (CollectionUtil.isEmpty(reviews)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(reviews, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(REVIEWTABLE).where(and(
				REVIEWTABLE.REVIEW_ID.eq(new JdbcNamedParameter("reviewId")),
				REVIEWTABLE.CLIENT_REQUEST_ID.eq(new JdbcNamedParameter("clientRequestId")),
				REVIEWTABLE.REVIEW_SPEC.eq(new JdbcNamedParameter("reviewSpec")),
				REVIEWTABLE.REQUESTER.eq(new JdbcNamedParameter("requester")),
				REVIEWTABLE.REVIEWER.eq(new JdbcNamedParameter("reviewer")),
				REVIEWTABLE.REVIEW_DATE.eq(new JdbcNamedParameter("reviewDate")),
				REVIEWTABLE.REVIEW_RESULT.eq(new JdbcNamedParameter("reviewResult")),
				REVIEWTABLE.REVIEW_SCORE.eq(new JdbcNamedParameter("reviewScore")),
				REVIEWTABLE.REVIEW_TYPE.eq(new JdbcNamedParameter("reviewType"))));
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
