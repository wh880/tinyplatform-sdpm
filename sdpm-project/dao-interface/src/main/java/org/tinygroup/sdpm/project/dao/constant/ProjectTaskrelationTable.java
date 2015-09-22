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
 * TASKRELATION
 * 
 */
public class ProjectTaskrelationTable extends Table {

	public static final ProjectTaskrelationTable PROJECT_TASKRELATIONTABLE = new ProjectTaskrelationTable();

	/**
	 * 逻辑ID
	 */
	public final Column ID = new Column(this, "id");

	/**
	 * 前置任务
	 */
	public final Column PER_TASK = new Column(this, "per_task");

	/**
	 * 前置条件
	 * <p/>
	 * 0-开始后，1-完成后
	 */
	public final Column TASKRELATION_CONDITON = new Column(this, "taskrelation_conditon");

	/**
	 * 后置任务
	 */
	public final Column AFTER_TASK = new Column(this, "after_task");

	/**
	 * 动作
	 * <p/>
	 * 0-才能开始，1-才能完成
	 */
	public final Column TASKRELATION_ACTION = new Column(this, "taskrelation_action");


	private ProjectTaskrelationTable() {
		super("project_taskrelation");
	}

}
