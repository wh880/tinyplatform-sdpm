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

package sdpm.project.dao.inter.team.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class TeamTable extends Table {

	public static final TeamTable TEAMTABLE = new TeamTable();
	/** 项目id */
	public final Column PROJECT_ID = new Column(this, "project_id");
	/** 用户 */
	public final Column TEAM_ACCOUNT = new Column(this, "team_account");
	/** 角色 */
	public final Column TEAM_ROLE = new Column(this, "team_role");
	/** 加盟日 */
	public final Column TEAM_JOIN = new Column(this, "team_join");
	/** 可用工时 */
	public final Column TEAM_DAYS = new Column(this, "team_days");
	/** 可用工时 */
	public final Column TEAM_HOURS = new Column(this, "team_hours");

		private TeamTable() {
			super("team");
		}

}
