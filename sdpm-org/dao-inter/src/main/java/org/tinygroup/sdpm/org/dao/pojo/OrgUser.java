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

package org.tinygroup.sdpm.org.dao.pojo;

/** 
 * 用户表
 * 
 * 用户表
 */
public class OrgUser {

	/** 
	 * 用户编号
	 * 
	 * 用户编号
	 */
	private Integer orgUserId;

	/** 
	 * 部门编号
	 * 
	 * 部门编号
	 */
	private Integer orgDeptId;

	/** 
	 * 用户名
	 * 
	 * 用户名
	 */
	private String orgUserAccount;

	/** 
	 * 密码
	 * 
	 * 密码
	 */
	private String orgUserPassword;

	/** 
	 * 职位
	 * 
	 * 职位
	 */
	private String orgUserRole;

	/** 
	 * 真实姓名
	 * 
	 * 真实姓名
	 */
	private String orgUserRealname;

	/** 
	 * 昵称
	 * 
	 * 昵称
	 */
	private String orgUserNickname;

	/** 
	 * 源代码账号
	 * 
	 * 源代码账号
	 */
	private String orgUserCommiter;

	/** 
	 * 头像
	 * 
	 * 头像
	 */
	private String orgUserAvatar;

	/** 
	 * 出生日期
	 * 
	 * 出生日期
	 */
	private String orgUserBirthday;

	/** 
	 * 性别 
	 * 
	 * 性别
	 */
	private String orgUserGender;

	/** 
	 * 邮箱
	 * 
	 * 邮箱
	 */
	private String orgUserEmail;

	/** 
	 * Skype
	 * 
	 * Skype
	 */
	private String orgUserSkype;

	/** 
	 * QQ
	 * 
	 * QQ
	 */
	private String orgUserQq;

	/** 
	 * 雅虎通
	 * 
	 * 雅虎通
	 */
	private String orgUserYahoo;

	/** 
	 * Gtalk
	 * 
	 * Gtalk
	 */
	private String orgUserGtalk;

	/** 
	 * 旺旺
	 * 
	 * 旺旺
	 */
	private String orgUserWangwang;

	/** 
	 * 手机
	 * 
	 * 手机
	 */
	private String orgUserMobile;

	/** 
	 * 电话 
	 * 
	 * 电话
	 */
	private String orgUserPhone;

	/** 
	 * 通讯地址
	 * 
	 * 通讯地址
	 */
	private String orgUserAdress;

	/** 
	 * 邮编
	 * 
	 * 邮编
	 */
	private String orgUserZipcode;

	/** 
	 * 加入日期
	 * 
	 * 加入日期
	 */
	private String orgUserJoin;

	/** 
	 * 访问次数
	 * 
	 * 访问次数
	 */
	private Integer orgUserVisits;

	/** 
	 * 最后IP
	 * 
	 * 最后IP
	 */
	private String orgUserIp;

	/** 
	 * 最后登录
	 * 
	 * 最后登录
	 */
	private Integer orgUserLast;

	/** 
	 * 失败次数
	 * 
	 * 失败次数
	 */
	private Integer orgUserFails;

	/** 
	 * 上次锁定时间
	 * 
	 * 上次锁定时间
	 */
	private String orgUserLocked;

	/** 
	 * 是否删除
	 * 
	 * 是否删除
	 */
	private String orgUserDeleted;


	public void setOrgUserId(Integer orgUserId){
		this. orgUserId = orgUserId;
	}

	public Integer getOrgUserId(){
		return orgUserId;
	}

	public void setOrgDeptId(Integer orgDeptId){
		this. orgDeptId = orgDeptId;
	}

	public Integer getOrgDeptId(){
		return orgDeptId;
	}

	public void setOrgUserAccount(String orgUserAccount){
		this. orgUserAccount = orgUserAccount;
	}

	public String getOrgUserAccount(){
		return orgUserAccount;
	}

	public void setOrgUserPassword(String orgUserPassword){
		this. orgUserPassword = orgUserPassword;
	}

	public String getOrgUserPassword(){
		return orgUserPassword;
	}

	public void setOrgUserRole(String orgUserRole){
		this. orgUserRole = orgUserRole;
	}

	public String getOrgUserRole(){
		return orgUserRole;
	}

	public void setOrgUserRealname(String orgUserRealname){
		this. orgUserRealname = orgUserRealname;
	}

	public String getOrgUserRealname(){
		return orgUserRealname;
	}

	public void setOrgUserNickname(String orgUserNickname){
		this. orgUserNickname = orgUserNickname;
	}

	public String getOrgUserNickname(){
		return orgUserNickname;
	}

	public void setOrgUserCommiter(String orgUserCommiter){
		this. orgUserCommiter = orgUserCommiter;
	}

	public String getOrgUserCommiter(){
		return orgUserCommiter;
	}

	public void setOrgUserAvatar(String orgUserAvatar){
		this. orgUserAvatar = orgUserAvatar;
	}

	public String getOrgUserAvatar(){
		return orgUserAvatar;
	}

	public void setOrgUserBirthday(String orgUserBirthday){
		this. orgUserBirthday = orgUserBirthday;
	}

	public String getOrgUserBirthday(){
		return orgUserBirthday;
	}

	public void setOrgUserGender(String orgUserGender){
		this. orgUserGender = orgUserGender;
	}

	public String getOrgUserGender(){
		return orgUserGender;
	}

	public void setOrgUserEmail(String orgUserEmail){
		this. orgUserEmail = orgUserEmail;
	}

	public String getOrgUserEmail(){
		return orgUserEmail;
	}

	public void setOrgUserSkype(String orgUserSkype){
		this. orgUserSkype = orgUserSkype;
	}

	public String getOrgUserSkype(){
		return orgUserSkype;
	}

	public void setOrgUserQq(String orgUserQq){
		this. orgUserQq = orgUserQq;
	}

	public String getOrgUserQq(){
		return orgUserQq;
	}

	public void setOrgUserYahoo(String orgUserYahoo){
		this. orgUserYahoo = orgUserYahoo;
	}

	public String getOrgUserYahoo(){
		return orgUserYahoo;
	}

	public void setOrgUserGtalk(String orgUserGtalk){
		this. orgUserGtalk = orgUserGtalk;
	}

	public String getOrgUserGtalk(){
		return orgUserGtalk;
	}

	public void setOrgUserWangwang(String orgUserWangwang){
		this. orgUserWangwang = orgUserWangwang;
	}

	public String getOrgUserWangwang(){
		return orgUserWangwang;
	}

	public void setOrgUserMobile(String orgUserMobile){
		this. orgUserMobile = orgUserMobile;
	}

	public String getOrgUserMobile(){
		return orgUserMobile;
	}

	public void setOrgUserPhone(String orgUserPhone){
		this. orgUserPhone = orgUserPhone;
	}

	public String getOrgUserPhone(){
		return orgUserPhone;
	}

	public void setOrgUserAdress(String orgUserAdress){
		this. orgUserAdress = orgUserAdress;
	}

	public String getOrgUserAdress(){
		return orgUserAdress;
	}

	public void setOrgUserZipcode(String orgUserZipcode){
		this. orgUserZipcode = orgUserZipcode;
	}

	public String getOrgUserZipcode(){
		return orgUserZipcode;
	}

	public void setOrgUserJoin(String orgUserJoin){
		this. orgUserJoin = orgUserJoin;
	}

	public String getOrgUserJoin(){
		return orgUserJoin;
	}

	public void setOrgUserVisits(Integer orgUserVisits){
		this. orgUserVisits = orgUserVisits;
	}

	public Integer getOrgUserVisits(){
		return orgUserVisits;
	}

	public void setOrgUserIp(String orgUserIp){
		this. orgUserIp = orgUserIp;
	}

	public String getOrgUserIp(){
		return orgUserIp;
	}

	public void setOrgUserLast(Integer orgUserLast){
		this. orgUserLast = orgUserLast;
	}

	public Integer getOrgUserLast(){
		return orgUserLast;
	}

	public void setOrgUserFails(Integer orgUserFails){
		this. orgUserFails = orgUserFails;
	}

	public Integer getOrgUserFails(){
		return orgUserFails;
	}

	public void setOrgUserLocked(String orgUserLocked){
		this. orgUserLocked = orgUserLocked;
	}

	public String getOrgUserLocked(){
		return orgUserLocked;
	}

	public void setOrgUserDeleted(String orgUserDeleted){
		this. orgUserDeleted = orgUserDeleted;
	}

	public String getOrgUserDeleted(){
		return orgUserDeleted;
	}

}
