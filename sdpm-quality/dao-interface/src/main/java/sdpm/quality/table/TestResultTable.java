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

public class TestResultTable extends Table {

	public static final TestResultTable TESTRESULTTABLE = new TestResultTable();
	/** 测试结果 */
	public final Column TESTRESULT_ID = new Column(this, "testResult_id");
	/** RUN */
	public final Column RUN = new Column(this, "run");
	/** 用例编号 */
	public final Column CASE_ID = new Column(this, "case_ID");
	/** 关联用例版本 */
	public final Column CASE_VERSION = new Column(this, "case_version");
	/** 测试结果 */
	public final Column CASE_RESULT = new Column(this, "case_Result");
	/** STEPRESULTS */
	public final Column CASESTEP_RESULTS = new Column(this, "caseStep_results");
	/** TESTRESULT_LASTRUNNER */
	public final Column TESTRESULT_LASTRUNNER = new Column(this, "testResult_lastRunner");
	/** TESTRESULT_DATE */
	public final Column TESTRESULT_DATE = new Column(this, "testResult_date");

		private TestResultTable() {
			super("testResult");
		}

}
