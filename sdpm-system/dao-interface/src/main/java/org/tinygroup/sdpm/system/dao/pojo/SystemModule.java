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

package org.tinygroup.sdpm.system.dao.pojo;

import java.io.Serializable;


/** 
 * 系统模块表
 * 
 */
public class SystemModule implements Serializable{

	/** 
	 * 模块根节点
	 * 
	 */
	private Integer moduleRoot;

	/** 
	 * 模块名称
	 * 
	 */
	private String moduleName;

	/** 
	 * 模块地址
	 * 
	 */
	private String modulePath;

	/** 
	 * 模块父节点
	 * 
	 */
	private Integer moduleParent;

	/** 
	 * 模块等级
	 * 
	 */
	private Integer moduleGrade;

	/** 
	 * 模块次序
	 * 
	 */
	private Integer moduleOrder;

	/** 
	 * 模块类型
	 * 
	 */
	private String moduleType;

	/** 
	 * 模块归属
	 * 
	 */
	private String moduleOwner;

	/** 
	 * 模块ID
	 * 
	 */
	private Integer moduleId;


	public void setModuleRoot(Integer moduleRoot){
		this. moduleRoot = moduleRoot;
	}

	public Integer getModuleRoot(){
		return moduleRoot;
	}

	public void setModuleName(String moduleName){
		this. moduleName = moduleName;
	}

	public String getModuleName(){
		return moduleName;
	}

	public void setModulePath(String modulePath){
		this. modulePath = modulePath;
	}

	public String getModulePath(){
		return modulePath;
	}

	public void setModuleParent(Integer moduleParent){
		this. moduleParent = moduleParent;
	}

	public Integer getModuleParent(){
		return moduleParent;
	}

	public void setModuleGrade(Integer moduleGrade){
		this. moduleGrade = moduleGrade;
	}

	public Integer getModuleGrade(){
		return moduleGrade;
	}

	public void setModuleOrder(Integer moduleOrder){
		this. moduleOrder = moduleOrder;
	}

	public Integer getModuleOrder(){
		return moduleOrder;
	}

	public void setModuleType(String moduleType){
		this. moduleType = moduleType;
	}

	public String getModuleType(){
		return moduleType;
	}

	public void setModuleOwner(String moduleOwner){
		this. moduleOwner = moduleOwner;
	}

	public String getModuleOwner(){
		return moduleOwner;
	}

	public void setModuleId(Integer moduleId){
		this. moduleId = moduleId;
	}

	public Integer getModuleId(){
		return moduleId;
	}

}
