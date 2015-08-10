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

public class ModuleTable extends Table {

	public static final ModuleTable MODULETABLE = new ModuleTable();
	/** 模块ID */
	public final Column MODULE_ID = new Column(this, "module_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 根模块ID */
	public final Column MODULE_ROOT = new Column(this, "module_root");
	/** 模块名称 */
	public final Column MODULE_NAME = new Column(this, "module_name");
	/** 父模块 */
	public final Column MODULE_PARENT = new Column(this, "module_parent");
	/** 模块层次路径 */
	public final Column MODULE_PATH = new Column(this, "module_path");
	/** 模块级别 */
	public final Column MODULE_GRADE = new Column(this, "module_grade");
	/** 模块序号 */
	public final Column MODULE_ORDER = new Column(this, "module_order");
	/** 模块类型 */
	public final Column MODULE_TYPE = new Column(this, "module_type");
	/** 模块负责人 */
	public final Column MODULE_OWNER = new Column(this, "module_owner");
	/** 模块描述 */
	public final Column MODULE_SPEC = new Column(this, "module_spec");

		private ModuleTable() {
			super("module");
		}

}
