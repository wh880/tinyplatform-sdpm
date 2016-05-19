/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/gpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tinygroup.sdpm.project.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * 任务
 * <p>
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class ProjectTask implements Serializable {

    public static final String DELETE_YES = "1";
    public static final String DELETE_NO = "0";
    //0-等待，1-未开始，2-进行中，3-已完成，4-已暂停，5-已取消,6-已关闭
    public static final String WAIT = "1";
    public static final String DOING = "2";
    public static final String DONE = "3";
    public static final String PAUSE = "4";
    public static final String CANCEL = "5";
    public static final String CLOSE = "6";


    private Integer taskRelationBug;

    /**
     * 用于显示关联项目名称
     */
    private String projectName;
    /**
     * 关联需求及需求描述表
     */
    private String storySpec;
    /**
     * 任务id
     */
    private Integer taskId;
    /**
     * 编号
     */
    private Integer taskNo;

    /**
     * 任务所属项目Id
     */
    private Integer taskProject;
    /**
     * 任务相关需求
     */
    private Integer taskStory;
    /**
     * 需求版本
     * <p>
     * 需求变更后自动增长
     */
    private Integer taskStoryVersion;
    /**
     * 关联任务表
     */

    private Integer taskModule;
    /**
     * 来源bug
     */
    private Integer taskFromBug;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 任务类型
     */
    private String taskType;
    /**
     * 任务优先级
     * <p>
     * 1，2，4，5
     * 递增
     */
    private String taskPri;
    /**
     * 最初预计
     */
    private Float taskEstimate;
    /**
     * 总消耗
     */
    private Float taskConsumed;
    /**
     * 预计剩余
     */
    private Float taskLeft;
    /**
     * 截止日期
     */
    private Date taskDeadLine;
    /**
     * 任务状态
     * <p>
     * 0-等待，1-未开始，2-进行中，3-已完成，4-已暂停，5-已取消,6-已关闭,7-已延期
     */
    private String taskStatus;
    /**
     * 抄送给
     */
    private String taskMailto;
    /**
     * 任务描述
     */
    private String taskDesc;
    /**
     * 由谁创建
     */
    private String taskOpenBy;
    /**
     * 创建日期
     */
    private Date taskOpenedDate;
    /**
     * 指派给
     */
    private String taskAssignedTo;
    /**
     * 指派日期
     */
    private Date taskAssignedDate;
    /**
     * 预计开始
     */
    private Date taskEstStared;
    /**
     * 实际开始
     */
    private Date taskRealStarted;
    /**
     * 由谁完成
     */
    private String taskFinishedBy;
    /**
     * 完成时间
     */
    private Date taskFinishedDate;
    /**
     * 由谁取消
     */
    private String taskCanceledBy;
    /**
     * 取消时间
     */
    private Date taskCanceledDate;
    /**
     * 由谁关闭
     */
    private String taskClosedBy;
    /**
     * 关闭时间
     */
    private Date taskCloseDate;
    /**
     * 关闭原因
     */
    private String taskClosedReason;
    /**
     * 最后修改
     */
    private String taskLastEditedBy;
    /**
     * 最后修改日期
     */
    private Date taskLastEditedDate;
    /**
     * 已删除
     * <p>
     * 0-未删除，1-已删除
     */
    private String taskDeleted;

    public ProjectTask() {
        this.taskDeleted = DELETE_NO;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getTaskModule() {
        return taskModule;
    }

    public void setTaskModule(Integer taskModule) {
        this.taskModule = taskModule;
    }

    public String getStorySpec() {
        return storySpec;
    }

    public void setStorySpec(String storySpec) {
        this.storySpec = storySpec;
    }

    public Integer getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(Integer taskNo) {
        this.taskNo = taskNo;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getTaskProject() {
        return taskProject;
    }

    public void setTaskProject(Integer taskProject) {
        this.taskProject = taskProject;
    }

    public Integer getTaskStory() {
        return taskStory;
    }

    public void setTaskStory(Integer taskStory) {
        this.taskStory = taskStory;
    }

    public Integer getTaskStoryVersion() {
        return taskStoryVersion;
    }

    public void setTaskStoryVersion(Integer taskStoryVersion) {
        this.taskStoryVersion = taskStoryVersion;
    }

    public Integer getTaskFromBug() {
        return taskFromBug;
    }

    public void setTaskFromBug(Integer taskFromBug) {
        this.taskFromBug = taskFromBug;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskPri() {
        return taskPri;
    }

    public void setTaskPri(String taskPri) {
        this.taskPri = taskPri;
    }

    public Float getTaskEstimate() {
        return taskEstimate;
    }

    public void setTaskEstimate(Float taskEstimate) {
        this.taskEstimate = taskEstimate;
    }

    public Float getTaskConsumed() {
        return taskConsumed;
    }

    public void setTaskConsumed(Float taskConsumed) {
        this.taskConsumed = taskConsumed;
    }

    public Float getTaskLeft() {
        return taskLeft;
    }

    public void setTaskLeft(Float taskLeft) {
        this.taskLeft = taskLeft;
    }

    public Date getTaskDeadLine() {
        return taskDeadLine;
    }

    public void setTaskDeadLine(Date taskDeadLine) {
        this.taskDeadLine = taskDeadLine;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getTaskMailto() {
        return taskMailto;
    }

    public void setTaskMailto(String taskMailto) {
        this.taskMailto = taskMailto;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskOpenBy() {
        return taskOpenBy;
    }

    public void setTaskOpenBy(String taskOpenBy) {
        this.taskOpenBy = taskOpenBy;
    }

    public Date getTaskOpenedDate() {
        return taskOpenedDate;
    }

    public void setTaskOpenedDate(Date taskOpenedDate) {
        this.taskOpenedDate = taskOpenedDate;
    }

    public String getTaskAssignedTo() {
        return taskAssignedTo;
    }

    public void setTaskAssignedTo(String taskAssignedTo) {
        this.taskAssignedTo = taskAssignedTo;
    }

    public Date getTaskAssignedDate() {
        return taskAssignedDate;
    }

    public void setTaskAssignedDate(Date taskAssignedDate) {
        this.taskAssignedDate = taskAssignedDate;
    }

    public Date getTaskEstStared() {
        return taskEstStared;
    }

    public void setTaskEstStared(Date taskEstStared) {
        this.taskEstStared = taskEstStared;
    }

    public Date getTaskRealStarted() {
        return taskRealStarted;
    }

    public void setTaskRealStarted(Date taskRealStarted) {
        this.taskRealStarted = taskRealStarted;
    }

    public String getTaskFinishedBy() {
        return taskFinishedBy;
    }

    public void setTaskFinishedBy(String taskFinishedBy) {
        this.taskFinishedBy = taskFinishedBy;
    }

    public Date getTaskFinishedDate() {
        return taskFinishedDate;
    }

    public void setTaskFinishedDate(Date taskFinishedDate) {
        this.taskFinishedDate = taskFinishedDate;
    }

    public String getTaskCanceledBy() {
        return taskCanceledBy;
    }

    public void setTaskCanceledBy(String taskCanceledBy) {
        this.taskCanceledBy = taskCanceledBy;
    }

    public Date getTaskCanceledDate() {
        return taskCanceledDate;
    }

    public void setTaskCanceledDate(Date taskCanceledDate) {
        this.taskCanceledDate = taskCanceledDate;
    }

    public String getTaskClosedBy() {
        return taskClosedBy;
    }

    public void setTaskClosedBy(String taskClosedBy) {
        this.taskClosedBy = taskClosedBy;
    }

    public Date getTaskCloseDate() {
        return taskCloseDate;
    }

    public void setTaskCloseDate(Date taskCloseDate) {
        this.taskCloseDate = taskCloseDate;
    }

    public String getTaskClosedReason() {
        return taskClosedReason;
    }

    public void setTaskClosedReason(String taskClosedReason) {
        this.taskClosedReason = taskClosedReason;
    }

    public String getTaskLastEditedBy() {
        return taskLastEditedBy;
    }

    public void setTaskLastEditedBy(String taskLastEditedBy) {
        this.taskLastEditedBy = taskLastEditedBy;
    }

    public Date getTaskLastEditedDate() {
        return taskLastEditedDate;
    }

    public void setTaskLastEditedDate(Date taskLastEditedDate) {
        this.taskLastEditedDate = taskLastEditedDate;
    }

    public String getTaskDeleted() {
        return taskDeleted;
    }

    public void setTaskDeleted(String taskDeleted) {
        this.taskDeleted = taskDeleted;
    }

    public Integer getTaskRelationBug() {
        return taskRelationBug;
    }

    public void setTaskRelationBug(Integer taskRelationBug) {
        this.taskRelationBug = taskRelationBug;
    }

}
