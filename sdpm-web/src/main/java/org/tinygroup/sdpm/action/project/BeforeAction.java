package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.util.CookieUtils;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.template.rumtime.convert.IntegerBigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by shenly13343 on 2015-09-28.
 */
@Controller
@RequestMapping("/projectmanage")
public class BeforeAction {
    @Autowired
    private ProjectService projectService;

//    @RequestMapping("/task/index")
//    public String jumpTaskIndex(@CookieValue(required = false) Integer projectId, HttpServletResponse response, HttpServletRequest request, Model model) {
//        List<Project> list = projectService.findList();
//        if (list == null || list.isEmpty()) {
//            return "redirect:/project/add";
//        }
//
//        Project first = list.get(0);
//        projectId = first.getProjectId();
//        CookieUtils.setCookie(response, "projectId", projectId.toString(), -1);
//        model.addAttribute("projectList", list);
//        return "project/task/index.page";
//    }

    @RequestMapping("/demand/index")
    public String jumpStoryIndex() {
        return "project/demand/index.page";
    }

    @RequestMapping("/bug/index")
    public String jumpBugIndex() {
        return "project/bug/index.page";
    }

    @RequestMapping("/build/index")
    public String jumpBuildIndex(Model model, HttpServletRequest request) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        Project project = projectService.findById(projectId);
        model.addAttribute("project", project);
        return "project/version/index.page";
    }

    @RequestMapping("/test/index")
    public String jumpTestIndex() {
        return "project/test/index.page";
    }

    @RequestMapping("/team/index")
    public String jumpTeamIndex() {
        return "project/team/index.page";
    }

    @RequestMapping("/doc/index")
    public String jumpDocIndex() {
        return "project/document/index.page";
    }

    @RequestMapping("/survey/index")
    public String jumpSurveyIndex(Model model, HttpServletRequest request) {
        Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
        Project project = projectService.findById(projectId);
        model.addAttribute("selProject", project);
        return "/project/survey/index.page";
    }

    @RequestMapping("/select")
    public String selectProject(Integer projectId, String oldUrl, Model model, HttpServletResponse response, HttpServletRequest request) {
        CookieUtils.setCookie(response, "cookie_projectId", projectId.toString(), -1);
        Project selProject = projectService.findById(projectId);
        List<Project> list = projectService.findList();
        request.getSession().setAttribute("selProject", selProject);
        request.getSession().setAttribute("projectList", list);
        return "redirect:" + oldUrl;
    }

}
