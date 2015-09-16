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

public class TestPlanTable extends Table {

	public static final TestPlanTable TESTPLANTABLE = new TestPlanTable();
	/** 测试计划ID */
	public final Column TESTPLAN_ID = new Column(this, "testplan_ID");
	/** 任务名称 */
	public final Column TESTPLAN_NAME = new Column(this, "testplan_name");
	/** 所属产品 */
	public final Column PRODUCT_ID = new Column(this, "product_ID");
	/** 所属项目 */
	public final Column PROJECT_ID = new Column(this, "project_ID");
	/** 版本 */
	public final Column BUILD = new Column(this, "build");
	/** 负责人 */
	public final Column TESTPLAN_OWNER = new Column(this, "testplan_owner");
	/** 优先级 */
	public final Column PRIORITY = new Column(this, "priority");
	/** 开始日期 */
	public final Column TESTPLAN_BEGIN = new Column(this, "testplan_begin");
	/** 结束日期 */
	public final Column TESTPLAN_END = new Column(this, "testplan_end");
	/** 描述 */
	public final Column TESTPLAN_DESC = new Column(this, "testplan_desc");
	/** 测试总结 */
	public final Column TESTPLAN_REPORT = new Column(this, "testplan_report");
	/** 当前状态 */
	public final Column TESTPLAN_STATUS = new Column(this, "testplan_status");
	/** 已删除 */
	public final Column DELETED = new Column(this, "deleted");

		private TestPlanTable() {
			super("testPlan");
		}

}
