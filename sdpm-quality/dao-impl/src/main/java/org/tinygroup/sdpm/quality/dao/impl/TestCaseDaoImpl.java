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
import static org.tinygroup.sdpm.quality.dao.constant.TestCaseTable.*;
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
import org.tinygroup.sdpm.quality.dao.pojo.TestCase;
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.sdpm.quality.dao.TestCaseDao;
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
@LogClass("testCase")
public class TestCaseDaoImpl extends TinyDslDaoSupport implements TestCaseDao {
	@LogMethod("add")
	public TestCase add(TestCase testCase) {
		return getDslTemplate().insertAndReturnKey(testCase, new InsertGenerateCallback<TestCase>() {
			public Insert generate(TestCase t) {
				Insert insert = insertInto(TESTCASETABLE).values(
					TESTCASETABLE.CASE_ID.value(t.getCaseId()),
					TESTCASETABLE.PRODUCT_ID.value(t.getProductId()),
					TESTCASETABLE.MODULE_ID.value(t.getModuleId()),
					TESTCASETABLE.CASE_PATH.value(t.getCasePath()),
					TESTCASETABLE.STORY_ID.value(t.getStoryId()),
					TESTCASETABLE.STORY_VERSION.value(t.getStoryVersion()),
					TESTCASETABLE.CASE_TITLE.value(t.getCaseTitle()),
					TESTCASETABLE.CASE_PRECONDITION.value(t.getCasePrecondition()),
					TESTCASETABLE.CASE_KEYWORDS.value(t.getCaseKeywords()),
					TESTCASETABLE.PRIORITY.value(t.getPriority()),
					TESTCASETABLE.CASE_TYPE.value(t.getCaseType()),
					TESTCASETABLE.CASE_STAGE.value(t.getCaseStage()),
					TESTCASETABLE.CASE_RUNWAY.value(t.getCaseRunway()),
					TESTCASETABLE.CASE_SCRIPTEDBY.value(t.getCaseScriptedBy()),
					TESTCASETABLE.CASE_SCRIPTEDDATE.value(t.getCaseScriptedDate()),
					TESTCASETABLE.SCRIPTSTATUS.value(t.getScriptStatus()),
					TESTCASETABLE.SCRIPTLOCATION.value(t.getScriptLocation()),
					TESTCASETABLE.CASE_STATUS.value(t.getCaseStatus()),
					TESTCASETABLE.CASE_FREQUENCY.value(t.getCaseFrequency()),
					TESTCASETABLE.CASE_ORDER.value(t.getCaseOrder()),
					TESTCASETABLE.CASE_OPENEDBY.value(t.getCaseOpenedBy()),
					TESTCASETABLE.CASE_OPENEDDATE.value(t.getCaseOpenedDate()),
					TESTCASETABLE.CASE_LASTEDITEDBY.value(t.getCaseLastEditedBy()),
					TESTCASETABLE.CASE_LASTEDITEDDATE.value(t.getCaseLastEditedDate()),
					TESTCASETABLE.CASE_VERSION.value(t.getCaseVersion()),
					TESTCASETABLE.LINK_CASE.value(t.getLinkCase()),
					TESTCASETABLE.CASE_FROMBUG.value(t.getCaseFromBug()),
					TESTCASETABLE.DELETED.value(t.getDeleted()),
					TESTCASETABLE.CASE_LASTRUNNER.value(t.getCaseLastRunner()),
					TESTCASETABLE.CASE_LASTRUNDATE.value(t.getCaseLastRunDate()),
					TESTCASETABLE.CASE_LASTRUNRESULT.value(t.getCaseLastRunResult()));
				return insert;
			}
		});
	}
	@LogMethod("edit")
	public int edit(TestCase testCase) {
		if(testCase == null || testCase.getCaseId() == null){
			return 0;
		}
		return getDslTemplate().update(testCase, new UpdateGenerateCallback<TestCase>() {
			public Update generate(TestCase t) {
				Update update = update(TESTCASETABLE).set(
					TESTCASETABLE.PRODUCT_ID.value(t.getProductId()),
					TESTCASETABLE.MODULE_ID.value(t.getModuleId()),
					TESTCASETABLE.CASE_PATH.value(t.getCasePath()),
					TESTCASETABLE.STORY_ID.value(t.getStoryId()),
					TESTCASETABLE.STORY_VERSION.value(t.getStoryVersion()),
					TESTCASETABLE.CASE_TITLE.value(t.getCaseTitle()),
					TESTCASETABLE.CASE_PRECONDITION.value(t.getCasePrecondition()),
					TESTCASETABLE.CASE_KEYWORDS.value(t.getCaseKeywords()),
					TESTCASETABLE.PRIORITY.value(t.getPriority()),
					TESTCASETABLE.CASE_TYPE.value(t.getCaseType()),
					TESTCASETABLE.CASE_STAGE.value(t.getCaseStage()),
					TESTCASETABLE.CASE_RUNWAY.value(t.getCaseRunway()),
					TESTCASETABLE.CASE_SCRIPTEDBY.value(t.getCaseScriptedBy()),
					TESTCASETABLE.CASE_SCRIPTEDDATE.value(t.getCaseScriptedDate()),
					TESTCASETABLE.SCRIPTSTATUS.value(t.getScriptStatus()),
					TESTCASETABLE.SCRIPTLOCATION.value(t.getScriptLocation()),
					TESTCASETABLE.CASE_STATUS.value(t.getCaseStatus()),
					TESTCASETABLE.CASE_FREQUENCY.value(t.getCaseFrequency()),
					TESTCASETABLE.CASE_ORDER.value(t.getCaseOrder()),
					TESTCASETABLE.CASE_OPENEDBY.value(t.getCaseOpenedBy()),
					TESTCASETABLE.CASE_OPENEDDATE.value(t.getCaseOpenedDate()),
					TESTCASETABLE.CASE_LASTEDITEDBY.value(t.getCaseLastEditedBy()),
					TESTCASETABLE.CASE_LASTEDITEDDATE.value(t.getCaseLastEditedDate()),
					TESTCASETABLE.CASE_VERSION.value(t.getCaseVersion()),
					TESTCASETABLE.LINK_CASE.value(t.getLinkCase()),
					TESTCASETABLE.CASE_FROMBUG.value(t.getCaseFromBug()),
					TESTCASETABLE.DELETED.value(t.getDeleted()),
					TESTCASETABLE.CASE_LASTRUNNER.value(t.getCaseLastRunner()),
					TESTCASETABLE.CASE_LASTRUNDATE.value(t.getCaseLastRunDate()),
					TESTCASETABLE.CASE_LASTRUNRESULT.value(t.getCaseLastRunResult())).where(
					TESTCASETABLE.CASE_ID.eq(t.getCaseId()));
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
				return delete(TESTCASETABLE).where(TESTCASETABLE.CASE_ID.eq(pk));
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
				return delete(TESTCASETABLE).where(TESTCASETABLE.CASE_ID.in(t));
		}
		},pks);
	}
	
	public TestCase getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, TestCase.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(TESTCASETABLE).where(TESTCASETABLE.CASE_ID.eq(t));
			}
		});
	}

	public List<TestCase> query(TestCase testCase ,final OrderBy... orderBies) {
		if(testCase==null){
			testCase=new TestCase();
		}
		return getDslTemplate().query(testCase, new SelectGenerateCallback<TestCase>() {

			@SuppressWarnings("rawtypes")
			public Select generate(TestCase t) {
				Select select = selectFrom(TESTCASETABLE).where(
				and(
					TESTCASETABLE.PRODUCT_ID.eq(t.getProductId()),
					TESTCASETABLE.MODULE_ID.eq(t.getModuleId()),
					TESTCASETABLE.CASE_PATH.eq(t.getCasePath()),
					TESTCASETABLE.STORY_ID.eq(t.getStoryId()),
					TESTCASETABLE.STORY_VERSION.eq(t.getStoryVersion()),
					TESTCASETABLE.CASE_TITLE.eq(t.getCaseTitle()),
					TESTCASETABLE.CASE_PRECONDITION.eq(t.getCasePrecondition()),
					TESTCASETABLE.CASE_KEYWORDS.eq(t.getCaseKeywords()),
					TESTCASETABLE.PRIORITY.eq(t.getPriority()),
					TESTCASETABLE.CASE_TYPE.eq(t.getCaseType()),
					TESTCASETABLE.CASE_STAGE.eq(t.getCaseStage()),
					TESTCASETABLE.CASE_RUNWAY.eq(t.getCaseRunway()),
					TESTCASETABLE.CASE_SCRIPTEDBY.eq(t.getCaseScriptedBy()),
					TESTCASETABLE.CASE_SCRIPTEDDATE.eq(t.getCaseScriptedDate()),
					TESTCASETABLE.SCRIPTSTATUS.eq(t.getScriptStatus()),
					TESTCASETABLE.SCRIPTLOCATION.eq(t.getScriptLocation()),
					TESTCASETABLE.CASE_STATUS.eq(t.getCaseStatus()),
					TESTCASETABLE.CASE_FREQUENCY.eq(t.getCaseFrequency()),
					TESTCASETABLE.CASE_ORDER.eq(t.getCaseOrder()),
					TESTCASETABLE.CASE_OPENEDBY.eq(t.getCaseOpenedBy()),
					TESTCASETABLE.CASE_OPENEDDATE.eq(t.getCaseOpenedDate()),
					TESTCASETABLE.CASE_LASTEDITEDBY.eq(t.getCaseLastEditedBy()),
					TESTCASETABLE.CASE_LASTEDITEDDATE.eq(t.getCaseLastEditedDate()),
					TESTCASETABLE.CASE_VERSION.eq(t.getCaseVersion()),
					TESTCASETABLE.LINK_CASE.eq(t.getLinkCase()),
					TESTCASETABLE.CASE_FROMBUG.eq(t.getCaseFromBug()),
					TESTCASETABLE.DELETED.eq(t.getDeleted()),
					TESTCASETABLE.CASE_LASTRUNNER.eq(t.getCaseLastRunner()),
					TESTCASETABLE.CASE_LASTRUNDATE.eq(t.getCaseLastRunDate()),
					TESTCASETABLE.CASE_LASTRUNRESULT.eq(t.getCaseLastRunResult())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<TestCase> queryPager(int start,int limit ,TestCase testCase ,final OrderBy... orderBies) {
		if(testCase==null){
			testCase=new TestCase();
		}
		return getDslTemplate().queryPager(start, limit, testCase, false, new SelectGenerateCallback<TestCase>() {

			public Select generate(TestCase t) {
				Select select = MysqlSelect.selectFrom(TESTCASETABLE).where(
				and(
					TESTCASETABLE.PRODUCT_ID.eq(t.getProductId()),
					TESTCASETABLE.MODULE_ID.eq(t.getModuleId()),
					TESTCASETABLE.CASE_PATH.eq(t.getCasePath()),
					TESTCASETABLE.STORY_ID.eq(t.getStoryId()),
					TESTCASETABLE.STORY_VERSION.eq(t.getStoryVersion()),
					TESTCASETABLE.CASE_TITLE.eq(t.getCaseTitle()),
					TESTCASETABLE.CASE_PRECONDITION.eq(t.getCasePrecondition()),
					TESTCASETABLE.CASE_KEYWORDS.eq(t.getCaseKeywords()),
					TESTCASETABLE.PRIORITY.eq(t.getPriority()),
					TESTCASETABLE.CASE_TYPE.eq(t.getCaseType()),
					TESTCASETABLE.CASE_STAGE.eq(t.getCaseStage()),
					TESTCASETABLE.CASE_RUNWAY.eq(t.getCaseRunway()),
					TESTCASETABLE.CASE_SCRIPTEDBY.eq(t.getCaseScriptedBy()),
					TESTCASETABLE.CASE_SCRIPTEDDATE.eq(t.getCaseScriptedDate()),
					TESTCASETABLE.SCRIPTSTATUS.eq(t.getScriptStatus()),
					TESTCASETABLE.SCRIPTLOCATION.eq(t.getScriptLocation()),
					TESTCASETABLE.CASE_STATUS.eq(t.getCaseStatus()),
					TESTCASETABLE.CASE_FREQUENCY.eq(t.getCaseFrequency()),
					TESTCASETABLE.CASE_ORDER.eq(t.getCaseOrder()),
					TESTCASETABLE.CASE_OPENEDBY.eq(t.getCaseOpenedBy()),
					TESTCASETABLE.CASE_OPENEDDATE.eq(t.getCaseOpenedDate()),
					TESTCASETABLE.CASE_LASTEDITEDBY.eq(t.getCaseLastEditedBy()),
					TESTCASETABLE.CASE_LASTEDITEDDATE.eq(t.getCaseLastEditedDate()),
					TESTCASETABLE.CASE_VERSION.eq(t.getCaseVersion()),
					TESTCASETABLE.LINK_CASE.eq(t.getLinkCase()),
					TESTCASETABLE.CASE_FROMBUG.eq(t.getCaseFromBug()),
					TESTCASETABLE.DELETED.eq(t.getDeleted()),
					TESTCASETABLE.CASE_LASTRUNNER.eq(t.getCaseLastRunner()),
					TESTCASETABLE.CASE_LASTRUNDATE.eq(t.getCaseLastRunDate()),
					TESTCASETABLE.CASE_LASTRUNRESULT.eq(t.getCaseLastRunResult())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<TestCase> testCases) {
		if (CollectionUtil.isEmpty(testCases)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, testCases, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(TESTCASETABLE).values(
					TESTCASETABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					TESTCASETABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
					TESTCASETABLE.CASE_PATH.value(new JdbcNamedParameter("casePath")),
					TESTCASETABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
					TESTCASETABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
					TESTCASETABLE.CASE_TITLE.value(new JdbcNamedParameter("caseTitle")),
					TESTCASETABLE.CASE_PRECONDITION.value(new JdbcNamedParameter("casePrecondition")),
					TESTCASETABLE.CASE_KEYWORDS.value(new JdbcNamedParameter("caseKeywords")),
					TESTCASETABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
					TESTCASETABLE.CASE_TYPE.value(new JdbcNamedParameter("caseType")),
					TESTCASETABLE.CASE_STAGE.value(new JdbcNamedParameter("caseStage")),
					TESTCASETABLE.CASE_RUNWAY.value(new JdbcNamedParameter("caseRunway")),
					TESTCASETABLE.CASE_SCRIPTEDBY.value(new JdbcNamedParameter("caseScriptedBy")),
					TESTCASETABLE.CASE_SCRIPTEDDATE.value(new JdbcNamedParameter("caseScriptedDate")),
					TESTCASETABLE.SCRIPTSTATUS.value(new JdbcNamedParameter("scriptStatus")),
					TESTCASETABLE.SCRIPTLOCATION.value(new JdbcNamedParameter("scriptLocation")),
					TESTCASETABLE.CASE_STATUS.value(new JdbcNamedParameter("caseStatus")),
					TESTCASETABLE.CASE_FREQUENCY.value(new JdbcNamedParameter("caseFrequency")),
					TESTCASETABLE.CASE_ORDER.value(new JdbcNamedParameter("caseOrder")),
					TESTCASETABLE.CASE_OPENEDBY.value(new JdbcNamedParameter("caseOpenedBy")),
					TESTCASETABLE.CASE_OPENEDDATE.value(new JdbcNamedParameter("caseOpenedDate")),
					TESTCASETABLE.CASE_LASTEDITEDBY.value(new JdbcNamedParameter("caseLastEditedBy")),
					TESTCASETABLE.CASE_LASTEDITEDDATE.value(new JdbcNamedParameter("caseLastEditedDate")),
					TESTCASETABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					TESTCASETABLE.LINK_CASE.value(new JdbcNamedParameter("linkCase")),
					TESTCASETABLE.CASE_FROMBUG.value(new JdbcNamedParameter("caseFromBug")),
					TESTCASETABLE.DELETED.value(new JdbcNamedParameter("deleted")),
					TESTCASETABLE.CASE_LASTRUNNER.value(new JdbcNamedParameter("caseLastRunner")),
					TESTCASETABLE.CASE_LASTRUNDATE.value(new JdbcNamedParameter("caseLastRunDate")),
					TESTCASETABLE.CASE_LASTRUNRESULT.value(new JdbcNamedParameter("caseLastRunResult")));
			}
		});
	}

	public int[] batchInsert(List<TestCase> testCases){
			return batchInsert(true ,testCases);
	}
	@LogMethod("batchUpdate")
	public int[] batchUpdate(List<TestCase> testCases) {
		if (CollectionUtil.isEmpty(testCases)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(testCases, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(TESTCASETABLE).set(
					TESTCASETABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					TESTCASETABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
					TESTCASETABLE.CASE_PATH.value(new JdbcNamedParameter("casePath")),
					TESTCASETABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
					TESTCASETABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
					TESTCASETABLE.CASE_TITLE.value(new JdbcNamedParameter("caseTitle")),
					TESTCASETABLE.CASE_PRECONDITION.value(new JdbcNamedParameter("casePrecondition")),
					TESTCASETABLE.CASE_KEYWORDS.value(new JdbcNamedParameter("caseKeywords")),
					TESTCASETABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
					TESTCASETABLE.CASE_TYPE.value(new JdbcNamedParameter("caseType")),
					TESTCASETABLE.CASE_STAGE.value(new JdbcNamedParameter("caseStage")),
					TESTCASETABLE.CASE_RUNWAY.value(new JdbcNamedParameter("caseRunway")),
					TESTCASETABLE.CASE_SCRIPTEDBY.value(new JdbcNamedParameter("caseScriptedBy")),
					TESTCASETABLE.CASE_SCRIPTEDDATE.value(new JdbcNamedParameter("caseScriptedDate")),
					TESTCASETABLE.SCRIPTSTATUS.value(new JdbcNamedParameter("scriptStatus")),
					TESTCASETABLE.SCRIPTLOCATION.value(new JdbcNamedParameter("scriptLocation")),
					TESTCASETABLE.CASE_STATUS.value(new JdbcNamedParameter("caseStatus")),
					TESTCASETABLE.CASE_FREQUENCY.value(new JdbcNamedParameter("caseFrequency")),
					TESTCASETABLE.CASE_ORDER.value(new JdbcNamedParameter("caseOrder")),
					TESTCASETABLE.CASE_OPENEDBY.value(new JdbcNamedParameter("caseOpenedBy")),
					TESTCASETABLE.CASE_OPENEDDATE.value(new JdbcNamedParameter("caseOpenedDate")),
					TESTCASETABLE.CASE_LASTEDITEDBY.value(new JdbcNamedParameter("caseLastEditedBy")),
					TESTCASETABLE.CASE_LASTEDITEDDATE.value(new JdbcNamedParameter("caseLastEditedDate")),
					TESTCASETABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					TESTCASETABLE.LINK_CASE.value(new JdbcNamedParameter("linkCase")),
					TESTCASETABLE.CASE_FROMBUG.value(new JdbcNamedParameter("caseFromBug")),
					TESTCASETABLE.DELETED.value(new JdbcNamedParameter("deleted")),
					TESTCASETABLE.CASE_LASTRUNNER.value(new JdbcNamedParameter("caseLastRunner")),
					TESTCASETABLE.CASE_LASTRUNDATE.value(new JdbcNamedParameter("caseLastRunDate")),
					TESTCASETABLE.CASE_LASTRUNRESULT.value(new JdbcNamedParameter("caseLastRunResult"))).where(
				TESTCASETABLE.CASE_ID.eq(new JdbcNamedParameter("caseId")));
			}
		});
	}
	@LogMethod("batchDelete")
	public int[] batchDelete(List<TestCase> testCases) {
		if (CollectionUtil.isEmpty(testCases)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(testCases, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(TESTCASETABLE).where(and(
				TESTCASETABLE.CASE_ID.eq(new JdbcNamedParameter("caseId")),
				TESTCASETABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
				TESTCASETABLE.MODULE_ID.eq(new JdbcNamedParameter("moduleId")),
				TESTCASETABLE.CASE_PATH.eq(new JdbcNamedParameter("casePath")),
				TESTCASETABLE.STORY_ID.eq(new JdbcNamedParameter("storyId")),
				TESTCASETABLE.STORY_VERSION.eq(new JdbcNamedParameter("storyVersion")),
				TESTCASETABLE.CASE_TITLE.eq(new JdbcNamedParameter("caseTitle")),
				TESTCASETABLE.CASE_PRECONDITION.eq(new JdbcNamedParameter("casePrecondition")),
				TESTCASETABLE.CASE_KEYWORDS.eq(new JdbcNamedParameter("caseKeywords")),
				TESTCASETABLE.PRIORITY.eq(new JdbcNamedParameter("priority")),
				TESTCASETABLE.CASE_TYPE.eq(new JdbcNamedParameter("caseType")),
				TESTCASETABLE.CASE_STAGE.eq(new JdbcNamedParameter("caseStage")),
				TESTCASETABLE.CASE_RUNWAY.eq(new JdbcNamedParameter("caseRunway")),
				TESTCASETABLE.CASE_SCRIPTEDBY.eq(new JdbcNamedParameter("caseScriptedBy")),
				TESTCASETABLE.CASE_SCRIPTEDDATE.eq(new JdbcNamedParameter("caseScriptedDate")),
				TESTCASETABLE.SCRIPTSTATUS.eq(new JdbcNamedParameter("scriptStatus")),
				TESTCASETABLE.SCRIPTLOCATION.eq(new JdbcNamedParameter("scriptLocation")),
				TESTCASETABLE.CASE_STATUS.eq(new JdbcNamedParameter("caseStatus")),
				TESTCASETABLE.CASE_FREQUENCY.eq(new JdbcNamedParameter("caseFrequency")),
				TESTCASETABLE.CASE_ORDER.eq(new JdbcNamedParameter("caseOrder")),
				TESTCASETABLE.CASE_OPENEDBY.eq(new JdbcNamedParameter("caseOpenedBy")),
				TESTCASETABLE.CASE_OPENEDDATE.eq(new JdbcNamedParameter("caseOpenedDate")),
				TESTCASETABLE.CASE_LASTEDITEDBY.eq(new JdbcNamedParameter("caseLastEditedBy")),
				TESTCASETABLE.CASE_LASTEDITEDDATE.eq(new JdbcNamedParameter("caseLastEditedDate")),
				TESTCASETABLE.CASE_VERSION.eq(new JdbcNamedParameter("caseVersion")),
				TESTCASETABLE.LINK_CASE.eq(new JdbcNamedParameter("linkCase")),
				TESTCASETABLE.CASE_FROMBUG.eq(new JdbcNamedParameter("caseFromBug")),
				TESTCASETABLE.DELETED.eq(new JdbcNamedParameter("deleted")),
				TESTCASETABLE.CASE_LASTRUNNER.eq(new JdbcNamedParameter("caseLastRunner")),
				TESTCASETABLE.CASE_LASTRUNDATE.eq(new JdbcNamedParameter("caseLastRunDate")),
				TESTCASETABLE.CASE_LASTRUNRESULT.eq(new JdbcNamedParameter("caseLastRunResult"))));
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
