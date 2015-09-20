package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.project.biz.inter.BurnManager;
import org.tinygroup.sdpm.project.dao.ProjectBurnDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBurn;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
@Service
@Transactional
public class BurnManagerImpl implements BurnManager {
    @Autowired
    private ProjectBurnDao projectBurnDao;

    public ProjectBurn find(String id) {
        return null;
    }

    public List<ProjectBurn> findList(int projectId) {
        return null;
    }

    public List<ProjectBurn> findList(ProjectBurn burn) {
        return null;
    }

    public ProjectBurn add(ProjectBurn burn) {
        return null;
    }

    public ProjectBurn update(ProjectBurn burn) {
        return null;
    }

    public Integer delete(String id) {
        return null;
    }
}
