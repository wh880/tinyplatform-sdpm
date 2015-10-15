package org.tinygroup.sdpm.action.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/a/product/project")
public class ProductprojectAction extends BaseController{
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping("/save")
	public String save(Project project,Model model,HttpServletRequest request){
		
		project.setProjectId((Integer)(request.getSession().getAttribute("sessionProjectId")));
		projectService.addProject(project);
	
		return "redirect:" + "/product/page/project/product-project-list.page";
		
		}
	
	
	@RequestMapping("/list")
	public String list(Project project,
			@RequestParam(required = false,defaultValue = "1")int page,
			@RequestParam(required = false,defaultValue = "10")int pagesize,
			@RequestParam(required = false,defaultValue = "projectId")String order,
			@RequestParam(required = false,defaultValue = "asc")String ordertype,Model model,HttpServletRequest request){
		project.setProjectId((Integer)(request.getSession().getAttribute("sessionProjectId")));
		Pager<Project> pagerProject = projectService.findProjectPager(page, pagesize, project, order, ordertype);
		model.addAttribute("project",pagerProject);
		return "/product/data/allproduct-project.pagelet";
		
	}
	
}
