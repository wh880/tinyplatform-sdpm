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


public class RolePriv {

	/** 角色权限ID */
	private Integer rolePrivId;

	/** 公司ID */
	private Integer companyId;

	/** 角色ID */
	private Integer roleId;

	/** 模块名称 */
	private String moduleName;

	/** 功能名称 */
	private String function;


	public void setRolePrivId(Integer rolePrivId){
		this. rolePrivId = rolePrivId;
	}

	public Integer getRolePrivId(){
		return rolePrivId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setRoleId(Integer roleId){
		this. roleId = roleId;
	}

	public Integer getRoleId(){
		return roleId;
	}

	public void setModuleName(String moduleName){
		this. moduleName = moduleName;
	}

	public String getModuleName(){
		return moduleName;
	}

	public void setFunction(String function){
		this. function = function;
	}

	public String getFunction(){
		return function;
	}

}
