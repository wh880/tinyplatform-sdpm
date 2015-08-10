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

package org.tinygroup.sdpm.common.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.common.constant.SysDictTable.*;
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
import org.tinygroup.sdpm.common.pojo.SysDict;
import org.tinygroup.sdpm.common.inter.SysDictDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class SysDictDaoImpl extends TinyDslDaoSupport implements SysDictDao {

	public SysDict insertObject(SysDict sysDict) {
		return getDslTemplate().insertObject(sysDict, new InsertGenerateCallback<SysDict>() {
			public Insert generate(SysDict t) {
				Insert insert = insertInto(SYSDICTTABLE).values(
					SYSDICTTABLE.SYSDICT_ID.value(t.getSysdictId()),
					SYSDICTTABLE.COMPANY_ID.value(t.getCompanyId()),
					SYSDICTTABLE.SYSDICT_OWNER.value(t.getSysdictOwner()),
					SYSDICTTABLE.SYSDICT_MODULE.value(t.getSysdictModule()),
					SYSDICTTABLE.SYSDICT_SECTION.value(t.getSysdictSection()),
					SYSDICTTABLE.SYSDICT_KEY.value(t.getSysdictKey()),
					SYSDICTTABLE.SYSDICT_VALUE.value(t.getSysdictValue()));
				return insert;
			}
		});
	}

	public SysDict insertObject(boolean autoGeneratedKeys ,SysDict sysDict) {
		return getDslTemplate().insertObjectAndReturnKey(autoGeneratedKeys ,sysDict, new InsertGenerateCallback<SysDict>() {

			public Insert generate(SysDict t) {
				Insert insert = insertInto(SYSDICTTABLE).values(
					SYSDICTTABLE.SYSDICT_ID.value(t.getSysdictId()),
					SYSDICTTABLE.COMPANY_ID.value(t.getCompanyId()),
					SYSDICTTABLE.SYSDICT_OWNER.value(t.getSysdictOwner()),
					SYSDICTTABLE.SYSDICT_MODULE.value(t.getSysdictModule()),
					SYSDICTTABLE.SYSDICT_SECTION.value(t.getSysdictSection()),
					SYSDICTTABLE.SYSDICT_KEY.value(t.getSysdictKey()),
					SYSDICTTABLE.SYSDICT_VALUE.value(t.getSysdictValue()));
				return insert;
			}
		});
	}

	public int updateObject(SysDict sysDict) {
		return getDslTemplate().updateObject(sysDict, new UpdateGenerateCallback<SysDict>() {
			public Update generate(SysDict t) {
				Update update = update(SYSDICTTABLE).set(
					SYSDICTTABLE.COMPANY_ID.value(t.getCompanyId()),
					SYSDICTTABLE.SYSDICT_OWNER.value(t.getSysdictOwner()),
					SYSDICTTABLE.SYSDICT_MODULE.value(t.getSysdictModule()),
					SYSDICTTABLE.SYSDICT_SECTION.value(t.getSysdictSection()),
					SYSDICTTABLE.SYSDICT_KEY.value(t.getSysdictKey()),
					SYSDICTTABLE.SYSDICT_VALUE.value(t.getSysdictValue())).where(
					SYSDICTTABLE.SYSDICT_ID.eq(t.getSysdictId()));
				return update;
			}
		});
	}

	public int deleteObject(Serializable pk){
		return getDslTemplate().deleteObject(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(SYSDICTTABLE).where(SYSDICTTABLE.SYSDICT_ID.eq(pk));
			}
		});
	}

	public int deleteObjects(Serializable... pks) {
		return getDslTemplate().deleteObjects(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(SYSDICTTABLE).where(SYSDICTTABLE.SYSDICT_ID.in(t));
		}
		},pks);
	}

	public SysDict getObjectById(Serializable pk) {
		return getDslTemplate().getObjectById(pk, SysDict.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(SYSDICTTABLE).where(SYSDICTTABLE.SYSDICT_ID.eq(t));
			}
		});
	}

	public List<SysDict> queryObjects(SysDict sysDict) {
		if(sysDict==null){
			sysDict=new SysDict();
		}
		return getDslTemplate().queryObjects(sysDict, new SelectGenerateCallback<SysDict>() {

			@SuppressWarnings("rawtypes")
			public Select generate(SysDict t) {
				return selectFrom(SYSDICTTABLE).where(
				and(
					SYSDICTTABLE.COMPANY_ID.eq(t.getCompanyId()),
					SYSDICTTABLE.SYSDICT_OWNER.eq(t.getSysdictOwner()),
					SYSDICTTABLE.SYSDICT_MODULE.eq(t.getSysdictModule()),
					SYSDICTTABLE.SYSDICT_SECTION.eq(t.getSysdictSection()),
					SYSDICTTABLE.SYSDICT_KEY.eq(t.getSysdictKey()),
					SYSDICTTABLE.SYSDICT_VALUE.eq(t.getSysdictValue())));
			}
		});
	}

	public Pager<SysDict> queryObjectsForPage(int start,int limit ,SysDict sysDict) {
		if(sysDict==null){
			sysDict=new SysDict();
		}
		return getDslTemplate().queryObjectsForPage(start, limit, sysDict, false, new SelectGenerateCallback<SysDict>() {

			public Select generate(SysDict t) {
				return MysqlSelect.selectFrom(SYSDICTTABLE).where(
				and(
					SYSDICTTABLE.COMPANY_ID.eq(t.getCompanyId()),
					SYSDICTTABLE.SYSDICT_OWNER.eq(t.getSysdictOwner()),
					SYSDICTTABLE.SYSDICT_MODULE.eq(t.getSysdictModule()),
					SYSDICTTABLE.SYSDICT_SECTION.eq(t.getSysdictSection()),
					SYSDICTTABLE.SYSDICT_KEY.eq(t.getSysdictKey()),
					SYSDICTTABLE.SYSDICT_VALUE.eq(t.getSysdictValue())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<SysDict> sysDicts) {
		if (CollectionUtil.isEmpty(sysDicts)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, sysDicts, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(SYSDICTTABLE).values(
					SYSDICTTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					SYSDICTTABLE.SYSDICT_OWNER.value(new JdbcNamedParameter("sysdictOwner")),
					SYSDICTTABLE.SYSDICT_MODULE.value(new JdbcNamedParameter("sysdictModule")),
					SYSDICTTABLE.SYSDICT_SECTION.value(new JdbcNamedParameter("sysdictSection")),
					SYSDICTTABLE.SYSDICT_KEY.value(new JdbcNamedParameter("sysdictKey")),
					SYSDICTTABLE.SYSDICT_VALUE.value(new JdbcNamedParameter("sysdictValue")));
			}
		});
	}

	public int[] batchInsert(List<SysDict> sysDicts){
			return batchInsert(true ,sysDicts);
	}

	public int[] batchUpdate(List<SysDict> sysDicts) {
		if (CollectionUtil.isEmpty(sysDicts)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(sysDicts, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(SYSDICTTABLE).set(
					SYSDICTTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					SYSDICTTABLE.SYSDICT_OWNER.value(new JdbcNamedParameter("sysdictOwner")),
					SYSDICTTABLE.SYSDICT_MODULE.value(new JdbcNamedParameter("sysdictModule")),
					SYSDICTTABLE.SYSDICT_SECTION.value(new JdbcNamedParameter("sysdictSection")),
					SYSDICTTABLE.SYSDICT_KEY.value(new JdbcNamedParameter("sysdictKey")),
					SYSDICTTABLE.SYSDICT_VALUE.value(new JdbcNamedParameter("sysdictValue"))).where(
				SYSDICTTABLE.SYSDICT_ID.eq(new JdbcNamedParameter("sysdictId")));
			}
		});
	}

	public int[] batchDelete(List<SysDict> sysDicts) {
		if (CollectionUtil.isEmpty(sysDicts)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(sysDicts, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(SYSDICTTABLE).where(and(
				SYSDICTTABLE.SYSDICT_ID.eq(new JdbcNamedParameter("sysdictId")),
				SYSDICTTABLE.COMPANY_ID.eq(new JdbcNamedParameter("companyId")),
				SYSDICTTABLE.SYSDICT_OWNER.eq(new JdbcNamedParameter("sysdictOwner")),
				SYSDICTTABLE.SYSDICT_MODULE.eq(new JdbcNamedParameter("sysdictModule")),
				SYSDICTTABLE.SYSDICT_SECTION.eq(new JdbcNamedParameter("sysdictSection")),
				SYSDICTTABLE.SYSDICT_KEY.eq(new JdbcNamedParameter("sysdictKey")),
				SYSDICTTABLE.SYSDICT_VALUE.eq(new JdbcNamedParameter("sysdictValue"))));
			}
		});
	}

}
