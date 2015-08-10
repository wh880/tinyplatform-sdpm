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

public class TestCaseTable extends Table {

	public static final TestCaseTable TESTCASETABLE = new TestCaseTable();
	/** 关联用例ID */
	public final Column CASE_ID = new Column(this, "case_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 产品ID */
	public final Column PRODUCT_ID = new Column(this, "product_id");
	/** 需求ID */
	public final Column STORY_ID = new Column(this, "story_id");
	/** 需求版本 */
	public final Column STORY_VERSION = new Column(this, "story_version");
	/** 模块ID */
	public final Column MODULE_ID = new Column(this, "module_id");
	/** 用例标题 */
	public final Column CASE_TITLE = new Column(this, "case_title");
	/** 前置条件 */
	public final Column CASE_PRECONDITION = new Column(this, "case_precondition");
	/** 关键字 */
	public final Column CASE_KEYWORDS = new Column(this, "case_keywords");
	/** 优先级 */
	public final Column CASE_PRI = new Column(this, "case_pri");
	/** 用例类型 */
	public final Column CASE_TYPE = new Column(this, "case_type");
	/** 使用阶段 */
	public final Column CASE_STAGE = new Column(this, "case_stage");
	/** 用例状态 */
	public final Column CASE_STATUS = new Column(this, "case_status");
	/** 执行次数 */
	public final Column CASE_RUN = new Column(this, "case_run");
	/** 用例序号 */
	public final Column CASE_ORDER = new Column(this, "case_order");
	/** 关联用例版本 */
	public final Column CASE_VERSION = new Column(this, "case_version");
	/** 关联用例ID */
	public final Column LINKCASE_ID = new Column(this, "linkcase_id");
	/** 建自缺陷ID */
	public final Column FROM_BUG_ID = new Column(this, "from_bug_id");
	/** 用例创建人 */
	public final Column CASE_OPENED_BY = new Column(this, "case_opened_by");
	/** 用例创建日期 */
	public final Column CASE_OPENED_DATE = new Column(this, "case_opened_date");
	/** 用例最后编辑者 */
	public final Column CASE_LAST_EDITED_BY = new Column(this, "case_last_edited_by");
	/** 用例最后编辑日期 */
	public final Column CASE_LAST_EDITED_DATE = new Column(this, "case_last_edited_date");
	/** 用例最后执行者 */
	public final Column CASE_LAST_RUNNER = new Column(this, "case_last_runner");
	/** 用例最后执行日期 */
	public final Column CASE_LAST_RUN_DATE = new Column(this, "case_last_run_date");
	/** 用例最后执行结果 */
	public final Column CASE_LAST_RUN_RESULT = new Column(this, "case_last_run_result");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private TestCaseTable() {
			super("testCase");
		}

}
