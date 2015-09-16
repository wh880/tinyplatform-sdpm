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
import static org.tinygroup.sdpm.system.dao.constant.SearchTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;

import java.util.List;

import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.sdpm.system.dao.pojo.Search;
import org.tinygroup.sdpm.system.dao.SearchDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class SearchDaoImpl extends TinyDslDaoSupport implements SearchDao {

	public Search add(Search search) {
		return getDslTemplate().insertAndReturnKey(search, new InsertGenerateCallback<Search>() {
			public Insert generate(Search t) {
				Insert insert = insertInto(SEARCHTABLE).values(
					SEARCHTABLE.SEARCH_ID.value(t.getSearchId()),
					SEARCHTABLE.SEARCH_OBJECTTYPE.value(t.getSearchObjectType()),
					SEARCHTABLE.SEARCH_OBJECTID.value(t.getSearchObjectID()),
					SEARCHTABLE.SEARCH_TITLE.value(t.getSearchTitle()),
					SEARCHTABLE.SEARCH_CONTENT.value(t.getSearchContent()),
					SEARCHTABLE.SEARCH_ADDEDDATE.value(t.getSearchAddedDate()),
					SEARCHTABLE.SEARCH_EDITEDDATE.value(t.getSearchEditedDate()));
				return insert;
			}
		});
	}

	public int edit(Search search) {
		if(search == null || search.getSearchId() == null){
			return 0;
		}
		return getDslTemplate().update(search, new UpdateGenerateCallback<Search>() {
			public Update generate(Search t) {
				Update update = update(SEARCHTABLE).set(
					SEARCHTABLE.SEARCH_OBJECTTYPE.value(t.getSearchObjectType()),
					SEARCHTABLE.SEARCH_OBJECTID.value(t.getSearchObjectID()),
					SEARCHTABLE.SEARCH_TITLE.value(t.getSearchTitle()),
					SEARCHTABLE.SEARCH_CONTENT.value(t.getSearchContent()),
					SEARCHTABLE.SEARCH_ADDEDDATE.value(t.getSearchAddedDate()),
					SEARCHTABLE.SEARCH_EDITEDDATE.value(t.getSearchEditedDate())).where(
					SEARCHTABLE.SEARCH_ID.eq(t.getSearchId()));
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
				return delete(SEARCHTABLE).where(SEARCHTABLE.SEARCH_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(SEARCHTABLE).where(SEARCHTABLE.SEARCH_ID.in(t));
		}
		},pks);
	}

	public Search getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Search.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(SEARCHTABLE).where(SEARCHTABLE.SEARCH_ID.eq(t));
			}
		});
	}

	public List<Search> query(Search search) {
		if(search==null){
			search=new Search();
		}
		return getDslTemplate().query(search, new SelectGenerateCallback<Search>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Search t) {
				return selectFrom(SEARCHTABLE).where(
				and(
					SEARCHTABLE.SEARCH_OBJECTTYPE.eq(t.getSearchObjectType()),
					SEARCHTABLE.SEARCH_OBJECTID.eq(t.getSearchObjectID()),
					SEARCHTABLE.SEARCH_TITLE.eq(t.getSearchTitle()),
					SEARCHTABLE.SEARCH_CONTENT.eq(t.getSearchContent()),
					SEARCHTABLE.SEARCH_ADDEDDATE.eq(t.getSearchAddedDate()),
					SEARCHTABLE.SEARCH_EDITEDDATE.eq(t.getSearchEditedDate())));
			}
		});
	}

	public Pager<Search> queryPager(int start,int limit ,Search search) {
		if(search==null){
			search=new Search();
		}
		return getDslTemplate().queryPager(start, limit, search, false, new SelectGenerateCallback<Search>() {

			public Select generate(Search t) {
				return MysqlSelect.selectFrom(SEARCHTABLE).where(
				and(
					SEARCHTABLE.SEARCH_OBJECTTYPE.eq(t.getSearchObjectType()),
					SEARCHTABLE.SEARCH_OBJECTID.eq(t.getSearchObjectID()),
					SEARCHTABLE.SEARCH_TITLE.eq(t.getSearchTitle()),
					SEARCHTABLE.SEARCH_CONTENT.eq(t.getSearchContent()),
					SEARCHTABLE.SEARCH_ADDEDDATE.eq(t.getSearchAddedDate()),
					SEARCHTABLE.SEARCH_EDITEDDATE.eq(t.getSearchEditedDate())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Search> searchs) {
		if (CollectionUtil.isEmpty(searchs)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, searchs, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(SEARCHTABLE).values(
					SEARCHTABLE.SEARCH_OBJECTTYPE.value(new JdbcNamedParameter("searchObjectType")),
					SEARCHTABLE.SEARCH_OBJECTID.value(new JdbcNamedParameter("searchObjectID")),
					SEARCHTABLE.SEARCH_TITLE.value(new JdbcNamedParameter("searchTitle")),
					SEARCHTABLE.SEARCH_CONTENT.value(new JdbcNamedParameter("searchContent")),
					SEARCHTABLE.SEARCH_ADDEDDATE.value(new JdbcNamedParameter("searchAddedDate")),
					SEARCHTABLE.SEARCH_EDITEDDATE.value(new JdbcNamedParameter("searchEditedDate")));
			}
		});
	}

	public int[] batchInsert(List<Search> searchs){
			return batchInsert(true ,searchs);
	}

	public int[] batchUpdate(List<Search> searchs) {
		if (CollectionUtil.isEmpty(searchs)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(searchs, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(SEARCHTABLE).set(
					SEARCHTABLE.SEARCH_OBJECTTYPE.value(new JdbcNamedParameter("searchObjectType")),
					SEARCHTABLE.SEARCH_OBJECTID.value(new JdbcNamedParameter("searchObjectID")),
					SEARCHTABLE.SEARCH_TITLE.value(new JdbcNamedParameter("searchTitle")),
					SEARCHTABLE.SEARCH_CONTENT.value(new JdbcNamedParameter("searchContent")),
					SEARCHTABLE.SEARCH_ADDEDDATE.value(new JdbcNamedParameter("searchAddedDate")),
					SEARCHTABLE.SEARCH_EDITEDDATE.value(new JdbcNamedParameter("searchEditedDate"))).where(
				SEARCHTABLE.SEARCH_ID.eq(new JdbcNamedParameter("searchId")));
			}
		});
	}

	public int[] batchDelete(List<Search> searchs) {
		if (CollectionUtil.isEmpty(searchs)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(searchs, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(SEARCHTABLE).where(and(
				SEARCHTABLE.SEARCH_ID.eq(new JdbcNamedParameter("searchId")),
				SEARCHTABLE.SEARCH_OBJECTTYPE.eq(new JdbcNamedParameter("searchObjectType")),
				SEARCHTABLE.SEARCH_OBJECTID.eq(new JdbcNamedParameter("searchObjectID")),
				SEARCHTABLE.SEARCH_TITLE.eq(new JdbcNamedParameter("searchTitle")),
				SEARCHTABLE.SEARCH_CONTENT.eq(new JdbcNamedParameter("searchContent")),
				SEARCHTABLE.SEARCH_ADDEDDATE.eq(new JdbcNamedParameter("searchAddedDate")),
				SEARCHTABLE.SEARCH_EDITEDDATE.eq(new JdbcNamedParameter("searchEditedDate"))));
			}
		});
	}

}
