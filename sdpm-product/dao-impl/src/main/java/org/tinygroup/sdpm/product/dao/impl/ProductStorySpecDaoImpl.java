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

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.product.dao.constant.ProductReleaseTable.PRODUCT_RELEASETABLE;
import static org.tinygroup.sdpm.product.dao.constant.ProductStorySpecTable.*;
import static org.tinygroup.sdpm.product.dao.constant.ProductStoryTable.PRODUCT_STORYTABLE;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.sdpm.common.util.update.UpdateUtil;
import org.tinygroup.sdpm.product.dao.pojo.ProductRelease;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.ProductStorySpec;
import org.tinygroup.sdpm.product.dao.ProductStorySpecDao;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

@Component
public class ProductStorySpecDaoImpl extends TinyDslDaoSupport implements ProductStorySpecDao {

	public ProductStorySpec add(ProductStorySpec productStorySpec) {
		return getDslTemplate().insertAndReturnKey(productStorySpec, new InsertGenerateCallback<ProductStorySpec>() {
			public Insert generate(ProductStorySpec t) {
				Insert insert = insertInto(PRODUCT_STORY_SPECTABLE).values(
					PRODUCT_STORY_SPECTABLE.STORYSPEC_ID.value(t.getStoryspecId()),
					PRODUCT_STORY_SPECTABLE.COMPANY_ID.value(t.getCompanyId()),
					PRODUCT_STORY_SPECTABLE.STORY_ID.value(t.getStoryId()),
					PRODUCT_STORY_SPECTABLE.STORY_VERSION.value(t.getStoryVersion()),
					PRODUCT_STORY_SPECTABLE.STORY_TITLE.value(t.getStoryTitle()),
					PRODUCT_STORY_SPECTABLE.STORY_SPEC.value(t.getStorySpec()),
					PRODUCT_STORY_SPECTABLE.STORY_VERIFICATION.value(t.getStoryVerification()));
				return insert;
			}
		});
	}

	public int edit(ProductStorySpec productStorySpec) {
		if(productStorySpec == null || productStorySpec.getStoryspecId() == null){
			return 0;
		}
		return getDslTemplate().update(productStorySpec, new UpdateGenerateCallback<ProductStorySpec>() {
			public Update generate(ProductStorySpec t) {
				Update update = UpdateUtil.getUpdate(PRODUCT_STORY_SPECTABLE, t);
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
				return delete(PRODUCT_STORY_SPECTABLE).where(PRODUCT_STORY_SPECTABLE.STORYSPEC_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(PRODUCT_STORY_SPECTABLE).where(PRODUCT_STORY_SPECTABLE.STORYSPEC_ID.in(t));
		}
		},pks);
	}
	
	public List<ProductStorySpec> getByKeys(Integer... pk){
			
		SelectGenerateCallback<Serializable[]> callback = new SelectGenerateCallback<Serializable[]>() {
			@SuppressWarnings("rawtypes")
			public Select generate(Serializable[] t) {

				return selectFrom(PRODUCT_STORY_SPECTABLE).where(PRODUCT_STORY_SPECTABLE.STORYSPEC_ID.in(t));
			}
			
		};
		Select select = callback.generate(pk);
		return getDslSession().fetchList(select, ProductStorySpec.class);
	}


	public ProductStorySpec getByKey(Integer pk) {
		
		try {
			return getDslTemplate().getByKey(pk, ProductStorySpec.class, new SelectGenerateCallback<Serializable>() {
				@SuppressWarnings("rawtypes")
				public Select generate(Serializable t) {
					return selectFrom(PRODUCT_STORY_SPECTABLE).where(PRODUCT_STORY_SPECTABLE.STORY_ID.eq(t));
					}
				});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		
	}

	public List<ProductStorySpec> query(ProductStorySpec productStorySpec ,final OrderBy... orderArgs) {
		if(productStorySpec==null){
			productStorySpec=new ProductStorySpec();
		}
		return getDslTemplate().query(productStorySpec, new SelectGenerateCallback<ProductStorySpec>() {

			@SuppressWarnings("rawtypes")
			public Select generate(ProductStorySpec t) {
				Select select = selectFrom(PRODUCT_STORY_SPECTABLE).where(
				and(
					PRODUCT_STORY_SPECTABLE.COMPANY_ID.eq(t.getCompanyId()),
					PRODUCT_STORY_SPECTABLE.STORY_ID.eq(t.getStoryId()),
					PRODUCT_STORY_SPECTABLE.STORY_VERSION.eq(t.getStoryVersion()),
					PRODUCT_STORY_SPECTABLE.STORY_TITLE.eq(t.getStoryTitle()),
					PRODUCT_STORY_SPECTABLE.STORY_SPEC.eq(t.getStorySpec()),
					PRODUCT_STORY_SPECTABLE.STORY_VERIFICATION.eq(t.getStoryVerification())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	public Pager<ProductStorySpec> queryPager(int start,int limit ,ProductStorySpec productStorySpec ,final OrderBy... orderArgs) {
		if(productStorySpec==null){
			productStorySpec=new ProductStorySpec();
		}
		return getDslTemplate().queryPager(start, limit, productStorySpec, false, new SelectGenerateCallback<ProductStorySpec>() {

			public Select generate(ProductStorySpec t) {
				Select select = MysqlSelect.selectFrom(PRODUCT_STORY_SPECTABLE).where(
				and(
					PRODUCT_STORY_SPECTABLE.COMPANY_ID.eq(t.getCompanyId()),
					PRODUCT_STORY_SPECTABLE.STORY_ID.eq(t.getStoryId()),
					PRODUCT_STORY_SPECTABLE.STORY_VERSION.eq(t.getStoryVersion()),
					PRODUCT_STORY_SPECTABLE.STORY_TITLE.eq(t.getStoryTitle()),
					PRODUCT_STORY_SPECTABLE.STORY_SPEC.eq(t.getStorySpec()),
					PRODUCT_STORY_SPECTABLE.STORY_VERIFICATION.eq(t.getStoryVerification())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<ProductStorySpec> productStorySpecs) {
		if (CollectionUtil.isEmpty(productStorySpecs)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, productStorySpecs, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(PRODUCT_STORY_SPECTABLE).values(
					PRODUCT_STORY_SPECTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					PRODUCT_STORY_SPECTABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
					PRODUCT_STORY_SPECTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
					PRODUCT_STORY_SPECTABLE.STORY_TITLE.value(new JdbcNamedParameter("storyTitle")),
					PRODUCT_STORY_SPECTABLE.STORY_SPEC.value(new JdbcNamedParameter("storySpec")),
					PRODUCT_STORY_SPECTABLE.STORY_VERIFICATION.value(new JdbcNamedParameter("storyVerification")));
			}
		});
	}

	public int[] batchInsert(List<ProductStorySpec> productStorySpecs){
			return batchInsert(true ,productStorySpecs);
	}

	public int[] batchUpdate(List<ProductStorySpec> productStorySpecs) {
		if (CollectionUtil.isEmpty(productStorySpecs)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(productStorySpecs, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(PRODUCT_STORY_SPECTABLE).set(
					PRODUCT_STORY_SPECTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					PRODUCT_STORY_SPECTABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
					PRODUCT_STORY_SPECTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
					PRODUCT_STORY_SPECTABLE.STORY_TITLE.value(new JdbcNamedParameter("storyTitle")),
					PRODUCT_STORY_SPECTABLE.STORY_SPEC.value(new JdbcNamedParameter("storySpec")),
					PRODUCT_STORY_SPECTABLE.STORY_VERIFICATION.value(new JdbcNamedParameter("storyVerification"))).where(
				PRODUCT_STORY_SPECTABLE.STORYSPEC_ID.eq(new JdbcNamedParameter("storyspecId")));
			}
		});
	}

	public int[] batchDelete(List<ProductStorySpec> productStorySpecs) {
		if (CollectionUtil.isEmpty(productStorySpecs)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(productStorySpecs, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(PRODUCT_STORY_SPECTABLE).where(and(
				PRODUCT_STORY_SPECTABLE.STORYSPEC_ID.eq(new JdbcNamedParameter("storyspecId")),
				PRODUCT_STORY_SPECTABLE.COMPANY_ID.eq(new JdbcNamedParameter("companyId")),
				PRODUCT_STORY_SPECTABLE.STORY_ID.eq(new JdbcNamedParameter("storyId")),
				PRODUCT_STORY_SPECTABLE.STORY_VERSION.eq(new JdbcNamedParameter("storyVersion")),
				PRODUCT_STORY_SPECTABLE.STORY_TITLE.eq(new JdbcNamedParameter("storyTitle")),
				PRODUCT_STORY_SPECTABLE.STORY_SPEC.eq(new JdbcNamedParameter("storySpec")),
				PRODUCT_STORY_SPECTABLE.STORY_VERIFICATION.eq(new JdbcNamedParameter("storyVerification"))));
			}
		});
	}

	private  Select addOrderByElements(Select select ,OrderBy... orderBies){
		if(orderBies==null||orderBies.length==0){
			return select;
		}
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
	
	public static Condition productStorySpecPueryCondition(ProductStorySpec t){
		return t==null?null:and(
						PRODUCT_STORY_SPECTABLE.COMPANY_ID.eq(t.getCompanyId()),
						PRODUCT_STORY_SPECTABLE.STORY_ID.eq(t.getStoryId()),
						PRODUCT_STORY_SPECTABLE.STORY_VERSION.eq(t.getStoryVersion()),
						PRODUCT_STORY_SPECTABLE.STORY_TITLE.eq(t.getStoryTitle()),
						PRODUCT_STORY_SPECTABLE.STORY_SPEC.eq(t.getStorySpec()),
						PRODUCT_STORY_SPECTABLE.STORY_VERIFICATION.eq(t.getStoryVerification()));
	}
}
