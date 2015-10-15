package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.sdpm.common.util.CookieUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.project.service.inter.TeamService;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by shenly13343 on 2015-09-21.
 */
@Controller
@RequestMapping("/project")
public class ProjectAction extends BaseController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProjectProductService projectProductService;

    @RequestMapping("/find")
    public String find(Integer projectId, Model model) {
        if (projectId == null) {
            projectService.findById(projectId);
        }
        return "project/addProject.page";
    }

    @RequestMapping("/findProjects")
    public String findProjects(Integer start, Integer limit, String order, String ordertype, Model model) {
        Pager<Project> projectPager = projectService.findProjects(start, limit, order, ordertype);
        model.addAttribute("projectPager", projectPager);
        return "project/allProjectData.pagelet";
    }

    @RequestMapping("/preadd")
    public String preadd(Model model) {
        List<Product> list = productService.findProductList(new Product(), "productId", "desc");
        model.addAttribute("prodcutList", list);
        return "project/addProject.page";
    }

    @RequestMapping("add")
    public String addPage(Model model, HttpServletResponse response, Project project, Integer[] linkProduct) {

        Project tProject = projectService.addProject(project);
        projectProductService.addLink(linkProduct, tProject.getProjectId());
        CookieUtils.setCookie(response, "cookie_projectId", tProject.getProjectId().toString());

        return "project/allProject.page";
    }


    @RequestMapping("/allProject")
    public String jumpAllProject() {
        return "project/allProject.page";
    }

    @RequestMapping("/edit")
    public String form(Integer projectId, Model model) {
        if (projectId != null) {
            Project project = projectService.findById(projectId);
            List<ProjectTeam> teamList = teamService.findTeamByProjectId(project.getProjectId());
            model.addAttribute("teamList", teamList);
            model.addAttribute("project", project);
            //还需要查询其他相关任务剩余时间的信息
            return "project/survey/edit.page";
        }
        return "error";
    }

    @RequestMapping(value = "/editsave", method = RequestMethod.POST)
    public String editSave(Project project, Model model) {
        if (project.getProjectId() == null) {
            projectService.addProject(project);
        } else {
            projectService.updateProject(project);
        }
        model.addAttribute("project", project);
        return "project/survey/index.page";
    }
    @RequestMapping(value = "/delaysave", method = RequestMethod.POST)
    public String delaySave(Project project, Model model) {
        if (project.getProjectId() == null) {
            projectService.addProject(project);
        } else {
            projectService.updateProject(project);
        }
        model.addAttribute("project", project);
        return "redirect:/projectmanage/survey/index";
    }
    @RequestMapping("/delay")
    public String delay(Integer projectId, Model model) {
        if (projectId != null) {
            Project project = projectService.findById(projectId);
            model.addAttribute("project", project);
            //还需要查询其他相关任务剩余时间的信息
            return "/project/survey/delay.pagelet";
        }
        return "error";
    }

    @RequestMapping("/hangup")
    public String hangup(Integer projectId, Model model) {
        if (projectId != null) {
            Project project = projectService.findById(projectId);
            model.addAttribute("project", project);
            //还需要查询其他相关任务剩余时间的信息
            return "/project/survey/hang-up.pagelet";
        }
        return "error";
    }

    @RequestMapping("/basicInformation")
    public String basicInformation(Integer projectID, Model model){
        Project project = projectService.findById(projectID);
        model.addAttribute("project", project);
        return "project/survey/basicInformation.pagelet";
    }

    @RequestMapping("/allProject")
    public String allProject() {
        return "project/allProject.page";
    }
}
