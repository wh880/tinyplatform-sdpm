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
import static org.tinygroup.sdpm.service.dao.constant.ReplyTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;

import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
	import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.sdpm.service.dao.pojo.Reply;
import org.tinygroup.sdpm.service.dao.ReplyDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class ReplyDaoImpl extends TinyDslDaoSupport implements ReplyDao {

	public Reply add(Reply reply) {
		return getDslTemplate().insertAndReturnKey(reply, new InsertGenerateCallback<Reply>() {
			public Insert generate(Reply t) {
				Insert insert = insertInto(REPLYTABLE).values(
					REPLYTABLE.REPLY_ID.value(t.getReplyId()),
					REPLYTABLE.CLIENT_REQUEST_ID.value(t.getClientRequestId()),
					REPLYTABLE.REPLY_OPINION.value(t.getReplyOpinion()),
					REPLYTABLE.REPLY_SPEC.value(t.getReplySpec()),
					REPLYTABLE.REPLY_COMMITMENT_DATE.value(t.getReplyCommitmentDate()),
					REPLYTABLE.REPLY_DO_BY.value(t.getReplyDoBy()),
					REPLYTABLE.REPLY_DO_DATE.value(t.getReplyDoDate()),
					REPLYTABLE.REPLIER.value(t.getReplier()),
					REPLYTABLE.REPLY_DATE.value(t.getReplyDate()),
					REPLYTABLE.REPLY_DONE.value(t.getReplyDone()));
				return insert;
			}
		});
	}

	public int edit(Reply reply) {
		if(reply == null || reply.getReplyId() == null){
			return 0;
		}
		return getDslTemplate().update(reply, new UpdateGenerateCallback<Reply>() {
			public Update generate(Reply t) {
				Update update = update(REPLYTABLE).set(
					REPLYTABLE.CLIENT_REQUEST_ID.value(t.getClientRequestId()),
					REPLYTABLE.REPLY_OPINION.value(t.getReplyOpinion()),
					REPLYTABLE.REPLY_SPEC.value(t.getReplySpec()),
					REPLYTABLE.REPLY_COMMITMENT_DATE.value(t.getReplyCommitmentDate()),
					REPLYTABLE.REPLY_DO_BY.value(t.getReplyDoBy()),
					REPLYTABLE.REPLY_DO_DATE.value(t.getReplyDoDate()),
					REPLYTABLE.REPLIER.value(t.getReplier()),
					REPLYTABLE.REPLY_DATE.value(t.getReplyDate()),
					REPLYTABLE.REPLY_DONE.value(t.getReplyDone())).where(
					REPLYTABLE.REPLY_ID.eq(t.getReplyId()));
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
				return delete(REPLYTABLE).where(REPLYTABLE.REPLY_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(REPLYTABLE).where(REPLYTABLE.REPLY_ID.in(t));
		}
		},pks);
	}

	public Reply getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Reply.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(REPLYTABLE).where(REPLYTABLE.REPLY_ID.eq(t));
			}
		});
	}

	public List<Reply> query(Reply reply ,final OrderBy... orderBies) {
		if(reply==null){
			reply=new Reply();
		}
		return getDslTemplate().query(reply, new SelectGenerateCallback<Reply>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Reply t) {
				Select select = selectFrom(REPLYTABLE).where(
				and(
					REPLYTABLE.CLIENT_REQUEST_ID.eq(t.getClientRequestId()),
					REPLYTABLE.REPLY_OPINION.eq(t.getReplyOpinion()),
					REPLYTABLE.REPLY_SPEC.eq(t.getReplySpec()),
					REPLYTABLE.REPLY_COMMITMENT_DATE.eq(t.getReplyCommitmentDate()),
					REPLYTABLE.REPLY_DO_BY.eq(t.getReplyDoBy()),
					REPLYTABLE.REPLY_DO_DATE.eq(t.getReplyDoDate()),
					REPLYTABLE.REPLIER.eq(t.getReplier()),
					REPLYTABLE.REPLY_DATE.eq(t.getReplyDate()),
					REPLYTABLE.REPLY_DONE.eq(t.getReplyDone())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<Reply> queryPager(int start,int limit ,Reply reply ,final OrderBy... orderBies) {
		if(reply==null){
			reply=new Reply();
		}
		return getDslTemplate().queryPager(start, limit, reply, false, new SelectGenerateCallback<Reply>() {

			public Select generate(Reply t) {
				Select select = MysqlSelect.selectFrom(REPLYTABLE).where(
				and(
					REPLYTABLE.CLIENT_REQUEST_ID.eq(t.getClientRequestId()),
					REPLYTABLE.REPLY_OPINION.eq(t.getReplyOpinion()),
					REPLYTABLE.REPLY_SPEC.eq(t.getReplySpec()),
					REPLYTABLE.REPLY_COMMITMENT_DATE.eq(t.getReplyCommitmentDate()),
					REPLYTABLE.REPLY_DO_BY.eq(t.getReplyDoBy()),
					REPLYTABLE.REPLY_DO_DATE.eq(t.getReplyDoDate()),
					REPLYTABLE.REPLIER.eq(t.getReplier()),
					REPLYTABLE.REPLY_DATE.eq(t.getReplyDate()),
					REPLYTABLE.REPLY_DONE.eq(t.getReplyDone())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Reply> replys) {
		if (CollectionUtil.isEmpty(replys)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, replys, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(REPLYTABLE).values(
					REPLYTABLE.CLIENT_REQUEST_ID.value(new JdbcNamedParameter("clientRequestId")),
					REPLYTABLE.REPLY_OPINION.value(new JdbcNamedParameter("replyOpinion")),
					REPLYTABLE.REPLY_SPEC.value(new JdbcNamedParameter("replySpec")),
					REPLYTABLE.REPLY_COMMITMENT_DATE.value(new JdbcNamedParameter("replyCommitmentDate")),
					REPLYTABLE.REPLY_DO_BY.value(new JdbcNamedParameter("replyDoBy")),
					REPLYTABLE.REPLY_DO_DATE.value(new JdbcNamedParameter("replyDoDate")),
					REPLYTABLE.REPLIER.value(new JdbcNamedParameter("replier")),
					REPLYTABLE.REPLY_DATE.value(new JdbcNamedParameter("replyDate")),
					REPLYTABLE.REPLY_DONE.value(new JdbcNamedParameter("replyDone")));
			}
		});
	}

	public int[] batchInsert(List<Reply> replys){
			return batchInsert(true ,replys);
	}

	public int[] batchUpdate(List<Reply> replys) {
		if (CollectionUtil.isEmpty(replys)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(replys, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(REPLYTABLE).set(
					REPLYTABLE.CLIENT_REQUEST_ID.value(new JdbcNamedParameter("clientRequestId")),
					REPLYTABLE.REPLY_OPINION.value(new JdbcNamedParameter("replyOpinion")),
					REPLYTABLE.REPLY_SPEC.value(new JdbcNamedParameter("replySpec")),
					REPLYTABLE.REPLY_COMMITMENT_DATE.value(new JdbcNamedParameter("replyCommitmentDate")),
					REPLYTABLE.REPLY_DO_BY.value(new JdbcNamedParameter("replyDoBy")),
					REPLYTABLE.REPLY_DO_DATE.value(new JdbcNamedParameter("replyDoDate")),
					REPLYTABLE.REPLIER.value(new JdbcNamedParameter("replier")),
					REPLYTABLE.REPLY_DATE.value(new JdbcNamedParameter("replyDate")),
					REPLYTABLE.REPLY_DONE.value(new JdbcNamedParameter("replyDone"))).where(
				REPLYTABLE.REPLY_ID.eq(new JdbcNamedParameter("replyId")));
			}
		});
	}

	public int[] batchDelete(List<Reply> replys) {
		if (CollectionUtil.isEmpty(replys)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(replys, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(REPLYTABLE).where(and(
				REPLYTABLE.REPLY_ID.eq(new JdbcNamedParameter("replyId")),
				REPLYTABLE.CLIENT_REQUEST_ID.eq(new JdbcNamedParameter("clientRequestId")),
				REPLYTABLE.REPLY_OPINION.eq(new JdbcNamedParameter("replyOpinion")),
				REPLYTABLE.REPLY_SPEC.eq(new JdbcNamedParameter("replySpec")),
				REPLYTABLE.REPLY_COMMITMENT_DATE.eq(new JdbcNamedParameter("replyCommitmentDate")),
				REPLYTABLE.REPLY_DO_BY.eq(new JdbcNamedParameter("replyDoBy")),
				REPLYTABLE.REPLY_DO_DATE.eq(new JdbcNamedParameter("replyDoDate")),
				REPLYTABLE.REPLIER.eq(new JdbcNamedParameter("replier")),
				REPLYTABLE.REPLY_DATE.eq(new JdbcNamedParameter("replyDate")),
				REPLYTABLE.REPLY_DONE.eq(new JdbcNamedParameter("replyDone"))));
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
