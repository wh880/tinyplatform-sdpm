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

package org.tinygroup.sdpm.quality.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/** 
 * 测试计划执行表
 * 
 */
public class QualityTestRunTable extends Table {

	public static final QualityTestRunTable QUALITY_TESTRUNTABLE = new QualityTestRunTable();

	/** 
	 * 执行编号
	 * 
	 */
	public final Column TEST_RUN_ID = new Column(this, "test_run_id");

	/** 
	 * 任务id
	 * 
	 */
	public final Column TASK_ID = new Column(this, "task_id");

	/** 
	 * 用例编号
	 * 
	 */
	public final Column CASE_ID = new Column(this, "case_id");

	/** 
	 * 关联用例版本
	 * 
	 */
	public final Column CASE_VERSION = new Column(this, "case_version");

	/** 
	 * 指派给
	 * 
	 */
	public final Column TEST_RUN_ASSIGNED_TO = new Column(this, "test_run_assigned_to");

	/** 
	 * 最后执行人
	 * 
	 */
	public final Column TEST_RUN_LAST_RUNNER = new Column(this, "test_run_last_runner");

	/** 
	 * 最后执行日期
	 * 
	 */
	public final Column TEST_RUN_LAST_RUN_DATE = new Column(this, "test_run_last_run_date");

	/** 
	 * 最后执行结果
	 * 
	 */
	public final Column TEST_RUN_LAST_RUN_RESULT = new Column(this, "test_run_last_run_result");

	/** 
	 * 测试执行状态
	 * 
	 */
	public final Column TEST_RUN_STATUS = new Column(this, "test_run_status");


	private QualityTestRunTable() {
		super("quality_testRun");
	}

}
