/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p/>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.gnu.org/licenses/gpl.html
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tinygroup.sdpm.project.dao.impl;

import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.jdbctemplatedslsession.callback.*;
import org.tinygroup.jdbctemplatedslsession.daosupport.TinyDslDaoSupport;
import org.tinygroup.sdpm.project.dao.ProjectTaskDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.tinysqldsl.*;
import org.tinygroup.tinysqldsl.expression.JdbcNamedParameter;
import org.tinygroup.tinysqldsl.extend.MysqlSelect;

import java.io.Serializable;
import java.util.List;

import static org.tinygroup.sdpm.project.dao.constant.ProjectTaskTable.PROJECT_TASKTABLE;
import static org.tinygroup.tinysqldsl.Delete.delete;
import static org.tinygroup.tinysqldsl.Insert.insertInto;
import static org.tinygroup.tinysqldsl.Select.selectFrom;
import static org.tinygroup.tinysqldsl.Update.update;
import static org.tinygroup.tinysqldsl.base.StatementSqlBuilder.and;

public class ProjectTaskDaoImpl extends TinyDslDaoSupport implements ProjectTaskDao {

    public ProjectTask add(ProjectTask projectTask) {
        return getDslTemplate().insertAndReturnKey(projectTask, new InsertGenerateCallback<ProjectTask>() {
            public Insert generate(ProjectTask t) {
                Insert insert = insertInto(PROJECT_TASKTABLE).values(
                        PROJECT_TASKTABLE.TASK_ID.value(t.getTaskId()),
                        PROJECT_TASKTABLE.TASK_PROJECT.value(t.getTaskProject()),
                        PROJECT_TASKTABLE.TASK_STORY.value(t.getTaskStory()),
                        PROJECT_TASKTABLE.TASK_STORY_VERSION.value(t.getTaskStoryVersion()),
                        PROJECT_TASKTABLE.TASK_FROM_BUG.value(t.getTaskFromBug()),
                        PROJECT_TASKTABLE.TASK_NAME.value(t.getTaskName()),
                        PROJECT_TASKTABLE.TASK_TYPE.value(t.getTaskType()),
                        PROJECT_TASKTABLE.TASK_PRI.value(t.getTaskPri()),
                        PROJECT_TASKTABLE.TASK_ESTIMATE.value(t.getTaskEstimate()),
                        PROJECT_TASKTABLE.TASK_CONSUMED.value(t.getTaskConsumed()),
                        PROJECT_TASKTABLE.TASK_LEFT.value(t.getTaskLeft()),
                        PROJECT_TASKTABLE.TASK_DEAD_LINE.value(t.getTaskDeadLine()),
                        PROJECT_TASKTABLE.TASK_STATUS.value(t.getTaskStatus()),
                        PROJECT_TASKTABLE.TASK_MAILTO.value(t.getTaskMailto()),
                        PROJECT_TASKTABLE.TASK_DESC.value(t.getTaskDesc()),
                        PROJECT_TASKTABLE.TASK_OPEN_BY.value(t.getTaskOpenBy()),
                        PROJECT_TASKTABLE.TASK_OPENED_DATE.value(t.getTaskOpenedDate()),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_TO.value(t.getTaskAssignedTo()),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_DATE.value(t.getTaskAssignedDate()),
                        PROJECT_TASKTABLE.TASK_EST_STARED.value(t.getTaskEstStared()),
                        PROJECT_TASKTABLE.TASK_REAL_STARTED.value(t.getTaskRealStarted()),
                        PROJECT_TASKTABLE.TASK_FINISHED_BY.value(t.getTaskFinishedBy()),
                        PROJECT_TASKTABLE.TASK_FINISHED_DATE.value(t.getTaskFinishedDate()),
                        PROJECT_TASKTABLE.TASK_CANCELED_BY.value(t.getTaskCanceledBy()),
                        PROJECT_TASKTABLE.TASK_CANCELED_DATE.value(t.getTaskCanceledDate()),
                        PROJECT_TASKTABLE.TASK_CLOSED_BY.value(t.getTaskClosedBy()),
                        PROJECT_TASKTABLE.TASK_CLOSE_DATE.value(t.getTaskCloseDate()),
                        PROJECT_TASKTABLE.TASK_CLOSED_REASON.value(t.getTaskClosedReason()),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_BY.value(t.getTaskLastEditedBy()),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_DATE.value(t.getTaskLastEditedDate()),
                        PROJECT_TASKTABLE.TASK_DELETED.value(t.getTaskDeleted()));
                return insert;
            }
        });
    }

    public int edit(ProjectTask projectTask) {
        if (projectTask == null || projectTask.getTaskId() == null) {
            return 0;
        }
        return getDslTemplate().update(projectTask, new UpdateGenerateCallback<ProjectTask>() {
            public Update generate(ProjectTask t) {
                Update update = update(PROJECT_TASKTABLE).set(
                        PROJECT_TASKTABLE.TASK_PROJECT.value(t.getTaskProject()),
                        PROJECT_TASKTABLE.TASK_STORY.value(t.getTaskStory()),
                        PROJECT_TASKTABLE.TASK_STORY_VERSION.value(t.getTaskStoryVersion()),
                        PROJECT_TASKTABLE.TASK_FROM_BUG.value(t.getTaskFromBug()),
                        PROJECT_TASKTABLE.TASK_NAME.value(t.getTaskName()),
                        PROJECT_TASKTABLE.TASK_TYPE.value(t.getTaskType()),
                        PROJECT_TASKTABLE.TASK_PRI.value(t.getTaskPri()),
                        PROJECT_TASKTABLE.TASK_ESTIMATE.value(t.getTaskEstimate()),
                        PROJECT_TASKTABLE.TASK_CONSUMED.value(t.getTaskConsumed()),
                        PROJECT_TASKTABLE.TASK_LEFT.value(t.getTaskLeft()),
                        PROJECT_TASKTABLE.TASK_DEAD_LINE.value(t.getTaskDeadLine()),
                        PROJECT_TASKTABLE.TASK_STATUS.value(t.getTaskStatus()),
                        PROJECT_TASKTABLE.TASK_MAILTO.value(t.getTaskMailto()),
                        PROJECT_TASKTABLE.TASK_DESC.value(t.getTaskDesc()),
                        PROJECT_TASKTABLE.TASK_OPEN_BY.value(t.getTaskOpenBy()),
                        PROJECT_TASKTABLE.TASK_OPENED_DATE.value(t.getTaskOpenedDate()),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_TO.value(t.getTaskAssignedTo()),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_DATE.value(t.getTaskAssignedDate()),
                        PROJECT_TASKTABLE.TASK_EST_STARED.value(t.getTaskEstStared()),
                        PROJECT_TASKTABLE.TASK_REAL_STARTED.value(t.getTaskRealStarted()),
                        PROJECT_TASKTABLE.TASK_FINISHED_BY.value(t.getTaskFinishedBy()),
                        PROJECT_TASKTABLE.TASK_FINISHED_DATE.value(t.getTaskFinishedDate()),
                        PROJECT_TASKTABLE.TASK_CANCELED_BY.value(t.getTaskCanceledBy()),
                        PROJECT_TASKTABLE.TASK_CANCELED_DATE.value(t.getTaskCanceledDate()),
                        PROJECT_TASKTABLE.TASK_CLOSED_BY.value(t.getTaskClosedBy()),
                        PROJECT_TASKTABLE.TASK_CLOSE_DATE.value(t.getTaskCloseDate()),
                        PROJECT_TASKTABLE.TASK_CLOSED_REASON.value(t.getTaskClosedReason()),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_BY.value(t.getTaskLastEditedBy()),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_DATE.value(t.getTaskLastEditedDate()),
                        PROJECT_TASKTABLE.TASK_DELETED.value(t.getTaskDeleted())).where(
                        PROJECT_TASKTABLE.TASK_ID.eq(t.getTaskId()));
                return update;
            }
        });
    }

    public int deleteByKey(Integer pk) {
        if (pk == null) {
            return 0;
        }
        return getDslTemplate().deleteByKey(pk, new DeleteGenerateCallback<Serializable>() {
            public Delete generate(Serializable pk) {
                return delete(PROJECT_TASKTABLE).where(PROJECT_TASKTABLE.TASK_ID.eq(pk));
            }
        });
    }

    public int deleteByKeys(Integer... pks) {
        if (pks == null || pks.length == 0) {
            return 0;
        }
        return getDslTemplate().deleteByKeys(new DeleteGenerateCallback<Serializable[]>() {
            public Delete generate(Serializable[] t) {
                return delete(PROJECT_TASKTABLE).where(PROJECT_TASKTABLE.TASK_ID.in(t));
            }
        }, pks);
    }

    public ProjectTask getByKey(Integer pk) {
        return getDslTemplate().getByKey(pk, ProjectTask.class, new SelectGenerateCallback<Serializable>() {
            @SuppressWarnings("rawtypes")
            public Select generate(Serializable t) {
                return selectFrom(PROJECT_TASKTABLE).where(PROJECT_TASKTABLE.TASK_ID.eq(t));
            }
        });
    }

    public List<ProjectTask> query(ProjectTask projectTask) {
        if (projectTask == null) {
            projectTask = new ProjectTask();
        }
        return getDslTemplate().query(projectTask, new SelectGenerateCallback<ProjectTask>() {

            @SuppressWarnings("rawtypes")
            public Select generate(ProjectTask t) {
                return selectFrom(PROJECT_TASKTABLE).where(
                        and(
                                PROJECT_TASKTABLE.TASK_PROJECT.eq(t.getTaskProject()),
                                PROJECT_TASKTABLE.TASK_STORY.eq(t.getTaskStory()),
                                PROJECT_TASKTABLE.TASK_STORY_VERSION.eq(t.getTaskStoryVersion()),
                                PROJECT_TASKTABLE.TASK_FROM_BUG.eq(t.getTaskFromBug()),
                                PROJECT_TASKTABLE.TASK_NAME.eq(t.getTaskName()),
                                PROJECT_TASKTABLE.TASK_TYPE.eq(t.getTaskType()),
                                PROJECT_TASKTABLE.TASK_PRI.eq(t.getTaskPri()),
                                PROJECT_TASKTABLE.TASK_ESTIMATE.eq(t.getTaskEstimate()),
                                PROJECT_TASKTABLE.TASK_CONSUMED.eq(t.getTaskConsumed()),
                                PROJECT_TASKTABLE.TASK_LEFT.eq(t.getTaskLeft()),
                                PROJECT_TASKTABLE.TASK_DEAD_LINE.eq(t.getTaskDeadLine()),
                                PROJECT_TASKTABLE.TASK_STATUS.eq(t.getTaskStatus()),
                                PROJECT_TASKTABLE.TASK_MAILTO.eq(t.getTaskMailto()),
                                PROJECT_TASKTABLE.TASK_DESC.eq(t.getTaskDesc()),
                                PROJECT_TASKTABLE.TASK_OPEN_BY.eq(t.getTaskOpenBy()),
                                PROJECT_TASKTABLE.TASK_OPENED_DATE.eq(t.getTaskOpenedDate()),
                                PROJECT_TASKTABLE.TASK_ASSIGNED_TO.eq(t.getTaskAssignedTo()),
                                PROJECT_TASKTABLE.TASK_ASSIGNED_DATE.eq(t.getTaskAssignedDate()),
                                PROJECT_TASKTABLE.TASK_EST_STARED.eq(t.getTaskEstStared()),
                                PROJECT_TASKTABLE.TASK_REAL_STARTED.eq(t.getTaskRealStarted()),
                                PROJECT_TASKTABLE.TASK_FINISHED_BY.eq(t.getTaskFinishedBy()),
                                PROJECT_TASKTABLE.TASK_FINISHED_DATE.eq(t.getTaskFinishedDate()),
                                PROJECT_TASKTABLE.TASK_CANCELED_BY.eq(t.getTaskCanceledBy()),
                                PROJECT_TASKTABLE.TASK_CANCELED_DATE.eq(t.getTaskCanceledDate()),
                                PROJECT_TASKTABLE.TASK_CLOSED_BY.eq(t.getTaskClosedBy()),
                                PROJECT_TASKTABLE.TASK_CLOSE_DATE.eq(t.getTaskCloseDate()),
                                PROJECT_TASKTABLE.TASK_CLOSED_REASON.eq(t.getTaskClosedReason()),
                                PROJECT_TASKTABLE.TASK_LAST_EDITED_BY.eq(t.getTaskLastEditedBy()),
                                PROJECT_TASKTABLE.TASK_LAST_EDITED_DATE.eq(t.getTaskLastEditedDate()),
                                PROJECT_TASKTABLE.TASK_DELETED.eq(t.getTaskDeleted())));
            }
        });
    }

    public Pager<ProjectTask> queryPager(int start, int limit, ProjectTask projectTask) {
        if (projectTask == null) {
            projectTask = new ProjectTask();
        }
        return getDslTemplate().queryPager(start, limit, projectTask, false, new SelectGenerateCallback<ProjectTask>() {

            public Select generate(ProjectTask t) {
                return MysqlSelect.selectFrom(PROJECT_TASKTABLE).where(
                        and(
                                PROJECT_TASKTABLE.TASK_PROJECT.eq(t.getTaskProject()),
                                PROJECT_TASKTABLE.TASK_STORY.eq(t.getTaskStory()),
                                PROJECT_TASKTABLE.TASK_STORY_VERSION.eq(t.getTaskStoryVersion()),
                                PROJECT_TASKTABLE.TASK_FROM_BUG.eq(t.getTaskFromBug()),
                                PROJECT_TASKTABLE.TASK_NAME.eq(t.getTaskName()),
                                PROJECT_TASKTABLE.TASK_TYPE.eq(t.getTaskType()),
                                PROJECT_TASKTABLE.TASK_PRI.eq(t.getTaskPri()),
                                PROJECT_TASKTABLE.TASK_ESTIMATE.eq(t.getTaskEstimate()),
                                PROJECT_TASKTABLE.TASK_CONSUMED.eq(t.getTaskConsumed()),
                                PROJECT_TASKTABLE.TASK_LEFT.eq(t.getTaskLeft()),
                                PROJECT_TASKTABLE.TASK_DEAD_LINE.eq(t.getTaskDeadLine()),
                                PROJECT_TASKTABLE.TASK_STATUS.eq(t.getTaskStatus()),
                                PROJECT_TASKTABLE.TASK_MAILTO.eq(t.getTaskMailto()),
                                PROJECT_TASKTABLE.TASK_DESC.eq(t.getTaskDesc()),
                                PROJECT_TASKTABLE.TASK_OPEN_BY.eq(t.getTaskOpenBy()),
                                PROJECT_TASKTABLE.TASK_OPENED_DATE.eq(t.getTaskOpenedDate()),
                                PROJECT_TASKTABLE.TASK_ASSIGNED_TO.eq(t.getTaskAssignedTo()),
                                PROJECT_TASKTABLE.TASK_ASSIGNED_DATE.eq(t.getTaskAssignedDate()),
                                PROJECT_TASKTABLE.TASK_EST_STARED.eq(t.getTaskEstStared()),
                                PROJECT_TASKTABLE.TASK_REAL_STARTED.eq(t.getTaskRealStarted()),
                                PROJECT_TASKTABLE.TASK_FINISHED_BY.eq(t.getTaskFinishedBy()),
                                PROJECT_TASKTABLE.TASK_FINISHED_DATE.eq(t.getTaskFinishedDate()),
                                PROJECT_TASKTABLE.TASK_CANCELED_BY.eq(t.getTaskCanceledBy()),
                                PROJECT_TASKTABLE.TASK_CANCELED_DATE.eq(t.getTaskCanceledDate()),
                                PROJECT_TASKTABLE.TASK_CLOSED_BY.eq(t.getTaskClosedBy()),
                                PROJECT_TASKTABLE.TASK_CLOSE_DATE.eq(t.getTaskCloseDate()),
                                PROJECT_TASKTABLE.TASK_CLOSED_REASON.eq(t.getTaskClosedReason()),
                                PROJECT_TASKTABLE.TASK_LAST_EDITED_BY.eq(t.getTaskLastEditedBy()),
                                PROJECT_TASKTABLE.TASK_LAST_EDITED_DATE.eq(t.getTaskLastEditedDate()),
                                PROJECT_TASKTABLE.TASK_DELETED.eq(t.getTaskDeleted())));
            }
        });
    }

    public int[] batchInsert(boolean autoGeneratedKeys, List<ProjectTask> projectTasks) {
        if (CollectionUtil.isEmpty(projectTasks)) {
            return new int[0];
        }
        return getDslTemplate().batchInsert(autoGeneratedKeys, projectTasks, new NoParamInsertGenerateCallback() {

            public Insert generate() {
                return insertInto(PROJECT_TASKTABLE).values(
                        PROJECT_TASKTABLE.TASK_PROJECT.value(new JdbcNamedParameter("taskProject")),
                        PROJECT_TASKTABLE.TASK_STORY.value(new JdbcNamedParameter("taskStory")),
                        PROJECT_TASKTABLE.TASK_STORY_VERSION.value(new JdbcNamedParameter("taskStoryVersion")),
                        PROJECT_TASKTABLE.TASK_FROM_BUG.value(new JdbcNamedParameter("taskFromBug")),
                        PROJECT_TASKTABLE.TASK_NAME.value(new JdbcNamedParameter("taskName")),
                        PROJECT_TASKTABLE.TASK_TYPE.value(new JdbcNamedParameter("taskType")),
                        PROJECT_TASKTABLE.TASK_PRI.value(new JdbcNamedParameter("taskPri")),
                        PROJECT_TASKTABLE.TASK_ESTIMATE.value(new JdbcNamedParameter("taskEstimate")),
                        PROJECT_TASKTABLE.TASK_CONSUMED.value(new JdbcNamedParameter("taskConsumed")),
                        PROJECT_TASKTABLE.TASK_LEFT.value(new JdbcNamedParameter("taskLeft")),
                        PROJECT_TASKTABLE.TASK_DEAD_LINE.value(new JdbcNamedParameter("taskDeadLine")),
                        PROJECT_TASKTABLE.TASK_STATUS.value(new JdbcNamedParameter("taskStatus")),
                        PROJECT_TASKTABLE.TASK_MAILTO.value(new JdbcNamedParameter("taskMailto")),
                        PROJECT_TASKTABLE.TASK_DESC.value(new JdbcNamedParameter("taskDesc")),
                        PROJECT_TASKTABLE.TASK_OPEN_BY.value(new JdbcNamedParameter("taskOpenBy")),
                        PROJECT_TASKTABLE.TASK_OPENED_DATE.value(new JdbcNamedParameter("taskOpenedDate")),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_TO.value(new JdbcNamedParameter("taskAssignedTo")),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_DATE.value(new JdbcNamedParameter("taskAssignedDate")),
                        PROJECT_TASKTABLE.TASK_EST_STARED.value(new JdbcNamedParameter("taskEstStared")),
                        PROJECT_TASKTABLE.TASK_REAL_STARTED.value(new JdbcNamedParameter("taskRealStarted")),
                        PROJECT_TASKTABLE.TASK_FINISHED_BY.value(new JdbcNamedParameter("taskFinishedBy")),
                        PROJECT_TASKTABLE.TASK_FINISHED_DATE.value(new JdbcNamedParameter("taskFinishedDate")),
                        PROJECT_TASKTABLE.TASK_CANCELED_BY.value(new JdbcNamedParameter("taskCanceledBy")),
                        PROJECT_TASKTABLE.TASK_CANCELED_DATE.value(new JdbcNamedParameter("taskCanceledDate")),
                        PROJECT_TASKTABLE.TASK_CLOSED_BY.value(new JdbcNamedParameter("taskClosedBy")),
                        PROJECT_TASKTABLE.TASK_CLOSE_DATE.value(new JdbcNamedParameter("taskCloseDate")),
                        PROJECT_TASKTABLE.TASK_CLOSED_REASON.value(new JdbcNamedParameter("taskClosedReason")),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_BY.value(new JdbcNamedParameter("taskLastEditedBy")),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_DATE.value(new JdbcNamedParameter("taskLastEditedDate")),
                        PROJECT_TASKTABLE.TASK_DELETED.value(new JdbcNamedParameter("taskDeleted")));
            }
        });
    }

    public int[] batchInsert(List<ProjectTask> projectTasks) {
        return batchInsert(true, projectTasks);
    }

    public int[] batchUpdate(List<ProjectTask> projectTasks) {
        if (CollectionUtil.isEmpty(projectTasks)) {
            return new int[0];
        }
        return getDslTemplate().batchUpdate(projectTasks, new NoParamUpdateGenerateCallback() {

            public Update generate() {
                return update(PROJECT_TASKTABLE).set(
                        PROJECT_TASKTABLE.TASK_PROJECT.value(new JdbcNamedParameter("taskProject")),
                        PROJECT_TASKTABLE.TASK_STORY.value(new JdbcNamedParameter("taskStory")),
                        PROJECT_TASKTABLE.TASK_STORY_VERSION.value(new JdbcNamedParameter("taskStoryVersion")),
                        PROJECT_TASKTABLE.TASK_FROM_BUG.value(new JdbcNamedParameter("taskFromBug")),
                        PROJECT_TASKTABLE.TASK_NAME.value(new JdbcNamedParameter("taskName")),
                        PROJECT_TASKTABLE.TASK_TYPE.value(new JdbcNamedParameter("taskType")),
                        PROJECT_TASKTABLE.TASK_PRI.value(new JdbcNamedParameter("taskPri")),
                        PROJECT_TASKTABLE.TASK_ESTIMATE.value(new JdbcNamedParameter("taskEstimate")),
                        PROJECT_TASKTABLE.TASK_CONSUMED.value(new JdbcNamedParameter("taskConsumed")),
                        PROJECT_TASKTABLE.TASK_LEFT.value(new JdbcNamedParameter("taskLeft")),
                        PROJECT_TASKTABLE.TASK_DEAD_LINE.value(new JdbcNamedParameter("taskDeadLine")),
                        PROJECT_TASKTABLE.TASK_STATUS.value(new JdbcNamedParameter("taskStatus")),
                        PROJECT_TASKTABLE.TASK_MAILTO.value(new JdbcNamedParameter("taskMailto")),
                        PROJECT_TASKTABLE.TASK_DESC.value(new JdbcNamedParameter("taskDesc")),
                        PROJECT_TASKTABLE.TASK_OPEN_BY.value(new JdbcNamedParameter("taskOpenBy")),
                        PROJECT_TASKTABLE.TASK_OPENED_DATE.value(new JdbcNamedParameter("taskOpenedDate")),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_TO.value(new JdbcNamedParameter("taskAssignedTo")),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_DATE.value(new JdbcNamedParameter("taskAssignedDate")),
                        PROJECT_TASKTABLE.TASK_EST_STARED.value(new JdbcNamedParameter("taskEstStared")),
                        PROJECT_TASKTABLE.TASK_REAL_STARTED.value(new JdbcNamedParameter("taskRealStarted")),
                        PROJECT_TASKTABLE.TASK_FINISHED_BY.value(new JdbcNamedParameter("taskFinishedBy")),
                        PROJECT_TASKTABLE.TASK_FINISHED_DATE.value(new JdbcNamedParameter("taskFinishedDate")),
                        PROJECT_TASKTABLE.TASK_CANCELED_BY.value(new JdbcNamedParameter("taskCanceledBy")),
                        PROJECT_TASKTABLE.TASK_CANCELED_DATE.value(new JdbcNamedParameter("taskCanceledDate")),
                        PROJECT_TASKTABLE.TASK_CLOSED_BY.value(new JdbcNamedParameter("taskClosedBy")),
                        PROJECT_TASKTABLE.TASK_CLOSE_DATE.value(new JdbcNamedParameter("taskCloseDate")),
                        PROJECT_TASKTABLE.TASK_CLOSED_REASON.value(new JdbcNamedParameter("taskClosedReason")),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_BY.value(new JdbcNamedParameter("taskLastEditedBy")),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_DATE.value(new JdbcNamedParameter("taskLastEditedDate")),
                        PROJECT_TASKTABLE.TASK_DELETED.value(new JdbcNamedParameter("taskDeleted"))).where(
                        PROJECT_TASKTABLE.TASK_ID.eq(new JdbcNamedParameter("taskId")));
            }
        });
    }

    public int[] batchDelete(List<ProjectTask> projectTasks) {
        if (CollectionUtil.isEmpty(projectTasks)) {
            return new int[0];
        }
        return getDslTemplate().batchDelete(projectTasks, new NoParamDeleteGenerateCallback() {

            public Delete generate() {
                return delete(PROJECT_TASKTABLE).where(and(
                        PROJECT_TASKTABLE.TASK_ID.eq(new JdbcNamedParameter("taskId")),
                        PROJECT_TASKTABLE.TASK_PROJECT.eq(new JdbcNamedParameter("taskProject")),
                        PROJECT_TASKTABLE.TASK_STORY.eq(new JdbcNamedParameter("taskStory")),
                        PROJECT_TASKTABLE.TASK_STORY_VERSION.eq(new JdbcNamedParameter("taskStoryVersion")),
                        PROJECT_TASKTABLE.TASK_FROM_BUG.eq(new JdbcNamedParameter("taskFromBug")),
                        PROJECT_TASKTABLE.TASK_NAME.eq(new JdbcNamedParameter("taskName")),
                        PROJECT_TASKTABLE.TASK_TYPE.eq(new JdbcNamedParameter("taskType")),
                        PROJECT_TASKTABLE.TASK_PRI.eq(new JdbcNamedParameter("taskPri")),
                        PROJECT_TASKTABLE.TASK_ESTIMATE.eq(new JdbcNamedParameter("taskEstimate")),
                        PROJECT_TASKTABLE.TASK_CONSUMED.eq(new JdbcNamedParameter("taskConsumed")),
                        PROJECT_TASKTABLE.TASK_LEFT.eq(new JdbcNamedParameter("taskLeft")),
                        PROJECT_TASKTABLE.TASK_DEAD_LINE.eq(new JdbcNamedParameter("taskDeadLine")),
                        PROJECT_TASKTABLE.TASK_STATUS.eq(new JdbcNamedParameter("taskStatus")),
                        PROJECT_TASKTABLE.TASK_MAILTO.eq(new JdbcNamedParameter("taskMailto")),
                        PROJECT_TASKTABLE.TASK_DESC.eq(new JdbcNamedParameter("taskDesc")),
                        PROJECT_TASKTABLE.TASK_OPEN_BY.eq(new JdbcNamedParameter("taskOpenBy")),
                        PROJECT_TASKTABLE.TASK_OPENED_DATE.eq(new JdbcNamedParameter("taskOpenedDate")),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_TO.eq(new JdbcNamedParameter("taskAssignedTo")),
                        PROJECT_TASKTABLE.TASK_ASSIGNED_DATE.eq(new JdbcNamedParameter("taskAssignedDate")),
                        PROJECT_TASKTABLE.TASK_EST_STARED.eq(new JdbcNamedParameter("taskEstStared")),
                        PROJECT_TASKTABLE.TASK_REAL_STARTED.eq(new JdbcNamedParameter("taskRealStarted")),
                        PROJECT_TASKTABLE.TASK_FINISHED_BY.eq(new JdbcNamedParameter("taskFinishedBy")),
                        PROJECT_TASKTABLE.TASK_FINISHED_DATE.eq(new JdbcNamedParameter("taskFinishedDate")),
                        PROJECT_TASKTABLE.TASK_CANCELED_BY.eq(new JdbcNamedParameter("taskCanceledBy")),
                        PROJECT_TASKTABLE.TASK_CANCELED_DATE.eq(new JdbcNamedParameter("taskCanceledDate")),
                        PROJECT_TASKTABLE.TASK_CLOSED_BY.eq(new JdbcNamedParameter("taskClosedBy")),
                        PROJECT_TASKTABLE.TASK_CLOSE_DATE.eq(new JdbcNamedParameter("taskCloseDate")),
                        PROJECT_TASKTABLE.TASK_CLOSED_REASON.eq(new JdbcNamedParameter("taskClosedReason")),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_BY.eq(new JdbcNamedParameter("taskLastEditedBy")),
                        PROJECT_TASKTABLE.TASK_LAST_EDITED_DATE.eq(new JdbcNamedParameter("taskLastEditedDate")),
                        PROJECT_TASKTABLE.TASK_DELETED.eq(new JdbcNamedParameter("taskDeleted"))));
            }
        });
    }

}