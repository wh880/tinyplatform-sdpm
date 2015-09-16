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

import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

import java.io.Serializable;
import java.util.List;

import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.system.dao.inter.SearchindexDao;
import org.tinygroup.sdpm.system.dao.pojo.Searchindex;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import static org.tinygroup.sdpm.system.dao.constant.SearchindexTable.SEARCHINDEXTABLE;

public class SearchindexDaoImpl extends TinyDslDaoSupport implements SearchindexDao {

	public Searchindex add(Searchindex searchindex) {
		return getDslTemplate().insertAndReturnKey(searchindex, new InsertGenerateCallback<Searchindex>() {
			public Insert generate(Searchindex t) {
				Insert insert = insertInto(SEARCHINDEXTABLE).values(
					SEARCHINDEXTABLE.SEARCH_ID.value(t.getSearchId()),
					SEARCHINDEXTABLE.SEARCH_OBJECTTYPE.value(t.getSearchObjectType()),
					SEARCHINDEXTABLE.SEARCH_OBJECTID.value(t.getSearchObjectID()),
					SEARCHINDEXTABLE.SEARCH_TITLE.value(t.getSearchTitle()),
					SEARCHINDEXTABLE.SEARCH_CONTENT.value(t.getSearchContent()),
					SEARCHINDEXTABLE.SEARCH_ADDEDDATE.value(t.getSearchAddedDate()),
					SEARCHINDEXTABLE.SEARCH_EDITEDDATE.value(t.getSearchEditedDate()));
				return insert;
			}
		});
	}

	public int edit(Searchindex searchindex) {
		if(searchindex == null || searchindex.getSearchId() == null){
			return 0;
		}
		return getDslTemplate().update(searchindex, new UpdateGenerateCallback<Searchindex>() {
			public Update generate(Searchindex t) {
				Update update = update(SEARCHINDEXTABLE).set(
					SEARCHINDEXTABLE.SEARCH_OBJECTTYPE.value(t.getSearchObjectType()),
					SEARCHINDEXTABLE.SEARCH_OBJECTID.value(t.getSearchObjectID()),
					SEARCHINDEXTABLE.SEARCH_TITLE.value(t.getSearchTitle()),
					SEARCHINDEXTABLE.SEARCH_CONTENT.value(t.getSearchContent()),
					SEARCHINDEXTABLE.SEARCH_ADDEDDATE.value(t.getSearchAddedDate()),
					SEARCHINDEXTABLE.SEARCH_EDITEDDATE.value(t.getSearchEditedDate())).where(
					SEARCHINDEXTABLE.SEARCH_ID.eq(t.getSearchId()));
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
				return delete(SEARCHINDEXTABLE).where(SEARCHINDEXTABLE.SEARCH_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(SEARCHINDEXTABLE).where(SEARCHINDEXTABLE.SEARCH_ID.in(t));
		}
		},pks);
	}

	public Searchindex getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Searchindex.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(SEARCHINDEXTABLE).where(SEARCHINDEXTABLE.SEARCH_ID.eq(t));
			}
		});
	}

	public List<Searchindex> query(Searchindex searchindex) {
		if(searchindex==null){
			searchindex=new Searchindex();
		}
		return getDslTemplate().query(searchindex, new SelectGenerateCallback<Searchindex>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Searchindex t) {
				return selectFrom(SEARCHINDEXTABLE).where(
				and(
					SEARCHINDEXTABLE.SEARCH_OBJECTTYPE.eq(t.getSearchObjectType()),
					SEARCHINDEXTABLE.SEARCH_OBJECTID.eq(t.getSearchObjectID()),
					SEARCHINDEXTABLE.SEARCH_TITLE.eq(t.getSearchTitle()),
					SEARCHINDEXTABLE.SEARCH_CONTENT.eq(t.getSearchContent()),
					SEARCHINDEXTABLE.SEARCH_ADDEDDATE.eq(t.getSearchAddedDate()),
					SEARCHINDEXTABLE.SEARCH_EDITEDDATE.eq(t.getSearchEditedDate())));
			}
		});
	}

	public Pager<Searchindex> queryPager(int start,int limit ,Searchindex searchindex) {
		if(searchindex==null){
			searchindex=new Searchindex();
		}
		return getDslTemplate().queryPager(start, limit, searchindex, false, new SelectGenerateCallback<Searchindex>() {

			public Select generate(Searchindex t) {
				return MysqlSelect.selectFrom(SEARCHINDEXTABLE).where(
				and(
					SEARCHINDEXTABLE.SEARCH_OBJECTTYPE.eq(t.getSearchObjectType()),
					SEARCHINDEXTABLE.SEARCH_OBJECTID.eq(t.getSearchObjectID()),
					SEARCHINDEXTABLE.SEARCH_TITLE.eq(t.getSearchTitle()),
					SEARCHINDEXTABLE.SEARCH_CONTENT.eq(t.getSearchContent()),
					SEARCHINDEXTABLE.SEARCH_ADDEDDATE.eq(t.getSearchAddedDate()),
					SEARCHINDEXTABLE.SEARCH_EDITEDDATE.eq(t.getSearchEditedDate())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Searchindex> searchindexs) {
		if (CollectionUtil.isEmpty(searchindexs)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, searchindexs, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(SEARCHINDEXTABLE).values(
					SEARCHINDEXTABLE.SEARCH_OBJECTTYPE.value(new JdbcNamedParameter("searchObjectType")),
					SEARCHINDEXTABLE.SEARCH_OBJECTID.value(new JdbcNamedParameter("searchObjectID")),
					SEARCHINDEXTABLE.SEARCH_TITLE.value(new JdbcNamedParameter("searchTitle")),
					SEARCHINDEXTABLE.SEARCH_CONTENT.value(new JdbcNamedParameter("searchContent")),
					SEARCHINDEXTABLE.SEARCH_ADDEDDATE.value(new JdbcNamedParameter("searchAddedDate")),
					SEARCHINDEXTABLE.SEARCH_EDITEDDATE.value(new JdbcNamedParameter("searchEditedDate")));
			}
		});
	}

	public int[] batchInsert(List<Searchindex> searchindexs){
			return batchInsert(true ,searchindexs);
	}

	public int[] batchUpdate(List<Searchindex> searchindexs) {
		if (CollectionUtil.isEmpty(searchindexs)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(searchindexs, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(SEARCHINDEXTABLE).set(
					SEARCHINDEXTABLE.SEARCH_OBJECTTYPE.value(new JdbcNamedParameter("searchObjectType")),
					SEARCHINDEXTABLE.SEARCH_OBJECTID.value(new JdbcNamedParameter("searchObjectID")),
					SEARCHINDEXTABLE.SEARCH_TITLE.value(new JdbcNamedParameter("searchTitle")),
					SEARCHINDEXTABLE.SEARCH_CONTENT.value(new JdbcNamedParameter("searchContent")),
					SEARCHINDEXTABLE.SEARCH_ADDEDDATE.value(new JdbcNamedParameter("searchAddedDate")),
					SEARCHINDEXTABLE.SEARCH_EDITEDDATE.value(new JdbcNamedParameter("searchEditedDate"))).where(
				SEARCHINDEXTABLE.SEARCH_ID.eq(new JdbcNamedParameter("searchId")));
			}
		});
	}

	public int[] batchDelete(List<Searchindex> searchindexs) {
		if (CollectionUtil.isEmpty(searchindexs)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(searchindexs, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(SEARCHINDEXTABLE).where(and(
				SEARCHINDEXTABLE.SEARCH_ID.eq(new JdbcNamedParameter("searchId")),
				SEARCHINDEXTABLE.SEARCH_OBJECTTYPE.eq(new JdbcNamedParameter("searchObjectType")),
				SEARCHINDEXTABLE.SEARCH_OBJECTID.eq(new JdbcNamedParameter("searchObjectID")),
				SEARCHINDEXTABLE.SEARCH_TITLE.eq(new JdbcNamedParameter("searchTitle")),
				SEARCHINDEXTABLE.SEARCH_CONTENT.eq(new JdbcNamedParameter("searchContent")),
				SEARCHINDEXTABLE.SEARCH_ADDEDDATE.eq(new JdbcNamedParameter("searchAddedDate")),
				SEARCHINDEXTABLE.SEARCH_EDITEDDATE.eq(new JdbcNamedParameter("searchEditedDate"))));
			}
		});
	}

}
