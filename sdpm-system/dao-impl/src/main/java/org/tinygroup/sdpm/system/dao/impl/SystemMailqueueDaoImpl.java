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
import static org.tinygroup.sdpm.system.dao.constant.SystemMailqueueTable.*;
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
import org.tinygroup.sdpm.system.dao.pojo.SystemMailqueue;
import org.tinygroup.sdpm.system.dao.SystemMailqueueDao;
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
public class SystemMailqueueDaoImpl extends TinyDslDaoSupport implements SystemMailqueueDao {

	public SystemMailqueue add(SystemMailqueue systemMailqueue) {
		return getDslTemplate().insertAndReturnKey(systemMailqueue, new InsertGenerateCallback<SystemMailqueue>() {
			public Insert generate(SystemMailqueue t) {
				Insert insert = insertInto(SYSTEM_MAILQUEUETABLE).values(
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_ID.value(t.getMailqueueId()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_TOLIST.value(t.getMailqueueToList()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_CCLIST.value(t.getMailqueueCcList()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_SUBJECT.value(t.getMailqueueSubject()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_BODY.value(t.getMailqueueBody()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_ADDED_DATE.value(t.getMailqueueAddedDate()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_ADDED_BY.value(t.getMailqueueAddedBy()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_SENDTIME.value(t.getMailqueueSendTime()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_STATUS.value(t.getMailqueueStatus()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_FAIL_REASON.value(t.getMailqueueFailReason()));
				return insert;
			}
		});
	}

	public int edit(SystemMailqueue systemMailqueue) {
		if(systemMailqueue == null || systemMailqueue.getMailqueueId() == null){
			return 0;
		}
		return getDslTemplate().update(systemMailqueue, new UpdateGenerateCallback<SystemMailqueue>() {
			public Update generate(SystemMailqueue t) {
				Update update = update(SYSTEM_MAILQUEUETABLE).set(
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_TOLIST.value(t.getMailqueueToList()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_CCLIST.value(t.getMailqueueCcList()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_SUBJECT.value(t.getMailqueueSubject()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_BODY.value(t.getMailqueueBody()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_ADDED_DATE.value(t.getMailqueueAddedDate()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_ADDED_BY.value(t.getMailqueueAddedBy()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_SENDTIME.value(t.getMailqueueSendTime()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_STATUS.value(t.getMailqueueStatus()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_FAIL_REASON.value(t.getMailqueueFailReason())).where(
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_ID.eq(t.getMailqueueId()));
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
				return delete(SYSTEM_MAILQUEUETABLE).where(SYSTEM_MAILQUEUETABLE.MAILQUEUE_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(SYSTEM_MAILQUEUETABLE).where(SYSTEM_MAILQUEUETABLE.MAILQUEUE_ID.in(t));
		}
		},pks);
	}

	public SystemMailqueue getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, SystemMailqueue.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(SYSTEM_MAILQUEUETABLE).where(SYSTEM_MAILQUEUETABLE.MAILQUEUE_ID.eq(t));
			}
		});
	}

	public List<SystemMailqueue> query(SystemMailqueue systemMailqueue ,final OrderBy... orderBies) {
		if(systemMailqueue==null){
			systemMailqueue=new SystemMailqueue();
		}
		return getDslTemplate().query(systemMailqueue, new SelectGenerateCallback<SystemMailqueue>() {

			@SuppressWarnings("rawtypes")
			public Select generate(SystemMailqueue t) {
				Select select = selectFrom(SYSTEM_MAILQUEUETABLE).where(
				and(
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_TOLIST.eq(t.getMailqueueToList()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_CCLIST.eq(t.getMailqueueCcList()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_SUBJECT.eq(t.getMailqueueSubject()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_BODY.eq(t.getMailqueueBody()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_ADDED_DATE.eq(t.getMailqueueAddedDate()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_ADDED_BY.eq(t.getMailqueueAddedBy()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_SENDTIME.eq(t.getMailqueueSendTime()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_STATUS.eq(t.getMailqueueStatus()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_FAIL_REASON.eq(t.getMailqueueFailReason())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<SystemMailqueue> queryPager(int start,int limit ,SystemMailqueue systemMailqueue ,final OrderBy... orderBies) {
		if(systemMailqueue==null){
			systemMailqueue=new SystemMailqueue();
		}
		return getDslTemplate().queryPager(start, limit, systemMailqueue, false, new SelectGenerateCallback<SystemMailqueue>() {

			public Select generate(SystemMailqueue t) {
				Select select = MysqlSelect.selectFrom(SYSTEM_MAILQUEUETABLE).where(
				and(
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_TOLIST.eq(t.getMailqueueToList()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_CCLIST.eq(t.getMailqueueCcList()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_SUBJECT.eq(t.getMailqueueSubject()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_BODY.eq(t.getMailqueueBody()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_ADDED_DATE.eq(t.getMailqueueAddedDate()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_ADDED_BY.eq(t.getMailqueueAddedBy()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_SENDTIME.eq(t.getMailqueueSendTime()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_STATUS.eq(t.getMailqueueStatus()),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_FAIL_REASON.eq(t.getMailqueueFailReason())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<SystemMailqueue> systemMailqueues) {
		if (CollectionUtil.isEmpty(systemMailqueues)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, systemMailqueues, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(SYSTEM_MAILQUEUETABLE).values(
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_TOLIST.value(new JdbcNamedParameter("mailqueueToList")),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_CCLIST.value(new JdbcNamedParameter("mailqueueCcList")),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_SUBJECT.value(new JdbcNamedParameter("mailqueueSubject")),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_BODY.value(new JdbcNamedParameter("mailqueueBody")),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_ADDED_DATE.value(new JdbcNamedParameter("mailqueueAddedDate")),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_ADDED_BY.value(new JdbcNamedParameter("mailqueueAddedBy")),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_SENDTIME.value(new JdbcNamedParameter("mailqueueSendTime")),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_STATUS.value(new JdbcNamedParameter("mailqueueStatus")),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_FAIL_REASON.value(new JdbcNamedParameter("mailqueueFailReason")));
			}
		});
	}

	public int[] batchInsert(List<SystemMailqueue> systemMailqueues){
			return batchInsert(true ,systemMailqueues);
	}

	public int[] batchUpdate(List<SystemMailqueue> systemMailqueues) {
		if (CollectionUtil.isEmpty(systemMailqueues)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(systemMailqueues, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(SYSTEM_MAILQUEUETABLE).set(
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_TOLIST.value(new JdbcNamedParameter("mailqueueToList")),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_CCLIST.value(new JdbcNamedParameter("mailqueueCcList")),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_SUBJECT.value(new JdbcNamedParameter("mailqueueSubject")),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_BODY.value(new JdbcNamedParameter("mailqueueBody")),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_ADDED_DATE.value(new JdbcNamedParameter("mailqueueAddedDate")),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_ADDED_BY.value(new JdbcNamedParameter("mailqueueAddedBy")),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_SENDTIME.value(new JdbcNamedParameter("mailqueueSendTime")),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_STATUS.value(new JdbcNamedParameter("mailqueueStatus")),
					SYSTEM_MAILQUEUETABLE.MAILQUEUE_FAIL_REASON.value(new JdbcNamedParameter("mailqueueFailReason"))).where(
				SYSTEM_MAILQUEUETABLE.MAILQUEUE_ID.eq(new JdbcNamedParameter("mailqueueId")));
			}
		});
	}

	public int[] batchDelete(List<SystemMailqueue> systemMailqueues) {
		if (CollectionUtil.isEmpty(systemMailqueues)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(systemMailqueues, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(SYSTEM_MAILQUEUETABLE).where(and(
				SYSTEM_MAILQUEUETABLE.MAILQUEUE_ID.eq(new JdbcNamedParameter("mailqueueId")),
				SYSTEM_MAILQUEUETABLE.MAILQUEUE_TOLIST.eq(new JdbcNamedParameter("mailqueueToList")),
				SYSTEM_MAILQUEUETABLE.MAILQUEUE_CCLIST.eq(new JdbcNamedParameter("mailqueueCcList")),
				SYSTEM_MAILQUEUETABLE.MAILQUEUE_SUBJECT.eq(new JdbcNamedParameter("mailqueueSubject")),
				SYSTEM_MAILQUEUETABLE.MAILQUEUE_BODY.eq(new JdbcNamedParameter("mailqueueBody")),
				SYSTEM_MAILQUEUETABLE.MAILQUEUE_ADDED_DATE.eq(new JdbcNamedParameter("mailqueueAddedDate")),
				SYSTEM_MAILQUEUETABLE.MAILQUEUE_ADDED_BY.eq(new JdbcNamedParameter("mailqueueAddedBy")),
				SYSTEM_MAILQUEUETABLE.MAILQUEUE_SENDTIME.eq(new JdbcNamedParameter("mailqueueSendTime")),
				SYSTEM_MAILQUEUETABLE.MAILQUEUE_STATUS.eq(new JdbcNamedParameter("mailqueueStatus")),
				SYSTEM_MAILQUEUETABLE.MAILQUEUE_FAIL_REASON.eq(new JdbcNamedParameter("mailqueueFailReason"))));
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
