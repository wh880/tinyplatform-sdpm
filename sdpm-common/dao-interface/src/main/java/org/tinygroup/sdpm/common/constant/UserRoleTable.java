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

package org.tinygroup.sdpm.common.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class UserRoleTable extends Table {

	public static final UserRoleTable USERROLETABLE = new UserRoleTable();
	/** 用户角色ID */
	public final Column USER_ROLE_ID = new Column(this, "user_role_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 用户账户 */
	public final Column USER_ACCOUNT = new Column(this, "user_account");
	/** 角色ID */
	public final Column ROLE_ID = new Column(this, "role_id");

		private UserRoleTable() {
			super("userRole");
		}

}
