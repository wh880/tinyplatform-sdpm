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

package org.tinygroup.sdpm.service.pojo;


public class ClientUser {

	/** 客户名称 */
	private String clientUserId;

	/** 客户名称 */
	private String clientName;

	/** 用户账户 */
	private String userAccount;


	public void setClientUserId(String clientUserId){
		this. clientUserId = clientUserId;
	}

	public String getClientUserId(){
		return clientUserId;
	}

	public void setClientName(String clientName){
		this. clientName = clientName;
	}

	public String getClientName(){
		return clientName;
	}

	public void setUserAccount(String userAccount){
		this. userAccount = userAccount;
	}

	public String getUserAccount(){
		return userAccount;
	}

}
