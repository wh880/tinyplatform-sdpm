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
import static org.tinygroup.sdpm.quality.dao.constant.TestResultTable.*;
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
import org.tinygroup.sdpm.quality.dao.pojo.TestResult;
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.sdpm.quality.dao.TestResultDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

@Repository
@LogClass("testResult")
public class TestResultDaoImpl extends TinyDslDaoSupport implements TestResultDao {
	
	@LogMethod("add")
	public TestResult add(TestResult testResult) {
		return getDslTemplate().insertAndReturnKey(testResult, new InsertGenerateCallback<TestResult>() {
			public Insert generate(TestResult t) {
				Insert insert = insertInto(TESTRESULTTABLE).values(
					TESTRESULTTABLE.TESTRESULT_ID.value(t.getTestResultId()),
					TESTRESULTTABLE.TESTRESULT_RUN.value(t.getTestresultRun()),
					TESTRESULTTABLE.LINK_CASE.value(t.getLinkCase()),
					TESTRESULTTABLE.CASE_VERSION.value(t.getCaseVersion()),
					TESTRESULTTABLE.CASE_RESULT.value(t.getCaseResult()),
					TESTRESULTTABLE.CASE_STEPRESULTS.value(t.getCaseStepresults()),
					TESTRESULTTABLE.TESTRESULT_LASTRUNNER.value(t.getTestResultLastRunner()),
					TESTRESULTTABLE.TESTRESULT_DATE.value(t.getTestResultDate()));
				return insert;
			}
		});
	}
	@LogMethod("edit")
	public int edit(TestResult testResult) {
		if(testResult == null || testResult.getTestResultId() == null){
			return 0;
		}
		return getDslTemplate().update(testResult, new UpdateGenerateCallback<TestResult>() {
			public Update generate(TestResult t) {
				Update update = update(TESTRESULTTABLE).set(
					TESTRESULTTABLE.TESTRESULT_RUN.value(t.getTestresultRun()),
					TESTRESULTTABLE.LINK_CASE.value(t.getLinkCase()),
					TESTRESULTTABLE.CASE_VERSION.value(t.getCaseVersion()),
					TESTRESULTTABLE.CASE_RESULT.value(t.getCaseResult()),
					TESTRESULTTABLE.CASE_STEPRESULTS.value(t.getCaseStepresults()),
					TESTRESULTTABLE.TESTRESULT_LASTRUNNER.value(t.getTestResultLastRunner()),
					TESTRESULTTABLE.TESTRESULT_DATE.value(t.getTestResultDate())).where(
					TESTRESULTTABLE.TESTRESULT_ID.eq(t.getTestResultId()));
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
				return delete(TESTRESULTTABLE).where(TESTRESULTTABLE.TESTRESULT_ID.eq(pk));
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
				return delete(TESTRESULTTABLE).where(TESTRESULTTABLE.TESTRESULT_ID.in(t));
		}
		},pks);
	}

	public TestResult getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, TestResult.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(TESTRESULTTABLE).where(TESTRESULTTABLE.TESTRESULT_ID.eq(t));
			}
		});
	}

	public List<TestResult> query(TestResult testResult ,final OrderBy... orderBies) {
		if(testResult==null){
			testResult=new TestResult();
		}
		return getDslTemplate().query(testResult, new SelectGenerateCallback<TestResult>() {

			@SuppressWarnings("rawtypes")
			public Select generate(TestResult t) {
				Select select = selectFrom(TESTRESULTTABLE).where(
				and(
					TESTRESULTTABLE.TESTRESULT_RUN.eq(t.getTestresultRun()),
					TESTRESULTTABLE.LINK_CASE.eq(t.getLinkCase()),
					TESTRESULTTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					TESTRESULTTABLE.CASE_RESULT.eq(t.getCaseResult()),
					TESTRESULTTABLE.CASE_STEPRESULTS.eq(t.getCaseStepresults()),
					TESTRESULTTABLE.TESTRESULT_LASTRUNNER.eq(t.getTestResultLastRunner()),
					TESTRESULTTABLE.TESTRESULT_DATE.eq(t.getTestResultDate())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<TestResult> queryPager(int start,int limit ,TestResult testResult ,final OrderBy... orderBies) {
		if(testResult==null){
			testResult=new TestResult();
		}
		return getDslTemplate().queryPager(start, limit, testResult, false, new SelectGenerateCallback<TestResult>() {

			public Select generate(TestResult t) {
				Select select = MysqlSelect.selectFrom(TESTRESULTTABLE).where(
				and(
					TESTRESULTTABLE.TESTRESULT_RUN.eq(t.getTestresultRun()),
					TESTRESULTTABLE.LINK_CASE.eq(t.getLinkCase()),
					TESTRESULTTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					TESTRESULTTABLE.CASE_RESULT.eq(t.getCaseResult()),
					TESTRESULTTABLE.CASE_STEPRESULTS.eq(t.getCaseStepresults()),
					TESTRESULTTABLE.TESTRESULT_LASTRUNNER.eq(t.getTestResultLastRunner()),
					TESTRESULTTABLE.TESTRESULT_DATE.eq(t.getTestResultDate())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<TestResult> testResults) {
		if (CollectionUtil.isEmpty(testResults)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, testResults, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(TESTRESULTTABLE).values(
					TESTRESULTTABLE.TESTRESULT_RUN.value(new JdbcNamedParameter("testresultRun")),
					TESTRESULTTABLE.LINK_CASE.value(new JdbcNamedParameter("linkCase")),
					TESTRESULTTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					TESTRESULTTABLE.CASE_RESULT.value(new JdbcNamedParameter("caseResult")),
					TESTRESULTTABLE.CASE_STEPRESULTS.value(new JdbcNamedParameter("caseStepresults")),
					TESTRESULTTABLE.TESTRESULT_LASTRUNNER.value(new JdbcNamedParameter("testResultLastRunner")),
					TESTRESULTTABLE.TESTRESULT_DATE.value(new JdbcNamedParameter("testResultDate")));
			}
		});
	}

	public int[] batchInsert(List<TestResult> testResults){
			return batchInsert(true ,testResults);
	}
	@LogMethod("batchUpdate")
	public int[] batchUpdate(List<TestResult> testResults) {
		if (CollectionUtil.isEmpty(testResults)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(testResults, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(TESTRESULTTABLE).set(
					TESTRESULTTABLE.TESTRESULT_RUN.value(new JdbcNamedParameter("testresultRun")),
					TESTRESULTTABLE.LINK_CASE.value(new JdbcNamedParameter("linkCase")),
					TESTRESULTTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					TESTRESULTTABLE.CASE_RESULT.value(new JdbcNamedParameter("caseResult")),
					TESTRESULTTABLE.CASE_STEPRESULTS.value(new JdbcNamedParameter("caseStepresults")),
					TESTRESULTTABLE.TESTRESULT_LASTRUNNER.value(new JdbcNamedParameter("testResultLastRunner")),
					TESTRESULTTABLE.TESTRESULT_DATE.value(new JdbcNamedParameter("testResultDate"))).where(
				TESTRESULTTABLE.TESTRESULT_ID.eq(new JdbcNamedParameter("testResultId")));
			}
		});
	}

	public int[] batchDelete(List<TestResult> testResults) {
		if (CollectionUtil.isEmpty(testResults)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(testResults, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(TESTRESULTTABLE).where(and(
				TESTRESULTTABLE.TESTRESULT_ID.eq(new JdbcNamedParameter("testResultId")),
				TESTRESULTTABLE.TESTRESULT_RUN.eq(new JdbcNamedParameter("testresultRun")),
				TESTRESULTTABLE.LINK_CASE.eq(new JdbcNamedParameter("linkCase")),
				TESTRESULTTABLE.CASE_VERSION.eq(new JdbcNamedParameter("caseVersion")),
				TESTRESULTTABLE.CASE_RESULT.eq(new JdbcNamedParameter("caseResult")),
				TESTRESULTTABLE.CASE_STEPRESULTS.eq(new JdbcNamedParameter("caseStepresults")),
				TESTRESULTTABLE.TESTRESULT_LASTRUNNER.eq(new JdbcNamedParameter("testResultLastRunner")),
				TESTRESULTTABLE.TESTRESULT_DATE.eq(new JdbcNamedParameter("testResultDate"))));
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
