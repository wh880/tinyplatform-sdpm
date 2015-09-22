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

package org.tinygroup.sdpm.system.dao.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.system.dao.constant.MailqueueTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
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
import org.tinygroup.sdpm.system.dao.pojo.Mailqueue;
import org.tinygroup.sdpm.system.dao.MailqueueDao;
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
public class MailqueueDaoImpl extends TinyDslDaoSupport implements MailqueueDao {

	public Mailqueue add(Mailqueue mailqueue) {
		return getDslTemplate().insertAndReturnKey(mailqueue, new InsertGenerateCallback<Mailqueue>() {
			public Insert generate(Mailqueue t) {
				Insert insert = insertInto(MAILQUEUETABLE).values(
					MAILQUEUETABLE.MAILQUEUE_ID.value(t.getMailqueueId()),
					MAILQUEUETABLE.MAILQUEUE_TOLIST.value(t.getMailqueueToList()),
					MAILQUEUETABLE.MAILQUEUE_CCLIST.value(t.getMailqueueCcList()),
					MAILQUEUETABLE.MAILQUEUE_SUBJECT.value(t.getMailqueueSubject()),
					MAILQUEUETABLE.MAILQUEUE_BODY.value(t.getMailqueueBody()),
					MAILQUEUETABLE.MAILQUEUE_ADDEDDATE.value(t.getMailqueueAddedDate()),
					MAILQUEUETABLE.MAILQUEUE_ADDEDBY.value(t.getMailqueueAddedBy()),
					MAILQUEUETABLE.MAILQUEUE_SENDTIME.value(t.getMailqueueSendTime()),
					MAILQUEUETABLE.MAILQUEUE_STATUS.value(t.getMailqueueStatus()),
					MAILQUEUETABLE.MAILQUEUE_FAILREASON.value(t.getMailqueueFailReason()));
				return insert;
			}
		});
	}

	public int edit(Mailqueue mailqueue) {
		if(mailqueue == null || mailqueue.getMailqueueId() == null){
			return 0;
		}
		return getDslTemplate().update(mailqueue, new UpdateGenerateCallback<Mailqueue>() {
			public Update generate(Mailqueue t) {
				Update update = update(MAILQUEUETABLE).set(
					MAILQUEUETABLE.MAILQUEUE_TOLIST.value(t.getMailqueueToList()),
					MAILQUEUETABLE.MAILQUEUE_CCLIST.value(t.getMailqueueCcList()),
					MAILQUEUETABLE.MAILQUEUE_SUBJECT.value(t.getMailqueueSubject()),
					MAILQUEUETABLE.MAILQUEUE_BODY.value(t.getMailqueueBody()),
					MAILQUEUETABLE.MAILQUEUE_ADDEDDATE.value(t.getMailqueueAddedDate()),
					MAILQUEUETABLE.MAILQUEUE_ADDEDBY.value(t.getMailqueueAddedBy()),
					MAILQUEUETABLE.MAILQUEUE_SENDTIME.value(t.getMailqueueSendTime()),
					MAILQUEUETABLE.MAILQUEUE_STATUS.value(t.getMailqueueStatus()),
					MAILQUEUETABLE.MAILQUEUE_FAILREASON.value(t.getMailqueueFailReason())).where(
					MAILQUEUETABLE.MAILQUEUE_ID.eq(t.getMailqueueId()));
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
				return delete(MAILQUEUETABLE).where(MAILQUEUETABLE.MAILQUEUE_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(MAILQUEUETABLE).where(MAILQUEUETABLE.MAILQUEUE_ID.in(t));
		}
		},pks);
	}

	public Mailqueue getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Mailqueue.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(MAILQUEUETABLE).where(MAILQUEUETABLE.MAILQUEUE_ID.eq(t));
			}
		});
	}

	public List<Mailqueue> query(Mailqueue mailqueue ,final OrderBy... orderBies) {
		if(mailqueue==null){
			mailqueue=new Mailqueue();
		}
		return getDslTemplate().query(mailqueue, new SelectGenerateCallback<Mailqueue>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Mailqueue t) {
				Select select = selectFrom(MAILQUEUETABLE).where(
				and(
					MAILQUEUETABLE.MAILQUEUE_TOLIST.eq(t.getMailqueueToList()),
					MAILQUEUETABLE.MAILQUEUE_CCLIST.eq(t.getMailqueueCcList()),
					MAILQUEUETABLE.MAILQUEUE_SUBJECT.eq(t.getMailqueueSubject()),
					MAILQUEUETABLE.MAILQUEUE_BODY.eq(t.getMailqueueBody()),
					MAILQUEUETABLE.MAILQUEUE_ADDEDDATE.eq(t.getMailqueueAddedDate()),
					MAILQUEUETABLE.MAILQUEUE_ADDEDBY.eq(t.getMailqueueAddedBy()),
					MAILQUEUETABLE.MAILQUEUE_SENDTIME.eq(t.getMailqueueSendTime()),
					MAILQUEUETABLE.MAILQUEUE_STATUS.eq(t.getMailqueueStatus()),
					MAILQUEUETABLE.MAILQUEUE_FAILREASON.eq(t.getMailqueueFailReason())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<Mailqueue> queryPager(int start,int limit ,Mailqueue mailqueue ,final OrderBy... orderBies) {
		if(mailqueue==null){
			mailqueue=new Mailqueue();
		}
		return getDslTemplate().queryPager(start, limit, mailqueue, false, new SelectGenerateCallback<Mailqueue>() {

			public Select generate(Mailqueue t) {
				Select select = MysqlSelect.selectFrom(MAILQUEUETABLE).where(
				and(
					MAILQUEUETABLE.MAILQUEUE_TOLIST.eq(t.getMailqueueToList()),
					MAILQUEUETABLE.MAILQUEUE_CCLIST.eq(t.getMailqueueCcList()),
					MAILQUEUETABLE.MAILQUEUE_SUBJECT.eq(t.getMailqueueSubject()),
					MAILQUEUETABLE.MAILQUEUE_BODY.eq(t.getMailqueueBody()),
					MAILQUEUETABLE.MAILQUEUE_ADDEDDATE.eq(t.getMailqueueAddedDate()),
					MAILQUEUETABLE.MAILQUEUE_ADDEDBY.eq(t.getMailqueueAddedBy()),
					MAILQUEUETABLE.MAILQUEUE_SENDTIME.eq(t.getMailqueueSendTime()),
					MAILQUEUETABLE.MAILQUEUE_STATUS.eq(t.getMailqueueStatus()),
					MAILQUEUETABLE.MAILQUEUE_FAILREASON.eq(t.getMailqueueFailReason())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Mailqueue> mailqueues) {
		if (CollectionUtil.isEmpty(mailqueues)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, mailqueues, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(MAILQUEUETABLE).values(
					MAILQUEUETABLE.MAILQUEUE_TOLIST.value(new JdbcNamedParameter("mailqueueToList")),
					MAILQUEUETABLE.MAILQUEUE_CCLIST.value(new JdbcNamedParameter("mailqueueCcList")),
					MAILQUEUETABLE.MAILQUEUE_SUBJECT.value(new JdbcNamedParameter("mailqueueSubject")),
					MAILQUEUETABLE.MAILQUEUE_BODY.value(new JdbcNamedParameter("mailqueueBody")),
					MAILQUEUETABLE.MAILQUEUE_ADDEDDATE.value(new JdbcNamedParameter("mailqueueAddedDate")),
					MAILQUEUETABLE.MAILQUEUE_ADDEDBY.value(new JdbcNamedParameter("mailqueueAddedBy")),
					MAILQUEUETABLE.MAILQUEUE_SENDTIME.value(new JdbcNamedParameter("mailqueueSendTime")),
					MAILQUEUETABLE.MAILQUEUE_STATUS.value(new JdbcNamedParameter("mailqueueStatus")),
					MAILQUEUETABLE.MAILQUEUE_FAILREASON.value(new JdbcNamedParameter("mailqueueFailReason")));
			}
		});
	}

	public int[] batchInsert(List<Mailqueue> mailqueues){
			return batchInsert(true ,mailqueues);
	}

	public int[] batchUpdate(List<Mailqueue> mailqueues) {
		if (CollectionUtil.isEmpty(mailqueues)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(mailqueues, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(MAILQUEUETABLE).set(
					MAILQUEUETABLE.MAILQUEUE_TOLIST.value(new JdbcNamedParameter("mailqueueToList")),
					MAILQUEUETABLE.MAILQUEUE_CCLIST.value(new JdbcNamedParameter("mailqueueCcList")),
					MAILQUEUETABLE.MAILQUEUE_SUBJECT.value(new JdbcNamedParameter("mailqueueSubject")),
					MAILQUEUETABLE.MAILQUEUE_BODY.value(new JdbcNamedParameter("mailqueueBody")),
					MAILQUEUETABLE.MAILQUEUE_ADDEDDATE.value(new JdbcNamedParameter("mailqueueAddedDate")),
					MAILQUEUETABLE.MAILQUEUE_ADDEDBY.value(new JdbcNamedParameter("mailqueueAddedBy")),
					MAILQUEUETABLE.MAILQUEUE_SENDTIME.value(new JdbcNamedParameter("mailqueueSendTime")),
					MAILQUEUETABLE.MAILQUEUE_STATUS.value(new JdbcNamedParameter("mailqueueStatus")),
					MAILQUEUETABLE.MAILQUEUE_FAILREASON.value(new JdbcNamedParameter("mailqueueFailReason"))).where(
				MAILQUEUETABLE.MAILQUEUE_ID.eq(new JdbcNamedParameter("mailqueueId")));
			}
		});
	}

	public int[] batchDelete(List<Mailqueue> mailqueues) {
		if (CollectionUtil.isEmpty(mailqueues)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(mailqueues, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(MAILQUEUETABLE).where(and(
				MAILQUEUETABLE.MAILQUEUE_ID.eq(new JdbcNamedParameter("mailqueueId")),
				MAILQUEUETABLE.MAILQUEUE_TOLIST.eq(new JdbcNamedParameter("mailqueueToList")),
				MAILQUEUETABLE.MAILQUEUE_CCLIST.eq(new JdbcNamedParameter("mailqueueCcList")),
				MAILQUEUETABLE.MAILQUEUE_SUBJECT.eq(new JdbcNamedParameter("mailqueueSubject")),
				MAILQUEUETABLE.MAILQUEUE_BODY.eq(new JdbcNamedParameter("mailqueueBody")),
				MAILQUEUETABLE.MAILQUEUE_ADDEDDATE.eq(new JdbcNamedParameter("mailqueueAddedDate")),
				MAILQUEUETABLE.MAILQUEUE_ADDEDBY.eq(new JdbcNamedParameter("mailqueueAddedBy")),
				MAILQUEUETABLE.MAILQUEUE_SENDTIME.eq(new JdbcNamedParameter("mailqueueSendTime")),
				MAILQUEUETABLE.MAILQUEUE_STATUS.eq(new JdbcNamedParameter("mailqueueStatus")),
				MAILQUEUETABLE.MAILQUEUE_FAILREASON.eq(new JdbcNamedParameter("mailqueueFailReason"))));
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
