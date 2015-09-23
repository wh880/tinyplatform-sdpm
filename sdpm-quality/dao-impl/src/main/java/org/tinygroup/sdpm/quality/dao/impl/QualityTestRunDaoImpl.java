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
import static org.tinygroup.sdpm.quality.dao.constant.QualityTestRunTable.*;
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
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun;
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.sdpm.quality.dao.QualityTestRunDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

@LogClass("qualityTestRun")
@Repository
public class QualityTestRunDaoImpl extends TinyDslDaoSupport implements QualityTestRunDao {

	@LogMethod("add")
	public QualityTestRun add(QualityTestRun qualityTestRun) {
		return getDslTemplate().insertAndReturnKey(qualityTestRun, new InsertGenerateCallback<QualityTestRun>() {
			public Insert generate(QualityTestRun t) {
				Insert insert = insertInto(QUALITY_TESTRUNTABLE).values(
					QUALITY_TESTRUNTABLE.TESTRUN_ID.value(t.getTestRunId()),
					QUALITY_TESTRUNTABLE.TASK_ID.value(t.getTaskId()),
					QUALITY_TESTRUNTABLE.CASE_ID.value(t.getCaseId()),
					QUALITY_TESTRUNTABLE.CASE_VERSION.value(t.getCaseVersion()),
					QUALITY_TESTRUNTABLE.TESTRUN_ASSIGNEDTO.value(t.getTestRunAssignedTo()),
					QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNNER.value(t.getTestRunLastRunner()),
					QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNDATE.value(t.getTestRunLastRunDate()),
					QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNRESULT.value(t.getTestRunLastRunResult()),
					QUALITY_TESTRUNTABLE.TESTRUN_STATUS.value(t.getTestRunStatus()));
				return insert;
			}
		});
	}
	@LogMethod("edit")
	public int edit(QualityTestRun qualityTestRun) {
		if(qualityTestRun == null || qualityTestRun.getTestRunId() == null){
			return 0;
		}
		return getDslTemplate().update(qualityTestRun, new UpdateGenerateCallback<QualityTestRun>() {
			public Update generate(QualityTestRun t) {
				Update update = update(QUALITY_TESTRUNTABLE).set(
					QUALITY_TESTRUNTABLE.TASK_ID.value(t.getTaskId()),
					QUALITY_TESTRUNTABLE.CASE_ID.value(t.getCaseId()),
					QUALITY_TESTRUNTABLE.CASE_VERSION.value(t.getCaseVersion()),
					QUALITY_TESTRUNTABLE.TESTRUN_ASSIGNEDTO.value(t.getTestRunAssignedTo()),
					QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNNER.value(t.getTestRunLastRunner()),
					QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNDATE.value(t.getTestRunLastRunDate()),
					QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNRESULT.value(t.getTestRunLastRunResult()),
					QUALITY_TESTRUNTABLE.TESTRUN_STATUS.value(t.getTestRunStatus())).where(
					QUALITY_TESTRUNTABLE.TESTRUN_ID.eq(t.getTestRunId()));
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
				return delete(QUALITY_TESTRUNTABLE).where(QUALITY_TESTRUNTABLE.TESTRUN_ID.eq(pk));
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
				return delete(QUALITY_TESTRUNTABLE).where(QUALITY_TESTRUNTABLE.TESTRUN_ID.in(t));
		}
		},pks);
	}

	public QualityTestRun getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, QualityTestRun.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(QUALITY_TESTRUNTABLE).where(QUALITY_TESTRUNTABLE.TESTRUN_ID.eq(t));
			}
		});
	}

	public List<QualityTestRun> query(QualityTestRun qualityTestRun ,final OrderBy... orderBies) {
		if(qualityTestRun==null){
			qualityTestRun=new QualityTestRun();
		}
		return getDslTemplate().query(qualityTestRun, new SelectGenerateCallback<QualityTestRun>() {

			@SuppressWarnings("rawtypes")
			public Select generate(QualityTestRun t) {
				Select select = selectFrom(QUALITY_TESTRUNTABLE).where(
				and(
					QUALITY_TESTRUNTABLE.TASK_ID.eq(t.getTaskId()),
					QUALITY_TESTRUNTABLE.CASE_ID.eq(t.getCaseId()),
					QUALITY_TESTRUNTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					QUALITY_TESTRUNTABLE.TESTRUN_ASSIGNEDTO.eq(t.getTestRunAssignedTo()),
					QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNNER.eq(t.getTestRunLastRunner()),
					QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNDATE.eq(t.getTestRunLastRunDate()),
					QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNRESULT.eq(t.getTestRunLastRunResult()),
					QUALITY_TESTRUNTABLE.TESTRUN_STATUS.eq(t.getTestRunStatus())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<QualityTestRun> queryPager(int start,int limit ,QualityTestRun qualityTestRun ,final OrderBy... orderBies) {
		if(qualityTestRun==null){
			qualityTestRun=new QualityTestRun();
		}
		return getDslTemplate().queryPager(start, limit, qualityTestRun, false, new SelectGenerateCallback<QualityTestRun>() {

			public Select generate(QualityTestRun t) {
				Select select = MysqlSelect.selectFrom(QUALITY_TESTRUNTABLE).where(
				and(
					QUALITY_TESTRUNTABLE.TASK_ID.eq(t.getTaskId()),
					QUALITY_TESTRUNTABLE.CASE_ID.eq(t.getCaseId()),
					QUALITY_TESTRUNTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					QUALITY_TESTRUNTABLE.TESTRUN_ASSIGNEDTO.eq(t.getTestRunAssignedTo()),
					QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNNER.eq(t.getTestRunLastRunner()),
					QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNDATE.eq(t.getTestRunLastRunDate()),
					QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNRESULT.eq(t.getTestRunLastRunResult()),
					QUALITY_TESTRUNTABLE.TESTRUN_STATUS.eq(t.getTestRunStatus())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<QualityTestRun> qualityTestRuns) {
		if (CollectionUtil.isEmpty(qualityTestRuns)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, qualityTestRuns, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(QUALITY_TESTRUNTABLE).values(
					QUALITY_TESTRUNTABLE.TASK_ID.value(new JdbcNamedParameter("taskId")),
					QUALITY_TESTRUNTABLE.CASE_ID.value(new JdbcNamedParameter("caseId")),
					QUALITY_TESTRUNTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					QUALITY_TESTRUNTABLE.TESTRUN_ASSIGNEDTO.value(new JdbcNamedParameter("testRunAssignedTo")),
					QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNNER.value(new JdbcNamedParameter("testRunLastRunner")),
					QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNDATE.value(new JdbcNamedParameter("testRunLastRunDate")),
					QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNRESULT.value(new JdbcNamedParameter("testRunLastRunResult")),
					QUALITY_TESTRUNTABLE.TESTRUN_STATUS.value(new JdbcNamedParameter("testRunStatus")));
			}
		});
	}

	public int[] batchInsert(List<QualityTestRun> qualityTestRuns){
			return batchInsert(true ,qualityTestRuns);
	}
	@LogMethod("batchUpdate")
	public int[] batchUpdate(List<QualityTestRun> qualityTestRuns) {
		if (CollectionUtil.isEmpty(qualityTestRuns)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(qualityTestRuns, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(QUALITY_TESTRUNTABLE).set(
					QUALITY_TESTRUNTABLE.TASK_ID.value(new JdbcNamedParameter("taskId")),
					QUALITY_TESTRUNTABLE.CASE_ID.value(new JdbcNamedParameter("caseId")),
					QUALITY_TESTRUNTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					QUALITY_TESTRUNTABLE.TESTRUN_ASSIGNEDTO.value(new JdbcNamedParameter("testRunAssignedTo")),
					QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNNER.value(new JdbcNamedParameter("testRunLastRunner")),
					QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNDATE.value(new JdbcNamedParameter("testRunLastRunDate")),
					QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNRESULT.value(new JdbcNamedParameter("testRunLastRunResult")),
					QUALITY_TESTRUNTABLE.TESTRUN_STATUS.value(new JdbcNamedParameter("testRunStatus"))).where(
				QUALITY_TESTRUNTABLE.TESTRUN_ID.eq(new JdbcNamedParameter("testRunId")));
			}
		});
	}
	@LogMethod("batchDelete")
	public int[] batchDelete(List<QualityTestRun> qualityTestRuns) {
		if (CollectionUtil.isEmpty(qualityTestRuns)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(qualityTestRuns, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(QUALITY_TESTRUNTABLE).where(and(
				QUALITY_TESTRUNTABLE.TESTRUN_ID.eq(new JdbcNamedParameter("testRunId")),
				QUALITY_TESTRUNTABLE.TASK_ID.eq(new JdbcNamedParameter("taskId")),
				QUALITY_TESTRUNTABLE.CASE_ID.eq(new JdbcNamedParameter("caseId")),
				QUALITY_TESTRUNTABLE.CASE_VERSION.eq(new JdbcNamedParameter("caseVersion")),
				QUALITY_TESTRUNTABLE.TESTRUN_ASSIGNEDTO.eq(new JdbcNamedParameter("testRunAssignedTo")),
				QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNNER.eq(new JdbcNamedParameter("testRunLastRunner")),
				QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNDATE.eq(new JdbcNamedParameter("testRunLastRunDate")),
				QUALITY_TESTRUNTABLE.TESTRUN_LASTRUNRESULT.eq(new JdbcNamedParameter("testRunLastRunResult")),
				QUALITY_TESTRUNTABLE.TESTRUN_STATUS.eq(new JdbcNamedParameter("testRunStatus"))));
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
