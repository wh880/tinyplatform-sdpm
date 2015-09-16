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

package sdpm.metadata.common.org.tinygroup.alm.quality;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static sdpm.quality.dao.inter.org.tinygroup.alm.quality.constant.TestResultTable.*;
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
import sdpm.quality.dao.inter.org.tinygroup.alm.quality.pojo.TestResult;
import sdpm.quality.dao.inter.org.tinygroup.alm.quality.TestResultDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class TestResultDaoImpl extends TinyDslDaoSupport implements TestResultDao {

	public TestResult add(TestResult testResult) {
		return getDslTemplate().insertAndReturnKey(testResult, new InsertGenerateCallback<TestResult>() {
			public Insert generate(TestResult t) {
				Insert insert = insertInto(TESTRESULTTABLE).values(
					TESTRESULTTABLE.TESTRESULT_ID.value(t.getTestResultId()),
					TESTRESULTTABLE.RUN.value(t.getRun()),
					TESTRESULTTABLE.CASE_ID.value(t.getCaseID()),
					TESTRESULTTABLE.CASE_VERSION.value(t.getCaseVersion()),
					TESTRESULTTABLE.CASE_RESULT.value(t.getCaseResult()),
					TESTRESULTTABLE.CASESTEP_RESULTS.value(t.getCaseStepResults()),
					TESTRESULTTABLE.TESTRESULT_LASTRUNNER.value(t.getTestResultLastRunner()),
					TESTRESULTTABLE.TESTRESULT_DATE.value(t.getTestResultDate()));
				return insert;
			}
		});
	}

	public int edit(TestResult testResult) {
		if(testResult == null || testResult.getTestResultId() == null){
			return 0;
		}
		return getDslTemplate().update(testResult, new UpdateGenerateCallback<TestResult>() {
			public Update generate(TestResult t) {
				Update update = update(TESTRESULTTABLE).set(
					TESTRESULTTABLE.RUN.value(t.getRun()),
					TESTRESULTTABLE.CASE_ID.value(t.getCaseID()),
					TESTRESULTTABLE.CASE_VERSION.value(t.getCaseVersion()),
					TESTRESULTTABLE.CASE_RESULT.value(t.getCaseResult()),
					TESTRESULTTABLE.CASESTEP_RESULTS.value(t.getCaseStepResults()),
					TESTRESULTTABLE.TESTRESULT_LASTRUNNER.value(t.getTestResultLastRunner()),
					TESTRESULTTABLE.TESTRESULT_DATE.value(t.getTestResultDate())).where(
					TESTRESULTTABLE.TESTRESULT_ID.eq(t.getTestResultId()));
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
				return delete(TESTRESULTTABLE).where(TESTRESULTTABLE.TESTRESULT_ID.eq(pk));
			}
		});
	}

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

	public List<TestResult> query(TestResult testResult) {
		if(testResult==null){
			testResult=new TestResult();
		}
		return getDslTemplate().query(testResult, new SelectGenerateCallback<TestResult>() {

			@SuppressWarnings("rawtypes")
			public Select generate(TestResult t) {
				return selectFrom(TESTRESULTTABLE).where(
				and(
					TESTRESULTTABLE.RUN.eq(t.getRun()),
					TESTRESULTTABLE.CASE_ID.eq(t.getCaseID()),
					TESTRESULTTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					TESTRESULTTABLE.CASE_RESULT.eq(t.getCaseResult()),
					TESTRESULTTABLE.CASESTEP_RESULTS.eq(t.getCaseStepResults()),
					TESTRESULTTABLE.TESTRESULT_LASTRUNNER.eq(t.getTestResultLastRunner()),
					TESTRESULTTABLE.TESTRESULT_DATE.eq(t.getTestResultDate())));
			}
		});
	}

	public Pager<TestResult> queryPager(int start,int limit ,TestResult testResult) {
		if(testResult==null){
			testResult=new TestResult();
		}
		return getDslTemplate().queryPager(start, limit, testResult, false, new SelectGenerateCallback<TestResult>() {

			public Select generate(TestResult t) {
				return MysqlSelect.selectFrom(TESTRESULTTABLE).where(
				and(
					TESTRESULTTABLE.RUN.eq(t.getRun()),
					TESTRESULTTABLE.CASE_ID.eq(t.getCaseID()),
					TESTRESULTTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					TESTRESULTTABLE.CASE_RESULT.eq(t.getCaseResult()),
					TESTRESULTTABLE.CASESTEP_RESULTS.eq(t.getCaseStepResults()),
					TESTRESULTTABLE.TESTRESULT_LASTRUNNER.eq(t.getTestResultLastRunner()),
					TESTRESULTTABLE.TESTRESULT_DATE.eq(t.getTestResultDate())));
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
					TESTRESULTTABLE.RUN.value(new JdbcNamedParameter("run")),
					TESTRESULTTABLE.CASE_ID.value(new JdbcNamedParameter("caseID")),
					TESTRESULTTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					TESTRESULTTABLE.CASE_RESULT.value(new JdbcNamedParameter("caseResult")),
					TESTRESULTTABLE.CASESTEP_RESULTS.value(new JdbcNamedParameter("caseStepResults")),
					TESTRESULTTABLE.TESTRESULT_LASTRUNNER.value(new JdbcNamedParameter("testResultLastRunner")),
					TESTRESULTTABLE.TESTRESULT_DATE.value(new JdbcNamedParameter("testResultDate")));
			}
		});
	}

	public int[] batchInsert(List<TestResult> testResults){
			return batchInsert(true ,testResults);
	}

	public int[] batchUpdate(List<TestResult> testResults) {
		if (CollectionUtil.isEmpty(testResults)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(testResults, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(TESTRESULTTABLE).set(
					TESTRESULTTABLE.RUN.value(new JdbcNamedParameter("run")),
					TESTRESULTTABLE.CASE_ID.value(new JdbcNamedParameter("caseID")),
					TESTRESULTTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					TESTRESULTTABLE.CASE_RESULT.value(new JdbcNamedParameter("caseResult")),
					TESTRESULTTABLE.CASESTEP_RESULTS.value(new JdbcNamedParameter("caseStepResults")),
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
				TESTRESULTTABLE.RUN.eq(new JdbcNamedParameter("run")),
				TESTRESULTTABLE.CASE_ID.eq(new JdbcNamedParameter("caseID")),
				TESTRESULTTABLE.CASE_VERSION.eq(new JdbcNamedParameter("caseVersion")),
				TESTRESULTTABLE.CASE_RESULT.eq(new JdbcNamedParameter("caseResult")),
				TESTRESULTTABLE.CASESTEP_RESULTS.eq(new JdbcNamedParameter("caseStepResults")),
				TESTRESULTTABLE.TESTRESULT_LASTRUNNER.eq(new JdbcNamedParameter("testResultLastRunner")),
				TESTRESULTTABLE.TESTRESULT_DATE.eq(new JdbcNamedParameter("testResultDate"))));
			}
		});
	}

}
