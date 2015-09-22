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

import java.io.Serializable;

/**
 * 角色菜单表
 * 
 */
public class OrgRoldMenu implements Serializable {

	public static String DELETE_YES = "1";
	public static String DELETE_NO = "0";
	/** 
	 * 逻辑ID
	 * 
	 */
	private Integer id;

	/** 
	 * 角色ID
	 * 
	 */
	private Integer orgRoleId;

	/** 
	 * 角色菜单id
	 * 
	 */
	private String orgRoleMenuId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id){
		this. id = id;
	}

	public Integer getOrgRoleId() {
		return orgRoleId;
	}

	public void setOrgRoleId(Integer orgRoleId){
		this. orgRoleId = orgRoleId;
	}

	public String getOrgRoleMenuId() {
		return orgRoleMenuId;
	}

	public void setOrgRoleMenuId(String orgRoleMenuId){
		this. orgRoleMenuId = orgRoleMenuId;
	}

}
