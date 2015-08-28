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

package org.tinygroup.sdpm.project.pojo;

import java.util.Date;

public class Task {

	/** 任务ID */
	private Integer taskId;

	/** 公司ID */
	private Integer companyId;

	/** 项目ID */
	private Integer projectId;

	/** 模块ID */
	private Integer moduleId;

	/** 任务标题 */
	private String taskName;

	/** 任务类型 */
	private Integer taskType;

	/** 任务优先级 */
	private Integer taskPri;

	/** 任务估算 */
	private Float taskEstimate;

	/** 任务消耗 */
	private Float taskConsumed;

	/** 任务剩余 */
	private Float taskLeft;

	/** 任务期限 */
	private Date taskDeadline;

	/** 任务状态 */
	private Integer taskStatus;

	/** 邮件列表 */
	private String taskMailto;

	/** 任务描述 */
	private String taskDesc;

	/** TASK_OPEN_COUNT */
	private Integer taskOpenCount;

	/** 任务创建者 */
	private String taskOpenedBy;

	/** 任务创建日期 */
	private Date taskOpenedDate;

	/** 任务指派人 */
	private String taskAssignedTo;

	/** 任务指派日期 */
	private Date taskAssignedDate;

	/** 任务预计开始日期 */
	private Date taskEstStarted;

	/** 任务实际开始日期 */
	private Date taskRealStarted;

	/** 任务完成者 */
	private String taskFinishedBy;

	/** 任务完成日期 */
	private Date taskFinishedDate;

	/** 任务取消者 */
	private String taskCanceledBy;

	/** 任务取消日期 */
	private Date taskCanceledDate;

	/** 任务关闭者 */
	private String taskClosedBy;

	/** 任务关闭日期 */
	private Date taskClosedDate;

	/** 任务关闭原因 */
	private String taskClosedReason;

	/** 最后编辑者 */
	private String taskLastEditedBy;

	/** 最后编辑日期 */
	private Date taskLastEditedDate;

	/** 紧前任务ID */
	private Integer taskFS;

	/** 同启任务ID */
	private Integer taskSS;

	/** 同完任务ID */
	private Integer taskFF;

	/** 紧后任务ID */
	private Integer taskSF;

	/** 客户请求ID */
	private Integer requestId;

	/** 删除标记 */
	private Integer deleted;


	public void setTaskId(Integer taskId){
		this. taskId = taskId;
	}

	public Integer getTaskId(){
		return taskId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setProjectId(Integer projectId){
		this. projectId = projectId;
	}

	public Integer getProjectId(){
		return projectId;
	}

	public void setModuleId(Integer moduleId){
		this. moduleId = moduleId;
	}

	public Integer getModuleId(){
		return moduleId;
	}

	public void setTaskName(String taskName){
		this. taskName = taskName;
	}

	public String getTaskName(){
		return taskName;
	}

	public void setTaskType(Integer taskType){
		this. taskType = taskType;
	}

	public Integer getTaskType(){
		return taskType;
	}

	public void setTaskPri(Integer taskPri){
		this. taskPri = taskPri;
	}

	public Integer getTaskPri(){
		return taskPri;
	}

	public void setTaskEstimate(Float taskEstimate){
		this. taskEstimate = taskEstimate;
	}

	public Float getTaskEstimate(){
		return taskEstimate;
	}

	public void setTaskConsumed(Float taskConsumed){
		this. taskConsumed = taskConsumed;
	}

	public Float getTaskConsumed(){
		return taskConsumed;
	}

	public void setTaskLeft(Float taskLeft){
		this. taskLeft = taskLeft;
	}

	public Float getTaskLeft(){
		return taskLeft;
	}

	public void setTaskDeadline(Date taskDeadline){
		this. taskDeadline = taskDeadline;
	}

	public Date getTaskDeadline(){
		return taskDeadline;
	}

	public void setTaskStatus(Integer taskStatus){
		this. taskStatus = taskStatus;
	}

	public Integer getTaskStatus(){
		return taskStatus;
	}

	public void setTaskMailto(String taskMailto){
		this. taskMailto = taskMailto;
	}

	public String getTaskMailto(){
		return taskMailto;
	}

	public void setTaskDesc(String taskDesc){
		this. taskDesc = taskDesc;
	}

	public String getTaskDesc(){
		return taskDesc;
	}

	public void setTaskOpenCount(Integer taskOpenCount){
		this. taskOpenCount = taskOpenCount;
	}

	public Integer getTaskOpenCount(){
		return taskOpenCount;
	}

	public void setTaskOpenedBy(String taskOpenedBy){
		this. taskOpenedBy = taskOpenedBy;
	}

	public String getTaskOpenedBy(){
		return taskOpenedBy;
	}

	public void setTaskOpenedDate(Date taskOpenedDate){
		this. taskOpenedDate = taskOpenedDate;
	}

	public Date getTaskOpenedDate(){
		return taskOpenedDate;
	}

	public void setTaskAssignedTo(String taskAssignedTo){
		this. taskAssignedTo = taskAssignedTo;
	}

	public String getTaskAssignedTo(){
		return taskAssignedTo;
	}

	public void setTaskAssignedDate(Date taskAssignedDate){
		this. taskAssignedDate = taskAssignedDate;
	}

	public Date getTaskAssignedDate(){
		return taskAssignedDate;
	}

	public void setTaskEstStarted(Date taskEstStarted){
		this. taskEstStarted = taskEstStarted;
	}

	public Date getTaskEstStarted(){
		return taskEstStarted;
	}

	public void setTaskRealStarted(Date taskRealStarted){
		this. taskRealStarted = taskRealStarted;
	}

	public Date getTaskRealStarted(){
		return taskRealStarted;
	}

	public void setTaskFinishedBy(String taskFinishedBy){
		this. taskFinishedBy = taskFinishedBy;
	}

	public String getTaskFinishedBy(){
		return taskFinishedBy;
	}

	public void setTaskFinishedDate(Date taskFinishedDate){
		this. taskFinishedDate = taskFinishedDate;
	}

	public Date getTaskFinishedDate(){
		return taskFinishedDate;
	}

	public void setTaskCanceledBy(String taskCanceledBy){
		this. taskCanceledBy = taskCanceledBy;
	}

	public String getTaskCanceledBy(){
		return taskCanceledBy;
	}

	public void setTaskCanceledDate(Date taskCanceledDate){
		this. taskCanceledDate = taskCanceledDate;
	}

	public Date getTaskCanceledDate(){
		return taskCanceledDate;
	}

	public void setTaskClosedBy(String taskClosedBy){
		this. taskClosedBy = taskClosedBy;
	}

	public String getTaskClosedBy(){
		return taskClosedBy;
	}

	public void setTaskClosedDate(Date taskClosedDate){
		this. taskClosedDate = taskClosedDate;
	}

	public Date getTaskClosedDate(){
		return taskClosedDate;
	}

	public void setTaskClosedReason(String taskClosedReason){
		this. taskClosedReason = taskClosedReason;
	}

	public String getTaskClosedReason(){
		return taskClosedReason;
	}

	public void setTaskLastEditedBy(String taskLastEditedBy){
		this. taskLastEditedBy = taskLastEditedBy;
	}

	public String getTaskLastEditedBy(){
		return taskLastEditedBy;
	}

	public void setTaskLastEditedDate(Date taskLastEditedDate){
		this. taskLastEditedDate = taskLastEditedDate;
	}

	public Date getTaskLastEditedDate(){
		return taskLastEditedDate;
	}

	public void setTaskFS(Integer taskFS){
		this. taskFS = taskFS;
	}

	public Integer getTaskFS(){
		return taskFS;
	}

	public void setTaskSS(Integer taskSS){
		this. taskSS = taskSS;
	}

	public Integer getTaskSS(){
		return taskSS;
	}

	public void setTaskFF(Integer taskFF){
		this. taskFF = taskFF;
	}

	public Integer getTaskFF(){
		return taskFF;
	}

	public void setTaskSF(Integer taskSF){
		this. taskSF = taskSF;
	}

	public Integer getTaskSF(){
		return taskSF;
	}

	public void setRequestId(Integer requestId){
		this. requestId = requestId;
	}

	public Integer getRequestId(){
		return requestId;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
