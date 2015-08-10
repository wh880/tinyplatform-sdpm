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

package org.tinygroup.sdpm.service.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class ClientTable extends Table {

	public static final ClientTable CLIENTTABLE = new ClientTable();
	/** 客户ID */
	public final Column CLIENT_ID = new Column(this, "client_id");
	/** 客户名称 */
	public final Column CLIENT_NAME = new Column(this, "client_name");
	/** 客户描述 */
	public final Column CLIENT_SPEC = new Column(this, "client_spec");
	/** 客户编号 */
	public final Column CLIENT_N_O = new Column(this, "client_n_o");
	/** 客户单位/部门ID */
	public final Column CLIENT_DEPT_ID = new Column(this, "client_dept_id");
	/** 客户登记人 */
	public final Column CLIENT_CREATED_BY = new Column(this, "client_created_by");
	/** 客户登记时间 */
	public final Column CLIENT_CREATE_DATE = new Column(this, "client_create_date");
	/** 客户状态 */
	public final Column CLIENT_STATUS = new Column(this, "client_status");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private ClientTable() {
			super("client");
		}

}
