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
import static org.tinygroup.sdpm.system.dao.constant.SysModuleTable.*;
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
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.sdpm.system.dao.pojo.SysModule;
import org.tinygroup.sdpm.system.dao.SysModuleDao;
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
@LogClass("systemModule")
public class SysModuleDaoImpl extends TinyDslDaoSupport implements SysModuleDao {
    @LogMethod("add")
	public SysModule add(SysModule sysModule) {
		return getDslTemplate().insertAndReturnKey(sysModule, new InsertGenerateCallback<SysModule>() {
			public Insert generate(SysModule t) {
				Insert insert = insertInto(SYS_MODULETABLE).values(
					SYS_MODULETABLE.SYS_MODULE_ID.value(t.getSysModuleId()),
					SYS_MODULETABLE.SYS_MODULE_ROOT.value(t.getSysModuleRoot()),
					SYS_MODULETABLE.SYS_MODULE_NAME.value(t.getSysModuleName()),
					SYS_MODULETABLE.SYS_MODULE_PATH.value(t.getSysModulePath()),
					SYS_MODULETABLE.SYS_MODULE_PARENT.value(t.getSysModuleParent()),
					SYS_MODULETABLE.SYS_MODULE_GRADE.value(t.getSysModuleGrade()),
					SYS_MODULETABLE.SYS_MODULE_ORDER.value(t.getSysModuleOrder()),
					SYS_MODULETABLE.SYS_MODULE_TYPE.value(t.getSysModuleType()),
					SYS_MODULETABLE.SYS_MODULE_OWNER.value(t.getSysModuleOwner()));
				return insert;
			}
		});
	}
    @LogMethod("edit")
	public int edit(SysModule sysModule) {
		if(sysModule == null || sysModule.getSysModuleId() == null){
			return 0;
		}
		return getDslTemplate().update(sysModule, new UpdateGenerateCallback<SysModule>() {
			public Update generate(SysModule t) {
				Update update = update(SYS_MODULETABLE).set(
					SYS_MODULETABLE.SYS_MODULE_ROOT.value(t.getSysModuleRoot()),
					SYS_MODULETABLE.SYS_MODULE_NAME.value(t.getSysModuleName()),
					SYS_MODULETABLE.SYS_MODULE_PATH.value(t.getSysModulePath()),
					SYS_MODULETABLE.SYS_MODULE_PARENT.value(t.getSysModuleParent()),
					SYS_MODULETABLE.SYS_MODULE_GRADE.value(t.getSysModuleGrade()),
					SYS_MODULETABLE.SYS_MODULE_ORDER.value(t.getSysModuleOrder()),
					SYS_MODULETABLE.SYS_MODULE_TYPE.value(t.getSysModuleType()),
					SYS_MODULETABLE.SYS_MODULE_OWNER.value(t.getSysModuleOwner())).where(
					SYS_MODULETABLE.SYS_MODULE_ID.eq(t.getSysModuleId()));
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
				return delete(SYS_MODULETABLE).where(SYS_MODULETABLE.SYS_MODULE_ID.eq(pk));
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
				return delete(SYS_MODULETABLE).where(SYS_MODULETABLE.SYS_MODULE_ID.in(t));
		}
		},pks);
	}

	public SysModule getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, SysModule.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(SYS_MODULETABLE).where(SYS_MODULETABLE.SYS_MODULE_ID.eq(t));
			}
		});
	}

	public List<SysModule> query(SysModule sysModule ,final OrderBy... orderBies) {
		if(sysModule==null){
			sysModule=new SysModule();
		}
		return getDslTemplate().query(sysModule, new SelectGenerateCallback<SysModule>() {

			@SuppressWarnings("rawtypes")
			public Select generate(SysModule t) {
				Select select = selectFrom(SYS_MODULETABLE).where(
				and(
					SYS_MODULETABLE.SYS_MODULE_ROOT.eq(t.getSysModuleRoot()),
					SYS_MODULETABLE.SYS_MODULE_NAME.eq(t.getSysModuleName()),
					SYS_MODULETABLE.SYS_MODULE_PATH.eq(t.getSysModulePath()),
					SYS_MODULETABLE.SYS_MODULE_PARENT.eq(t.getSysModuleParent()),
					SYS_MODULETABLE.SYS_MODULE_GRADE.eq(t.getSysModuleGrade()),
					SYS_MODULETABLE.SYS_MODULE_ORDER.eq(t.getSysModuleOrder()),
					SYS_MODULETABLE.SYS_MODULE_TYPE.eq(t.getSysModuleType()),
					SYS_MODULETABLE.SYS_MODULE_OWNER.eq(t.getSysModuleOwner())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<SysModule> queryPager(int start,int limit ,SysModule sysModule ,final OrderBy... orderBies) {
		if(sysModule==null){
			sysModule=new SysModule();
		}
		return getDslTemplate().queryPager(start, limit, sysModule, false, new SelectGenerateCallback<SysModule>() {

			public Select generate(SysModule t) {
				Select select = MysqlSelect.selectFrom(SYS_MODULETABLE).where(
				and(
					SYS_MODULETABLE.SYS_MODULE_ROOT.eq(t.getSysModuleRoot()),
					SYS_MODULETABLE.SYS_MODULE_NAME.eq(t.getSysModuleName()),
					SYS_MODULETABLE.SYS_MODULE_PATH.eq(t.getSysModulePath()),
					SYS_MODULETABLE.SYS_MODULE_PARENT.eq(t.getSysModuleParent()),
					SYS_MODULETABLE.SYS_MODULE_GRADE.eq(t.getSysModuleGrade()),
					SYS_MODULETABLE.SYS_MODULE_ORDER.eq(t.getSysModuleOrder()),
					SYS_MODULETABLE.SYS_MODULE_TYPE.eq(t.getSysModuleType()),
					SYS_MODULETABLE.SYS_MODULE_OWNER.eq(t.getSysModuleOwner())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<SysModule> sysModules) {
		if (CollectionUtil.isEmpty(sysModules)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, sysModules, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(SYS_MODULETABLE).values(
					SYS_MODULETABLE.SYS_MODULE_ROOT.value(new JdbcNamedParameter("sysModuleRoot")),
					SYS_MODULETABLE.SYS_MODULE_NAME.value(new JdbcNamedParameter("sysModuleName")),
					SYS_MODULETABLE.SYS_MODULE_PATH.value(new JdbcNamedParameter("sysModulePath")),
					SYS_MODULETABLE.SYS_MODULE_PARENT.value(new JdbcNamedParameter("sysModuleParent")),
					SYS_MODULETABLE.SYS_MODULE_GRADE.value(new JdbcNamedParameter("sysModuleGrade")),
					SYS_MODULETABLE.SYS_MODULE_ORDER.value(new JdbcNamedParameter("sysModuleOrder")),
					SYS_MODULETABLE.SYS_MODULE_TYPE.value(new JdbcNamedParameter("sysModuleType")),
					SYS_MODULETABLE.SYS_MODULE_OWNER.value(new JdbcNamedParameter("sysModuleOwner")));
			}
		});
	}

	public int[] batchInsert(List<SysModule> sysModules){
			return batchInsert(true ,sysModules);
	}

	public int[] batchUpdate(List<SysModule> sysModules) {
		if (CollectionUtil.isEmpty(sysModules)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(sysModules, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(SYS_MODULETABLE).set(
					SYS_MODULETABLE.SYS_MODULE_ROOT.value(new JdbcNamedParameter("sysModuleRoot")),
					SYS_MODULETABLE.SYS_MODULE_NAME.value(new JdbcNamedParameter("sysModuleName")),
					SYS_MODULETABLE.SYS_MODULE_PATH.value(new JdbcNamedParameter("sysModulePath")),
					SYS_MODULETABLE.SYS_MODULE_PARENT.value(new JdbcNamedParameter("sysModuleParent")),
					SYS_MODULETABLE.SYS_MODULE_GRADE.value(new JdbcNamedParameter("sysModuleGrade")),
					SYS_MODULETABLE.SYS_MODULE_ORDER.value(new JdbcNamedParameter("sysModuleOrder")),
					SYS_MODULETABLE.SYS_MODULE_TYPE.value(new JdbcNamedParameter("sysModuleType")),
					SYS_MODULETABLE.SYS_MODULE_OWNER.value(new JdbcNamedParameter("sysModuleOwner"))).where(
				SYS_MODULETABLE.SYS_MODULE_ID.eq(new JdbcNamedParameter("sysModuleId")));
			}
		});
	}

	public int[] batchDelete(List<SysModule> sysModules) {
		if (CollectionUtil.isEmpty(sysModules)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(sysModules, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(SYS_MODULETABLE).where(and(
				SYS_MODULETABLE.SYS_MODULE_ID.eq(new JdbcNamedParameter("sysModuleId")),
				SYS_MODULETABLE.SYS_MODULE_ROOT.eq(new JdbcNamedParameter("sysModuleRoot")),
				SYS_MODULETABLE.SYS_MODULE_NAME.eq(new JdbcNamedParameter("sysModuleName")),
				SYS_MODULETABLE.SYS_MODULE_PATH.eq(new JdbcNamedParameter("sysModulePath")),
				SYS_MODULETABLE.SYS_MODULE_PARENT.eq(new JdbcNamedParameter("sysModuleParent")),
				SYS_MODULETABLE.SYS_MODULE_GRADE.eq(new JdbcNamedParameter("sysModuleGrade")),
				SYS_MODULETABLE.SYS_MODULE_ORDER.eq(new JdbcNamedParameter("sysModuleOrder")),
				SYS_MODULETABLE.SYS_MODULE_TYPE.eq(new JdbcNamedParameter("sysModuleType")),
				SYS_MODULETABLE.SYS_MODULE_OWNER.eq(new JdbcNamedParameter("sysModuleOwner"))));
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
