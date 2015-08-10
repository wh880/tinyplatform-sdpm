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

public class ProjectTable extends Table {

	public static final ProjectTable PROJECTTABLE = new ProjectTable();
	/** 项目ID */
	public final Column PROJECT_ID = new Column(this, "project_id");
	/** 项目集ID */
	public final Column PROGRAM_ID = new Column(this, "program_id");
	/** 项目组合ID */
	public final Column PORTFOLIO_ID = new Column(this, "portfolio_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 部门ID */
	public final Column DEPT_ID = new Column(this, "dept_id");
	/** 项目类型 */
	public final Column PROJECT_TYPE = new Column(this, "project_type");
	/** 父项目ID */
	public final Column PROJECT_PARENT = new Column(this, "project_parent");
	/** 项目名称 */
	public final Column PROJECT_NAME = new Column(this, "project_name");
	/** 项目代号 */
	public final Column PROJECT_CODE = new Column(this, "project_code");
	/** 项目序号 */
	public final Column PROJECT_ORDER = new Column(this, "project_order");
	/** 项目开始日期 */
	public final Column PROJECT_BEGIN = new Column(this, "project_begin");
	/** 项目结束日期 */
	public final Column PROJECT_END = new Column(this, "project_end");
	/** 可用工作日 */
	public final Column PROJECT_DAYS = new Column(this, "project_days");
	/** 项目状态 */
	public final Column PROJECT_STATUS = new Column(this, "project_status");
	/** 项目阶段 */
	public final Column PROJECT_STAGE = new Column(this, "project_stage");
	/** 项目优先级 */
	public final Column PROJECT_PRI = new Column(this, "project_pri");
	/** 项目描述 */
	public final Column PROJECT_DESC = new Column(this, "project_desc");
	/** 项目目标 */
	public final Column PROJECT_GOAL = new Column(this, "project_goal");
	/** 项目创建人 */
	public final Column PROJECT_OPENED_BY = new Column(this, "project_opened_by");
	/** 项目创建日期 */
	public final Column PROJECT_OPENED_DATE = new Column(this, "project_opened_date");
	/** 项目关闭人 */
	public final Column PROJECT_CLOSED_BY = new Column(this, "project_closed_by");
	/** 项目关闭日期 */
	public final Column PROJECT_CLOSED_DATE = new Column(this, "project_closed_date");
	/** 项目取消人 */
	public final Column PROJECT_CANCELED_BY = new Column(this, "project_canceled_by");
	/** 项目取消日期 */
	public final Column PROJECT_CANCELED_DATE = new Column(this, "project_canceled_date");
	/** 项目经理 */
	public final Column PROJECT_MANAGER = new Column(this, "project_manager");
	/** 质量经理 */
	public final Column QUALITY_MANAGER = new Column(this, "quality_manager");
	/** 交付经理 */
	public final Column DELIVERY_MANAGER = new Column(this, "delivery_manager");
	/** 项目组名 */
	public final Column PROJECT_TEAM = new Column(this, "project_team");
	/** 权限模式 */
	public final Column ACL = new Column(this, "acl");
	/** PROJECT_WHITELIST */
	public final Column PROJECT_WHITELIST = new Column(this, "project_whitelist");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private ProjectTable() {
			super("project");
		}

}
