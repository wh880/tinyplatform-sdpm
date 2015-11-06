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

package org.tinygroup.sdpm.project.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/** 
 * 任务
 * 
 */
public class ProjectTaskTable extends Table {

	public static final ProjectTaskTable PROJECT_TASKTABLE = new ProjectTaskTable();

	/** 
	 * 任务id
	 * 
	 */
	public final Column TASK_ID = new Column(this, "task_id");

	/** 
	 * 任务所属项目
	 * 
	 */
	public final Column TASK_PROJECT = new Column(this, "task_project");

	/** 
	 * 任务相关需求
	 * 
	 */
	public final Column TASK_STORY = new Column(this, "task_story");

	/** 
	 * 需求版本
	 * 
	 * 需求变更后自动增长
	 */
	public final Column TASK_STORY_VERSION = new Column(this, "task_story_version");

	/**
	 *
	 */
	public final Column TASK_MOMODULE = new Column(this, "task_module");
	/** 
	 * 来源bug
	 * 
	 */
	public final Column TASK_FROM_BUG = new Column(this, "task_from_bug");

	/** 
	 * 任务名称
	 * 
	 */
	public final Column TASK_NAME = new Column(this, "task_name");

	/** 
	 * 任务类型
	 * 
	 */
	public final Column TASK_TYPE = new Column(this, "task_type");

	/** 
	 * 任务优先级
	 * 
	 * 1，2，4，5
	 * 递增
	 */
	public final Column TASK_PRI = new Column(this, "task_pri");

	/** 
	 * 最初预计
	 * 
	 */
	public final Column TASK_ESTIMATE = new Column(this, "task_estimate");

	/** 
	 * 总消耗
	 * 
	 */
	public final Column TASK_CONSUMED = new Column(this, "task_consumed");

	/** 
	 * 预计剩余
	 * 
	 */
	public final Column TASK_LEFT = new Column(this, "task_left");

	/** 
	 * 截止日期
	 * 
	 */
	public final Column TASK_DEAD_LINE = new Column(this, "task_dead_line");

	/** 
	 * 任务状态
	 * 
	 * 0-等待，1-未开始，2-进行中，3-已完成，4-已暂停，5-已取消,6-已关闭
	 */
	public final Column TASK_STATUS = new Column(this, "task_status");

	/** 
	 * 抄送给
	 * 
	 */
	public final Column TASK_MAILTO = new Column(this, "task_mailto");

	/** 
	 * 任务描述
	 * 
	 */
	public final Column TASK_DESC = new Column(this, "task_desc");

	/** 
	 * 由谁创建
	 * 
	 */
	public final Column TASK_OPEN_BY = new Column(this, "task_open_by");

	/** 
	 * 创建日期
	 * 
	 */
	public final Column TASK_OPENED_DATE = new Column(this, "task_opened_date");

	/** 
	 * 指派给
	 * 
	 */
	public final Column TASK_ASSIGNED_TO = new Column(this, "task_assigned_to");

	/** 
	 * 指派日期
	 * 
	 */
	public final Column TASK_ASSIGNED_DATE = new Column(this, "task_assigned_date");

	/** 
	 * 预计开始
	 * 
	 */
	public final Column TASK_EST_STARED = new Column(this, "task_est_stared");

	/** 
	 * 实际开始
	 * 
	 */
	public final Column TASK_REAL_STARTED = new Column(this, "task_real_started");

	/** 
	 * 由谁完成
	 * 
	 */
	public final Column TASK_FINISHED_BY = new Column(this, "task_finished_by");

	/** 
	 * 完成时间
	 * 
	 */
	public final Column TASK_FINISHED_DATE = new Column(this, "task_finished_date");

	/** 
	 * 由谁取消
	 * 
	 */
	public final Column TASK_CANCELED_BY = new Column(this, "task_canceled_by");

	/** 
	 * 取消时间
	 * 
	 */
	public final Column TASK_CANCELED_DATE = new Column(this, "task_canceled_date");

	/** 
	 * 由谁关闭
	 * 
	 */
	public final Column TASK_CLOSED_BY = new Column(this, "task_closed_by");

	/** 
	 * 关闭时间
	 * 
	 */
	public final Column TASK_CLOSE_DATE = new Column(this, "task_close_date");

	/** 
	 * 关闭原因
	 * 
	 */
	public final Column TASK_CLOSED_REASON = new Column(this, "task_closed_reason");

	/** 
	 * 最后修改
	 * 
	 */
	public final Column TASK_LAST_EDITED_BY = new Column(this, "task_last_edited_by");

	/** 
	 * 最后修改日期
	 * 
	 */
	public final Column TASK_LAST_EDITED_DATE = new Column(this, "task_last_edited_date");

	/** 
	 * 已删除
	 * 
	 * 0-未删除，1-已删除
	 */
	public final Column TASK_DELETED = new Column(this, "task_deleted");


	public ProjectTaskTable() {
		super("project_task");
	}

}
