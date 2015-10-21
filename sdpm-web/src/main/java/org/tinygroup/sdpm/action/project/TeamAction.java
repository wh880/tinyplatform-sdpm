package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.inter.TeamService;
import org.tinygroup.sdpm.util.CookieUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangying14938 on 2015-09-22.团队
 */
@Controller
@RequestMapping("/a/project/team")
public class TeamAction extends BaseController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;

    @RequestMapping("manage")
    public String manage(ProjectTeam team, Model model) {
        return "";
    }

    @RequestMapping("find")
    public String find(Model model, HttpServletRequest request, Integer start, Integer limit, String order, String ordertype) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        ProjectTeam team = new ProjectTeam();
        team.setProjectId(projectId);
        Pager<ProjectTeam> pager = teamService.findPager(team, start, limit, order, ordertype);
        model.addAttribute("teamPager", pager);
        return "project/team/manageTableData.pagelet";
    }

    @RequestMapping("/preTeamManage")
    public String preTeamManage(Model model, HttpServletRequest request) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        List<ProjectTeam> teamList = teamService.findTeamByProjectId(projectId);
        List<OrgUser> userList = userService.findUserList(new OrgUser());
        //删除用户列表中的团队成员
        for (ProjectTeam team : teamList) {
            for (int i = 0; i < userList.size(); i++) {
                if (team.getTeamUserId().equals(userList.get(i).getOrgUserId())) {
                    userList.remove(userList.get(i));
                    i--;
                }
            }
        }

        model.addAttribute("userList", userList);
        model.addAttribute("teamList", teamList);
        return "project/team/teamManage.page";
    }

    @RequestMapping("/teamManageSave")
    public String teamManageSave(Teams teams, HttpServletRequest request) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        List<ProjectTeam> updateList = new ArrayList<ProjectTeam>();
        List<ProjectTeam> addList = new ArrayList<ProjectTeam>();

        List<ProjectTeam> teamList = teams.getTeamList();
        //删选没有账号的team
        for (int i = 0; i < teamList.size(); i++) {
            if (StringUtil.isBlank(teamList.get(i).getTeamUserId())) {
                teamList.remove(teamList.get(i));
                i--;
            }
        }
        //根据是否有teamId分为增加列表和更新列表
        for (ProjectTeam team : teamList) {
            if (team.getId() != null) {
                team.setProjectId(projectId);
                updateList.add(team);
            } else {
                team.setProjectId(projectId);
                addList.add(team);
            }
        }

        Integer addRes = teamService.batchAdd(addList);
        Integer updateRes = teamService.batchUpdate(updateList);

        return "project/team/index.page";
    }
}
