package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.project.biz.inter.TaskRelationManager;
import org.tinygroup.sdpm.project.dao.ProjectTaskrelationDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTaskrelation;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
@Service
@Transactional
public class TaskRelationManagerImpl implements TaskRelationManager {
    @Autowired
    private ProjectTaskrelationDao taskrelationDao;

    public ProjectTaskrelation find(int id) {
        return taskrelationDao.getByKey(id);
    }

    public List<ProjectTaskrelation> findList(ProjectTaskrelation taskrelation) {
        return taskrelationDao.query(taskrelation);
    }

    public ProjectTaskrelation add(ProjectTaskrelation taskrelation) {
        return taskrelationDao.add(taskrelation);
    }

    public Integer update(ProjectTaskrelation taskrelation) {
        return taskrelationDao.edit(taskrelation);
    }

    public Integer delete(int id) {
        return taskrelationDao.deleteByKey(id);
    }
}
