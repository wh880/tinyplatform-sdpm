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

package org.tinygroup.sdpm.product.dao.impl;

import static org.tinygroup.sdpm.product.dao.constant.ProductTable.PRODUCTTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.product.dao.ProductDao;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.base.Table;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;
@Repository
public class ProductDaoImpl extends TinyDslDaoSupport implements ProductDao {

	protected static final Table SERVICE_CLIENTTABLE = null;

	public Product add(Product product) {
		return getDslTemplate().insertAndReturnKey(product, new InsertGenerateCallback<Product>() {
			public Insert generate(Product t) {
				Insert insert = insertInto(PRODUCTTABLE).values(
					PRODUCTTABLE.PRODUCT_ID.value(t.getProductId()),
					PRODUCTTABLE.COMPANY_ID.value(t.getCompanyId()),
					PRODUCTTABLE.DEPT_ID.value(t.getDeptId()),
					PRODUCTTABLE.PRODUCTLINE_ID.value(t.getProductLineId()),
					PRODUCTTABLE.PRODUCT_NAME.value(t.getProductName()),
					PRODUCTTABLE.PRODUCT_CODE.value(t.getProductCode()),
					PRODUCTTABLE.PRODUCT_ORDER.value(t.getProductOrder()),
					PRODUCTTABLE.PRODUCT_STATUS.value(t.getProductStatus()),
					PRODUCTTABLE.PRODUCT_DESC.value(t.getProductDesc()),
					PRODUCTTABLE.PRODUCT_OWNER.value(t.getProductOwner()),
					PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.value(t.getProductQualityManager()),
					PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.value(t.getProductDeliveryManager()),
					PRODUCTTABLE.ACL.value(t.getAcl()),
					PRODUCTTABLE.PRODUCT_WHITE_LIST.value(t.getProductWhiteList()),
					PRODUCTTABLE.PRODUCT_CREATED_BY.value(t.getProductCreatedBy()),
					PRODUCTTABLE.PRODUCT_CREATED_DATE.value(t.getProductCreatedDate()),
					PRODUCTTABLE.PRODUCT_CREATED_VERSION.value(t.getProductCreatedVersion()),
					PRODUCTTABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}

	public int edit(Product product) {
		if(product == null || product.getProductId() == null){
			return 0;
		}
		return getDslTemplate().update(product, new UpdateGenerateCallback<Product>() {
			public Update generate(Product t) {
				Update update = update(PRODUCTTABLE).set(
					PRODUCTTABLE.COMPANY_ID.value(t.getCompanyId()),
					PRODUCTTABLE.DEPT_ID.value(t.getDeptId()),
					PRODUCTTABLE.PRODUCTLINE_ID.value(t.getProductLineId()),
					PRODUCTTABLE.PRODUCT_NAME.value(t.getProductName()),
					PRODUCTTABLE.PRODUCT_CODE.value(t.getProductCode()),
					PRODUCTTABLE.PRODUCT_ORDER.value(t.getProductOrder()),
					PRODUCTTABLE.PRODUCT_STATUS.value(t.getProductStatus()),
					PRODUCTTABLE.PRODUCT_DESC.value(t.getProductDesc()),
					PRODUCTTABLE.PRODUCT_OWNER.value(t.getProductOwner()),
					PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.value(t.getProductQualityManager()),
					PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.value(t.getProductDeliveryManager()),
					PRODUCTTABLE.ACL.value(t.getAcl()),
					PRODUCTTABLE.PRODUCT_WHITE_LIST.value(t.getProductWhiteList()),
					PRODUCTTABLE.PRODUCT_CREATED_BY.value(t.getProductCreatedBy()),
					PRODUCTTABLE.PRODUCT_CREATED_DATE.value(t.getProductCreatedDate()),
					PRODUCTTABLE.PRODUCT_CREATED_VERSION.value(t.getProductCreatedVersion()),
					PRODUCTTABLE.DELETED.value(t.getDeleted())).where(
					PRODUCTTABLE.PRODUCT_ID.eq(t.getProductId()));
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
				return delete(PRODUCTTABLE).where(PRODUCTTABLE.PRODUCT_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(PRODUCTTABLE).where(PRODUCTTABLE.PRODUCT_ID.in(t));
		}
		},pks);
	}

	public Product getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Product.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(PRODUCTTABLE).where(PRODUCTTABLE.PRODUCT_ID.eq(t));
			}
		});
	}

	public List<Product> query(Product product ,final OrderBy... orderArgs) {
		if(product==null){
			product=new Product();
		}
		return getDslTemplate().query(product, new SelectGenerateCallback<Product>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Product t) {
				Select select = selectFrom(PRODUCTTABLE).where(
				and(
					PRODUCTTABLE.COMPANY_ID.eq(t.getCompanyId()),
					PRODUCTTABLE.DEPT_ID.eq(t.getDeptId()),
					PRODUCTTABLE.PRODUCTLINE_ID.eq(t.getProductLineId()),
					PRODUCTTABLE.PRODUCT_NAME.eq(t.getProductName()),
					PRODUCTTABLE.PRODUCT_CODE.eq(t.getProductCode()),
					PRODUCTTABLE.PRODUCT_ORDER.eq(t.getProductOrder()),
					PRODUCTTABLE.PRODUCT_STATUS.eq(t.getProductStatus()),
					PRODUCTTABLE.PRODUCT_DESC.eq(t.getProductDesc()),
					PRODUCTTABLE.PRODUCT_OWNER.eq(t.getProductOwner()),
					PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.eq(t.getProductQualityManager()),
					PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.eq(t.getProductDeliveryManager()),
					PRODUCTTABLE.ACL.eq(t.getAcl()),
					PRODUCTTABLE.PRODUCT_WHITE_LIST.eq(t.getProductWhiteList()),
					PRODUCTTABLE.PRODUCT_CREATED_BY.eq(t.getProductCreatedBy()),
					PRODUCTTABLE.PRODUCT_CREATED_DATE.eq(t.getProductCreatedDate()),
					PRODUCTTABLE.PRODUCT_CREATED_VERSION.eq(t.getProductCreatedVersion()),
					PRODUCTTABLE.DELETED.eq(t.getDeleted())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	public Pager<Product> queryPager(int start,int limit ,Product product ,final OrderBy... orderArgs) {
		if(product==null){
			product=new Product();
		}
		return getDslTemplate().queryPager(start, limit, product, false, new SelectGenerateCallback<Product>() {

			public Select generate(Product t) {
				Select select = MysqlSelect.selectFrom(PRODUCTTABLE).where(
				and(
					PRODUCTTABLE.COMPANY_ID.eq(t.getCompanyId()),
					PRODUCTTABLE.DEPT_ID.eq(t.getDeptId()),
					PRODUCTTABLE.PRODUCTLINE_ID.eq(t.getProductLineId()),
					PRODUCTTABLE.PRODUCT_NAME.eq(t.getProductName()),
					PRODUCTTABLE.PRODUCT_CODE.eq(t.getProductCode()),
					PRODUCTTABLE.PRODUCT_ORDER.eq(t.getProductOrder()),
					PRODUCTTABLE.PRODUCT_STATUS.eq(t.getProductStatus()),
					PRODUCTTABLE.PRODUCT_DESC.eq(t.getProductDesc()),
					PRODUCTTABLE.PRODUCT_OWNER.eq(t.getProductOwner()),
					PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.eq(t.getProductQualityManager()),
					PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.eq(t.getProductDeliveryManager()),
					PRODUCTTABLE.ACL.eq(t.getAcl()),
					PRODUCTTABLE.PRODUCT_WHITE_LIST.eq(t.getProductWhiteList()),
					PRODUCTTABLE.PRODUCT_CREATED_BY.eq(t.getProductCreatedBy()),
					PRODUCTTABLE.PRODUCT_CREATED_DATE.eq(t.getProductCreatedDate()),
					PRODUCTTABLE.PRODUCT_CREATED_VERSION.eq(t.getProductCreatedVersion()),
					PRODUCTTABLE.DELETED.eq(t.getDeleted())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Product> products) {
		if (CollectionUtil.isEmpty(products)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, products, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(PRODUCTTABLE).values(
					PRODUCTTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					PRODUCTTABLE.DEPT_ID.value(new JdbcNamedParameter("deptId")),
					PRODUCTTABLE.PRODUCTLINE_ID.value(new JdbcNamedParameter("productLineId")),
					PRODUCTTABLE.PRODUCT_NAME.value(new JdbcNamedParameter("productName")),
					PRODUCTTABLE.PRODUCT_CODE.value(new JdbcNamedParameter("productCode")),
					PRODUCTTABLE.PRODUCT_ORDER.value(new JdbcNamedParameter("productOrder")),
					PRODUCTTABLE.PRODUCT_STATUS.value(new JdbcNamedParameter("productStatus")),
					PRODUCTTABLE.PRODUCT_DESC.value(new JdbcNamedParameter("productDesc")),
					PRODUCTTABLE.PRODUCT_OWNER.value(new JdbcNamedParameter("productOwner")),
					PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.value(new JdbcNamedParameter("productQualityManager")),
					PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.value(new JdbcNamedParameter("productDeliveryManager")),
					PRODUCTTABLE.ACL.value(new JdbcNamedParameter("acl")),
					PRODUCTTABLE.PRODUCT_WHITE_LIST.value(new JdbcNamedParameter("productWhiteList")),
					PRODUCTTABLE.PRODUCT_CREATED_BY.value(new JdbcNamedParameter("productCreatedBy")),
					PRODUCTTABLE.PRODUCT_CREATED_DATE.value(new JdbcNamedParameter("productCreatedDate")),
					PRODUCTTABLE.PRODUCT_CREATED_VERSION.value(new JdbcNamedParameter("productCreatedVersion")),
					PRODUCTTABLE.DELETED.value(new JdbcNamedParameter("deleted")));
			}
		});
	}

	public int[] batchInsert(List<Product> products){
			return batchInsert(true ,products);
	}

	public int[] batchUpdate(List<Product> products) {
		if (CollectionUtil.isEmpty(products)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(products, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(PRODUCTTABLE).set(
					PRODUCTTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					PRODUCTTABLE.DEPT_ID.value(new JdbcNamedParameter("deptId")),
					PRODUCTTABLE.PRODUCTLINE_ID.value(new JdbcNamedParameter("productLineId")),
					PRODUCTTABLE.PRODUCT_NAME.value(new JdbcNamedParameter("productName")),
					PRODUCTTABLE.PRODUCT_CODE.value(new JdbcNamedParameter("productCode")),
					PRODUCTTABLE.PRODUCT_ORDER.value(new JdbcNamedParameter("productOrder")),
					PRODUCTTABLE.PRODUCT_STATUS.value(new JdbcNamedParameter("productStatus")),
					PRODUCTTABLE.PRODUCT_DESC.value(new JdbcNamedParameter("productDesc")),
					PRODUCTTABLE.PRODUCT_OWNER.value(new JdbcNamedParameter("productOwner")),
					PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.value(new JdbcNamedParameter("productQualityManager")),
					PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.value(new JdbcNamedParameter("productDeliveryManager")),
					PRODUCTTABLE.ACL.value(new JdbcNamedParameter("acl")),
					PRODUCTTABLE.PRODUCT_WHITE_LIST.value(new JdbcNamedParameter("productWhiteList")),
					PRODUCTTABLE.PRODUCT_CREATED_BY.value(new JdbcNamedParameter("productCreatedBy")),
					PRODUCTTABLE.PRODUCT_CREATED_DATE.value(new JdbcNamedParameter("productCreatedDate")),
					PRODUCTTABLE.PRODUCT_CREATED_VERSION.value(new JdbcNamedParameter("productCreatedVersion")),
					PRODUCTTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
				PRODUCTTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")));
			}
		});
	}

	public int[] batchDelete(List<Product> products) {
		if (CollectionUtil.isEmpty(products)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(products, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(PRODUCTTABLE).where(and(
				PRODUCTTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
				PRODUCTTABLE.COMPANY_ID.eq(new JdbcNamedParameter("companyId")),
				PRODUCTTABLE.DEPT_ID.eq(new JdbcNamedParameter("deptId")),
				PRODUCTTABLE.PRODUCTLINE_ID.eq(new JdbcNamedParameter("productLineId")),
				PRODUCTTABLE.PRODUCT_NAME.eq(new JdbcNamedParameter("productName")),
				PRODUCTTABLE.PRODUCT_CODE.eq(new JdbcNamedParameter("productCode")),
				PRODUCTTABLE.PRODUCT_ORDER.eq(new JdbcNamedParameter("productOrder")),
				PRODUCTTABLE.PRODUCT_STATUS.eq(new JdbcNamedParameter("productStatus")),
				PRODUCTTABLE.PRODUCT_DESC.eq(new JdbcNamedParameter("productDesc")),
				PRODUCTTABLE.PRODUCT_OWNER.eq(new JdbcNamedParameter("productOwner")),
				PRODUCTTABLE.PRODUCT_QUALITY_MANAGER.eq(new JdbcNamedParameter("productQualityManager")),
				PRODUCTTABLE.PRODUCT_DELIVERY_MANAGER.eq(new JdbcNamedParameter("productDeliveryManager")),
				PRODUCTTABLE.ACL.eq(new JdbcNamedParameter("acl")),
				PRODUCTTABLE.PRODUCT_WHITE_LIST.eq(new JdbcNamedParameter("productWhiteList")),
				PRODUCTTABLE.PRODUCT_CREATED_BY.eq(new JdbcNamedParameter("productCreatedBy")),
				PRODUCTTABLE.PRODUCT_CREATED_DATE.eq(new JdbcNamedParameter("productCreatedDate")),
				PRODUCTTABLE.PRODUCT_CREATED_VERSION.eq(new JdbcNamedParameter("productCreatedVersion")),
				PRODUCTTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
			}
		});
	}

	private  Select addOrderByElements(Select select ,OrderBy... orderBies){
		List<OrderByElement> orderByElements = new ArrayList<OrderByElement>();
		for (int i = 0; orderBies != null && i < orderBies.length; i++) {
			OrderByElement tempElement = null;
			
			if(orderBies[i]!=null){
				tempElement = orderBies[i].getOrderByElement();
			}
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
