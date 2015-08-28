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

public class ProgramTable extends Table {

	public static final ProgramTable PROGRAMTABLE = new ProgramTable();
	/** 项目集ID */
	public final Column PROGRAM_ID = new Column(this, "program_id");
	/** 项目组合ID */
	public final Column PORTFOLIO_ID = new Column(this, "portfolio_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 部门ID */
	public final Column DEPT_ID = new Column(this, "dept_id");
	/** 父项目集ID */
	public final Column PROGRAM_PARENT = new Column(this, "program_parent");
	/** 项目集名称 */
	public final Column PROGRAM_NAME = new Column(this, "program_name");
	/** 项目集代号 */
	public final Column PROGRAM_CODE = new Column(this, "program_code");
	/** 项目集序号 */
	public final Column PROGRAM_ORDER = new Column(this, "program_order");
	/** 项目集开始日期 */
	public final Column PROGRAM_BEGIN = new Column(this, "program_begin");
	/** 项目集结束日期 */
	public final Column PROGRAM_END = new Column(this, "program_end");
	/** 项目集持续工作日 */
	public final Column PROGRAM_DAYS = new Column(this, "program_days");
	/** 项目集状态 */
	public final Column PROGRAM_STATUS = new Column(this, "program_status");
	/** 项目集阶段 */
	public final Column PROGRAM_STAGE = new Column(this, "program_stage");
	/** 项目集优先级 */
	public final Column PROGRAM_PRI = new Column(this, "program_pri");
	/** 项目集描述 */
	public final Column PROGRAM_DESC = new Column(this, "program_desc");
	/** 项目集目标 */
	public final Column PROGRAM_GOAL = new Column(this, "program_goal");
	/** 项目集创建者 */
	public final Column PROGRAM_OPENED_BY = new Column(this, "program_opened_by");
	/** 项目集创建日期 */
	public final Column PROGRAM_OPENED_DATE = new Column(this, "program_opened_date");
	/** 项目集关闭者 */
	public final Column PROGRAM_CLOSED_BY = new Column(this, "program_closed_by");
	/** 项目集关闭日期 */
	public final Column PROGRAM_CLOSED_DATE = new Column(this, "program_closed_date");
	/** 项目集取消人 */
	public final Column PROGRAM_CANCELED_BY = new Column(this, "program_canceled_by");
	/** 项目集取消日期 */
	public final Column PROGRAM_CANCELED_DATE = new Column(this, "program_canceled_date");
	/** 项目部经理 */
	public final Column PROGRAM_MANAGER = new Column(this, "program_manager");
	/** 权限模式 */
	public final Column ACL = new Column(this, "acl");
	/** 项目集白名单 */
	public final Column PROGRAM_WHITELIST = new Column(this, "program_whitelist");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private ProgramTable() {
			super("program");
		}

}
