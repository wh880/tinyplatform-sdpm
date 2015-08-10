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

public class TeamMemberTable extends Table {

	public static final TeamMemberTable TEAMMEMBERTABLE = new TeamMemberTable();
	/** 团队成员ID */
	public final Column TEAM_MEMBER_ID = new Column(this, "team_member_id");
	/** 项目ID */
	public final Column PROJECT_ID = new Column(this, "project_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 用户账户 */
	public final Column USER_ACCOUNT = new Column(this, "user_account");
	/** 角色 */
	public final Column TEAM_ROLE = new Column(this, "team_role");
	/** 加入日期 */
	public final Column TEAM_JOIN = new Column(this, "team_join");
	/** 离开日期 */
	public final Column TEAM_LEAVE = new Column(this, "team_leave");
	/** 可用工日 */
	public final Column TEAM_DAYS = new Column(this, "team_days");
	/** 可用工时/天 */
	public final Column TEAM_HOURS = new Column(this, "team_hours");

		private TeamMemberTable() {
			super("teamMember");
		}

}
