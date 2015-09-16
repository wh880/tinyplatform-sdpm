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

package org.tinygroup.sdpm.quality.dao.org.tinygroup.alm.quality.pojo;

import java.util.Date;

/** 
 * 测试结果表
 * 
 */
public class TestResult {

	/** 
	 * 测试结果
	 * 
	 */
	private Integer testResultId;

	/** 
	 * RUN
	 * 
	 */
	private Integer run;

	/** 
	 * 相关用例
	 * 
	 */
	private Integer linkCase;

	/** 
	 * 关联用例版本
	 * 
	 */
	private Integer caseVersion;

	/** 
	 * 测试结果
	 * 
	 */
	private String caseResult;

	/** 
	 * 用例步骤结果
	 * 
	 */
	private String caseStepresults;

	/** 
	 * 最后执行人
	 * 
	 */
	private String testResultLastRunner;

	/** 
	 * 最后执行日期
	 * 
	 */
	private Date testResultDate;


	public void setTestResultId(Integer testResultId){
		this. testResultId = testResultId;
	}

	public Integer getTestResultId(){
		return testResultId;
	}

	public void setRun(Integer run){
		this. run = run;
	}

	public Integer getRun(){
		return run;
	}

	public void setLinkCase(Integer linkCase){
		this. linkCase = linkCase;
	}

	public Integer getLinkCase(){
		return linkCase;
	}

	public void setCaseVersion(Integer caseVersion){
		this. caseVersion = caseVersion;
	}

	public Integer getCaseVersion(){
		return caseVersion;
	}

	public void setCaseResult(String caseResult){
		this. caseResult = caseResult;
	}

	public String getCaseResult(){
		return caseResult;
	}

	public void setCaseStepresults(String caseStepresults){
		this. caseStepresults = caseStepresults;
	}

	public String getCaseStepresults(){
		return caseStepresults;
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
