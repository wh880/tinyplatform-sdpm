package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.project.biz.inter.TaskManager;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.dao.pojo.TaskChartBean;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.tinysqldsl.Pager;

import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by shenly13343 on 2015-09-20.
 */
@Component
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskManager taskManager;

    public Integer updateDoingTask(ProjectTask task) {
        task.setTaskStatus(ProjectTask.DOING);
        task.setTaskLastEditedDate(new Date());
        return taskManager.update(task);
    }

    public Integer batchAddTask(List<ProjectTask> taskList, Integer projectId) {
        Integer maxNo = taskManager.getMaxNo(projectId);
        if (maxNo == null) {
            maxNo = 0;
        }
        for (ProjectTask task : taskList) {
            task.setTaskNo(++maxNo);
            task.setTaskLastEditedDate(new Date());
            task.setTaskOpenedDate(new Date());
            task.setTaskStatus("1");
            task.setTaskDeleted(ProjectTask.DELETE_NO);
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

    public Pager<ProjectTask> findPagerTaskByMe(Integer start, Integer limit, ProjectTask task, String sortName, boolean asc, OrgUser user) {
        return taskManager.findPagerByMe(start, limit, task, sortName, asc, user);
    }

    public Pager<ProjectTask> findTaskPager(Integer start, Integer limit, ProjectTask task, String sortName, boolean asc, String condititon) {
        if (!StringUtil.isBlank(condititon)) {
            return taskManager.findPagerByStatus(start, limit, task, sortName, asc, condititon);
        }
        return taskManager.findPager(start, limit, task, sortName, asc);
    }

    public ProjectTask findTaskById(Integer taskId) {
        return taskManager.find(taskId);
    }

    public Integer updateTask(ProjectTask task) {
        return taskManager.update(task);
    }

    public Integer updateEditTask(ProjectTask task) {
        task.setTaskLastEditedDate(new Date());
        return taskManager.update(task);
    }

    public Integer updateCallTask(ProjectTask task) {
        return taskManager.update(task);
    }

    public Integer updateFinishTask(ProjectTask task) {
        task.setTaskStatus("3");
        task.setTaskLastEditedDate(new Date());
        task.setTaskLeft(0f);
        return taskManager.update(task);
    }

    public Integer updateStartTask(ProjectTask task) {
        task.setTaskRealStarted(new Date());
        task.setTaskStatus("2");
        task.setTaskLastEditedDate(new Date());
        return taskManager.update(task);
    }

    public Integer updateCloseTask(ProjectTask task) {
        task.setTaskCloseDate(new Date());
        task.setTaskStatus("6");
        task.setTaskLastEditedDate(new Date());
        return taskManager.update(task);
    }

    public Integer updateCancelTask(ProjectTask task) {
        task.setTaskCanceledDate(new Date());
        task.setTaskStatus("5");
        task.setTaskLastEditedDate(new Date());
        return taskManager.update(task);
    }

    public Map<String, List<ProjectTask>> findGroup(String type, Integer projectId) {
        ProjectTask projectTask = new ProjectTask();
        projectTask.setTaskProject(projectId);
        List<ProjectTask> taskList = taskManager.findList(projectTask);
        Map<String, List<ProjectTask>> resMap = new HashMap<String, List<ProjectTask>>();
        for (ProjectTask task : taskList) {
            String value = getFieldValueByName(type, task).toString();
            if (resMap.keySet().contains(value)) {
                resMap.get(value).add(task);
            } else {
                List<ProjectTask> tList = new ArrayList<ProjectTask>();
                tList.add(task);
                resMap.put(value, tList);
            }
        }
        //转化为前台显示格式
        return resMap;
    }

    public List<TaskChartBean> buildChart(String id) {
        return taskManager.findByGroup(id);
    }

    private Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter);
            Object value = method.invoke(o);
            if (value == null) {
                value = "";
            }
            return value;
        } catch (Exception e) {
            return null;
        }
    }

}
