package org.tinygroup.sdpm.statistic.dao.pojo;

/**
 * Created by MCK on 2015/10/22.
 */
public class ProjectTaskSta {
    /**
     * 任务Id
     */
    private Integer taskId;
    /**
     * 项目名
     */
    private  String projectName;
    /**
     * 任务指派对象
     */
    private String assignedTo;
    /**
     * 任务数
     */
    private Integer taskNum;
    /**
     * 总预计
     */
    private float estimate;
    /**
     * 总剩余

     */
    private float left;
    //后面需求在加字段


    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public float getEstimate() {
        return estimate;
    }

    public void setEstimate(float estimate) {
        this.estimate = estimate;
    }



}
