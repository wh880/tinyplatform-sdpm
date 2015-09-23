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
import static org.tinygroup.sdpm.quality.dao.constant.QualityTestResultTable.*;
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
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Repository;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
	import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestResult;
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.sdpm.quality.dao.QualityTestResultDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

@LogClass("qualityTestResult")
@Repository
public class QualityTestResultDaoImpl extends TinyDslDaoSupport implements QualityTestResultDao {
	
	@LogMethod("add")
	public QualityTestResult add(QualityTestResult qualityTestResult) {
		return getDslTemplate().insertAndReturnKey(qualityTestResult, new InsertGenerateCallback<QualityTestResult>() {
			public Insert generate(QualityTestResult t) {
				Insert insert = insertInto(QUALITY_TESTRESULTTABLE).values(
					QUALITY_TESTRESULTTABLE.TESTRESULT_ID.value(t.getTestResultId()),
					QUALITY_TESTRESULTTABLE.TESTRESULT_RUN.value(t.getTestresultRun()),
					QUALITY_TESTRESULTTABLE.LINK_CASE.value(t.getLinkCase()),
					QUALITY_TESTRESULTTABLE.CASE_VERSION.value(t.getCaseVersion()),
					QUALITY_TESTRESULTTABLE.CASE_RESULT.value(t.getCaseResult()),
					QUALITY_TESTRESULTTABLE.CASE_STEPRESULTS.value(t.getCaseStepresults()),
					QUALITY_TESTRESULTTABLE.TESTRESULT_LASTRUNNER.value(t.getTestResultLastRunner()),
					QUALITY_TESTRESULTTABLE.TESTRESULT_DATE.value(t.getTestResultDate()));
				return insert;
			}
		});
	}
	@LogMethod("edit")
	public int edit(QualityTestResult qualityTestResult) {
		if(qualityTestResult == null || qualityTestResult.getTestResultId() == null){
			return 0;
		}
		return getDslTemplate().update(qualityTestResult, new UpdateGenerateCallback<QualityTestResult>() {
			public Update generate(QualityTestResult t) {
				Update update = update(QUALITY_TESTRESULTTABLE).set(
					QUALITY_TESTRESULTTABLE.TESTRESULT_RUN.value(t.getTestresultRun()),
					QUALITY_TESTRESULTTABLE.LINK_CASE.value(t.getLinkCase()),
					QUALITY_TESTRESULTTABLE.CASE_VERSION.value(t.getCaseVersion()),
					QUALITY_TESTRESULTTABLE.CASE_RESULT.value(t.getCaseResult()),
					QUALITY_TESTRESULTTABLE.CASE_STEPRESULTS.value(t.getCaseStepresults()),
					QUALITY_TESTRESULTTABLE.TESTRESULT_LASTRUNNER.value(t.getTestResultLastRunner()),
					QUALITY_TESTRESULTTABLE.TESTRESULT_DATE.value(t.getTestResultDate())).where(
					QUALITY_TESTRESULTTABLE.TESTRESULT_ID.eq(t.getTestResultId()));
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
				return delete(QUALITY_TESTRESULTTABLE).where(QUALITY_TESTRESULTTABLE.TESTRESULT_ID.eq(pk));
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
				return delete(QUALITY_TESTRESULTTABLE).where(QUALITY_TESTRESULTTABLE.TESTRESULT_ID.in(t));
		}
		},pks);
	}

	public QualityTestResult getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, QualityTestResult.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(QUALITY_TESTRESULTTABLE).where(QUALITY_TESTRESULTTABLE.TESTRESULT_ID.eq(t));
			}
		});
	}

	public List<QualityTestResult> query(QualityTestResult qualityTestResult ,final OrderBy... orderBies) {
		if(qualityTestResult==null){
			qualityTestResult=new QualityTestResult();
		}
		return getDslTemplate().query(qualityTestResult, new SelectGenerateCallback<QualityTestResult>() {

			@SuppressWarnings("rawtypes")
			public Select generate(QualityTestResult t) {
				Select select = selectFrom(QUALITY_TESTRESULTTABLE).where(
				and(
					QUALITY_TESTRESULTTABLE.TESTRESULT_RUN.eq(t.getTestresultRun()),
					QUALITY_TESTRESULTTABLE.LINK_CASE.eq(t.getLinkCase()),
					QUALITY_TESTRESULTTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					QUALITY_TESTRESULTTABLE.CASE_RESULT.eq(t.getCaseResult()),
					QUALITY_TESTRESULTTABLE.CASE_STEPRESULTS.eq(t.getCaseStepresults()),
					QUALITY_TESTRESULTTABLE.TESTRESULT_LASTRUNNER.eq(t.getTestResultLastRunner()),
					QUALITY_TESTRESULTTABLE.TESTRESULT_DATE.eq(t.getTestResultDate())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<QualityTestResult> queryPager(int start,int limit ,QualityTestResult qualityTestResult ,final OrderBy... orderBies) {
		if(qualityTestResult==null){
			qualityTestResult=new QualityTestResult();
		}
		return getDslTemplate().queryPager(start, limit, qualityTestResult, false, new SelectGenerateCallback<QualityTestResult>() {

			public Select generate(QualityTestResult t) {
				Select select = MysqlSelect.selectFrom(QUALITY_TESTRESULTTABLE).where(
				and(
					QUALITY_TESTRESULTTABLE.TESTRESULT_RUN.eq(t.getTestresultRun()),
					QUALITY_TESTRESULTTABLE.LINK_CASE.eq(t.getLinkCase()),
					QUALITY_TESTRESULTTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					QUALITY_TESTRESULTTABLE.CASE_RESULT.eq(t.getCaseResult()),
					QUALITY_TESTRESULTTABLE.CASE_STEPRESULTS.eq(t.getCaseStepresults()),
					QUALITY_TESTRESULTTABLE.TESTRESULT_LASTRUNNER.eq(t.getTestResultLastRunner()),
					QUALITY_TESTRESULTTABLE.TESTRESULT_DATE.eq(t.getTestResultDate())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<QualityTestResult> qualityTestResults) {
		if (CollectionUtil.isEmpty(qualityTestResults)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, qualityTestResults, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(QUALITY_TESTRESULTTABLE).values(
					QUALITY_TESTRESULTTABLE.TESTRESULT_RUN.value(new JdbcNamedParameter("testresultRun")),
					QUALITY_TESTRESULTTABLE.LINK_CASE.value(new JdbcNamedParameter("linkCase")),
					QUALITY_TESTRESULTTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					QUALITY_TESTRESULTTABLE.CASE_RESULT.value(new JdbcNamedParameter("caseResult")),
					QUALITY_TESTRESULTTABLE.CASE_STEPRESULTS.value(new JdbcNamedParameter("caseStepresults")),
					QUALITY_TESTRESULTTABLE.TESTRESULT_LASTRUNNER.value(new JdbcNamedParameter("testResultLastRunner")),
					QUALITY_TESTRESULTTABLE.TESTRESULT_DATE.value(new JdbcNamedParameter("testResultDate")));
			}
		});
	}

	public int[] batchInsert(List<QualityTestResult> qualityTestResults){
			return batchInsert(true ,qualityTestResults);
	}
	@LogMethod("batchUpdate")
	public int[] batchUpdate(List<QualityTestResult> qualityTestResults) {
		if (CollectionUtil.isEmpty(qualityTestResults)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(qualityTestResults, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(QUALITY_TESTRESULTTABLE).set(
					QUALITY_TESTRESULTTABLE.TESTRESULT_RUN.value(new JdbcNamedParameter("testresultRun")),
					QUALITY_TESTRESULTTABLE.LINK_CASE.value(new JdbcNamedParameter("linkCase")),
					QUALITY_TESTRESULTTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					QUALITY_TESTRESULTTABLE.CASE_RESULT.value(new JdbcNamedParameter("caseResult")),
					QUALITY_TESTRESULTTABLE.CASE_STEPRESULTS.value(new JdbcNamedParameter("caseStepresults")),
					QUALITY_TESTRESULTTABLE.TESTRESULT_LASTRUNNER.value(new JdbcNamedParameter("testResultLastRunner")),
					QUALITY_TESTRESULTTABLE.TESTRESULT_DATE.value(new JdbcNamedParameter("testResultDate"))).where(
				QUALITY_TESTRESULTTABLE.TESTRESULT_ID.eq(new JdbcNamedParameter("testResultId")));
			}
		});
	}
	@LogMethod("batchDelete")
	public int[] batchDelete(List<QualityTestResult> qualityTestResults) {
		if (CollectionUtil.isEmpty(qualityTestResults)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(qualityTestResults, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(QUALITY_TESTRESULTTABLE).where(and(
				QUALITY_TESTRESULTTABLE.TESTRESULT_ID.eq(new JdbcNamedParameter("testResultId")),
				QUALITY_TESTRESULTTABLE.TESTRESULT_RUN.eq(new JdbcNamedParameter("testresultRun")),
				QUALITY_TESTRESULTTABLE.LINK_CASE.eq(new JdbcNamedParameter("linkCase")),
				QUALITY_TESTRESULTTABLE.CASE_VERSION.eq(new JdbcNamedParameter("caseVersion")),
				QUALITY_TESTRESULTTABLE.CASE_RESULT.eq(new JdbcNamedParameter("caseResult")),
				QUALITY_TESTRESULTTABLE.CASE_STEPRESULTS.eq(new JdbcNamedParameter("caseStepresults")),
				QUALITY_TESTRESULTTABLE.TESTRESULT_LASTRUNNER.eq(new JdbcNamedParameter("testResultLastRunner")),
				QUALITY_TESTRESULTTABLE.TESTRESULT_DATE.eq(new JdbcNamedParameter("testResultDate"))));
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
