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

import static org.tinygroup.sdpm.system.dao.constant.SystemActionTable.SYSTEM_ACTIONTABLE;
import static org.tinygroup.sdpm.org.dao.constant.OrgUserTable.ORG_USERTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.select;
import static org.tinygroup.tinysqldsl.select.Join.leftJoin;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.system.dao.SystemActionDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.base.FragmentSql;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;
@Repository

public class SystemActionDaoImpl extends TinyDslDaoSupport implements SystemActionDao {

	public SystemAction add(SystemAction systemAction) {
		return getDslTemplate().insertAndReturnKey(systemAction, new InsertGenerateCallback<SystemAction>() {
			public Insert generate(SystemAction t) {
				Insert insert = insertInto(SYSTEM_ACTIONTABLE).values(
					SYSTEM_ACTIONTABLE.ACTION_ID.value(t.getActionId()),
					SYSTEM_ACTIONTABLE.ACTION_OBJECT_TYPE.value(t.getActionObjectType()),
					SYSTEM_ACTIONTABLE.ACTION_OBJECT_ID.value(t.getActionObjectId()),
					SYSTEM_ACTIONTABLE.ACTION_PROJECT.value(t.getActionProject()),
					SYSTEM_ACTIONTABLE.ACTION_PRODUCT.value(t.getActionProduct()),
					SYSTEM_ACTIONTABLE.ACTION_ACTOR.value(t.getActionActor()),
					SYSTEM_ACTIONTABLE.ACTION_DATE.value(t.getActionDate()),
					SYSTEM_ACTIONTABLE.ACTION_COMMENT.value(t.getActionComment()),
					SYSTEM_ACTIONTABLE.ACTION_EXTRA.value(t.getActionExtra()),
					SYSTEM_ACTIONTABLE.ACTION_READ.value(t.getActionRead()),
					SYSTEM_ACTIONTABLE.ACTION_ACTION.value(t.getActionAction()),
					SYSTEM_ACTIONTABLE.ACTION_EFFORTED.value(t.getActionEfforted()));
				return insert;
			}
		});
	}

	public int edit(SystemAction systemAction) {
		if(systemAction == null || systemAction.getActionId() == null){
			return 0;
		}
		return getDslTemplate().update(systemAction, new UpdateGenerateCallback<SystemAction>() {
			public Update generate(SystemAction t) {
				Update update = update(SYSTEM_ACTIONTABLE).set(
					SYSTEM_ACTIONTABLE.ACTION_OBJECT_TYPE.value(t.getActionObjectType()),
					SYSTEM_ACTIONTABLE.ACTION_OBJECT_ID.value(t.getActionObjectId()),
					SYSTEM_ACTIONTABLE.ACTION_PROJECT.value(t.getActionProject()),
					SYSTEM_ACTIONTABLE.ACTION_PRODUCT.value(t.getActionProduct()),
					SYSTEM_ACTIONTABLE.ACTION_ACTOR.value(t.getActionActor()),
					SYSTEM_ACTIONTABLE.ACTION_DATE.value(t.getActionDate()),
					SYSTEM_ACTIONTABLE.ACTION_COMMENT.value(t.getActionComment()),
					SYSTEM_ACTIONTABLE.ACTION_EXTRA.value(t.getActionExtra()),
					SYSTEM_ACTIONTABLE.ACTION_READ.value(t.getActionRead()),
					SYSTEM_ACTIONTABLE.ACTION_ACTION.value(t.getActionAction()),
					SYSTEM_ACTIONTABLE.ACTION_EFFORTED.value(t.getActionEfforted())).where(
					SYSTEM_ACTIONTABLE.ACTION_ID.eq(t.getActionId()));
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
				return delete(SYSTEM_ACTIONTABLE).where(SYSTEM_ACTIONTABLE.ACTION_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(SYSTEM_ACTIONTABLE).where(SYSTEM_ACTIONTABLE.ACTION_ID.in(t));
		}
		},pks);
	}

	public SystemAction getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, SystemAction.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(SYSTEM_ACTIONTABLE).where(SYSTEM_ACTIONTABLE.ACTION_ID.eq(t));
			}
		});
	}

	public List<SystemAction> query(SystemAction systemAction ,final OrderBy... orderBies) {
		if(systemAction==null){
			systemAction=new SystemAction();
		}
		return getDslTemplate().query(systemAction, new SelectGenerateCallback<SystemAction>() {

			@SuppressWarnings("rawtypes")
			public Select generate(SystemAction t) {
				Select select = select(SYSTEM_ACTIONTABLE.ALL,FragmentSql.fragmentSelect("org_user_account actorName")).from(SYSTEM_ACTIONTABLE).join(
						leftJoin(ORG_USERTABLE, ORG_USERTABLE.ORG_USER_ID.eq(SYSTEM_ACTIONTABLE.ACTION_ACTOR))).where(
				and(
					SYSTEM_ACTIONTABLE.ACTION_OBJECT_TYPE.eq(t.getActionObjectType()),
					SYSTEM_ACTIONTABLE.ACTION_OBJECT_ID.eq(t.getActionObjectId()),
					SYSTEM_ACTIONTABLE.ACTION_PROJECT.eq(t.getActionProject()),
					SYSTEM_ACTIONTABLE.ACTION_PRODUCT.eq(t.getActionProduct()),
					SYSTEM_ACTIONTABLE.ACTION_ACTOR.eq(t.getActionActor()),
					SYSTEM_ACTIONTABLE.ACTION_DATE.eq(t.getActionDate()),
					SYSTEM_ACTIONTABLE.ACTION_COMMENT.eq(t.getActionComment()),
					SYSTEM_ACTIONTABLE.ACTION_EXTRA.eq(t.getActionExtra()),
					SYSTEM_ACTIONTABLE.ACTION_READ.eq(t.getActionRead()),
					SYSTEM_ACTIONTABLE.ACTION_ACTION.eq(t.getActionAction()),
					SYSTEM_ACTIONTABLE.ACTION_EFFORTED.eq(t.getActionEfforted())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<SystemAction> queryPager(int start,int limit ,SystemAction systemAction ,final OrderBy... orderBies) {
		if(systemAction==null){
			systemAction=new SystemAction();
		}
		return getDslTemplate().queryPager(start, limit, systemAction, false, new SelectGenerateCallback<SystemAction>() {

			public Select generate(SystemAction t) {
				Select select = select(SYSTEM_ACTIONTABLE.ALL,FragmentSql.fragmentSelect("org_user_account actorName")).from(SYSTEM_ACTIONTABLE).join(
						leftJoin(ORG_USERTABLE, ORG_USERTABLE.ORG_USER_ID.eq(SYSTEM_ACTIONTABLE.ACTION_ACTOR))).where(
				and(
					SYSTEM_ACTIONTABLE.ACTION_OBJECT_TYPE.eq(t.getActionObjectType()),
					SYSTEM_ACTIONTABLE.ACTION_OBJECT_ID.eq(t.getActionObjectId()),
					SYSTEM_ACTIONTABLE.ACTION_PROJECT.eq(t.getActionProject()),
					SYSTEM_ACTIONTABLE.ACTION_PRODUCT.eq(t.getActionProduct()),
					SYSTEM_ACTIONTABLE.ACTION_ACTOR.eq(t.getActionActor()),
					SYSTEM_ACTIONTABLE.ACTION_DATE.eq(t.getActionDate()),
					SYSTEM_ACTIONTABLE.ACTION_COMMENT.eq(t.getActionComment()),
					SYSTEM_ACTIONTABLE.ACTION_EXTRA.eq(t.getActionExtra()),
					SYSTEM_ACTIONTABLE.ACTION_READ.eq(t.getActionRead()),
					SYSTEM_ACTIONTABLE.ACTION_ACTION.eq(t.getActionAction()),
					SYSTEM_ACTIONTABLE.ACTION_EFFORTED.eq(t.getActionEfforted())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<SystemAction> systemActions) {
		if (CollectionUtil.isEmpty(systemActions)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, systemActions, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(SYSTEM_ACTIONTABLE).values(
					SYSTEM_ACTIONTABLE.ACTION_OBJECT_TYPE.value(new JdbcNamedParameter("actionObjectType")),
					SYSTEM_ACTIONTABLE.ACTION_OBJECT_ID.value(new JdbcNamedParameter("actionObjectId")),
					SYSTEM_ACTIONTABLE.ACTION_PROJECT.value(new JdbcNamedParameter("actionProject")),
					SYSTEM_ACTIONTABLE.ACTION_PRODUCT.value(new JdbcNamedParameter("actionProduct")),
					SYSTEM_ACTIONTABLE.ACTION_ACTOR.value(new JdbcNamedParameter("actionActor")),
					SYSTEM_ACTIONTABLE.ACTION_DATE.value(new JdbcNamedParameter("actionDate")),
					SYSTEM_ACTIONTABLE.ACTION_COMMENT.value(new JdbcNamedParameter("actionComment")),
					SYSTEM_ACTIONTABLE.ACTION_EXTRA.value(new JdbcNamedParameter("actionExtra")),
					SYSTEM_ACTIONTABLE.ACTION_READ.value(new JdbcNamedParameter("actionRead")),
					SYSTEM_ACTIONTABLE.ACTION_ACTION.value(new JdbcNamedParameter("actionAction")),
					SYSTEM_ACTIONTABLE.ACTION_EFFORTED.value(new JdbcNamedParameter("actionEfforted")));
			}
		});
	}

	public int[] batchInsert(List<SystemAction> systemActions){
			return batchInsert(true ,systemActions);
	}

	public int[] batchUpdate(List<SystemAction> systemActions) {
		if (CollectionUtil.isEmpty(systemActions)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(systemActions, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(SYSTEM_ACTIONTABLE).set(
					SYSTEM_ACTIONTABLE.ACTION_OBJECT_TYPE.value(new JdbcNamedParameter("actionObjectType")),
					SYSTEM_ACTIONTABLE.ACTION_OBJECT_ID.value(new JdbcNamedParameter("actionObjectId")),
					SYSTEM_ACTIONTABLE.ACTION_PROJECT.value(new JdbcNamedParameter("actionProject")),
					SYSTEM_ACTIONTABLE.ACTION_PRODUCT.value(new JdbcNamedParameter("actionProduct")),
					SYSTEM_ACTIONTABLE.ACTION_ACTOR.value(new JdbcNamedParameter("actionActor")),
					SYSTEM_ACTIONTABLE.ACTION_DATE.value(new JdbcNamedParameter("actionDate")),
					SYSTEM_ACTIONTABLE.ACTION_COMMENT.value(new JdbcNamedParameter("actionComment")),
					SYSTEM_ACTIONTABLE.ACTION_EXTRA.value(new JdbcNamedParameter("actionExtra")),
					SYSTEM_ACTIONTABLE.ACTION_READ.value(new JdbcNamedParameter("actionRead")),
					SYSTEM_ACTIONTABLE.ACTION_ACTION.value(new JdbcNamedParameter("actionAction")),
					SYSTEM_ACTIONTABLE.ACTION_EFFORTED.value(new JdbcNamedParameter("actionEfforted"))).where(
				SYSTEM_ACTIONTABLE.ACTION_ID.eq(new JdbcNamedParameter("actionId")));
			}
		});
	}

	public int[] batchDelete(List<SystemAction> systemActions) {
		if (CollectionUtil.isEmpty(systemActions)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(systemActions, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(SYSTEM_ACTIONTABLE).where(and(
				SYSTEM_ACTIONTABLE.ACTION_ID.eq(new JdbcNamedParameter("actionId")),
				SYSTEM_ACTIONTABLE.ACTION_OBJECT_TYPE.eq(new JdbcNamedParameter("actionObjectType")),
				SYSTEM_ACTIONTABLE.ACTION_OBJECT_ID.eq(new JdbcNamedParameter("actionObjectId")),
				SYSTEM_ACTIONTABLE.ACTION_PROJECT.eq(new JdbcNamedParameter("actionProject")),
				SYSTEM_ACTIONTABLE.ACTION_PRODUCT.eq(new JdbcNamedParameter("actionProduct")),
				SYSTEM_ACTIONTABLE.ACTION_ACTOR.eq(new JdbcNamedParameter("actionActor")),
				SYSTEM_ACTIONTABLE.ACTION_DATE.eq(new JdbcNamedParameter("actionDate")),
				SYSTEM_ACTIONTABLE.ACTION_COMMENT.eq(new JdbcNamedParameter("actionComment")),
				SYSTEM_ACTIONTABLE.ACTION_EXTRA.eq(new JdbcNamedParameter("actionExtra")),
				SYSTEM_ACTIONTABLE.ACTION_READ.eq(new JdbcNamedParameter("actionRead")),
				SYSTEM_ACTIONTABLE.ACTION_ACTION.eq(new JdbcNamedParameter("actionAction")),
				SYSTEM_ACTIONTABLE.ACTION_EFFORTED.eq(new JdbcNamedParameter("actionEfforted"))));
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

	public SystemAction getActionAndObject(final SystemAction systemAction) {

		Select select = select(FragmentSql.fragmentSelect("a.*, "+ActionEnum.getName(systemAction.getActionObjectType())+" as objectName"))
				.from(FragmentSql.fragmentFrom(
						"system_action a JOIN "+ActionEnum.getTable(systemAction.getActionObjectType())
								+" b ON a.action_object_id = b."+ActionEnum.getPrimary(systemAction.getActionObjectType())))
				.where(FragmentSql.fragmentCondition("a.action_id="+systemAction.getActionId()));

		return getDslSession().fetchOneResult(select,SystemAction.class);
	}
	
	public Pager<SystemAction> queryPager(int start,int limit ,final Condition dateCondition,SystemAction systemAction ,final OrderBy... orderBies) {
		if(systemAction==null){
			systemAction=new SystemAction();
		}
		return getDslTemplate().queryPager(start, limit, systemAction, false, new SelectGenerateCallback<SystemAction>() {

			public Select generate(SystemAction t) {
				Select select = select(SYSTEM_ACTIONTABLE.ALL,FragmentSql.fragmentSelect("org_user_account actorName")).from(SYSTEM_ACTIONTABLE).join(
						leftJoin(ORG_USERTABLE, ORG_USERTABLE.ORG_USER_ID.eq(SYSTEM_ACTIONTABLE.ACTION_ACTOR))).where(
				and(
					SYSTEM_ACTIONTABLE.ACTION_OBJECT_TYPE.eq(t.getActionObjectType()),
					SYSTEM_ACTIONTABLE.ACTION_OBJECT_ID.eq(t.getActionObjectId()),
					SYSTEM_ACTIONTABLE.ACTION_PROJECT.eq(t.getActionProject()),
					SYSTEM_ACTIONTABLE.ACTION_PRODUCT.eq(t.getActionProduct()),
					SYSTEM_ACTIONTABLE.ACTION_ACTOR.eq(t.getActionActor()),
					dateCondition==null?SYSTEM_ACTIONTABLE.ACTION_DATE.eq(t.getActionDate()):dateCondition,
					SYSTEM_ACTIONTABLE.ACTION_COMMENT.eq(t.getActionComment()),
					SYSTEM_ACTIONTABLE.ACTION_EXTRA.eq(t.getActionExtra()),
					SYSTEM_ACTIONTABLE.ACTION_READ.eq(t.getActionRead()),
					SYSTEM_ACTIONTABLE.ACTION_ACTION.eq(t.getActionAction()),
					SYSTEM_ACTIONTABLE.ACTION_EFFORTED.eq(t.getActionEfforted())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<SystemAction> findByDate(int start, int limit,
			SystemAction action, final String startDate, final String endDate,
			final OrderBy... orderArgs) {
		if(action==null){
			action=new SystemAction();
		}
		return getDslTemplate().queryPager(start, limit, action, false, new SelectGenerateCallback<SystemAction>() {

			public Select generate(SystemAction t) {
				Select select = select(SYSTEM_ACTIONTABLE.ALL,FragmentSql.fragmentSelect("org_user_account actorName")).from(SYSTEM_ACTIONTABLE).join(
						leftJoin(ORG_USERTABLE, ORG_USERTABLE.ORG_USER_ID.eq(SYSTEM_ACTIONTABLE.ACTION_ACTOR))).where(
				and(
					SYSTEM_ACTIONTABLE.ACTION_OBJECT_TYPE.eq(t.getActionObjectType()),
					SYSTEM_ACTIONTABLE.ACTION_OBJECT_ID.eq(t.getActionObjectId()),
					SYSTEM_ACTIONTABLE.ACTION_PROJECT.eq(t.getActionProject()),
					SYSTEM_ACTIONTABLE.ACTION_PRODUCT.eq(t.getActionProduct()),
					SYSTEM_ACTIONTABLE.ACTION_ACTOR.eq(t.getActionActor()),
					
					startDate!=null||endDate!=null?SYSTEM_ACTIONTABLE.ACTION_DATE.between(startDate, endDate):
					
						SYSTEM_ACTIONTABLE.ACTION_DATE.eq(t.getActionDate()),
					
					SYSTEM_ACTIONTABLE.ACTION_COMMENT.eq(t.getActionComment()),
					SYSTEM_ACTIONTABLE.ACTION_EXTRA.eq(t.getActionExtra()),
					SYSTEM_ACTIONTABLE.ACTION_READ.eq(t.getActionRead()),
					SYSTEM_ACTIONTABLE.ACTION_ACTION.eq(t.getActionAction()),
					SYSTEM_ACTIONTABLE.ACTION_EFFORTED.eq(t.getActionEfforted())));
		return addOrderByElements(select, orderArgs);
			}
		});
	}

}
