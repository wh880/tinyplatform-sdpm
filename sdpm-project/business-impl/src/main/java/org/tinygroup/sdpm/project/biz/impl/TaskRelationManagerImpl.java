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
    private ProjectTaskrelationDao projectTaskrelationDao;

    public ProjectTaskrelation find(int id) {
        return projectTaskrelationDao.getByKey(id);
    }

    public List<ProjectTaskrelation> findList(ProjectTaskrelation taskrelation) {
        return null;
    }

    public ProjectTaskrelation add(ProjectTaskrelation taskrelation) {
        return null;
    }

    public ProjectTaskrelation update(ProjectTaskrelation taskrelation) {
        return null;
    }

    public Integer delete(String id) {
        return null;
    }
}
