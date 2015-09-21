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
import static org.tinygroup.sdpm.system.dao.constant.EffortTable.*;
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
import org.tinygroup.sdpm.system.dao.pojo.Effort;
import org.tinygroup.sdpm.system.dao.EffortDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class EffortDaoImpl extends TinyDslDaoSupport implements EffortDao {

	public Effort add(Effort effort) {
		return getDslTemplate().insertAndReturnKey(effort, new InsertGenerateCallback<Effort>() {
			public Insert generate(Effort t) {
				Insert insert = insertInto(EFFORTTABLE).values(
					EFFORTTABLE.EFFORT_ID.value(t.getEffortId()),
					EFFORTTABLE.EFFORT_OBJECTTYPE.value(t.getEffortObjectType()),
					EFFORTTABLE.EFFORT_OBJECTID.value(t.getEffortObjectID()),
					EFFORTTABLE.EFFORT_PRODUCT.value(t.getEffortProduct()),
					EFFORTTABLE.EFFORT_PROJECT.value(t.getEffortProject()),
					EFFORTTABLE.EFFORT_ACCOUNT.value(t.getEffortAccount()),
					EFFORTTABLE.EFFORT_WORK.value(t.getEffortWork()),
					EFFORTTABLE.EFFORT_DATE.value(t.getEffortDate()),
					EFFORTTABLE.EFFORT_LEFT.value(t.getEffortLeft()),
					EFFORTTABLE.EFFORT_CONSUMED.value(t.getEffortConsumed()),
					EFFORTTABLE.EFFORT_BEGIN.value(t.getEffortBegin()),
					EFFORTTABLE.EFFORT_END.value(t.getEffortEnd()));
				return insert;
			}
		});
	}

	public int edit(Effort effort) {
		if(effort == null || effort.getEffortId() == null){
			return 0;
		}
		return getDslTemplate().update(effort, new UpdateGenerateCallback<Effort>() {
			public Update generate(Effort t) {
				Update update = update(EFFORTTABLE).set(
					EFFORTTABLE.EFFORT_OBJECTTYPE.value(t.getEffortObjectType()),
					EFFORTTABLE.EFFORT_OBJECTID.value(t.getEffortObjectID()),
					EFFORTTABLE.EFFORT_PRODUCT.value(t.getEffortProduct()),
					EFFORTTABLE.EFFORT_PROJECT.value(t.getEffortProject()),
					EFFORTTABLE.EFFORT_ACCOUNT.value(t.getEffortAccount()),
					EFFORTTABLE.EFFORT_WORK.value(t.getEffortWork()),
					EFFORTTABLE.EFFORT_DATE.value(t.getEffortDate()),
					EFFORTTABLE.EFFORT_LEFT.value(t.getEffortLeft()),
					EFFORTTABLE.EFFORT_CONSUMED.value(t.getEffortConsumed()),
					EFFORTTABLE.EFFORT_BEGIN.value(t.getEffortBegin()),
					EFFORTTABLE.EFFORT_END.value(t.getEffortEnd())).where(
					EFFORTTABLE.EFFORT_ID.eq(t.getEffortId()));
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
				return delete(EFFORTTABLE).where(EFFORTTABLE.EFFORT_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(EFFORTTABLE).where(EFFORTTABLE.EFFORT_ID.in(t));
		}
		},pks);
	}

	public Effort getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Effort.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(EFFORTTABLE).where(EFFORTTABLE.EFFORT_ID.eq(t));
			}
		});
	}

	public List<Effort> query(Effort effort ,final OrderBy... orderBies) {
		if(effort==null){
			effort=new Effort();
		}
		return getDslTemplate().query(effort, new SelectGenerateCallback<Effort>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Effort t) {
				Select select = selectFrom(EFFORTTABLE).where(
				and(
					EFFORTTABLE.EFFORT_OBJECTTYPE.eq(t.getEffortObjectType()),
					EFFORTTABLE.EFFORT_OBJECTID.eq(t.getEffortObjectID()),
					EFFORTTABLE.EFFORT_PRODUCT.eq(t.getEffortProduct()),
					EFFORTTABLE.EFFORT_PROJECT.eq(t.getEffortProject()),
					EFFORTTABLE.EFFORT_ACCOUNT.eq(t.getEffortAccount()),
					EFFORTTABLE.EFFORT_WORK.eq(t.getEffortWork()),
					EFFORTTABLE.EFFORT_DATE.eq(t.getEffortDate()),
					EFFORTTABLE.EFFORT_LEFT.eq(t.getEffortLeft()),
					EFFORTTABLE.EFFORT_CONSUMED.eq(t.getEffortConsumed()),
					EFFORTTABLE.EFFORT_BEGIN.eq(t.getEffortBegin()),
					EFFORTTABLE.EFFORT_END.eq(t.getEffortEnd())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<Effort> queryPager(int start,int limit ,Effort effort ,final OrderBy... orderBies) {
		if(effort==null){
			effort=new Effort();
		}
		return getDslTemplate().queryPager(start, limit, effort, false, new SelectGenerateCallback<Effort>() {

			public Select generate(Effort t) {
				Select select = MysqlSelect.selectFrom(EFFORTTABLE).where(
				and(
					EFFORTTABLE.EFFORT_OBJECTTYPE.eq(t.getEffortObjectType()),
					EFFORTTABLE.EFFORT_OBJECTID.eq(t.getEffortObjectID()),
					EFFORTTABLE.EFFORT_PRODUCT.eq(t.getEffortProduct()),
					EFFORTTABLE.EFFORT_PROJECT.eq(t.getEffortProject()),
					EFFORTTABLE.EFFORT_ACCOUNT.eq(t.getEffortAccount()),
					EFFORTTABLE.EFFORT_WORK.eq(t.getEffortWork()),
					EFFORTTABLE.EFFORT_DATE.eq(t.getEffortDate()),
					EFFORTTABLE.EFFORT_LEFT.eq(t.getEffortLeft()),
					EFFORTTABLE.EFFORT_CONSUMED.eq(t.getEffortConsumed()),
					EFFORTTABLE.EFFORT_BEGIN.eq(t.getEffortBegin()),
					EFFORTTABLE.EFFORT_END.eq(t.getEffortEnd())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Effort> efforts) {
		if (CollectionUtil.isEmpty(efforts)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, efforts, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(EFFORTTABLE).values(
					EFFORTTABLE.EFFORT_OBJECTTYPE.value(new JdbcNamedParameter("effortObjectType")),
					EFFORTTABLE.EFFORT_OBJECTID.value(new JdbcNamedParameter("effortObjectID")),
					EFFORTTABLE.EFFORT_PRODUCT.value(new JdbcNamedParameter("effortProduct")),
					EFFORTTABLE.EFFORT_PROJECT.value(new JdbcNamedParameter("effortProject")),
					EFFORTTABLE.EFFORT_ACCOUNT.value(new JdbcNamedParameter("effortAccount")),
					EFFORTTABLE.EFFORT_WORK.value(new JdbcNamedParameter("effortWork")),
					EFFORTTABLE.EFFORT_DATE.value(new JdbcNamedParameter("effortDate")),
					EFFORTTABLE.EFFORT_LEFT.value(new JdbcNamedParameter("effortLeft")),
					EFFORTTABLE.EFFORT_CONSUMED.value(new JdbcNamedParameter("effortConsumed")),
					EFFORTTABLE.EFFORT_BEGIN.value(new JdbcNamedParameter("effortBegin")),
					EFFORTTABLE.EFFORT_END.value(new JdbcNamedParameter("effortEnd")));
			}
		});
	}

	public int[] batchInsert(List<Effort> efforts){
			return batchInsert(true ,efforts);
	}

	public int[] batchUpdate(List<Effort> efforts) {
		if (CollectionUtil.isEmpty(efforts)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(efforts, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(EFFORTTABLE).set(
					EFFORTTABLE.EFFORT_OBJECTTYPE.value(new JdbcNamedParameter("effortObjectType")),
					EFFORTTABLE.EFFORT_OBJECTID.value(new JdbcNamedParameter("effortObjectID")),
					EFFORTTABLE.EFFORT_PRODUCT.value(new JdbcNamedParameter("effortProduct")),
					EFFORTTABLE.EFFORT_PROJECT.value(new JdbcNamedParameter("effortProject")),
					EFFORTTABLE.EFFORT_ACCOUNT.value(new JdbcNamedParameter("effortAccount")),
					EFFORTTABLE.EFFORT_WORK.value(new JdbcNamedParameter("effortWork")),
					EFFORTTABLE.EFFORT_DATE.value(new JdbcNamedParameter("effortDate")),
					EFFORTTABLE.EFFORT_LEFT.value(new JdbcNamedParameter("effortLeft")),
					EFFORTTABLE.EFFORT_CONSUMED.value(new JdbcNamedParameter("effortConsumed")),
					EFFORTTABLE.EFFORT_BEGIN.value(new JdbcNamedParameter("effortBegin")),
					EFFORTTABLE.EFFORT_END.value(new JdbcNamedParameter("effortEnd"))).where(
				EFFORTTABLE.EFFORT_ID.eq(new JdbcNamedParameter("effortId")));
			}
		});
	}

	public int[] batchDelete(List<Effort> efforts) {
		if (CollectionUtil.isEmpty(efforts)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(efforts, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(EFFORTTABLE).where(and(
				EFFORTTABLE.EFFORT_ID.eq(new JdbcNamedParameter("effortId")),
				EFFORTTABLE.EFFORT_OBJECTTYPE.eq(new JdbcNamedParameter("effortObjectType")),
				EFFORTTABLE.EFFORT_OBJECTID.eq(new JdbcNamedParameter("effortObjectID")),
				EFFORTTABLE.EFFORT_PRODUCT.eq(new JdbcNamedParameter("effortProduct")),
				EFFORTTABLE.EFFORT_PROJECT.eq(new JdbcNamedParameter("effortProject")),
				EFFORTTABLE.EFFORT_ACCOUNT.eq(new JdbcNamedParameter("effortAccount")),
				EFFORTTABLE.EFFORT_WORK.eq(new JdbcNamedParameter("effortWork")),
				EFFORTTABLE.EFFORT_DATE.eq(new JdbcNamedParameter("effortDate")),
				EFFORTTABLE.EFFORT_LEFT.eq(new JdbcNamedParameter("effortLeft")),
				EFFORTTABLE.EFFORT_CONSUMED.eq(new JdbcNamedParameter("effortConsumed")),
				EFFORTTABLE.EFFORT_BEGIN.eq(new JdbcNamedParameter("effortBegin")),
				EFFORTTABLE.EFFORT_END.eq(new JdbcNamedParameter("effortEnd"))));
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
