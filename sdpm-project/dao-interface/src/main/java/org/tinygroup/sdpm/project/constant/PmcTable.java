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

package org.tinygroup.sdpm.project.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class PmcTable extends Table {

	public static final PmcTable PMCTABLE = new PmcTable();
	/** 项目监控ID */
	public final Column PMC_ID = new Column(this, "pmc_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 项目ID */
	public final Column PROJECT_ID = new Column(this, "project_id");
	/** 统计日期 */
	public final Column PMC_DATE = new Column(this, "pmc_date");
	/** 剩余工作量 */
	public final Column PMC_LEFT = new Column(this, "pmc_left");
	/** 剩余任务数 */
	public final Column PMC_TASKNUM = new Column(this, "pmc_tasknum");
	/** 消耗工作量 */
	public final Column PMC_AC = new Column(this, "pmc_ac");
	/** 计划值 */
	public final Column PMC_PV = new Column(this, "pmc_pv");
	/** 挣值 */
	public final Column PMC_EV = new Column(this, "pmc_ev");
	/** 成本偏差 */
	public final Column PMC_CPI = new Column(this, "pmc_cpi");
	/** 进度偏差 */
	public final Column PMC_SPI = new Column(this, "pmc_spi");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private PmcTable() {
			super("pmc");
		}

}
