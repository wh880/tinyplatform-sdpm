package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.util.CookieUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.inter.TeamService;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by wangying14938 on 2015-09-22.团队
 */
@Controller
@RequestMapping("/project/team")
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
    public String preTeamManage(Model model) {
        List<OrgUser> userList = userService.findUserList(new OrgUser());
        model.addAttribute("userList", userList);
        return "";
    }
}
