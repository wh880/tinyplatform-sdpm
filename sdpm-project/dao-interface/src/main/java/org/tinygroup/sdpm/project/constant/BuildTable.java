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

package org.tinygroup.sdpm.project.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class BuildTable extends Table {

	public static final BuildTable BUILDTABLE = new BuildTable();
	/** 集成版本ID */
	public final Column BUILD_ID = new Column(this, "build_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 产品ID */
	public final Column PRODUCT_ID = new Column(this, "product_id");
	/** 项目ID */
	public final Column PROJECT_ID = new Column(this, "project_id");
	/** 集成版本名称 */
	public final Column BUILD_NAME = new Column(this, "build_name");
	/** 集成描述 */
	public final Column BUILD_SPEC = new Column(this, "build_spec");
	/** 配置库路径 */
	public final Column BUILD_SCM_PATH = new Column(this, "build_scm_path");
	/** 文件路径 */
	public final Column BUILD_FILE_PATH = new Column(this, "build_file_path");
	/** 集成日期 */
	public final Column BUILD_DATE = new Column(this, "build_date");
	/** 集成者 */
	public final Column BUILDER = new Column(this, "builder");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private BuildTable() {
			super("build");
		}

}
