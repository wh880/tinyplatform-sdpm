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

public class DeptTable extends Table {

	public static final DeptTable DEPTTABLE = new DeptTable();
	/** 部门ID */
	public final Column DEPT_ID = new Column(this, "dept_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 部门名称 */
	public final Column DEPT_NAME = new Column(this, "dept_name");
	/** 上级部门 */
	public final Column DEPT_PARENT = new Column(this, "dept_parent");
	/** 部门层次路径 */
	public final Column DEPT_PATH = new Column(this, "dept_path");
	/** 部门级别 */
	public final Column DEPT_GRADE = new Column(this, "dept_grade");
	/** 部门排序 */
	public final Column DEPT_ORDER = new Column(this, "dept_order");
	/** 部门位置 */
	public final Column DEPT_POSITION = new Column(this, "dept_position");
	/** 部门职能 */
	public final Column DEPT_FUNCTION = new Column(this, "dept_function");
	/** 部门负责人 */
	public final Column DEPT_MANAGER = new Column(this, "dept_manager");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private DeptTable() {
			super("dept");
		}

}
