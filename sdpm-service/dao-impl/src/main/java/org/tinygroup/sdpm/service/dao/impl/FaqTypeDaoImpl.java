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

package org.tinygroup.sdpm.service.dao.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.service.dao.constant.FaqTypeTable.*;
import static org.tinygroup.tinysqldsl.Select.*;
import static org.tinygroup.tinysqldsl.Insert.*;
import static org.tinygroup.tinysqldsl.Delete.*;
import static org.tinygroup.tinysqldsl.Update.*;

import java.io.Serializable;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.tinysqldsl.Delete;
import org.tinygroup.tinysqldsl.Insert;
import org.tinygroup.tinysqldsl.Select;
import org.tinygroup.tinysqldsl.Update;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
	import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.sdpm.service.dao.pojo.FaqType;
import org.tinygroup.sdpm.service.dao.FaqTypeDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;
@LogClass("faqType")
@Repository
public class FaqTypeDaoImpl extends TinyDslDaoSupport implements FaqTypeDao {
@LogMethod("add")
	public FaqType add(FaqType faqType) {
		return getDslTemplate().insertAndReturnKey(faqType, new InsertGenerateCallback<FaqType>() {
			public Insert generate(FaqType t) {
				Insert insert = insertInto(FAQ_TYPETABLE).values(
					FAQ_TYPETABLE.FAQ_TYPE_ID.value(t.getFaqTypeId()),
					FAQ_TYPETABLE.FAQ_TYPE.value(t.getFaqType()),
					FAQ_TYPETABLE.FAQ_PARENT_TYPE_ID.value(t.getFaqParentTypeId()),
					FAQ_TYPETABLE.FAQ_TYPE_CREATDAY.value(t.getFaqTypeCreatDay()),
					FAQ_TYPETABLE.FAQ_CREATED_BY.value(t.getFaqCreatedBy()),
					FAQ_TYPETABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}
@LogMethod("edit")
	public int edit(FaqType faqType) {
		if(faqType == null || faqType.getFaqTypeId() == null){
			return 0;
		}
		return getDslTemplate().update(faqType, new UpdateGenerateCallback<FaqType>() {
			public Update generate(FaqType t) {
				Update update = update(FAQ_TYPETABLE).set(
					FAQ_TYPETABLE.FAQ_TYPE.value(t.getFaqType()),
					FAQ_TYPETABLE.FAQ_PARENT_TYPE_ID.value(t.getFaqParentTypeId()),
					FAQ_TYPETABLE.FAQ_TYPE_CREATDAY.value(t.getFaqTypeCreatDay()),
					FAQ_TYPETABLE.FAQ_CREATED_BY.value(t.getFaqCreatedBy()),
					FAQ_TYPETABLE.DELETED.value(t.getDeleted())).where(
					FAQ_TYPETABLE.FAQ_TYPE_ID.eq(t.getFaqTypeId()));
				return update;
			}
		});
	}
@LogMethod("deleteByKey")
	public int deleteByKey(Integer pk){
		if(pk == null){
			return 0;
		}
		return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(FAQ_TYPETABLE).where(FAQ_TYPETABLE.FAQ_TYPE_ID.eq(pk));
			}
		});
	}
@LogMethod("deleteByKeys")
	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(FAQ_TYPETABLE).where(FAQ_TYPETABLE.FAQ_TYPE_ID.in(t));
		}
		},pks);
	}
@LogMethod("getByKey")
	public FaqType getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, FaqType.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(FAQ_TYPETABLE).where(FAQ_TYPETABLE.FAQ_TYPE_ID.eq(t));
			}
		});
	}
@LogMethod("query")
	public List<FaqType> query(FaqType faqType ,final OrderBy... orderBies) {
		if(faqType==null){
			faqType=new FaqType();
		}
		return getDslTemplate().query(faqType, new SelectGenerateCallback<FaqType>() {

			@SuppressWarnings("rawtypes")
			public Select generate(FaqType t) {
				Select select = selectFrom(FAQ_TYPETABLE).where(
				and(
					FAQ_TYPETABLE.FAQ_TYPE.eq(t.getFaqType()),
					FAQ_TYPETABLE.FAQ_PARENT_TYPE_ID.eq(t.getFaqParentTypeId()),
					FAQ_TYPETABLE.FAQ_TYPE_CREATDAY.eq(t.getFaqTypeCreatDay()),
					FAQ_TYPETABLE.FAQ_CREATED_BY.eq(t.getFaqCreatedBy()),
					FAQ_TYPETABLE.DELETED.eq(t.getDeleted())));
		return addOrderByElements(select, orderBies);
			}
		});
	}
@LogMethod("queryPager")
	public Pager<FaqType> queryPager(int start,int limit ,FaqType faqType ,final OrderBy... orderBies) {
		if(faqType==null){
			faqType=new FaqType();
		}
		return getDslTemplate().queryPager(start, limit, faqType, false, new SelectGenerateCallback<FaqType>() {

			public Select generate(FaqType t) {
				Select select = MysqlSelect.selectFrom(FAQ_TYPETABLE).where(
				and(
					FAQ_TYPETABLE.FAQ_TYPE.eq(t.getFaqType()),
					FAQ_TYPETABLE.FAQ_PARENT_TYPE_ID.eq(t.getFaqParentTypeId()),
					FAQ_TYPETABLE.FAQ_TYPE_CREATDAY.eq(t.getFaqTypeCreatDay()),
					FAQ_TYPETABLE.FAQ_CREATED_BY.eq(t.getFaqCreatedBy()),
					FAQ_TYPETABLE.DELETED.eq(t.getDeleted())));
		return addOrderByElements(select, orderBies);
			}
		});
	}
@LogMethod("batchInsert")
	public int[] batchInsert(boolean autoGeneratedKeys ,List<FaqType> faqTypes) {
		if (CollectionUtil.isEmpty(faqTypes)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, faqTypes, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(FAQ_TYPETABLE).values(
					FAQ_TYPETABLE.FAQ_TYPE.value(new JdbcNamedParameter("faqType")),
					FAQ_TYPETABLE.FAQ_PARENT_TYPE_ID.value(new JdbcNamedParameter("faqParentTypeId")),
					FAQ_TYPETABLE.FAQ_TYPE_CREATDAY.value(new JdbcNamedParameter("faqTypeCreatDay")),
					FAQ_TYPETABLE.FAQ_CREATED_BY.value(new JdbcNamedParameter("faqCreatedBy")),
					FAQ_TYPETABLE.DELETED.value(new JdbcNamedParameter("deleted")));
			}
		});
	}
@LogMethod("batchInsert")
	public int[] batchInsert(List<FaqType> faqTypes){
			return batchInsert(true ,faqTypes);
	}
@LogMethod("batchUpdate")
	public int[] batchUpdate(List<FaqType> faqTypes) {
		if (CollectionUtil.isEmpty(faqTypes)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(faqTypes, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(FAQ_TYPETABLE).set(
					FAQ_TYPETABLE.FAQ_TYPE.value(new JdbcNamedParameter("faqType")),
					FAQ_TYPETABLE.FAQ_PARENT_TYPE_ID.value(new JdbcNamedParameter("faqParentTypeId")),
					FAQ_TYPETABLE.FAQ_TYPE_CREATDAY.value(new JdbcNamedParameter("faqTypeCreatDay")),
					FAQ_TYPETABLE.FAQ_CREATED_BY.value(new JdbcNamedParameter("faqCreatedBy")),
					FAQ_TYPETABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
				FAQ_TYPETABLE.FAQ_TYPE_ID.eq(new JdbcNamedParameter("faqTypeId")));
			}
		});
	}
@LogMethod("batchDelete")
	public int[] batchDelete(List<FaqType> faqTypes) {
		if (CollectionUtil.isEmpty(faqTypes)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(faqTypes, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(FAQ_TYPETABLE).where(and(
				FAQ_TYPETABLE.FAQ_TYPE_ID.eq(new JdbcNamedParameter("faqTypeId")),
				FAQ_TYPETABLE.FAQ_TYPE.eq(new JdbcNamedParameter("faqType")),
				FAQ_TYPETABLE.FAQ_PARENT_TYPE_ID.eq(new JdbcNamedParameter("faqParentTypeId")),
				FAQ_TYPETABLE.FAQ_TYPE_CREATDAY.eq(new JdbcNamedParameter("faqTypeCreatDay")),
				FAQ_TYPETABLE.FAQ_CREATED_BY.eq(new JdbcNamedParameter("faqCreatedBy")),
				FAQ_TYPETABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
			}
		});
	}
@LogMethod("addOrderByElements")
	private  Select addOrderByElements(Select select ,OrderBy... orderBies){
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
