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
 * 任务预计
 * 
 */
public class TaskestimateTable extends Table {

	public static final TaskestimateTable TASKESTIMATETABLE = new TaskestimateTable();

	/** 
	 * 任务预计id
	 * 
	 */
	public final Column TASKESTIMATE_ID = new Column(this, "taskestimate_id");

	/** 
	 * 任务id
	 * 
	 */
	public final Column TASK_ID = new Column(this, "task_id");

	/** 
	 * 任务预计时间
	 * 
	 */
	public final Column TASKESTIMATE_DATE = new Column(this, "taskestimate_date");

	/** 
	 * 任务预计剩余
	 * 
	 */
	public final Column TASKESTIMATE_LEFT = new Column(this, "taskestimate_left");

	/** 
	 * 任务预计消耗
	 * 
	 */
	public final Column TASKESTIMATE_CONSUMED = new Column(this, "taskestimate_consumed");

	/** 
	 * 操作人帐号
	 * 
	 */
	public final Column TASKESTIMATE_ACCOUNT = new Column(this, "taskestimate_account");

	/** 
	 * 备用字段
	 * 
	 */
	public final Column TASKESTIMATE_WORK = new Column(this, "taskestimate_work");


		private TaskestimateTable() {
			super("taskestimate");
		}

}
