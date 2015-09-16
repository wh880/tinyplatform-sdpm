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
import static org.tinygroup.sdpm.org.dao.constant.OrgCompanyyTable.*;
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
import org.tinygroup.sdpm.org.dao.pojo.OrgCompanyy;
import org.tinygroup.sdpm.org.dao.OrgCompanyyDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class OrgCompanyyDaoImpl extends TinyDslDaoSupport implements OrgCompanyyDao {

	public OrgCompanyy add(OrgCompanyy orgCompanyy) {
		return getDslTemplate().insertAndReturnKey(orgCompanyy, new InsertGenerateCallback<OrgCompanyy>() {
			public Insert generate(OrgCompanyy t) {
				Insert insert = insertInto(ORG_COMPANYYTABLE).values(
					ORG_COMPANYYTABLE.ORG_COMPANY_ID.value(t.getOrgCompanyId()),
					ORG_COMPANYYTABLE.ORG_COMPANY_NAME.value(t.getOrgCompanyName()),
					ORG_COMPANYYTABLE.ORG_COMPANY_PHONE.value(t.getOrgCompanyPhone()),
					ORG_COMPANYYTABLE.ORG_COMPANY_FAX.value(t.getOrgCompanyFax()),
					ORG_COMPANYYTABLE.ORG_COMPANY_ADDRESS.value(t.getOrgCompanyAddress()),
					ORG_COMPANYYTABLE.ORG_COMPANY_ZIPCODE.value(t.getOrgCompanyZipcode()),
					ORG_COMPANYYTABLE.ORG_COMPANY_WEBSITE.value(t.getOrgCompanyWebsite()),
					ORG_COMPANYYTABLE.ORG_COMPANY_BACKYARD.value(t.getOrgCompanyBackyard()),
					ORG_COMPANYYTABLE.ORG_COMPANY_GUEST.value(t.getOrgCompanyGuest()),
					ORG_COMPANYYTABLE.ORG_COMPANY_ADMINS.value(t.getOrgCompanyAdmins()),
					ORG_COMPANYYTABLE.ORG_COMPANY_DELETED.value(t.getOrgCompanyDeleted()));
				return insert;
			}
		});
	}

	public int edit(OrgCompanyy orgCompanyy) {
		if(orgCompanyy == null || orgCompanyy.getOrgCompanyId() == null){
			return 0;
		}
		return getDslTemplate().update(orgCompanyy, new UpdateGenerateCallback<OrgCompanyy>() {
			public Update generate(OrgCompanyy t) {
				Update update = update(ORG_COMPANYYTABLE).set(
					ORG_COMPANYYTABLE.ORG_COMPANY_NAME.value(t.getOrgCompanyName()),
					ORG_COMPANYYTABLE.ORG_COMPANY_PHONE.value(t.getOrgCompanyPhone()),
					ORG_COMPANYYTABLE.ORG_COMPANY_FAX.value(t.getOrgCompanyFax()),
					ORG_COMPANYYTABLE.ORG_COMPANY_ADDRESS.value(t.getOrgCompanyAddress()),
					ORG_COMPANYYTABLE.ORG_COMPANY_ZIPCODE.value(t.getOrgCompanyZipcode()),
					ORG_COMPANYYTABLE.ORG_COMPANY_WEBSITE.value(t.getOrgCompanyWebsite()),
					ORG_COMPANYYTABLE.ORG_COMPANY_BACKYARD.value(t.getOrgCompanyBackyard()),
					ORG_COMPANYYTABLE.ORG_COMPANY_GUEST.value(t.getOrgCompanyGuest()),
					ORG_COMPANYYTABLE.ORG_COMPANY_ADMINS.value(t.getOrgCompanyAdmins()),
					ORG_COMPANYYTABLE.ORG_COMPANY_DELETED.value(t.getOrgCompanyDeleted())).where(
					ORG_COMPANYYTABLE.ORG_COMPANY_ID.eq(t.getOrgCompanyId()));
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
				return delete(ORG_COMPANYYTABLE).where(ORG_COMPANYYTABLE.ORG_COMPANY_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(ORG_COMPANYYTABLE).where(ORG_COMPANYYTABLE.ORG_COMPANY_ID.in(t));
		}
		},pks);
	}

	public OrgCompanyy getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, OrgCompanyy.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(ORG_COMPANYYTABLE).where(ORG_COMPANYYTABLE.ORG_COMPANY_ID.eq(t));
			}
		});
	}

	public List<OrgCompanyy> query(OrgCompanyy orgCompanyy) {
		if(orgCompanyy==null){
			orgCompanyy=new OrgCompanyy();
		}
		return getDslTemplate().query(orgCompanyy, new SelectGenerateCallback<OrgCompanyy>() {

			@SuppressWarnings("rawtypes")
			public Select generate(OrgCompanyy t) {
				return selectFrom(ORG_COMPANYYTABLE).where(
				and(
					ORG_COMPANYYTABLE.ORG_COMPANY_NAME.eq(t.getOrgCompanyName()),
					ORG_COMPANYYTABLE.ORG_COMPANY_PHONE.eq(t.getOrgCompanyPhone()),
					ORG_COMPANYYTABLE.ORG_COMPANY_FAX.eq(t.getOrgCompanyFax()),
					ORG_COMPANYYTABLE.ORG_COMPANY_ADDRESS.eq(t.getOrgCompanyAddress()),
					ORG_COMPANYYTABLE.ORG_COMPANY_ZIPCODE.eq(t.getOrgCompanyZipcode()),
					ORG_COMPANYYTABLE.ORG_COMPANY_WEBSITE.eq(t.getOrgCompanyWebsite()),
					ORG_COMPANYYTABLE.ORG_COMPANY_BACKYARD.eq(t.getOrgCompanyBackyard()),
					ORG_COMPANYYTABLE.ORG_COMPANY_GUEST.eq(t.getOrgCompanyGuest()),
					ORG_COMPANYYTABLE.ORG_COMPANY_ADMINS.eq(t.getOrgCompanyAdmins()),
					ORG_COMPANYYTABLE.ORG_COMPANY_DELETED.eq(t.getOrgCompanyDeleted())));
			}
		});
	}

	public Pager<OrgCompanyy> queryPager(int start,int limit ,OrgCompanyy orgCompanyy) {
		if(orgCompanyy==null){
			orgCompanyy=new OrgCompanyy();
		}
		return getDslTemplate().queryPager(start, limit, orgCompanyy, false, new SelectGenerateCallback<OrgCompanyy>() {

			public Select generate(OrgCompanyy t) {
				return MysqlSelect.selectFrom(ORG_COMPANYYTABLE).where(
				and(
					ORG_COMPANYYTABLE.ORG_COMPANY_NAME.eq(t.getOrgCompanyName()),
					ORG_COMPANYYTABLE.ORG_COMPANY_PHONE.eq(t.getOrgCompanyPhone()),
					ORG_COMPANYYTABLE.ORG_COMPANY_FAX.eq(t.getOrgCompanyFax()),
					ORG_COMPANYYTABLE.ORG_COMPANY_ADDRESS.eq(t.getOrgCompanyAddress()),
					ORG_COMPANYYTABLE.ORG_COMPANY_ZIPCODE.eq(t.getOrgCompanyZipcode()),
					ORG_COMPANYYTABLE.ORG_COMPANY_WEBSITE.eq(t.getOrgCompanyWebsite()),
					ORG_COMPANYYTABLE.ORG_COMPANY_BACKYARD.eq(t.getOrgCompanyBackyard()),
					ORG_COMPANYYTABLE.ORG_COMPANY_GUEST.eq(t.getOrgCompanyGuest()),
					ORG_COMPANYYTABLE.ORG_COMPANY_ADMINS.eq(t.getOrgCompanyAdmins()),
					ORG_COMPANYYTABLE.ORG_COMPANY_DELETED.eq(t.getOrgCompanyDeleted())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<OrgCompanyy> orgCompanyys) {
		if (CollectionUtil.isEmpty(orgCompanyys)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, orgCompanyys, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(ORG_COMPANYYTABLE).values(
					ORG_COMPANYYTABLE.ORG_COMPANY_NAME.value(new JdbcNamedParameter("orgCompanyName")),
					ORG_COMPANYYTABLE.ORG_COMPANY_PHONE.value(new JdbcNamedParameter("orgCompanyPhone")),
					ORG_COMPANYYTABLE.ORG_COMPANY_FAX.value(new JdbcNamedParameter("orgCompanyFax")),
					ORG_COMPANYYTABLE.ORG_COMPANY_ADDRESS.value(new JdbcNamedParameter("orgCompanyAddress")),
					ORG_COMPANYYTABLE.ORG_COMPANY_ZIPCODE.value(new JdbcNamedParameter("orgCompanyZipcode")),
					ORG_COMPANYYTABLE.ORG_COMPANY_WEBSITE.value(new JdbcNamedParameter("orgCompanyWebsite")),
					ORG_COMPANYYTABLE.ORG_COMPANY_BACKYARD.value(new JdbcNamedParameter("orgCompanyBackyard")),
					ORG_COMPANYYTABLE.ORG_COMPANY_GUEST.value(new JdbcNamedParameter("orgCompanyGuest")),
					ORG_COMPANYYTABLE.ORG_COMPANY_ADMINS.value(new JdbcNamedParameter("orgCompanyAdmins")),
					ORG_COMPANYYTABLE.ORG_COMPANY_DELETED.value(new JdbcNamedParameter("orgCompanyDeleted")));
			}
		});
	}

	public int[] batchInsert(List<OrgCompanyy> orgCompanyys){
			return batchInsert(true ,orgCompanyys);
	}

	public int[] batchUpdate(List<OrgCompanyy> orgCompanyys) {
		if (CollectionUtil.isEmpty(orgCompanyys)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(orgCompanyys, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(ORG_COMPANYYTABLE).set(
					ORG_COMPANYYTABLE.ORG_COMPANY_NAME.value(new JdbcNamedParameter("orgCompanyName")),
					ORG_COMPANYYTABLE.ORG_COMPANY_PHONE.value(new JdbcNamedParameter("orgCompanyPhone")),
					ORG_COMPANYYTABLE.ORG_COMPANY_FAX.value(new JdbcNamedParameter("orgCompanyFax")),
					ORG_COMPANYYTABLE.ORG_COMPANY_ADDRESS.value(new JdbcNamedParameter("orgCompanyAddress")),
					ORG_COMPANYYTABLE.ORG_COMPANY_ZIPCODE.value(new JdbcNamedParameter("orgCompanyZipcode")),
					ORG_COMPANYYTABLE.ORG_COMPANY_WEBSITE.value(new JdbcNamedParameter("orgCompanyWebsite")),
					ORG_COMPANYYTABLE.ORG_COMPANY_BACKYARD.value(new JdbcNamedParameter("orgCompanyBackyard")),
					ORG_COMPANYYTABLE.ORG_COMPANY_GUEST.value(new JdbcNamedParameter("orgCompanyGuest")),
					ORG_COMPANYYTABLE.ORG_COMPANY_ADMINS.value(new JdbcNamedParameter("orgCompanyAdmins")),
					ORG_COMPANYYTABLE.ORG_COMPANY_DELETED.value(new JdbcNamedParameter("orgCompanyDeleted"))).where(
				ORG_COMPANYYTABLE.ORG_COMPANY_ID.eq(new JdbcNamedParameter("orgCompanyId")));
			}
		});
	}

	public int[] batchDelete(List<OrgCompanyy> orgCompanyys) {
		if (CollectionUtil.isEmpty(orgCompanyys)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(orgCompanyys, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(ORG_COMPANYYTABLE).where(and(
				ORG_COMPANYYTABLE.ORG_COMPANY_ID.eq(new JdbcNamedParameter("orgCompanyId")),
				ORG_COMPANYYTABLE.ORG_COMPANY_NAME.eq(new JdbcNamedParameter("orgCompanyName")),
				ORG_COMPANYYTABLE.ORG_COMPANY_PHONE.eq(new JdbcNamedParameter("orgCompanyPhone")),
				ORG_COMPANYYTABLE.ORG_COMPANY_FAX.eq(new JdbcNamedParameter("orgCompanyFax")),
				ORG_COMPANYYTABLE.ORG_COMPANY_ADDRESS.eq(new JdbcNamedParameter("orgCompanyAddress")),
				ORG_COMPANYYTABLE.ORG_COMPANY_ZIPCODE.eq(new JdbcNamedParameter("orgCompanyZipcode")),
				ORG_COMPANYYTABLE.ORG_COMPANY_WEBSITE.eq(new JdbcNamedParameter("orgCompanyWebsite")),
				ORG_COMPANYYTABLE.ORG_COMPANY_BACKYARD.eq(new JdbcNamedParameter("orgCompanyBackyard")),
				ORG_COMPANYYTABLE.ORG_COMPANY_GUEST.eq(new JdbcNamedParameter("orgCompanyGuest")),
				ORG_COMPANYYTABLE.ORG_COMPANY_ADMINS.eq(new JdbcNamedParameter("orgCompanyAdmins")),
				ORG_COMPANYYTABLE.ORG_COMPANY_DELETED.eq(new JdbcNamedParameter("orgCompanyDeleted"))));
			}
		});
	}

}
