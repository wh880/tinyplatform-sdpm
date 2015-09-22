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
import org.tinygroup.sdpm.org.dao.OrgCompanyDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgCompany;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.org.dao.constant.OrgCompanyTable.ORG_COMPANYTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class OrgCompanyDaoImpl extends TinyDslDaoSupport implements OrgCompanyDao {

	public OrgCompany add(OrgCompany orgCompany) {
		return getDslTemplate().insertAndReturnKey(orgCompany, new InsertGenerateCallback<OrgCompany>() {
			public Insert generate(OrgCompany t) {
				Insert insert = insertInto(ORG_COMPANYTABLE).values(
					ORG_COMPANYTABLE.ORG_COMPANY_ID.value(t.getOrgCompanyId()),
					ORG_COMPANYTABLE.ORG_COMPANY_NAME.value(t.getOrgCompanyName()),
					ORG_COMPANYTABLE.ORG_COMPANY_PHONE.value(t.getOrgCompanyPhone()),
					ORG_COMPANYTABLE.ORG_COMPANY_FAX.value(t.getOrgCompanyFax()),
					ORG_COMPANYTABLE.ORG_COMPANY_ADDRESS.value(t.getOrgCompanyAddress()),
					ORG_COMPANYTABLE.ORG_COMPANY_ZIPCODE.value(t.getOrgCompanyZipCode()),
					ORG_COMPANYTABLE.ORG_COMPANY_WEBSITE.value(t.getOrgCompanyWebsite()),
					ORG_COMPANYTABLE.ORG_COMPANY_BACKYARD.value(t.getOrgCompanyBackyard()),
					ORG_COMPANYTABLE.ORG_COMPANY_GUEST.value(t.getOrgCompanyGuest()),
					ORG_COMPANYTABLE.ORG_COMPANY_ADMINS.value(t.getOrgCompanyAdmins()),
					ORG_COMPANYTABLE.ORG_COMPANY_DELETED.value(t.getOrgCompanyDeleted()));
				return insert;
			}
		});
	}

	public int edit(OrgCompany orgCompany) {
		if(orgCompany == null || orgCompany.getOrgCompanyId() == null){
			return 0;
		}
		return getDslTemplate().update(orgCompany, new UpdateGenerateCallback<OrgCompany>() {
			public Update generate(OrgCompany t) {
				Update update = update(ORG_COMPANYTABLE).set(
					ORG_COMPANYTABLE.ORG_COMPANY_NAME.value(t.getOrgCompanyName()),
					ORG_COMPANYTABLE.ORG_COMPANY_PHONE.value(t.getOrgCompanyPhone()),
					ORG_COMPANYTABLE.ORG_COMPANY_FAX.value(t.getOrgCompanyFax()),
					ORG_COMPANYTABLE.ORG_COMPANY_ADDRESS.value(t.getOrgCompanyAddress()),
					ORG_COMPANYTABLE.ORG_COMPANY_ZIPCODE.value(t.getOrgCompanyZipCode()),
					ORG_COMPANYTABLE.ORG_COMPANY_WEBSITE.value(t.getOrgCompanyWebsite()),
					ORG_COMPANYTABLE.ORG_COMPANY_BACKYARD.value(t.getOrgCompanyBackyard()),
					ORG_COMPANYTABLE.ORG_COMPANY_GUEST.value(t.getOrgCompanyGuest()),
					ORG_COMPANYTABLE.ORG_COMPANY_ADMINS.value(t.getOrgCompanyAdmins()),
					ORG_COMPANYTABLE.ORG_COMPANY_DELETED.value(t.getOrgCompanyDeleted())).where(
					ORG_COMPANYTABLE.ORG_COMPANY_ID.eq(t.getOrgCompanyId()));
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
				return delete(ORG_COMPANYTABLE).where(ORG_COMPANYTABLE.ORG_COMPANY_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(ORG_COMPANYTABLE).where(ORG_COMPANYTABLE.ORG_COMPANY_ID.in(t));
		}
		},pks);
	}

	public OrgCompany getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, OrgCompany.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(ORG_COMPANYTABLE).where(ORG_COMPANYTABLE.ORG_COMPANY_ID.eq(t));
			}
		});
	}

	public List<OrgCompany> query(OrgCompany orgCompany, final OrderBy... orderBies) {
		if(orgCompany==null){
			orgCompany=new OrgCompany();
		}
		return getDslTemplate().query(orgCompany, new SelectGenerateCallback<OrgCompany>() {

			@SuppressWarnings("rawtypes")
			public Select generate(OrgCompany t) {
				Select select = selectFrom(ORG_COMPANYTABLE).where(
				and(
					ORG_COMPANYTABLE.ORG_COMPANY_NAME.eq(t.getOrgCompanyName()),
					ORG_COMPANYTABLE.ORG_COMPANY_PHONE.eq(t.getOrgCompanyPhone()),
					ORG_COMPANYTABLE.ORG_COMPANY_FAX.eq(t.getOrgCompanyFax()),
					ORG_COMPANYTABLE.ORG_COMPANY_ADDRESS.eq(t.getOrgCompanyAddress()),
					ORG_COMPANYTABLE.ORG_COMPANY_ZIPCODE.eq(t.getOrgCompanyZipCode()),
					ORG_COMPANYTABLE.ORG_COMPANY_WEBSITE.eq(t.getOrgCompanyWebsite()),
					ORG_COMPANYTABLE.ORG_COMPANY_BACKYARD.eq(t.getOrgCompanyBackyard()),
					ORG_COMPANYTABLE.ORG_COMPANY_GUEST.eq(t.getOrgCompanyGuest()),
					ORG_COMPANYTABLE.ORG_COMPANY_ADMINS.eq(t.getOrgCompanyAdmins()),
					ORG_COMPANYTABLE.ORG_COMPANY_DELETED.eq(t.getOrgCompanyDeleted())));
				return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<OrgCompany> queryPager(int start, int limit, OrgCompany orgCompany, final OrderBy... orderBies) {
		if(orgCompany==null){
			orgCompany=new OrgCompany();
		}
		return getDslTemplate().queryPager(start, limit, orgCompany, false, new SelectGenerateCallback<OrgCompany>() {

			public Select generate(OrgCompany t) {
				Select select = MysqlSelect.selectFrom(ORG_COMPANYTABLE).where(
				and(
					ORG_COMPANYTABLE.ORG_COMPANY_NAME.eq(t.getOrgCompanyName()),
					ORG_COMPANYTABLE.ORG_COMPANY_PHONE.eq(t.getOrgCompanyPhone()),
					ORG_COMPANYTABLE.ORG_COMPANY_FAX.eq(t.getOrgCompanyFax()),
					ORG_COMPANYTABLE.ORG_COMPANY_ADDRESS.eq(t.getOrgCompanyAddress()),
					ORG_COMPANYTABLE.ORG_COMPANY_ZIPCODE.eq(t.getOrgCompanyZipCode()),
					ORG_COMPANYTABLE.ORG_COMPANY_WEBSITE.eq(t.getOrgCompanyWebsite()),
					ORG_COMPANYTABLE.ORG_COMPANY_BACKYARD.eq(t.getOrgCompanyBackyard()),
					ORG_COMPANYTABLE.ORG_COMPANY_GUEST.eq(t.getOrgCompanyGuest()),
					ORG_COMPANYTABLE.ORG_COMPANY_ADMINS.eq(t.getOrgCompanyAdmins()),
					ORG_COMPANYTABLE.ORG_COMPANY_DELETED.eq(t.getOrgCompanyDeleted())));
				return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<OrgCompany> orgCompanys) {
		if (CollectionUtil.isEmpty(orgCompanys)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, orgCompanys, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(ORG_COMPANYTABLE).values(
					ORG_COMPANYTABLE.ORG_COMPANY_NAME.value(new JdbcNamedParameter("orgCompanyName")),
					ORG_COMPANYTABLE.ORG_COMPANY_PHONE.value(new JdbcNamedParameter("orgCompanyPhone")),
					ORG_COMPANYTABLE.ORG_COMPANY_FAX.value(new JdbcNamedParameter("orgCompanyFax")),
					ORG_COMPANYTABLE.ORG_COMPANY_ADDRESS.value(new JdbcNamedParameter("orgCompanyAddress")),
					ORG_COMPANYTABLE.ORG_COMPANY_ZIPCODE.value(new JdbcNamedParameter("orgCompanyZipCode")),
					ORG_COMPANYTABLE.ORG_COMPANY_WEBSITE.value(new JdbcNamedParameter("orgCompanyWebsite")),
					ORG_COMPANYTABLE.ORG_COMPANY_BACKYARD.value(new JdbcNamedParameter("orgCompanyBackyard")),
					ORG_COMPANYTABLE.ORG_COMPANY_GUEST.value(new JdbcNamedParameter("orgCompanyGuest")),
					ORG_COMPANYTABLE.ORG_COMPANY_ADMINS.value(new JdbcNamedParameter("orgCompanyAdmins")),
					ORG_COMPANYTABLE.ORG_COMPANY_DELETED.value(new JdbcNamedParameter("orgCompanyDeleted")));
			}
		});
	}

	public int[] batchInsert(List<OrgCompany> orgCompanys){
			return batchInsert(true ,orgCompanys);
	}

	public int[] batchUpdate(List<OrgCompany> orgCompanys) {
		if (CollectionUtil.isEmpty(orgCompanys)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(orgCompanys, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(ORG_COMPANYTABLE).set(
					ORG_COMPANYTABLE.ORG_COMPANY_NAME.value(new JdbcNamedParameter("orgCompanyName")),
					ORG_COMPANYTABLE.ORG_COMPANY_PHONE.value(new JdbcNamedParameter("orgCompanyPhone")),
					ORG_COMPANYTABLE.ORG_COMPANY_FAX.value(new JdbcNamedParameter("orgCompanyFax")),
					ORG_COMPANYTABLE.ORG_COMPANY_ADDRESS.value(new JdbcNamedParameter("orgCompanyAddress")),
					ORG_COMPANYTABLE.ORG_COMPANY_ZIPCODE.value(new JdbcNamedParameter("orgCompanyZipCode")),
					ORG_COMPANYTABLE.ORG_COMPANY_WEBSITE.value(new JdbcNamedParameter("orgCompanyWebsite")),
					ORG_COMPANYTABLE.ORG_COMPANY_BACKYARD.value(new JdbcNamedParameter("orgCompanyBackyard")),
					ORG_COMPANYTABLE.ORG_COMPANY_GUEST.value(new JdbcNamedParameter("orgCompanyGuest")),
					ORG_COMPANYTABLE.ORG_COMPANY_ADMINS.value(new JdbcNamedParameter("orgCompanyAdmins")),
					ORG_COMPANYTABLE.ORG_COMPANY_DELETED.value(new JdbcNamedParameter("orgCompanyDeleted"))).where(
				ORG_COMPANYTABLE.ORG_COMPANY_ID.eq(new JdbcNamedParameter("orgCompanyId")));
			}
		});
	}

	public int[] batchDelete(List<OrgCompany> orgCompanys) {
		if (CollectionUtil.isEmpty(orgCompanys)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(orgCompanys, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(ORG_COMPANYTABLE).where(and(
				ORG_COMPANYTABLE.ORG_COMPANY_ID.eq(new JdbcNamedParameter("orgCompanyId")),
				ORG_COMPANYTABLE.ORG_COMPANY_NAME.eq(new JdbcNamedParameter("orgCompanyName")),
				ORG_COMPANYTABLE.ORG_COMPANY_PHONE.eq(new JdbcNamedParameter("orgCompanyPhone")),
				ORG_COMPANYTABLE.ORG_COMPANY_FAX.eq(new JdbcNamedParameter("orgCompanyFax")),
				ORG_COMPANYTABLE.ORG_COMPANY_ADDRESS.eq(new JdbcNamedParameter("orgCompanyAddress")),
				ORG_COMPANYTABLE.ORG_COMPANY_ZIPCODE.eq(new JdbcNamedParameter("orgCompanyZipCode")),
				ORG_COMPANYTABLE.ORG_COMPANY_WEBSITE.eq(new JdbcNamedParameter("orgCompanyWebsite")),
				ORG_COMPANYTABLE.ORG_COMPANY_BACKYARD.eq(new JdbcNamedParameter("orgCompanyBackyard")),
				ORG_COMPANYTABLE.ORG_COMPANY_GUEST.eq(new JdbcNamedParameter("orgCompanyGuest")),
				ORG_COMPANYTABLE.ORG_COMPANY_ADMINS.eq(new JdbcNamedParameter("orgCompanyAdmins")),
				ORG_COMPANYTABLE.ORG_COMPANY_DELETED.eq(new JdbcNamedParameter("orgCompanyDeleted"))));
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
