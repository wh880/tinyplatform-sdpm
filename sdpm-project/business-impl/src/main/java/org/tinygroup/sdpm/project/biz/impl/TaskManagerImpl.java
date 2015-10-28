package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.project.biz.inter.TaskManager;
import org.tinygroup.sdpm.project.dao.ProjectTaskDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.dao.pojo.TaskChartBean;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
@Service
@Transactional
public class TaskManagerImpl implements TaskManager {
    @Autowired
    private ProjectTaskDao taskDao;

    public Integer batchSoftDel(String condition) {
        return taskDao.batchSoftDel(condition);
    }

    public int[] batchAdd(List<ProjectTask> taskList) {
        return taskDao.batchInsert(taskList);
    }

    public ProjectTask find(int id) {
        return taskDao.findTaskStory(id);
    }

    public List<ProjectTask> findList(ProjectTask task) {
        return taskDao.query(task);
    }

    public Integer getTaskSumByStory(Integer storyId) {
        return taskDao.getSumByStory(storyId);
    }

    public Pager<ProjectTask> findPager(Integer start, Integer limit, ProjectTask task, String sortName, boolean asc) {
        if (StringUtil.isBlank(sortName)) {
            return taskDao.queryPager(start, limit, task);
        }
        OrderBy orderBy = new OrderBy(sortName, asc);
        return taskDao.queryPager(start, limit, task, orderBy);
    }

    public Pager<ProjectTask> findPagerByStatus(Integer start, Integer limit, ProjectTask task, String sortName, boolean asc, String condition) {
        if (StringUtil.isBlank(sortName)) {
            return taskDao.queryPagerByStatus(start, limit, task, condition);
        }
        OrderBy orderBy = new OrderBy(sortName, asc);
        return taskDao.queryPagerByStatus(start, limit, task, condition, orderBy);
    }

    public ProjectTask add(ProjectTask task) {
        return taskDao.add(task);
    }

    public Integer update(ProjectTask task) {
        return taskDao.edit(task);
    }

    public Integer updateTask(ProjectTask task) {
        return taskDao.edit(task);
    }

    public Integer updateEditTask(ProjectTask task) {
        return taskDao.updateColum(task);
    }

    public Integer updateCallTask(ProjectTask task) {
        return taskDao.editcall(task);
    }

    public Integer updateFinishTask(ProjectTask task) {
        return taskDao.updateColum(task);
    }

    public Integer updateStartTask(ProjectTask task) {
        return taskDao.editstart(task);
    }

    public Integer updateCloseTask(ProjectTask task) {
        return taskDao.updateColum(task);
    }

    public List<TaskChartBean> findByGroup(String id) {
        if ("1".equals(id)) {
            return null;
        } else if ("2".equals(id)) {
            return taskDao.queryChartModule();
        } else if ("3".equals(id)) {
            return taskDao.queryChartType();
        } else if ("4".equals(id)) {
            return taskDao.queryChartPri();
        } else if ("5".equals(id)) {
            return taskDao.queryChartStatus();
        } else if ("6".equals(id)) {
            return taskDao.queryChartDeadLine();
        } else if ("10".equals(id)) {
            return taskDao.queryChartFinishedBy();
        } else if ("15".equals(id)) {
            return taskDao.queryChartAssigned();
        } else {
            return null;
        }
    }

    public Integer updateColum(ProjectTask task) {
        return taskDao.updateColum(task);
    }

    public Integer delete(int id) {
        ProjectTask task = new ProjectTask();
        task.setTaskId(id);
        task.setTaskDeleted(task.DELETE_YES);

        return taskDao.edit(task);
    }

    public List<ProjectTask> findAll() {
        return taskDao.findAll();
    }
}
