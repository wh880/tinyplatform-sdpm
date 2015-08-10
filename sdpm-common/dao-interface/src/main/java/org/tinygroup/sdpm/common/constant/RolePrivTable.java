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

public class RolePrivTable extends Table {

	public static final RolePrivTable ROLEPRIVTABLE = new RolePrivTable();
	/** 角色权限ID */
	public final Column ROLE_PRIV_ID = new Column(this, "role_priv_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 角色ID */
	public final Column ROLE_ID = new Column(this, "role_id");
	/** 模块名称 */
	public final Column MODULE_NAME = new Column(this, "module_name");
	/** 功能名称 */
	public final Column FUNCTION = new Column(this, "function");

		private RolePrivTable() {
			super("rolePriv");
		}

}
