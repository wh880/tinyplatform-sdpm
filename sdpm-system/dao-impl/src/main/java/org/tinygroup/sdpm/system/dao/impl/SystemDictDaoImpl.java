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
import static org.tinygroup.sdpm.system.dao.constant.SystemDictTable.*;
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
import org.tinygroup.sdpm.system.dao.pojo.SystemDict;
import org.tinygroup.sdpm.system.dao.SystemDictDao;
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
public class SystemDictDaoImpl extends TinyDslDaoSupport implements SystemDictDao {

	public SystemDict add(SystemDict systemDict) {
		return getDslTemplate().insertAndReturnKey(systemDict, new InsertGenerateCallback<SystemDict>() {
			public Insert generate(SystemDict t) {
				Insert insert = insertInto(SYSTEM_DICTTABLE).values(
					SYSTEM_DICTTABLE.DICT_ID.value(t.getDictId()),
					SYSTEM_DICTTABLE.DICT_KEY.value(t.getDictKey()),
					SYSTEM_DICTTABLE.DICT_VALUE.value(t.getDictValue()),
					SYSTEM_DICTTABLE.DICT_SORT.value(t.getDictSort()),
					SYSTEM_DICTTABLE.MODULE_ID.value(t.getModuleId()),
					SYSTEM_DICTTABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}

	public int edit(SystemDict systemDict) {
		if(systemDict == null || systemDict.getDictId() == null){
			return 0;
		}
		return getDslTemplate().update(systemDict, new UpdateGenerateCallback<SystemDict>() {
			public Update generate(SystemDict t) {
				Update update = update(SYSTEM_DICTTABLE).set(
					SYSTEM_DICTTABLE.DICT_ID.value(t.getDictId()),
					SYSTEM_DICTTABLE.DICT_KEY.value(t.getDictKey()),
					SYSTEM_DICTTABLE.DICT_VALUE.value(t.getDictValue()),
					SYSTEM_DICTTABLE.DICT_SORT.value(t.getDictSort()),
					SYSTEM_DICTTABLE.MODULE_ID.value(t.getModuleId()),
					SYSTEM_DICTTABLE.DELETED.value(t.getDeleted())).where(
					SYSTEM_DICTTABLE.DICT_ID.eq(t.getDictId()));
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
				return delete(SYSTEM_DICTTABLE).where(SYSTEM_DICTTABLE.DICT_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(SYSTEM_DICTTABLE).where(SYSTEM_DICTTABLE.DICT_ID.in(t));
		}
		},pks);
	}

	public SystemDict getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, SystemDict.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(SYSTEM_DICTTABLE).where(SYSTEM_DICTTABLE.DICT_ID.eq(t));
			}
		});
	}

	public List<SystemDict> query(SystemDict systemDict ,final OrderBy... orderBies) {
		if(systemDict==null){
			systemDict=new SystemDict();
		}
		return getDslTemplate().query(systemDict, new SelectGenerateCallback<SystemDict>() {

			@SuppressWarnings("rawtypes")
			public Select generate(SystemDict t) {
				Select select = selectFrom(SYSTEM_DICTTABLE).where(
				and(
					SYSTEM_DICTTABLE.DICT_ID.eq(t.getDictId()),
					SYSTEM_DICTTABLE.DICT_KEY.eq(t.getDictKey()),
					SYSTEM_DICTTABLE.DICT_VALUE.eq(t.getDictValue()),
					SYSTEM_DICTTABLE.DICT_SORT.eq(t.getDictSort()),
					SYSTEM_DICTTABLE.MODULE_ID.eq(t.getModuleId()),
					SYSTEM_DICTTABLE.DELETED.eq(t.getDeleted())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<SystemDict> queryPager(int start,int limit ,SystemDict systemDict ,final OrderBy... orderBies) {
		if(systemDict==null){
			systemDict=new SystemDict();
		}
		return getDslTemplate().queryPager(start, limit, systemDict, false, new SelectGenerateCallback<SystemDict>() {

			public Select generate(SystemDict t) {
				Select select = MysqlSelect.selectFrom(SYSTEM_DICTTABLE).where(
				and(
					SYSTEM_DICTTABLE.DICT_ID.eq(t.getDictId()),
					SYSTEM_DICTTABLE.DICT_KEY.eq(t.getDictKey()),
					SYSTEM_DICTTABLE.DICT_VALUE.eq(t.getDictValue()),
					SYSTEM_DICTTABLE.DICT_SORT.eq(t.getDictSort()),
					SYSTEM_DICTTABLE.MODULE_ID.eq(t.getModuleId()),
					SYSTEM_DICTTABLE.DELETED.eq(t.getDeleted())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<SystemDict> systemDicts) {
		if (CollectionUtil.isEmpty(systemDicts)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, systemDicts, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(SYSTEM_DICTTABLE).values(
					SYSTEM_DICTTABLE.DICT_ID.value(new JdbcNamedParameter("dictId")),
					SYSTEM_DICTTABLE.DICT_KEY.value(new JdbcNamedParameter("dictKey")),
					SYSTEM_DICTTABLE.DICT_VALUE.value(new JdbcNamedParameter("dictValue")),
					SYSTEM_DICTTABLE.DICT_SORT.value(new JdbcNamedParameter("dictSort")),
					SYSTEM_DICTTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
					SYSTEM_DICTTABLE.DELETED.value(new JdbcNamedParameter("deleted")));
			}
		});
	}

	public int[] batchInsert(List<SystemDict> systemDicts){
			return batchInsert(true ,systemDicts);
	}

	public int[] batchUpdate(List<SystemDict> systemDicts) {
		if (CollectionUtil.isEmpty(systemDicts)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(systemDicts, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(SYSTEM_DICTTABLE).set(
					SYSTEM_DICTTABLE.DICT_ID.value(new JdbcNamedParameter("dictId")),
					SYSTEM_DICTTABLE.DICT_KEY.value(new JdbcNamedParameter("dictKey")),
					SYSTEM_DICTTABLE.DICT_VALUE.value(new JdbcNamedParameter("dictValue")),
					SYSTEM_DICTTABLE.DICT_SORT.value(new JdbcNamedParameter("dictSort")),
					SYSTEM_DICTTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
					SYSTEM_DICTTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
				SYSTEM_DICTTABLE.DICT_ID.eq(new JdbcNamedParameter("dictId")));
			}
		});
	}

	public int[] batchDelete(List<SystemDict> systemDicts) {
		if (CollectionUtil.isEmpty(systemDicts)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(systemDicts, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(SYSTEM_DICTTABLE).where(and(
				SYSTEM_DICTTABLE.DICT_ID.eq(new JdbcNamedParameter("dictId")),
				SYSTEM_DICTTABLE.DICT_KEY.eq(new JdbcNamedParameter("dictKey")),
				SYSTEM_DICTTABLE.DICT_VALUE.eq(new JdbcNamedParameter("dictValue")),
				SYSTEM_DICTTABLE.DICT_SORT.eq(new JdbcNamedParameter("dictSort")),
				SYSTEM_DICTTABLE.MODULE_ID.eq(new JdbcNamedParameter("moduleId")),
				SYSTEM_DICTTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
			}
		});
	}

	private  Select addOrderByElements(Select select ,OrderBy... orderBies){
		List<OrderByElement> orderByElements = new ArrayList<OrderByElement>();
		for (int i = 0; orderBies != null && i < orderBies.length; i++) {
			OrderByElement tempElement = null;
			if(orderBies[i] != null){
				tempElement = orderBies[i].getOrderByElement();
			}
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
