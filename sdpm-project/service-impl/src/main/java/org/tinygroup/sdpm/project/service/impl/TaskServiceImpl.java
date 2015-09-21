package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.project.biz.inter.TaskManager;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;
import java.util.Map;

/**
 * Created by shenly13343 on 2015-09-20.
 */
@Component
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskManager taskManager;

    public ProjectTask addTask(ProjectTask task) {
        return taskManager.add(task);
    }

    public Map<String, List<ProjectTask>> findByGroup(int projectId, String colum) {
        return null;
    }

    public List<ProjectTask> findListTask(ProjectTask task) {
        return taskManager.findList(task);
    }

    public Integer updateTask(ProjectTask task) {
        return taskManager.update(task);
    }

    public Pager<ProjectTask> findComplex() {
        return null;
    }
}
