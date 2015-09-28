package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.tinysqldsl.Pager;

/**
 * Created by shenly13343 on 2015-09-21.
 */
@Controller
@RequestMapping("/project")
public class ProjectAction extends BaseController {
    @Autowired
    private ProjectService projectService;

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
}
