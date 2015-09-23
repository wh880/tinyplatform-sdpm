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
import static org.tinygroup.sdpm.system.dao.constant.SystemSearchTable.*;
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
import org.tinygroup.sdpm.system.dao.pojo.SystemSearch;
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.sdpm.system.dao.SystemSearchDao;
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
@LogClass("systemSearch")
public class SystemSearchDaoImpl extends TinyDslDaoSupport implements SystemSearchDao {

	@LogMethod("add")
	public SystemSearch add(SystemSearch systemSearch) {
		return getDslTemplate().insertAndReturnKey(systemSearch, new InsertGenerateCallback<SystemSearch>() {
			public Insert generate(SystemSearch t) {
				Insert insert = insertInto(SYSTEM_SEARCHTABLE).values(
					SYSTEM_SEARCHTABLE.SEARCH_ID.value(t.getSearchId()),
					SYSTEM_SEARCHTABLE.SEARCH_OBJECTTYPE.value(t.getSearchObjectType()),
					SYSTEM_SEARCHTABLE.SEARCH_OBJECTID.value(t.getSearchObjectID()),
					SYSTEM_SEARCHTABLE.SEARCH_TITLE.value(t.getSearchTitle()),
					SYSTEM_SEARCHTABLE.SEARCH_CONTENT.value(t.getSearchContent()),
					SYSTEM_SEARCHTABLE.SEARCH_ADDEDDATE.value(t.getSearchAddedDate()),
					SYSTEM_SEARCHTABLE.SEARCH_EDITEDDATE.value(t.getSearchEditedDate()),
					SYSTEM_SEARCHTABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}

	@LogMethod("edit")
	public int edit(SystemSearch systemSearch) {
		if(systemSearch == null || systemSearch.getSearchId() == null){
			return 0;
		}
		return getDslTemplate().update(systemSearch, new UpdateGenerateCallback<SystemSearch>() {
			public Update generate(SystemSearch t) {
				Update update = update(SYSTEM_SEARCHTABLE).set(
					SYSTEM_SEARCHTABLE.SEARCH_OBJECTTYPE.value(t.getSearchObjectType()),
					SYSTEM_SEARCHTABLE.SEARCH_OBJECTID.value(t.getSearchObjectID()),
					SYSTEM_SEARCHTABLE.SEARCH_TITLE.value(t.getSearchTitle()),
					SYSTEM_SEARCHTABLE.SEARCH_CONTENT.value(t.getSearchContent()),
					SYSTEM_SEARCHTABLE.SEARCH_ADDEDDATE.value(t.getSearchAddedDate()),
					SYSTEM_SEARCHTABLE.SEARCH_EDITEDDATE.value(t.getSearchEditedDate()),
					SYSTEM_SEARCHTABLE.DELETED.value(t.getDeleted())).where(
					SYSTEM_SEARCHTABLE.SEARCH_ID.eq(t.getSearchId()));
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
				return delete(SYSTEM_SEARCHTABLE).where(SYSTEM_SEARCHTABLE.SEARCH_ID.eq(pk));
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
				return delete(SYSTEM_SEARCHTABLE).where(SYSTEM_SEARCHTABLE.SEARCH_ID.in(t));
		}
		},pks);
	}

	public SystemSearch getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, SystemSearch.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(SYSTEM_SEARCHTABLE).where(SYSTEM_SEARCHTABLE.SEARCH_ID.eq(t));
			}
		});
	}

	public List<SystemSearch> query(SystemSearch systemSearch ,final OrderBy... orderBies) {
		if(systemSearch==null){
			systemSearch=new SystemSearch();
		}
		return getDslTemplate().query(systemSearch, new SelectGenerateCallback<SystemSearch>() {

			@SuppressWarnings("rawtypes")
			public Select generate(SystemSearch t) {
				Select select = selectFrom(SYSTEM_SEARCHTABLE).where(
				and(
					SYSTEM_SEARCHTABLE.SEARCH_OBJECTTYPE.eq(t.getSearchObjectType()),
					SYSTEM_SEARCHTABLE.SEARCH_OBJECTID.eq(t.getSearchObjectID()),
					SYSTEM_SEARCHTABLE.SEARCH_TITLE.eq(t.getSearchTitle()),
					SYSTEM_SEARCHTABLE.SEARCH_CONTENT.eq(t.getSearchContent()),
					SYSTEM_SEARCHTABLE.SEARCH_ADDEDDATE.eq(t.getSearchAddedDate()),
					SYSTEM_SEARCHTABLE.SEARCH_EDITEDDATE.eq(t.getSearchEditedDate()),
					SYSTEM_SEARCHTABLE.DELETED.eq(t.getDeleted())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<SystemSearch> queryPager(int start,int limit ,SystemSearch systemSearch ,final OrderBy... orderBies) {
		if(systemSearch==null){
			systemSearch=new SystemSearch();
		}
		return getDslTemplate().queryPager(start, limit, systemSearch, false, new SelectGenerateCallback<SystemSearch>() {

			public Select generate(SystemSearch t) {
				Select select = MysqlSelect.selectFrom(SYSTEM_SEARCHTABLE).where(
				and(
					SYSTEM_SEARCHTABLE.SEARCH_OBJECTTYPE.eq(t.getSearchObjectType()),
					SYSTEM_SEARCHTABLE.SEARCH_OBJECTID.eq(t.getSearchObjectID()),
					SYSTEM_SEARCHTABLE.SEARCH_TITLE.eq(t.getSearchTitle()),
					SYSTEM_SEARCHTABLE.SEARCH_CONTENT.eq(t.getSearchContent()),
					SYSTEM_SEARCHTABLE.SEARCH_ADDEDDATE.eq(t.getSearchAddedDate()),
					SYSTEM_SEARCHTABLE.SEARCH_EDITEDDATE.eq(t.getSearchEditedDate()),
					SYSTEM_SEARCHTABLE.DELETED.eq(t.getDeleted())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<SystemSearch> systemSearchs) {
		if (CollectionUtil.isEmpty(systemSearchs)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, systemSearchs, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(SYSTEM_SEARCHTABLE).values(
					SYSTEM_SEARCHTABLE.SEARCH_OBJECTTYPE.value(new JdbcNamedParameter("searchObjectType")),
					SYSTEM_SEARCHTABLE.SEARCH_OBJECTID.value(new JdbcNamedParameter("searchObjectID")),
					SYSTEM_SEARCHTABLE.SEARCH_TITLE.value(new JdbcNamedParameter("searchTitle")),
					SYSTEM_SEARCHTABLE.SEARCH_CONTENT.value(new JdbcNamedParameter("searchContent")),
					SYSTEM_SEARCHTABLE.SEARCH_ADDEDDATE.value(new JdbcNamedParameter("searchAddedDate")),
					SYSTEM_SEARCHTABLE.SEARCH_EDITEDDATE.value(new JdbcNamedParameter("searchEditedDate")),
					SYSTEM_SEARCHTABLE.DELETED.value(new JdbcNamedParameter("deleted")));
			}
		});
	}

	public int[] batchInsert(List<SystemSearch> systemSearchs){
			return batchInsert(true ,systemSearchs);
	}

	@LogMethod("batchUpdate")
	public int[] batchUpdate(List<SystemSearch> systemSearchs) {
		if (CollectionUtil.isEmpty(systemSearchs)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(systemSearchs, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(SYSTEM_SEARCHTABLE).set(
					SYSTEM_SEARCHTABLE.SEARCH_OBJECTTYPE.value(new JdbcNamedParameter("searchObjectType")),
					SYSTEM_SEARCHTABLE.SEARCH_OBJECTID.value(new JdbcNamedParameter("searchObjectID")),
					SYSTEM_SEARCHTABLE.SEARCH_TITLE.value(new JdbcNamedParameter("searchTitle")),
					SYSTEM_SEARCHTABLE.SEARCH_CONTENT.value(new JdbcNamedParameter("searchContent")),
					SYSTEM_SEARCHTABLE.SEARCH_ADDEDDATE.value(new JdbcNamedParameter("searchAddedDate")),
					SYSTEM_SEARCHTABLE.SEARCH_EDITEDDATE.value(new JdbcNamedParameter("searchEditedDate")),
					SYSTEM_SEARCHTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
				SYSTEM_SEARCHTABLE.SEARCH_ID.eq(new JdbcNamedParameter("searchId")));
			}
		});
	}

	@LogMethod("batchDelete")
	public int[] batchDelete(List<SystemSearch> systemSearchs) {
		if (CollectionUtil.isEmpty(systemSearchs)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(systemSearchs, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(SYSTEM_SEARCHTABLE).where(and(
				SYSTEM_SEARCHTABLE.SEARCH_ID.eq(new JdbcNamedParameter("searchId")),
				SYSTEM_SEARCHTABLE.SEARCH_OBJECTTYPE.eq(new JdbcNamedParameter("searchObjectType")),
				SYSTEM_SEARCHTABLE.SEARCH_OBJECTID.eq(new JdbcNamedParameter("searchObjectID")),
				SYSTEM_SEARCHTABLE.SEARCH_TITLE.eq(new JdbcNamedParameter("searchTitle")),
				SYSTEM_SEARCHTABLE.SEARCH_CONTENT.eq(new JdbcNamedParameter("searchContent")),
				SYSTEM_SEARCHTABLE.SEARCH_ADDEDDATE.eq(new JdbcNamedParameter("searchAddedDate")),
				SYSTEM_SEARCHTABLE.SEARCH_EDITEDDATE.eq(new JdbcNamedParameter("searchEditedDate")),
				SYSTEM_SEARCHTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
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
