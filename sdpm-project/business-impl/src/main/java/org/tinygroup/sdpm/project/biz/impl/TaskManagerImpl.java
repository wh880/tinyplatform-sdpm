package org.tinygroup.sdpm.project.biz.impl;

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
    private ProjectTaskDao projectTaskDao;

    public ProjectTask find(String id) {
        return null;
    }

    public List<ProjectTask> findList(ProjectTask task) {
        return null;
    }

    public ProjectTask add(ProjectTask task) {
        return null;
    }

    public ProjectTask update(ProjectTask task) {
        return null;
    }

    public Integer delete(String id) {
        return null;
    }
}
