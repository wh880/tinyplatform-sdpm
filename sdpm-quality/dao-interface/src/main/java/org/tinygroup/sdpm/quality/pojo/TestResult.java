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

public class TestResult {

	/** 测试结果ID */
	private Integer testResultId;

	/** 公司ID */
	private Integer companyId;

	/** 测试执行ID */
	private Integer runId;

	/** 关联用例ID */
	private Integer caseId;

	/** 关联用例版本 */
	private Integer caseVersion;

	/** 用例结果 */
	private Integer caseResult;

	/** 用例步骤结果 */
	private String caseStepResults;

	/** 测试最后执行者 */
	private String testResultLastRunner;

	/** 测试结果日期 */
	private Date testResultDate;


	public void setTestResultId(Integer testResultId){
		this. testResultId = testResultId;
	}

	public Integer getTestResultId(){
		return testResultId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setRunId(Integer runId){
		this. runId = runId;
	}

	public Integer getRunId(){
		return runId;
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

	public void setCaseResult(Integer caseResult){
		this. caseResult = caseResult;
	}

	public Integer getCaseResult(){
		return caseResult;
	}

	public void setCaseStepResults(String caseStepResults){
		this. caseStepResults = caseStepResults;
	}

	public String getCaseStepResults(){
		return caseStepResults;
	}

	public void setTestResultLastRunner(String testResultLastRunner){
		this. testResultLastRunner = testResultLastRunner;
	}

	public String getTestResultLastRunner(){
		return testResultLastRunner;
	}

	public void setTestResultDate(Date testResultDate){
		this. testResultDate = testResultDate;
	}

	public Date getTestResultDate(){
		return testResultDate;
	}

}
