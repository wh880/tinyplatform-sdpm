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

package org.tinygroup.sdpm.quality.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class TestRunTable extends Table {

	public static final TestRunTable TESTRUNTABLE = new TestRunTable();
	/** 测试执行ID */
	public final Column TESTRUN_ID = new Column(this, "testrun_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 测试计划ID */
	public final Column TESTPLAN_ID = new Column(this, "testplan_id");
	/** 关联用例ID */
	public final Column CASE_ID = new Column(this, "case_id");
	/** 关联用例版本 */
	public final Column CASE_VERSION = new Column(this, "case_version");
	/** 测试执行指派给 */
	public final Column TESTRUN_ASSIGNED_TO = new Column(this, "testrun_assigned_to");
	/** 测试执行者 */
	public final Column TESTRUN_LAST_RUNNER = new Column(this, "testrun_last_runner");
	/** 测试执行日期 */
	public final Column TESTRUN_LAST_RUN_DATE = new Column(this, "testrun_last_run_date");
	/** 测试执行结果 */
	public final Column TESTRUN_LAST_RUN_RESULT = new Column(this, "testrun_last_run_result");
	/** 测试执行状态 */
	public final Column TESTRUN_STATUS = new Column(this, "testrun_status");

		private TestRunTable() {
			super("testRun");
		}

}
