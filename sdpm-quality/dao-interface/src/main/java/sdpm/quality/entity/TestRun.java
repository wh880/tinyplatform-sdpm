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

package sdpm.quality.entity;

import java.util.Date;

public class TestRun {

	/** 执行编号 */
	private Integer testRunId;

	/** 相关任务 */
	private Integer taskID;

	/** 用例编号 */
	private Integer caseID;

	/** 关联用例版本 */
	private Integer caseVersion;

	/** 指派给 */
	private String testRunAssignedTo;

	/** TESTRUN_LASTRUNNER */
	private String testRunLastRunner;

	/** 最后执行日期 */
	private Date testRunLastRunDate;

	/** 最后执行结果 */
	private String testRunLastRunResult;

	/** STATUS */
	private String testRunStatus;


	public void setTestRunId(Integer testRunId){
		this. testRunId = testRunId;
	}

	public Integer getTestRunId(){
		return testRunId;
	}

	public void setTaskID(Integer taskID){
		this. taskID = taskID;
	}

	public Integer getTaskID(){
		return taskID;
	}

	public void setCaseID(Integer caseID){
		this. caseID = caseID;
	}

	public Integer getCaseID(){
		return caseID;
	}

	public void setCaseVersion(Integer caseVersion){
		this. caseVersion = caseVersion;
	}

	public Integer getCaseVersion(){
		return caseVersion;
	}

	public void setTestRunAssignedTo(String testRunAssignedTo){
		this. testRunAssignedTo = testRunAssignedTo;
	}

	public String getTestRunAssignedTo(){
		return testRunAssignedTo;
	}

	public void setTestRunLastRunner(String testRunLastRunner){
		this. testRunLastRunner = testRunLastRunner;
	}

	public String getTestRunLastRunner(){
		return testRunLastRunner;
	}

	public void setTestRunLastRunDate(Date testRunLastRunDate){
		this. testRunLastRunDate = testRunLastRunDate;
	}

	public Date getTestRunLastRunDate(){
		return testRunLastRunDate;
	}

	public void setTestRunLastRunResult(String testRunLastRunResult){
		this. testRunLastRunResult = testRunLastRunResult;
	}

	public String getTestRunLastRunResult(){
		return testRunLastRunResult;
	}

	public void setTestRunStatus(String testRunStatus){
		this. testRunStatus = testRunStatus;
	}

	public String getTestRunStatus(){
		return testRunStatus;
	}

}
