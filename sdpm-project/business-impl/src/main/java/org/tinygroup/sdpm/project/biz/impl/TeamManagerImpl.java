package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.project.biz.inter.TeamManager;
import org.tinygroup.sdpm.project.dao.ProjectTeamDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
@Service
@Transactional
public class TeamManagerImpl implements TeamManager {
    @Autowired
    private ProjectTeamDao projectTeamDao;

    public ProjectTeam find(String id) {
        return null;
    }

    public List<ProjectTeam> findList(ProjectTeam team) {
        return null;
    }

    public ProjectTeam add(ProjectTeam team) {
        return null;
    }

    public ProjectTeam update(ProjectTeam team) {
        return null;
    }

    public Integer delete(String id) {
        return null;
    }
}
