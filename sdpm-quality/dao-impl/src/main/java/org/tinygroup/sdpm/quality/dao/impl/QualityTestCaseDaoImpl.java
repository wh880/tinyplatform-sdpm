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
import static org.tinygroup.sdpm.quality.dao.constant.QualityTestCaseTable.*;
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
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.sdpm.quality.dao.QualityTestCaseDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

@LogClass("qualityTestCase")
@Repository
public class QualityTestCaseDaoImpl extends TinyDslDaoSupport implements QualityTestCaseDao {
	
	@LogMethod("add")
	public QualityTestCase add(QualityTestCase qualityTestCase) {
		return getDslTemplate().insertAndReturnKey(qualityTestCase, new InsertGenerateCallback<QualityTestCase>() {
			public Insert generate(QualityTestCase t) {
				Insert insert = insertInto(QUALITY_TESTCASETABLE).values(
					QUALITY_TESTCASETABLE.CASE_ID.value(t.getCaseId()),
					QUALITY_TESTCASETABLE.PRODUCT_ID.value(t.getProductId()),
					QUALITY_TESTCASETABLE.MODULE_ID.value(t.getModuleId()),
					QUALITY_TESTCASETABLE.CASE_PATH.value(t.getCasePath()),
					QUALITY_TESTCASETABLE.STORY_ID.value(t.getStoryId()),
					QUALITY_TESTCASETABLE.STORY_VERSION.value(t.getStoryVersion()),
					QUALITY_TESTCASETABLE.CASE_TITLE.value(t.getCaseTitle()),
					QUALITY_TESTCASETABLE.CASE_PRECONDITION.value(t.getCasePrecondition()),
					QUALITY_TESTCASETABLE.CASE_KEYWORDS.value(t.getCaseKeywords()),
					QUALITY_TESTCASETABLE.PRIORITY.value(t.getPriority()),
					QUALITY_TESTCASETABLE.CASE_TYPE.value(t.getCaseType()),
					QUALITY_TESTCASETABLE.CASE_STAGE.value(t.getCaseStage()),
					QUALITY_TESTCASETABLE.CASE_RUNWAY.value(t.getCaseRunway()),
					QUALITY_TESTCASETABLE.CASE_SCRIPTEDBY.value(t.getCaseScriptedBy()),
					QUALITY_TESTCASETABLE.CASE_SCRIPTEDDATE.value(t.getCaseScriptedDate()),
					QUALITY_TESTCASETABLE.SCRIPTSTATUS.value(t.getScriptStatus()),
					QUALITY_TESTCASETABLE.SCRIPTLOCATION.value(t.getScriptLocation()),
					QUALITY_TESTCASETABLE.CASE_STATUS.value(t.getCaseStatus()),
					QUALITY_TESTCASETABLE.CASE_FREQUENCY.value(t.getCaseFrequency()),
					QUALITY_TESTCASETABLE.CASE_ORDER.value(t.getCaseOrder()),
					QUALITY_TESTCASETABLE.CASE_OPENEDBY.value(t.getCaseOpenedBy()),
					QUALITY_TESTCASETABLE.CASE_OPENEDDATE.value(t.getCaseOpenedDate()),
					QUALITY_TESTCASETABLE.CASE_LASTEDITEDBY.value(t.getCaseLastEditedBy()),
					QUALITY_TESTCASETABLE.CASE_LASTEDITEDDATE.value(t.getCaseLastEditedDate()),
					QUALITY_TESTCASETABLE.CASE_VERSION.value(t.getCaseVersion()),
					QUALITY_TESTCASETABLE.LINK_CASE.value(t.getLinkCase()),
					QUALITY_TESTCASETABLE.CASE_FROMBUG.value(t.getCaseFromBug()),
					QUALITY_TESTCASETABLE.DELETED.value(t.getDeleted()),
					QUALITY_TESTCASETABLE.CASE_LASTRUNNER.value(t.getCaseLastRunner()),
					QUALITY_TESTCASETABLE.CASE_LASTRUNDATE.value(t.getCaseLastRunDate()),
					QUALITY_TESTCASETABLE.CASE_LASTRUNRESULT.value(t.getCaseLastRunResult()));
				return insert;
			}
		});
	}
	@LogMethod("edit")
	public int edit(QualityTestCase qualityTestCase) {
		if(qualityTestCase == null || qualityTestCase.getCaseId() == null){
			return 0;
		}
		return getDslTemplate().update(qualityTestCase, new UpdateGenerateCallback<QualityTestCase>() {
			public Update generate(QualityTestCase t) {
				Update update = update(QUALITY_TESTCASETABLE).set(
					QUALITY_TESTCASETABLE.PRODUCT_ID.value(t.getProductId()),
					QUALITY_TESTCASETABLE.MODULE_ID.value(t.getModuleId()),
					QUALITY_TESTCASETABLE.CASE_PATH.value(t.getCasePath()),
					QUALITY_TESTCASETABLE.STORY_ID.value(t.getStoryId()),
					QUALITY_TESTCASETABLE.STORY_VERSION.value(t.getStoryVersion()),
					QUALITY_TESTCASETABLE.CASE_TITLE.value(t.getCaseTitle()),
					QUALITY_TESTCASETABLE.CASE_PRECONDITION.value(t.getCasePrecondition()),
					QUALITY_TESTCASETABLE.CASE_KEYWORDS.value(t.getCaseKeywords()),
					QUALITY_TESTCASETABLE.PRIORITY.value(t.getPriority()),
					QUALITY_TESTCASETABLE.CASE_TYPE.value(t.getCaseType()),
					QUALITY_TESTCASETABLE.CASE_STAGE.value(t.getCaseStage()),
					QUALITY_TESTCASETABLE.CASE_RUNWAY.value(t.getCaseRunway()),
					QUALITY_TESTCASETABLE.CASE_SCRIPTEDBY.value(t.getCaseScriptedBy()),
					QUALITY_TESTCASETABLE.CASE_SCRIPTEDDATE.value(t.getCaseScriptedDate()),
					QUALITY_TESTCASETABLE.SCRIPTSTATUS.value(t.getScriptStatus()),
					QUALITY_TESTCASETABLE.SCRIPTLOCATION.value(t.getScriptLocation()),
					QUALITY_TESTCASETABLE.CASE_STATUS.value(t.getCaseStatus()),
					QUALITY_TESTCASETABLE.CASE_FREQUENCY.value(t.getCaseFrequency()),
					QUALITY_TESTCASETABLE.CASE_ORDER.value(t.getCaseOrder()),
					QUALITY_TESTCASETABLE.CASE_OPENEDBY.value(t.getCaseOpenedBy()),
					QUALITY_TESTCASETABLE.CASE_OPENEDDATE.value(t.getCaseOpenedDate()),
					QUALITY_TESTCASETABLE.CASE_LASTEDITEDBY.value(t.getCaseLastEditedBy()),
					QUALITY_TESTCASETABLE.CASE_LASTEDITEDDATE.value(t.getCaseLastEditedDate()),
					QUALITY_TESTCASETABLE.CASE_VERSION.value(t.getCaseVersion()),
					QUALITY_TESTCASETABLE.LINK_CASE.value(t.getLinkCase()),
					QUALITY_TESTCASETABLE.CASE_FROMBUG.value(t.getCaseFromBug()),
					QUALITY_TESTCASETABLE.DELETED.value(t.getDeleted()),
					QUALITY_TESTCASETABLE.CASE_LASTRUNNER.value(t.getCaseLastRunner()),
					QUALITY_TESTCASETABLE.CASE_LASTRUNDATE.value(t.getCaseLastRunDate()),
					QUALITY_TESTCASETABLE.CASE_LASTRUNRESULT.value(t.getCaseLastRunResult())).where(
					QUALITY_TESTCASETABLE.CASE_ID.eq(t.getCaseId()));
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
				return delete(QUALITY_TESTCASETABLE).where(QUALITY_TESTCASETABLE.CASE_ID.eq(pk));
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
				return delete(QUALITY_TESTCASETABLE).where(QUALITY_TESTCASETABLE.CASE_ID.in(t));
		}
		},pks);
	}

	public QualityTestCase getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, QualityTestCase.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(QUALITY_TESTCASETABLE).where(QUALITY_TESTCASETABLE.CASE_ID.eq(t));
			}
		});
	}

	public List<QualityTestCase> query(QualityTestCase qualityTestCase ,final OrderBy... orderBies) {
		if(qualityTestCase==null){
			qualityTestCase=new QualityTestCase();
		}
		return getDslTemplate().query(qualityTestCase, new SelectGenerateCallback<QualityTestCase>() {

			@SuppressWarnings("rawtypes")
			public Select generate(QualityTestCase t) {
				Select select = selectFrom(QUALITY_TESTCASETABLE).where(
				and(
					QUALITY_TESTCASETABLE.PRODUCT_ID.eq(t.getProductId()),
					QUALITY_TESTCASETABLE.MODULE_ID.eq(t.getModuleId()),
					QUALITY_TESTCASETABLE.CASE_PATH.eq(t.getCasePath()),
					QUALITY_TESTCASETABLE.STORY_ID.eq(t.getStoryId()),
					QUALITY_TESTCASETABLE.STORY_VERSION.eq(t.getStoryVersion()),
					QUALITY_TESTCASETABLE.CASE_TITLE.eq(t.getCaseTitle()),
					QUALITY_TESTCASETABLE.CASE_PRECONDITION.eq(t.getCasePrecondition()),
					QUALITY_TESTCASETABLE.CASE_KEYWORDS.eq(t.getCaseKeywords()),
					QUALITY_TESTCASETABLE.PRIORITY.eq(t.getPriority()),
					QUALITY_TESTCASETABLE.CASE_TYPE.eq(t.getCaseType()),
					QUALITY_TESTCASETABLE.CASE_STAGE.eq(t.getCaseStage()),
					QUALITY_TESTCASETABLE.CASE_RUNWAY.eq(t.getCaseRunway()),
					QUALITY_TESTCASETABLE.CASE_SCRIPTEDBY.eq(t.getCaseScriptedBy()),
					QUALITY_TESTCASETABLE.CASE_SCRIPTEDDATE.eq(t.getCaseScriptedDate()),
					QUALITY_TESTCASETABLE.SCRIPTSTATUS.eq(t.getScriptStatus()),
					QUALITY_TESTCASETABLE.SCRIPTLOCATION.eq(t.getScriptLocation()),
					QUALITY_TESTCASETABLE.CASE_STATUS.eq(t.getCaseStatus()),
					QUALITY_TESTCASETABLE.CASE_FREQUENCY.eq(t.getCaseFrequency()),
					QUALITY_TESTCASETABLE.CASE_ORDER.eq(t.getCaseOrder()),
					QUALITY_TESTCASETABLE.CASE_OPENEDBY.eq(t.getCaseOpenedBy()),
					QUALITY_TESTCASETABLE.CASE_OPENEDDATE.eq(t.getCaseOpenedDate()),
					QUALITY_TESTCASETABLE.CASE_LASTEDITEDBY.eq(t.getCaseLastEditedBy()),
					QUALITY_TESTCASETABLE.CASE_LASTEDITEDDATE.eq(t.getCaseLastEditedDate()),
					QUALITY_TESTCASETABLE.CASE_VERSION.eq(t.getCaseVersion()),
					QUALITY_TESTCASETABLE.LINK_CASE.eq(t.getLinkCase()),
					QUALITY_TESTCASETABLE.CASE_FROMBUG.eq(t.getCaseFromBug()),
					QUALITY_TESTCASETABLE.DELETED.eq(t.getDeleted()),
					QUALITY_TESTCASETABLE.CASE_LASTRUNNER.eq(t.getCaseLastRunner()),
					QUALITY_TESTCASETABLE.CASE_LASTRUNDATE.eq(t.getCaseLastRunDate()),
					QUALITY_TESTCASETABLE.CASE_LASTRUNRESULT.eq(t.getCaseLastRunResult())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<QualityTestCase> queryPager(int start,int limit ,QualityTestCase qualityTestCase ,final OrderBy... orderBies) {
		if(qualityTestCase==null){
			qualityTestCase=new QualityTestCase();
		}
		return getDslTemplate().queryPager(start, limit, qualityTestCase, false, new SelectGenerateCallback<QualityTestCase>() {

			public Select generate(QualityTestCase t) {
				Select select = MysqlSelect.selectFrom(QUALITY_TESTCASETABLE).where(
				and(
					QUALITY_TESTCASETABLE.PRODUCT_ID.eq(t.getProductId()),
					QUALITY_TESTCASETABLE.MODULE_ID.eq(t.getModuleId()),
					QUALITY_TESTCASETABLE.CASE_PATH.eq(t.getCasePath()),
					QUALITY_TESTCASETABLE.STORY_ID.eq(t.getStoryId()),
					QUALITY_TESTCASETABLE.STORY_VERSION.eq(t.getStoryVersion()),
					QUALITY_TESTCASETABLE.CASE_TITLE.eq(t.getCaseTitle()),
					QUALITY_TESTCASETABLE.CASE_PRECONDITION.eq(t.getCasePrecondition()),
					QUALITY_TESTCASETABLE.CASE_KEYWORDS.eq(t.getCaseKeywords()),
					QUALITY_TESTCASETABLE.PRIORITY.eq(t.getPriority()),
					QUALITY_TESTCASETABLE.CASE_TYPE.eq(t.getCaseType()),
					QUALITY_TESTCASETABLE.CASE_STAGE.eq(t.getCaseStage()),
					QUALITY_TESTCASETABLE.CASE_RUNWAY.eq(t.getCaseRunway()),
					QUALITY_TESTCASETABLE.CASE_SCRIPTEDBY.eq(t.getCaseScriptedBy()),
					QUALITY_TESTCASETABLE.CASE_SCRIPTEDDATE.eq(t.getCaseScriptedDate()),
					QUALITY_TESTCASETABLE.SCRIPTSTATUS.eq(t.getScriptStatus()),
					QUALITY_TESTCASETABLE.SCRIPTLOCATION.eq(t.getScriptLocation()),
					QUALITY_TESTCASETABLE.CASE_STATUS.eq(t.getCaseStatus()),
					QUALITY_TESTCASETABLE.CASE_FREQUENCY.eq(t.getCaseFrequency()),
					QUALITY_TESTCASETABLE.CASE_ORDER.eq(t.getCaseOrder()),
					QUALITY_TESTCASETABLE.CASE_OPENEDBY.eq(t.getCaseOpenedBy()),
					QUALITY_TESTCASETABLE.CASE_OPENEDDATE.eq(t.getCaseOpenedDate()),
					QUALITY_TESTCASETABLE.CASE_LASTEDITEDBY.eq(t.getCaseLastEditedBy()),
					QUALITY_TESTCASETABLE.CASE_LASTEDITEDDATE.eq(t.getCaseLastEditedDate()),
					QUALITY_TESTCASETABLE.CASE_VERSION.eq(t.getCaseVersion()),
					QUALITY_TESTCASETABLE.LINK_CASE.eq(t.getLinkCase()),
					QUALITY_TESTCASETABLE.CASE_FROMBUG.eq(t.getCaseFromBug()),
					QUALITY_TESTCASETABLE.DELETED.eq(t.getDeleted()),
					QUALITY_TESTCASETABLE.CASE_LASTRUNNER.eq(t.getCaseLastRunner()),
					QUALITY_TESTCASETABLE.CASE_LASTRUNDATE.eq(t.getCaseLastRunDate()),
					QUALITY_TESTCASETABLE.CASE_LASTRUNRESULT.eq(t.getCaseLastRunResult())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<QualityTestCase> qualityTestCases) {
		if (CollectionUtil.isEmpty(qualityTestCases)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, qualityTestCases, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(QUALITY_TESTCASETABLE).values(
					QUALITY_TESTCASETABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					QUALITY_TESTCASETABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
					QUALITY_TESTCASETABLE.CASE_PATH.value(new JdbcNamedParameter("casePath")),
					QUALITY_TESTCASETABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
					QUALITY_TESTCASETABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
					QUALITY_TESTCASETABLE.CASE_TITLE.value(new JdbcNamedParameter("caseTitle")),
					QUALITY_TESTCASETABLE.CASE_PRECONDITION.value(new JdbcNamedParameter("casePrecondition")),
					QUALITY_TESTCASETABLE.CASE_KEYWORDS.value(new JdbcNamedParameter("caseKeywords")),
					QUALITY_TESTCASETABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
					QUALITY_TESTCASETABLE.CASE_TYPE.value(new JdbcNamedParameter("caseType")),
					QUALITY_TESTCASETABLE.CASE_STAGE.value(new JdbcNamedParameter("caseStage")),
					QUALITY_TESTCASETABLE.CASE_RUNWAY.value(new JdbcNamedParameter("caseRunway")),
					QUALITY_TESTCASETABLE.CASE_SCRIPTEDBY.value(new JdbcNamedParameter("caseScriptedBy")),
					QUALITY_TESTCASETABLE.CASE_SCRIPTEDDATE.value(new JdbcNamedParameter("caseScriptedDate")),
					QUALITY_TESTCASETABLE.SCRIPTSTATUS.value(new JdbcNamedParameter("scriptStatus")),
					QUALITY_TESTCASETABLE.SCRIPTLOCATION.value(new JdbcNamedParameter("scriptLocation")),
					QUALITY_TESTCASETABLE.CASE_STATUS.value(new JdbcNamedParameter("caseStatus")),
					QUALITY_TESTCASETABLE.CASE_FREQUENCY.value(new JdbcNamedParameter("caseFrequency")),
					QUALITY_TESTCASETABLE.CASE_ORDER.value(new JdbcNamedParameter("caseOrder")),
					QUALITY_TESTCASETABLE.CASE_OPENEDBY.value(new JdbcNamedParameter("caseOpenedBy")),
					QUALITY_TESTCASETABLE.CASE_OPENEDDATE.value(new JdbcNamedParameter("caseOpenedDate")),
					QUALITY_TESTCASETABLE.CASE_LASTEDITEDBY.value(new JdbcNamedParameter("caseLastEditedBy")),
					QUALITY_TESTCASETABLE.CASE_LASTEDITEDDATE.value(new JdbcNamedParameter("caseLastEditedDate")),
					QUALITY_TESTCASETABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					QUALITY_TESTCASETABLE.LINK_CASE.value(new JdbcNamedParameter("linkCase")),
					QUALITY_TESTCASETABLE.CASE_FROMBUG.value(new JdbcNamedParameter("caseFromBug")),
					QUALITY_TESTCASETABLE.DELETED.value(new JdbcNamedParameter("deleted")),
					QUALITY_TESTCASETABLE.CASE_LASTRUNNER.value(new JdbcNamedParameter("caseLastRunner")),
					QUALITY_TESTCASETABLE.CASE_LASTRUNDATE.value(new JdbcNamedParameter("caseLastRunDate")),
					QUALITY_TESTCASETABLE.CASE_LASTRUNRESULT.value(new JdbcNamedParameter("caseLastRunResult")));
			}
		});
	}

	public int[] batchInsert(List<QualityTestCase> qualityTestCases){
			return batchInsert(true ,qualityTestCases);
	}
	@LogMethod("batchUpdate")
	public int[] batchUpdate(List<QualityTestCase> qualityTestCases) {
		if (CollectionUtil.isEmpty(qualityTestCases)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(qualityTestCases, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(QUALITY_TESTCASETABLE).set(
					QUALITY_TESTCASETABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					QUALITY_TESTCASETABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
					QUALITY_TESTCASETABLE.CASE_PATH.value(new JdbcNamedParameter("casePath")),
					QUALITY_TESTCASETABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
					QUALITY_TESTCASETABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
					QUALITY_TESTCASETABLE.CASE_TITLE.value(new JdbcNamedParameter("caseTitle")),
					QUALITY_TESTCASETABLE.CASE_PRECONDITION.value(new JdbcNamedParameter("casePrecondition")),
					QUALITY_TESTCASETABLE.CASE_KEYWORDS.value(new JdbcNamedParameter("caseKeywords")),
					QUALITY_TESTCASETABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
					QUALITY_TESTCASETABLE.CASE_TYPE.value(new JdbcNamedParameter("caseType")),
					QUALITY_TESTCASETABLE.CASE_STAGE.value(new JdbcNamedParameter("caseStage")),
					QUALITY_TESTCASETABLE.CASE_RUNWAY.value(new JdbcNamedParameter("caseRunway")),
					QUALITY_TESTCASETABLE.CASE_SCRIPTEDBY.value(new JdbcNamedParameter("caseScriptedBy")),
					QUALITY_TESTCASETABLE.CASE_SCRIPTEDDATE.value(new JdbcNamedParameter("caseScriptedDate")),
					QUALITY_TESTCASETABLE.SCRIPTSTATUS.value(new JdbcNamedParameter("scriptStatus")),
					QUALITY_TESTCASETABLE.SCRIPTLOCATION.value(new JdbcNamedParameter("scriptLocation")),
					QUALITY_TESTCASETABLE.CASE_STATUS.value(new JdbcNamedParameter("caseStatus")),
					QUALITY_TESTCASETABLE.CASE_FREQUENCY.value(new JdbcNamedParameter("caseFrequency")),
					QUALITY_TESTCASETABLE.CASE_ORDER.value(new JdbcNamedParameter("caseOrder")),
					QUALITY_TESTCASETABLE.CASE_OPENEDBY.value(new JdbcNamedParameter("caseOpenedBy")),
					QUALITY_TESTCASETABLE.CASE_OPENEDDATE.value(new JdbcNamedParameter("caseOpenedDate")),
					QUALITY_TESTCASETABLE.CASE_LASTEDITEDBY.value(new JdbcNamedParameter("caseLastEditedBy")),
					QUALITY_TESTCASETABLE.CASE_LASTEDITEDDATE.value(new JdbcNamedParameter("caseLastEditedDate")),
					QUALITY_TESTCASETABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					QUALITY_TESTCASETABLE.LINK_CASE.value(new JdbcNamedParameter("linkCase")),
					QUALITY_TESTCASETABLE.CASE_FROMBUG.value(new JdbcNamedParameter("caseFromBug")),
					QUALITY_TESTCASETABLE.DELETED.value(new JdbcNamedParameter("deleted")),
					QUALITY_TESTCASETABLE.CASE_LASTRUNNER.value(new JdbcNamedParameter("caseLastRunner")),
					QUALITY_TESTCASETABLE.CASE_LASTRUNDATE.value(new JdbcNamedParameter("caseLastRunDate")),
					QUALITY_TESTCASETABLE.CASE_LASTRUNRESULT.value(new JdbcNamedParameter("caseLastRunResult"))).where(
				QUALITY_TESTCASETABLE.CASE_ID.eq(new JdbcNamedParameter("caseId")));
			}
		});
	}
	@LogMethod("batchDelete")
	public int[] batchDelete(List<QualityTestCase> qualityTestCases) {
		if (CollectionUtil.isEmpty(qualityTestCases)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(qualityTestCases, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(QUALITY_TESTCASETABLE).where(and(
				QUALITY_TESTCASETABLE.CASE_ID.eq(new JdbcNamedParameter("caseId")),
				QUALITY_TESTCASETABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
				QUALITY_TESTCASETABLE.MODULE_ID.eq(new JdbcNamedParameter("moduleId")),
				QUALITY_TESTCASETABLE.CASE_PATH.eq(new JdbcNamedParameter("casePath")),
				QUALITY_TESTCASETABLE.STORY_ID.eq(new JdbcNamedParameter("storyId")),
				QUALITY_TESTCASETABLE.STORY_VERSION.eq(new JdbcNamedParameter("storyVersion")),
				QUALITY_TESTCASETABLE.CASE_TITLE.eq(new JdbcNamedParameter("caseTitle")),
				QUALITY_TESTCASETABLE.CASE_PRECONDITION.eq(new JdbcNamedParameter("casePrecondition")),
				QUALITY_TESTCASETABLE.CASE_KEYWORDS.eq(new JdbcNamedParameter("caseKeywords")),
				QUALITY_TESTCASETABLE.PRIORITY.eq(new JdbcNamedParameter("priority")),
				QUALITY_TESTCASETABLE.CASE_TYPE.eq(new JdbcNamedParameter("caseType")),
				QUALITY_TESTCASETABLE.CASE_STAGE.eq(new JdbcNamedParameter("caseStage")),
				QUALITY_TESTCASETABLE.CASE_RUNWAY.eq(new JdbcNamedParameter("caseRunway")),
				QUALITY_TESTCASETABLE.CASE_SCRIPTEDBY.eq(new JdbcNamedParameter("caseScriptedBy")),
				QUALITY_TESTCASETABLE.CASE_SCRIPTEDDATE.eq(new JdbcNamedParameter("caseScriptedDate")),
				QUALITY_TESTCASETABLE.SCRIPTSTATUS.eq(new JdbcNamedParameter("scriptStatus")),
				QUALITY_TESTCASETABLE.SCRIPTLOCATION.eq(new JdbcNamedParameter("scriptLocation")),
				QUALITY_TESTCASETABLE.CASE_STATUS.eq(new JdbcNamedParameter("caseStatus")),
				QUALITY_TESTCASETABLE.CASE_FREQUENCY.eq(new JdbcNamedParameter("caseFrequency")),
				QUALITY_TESTCASETABLE.CASE_ORDER.eq(new JdbcNamedParameter("caseOrder")),
				QUALITY_TESTCASETABLE.CASE_OPENEDBY.eq(new JdbcNamedParameter("caseOpenedBy")),
				QUALITY_TESTCASETABLE.CASE_OPENEDDATE.eq(new JdbcNamedParameter("caseOpenedDate")),
				QUALITY_TESTCASETABLE.CASE_LASTEDITEDBY.eq(new JdbcNamedParameter("caseLastEditedBy")),
				QUALITY_TESTCASETABLE.CASE_LASTEDITEDDATE.eq(new JdbcNamedParameter("caseLastEditedDate")),
				QUALITY_TESTCASETABLE.CASE_VERSION.eq(new JdbcNamedParameter("caseVersion")),
				QUALITY_TESTCASETABLE.LINK_CASE.eq(new JdbcNamedParameter("linkCase")),
				QUALITY_TESTCASETABLE.CASE_FROMBUG.eq(new JdbcNamedParameter("caseFromBug")),
				QUALITY_TESTCASETABLE.DELETED.eq(new JdbcNamedParameter("deleted")),
				QUALITY_TESTCASETABLE.CASE_LASTRUNNER.eq(new JdbcNamedParameter("caseLastRunner")),
				QUALITY_TESTCASETABLE.CASE_LASTRUNDATE.eq(new JdbcNamedParameter("caseLastRunDate")),
				QUALITY_TESTCASETABLE.CASE_LASTRUNRESULT.eq(new JdbcNamedParameter("caseLastRunResult"))));
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
