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
import static org.tinygroup.sdpm.quality.dao.constant.BugTable.*;
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
import org.tinygroup.sdpm.quality.dao.pojo.Bug;
import org.tinygroup.sdpm.common.log.annotation.LogClass;
import org.tinygroup.sdpm.common.log.annotation.LogMethod;
import org.tinygroup.sdpm.quality.dao.BugDao;
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
@LogClass("bug")
public class BugDaoImpl extends TinyDslDaoSupport implements BugDao {
	
	@LogMethod("add")
	public Bug add(Bug bug) {
		return getDslTemplate().insertAndReturnKey(bug, new InsertGenerateCallback<Bug>() {
			public Insert generate(Bug t) {
				Insert insert = insertInto(BUGTABLE).values(
					BUGTABLE.BUG_ID.value(t.getBugId()),
					BUGTABLE.PRODUCT_ID.value(t.getProductId()),
					BUGTABLE.MODULE_ID.value(t.getModuleId()),
					BUGTABLE.PROJECT_ID.value(t.getProjectId()),
					BUGTABLE.PLAN_ID.value(t.getPlanId()),
					BUGTABLE.STORY_ID.value(t.getStoryId()),
					BUGTABLE.STORY_VERSION.value(t.getStoryVersion()),
					BUGTABLE.TASK_ID.value(t.getTaskId()),
					BUGTABLE.TOTASK_ID.value(t.getToTaskId()),
					BUGTABLE.TOSTORY_ID.value(t.getToStoryId()),
					BUGTABLE.BUG_TITLE.value(t.getBugTitle()),
					BUGTABLE.BUG_KEYWORDS.value(t.getBugKeywords()),
					BUGTABLE.BUG_SEVERITY.value(t.getBugSeverity()),
					BUGTABLE.PRIORITY.value(t.getPriority()),
					BUGTABLE.BUG_TYPE.value(t.getBugType()),
					BUGTABLE.OPERATINGSYSTEM.value(t.getOperatingSystem()),
					BUGTABLE.BROWSER.value(t.getBrowser()),
					BUGTABLE.HARDWARE.value(t.getHardware()),
					BUGTABLE.BUG_FOUND.value(t.getBugFound()),
					BUGTABLE.BUG_STEPS.value(t.getBugSteps()),
					BUGTABLE.BUG_STATUS.value(t.getBugStatus()),
					BUGTABLE.BUG_CONFIRMED.value(t.getBugConfirmed()),
					BUGTABLE.BUG_ACTIVATEDCOUNT.value(t.getBugActivatedCount()),
					BUGTABLE.BUG_MAILTO.value(t.getBugMailto()),
					BUGTABLE.BUG_OPENEDBY.value(t.getBugOpenedBy()),
					BUGTABLE.BUG_OPENEDDATE.value(t.getBugOpenedDate()),
					BUGTABLE.BUG_OPENEDBUILD.value(t.getBugOpenedBuild()),
					BUGTABLE.BUG_ASSIGNEDTO.value(t.getBugAssignedTo()),
					BUGTABLE.BUG_ASSIGNEDDATE.value(t.getBugAssignedDate()),
					BUGTABLE.BUG_RESOLVEDBY.value(t.getBugResolvedBy()),
					BUGTABLE.BUG_RESOLUTION.value(t.getBugResolution()),
					BUGTABLE.BUG_RESOLVEDBUILD.value(t.getBugResolvedBuild()),
					BUGTABLE.BUG_RESOLVEDDATE.value(t.getBugResolvedDate()),
					BUGTABLE.BUG_CLOSEDBY.value(t.getBugClosedBy()),
					BUGTABLE.BUG_CLOSEDDATE.value(t.getBugClosedDate()),
					BUGTABLE.BUG_DUPLICATEBUG.value(t.getBugDuplicateBug()),
					BUGTABLE.LINK_BUG.value(t.getLinkBug()),
					BUGTABLE.LINK_CASE.value(t.getLinkCase()),
					BUGTABLE.CASE_VERSION.value(t.getCaseVersion()),
					BUGTABLE.BUG_RESULT.value(t.getBugResult()),
					BUGTABLE.BUG_REPO.value(t.getBugRepo()),
					BUGTABLE.BUG_ENTRY.value(t.getBugEntry()),
					BUGTABLE.BUG_LINES.value(t.getBugLines()),
					BUGTABLE.BUG_V1.value(t.getBugV1()),
					BUGTABLE.BUG_V2.value(t.getBugV2()),
					BUGTABLE.BUG_REPOTYPE.value(t.getBugRepoType()),
					BUGTABLE.TESTTASK.value(t.getTesttask()),
					BUGTABLE.BUG_LASTEDITEDBY.value(t.getBugLastEditedBy()),
					BUGTABLE.BUG_LASTEDITEDDATE.value(t.getBugLastEditedDate()),
					BUGTABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}
	@LogMethod("edit")
	public int edit(Bug bug) {
		if(bug == null || bug.getBugId() == null){
			return 0;
		}
		return getDslTemplate().update(bug, new UpdateGenerateCallback<Bug>() {
			public Update generate(Bug t) {
				Update update = update(BUGTABLE).set(
					BUGTABLE.PRODUCT_ID.value(t.getProductId()),
					BUGTABLE.MODULE_ID.value(t.getModuleId()),
					BUGTABLE.PROJECT_ID.value(t.getProjectId()),
					BUGTABLE.PLAN_ID.value(t.getPlanId()),
					BUGTABLE.STORY_ID.value(t.getStoryId()),
					BUGTABLE.STORY_VERSION.value(t.getStoryVersion()),
					BUGTABLE.TASK_ID.value(t.getTaskId()),
					BUGTABLE.TOTASK_ID.value(t.getToTaskId()),
					BUGTABLE.TOSTORY_ID.value(t.getToStoryId()),
					BUGTABLE.BUG_TITLE.value(t.getBugTitle()),
					BUGTABLE.BUG_KEYWORDS.value(t.getBugKeywords()),
					BUGTABLE.BUG_SEVERITY.value(t.getBugSeverity()),
					BUGTABLE.PRIORITY.value(t.getPriority()),
					BUGTABLE.BUG_TYPE.value(t.getBugType()),
					BUGTABLE.OPERATINGSYSTEM.value(t.getOperatingSystem()),
					BUGTABLE.BROWSER.value(t.getBrowser()),
					BUGTABLE.HARDWARE.value(t.getHardware()),
					BUGTABLE.BUG_FOUND.value(t.getBugFound()),
					BUGTABLE.BUG_STEPS.value(t.getBugSteps()),
					BUGTABLE.BUG_STATUS.value(t.getBugStatus()),
					BUGTABLE.BUG_CONFIRMED.value(t.getBugConfirmed()),
					BUGTABLE.BUG_ACTIVATEDCOUNT.value(t.getBugActivatedCount()),
					BUGTABLE.BUG_MAILTO.value(t.getBugMailto()),
					BUGTABLE.BUG_OPENEDBY.value(t.getBugOpenedBy()),
					BUGTABLE.BUG_OPENEDDATE.value(t.getBugOpenedDate()),
					BUGTABLE.BUG_OPENEDBUILD.value(t.getBugOpenedBuild()),
					BUGTABLE.BUG_ASSIGNEDTO.value(t.getBugAssignedTo()),
					BUGTABLE.BUG_ASSIGNEDDATE.value(t.getBugAssignedDate()),
					BUGTABLE.BUG_RESOLVEDBY.value(t.getBugResolvedBy()),
					BUGTABLE.BUG_RESOLUTION.value(t.getBugResolution()),
					BUGTABLE.BUG_RESOLVEDBUILD.value(t.getBugResolvedBuild()),
					BUGTABLE.BUG_RESOLVEDDATE.value(t.getBugResolvedDate()),
					BUGTABLE.BUG_CLOSEDBY.value(t.getBugClosedBy()),
					BUGTABLE.BUG_CLOSEDDATE.value(t.getBugClosedDate()),
					BUGTABLE.BUG_DUPLICATEBUG.value(t.getBugDuplicateBug()),
					BUGTABLE.LINK_BUG.value(t.getLinkBug()),
					BUGTABLE.LINK_CASE.value(t.getLinkCase()),
					BUGTABLE.CASE_VERSION.value(t.getCaseVersion()),
					BUGTABLE.BUG_RESULT.value(t.getBugResult()),
					BUGTABLE.BUG_REPO.value(t.getBugRepo()),
					BUGTABLE.BUG_ENTRY.value(t.getBugEntry()),
					BUGTABLE.BUG_LINES.value(t.getBugLines()),
					BUGTABLE.BUG_V1.value(t.getBugV1()),
					BUGTABLE.BUG_V2.value(t.getBugV2()),
					BUGTABLE.BUG_REPOTYPE.value(t.getBugRepoType()),
					BUGTABLE.TESTTASK.value(t.getTesttask()),
					BUGTABLE.BUG_LASTEDITEDBY.value(t.getBugLastEditedBy()),
					BUGTABLE.BUG_LASTEDITEDDATE.value(t.getBugLastEditedDate()),
					BUGTABLE.DELETED.value(t.getDeleted())).where(
					BUGTABLE.BUG_ID.eq(t.getBugId()));
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
				return delete(BUGTABLE).where(BUGTABLE.BUG_ID.eq(pk));
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
				return delete(BUGTABLE).where(BUGTABLE.BUG_ID.in(t));
		}
		},pks);
	}

	public Bug getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Bug.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(BUGTABLE).where(BUGTABLE.BUG_ID.eq(t));
			}
		});
	}
	
	public List<Bug> query(Bug bug ,final OrderBy... orderBies) {
		if(bug==null){
			bug=new Bug();
		}
		return getDslTemplate().query(bug, new SelectGenerateCallback<Bug>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Bug t) {
				Select select = selectFrom(BUGTABLE).where(
				and(
					BUGTABLE.PRODUCT_ID.eq(t.getProductId()),
					BUGTABLE.MODULE_ID.eq(t.getModuleId()),
					BUGTABLE.PROJECT_ID.eq(t.getProjectId()),
					BUGTABLE.PLAN_ID.eq(t.getPlanId()),
					BUGTABLE.STORY_ID.eq(t.getStoryId()),
					BUGTABLE.STORY_VERSION.eq(t.getStoryVersion()),
					BUGTABLE.TASK_ID.eq(t.getTaskId()),
					BUGTABLE.TOTASK_ID.eq(t.getToTaskId()),
					BUGTABLE.TOSTORY_ID.eq(t.getToStoryId()),
					BUGTABLE.BUG_TITLE.eq(t.getBugTitle()),
					BUGTABLE.BUG_KEYWORDS.eq(t.getBugKeywords()),
					BUGTABLE.BUG_SEVERITY.eq(t.getBugSeverity()),
					BUGTABLE.PRIORITY.eq(t.getPriority()),
					BUGTABLE.BUG_TYPE.eq(t.getBugType()),
					BUGTABLE.OPERATINGSYSTEM.eq(t.getOperatingSystem()),
					BUGTABLE.BROWSER.eq(t.getBrowser()),
					BUGTABLE.HARDWARE.eq(t.getHardware()),
					BUGTABLE.BUG_FOUND.eq(t.getBugFound()),
					BUGTABLE.BUG_STEPS.eq(t.getBugSteps()),
					BUGTABLE.BUG_STATUS.eq(t.getBugStatus()),
					BUGTABLE.BUG_CONFIRMED.eq(t.getBugConfirmed()),
					BUGTABLE.BUG_ACTIVATEDCOUNT.eq(t.getBugActivatedCount()),
					BUGTABLE.BUG_MAILTO.eq(t.getBugMailto()),
					BUGTABLE.BUG_OPENEDBY.eq(t.getBugOpenedBy()),
					BUGTABLE.BUG_OPENEDDATE.eq(t.getBugOpenedDate()),
					BUGTABLE.BUG_OPENEDBUILD.eq(t.getBugOpenedBuild()),
					BUGTABLE.BUG_ASSIGNEDTO.eq(t.getBugAssignedTo()),
					BUGTABLE.BUG_ASSIGNEDDATE.eq(t.getBugAssignedDate()),
					BUGTABLE.BUG_RESOLVEDBY.eq(t.getBugResolvedBy()),
					BUGTABLE.BUG_RESOLUTION.eq(t.getBugResolution()),
					BUGTABLE.BUG_RESOLVEDBUILD.eq(t.getBugResolvedBuild()),
					BUGTABLE.BUG_RESOLVEDDATE.eq(t.getBugResolvedDate()),
					BUGTABLE.BUG_CLOSEDBY.eq(t.getBugClosedBy()),
					BUGTABLE.BUG_CLOSEDDATE.eq(t.getBugClosedDate()),
					BUGTABLE.BUG_DUPLICATEBUG.eq(t.getBugDuplicateBug()),
					BUGTABLE.LINK_BUG.eq(t.getLinkBug()),
					BUGTABLE.LINK_CASE.eq(t.getLinkCase()),
					BUGTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					BUGTABLE.BUG_RESULT.eq(t.getBugResult()),
					BUGTABLE.BUG_REPO.eq(t.getBugRepo()),
					BUGTABLE.BUG_ENTRY.eq(t.getBugEntry()),
					BUGTABLE.BUG_LINES.eq(t.getBugLines()),
					BUGTABLE.BUG_V1.eq(t.getBugV1()),
					BUGTABLE.BUG_V2.eq(t.getBugV2()),
					BUGTABLE.BUG_REPOTYPE.eq(t.getBugRepoType()),
					BUGTABLE.TESTTASK.eq(t.getTesttask()),
					BUGTABLE.BUG_LASTEDITEDBY.eq(t.getBugLastEditedBy()),
					BUGTABLE.BUG_LASTEDITEDDATE.eq(t.getBugLastEditedDate()),
					BUGTABLE.DELETED.eq(t.getDeleted())));
		return addOrderByElements(select, orderBies);
			}
		});
	}

	public Pager<Bug> queryPager(int start,int limit ,Bug bug ,final OrderBy... orderBies) {
		if(bug==null){
			bug=new Bug();
		}
		return getDslTemplate().queryPager(start, limit, bug, false, new SelectGenerateCallback<Bug>() {

			public Select generate(Bug t) {
				Select select = MysqlSelect.selectFrom(BUGTABLE).where(
				and(
					BUGTABLE.PRODUCT_ID.eq(t.getProductId()),
					BUGTABLE.MODULE_ID.eq(t.getModuleId()),
					BUGTABLE.PROJECT_ID.eq(t.getProjectId()),
					BUGTABLE.PLAN_ID.eq(t.getPlanId()),
					BUGTABLE.STORY_ID.eq(t.getStoryId()),
					BUGTABLE.STORY_VERSION.eq(t.getStoryVersion()),
					BUGTABLE.TASK_ID.eq(t.getTaskId()),
					BUGTABLE.TOTASK_ID.eq(t.getToTaskId()),
					BUGTABLE.TOSTORY_ID.eq(t.getToStoryId()),
					BUGTABLE.BUG_TITLE.eq(t.getBugTitle()),
					BUGTABLE.BUG_KEYWORDS.eq(t.getBugKeywords()),
					BUGTABLE.BUG_SEVERITY.eq(t.getBugSeverity()),
					BUGTABLE.PRIORITY.eq(t.getPriority()),
					BUGTABLE.BUG_TYPE.eq(t.getBugType()),
					BUGTABLE.OPERATINGSYSTEM.eq(t.getOperatingSystem()),
					BUGTABLE.BROWSER.eq(t.getBrowser()),
					BUGTABLE.HARDWARE.eq(t.getHardware()),
					BUGTABLE.BUG_FOUND.eq(t.getBugFound()),
					BUGTABLE.BUG_STEPS.eq(t.getBugSteps()),
					BUGTABLE.BUG_STATUS.eq(t.getBugStatus()),
					BUGTABLE.BUG_CONFIRMED.eq(t.getBugConfirmed()),
					BUGTABLE.BUG_ACTIVATEDCOUNT.eq(t.getBugActivatedCount()),
					BUGTABLE.BUG_MAILTO.eq(t.getBugMailto()),
					BUGTABLE.BUG_OPENEDBY.eq(t.getBugOpenedBy()),
					BUGTABLE.BUG_OPENEDDATE.eq(t.getBugOpenedDate()),
					BUGTABLE.BUG_OPENEDBUILD.eq(t.getBugOpenedBuild()),
					BUGTABLE.BUG_ASSIGNEDTO.eq(t.getBugAssignedTo()),
					BUGTABLE.BUG_ASSIGNEDDATE.eq(t.getBugAssignedDate()),
					BUGTABLE.BUG_RESOLVEDBY.eq(t.getBugResolvedBy()),
					BUGTABLE.BUG_RESOLUTION.eq(t.getBugResolution()),
					BUGTABLE.BUG_RESOLVEDBUILD.eq(t.getBugResolvedBuild()),
					BUGTABLE.BUG_RESOLVEDDATE.eq(t.getBugResolvedDate()),
					BUGTABLE.BUG_CLOSEDBY.eq(t.getBugClosedBy()),
					BUGTABLE.BUG_CLOSEDDATE.eq(t.getBugClosedDate()),
					BUGTABLE.BUG_DUPLICATEBUG.eq(t.getBugDuplicateBug()),
					BUGTABLE.LINK_BUG.eq(t.getLinkBug()),
					BUGTABLE.LINK_CASE.eq(t.getLinkCase()),
					BUGTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					BUGTABLE.BUG_RESULT.eq(t.getBugResult()),
					BUGTABLE.BUG_REPO.eq(t.getBugRepo()),
					BUGTABLE.BUG_ENTRY.eq(t.getBugEntry()),
					BUGTABLE.BUG_LINES.eq(t.getBugLines()),
					BUGTABLE.BUG_V1.eq(t.getBugV1()),
					BUGTABLE.BUG_V2.eq(t.getBugV2()),
					BUGTABLE.BUG_REPOTYPE.eq(t.getBugRepoType()),
					BUGTABLE.TESTTASK.eq(t.getTesttask()),
					BUGTABLE.BUG_LASTEDITEDBY.eq(t.getBugLastEditedBy()),
					BUGTABLE.BUG_LASTEDITEDDATE.eq(t.getBugLastEditedDate()),
					BUGTABLE.DELETED.eq(t.getDeleted())));
		return addOrderByElements(select, orderBies);
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
					BUGTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					BUGTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
					BUGTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
					BUGTABLE.PLAN_ID.value(new JdbcNamedParameter("planId")),
					BUGTABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
					BUGTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
					BUGTABLE.TASK_ID.value(new JdbcNamedParameter("taskId")),
					BUGTABLE.TOTASK_ID.value(new JdbcNamedParameter("toTaskId")),
					BUGTABLE.TOSTORY_ID.value(new JdbcNamedParameter("toStoryId")),
					BUGTABLE.BUG_TITLE.value(new JdbcNamedParameter("bugTitle")),
					BUGTABLE.BUG_KEYWORDS.value(new JdbcNamedParameter("bugKeywords")),
					BUGTABLE.BUG_SEVERITY.value(new JdbcNamedParameter("bugSeverity")),
					BUGTABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
					BUGTABLE.BUG_TYPE.value(new JdbcNamedParameter("bugType")),
					BUGTABLE.OPERATINGSYSTEM.value(new JdbcNamedParameter("operatingSystem")),
					BUGTABLE.BROWSER.value(new JdbcNamedParameter("browser")),
					BUGTABLE.HARDWARE.value(new JdbcNamedParameter("hardware")),
					BUGTABLE.BUG_FOUND.value(new JdbcNamedParameter("bugFound")),
					BUGTABLE.BUG_STEPS.value(new JdbcNamedParameter("bugSteps")),
					BUGTABLE.BUG_STATUS.value(new JdbcNamedParameter("bugStatus")),
					BUGTABLE.BUG_CONFIRMED.value(new JdbcNamedParameter("bugConfirmed")),
					BUGTABLE.BUG_ACTIVATEDCOUNT.value(new JdbcNamedParameter("bugActivatedCount")),
					BUGTABLE.BUG_MAILTO.value(new JdbcNamedParameter("bugMailto")),
					BUGTABLE.BUG_OPENEDBY.value(new JdbcNamedParameter("bugOpenedBy")),
					BUGTABLE.BUG_OPENEDDATE.value(new JdbcNamedParameter("bugOpenedDate")),
					BUGTABLE.BUG_OPENEDBUILD.value(new JdbcNamedParameter("bugOpenedBuild")),
					BUGTABLE.BUG_ASSIGNEDTO.value(new JdbcNamedParameter("bugAssignedTo")),
					BUGTABLE.BUG_ASSIGNEDDATE.value(new JdbcNamedParameter("bugAssignedDate")),
					BUGTABLE.BUG_RESOLVEDBY.value(new JdbcNamedParameter("bugResolvedBy")),
					BUGTABLE.BUG_RESOLUTION.value(new JdbcNamedParameter("bugResolution")),
					BUGTABLE.BUG_RESOLVEDBUILD.value(new JdbcNamedParameter("bugResolvedBuild")),
					BUGTABLE.BUG_RESOLVEDDATE.value(new JdbcNamedParameter("bugResolvedDate")),
					BUGTABLE.BUG_CLOSEDBY.value(new JdbcNamedParameter("bugClosedBy")),
					BUGTABLE.BUG_CLOSEDDATE.value(new JdbcNamedParameter("bugClosedDate")),
					BUGTABLE.BUG_DUPLICATEBUG.value(new JdbcNamedParameter("bugDuplicateBug")),
					BUGTABLE.LINK_BUG.value(new JdbcNamedParameter("linkBug")),
					BUGTABLE.LINK_CASE.value(new JdbcNamedParameter("linkCase")),
					BUGTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					BUGTABLE.BUG_RESULT.value(new JdbcNamedParameter("bugResult")),
					BUGTABLE.BUG_REPO.value(new JdbcNamedParameter("bugRepo")),
					BUGTABLE.BUG_ENTRY.value(new JdbcNamedParameter("bugEntry")),
					BUGTABLE.BUG_LINES.value(new JdbcNamedParameter("bugLines")),
					BUGTABLE.BUG_V1.value(new JdbcNamedParameter("bugV1")),
					BUGTABLE.BUG_V2.value(new JdbcNamedParameter("bugV2")),
					BUGTABLE.BUG_REPOTYPE.value(new JdbcNamedParameter("bugRepoType")),
					BUGTABLE.TESTTASK.value(new JdbcNamedParameter("testtask")),
					BUGTABLE.BUG_LASTEDITEDBY.value(new JdbcNamedParameter("bugLastEditedBy")),
					BUGTABLE.BUG_LASTEDITEDDATE.value(new JdbcNamedParameter("bugLastEditedDate")),
					BUGTABLE.DELETED.value(new JdbcNamedParameter("deleted")));
			}
		});
	}
	
	public int[] batchInsert(List<Bug> bugs){
			return batchInsert(true ,bugs);
	}
	@LogMethod("batchUpdate")
	public int[] batchUpdate(List<Bug> bugs) {
		if (CollectionUtil.isEmpty(bugs)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(bugs, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(BUGTABLE).set(
					BUGTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productId")),
					BUGTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleId")),
					BUGTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectId")),
					BUGTABLE.PLAN_ID.value(new JdbcNamedParameter("planId")),
					BUGTABLE.STORY_ID.value(new JdbcNamedParameter("storyId")),
					BUGTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
					BUGTABLE.TASK_ID.value(new JdbcNamedParameter("taskId")),
					BUGTABLE.TOTASK_ID.value(new JdbcNamedParameter("toTaskId")),
					BUGTABLE.TOSTORY_ID.value(new JdbcNamedParameter("toStoryId")),
					BUGTABLE.BUG_TITLE.value(new JdbcNamedParameter("bugTitle")),
					BUGTABLE.BUG_KEYWORDS.value(new JdbcNamedParameter("bugKeywords")),
					BUGTABLE.BUG_SEVERITY.value(new JdbcNamedParameter("bugSeverity")),
					BUGTABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
					BUGTABLE.BUG_TYPE.value(new JdbcNamedParameter("bugType")),
					BUGTABLE.OPERATINGSYSTEM.value(new JdbcNamedParameter("operatingSystem")),
					BUGTABLE.BROWSER.value(new JdbcNamedParameter("browser")),
					BUGTABLE.HARDWARE.value(new JdbcNamedParameter("hardware")),
					BUGTABLE.BUG_FOUND.value(new JdbcNamedParameter("bugFound")),
					BUGTABLE.BUG_STEPS.value(new JdbcNamedParameter("bugSteps")),
					BUGTABLE.BUG_STATUS.value(new JdbcNamedParameter("bugStatus")),
					BUGTABLE.BUG_CONFIRMED.value(new JdbcNamedParameter("bugConfirmed")),
					BUGTABLE.BUG_ACTIVATEDCOUNT.value(new JdbcNamedParameter("bugActivatedCount")),
					BUGTABLE.BUG_MAILTO.value(new JdbcNamedParameter("bugMailto")),
					BUGTABLE.BUG_OPENEDBY.value(new JdbcNamedParameter("bugOpenedBy")),
					BUGTABLE.BUG_OPENEDDATE.value(new JdbcNamedParameter("bugOpenedDate")),
					BUGTABLE.BUG_OPENEDBUILD.value(new JdbcNamedParameter("bugOpenedBuild")),
					BUGTABLE.BUG_ASSIGNEDTO.value(new JdbcNamedParameter("bugAssignedTo")),
					BUGTABLE.BUG_ASSIGNEDDATE.value(new JdbcNamedParameter("bugAssignedDate")),
					BUGTABLE.BUG_RESOLVEDBY.value(new JdbcNamedParameter("bugResolvedBy")),
					BUGTABLE.BUG_RESOLUTION.value(new JdbcNamedParameter("bugResolution")),
					BUGTABLE.BUG_RESOLVEDBUILD.value(new JdbcNamedParameter("bugResolvedBuild")),
					BUGTABLE.BUG_RESOLVEDDATE.value(new JdbcNamedParameter("bugResolvedDate")),
					BUGTABLE.BUG_CLOSEDBY.value(new JdbcNamedParameter("bugClosedBy")),
					BUGTABLE.BUG_CLOSEDDATE.value(new JdbcNamedParameter("bugClosedDate")),
					BUGTABLE.BUG_DUPLICATEBUG.value(new JdbcNamedParameter("bugDuplicateBug")),
					BUGTABLE.LINK_BUG.value(new JdbcNamedParameter("linkBug")),
					BUGTABLE.LINK_CASE.value(new JdbcNamedParameter("linkCase")),
					BUGTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					BUGTABLE.BUG_RESULT.value(new JdbcNamedParameter("bugResult")),
					BUGTABLE.BUG_REPO.value(new JdbcNamedParameter("bugRepo")),
					BUGTABLE.BUG_ENTRY.value(new JdbcNamedParameter("bugEntry")),
					BUGTABLE.BUG_LINES.value(new JdbcNamedParameter("bugLines")),
					BUGTABLE.BUG_V1.value(new JdbcNamedParameter("bugV1")),
					BUGTABLE.BUG_V2.value(new JdbcNamedParameter("bugV2")),
					BUGTABLE.BUG_REPOTYPE.value(new JdbcNamedParameter("bugRepoType")),
					BUGTABLE.TESTTASK.value(new JdbcNamedParameter("testtask")),
					BUGTABLE.BUG_LASTEDITEDBY.value(new JdbcNamedParameter("bugLastEditedBy")),
					BUGTABLE.BUG_LASTEDITEDDATE.value(new JdbcNamedParameter("bugLastEditedDate")),
					BUGTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
				BUGTABLE.BUG_ID.eq(new JdbcNamedParameter("bugId")));
			}
		});
	}
	@LogMethod("batchDelete")
	public int[] batchDelete(List<Bug> bugs) {
		if (CollectionUtil.isEmpty(bugs)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(bugs, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(BUGTABLE).where(and(
				BUGTABLE.BUG_ID.eq(new JdbcNamedParameter("bugId")),
				BUGTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productId")),
				BUGTABLE.MODULE_ID.eq(new JdbcNamedParameter("moduleId")),
				BUGTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectId")),
				BUGTABLE.PLAN_ID.eq(new JdbcNamedParameter("planId")),
				BUGTABLE.STORY_ID.eq(new JdbcNamedParameter("storyId")),
				BUGTABLE.STORY_VERSION.eq(new JdbcNamedParameter("storyVersion")),
				BUGTABLE.TASK_ID.eq(new JdbcNamedParameter("taskId")),
				BUGTABLE.TOTASK_ID.eq(new JdbcNamedParameter("toTaskId")),
				BUGTABLE.TOSTORY_ID.eq(new JdbcNamedParameter("toStoryId")),
				BUGTABLE.BUG_TITLE.eq(new JdbcNamedParameter("bugTitle")),
				BUGTABLE.BUG_KEYWORDS.eq(new JdbcNamedParameter("bugKeywords")),
				BUGTABLE.BUG_SEVERITY.eq(new JdbcNamedParameter("bugSeverity")),
				BUGTABLE.PRIORITY.eq(new JdbcNamedParameter("priority")),
				BUGTABLE.BUG_TYPE.eq(new JdbcNamedParameter("bugType")),
				BUGTABLE.OPERATINGSYSTEM.eq(new JdbcNamedParameter("operatingSystem")),
				BUGTABLE.BROWSER.eq(new JdbcNamedParameter("browser")),
				BUGTABLE.HARDWARE.eq(new JdbcNamedParameter("hardware")),
				BUGTABLE.BUG_FOUND.eq(new JdbcNamedParameter("bugFound")),
				BUGTABLE.BUG_STEPS.eq(new JdbcNamedParameter("bugSteps")),
				BUGTABLE.BUG_STATUS.eq(new JdbcNamedParameter("bugStatus")),
				BUGTABLE.BUG_CONFIRMED.eq(new JdbcNamedParameter("bugConfirmed")),
				BUGTABLE.BUG_ACTIVATEDCOUNT.eq(new JdbcNamedParameter("bugActivatedCount")),
				BUGTABLE.BUG_MAILTO.eq(new JdbcNamedParameter("bugMailto")),
				BUGTABLE.BUG_OPENEDBY.eq(new JdbcNamedParameter("bugOpenedBy")),
				BUGTABLE.BUG_OPENEDDATE.eq(new JdbcNamedParameter("bugOpenedDate")),
				BUGTABLE.BUG_OPENEDBUILD.eq(new JdbcNamedParameter("bugOpenedBuild")),
				BUGTABLE.BUG_ASSIGNEDTO.eq(new JdbcNamedParameter("bugAssignedTo")),
				BUGTABLE.BUG_ASSIGNEDDATE.eq(new JdbcNamedParameter("bugAssignedDate")),
				BUGTABLE.BUG_RESOLVEDBY.eq(new JdbcNamedParameter("bugResolvedBy")),
				BUGTABLE.BUG_RESOLUTION.eq(new JdbcNamedParameter("bugResolution")),
				BUGTABLE.BUG_RESOLVEDBUILD.eq(new JdbcNamedParameter("bugResolvedBuild")),
				BUGTABLE.BUG_RESOLVEDDATE.eq(new JdbcNamedParameter("bugResolvedDate")),
				BUGTABLE.BUG_CLOSEDBY.eq(new JdbcNamedParameter("bugClosedBy")),
				BUGTABLE.BUG_CLOSEDDATE.eq(new JdbcNamedParameter("bugClosedDate")),
				BUGTABLE.BUG_DUPLICATEBUG.eq(new JdbcNamedParameter("bugDuplicateBug")),
				BUGTABLE.LINK_BUG.eq(new JdbcNamedParameter("linkBug")),
				BUGTABLE.LINK_CASE.eq(new JdbcNamedParameter("linkCase")),
				BUGTABLE.CASE_VERSION.eq(new JdbcNamedParameter("caseVersion")),
				BUGTABLE.BUG_RESULT.eq(new JdbcNamedParameter("bugResult")),
				BUGTABLE.BUG_REPO.eq(new JdbcNamedParameter("bugRepo")),
				BUGTABLE.BUG_ENTRY.eq(new JdbcNamedParameter("bugEntry")),
				BUGTABLE.BUG_LINES.eq(new JdbcNamedParameter("bugLines")),
				BUGTABLE.BUG_V1.eq(new JdbcNamedParameter("bugV1")),
				BUGTABLE.BUG_V2.eq(new JdbcNamedParameter("bugV2")),
				BUGTABLE.BUG_REPOTYPE.eq(new JdbcNamedParameter("bugRepoType")),
				BUGTABLE.TESTTASK.eq(new JdbcNamedParameter("testtask")),
				BUGTABLE.BUG_LASTEDITEDBY.eq(new JdbcNamedParameter("bugLastEditedBy")),
				BUGTABLE.BUG_LASTEDITEDDATE.eq(new JdbcNamedParameter("bugLastEditedDate")),
				BUGTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
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
