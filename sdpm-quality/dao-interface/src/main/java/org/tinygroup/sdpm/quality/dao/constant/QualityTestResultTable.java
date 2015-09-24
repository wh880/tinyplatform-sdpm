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
 * 测试结果表
 * 
 */
public class QualityTestResultTable extends Table {

	public static final QualityTestResultTable QUALITY_TESTRESULTTABLE = new QualityTestResultTable();

	/** 
	 * 测试结果
	 * 
	 */
	public final Column TESTRESULT_ID = new Column(this, "testResult_id");

	/** 
	 * TESTRESULT_RUN
	 * 
	 */
	public final Column TESTRESULT_RUN = new Column(this, "testresult_run");

	/** 
	 * 相关用例
	 * 
	 */
	public final Column LINK_CASE = new Column(this, "link_case");

	/** 
	 * 关联用例版本
	 * 
	 */
	public final Column CASE_VERSION = new Column(this, "case_version");

	/** 
	 * 测试结果
	 * 
	 */
	public final Column CASE_RESULT = new Column(this, "case_Result");

	/** 
	 * 用例步骤结果
	 * 
	 */
	public final Column CASE_STEPRESULTS = new Column(this, "case_stepresults");

	/** 
	 * 最后执行人
	 * 
	 */
	public final Column TESTRESULT_LASTRUNNER = new Column(this, "testResult_lastRunner");

	/** 
	 * 最后执行日期
	 * 
	 */
	public final Column TESTRESULT_DATE = new Column(this, "testResult_date");


	private QualityTestResultTable() {
		super("quality_testResult");
	}

}
