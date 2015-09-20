package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.project.biz.inter.ProjectManager;
import org.tinygroup.sdpm.project.dao.ProjectDao;
import org.tinygroup.sdpm.project.dao.pojo.Project;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
@Service
@Transactional
public class ProjectManagerImpl implements ProjectManager {
    @Autowired
    private ProjectDao projectDao;

    public Project find(String projectId) {
        return null;
    }

    public List<Project> findList() {
        return null;
    }

    public Project add(Project project) {
        return null;
    }

    public Project update(Project project) {
        return null;
    }

    public Integer delete(String projectId) {
        return null;
    }
}
