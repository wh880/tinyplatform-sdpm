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

package sdpm.common.dao.inter.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class SysModuleTable extends Table {

	public static final SysModuleTable SYS_MODULETABLE = new SysModuleTable();
	/** 模块ID */
	public final Column SYS_MODULE_ID = new Column(this, "sys_module_id");
	/** 模块根节点 */
	public final Column SYS_MODULE_ROOT = new Column(this, "sys_module_root");
	/** 模块名称 */
	public final Column SYS_MODULE_NAME = new Column(this, "sys_module_name");
	/** 模块地址 */
	public final Column SYS_MODULE_PATH = new Column(this, "sys_module_path");
	/** 模块父节点 */
	public final Column SYS_MODULE_PARENT = new Column(this, "sys_module_parent");
	/** 模块等级 */
	public final Column SYS_MODULE_GRADE = new Column(this, "sys_module_grade");
	/** 模块次序 */
	public final Column SYS_MODULE_ORDER = new Column(this, "sys_module_order");
	/** 模块类型 */
	public final Column SYS_MODULE_TYPE = new Column(this, "sys_module_type");
	/** 模块归属 */
	public final Column SYS_MODULE_OWNER = new Column(this, "sys_module_owner");

		private SysModuleTable() {
			super("sys_module");
		}

}