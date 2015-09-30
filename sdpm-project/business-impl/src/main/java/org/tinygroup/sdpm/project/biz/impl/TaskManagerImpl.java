package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.project.biz.inter.TaskManager;
import org.tinygroup.sdpm.project.dao.ProjectTaskDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
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

    public ProjectTask find(int id) {
        return taskDao.getByKey(id);
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

    public Pager<ProjectTask> findPagerByStatu(Integer start, Integer limit, ProjectTask task, String sortName, boolean asc, String conditon) {
        if (StringUtil.isBlank(sortName)) {
            return taskDao.queryPagerByStuta(start, limit, task, conditon);
            //return taskDao.queryPager(start, limit, task);
        }
        OrderBy orderBy = new OrderBy(sortName, asc);
        return taskDao.queryPagerByStuta(start, limit, task, conditon, orderBy);
        //return taskDao.queryPager(start, limit, task, orderBy);

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
        return taskDao.editTask(task);
    }

    public Integer updateCallTask(ProjectTask task) {
        return taskDao.editcall(task);
    }

    public Integer updateFinishTask(ProjectTask task) {
        return taskDao.editfinish(task);
    }

    public Integer updateStartTask(ProjectTask task) {
        return taskDao.editstart(task);
    }
    public Integer updateCloseTask(ProjectTask task) {
        return taskDao.editclose(task);
    }

    public Integer delete(int id) {
        ProjectTask task = new ProjectTask();
        task.setTaskId(id);
        task.setTaskDeleted(task.DELETE_YES);

        return taskDao.edit(task);
    }
}
