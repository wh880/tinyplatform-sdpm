package org.tinygroup.sdpm.project.biz.impl;

import com.sun.jmx.snmp.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.project.biz.inter.TaskManager;
import org.tinygroup.sdpm.project.dao.ProjectTaskDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;

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

    public ProjectTask add(ProjectTask task) {
        return taskDao.add(task);
    }

    public Integer update(ProjectTask task) {
        return taskDao.edit(task);
    }

    public Integer delete(int id) {
        ProjectTask task = new ProjectTask();
        task.setTaskId(id);
        task.setTaskDeleted(task.DELETE_YES);

        return taskDao.edit(task);
    }
}
