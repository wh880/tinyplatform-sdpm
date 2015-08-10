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

import java.util.Date;

public class Action {

	/** 动作ID */
	private Integer actionId;

	/** 公司ID */
	private Integer companyId;

	/** 关联对象类型 */
	private Integer objectType;

	/** 关联对象ID */
	private Integer objectId;

	/** 产品ID列表 */
	private String productList;

	/** 项目ID */
	private Integer projectId;

	/** 操作者 */
	private String actor;

	/** 操作 */
	private String action;

	/** 操作时间 */
	private Date actionDate;

	/** 备注 */
	private String actionComment;

	/** 扩展字段 */
	private String actionExtra;


	public void setActionId(Integer actionId){
		this. actionId = actionId;
	}

	public Integer getActionId(){
		return actionId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setObjectType(Integer objectType){
		this. objectType = objectType;
	}

	public Integer getObjectType(){
		return objectType;
	}

	public void setObjectId(Integer objectId){
		this. objectId = objectId;
	}

	public Integer getObjectId(){
		return objectId;
	}

	public void setProductList(String productList){
		this. productList = productList;
	}

	public String getProductList(){
		return productList;
	}

	public void setProjectId(Integer projectId){
		this. projectId = projectId;
	}

	public Integer getProjectId(){
		return projectId;
	}

	public void setActor(String actor){
		this. actor = actor;
	}

	public String getActor(){
		return actor;
	}

	public void setAction(String action){
		this. action = action;
	}

	public String getAction(){
		return action;
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

}
