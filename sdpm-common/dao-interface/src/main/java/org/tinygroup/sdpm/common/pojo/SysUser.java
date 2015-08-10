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

package org.tinygroup.sdpm.common.pojo;

import java.math.BigInteger;
import java.util.Date;

public class SysUser {

	/** 用户ID */
	private Integer userId;

	/** 公司ID */
	private Integer companyId;

	/** 部门ID */
	private Integer deptId;

	/** 用户账户 */
	private String userAccount;

	/** 用户口令 */
	private String userPassword;

	/** 真实姓名 */
	private String userRealname;

	/** 用户昵称 */
	private String userNickname;

	/** 用户化身 */
	private String userAvatar;

	/** 用户生日 */
	private Date userBirthday;

	/** 用户性别 */
	private Integer userGender;

	/** 电子邮件 */
	private String userEmail;

	/** MSN */
	private String userMsn;

	/** QQ */
	private String userQq;

	/** GTALK */
	private String userGtalk;

	/** 用户手机 */
	private String userMobile;

	/** 用户电话 */
	private String userPhone;

	/** 用户地址 */
	private String userAddress;

	/** 用户邮编 */
	private String userZipcode;

	/** 加入时间 */
	private Date userRegdate;

	/** 访问次数 */
	private Integer userVisits;

	/** 用户IP */
	private String userIp;

	/** 上次访问时间 */
	private Date userLastvisit;

	/** 用户位置 */
	private String userPosition;

	/** USER_LAST */
	private BigInteger userLast;

	/** 用户头衔 */
	private String userTitle;

	/** 用户状态 */
	private Integer userStatus;

	/** 用户在线时间 */
	private Integer userOnlinetime;

	/** 用户本月在线时间 */
	private BigInteger userOnlinetimem;

	/** 本月访问次数 */
	private Integer userVisitsm;

	/** 删除标记 */
	private Integer deleted;


	public void setUserId(Integer userId){
		this. userId = userId;
	}

	public Integer getUserId(){
		return userId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setDeptId(Integer deptId){
		this. deptId = deptId;
	}

	public Integer getDeptId(){
		return deptId;
	}

	public void setUserAccount(String userAccount){
		this. userAccount = userAccount;
	}

	public String getUserAccount(){
		return userAccount;
	}

	public void setUserPassword(String userPassword){
		this. userPassword = userPassword;
	}

	public String getUserPassword(){
		return userPassword;
	}

	public void setUserRealname(String userRealname){
		this. userRealname = userRealname;
	}

	public String getUserRealname(){
		return userRealname;
	}

	public void setUserNickname(String userNickname){
		this. userNickname = userNickname;
	}

	public String getUserNickname(){
		return userNickname;
	}

	public void setUserAvatar(String userAvatar){
		this. userAvatar = userAvatar;
	}

	public String getUserAvatar(){
		return userAvatar;
	}

	public void setUserBirthday(Date userBirthday){
		this. userBirthday = userBirthday;
	}

	public Date getUserBirthday(){
		return userBirthday;
	}

	public void setUserGender(Integer userGender){
		this. userGender = userGender;
	}

	public Integer getUserGender(){
		return userGender;
	}

	public void setUserEmail(String userEmail){
		this. userEmail = userEmail;
	}

	public String getUserEmail(){
		return userEmail;
	}

	public void setUserMsn(String userMsn){
		this. userMsn = userMsn;
	}

	public String getUserMsn(){
		return userMsn;
	}

	public void setUserQq(String userQq){
		this. userQq = userQq;
	}

	public String getUserQq(){
		return userQq;
	}

	public void setUserGtalk(String userGtalk){
		this. userGtalk = userGtalk;
	}

	public String getUserGtalk(){
		return userGtalk;
	}

	public void setUserMobile(String userMobile){
		this. userMobile = userMobile;
	}

	public String getUserMobile(){
		return userMobile;
	}

	public void setUserPhone(String userPhone){
		this. userPhone = userPhone;
	}

	public String getUserPhone(){
		return userPhone;
	}

	public void setUserAddress(String userAddress){
		this. userAddress = userAddress;
	}

	public String getUserAddress(){
		return userAddress;
	}

	public void setUserZipcode(String userZipcode){
		this. userZipcode = userZipcode;
	}

	public String getUserZipcode(){
		return userZipcode;
	}

	public void setUserRegdate(Date userRegdate){
		this. userRegdate = userRegdate;
	}

	public Date getUserRegdate(){
		return userRegdate;
	}

	public void setUserVisits(Integer userVisits){
		this. userVisits = userVisits;
	}

	public Integer getUserVisits(){
		return userVisits;
	}

	public void setUserIp(String userIp){
		this. userIp = userIp;
	}

	public String getUserIp(){
		return userIp;
	}

	public void setUserLastvisit(Date userLastvisit){
		this. userLastvisit = userLastvisit;
	}

	public Date getUserLastvisit(){
		return userLastvisit;
	}

	public void setUserPosition(String userPosition){
		this. userPosition = userPosition;
	}

	public String getUserPosition(){
		return userPosition;
	}

	public void setUserLast(BigInteger userLast){
		this. userLast = userLast;
	}

	public BigInteger getUserLast(){
		return userLast;
	}

	public void setUserTitle(String userTitle){
		this. userTitle = userTitle;
	}

	public String getUserTitle(){
		return userTitle;
	}

	public void setUserStatus(Integer userStatus){
		this. userStatus = userStatus;
	}

	public Integer getUserStatus(){
		return userStatus;
	}

	public void setUserOnlinetime(Integer userOnlinetime){
		this. userOnlinetime = userOnlinetime;
	}

	public Integer getUserOnlinetime(){
		return userOnlinetime;
	}

	public void setUserOnlinetimem(BigInteger userOnlinetimem){
		this. userOnlinetimem = userOnlinetimem;
	}

	public BigInteger getUserOnlinetimem(){
		return userOnlinetimem;
	}

	public void setUserVisitsm(Integer userVisitsm){
		this. userVisitsm = userVisitsm;
	}

	public Integer getUserVisitsm(){
		return userVisitsm;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
