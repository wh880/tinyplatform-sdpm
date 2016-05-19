package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.sdpm.project.biz.inter.BurnManager;
import org.tinygroup.sdpm.project.dao.ProjectBurnDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBurn;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
@Service
public class BurnManagerImpl implements BurnManager {
    @Autowired
    private ProjectBurnDao burnDao;

    public ProjectBurn find(int id) {
        return burnDao.getByKey(id);
    }

    public List<ProjectBurn> findList(ProjectBurn burn) {
        return burnDao.query(burn);
    }

    public ProjectBurn add(ProjectBurn burn) {
        return burnDao.add(burn);
    }

    public int update(ProjectBurn burn) {
        return burnDao.edit(burn);
    }

    public Integer delete(int id) {
        return burnDao.deleteByKey(id);
    }
}
