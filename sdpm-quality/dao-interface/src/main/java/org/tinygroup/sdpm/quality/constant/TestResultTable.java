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

public class TestResultTable extends Table {

	public static final TestResultTable TESTRESULTTABLE = new TestResultTable();
	/** 测试结果ID */
	public final Column TEST_RESULT_ID = new Column(this, "test_result_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 测试执行ID */
	public final Column RUN_ID = new Column(this, "run_id");
	/** 关联用例ID */
	public final Column CASE_ID = new Column(this, "case_id");
	/** 关联用例版本 */
	public final Column CASE_VERSION = new Column(this, "case_version");
	/** 用例结果 */
	public final Column CASE_RESULT = new Column(this, "case_result");
	/** 用例步骤结果 */
	public final Column CASE_STEP_RESULTS = new Column(this, "case_step_results");
	/** 测试最后执行者 */
	public final Column TEST_RESULT_LAST_RUNNER = new Column(this, "test_result_last_runner");
	/** 测试结果日期 */
	public final Column TEST_RESULT_DATE = new Column(this, "test_result_date");

		private TestResultTable() {
			super("testResult");
		}

}
