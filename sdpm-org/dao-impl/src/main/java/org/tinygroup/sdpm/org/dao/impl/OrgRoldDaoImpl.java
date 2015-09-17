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
import static org.tinygroup.sdpm.org.dao.constant.OrgRoldTable.*;
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
import org.tinygroup.sdpm.org.dao.pojo.OrgRold;
import org.tinygroup.sdpm.org.dao.OrgRoldDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class OrgRoldDaoImpl extends TinyDslDaoSupport implements OrgRoldDao {

	public OrgRold add(OrgRold orgRold) {
		return getDslTemplate().insertAndReturnKey(orgRold, new InsertGenerateCallback<OrgRold>() {
			public Insert generate(OrgRold t) {
				Insert insert = insertInto(ORG_ROLDTABLE).values(
					ORG_ROLDTABLE.ORG_ROLE_ID.value(t.getOrgRoleId()),
					ORG_ROLDTABLE.ORG_ROLE_NAME.value(t.getOrgRoleName()),
					ORG_ROLDTABLE.ORG_ROLE_REMARKS.value(t.getOrgRoleRemarks()),
					ORG_ROLDTABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}

	public int edit(OrgRold orgRold) {
		if(orgRold == null || orgRold.getOrgRoleId() == null){
			return 0;
		}
		return getDslTemplate().update(orgRold, new UpdateGenerateCallback<OrgRold>() {
			public Update generate(OrgRold t) {
				Update update = update(ORG_ROLDTABLE).set(
					ORG_ROLDTABLE.ORG_ROLE_NAME.value(t.getOrgRoleName()),
					ORG_ROLDTABLE.ORG_ROLE_REMARKS.value(t.getOrgRoleRemarks()),
					ORG_ROLDTABLE.DELETED.value(t.getDeleted())).where(
					ORG_ROLDTABLE.ORG_ROLE_ID.eq(t.getOrgRoleId()));
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
				return delete(ORG_ROLDTABLE).where(ORG_ROLDTABLE.ORG_ROLE_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(ORG_ROLDTABLE).where(ORG_ROLDTABLE.ORG_ROLE_ID.in(t));
		}
		},pks);
	}

	public OrgRold getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, OrgRold.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(ORG_ROLDTABLE).where(ORG_ROLDTABLE.ORG_ROLE_ID.eq(t));
			}
		});
	}

	public List<OrgRold> query(OrgRold orgRold) {
		if(orgRold==null){
			orgRold=new OrgRold();
		}
		return getDslTemplate().query(orgRold, new SelectGenerateCallback<OrgRold>() {

			@SuppressWarnings("rawtypes")
			public Select generate(OrgRold t) {
				return selectFrom(ORG_ROLDTABLE).where(
				and(
					ORG_ROLDTABLE.ORG_ROLE_NAME.eq(t.getOrgRoleName()),
					ORG_ROLDTABLE.ORG_ROLE_REMARKS.eq(t.getOrgRoleRemarks()),
					ORG_ROLDTABLE.DELETED.eq(t.getDeleted())));
			}
		});
	}

	public Pager<OrgRold> queryPager(int start,int limit ,OrgRold orgRold) {
		if(orgRold==null){
			orgRold=new OrgRold();
		}
		return getDslTemplate().queryPager(start, limit, orgRold, false, new SelectGenerateCallback<OrgRold>() {

			public Select generate(OrgRold t) {
				return MysqlSelect.selectFrom(ORG_ROLDTABLE).where(
				and(
					ORG_ROLDTABLE.ORG_ROLE_NAME.eq(t.getOrgRoleName()),
					ORG_ROLDTABLE.ORG_ROLE_REMARKS.eq(t.getOrgRoleRemarks()),
					ORG_ROLDTABLE.DELETED.eq(t.getDeleted())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<OrgRold> orgRolds) {
		if (CollectionUtil.isEmpty(orgRolds)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, orgRolds, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(ORG_ROLDTABLE).values(
					ORG_ROLDTABLE.ORG_ROLE_NAME.value(new JdbcNamedParameter("orgRoleName")),
					ORG_ROLDTABLE.ORG_ROLE_REMARKS.value(new JdbcNamedParameter("orgRoleRemarks")),
					ORG_ROLDTABLE.DELETED.value(new JdbcNamedParameter("deleted")));
			}
		});
	}

	public int[] batchInsert(List<OrgRold> orgRolds){
			return batchInsert(true ,orgRolds);
	}

	public int[] batchUpdate(List<OrgRold> orgRolds) {
		if (CollectionUtil.isEmpty(orgRolds)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(orgRolds, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(ORG_ROLDTABLE).set(
					ORG_ROLDTABLE.ORG_ROLE_NAME.value(new JdbcNamedParameter("orgRoleName")),
					ORG_ROLDTABLE.ORG_ROLE_REMARKS.value(new JdbcNamedParameter("orgRoleRemarks")),
					ORG_ROLDTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
				ORG_ROLDTABLE.ORG_ROLE_ID.eq(new JdbcNamedParameter("orgRoleId")));
			}
		});
	}

	public int[] batchDelete(List<OrgRold> orgRolds) {
		if (CollectionUtil.isEmpty(orgRolds)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(orgRolds, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(ORG_ROLDTABLE).where(and(
				ORG_ROLDTABLE.ORG_ROLE_ID.eq(new JdbcNamedParameter("orgRoleId")),
				ORG_ROLDTABLE.ORG_ROLE_NAME.eq(new JdbcNamedParameter("orgRoleName")),
				ORG_ROLDTABLE.ORG_ROLE_REMARKS.eq(new JdbcNamedParameter("orgRoleRemarks")),
				ORG_ROLDTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
			}
		});
	}

}
