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
 * 部门表
 * 
 */
public class OrgDeptTable extends Table {

	public static final OrgDeptTable ORG_DEPTTABLE = new OrgDeptTable();

	/** 
	 * 部门编号
	 * 
	 */
	public final Column ORG_DEPT_ID = new Column(this, "org_dept_id");

	/** 
	 * 部门名称
	 * 
	 */
	public final Column ORG_DEPT_NAME = new Column(this, "org_dept_name");

	/** 
	 * 上级部门
	 * 
	 */
	public final Column ORG_DEPT_PARENT = new Column(this, "org_dept_parent");

	/** 
	 * 部门层次路径
	 * 
	 */
	public final Column ORG_DEPT_PATH = new Column(this, "org_dept_path");

	/** 
	 * 部门级别
	 * 
	 */
	public final Column ORG_DEPT_GRADE = new Column(this, "org_dept_grade");

	/** 
	 * 部门排序
	 * 
	 */
	public final Column ORG_DEPT_ORDER = new Column(this, "org_dept_order");

	/** 
	 * 部门位置
	 * 
	 */
	public final Column ORG_DEPT_POSITION = new Column(this, "org_dept_position");

	/**
	 * 负责人 
	 * 
	 */
	public final Column ORG_DEPT_MANAGER = new Column(this, "org_dept_manager");


	private OrgDeptTable() {
		super("org_dept");
	}

}
