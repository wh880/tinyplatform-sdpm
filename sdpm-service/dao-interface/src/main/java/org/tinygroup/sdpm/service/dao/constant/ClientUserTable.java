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

package org.tinygroup.sdpm.service.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/** 
 * 客户联系人信息表
 * 
 */
public class ClientUserTable extends Table {

	public static final ClientUserTable CLIENT_USERTABLE = new ClientUserTable();

	/** 
	 * 逻辑ID
	 * 
	 */
	public final Column ID = new Column(this, "id");

	/** 
	 * 客户ID
	 * 
	 */
	public final Column CLIENT_ID = new Column(this, "client_id");

	/** 
	 * 客户联系人
	 * 
	 */
	public final Column USER_ACCOUNT = new Column(this, "user_account");

	/** 
	 * 客户联系电话
	 * 
	 */
	public final Column USER_PHONE = new Column(this, "user_phone");

	/** 
	 * 联系人职务
	 * 
	 */
	public final Column USER_POST = new Column(this, "user_post");


		private ClientUserTable() {
			super("client_user");
		}

}
