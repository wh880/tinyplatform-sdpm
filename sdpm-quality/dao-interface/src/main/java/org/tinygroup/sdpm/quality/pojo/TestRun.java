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

package org.tinygroup.sdpm.quality.pojo;

import java.util.Date;

public class TestRun {

	/** 测试执行ID */
	private Integer testrunId;

	/** 公司ID */
	private Integer companyId;

	/** 测试计划ID */
	private Integer testplanId;

	/** 关联用例ID */
	private Integer caseId;

	/** 关联用例版本 */
	private Integer caseVersion;

	/** 测试执行指派给 */
	private String testrunAssignedTo;

	/** 测试执行者 */
	private String testrunLastRunner;

	/** 测试执行日期 */
	private Date testrunLastRunDate;

	/** 测试执行结果 */
	private Integer testrunLastRunResult;

	/** 测试执行状态 */
	private Integer testrunStatus;


	public void setTestrunId(Integer testrunId){
		this. testrunId = testrunId;
	}

	public Integer getTestrunId(){
		return testrunId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setTestplanId(Integer testplanId){
		this. testplanId = testplanId;
	}

	public Integer getTestplanId(){
		return testplanId;
	}

	public void setCaseId(Integer caseId){
		this. caseId = caseId;
	}

	public Integer getCaseId(){
		return caseId;
	}

	public void setCaseVersion(Integer caseVersion){
		this. caseVersion = caseVersion;
	}

	public Integer getCaseVersion(){
		return caseVersion;
	}

	public void setTestrunAssignedTo(String testrunAssignedTo){
		this. testrunAssignedTo = testrunAssignedTo;
	}

	public String getTestrunAssignedTo(){
		return testrunAssignedTo;
	}

	public void setTestrunLastRunner(String testrunLastRunner){
		this. testrunLastRunner = testrunLastRunner;
	}

	public String getTestrunLastRunner(){
		return testrunLastRunner;
	}

	public void setTestrunLastRunDate(Date testrunLastRunDate){
		this. testrunLastRunDate = testrunLastRunDate;
	}

	public Date getTestrunLastRunDate(){
		return testrunLastRunDate;
	}

	public void setTestrunLastRunResult(Integer testrunLastRunResult){
		this. testrunLastRunResult = testrunLastRunResult;
	}

	public Integer getTestrunLastRunResult(){
		return testrunLastRunResult;
	}

	public void setTestrunStatus(Integer testrunStatus){
		this. testrunStatus = testrunStatus;
	}

	public Integer getTestrunStatus(){
		return testrunStatus;
	}

}
