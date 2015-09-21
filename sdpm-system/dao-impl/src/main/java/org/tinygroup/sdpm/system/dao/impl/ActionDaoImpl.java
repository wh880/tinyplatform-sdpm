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
import static org.tinygroup.sdpm.system.dao.constant.ActionTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
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
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.sdpm.system.dao.pojo.Action;
import org.tinygroup.sdpm.system.dao.ActionDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;
@Component
@LogClass("system")
public class ActionDaoImpl extends TinyDslDaoSupport implements ActionDao {
    @LogMethod("add")
	public Action add(Action action) {
		return getDslTemplate().insertAndReturnKey(action, new InsertGenerateCallback<Action>() {
			public Insert generate(Action t) {
				Insert insert = insertInto(ACTIONTABLE).values(
					ACTIONTABLE.ACTION_ID.value(t.getActionId()),
					ACTIONTABLE.ACTION_OBJECTTYPE.value(t.getActionObjectType()),
					ACTIONTABLE.ACTION_OBJECTID.value(t.getActionObjectID()),
					ACTIONTABLE.ACTION_PROJECT.value(t.getActionProject()),
					ACTIONTABLE.ACTION_PRODUCT.value(t.getActionProduct()),
					ACTIONTABLE.ACTION_ACTOR.value(t.getActionActor()),
					ACTIONTABLE.ACTION_DATE.value(t.getActionDate()),
					ACTIONTABLE.ACTION_COMMENT.value(t.getActionComment()),
					ACTIONTABLE.ACTION_EXTRA.value(t.getActionExtra()),
					ACTIONTABLE.ACTION_READ.value(t.getActionRead()),
					ACTIONTABLE.ACTION_EFFORTED.value(t.getActionEfforted()));
				return insert;
			}
		});
	}
    @LogMethod("edit")
	public int edit(Action action) {
		if(action == null || action.getActionId() == null){
			return 0;
		}
		return getDslTemplate().update(action, new UpdateGenerateCallback<Action>() {
			public Update generate(Action t) {
				Update update = update(ACTIONTABLE).set(
					ACTIONTABLE.ACTION_OBJECTTYPE.value(t.getActionObjectType()),
					ACTIONTABLE.ACTION_OBJECTID.value(t.getActionObjectID()),
					ACTIONTABLE.ACTION_PROJECT.value(t.getActionProject()),
					ACTIONTABLE.ACTION_PRODUCT.value(t.getActionProduct()),
					ACTIONTABLE.ACTION_ACTOR.value(t.getActionActor()),
					ACTIONTABLE.ACTION_DATE.value(t.getActionDate()),
					ACTIONTABLE.ACTION_COMMENT.value(t.getActionComment()),
					ACTIONTABLE.ACTION_EXTRA.value(t.getActionExtra()),
					ACTIONTABLE.ACTION_READ.value(t.getActionRead()),
					ACTIONTABLE.ACTION_EFFORTED.value(t.getActionEfforted())).where(
					ACTIONTABLE.ACTION_ID.eq(t.getActionId()));
				return update;
			}
		});
	}
    @LogMethod("delete")
	public int deleteByKey(Integer pk){
		if(pk == null){
			return 0;
		}
		return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(ACTIONTABLE).where(ACTIONTABLE.ACTION_ID.eq(pk));
			}
		});
	}
    @LogMethod("delete")
	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(ACTIONTABLE).where(ACTIONTABLE.ACTION_ID.in(t));
		}
		},pks);
	}

	public Action getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Action.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(ACTIONTABLE).where(ACTIONTABLE.ACTION_ID.eq(t));
			}
		});
	}

	public List<Action> query(Action action ,final OrderBy... orderBies) {
		if(action==null){
			action=new Action();
		}
		return getDslTemplate().query(action, new SelectGenerateCallback<Action>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Action t) {
				Select select = selectFrom(ACTIONTABLE).where(
				and(
					ACTIONTABLE.ACTION_OBJECTTYPE.eq(t.getActionObjectType()),
					ACTIONTABLE.ACTION_OBJECTID.eq(t.getActionObjectID()),
					ACTIONTABLE.ACTION_PROJECT.eq(t.getActionProject()),
					ACTIONTABLE.ACTION_PRODUCT.eq(t.getActionProduct()),
					ACTIONTABLE.ACTION_ACTOR.eq(t.getActionActor()),
					ACTIONTABLE.ACTION_DATE.eq(t.getActionDate()),
					ACTIONTABLE.ACTION_COMMENT.eq(t.getActionComment()),
					ACTIONTABLE.ACTION_EXTRA.eq(t.getActionExtra()),
					ACTIONTABLE.ACTION_READ.eq(t.getActionRead()),
					ACTIONTABLE.ACTION_EFFORTED.eq(t.getActionEfforted())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<Action> queryPager(int start,int limit ,Action action ,final OrderBy... orderBies) {
		if(action==null){
			action=new Action();
		}
		return getDslTemplate().queryPager(start, limit, action, false, new SelectGenerateCallback<Action>() {

			public Select generate(Action t) {
				Select select = MysqlSelect.selectFrom(ACTIONTABLE).where(
				and(
					ACTIONTABLE.ACTION_OBJECTTYPE.eq(t.getActionObjectType()),
					ACTIONTABLE.ACTION_OBJECTID.eq(t.getActionObjectID()),
					ACTIONTABLE.ACTION_PROJECT.eq(t.getActionProject()),
					ACTIONTABLE.ACTION_PRODUCT.eq(t.getActionProduct()),
					ACTIONTABLE.ACTION_ACTOR.eq(t.getActionActor()),
					ACTIONTABLE.ACTION_DATE.eq(t.getActionDate()),
					ACTIONTABLE.ACTION_COMMENT.eq(t.getActionComment()),
					ACTIONTABLE.ACTION_EXTRA.eq(t.getActionExtra()),
					ACTIONTABLE.ACTION_READ.eq(t.getActionRead()),
					ACTIONTABLE.ACTION_EFFORTED.eq(t.getActionEfforted())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Action> actions) {
		if (CollectionUtil.isEmpty(actions)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, actions, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(ACTIONTABLE).values(
					ACTIONTABLE.ACTION_OBJECTTYPE.value(new JdbcNamedParameter("actionObjectType")),
					ACTIONTABLE.ACTION_OBJECTID.value(new JdbcNamedParameter("actionObjectID")),
					ACTIONTABLE.ACTION_PROJECT.value(new JdbcNamedParameter("actionProject")),
					ACTIONTABLE.ACTION_PRODUCT.value(new JdbcNamedParameter("actionProduct")),
					ACTIONTABLE.ACTION_ACTOR.value(new JdbcNamedParameter("actionActor")),
					ACTIONTABLE.ACTION_DATE.value(new JdbcNamedParameter("actionDate")),
					ACTIONTABLE.ACTION_COMMENT.value(new JdbcNamedParameter("actionComment")),
					ACTIONTABLE.ACTION_EXTRA.value(new JdbcNamedParameter("actionExtra")),
					ACTIONTABLE.ACTION_READ.value(new JdbcNamedParameter("actionRead")),
					ACTIONTABLE.ACTION_EFFORTED.value(new JdbcNamedParameter("actionEfforted")));
			}
		});
	}

	public int[] batchInsert(List<Action> actions){
			return batchInsert(true ,actions);
	}

	public int[] batchUpdate(List<Action> actions) {
		if (CollectionUtil.isEmpty(actions)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(actions, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(ACTIONTABLE).set(
					ACTIONTABLE.ACTION_OBJECTTYPE.value(new JdbcNamedParameter("actionObjectType")),
					ACTIONTABLE.ACTION_OBJECTID.value(new JdbcNamedParameter("actionObjectID")),
					ACTIONTABLE.ACTION_PROJECT.value(new JdbcNamedParameter("actionProject")),
					ACTIONTABLE.ACTION_PRODUCT.value(new JdbcNamedParameter("actionProduct")),
					ACTIONTABLE.ACTION_ACTOR.value(new JdbcNamedParameter("actionActor")),
					ACTIONTABLE.ACTION_DATE.value(new JdbcNamedParameter("actionDate")),
					ACTIONTABLE.ACTION_COMMENT.value(new JdbcNamedParameter("actionComment")),
					ACTIONTABLE.ACTION_EXTRA.value(new JdbcNamedParameter("actionExtra")),
					ACTIONTABLE.ACTION_READ.value(new JdbcNamedParameter("actionRead")),
					ACTIONTABLE.ACTION_EFFORTED.value(new JdbcNamedParameter("actionEfforted"))).where(
				ACTIONTABLE.ACTION_ID.eq(new JdbcNamedParameter("actionId")));
			}
		});
	}

	public int[] batchDelete(List<Action> actions) {
		if (CollectionUtil.isEmpty(actions)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(actions, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(ACTIONTABLE).where(and(
				ACTIONTABLE.ACTION_ID.eq(new JdbcNamedParameter("actionId")),
				ACTIONTABLE.ACTION_OBJECTTYPE.eq(new JdbcNamedParameter("actionObjectType")),
				ACTIONTABLE.ACTION_OBJECTID.eq(new JdbcNamedParameter("actionObjectID")),
				ACTIONTABLE.ACTION_PROJECT.eq(new JdbcNamedParameter("actionProject")),
				ACTIONTABLE.ACTION_PRODUCT.eq(new JdbcNamedParameter("actionProduct")),
				ACTIONTABLE.ACTION_ACTOR.eq(new JdbcNamedParameter("actionActor")),
				ACTIONTABLE.ACTION_DATE.eq(new JdbcNamedParameter("actionDate")),
				ACTIONTABLE.ACTION_COMMENT.eq(new JdbcNamedParameter("actionComment")),
				ACTIONTABLE.ACTION_EXTRA.eq(new JdbcNamedParameter("actionExtra")),
				ACTIONTABLE.ACTION_READ.eq(new JdbcNamedParameter("actionRead")),
				ACTIONTABLE.ACTION_EFFORTED.eq(new JdbcNamedParameter("actionEfforted"))));
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
