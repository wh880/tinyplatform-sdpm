package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.util.CookieUtils;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.inter.ProjectService;

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

    @RequestMapping("/task/indexs")
    public String jumpTaskIndex(@CookieValue(required = false) Integer projectId, HttpServletResponse response, HttpServletRequest request, Model model) {
        List<Project> list = projectService.findList();
        if (list == null || list.isEmpty()) {
            return "redirect:/project/add";
        }

        Project first = list.get(0);
        projectId = first.getProjectId();
        CookieUtils.setCookie(response, "projectId", projectId.toString(), -1);
        model.addAttribute("projectList", list);
        return "project/task/index.page";
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
