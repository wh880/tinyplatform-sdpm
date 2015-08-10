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

package org.tinygroup.sdpm.quality.impl;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static org.tinygroup.sdpm.quality.constant.BugTable.*;
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
import org.tinygroup.sdpm.quality.pojo.Bug;
import org.tinygroup.sdpm.quality.inter.BugDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class BugDaoImpl extends TinyDslDaoSupport implements BugDao {

	public Bug insertObject(Bug bug) {
		return getDslTemplate().insertObject(bug, new InsertGenerateCallback<Bug>() {
			public Insert generate(Bug t) {
				Insert insert = insertInto(BUGTABLE).values(
					BUGTABLE.BUG_ID.value(t.getBugId()),
					BUGTABLE.COMPANY_ID.value(t.getCompanyId()),
					BUGTABLE.PRODUCT_ID.value(t.getProductId()),
					BUGTABLE.STORY_ID.value(t.getStoryId()),
					BUGTABLE.STORY_VERSION.value(t.getStoryVersion()),
					BUGTABLE.PROJECT_ID.value(t.getProjectId()),
					BUGTABLE.TO_STORY_ID.value(t.getToStoryId()),
					BUGTABLE.TASK_ID.value(t.getTaskId()),
					BUGTABLE.TO_TASK_ID.value(t.getToTaskId()),
					BUGTABLE.BUG_TITLE.value(t.getBugTitle()),
					BUGTABLE.MODULE_ID.value(t.getModuleId()),
					BUGTABLE.BUG_KEYWORDS.value(t.getBugKeywords()),
					BUGTABLE.BUG_SEVERITY.value(t.getBugSeverity()),
					BUGTABLE.BUG_PRI.value(t.getBugPri()),
					BUGTABLE.BUG_TYPE.value(t.getBugType()),
					BUGTABLE.BUG_OS.value(t.getBugOs()),
					BUGTABLE.BUG_BROWSER.value(t.getBugBrowser()),
					BUGTABLE.BUG_HARDWARE.value(t.getBugHardware()),
					BUGTABLE.BUG_STEPS.value(t.getBugSteps()),
					BUGTABLE.BUG_STATUS.value(t.getBugStatus()),
					BUGTABLE.BUG_CONFIRMED.value(t.getBugConfirmed()),
					BUGTABLE.BUG_OPEN_COUNT.value(t.getBugOpenCount()),
					BUGTABLE.BUG_MAILTO.value(t.getBugMailto()),
					BUGTABLE.BUG_OPENED_BY.value(t.getBugOpenedBy()),
					BUGTABLE.BUG_OPENED_DATE.value(t.getBugOpenedDate()),
					BUGTABLE.BUG_OPENED_BUILD.value(t.getBugOpenedBuild()),
					BUGTABLE.BUG_ASSIGNED_TO.value(t.getBugAssignedTo()),
					BUGTABLE.BUG_ASSIGNED_DATE.value(t.getBugAssignedDate()),
					BUGTABLE.BUG_RESOLVED_BY.value(t.getBugResolvedBy()),
					BUGTABLE.BUG_RESOLUTION.value(t.getBugResolution()),
					BUGTABLE.BUG_RESOLVED_BUILD.value(t.getBugResolvedBuild()),
					BUGTABLE.BUG_RESOLVED_DATE.value(t.getBugResolvedDate()),
					BUGTABLE.BUG_CLOSED_BY.value(t.getBugClosedBy()),
					BUGTABLE.BUG_CLOSED_DATE.value(t.getBugClosedDate()),
					BUGTABLE.BUG_DUPLICATE_BUG.value(t.getBugDuplicateBug()),
					BUGTABLE.BUG_LINK_BUG.value(t.getBugLinkBug()),
					BUGTABLE.BUG_LAST_EDITED_BY.value(t.getBugLastEditedBy()),
					BUGTABLE.BUG_LAST_EDITED_DATE.value(t.getBugLastEditedDate()),
					BUGTABLE.CASE_ID.value(t.getCaseId()),
					BUGTABLE.CASE_VERSION.value(t.getCaseVersion()),
					BUGTABLE.BUG_SOURCE.value(t.getBugSource()),
					BUGTABLE.BUG_ORIGIN.value(t.getBugOrigin()),
					BUGTABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}

	public Bug insertObject(boolean autoGeneratedKeys ,Bug bug) {
		return getDslTemplate().insertObjectAndReturnKey(autoGeneratedKeys ,bug, new InsertGenerateCallback<Bug>() {

			public Insert generate(Bug t) {
				Insert insert = insertInto(BUGTABLE).values(
					BUGTABLE.BUG_ID.value(t.getBugId()),
					BUGTABLE.COMPANY_ID.value(t.getCompanyId()),
					BUGTABLE.PRODUCT_ID.value(t.getProductId()),
					BUGTABLE.STORY_ID.value(t.getStoryId()),
					BUGTABLE.STORY_VERSION.value(t.getStoryVersion()),
					BUGTABLE.PROJECT_ID.value(t.getProjectId()),
					BUGTABLE.TO_STORY_ID.value(t.getToStoryId()),
					BUGTABLE.TASK_ID.value(t.getTaskId()),
					BUGTABLE.TO_TASK_ID.value(t.getToTaskId()),
					BUGTABLE.BUG_TITLE.value(t.getBugTitle()),
					BUGTABLE.MODULE_ID.value(t.getModuleId()),
					BUGTABLE.BUG_KEYWORDS.value(t.getBugKeywords()),
					BUGTABLE.BUG_SEVERITY.value(t.getBugSeverity()),
					BUGTABLE.BUG_PRI.value(t.getBugPri()),
					BUGTABLE.BUG_TYPE.value(t.getBugType()),
					BUGTABLE.BUG_OS.value(t.getBugOs()),
					BUGTABLE.BUG_BROWSER.value(t.getBugBrowser()),
					BUGTABLE.BUG_HARDWARE.value(t.getBugHardware()),
					BUGTABLE.BUG_STEPS.value(t.getBugSteps()),
					BUGTABLE.BUG_STATUS.value(t.getBugStatus()),
					BUGTABLE.BUG_CONFIRMED.value(t.getBugConfirmed()),
					BUGTABLE.BUG_OPEN_COUNT.value(t.getBugOpenCount()),
					BUGTABLE.BUG_MAILTO.value(t.getBugMailto()),
					BUGTABLE.BUG_OPENED_BY.value(t.getBugOpenedBy()),
					BUGTABLE.BUG_OPENED_DATE.value(t.getBugOpenedDate()),
					BUGTABLE.BUG_OPENED_BUILD.value(t.getBugOpenedBuild()),
					BUGTABLE.BUG_ASSIGNED_TO.value(t.getBugAssignedTo()),
					BUGTABLE.BUG_ASSIGNED_DATE.value(t.getBugAssignedDate()),
					BUGTABLE.BUG_RESOLVED_BY.value(t.getBugResolvedBy()),
					BUGTABLE.BUG_RESOLUTION.value(t.getBugResolution()),
					BUGTABLE.BUG_RESOLVED_BUILD.value(t.getBugResolvedBuild()),
					BUGTABLE.BUG_RESOLVED_DATE.value(t.getBugResolvedDate()),
					BUGTABLE.BUG_CLOSED_BY.value(t.getBugClosedBy()),
					BUGTABLE.BUG_CLOSED_DATE.value(t.getBugClosedDate()),
					BUGTABLE.BUG_DUPLICATE_BUG.value(t.getBugDuplicateBug()),
					BUGTABLE.BUG_LINK_BUG.value(t.getBugLinkBug()),
					BUGTABLE.BUG_LAST_EDITED_BY.value(t.getBugLastEditedBy()),
					BUGTABLE.BUG_LAST_EDITED_DATE.value(t.getBugLastEditedDate()),
					BUGTABLE.CASE_ID.value(t.getCaseId()),
					BUGTABLE.CASE_VERSION.value(t.getCaseVersion()),
					BUGTABLE.BUG_SOURCE.value(t.getBugSource()),
					BUGTABLE.BUG_ORIGIN.value(t.getBugOrigin()),
					BUGTABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}

	public int updateObject(Bug bug) {
		return getDslTemplate().updateObject(bug, new UpdateGenerateCallback<Bug>() {
			public Update generate(Bug t) {
				Update update = update(BUGTABLE).set(
					BUGTABLE.COMPANY_ID.value(t.getCompanyId()),
					BUGTABLE.PRODUCT_ID.value(t.getProductId()),
					BUGTABLE.STORY_ID.value(t.getStoryId()),
					BUGTABLE.STORY_VERSION.value(t.getStoryVersion()),
					BUGTABLE.PROJECT_ID.value(t.getProjectId()),
					BUGTABLE.TO_STORY_ID.value(t.getToStoryId()),
					BUGTABLE.TASK_ID.value(t.getTaskId()),
					BUGTABLE.TO_TASK_ID.value(t.getToTaskId()),
					BUGTABLE.BUG_TITLE.value(t.getBugTitle()),
					BUGTABLE.MODULE_ID.value(t.getModuleId()),
					BUGTABLE.BUG_KEYWORDS.value(t.getBugKeywords()),
					BUGTABLE.BUG_SEVERITY.value(t.getBugSeverity()),
					BUGTABLE.BUG_PRI.value(t.getBugPri()),
					BUGTABLE.BUG_TYPE.value(t.getBugType()),
					BUGTABLE.BUG_OS.value(t.getBugOs()),
					BUGTABLE.BUG_BROWSER.value(t.getBugBrowser()),
					BUGTABLE.BUG_HARDWARE.value(t.getBugHardware()),
					BUGTABLE.BUG_STEPS.value(t.getBugSteps()),
					BUGTABLE.BUG_STATUS.value(t.getBugStatus()),
					BUGTABLE.BUG_CONFIRMED.value(t.getBugConfirmed()),
					BUGTABLE.BUG_OPEN_COUNT.value(t.getBugOpenCount()),
					BUGTABLE.BUG_MAILTO.value(t.getBugMailto()),
					BUGTABLE.BUG_OPENED_BY.value(t.getBugOpenedBy()),
					BUGTABLE.BUG_OPENED_DATE.value(t.getBugOpenedDate()),
					BUGTABLE.BUG_OPENED_BUILD.value(t.getBugOpenedBuild()),
					BUGTABLE.BUG_ASSIGNED_TO.value(t.getBugAssignedTo()),
					BUGTABLE.BUG_ASSIGNED_DATE.value(t.getBugAssignedDate()),
					BUGTABLE.BUG_RESOLVED_BY.value(t.getBugResolvedBy()),
					BUGTABLE.BUG_RESOLUTION.value(t.getBugResolution()),
					BUGTABLE.BUG_RESOLVED_BUILD.value(t.getBugResolvedBuild()),
					BUGTABLE.BUG_RESOLVED_DATE.value(t.getBugResolvedDate()),
					BUGTABLE.BUG_CLOSED_BY.value(t.getBugClosedBy()),
					BUGTABLE.BUG_CLOSED_DATE.value(t.getBugClosedDate()),
					BUGTABLE.BUG_DUPLICATE_BUG.value(t.getBugDuplicateBug()),
					BUGTABLE.BUG_LINK_BUG.value(t.getBugLinkBug()),
					BUGTABLE.BUG_LAST_EDITED_BY.value(t.getBugLastEditedBy()),
					BUGTABLE.BUG_LAST_EDITED_DATE.value(t.getBugLastEditedDate()),
					BUGTABLE.CASE_ID.value(t.getCaseId()),
					BUGTABLE.CASE_VERSION.value(t.getCaseVersion()),
					BUGTABLE.BUG_SOURCE.value(t.getBugSource()),
					BUGTABLE.BUG_ORIGIN.value(t.getBugOrigin()),
					BUGTABLE.DELETED.value(t.getDeleted())).where(
					BUGTABLE.BUG_ID.eq(t.getBugId()));
				return update;
			}
		});
	}

	public int deleteObject(Serializable pk){
		return getDslTemplate().deleteObject(pk, new DeleteGenerateCallback<Serializable>() {
			public Delete generate(Serializable pk) {
				return delete(BUGTABLE).where(BUGTABLE.BUG_ID.eq(pk));
			}
		});
	}

	public int deleteObjects(Serializable... pks) {
		return getDslTemplate().deleteObjects(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(BUGTABLE).where(BUGTABLE.BUG_ID.in(t));
		}
		},pks);
	}

	public Bug getObjectById(Serializable pk) {
		return getDslTemplate().getObjectById(pk, Bug.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(BUGTABLE).where(BUGTABLE.BUG_ID.eq(t));
			}
		});
	}

	public List<Bug> queryObjects(Bug bug) {
		if(bug==null){
			bug=new Bug();
		}
		return getDslTemplate().queryObjects(bug, new SelectGenerateCallback<Bug>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Bug t) {
				return selectFrom(BUGTABLE).where(
				and(
					BUGTABLE.COMPANY_ID.eq(t.getCompanyId()),
					BUGTABLE.PRODUCT_ID.eq(t.getProductId()),
					BUGTABLE.STORY_ID.eq(t.getStoryId()),
					BUGTABLE.STORY_VERSION.eq(t.getStoryVersion()),
					BUGTABLE.PROJECT_ID.eq(t.getProjectId()),
					BUGTABLE.TO_STORY_ID.eq(t.getToStoryId()),
					BUGTABLE.TASK_ID.eq(t.getTaskId()),
					BUGTABLE.TO_TASK_ID.eq(t.getToTaskId()),
					BUGTABLE.BUG_TITLE.eq(t.getBugTitle()),
					BUGTABLE.MODULE_ID.eq(t.getModuleId()),
					BUGTABLE.BUG_KEYWORDS.eq(t.getBugKeywords()),
					BUGTABLE.BUG_SEVERITY.eq(t.getBugSeverity()),
					BUGTABLE.BUG_PRI.eq(t.getBugPri()),
					BUGTABLE.BUG_TYPE.eq(t.getBugType()),
					BUGTABLE.BUG_OS.eq(t.getBugOs()),
					BUGTABLE.BUG_BROWSER.eq(t.getBugBrowser()),
					BUGTABLE.BUG_HARDWARE.eq(t.getBugHardware()),
					BUGTABLE.BUG_STEPS.eq(t.getBugSteps()),
					BUGTABLE.BUG_STATUS.eq(t.getBugStatus()),
					BUGTABLE.BUG_CONFIRMED.eq(t.getBugConfirmed()),
					BUGTABLE.BUG_OPEN_COUNT.eq(t.getBugOpenCount()),
					BUGTABLE.BUG_MAILTO.eq(t.getBugMailto()),
					BUGTABLE.BUG_OPENED_BY.eq(t.getBugOpenedBy()),
					BUGTABLE.BUG_OPENED_DATE.eq(t.getBugOpenedDate()),
					BUGTABLE.BUG_OPENED_BUILD.eq(t.getBugOpenedBuild()),
					BUGTABLE.BUG_ASSIGNED_TO.eq(t.getBugAssignedTo()),
					BUGTABLE.BUG_ASSIGNED_DATE.eq(t.getBugAssignedDate()),
					BUGTABLE.BUG_RESOLVED_BY.eq(t.getBugResolvedBy()),
					BUGTABLE.BUG_RESOLUTION.eq(t.getBugResolution()),
					BUGTABLE.BUG_RESOLVED_BUILD.eq(t.getBugResolvedBuild()),
					BUGTABLE.BUG_RESOLVED_DATE.eq(t.getBugResolvedDate()),
					BUGTABLE.BUG_CLOSED_BY.eq(t.getBugClosedBy()),
					BUGTABLE.BUG_CLOSED_DATE.eq(t.getBugClosedDate()),
					BUGTABLE.BUG_DUPLICATE_BUG.eq(t.getBugDuplicateBug()),
					BUGTABLE.BUG_LINK_BUG.eq(t.getBugLinkBug()),
					BUGTABLE.BUG_LAST_EDITED_BY.eq(t.getBugLastEditedBy()),
					BUGTABLE.BUG_LAST_EDITED_DATE.eq(t.getBugLastEditedDate()),
					BUGTABLE.CASE_ID.eq(t.getCaseId()),
					BUGTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					BUGTABLE.BUG_SOURCE.eq(t.getBugSource()),
					BUGTABLE.BUG_ORIGIN.eq(t.getBugOrigin()),
					BUGTABLE.DELETED.eq(t.getDeleted())));
			}
		});
	}

	public Pager<Bug> queryObjectsForPage(int start,int limit ,Bug bug) {
		if(bug==null){
			bug=new Bug();
		}
		return getDslTemplate().queryObjectsForPage(start, limit, bug, false, new SelectGenerateCallback<Bug>() {

			public Select generate(Bug t) {
				return MysqlSelect.selectFrom(BUGTABLE).where(
				and(
					BUGTABLE.COMPANY_ID.eq(t.getCompanyId()),
					BUGTABLE.PRODUCT_ID.eq(t.getProductId()),
					BUGTABLE.STORY_ID.eq(t.getStoryId()),
					BUGTABLE.STORY_VERSION.eq(t.getStoryVersion()),
					BUGTABLE.PROJECT_ID.eq(t.getProjectId()),
					BUGTABLE.TO_STORY_ID.eq(t.getToStoryId()),
					BUGTABLE.TASK_ID.eq(t.getTaskId()),
					BUGTABLE.TO_TASK_ID.eq(t.getToTaskId()),
					BUGTABLE.BUG_TITLE.eq(t.getBugTitle()),
					BUGTABLE.MODULE_ID.eq(t.getModuleId()),
					BUGTABLE.BUG_KEYWORDS.eq(t.getBugKeywords()),
					BUGTABLE.BUG_SEVERITY.eq(t.getBugSeverity()),
					BUGTABLE.BUG_PRI.eq(t.getBugPri()),
					BUGTABLE.BUG_TYPE.eq(t.getBugType()),
					BUGTABLE.BUG_OS.eq(t.getBugOs()),
					BUGTABLE.BUG_BROWSER.eq(t.getBugBrowser()),
					BUGTABLE.BUG_HARDWARE.eq(t.getBugHardware()),
					BUGTABLE.BUG_STEPS.eq(t.getBugSteps()),
					BUGTABLE.BUG_STATUS.eq(t.getBugStatus()),
					BUGTABLE.BUG_CONFIRMED.eq(t.getBugConfirmed()),
					BUGTABLE.BUG_OPEN_COUNT.eq(t.getBugOpenCount()),
					BUGTABLE.BUG_MAILTO.eq(t.getBugMailto()),
					BUGTABLE.BUG_OPENED_BY.eq(t.getBugOpenedBy()),
					BUGTABLE.BUG_OPENED_DATE.eq(t.getBugOpenedDate()),
					BUGTABLE.BUG_OPENED_BUILD.eq(t.getBugOpenedBuild()),
					BUGTABLE.BUG_ASSIGNED_TO.eq(t.getBugAssignedTo()),
					BUGTABLE.BUG_ASSIGNED_DATE.eq(t.getBugAssignedDate()),
					BUGTABLE.BUG_RESOLVED_BY.eq(t.getBugResolvedBy()),
					BUGTABLE.BUG_RESOLUTION.eq(t.getBugResolution()),
					BUGTABLE.BUG_RESOLVED_BUILD.eq(t.getBugResolvedBuild()),
					BUGTABLE.BUG_RESOLVED_DATE.eq(t.getBugResolvedDate()),
					BUGTABLE.BUG_CLOSED_BY.eq(t.getBugClosedBy()),
					BUGTABLE.BUG_CLOSED_DATE.eq(t.getBugClosedDate()),
					BUGTABLE.BUG_DUPLICATE_BUG.eq(t.getBugDuplicateBug()),
					BUGTABLE.BUG_LINK_BUG.eq(t.getBugLinkBug()),
					BUGTABLE.BUG_LAST_EDITED_BY.eq(t.getBugLastEditedBy()),
					BUGTABLE.BUG_LAST_EDITED_DATE.eq(t.getBugLastEditedDate()),
					BUGTABLE.CASE_ID.eq(t.getCaseId()),
					BUGTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					BUGTABLE.BUG_SOURCE.eq(t.getBugSource()),
					BUGTABLE.BUG_ORIGIN.eq(t.getBugOrigin()),
					BUGTABLE.DELETED.eq(t.getDeleted())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Bug> bugs) {
		if (CollectionUtil.isEmpty(bugs)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, bugs, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(BUGTABLE).values(
					BUGTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					BUGTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					BUGTABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
					BUGTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
					BUGTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
					BUGTABLE.TO_STORY_ID.value(new JdbcNamedParameter("toStoryId")),
					BUGTABLE.TASK_ID.value(new JdbcNamedParameter("taskId")),
					BUGTABLE.TO_TASK_ID.value(new JdbcNamedParameter("toTaskId")),
					BUGTABLE.BUG_TITLE.value(new JdbcNamedParameter("bugTitle")),
					BUGTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
					BUGTABLE.BUG_KEYWORDS.value(new JdbcNamedParameter("bugKeywords")),
					BUGTABLE.BUG_SEVERITY.value(new JdbcNamedParameter("bugSeverity")),
					BUGTABLE.BUG_PRI.value(new JdbcNamedParameter("bugPri")),
					BUGTABLE.BUG_TYPE.value(new JdbcNamedParameter("bugType")),
					BUGTABLE.BUG_OS.value(new JdbcNamedParameter("bugOs")),
					BUGTABLE.BUG_BROWSER.value(new JdbcNamedParameter("bugBrowser")),
					BUGTABLE.BUG_HARDWARE.value(new JdbcNamedParameter("bugHardware")),
					BUGTABLE.BUG_STEPS.value(new JdbcNamedParameter("bugSteps")),
					BUGTABLE.BUG_STATUS.value(new JdbcNamedParameter("bugStatus")),
					BUGTABLE.BUG_CONFIRMED.value(new JdbcNamedParameter("bugConfirmed")),
					BUGTABLE.BUG_OPEN_COUNT.value(new JdbcNamedParameter("bugOpenCount")),
					BUGTABLE.BUG_MAILTO.value(new JdbcNamedParameter("bugMailto")),
					BUGTABLE.BUG_OPENED_BY.value(new JdbcNamedParameter("bugOpenedBy")),
					BUGTABLE.BUG_OPENED_DATE.value(new JdbcNamedParameter("bugOpenedDate")),
					BUGTABLE.BUG_OPENED_BUILD.value(new JdbcNamedParameter("bugOpenedBuild")),
					BUGTABLE.BUG_ASSIGNED_TO.value(new JdbcNamedParameter("bugAssignedTo")),
					BUGTABLE.BUG_ASSIGNED_DATE.value(new JdbcNamedParameter("bugAssignedDate")),
					BUGTABLE.BUG_RESOLVED_BY.value(new JdbcNamedParameter("bugResolvedBy")),
					BUGTABLE.BUG_RESOLUTION.value(new JdbcNamedParameter("bugResolution")),
					BUGTABLE.BUG_RESOLVED_BUILD.value(new JdbcNamedParameter("bugResolvedBuild")),
					BUGTABLE.BUG_RESOLVED_DATE.value(new JdbcNamedParameter("bugResolvedDate")),
					BUGTABLE.BUG_CLOSED_BY.value(new JdbcNamedParameter("bugClosedBy")),
					BUGTABLE.BUG_CLOSED_DATE.value(new JdbcNamedParameter("bugClosedDate")),
					BUGTABLE.BUG_DUPLICATE_BUG.value(new JdbcNamedParameter("bugDuplicateBug")),
					BUGTABLE.BUG_LINK_BUG.value(new JdbcNamedParameter("bugLinkBug")),
					BUGTABLE.BUG_LAST_EDITED_BY.value(new JdbcNamedParameter("bugLastEditedBy")),
					BUGTABLE.BUG_LAST_EDITED_DATE.value(new JdbcNamedParameter("bugLastEditedDate")),
					BUGTABLE.CASE_ID.value(new JdbcNamedParameter("caseId")),
					BUGTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					BUGTABLE.BUG_SOURCE.value(new JdbcNamedParameter("bugSource")),
					BUGTABLE.BUG_ORIGIN.value(new JdbcNamedParameter("bugOrigin")),
					BUGTABLE.DELETED.value(new JdbcNamedParameter("deleted")));
			}
		});
	}

	public int[] batchInsert(List<Bug> bugs){
			return batchInsert(true ,bugs);
	}

	public int[] batchUpdate(List<Bug> bugs) {
		if (CollectionUtil.isEmpty(bugs)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(bugs, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(BUGTABLE).set(
					BUGTABLE.COMPANY_ID.value(new JdbcNamedParameter("companyId")),
					BUGTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					BUGTABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
					BUGTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
					BUGTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
					BUGTABLE.TO_STORY_ID.value(new JdbcNamedParameter("toStoryId")),
					BUGTABLE.TASK_ID.value(new JdbcNamedParameter("taskId")),
					BUGTABLE.TO_TASK_ID.value(new JdbcNamedParameter("toTaskId")),
					BUGTABLE.BUG_TITLE.value(new JdbcNamedParameter("bugTitle")),
					BUGTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
					BUGTABLE.BUG_KEYWORDS.value(new JdbcNamedParameter("bugKeywords")),
					BUGTABLE.BUG_SEVERITY.value(new JdbcNamedParameter("bugSeverity")),
					BUGTABLE.BUG_PRI.value(new JdbcNamedParameter("bugPri")),
					BUGTABLE.BUG_TYPE.value(new JdbcNamedParameter("bugType")),
					BUGTABLE.BUG_OS.value(new JdbcNamedParameter("bugOs")),
					BUGTABLE.BUG_BROWSER.value(new JdbcNamedParameter("bugBrowser")),
					BUGTABLE.BUG_HARDWARE.value(new JdbcNamedParameter("bugHardware")),
					BUGTABLE.BUG_STEPS.value(new JdbcNamedParameter("bugSteps")),
					BUGTABLE.BUG_STATUS.value(new JdbcNamedParameter("bugStatus")),
					BUGTABLE.BUG_CONFIRMED.value(new JdbcNamedParameter("bugConfirmed")),
					BUGTABLE.BUG_OPEN_COUNT.value(new JdbcNamedParameter("bugOpenCount")),
					BUGTABLE.BUG_MAILTO.value(new JdbcNamedParameter("bugMailto")),
					BUGTABLE.BUG_OPENED_BY.value(new JdbcNamedParameter("bugOpenedBy")),
					BUGTABLE.BUG_OPENED_DATE.value(new JdbcNamedParameter("bugOpenedDate")),
					BUGTABLE.BUG_OPENED_BUILD.value(new JdbcNamedParameter("bugOpenedBuild")),
					BUGTABLE.BUG_ASSIGNED_TO.value(new JdbcNamedParameter("bugAssignedTo")),
					BUGTABLE.BUG_ASSIGNED_DATE.value(new JdbcNamedParameter("bugAssignedDate")),
					BUGTABLE.BUG_RESOLVED_BY.value(new JdbcNamedParameter("bugResolvedBy")),
					BUGTABLE.BUG_RESOLUTION.value(new JdbcNamedParameter("bugResolution")),
					BUGTABLE.BUG_RESOLVED_BUILD.value(new JdbcNamedParameter("bugResolvedBuild")),
					BUGTABLE.BUG_RESOLVED_DATE.value(new JdbcNamedParameter("bugResolvedDate")),
					BUGTABLE.BUG_CLOSED_BY.value(new JdbcNamedParameter("bugClosedBy")),
					BUGTABLE.BUG_CLOSED_DATE.value(new JdbcNamedParameter("bugClosedDate")),
					BUGTABLE.BUG_DUPLICATE_BUG.value(new JdbcNamedParameter("bugDuplicateBug")),
					BUGTABLE.BUG_LINK_BUG.value(new JdbcNamedParameter("bugLinkBug")),
					BUGTABLE.BUG_LAST_EDITED_BY.value(new JdbcNamedParameter("bugLastEditedBy")),
					BUGTABLE.BUG_LAST_EDITED_DATE.value(new JdbcNamedParameter("bugLastEditedDate")),
					BUGTABLE.CASE_ID.value(new JdbcNamedParameter("caseId")),
					BUGTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					BUGTABLE.BUG_SOURCE.value(new JdbcNamedParameter("bugSource")),
					BUGTABLE.BUG_ORIGIN.value(new JdbcNamedParameter("bugOrigin")),
					BUGTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
				BUGTABLE.BUG_ID.eq(new JdbcNamedParameter("bugId")));
			}
		});
	}

	public int[] batchDelete(List<Bug> bugs) {
		if (CollectionUtil.isEmpty(bugs)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(bugs, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(BUGTABLE).where(and(
				BUGTABLE.BUG_ID.eq(new JdbcNamedParameter("bugId")),
				BUGTABLE.COMPANY_ID.eq(new JdbcNamedParameter("companyId")),
				BUGTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
				BUGTABLE.STORY_ID.eq(new JdbcNamedParameter("storyId")),
				BUGTABLE.STORY_VERSION.eq(new JdbcNamedParameter("storyVersion")),
				BUGTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectId")),
				BUGTABLE.TO_STORY_ID.eq(new JdbcNamedParameter("toStoryId")),
				BUGTABLE.TASK_ID.eq(new JdbcNamedParameter("taskId")),
				BUGTABLE.TO_TASK_ID.eq(new JdbcNamedParameter("toTaskId")),
				BUGTABLE.BUG_TITLE.eq(new JdbcNamedParameter("bugTitle")),
				BUGTABLE.MODULE_ID.eq(new JdbcNamedParameter("moduleId")),
				BUGTABLE.BUG_KEYWORDS.eq(new JdbcNamedParameter("bugKeywords")),
				BUGTABLE.BUG_SEVERITY.eq(new JdbcNamedParameter("bugSeverity")),
				BUGTABLE.BUG_PRI.eq(new JdbcNamedParameter("bugPri")),
				BUGTABLE.BUG_TYPE.eq(new JdbcNamedParameter("bugType")),
				BUGTABLE.BUG_OS.eq(new JdbcNamedParameter("bugOs")),
				BUGTABLE.BUG_BROWSER.eq(new JdbcNamedParameter("bugBrowser")),
				BUGTABLE.BUG_HARDWARE.eq(new JdbcNamedParameter("bugHardware")),
				BUGTABLE.BUG_STEPS.eq(new JdbcNamedParameter("bugSteps")),
				BUGTABLE.BUG_STATUS.eq(new JdbcNamedParameter("bugStatus")),
				BUGTABLE.BUG_CONFIRMED.eq(new JdbcNamedParameter("bugConfirmed")),
				BUGTABLE.BUG_OPEN_COUNT.eq(new JdbcNamedParameter("bugOpenCount")),
				BUGTABLE.BUG_MAILTO.eq(new JdbcNamedParameter("bugMailto")),
				BUGTABLE.BUG_OPENED_BY.eq(new JdbcNamedParameter("bugOpenedBy")),
				BUGTABLE.BUG_OPENED_DATE.eq(new JdbcNamedParameter("bugOpenedDate")),
				BUGTABLE.BUG_OPENED_BUILD.eq(new JdbcNamedParameter("bugOpenedBuild")),
				BUGTABLE.BUG_ASSIGNED_TO.eq(new JdbcNamedParameter("bugAssignedTo")),
				BUGTABLE.BUG_ASSIGNED_DATE.eq(new JdbcNamedParameter("bugAssignedDate")),
				BUGTABLE.BUG_RESOLVED_BY.eq(new JdbcNamedParameter("bugResolvedBy")),
				BUGTABLE.BUG_RESOLUTION.eq(new JdbcNamedParameter("bugResolution")),
				BUGTABLE.BUG_RESOLVED_BUILD.eq(new JdbcNamedParameter("bugResolvedBuild")),
				BUGTABLE.BUG_RESOLVED_DATE.eq(new JdbcNamedParameter("bugResolvedDate")),
				BUGTABLE.BUG_CLOSED_BY.eq(new JdbcNamedParameter("bugClosedBy")),
				BUGTABLE.BUG_CLOSED_DATE.eq(new JdbcNamedParameter("bugClosedDate")),
				BUGTABLE.BUG_DUPLICATE_BUG.eq(new JdbcNamedParameter("bugDuplicateBug")),
				BUGTABLE.BUG_LINK_BUG.eq(new JdbcNamedParameter("bugLinkBug")),
				BUGTABLE.BUG_LAST_EDITED_BY.eq(new JdbcNamedParameter("bugLastEditedBy")),
				BUGTABLE.BUG_LAST_EDITED_DATE.eq(new JdbcNamedParameter("bugLastEditedDate")),
				BUGTABLE.CASE_ID.eq(new JdbcNamedParameter("caseId")),
				BUGTABLE.CASE_VERSION.eq(new JdbcNamedParameter("caseVersion")),
				BUGTABLE.BUG_SOURCE.eq(new JdbcNamedParameter("bugSource")),
				BUGTABLE.BUG_ORIGIN.eq(new JdbcNamedParameter("bugOrigin")),
				BUGTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
			}
		});
	}

}
