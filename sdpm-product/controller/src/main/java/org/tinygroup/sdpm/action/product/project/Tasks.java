package org.tinygroup.sdpm.action.product.project;

import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;

import java.util.List;

/**
 * Created by shenly13343 on 2015-10-13.
 */
public class Tasks {
    private List<ProjectTask> taskList;

    public List<ProjectTask> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<ProjectTask> taskList) {
        this.taskList = taskList;
    }
}
