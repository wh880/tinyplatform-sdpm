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
import static org.tinygroup.sdpm.system.dao.constant.SystemModuleTable.*;
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
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.dao.SystemModuleDao;
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
public class SystemModuleDaoImpl extends TinyDslDaoSupport implements SystemModuleDao {

	public SystemModule add(SystemModule systemModule) {
		return getDslTemplate().insertAndReturnKey(systemModule, new InsertGenerateCallback<SystemModule>() {
			public Insert generate(SystemModule t) {
				Insert insert = insertInto(SYSTEM_MODULETABLE).values(
					SYSTEM_MODULETABLE.SYS_MODULE_ID.value(t.getSysModuleId()),
					SYSTEM_MODULETABLE.SYS_MODULE_ROOT.value(t.getSysModuleRoot()),
					SYSTEM_MODULETABLE.SYS_MODULE_NAME.value(t.getSysModuleName()),
					SYSTEM_MODULETABLE.SYS_MODULE_PATH.value(t.getSysModulePath()),
					SYSTEM_MODULETABLE.SYS_MODULE_PARENT.value(t.getSysModuleParent()),
					SYSTEM_MODULETABLE.SYS_MODULE_GRADE.value(t.getSysModuleGrade()),
					SYSTEM_MODULETABLE.SYS_MODULE_ORDER.value(t.getSysModuleOrder()),
					SYSTEM_MODULETABLE.SYS_MODULE_TYPE.value(t.getSysModuleType()),
					SYSTEM_MODULETABLE.SYS_MODULE_OWNER.value(t.getSysModuleOwner()));
				return insert;
			}
		});
	}

	public int edit(SystemModule systemModule) {
		if(systemModule == null || systemModule.getSysModuleId() == null){
			return 0;
		}
		return getDslTemplate().update(systemModule, new UpdateGenerateCallback<SystemModule>() {
			public Update generate(SystemModule t) {
				Update update = update(SYSTEM_MODULETABLE).set(
					SYSTEM_MODULETABLE.SYS_MODULE_ROOT.value(t.getSysModuleRoot()),
					SYSTEM_MODULETABLE.SYS_MODULE_NAME.value(t.getSysModuleName()),
					SYSTEM_MODULETABLE.SYS_MODULE_PATH.value(t.getSysModulePath()),
					SYSTEM_MODULETABLE.SYS_MODULE_PARENT.value(t.getSysModuleParent()),
					SYSTEM_MODULETABLE.SYS_MODULE_GRADE.value(t.getSysModuleGrade()),
					SYSTEM_MODULETABLE.SYS_MODULE_ORDER.value(t.getSysModuleOrder()),
					SYSTEM_MODULETABLE.SYS_MODULE_TYPE.value(t.getSysModuleType()),
					SYSTEM_MODULETABLE.SYS_MODULE_OWNER.value(t.getSysModuleOwner())).where(
					SYSTEM_MODULETABLE.SYS_MODULE_ID.eq(t.getSysModuleId()));
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
				return delete(SYSTEM_MODULETABLE).where(SYSTEM_MODULETABLE.SYS_MODULE_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(SYSTEM_MODULETABLE).where(SYSTEM_MODULETABLE.SYS_MODULE_ID.in(t));
		}
		},pks);
	}

	public SystemModule getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, SystemModule.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(SYSTEM_MODULETABLE).where(SYSTEM_MODULETABLE.SYS_MODULE_ID.eq(t));
			}
		});
	}

	public List<SystemModule> query(SystemModule systemModule ,final OrderBy... orderBies) {
		if(systemModule==null){
			systemModule=new SystemModule();
		}
		return getDslTemplate().query(systemModule, new SelectGenerateCallback<SystemModule>() {

			@SuppressWarnings("rawtypes")
			public Select generate(SystemModule t) {
				Select select = selectFrom(SYSTEM_MODULETABLE).where(
				and(
					SYSTEM_MODULETABLE.SYS_MODULE_ROOT.eq(t.getSysModuleRoot()),
					SYSTEM_MODULETABLE.SYS_MODULE_NAME.eq(t.getSysModuleName()),
					SYSTEM_MODULETABLE.SYS_MODULE_PATH.eq(t.getSysModulePath()),
					SYSTEM_MODULETABLE.SYS_MODULE_PARENT.eq(t.getSysModuleParent()),
					SYSTEM_MODULETABLE.SYS_MODULE_GRADE.eq(t.getSysModuleGrade()),
					SYSTEM_MODULETABLE.SYS_MODULE_ORDER.eq(t.getSysModuleOrder()),
					SYSTEM_MODULETABLE.SYS_MODULE_TYPE.eq(t.getSysModuleType()),
					SYSTEM_MODULETABLE.SYS_MODULE_OWNER.eq(t.getSysModuleOwner())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<SystemModule> queryPager(int start,int limit ,SystemModule systemModule ,final OrderBy... orderBies) {
		if(systemModule==null){
			systemModule=new SystemModule();
		}
		return getDslTemplate().queryPager(start, limit, systemModule, false, new SelectGenerateCallback<SystemModule>() {

			public Select generate(SystemModule t) {
				Select select = MysqlSelect.selectFrom(SYSTEM_MODULETABLE).where(
				and(
					SYSTEM_MODULETABLE.SYS_MODULE_ROOT.eq(t.getSysModuleRoot()),
					SYSTEM_MODULETABLE.SYS_MODULE_NAME.eq(t.getSysModuleName()),
					SYSTEM_MODULETABLE.SYS_MODULE_PATH.eq(t.getSysModulePath()),
					SYSTEM_MODULETABLE.SYS_MODULE_PARENT.eq(t.getSysModuleParent()),
					SYSTEM_MODULETABLE.SYS_MODULE_GRADE.eq(t.getSysModuleGrade()),
					SYSTEM_MODULETABLE.SYS_MODULE_ORDER.eq(t.getSysModuleOrder()),
					SYSTEM_MODULETABLE.SYS_MODULE_TYPE.eq(t.getSysModuleType()),
					SYSTEM_MODULETABLE.SYS_MODULE_OWNER.eq(t.getSysModuleOwner())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<SystemModule> systemModules) {
		if (CollectionUtil.isEmpty(systemModules)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, systemModules, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(SYSTEM_MODULETABLE).values(
					SYSTEM_MODULETABLE.SYS_MODULE_ROOT.value(new JdbcNamedParameter("sysModuleRoot")),
					SYSTEM_MODULETABLE.SYS_MODULE_NAME.value(new JdbcNamedParameter("sysModuleName")),
					SYSTEM_MODULETABLE.SYS_MODULE_PATH.value(new JdbcNamedParameter("sysModulePath")),
					SYSTEM_MODULETABLE.SYS_MODULE_PARENT.value(new JdbcNamedParameter("sysModuleParent")),
					SYSTEM_MODULETABLE.SYS_MODULE_GRADE.value(new JdbcNamedParameter("sysModuleGrade")),
					SYSTEM_MODULETABLE.SYS_MODULE_ORDER.value(new JdbcNamedParameter("sysModuleOrder")),
					SYSTEM_MODULETABLE.SYS_MODULE_TYPE.value(new JdbcNamedParameter("sysModuleType")),
					SYSTEM_MODULETABLE.SYS_MODULE_OWNER.value(new JdbcNamedParameter("sysModuleOwner")));
			}
		});
	}

	public int[] batchInsert(List<SystemModule> systemModules){
			return batchInsert(true ,systemModules);
	}

	public int[] batchUpdate(List<SystemModule> systemModules) {
		if (CollectionUtil.isEmpty(systemModules)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(systemModules, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(SYSTEM_MODULETABLE).set(
					SYSTEM_MODULETABLE.SYS_MODULE_ROOT.value(new JdbcNamedParameter("sysModuleRoot")),
					SYSTEM_MODULETABLE.SYS_MODULE_NAME.value(new JdbcNamedParameter("sysModuleName")),
					SYSTEM_MODULETABLE.SYS_MODULE_PATH.value(new JdbcNamedParameter("sysModulePath")),
					SYSTEM_MODULETABLE.SYS_MODULE_PARENT.value(new JdbcNamedParameter("sysModuleParent")),
					SYSTEM_MODULETABLE.SYS_MODULE_GRADE.value(new JdbcNamedParameter("sysModuleGrade")),
					SYSTEM_MODULETABLE.SYS_MODULE_ORDER.value(new JdbcNamedParameter("sysModuleOrder")),
					SYSTEM_MODULETABLE.SYS_MODULE_TYPE.value(new JdbcNamedParameter("sysModuleType")),
					SYSTEM_MODULETABLE.SYS_MODULE_OWNER.value(new JdbcNamedParameter("sysModuleOwner"))).where(
				SYSTEM_MODULETABLE.SYS_MODULE_ID.eq(new JdbcNamedParameter("sysModuleId")));
			}
		});
	}

	public int[] batchDelete(List<SystemModule> systemModules) {
		if (CollectionUtil.isEmpty(systemModules)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(systemModules, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(SYSTEM_MODULETABLE).where(and(
				SYSTEM_MODULETABLE.SYS_MODULE_ID.eq(new JdbcNamedParameter("sysModuleId")),
				SYSTEM_MODULETABLE.SYS_MODULE_ROOT.eq(new JdbcNamedParameter("sysModuleRoot")),
				SYSTEM_MODULETABLE.SYS_MODULE_NAME.eq(new JdbcNamedParameter("sysModuleName")),
				SYSTEM_MODULETABLE.SYS_MODULE_PATH.eq(new JdbcNamedParameter("sysModulePath")),
				SYSTEM_MODULETABLE.SYS_MODULE_PARENT.eq(new JdbcNamedParameter("sysModuleParent")),
				SYSTEM_MODULETABLE.SYS_MODULE_GRADE.eq(new JdbcNamedParameter("sysModuleGrade")),
				SYSTEM_MODULETABLE.SYS_MODULE_ORDER.eq(new JdbcNamedParameter("sysModuleOrder")),
				SYSTEM_MODULETABLE.SYS_MODULE_TYPE.eq(new JdbcNamedParameter("sysModuleType")),
				SYSTEM_MODULETABLE.SYS_MODULE_OWNER.eq(new JdbcNamedParameter("sysModuleOwner"))));
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
