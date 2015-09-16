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
import static sdpm.quality.dao.inter.org.tinygroup.alm.quality.constant.BugTable.*;
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
import sdpm.quality.dao.inter.org.tinygroup.alm.quality.pojo.Bug;
import sdpm.quality.dao.inter.org.tinygroup.alm.quality.BugDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class BugDaoImpl extends TinyDslDaoSupport implements BugDao {

	public Bug add(Bug bug) {
		return getDslTemplate().insertAndReturnKey(bug, new InsertGenerateCallback<Bug>() {
			public Insert generate(Bug t) {
				Insert insert = insertInto(BUGTABLE).values(
					BUGTABLE.BUG_ID.value(t.getBugID()),
					BUGTABLE.PRODUCT_ID.value(t.getProductID()),
					BUGTABLE.MODULE_ID.value(t.getModuleID()),
					BUGTABLE.PROJECT_ID.value(t.getProjectID()),
					BUGTABLE.PLAN_ID.value(t.getPlanID()),
					BUGTABLE.STORY_ID.value(t.getStoryID()),
					BUGTABLE.STORY_VERSION.value(t.getStoryVersion()),
					BUGTABLE.TASK_ID.value(t.getTaskID()),
					BUGTABLE.TOTASK_ID.value(t.getToTaskID()),
					BUGTABLE.TOSTORY_ID.value(t.getToStoryID()),
					BUGTABLE.BUG_TITLE.value(t.getBugTitle()),
					BUGTABLE.KEYWORDS.value(t.getKeywords()),
					BUGTABLE.BUG_SEVERITY.value(t.getBugSeverity()),
					BUGTABLE.PRIORITY.value(t.getPriority()),
					BUGTABLE.BUG_TYPE.value(t.getBugType()),
					BUGTABLE.OS.value(t.getOs()),
					BUGTABLE.BROWSER.value(t.getBrowser()),
					BUGTABLE.HARDWARE.value(t.getHardware()),
					BUGTABLE.BUG_FOUND.value(t.getBugFound()),
					BUGTABLE.BUG_STEPS.value(t.getBugSteps()),
					BUGTABLE.BUG_STATUS.value(t.getBugStatus()),
					BUGTABLE.CONFIRMED.value(t.getConfirmed()),
					BUGTABLE.BUG_ACTIVATEDCOUNT.value(t.getBugActivatedCount()),
					BUGTABLE.BUG_MAILTO.value(t.getBugMailto()),
					BUGTABLE.OPENEDBY.value(t.getOpenedBy()),
					BUGTABLE.OPENEDDATE.value(t.getOpenedDate()),
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
					BUGTABLE.BUG_LINK.value(t.getBugLink()),
					BUGTABLE.CASE_LINK.value(t.getCaseLink()),
					BUGTABLE.CASE_VERSION.value(t.getCaseVersion()),
					BUGTABLE.RESULT.value(t.getResult()),
					BUGTABLE.REPO.value(t.getRepo()),
					BUGTABLE.ENTRY.value(t.getEntry()),
					BUGTABLE.LINES.value(t.getLines()),
					BUGTABLE.V1.value(t.getV1()),
					BUGTABLE.V2.value(t.getV2()),
					BUGTABLE.REPOTYPE.value(t.getRepoType()),
					BUGTABLE.TESTTASK.value(t.getTesttask()),
					BUGTABLE.BUG_LASTEDITEDBY.value(t.getBugLastEditedBy()),
					BUGTABLE.BUG_LASTEDITEDDATE.value(t.getBugLastEditedDate()),
					BUGTABLE.DELETED.value(t.getDeleted()));
				return insert;
			}
		});
	}

	public int edit(Bug bug) {
		if(bug == null || bug.getBugID() == null){
			return 0;
		}
		return getDslTemplate().update(bug, new UpdateGenerateCallback<Bug>() {
			public Update generate(Bug t) {
				Update update = update(BUGTABLE).set(
					BUGTABLE.PRODUCT_ID.value(t.getProductID()),
					BUGTABLE.MODULE_ID.value(t.getModuleID()),
					BUGTABLE.PROJECT_ID.value(t.getProjectID()),
					BUGTABLE.PLAN_ID.value(t.getPlanID()),
					BUGTABLE.STORY_ID.value(t.getStoryID()),
					BUGTABLE.STORY_VERSION.value(t.getStoryVersion()),
					BUGTABLE.TASK_ID.value(t.getTaskID()),
					BUGTABLE.TOTASK_ID.value(t.getToTaskID()),
					BUGTABLE.TOSTORY_ID.value(t.getToStoryID()),
					BUGTABLE.BUG_TITLE.value(t.getBugTitle()),
					BUGTABLE.KEYWORDS.value(t.getKeywords()),
					BUGTABLE.BUG_SEVERITY.value(t.getBugSeverity()),
					BUGTABLE.PRIORITY.value(t.getPriority()),
					BUGTABLE.BUG_TYPE.value(t.getBugType()),
					BUGTABLE.OS.value(t.getOs()),
					BUGTABLE.BROWSER.value(t.getBrowser()),
					BUGTABLE.HARDWARE.value(t.getHardware()),
					BUGTABLE.BUG_FOUND.value(t.getBugFound()),
					BUGTABLE.BUG_STEPS.value(t.getBugSteps()),
					BUGTABLE.BUG_STATUS.value(t.getBugStatus()),
					BUGTABLE.CONFIRMED.value(t.getConfirmed()),
					BUGTABLE.BUG_ACTIVATEDCOUNT.value(t.getBugActivatedCount()),
					BUGTABLE.BUG_MAILTO.value(t.getBugMailto()),
					BUGTABLE.OPENEDBY.value(t.getOpenedBy()),
					BUGTABLE.OPENEDDATE.value(t.getOpenedDate()),
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
					BUGTABLE.BUG_LINK.value(t.getBugLink()),
					BUGTABLE.CASE_LINK.value(t.getCaseLink()),
					BUGTABLE.CASE_VERSION.value(t.getCaseVersion()),
					BUGTABLE.RESULT.value(t.getResult()),
					BUGTABLE.REPO.value(t.getRepo()),
					BUGTABLE.ENTRY.value(t.getEntry()),
					BUGTABLE.LINES.value(t.getLines()),
					BUGTABLE.V1.value(t.getV1()),
					BUGTABLE.V2.value(t.getV2()),
					BUGTABLE.REPOTYPE.value(t.getRepoType()),
					BUGTABLE.TESTTASK.value(t.getTesttask()),
					BUGTABLE.BUG_LASTEDITEDBY.value(t.getBugLastEditedBy()),
					BUGTABLE.BUG_LASTEDITEDDATE.value(t.getBugLastEditedDate()),
					BUGTABLE.DELETED.value(t.getDeleted())).where(
					BUGTABLE.BUG_ID.eq(t.getBugID()));
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
				return delete(BUGTABLE).where(BUGTABLE.BUG_ID.eq(pk));
			}
		});
	}

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

	public List<Bug> query(Bug bug) {
		if(bug==null){
			bug=new Bug();
		}
		return getDslTemplate().query(bug, new SelectGenerateCallback<Bug>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Bug t) {
				return selectFrom(BUGTABLE).where(
				and(
					BUGTABLE.PRODUCT_ID.eq(t.getProductID()),
					BUGTABLE.MODULE_ID.eq(t.getModuleID()),
					BUGTABLE.PROJECT_ID.eq(t.getProjectID()),
					BUGTABLE.PLAN_ID.eq(t.getPlanID()),
					BUGTABLE.STORY_ID.eq(t.getStoryID()),
					BUGTABLE.STORY_VERSION.eq(t.getStoryVersion()),
					BUGTABLE.TASK_ID.eq(t.getTaskID()),
					BUGTABLE.TOTASK_ID.eq(t.getToTaskID()),
					BUGTABLE.TOSTORY_ID.eq(t.getToStoryID()),
					BUGTABLE.BUG_TITLE.eq(t.getBugTitle()),
					BUGTABLE.KEYWORDS.eq(t.getKeywords()),
					BUGTABLE.BUG_SEVERITY.eq(t.getBugSeverity()),
					BUGTABLE.PRIORITY.eq(t.getPriority()),
					BUGTABLE.BUG_TYPE.eq(t.getBugType()),
					BUGTABLE.OS.eq(t.getOs()),
					BUGTABLE.BROWSER.eq(t.getBrowser()),
					BUGTABLE.HARDWARE.eq(t.getHardware()),
					BUGTABLE.BUG_FOUND.eq(t.getBugFound()),
					BUGTABLE.BUG_STEPS.eq(t.getBugSteps()),
					BUGTABLE.BUG_STATUS.eq(t.getBugStatus()),
					BUGTABLE.CONFIRMED.eq(t.getConfirmed()),
					BUGTABLE.BUG_ACTIVATEDCOUNT.eq(t.getBugActivatedCount()),
					BUGTABLE.BUG_MAILTO.eq(t.getBugMailto()),
					BUGTABLE.OPENEDBY.eq(t.getOpenedBy()),
					BUGTABLE.OPENEDDATE.eq(t.getOpenedDate()),
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
					BUGTABLE.BUG_LINK.eq(t.getBugLink()),
					BUGTABLE.CASE_LINK.eq(t.getCaseLink()),
					BUGTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					BUGTABLE.RESULT.eq(t.getResult()),
					BUGTABLE.REPO.eq(t.getRepo()),
					BUGTABLE.ENTRY.eq(t.getEntry()),
					BUGTABLE.LINES.eq(t.getLines()),
					BUGTABLE.V1.eq(t.getV1()),
					BUGTABLE.V2.eq(t.getV2()),
					BUGTABLE.REPOTYPE.eq(t.getRepoType()),
					BUGTABLE.TESTTASK.eq(t.getTesttask()),
					BUGTABLE.BUG_LASTEDITEDBY.eq(t.getBugLastEditedBy()),
					BUGTABLE.BUG_LASTEDITEDDATE.eq(t.getBugLastEditedDate()),
					BUGTABLE.DELETED.eq(t.getDeleted())));
			}
		});
	}

	public Pager<Bug> queryPager(int start,int limit ,Bug bug) {
		if(bug==null){
			bug=new Bug();
		}
		return getDslTemplate().queryPager(start, limit, bug, false, new SelectGenerateCallback<Bug>() {

			public Select generate(Bug t) {
				return MysqlSelect.selectFrom(BUGTABLE).where(
				and(
					BUGTABLE.PRODUCT_ID.eq(t.getProductID()),
					BUGTABLE.MODULE_ID.eq(t.getModuleID()),
					BUGTABLE.PROJECT_ID.eq(t.getProjectID()),
					BUGTABLE.PLAN_ID.eq(t.getPlanID()),
					BUGTABLE.STORY_ID.eq(t.getStoryID()),
					BUGTABLE.STORY_VERSION.eq(t.getStoryVersion()),
					BUGTABLE.TASK_ID.eq(t.getTaskID()),
					BUGTABLE.TOTASK_ID.eq(t.getToTaskID()),
					BUGTABLE.TOSTORY_ID.eq(t.getToStoryID()),
					BUGTABLE.BUG_TITLE.eq(t.getBugTitle()),
					BUGTABLE.KEYWORDS.eq(t.getKeywords()),
					BUGTABLE.BUG_SEVERITY.eq(t.getBugSeverity()),
					BUGTABLE.PRIORITY.eq(t.getPriority()),
					BUGTABLE.BUG_TYPE.eq(t.getBugType()),
					BUGTABLE.OS.eq(t.getOs()),
					BUGTABLE.BROWSER.eq(t.getBrowser()),
					BUGTABLE.HARDWARE.eq(t.getHardware()),
					BUGTABLE.BUG_FOUND.eq(t.getBugFound()),
					BUGTABLE.BUG_STEPS.eq(t.getBugSteps()),
					BUGTABLE.BUG_STATUS.eq(t.getBugStatus()),
					BUGTABLE.CONFIRMED.eq(t.getConfirmed()),
					BUGTABLE.BUG_ACTIVATEDCOUNT.eq(t.getBugActivatedCount()),
					BUGTABLE.BUG_MAILTO.eq(t.getBugMailto()),
					BUGTABLE.OPENEDBY.eq(t.getOpenedBy()),
					BUGTABLE.OPENEDDATE.eq(t.getOpenedDate()),
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
					BUGTABLE.BUG_LINK.eq(t.getBugLink()),
					BUGTABLE.CASE_LINK.eq(t.getCaseLink()),
					BUGTABLE.CASE_VERSION.eq(t.getCaseVersion()),
					BUGTABLE.RESULT.eq(t.getResult()),
					BUGTABLE.REPO.eq(t.getRepo()),
					BUGTABLE.ENTRY.eq(t.getEntry()),
					BUGTABLE.LINES.eq(t.getLines()),
					BUGTABLE.V1.eq(t.getV1()),
					BUGTABLE.V2.eq(t.getV2()),
					BUGTABLE.REPOTYPE.eq(t.getRepoType()),
					BUGTABLE.TESTTASK.eq(t.getTesttask()),
					BUGTABLE.BUG_LASTEDITEDBY.eq(t.getBugLastEditedBy()),
					BUGTABLE.BUG_LASTEDITEDDATE.eq(t.getBugLastEditedDate()),
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
					BUGTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productID")),
					BUGTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleID")),
					BUGTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectID")),
					BUGTABLE.PLAN_ID.value(new JdbcNamedParameter("planID")),
					BUGTABLE.STORY_ID.value(new JdbcNamedParameter("storyID")),
					BUGTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
					BUGTABLE.TASK_ID.value(new JdbcNamedParameter("taskID")),
					BUGTABLE.TOTASK_ID.value(new JdbcNamedParameter("toTaskID")),
					BUGTABLE.TOSTORY_ID.value(new JdbcNamedParameter("toStoryID")),
					BUGTABLE.BUG_TITLE.value(new JdbcNamedParameter("bugTitle")),
					BUGTABLE.KEYWORDS.value(new JdbcNamedParameter("keywords")),
					BUGTABLE.BUG_SEVERITY.value(new JdbcNamedParameter("bugSeverity")),
					BUGTABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
					BUGTABLE.BUG_TYPE.value(new JdbcNamedParameter("bugType")),
					BUGTABLE.OS.value(new JdbcNamedParameter("os")),
					BUGTABLE.BROWSER.value(new JdbcNamedParameter("browser")),
					BUGTABLE.HARDWARE.value(new JdbcNamedParameter("hardware")),
					BUGTABLE.BUG_FOUND.value(new JdbcNamedParameter("bugFound")),
					BUGTABLE.BUG_STEPS.value(new JdbcNamedParameter("bugSteps")),
					BUGTABLE.BUG_STATUS.value(new JdbcNamedParameter("bugStatus")),
					BUGTABLE.CONFIRMED.value(new JdbcNamedParameter("confirmed")),
					BUGTABLE.BUG_ACTIVATEDCOUNT.value(new JdbcNamedParameter("bugActivatedCount")),
					BUGTABLE.BUG_MAILTO.value(new JdbcNamedParameter("bugMailto")),
					BUGTABLE.OPENEDBY.value(new JdbcNamedParameter("openedBy")),
					BUGTABLE.OPENEDDATE.value(new JdbcNamedParameter("openedDate")),
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
					BUGTABLE.BUG_LINK.value(new JdbcNamedParameter("bugLink")),
					BUGTABLE.CASE_LINK.value(new JdbcNamedParameter("caseLink")),
					BUGTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					BUGTABLE.RESULT.value(new JdbcNamedParameter("result")),
					BUGTABLE.REPO.value(new JdbcNamedParameter("repo")),
					BUGTABLE.ENTRY.value(new JdbcNamedParameter("entry")),
					BUGTABLE.LINES.value(new JdbcNamedParameter("lines")),
					BUGTABLE.V1.value(new JdbcNamedParameter("v1")),
					BUGTABLE.V2.value(new JdbcNamedParameter("v2")),
					BUGTABLE.REPOTYPE.value(new JdbcNamedParameter("repoType")),
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

	public int[] batchUpdate(List<Bug> bugs) {
		if (CollectionUtil.isEmpty(bugs)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(bugs, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(BUGTABLE).set(
					BUGTABLE.PRODUCT_ID.value(new JdbcNamedParameter("productID")),
					BUGTABLE.MODULE_ID.value(new JdbcNamedParameter("moduleID")),
					BUGTABLE.PROJECT_ID.value(new JdbcNamedParameter("projectID")),
					BUGTABLE.PLAN_ID.value(new JdbcNamedParameter("planID")),
					BUGTABLE.STORY_ID.value(new JdbcNamedParameter("storyID")),
					BUGTABLE.STORY_VERSION.value(new JdbcNamedParameter("storyVersion")),
					BUGTABLE.TASK_ID.value(new JdbcNamedParameter("taskID")),
					BUGTABLE.TOTASK_ID.value(new JdbcNamedParameter("toTaskID")),
					BUGTABLE.TOSTORY_ID.value(new JdbcNamedParameter("toStoryID")),
					BUGTABLE.BUG_TITLE.value(new JdbcNamedParameter("bugTitle")),
					BUGTABLE.KEYWORDS.value(new JdbcNamedParameter("keywords")),
					BUGTABLE.BUG_SEVERITY.value(new JdbcNamedParameter("bugSeverity")),
					BUGTABLE.PRIORITY.value(new JdbcNamedParameter("priority")),
					BUGTABLE.BUG_TYPE.value(new JdbcNamedParameter("bugType")),
					BUGTABLE.OS.value(new JdbcNamedParameter("os")),
					BUGTABLE.BROWSER.value(new JdbcNamedParameter("browser")),
					BUGTABLE.HARDWARE.value(new JdbcNamedParameter("hardware")),
					BUGTABLE.BUG_FOUND.value(new JdbcNamedParameter("bugFound")),
					BUGTABLE.BUG_STEPS.value(new JdbcNamedParameter("bugSteps")),
					BUGTABLE.BUG_STATUS.value(new JdbcNamedParameter("bugStatus")),
					BUGTABLE.CONFIRMED.value(new JdbcNamedParameter("confirmed")),
					BUGTABLE.BUG_ACTIVATEDCOUNT.value(new JdbcNamedParameter("bugActivatedCount")),
					BUGTABLE.BUG_MAILTO.value(new JdbcNamedParameter("bugMailto")),
					BUGTABLE.OPENEDBY.value(new JdbcNamedParameter("openedBy")),
					BUGTABLE.OPENEDDATE.value(new JdbcNamedParameter("openedDate")),
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
					BUGTABLE.BUG_LINK.value(new JdbcNamedParameter("bugLink")),
					BUGTABLE.CASE_LINK.value(new JdbcNamedParameter("caseLink")),
					BUGTABLE.CASE_VERSION.value(new JdbcNamedParameter("caseVersion")),
					BUGTABLE.RESULT.value(new JdbcNamedParameter("result")),
					BUGTABLE.REPO.value(new JdbcNamedParameter("repo")),
					BUGTABLE.ENTRY.value(new JdbcNamedParameter("entry")),
					BUGTABLE.LINES.value(new JdbcNamedParameter("lines")),
					BUGTABLE.V1.value(new JdbcNamedParameter("v1")),
					BUGTABLE.V2.value(new JdbcNamedParameter("v2")),
					BUGTABLE.REPOTYPE.value(new JdbcNamedParameter("repoType")),
					BUGTABLE.TESTTASK.value(new JdbcNamedParameter("testtask")),
					BUGTABLE.BUG_LASTEDITEDBY.value(new JdbcNamedParameter("bugLastEditedBy")),
					BUGTABLE.BUG_LASTEDITEDDATE.value(new JdbcNamedParameter("bugLastEditedDate")),
					BUGTABLE.DELETED.value(new JdbcNamedParameter("deleted"))).where(
				BUGTABLE.BUG_ID.eq(new JdbcNamedParameter("bugID")));
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
				BUGTABLE.BUG_ID.eq(new JdbcNamedParameter("bugID")),
				BUGTABLE.PRODUCT_ID.eq(new JdbcNamedParameter("productID")),
				BUGTABLE.MODULE_ID.eq(new JdbcNamedParameter("moduleID")),
				BUGTABLE.PROJECT_ID.eq(new JdbcNamedParameter("projectID")),
				BUGTABLE.PLAN_ID.eq(new JdbcNamedParameter("planID")),
				BUGTABLE.STORY_ID.eq(new JdbcNamedParameter("storyID")),
				BUGTABLE.STORY_VERSION.eq(new JdbcNamedParameter("storyVersion")),
				BUGTABLE.TASK_ID.eq(new JdbcNamedParameter("taskID")),
				BUGTABLE.TOTASK_ID.eq(new JdbcNamedParameter("toTaskID")),
				BUGTABLE.TOSTORY_ID.eq(new JdbcNamedParameter("toStoryID")),
				BUGTABLE.BUG_TITLE.eq(new JdbcNamedParameter("bugTitle")),
				BUGTABLE.KEYWORDS.eq(new JdbcNamedParameter("keywords")),
				BUGTABLE.BUG_SEVERITY.eq(new JdbcNamedParameter("bugSeverity")),
				BUGTABLE.PRIORITY.eq(new JdbcNamedParameter("priority")),
				BUGTABLE.BUG_TYPE.eq(new JdbcNamedParameter("bugType")),
				BUGTABLE.OS.eq(new JdbcNamedParameter("os")),
				BUGTABLE.BROWSER.eq(new JdbcNamedParameter("browser")),
				BUGTABLE.HARDWARE.eq(new JdbcNamedParameter("hardware")),
				BUGTABLE.BUG_FOUND.eq(new JdbcNamedParameter("bugFound")),
				BUGTABLE.BUG_STEPS.eq(new JdbcNamedParameter("bugSteps")),
				BUGTABLE.BUG_STATUS.eq(new JdbcNamedParameter("bugStatus")),
				BUGTABLE.CONFIRMED.eq(new JdbcNamedParameter("confirmed")),
				BUGTABLE.BUG_ACTIVATEDCOUNT.eq(new JdbcNamedParameter("bugActivatedCount")),
				BUGTABLE.BUG_MAILTO.eq(new JdbcNamedParameter("bugMailto")),
				BUGTABLE.OPENEDBY.eq(new JdbcNamedParameter("openedBy")),
				BUGTABLE.OPENEDDATE.eq(new JdbcNamedParameter("openedDate")),
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
				BUGTABLE.BUG_LINK.eq(new JdbcNamedParameter("bugLink")),
				BUGTABLE.CASE_LINK.eq(new JdbcNamedParameter("caseLink")),
				BUGTABLE.CASE_VERSION.eq(new JdbcNamedParameter("caseVersion")),
				BUGTABLE.RESULT.eq(new JdbcNamedParameter("result")),
				BUGTABLE.REPO.eq(new JdbcNamedParameter("repo")),
				BUGTABLE.ENTRY.eq(new JdbcNamedParameter("entry")),
				BUGTABLE.LINES.eq(new JdbcNamedParameter("lines")),
				BUGTABLE.V1.eq(new JdbcNamedParameter("v1")),
				BUGTABLE.V2.eq(new JdbcNamedParameter("v2")),
				BUGTABLE.REPOTYPE.eq(new JdbcNamedParameter("repoType")),
				BUGTABLE.TESTTASK.eq(new JdbcNamedParameter("testtask")),
				BUGTABLE.BUG_LASTEDITEDBY.eq(new JdbcNamedParameter("bugLastEditedBy")),
				BUGTABLE.BUG_LASTEDITEDDATE.eq(new JdbcNamedParameter("bugLastEditedDate")),
				BUGTABLE.DELETED.eq(new JdbcNamedParameter("deleted"))));
			}
		});
	}

}
