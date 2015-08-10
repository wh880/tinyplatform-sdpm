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


public class Dept {

	/** 部门ID */
	private Integer deptId;

	/** 公司ID */
	private Integer companyId;

	/** 部门名称 */
	private String deptName;

	/** 上级部门 */
	private Integer deptParent;

	/** 部门层次路径 */
	private String deptPath;

	/** 部门级别 */
	private Integer deptGrade;

	/** 部门排序 */
	private Integer deptOrder;

	/** 部门位置 */
	private String deptPosition;

	/** 部门职能 */
	private String deptFunction;

	/** 部门负责人 */
	private String deptManager;

	/** 删除标记 */
	private Integer deleted;


	public void setDeptId(Integer deptId){
		this. deptId = deptId;
	}

	public Integer getDeptId(){
		return deptId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setDeptName(String deptName){
		this. deptName = deptName;
	}

	public String getDeptName(){
		return deptName;
	}

	public void setDeptParent(Integer deptParent){
		this. deptParent = deptParent;
	}

	public Integer getDeptParent(){
		return deptParent;
	}

	public void setDeptPath(String deptPath){
		this. deptPath = deptPath;
	}

	public String getDeptPath(){
		return deptPath;
	}

	public void setDeptGrade(Integer deptGrade){
		this. deptGrade = deptGrade;
	}

	public Integer getDeptGrade(){
		return deptGrade;
	}

	public void setDeptOrder(Integer deptOrder){
		this. deptOrder = deptOrder;
	}

	public Integer getDeptOrder(){
		return deptOrder;
	}

	public void setDeptPosition(String deptPosition){
		this. deptPosition = deptPosition;
	}

	public String getDeptPosition(){
		return deptPosition;
	}

	public void setDeptFunction(String deptFunction){
		this. deptFunction = deptFunction;
	}

	public String getDeptFunction(){
		return deptFunction;
	}

	public void setDeptManager(String deptManager){
		this. deptManager = deptManager;
	}

	public String getDeptManager(){
		return deptManager;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
