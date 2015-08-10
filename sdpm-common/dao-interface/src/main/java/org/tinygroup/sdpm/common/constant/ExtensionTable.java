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

public class ExtensionTable extends Table {

	public static final ExtensionTable EXTENSIONTABLE = new ExtensionTable();
	/** 扩展ID */
	public final Column EXTENSION_ID = new Column(this, "extension_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 扩展名称 */
	public final Column EXTENSION_NAME = new Column(this, "extension_name");
	/** 扩展编号 */
	public final Column EXTENSION_CODE = new Column(this, "extension_code");
	/** 扩展版本 */
	public final Column EXTENSION_VERSION = new Column(this, "extension_version");
	/** 扩展作者 */
	public final Column EXTENSION_AUTHOR = new Column(this, "extension_author");
	/** 扩展描述 */
	public final Column EXTENSION_DESC = new Column(this, "extension_desc");
	/** 扩展授权 */
	public final Column EXTENSION_LICENSE = new Column(this, "extension_license");
	/** 扩展类型 */
	public final Column EXTENSION_TYPE = new Column(this, "extension_type");
	/** 扩展网站 */
	public final Column EXTENSION_SITE = new Column(this, "extension_site");
	/** 支持系统版本列表 */
	public final Column EXTENSION_SYSTEMVERSION = new Column(this, "extension_systemversion");
	/** 安装日期 */
	public final Column EXTENSION_INSTALLEDDATE = new Column(this, "extension_installeddate");
	/** 扩展文件 */
	public final Column EXTENSION_FILES = new Column(this, "extension_files");
	/** 状态 */
	public final Column EXTENSION_STATUS = new Column(this, "extension_status");

		private ExtensionTable() {
			super("extension");
		}

}
