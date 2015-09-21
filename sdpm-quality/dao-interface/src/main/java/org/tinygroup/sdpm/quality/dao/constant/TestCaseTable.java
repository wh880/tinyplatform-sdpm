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
 * 测试用例表
 * 
 */
public class TestCaseTable extends Table {

	public static final TestCaseTable TESTCASETABLE = new TestCaseTable();

	/** 
	 * 用例编号
	 * 
	 */
	public final Column CASE_ID = new Column(this, "case_id");

	/** 
	 * 产品ID
	 * 
	 */
	public final Column PRODUCT_ID = new Column(this, "product_id");

	/** 
	 * 模块ID
	 * 
	 */
	public final Column MODULE_ID = new Column(this, "module_id");

	/** 
	 * CASE_PATH
	 * 
	 */
	public final Column CASE_PATH = new Column(this, "case_path");

	/** 
	 * 需求ID
	 * 
	 */
	public final Column STORY_ID = new Column(this, "story_id");

	/** 
	 * 需求版本
	 * 
	 */
	public final Column STORY_VERSION = new Column(this, "story_version");

	/** 
	 * 用例标题
	 * 
	 */
	public final Column CASE_TITLE = new Column(this, "case_title");

	/** 
	 * 前置条件
	 * 
	 */
	public final Column CASE_PRECONDITION = new Column(this, "case_precondition");

	/** 
	 * 关键词
	 * 
	 */
	public final Column CASE_KEYWORDS = new Column(this, "case_keywords");

	/** 
	 * 优先级
	 * 
	 */
	public final Column PRIORITY = new Column(this, "priority");

	/** 
	 * 用例类型
	 * 
	 */
	public final Column CASE_TYPE = new Column(this, "case_type");

	/** 
	 * 适用阶段
	 * 
	 */
	public final Column CASE_STAGE = new Column(this, "case_stage");

	/** 
	 * 执行方式
	 * 
	 */
	public final Column CASE_RUNWAY = new Column(this, "case_runway");

	/** 
	 * 由谁编写
	 * 
	 */
	public final Column CASE_SCRIPTEDBY = new Column(this, "case_scriptedBy");

	/** 
	 * 编写日期
	 * 
	 */
	public final Column CASE_SCRIPTEDDATE = new Column(this, "case_scriptedDate");

	/** 
	 * SCRIPTSTATUS
	 * 
	 */
	public final Column SCRIPTSTATUS = new Column(this, "scriptStatus");

	/** 
	 * SCRIPTLOCATION
	 * 
	 */
	public final Column SCRIPTLOCATION = new Column(this, "scriptLocation");

	/** 
	 * 用例状态
	 * 
	 */
	public final Column CASE_STATUS = new Column(this, "case_status");

	/** 
	 * 执行频率
	 * 
	 */
	public final Column CASE_FREQUENCY = new Column(this, "case_frequency");

	/** 
	 * 排序
	 * 
	 */
	public final Column CASE_ORDER = new Column(this, "case_order");

	/** 
	 * 由谁创建
	 * 
	 */
	public final Column CASE_OPENEDBY = new Column(this, "case_openedBy");

	/** 
	 * 创建日期
	 * 
	 */
	public final Column CASE_OPENEDDATE = new Column(this, "case_openedDate");

	/** 
	 * 最后修改者
	 * 
	 */
	public final Column CASE_LASTEDITEDBY = new Column(this, "case_lastEditedBy");

	/** 
	 * 最后修改日期
	 * 
	 */
	public final Column CASE_LASTEDITEDDATE = new Column(this, "case_lastEditedDate");

	/** 
	 * 关联用例版本
	 * 
	 */
	public final Column CASE_VERSION = new Column(this, "case_version");

	/** 
	 * 相关用例
	 * 
	 */
	public final Column LINK_CASE = new Column(this, "link_case");

	/** 
	 * 来源Bug
	 * 
	 */
	public final Column CASE_FROMBUG = new Column(this, "case_fromBug");

	/** 
	 * 已删除
	 * 
	 */
	public final Column DELETED = new Column(this, "deleted");

	/** 
	 * 最后执行人
	 * 
	 */
	public final Column CASE_LASTRUNNER = new Column(this, "case_lastRunner");

	/** 
	 * 最后执行时间
	 * 
	 */
	public final Column CASE_LASTRUNDATE = new Column(this, "case_lastRunDate");

	/** 
	 * 用例执行结果
	 * 
	 */
	public final Column CASE_LASTRUNRESULT = new Column(this, "case_lastRunResult");


	private TestCaseTable() {
		super("testCase");
	}

}
