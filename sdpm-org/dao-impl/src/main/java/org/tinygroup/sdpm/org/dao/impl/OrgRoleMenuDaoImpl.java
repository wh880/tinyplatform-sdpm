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

import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.org.dao.OrgRoleMenuDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.org.dao.constant.OrgRoleMenuTable.ORG_ROLE_MENUTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
@Repository
public class OrgRoleMenuDaoImpl extends TinyDslDaoSupport implements OrgRoleMenuDao {

	public OrgRoleMenu add(OrgRoleMenu orgRoleMenu) {
		return getDslTemplate().insertAndReturnKey(orgRoleMenu, new InsertGenerateCallback<OrgRoleMenu>() {
			public Insert generate(OrgRoleMenu t) {
				Insert insert = insertInto(ORG_ROLE_MENUTABLE).values(
						ORG_ROLE_MENUTABLE.ID.value(t.getId()),
						ORG_ROLE_MENUTABLE.ORG_ROLE_ID.value(t.getOrgRoleId()),
						ORG_ROLE_MENUTABLE.ORG_ROLE_MENU_ID.value(t.getOrgRoleMenuId()));
				return insert;
			}
		});
	}

	public int edit(OrgRoleMenu orgRoleMenu) {
		if (orgRoleMenu == null || orgRoleMenu.getId() == null) {
			return 0;
		}
		return getDslTemplate().update(orgRoleMenu, new UpdateGenerateCallback<OrgRoleMenu>() {
			public Update generate(OrgRoleMenu t) {
				Update update = update(ORG_ROLE_MENUTABLE).set(
						ORG_ROLE_MENUTABLE.ORG_ROLE_ID.value(t.getOrgRoleId()),
						ORG_ROLE_MENUTABLE.ORG_ROLE_MENU_ID.value(t.getOrgRoleMenuId())).where(
						ORG_ROLE_MENUTABLE.ID.eq(t.getId()));
				return update;
			}
		});
	}

	public int deleteByKey(Integer pk) {
		if (pk == null) {
			return 0;
		}
		return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(ORG_ROLE_MENUTABLE).where(ORG_ROLE_MENUTABLE.ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if (pks == null || pks.length == 0) {
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(ORG_ROLE_MENUTABLE).where(ORG_ROLE_MENUTABLE.ID.in(t));
			}
		}, pks);
	}

	public OrgRoleMenu getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, OrgRoleMenu.class, new SelectGenerateCallback<Serializable>() {
			@SuppressWarnings("rawtypes")
			public Select generate(Serializable t) {
				return selectFrom(ORG_ROLE_MENUTABLE).where(ORG_ROLE_MENUTABLE.ID.eq(t));
			}
		});
	}

	public List<OrgRoleMenu> query(OrgRoleMenu orgRoleMenu, final OrderBy... orderBies) {
		if (orgRoleMenu == null) {
			orgRoleMenu = new OrgRoleMenu();
		}
		return getDslTemplate().query(orgRoleMenu, new SelectGenerateCallback<OrgRoleMenu>() {

			@SuppressWarnings("rawtypes")
			public Select generate(OrgRoleMenu t) {
				Select select = selectFrom(ORG_ROLE_MENUTABLE).where(
						and(
								ORG_ROLE_MENUTABLE.ORG_ROLE_ID.eq(t.getOrgRoleId()),
								ORG_ROLE_MENUTABLE.ORG_ROLE_MENU_ID.eq(t.getOrgRoleMenuId())));
				return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<OrgRoleMenu> queryPager(int start, int limit, OrgRoleMenu orgRoleMenu, final OrderBy... orderBies) {
		if (orgRoleMenu == null) {
			orgRoleMenu = new OrgRoleMenu();
		}
		return getDslTemplate().queryPager(start, limit, orgRoleMenu, false, new SelectGenerateCallback<OrgRoleMenu>() {

			public Select generate(OrgRoleMenu t) {
				Select select = MysqlSelect.selectFrom(ORG_ROLE_MENUTABLE).where(
						and(
								ORG_ROLE_MENUTABLE.ORG_ROLE_ID.eq(t.getOrgRoleId()),
								ORG_ROLE_MENUTABLE.ORG_ROLE_MENU_ID.eq(t.getOrgRoleMenuId())));
				return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys, List<OrgRoleMenu> orgRoleMenus) {
		if (CollectionUtil.isEmpty(orgRoleMenus)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, orgRoleMenus, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(ORG_ROLE_MENUTABLE).values(
						ORG_ROLE_MENUTABLE.ORG_ROLE_ID.value(new JdbcNamedParameter("orgRoleId")),
						ORG_ROLE_MENUTABLE.ORG_ROLE_MENU_ID.value(new JdbcNamedParameter("orgRoleMenuId")));
			}
		});
	}

	public int[] batchInsert(List<OrgRoleMenu> orgRoleMenus) {
		return batchInsert(true, orgRoleMenus);
	}

	public int[] batchUpdate(List<OrgRoleMenu> orgRoleMenus) {
		if (CollectionUtil.isEmpty(orgRoleMenus)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(orgRoleMenus, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(ORG_ROLE_MENUTABLE).set(
						ORG_ROLE_MENUTABLE.ORG_ROLE_ID.value(new JdbcNamedParameter("orgRoleId")),
						ORG_ROLE_MENUTABLE.ORG_ROLE_MENU_ID.value(new JdbcNamedParameter("orgRoleMenuId"))).where(
						ORG_ROLE_MENUTABLE.ID.eq(new JdbcNamedParameter("id")));
			}
		});
	}

	public int[] batchDelete(List<OrgRoleMenu> orgRoleMenus) {
		if (CollectionUtil.isEmpty(orgRoleMenus)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(orgRoleMenus, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(ORG_ROLE_MENUTABLE).where(and(
						ORG_ROLE_MENUTABLE.ID.eq(new JdbcNamedParameter("id")),
						ORG_ROLE_MENUTABLE.ORG_ROLE_ID.eq(new JdbcNamedParameter("orgRoleId")),
						ORG_ROLE_MENUTABLE.ORG_ROLE_MENU_ID.eq(new JdbcNamedParameter("orgRoleMenuId"))));
			}
		});
	}

	private Select addOrderByElements(Select select, OrderBy... orderBies) {
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
