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

public class UserQueryTable extends Table {

	public static final UserQueryTable USERQUERYTABLE = new UserQueryTable();
	/** 用户查询ID */
	public final Column USERQUERY_ID = new Column(this, "userquery_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 用户账户 */
	public final Column USER_ACCOUNT = new Column(this, "user_account");
	/** 模块名称 */
	public final Column MODULE_NAME = new Column(this, "module_name");
	/** 用户查询标题 */
	public final Column USERQUERY_TITLE = new Column(this, "userquery_title");
	/** 用户查询表单 */
	public final Column USERQUERY_FORM = new Column(this, "userquery_form");
	/** 用户查询SQL */
	public final Column USERQUERY_SQLCONDITION = new Column(this, "userquery_sqlcondition");

		private UserQueryTable() {
			super("userQuery");
		}

}
