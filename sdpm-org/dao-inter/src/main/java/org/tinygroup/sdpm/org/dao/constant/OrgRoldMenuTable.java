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

package org.tinygroup.sdpm.org.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/** 
 * 角色菜单表
 * 
 */
public class OrgRoldMenuTable extends Table {

	public static final OrgRoldMenuTable ORG_ROLD_MENUTABLE = new OrgRoldMenuTable();

	/** 
	 * 逻辑ID
	 * 
	 */
	public final Column ID = new Column(this, "id");

	/** 
	 * 角色ID
	 * 
	 */
	public final Column ORG_ROLE_ID = new Column(this, "org_role_id");

	/** 
	 * 角色菜单id
	 * 
	 */
	public final Column ORG_ROLE_MENU_ID = new Column(this, "org_role_menu_id");


	private OrgRoldMenuTable() {
		super("org_rold_menu");
	}

}
