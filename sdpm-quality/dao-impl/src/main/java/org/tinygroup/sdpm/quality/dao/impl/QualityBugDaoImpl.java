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
import static org.tinygroup.sdpm.quality.dao.constant.QualityBugTable.*;
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
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.sdpm.quality.dao.QualityBugDao;
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
@LogClass("qualitybug")
public class QualityBugDaoImpl extends TinyDslDaoSupport implements QualityBugDao {
	@LogMethod("add")
	public QualityBug add(QualityBug qualityBug) {
		return getDslTemplate().insertAndReturnKey(qualityBug, new InsertGenerateCallback<QualityBug>() {
			public Insert generate(QualityBug t) {
				Insert insert = insertInto(QUALITY_BUGTABLE).values(
					QUALITY_BUGTABLE.BUG_ID.value(t.getBugId()),
					QUALITY_BUGTABLE.PRODUCT_ID.value(t.getProductId()),
					QUALITY_BUGTABLE.MODULE_ID.value(t.getModuleId()),
					QUALITY_BUGTABLE.PROJECT_ID.value(t.getProjectId()),
					QUALITY_BUGTABLE.PLAN_ID.value(t.getPlanId()),
					QUALITY_BUGTABLE.STORY_ID.value(t.getStoryId()),
					QUALITY_BUGTABLE.STORY_VERSION.value(t.getStoryVersion()),
					QUALITY_BUGTABLE.TASK_ID.value(t.getTaskId()),
					QUALITY_BUGTABLE.TO_TASK_ID.value(t.getToTaskId()),
					QUALITY_BUGTABLE.TO_STORY_ID.value(t.getToStoryId()),
					QUALITY_BUGTABLE.BUG_TITLE.value(t.getBugTitle()),
					QUALITY_BUGTABLE.BUG_KEYWORDS.value(t.getBugKeywords()),
					QUALITY_BUGTABLE.BUG_SEVERITY.value(t.getBugSeverity()),
					QUALITY_BUGTABLE.PRIORITY.value(t.getPriority()),
					QUALITY_BUGTABLE.BUG_TYPE.value(t.getBugType()),
					QUALITY_BUGTABLE.OPERATING_SYSTEM.value(t.getOperatingSystem()),
					QUALITY_BUGTABLE.BROWSER.value(t.getBrowser()),
					QUALITY_BUGTABLE.HARDWARE.value(t.getHardware()),
					QUALITY_BUGTABLE.BUG_FOUND.value(t.getBugFound()),
					QUALITY_BUGTABLE.BUG_STEPS.value(t.getBugSteps()),
					QUALITY_BUGTABLE.BUG_STATUS.value(t.getBugStatus()),
					QUALITY_BUGTABLE.BUG_CONFIRMED.value(t.getBugConfirmed()),
					QUALITY_BUGTABLE.BUG_ACTIVATED_COUNT.value(t.getBugActivatedCount()),
					QUALITY_BUGTABLE.BUG_MAILTO.value(t.getBugMailto()),
					QUALITY_BUGTABLE.BUG_OPENED_BY.value(t.getBugOpenedBy()),
					QUALITY_BUGTABLE.BUG_OPENED_DATE.value(t.getBugOpenedDate()),
					QUALITY_BUGTABLE.BUG_OPENED_BUILD.value(t.getBugOpenedBuild()),
					QUALITY_BUGTABLE.BUG_ASSIGNED_TO.value(t.getBugAssignedTo()),
					QUALITY_BUGTABLE.BUG_ASSIGNED_DATE.value(t.getBugAssignedDate()),
					QUALITY_BUGTABLE.BUG_RESOLVED_BY.value(t.getBugResolvedBy()),
					QUALITY_BUGTABLE.BUG_RESOLUTION.value(t.getBugResolution()),
					QUALITY_BUGTABLE.BUG_RESOLVED_BUILD.value(t.getBugResolvedBuild()),
					QUALITY_BUGTABLE.BUG_RESOLVED_DATE.value(t.getBugResolvedDate()),
					QUALITY_BUGTABLE.BUG_CLOSED_BY.value(t.getBugClosedBy()),
					QUALITY_BUGTABLE.BUG_CLOSED_DATE.value(t.getBugClosedDate()),
					QUALITY_BUGTABLE.BUG_DUPLICATE_BUG.value(t.getBugDuplicateBug()),
					QUALITY_BUGTABLE.LINK_BUG.value(t.getLinkBug()),
					QUALITY_BUGTABLE.LINK_CASE.value(t.getLinkCase()),
					QUALITY_BUGTABLE.CASE_VERSION.value(t.getCaseVersion()),
					QUALITY_BUGTABLE.BUG_RESULT.value(t.getBugResult()),
					QUALITY_BUGTABLE.BUG_REPO.value(t.getBugRepo()),
					QUALITY_BUGTABLE.BUG_ENTRY.value(t.getBugEntry()),
					QUALITY_BUGTABLE.BUG_LINES.value(t.getBugLines()),
					QUALITY_BUGTABLE.BUG_V1.value(t.getBugV1()),
					QUALITY_BUGTABLE.BUG_V2.value(t.getBugV2()),
					QUALITY_BUGTABLE.BUG_REPO_TYPE.value(t.getBugRepoType()),
					QUALITY_BUGTABLE.TESTTASK.value(t.getTesttask()),
					QUALITY_BUGTABLE.BUG_LAST_EDITED_BY.value(t.getBugLastEditedBy()),
					QUALITY_BUGTABLE.BUG_LAST_EDITED_DATE.value(t.getBugLastEditedDate()),
					QUALITY_BUGTABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}
	@LogMethod("edit")
	public int edit(QualityBug qualityBug) {
		if(qualityBug == null || qualityBug.getBugId() == null){
			return 0;
		}
		return getDslTemplate().update(qualityBug, new UpdateGenerateCallback<QualityBug>() {
			public Update generate(QualityBug t) {
				Update update = update(QUALITY_BUGTABLE).set(
					QUALITY_BUGTABLE.PRODUCT_ID.value(t.getProductId()),
					QUALITY_BUGTABLE.MODULE_ID.value(t.getModuleId()),
					QUALITY_BUGTABLE.PROJECT_ID.value(t.getProjectId()),
					QUALITY_BUGTABLE.PLAN_ID.value(t.getPlanId()),
					QUALITY_BUGTABLE.STORY_ID.value(t.getStoryId()),
					QUALITY_BUGTABLE.STORY_VERSION.value(t.getStoryVersion()),
					QUALITY_BUGTABLE.TASK_ID.value(t.getTaskId()),
					QUALITY_BUGTABLE.TO_TASK_ID.value(t.getToTaskId()),
					QUALITY_BUGTABLE.TO_STORY_ID.value(t.getToStoryId()),
					QUALITY_BUGTABLE.BUG_TITLE.value(t.getBugTitle()),
					QUALITY_BUGTABLE.BUG_KEYWORDS.value(t.getBugKeywords()),
					QUALITY_BUGTABLE.BUG_SEVERITY.value(t.getBugSeverity()),
					QUALITY_BUGTABLE.PRIORITY.value(t.getPriority()),
					QUALITY_BUGTABLE.BUG_TYPE.value(t.getBugType()),
					QUALITY_BUGTABLE.OPERATING_SYSTEM.value(t.getOperatingSystem()),
					QUALITY_BUGTABLE.BROWSER.value(t.getBrowser()),
					QUALITY_BUGTABLE.HARDWARE.value(t.getHardware()),
					QUALITY_BUGTABLE.BUG_FOUND.value(t.getBugFound()),
					QUALITY_BUGTABLE.BUG_STEPS.value(t.getBugSteps()),
					QUALITY_BUGTABLE.BUG_STATUS.value(t.getBugStatus()),
					QUALITY_BUGTABLE.BUG_CONFIRMED.value(t.getBugConfirmed()),
					QUALITY_BUGTABLE.BUG_ACTIVATED_COUNT.value(t.getBugActivatedCount()),
					QUALITY_BUGTABLE.BUG_MAILTO.value(t.getBugMailto()),
					QUALITY_BUGTABLE.BUG_OPENED_BY.value(t.getBugOpenedBy()),
					QUALITY_BUGTABLE.BUG_OPENED_DATE.value(t.getBugOpenedDate()),
					QUALITY_BUGTABLE.BUG_OPENED_BUILD.value(t.getBugOpenedBuild()),
					QUALITY_BUGTABLE.BUG_ASSIGNED_TO.value(t.getBugAssignedTo()),
					QUALITY_BUGTABLE.BUG_ASSIGNED_DATE.value(t.getBugAssignedDate()),
					QUALITY_BUGTABLE.BUG_RESOLVED_BY.value(t.getBugResolvedBy()),
					QUALITY_BUGTABLE.BUG_RESOLUTION.value(t.getBugResolution()),
					QUALITY_BUGTABLE.BUG_RESOLVED_BUILD.value(t.getBugResolvedBuild()),
					QUALITY_BUGTABLE.BUG_RESOLVED_DATE.value(t.getBugResolvedDate()),
					QUALITY_BUGTABLE.BUG_CLOSED_BY.value(t.getBugClosedBy()),
					QUALITY_BUGTABLE.BUG_CLOSED_DATE.value(t.getBugClosedDate()),
					QUALITY_BUGTABLE.BUG_DUPLICATE_BUG.value(t.getBugDuplicateBug()),
					QUALITY_BUGTABLE.LINK_BUG.value(t.getLinkBug()),
					QUALITY_BUGTABLE.LINK_CASE.value(t.getLinkCase()),
					QUALITY_BUGTABLE.CASE_VERSION.value(t.getCaseVersion()),
					QUALITY_BUGTABLE.BUG_RESULT.value(t.getBugResult()),
					QUALITY_BUGTABLE.BUG_REPO.value(t.getBugRepo()),
					QUALITY_BUGTABLE.BUG_ENTRY.value(t.getBugEntry()),
					QUALITY_BUGTABLE.BUG_LINES.value(t.getBugLines()),
					QUALITY_BUGTABLE.BUG_V1.value(t.getBugV1()),
					QUALITY_BUGTABLE.BUG_V2.value(t.getBugV2()),
					QUALITY_BUGTABLE.BUG_REPO_TYPE.value(t.getBugRepoType()),
					QUALITY_BUGTABLE.TESTTASK.value(t.getTesttask()),
					QUALITY_BUGTABLE.BUG_LAST_EDITED_BY.value(t.getBugLastEditedBy()),
					QUALITY_BUGTABLE.BUG_LAST_EDITED_DATE.value(t.getBugLastEditedDate()),
					QUALITY_BUGTABLE.DELETED.value(t.getDeleted())).where(
					QUALITY_BUGTABLE.BUG_ID.eq(t.getBugId()));
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
				return delete(QUALITY_BUGTABLE).where(QUALITY_BUGTABLE.BUG_ID.eq(pk));
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
				return delete(QUALITY_BUGTABLE).where(QUALITY_BUGTABLE.BUG_ID.in(t));
		}
		},pks);
	}

	public QualityBug getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, QualityBug.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(QUALITY_BUGTABLE).where(QUALITY_BUGTABLE.BUG_ID.eq(t));
			}
		});
	}

	public List<QualityBug> query(QualityBug qualityBug ,final OrderBy... orderArgs) {
		if(qualityBug==null){
			qualityBug=new QualityBug();
		}
		return getDslTemplate().query(qualityBug, new SelectGenerateCallback<QualityBug>() {

			@SuppressWarnings("rawtypes")
			public Select generate(QualityBug t) {
				Select select = selectFrom(QUALITY_BUGTABLE).where(
				and(
					QUALITY_BUGTABLE.PRODUCT_ID.eq(t.getProductId()),
					QUALITY_BUGTABLE.MODULE_ID.eq(t.getModuleId()),
					QUALITY_BUGTABLE.PROJECT_ID.eq(t.getProjectId()),
					QUALITY_BUGTABLE.PLAN_ID.eq(t.getPlanId()),
					QUALITY_BUGTABLE.STORY_ID.eq(t.getStoryId()),
					QUALITY_BUGTABLE.STORY_VERSION.eq(t.getStoryVersion()),
					QUALITY_BUGTABLE.TASK_ID.eq(t.getTaskId()),
					QUALITY_BUGTABLE.TO_TASK_ID.eq(t.getToTaskId()),
					QUALITY_BUGTABLE.TO_STORY_ID.eq(t.getToStoryId()),
					QUALITY_BUGTABLE.BUG_TITLE.eq(t.getBugTitle()),
					QUALITY_BUGTABLE.BUG_KEYWORDS.eq(t.getBugKeywords()),
					QUALITY_BUGTABLE.BUG_SEVERITY.eq(t.getBugSeverity()),
					QUALITY_BUGTABLE.PRIORITY.eq(t.getPriority()),
					QUALITY_BUGTABLE.BUG_TYPE.eq(t.getBugType()),
					QUALITY_BUGTABLE.OPERATING_SYSTEM.eq(t.getOperatingSystem()),
					QUALITY_BUGTABLE.BROWSER.eq(t.getBrowser()),
					QUALITY_BUGTABLE.HARDWARE.eq(t.getHardware()),
					QUALITY_BUGTABLE.BUG_FOUND.eq(t.getBugFound()),
					QUALITY_BUGTABLE.BUG_STEPS.eq(t.getBugSteps()),
					QUALITY_BUGTABLE.BUG_STATUS.eq(t.getBugStatus()),
					QUALITY_BUGTABLE.BUG_CONFIRMED.eq(t.getBugConfirmed()),
					QUALITY_BUGTABLE.BUG_ACTIVATED_COUNT.eq(t.getBugActivatedCount()),
					QUALITY_BUGTABLE.BUG_MAILTO.eq(t.getBugMailto()),
					QUALITY_BUGTABLE.BUG_OPENED_BY.eq(t.getBugOpenedBy()),
					QUALITY_BUGTABLE.BUG_OPENED_DATE.eq(t.getBugOpenedDate()),
					QUALITY_BUGTABLE.BUG_OPENED_BUILD.eq(t.getBugOpenedBuild()),
					QUALITY_BUGTABLE.BUG_ASSIGNED_TO.eq(t.getBugAssignedTo()),
					QUALITY_BUGTABLE.BUG_ASSIGNED_DATE.eq(t.getBugAssignedDate()),
					QUALITY_BUGTABLE.BUG_RESOLVED_BY.eq(t.getBugResolvedBy()),
					QUALITY_BUGTABLE.BUG_RESOLUTION.eq(t.getBugResolution()),
					QUALITY_BUGTABLE.BUG_RESOLVED_BUILD.eq(t.getBugResolvedBuild()),
					QUALITY_BUGTABLE.BUG_RESOLVED_DATE.eq(t.getBugResolvedDate()),
					QUALITY_BUGTABLE.BUG_CLOSED_BY.eq(t.getBugClosedBy()),
					QUALITY_BUGTABLE.BUG_CLOSED_DATE.eq(t.getBugClosedDate()),
					QUALITY_BUGTABLE.BUG_DUPLICATE_BUG.eq(t.getBugDuplicateBug()),
					QUALITY_BUGTABLE.LINK_BUG.eq(t.getLinkBug()),
					QUALITY_BUGTABLE.LINK_CASE.eq(t.getLinkCase()),
					QUALITY_BUGTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					QUALITY_BUGTABLE.BUG_RESULT.eq(t.getBugResult()),
					QUALITY_BUGTABLE.BUG_REPO.eq(t.getBugRepo()),
					QUALITY_BUGTABLE.BUG_ENTRY.eq(t.getBugEntry()),
					QUALITY_BUGTABLE.BUG_LINES.eq(t.getBugLines()),
					QUALITY_BUGTABLE.BUG_V1.eq(t.getBugV1()),
					QUALITY_BUGTABLE.BUG_V2.eq(t.getBugV2()),
					QUALITY_BUGTABLE.BUG_REPO_TYPE.eq(t.getBugRepoType()),
					QUALITY_BUGTABLE.TESTTASK.eq(t.getTesttask()),
					QUALITY_BUGTABLE.BUG_LAST_EDITED_BY.eq(t.getBugLastEditedBy()),
					QUALITY_BUGTABLE.BUG_LAST_EDITED_DATE.eq(t.getBugLastEditedDate()),
					QUALITY_BUGTABLE.DELETED.eq(t.getDeleted())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	public Pager<QualityBug> queryPager(int start,int limit ,QualityBug qualityBug ,final OrderBy... orderArgs) {
		if(qualityBug==null){
			qualityBug=new QualityBug();
		}
		return getDslTemplate().queryPager(start, limit, qualityBug, false, new SelectGenerateCallback<QualityBug>() {

			public Select generate(QualityBug t) {
				Select select = MysqlSelect.selectFrom(QUALITY_BUGTABLE).where(
				and(
					QUALITY_BUGTABLE.PRODUCT_ID.eq(t.getProductId()),
					QUALITY_BUGTABLE.MODULE_ID.eq(t.getModuleId()),
					QUALITY_BUGTABLE.PROJECT_ID.eq(t.getProjectId()),
					QUALITY_BUGTABLE.PLAN_ID.eq(t.getPlanId()),
					QUALITY_BUGTABLE.STORY_ID.eq(t.getStoryId()),
					QUALITY_BUGTABLE.STORY_VERSION.eq(t.getStoryVersion()),
					QUALITY_BUGTABLE.TASK_ID.eq(t.getTaskId()),
					QUALITY_BUGTABLE.TO_TASK_ID.eq(t.getToTaskId()),
					QUALITY_BUGTABLE.TO_STORY_ID.eq(t.getToStoryId()),
					QUALITY_BUGTABLE.BUG_TITLE.eq(t.getBugTitle()),
					QUALITY_BUGTABLE.BUG_KEYWORDS.eq(t.getBugKeywords()),
					QUALITY_BUGTABLE.BUG_SEVERITY.eq(t.getBugSeverity()),
					QUALITY_BUGTABLE.PRIORITY.eq(t.getPriority()),
					QUALITY_BUGTABLE.BUG_TYPE.eq(t.getBugType()),
					QUALITY_BUGTABLE.OPERATING_SYSTEM.eq(t.getOperatingSystem()),
					QUALITY_BUGTABLE.BROWSER.eq(t.getBrowser()),
					QUALITY_BUGTABLE.HARDWARE.eq(t.getHardware()),
					QUALITY_BUGTABLE.BUG_FOUND.eq(t.getBugFound()),
					QUALITY_BUGTABLE.BUG_STEPS.eq(t.getBugSteps()),
					QUALITY_BUGTABLE.BUG_STATUS.eq(t.getBugStatus()),
					QUALITY_BUGTABLE.BUG_CONFIRMED.eq(t.getBugConfirmed()),
					QUALITY_BUGTABLE.BUG_ACTIVATED_COUNT.eq(t.getBugActivatedCount()),
					QUALITY_BUGTABLE.BUG_MAILTO.eq(t.getBugMailto()),
					QUALITY_BUGTABLE.BUG_OPENED_BY.eq(t.getBugOpenedBy()),
					QUALITY_BUGTABLE.BUG_OPENED_DATE.eq(t.getBugOpenedDate()),
					QUALITY_BUGTABLE.BUG_OPENED_BUILD.eq(t.getBugOpenedBuild()),
					QUALITY_BUGTABLE.BUG_ASSIGNED_TO.eq(t.getBugAssignedTo()),
					QUALITY_BUGTABLE.BUG_ASSIGNED_DATE.eq(t.getBugAssignedDate()),
					QUALITY_BUGTABLE.BUG_RESOLVED_BY.eq(t.getBugResolvedBy()),
					QUALITY_BUGTABLE.BUG_RESOLUTION.eq(t.getBugResolution()),
					QUALITY_BUGTABLE.BUG_RESOLVED_BUILD.eq(t.getBugResolvedBuild()),
					QUALITY_BUGTABLE.BUG_RESOLVED_DATE.eq(t.getBugResolvedDate()),
					QUALITY_BUGTABLE.BUG_CLOSED_BY.eq(t.getBugClosedBy()),
					QUALITY_BUGTABLE.BUG_CLOSED_DATE.eq(t.getBugClosedDate()),
					QUALITY_BUGTABLE.BUG_DUPLICATE_BUG.eq(t.getBugDuplicateBug()),
					QUALITY_BUGTABLE.LINK_BUG.eq(t.getLinkBug()),
					QUALITY_BUGTABLE.LINK_CASE.eq(t.getLinkCase()),
					QUALITY_BUGTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					QUALITY_BUGTABLE.BUG_RESULT.eq(t.getBugResult()),
					QUALITY_BUGTABLE.BUG_REPO.eq(t.getBugRepo()),
					QUALITY_BUGTABLE.BUG_ENTRY.eq(t.getBugEntry()),
					QUALITY_BUGTABLE.BUG_LINES.eq(t.getBugLines()),
					QUALITY_BUGTABLE.BUG_V1.eq(t.getBugV1()),
					QUALITY_BUGTABLE.BUG_V2.eq(t.getBugV2()),
					QUALITY_BUGTABLE.BUG_REPO_TYPE.eq(t.getBugRepoType()),
					QUALITY_BUGTABLE.TESTTASK.eq(t.getTesttask()),
					QUALITY_BUGTABLE.BUG_LAST_EDITED_BY.eq(t.getBugLastEditedBy()),
					QUALITY_BUGTABLE.BUG_LAST_EDITED_DATE.eq(t.getBugLastEditedDate()),
					QUALITY_BUGTABLE.DELETED.eq(t.getDeleted())));
			return addOrderByElements(select, orderArgs);
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<QualityBug> qualityBugs) {
		if (CollectionUtil.isEmpty(qualityBugs)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, qualityBugs, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(QUALITY_BUGTABLE).values(
					QUALITY_BUGTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					QUALITY_BUGTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
					QUALITY_BUGTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
					QUALITY_BUGTABLE.PLAN_ID.value(new JdbcNamedParameter("planId")),
					QUALITY_BUGTABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
					QUALITY_BUGTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
					QUALITY_BUGTABLE.TASK_ID.value(new JdbcNamedParameter("taskId")),
					QUALITY_BUGTABLE.TO_TASK_ID.value(new JdbcNamedParameter("toTaskId")),
					QUALITY_BUGTABLE.TO_STORY_ID.value(new JdbcNamedParameter("toStoryId")),
					QUALITY_BUGTABLE.BUG_TITLE.value(new JdbcNamedParameter("bugTitle")),
					QUALITY_BUGTABLE.BUG_KEYWORDS.value(new JdbcNamedParameter("bugKeywords")),
					QUALITY_BUGTABLE.BUG_SEVERITY.value(new JdbcNamedParameter("bugSeverity")),
					QUALITY_BUGTABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
					QUALITY_BUGTABLE.BUG_TYPE.value(new JdbcNamedParameter("bugType")),
					QUALITY_BUGTABLE.OPERATING_SYSTEM.value(new JdbcNamedParameter("operatingSystem")),
					QUALITY_BUGTABLE.BROWSER.value(new JdbcNamedParameter("browser")),
					QUALITY_BUGTABLE.HARDWARE.value(new JdbcNamedParameter("hardware")),
					QUALITY_BUGTABLE.BUG_FOUND.value(new JdbcNamedParameter("bugFound")),
					QUALITY_BUGTABLE.BUG_STEPS.value(new JdbcNamedParameter("bugSteps")),
					QUALITY_BUGTABLE.BUG_STATUS.value(new JdbcNamedParameter("bugStatus")),
					QUALITY_BUGTABLE.BUG_CONFIRMED.value(new JdbcNamedParameter("bugConfirmed")),
					QUALITY_BUGTABLE.BUG_ACTIVATED_COUNT.value(new JdbcNamedParameter("bugActivatedCount")),
					QUALITY_BUGTABLE.BUG_MAILTO.value(new JdbcNamedParameter("bugMailto")),
					QUALITY_BUGTABLE.BUG_OPENED_BY.value(new JdbcNamedParameter("bugOpenedBy")),
					QUALITY_BUGTABLE.BUG_OPENED_DATE.value(new JdbcNamedParameter("bugOpenedDate")),
					QUALITY_BUGTABLE.BUG_OPENED_BUILD.value(new JdbcNamedParameter("bugOpenedBuild")),
					QUALITY_BUGTABLE.BUG_ASSIGNED_TO.value(new JdbcNamedParameter("bugAssignedTo")),
					QUALITY_BUGTABLE.BUG_ASSIGNED_DATE.value(new JdbcNamedParameter("bugAssignedDate")),
					QUALITY_BUGTABLE.BUG_RESOLVED_BY.value(new JdbcNamedParameter("bugResolvedBy")),
					QUALITY_BUGTABLE.BUG_RESOLUTION.value(new JdbcNamedParameter("bugResolution")),
					QUALITY_BUGTABLE.BUG_RESOLVED_BUILD.value(new JdbcNamedParameter("bugResolvedBuild")),
					QUALITY_BUGTABLE.BUG_RESOLVED_DATE.value(new JdbcNamedParameter("bugResolvedDate")),
					QUALITY_BUGTABLE.BUG_CLOSED_BY.value(new JdbcNamedParameter("bugClosedBy")),
					QUALITY_BUGTABLE.BUG_CLOSED_DATE.value(new JdbcNamedParameter("bugClosedDate")),
					QUALITY_BUGTABLE.BUG_DUPLICATE_BUG.value(new JdbcNamedParameter("bugDuplicateBug")),
					QUALITY_BUGTABLE.LINK_BUG.value(new JdbcNamedParameter("linkBug")),
					QUALITY_BUGTABLE.LINK_CASE.value(new JdbcNamedParameter("linkCase")),
					QUALITY_BUGTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					QUALITY_BUGTABLE.BUG_RESULT.value(new JdbcNamedParameter("bugResult")),
					QUALITY_BUGTABLE.BUG_REPO.value(new JdbcNamedParameter("bugRepo")),
					QUALITY_BUGTABLE.BUG_ENTRY.value(new JdbcNamedParameter("bugEntry")),
					QUALITY_BUGTABLE.BUG_LINES.value(new JdbcNamedParameter("bugLines")),
					QUALITY_BUGTABLE.BUG_V1.value(new JdbcNamedParameter("bugV1")),
					QUALITY_BUGTABLE.BUG_V2.value(new JdbcNamedParameter("bugV2")),
					QUALITY_BUGTABLE.BUG_REPO_TYPE.value(new JdbcNamedParameter("bugRepoType")),
					QUALITY_BUGTABLE.TESTTASK.value(new JdbcNamedParameter("testtask")),
					QUALITY_BUGTABLE.BUG_LAST_EDITED_BY.value(new JdbcNamedParameter("bugLastEditedBy")),
					QUALITY_BUGTABLE.BUG_LAST_EDITED_DATE.value(new JdbcNamedParameter("bugLastEditedDate")),
					QUALITY_BUGTABLE.DELETED.value(new JdbcNamedParameter("deleted")));
			}
		});
	}

	public int[] batchInsert(List<QualityBug> qualityBugs){
			return batchInsert(true ,qualityBugs);
	}
	@LogMethod("batchUpdate")
	public int[] batchUpdate(List<QualityBug> qualityBugs) {
		if (CollectionUtil.isEmpty(qualityBugs)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(qualityBugs, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(QUALITY_BUGTABLE).set(
					QUALITY_BUGTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					QUALITY_BUGTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
					QUALITY_BUGTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
					QUALITY_BUGTABLE.PLAN_ID.value(new JdbcNamedParameter("planId")),
					QUALITY_BUGTABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
					QUALITY_BUGTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
					QUALITY_BUGTABLE.TASK_ID.value(new JdbcNamedParameter("taskId")),
					QUALITY_BUGTABLE.TO_TASK_ID.value(new JdbcNamedParameter("toTaskId")),
					QUALITY_BUGTABLE.TO_STORY_ID.value(new JdbcNamedParameter("toStoryId")),
					QUALITY_BUGTABLE.BUG_TITLE.value(new JdbcNamedParameter("bugTitle")),
					QUALITY_BUGTABLE.BUG_KEYWORDS.value(new JdbcNamedParameter("bugKeywords")),
					QUALITY_BUGTABLE.BUG_SEVERITY.value(new JdbcNamedParameter("bugSeverity")),
					QUALITY_BUGTABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
					QUALITY_BUGTABLE.BUG_TYPE.value(new JdbcNamedParameter("bugType")),
					QUALITY_BUGTABLE.OPERATING_SYSTEM.value(new JdbcNamedParameter("operatingSystem")),
					QUALITY_BUGTABLE.BROWSER.value(new JdbcNamedParameter("browser")),
					QUALITY_BUGTABLE.HARDWARE.value(new JdbcNamedParameter("hardware")),
					QUALITY_BUGTABLE.BUG_FOUND.value(new JdbcNamedParameter("bugFound")),
					QUALITY_BUGTABLE.BUG_STEPS.value(new JdbcNamedParameter("bugSteps")),
					QUALITY_BUGTABLE.BUG_STATUS.value(new JdbcNamedParameter("bugStatus")),
					QUALITY_BUGTABLE.BUG_CONFIRMED.value(new JdbcNamedParameter("bugConfirmed")),
					QUALITY_BUGTABLE.BUG_ACTIVATED_COUNT.value(new JdbcNamedParameter("bugActivatedCount")),
					QUALITY_BUGTABLE.BUG_MAILTO.value(new JdbcNamedParameter("bugMailto")),
					QUALITY_BUGTABLE.BUG_OPENED_BY.value(new JdbcNamedParameter("bugOpenedBy")),
					QUALITY_BUGTABLE.BUG_OPENED_DATE.value(new JdbcNamedParameter("bugOpenedDate")),
					QUALITY_BUGTABLE.BUG_OPENED_BUILD.value(new JdbcNamedParameter("bugOpenedBuild")),
					QUALITY_BUGTABLE.BUG_ASSIGNED_TO.value(new JdbcNamedParameter("bugAssignedTo")),
					QUALITY_BUGTABLE.BUG_ASSIGNED_DATE.value(new JdbcNamedParameter("bugAssignedDate")),
					QUALITY_BUGTABLE.BUG_RESOLVED_BY.value(new JdbcNamedParameter("bugResolvedBy")),
					QUALITY_BUGTABLE.BUG_RESOLUTION.value(new JdbcNamedParameter("bugResolution")),
					QUALITY_BUGTABLE.BUG_RESOLVED_BUILD.value(new JdbcNamedParameter("bugResolvedBuild")),
					QUALITY_BUGTABLE.BUG_RESOLVED_DATE.value(new JdbcNamedParameter("bugResolvedDate")),
					QUALITY_BUGTABLE.BUG_CLOSED_BY.value(new JdbcNamedParameter("bugClosedBy")),
					QUALITY_BUGTABLE.BUG_CLOSED_DATE.value(new JdbcNamedParameter("bugClosedDate")),
					QUALITY_BUGTABLE.BUG_DUPLICATE_BUG.value(new JdbcNamedParameter("bugDuplicateBug")),
					QUALITY_BUGTABLE.LINK_BUG.value(new JdbcNamedParameter("linkBug")),
					QUALITY_BUGTABLE.LINK_CASE.value(new JdbcNamedParameter("linkCase")),
					QUALITY_BUGTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					QUALITY_BUGTABLE.BUG_RESULT.value(new JdbcNamedParameter("bugResult")),
					QUALITY_BUGTABLE.BUG_REPO.value(new JdbcNamedParameter("bugRepo")),
					QUALITY_BUGTABLE.BUG_ENTRY.value(new JdbcNamedParameter("bugEntry")),
					QUALITY_BUGTABLE.BUG_LINES.value(new JdbcNamedParameter("bugLines")),
					QUALITY_BUGTABLE.BUG_V1.value(new JdbcNamedParameter("bugV1")),
					QUALITY_BUGTABLE.BUG_V2.value(new JdbcNamedParameter("bugV2")),
					QUALITY_BUGTABLE.BUG_REPO_TYPE.value(new JdbcNamedParameter("bugRepoType")),
					QUALITY_BUGTABLE.TESTTASK.value(new JdbcNamedParameter("testtask")),
					QUALITY_BUGTABLE.BUG_LAST_EDITED_BY.value(new JdbcNamedParameter("bugLastEditedBy")),
					QUALITY_BUGTABLE.BUG_LAST_EDITED_DATE.value(new JdbcNamedParameter("bugLastEditedDate")),
					QUALITY_BUGTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
				QUALITY_BUGTABLE.BUG_ID.eq(new JdbcNamedParameter("bugId")));
			}
		});
	}
	@LogMethod("batchDelete")
	public int[] batchDelete(List<QualityBug> qualityBugs) {
		if (CollectionUtil.isEmpty(qualityBugs)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(qualityBugs, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(QUALITY_BUGTABLE).where(and(
				QUALITY_BUGTABLE.BUG_ID.eq(new JdbcNamedParameter("bugId")),
				QUALITY_BUGTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
				QUALITY_BUGTABLE.MODULE_ID.eq(new JdbcNamedParameter("moduleId")),
				QUALITY_BUGTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectId")),
				QUALITY_BUGTABLE.PLAN_ID.eq(new JdbcNamedParameter("planId")),
				QUALITY_BUGTABLE.STORY_ID.eq(new JdbcNamedParameter("storyId")),
				QUALITY_BUGTABLE.STORY_VERSION.eq(new JdbcNamedParameter("storyVersion")),
				QUALITY_BUGTABLE.TASK_ID.eq(new JdbcNamedParameter("taskId")),
				QUALITY_BUGTABLE.TO_TASK_ID.eq(new JdbcNamedParameter("toTaskId")),
				QUALITY_BUGTABLE.TO_STORY_ID.eq(new JdbcNamedParameter("toStoryId")),
				QUALITY_BUGTABLE.BUG_TITLE.eq(new JdbcNamedParameter("bugTitle")),
				QUALITY_BUGTABLE.BUG_KEYWORDS.eq(new JdbcNamedParameter("bugKeywords")),
				QUALITY_BUGTABLE.BUG_SEVERITY.eq(new JdbcNamedParameter("bugSeverity")),
				QUALITY_BUGTABLE.PRIORITY.eq(new JdbcNamedParameter("priority")),
				QUALITY_BUGTABLE.BUG_TYPE.eq(new JdbcNamedParameter("bugType")),
				QUALITY_BUGTABLE.OPERATING_SYSTEM.eq(new JdbcNamedParameter("operatingSystem")),
				QUALITY_BUGTABLE.BROWSER.eq(new JdbcNamedParameter("browser")),
				QUALITY_BUGTABLE.HARDWARE.eq(new JdbcNamedParameter("hardware")),
				QUALITY_BUGTABLE.BUG_FOUND.eq(new JdbcNamedParameter("bugFound")),
				QUALITY_BUGTABLE.BUG_STEPS.eq(new JdbcNamedParameter("bugSteps")),
				QUALITY_BUGTABLE.BUG_STATUS.eq(new JdbcNamedParameter("bugStatus")),
				QUALITY_BUGTABLE.BUG_CONFIRMED.eq(new JdbcNamedParameter("bugConfirmed")),
				QUALITY_BUGTABLE.BUG_ACTIVATED_COUNT.eq(new JdbcNamedParameter("bugActivatedCount")),
				QUALITY_BUGTABLE.BUG_MAILTO.eq(new JdbcNamedParameter("bugMailto")),
				QUALITY_BUGTABLE.BUG_OPENED_BY.eq(new JdbcNamedParameter("bugOpenedBy")),
				QUALITY_BUGTABLE.BUG_OPENED_DATE.eq(new JdbcNamedParameter("bugOpenedDate")),
				QUALITY_BUGTABLE.BUG_OPENED_BUILD.eq(new JdbcNamedParameter("bugOpenedBuild")),
				QUALITY_BUGTABLE.BUG_ASSIGNED_TO.eq(new JdbcNamedParameter("bugAssignedTo")),
				QUALITY_BUGTABLE.BUG_ASSIGNED_DATE.eq(new JdbcNamedParameter("bugAssignedDate")),
				QUALITY_BUGTABLE.BUG_RESOLVED_BY.eq(new JdbcNamedParameter("bugResolvedBy")),
				QUALITY_BUGTABLE.BUG_RESOLUTION.eq(new JdbcNamedParameter("bugResolution")),
				QUALITY_BUGTABLE.BUG_RESOLVED_BUILD.eq(new JdbcNamedParameter("bugResolvedBuild")),
				QUALITY_BUGTABLE.BUG_RESOLVED_DATE.eq(new JdbcNamedParameter("bugResolvedDate")),
				QUALITY_BUGTABLE.BUG_CLOSED_BY.eq(new JdbcNamedParameter("bugClosedBy")),
				QUALITY_BUGTABLE.BUG_CLOSED_DATE.eq(new JdbcNamedParameter("bugClosedDate")),
				QUALITY_BUGTABLE.BUG_DUPLICATE_BUG.eq(new JdbcNamedParameter("bugDuplicateBug")),
				QUALITY_BUGTABLE.LINK_BUG.eq(new JdbcNamedParameter("linkBug")),
				QUALITY_BUGTABLE.LINK_CASE.eq(new JdbcNamedParameter("linkCase")),
				QUALITY_BUGTABLE.CASE_VERSION.eq(new JdbcNamedParameter("caseVersion")),
				QUALITY_BUGTABLE.BUG_RESULT.eq(new JdbcNamedParameter("bugResult")),
				QUALITY_BUGTABLE.BUG_REPO.eq(new JdbcNamedParameter("bugRepo")),
				QUALITY_BUGTABLE.BUG_ENTRY.eq(new JdbcNamedParameter("bugEntry")),
				QUALITY_BUGTABLE.BUG_LINES.eq(new JdbcNamedParameter("bugLines")),
				QUALITY_BUGTABLE.BUG_V1.eq(new JdbcNamedParameter("bugV1")),
				QUALITY_BUGTABLE.BUG_V2.eq(new JdbcNamedParameter("bugV2")),
				QUALITY_BUGTABLE.BUG_REPO_TYPE.eq(new JdbcNamedParameter("bugRepoType")),
				QUALITY_BUGTABLE.TESTTASK.eq(new JdbcNamedParameter("testtask")),
				QUALITY_BUGTABLE.BUG_LAST_EDITED_BY.eq(new JdbcNamedParameter("bugLastEditedBy")),
				QUALITY_BUGTABLE.BUG_LAST_EDITED_DATE.eq(new JdbcNamedParameter("bugLastEditedDate")),
				QUALITY_BUGTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
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
