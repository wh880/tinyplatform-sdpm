package org.tinygroup.sdpm.project.dao.pojo;

/**
 * Created by shenly13343 on 2015-10-26.
 */
public class TaskChartBean {
    private Integer taskCount;
    private String moduleName;
    private String assignedTo;

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }
}
