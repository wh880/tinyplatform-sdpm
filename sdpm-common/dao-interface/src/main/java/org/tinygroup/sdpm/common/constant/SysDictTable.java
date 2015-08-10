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

public class SysDictTable extends Table {

	public static final SysDictTable SYSDICTTABLE = new SysDictTable();
	/** 字典ID */
	public final Column SYSDICT_ID = new Column(this, "sysdict_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 信息所有者 */
	public final Column SYSDICT_OWNER = new Column(this, "sysdict_owner");
	/** 所属模块 */
	public final Column SYSDICT_MODULE = new Column(this, "sysdict_module");
	/** 分区 */
	public final Column SYSDICT_SECTION = new Column(this, "sysdict_section");
	/** 字典键 */
	public final Column SYSDICT_KEY = new Column(this, "sysdict_key");
	/** 字典值 */
	public final Column SYSDICT_VALUE = new Column(this, "sysdict_value");

		private SysDictTable() {
			super("sysDict");
		}

}
