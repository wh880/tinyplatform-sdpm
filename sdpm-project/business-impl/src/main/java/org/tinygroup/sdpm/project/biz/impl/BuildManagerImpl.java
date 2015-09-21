package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.project.biz.inter.BuildManager;
import org.tinygroup.sdpm.project.dao.ProjectBuildDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
@Service
@Transactional
public class BuildManagerImpl implements BuildManager {
    @Autowired
    private ProjectBuildDao projectBuildDao;

    public ProjectBuild find(String id) {
        return null;
    }

    public List<ProjectBuild> findList(ProjectBuild build) {
        return projectBuildDao.query(build);
    }

    public ProjectBuild add(ProjectBuild build) {
        return projectBuildDao.add(build);
    }

    public int update(ProjectBuild build) {
        return projectBuildDao.edit(build);
    }

    public Integer delete(int id) {
        ProjectBuild build = new ProjectBuild();
        build.setBuildId(id);
        build.setBuildDeleted(build.DELETE_YES);
        return projectBuildDao.edit(build);
    }
}
