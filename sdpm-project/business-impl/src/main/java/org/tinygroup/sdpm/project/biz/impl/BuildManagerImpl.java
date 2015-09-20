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

    public ProcessBuilder find(String id) {
        return null;
    }

    public List<ProcessBuilder> findList(ProcessBuilder build) {
        return null;
    }

    public ProjectBuild add(ProjectBuild build) {
        return null;
    }

    public ProjectBuild update(ProjectBuild build) {
        return null;
    }

    public Integer delete(String id) {
        return null;
    }
}
