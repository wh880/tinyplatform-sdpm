package org.tinygroup.sdpm.action.project.dto;

import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;

import java.util.List;

/**
 * Created by shenly13343 on 2015-10-14.
 */
public class Teams {
    private List<ProjectTeam> teamList;

    public List<ProjectTeam> getTeamList() {
        return teamList;
    }

    public void setTeamList(List<ProjectTeam> teamList) {
        this.teamList = teamList;
    }
}
