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


/** 
 * 假期表
 * 
 */
public class Holiday {

	/** 
	 * 假期ID
	 * 
	 */
	private Integer holidayId;

	/** 
	 * 假期名
	 * 
	 */
	private String holidayName;

	/** 
	 * 假期创建人
	 * 
	 */
	private String holidayAccount;

	/** 
	 * 假期日期
	 * 
	 */
	private String holidayDate;

	/** 
	 * 假期类型
	 * 
	 */
	private String holidayType;

	/** 
	 * 删除标志位
	 * 
	 */
	private Integer holidayDeleted;

	/** 
	 * 公司ID
	 * 
	 */
	private Integer companyId;


	public void setHolidayId(Integer holidayId){
		this. holidayId = holidayId;
	}

	public Integer getHolidayId(){
		return holidayId;
	}

	public void setHolidayName(String holidayName){
		this. holidayName = holidayName;
	}

	public String getHolidayName(){
		return holidayName;
	}

	public void setHolidayAccount(String holidayAccount){
		this. holidayAccount = holidayAccount;
	}

	public String getHolidayAccount(){
		return holidayAccount;
	}

	public void setHolidayDate(String holidayDate){
		this. holidayDate = holidayDate;
	}

	public String getHolidayDate(){
		return holidayDate;
	}

	public void setHolidayType(String holidayType){
		this. holidayType = holidayType;
	}

	public String getHolidayType(){
		return holidayType;
	}

	public void setHolidayDeleted(Integer holidayDeleted){
		this. holidayDeleted = holidayDeleted;
	}

	public Integer getHolidayDeleted(){
		return holidayDeleted;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

}
