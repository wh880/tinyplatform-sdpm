package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
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
@Transactional
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskManager taskManager;
    @Transactional(readOnly = true)
    public ProjectTask getProjectTaskTimeInfo(Integer projectId) {
        return taskManager.getProjectTaskTimeInfo(projectId);
    }

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
            if("1,".equals(task.getTaskPri())) {
                task.setTaskPri("1");
            }
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
    @Transactional(readOnly = true)
    public List<ProjectTask> findListTask(ProjectTask task) {
        return taskManager.findList(task);
    }
    @Transactional(readOnly = true)
    public Pager<ProjectTask> findPagerTaskByMe(Integer start, Integer limit, ProjectTask task, String sortName, boolean asc, OrgUser user) {
        return taskManager.findPagerByMe(start, limit, task, sortName, asc, user);
    }
    @Transactional(readOnly = true)
    public Pager<ProjectTask> findTaskPager(Integer start, Integer limit, ProjectTask task, String sortName, boolean asc, ConditionCarrier carrier) {
        if (carrier != null) {
            return taskManager.findPagerByStatus(start, limit, task, sortName, asc, carrier);
        }
        return taskManager.findPager(start, limit, task, sortName, asc);
    }
    @Transactional(readOnly = true)
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
        task.setTaskStatus("2");
        task.setTaskLastEditedDate(new Date());
        return taskManager.update(task);
    }

    public Integer updateCloseTask(ProjectTask task) {
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
    @Transactional(readOnly = true)
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
    @Transactional(readOnly = true)
    public List<TaskChartBean> buildChart(String id) {
        return taskManager.findByGroup(id);
    }

    public Integer deleteTask(Integer taskId) {
        if (taskId == null) {
            return 0;
        }
        ProjectTask projectTask = new ProjectTask();
        projectTask.setTaskId(taskId);
        projectTask.setTaskDeleted(ProjectTask.DELETE_YES);
        return taskManager.update(projectTask);
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

    @Override
    @Transactional(readOnly = true)
    public ProjectTask findTaskByTaskId(Integer taskId) {
        return taskManager.findTaskByTaskId(taskId);
    }
}
