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
import static org.tinygroup.sdpm.service.dao.constant.FaqTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
	import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.sdpm.service.dao.pojo.Faq;
import org.tinygroup.sdpm.service.dao.FaqDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;
@Repository
public class FaqDaoImpl extends TinyDslDaoSupport implements FaqDao {

	public Faq add(Faq faq) {
		return getDslTemplate().insertAndReturnKey(faq, new InsertGenerateCallback<Faq>() {
			public Insert generate(Faq t) {
				Insert insert = insertInto(FAQTABLE).values(
					FAQTABLE.FAQ_ID.value(t.getFaqId()),
					FAQTABLE.FAQ_TYPE.value(t.getFaqType()),
					FAQTABLE.FAQ_QUESTION.value(t.getFaqQuestion()),
					FAQTABLE.FAQ_ANSWER.value(t.getFaqAnswer()),
					FAQTABLE.DELETED.value(t.getDeleted()),
					FAQTABLE.FAQ_CREATE_DATE.value(t.getFaqCreateDate()),
					FAQTABLE.FAQ_CREATED_BY.value(t.getFaqCreatedBy()),
					FAQTABLE.PRODUCT_ID.value(t.getProductId()),
					FAQTABLE.FAQ_KEYWORDS.value(t.getFaqKeywords()),
					FAQTABLE.FAQ_SOURCE_ID.value(t.getFaqSourceId()),
					FAQTABLE.FAQ_SOURCE.value(t.getFaqSource()),
					FAQTABLE.FAQ_REPLIED_BY.value(t.getFaqRepliedBy()),
					FAQTABLE.FAQ_REPLY_DATE.value(t.getFaqReplyDate()));
				return insert;
			}
		});
	}

	public int edit(Faq faq) {
		if(faq == null || faq.getFaqId() == null){
			return 0;
		}
		return getDslTemplate().update(faq, new UpdateGenerateCallback<Faq>() {
			public Update generate(Faq t) {
				Update update = update(FAQTABLE).set(
					FAQTABLE.FAQ_TYPE.value(t.getFaqType()),
					FAQTABLE.FAQ_QUESTION.value(t.getFaqQuestion()),
					FAQTABLE.FAQ_ANSWER.value(t.getFaqAnswer()),
					FAQTABLE.DELETED.value(t.getDeleted()),
					FAQTABLE.FAQ_CREATE_DATE.value(t.getFaqCreateDate()),
					FAQTABLE.FAQ_CREATED_BY.value(t.getFaqCreatedBy()),
					FAQTABLE.PRODUCT_ID.value(t.getProductId()),
					FAQTABLE.FAQ_KEYWORDS.value(t.getFaqKeywords()),
					FAQTABLE.FAQ_SOURCE_ID.value(t.getFaqSourceId()),
					FAQTABLE.FAQ_SOURCE.value(t.getFaqSource()),
					FAQTABLE.FAQ_REPLIED_BY.value(t.getFaqRepliedBy()),
					FAQTABLE.FAQ_REPLY_DATE.value(t.getFaqReplyDate())).where(
					FAQTABLE.FAQ_ID.eq(t.getFaqId()));
				return update;
			}
		});
	}

	public int deleteByKey(Integer pk){
		if(pk == null){
			return 0;
		}
		return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(FAQTABLE).where(FAQTABLE.FAQ_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(FAQTABLE).where(FAQTABLE.FAQ_ID.in(t));
		}
		},pks);
	}

	public Faq getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Faq.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(FAQTABLE).where(FAQTABLE.FAQ_ID.eq(t));
			}
		});
	}

	public List<Faq> query(Faq faq ,final OrderBy... orderBies) {
		if(faq==null){
			faq=new Faq();
		}
		return getDslTemplate().query(faq, new SelectGenerateCallback<Faq>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Faq t) {
				Select select = selectFrom(FAQTABLE).where(
				and(
					FAQTABLE.FAQ_TYPE.eq(t.getFaqType()),
					FAQTABLE.FAQ_QUESTION.eq(t.getFaqQuestion()),
					FAQTABLE.FAQ_ANSWER.eq(t.getFaqAnswer()),
					FAQTABLE.DELETED.eq(t.getDeleted()),
					FAQTABLE.FAQ_CREATE_DATE.eq(t.getFaqCreateDate()),
					FAQTABLE.FAQ_CREATED_BY.eq(t.getFaqCreatedBy()),
					FAQTABLE.PRODUCT_ID.eq(t.getProductId()),
					FAQTABLE.FAQ_KEYWORDS.eq(t.getFaqKeywords()),
					FAQTABLE.FAQ_SOURCE_ID.eq(t.getFaqSourceId()),
					FAQTABLE.FAQ_SOURCE.eq(t.getFaqSource()),
					FAQTABLE.FAQ_REPLIED_BY.eq(t.getFaqRepliedBy()),
					FAQTABLE.FAQ_REPLY_DATE.eq(t.getFaqReplyDate())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<Faq> queryPager(int start,int limit ,Faq faq ,final OrderBy... orderBies) {
		if(faq==null){
			faq=new Faq();
		}
		return getDslTemplate().queryPager(start, limit, faq, false, new SelectGenerateCallback<Faq>() {

			public Select generate(Faq t) {
				Select select = MysqlSelect.selectFrom(FAQTABLE).where(
				and(
					FAQTABLE.FAQ_TYPE.eq(t.getFaqType()),
					FAQTABLE.FAQ_QUESTION.eq(t.getFaqQuestion()),
					FAQTABLE.FAQ_ANSWER.eq(t.getFaqAnswer()),
					FAQTABLE.DELETED.eq(t.getDeleted()),
					FAQTABLE.FAQ_CREATE_DATE.eq(t.getFaqCreateDate()),
					FAQTABLE.FAQ_CREATED_BY.eq(t.getFaqCreatedBy()),
					FAQTABLE.PRODUCT_ID.eq(t.getProductId()),
					FAQTABLE.FAQ_KEYWORDS.eq(t.getFaqKeywords()),
					FAQTABLE.FAQ_SOURCE_ID.eq(t.getFaqSourceId()),
					FAQTABLE.FAQ_SOURCE.eq(t.getFaqSource()),
					FAQTABLE.FAQ_REPLIED_BY.eq(t.getFaqRepliedBy()),
					FAQTABLE.FAQ_REPLY_DATE.eq(t.getFaqReplyDate())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Faq> faqs) {
		if (CollectionUtil.isEmpty(faqs)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, faqs, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(FAQTABLE).values(
					FAQTABLE.FAQ_TYPE.value(new JdbcNamedParameter("faqType")),
					FAQTABLE.FAQ_QUESTION.value(new JdbcNamedParameter("faqQuestion")),
					FAQTABLE.FAQ_ANSWER.value(new JdbcNamedParameter("faqAnswer")),
					FAQTABLE.DELETED.value(new JdbcNamedParameter("deleted")),
					FAQTABLE.FAQ_CREATE_DATE.value(new JdbcNamedParameter("faqCreateDate")),
					FAQTABLE.FAQ_CREATED_BY.value(new JdbcNamedParameter("faqCreatedBy")),
					FAQTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					FAQTABLE.FAQ_KEYWORDS.value(new JdbcNamedParameter("faqKeywords")),
					FAQTABLE.FAQ_SOURCE_ID.value(new JdbcNamedParameter("faqSourceId")),
					FAQTABLE.FAQ_SOURCE.value(new JdbcNamedParameter("faqSource")),
					FAQTABLE.FAQ_REPLIED_BY.value(new JdbcNamedParameter("faqRepliedBy")),
					FAQTABLE.FAQ_REPLY_DATE.value(new JdbcNamedParameter("faqReplyDate")));
			}
		});
	}

	public int[] batchInsert(List<Faq> faqs){
			return batchInsert(true ,faqs);
	}

	public int[] batchUpdate(List<Faq> faqs) {
		if (CollectionUtil.isEmpty(faqs)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(faqs, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(FAQTABLE).set(
					FAQTABLE.FAQ_TYPE.value(new JdbcNamedParameter("faqType")),
					FAQTABLE.FAQ_QUESTION.value(new JdbcNamedParameter("faqQuestion")),
					FAQTABLE.FAQ_ANSWER.value(new JdbcNamedParameter("faqAnswer")),
					FAQTABLE.DELETED.value(new JdbcNamedParameter("deleted")),
					FAQTABLE.FAQ_CREATE_DATE.value(new JdbcNamedParameter("faqCreateDate")),
					FAQTABLE.FAQ_CREATED_BY.value(new JdbcNamedParameter("faqCreatedBy")),
					FAQTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					FAQTABLE.FAQ_KEYWORDS.value(new JdbcNamedParameter("faqKeywords")),
					FAQTABLE.FAQ_SOURCE_ID.value(new JdbcNamedParameter("faqSourceId")),
					FAQTABLE.FAQ_SOURCE.value(new JdbcNamedParameter("faqSource")),
					FAQTABLE.FAQ_REPLIED_BY.value(new JdbcNamedParameter("faqRepliedBy")),
					FAQTABLE.FAQ_REPLY_DATE.value(new JdbcNamedParameter("faqReplyDate"))).where(
				FAQTABLE.FAQ_ID.eq(new JdbcNamedParameter("faqId")));
			}
		});
	}

	public int[] batchDelete(List<Faq> faqs) {
		if (CollectionUtil.isEmpty(faqs)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(faqs, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(FAQTABLE).where(and(
				FAQTABLE.FAQ_ID.eq(new JdbcNamedParameter("faqId")),
				FAQTABLE.FAQ_TYPE.eq(new JdbcNamedParameter("faqType")),
				FAQTABLE.FAQ_QUESTION.eq(new JdbcNamedParameter("faqQuestion")),
				FAQTABLE.FAQ_ANSWER.eq(new JdbcNamedParameter("faqAnswer")),
				FAQTABLE.DELETED.eq(new JdbcNamedParameter("deleted")),
				FAQTABLE.FAQ_CREATE_DATE.eq(new JdbcNamedParameter("faqCreateDate")),
				FAQTABLE.FAQ_CREATED_BY.eq(new JdbcNamedParameter("faqCreatedBy")),
				FAQTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
				FAQTABLE.FAQ_KEYWORDS.eq(new JdbcNamedParameter("faqKeywords")),
				FAQTABLE.FAQ_SOURCE_ID.eq(new JdbcNamedParameter("faqSourceId")),
				FAQTABLE.FAQ_SOURCE.eq(new JdbcNamedParameter("faqSource")),
				FAQTABLE.FAQ_REPLIED_BY.eq(new JdbcNamedParameter("faqRepliedBy")),
				FAQTABLE.FAQ_REPLY_DATE.eq(new JdbcNamedParameter("faqReplyDate"))));
			}
		});
	}

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
