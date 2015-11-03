package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.util.ProjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by shenly13343 on 2015-09-28.
 */
@Controller
@RequestMapping("/a/project/manage")
public class BeforeAction extends BaseController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/demand/index")
    public String jumpStoryIndex() {
        return "project/demand/index.page";
    }

    @RequestMapping("/bug/index")
    public String jumpBugIndex() {
        return "project/bug/index.page";
    }

    @RequestMapping("/build/index")
    public String jumpBuildIndex(Model model, HttpServletRequest request, HttpServletResponse response) {
        Integer projectId = ProjectUtils.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }
        Project project = projectService.findProjectById(projectId);
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


    @RequestMapping("/survey/index")
    public String jumpSurveyIndex(Model model, HttpServletRequest request, HttpServletResponse response,
                                  Integer projectId) {
        if (null == projectId) {
            projectId = ProjectUtils.getCurrentProjectId(request, response);
            if (projectId == null) {
                return redirectProjectForm();
            }
        }
        Project project = projectService.findProjectById(projectId);
        model.addAttribute("project", project);
        return "/project/survey/index.page";
    }

//    @RequestMapping("/select")
//    public String selectProject(Integer projectId, String oldUrl, HttpServletResponse response, HttpServletRequest request) {
//        CookieUtils.setCookie(response, TaskAction.COOKIE_PROJECT_ID, projectId.toString(), -1);
//        return "redirect:" + oldUrl;
//    }

    @RequestMapping("/selModelTask")
    public String selModelTask(String moduleId) {
        return "redirect:" + adminPath + "/project/task/index?moduleId=" + moduleId;
    }

    @RequestMapping("/selModelDemand")
    public String selModelDemand(String moduleId) {
        return "redirect:" + adminPath + "/project/manage/demand/index?moduleId=" + moduleId;
    }

}
