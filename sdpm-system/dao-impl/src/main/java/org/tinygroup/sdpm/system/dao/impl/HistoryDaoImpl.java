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
import static org.tinygroup.sdpm.system.dao.constant.HistoryTable.*;
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
import org.tinygroup.sdpm.system.dao.pojo.History;
import org.tinygroup.sdpm.system.dao.HistoryDao;
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
public class HistoryDaoImpl extends TinyDslDaoSupport implements HistoryDao {

	public History add(History history) {
		return getDslTemplate().insertAndReturnKey(history, new InsertGenerateCallback<History>() {
			public Insert generate(History t) {
				Insert insert = insertInto(HISTORYTABLE).values(
					HISTORYTABLE.HISTORY_ID.value(t.getHistoryId()),
					HISTORYTABLE.HISTORY_ACTION.value(t.getHistoryAction()),
					HISTORYTABLE.HISTORY_FIELD.value(t.getHistoryField()),
					HISTORYTABLE.HISTORY_NEW.value(t.getHistoryNew()),
					HISTORYTABLE.HISTORY_OLD.value(t.getHistoryOld()),
					HISTORYTABLE.HISTORY_DIFF.value(t.getHistoryDiff()));
				return insert;
			}
		});
	}

	public int edit(History history) {
		if(history == null || history.getHistoryId() == null){
			return 0;
		}
		return getDslTemplate().update(history, new UpdateGenerateCallback<History>() {
			public Update generate(History t) {
				Update update = update(HISTORYTABLE).set(
					HISTORYTABLE.HISTORY_ACTION.value(t.getHistoryAction()),
					HISTORYTABLE.HISTORY_FIELD.value(t.getHistoryField()),
					HISTORYTABLE.HISTORY_NEW.value(t.getHistoryNew()),
					HISTORYTABLE.HISTORY_OLD.value(t.getHistoryOld()),
					HISTORYTABLE.HISTORY_DIFF.value(t.getHistoryDiff())).where(
					HISTORYTABLE.HISTORY_ID.eq(t.getHistoryId()));
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
				return delete(HISTORYTABLE).where(HISTORYTABLE.HISTORY_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(HISTORYTABLE).where(HISTORYTABLE.HISTORY_ID.in(t));
		}
		},pks);
	}

	public History getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, History.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(HISTORYTABLE).where(HISTORYTABLE.HISTORY_ID.eq(t));
			}
		});
	}

	public List<History> query(History history ,final OrderBy... orderBies) {
		if(history==null){
			history=new History();
		}
		return getDslTemplate().query(history, new SelectGenerateCallback<History>() {

			@SuppressWarnings("rawtypes")
			public Select generate(History t) {
				Select select = selectFrom(HISTORYTABLE).where(
				and(
					HISTORYTABLE.HISTORY_ACTION.eq(t.getHistoryAction()),
					HISTORYTABLE.HISTORY_FIELD.eq(t.getHistoryField()),
					HISTORYTABLE.HISTORY_NEW.eq(t.getHistoryNew()),
					HISTORYTABLE.HISTORY_OLD.eq(t.getHistoryOld()),
					HISTORYTABLE.HISTORY_DIFF.eq(t.getHistoryDiff())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<History> queryPager(int start,int limit ,History history ,final OrderBy... orderBies) {
		if(history==null){
			history=new History();
		}
		return getDslTemplate().queryPager(start, limit, history, false, new SelectGenerateCallback<History>() {

			public Select generate(History t) {
				Select select = MysqlSelect.selectFrom(HISTORYTABLE).where(
				and(
					HISTORYTABLE.HISTORY_ACTION.eq(t.getHistoryAction()),
					HISTORYTABLE.HISTORY_FIELD.eq(t.getHistoryField()),
					HISTORYTABLE.HISTORY_NEW.eq(t.getHistoryNew()),
					HISTORYTABLE.HISTORY_OLD.eq(t.getHistoryOld()),
					HISTORYTABLE.HISTORY_DIFF.eq(t.getHistoryDiff())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<History> historys) {
		if (CollectionUtil.isEmpty(historys)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, historys, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(HISTORYTABLE).values(
					HISTORYTABLE.HISTORY_ACTION.value(new JdbcNamedParameter("historyAction")),
					HISTORYTABLE.HISTORY_FIELD.value(new JdbcNamedParameter("historyField")),
					HISTORYTABLE.HISTORY_NEW.value(new JdbcNamedParameter("historyNew")),
					HISTORYTABLE.HISTORY_OLD.value(new JdbcNamedParameter("historyOld")),
					HISTORYTABLE.HISTORY_DIFF.value(new JdbcNamedParameter("historyDiff")));
			}
		});
	}

	public int[] batchInsert(List<History> historys){
			return batchInsert(true ,historys);
	}

	public int[] batchUpdate(List<History> historys) {
		if (CollectionUtil.isEmpty(historys)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(historys, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(HISTORYTABLE).set(
					HISTORYTABLE.HISTORY_ACTION.value(new JdbcNamedParameter("historyAction")),
					HISTORYTABLE.HISTORY_FIELD.value(new JdbcNamedParameter("historyField")),
					HISTORYTABLE.HISTORY_NEW.value(new JdbcNamedParameter("historyNew")),
					HISTORYTABLE.HISTORY_OLD.value(new JdbcNamedParameter("historyOld")),
					HISTORYTABLE.HISTORY_DIFF.value(new JdbcNamedParameter("historyDiff"))).where(
				HISTORYTABLE.HISTORY_ID.eq(new JdbcNamedParameter("historyId")));
			}
		});
	}

	public int[] batchDelete(List<History> historys) {
		if (CollectionUtil.isEmpty(historys)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(historys, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(HISTORYTABLE).where(and(
				HISTORYTABLE.HISTORY_ID.eq(new JdbcNamedParameter("historyId")),
				HISTORYTABLE.HISTORY_ACTION.eq(new JdbcNamedParameter("historyAction")),
				HISTORYTABLE.HISTORY_FIELD.eq(new JdbcNamedParameter("historyField")),
				HISTORYTABLE.HISTORY_NEW.eq(new JdbcNamedParameter("historyNew")),
				HISTORYTABLE.HISTORY_OLD.eq(new JdbcNamedParameter("historyOld")),
				HISTORYTABLE.HISTORY_DIFF.eq(new JdbcNamedParameter("historyDiff"))));
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
