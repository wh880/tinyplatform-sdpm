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

package org.tinygroup.sdpm.project.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class TaskTable extends Table {

	public static final TaskTable TASKTABLE = new TaskTable();
	/** 任务ID */
	public final Column TASK_ID = new Column(this, "task_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 项目ID */
	public final Column PROJECT_ID = new Column(this, "project_id");
	/** 模块ID */
	public final Column MODULE_ID = new Column(this, "module_id");
	/** 任务标题 */
	public final Column TASK_NAME = new Column(this, "task_name");
	/** 任务类型 */
	public final Column TASK_TYPE = new Column(this, "task_type");
	/** 任务优先级 */
	public final Column TASK_PRI = new Column(this, "task_pri");
	/** 任务估算 */
	public final Column TASK_ESTIMATE = new Column(this, "task_estimate");
	/** 任务消耗 */
	public final Column TASK_CONSUMED = new Column(this, "task_consumed");
	/** 任务剩余 */
	public final Column TASK_LEFT = new Column(this, "task_left");
	/** 任务期限 */
	public final Column TASK_DEADLINE = new Column(this, "task_deadline");
	/** 任务状态 */
	public final Column TASK_STATUS = new Column(this, "task_status");
	/** 邮件列表 */
	public final Column TASK_MAILTO = new Column(this, "task_mailto");
	/** 任务描述 */
	public final Column TASK_DESC = new Column(this, "task_desc");
	/** TASK_OPEN_COUNT */
	public final Column TASK_OPEN_COUNT = new Column(this, "task_open_count");
	/** 任务创建者 */
	public final Column TASK_OPENED_BY = new Column(this, "task_opened_by");
	/** 任务创建日期 */
	public final Column TASK_OPENED_DATE = new Column(this, "task_opened_date");
	/** 任务指派人 */
	public final Column TASK_ASSIGNED_TO = new Column(this, "task_assigned_to");
	/** 任务指派日期 */
	public final Column TASK_ASSIGNED_DATE = new Column(this, "task_assigned_date");
	/** 任务预计开始日期 */
	public final Column TASK_EST_STARTED = new Column(this, "task_est_started");
	/** 任务实际开始日期 */
	public final Column TASK_REAL_STARTED = new Column(this, "task_real_started");
	/** 任务完成者 */
	public final Column TASK_FINISHED_BY = new Column(this, "task_finished_by");
	/** 任务完成日期 */
	public final Column TASK_FINISHED_DATE = new Column(this, "task_finished_date");
	/** 任务取消者 */
	public final Column TASK_CANCELED_BY = new Column(this, "task_canceled_by");
	/** 任务取消日期 */
	public final Column TASK_CANCELED_DATE = new Column(this, "task_canceled_date");
	/** 任务关闭者 */
	public final Column TASK_CLOSED_BY = new Column(this, "task_closed_by");
	/** 任务关闭日期 */
	public final Column TASK_CLOSED_DATE = new Column(this, "task_closed_date");
	/** 任务关闭原因 */
	public final Column TASK_CLOSED_REASON = new Column(this, "task_closed_reason");
	/** 最后编辑者 */
	public final Column TASK_LAST_EDITED_BY = new Column(this, "task_last_edited_by");
	/** 最后编辑日期 */
	public final Column TASK_LAST_EDITED_DATE = new Column(this, "task_last_edited_date");
	/** 紧前任务ID */
	public final Column TASK_F_S = new Column(this, "task_f_s");
	/** 同启任务ID */
	public final Column TASK_S_S = new Column(this, "task_s_s");
	/** 同完任务ID */
	public final Column TASK_F_F = new Column(this, "task_f_f");
	/** 紧后任务ID */
	public final Column TASK_S_F = new Column(this, "task_s_f");
	/** 客户请求ID */
	public final Column REQUEST_ID = new Column(this, "request_id");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private TaskTable() {
			super("task");
		}

}
