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
import static org.tinygroup.sdpm.quality.dao.constant.TestRunTable.*;
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
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;
	import org.tinygroup.tinysqldsl.select.OrderByElement;
import org.tinygroup.sdpm.quality.dao.pojo.TestRun;
import org.tinygroup.sdpm.quality.dao.TestRunDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class TestRunDaoImpl extends TinyDslDaoSupport implements TestRunDao {

	public TestRun add(TestRun testRun) {
		return getDslTemplate().insertAndReturnKey(testRun, new InsertGenerateCallback<TestRun>() {
			public Insert generate(TestRun t) {
				Insert insert = insertInto(TESTRUNTABLE).values(
					TESTRUNTABLE.TESTRUN_ID.value(t.getTestRunId()),
					TESTRUNTABLE.TASK_ID.value(t.getTaskId()),
					TESTRUNTABLE.CASE_ID.value(t.getCaseId()),
					TESTRUNTABLE.CASE_VERSION.value(t.getCaseVersion()),
					TESTRUNTABLE.TESTRUN_ASSIGNEDTO.value(t.getTestRunAssignedTo()),
					TESTRUNTABLE.TESTRUN_LASTRUNNER.value(t.getTestRunLastRunner()),
					TESTRUNTABLE.TESTRUN_LASTRUNDATE.value(t.getTestRunLastRunDate()),
					TESTRUNTABLE.TESTRUN_LASTRUNRESULT.value(t.getTestRunLastRunResult()),
					TESTRUNTABLE.TESTRUN_STATUS.value(t.getTestRunStatus()));
				return insert;
			}
		});
	}

	public int edit(TestRun testRun) {
		if(testRun == null || testRun.getTestRunId() == null){
			return 0;
		}
		return getDslTemplate().update(testRun, new UpdateGenerateCallback<TestRun>() {
			public Update generate(TestRun t) {
				Update update = update(TESTRUNTABLE).set(
					TESTRUNTABLE.TASK_ID.value(t.getTaskId()),
					TESTRUNTABLE.CASE_ID.value(t.getCaseId()),
					TESTRUNTABLE.CASE_VERSION.value(t.getCaseVersion()),
					TESTRUNTABLE.TESTRUN_ASSIGNEDTO.value(t.getTestRunAssignedTo()),
					TESTRUNTABLE.TESTRUN_LASTRUNNER.value(t.getTestRunLastRunner()),
					TESTRUNTABLE.TESTRUN_LASTRUNDATE.value(t.getTestRunLastRunDate()),
					TESTRUNTABLE.TESTRUN_LASTRUNRESULT.value(t.getTestRunLastRunResult()),
					TESTRUNTABLE.TESTRUN_STATUS.value(t.getTestRunStatus())).where(
					TESTRUNTABLE.TESTRUN_ID.eq(t.getTestRunId()));
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
				return delete(TESTRUNTABLE).where(TESTRUNTABLE.TESTRUN_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(TESTRUNTABLE).where(TESTRUNTABLE.TESTRUN_ID.in(t));
		}
		},pks);
	}

	public TestRun getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, TestRun.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(TESTRUNTABLE).where(TESTRUNTABLE.TESTRUN_ID.eq(t));
			}
		});
	}

	public List<TestRun> query(TestRun testRun ,final OrderBy... orderBies) {
		if(testRun==null){
			testRun=new TestRun();
		}
		return getDslTemplate().query(testRun, new SelectGenerateCallback<TestRun>() {

			@SuppressWarnings("rawtypes")
			public Select generate(TestRun t) {
				Select select = selectFrom(TESTRUNTABLE).where(
				and(
					TESTRUNTABLE.TASK_ID.eq(t.getTaskId()),
					TESTRUNTABLE.CASE_ID.eq(t.getCaseId()),
					TESTRUNTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					TESTRUNTABLE.TESTRUN_ASSIGNEDTO.eq(t.getTestRunAssignedTo()),
					TESTRUNTABLE.TESTRUN_LASTRUNNER.eq(t.getTestRunLastRunner()),
					TESTRUNTABLE.TESTRUN_LASTRUNDATE.eq(t.getTestRunLastRunDate()),
					TESTRUNTABLE.TESTRUN_LASTRUNRESULT.eq(t.getTestRunLastRunResult()),
					TESTRUNTABLE.TESTRUN_STATUS.eq(t.getTestRunStatus())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<TestRun> queryPager(int start,int limit ,TestRun testRun ,final OrderBy... orderBies) {
		if(testRun==null){
			testRun=new TestRun();
		}
		return getDslTemplate().queryPager(start, limit, testRun, false, new SelectGenerateCallback<TestRun>() {

			public Select generate(TestRun t) {
				Select select = MysqlSelect.selectFrom(TESTRUNTABLE).where(
				and(
					TESTRUNTABLE.TASK_ID.eq(t.getTaskId()),
					TESTRUNTABLE.CASE_ID.eq(t.getCaseId()),
					TESTRUNTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					TESTRUNTABLE.TESTRUN_ASSIGNEDTO.eq(t.getTestRunAssignedTo()),
					TESTRUNTABLE.TESTRUN_LASTRUNNER.eq(t.getTestRunLastRunner()),
					TESTRUNTABLE.TESTRUN_LASTRUNDATE.eq(t.getTestRunLastRunDate()),
					TESTRUNTABLE.TESTRUN_LASTRUNRESULT.eq(t.getTestRunLastRunResult()),
					TESTRUNTABLE.TESTRUN_STATUS.eq(t.getTestRunStatus())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<TestRun> testRuns) {
		if (CollectionUtil.isEmpty(testRuns)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, testRuns, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(TESTRUNTABLE).values(
					TESTRUNTABLE.TASK_ID.value(new JdbcNamedParameter("taskId")),
					TESTRUNTABLE.CASE_ID.value(new JdbcNamedParameter("caseId")),
					TESTRUNTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					TESTRUNTABLE.TESTRUN_ASSIGNEDTO.value(new JdbcNamedParameter("testRunAssignedTo")),
					TESTRUNTABLE.TESTRUN_LASTRUNNER.value(new JdbcNamedParameter("testRunLastRunner")),
					TESTRUNTABLE.TESTRUN_LASTRUNDATE.value(new JdbcNamedParameter("testRunLastRunDate")),
					TESTRUNTABLE.TESTRUN_LASTRUNRESULT.value(new JdbcNamedParameter("testRunLastRunResult")),
					TESTRUNTABLE.TESTRUN_STATUS.value(new JdbcNamedParameter("testRunStatus")));
			}
		});
	}

	public int[] batchInsert(List<TestRun> testRuns){
			return batchInsert(true ,testRuns);
	}

	public int[] batchUpdate(List<TestRun> testRuns) {
		if (CollectionUtil.isEmpty(testRuns)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(testRuns, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(TESTRUNTABLE).set(
					TESTRUNTABLE.TASK_ID.value(new JdbcNamedParameter("taskId")),
					TESTRUNTABLE.CASE_ID.value(new JdbcNamedParameter("caseId")),
					TESTRUNTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					TESTRUNTABLE.TESTRUN_ASSIGNEDTO.value(new JdbcNamedParameter("testRunAssignedTo")),
					TESTRUNTABLE.TESTRUN_LASTRUNNER.value(new JdbcNamedParameter("testRunLastRunner")),
					TESTRUNTABLE.TESTRUN_LASTRUNDATE.value(new JdbcNamedParameter("testRunLastRunDate")),
					TESTRUNTABLE.TESTRUN_LASTRUNRESULT.value(new JdbcNamedParameter("testRunLastRunResult")),
					TESTRUNTABLE.TESTRUN_STATUS.value(new JdbcNamedParameter("testRunStatus"))).where(
				TESTRUNTABLE.TESTRUN_ID.eq(new JdbcNamedParameter("testRunId")));
			}
		});
	}

	public int[] batchDelete(List<TestRun> testRuns) {
		if (CollectionUtil.isEmpty(testRuns)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(testRuns, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(TESTRUNTABLE).where(and(
				TESTRUNTABLE.TESTRUN_ID.eq(new JdbcNamedParameter("testRunId")),
				TESTRUNTABLE.TASK_ID.eq(new JdbcNamedParameter("taskId")),
				TESTRUNTABLE.CASE_ID.eq(new JdbcNamedParameter("caseId")),
				TESTRUNTABLE.CASE_VERSION.eq(new JdbcNamedParameter("caseVersion")),
				TESTRUNTABLE.TESTRUN_ASSIGNEDTO.eq(new JdbcNamedParameter("testRunAssignedTo")),
				TESTRUNTABLE.TESTRUN_LASTRUNNER.eq(new JdbcNamedParameter("testRunLastRunner")),
				TESTRUNTABLE.TESTRUN_LASTRUNDATE.eq(new JdbcNamedParameter("testRunLastRunDate")),
				TESTRUNTABLE.TESTRUN_LASTRUNRESULT.eq(new JdbcNamedParameter("testRunLastRunResult")),
				TESTRUNTABLE.TESTRUN_STATUS.eq(new JdbcNamedParameter("testRunStatus"))));
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
