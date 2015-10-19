package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.commons.tools.StringUtil;
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

    public Integer batchSoftDel(String condition) {
        return taskManager.batchSoftDel(condition);
    }

    public Integer batchAdd(List<ProjectTask> taskList, Integer projectId) {
        for (ProjectTask task : taskList) {
            task.setTaskLastEditedDate(new Date());
            task.setTaskOpenedDate(new Date());
            task.setTaskStatus("1");
            task.setTaskDeleted(task.DELETE_NO);
            task.setTaskProject(projectId);
        }
        int[] res = taskManager.batchAdd(taskList);
        return res.length;
    }

    public ProjectTask addTask(ProjectTask task) {
        task.setTaskStatus("1");
        task.setTaskOpenedDate(new Date());
        return taskManager.add(task);
    }

    public List<ProjectTask> findListTask(ProjectTask task) {
        return taskManager.findList(task);
    }


    public Pager<ProjectTask> findPagerTask(Integer start, Integer limit, ProjectTask task, String sortName, boolean asc, String condititon, String group) {
        if (!StringUtil.isBlank(condititon)) {
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
    public Integer updateEditTask(ProjectTask task) {
        task.setTaskLastEditedDate(new Date());
        return taskManager.updateEditTask(task);
    }
    public Integer updateCallTask(ProjectTask task) {
        return taskManager.updateCallTask(task);
    }

    public Integer updateFinishTask(ProjectTask task) {
        task.setTaskStatus("3");
        task.setTaskLastEditedDate(new Date());
        return taskManager.updateFinishTask(task);
    }
    public Integer updateStartTask(ProjectTask task) {
        //task.setTaskRealStarted(new Date());
        task.setTaskStatus("2");
        task.setTaskLastEditedDate(new Date());
        return taskManager.updateStartTask(task);
    }
    public Integer updateCloseTask(ProjectTask task) {
        task.setTaskCloseDate(new Date());
        task.setTaskStatus("6");
        task.setTaskLastEditedDate(new Date());
        return taskManager.updateCloseTask(task);
    }

    public Pager<ProjectTask> findComplexTask() {
        return null;
    }

    public Map<String, List<ProjectTask>> findTaskByGroup(int projectId, String colum) {
        // TODO Auto-generated method stub
        return null;
    }

}
