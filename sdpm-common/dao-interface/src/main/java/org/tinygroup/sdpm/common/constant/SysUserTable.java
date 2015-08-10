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

public class SysUserTable extends Table {

	public static final SysUserTable SYSUSERTABLE = new SysUserTable();
	/** 用户ID */
	public final Column USER_ID = new Column(this, "user_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 部门ID */
	public final Column DEPT_ID = new Column(this, "dept_id");
	/** 用户账户 */
	public final Column USER_ACCOUNT = new Column(this, "user_account");
	/** 用户口令 */
	public final Column USER_PASSWORD = new Column(this, "user_password");
	/** 真实姓名 */
	public final Column USER_REALNAME = new Column(this, "user_realname");
	/** 用户昵称 */
	public final Column USER_NICKNAME = new Column(this, "user_nickname");
	/** 用户化身 */
	public final Column USER_AVATAR = new Column(this, "user_avatar");
	/** 用户生日 */
	public final Column USER_BIRTHDAY = new Column(this, "user_birthday");
	/** 用户性别 */
	public final Column USER_GENDER = new Column(this, "user_gender");
	/** 电子邮件 */
	public final Column USER_EMAIL = new Column(this, "user_email");
	/** MSN */
	public final Column USER_MSN = new Column(this, "user_msn");
	/** QQ */
	public final Column USER_QQ = new Column(this, "user_qq");
	/** GTALK */
	public final Column USER_GTALK = new Column(this, "user_gtalk");
	/** 用户手机 */
	public final Column USER_MOBILE = new Column(this, "user_mobile");
	/** 用户电话 */
	public final Column USER_PHONE = new Column(this, "user_phone");
	/** 用户地址 */
	public final Column USER_ADDRESS = new Column(this, "user_address");
	/** 用户邮编 */
	public final Column USER_ZIPCODE = new Column(this, "user_zipcode");
	/** 加入时间 */
	public final Column USER_REGDATE = new Column(this, "user_regdate");
	/** 访问次数 */
	public final Column USER_VISITS = new Column(this, "user_visits");
	/** 用户IP */
	public final Column USER_IP = new Column(this, "user_ip");
	/** 上次访问时间 */
	public final Column USER_LASTVISIT = new Column(this, "user_lastvisit");
	/** 用户位置 */
	public final Column USER_POSITION = new Column(this, "user_position");
	/** USER_LAST */
	public final Column USER_LAST = new Column(this, "user_last");
	/** 用户头衔 */
	public final Column USER_TITLE = new Column(this, "user_title");
	/** 用户状态 */
	public final Column USER_STATUS = new Column(this, "user_status");
	/** 用户在线时间 */
	public final Column USER_ONLINETIME = new Column(this, "user_onlinetime");
	/** 用户本月在线时间 */
	public final Column USER_ONLINETIMEM = new Column(this, "user_onlinetimem");
	/** 本月访问次数 */
	public final Column USER_VISITSM = new Column(this, "user_visitsm");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private SysUserTable() {
			super("sysUser");
		}

}
