package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.project.biz.inter.TaskManager;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.Date;
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
        task.setTaskStatus("0");
        task.setTaskOpenedDate(new Date());
        return taskManager.add(task);
    }

    public List<ProjectTask> findListTask(ProjectTask task) {
        return taskManager.findList(task);
    }


    public Pager<ProjectTask> findPagerTask(Integer start, Integer limit, ProjectTask task, String sortName, boolean asc, String condititon, String group) {
        if (condititon != null) {
            return taskManager.findPagerByStatu(start, limit, task, sortName, asc, condititon);
        }
        return taskManager.findPager(start, limit, task, sortName, asc);
    }

    public ProjectTask findTask(Integer taskId) {
        return taskManager.find(taskId);
    }


    public Integer updateTask(ProjectTask task) {
        return taskManager.update(task);
    }
    public Integer updatEditTask(ProjectTask task) {
        return taskManager.update(task);
    }
    public Integer updatCallTask(ProjectTask task) {
        return taskManager.update(task);
    }

    public Pager<ProjectTask> findComplexTask() {
        return null;
    }

    public Map<String, List<ProjectTask>> findTaskByGroup(int projectId, String colum) {
        // TODO Auto-generated method stub
        return null;
    }

}
