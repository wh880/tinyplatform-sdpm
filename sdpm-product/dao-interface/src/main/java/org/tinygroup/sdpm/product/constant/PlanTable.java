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

package org.tinygroup.sdpm.product.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class PlanTable extends Table {

	public static final PlanTable PLANTABLE = new PlanTable();
	/** 发布计划ID */
	public final Column PLAN_ID = new Column(this, "plan_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 产品ID */
	public final Column PRODUCT_ID = new Column(this, "product_id");
	/** 规划版本名称 */
	public final Column PLAN_NAME = new Column(this, "plan_name");
	/** 规划描述 */
	public final Column PLAN_SPEC = new Column(this, "plan_spec");
	/** 规划开始时间 */
	public final Column PLAN_BEGIN_DATE = new Column(this, "plan_begin_date");
	/** 规划结束时间 */
	public final Column PLAN_END_DATE = new Column(this, "plan_end_date");
	/** 集成版本ID */
	public final Column BUILD_ID = new Column(this, "build_id");
	/** 发放日期 */
	public final Column PLAN_RELEASE_DATE = new Column(this, "plan_release_date");
	/** 版本需求列表 */
	public final Column PLAN_STORIES = new Column(this, "plan_stories");
	/** 版本缺陷列表 */
	public final Column PLAN_BUGS = new Column(this, "plan_bugs");
	/** 规划投入 */
	public final Column PLAN_EFFORT = new Column(this, "plan_effort");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private PlanTable() {
			super("plan");
		}

}
