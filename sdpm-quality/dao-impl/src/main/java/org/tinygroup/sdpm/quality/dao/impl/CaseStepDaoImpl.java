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

package org.tinygroup.sdpm.quality.dao.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.quality.dao.constant.CaseStepTable.*;
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
import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
	import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.sdpm.quality.dao.pojo.CaseStep;
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.sdpm.quality.dao.CaseStepDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;


@LogClass("caseStep")
@Repository
public class CaseStepDaoImpl extends TinyDslDaoSupport implements CaseStepDao {
	
	@LogMethod("add")
	public CaseStep add(CaseStep caseStep) {
		return getDslTemplate().insertAndReturnKey(caseStep, new InsertGenerateCallback<CaseStep>() {
			public Insert generate(CaseStep t) {
				Insert insert = insertInto(CASESTEPTABLE).values(
					CASESTEPTABLE.CASESTEP_ID.value(t.getCaseStepId()),
					CASESTEPTABLE.CASE_ID.value(t.getCaseId()),
					CASESTEPTABLE.CASE_VERSION.value(t.getCaseVersion()),
					CASESTEPTABLE.CASESTEP_DESC.value(t.getCaseStepDesc()),
					CASESTEPTABLE.CASESTEP_EXPECT.value(t.getCaseStepExpect()));
				return insert;
			}
		});
	}
	@LogMethod("edit")
	public int edit(CaseStep caseStep) {
		if(caseStep == null || caseStep.getCaseStepId() == null){
			return 0;
		}
		return getDslTemplate().update(caseStep, new UpdateGenerateCallback<CaseStep>() {
			public Update generate(CaseStep t) {
				Update update = update(CASESTEPTABLE).set(
					CASESTEPTABLE.CASE_ID.value(t.getCaseId()),
					CASESTEPTABLE.CASE_VERSION.value(t.getCaseVersion()),
					CASESTEPTABLE.CASESTEP_DESC.value(t.getCaseStepDesc()),
					CASESTEPTABLE.CASESTEP_EXPECT.value(t.getCaseStepExpect())).where(
					CASESTEPTABLE.CASESTEP_ID.eq(t.getCaseStepId()));
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
				return delete(CASESTEPTABLE).where(CASESTEPTABLE.CASESTEP_ID.eq(pk));
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
				return delete(CASESTEPTABLE).where(CASESTEPTABLE.CASESTEP_ID.in(t));
		}
		},pks);
	}
	
	public CaseStep getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, CaseStep.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(CASESTEPTABLE).where(CASESTEPTABLE.CASESTEP_ID.eq(t));
			}
		});
	}

	public List<CaseStep> query(CaseStep caseStep ,final OrderBy... orderBies) {
		if(caseStep==null){
			caseStep=new CaseStep();
		}
		return getDslTemplate().query(caseStep, new SelectGenerateCallback<CaseStep>() {

			@SuppressWarnings("rawtypes")
			public Select generate(CaseStep t) {
				Select select = selectFrom(CASESTEPTABLE).where(
				and(
					CASESTEPTABLE.CASE_ID.eq(t.getCaseId()),
					CASESTEPTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					CASESTEPTABLE.CASESTEP_DESC.eq(t.getCaseStepDesc()),
					CASESTEPTABLE.CASESTEP_EXPECT.eq(t.getCaseStepExpect())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<CaseStep> queryPager(int start,int limit ,CaseStep caseStep ,final OrderBy... orderBies) {
		if(caseStep==null){
			caseStep=new CaseStep();
		}
		return getDslTemplate().queryPager(start, limit, caseStep, false, new SelectGenerateCallback<CaseStep>() {

			public Select generate(CaseStep t) {
				Select select = MysqlSelect.selectFrom(CASESTEPTABLE).where(
				and(
					CASESTEPTABLE.CASE_ID.eq(t.getCaseId()),
					CASESTEPTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					CASESTEPTABLE.CASESTEP_DESC.eq(t.getCaseStepDesc()),
					CASESTEPTABLE.CASESTEP_EXPECT.eq(t.getCaseStepExpect())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<CaseStep> caseSteps) {
		if (CollectionUtil.isEmpty(caseSteps)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, caseSteps, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(CASESTEPTABLE).values(
					CASESTEPTABLE.CASE_ID.value(new JdbcNamedParameter("caseId")),
					CASESTEPTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					CASESTEPTABLE.CASESTEP_DESC.value(new JdbcNamedParameter("caseStepDesc")),
					CASESTEPTABLE.CASESTEP_EXPECT.value(new JdbcNamedParameter("caseStepExpect")));
			}
		});
	}

	public int[] batchInsert(List<CaseStep> caseSteps){
			return batchInsert(true ,caseSteps);
	}
	@LogMethod("batchUpdate")
	public int[] batchUpdate(List<CaseStep> caseSteps) {
		if (CollectionUtil.isEmpty(caseSteps)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(caseSteps, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(CASESTEPTABLE).set(
					CASESTEPTABLE.CASE_ID.value(new JdbcNamedParameter("caseId")),
					CASESTEPTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					CASESTEPTABLE.CASESTEP_DESC.value(new JdbcNamedParameter("caseStepDesc")),
					CASESTEPTABLE.CASESTEP_EXPECT.value(new JdbcNamedParameter("caseStepExpect"))).where(
				CASESTEPTABLE.CASESTEP_ID.eq(new JdbcNamedParameter("caseStepId")));
			}
		});
	}
	@LogMethod("batchDelete")
	public int[] batchDelete(List<CaseStep> caseSteps) {
		if (CollectionUtil.isEmpty(caseSteps)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(caseSteps, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(CASESTEPTABLE).where(and(
				CASESTEPTABLE.CASESTEP_ID.eq(new JdbcNamedParameter("caseStepId")),
				CASESTEPTABLE.CASE_ID.eq(new JdbcNamedParameter("caseId")),
				CASESTEPTABLE.CASE_VERSION.eq(new JdbcNamedParameter("caseVersion")),
				CASESTEPTABLE.CASESTEP_DESC.eq(new JdbcNamedParameter("caseStepDesc")),
				CASESTEPTABLE.CASESTEP_EXPECT.eq(new JdbcNamedParameter("caseStepExpect"))));
			}
		});
	}

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
