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


public class UserQuery {

	/** 用户查询ID */
	private Integer userqueryId;

	/** 公司ID */
	private Integer companyId;

	/** 用户账户 */
	private String userAccount;

	/** 模块名称 */
	private String moduleName;

	/** 用户查询标题 */
	private String userqueryTitle;

	/** 用户查询表单 */
	private String userqueryForm;

	/** 用户查询SQL */
	private String userquerySqlcondition;


	public void setUserqueryId(Integer userqueryId){
		this. userqueryId = userqueryId;
	}

	public Integer getUserqueryId(){
		return userqueryId;
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

	public void setModuleName(String moduleName){
		this. moduleName = moduleName;
	}

	public String getModuleName(){
		return moduleName;
	}

	public void setUserqueryTitle(String userqueryTitle){
		this. userqueryTitle = userqueryTitle;
	}

	public String getUserqueryTitle(){
		return userqueryTitle;
	}

	public void setUserqueryForm(String userqueryForm){
		this. userqueryForm = userqueryForm;
	}

	public String getUserqueryForm(){
		return userqueryForm;
	}

	public void setUserquerySqlcondition(String userquerySqlcondition){
		this. userquerySqlcondition = userquerySqlcondition;
	}

	public String getUserquerySqlcondition(){
		return userquerySqlcondition;
	}

}
