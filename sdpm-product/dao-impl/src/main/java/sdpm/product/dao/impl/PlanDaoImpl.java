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

package sdpm.product.dao.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static sdpm.product.dao.constant.PlanTable.*;
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

import sdpm.product.dao.inter.PlanDao;
import sdpm.product.dao.pojo.Plan;

import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class PlanDaoImpl extends TinyDslDaoSupport implements PlanDao {

	public Plan add(Plan plan) {
		return getDslTemplate().insertAndReturnKey(plan, new InsertGenerateCallback<Plan>() {
			public Insert generate(Plan t) {
				Insert insert = insertInto(PLANTABLE).values(
					PLANTABLE.PLAN_ID.value(t.getPlanId()),
					PLANTABLE.COMPANY_ID.value(t.getCompanyId()),
					PLANTABLE.PRODUCT_ID.value(t.getProductId()),
					PLANTABLE.PLAN_NAME.value(t.getPlanName()),
					PLANTABLE.PLAN_SPEC.value(t.getPlanSpec()),
					PLANTABLE.PLAN_BEGINDATE.value(t.getPlanBeginDate()),
					PLANTABLE.PLAN_ENDDATE.value(t.getPlanEndDate()),
					PLANTABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}

	public int edit(Plan plan) {
		if(plan == null || plan.getPlanId() == null){
			return 0;
		}
		return getDslTemplate().update(plan, new UpdateGenerateCallback<Plan>() {
			public Update generate(Plan t) {
				Update update = update(PLANTABLE).set(
					PLANTABLE.COMPANY_ID.value(t.getCompanyId()),
					PLANTABLE.PRODUCT_ID.value(t.getProductId()),
					PLANTABLE.PLAN_NAME.value(t.getPlanName()),
					PLANTABLE.PLAN_SPEC.value(t.getPlanSpec()),
					PLANTABLE.PLAN_BEGINDATE.value(t.getPlanBeginDate()),
					PLANTABLE.PLAN_ENDDATE.value(t.getPlanEndDate()),
					PLANTABLE.DELETED.value(t.getDeleted())).where(
					PLANTABLE.PLAN_ID.eq(t.getPlanId()));
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
				return delete(PLANTABLE).where(PLANTABLE.PLAN_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(PLANTABLE).where(PLANTABLE.PLAN_ID.in(t));
		}
		},pks);
	}

	public Plan getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Plan.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(PLANTABLE).where(PLANTABLE.PLAN_ID.eq(t));
			}
		});
	}

	public List<Plan> query(Plan plan) {
		if(plan==null){
			plan=new Plan();
		}
		return getDslTemplate().query(plan, new SelectGenerateCallback<Plan>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Plan t) {
				return selectFrom(PLANTABLE).where(
				and(
					PLANTABLE.COMPANY_ID.eq(t.getCompanyId()),
					PLANTABLE.PRODUCT_ID.eq(t.getProductId()),
					PLANTABLE.PLAN_NAME.eq(t.getPlanName()),
					PLANTABLE.PLAN_SPEC.eq(t.getPlanSpec()),
					PLANTABLE.PLAN_BEGINDATE.eq(t.getPlanBeginDate()),
					PLANTABLE.PLAN_ENDDATE.eq(t.getPlanEndDate()),
					PLANTABLE.DELETED.eq(t.getDeleted())));
			}
		});
	}

	public Pager<Plan> queryPager(int start,int limit ,Plan plan) {
		if(plan==null){
			plan=new Plan();
		}
		return getDslTemplate().queryPager(start, limit, plan, false, new SelectGenerateCallback<Plan>() {

			public Select generate(Plan t) {
				return MysqlSelect.selectFrom(PLANTABLE).where(
				and(
					PLANTABLE.COMPANY_ID.eq(t.getCompanyId()),
					PLANTABLE.PRODUCT_ID.eq(t.getProductId()),
					PLANTABLE.PLAN_NAME.eq(t.getPlanName()),
					PLANTABLE.PLAN_SPEC.eq(t.getPlanSpec()),
					PLANTABLE.PLAN_BEGINDATE.eq(t.getPlanBeginDate()),
					PLANTABLE.PLAN_ENDDATE.eq(t.getPlanEndDate()),
					PLANTABLE.DELETED.eq(t.getDeleted())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Plan> plans) {
		if (CollectionUtil.isEmpty(plans)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, plans, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(PLANTABLE).values(
					PLANTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					PLANTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					PLANTABLE.PLAN_NAME.value(new JdbcNamedParameter("planName")),
					PLANTABLE.PLAN_SPEC.value(new JdbcNamedParameter("planSpec")),
					PLANTABLE.PLAN_BEGINDATE.value(new JdbcNamedParameter("planBeginDate")),
					PLANTABLE.PLAN_ENDDATE.value(new JdbcNamedParameter("planEndDate")),
					PLANTABLE.DELETED.value(new JdbcNamedParameter("deleted")));
			}
		});
	}

	public int[] batchInsert(List<Plan> plans){
			return batchInsert(true ,plans);
	}

	public int[] batchUpdate(List<Plan> plans) {
		if (CollectionUtil.isEmpty(plans)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(plans, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(PLANTABLE).set(
					PLANTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					PLANTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					PLANTABLE.PLAN_NAME.value(new JdbcNamedParameter("planName")),
					PLANTABLE.PLAN_SPEC.value(new JdbcNamedParameter("planSpec")),
					PLANTABLE.PLAN_BEGINDATE.value(new JdbcNamedParameter("planBeginDate")),
					PLANTABLE.PLAN_ENDDATE.value(new JdbcNamedParameter("planEndDate")),
					PLANTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
				PLANTABLE.PLAN_ID.eq(new JdbcNamedParameter("planId")));
			}
		});
	}

	public int[] batchDelete(List<Plan> plans) {
		if (CollectionUtil.isEmpty(plans)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(plans, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(PLANTABLE).where(and(
				PLANTABLE.PLAN_ID.eq(new JdbcNamedParameter("planId")),
				PLANTABLE.COMPANY_ID.eq(new JdbcNamedParameter("companyId")),
				PLANTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
				PLANTABLE.PLAN_NAME.eq(new JdbcNamedParameter("planName")),
				PLANTABLE.PLAN_SPEC.eq(new JdbcNamedParameter("planSpec")),
				PLANTABLE.PLAN_BEGINDATE.eq(new JdbcNamedParameter("planBeginDate")),
				PLANTABLE.PLAN_ENDDATE.eq(new JdbcNamedParameter("planEndDate")),
				PLANTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
			}
		});
	}

}
