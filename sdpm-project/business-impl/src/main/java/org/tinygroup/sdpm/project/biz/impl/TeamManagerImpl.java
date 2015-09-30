package org.tinygroup.sdpm.project.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.project.biz.inter.TeamManager;
import org.tinygroup.sdpm.project.dao.ProjectTeamDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by shenly13343 on 2015-09-18.
 */
@Service
@Transactional
public class TeamManagerImpl implements TeamManager {
    @Autowired
    private ProjectTeamDao teamDao;

    public ProjectTeam find(int id) {
        return teamDao.getByKey(id);
    }

    public List<ProjectTeam> findListAccount(int projectId) {
        ProjectTeam team = new ProjectTeam();
        team.setProjectId(projectId);
        return teamDao.query(team);
    }

    public List<ProjectTeam> findByProjectId(Integer projectId) {
        return teamDao.findByProjectId(projectId);
    }

    public ProjectTeam add(ProjectTeam team) {
        return teamDao.add(team);
    }

    public Integer update(ProjectTeam team) {
        return teamDao.edit(team);
    }

    public Integer delete(int id) {
        return teamDao.deleteByKey(id);
    }

    public Pager<ProjectTeam> findPager(ProjectTeam team, Integer start, Integer limit, String order, boolean asc) {
        if (StringUtil.isBlank(order)) {
            return teamDao.queryPager(start, limit, team);
        } else {
            OrderBy orderBy = new OrderBy(order, asc);
            return teamDao.queryPager(start, limit, team, orderBy);
        }

    }

}
