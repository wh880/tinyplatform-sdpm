package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/a/product/project")
public class ProductProjectAction extends BaseController{

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/save")
    public String save(@CookieValue(value = "currentProjectId",defaultValue = "0") String currentProjectId, Project project, Model model, HttpServletRequest request){

        project.setProjectId(Integer.parseInt(currentProjectId));
        projectService.addProject(project);

        return "redirect:" + "/product/page/project/product-project-list.page";

    }


    @RequestMapping("/list")
    public String list(Project project,
                       @CookieValue("currentProjectId") String currentProjectId,
                       @RequestParam(required = false,defaultValue = "1")int page,
                       @RequestParam(required = false,defaultValue = "10")int pagesize,
                       @RequestParam(required = false,defaultValue = "projectId")String order,
                       @RequestParam(required = false,defaultValue = "asc")String ordertype,Model model){
        project.setProjectId(Integer.parseInt(currentProjectId));
        Pager<Project> pagerProject = projectService.findProjectPager(page, pagesize, project, order, ordertype);
        model.addAttribute("project",pagerProject);
        return "/product/data/allproduct-project.pagelet";

    }

}
