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

package sdpm.project.dao.inter.task.pojo;

import java.util.Date;

public class Task {

	/** 任务id */
	private Integer taskId;

	/** 任务所属项目 */
	private Integer taskProject;

	/** 任务相关需求 */
	private Integer taskStory;

	/** 需求版本 */
	private Integer taskStoryVersion;

	/** 来源bug */
	private Integer taskFromBug;

	/** 任务名称 */
	private String taskName;

	/** 任务类型 */
	private String taskType;

	/** 任务优先级 */
	private Integer taskPri;

	/** 最初预计 */
	private Float taskEstimate;

	/** 总消耗 */
	private Float taskConsumed;

	/** 预计剩余 */
	private Float taskLeft;

	/** 截止日期 */
	private Date taskDeadLine;

	/** 任务状态 */
	private String taskStatus;

	/** 抄送给 */
	private String taskMailto;

	/** 任务描述 */
	private String taskDesc;

	/** 由谁创建 */
	private String taskOpenBy;

	/** 创建日期 */
	private Date taskOpenedDate;

	/** 指派给 */
	private String taskAssignedTo;

	/** 指派日期 */
	private Date taskAssignedDate;

	/** 预计开始 */
	private Date taskEstStared;

	/** 实际开始 */
	private Date taskRealStarted;

	/** 由谁完成 */
	private String taskFinishedBy;

	/** 完成时间 */
	private Date taskFinishedDate;

	/** 由谁取消 */
	private String taskCanceledBy;

	/** 取消时间 */
	private Date taskCanceledDate;

	/** 由谁关闭 */
	private String taskClosedBy;

	/** 关闭时间 */
	private Date taskCloseDate;

	/** 关闭原因 */
	private String taskClosedReason;

	/** 最后修改 */
	private String taskLastEditedBy;

	/** 最后修改日期 */
	private Date taskLastEditedDate;

	/** 已删除 */
	private String taskDeleted;


	public void setTaskId(Integer taskId){
		this. taskId = taskId;
	}

	public Integer getTaskId(){
		return taskId;
	}

	public void setTaskProject(Integer taskProject){
		this. taskProject = taskProject;
	}

	public Integer getTaskProject(){
		return taskProject;
	}

	public void setTaskStory(Integer taskStory){
		this. taskStory = taskStory;
	}

	public Integer getTaskStory(){
		return taskStory;
	}

	public void setTaskStoryVersion(Integer taskStoryVersion){
		this. taskStoryVersion = taskStoryVersion;
	}

	public Integer getTaskStoryVersion(){
		return taskStoryVersion;
	}

	public void setTaskFromBug(Integer taskFromBug){
		this. taskFromBug = taskFromBug;
	}

	public Integer getTaskFromBug(){
		return taskFromBug;
	}

	public void setTaskName(String taskName){
		this. taskName = taskName;
	}

	public String getTaskName(){
		return taskName;
	}

	public void setTaskType(String taskType){
		this. taskType = taskType;
	}

	public String getTaskType(){
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

	public void setTaskDeadLine(Date taskDeadLine){
		this. taskDeadLine = taskDeadLine;
	}

	public Date getTaskDeadLine(){
		return taskDeadLine;
	}

	public void setTaskStatus(String taskStatus){
		this. taskStatus = taskStatus;
	}

	public String getTaskStatus(){
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

	public void setTaskOpenBy(String taskOpenBy){
		this. taskOpenBy = taskOpenBy;
	}

	public String getTaskOpenBy(){
		return taskOpenBy;
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

	public void setTaskEstStared(Date taskEstStared){
		this. taskEstStared = taskEstStared;
	}

	public Date getTaskEstStared(){
		return taskEstStared;
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

	public void setTaskCloseDate(Date taskCloseDate){
		this. taskCloseDate = taskCloseDate;
	}

	public Date getTaskCloseDate(){
		return taskCloseDate;
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

	public void setTaskDeleted(String taskDeleted){
		this. taskDeleted = taskDeleted;
	}

	public String getTaskDeleted(){
		return taskDeleted;
	}

}
