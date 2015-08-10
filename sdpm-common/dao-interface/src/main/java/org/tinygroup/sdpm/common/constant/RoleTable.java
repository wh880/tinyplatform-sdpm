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

public class RoleTable extends Table {

	public static final RoleTable ROLETABLE = new RoleTable();
	/** 角色ID */
	public final Column ROLE_ID = new Column(this, "role_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 角色名称 */
	public final Column ROLE_NAME = new Column(this, "role_name");
	/** 角色描述 */
	public final Column ROLE_DESC = new Column(this, "role_desc");

		private RoleTable() {
			super("role");
		}

}
