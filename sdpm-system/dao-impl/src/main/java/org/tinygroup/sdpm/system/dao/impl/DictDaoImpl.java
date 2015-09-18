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
import static org.tinygroup.sdpm.system.dao.constant.DictTable.*;
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
import org.tinygroup.sdpm.system.dao.pojo.Dict;
import org.tinygroup.sdpm.system.dao.DictDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class DictDaoImpl extends TinyDslDaoSupport implements DictDao {

	public Dict add(Dict dict) {
		return getDslTemplate().insertAndReturnKey(dict, new InsertGenerateCallback<Dict>() {
			public Insert generate(Dict t) {
				Insert insert = insertInto(DICTTABLE).values(
					DICTTABLE.DICT_ID.value(t.getDictId()),
					DICTTABLE.DICT_KEY.value(t.getDictKey()),
					DICTTABLE.DICT_VALUE.value(t.getDictValue()),
					DICTTABLE.DICT_SORT.value(t.getDictSort()),
					DICTTABLE.MODULE_ID.value(t.getModuleId()));
				return insert;
			}
		});
	}

	public int edit(Dict dict) {
		if(dict == null || dict.getDictId() == null){
			return 0;
		}
		return getDslTemplate().update(dict, new UpdateGenerateCallback<Dict>() {
			public Update generate(Dict t) {
				Update update = update(DICTTABLE).set(
					DICTTABLE.DICT_ID.value(t.getDictId()),
					DICTTABLE.DICT_KEY.value(t.getDictKey()),
					DICTTABLE.DICT_VALUE.value(t.getDictValue()),
					DICTTABLE.DICT_SORT.value(t.getDictSort()),
					DICTTABLE.MODULE_ID.value(t.getModuleId())).where(
					DICTTABLE.DICT_ID.eq(t.getDictId()));
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
				return delete(DICTTABLE).where(DICTTABLE.DICT_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(DICTTABLE).where(DICTTABLE.DICT_ID.in(t));
		}
		},pks);
	}

	public Dict getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Dict.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(DICTTABLE).where(DICTTABLE.DICT_ID.eq(t));
			}
		});
	}

	public List<Dict> query(Dict dict) {
		if(dict==null){
			dict=new Dict();
		}
		return getDslTemplate().query(dict, new SelectGenerateCallback<Dict>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Dict t) {
				return selectFrom(DICTTABLE).where(
				and(
					DICTTABLE.DICT_ID.eq(t.getDictId()),
					DICTTABLE.DICT_KEY.eq(t.getDictKey()),
					DICTTABLE.DICT_VALUE.eq(t.getDictValue()),
					DICTTABLE.DICT_SORT.eq(t.getDictSort()),
					DICTTABLE.MODULE_ID.eq(t.getModuleId())));
			}
		});
	}

	public Pager<Dict> queryPager(int start,int limit ,Dict dict) {
		if(dict==null){
			dict=new Dict();
		}
		return getDslTemplate().queryPager(start, limit, dict, false, new SelectGenerateCallback<Dict>() {

			public Select generate(Dict t) {
				return MysqlSelect.selectFrom(DICTTABLE).where(
				and(
					DICTTABLE.DICT_ID.eq(t.getDictId()),
					DICTTABLE.DICT_KEY.eq(t.getDictKey()),
					DICTTABLE.DICT_VALUE.eq(t.getDictValue()),
					DICTTABLE.DICT_SORT.eq(t.getDictSort()),
					DICTTABLE.MODULE_ID.eq(t.getModuleId())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Dict> dicts) {
		if (CollectionUtil.isEmpty(dicts)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, dicts, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(DICTTABLE).values(
					DICTTABLE.DICT_ID.value(new JdbcNamedParameter("dictId")),
					DICTTABLE.DICT_KEY.value(new JdbcNamedParameter("dictKey")),
					DICTTABLE.DICT_VALUE.value(new JdbcNamedParameter("dictValue")),
					DICTTABLE.DICT_SORT.value(new JdbcNamedParameter("dictSort")),
					DICTTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")));
			}
		});
	}

	public int[] batchInsert(List<Dict> dicts){
			return batchInsert(true ,dicts);
	}

	public int[] batchUpdate(List<Dict> dicts) {
		if (CollectionUtil.isEmpty(dicts)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(dicts, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(DICTTABLE).set(
					DICTTABLE.DICT_ID.value(new JdbcNamedParameter("dictId")),
					DICTTABLE.DICT_KEY.value(new JdbcNamedParameter("dictKey")),
					DICTTABLE.DICT_VALUE.value(new JdbcNamedParameter("dictValue")),
					DICTTABLE.DICT_SORT.value(new JdbcNamedParameter("dictSort")),
					DICTTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId"))).where(
				DICTTABLE.DICT_ID.eq(new JdbcNamedParameter("dictId")));
			}
		});
	}

	public int[] batchDelete(List<Dict> dicts) {
		if (CollectionUtil.isEmpty(dicts)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(dicts, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(DICTTABLE).where(and(
				DICTTABLE.DICT_ID.eq(new JdbcNamedParameter("dictId")),
				DICTTABLE.DICT_KEY.eq(new JdbcNamedParameter("dictKey")),
				DICTTABLE.DICT_VALUE.eq(new JdbcNamedParameter("dictValue")),
				DICTTABLE.DICT_SORT.eq(new JdbcNamedParameter("dictSort")),
				DICTTABLE.MODULE_ID.eq(new JdbcNamedParameter("moduleId"))));
			}
		});
	}

}
