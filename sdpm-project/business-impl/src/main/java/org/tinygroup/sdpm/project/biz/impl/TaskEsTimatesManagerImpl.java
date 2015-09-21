package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.project.biz.inter.TaskEsTimateManager;
import org.tinygroup.sdpm.project.dao.ProjectTaskestimateDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTaskestimate;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
@Service
@Transactional
public class TaskEsTimatesManagerImpl implements TaskEsTimateManager {
    @Autowired
    private ProjectTaskestimateDao taskestimateDao;

    public ProjectTaskestimate find(int id) {
        return taskestimateDao.getByKey(id);
    }

    public List<ProjectTaskestimate> findList(ProjectTaskestimate taskestimate) {
        return taskestimateDao.query(taskestimate);
    }

    public ProjectTaskestimate add(ProjectTaskestimate taskestimate) {
        return taskestimateDao.add(taskestimate);
    }

    public Integer update(ProjectTaskestimate taskestimate) {
        return taskestimateDao.edit(taskestimate);
    }

    public Integer delete(int id) {
        return taskestimateDao.deleteByKey(id);
    }
}
