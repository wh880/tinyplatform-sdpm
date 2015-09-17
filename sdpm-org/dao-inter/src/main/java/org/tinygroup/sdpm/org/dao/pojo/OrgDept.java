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
 * 部门表
 * 
 * 部门表
 */
public class OrgDept {

	/** 
	 * 部门编号
	 * 
	 * 部门编号
	 */
	private Integer orgDeptId;

	/** 
	 * 部门名称
	 * 
	 * 部门名称
	 */
	private String orgDeptName;

	/** 
	 * 上级部门
	 * 
	 * 上级部门
	 */
	private Integer orgDeptParent;

	/** 
	 * 部门层次路径
	 * 
	 * 部门层次路径
	 */
	private String orgDeptPath;

	/** 
	 * 部门级别
	 * 
	 * 部门级别
	 */
	private Integer orgDeptGrade;

	/** 
	 * 部门排序
	 * 
	 * 部门排序
	 */
	private Integer orgDeptOrder;

	/** 
	 * 部门位置
	 * 
	 * 部门位置
	 */
	private String orgDeptPosition;

	/** 
	 * 负责人 
	 * 
	 * 负责人
	 */
	private String orgDeptManager;


	public void setOrgDeptId(Integer orgDeptId){
		this. orgDeptId = orgDeptId;
	}

	public Integer getOrgDeptId(){
		return orgDeptId;
	}

	public void setOrgDeptName(String orgDeptName){
		this. orgDeptName = orgDeptName;
	}

	public String getOrgDeptName(){
		return orgDeptName;
	}

	public void setOrgDeptParent(Integer orgDeptParent){
		this. orgDeptParent = orgDeptParent;
	}

	public Integer getOrgDeptParent(){
		return orgDeptParent;
	}

	public void setOrgDeptPath(String orgDeptPath){
		this. orgDeptPath = orgDeptPath;
	}

	public String getOrgDeptPath(){
		return orgDeptPath;
	}

	public void setOrgDeptGrade(Integer orgDeptGrade){
		this. orgDeptGrade = orgDeptGrade;
	}

	public Integer getOrgDeptGrade(){
		return orgDeptGrade;
	}

	public void setOrgDeptOrder(Integer orgDeptOrder){
		this. orgDeptOrder = orgDeptOrder;
	}

	public Integer getOrgDeptOrder(){
		return orgDeptOrder;
	}

	public void setOrgDeptPosition(String orgDeptPosition){
		this. orgDeptPosition = orgDeptPosition;
	}

	public String getOrgDeptPosition(){
		return orgDeptPosition;
	}

	public void setOrgDeptManager(String orgDeptManager){
		this. orgDeptManager = orgDeptManager;
	}

	public String getOrgDeptManager(){
		return orgDeptManager;
	}

}