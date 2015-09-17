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

package org.tinygroup.sdpm.service.dao.pojo;

import java.util.Date;

/** 
 * 客户信息表
 * 
 */
public class Client {

	/** 
	 * 客户ID
	 * 
	 */
	private Integer clientId;

	/** 
	 * 客户名称
	 * 
	 */
	private String clientName;

	/** 
	 * 客户描述
	 * 
	 */
	private String clientSpec;

	/** 
	 * 客户编号
	 * 
	 */
	private String clientNO;

	/** 
	 * 客户单位/部门ID
	 * 
	 */
	private Integer clientDeptId;

	/** 
	 * 客户登记人
	 * 
	 */
	private String clientCreatedBy;

	/** 
	 * 客户登记时间
	 * 
	 */
	private Date clientCreateDate;

	/** 
	 * 客户状态
	 * 
	 * 0-停止；1-活跃
	 */
	private Integer clientStatus;

	/** 
	 * 客户联系电话
	 * 
	 */
	private String userPhone;

	/** 
	 * 客户联系人
	 * 
	 */
	private String userAccount;

	/** 
	 * 联系人职务
	 * 
	 */
	private String userPost;

	/** 
	 * 已删除
	 * 
	 */
	private Integer deleted;


	public void setClientId(Integer clientId){
		this. clientId = clientId;
	}

	public Integer getClientId(){
		return clientId;
	}

	public void setClientName(String clientName){
		this. clientName = clientName;
	}

	public String getClientName(){
		return clientName;
	}

	public void setClientSpec(String clientSpec){
		this. clientSpec = clientSpec;
	}

	public String getClientSpec(){
		return clientSpec;
	}

	public void setClientNO(String clientNO){
		this. clientNO = clientNO;
	}

	public String getClientNO(){
		return clientNO;
	}

	public void setClientDeptId(Integer clientDeptId){
		this. clientDeptId = clientDeptId;
	}

	public Integer getClientDeptId(){
		return clientDeptId;
	}

	public void setClientCreatedBy(String clientCreatedBy){
		this. clientCreatedBy = clientCreatedBy;
	}

	public String getClientCreatedBy(){
		return clientCreatedBy;
	}

	public void setClientCreateDate(Date clientCreateDate){
		this. clientCreateDate = clientCreateDate;
	}

	public Date getClientCreateDate(){
		return clientCreateDate;
	}

	public void setClientStatus(Integer clientStatus){
		this. clientStatus = clientStatus;
	}

	public Integer getClientStatus(){
		return clientStatus;
	}

	public void setUserPhone(String userPhone){
		this. userPhone = userPhone;
	}

	public String getUserPhone(){
		return userPhone;
	}

	public void setUserAccount(String userAccount){
		this. userAccount = userAccount;
	}

	public String getUserAccount(){
		return userAccount;
	}

	public void setUserPost(String userPost){
		this. userPost = userPost;
	}

	public String getUserPost(){
		return userPost;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}