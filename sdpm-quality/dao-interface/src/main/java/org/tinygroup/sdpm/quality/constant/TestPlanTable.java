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

public class TestPlanTable extends Table {

	public static final TestPlanTable TESTPLANTABLE = new TestPlanTable();
	/** 测试计划ID */
	public final Column TESTPLAN_ID = new Column(this, "testplan_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 测试计划名称 */
	public final Column TESTPLAN_NAME = new Column(this, "testplan_name");
	/** 产品ID */
	public final Column PRODUCT_ID = new Column(this, "product_id");
	/** 项目ID */
	public final Column PROJECT_ID = new Column(this, "project_id");
	/** 集成版本ID */
	public final Column BUILD_ID = new Column(this, "build_id");
	/** 负责人 */
	public final Column TESTPLAN_OWNER = new Column(this, "testplan_owner");
	/** 测试计划开始日期 */
	public final Column TESTPLAN_BEGIN = new Column(this, "testplan_begin");
	/** 测试计划结束日期 */
	public final Column TESTPLAN_END = new Column(this, "testplan_end");
	/** 测试计划描述 */
	public final Column TESTPLAN_DESC = new Column(this, "testplan_desc");
	/** 测试计划状态 */
	public final Column TESTPLAN_STATUS = new Column(this, "testplan_status");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private TestPlanTable() {
			super("testPlan");
		}

}
