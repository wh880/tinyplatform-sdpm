package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.inter.ProjectService;

/**
 * Created by shenly13343 on 2015-09-21.
 */
@Controller
@RequestMapping("/project")
public class projectAction extends BaseController {
    @Autowired
    private ProjectService projectService;

    @RequestMapping("/add")
    public String save(Project project, Model model) {
        if (project.getProjectId()== null) {
            projectService.add(project);
        }
        return "project/addProject.page";
    }
    @RequestMapping("/form")
    public String form(Integer id, Model model) {
        if (id != null) {
            Project project = projectService.findById(id);
            model.addAttribute("project", project);
        }
        return "project/allProject.page";
    }
}
