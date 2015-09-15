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

package sdpm.project.dao.impl.task;

import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;
import static sdpm.project.dao.inter.task.constant.TaskTable.*;
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
import sdpm.project.dao.inter.task.pojo.Task;
import sdpm.project.dao.inter.task.TaskDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;

import org.tinygroup.jdbctemplatedslsession.callback.DeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.InsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamDeleteGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamInsertGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.NoParamUpdateGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.SelectGenerateCallback;
import org.tinygroup.jdbctemplatedslsession.callback.UpdateGenerateCallback;

public class TaskDaoImpl extends TinyDslDaoSupport implements TaskDao {

	public Task add(Task task) {
		return getDslTemplate().insertAndReturnKey(task, new InsertGenerateCallback<Task>() {
			public Insert generate(Task t) {
				Insert insert = insertInto(TASKTABLE).values(
					TASKTABLE.TASK_ID.value(t.getTaskId()),
					TASKTABLE.TASK_PROJECT.value(t.getTaskProject()),
					TASKTABLE.TASK_STORY.value(t.getTaskStory()),
					TASKTABLE.TASK_STORY_VERSION.value(t.getTaskStoryVersion()),
					TASKTABLE.TASK_FROM_BUG.value(t.getTaskFromBug()),
					TASKTABLE.TASK_NAME.value(t.getTaskName()),
					TASKTABLE.TASK_TYPE.value(t.getTaskType()),
					TASKTABLE.TASK_PRI.value(t.getTaskPri()),
					TASKTABLE.TASK_ESTIMATE.value(t.getTaskEstimate()),
					TASKTABLE.TASK_CONSUMED.value(t.getTaskConsumed()),
					TASKTABLE.TASK_LEFT.value(t.getTaskLeft()),
					TASKTABLE.TASK_DEAD_LINE.value(t.getTaskDeadLine()),
					TASKTABLE.TASK_STATUS.value(t.getTaskStatus()),
					TASKTABLE.TASK_MAILTO.value(t.getTaskMailto()),
					TASKTABLE.TASK_DESC.value(t.getTaskDesc()),
					TASKTABLE.TASK_OPEN_BY.value(t.getTaskOpenBy()),
					TASKTABLE.TASK_OPENED_DATE.value(t.getTaskOpenedDate()),
					TASKTABLE.TASK_ASSIGNED_TO.value(t.getTaskAssignedTo()),
					TASKTABLE.TASK_ASSIGNED_DATE.value(t.getTaskAssignedDate()),
					TASKTABLE.TASK_EST_STARED.value(t.getTaskEstStared()),
					TASKTABLE.TASK_REAL_STARTED.value(t.getTaskRealStarted()),
					TASKTABLE.TASK_FINISHED_BY.value(t.getTaskFinishedBy()),
					TASKTABLE.TASK_FINISHED_DATE.value(t.getTaskFinishedDate()),
					TASKTABLE.TASK_CANCELED_BY.value(t.getTaskCanceledBy()),
					TASKTABLE.TASK_CANCELED_DATE.value(t.getTaskCanceledDate()),
					TASKTABLE.TASK_CLOSED_BY.value(t.getTaskClosedBy()),
					TASKTABLE.TASK_CLOSE_DATE.value(t.getTaskCloseDate()),
					TASKTABLE.TASK_CLOSED_REASON.value(t.getTaskClosedReason()),
					TASKTABLE.TASK_LAST_EDITED_BY.value(t.getTaskLastEditedBy()),
					TASKTABLE.TASK_LAST_EDITED_DATE.value(t.getTaskLastEditedDate()),
					TASKTABLE.TASK_DELETED.value(t.getTaskDeleted()));
				return insert;
			}
		});
	}

	public int edit(Task task) {
		if(task == null || task.getTaskId() == null){
			return 0;
		}
		return getDslTemplate().update(task, new UpdateGenerateCallback<Task>() {
			public Update generate(Task t) {
				Update update = update(TASKTABLE).set(
					TASKTABLE.TASK_ID.value(t.getTaskId()),
					TASKTABLE.TASK_PROJECT.value(t.getTaskProject()),
					TASKTABLE.TASK_STORY.value(t.getTaskStory()),
					TASKTABLE.TASK_STORY_VERSION.value(t.getTaskStoryVersion()),
					TASKTABLE.TASK_FROM_BUG.value(t.getTaskFromBug()),
					TASKTABLE.TASK_NAME.value(t.getTaskName()),
					TASKTABLE.TASK_TYPE.value(t.getTaskType()),
					TASKTABLE.TASK_PRI.value(t.getTaskPri()),
					TASKTABLE.TASK_ESTIMATE.value(t.getTaskEstimate()),
					TASKTABLE.TASK_CONSUMED.value(t.getTaskConsumed()),
					TASKTABLE.TASK_LEFT.value(t.getTaskLeft()),
					TASKTABLE.TASK_DEAD_LINE.value(t.getTaskDeadLine()),
					TASKTABLE.TASK_STATUS.value(t.getTaskStatus()),
					TASKTABLE.TASK_MAILTO.value(t.getTaskMailto()),
					TASKTABLE.TASK_DESC.value(t.getTaskDesc()),
					TASKTABLE.TASK_OPEN_BY.value(t.getTaskOpenBy()),
					TASKTABLE.TASK_OPENED_DATE.value(t.getTaskOpenedDate()),
					TASKTABLE.TASK_ASSIGNED_TO.value(t.getTaskAssignedTo()),
					TASKTABLE.TASK_ASSIGNED_DATE.value(t.getTaskAssignedDate()),
					TASKTABLE.TASK_EST_STARED.value(t.getTaskEstStared()),
					TASKTABLE.TASK_REAL_STARTED.value(t.getTaskRealStarted()),
					TASKTABLE.TASK_FINISHED_BY.value(t.getTaskFinishedBy()),
					TASKTABLE.TASK_FINISHED_DATE.value(t.getTaskFinishedDate()),
					TASKTABLE.TASK_CANCELED_BY.value(t.getTaskCanceledBy()),
					TASKTABLE.TASK_CANCELED_DATE.value(t.getTaskCanceledDate()),
					TASKTABLE.TASK_CLOSED_BY.value(t.getTaskClosedBy()),
					TASKTABLE.TASK_CLOSE_DATE.value(t.getTaskCloseDate()),
					TASKTABLE.TASK_CLOSED_REASON.value(t.getTaskClosedReason()),
					TASKTABLE.TASK_LAST_EDITED_BY.value(t.getTaskLastEditedBy()),
					TASKTABLE.TASK_LAST_EDITED_DATE.value(t.getTaskLastEditedDate()),
					TASKTABLE.TASK_DELETED.value(t.getTaskDeleted())).where(
					TASKTABLE.TASK_ID.eq(t.getTaskId()));
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
				return delete(TASKTABLE).where(TASKTABLE.TASK_ID.eq(pk));
			}
		});
	}

	public int deleteByKeys(Integer... pks) {
		if(pks == null || pks.length == 0){
			return 0;
		}
		return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
			public Delete generate(Serializable[] t) {
				return delete(TASKTABLE).where(TASKTABLE.TASK_ID.in(t));
		}
		},pks);
	}

	public Task getByKey(Integer pk) {
		return getDslTemplate().getByKey(pk, Task.class, new SelectGenerateCallback<Serializable>() {
		@SuppressWarnings("rawtypes")
		public Select generate(Serializable t) {
			return selectFrom(TASKTABLE).where(TASKTABLE.TASK_ID.eq(t));
			}
		});
	}

	public List<Task> query(Task task) {
		if(task==null){
			task=new Task();
		}
		return getDslTemplate().query(task, new SelectGenerateCallback<Task>() {

			@SuppressWarnings("rawtypes")
			public Select generate(Task t) {
				return selectFrom(TASKTABLE).where(
				and(
					TASKTABLE.TASK_ID.eq(t.getTaskId()),
					TASKTABLE.TASK_PROJECT.eq(t.getTaskProject()),
					TASKTABLE.TASK_STORY.eq(t.getTaskStory()),
					TASKTABLE.TASK_STORY_VERSION.eq(t.getTaskStoryVersion()),
					TASKTABLE.TASK_FROM_BUG.eq(t.getTaskFromBug()),
					TASKTABLE.TASK_NAME.eq(t.getTaskName()),
					TASKTABLE.TASK_TYPE.eq(t.getTaskType()),
					TASKTABLE.TASK_PRI.eq(t.getTaskPri()),
					TASKTABLE.TASK_ESTIMATE.eq(t.getTaskEstimate()),
					TASKTABLE.TASK_CONSUMED.eq(t.getTaskConsumed()),
					TASKTABLE.TASK_LEFT.eq(t.getTaskLeft()),
					TASKTABLE.TASK_DEAD_LINE.eq(t.getTaskDeadLine()),
					TASKTABLE.TASK_STATUS.eq(t.getTaskStatus()),
					TASKTABLE.TASK_MAILTO.eq(t.getTaskMailto()),
					TASKTABLE.TASK_DESC.eq(t.getTaskDesc()),
					TASKTABLE.TASK_OPEN_BY.eq(t.getTaskOpenBy()),
					TASKTABLE.TASK_OPENED_DATE.eq(t.getTaskOpenedDate()),
					TASKTABLE.TASK_ASSIGNED_TO.eq(t.getTaskAssignedTo()),
					TASKTABLE.TASK_ASSIGNED_DATE.eq(t.getTaskAssignedDate()),
					TASKTABLE.TASK_EST_STARED.eq(t.getTaskEstStared()),
					TASKTABLE.TASK_REAL_STARTED.eq(t.getTaskRealStarted()),
					TASKTABLE.TASK_FINISHED_BY.eq(t.getTaskFinishedBy()),
					TASKTABLE.TASK_FINISHED_DATE.eq(t.getTaskFinishedDate()),
					TASKTABLE.TASK_CANCELED_BY.eq(t.getTaskCanceledBy()),
					TASKTABLE.TASK_CANCELED_DATE.eq(t.getTaskCanceledDate()),
					TASKTABLE.TASK_CLOSED_BY.eq(t.getTaskClosedBy()),
					TASKTABLE.TASK_CLOSE_DATE.eq(t.getTaskCloseDate()),
					TASKTABLE.TASK_CLOSED_REASON.eq(t.getTaskClosedReason()),
					TASKTABLE.TASK_LAST_EDITED_BY.eq(t.getTaskLastEditedBy()),
					TASKTABLE.TASK_LAST_EDITED_DATE.eq(t.getTaskLastEditedDate()),
					TASKTABLE.TASK_DELETED.eq(t.getTaskDeleted())));
			}
		});
	}

	public Pager<Task> queryPager(int start,int limit ,Task task) {
		if(task==null){
			task=new Task();
		}
		return getDslTemplate().queryPager(start, limit, task, false, new SelectGenerateCallback<Task>() {

			public Select generate(Task t) {
				return MysqlSelect.selectFrom(TASKTABLE).where(
				and(
					TASKTABLE.TASK_ID.eq(t.getTaskId()),
					TASKTABLE.TASK_PROJECT.eq(t.getTaskProject()),
					TASKTABLE.TASK_STORY.eq(t.getTaskStory()),
					TASKTABLE.TASK_STORY_VERSION.eq(t.getTaskStoryVersion()),
					TASKTABLE.TASK_FROM_BUG.eq(t.getTaskFromBug()),
					TASKTABLE.TASK_NAME.eq(t.getTaskName()),
					TASKTABLE.TASK_TYPE.eq(t.getTaskType()),
					TASKTABLE.TASK_PRI.eq(t.getTaskPri()),
					TASKTABLE.TASK_ESTIMATE.eq(t.getTaskEstimate()),
					TASKTABLE.TASK_CONSUMED.eq(t.getTaskConsumed()),
					TASKTABLE.TASK_LEFT.eq(t.getTaskLeft()),
					TASKTABLE.TASK_DEAD_LINE.eq(t.getTaskDeadLine()),
					TASKTABLE.TASK_STATUS.eq(t.getTaskStatus()),
					TASKTABLE.TASK_MAILTO.eq(t.getTaskMailto()),
					TASKTABLE.TASK_DESC.eq(t.getTaskDesc()),
					TASKTABLE.TASK_OPEN_BY.eq(t.getTaskOpenBy()),
					TASKTABLE.TASK_OPENED_DATE.eq(t.getTaskOpenedDate()),
					TASKTABLE.TASK_ASSIGNED_TO.eq(t.getTaskAssignedTo()),
					TASKTABLE.TASK_ASSIGNED_DATE.eq(t.getTaskAssignedDate()),
					TASKTABLE.TASK_EST_STARED.eq(t.getTaskEstStared()),
					TASKTABLE.TASK_REAL_STARTED.eq(t.getTaskRealStarted()),
					TASKTABLE.TASK_FINISHED_BY.eq(t.getTaskFinishedBy()),
					TASKTABLE.TASK_FINISHED_DATE.eq(t.getTaskFinishedDate()),
					TASKTABLE.TASK_CANCELED_BY.eq(t.getTaskCanceledBy()),
					TASKTABLE.TASK_CANCELED_DATE.eq(t.getTaskCanceledDate()),
					TASKTABLE.TASK_CLOSED_BY.eq(t.getTaskClosedBy()),
					TASKTABLE.TASK_CLOSE_DATE.eq(t.getTaskCloseDate()),
					TASKTABLE.TASK_CLOSED_REASON.eq(t.getTaskClosedReason()),
					TASKTABLE.TASK_LAST_EDITED_BY.eq(t.getTaskLastEditedBy()),
					TASKTABLE.TASK_LAST_EDITED_DATE.eq(t.getTaskLastEditedDate()),
					TASKTABLE.TASK_DELETED.eq(t.getTaskDeleted())));
			}
		});
	}

	public int[] batchInsert(boolean autoGeneratedKeys ,List<Task> tasks) {
		if (CollectionUtil.isEmpty(tasks)) {
			return new int[0];
		}
		return getDslTemplate().batchInsert(autoGeneratedKeys, tasks, new NoParamInsertGenerateCallback() {

			public Insert generate() {
				return insertInto(TASKTABLE).values(
					TASKTABLE.TASK_ID.value(new JdbcNamedParameter("taskId")),
					TASKTABLE.TASK_PROJECT.value(new JdbcNamedParameter("taskProject")),
					TASKTABLE.TASK_STORY.value(new JdbcNamedParameter("taskStory")),
					TASKTABLE.TASK_STORY_VERSION.value(new JdbcNamedParameter("taskStoryVersion")),
					TASKTABLE.TASK_FROM_BUG.value(new JdbcNamedParameter("taskFromBug")),
					TASKTABLE.TASK_NAME.value(new JdbcNamedParameter("taskName")),
					TASKTABLE.TASK_TYPE.value(new JdbcNamedParameter("taskType")),
					TASKTABLE.TASK_PRI.value(new JdbcNamedParameter("taskPri")),
					TASKTABLE.TASK_ESTIMATE.value(new JdbcNamedParameter("taskEstimate")),
					TASKTABLE.TASK_CONSUMED.value(new JdbcNamedParameter("taskConsumed")),
					TASKTABLE.TASK_LEFT.value(new JdbcNamedParameter("taskLeft")),
					TASKTABLE.TASK_DEAD_LINE.value(new JdbcNamedParameter("taskDeadLine")),
					TASKTABLE.TASK_STATUS.value(new JdbcNamedParameter("taskStatus")),
					TASKTABLE.TASK_MAILTO.value(new JdbcNamedParameter("taskMailto")),
					TASKTABLE.TASK_DESC.value(new JdbcNamedParameter("taskDesc")),
					TASKTABLE.TASK_OPEN_BY.value(new JdbcNamedParameter("taskOpenBy")),
					TASKTABLE.TASK_OPENED_DATE.value(new JdbcNamedParameter("taskOpenedDate")),
					TASKTABLE.TASK_ASSIGNED_TO.value(new JdbcNamedParameter("taskAssignedTo")),
					TASKTABLE.TASK_ASSIGNED_DATE.value(new JdbcNamedParameter("taskAssignedDate")),
					TASKTABLE.TASK_EST_STARED.value(new JdbcNamedParameter("taskEstStared")),
					TASKTABLE.TASK_REAL_STARTED.value(new JdbcNamedParameter("taskRealStarted")),
					TASKTABLE.TASK_FINISHED_BY.value(new JdbcNamedParameter("taskFinishedBy")),
					TASKTABLE.TASK_FINISHED_DATE.value(new JdbcNamedParameter("taskFinishedDate")),
					TASKTABLE.TASK_CANCELED_BY.value(new JdbcNamedParameter("taskCanceledBy")),
					TASKTABLE.TASK_CANCELED_DATE.value(new JdbcNamedParameter("taskCanceledDate")),
					TASKTABLE.TASK_CLOSED_BY.value(new JdbcNamedParameter("taskClosedBy")),
					TASKTABLE.TASK_CLOSE_DATE.value(new JdbcNamedParameter("taskCloseDate")),
					TASKTABLE.TASK_CLOSED_REASON.value(new JdbcNamedParameter("taskClosedReason")),
					TASKTABLE.TASK_LAST_EDITED_BY.value(new JdbcNamedParameter("taskLastEditedBy")),
					TASKTABLE.TASK_LAST_EDITED_DATE.value(new JdbcNamedParameter("taskLastEditedDate")),
					TASKTABLE.TASK_DELETED.value(new JdbcNamedParameter("taskDeleted")));
			}
		});
	}

	public int[] batchInsert(List<Task> tasks){
			return batchInsert(true ,tasks);
	}

	public int[] batchUpdate(List<Task> tasks) {
		if (CollectionUtil.isEmpty(tasks)) {
			return new int[0];
		}
		return getDslTemplate().batchUpdate(tasks, new NoParamUpdateGenerateCallback() {

			public Update generate() {
				return update(TASKTABLE).set(
					TASKTABLE.TASK_ID.value(new JdbcNamedParameter("taskId")),
					TASKTABLE.TASK_PROJECT.value(new JdbcNamedParameter("taskProject")),
					TASKTABLE.TASK_STORY.value(new JdbcNamedParameter("taskStory")),
					TASKTABLE.TASK_STORY_VERSION.value(new JdbcNamedParameter("taskStoryVersion")),
					TASKTABLE.TASK_FROM_BUG.value(new JdbcNamedParameter("taskFromBug")),
					TASKTABLE.TASK_NAME.value(new JdbcNamedParameter("taskName")),
					TASKTABLE.TASK_TYPE.value(new JdbcNamedParameter("taskType")),
					TASKTABLE.TASK_PRI.value(new JdbcNamedParameter("taskPri")),
					TASKTABLE.TASK_ESTIMATE.value(new JdbcNamedParameter("taskEstimate")),
					TASKTABLE.TASK_CONSUMED.value(new JdbcNamedParameter("taskConsumed")),
					TASKTABLE.TASK_LEFT.value(new JdbcNamedParameter("taskLeft")),
					TASKTABLE.TASK_DEAD_LINE.value(new JdbcNamedParameter("taskDeadLine")),
					TASKTABLE.TASK_STATUS.value(new JdbcNamedParameter("taskStatus")),
					TASKTABLE.TASK_MAILTO.value(new JdbcNamedParameter("taskMailto")),
					TASKTABLE.TASK_DESC.value(new JdbcNamedParameter("taskDesc")),
					TASKTABLE.TASK_OPEN_BY.value(new JdbcNamedParameter("taskOpenBy")),
					TASKTABLE.TASK_OPENED_DATE.value(new JdbcNamedParameter("taskOpenedDate")),
					TASKTABLE.TASK_ASSIGNED_TO.value(new JdbcNamedParameter("taskAssignedTo")),
					TASKTABLE.TASK_ASSIGNED_DATE.value(new JdbcNamedParameter("taskAssignedDate")),
					TASKTABLE.TASK_EST_STARED.value(new JdbcNamedParameter("taskEstStared")),
					TASKTABLE.TASK_REAL_STARTED.value(new JdbcNamedParameter("taskRealStarted")),
					TASKTABLE.TASK_FINISHED_BY.value(new JdbcNamedParameter("taskFinishedBy")),
					TASKTABLE.TASK_FINISHED_DATE.value(new JdbcNamedParameter("taskFinishedDate")),
					TASKTABLE.TASK_CANCELED_BY.value(new JdbcNamedParameter("taskCanceledBy")),
					TASKTABLE.TASK_CANCELED_DATE.value(new JdbcNamedParameter("taskCanceledDate")),
					TASKTABLE.TASK_CLOSED_BY.value(new JdbcNamedParameter("taskClosedBy")),
					TASKTABLE.TASK_CLOSE_DATE.value(new JdbcNamedParameter("taskCloseDate")),
					TASKTABLE.TASK_CLOSED_REASON.value(new JdbcNamedParameter("taskClosedReason")),
					TASKTABLE.TASK_LAST_EDITED_BY.value(new JdbcNamedParameter("taskLastEditedBy")),
					TASKTABLE.TASK_LAST_EDITED_DATE.value(new JdbcNamedParameter("taskLastEditedDate")),
					TASKTABLE.TASK_DELETED.value(new JdbcNamedParameter("taskDeleted"))).where(
				TASKTABLE.TASK_ID.eq(new JdbcNamedParameter("taskId")));
			}
		});
	}

	public int[] batchDelete(List<Task> tasks) {
		if (CollectionUtil.isEmpty(tasks)) {
			return new int[0];
		}
		return getDslTemplate().batchDelete(tasks, new NoParamDeleteGenerateCallback() {

			public Delete generate() {
				return delete(TASKTABLE).where(and(
				TASKTABLE.TASK_ID.eq(new JdbcNamedParameter("taskId")),
				TASKTABLE.TASK_PROJECT.eq(new JdbcNamedParameter("taskProject")),
				TASKTABLE.TASK_STORY.eq(new JdbcNamedParameter("taskStory")),
				TASKTABLE.TASK_STORY_VERSION.eq(new JdbcNamedParameter("taskStoryVersion")),
				TASKTABLE.TASK_FROM_BUG.eq(new JdbcNamedParameter("taskFromBug")),
				TASKTABLE.TASK_NAME.eq(new JdbcNamedParameter("taskName")),
				TASKTABLE.TASK_TYPE.eq(new JdbcNamedParameter("taskType")),
				TASKTABLE.TASK_PRI.eq(new JdbcNamedParameter("taskPri")),
				TASKTABLE.TASK_ESTIMATE.eq(new JdbcNamedParameter("taskEstimate")),
				TASKTABLE.TASK_CONSUMED.eq(new JdbcNamedParameter("taskConsumed")),
				TASKTABLE.TASK_LEFT.eq(new JdbcNamedParameter("taskLeft")),
				TASKTABLE.TASK_DEAD_LINE.eq(new JdbcNamedParameter("taskDeadLine")),
				TASKTABLE.TASK_STATUS.eq(new JdbcNamedParameter("taskStatus")),
				TASKTABLE.TASK_MAILTO.eq(new JdbcNamedParameter("taskMailto")),
				TASKTABLE.TASK_DESC.eq(new JdbcNamedParameter("taskDesc")),
				TASKTABLE.TASK_OPEN_BY.eq(new JdbcNamedParameter("taskOpenBy")),
				TASKTABLE.TASK_OPENED_DATE.eq(new JdbcNamedParameter("taskOpenedDate")),
				TASKTABLE.TASK_ASSIGNED_TO.eq(new JdbcNamedParameter("taskAssignedTo")),
				TASKTABLE.TASK_ASSIGNED_DATE.eq(new JdbcNamedParameter("taskAssignedDate")),
				TASKTABLE.TASK_EST_STARED.eq(new JdbcNamedParameter("taskEstStared")),
				TASKTABLE.TASK_REAL_STARTED.eq(new JdbcNamedParameter("taskRealStarted")),
				TASKTABLE.TASK_FINISHED_BY.eq(new JdbcNamedParameter("taskFinishedBy")),
				TASKTABLE.TASK_FINISHED_DATE.eq(new JdbcNamedParameter("taskFinishedDate")),
				TASKTABLE.TASK_CANCELED_BY.eq(new JdbcNamedParameter("taskCanceledBy")),
				TASKTABLE.TASK_CANCELED_DATE.eq(new JdbcNamedParameter("taskCanceledDate")),
				TASKTABLE.TASK_CLOSED_BY.eq(new JdbcNamedParameter("taskClosedBy")),
				TASKTABLE.TASK_CLOSE_DATE.eq(new JdbcNamedParameter("taskCloseDate")),
				TASKTABLE.TASK_CLOSED_REASON.eq(new JdbcNamedParameter("taskClosedReason")),
				TASKTABLE.TASK_LAST_EDITED_BY.eq(new JdbcNamedParameter("taskLastEditedBy")),
				TASKTABLE.TASK_LAST_EDITED_DATE.eq(new JdbcNamedParameter("taskLastEditedDate")),
				TASKTABLE.TASK_DELETED.eq(new JdbcNamedParameter("taskDeleted"))));
			}
		});
	}

}
