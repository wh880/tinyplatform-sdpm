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

import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.quality.dao.QualityCaseStepDao;
import org.tinygroup.sdpm.quality.dao.pojo.QualityCaseStep;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
import org.tinygroup.tinysqldsl.select.OrderByElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static org.tinygroup.sdpm.quality.dao.constant.QualityCaseStepTable.QUALITY_CASE_STEPTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.select;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

@Repository
public class QualityCaseStepDaoImpl extends TinyDslDaoSupport implements QualityCaseStepDao {

	public QualityCaseStep add(QualityCaseStep qualityCaseStep) {
		return getDslTemplate().insertAndReturnKey(qualityCaseStep, new InsertGenerateCallback<QualityCaseStep>() {
			public Insert generate(QualityCaseStep t) {
				Insert insert = insertInto(QUALITY_CASE_STEPTABLE).values(
					QUALITY_CASE_STEPTABLE.CASE_STEP_ID.value(t.getCaseStepId()),
					QUALITY_CASE_STEPTABLE.CASE_ID.value(t.getCaseId()),
					QUALITY_CASE_STEPTABLE.CASE_VERSION.value(t.getCaseVersion()),
					QUALITY_CASE_STEPTABLE.CASE_STEP_DESC.value(t.getCaseStepDesc()),
					QUALITY_CASE_STEPTABLE.CASE_STEP_EXPECT.value(t.getCaseStepExpect()));
				return insert;
			}
		});
	}

	public int edit(QualityCaseStep qualityCaseStep) {
		if(qualityCaseStep == null || qualityCaseStep.getCaseStepId() == null){
			return 0;
		}
		return getDslTemplate().update(qualityCaseStep, new UpdateGenerateCallback<QualityCaseStep>() {
			public Update generate(QualityCaseStep t) {
				Update update = update(QUALITY_CASE_STEPTABLE).set(
					QUALITY_CASE_STEPTABLE.CASE_ID.value(t.getCaseId()),
					QUALITY_CASE_STEPTABLE.CASE_VERSION.value(t.getCaseVersion()),
					QUALITY_CASE_STEPTABLE.CASE_STEP_DESC.value(t.getCaseStepDesc()),
					QUALITY_CASE_STEPTABLE.CASE_STEP_EXPECT.value(t.getCaseStepExpect())).where(
					QUALITY_CASE_STEPTABLE.CASE_STEP_ID.eq(t.getCaseStepId()));
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
				return delete(QUALITY_CASE_STEPTABLE).where(QUALITY_CASE_STEPTABLE.CASE_STEP_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(QUALITY_CASE_STEPTABLE).where(QUALITY_CASE_STEPTABLE.CASE_STEP_ID.in(t));
		}
		},pks);
	}

	public QualityCaseStep getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, QualityCaseStep.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(QUALITY_CASE_STEPTABLE).where(QUALITY_CASE_STEPTABLE.CASE_STEP_ID.eq(t));
			}
		});
	}

	public List<QualityCaseStep> query(QualityCaseStep qualityCaseStep ,final OrderBy... orderArgs) {
		if(qualityCaseStep==null){
			qualityCaseStep=new QualityCaseStep();
		}
		return getDslTemplate().query(qualityCaseStep, new SelectGenerateCallback<QualityCaseStep>() {

			@SuppressWarnings("rawtypes")
			public Select generate(QualityCaseStep t) {
				Select select = selectFrom(QUALITY_CASE_STEPTABLE).where(
				and(
					QUALITY_CASE_STEPTABLE.CASE_ID.eq(t.getCaseId()),
					QUALITY_CASE_STEPTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					QUALITY_CASE_STEPTABLE.CASE_STEP_DESC.eq(t.getCaseStepDesc()),
					QUALITY_CASE_STEPTABLE.CASE_STEP_EXPECT.eq(t.getCaseStepExpect())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	public Pager<QualityCaseStep> queryPager(int start,int limit ,QualityCaseStep qualityCaseStep ,final OrderBy... orderArgs) {
		if(qualityCaseStep==null){
			qualityCaseStep=new QualityCaseStep();
		}
		return getDslTemplate().queryPager(start, limit, qualityCaseStep, false, new SelectGenerateCallback<QualityCaseStep>() {

			public Select generate(QualityCaseStep t) {
				Select select = MysqlSelect.selectFrom(QUALITY_CASE_STEPTABLE).where(
				and(
					QUALITY_CASE_STEPTABLE.CASE_ID.eq(t.getCaseId()),
					QUALITY_CASE_STEPTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					QUALITY_CASE_STEPTABLE.CASE_STEP_DESC.eq(t.getCaseStepDesc()),
					QUALITY_CASE_STEPTABLE.CASE_STEP_EXPECT.eq(t.getCaseStepExpect())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<QualityCaseStep> qualityCaseSteps) {
		if (CollectionUtil.isEmpty(qualityCaseSteps)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, qualityCaseSteps, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(QUALITY_CASE_STEPTABLE).values(
					QUALITY_CASE_STEPTABLE.CASE_ID.value(new JdbcNamedParameter("caseId")),
					QUALITY_CASE_STEPTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					QUALITY_CASE_STEPTABLE.CASE_STEP_DESC.value(new JdbcNamedParameter("caseStepDesc")),
					QUALITY_CASE_STEPTABLE.CASE_STEP_EXPECT.value(new JdbcNamedParameter("caseStepExpect")));
			}
		});
	}

	public int[] batchInsert(List<QualityCaseStep> qualityCaseSteps){
			return batchInsert(true ,qualityCaseSteps);
	}

	public int[] batchUpdate(List<QualityCaseStep> qualityCaseSteps) {
		if (CollectionUtil.isEmpty(qualityCaseSteps)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(qualityCaseSteps, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(QUALITY_CASE_STEPTABLE).set(
					QUALITY_CASE_STEPTABLE.CASE_ID.value(new JdbcNamedParameter("caseId")),
					QUALITY_CASE_STEPTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					QUALITY_CASE_STEPTABLE.CASE_STEP_DESC.value(new JdbcNamedParameter("caseStepDesc")),
					QUALITY_CASE_STEPTABLE.CASE_STEP_EXPECT.value(new JdbcNamedParameter("caseStepExpect"))).where(
				QUALITY_CASE_STEPTABLE.CASE_STEP_ID.eq(new JdbcNamedParameter("caseStepId")));
			}
		});
	}

	public int[] batchDelete(List<QualityCaseStep> qualityCaseSteps) {
		if (CollectionUtil.isEmpty(qualityCaseSteps)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(qualityCaseSteps, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(QUALITY_CASE_STEPTABLE).where(and(
				QUALITY_CASE_STEPTABLE.CASE_STEP_ID.eq(new JdbcNamedParameter("caseStepId")),
				QUALITY_CASE_STEPTABLE.CASE_ID.eq(new JdbcNamedParameter("caseId")),
				QUALITY_CASE_STEPTABLE.CASE_VERSION.eq(new JdbcNamedParameter("caseVersion")),
				QUALITY_CASE_STEPTABLE.CASE_STEP_DESC.eq(new JdbcNamedParameter("caseStepDesc")),
				QUALITY_CASE_STEPTABLE.CASE_STEP_EXPECT.eq(new JdbcNamedParameter("caseStepExpect"))));
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

	public Integer getMaxVersion(Integer testCaseId) {
		Select select = select(QUALITY_CASE_STEPTABLE.CASE_VERSION.max()).from(QUALITY_CASE_STEPTABLE).where(QUALITY_CASE_STEPTABLE.CASE_ID.eq(testCaseId));
		return getDslSession().fetchOneResult(select,Integer.class);
	}
}
