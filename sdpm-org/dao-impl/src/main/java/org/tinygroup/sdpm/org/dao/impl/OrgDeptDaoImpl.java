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
import static org.tinygroup.sdpm.org.dao.constant.OrgDeptTable.*;
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
import org.tinygroup.sdpm.org.dao.pojo.OrgDept;
import org.tinygroup.sdpm.org.dao.OrgDeptDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class OrgDeptDaoImpl extends TinyDslDaoSupport implements OrgDeptDao {

	public OrgDept add(OrgDept orgDept) {
		return getDslTemplate().insertAndReturnKey(orgDept, new InsertGenerateCallback<OrgDept>() {
			public Insert generate(OrgDept t) {
				Insert insert = insertInto(ORG_DEPTTABLE).values(
					ORG_DEPTTABLE.ORG_DEPT_ID.value(t.getOrgDeptId()),
					ORG_DEPTTABLE.ORG_DEPT_NAME.value(t.getOrgDeptName()),
					ORG_DEPTTABLE.ORG_DEPT_PARENT.value(t.getOrgDeptParent()),
					ORG_DEPTTABLE.ORG_DEPT_PATH.value(t.getOrgDeptPath()),
					ORG_DEPTTABLE.ORG_DEPT_GRADE.value(t.getOrgDeptGrade()),
					ORG_DEPTTABLE.ORG_DEPT_ORDER.value(t.getOrgDeptOrder()),
					ORG_DEPTTABLE.ORG_DEPT_POSITION.value(t.getOrgDeptPosition()),
					ORG_DEPTTABLE.ORG_DEPT_MANAGER.value(t.getOrgDeptManager()));
				return insert;
			}
		});
	}

	public int edit(OrgDept orgDept) {
		if(orgDept == null || orgDept.getOrgDeptId() == null){
			return 0;
		}
		return getDslTemplate().update(orgDept, new UpdateGenerateCallback<OrgDept>() {
			public Update generate(OrgDept t) {
				Update update = update(ORG_DEPTTABLE).set(
					ORG_DEPTTABLE.ORG_DEPT_NAME.value(t.getOrgDeptName()),
					ORG_DEPTTABLE.ORG_DEPT_PARENT.value(t.getOrgDeptParent()),
					ORG_DEPTTABLE.ORG_DEPT_PATH.value(t.getOrgDeptPath()),
					ORG_DEPTTABLE.ORG_DEPT_GRADE.value(t.getOrgDeptGrade()),
					ORG_DEPTTABLE.ORG_DEPT_ORDER.value(t.getOrgDeptOrder()),
					ORG_DEPTTABLE.ORG_DEPT_POSITION.value(t.getOrgDeptPosition()),
					ORG_DEPTTABLE.ORG_DEPT_MANAGER.value(t.getOrgDeptManager())).where(
					ORG_DEPTTABLE.ORG_DEPT_ID.eq(t.getOrgDeptId()));
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
				return delete(ORG_DEPTTABLE).where(ORG_DEPTTABLE.ORG_DEPT_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(ORG_DEPTTABLE).where(ORG_DEPTTABLE.ORG_DEPT_ID.in(t));
		}
		},pks);
	}

	public OrgDept getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, OrgDept.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(ORG_DEPTTABLE).where(ORG_DEPTTABLE.ORG_DEPT_ID.eq(t));
			}
		});
	}

	public List<OrgDept> query(OrgDept orgDept) {
		if(orgDept==null){
			orgDept=new OrgDept();
		}
		return getDslTemplate().query(orgDept, new SelectGenerateCallback<OrgDept>() {

			@SuppressWarnings("rawtypes")
			public Select generate(OrgDept t) {
				return selectFrom(ORG_DEPTTABLE).where(
				and(
					ORG_DEPTTABLE.ORG_DEPT_NAME.eq(t.getOrgDeptName()),
					ORG_DEPTTABLE.ORG_DEPT_PARENT.eq(t.getOrgDeptParent()),
					ORG_DEPTTABLE.ORG_DEPT_PATH.eq(t.getOrgDeptPath()),
					ORG_DEPTTABLE.ORG_DEPT_GRADE.eq(t.getOrgDeptGrade()),
					ORG_DEPTTABLE.ORG_DEPT_ORDER.eq(t.getOrgDeptOrder()),
					ORG_DEPTTABLE.ORG_DEPT_POSITION.eq(t.getOrgDeptPosition()),
					ORG_DEPTTABLE.ORG_DEPT_MANAGER.eq(t.getOrgDeptManager())));
			}
		});
	}

	public Pager<OrgDept> queryPager(int start,int limit ,OrgDept orgDept) {
		if(orgDept==null){
			orgDept=new OrgDept();
		}
		return getDslTemplate().queryPager(start, limit, orgDept, false, new SelectGenerateCallback<OrgDept>() {

			public Select generate(OrgDept t) {
				return MysqlSelect.selectFrom(ORG_DEPTTABLE).where(
				and(
					ORG_DEPTTABLE.ORG_DEPT_NAME.eq(t.getOrgDeptName()),
					ORG_DEPTTABLE.ORG_DEPT_PARENT.eq(t.getOrgDeptParent()),
					ORG_DEPTTABLE.ORG_DEPT_PATH.eq(t.getOrgDeptPath()),
					ORG_DEPTTABLE.ORG_DEPT_GRADE.eq(t.getOrgDeptGrade()),
					ORG_DEPTTABLE.ORG_DEPT_ORDER.eq(t.getOrgDeptOrder()),
					ORG_DEPTTABLE.ORG_DEPT_POSITION.eq(t.getOrgDeptPosition()),
					ORG_DEPTTABLE.ORG_DEPT_MANAGER.eq(t.getOrgDeptManager())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<OrgDept> orgDepts) {
		if (CollectionUtil.isEmpty(orgDepts)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, orgDepts, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(ORG_DEPTTABLE).values(
					ORG_DEPTTABLE.ORG_DEPT_NAME.value(new JdbcNamedParameter("orgDeptName")),
					ORG_DEPTTABLE.ORG_DEPT_PARENT.value(new JdbcNamedParameter("orgDeptParent")),
					ORG_DEPTTABLE.ORG_DEPT_PATH.value(new JdbcNamedParameter("orgDeptPath")),
					ORG_DEPTTABLE.ORG_DEPT_GRADE.value(new JdbcNamedParameter("orgDeptGrade")),
					ORG_DEPTTABLE.ORG_DEPT_ORDER.value(new JdbcNamedParameter("orgDeptOrder")),
					ORG_DEPTTABLE.ORG_DEPT_POSITION.value(new JdbcNamedParameter("orgDeptPosition")),
					ORG_DEPTTABLE.ORG_DEPT_MANAGER.value(new JdbcNamedParameter("orgDeptManager")));
			}
		});
	}

	public int[] batchInsert(List<OrgDept> orgDepts){
			return batchInsert(true ,orgDepts);
	}

	public int[] batchUpdate(List<OrgDept> orgDepts) {
		if (CollectionUtil.isEmpty(orgDepts)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(orgDepts, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(ORG_DEPTTABLE).set(
					ORG_DEPTTABLE.ORG_DEPT_NAME.value(new JdbcNamedParameter("orgDeptName")),
					ORG_DEPTTABLE.ORG_DEPT_PARENT.value(new JdbcNamedParameter("orgDeptParent")),
					ORG_DEPTTABLE.ORG_DEPT_PATH.value(new JdbcNamedParameter("orgDeptPath")),
					ORG_DEPTTABLE.ORG_DEPT_GRADE.value(new JdbcNamedParameter("orgDeptGrade")),
					ORG_DEPTTABLE.ORG_DEPT_ORDER.value(new JdbcNamedParameter("orgDeptOrder")),
					ORG_DEPTTABLE.ORG_DEPT_POSITION.value(new JdbcNamedParameter("orgDeptPosition")),
					ORG_DEPTTABLE.ORG_DEPT_MANAGER.value(new JdbcNamedParameter("orgDeptManager"))).where(
				ORG_DEPTTABLE.ORG_DEPT_ID.eq(new JdbcNamedParameter("orgDeptId")));
			}
		});
	}

	public int[] batchDelete(List<OrgDept> orgDepts) {
		if (CollectionUtil.isEmpty(orgDepts)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(orgDepts, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(ORG_DEPTTABLE).where(and(
				ORG_DEPTTABLE.ORG_DEPT_ID.eq(new JdbcNamedParameter("orgDeptId")),
				ORG_DEPTTABLE.ORG_DEPT_NAME.eq(new JdbcNamedParameter("orgDeptName")),
				ORG_DEPTTABLE.ORG_DEPT_PARENT.eq(new JdbcNamedParameter("orgDeptParent")),
				ORG_DEPTTABLE.ORG_DEPT_PATH.eq(new JdbcNamedParameter("orgDeptPath")),
				ORG_DEPTTABLE.ORG_DEPT_GRADE.eq(new JdbcNamedParameter("orgDeptGrade")),
				ORG_DEPTTABLE.ORG_DEPT_ORDER.eq(new JdbcNamedParameter("orgDeptOrder")),
				ORG_DEPTTABLE.ORG_DEPT_POSITION.eq(new JdbcNamedParameter("orgDeptPosition")),
				ORG_DEPTTABLE.ORG_DEPT_MANAGER.eq(new JdbcNamedParameter("orgDeptManager"))));
			}
		});
	}

}
