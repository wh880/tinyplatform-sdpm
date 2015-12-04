package org.tinygroup.sdpm.project.dao.pojo;

import java.io.Serializable;

/**
 * Created by shenly13343 on 2015-10-26.
 */
public class TaskChartBean implements Serializable{
    private Integer taskCount;
    private String title;
    private float percent;

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(Integer taskCount) {
        this.taskCount = taskCount;
    }

}
