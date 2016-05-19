package org.tinygroup.sdpm.action.project;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.dto.project.Teams;
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.RoleService;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.project.service.inter.TeamService;
import org.tinygroup.sdpm.util.CookieUtils;
import org.tinygroup.sdpm.util.ProjectOperate;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 团队
 * Created by wangying14938 on 2015-09-22.
 */
@Controller
@RequestMapping("/a/project/team")
public class ProjectTeamAction extends BaseController {
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ProjectService projectService;

    @RequiresPermissions("team")
    @RequestMapping("/index")
    public String index() {
        return "project/index/team/index";
    }

    @RequestMapping("find")
    public String find(Model model, HttpServletRequest request, HttpServletResponse response,
                       Integer start, Integer limit, String order, String ordertype) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }
        ProjectTeam team = new ProjectTeam();
        team.setProjectId(projectId);
        Pager<ProjectTeam> pager = teamService.findProjectTeamPager(team, start, limit, order, ordertype);
        model.addAttribute("teamPager", pager);
        return "project/data/team/manageTableData.pagelet";
    }

    @RequiresPermissions("pro-team-report")
    @RequestMapping("/preTeamManage")
    public String preTeamManage(Model model, HttpServletRequest request, HttpServletResponse response) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }

        OrgRole orgRole = new OrgRole();
        orgRole.setOrgRoleType(OrgRole.ROLE_TYPE_PROJECT);
        List<OrgRole> roleList = roleService.findRoleList(orgRole);
        model.addAttribute("roleList", roleList);

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
        return "project/team/teamManage";
    }

    @RequestMapping("/teamManageSave")
    public String teamManageSave(Teams teams, HttpServletRequest request, HttpServletResponse response) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }
        List<ProjectTeam> updateList = new ArrayList<ProjectTeam>();
        List<ProjectTeam> addList = new ArrayList<ProjectTeam>();

        List<ProjectTeam> teamList = teams.getTeamList();
        //删选没有账号的team
        for (int i = 0; i < teamList.size(); i++) {
            if (teamList.get(i) == null
                    || StringUtil.isBlank(teamList.get(i).getTeamUserId())
                    || StringUtil.isBlank(teamList.get(i).getTeamRole())) {
                teamList.remove(teamList.get(i));
                i--;
            }
        }

        //删除重复
        for(int i=0;i<teamList.size()-1;i++)
        {
            for(int j=teamList.size()-1;j>i;j--)
            {
                if(teamList.get(i).getTeamUserId().equals(teamList.get(j).getTeamUserId()))
                    teamList.remove(j);
            }
        }

        //根据是否有teamId分为增加列表和更新列表
        for (ProjectTeam team : teamList) {
            if (team.getId() != null) {
                team.setProjectId(projectId);
                updateList.add(team);
            } else {
                team.setProjectId(projectId);
                if (team.getTeamDays() == null) {
                    team.setTeamDays((float) 0.0);
                }
                if (team.getTeamHours() == null) {
                    team.setTeamHours((float) 0.0);
                }
                addList.add(team);
            }
        }
        teamService.batchAddTeam(addList);
        teamService.batchUpdateTeam(updateList);
        return "redirect:" + adminPath + "/project/team/index";
    }

    @RequiresPermissions("pro-team-delete")
    @ResponseBody
    @RequestMapping("/del")
    public Map<String, String> del(Integer id) {
        Integer res = teamService.deleteTeam(id);
        return resultMap(res > 0 ? true : false, res > 0 ? "删除成功" : "删除失败");
    }

    @RequestMapping("nextTr")
    public String getNextTeamTr(Integer a, Model model) {
        List<OrgUser> userList = userService.findUserList(null);
        model.addAttribute("userList", userList);
        OrgRole role = new OrgRole();
        role.setDeleted(0);
        role.setOrgRoleType(OrgRole.ROLE_TYPE_PROJECT);
        List<OrgRole> roleList = roleService.findRoleList(role);
        model.addAttribute("roleList", roleList);
        model.addAttribute("a", a + 1);
        return "/product/page/team/teamAddTr.pagelet";
    }

    @RequestMapping("batchTeamTr")
    public String batchTeamTr(Integer a, String[] userIds, Model model) {
        List<OrgUser> userList = userService.findUserList(null);
        model.addAttribute("userList", userList);
        OrgRole role = new OrgRole();
        role.setDeleted(0);
        role.setOrgRoleType(OrgRole.ROLE_TYPE_PROJECT);
        List<OrgRole> roleList = roleService.findRoleList(role);
        model.addAttribute("roleList", roleList);
        model.addAttribute("a", a + 1);
        model.addAttribute("userIds", userIds);
        return "/product/page/team/teamAddTr.pagelet";
    }

    @RequestMapping("copy")
    public String teamCopy(Model model) {
        Project project = new Project();
        project.setProjectDeleted("0");
        List<Project> projects = projectService.findProjectList(project, null, "false");
        model.addAttribute("projectList", projects);
        return "project/modal/team/teamCopy.pagelet";
    }

    @RequestMapping("teamCopy")
    public String teamCopy(Integer a, Integer projectId, Model model, HttpServletRequest request) {

        List<ProjectTeam> teams = teamService.findTeamByProjectId(projectId);
        String thisP = CookieUtils.getCookie(request, ProjectOperate.COOKIE_PROJECT_ID);
        if (!StringUtil.isBlank(thisP)) {
            List<ProjectTeam> thisTeams = teamService.findTeamByProjectId(Integer.parseInt(thisP));
            for (int i = 0; i < teams.size(); i++) {
                for (ProjectTeam projectTeam : thisTeams) {
                    if (projectTeam.getTeamUserId().equals(teams.get(i).getTeamUserId())) {
                        teams.remove(teams.get(i));
                        i--;
                        break;
                    }
                }
            }
        }

        List<OrgUser> userList = userService.findUserList(null);
        model.addAttribute("userList", userList);
        OrgRole role = new OrgRole();
        role.setDeleted(0);
        role.setOrgRoleType(OrgRole.ROLE_TYPE_PROJECT);
        List<OrgRole> roleList = roleService.findRoleList(role);
        model.addAttribute("roleList", roleList);
        model.addAttribute("a", a + 1);
        model.addAttribute("copyTeam", teams);
        return "/product/page/team/teamAddTr.pagelet";
    }
}
