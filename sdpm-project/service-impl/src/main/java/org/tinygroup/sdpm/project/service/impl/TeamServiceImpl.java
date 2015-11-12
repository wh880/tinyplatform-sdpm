package org.tinygroup.sdpm.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.project.biz.inter.TeamManager;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.inter.TeamService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by wangying14938 on 2015-09-18.
 */
@Component
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamManager teamManager;

    public List<ProjectTeam> findTeamList(ProjectTeam team) {
        return teamManager.find(team);
    }

    public Integer batchAdd(List<ProjectTeam> list) {
        return teamManager.batchAdd(list);
    }

    public Integer batchUpdate(List<ProjectTeam> list) {
        return teamManager.batchUpdate(list);
    }


    public Integer delete(int id) {
        return teamManager.delete(id);
    }

    public List<ProjectTeam> findTeamByProjectId(Integer projectId) {
        return teamManager.findByProjectId(projectId);
    }


    public Pager<ProjectTeam> findPager(ProjectTeam team, Integer start, Integer limit, String order, String ordertype) {
        boolean asc = "asc".equals(ordertype) ? true : false;
        return teamManager.findPager(team, start, limit, order, asc);
    }
}
