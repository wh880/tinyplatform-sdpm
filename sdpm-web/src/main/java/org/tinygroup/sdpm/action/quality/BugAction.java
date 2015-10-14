package org.tinygroup.sdpm.action.quality;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.action.quality.util.QualityUtil;
import org.tinygroup.sdpm.action.system.ModuleUtil;
import org.tinygroup.sdpm.common.util.CookieUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.StoryService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.service.inter.BuildService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.service.inter.BugService;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by chenpeng15668 on 2015-9-22
 */

@Controller
@RequestMapping("/quality/bug")
public class BugAction extends BaseController {

	@Autowired
	private BugService bugService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private StoryService storyService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private UserService userService;
	@Autowired
	private BuildService buildService;

	@RequestMapping("")
	public String form(String get,QualityBug bug, HttpServletRequest request){
		String queryString = request.getQueryString();
		if(bug!=null&&bug.getModuleId()!=null){
			request.getSession().setAttribute("bugModuleId",bug.getModuleId());
		}else{
			request.getSession().removeAttribute("bugModuleId");
		}
		if(queryString!=null&&!queryString.contains("status")){
			return "redirect:/quality/bug?status=tbugstatus&"+queryString;
		}
		return "/testManagement/page/Bug.page";
	}
	
	@RequestMapping("/findList")
	public String findList(Integer id,Model model){
		QualityBug bug = new QualityBug();
		bug.setProductId(id);
		List<QualityBug> buglist = bugService.findBugList(bug);
		model.addAttribute("buglist",buglist);
		return "/testManagement/data/BugData.pagelet";
	}
	
	@RequestMapping("/findBug")
	public String findBugPager(Integer page,Integer limit,String order,String ordertype,String status,QualityBug bug,Model model,HttpServletRequest request){
		boolean asc = true;		
		if("desc".equals(ordertype)){
			asc = false;
		}
		String conditions = QualityUtil.getCondition(status,request);
		bug.setModuleId((Integer) request.getSession().getAttribute("bugModuleId"));
		bug.setProductId((Integer) request.getSession().getAttribute("qualityProductId"));
		Pager<QualityBug> bugpager = bugService.findBugListPager(limit*(page-1), limit,conditions, bug, order, asc);
		model.addAttribute("bugpager",bugpager);
		return "/testManagement/data/BugData.pagelet";
	}
	
	@RequestMapping("/reportForm")
	public String reportForm(){
		return "/testManagement/page/reportform.page";
	}
	
	@RequestMapping("/makesure")
	public String makesure(Integer bugId,Model model){
		QualityBug bug = new QualityBug();
	    bug = bugService.findById(bugId);
		List<OrgUser> orgUsers = userService.findUserList(null);
	    model.addAttribute("bug",bug);
		model.addAttribute("userList",orgUsers);
		return "/testManagement/page/tabledemo/makesure.page";
	}
	@RequestMapping("batch/sure")
	public Map makesure(String[] bugId,Model model){
		if(bugId.length>0){
			for(String id : bugId){
				QualityBug bug = bugService.findById(Integer.valueOf(id));
				bug.setBugConfirmed(1);
				bugService.updateBug(bug);
			}
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("status", "success");
		map.put("info", "成功");
		return map;
	}
	@RequestMapping("/sure")
	public String makesure(QualityBug bug, SystemAction systemAction, HttpServletRequest request){
		bug.setBugConfirmed(1);
		bugService.updateBug(bug);

		OrgUser user = (OrgUser) request.getSession().getAttribute("user");
		systemAction.setActionObjectId(bug.getBugId());
		systemAction.setActionProduct(String.valueOf(bug.getProductId()));
		systemAction.setActionProject(bug.getProjectId());
		systemAction.setActionObjectType("bug");
		systemAction.setActionAction("makeSure");
		systemAction.setActionActor(user != null?user.getOrgUserId():"0");
		logService.log(systemAction);
		return "redirect:"+"/quality/bug";
	}

	@RequestMapping("/assign")
	public String assign(Integer bugId,Model model){		
		QualityBug bug = bugService.findById(bugId);
		model.addAttribute("bug", bug);
		return "/testManagement/page/tabledemo/assign.page";
	}

	@RequestMapping("/assignTo")
	public String assign(QualityBug bug, SystemAction systemAction, HttpServletRequest request){
		OrgUser user = (OrgUser) request.getSession().getAttribute("user");
		bug.setBugAssignedDate(new Date());
		bug.setBugAssignedTo(user != null?user.getOrgUserId():"0");
		bugService.updateBug(bug);

		systemAction.setActionObjectId(bug.getBugId());
		systemAction.setActionProduct(String.valueOf(bug.getProductId()));
		systemAction.setActionProject(bug.getProjectId());
		systemAction.setActionObjectType("bug");
		systemAction.setActionAction("assignTo");
		systemAction.setActionActor(user != null?user.getOrgUserId():"0");
		logService.log(systemAction);
		return "redirect:"+"/quality/bug";
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Map delete(Integer bugId) {
		bugService.deleteBug(bugId);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }
	
	@RequestMapping("/toSolve")
	public String solve(Integer bugId,Model model){
//		List<ProjectBuild> projectBuilds = buildService.findPager()
		QualityBug bug = new QualityBug();
		bug = bugService.findById(bugId);
		bug.setBugResolvedDate(new Date());
		List<OrgUser> orgUsers = userService.findUserList(null);
		model.addAttribute("userList",orgUsers);
		ProjectBuild p = new ProjectBuild();
		p.setBuildProduct(bug.getProductId());
		List<ProjectBuild> projectBuilds = buildService.findListBuild(p);
		model.addAttribute("buildList",projectBuilds);
		model.addAttribute("bug", bug);
		return "/testManagement/page/tabledemo/solution.page";
	}
	@RequestMapping("/solve")
	public String solve(QualityBug bug, SystemAction systemAction, HttpServletRequest request){
		OrgUser user = (OrgUser) request.getSession().getAttribute("user");
		bug.setBugResolvedBy(user != null?user.getOrgUserId():"0");
		bug.setBugStatus("2");
		bugService.updateBug(bug);

		systemAction.setActionObjectId(bug.getBugId());
		systemAction.setActionProduct(String.valueOf(bug.getProductId()));
		systemAction.setActionProject(bug.getProjectId());
		systemAction.setActionObjectType("bug");
		systemAction.setActionAction("resolve");
		systemAction.setActionActor(user != null?user.getOrgUserId():"0");
		logService.log(systemAction);
		return "redirect:"+"/quality/bug";
	}
	
	@RequestMapping("/toClose")
	public String close(Integer bugId,Model model){
		QualityBug bug = new QualityBug();
		bug = bugService.findById(bugId);
		bug.setBugClosedDate(new Date());
		model.addAttribute("bug", bug);
		return "/testManagement/page/tabledemo/shutdown.page";
	}

	@RequestMapping("/close")
	public String close(QualityBug bug, SystemAction systemAction, HttpServletRequest request){
		OrgUser user = (OrgUser) request.getSession().getAttribute("user");
		bug.setBugClosedBy(user != null?user.getOrgUserId():"0");
		bug.setBugClosedDate(new Date());
		bug.setBugStatus("3");
		bugService.updateBug(bug);

		systemAction.setActionObjectId(bug.getBugId());
		systemAction.setActionProduct(String.valueOf(bug.getProductId()));
		systemAction.setActionProject(bug.getProjectId());
		systemAction.setActionObjectType("bug");
		systemAction.setActionAction("close");
		systemAction.setActionActor(user != null?user.getOrgUserId():"0");
		logService.log(systemAction);
		return "redirect:"+"/quality/bug";
	}
	
	@RequestMapping("/toEdit")
	public String edit(Integer bugId,Model model){
		QualityBug bug = bugService.findById(bugId);
		model.addAttribute("bug", bug);
		return "/testManagement/page/tabledemo/edition.page";
	}

	@RequestMapping("/edit")
	public String edit(QualityBug bug, SystemAction systemAction, HttpServletRequest request){
		OrgUser user = (OrgUser) request.getSession().getAttribute("user");
		QualityBug qualityBug = bugService.findById(bug.getBugId());
		bug.setBugLastEditedBy(user != null?user.getOrgUserId():"0");
		bug.setBugLastEditedDate(new Date());
		bugService.updateBug(bug);

		systemAction.setActionObjectId(bug.getBugId());
		systemAction.setActionProduct(String.valueOf(bug.getProductId()));
		systemAction.setActionProject(bug.getProjectId());
		systemAction.setActionObjectType("bug");
		systemAction.setActionAction("edit");
		systemAction.setActionActor(user != null?user.getOrgUserId():"0");
		logService.log(qualityBug,bug,systemAction);
		return "redirect:"+"/quality/bug";
	}
	
	@RequestMapping("/editionPaging")
	public String editionPaging(Integer bugId,Model model){
		QualityBug bug = bugService.findById(bugId);
		List<Project> projects = projectService.findProjectList(null,null,null);
		List<OrgUser> orgUsers = userService.findUserList(null);
		ProjectBuild p = new ProjectBuild();
		p.setBuildProduct(bug.getProductId());
		List<ProjectBuild> projectBuilds = buildService.findListBuild(p);
		model.addAttribute("projectList",projects);
		model.addAttribute("userList",orgUsers);
		model.addAttribute("buildList",projectBuilds);
		model.addAttribute("bug", bug);
		return "/testManagement/page/tabledemo/editionpaging.pagelet";
	}
			
	@RequestMapping("/add")
	public String add(Model model){
		List<Project> projects = projectService.findProjectList(null,null,null);
		List<OrgUser> orgUsers = userService.findUserList(null);
		model.addAttribute("projectList",projects);
		model.addAttribute("userList",orgUsers);
		return "/testManagement/page/proposeBug.page";
	}
	
	@RequestMapping("/toCopy")
	public String copy(Integer bugId,Model model){
		QualityBug bug = bugService.findById(bugId);
		List<Project> projects = projectService.findProjectList(null,null,null);
		List<OrgUser> orgUsers = userService.findUserList(null);
		ProjectBuild p = new ProjectBuild();
		p.setBuildProduct(bug.getProductId());
		List<ProjectBuild> projectBuilds = buildService.findListBuild(p);
		model.addAttribute("projectList",projects);
		model.addAttribute("userList",orgUsers);
		model.addAttribute("buildList",projectBuilds);
		model.addAttribute("bug", bug);
		return "/testManagement/page/copyBug.page";
	}

	@ResponseBody
	@RequestMapping("/ajax/module")
	public List<SystemModule> getModule(SystemModule systemModule){
		systemModule.setModuleType("story");
		return moduleService.findModules(systemModule);
	}

	@ResponseBody
	@RequestMapping("/ajax/story")
	public List<ProductStory> getStory(ProductStory productStory){
		if(productStory.getModuleId()==0){
			productStory.setModuleId(null);
		}
		return storyService.findStoryList(productStory,null,null);
	}

	@ResponseBody
	@RequestMapping("/ajax/task")
	public List<ProjectTask> getStory(ProjectTask projectTask){
		return taskService.findListTask(projectTask);
	}
	
	@RequestMapping(value = "/copy",method = RequestMethod.POST)
	public String copySave(QualityBug bug, SystemAction systemAction, HttpServletRequest request){
		OrgUser user = (OrgUser) request.getSession().getAttribute("user");
		bug.setBugOpenedDate(new Date());
		bug.setBugOpenedBy(user != null?user.getOrgUserId():"0");
		bugService.addBug(bug);

		systemAction.setActionObjectId(bug.getBugId());
		systemAction.setActionProduct(String.valueOf(bug.getProductId()));
		systemAction.setActionProject(bug.getProjectId());
		systemAction.setActionObjectType("bug");
		systemAction.setActionAction("copyBug");
		systemAction.setActionActor(user != null?user.getOrgUserId():"0");
		logService.log(systemAction);
		return "redirect:"+"/quality/bug";
	}
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(QualityBug bug,SystemAction systemAction, HttpServletRequest request){
		OrgUser user = (OrgUser) request.getSession().getAttribute("user");
		bug.setBugOpenedDate(new Date());
		bug.setBugOpenedBy(user != null?user.getOrgUserId():"0");
		bugService.addBug(bug);

		systemAction.setActionObjectId(bug.getBugId());
		systemAction.setActionProduct(String.valueOf(bug.getProductId()));
		systemAction.setActionProject(bug.getProjectId());
		systemAction.setActionObjectType("bug");
		systemAction.setActionAction("openBug");
		systemAction.setActionActor(user != null?user.getOrgUserId():"0");
		logService.log(systemAction);
		return "redirect:"+"/quality/bug";
	}

	@RequestMapping("/projectFindList")
	public String projectFindList(Model model, Integer start, Integer limit, String order, String ordertype, HttpServletRequest request) {
		Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_projectId"));
		boolean asc = "asc".equals(ordertype) ? true : false;
		QualityBug bug = new QualityBug();
		bug.setProjectId(projectId);
		Pager<QualityBug> bugPage = bugService.findBugListPager(start, limit,null, bug, order, asc);
		model.addAttribute("bugPage", bugPage);
		bugPage.getRecords();
		bugPage.getTotalCount();
		return "project/bug/bugViewTableData.pagelet";
	}
	
	//批量删除（软） 产品下面的计划、发布关联BUG表上使用的
	@ResponseBody
	@RequestMapping(value="/batchDelete")
	public Map bctchDelBug(String ids)
	{		
		Map<String,String> map = new HashMap<String,String>();
		if(ids == null){
			map.put("status", "fail");
		    map.put("info", "删除失败");
			return map;
		}
		 List<QualityBug> list = new ArrayList<QualityBug>();
		for(String s : ids.split(",")){			
			QualityBug bug= new QualityBug();
			bug.setBugId(Integer.valueOf(s));
			bug.setDeleted(1);
			list.add(bug);
		}	
		bugService.batchDeleteBug(list);
		map.put("status", "success");
	    map.put("info", "删除成功");
	    return map;
	}

}
