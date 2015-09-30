package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.project.biz.inter.TeamManager;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.inter.TeamService;

import java.util.List;

/**
 * Created by wangying14938 on 2015-09-18.
 */
@Component
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamManager teamManager;
    public int save(ProjectTeam team) {
        return 0;
    }

    public int delete(int id) {
        return 0;
    }

    public List<ProjectTeam> findTeamByProjectId(Integer projectId) {
        return teamManager.findByProjectId(projectId);
    }
}
