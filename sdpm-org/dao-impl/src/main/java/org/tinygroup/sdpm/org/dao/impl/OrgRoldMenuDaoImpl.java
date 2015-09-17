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

package org.tinygroup.sdpm.org.dao.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.org.dao.constant.OrgRoldMenuTable.*;
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
import org.tinygroup.sdpm.org.dao.pojo.OrgRoldMenu;
import org.tinygroup.sdpm.org.dao.OrgRoldMenuDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class OrgRoldMenuDaoImpl extends TinyDslDaoSupport implements OrgRoldMenuDao {

	public OrgRoldMenu add(OrgRoldMenu orgRoldMenu) {
		return getDslTemplate().insertAndReturnKey(orgRoldMenu, new InsertGenerateCallback<OrgRoldMenu>() {
			public Insert generate(OrgRoldMenu t) {
				Insert insert = insertInto(ORG_ROLD_MENUTABLE).values(
					ORG_ROLD_MENUTABLE.ID.value(t.getId()),
					ORG_ROLD_MENUTABLE.ORG_ROLE_ID.value(t.getOrgRoleId()),
					ORG_ROLD_MENUTABLE.ORG_ROLE_MENU_ID.value(t.getOrgRoleMenuId()));
				return insert;
			}
		});
	}

	public int edit(OrgRoldMenu orgRoldMenu) {
		if(orgRoldMenu == null || orgRoldMenu.getId() == null){
			return 0;
		}
		return getDslTemplate().update(orgRoldMenu, new UpdateGenerateCallback<OrgRoldMenu>() {
			public Update generate(OrgRoldMenu t) {
				Update update = update(ORG_ROLD_MENUTABLE).set(
					ORG_ROLD_MENUTABLE.ORG_ROLE_ID.value(t.getOrgRoleId()),
					ORG_ROLD_MENUTABLE.ORG_ROLE_MENU_ID.value(t.getOrgRoleMenuId())).where(
					ORG_ROLD_MENUTABLE.ID.eq(t.getId()));
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
				return delete(ORG_ROLD_MENUTABLE).where(ORG_ROLD_MENUTABLE.ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(ORG_ROLD_MENUTABLE).where(ORG_ROLD_MENUTABLE.ID.in(t));
		}
		},pks);
	}

	public OrgRoldMenu getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, OrgRoldMenu.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(ORG_ROLD_MENUTABLE).where(ORG_ROLD_MENUTABLE.ID.eq(t));
			}
		});
	}

	public List<OrgRoldMenu> query(OrgRoldMenu orgRoldMenu) {
		if(orgRoldMenu==null){
			orgRoldMenu=new OrgRoldMenu();
		}
		return getDslTemplate().query(orgRoldMenu, new SelectGenerateCallback<OrgRoldMenu>() {

			@SuppressWarnings("rawtypes")
			public Select generate(OrgRoldMenu t) {
				return selectFrom(ORG_ROLD_MENUTABLE).where(
				and(
					ORG_ROLD_MENUTABLE.ORG_ROLE_ID.eq(t.getOrgRoleId()),
					ORG_ROLD_MENUTABLE.ORG_ROLE_MENU_ID.eq(t.getOrgRoleMenuId())));
			}
		});
	}

	public Pager<OrgRoldMenu> queryPager(int start,int limit ,OrgRoldMenu orgRoldMenu) {
		if(orgRoldMenu==null){
			orgRoldMenu=new OrgRoldMenu();
		}
		return getDslTemplate().queryPager(start, limit, orgRoldMenu, false, new SelectGenerateCallback<OrgRoldMenu>() {

			public Select generate(OrgRoldMenu t) {
				return MysqlSelect.selectFrom(ORG_ROLD_MENUTABLE).where(
				and(
					ORG_ROLD_MENUTABLE.ORG_ROLE_ID.eq(t.getOrgRoleId()),
					ORG_ROLD_MENUTABLE.ORG_ROLE_MENU_ID.eq(t.getOrgRoleMenuId())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<OrgRoldMenu> orgRoldMenus) {
		if (CollectionUtil.isEmpty(orgRoldMenus)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, orgRoldMenus, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(ORG_ROLD_MENUTABLE).values(
					ORG_ROLD_MENUTABLE.ORG_ROLE_ID.value(new JdbcNamedParameter("orgRoleId")),
					ORG_ROLD_MENUTABLE.ORG_ROLE_MENU_ID.value(new JdbcNamedParameter("orgRoleMenuId")));
			}
		});
	}

	public int[] batchInsert(List<OrgRoldMenu> orgRoldMenus){
			return batchInsert(true ,orgRoldMenus);
	}

	public int[] batchUpdate(List<OrgRoldMenu> orgRoldMenus) {
		if (CollectionUtil.isEmpty(orgRoldMenus)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(orgRoldMenus, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(ORG_ROLD_MENUTABLE).set(
					ORG_ROLD_MENUTABLE.ORG_ROLE_ID.value(new JdbcNamedParameter("orgRoleId")),
					ORG_ROLD_MENUTABLE.ORG_ROLE_MENU_ID.value(new JdbcNamedParameter("orgRoleMenuId"))).where(
				ORG_ROLD_MENUTABLE.ID.eq(new JdbcNamedParameter("id")));
			}
		});
	}

	public int[] batchDelete(List<OrgRoldMenu> orgRoldMenus) {
		if (CollectionUtil.isEmpty(orgRoldMenus)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(orgRoldMenus, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(ORG_ROLD_MENUTABLE).where(and(
				ORG_ROLD_MENUTABLE.ID.eq(new JdbcNamedParameter("id")),
				ORG_ROLD_MENUTABLE.ORG_ROLE_ID.eq(new JdbcNamedParameter("orgRoleId")),
				ORG_ROLD_MENUTABLE.ORG_ROLE_MENU_ID.eq(new JdbcNamedParameter("orgRoleMenuId"))));
			}
		});
	}

}