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


public class UserRole {

	/** 用户角色ID */
	private Integer userRoleId;

	/** 公司ID */
	private Integer companyId;

	/** 用户账户 */
	private String userAccount;

	/** 角色ID */
	private Integer roleId;


	public void setUserRoleId(Integer userRoleId){
		this. userRoleId = userRoleId;
	}

	public Integer getUserRoleId(){
		return userRoleId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setUserAccount(String userAccount){
		this. userAccount = userAccount;
	}

	public String getUserAccount(){
		return userAccount;
	}

	public void setRoleId(Integer roleId){
		this. roleId = roleId;
	}

	public Integer getRoleId(){
		return roleId;
	}

}
