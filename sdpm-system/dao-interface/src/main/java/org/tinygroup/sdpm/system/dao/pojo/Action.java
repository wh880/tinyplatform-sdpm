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

import java.util.Date;

/** 
 * 系统日志表
 * 
 */
public class Action {

	/** 
	 * 系统日志ID
	 * 
	 */
	private Integer actionId;

	/** 
	 * 对象类型
	 * 
	 */
	private String actionObjectType;

	/** 
	 * 对象ID
	 * 
	 */
	private Integer actionObjectID;

	/** 
	 * 所属项目
	 * 
	 */
	private Integer actionProject;

	/** 
	 * 所属产品
	 * 
	 */
	private String actionProduct;

	/** 
	 * 操作者
	 * 
	 */
	private String actionActor;

	/** 
	 * 系统日志日期
	 * 
	 */
	private Date actionDate;

	/** 
	 * 注释
	 * 
	 */
	private String actionComment;

	/** 
	 * EXTRA
	 * 
	 */
	private String actionExtra;

	/** 
	 * 是否已读
	 * 
	 */
	private String actionRead;

	/** 
	 * ACTION_efforted
	 * 
	 */
	private Integer actionEfforted;


	public void setActionId(Integer actionId){
		this. actionId = actionId;
	}

	public Integer getActionId(){
		return actionId;
	}

	public void setActionObjectType(String actionObjectType){
		this. actionObjectType = actionObjectType;
	}

	public String getActionObjectType(){
		return actionObjectType;
	}

	public void setActionObjectID(Integer actionObjectID){
		this. actionObjectID = actionObjectID;
	}

	public Integer getActionObjectID(){
		return actionObjectID;
	}

	public void setActionProject(Integer actionProject){
		this. actionProject = actionProject;
	}

	public Integer getActionProject(){
		return actionProject;
	}

	public void setActionProduct(String actionProduct){
		this. actionProduct = actionProduct;
	}

	public String getActionProduct(){
		return actionProduct;
	}

	public void setActionActor(String actionActor){
		this. actionActor = actionActor;
	}

	public String getActionActor(){
		return actionActor;
	}

	public void setActionDate(Date actionDate){
		this. actionDate = actionDate;
	}

	public Date getActionDate(){
		return actionDate;
	}

	public void setActionComment(String actionComment){
		this. actionComment = actionComment;
	}

	public String getActionComment(){
		return actionComment;
	}

	public void setActionExtra(String actionExtra){
		this. actionExtra = actionExtra;
	}

	public String getActionExtra(){
		return actionExtra;
	}

	public void setActionRead(String actionRead){
		this. actionRead = actionRead;
	}

	public String getActionRead(){
		return actionRead;
	}

	public void setActionEfforted(Integer actionEfforted){
		this. actionEfforted = actionEfforted;
	}

	public Integer getActionEfforted(){
		return actionEfforted;
	}

}
