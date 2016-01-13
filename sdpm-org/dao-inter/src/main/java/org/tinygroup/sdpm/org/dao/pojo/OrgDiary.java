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
import java.util.Date;

/** 
 * <!-- begin-user-doc -->
 * 周报表
 * 
 * <!-- end-user-doc -->
 * @generated
 */
public class OrgDiary implements Serializable{
	/**
	 * 删除状态
	 */
	public static String DELETE_YES = "1";
	public static String DELETE_NO = "0";

	/** 
	 * <!-- begin-user-doc -->
	 * 周报id
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Integer orgDiaryId;

	/** 
	 * <!-- begin-user-doc -->
	 * 用户编号
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private String orgUserId;

	/** 
	 * <!-- begin-user-doc -->
	 * 周报开始时间
	 * 
	 * 每周开始的第一天
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Date orgDiaryBeginDate;

	/** 
	 * <!-- begin-user-doc -->
	 * 周报结束时间
	 * 
	 * 每周最后一天
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Date orgDiaryEndDate;

	/** 
	 * <!-- begin-user-doc -->
	 * 周报年
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Integer orgDiaryYear;

	/** 
	 * <!-- begin-user-doc -->
	 * 周报周数
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Integer orgDiaryWeek;

	/** 
	 * <!-- begin-user-doc -->
	 * 周报创建日期
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Date orgDiaryCreateDate;

	/** 
	 * <!-- begin-user-doc -->
	 * 每周总结
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private String orgDiarySummary;

	/** 
	 * <!-- begin-user-doc -->
	 * 周报状态
	 * 
	 * 正常-0
	 * 删除-1
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private String orgDiaryStatus;

	/** 
	 * <!-- begin-user-doc -->
	 * 最后修改日期
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Date orgDiaryModifyDate;

	public OrgDiary(){
		setOrgDiaryStatus(DELETE_NO);
	}

	public void setOrgDiaryId(Integer orgDiaryId){
		this. orgDiaryId = orgDiaryId;
	}

	public Integer getOrgDiaryId(){
		return orgDiaryId;
	}

	public void setOrgUserId(String orgUserId){
		this. orgUserId = orgUserId;
	}

	public String getOrgUserId(){
		return orgUserId;
	}

	public void setOrgDiaryBeginDate(Date orgDiaryBeginDate){
		this. orgDiaryBeginDate = orgDiaryBeginDate;
	}

	public Date getOrgDiaryBeginDate(){
		return orgDiaryBeginDate;
	}

	public void setOrgDiaryEndDate(Date orgDiaryEndDate){
		this. orgDiaryEndDate = orgDiaryEndDate;
	}

	public Date getOrgDiaryEndDate(){
		return orgDiaryEndDate;
	}

	public void setOrgDiaryYear(Integer orgDiaryYear){
		this. orgDiaryYear = orgDiaryYear;
	}

	public Integer getOrgDiaryYear(){
		return orgDiaryYear;
	}

	public void setOrgDiaryWeek(Integer orgDiaryWeek){
		this. orgDiaryWeek = orgDiaryWeek;
	}

	public Integer getOrgDiaryWeek(){
		return orgDiaryWeek;
	}

	public void setOrgDiaryCreateDate(Date orgDiaryCreateDate){
		this. orgDiaryCreateDate = orgDiaryCreateDate;
	}

	public Date getOrgDiaryCreateDate(){
		return orgDiaryCreateDate;
	}

	public void setOrgDiarySummary(String orgDiarySummary){
		this. orgDiarySummary = orgDiarySummary;
	}

	public String getOrgDiarySummary(){
		return orgDiarySummary;
	}

	public void setOrgDiaryStatus(String orgDiaryStatus){
		this. orgDiaryStatus = orgDiaryStatus;
	}

	public String getOrgDiaryStatus(){
		return orgDiaryStatus;
	}

	public void setOrgDiaryModifyDate(Date orgDiaryModifyDate){
		this. orgDiaryModifyDate = orgDiaryModifyDate;
	}

	public Date getOrgDiaryModifyDate(){
		return orgDiaryModifyDate;
	}

}
