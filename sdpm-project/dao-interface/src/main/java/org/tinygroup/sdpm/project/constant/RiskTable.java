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

public class RiskTable extends Table {

	public static final RiskTable RISKTABLE = new RiskTable();
	/** 风险ID */
	public final Column RISK_ID = new Column(this, "risk_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 项目ID */
	public final Column PROJECT_ID = new Column(this, "project_id");
	/** 风险识别人 */
	public final Column RISK_OPENED_BY = new Column(this, "risk_opened_by");
	/** 风险识别日期 */
	public final Column RISK_OPEN_DATE = new Column(this, "risk_open_date");
	/** 风险标题 */
	public final Column RISK_TITLE = new Column(this, "risk_title");
	/** 风险描述 */
	public final Column RISK_SPEC = new Column(this, "risk_spec");
	/** 风险分类 */
	public final Column RISK_CATEGORY = new Column(this, "risk_category");
	/** 风险可能性 */
	public final Column RISK_PROBABILITY = new Column(this, "risk_probability");
	/** 风险影响 */
	public final Column RISK_IMPACT = new Column(this, "risk_impact");
	/** 风险值 */
	public final Column RISK_EXPOSURE = new Column(this, "risk_exposure");
	/** 风险预期阶段 */
	public final Column RISK_EXP_PHASE = new Column(this, "risk_exp_phase");
	/** 风险规避计划 */
	public final Column RISK_AVOIDANCE_A_P = new Column(this, "risk_avoidance_a_p");
	/** 风险缓解计划 */
	public final Column RISK_MITIGATION_A_P = new Column(this, "risk_mitigation_a_p");
	/** 风险承受计划 */
	public final Column RISK_CONTINGENCY_A_P = new Column(this, "risk_contingency_a_p");
	/** 风险计划结果 */
	public final Column RISK_ACTION_RESULT = new Column(this, "risk_action_result");
	/** 风险责任人 */
	public final Column RISK_OWNER = new Column(this, "risk_owner");
	/** 风险状态 */
	public final Column RISK_STATUS = new Column(this, "risk_status");
	/** 风险跟踪触发 */
	public final Column RISK_CHECK_TRIGGER = new Column(this, "risk_check_trigger");
	/** 风险最后编辑者 */
	public final Column RISK_LAST_EDITED_BY = new Column(this, "risk_last_edited_by");
	/** 风险计划日期 */
	public final Column RISK_PLAN_DATE = new Column(this, "risk_plan_date");
	/** 风险最后编辑日期 */
	public final Column RISK_LAST_EDIT_DATE = new Column(this, "risk_last_edit_date");
	/** 风险关闭者 */
	public final Column RISK_CLOSED_BY = new Column(this, "risk_closed_by");
	/** 风险关闭日期 */
	public final Column RISK_CLOSE_DATE = new Column(this, "risk_close_date");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private RiskTable() {
			super("risk");
		}

}
