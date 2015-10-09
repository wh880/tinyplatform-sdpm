package org.tinygroup.sdpm.action.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.util.CookieUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
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
}
