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
 * 角色表
 * 
 */
public class OrgRole {

	/** 
	 * 角色ID
	 * 
	 */
	private Integer orgRoleId;

	/** 
	 * 角色名称
	 * 
	 */
	private String orgRoleName;

	/** 
	 * 角色备注
	 * 
	 */
	private String orgRoleRemarks;

	/** 
	 * 已删除
	 * 
	 */
	private Integer deleted;

	public Integer getOrgRoleId() {
		return orgRoleId;
	}

	public void setOrgRoleId(Integer orgRoleId){
		this. orgRoleId = orgRoleId;
	}

	public String getOrgRoleName() {
		return orgRoleName;
	}

	public void setOrgRoleName(String orgRoleName){
		this. orgRoleName = orgRoleName;
	}

	public String getOrgRoleRemarks() {
		return orgRoleRemarks;
	}

	public void setOrgRoleRemarks(String orgRoleRemarks){
		this. orgRoleRemarks = orgRoleRemarks;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

}
