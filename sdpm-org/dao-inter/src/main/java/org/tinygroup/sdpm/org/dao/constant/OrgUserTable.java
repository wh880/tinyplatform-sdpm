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
 * 用户表
 * 
 * 用户表
 */
public class OrgUserTable extends Table {

	public static final OrgUserTable ORG_USERTABLE = new OrgUserTable();

	/** 
	 * 用户编号
	 * 
	 * 用户编号
	 */
	public final Column ORG_USER_ID = new Column(this, "org_user_id");

	/** 
	 * 部门编号
	 * 
	 * 部门编号
	 */
	public final Column ORG_DEPT_ID = new Column(this, "org_dept_id");

	/** 
	 * 用户名
	 * 
	 * 用户名
	 */
	public final Column ORG_USER_ACCOUNT = new Column(this, "org_user_account");

	/** 
	 * 密码
	 * 
	 * 密码
	 */
	public final Column ORG_USER_PASSWORD = new Column(this, "org_user_password");

	/** 
	 * 职位
	 * 
	 * 职位
	 */
	public final Column ORG_USER_ROLE = new Column(this, "org_user_role");

	/** 
	 * 真实姓名
	 * 
	 * 真实姓名
	 */
	public final Column ORG_USER_REALNAME = new Column(this, "org_user_realname");

	/** 
	 * 昵称
	 * 
	 * 昵称
	 */
	public final Column ORG_USER_NICKNAME = new Column(this, "org_user_nickname");

	/** 
	 * 源代码账号
	 * 
	 * 源代码账号
	 */
	public final Column ORG_USER_COMMITER = new Column(this, "org_user_commiter");

	/** 
	 * 头像
	 * 
	 * 头像
	 */
	public final Column ORG_USER_AVATAR = new Column(this, "org_user_avatar");

	/** 
	 * 出生日期
	 * 
	 * 出生日期
	 */
	public final Column ORG_USER_BIRTHDAY = new Column(this, "org_user_birthday");

	/** 
	 * 性别 
	 * 
	 * 性别
	 */
	public final Column ORG_USER_GENDER = new Column(this, "org_user_gender");

	/** 
	 * 邮箱
	 * 
	 * 邮箱
	 */
	public final Column ORG_USER_EMAIL = new Column(this, "org_user_email");

	/** 
	 * Skype
	 * 
	 * Skype
	 */
	public final Column ORG_USER_SKYPE = new Column(this, "org_user_skype");

	/** 
	 * QQ
	 * 
	 * QQ
	 */
	public final Column ORG_USER_QQ = new Column(this, "org_user_qq");

	/** 
	 * 雅虎通
	 * 
	 * 雅虎通
	 */
	public final Column ORG_USER_YAHOO = new Column(this, "org_user_yahoo");

	/** 
	 * Gtalk
	 * 
	 * Gtalk
	 */
	public final Column ORG_USER_GTALK = new Column(this, "org_user_gtalk");

	/** 
	 * 旺旺
	 * 
	 * 旺旺
	 */
	public final Column ORG_USER_WANGWANG = new Column(this, "org_user_wangwang");

	/** 
	 * 手机
	 * 
	 * 手机
	 */
	public final Column ORG_USER_MOBILE = new Column(this, "org_user_mobile");

	/** 
	 * 电话 
	 * 
	 * 电话
	 */
	public final Column ORG_USER_PHONE = new Column(this, "org_user_phone");

	/** 
	 * 通讯地址
	 * 
	 * 通讯地址
	 */
	public final Column ORG_USER_ADRESS = new Column(this, "org_user_adress");

	/** 
	 * 邮编
	 * 
	 * 邮编
	 */
	public final Column ORG_USER_ZIPCODE = new Column(this, "org_user_zipcode");

	/** 
	 * 加入日期
	 * 
	 * 加入日期
	 */
	public final Column ORG_USER_JOIN = new Column(this, "org_user_join");

	/** 
	 * 访问次数
	 * 
	 * 访问次数
	 */
	public final Column ORG_USER_VISITS = new Column(this, "org_user_visits");

	/** 
	 * 最后IP
	 * 
	 * 最后IP
	 */
	public final Column ORG_USER_IP = new Column(this, "org_user_ip");

	/** 
	 * 最后登录
	 * 
	 * 最后登录
	 */
	public final Column ORG_USER_LAST = new Column(this, "org_user_last");

	/** 
	 * 失败次数
	 * 
	 * 失败次数
	 */
	public final Column ORG_USER_FAILS = new Column(this, "org_user_fails");

	/** 
	 * 上次锁定时间
	 * 
	 * 上次锁定时间
	 */
	public final Column ORG_USER_LOCKED = new Column(this, "org_user_locked");

	/** 
	 * 是否删除
	 * 
	 * 是否删除
	 */
	public final Column ORG_USER_DELETED = new Column(this, "org_user_deleted");


		private OrgUserTable() {
			super("org_user");
		}

}