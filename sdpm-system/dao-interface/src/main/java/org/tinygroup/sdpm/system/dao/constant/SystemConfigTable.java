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

package org.tinygroup.sdpm.system.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/** 
 * 模块配置表
 * 
 */
public class SystemConfigTable extends Table {

	public static final SystemConfigTable SYSTEM_CONFIGTABLE = new SystemConfigTable();

	/** 
	 * 配置ID
	 * 
	 */
	public final Column CONFIG_ID = new Column(this, "config_id");

	/** 
	 * 配置创建人
	 * 
	 */
	public final Column CONFIG_OWNER = new Column(this, "config_owner");

	/** 
	 * 配置模块
	 * 
	 */
	public final Column CONFIG_MODULE = new Column(this, "config_module");

	/** 
	 * 配置部分
	 * 
	 */
	public final Column CONFIG_SECTION = new Column(this, "config_section");

	/** 
	 * 配置的关键词
	 * 
	 */
	public final Column CONFIG_KEY = new Column(this, "config_key");

	/** 
	 * 配置的值
	 * 
	 */
	public final Column CONFIG_VALUE = new Column(this, "config_value");

	/** 
	 * 已删除
	 * 
	 */
	public final Column DELETED = new Column(this, "deleted");


		private SystemConfigTable() {
			super("system_config");
		}

}
