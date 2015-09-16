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

package sdpm.quality.table;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class TestRunTable extends Table {

	public static final TestRunTable TESTRUNTABLE = new TestRunTable();
	/** 执行编号 */
	public final Column TESTRUN_ID = new Column(this, "testRun_id");
	/** 相关任务 */
	public final Column TASK_ID = new Column(this, "task_ID");
	/** 用例编号 */
	public final Column CASE_ID = new Column(this, "case_ID");
	/** 关联用例版本 */
	public final Column CASE_VERSION = new Column(this, "case_version");
	/** 指派给 */
	public final Column TESTRUN_ASSIGNEDTO = new Column(this, "testRun_assignedTo");
	/** TESTRUN_LASTRUNNER */
	public final Column TESTRUN_LASTRUNNER = new Column(this, "testRun_lastRunner");
	/** 最后执行日期 */
	public final Column TESTRUN_LASTRUNDATE = new Column(this, "testRun_lastRunDate");
	/** 最后执行结果 */
	public final Column TESTRUN_LASTRUNRESULT = new Column(this, "testRun_lastRunResult");
	/** STATUS */
	public final Column TESTRUN_STATUS = new Column(this, "testRun_status");

		private TestRunTable() {
			super("testRun");
		}

}
