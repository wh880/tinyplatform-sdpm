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
import static org.tinygroup.sdpm.org.dao.constant.OrgWhiteListTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;

import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.sdpm.org.dao.pojo.OrgWhiteList;
import org.tinygroup.sdpm.org.dao.OrgWhiteListDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
public class OrgWhiteListDaoImpl extends TinyDslDaoSupport implements OrgWhiteListDao {

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrgWhiteList add(OrgWhiteList orgWhiteList) {
		return getDslTemplate().insertAndReturnKey(orgWhiteList, new InsertGenerateCallback<OrgWhiteList>() {
			public Insert generate(OrgWhiteList t) {
				Insert insert = insertInto(ORG_WHITE_LISTTABLE).values(
					ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_ID.value(t.getOrgWhiteListId()),
					ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_FIRST_ACCOUNT.value(t.getOrgWhiteListFirstAccount()),
					ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_SECOND_ACCOUNT.value(t.getOrgWhiteListSecondAccount()));
				return insert;
			}
		});
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int edit(OrgWhiteList orgWhiteList) {
		if(orgWhiteList == null || orgWhiteList.getOrgWhiteListId() == null){
			return 0;
		}
		return getDslTemplate().update(orgWhiteList, new UpdateGenerateCallback<OrgWhiteList>() {
			public Update generate(OrgWhiteList t) {
				Update update = update(ORG_WHITE_LISTTABLE).set(
					ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_FIRST_ACCOUNT.value(t.getOrgWhiteListFirstAccount()),
					ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_SECOND_ACCOUNT.value(t.getOrgWhiteListSecondAccount())).where(
					ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_ID.eq(t.getOrgWhiteListId()));
				return update;
			}
		});
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int deleteByKey(Integer pk){
		if(pk == null){
			return 0;
		}
		return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(ORG_WHITE_LISTTABLE).where(ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_ID.eq(pk));
			}
		});
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(ORG_WHITE_LISTTABLE).where(ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_ID.in(t));
		}
		},pks);
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OrgWhiteList getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, OrgWhiteList.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(ORG_WHITE_LISTTABLE).where(ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_ID.eq(t));
			}
		});
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<OrgWhiteList> query(OrgWhiteList orgWhiteList ,final OrderBy... orderArgs) {
		if(orgWhiteList==null){
			orgWhiteList=new OrgWhiteList();
		}
		return getDslTemplate().query(orgWhiteList, new SelectGenerateCallback<OrgWhiteList>() {

			@SuppressWarnings("rawtypes")
			public Select generate(OrgWhiteList t) {
				Select select = selectFrom(ORG_WHITE_LISTTABLE).where(
				and(
					ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_FIRST_ACCOUNT.eq(t.getOrgWhiteListFirstAccount()),
					ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_SECOND_ACCOUNT.eq(t.getOrgWhiteListSecondAccount())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Pager<OrgWhiteList> queryPager(int start,int limit ,OrgWhiteList orgWhiteList ,final OrderBy... orderArgs) {
		if(orgWhiteList==null){
			orgWhiteList=new OrgWhiteList();
		}
		return getDslTemplate().queryPager(start, limit, orgWhiteList, false, new SelectGenerateCallback<OrgWhiteList>() {

			public Select generate(OrgWhiteList t) {
				Select select = Select.selectFrom(ORG_WHITE_LISTTABLE).where(
				and(
					ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_FIRST_ACCOUNT.eq(t.getOrgWhiteListFirstAccount()),
					ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_SECOND_ACCOUNT.eq(t.getOrgWhiteListSecondAccount())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int[] batchInsert(boolean autoGeneratedKeys ,List<OrgWhiteList> orgWhiteLists) {
		if (CollectionUtil.isEmpty(orgWhiteLists)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, orgWhiteLists, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(ORG_WHITE_LISTTABLE).values(
					ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_FIRST_ACCOUNT.value(new JdbcNamedParameter("orgWhiteListFirstAccount")),
					ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_SECOND_ACCOUNT.value(new JdbcNamedParameter("orgWhiteListSecondAccount")));
			}
		});
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int[] batchInsert(List<OrgWhiteList> orgWhiteLists){
			return batchInsert(true ,orgWhiteLists);
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int[] batchUpdate(List<OrgWhiteList> orgWhiteLists) {
		if (CollectionUtil.isEmpty(orgWhiteLists)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(orgWhiteLists, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(ORG_WHITE_LISTTABLE).set(
					ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_FIRST_ACCOUNT.value(new JdbcNamedParameter("orgWhiteListFirstAccount")),
					ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_SECOND_ACCOUNT.value(new JdbcNamedParameter("orgWhiteListSecondAccount"))).where(
				ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_ID.eq(new JdbcNamedParameter("orgWhiteListId")));
			}
		});
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int[] batchDelete(List<OrgWhiteList> orgWhiteLists) {
		if (CollectionUtil.isEmpty(orgWhiteLists)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(orgWhiteLists, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(ORG_WHITE_LISTTABLE).where(and(
				ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_ID.eq(new JdbcNamedParameter("orgWhiteListId")),
				ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_FIRST_ACCOUNT.eq(new JdbcNamedParameter("orgWhiteListFirstAccount")),
				ORG_WHITE_LISTTABLE.ORG_WHITE_LIST_SECOND_ACCOUNT.eq(new JdbcNamedParameter("orgWhiteListSecondAccount"))));
			}
		});
	}

	/** 
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private  Select addOrderByElements(Select select ,OrderBy... orderBies){
		if (orderBies == null) {
			return select;
		}
		List<OrderByElement> orderByElements = new ArrayList<OrderByElement>();
		for (int i = 0; orderBies[i] != null && i < orderBies.length; i++) {
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
