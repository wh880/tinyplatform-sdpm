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

package org.tinygroup.sdpm.project.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/** 
 * 版本
 * 
 */
public class BuildTable extends Table {

	public static final BuildTable BUILDTABLE = new BuildTable();

	/** 
	 * 版本id
	 * 
	 */
	public final Column BUILD_ID = new Column(this, "build_id");

	/** 
	 * 产品
	 * 
	 */
	public final Column BUILD_PRODUCT = new Column(this, "build_product");

	/** 
	 * 项目
	 * 
	 */
	public final Column BUILD_PROJECT = new Column(this, "build_project");

	/** 
	 * 名称编号
	 * 
	 */
	public final Column BUILD_NAME = new Column(this, "build_name");

	/** 
	 * 源码地址
	 * 
	 */
	public final Column BUILD_SCM_PATH = new Column(this, "build_scm_path");

	/** 
	 * 下载地址
	 * 
	 */
	public final Column BUILD_FILE_PATH = new Column(this, "build_file_path");

	/** 
	 * 打包日期
	 * 
	 */
	public final Column BUILD_DATE = new Column(this, "build_date");

	/** 
	 * 已解决需求
	 * 
	 */
	public final Column BUILD_STORIES = new Column(this, "build_stories");

	/** 
	 * 已解决bug
	 * 
	 */
	public final Column BUILD_BUGS = new Column(this, "build_bugs");

	/** 
	 * 构建者
	 * 
	 */
	public final Column BUILD_BUILDER = new Column(this, "build_builder");

	/** 
	 * 描述
	 * 
	 */
	public final Column BUILD_DESC = new Column(this, "build_desc");

	/** 
	 * 已删除
	 * 
	 * 0-未删除，1-删除
	 */
	public final Column BUILD_DELETED = new Column(this, "build_deleted");


		private BuildTable() {
			super("build");
		}

}
