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
import static org.tinygroup.sdpm.system.dao.constant.SystemEffortTable.*;
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
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;
import org.tinygroup.sdpm.system.dao.SystemEffortDao;
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
@LogClass("systemEffort")
public class SystemEffortDaoImpl extends TinyDslDaoSupport implements SystemEffortDao {

	public SystemEffort add(SystemEffort systemEffort) {
		return getDslTemplate().insertAndReturnKey(systemEffort, new InsertGenerateCallback<SystemEffort>() {
			public Insert generate(SystemEffort t) {
				Insert insert = insertInto(SYSTEM_EFFORTTABLE).values(
					SYSTEM_EFFORTTABLE.EFFORT_ID.value(t.getEffortId()),
					SYSTEM_EFFORTTABLE.EFFORT_OBJECTTYPE.value(t.getEffortObjectType()),
					SYSTEM_EFFORTTABLE.EFFORT_OBJECTID.value(t.getEffortObjectID()),
					SYSTEM_EFFORTTABLE.EFFORT_PRODUCT.value(t.getEffortProduct()),
					SYSTEM_EFFORTTABLE.EFFORT_PROJECT.value(t.getEffortProject()),
					SYSTEM_EFFORTTABLE.EFFORT_ACCOUNT.value(t.getEffortAccount()),
					SYSTEM_EFFORTTABLE.EFFORT_WORK.value(t.getEffortWork()),
					SYSTEM_EFFORTTABLE.EFFORT_DATE.value(t.getEffortDate()),
					SYSTEM_EFFORTTABLE.EFFORT_LEFT.value(t.getEffortLeft()),
					SYSTEM_EFFORTTABLE.EFFORT_CONSUMED.value(t.getEffortConsumed()),
					SYSTEM_EFFORTTABLE.EFFORT_BEGIN.value(t.getEffortBegin()),
					SYSTEM_EFFORTTABLE.EFFORT_END.value(t.getEffortEnd()));
				return insert;
			}
		});
	}

	public int edit(SystemEffort systemEffort) {
		if(systemEffort == null || systemEffort.getEffortId() == null){
			return 0;
		}
		return getDslTemplate().update(systemEffort, new UpdateGenerateCallback<SystemEffort>() {
			public Update generate(SystemEffort t) {
				Update update = update(SYSTEM_EFFORTTABLE).set(
					SYSTEM_EFFORTTABLE.EFFORT_OBJECTTYPE.value(t.getEffortObjectType()),
					SYSTEM_EFFORTTABLE.EFFORT_OBJECTID.value(t.getEffortObjectID()),
					SYSTEM_EFFORTTABLE.EFFORT_PRODUCT.value(t.getEffortProduct()),
					SYSTEM_EFFORTTABLE.EFFORT_PROJECT.value(t.getEffortProject()),
					SYSTEM_EFFORTTABLE.EFFORT_ACCOUNT.value(t.getEffortAccount()),
					SYSTEM_EFFORTTABLE.EFFORT_WORK.value(t.getEffortWork()),
					SYSTEM_EFFORTTABLE.EFFORT_DATE.value(t.getEffortDate()),
					SYSTEM_EFFORTTABLE.EFFORT_LEFT.value(t.getEffortLeft()),
					SYSTEM_EFFORTTABLE.EFFORT_CONSUMED.value(t.getEffortConsumed()),
					SYSTEM_EFFORTTABLE.EFFORT_BEGIN.value(t.getEffortBegin()),
					SYSTEM_EFFORTTABLE.EFFORT_END.value(t.getEffortEnd())).where(
					SYSTEM_EFFORTTABLE.EFFORT_ID.eq(t.getEffortId()));
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
				return delete(SYSTEM_EFFORTTABLE).where(SYSTEM_EFFORTTABLE.EFFORT_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(SYSTEM_EFFORTTABLE).where(SYSTEM_EFFORTTABLE.EFFORT_ID.in(t));
		}
		},pks);
	}

	public SystemEffort getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, SystemEffort.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(SYSTEM_EFFORTTABLE).where(SYSTEM_EFFORTTABLE.EFFORT_ID.eq(t));
			}
		});
	}

	public List<SystemEffort> query(SystemEffort systemEffort ,final OrderBy... orderBies) {
		if(systemEffort==null){
			systemEffort=new SystemEffort();
		}
		return getDslTemplate().query(systemEffort, new SelectGenerateCallback<SystemEffort>() {

			@SuppressWarnings("rawtypes")
			public Select generate(SystemEffort t) {
				Select select = selectFrom(SYSTEM_EFFORTTABLE).where(
				and(
					SYSTEM_EFFORTTABLE.EFFORT_OBJECTTYPE.eq(t.getEffortObjectType()),
					SYSTEM_EFFORTTABLE.EFFORT_OBJECTID.eq(t.getEffortObjectID()),
					SYSTEM_EFFORTTABLE.EFFORT_PRODUCT.eq(t.getEffortProduct()),
					SYSTEM_EFFORTTABLE.EFFORT_PROJECT.eq(t.getEffortProject()),
					SYSTEM_EFFORTTABLE.EFFORT_ACCOUNT.eq(t.getEffortAccount()),
					SYSTEM_EFFORTTABLE.EFFORT_WORK.eq(t.getEffortWork()),
					SYSTEM_EFFORTTABLE.EFFORT_DATE.eq(t.getEffortDate()),
					SYSTEM_EFFORTTABLE.EFFORT_LEFT.eq(t.getEffortLeft()),
					SYSTEM_EFFORTTABLE.EFFORT_CONSUMED.eq(t.getEffortConsumed()),
					SYSTEM_EFFORTTABLE.EFFORT_BEGIN.eq(t.getEffortBegin()),
					SYSTEM_EFFORTTABLE.EFFORT_END.eq(t.getEffortEnd())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<SystemEffort> queryPager(int start,int limit ,SystemEffort systemEffort ,final OrderBy... orderBies) {
		if(systemEffort==null){
			systemEffort=new SystemEffort();
		}
		return getDslTemplate().queryPager(start, limit, systemEffort, false, new SelectGenerateCallback<SystemEffort>() {

			public Select generate(SystemEffort t) {
				Select select = MysqlSelect.selectFrom(SYSTEM_EFFORTTABLE).where(
				and(
					SYSTEM_EFFORTTABLE.EFFORT_OBJECTTYPE.eq(t.getEffortObjectType()),
					SYSTEM_EFFORTTABLE.EFFORT_OBJECTID.eq(t.getEffortObjectID()),
					SYSTEM_EFFORTTABLE.EFFORT_PRODUCT.eq(t.getEffortProduct()),
					SYSTEM_EFFORTTABLE.EFFORT_PROJECT.eq(t.getEffortProject()),
					SYSTEM_EFFORTTABLE.EFFORT_ACCOUNT.eq(t.getEffortAccount()),
					SYSTEM_EFFORTTABLE.EFFORT_WORK.eq(t.getEffortWork()),
					SYSTEM_EFFORTTABLE.EFFORT_DATE.eq(t.getEffortDate()),
					SYSTEM_EFFORTTABLE.EFFORT_LEFT.eq(t.getEffortLeft()),
					SYSTEM_EFFORTTABLE.EFFORT_CONSUMED.eq(t.getEffortConsumed()),
					SYSTEM_EFFORTTABLE.EFFORT_BEGIN.eq(t.getEffortBegin()),
					SYSTEM_EFFORTTABLE.EFFORT_END.eq(t.getEffortEnd())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<SystemEffort> systemEfforts) {
		if (CollectionUtil.isEmpty(systemEfforts)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, systemEfforts, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(SYSTEM_EFFORTTABLE).values(
					SYSTEM_EFFORTTABLE.EFFORT_OBJECTTYPE.value(new JdbcNamedParameter("effortObjectType")),
					SYSTEM_EFFORTTABLE.EFFORT_OBJECTID.value(new JdbcNamedParameter("effortObjectID")),
					SYSTEM_EFFORTTABLE.EFFORT_PRODUCT.value(new JdbcNamedParameter("effortProduct")),
					SYSTEM_EFFORTTABLE.EFFORT_PROJECT.value(new JdbcNamedParameter("effortProject")),
					SYSTEM_EFFORTTABLE.EFFORT_ACCOUNT.value(new JdbcNamedParameter("effortAccount")),
					SYSTEM_EFFORTTABLE.EFFORT_WORK.value(new JdbcNamedParameter("effortWork")),
					SYSTEM_EFFORTTABLE.EFFORT_DATE.value(new JdbcNamedParameter("effortDate")),
					SYSTEM_EFFORTTABLE.EFFORT_LEFT.value(new JdbcNamedParameter("effortLeft")),
					SYSTEM_EFFORTTABLE.EFFORT_CONSUMED.value(new JdbcNamedParameter("effortConsumed")),
					SYSTEM_EFFORTTABLE.EFFORT_BEGIN.value(new JdbcNamedParameter("effortBegin")),
					SYSTEM_EFFORTTABLE.EFFORT_END.value(new JdbcNamedParameter("effortEnd")));
			}
		});
	}

	public int[] batchInsert(List<SystemEffort> systemEfforts){
			return batchInsert(true ,systemEfforts);
	}

	public int[] batchUpdate(List<SystemEffort> systemEfforts) {
		if (CollectionUtil.isEmpty(systemEfforts)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(systemEfforts, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(SYSTEM_EFFORTTABLE).set(
					SYSTEM_EFFORTTABLE.EFFORT_OBJECTTYPE.value(new JdbcNamedParameter("effortObjectType")),
					SYSTEM_EFFORTTABLE.EFFORT_OBJECTID.value(new JdbcNamedParameter("effortObjectID")),
					SYSTEM_EFFORTTABLE.EFFORT_PRODUCT.value(new JdbcNamedParameter("effortProduct")),
					SYSTEM_EFFORTTABLE.EFFORT_PROJECT.value(new JdbcNamedParameter("effortProject")),
					SYSTEM_EFFORTTABLE.EFFORT_ACCOUNT.value(new JdbcNamedParameter("effortAccount")),
					SYSTEM_EFFORTTABLE.EFFORT_WORK.value(new JdbcNamedParameter("effortWork")),
					SYSTEM_EFFORTTABLE.EFFORT_DATE.value(new JdbcNamedParameter("effortDate")),
					SYSTEM_EFFORTTABLE.EFFORT_LEFT.value(new JdbcNamedParameter("effortLeft")),
					SYSTEM_EFFORTTABLE.EFFORT_CONSUMED.value(new JdbcNamedParameter("effortConsumed")),
					SYSTEM_EFFORTTABLE.EFFORT_BEGIN.value(new JdbcNamedParameter("effortBegin")),
					SYSTEM_EFFORTTABLE.EFFORT_END.value(new JdbcNamedParameter("effortEnd"))).where(
				SYSTEM_EFFORTTABLE.EFFORT_ID.eq(new JdbcNamedParameter("effortId")));
			}
		});
	}

	public int[] batchDelete(List<SystemEffort> systemEfforts) {
		if (CollectionUtil.isEmpty(systemEfforts)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(systemEfforts, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(SYSTEM_EFFORTTABLE).where(and(
				SYSTEM_EFFORTTABLE.EFFORT_ID.eq(new JdbcNamedParameter("effortId")),
				SYSTEM_EFFORTTABLE.EFFORT_OBJECTTYPE.eq(new JdbcNamedParameter("effortObjectType")),
				SYSTEM_EFFORTTABLE.EFFORT_OBJECTID.eq(new JdbcNamedParameter("effortObjectID")),
				SYSTEM_EFFORTTABLE.EFFORT_PRODUCT.eq(new JdbcNamedParameter("effortProduct")),
				SYSTEM_EFFORTTABLE.EFFORT_PROJECT.eq(new JdbcNamedParameter("effortProject")),
				SYSTEM_EFFORTTABLE.EFFORT_ACCOUNT.eq(new JdbcNamedParameter("effortAccount")),
				SYSTEM_EFFORTTABLE.EFFORT_WORK.eq(new JdbcNamedParameter("effortWork")),
				SYSTEM_EFFORTTABLE.EFFORT_DATE.eq(new JdbcNamedParameter("effortDate")),
				SYSTEM_EFFORTTABLE.EFFORT_LEFT.eq(new JdbcNamedParameter("effortLeft")),
				SYSTEM_EFFORTTABLE.EFFORT_CONSUMED.eq(new JdbcNamedParameter("effortConsumed")),
				SYSTEM_EFFORTTABLE.EFFORT_BEGIN.eq(new JdbcNamedParameter("effortBegin")),
				SYSTEM_EFFORTTABLE.EFFORT_END.eq(new JdbcNamedParameter("effortEnd"))));
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
