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

    public Integer getProjectTeamTimeInfo(Integer projectId) {
        int total = 0;
        List<ProjectTeam> list = findTeamByProjectId(projectId);
        if (list != null) {
            for (ProjectTeam projectTeam : list) {
                Float teamDays = projectTeam.getTeamDays();
                Float teamHours = projectTeam.getTeamHours();
                if (teamDays != null && teamHours != null) {
                    total += teamDays * teamHours;
                }
            }
        }
        return total;
    }

    public List<ProjectTeam> findTeamList(ProjectTeam team) {
        return teamManager.find(team);
    }

    public Integer batchAddTeam(List<ProjectTeam> list) {
        return teamManager.batchAdd(list);
    }

    public Integer batchUpdateTeam(List<ProjectTeam> list) {
        return teamManager.batchUpdate(list);
    }

    public Integer deleteTeam(int id) {
        return teamManager.delete(id);
    }

    public List<ProjectTeam> findTeamByProjectId(Integer projectId) {
        return teamManager.findByProjectId(projectId);
    }

    public List<ProjectTeam> findTeamByProductId(Integer productId) {
        return teamManager.findByProductId(productId);
    }

    public Pager<ProjectTeam> findProjectTeamPager(ProjectTeam team, Integer start, Integer limit, String order, String ordertype) {
        boolean asc = "asc".equals(ordertype) ? true : false;
        return teamManager.findPager(team, start, limit, order, asc);
    }

    public List<String> getMenuIdListByProjectAndUser(Integer projectId, String userId) {
        return teamManager.getMenuIdListByProjectAndUser(projectId, userId);
    }

    public List<String> getMenuIdListByProductAndUser(Integer productId, String userId) {
        return teamManager.getMenuIdListByProductAndUser(productId, userId);
    }
}
