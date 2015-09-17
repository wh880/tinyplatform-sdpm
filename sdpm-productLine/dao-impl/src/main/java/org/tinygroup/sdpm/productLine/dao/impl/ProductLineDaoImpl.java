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

package org.tinygroup.sdpm.productLine.dao.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.productLine.dao.constant.ProductLineTable.*;
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
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.productLine.dao.ProductLineDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class ProductLineDaoImpl extends TinyDslDaoSupport implements ProductLineDao {

	public ProductLine add(ProductLine productLine) {
		return getDslTemplate().insertAndReturnKey(productLine, new InsertGenerateCallback<ProductLine>() {
			public Insert generate(ProductLine t) {
				Insert insert = insertInto(PRODUCTLINETABLE).values(
					PRODUCTLINETABLE.PRODUCTLINE_ID.value(t.getProductLineId()),
					PRODUCTLINETABLE.COMPANY_ID.value(t.getCompanyId()),
					PRODUCTLINETABLE.DEPT_ID.value(t.getDeptId()),
					PRODUCTLINETABLE.PRODUCTLINE_ROOT.value(t.getProductLineRoot()),
					PRODUCTLINETABLE.PRODUCTLINE_PARENT.value(t.getProductLineParent()),
					PRODUCTLINETABLE.PRODUCTLINE_NAME.value(t.getProductLineName()),
					PRODUCTLINETABLE.PRODUCTLINE_CODE.value(t.getProductLineCode()),
					PRODUCTLINETABLE.PRODUCTLINE_ORDER.value(t.getProductLineOrder()),
					PRODUCTLINETABLE.PRODUCTLINE_SPEC.value(t.getProductLineSpec()),
					PRODUCTLINETABLE.PRODUCTLINE_STATUS.value(t.getProductLineStatus()),
					PRODUCTLINETABLE.PRODUCTLINE_OWNER.value(t.getProductLineOwner()),
					PRODUCTLINETABLE.PRODUCTLINE_QUALITY_MANAGER.value(t.getProductLineQualityManager()),
					PRODUCTLINETABLE.PRODUCTLINE_DELIVERY_MANAGER.value(t.getProductLineDeliveryManager()),
					PRODUCTLINETABLE.ACL.value(t.getAcl()),
					PRODUCTLINETABLE.PRODUCTLINE_WHITELIST.value(t.getProductLineWhiteList()),
					PRODUCTLINETABLE.PRODUCTLINE_CREATEDBY.value(t.getProductLineCreatedBy()),
					PRODUCTLINETABLE.PRODUCTLINE_CREATEDDATE.value(t.getProductLineCreatedDate()),
					PRODUCTLINETABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}

	public int edit(ProductLine productLine) {
		if(productLine == null || productLine.getProductLineId() == null){
			return 0;
		}
		return getDslTemplate().update(productLine, new UpdateGenerateCallback<ProductLine>() {
			public Update generate(ProductLine t) {
				Update update = update(PRODUCTLINETABLE).set(
					PRODUCTLINETABLE.COMPANY_ID.value(t.getCompanyId()),
					PRODUCTLINETABLE.DEPT_ID.value(t.getDeptId()),
					PRODUCTLINETABLE.PRODUCTLINE_ROOT.value(t.getProductLineRoot()),
					PRODUCTLINETABLE.PRODUCTLINE_PARENT.value(t.getProductLineParent()),
					PRODUCTLINETABLE.PRODUCTLINE_NAME.value(t.getProductLineName()),
					PRODUCTLINETABLE.PRODUCTLINE_CODE.value(t.getProductLineCode()),
					PRODUCTLINETABLE.PRODUCTLINE_ORDER.value(t.getProductLineOrder()),
					PRODUCTLINETABLE.PRODUCTLINE_SPEC.value(t.getProductLineSpec()),
					PRODUCTLINETABLE.PRODUCTLINE_STATUS.value(t.getProductLineStatus()),
					PRODUCTLINETABLE.PRODUCTLINE_OWNER.value(t.getProductLineOwner()),
					PRODUCTLINETABLE.PRODUCTLINE_QUALITY_MANAGER.value(t.getProductLineQualityManager()),
					PRODUCTLINETABLE.PRODUCTLINE_DELIVERY_MANAGER.value(t.getProductLineDeliveryManager()),
					PRODUCTLINETABLE.ACL.value(t.getAcl()),
					PRODUCTLINETABLE.PRODUCTLINE_WHITELIST.value(t.getProductLineWhiteList()),
					PRODUCTLINETABLE.PRODUCTLINE_CREATEDBY.value(t.getProductLineCreatedBy()),
					PRODUCTLINETABLE.PRODUCTLINE_CREATEDDATE.value(t.getProductLineCreatedDate()),
					PRODUCTLINETABLE.DELETED.value(t.getDeleted())).where(
					PRODUCTLINETABLE.PRODUCTLINE_ID.eq(t.getProductLineId()));
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
				return delete(PRODUCTLINETABLE).where(PRODUCTLINETABLE.PRODUCTLINE_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(PRODUCTLINETABLE).where(PRODUCTLINETABLE.PRODUCTLINE_ID.in(t));
		}
		},pks);
	}

	public ProductLine getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, ProductLine.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(PRODUCTLINETABLE).where(PRODUCTLINETABLE.PRODUCTLINE_ID.eq(t));
			}
		});
	}

	public List<ProductLine> query(ProductLine productLine) {
		if(productLine==null){
			productLine=new ProductLine();
		}
		return getDslTemplate().query(productLine, new SelectGenerateCallback<ProductLine>() {

			@SuppressWarnings("rawtypes")
			public Select generate(ProductLine t) {
				return selectFrom(PRODUCTLINETABLE).where(
				and(
					PRODUCTLINETABLE.COMPANY_ID.eq(t.getCompanyId()),
					PRODUCTLINETABLE.DEPT_ID.eq(t.getDeptId()),
					PRODUCTLINETABLE.PRODUCTLINE_ROOT.eq(t.getProductLineRoot()),
					PRODUCTLINETABLE.PRODUCTLINE_PARENT.eq(t.getProductLineParent()),
					PRODUCTLINETABLE.PRODUCTLINE_NAME.eq(t.getProductLineName()),
					PRODUCTLINETABLE.PRODUCTLINE_CODE.eq(t.getProductLineCode()),
					PRODUCTLINETABLE.PRODUCTLINE_ORDER.eq(t.getProductLineOrder()),
					PRODUCTLINETABLE.PRODUCTLINE_SPEC.eq(t.getProductLineSpec()),
					PRODUCTLINETABLE.PRODUCTLINE_STATUS.eq(t.getProductLineStatus()),
					PRODUCTLINETABLE.PRODUCTLINE_OWNER.eq(t.getProductLineOwner()),
					PRODUCTLINETABLE.PRODUCTLINE_QUALITY_MANAGER.eq(t.getProductLineQualityManager()),
					PRODUCTLINETABLE.PRODUCTLINE_DELIVERY_MANAGER.eq(t.getProductLineDeliveryManager()),
					PRODUCTLINETABLE.ACL.eq(t.getAcl()),
					PRODUCTLINETABLE.PRODUCTLINE_WHITELIST.eq(t.getProductLineWhiteList()),
					PRODUCTLINETABLE.PRODUCTLINE_CREATEDBY.eq(t.getProductLineCreatedBy()),
					PRODUCTLINETABLE.PRODUCTLINE_CREATEDDATE.eq(t.getProductLineCreatedDate()),
					PRODUCTLINETABLE.DELETED.eq(t.getDeleted())));
			}
		});
	}

	public Pager<ProductLine> queryPager(int start,int limit ,ProductLine productLine) {
		if(productLine==null){
			productLine=new ProductLine();
		}
		return getDslTemplate().queryPager(start, limit, productLine, false, new SelectGenerateCallback<ProductLine>() {

			public Select generate(ProductLine t) {
				return MysqlSelect.selectFrom(PRODUCTLINETABLE).where(
				and(
					PRODUCTLINETABLE.COMPANY_ID.eq(t.getCompanyId()),
					PRODUCTLINETABLE.DEPT_ID.eq(t.getDeptId()),
					PRODUCTLINETABLE.PRODUCTLINE_ROOT.eq(t.getProductLineRoot()),
					PRODUCTLINETABLE.PRODUCTLINE_PARENT.eq(t.getProductLineParent()),
					PRODUCTLINETABLE.PRODUCTLINE_NAME.eq(t.getProductLineName()),
					PRODUCTLINETABLE.PRODUCTLINE_CODE.eq(t.getProductLineCode()),
					PRODUCTLINETABLE.PRODUCTLINE_ORDER.eq(t.getProductLineOrder()),
					PRODUCTLINETABLE.PRODUCTLINE_SPEC.eq(t.getProductLineSpec()),
					PRODUCTLINETABLE.PRODUCTLINE_STATUS.eq(t.getProductLineStatus()),
					PRODUCTLINETABLE.PRODUCTLINE_OWNER.eq(t.getProductLineOwner()),
					PRODUCTLINETABLE.PRODUCTLINE_QUALITY_MANAGER.eq(t.getProductLineQualityManager()),
					PRODUCTLINETABLE.PRODUCTLINE_DELIVERY_MANAGER.eq(t.getProductLineDeliveryManager()),
					PRODUCTLINETABLE.ACL.eq(t.getAcl()),
					PRODUCTLINETABLE.PRODUCTLINE_WHITELIST.eq(t.getProductLineWhiteList()),
					PRODUCTLINETABLE.PRODUCTLINE_CREATEDBY.eq(t.getProductLineCreatedBy()),
					PRODUCTLINETABLE.PRODUCTLINE_CREATEDDATE.eq(t.getProductLineCreatedDate()),
					PRODUCTLINETABLE.DELETED.eq(t.getDeleted())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<ProductLine> productLines) {
		if (CollectionUtil.isEmpty(productLines)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, productLines, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(PRODUCTLINETABLE).values(
					PRODUCTLINETABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					PRODUCTLINETABLE.DEPT_ID.value(new JdbcNamedParameter("deptId")),
					PRODUCTLINETABLE.PRODUCTLINE_ROOT.value(new JdbcNamedParameter("productLineRoot")),
					PRODUCTLINETABLE.PRODUCTLINE_PARENT.value(new JdbcNamedParameter("productLineParent")),
					PRODUCTLINETABLE.PRODUCTLINE_NAME.value(new JdbcNamedParameter("productLineName")),
					PRODUCTLINETABLE.PRODUCTLINE_CODE.value(new JdbcNamedParameter("productLineCode")),
					PRODUCTLINETABLE.PRODUCTLINE_ORDER.value(new JdbcNamedParameter("productLineOrder")),
					PRODUCTLINETABLE.PRODUCTLINE_SPEC.value(new JdbcNamedParameter("productLineSpec")),
					PRODUCTLINETABLE.PRODUCTLINE_STATUS.value(new JdbcNamedParameter("productLineStatus")),
					PRODUCTLINETABLE.PRODUCTLINE_OWNER.value(new JdbcNamedParameter("productLineOwner")),
					PRODUCTLINETABLE.PRODUCTLINE_QUALITY_MANAGER.value(new JdbcNamedParameter("productLineQualityManager")),
					PRODUCTLINETABLE.PRODUCTLINE_DELIVERY_MANAGER.value(new JdbcNamedParameter("productLineDeliveryManager")),
					PRODUCTLINETABLE.ACL.value(new JdbcNamedParameter("acl")),
					PRODUCTLINETABLE.PRODUCTLINE_WHITELIST.value(new JdbcNamedParameter("productLineWhiteList")),
					PRODUCTLINETABLE.PRODUCTLINE_CREATEDBY.value(new JdbcNamedParameter("productLineCreatedBy")),
					PRODUCTLINETABLE.PRODUCTLINE_CREATEDDATE.value(new JdbcNamedParameter("productLineCreatedDate")),
					PRODUCTLINETABLE.DELETED.value(new JdbcNamedParameter("deleted")));
			}
		});
	}

	public int[] batchInsert(List<ProductLine> productLines){
			return batchInsert(true ,productLines);
	}

	public int[] batchUpdate(List<ProductLine> productLines) {
		if (CollectionUtil.isEmpty(productLines)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(productLines, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(PRODUCTLINETABLE).set(
					PRODUCTLINETABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					PRODUCTLINETABLE.DEPT_ID.value(new JdbcNamedParameter("deptId")),
					PRODUCTLINETABLE.PRODUCTLINE_ROOT.value(new JdbcNamedParameter("productLineRoot")),
					PRODUCTLINETABLE.PRODUCTLINE_PARENT.value(new JdbcNamedParameter("productLineParent")),
					PRODUCTLINETABLE.PRODUCTLINE_NAME.value(new JdbcNamedParameter("productLineName")),
					PRODUCTLINETABLE.PRODUCTLINE_CODE.value(new JdbcNamedParameter("productLineCode")),
					PRODUCTLINETABLE.PRODUCTLINE_ORDER.value(new JdbcNamedParameter("productLineOrder")),
					PRODUCTLINETABLE.PRODUCTLINE_SPEC.value(new JdbcNamedParameter("productLineSpec")),
					PRODUCTLINETABLE.PRODUCTLINE_STATUS.value(new JdbcNamedParameter("productLineStatus")),
					PRODUCTLINETABLE.PRODUCTLINE_OWNER.value(new JdbcNamedParameter("productLineOwner")),
					PRODUCTLINETABLE.PRODUCTLINE_QUALITY_MANAGER.value(new JdbcNamedParameter("productLineQualityManager")),
					PRODUCTLINETABLE.PRODUCTLINE_DELIVERY_MANAGER.value(new JdbcNamedParameter("productLineDeliveryManager")),
					PRODUCTLINETABLE.ACL.value(new JdbcNamedParameter("acl")),
					PRODUCTLINETABLE.PRODUCTLINE_WHITELIST.value(new JdbcNamedParameter("productLineWhiteList")),
					PRODUCTLINETABLE.PRODUCTLINE_CREATEDBY.value(new JdbcNamedParameter("productLineCreatedBy")),
					PRODUCTLINETABLE.PRODUCTLINE_CREATEDDATE.value(new JdbcNamedParameter("productLineCreatedDate")),
					PRODUCTLINETABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
				PRODUCTLINETABLE.PRODUCTLINE_ID.eq(new JdbcNamedParameter("productLineId")));
			}
		});
	}

	public int[] batchDelete(List<ProductLine> productLines) {
		if (CollectionUtil.isEmpty(productLines)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(productLines, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(PRODUCTLINETABLE).where(and(
				PRODUCTLINETABLE.PRODUCTLINE_ID.eq(new JdbcNamedParameter("productLineId")),
				PRODUCTLINETABLE.COMPANY_ID.eq(new JdbcNamedParameter("companyId")),
				PRODUCTLINETABLE.DEPT_ID.eq(new JdbcNamedParameter("deptId")),
				PRODUCTLINETABLE.PRODUCTLINE_ROOT.eq(new JdbcNamedParameter("productLineRoot")),
				PRODUCTLINETABLE.PRODUCTLINE_PARENT.eq(new JdbcNamedParameter("productLineParent")),
				PRODUCTLINETABLE.PRODUCTLINE_NAME.eq(new JdbcNamedParameter("productLineName")),
				PRODUCTLINETABLE.PRODUCTLINE_CODE.eq(new JdbcNamedParameter("productLineCode")),
				PRODUCTLINETABLE.PRODUCTLINE_ORDER.eq(new JdbcNamedParameter("productLineOrder")),
				PRODUCTLINETABLE.PRODUCTLINE_SPEC.eq(new JdbcNamedParameter("productLineSpec")),
				PRODUCTLINETABLE.PRODUCTLINE_STATUS.eq(new JdbcNamedParameter("productLineStatus")),
				PRODUCTLINETABLE.PRODUCTLINE_OWNER.eq(new JdbcNamedParameter("productLineOwner")),
				PRODUCTLINETABLE.PRODUCTLINE_QUALITY_MANAGER.eq(new JdbcNamedParameter("productLineQualityManager")),
				PRODUCTLINETABLE.PRODUCTLINE_DELIVERY_MANAGER.eq(new JdbcNamedParameter("productLineDeliveryManager")),
				PRODUCTLINETABLE.ACL.eq(new JdbcNamedParameter("acl")),
				PRODUCTLINETABLE.PRODUCTLINE_WHITELIST.eq(new JdbcNamedParameter("productLineWhiteList")),
				PRODUCTLINETABLE.PRODUCTLINE_CREATEDBY.eq(new JdbcNamedParameter("productLineCreatedBy")),
				PRODUCTLINETABLE.PRODUCTLINE_CREATEDDATE.eq(new JdbcNamedParameter("productLineCreatedDate")),
				PRODUCTLINETABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
			}
		});
	}

}
