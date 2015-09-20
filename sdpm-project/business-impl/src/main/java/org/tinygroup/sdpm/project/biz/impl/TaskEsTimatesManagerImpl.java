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
    private ProjectTaskestimateDao projectTaskestimateDao;

    public ProjectTaskestimate find(String id) {
        return null;
    }

    public List<ProjectTaskestimate> findList(ProjectTaskestimate taskestimate) {
        return null;
    }

    public ProjectTaskestimate add(ProjectTaskestimate taskestimate) {
        return null;
    }

    public ProjectTaskestimate update(ProjectTaskestimate taskestimate) {
        return null;
    }

    public Integer delete(String id) {
        return null;
    }
}
