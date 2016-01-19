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
 * <!-- begin-user-doc -->
 * 周报白名单表
 * 
 * <!-- end-user-doc -->
 * @generated
 */
public class OrgDiaryWhiteList implements Serializable{

	/** 
	 * <!-- begin-user-doc -->
	 * 白名单ID
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Integer orgDiaryWhiteListId;

	/** 
	 * <!-- begin-user-doc -->
	 * 白名单甲方账号
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private String orgDiaryWhiteListFirstAccount;

	/** 
	 * <!-- begin-user-doc -->
	 * 白名单乙方账号
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private String orgDiaryWhiteListSecondAccount;


	public void setOrgDiaryWhiteListId(Integer orgDiaryWhiteListId){
		this. orgDiaryWhiteListId = orgDiaryWhiteListId;
	}

	public Integer getOrgDiaryWhiteListId(){
		return orgDiaryWhiteListId;
	}

	public void setOrgDiaryWhiteListFirstAccount(String orgDiaryWhiteListFirstAccount){
		this. orgDiaryWhiteListFirstAccount = orgDiaryWhiteListFirstAccount;
	}

	public String getOrgDiaryWhiteListFirstAccount(){
		return orgDiaryWhiteListFirstAccount;
	}

	public void setOrgDiaryWhiteListSecondAccount(String orgDiaryWhiteListSecondAccount){
		this. orgDiaryWhiteListSecondAccount = orgDiaryWhiteListSecondAccount;
	}

	public String getOrgDiaryWhiteListSecondAccount(){
		return orgDiaryWhiteListSecondAccount;
	}

}
