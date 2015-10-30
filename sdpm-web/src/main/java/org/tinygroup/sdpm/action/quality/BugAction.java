package org.tinygroup.sdpm.action.quality;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.action.product.util.StoryUtil;
import org.tinygroup.sdpm.action.project.TaskAction;
import org.tinygroup.sdpm.action.quality.util.QualityUtil;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.ComplexSearch.SqlUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.PlanService;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.product.service.StoryService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.service.inter.BuildService;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.dao.pojo.QualityCaseStep;
import org.tinygroup.sdpm.quality.service.inter.BugService;
import org.tinygroup.sdpm.quality.service.inter.CaseStepService;
import org.tinygroup.sdpm.system.dao.pojo.ProfileType;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.sdpm.util.CookieUtils;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.ModuleUtil;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


/**
 * Created by chenpeng15668 on 2015-9-22
 */

@Controller
@RequestMapping("/a/quality/bug")
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
	@Autowired
	private PlanService planService;
	@Autowired
	private ProfileService profileService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProjectProductService projectProductService;
	@Autowired
	private CaseStepService caseStepService;

	@RequestMapping("")
	public String form(QualityBug bug, Model model, HttpServletRequest request) {
		String queryString = request.getQueryString();
		List<OrgUser> users = userService.findUserList(null);
		if(bug!=null&&bug.getModuleId()!=null){
			request.getSession().setAttribute("bugModuleId",bug.getModuleId());
		}else{
			request.getSession().removeAttribute("bugModuleId");
		}
		if(queryString!=null&&!queryString.contains("status")){
			if(!queryString.contains("currentPageId"))queryString = queryString+"&currentPageId=5";
			return "redirect:/a/quality/bug?status=tbugstatus&"+queryString;
		}
		if(StringUtil.isBlank(queryString)){
			return "redirect:/a/quality/bug?status=tbugstatus&currentPageId=5";
		}
		model.addAttribute("userList",users);
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
	@RequestMapping("/bugInfo")
	public String bugInfo(Integer bugId, Model model){
		Pager<QualityBug> bugs = bugService.findBugListPager(0,1," bug_id <"+bugId+" ",null,"bugId",false);
		int nextId = 0;
		if(bugs.getRecords().size()>0){
			nextId = bugs.getRecords().get(0).getBugId();
		}
		QualityBug bug = bugService.findById(bugId);
		SystemProfile systemProfile = new SystemProfile();
		systemProfile.setFileObjectId(bugId);
		systemProfile.setFileObjectType(LogUtil.LogOperateObject.BUG.toString());
		List<SystemProfile> profilesList = profileService.find(systemProfile);
		model.addAttribute("qualityBug",bug);
		model.addAttribute("profilesList",profilesList);
		model.addAttribute("nextId",nextId);
		return "/testManagement/page/bugInfo.page";
	}

	@RequestMapping("/bugBasicInfo")
	public String bugBasicInfo(Integer bugId, Model model){
		QualityBug bug = bugService.findById(bugId);
		String[] userIds = bug.getBugAssignedTo().split(",");
		List<OrgUser> assignUsers = userService.findUserListByIds(userIds);
		model.addAttribute("assignUsers",assignUsers);
		model.addAttribute("qualityBug",bug);
		return "/testManagement/page/bugRightInfo.pagelet";
	}

	@RequestMapping("/findBug")
	public String findBugPager(@CookieValue Integer qualityProductId, Integer start, Integer limit, SearchInfos infos, String groupOperate, String order, String ordertype, String status, QualityBug bug, Model model, HttpServletRequest request){
		boolean asc = true;		
		if("desc".equals(ordertype)){
			asc = false;
		}
		String conditions = QualityUtil.getCondition(status,request);
		String result = SqlUtil.toSql(infos.getInfos(),groupOperate);
		conditions = StringUtil.isBlank(result)?
				conditions:(StringUtil.isBlank(conditions)?result:conditions+" and "+ SqlUtil.toSql(infos.getInfos(),groupOperate));
		bug.setModuleId((Integer) request.getSession().getAttribute("bugModuleId"));
		bug.setProductId(qualityProductId);
		bug.setDeleted(0);
		if(bug.getModuleId()!=null){
			conditions = ("".equals(conditions)?" module_id ":conditions+" and module_id ")+ModuleUtil.getCondition(bug.getModuleId(),moduleService);
			bug.setModuleId(null);
		}
		Pager<QualityBug> bugpager = bugService.findBugListPager(start, limit,conditions, bug, order, asc);
		model.addAttribute("bugpager",bugpager);
		return "/testManagement/data/BugData.pagelet";
	}
	
	@RequestMapping("/reportForm")
	public String reportForm(){
		return "/testManagement/page/reportform.page";
	}
	
	@RequiresPermissions("tmakesure")
	@RequestMapping("/makesure")
	public String makesure(Integer bugId,Model model){
		QualityBug bug = new QualityBug();
	    bug = bugService.findById(bugId);
		List<OrgUser> orgUsers = userService.findUserList(null);
	    model.addAttribute("bug",bug);
		model.addAttribute("userList",orgUsers);
		return "/testManagement/page/tabledemo/makesure.page";
	}
	
	@RequiresPermissions("tmakesure")
	@ResponseBody
	@RequestMapping("batch/sure")
	public Map makesure(String ids,Model model){
		String[] bugIds = ids.split(",");
		if(bugIds.length>0){
			for(String id : bugIds){
				QualityBug bug = bugService.findById(Integer.valueOf(id));
				if(bug.getBugConfirmed()!=null&&bug.getBugConfirmed()<1) {
					bug.setBugConfirmed(1);
					bugService.updateBug(bug);
					LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
							, LogUtil.LogAction.BUGCONFIRMED
							, String.valueOf(bug.getBugId())
							, UserUtils.getUserId()
							, String.valueOf(bug.getProductId())
							, String.valueOf(bug.getProjectId())
							, null
							, null
							, null);
				}
			}
		}

		Map<String, String> map = new HashMap<String, String>();
		map.put("status", "success");
		map.put("info", "成功");
		return map;
	}
	
	@RequiresPermissions("tsolution")
	@ResponseBody
	@RequestMapping("batch/resolve")
	public Map resolve(String ids,String resolutionType,Model model) {
		String[] bugIds = ids.split(",");
		if (bugIds.length > 0) {
			for (String id : bugIds) {
				QualityBug bug = bugService.findById(Integer.valueOf(id));
				if (bug.getBugStatus()!=null&&Integer.parseInt(bug.getBugStatus()) < 2) {
					bug.setBugConfirmed(1);
					bug.setBugStatus("2");
					bug.setBugResolution(resolutionType);
					bug.setBugResolvedDate(new Date());
					bug.setBugResolvedBy(UserUtils.getUserId());
					bugService.updateBug(bug);
					LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
							, LogUtil.LogAction.RESOLVED
							, String.valueOf(bug.getBugId())
							, UserUtils.getUserId()
							, String.valueOf(bug.getProductId())
							, String.valueOf(bug.getProjectId())
							, null
							, null
							, null);
				}
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", "success");
		map.put("info", "成功");
		return map;
	}
	
	@RequiresPermissions("tassign")
	@ResponseBody
	@RequestMapping("batch/assign")
	public Map assign(String ids,String userId,Model model) {
		String[] bugIds = ids.split(",");
		if (bugIds.length > 0) {
			for (String id : bugIds) {
				QualityBug bug = bugService.findById(Integer.valueOf(id));
					bug.setBugConfirmed(1);
					bug.setBugAssignedDate(new Date());
					bug.setBugAssignedTo(userId);
					bugService.updateBug(bug);
					LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
							, LogUtil.LogAction.ASSIGNED
							, String.valueOf(bug.getBugId())
							, UserUtils.getUserId()
							, String.valueOf(bug.getProductId())
							, String.valueOf(bug.getProjectId())
							, null
							, null
							, null);
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", "success");
		map.put("info", "成功");
		return map;
	}
	
	@RequestMapping("/sure")
	public String makesure(QualityBug bug, SystemAction systemAction, HttpServletRequest request){
		QualityBug qualityBug = bugService.findById(bug.getBugId());
		if(qualityBug.getBugAssignedTo()!=bug.getBugAssignedTo()){
			bug.setBugAssignedDate(new Date());
		}
		bug.setBugConfirmed(1);
		bugService.updateBug(bug);

	
		LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
				, LogUtil.LogAction.BUGCONFIRMED
				,String.valueOf(bug.getBugId())
				,UserUtils.getUserId()
				,String.valueOf(bug.getProductId())
				,String.valueOf(bug.getProjectId())
				,null
				,null
				,systemAction.getActionComment());
		return "redirect:"+"/a/quality/bug";
	}
	
	
	@ResponseBody
	@RequestMapping("addComment")
	public Map recordComment(String comment, int bugId){
		QualityBug bug = bugService.findById(bugId);
		LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
				, LogUtil.LogAction.COMMENTED
				,String.valueOf(bugId)
				,UserUtils.getUserId()
				,String.valueOf(bug.getProductId())
				,String.valueOf(bug.getProjectId())
				,null
				,null
				,comment);
		Map<String,String> result = new HashMap<String, String>();
		result.put("status","success");
		return result;
	}

	@RequiresPermissions("tassign")
	@RequestMapping("/assign")
	public String assign(Integer bugId,Model model){		
		QualityBug bug = bugService.findById(bugId);
		List<OrgUser> users = userService.findUserList(null);
		model.addAttribute("bug", bug);
		model.addAttribute("userList",users);
		return "/testManagement/page/tabledemo/assign.page";
	}

	@RequestMapping("/assignTo")
	public String assign(QualityBug bug, SystemAction systemAction, HttpServletRequest request){
		QualityBug qualityBug = bugService.findById(bug.getBugId());
		bug.setBugAssignedDate(new Date());
		bugService.updateBug(bug);


		LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
				, LogUtil.LogAction.ASSIGNED
				,String.valueOf(bug.getBugId())
				,UserUtils.getUserId()
				,String.valueOf(bug.getProductId())
				,String.valueOf(bug.getProjectId())
				,null
				,null
				,systemAction.getActionComment());
		return "redirect:"+"/a/quality/bug";
	}

	@RequiresPermissions("tbugdelete")
	@RequestMapping("/delete")
	public String delete(Integer bugId) {
		bugService.deleteBug(bugId);

		return "redirect:"+"/a/quality/bug";
    }
	
	@RequiresPermissions("tsolution")
	@RequestMapping("/toSolve")
	public String solve(Integer bugId,Model model){
		QualityBug bug = new QualityBug();
		bug = bugService.findById(bugId);
		bug.setBugResolvedDate(new Date());
		List<OrgUser> orgUsers = userService.findUserList(null);
		ProjectBuild build = new ProjectBuild();
		build.setBuildProduct(bug.getProductId());
		List<ProjectBuild> builds = buildService.findListBuild(build);
		model.addAttribute("buildList",builds);
		model.addAttribute("userList",orgUsers);
		model.addAttribute("bug", bug);
		return "/testManagement/page/tabledemo/solution.page";
	}
	@RequestMapping("/solve")
	public String solve(QualityBug bug, SystemAction systemAction, HttpServletRequest request){
		QualityBug qualityBug = bugService.findById(bug.getBugId());
		if(qualityBug.getBugAssignedTo()!=bug.getBugAssignedTo()){
			bug.setBugAssignedDate(new Date());
		}
		bug.setBugResolvedBy(UserUtils.getUserId() != null?UserUtils.getUserId():"0");
		bug.setBugStatus("2");
		bugService.updateBug(bug);


		LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
				, LogUtil.LogAction.RESOLVED
				,String.valueOf(bug.getBugId())
				,UserUtils.getUserId()
				,String.valueOf(bug.getProductId())
				,String.valueOf(bug.getProjectId())
				,null
				,null
				,systemAction.getActionComment());
		return "redirect:"+"/a/quality/bug";
	}
	
	@RequiresPermissions("tshutdown")
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

		bug.setBugClosedBy(UserUtils.getUserId() != null?UserUtils.getUserId():"0");
		bug.setBugClosedDate(new Date());
		bug.setBugStatus("3");
		bugService.updateBug(bug);


		LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
				, LogUtil.LogAction.CLOSED
				,String.valueOf(bug.getBugId())
				,UserUtils.getUserId()
				,String.valueOf(bug.getProductId())
				,String.valueOf(bug.getProjectId())
				,null
				,null
				,systemAction.getActionComment());
		return "redirect:"+"/a/quality/bug";
	}
	
	@RequiresPermissions("tedition")
	@RequestMapping("/toEdit")
	public String edit(Integer bugId,Model model){
		QualityBug bug = new QualityBug();
		bug = bugService.findById(bugId);
		model.addAttribute("bug", bug);
		return "/testManagement/page/tabledemo/edition.page";
	}

	@RequestMapping("/edit")
	public String edit(QualityBug bug, SystemAction systemAction, HttpServletRequest request){

		QualityBug qualityBug = bugService.findById(bug.getBugId());

		bug.setBugLastEditedBy(UserUtils.getUserId() != null?UserUtils.getUserId():"0");
		bug.setBugLastEditedDate(new Date());
		bugService.updateBug(bug);


		LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
				, LogUtil.LogAction.EDITED
				,String.valueOf(bug.getBugId())
				,UserUtils.getUserId()
				,String.valueOf(bug.getProductId())
				,String.valueOf(bug.getProjectId())
				,qualityBug
				,bug
				,systemAction.getActionComment());
		return "redirect:"+"/a/quality/bug";
	}
	
	@RequestMapping("/editionPaging")
	public String editionPaging(Integer bugId,Model model){
		Product product = new Product();
		product.setProductId(bugId);
		List<Product> products = productService.findProductList(product);
		QualityBug bug = bugService.findById(bugId);
		Project p = new Project();
		p.setProjectDeleted("0");
		List<Project> projects = projectService.findProjectList(p,null,null);
		List<OrgUser> orgUsers = userService.findUserList(null);
		model.addAttribute("bugProductList",products);
		model.addAttribute("projectList",projects);
		model.addAttribute("userList",orgUsers);
		model.addAttribute("bug", bug);
		return "/testManagement/page/tabledemo/editionpaging.pagelet";
	}
		
	@RequiresPermissions("tbugpro")
	@RequestMapping("/add")
	public String add(Model model){
		List<Project> projects = projectService.findProjectList(null,null,null);
		List<OrgUser> orgUsers = userService.findUserList(null);
		model.addAttribute("projectList",projects);
		model.addAttribute("userList",orgUsers);
		return "/testManagement/page/proposeBug.page";
	}
	
	@RequiresPermissions("tbugpro")
	@RequestMapping("/toCopy")
	public String copy(Integer bugId,Model model){
		QualityBug bug = bugService.findById(bugId);
		List<Project> projects = projectService.findProjectList(null,null,null);
		List<OrgUser> orgUsers = userService.findUserList(null);
		model.addAttribute("userList",orgUsers);
		model.addAttribute("projectList",projects);
		model.addAttribute("bug",bug);
		return "/testManagement/page/copyBug.page";
	}

	@ResponseBody
	@RequestMapping("/ajax/project")
	public List<Project> getProject(ProjectProduct projectProduct){
		if(projectProduct.getProductId()<1){
			return new ArrayList<Project>();
		}
		List<ProjectProduct> projectProducts = projectProductService.findProjects(projectProduct.getProductId());
		List<Integer> ids = new ArrayList<Integer>();
		for(ProjectProduct projectProduct1 : projectProducts){
			ids.add(projectProduct1.getProjectId());
		}
		List<Project> projects = projectService.findByProjectList(ids);
		return projects==null?new ArrayList<Project>():projects;
	}

	@ResponseBody
	@RequestMapping("/ajax/plan")
	public List<ProductPlan> getPlan(ProductPlan productPlan){
		if(productPlan.getProductId()<1){
			return new ArrayList<ProductPlan>();
		}
		List<ProductPlan> moduleList = planService.findPlanList(productPlan);
		return moduleList;
	}

	@ResponseBody
	@RequestMapping("/ajax/module")
	public List<SystemModule> getModule(SystemModule systemModule){
		if(systemModule.getModuleRoot()<1){
			return new ArrayList<SystemModule>();
		}
		systemModule.setModuleType("story");
		List<SystemModule> moduleList = moduleService.findModules(systemModule);
		for(SystemModule module : moduleList){
			module.setModuleName(ModuleUtil.getPath(module.getModuleId(),"/",moduleService,null,false));
		}
		return moduleList;
	}

	@ResponseBody
	@RequestMapping("/ajax/story")
	public List<ProductStory> getStory(ProductStory productStory){
		if(productStory.getProductId()<1){
			return new ArrayList<ProductStory>();
		}
		if(productStory.getModuleId()==0){
			productStory.setModuleId(null);
		}
		return storyService.findStoryList(productStory,null,null);
	}

	@ResponseBody
	@RequestMapping("/ajax/task")
	public List<ProjectTask> getStory(ProjectTask projectTask){
		if(projectTask.getTaskProject()<1){
			return new ArrayList<ProjectTask>();
		}
		return taskService.findListTask(projectTask);
	}

	@ResponseBody
	@RequestMapping("/ajax/build")
	public List<ProjectBuild> getBuild(ProjectBuild projectBuild){
		if(projectBuild.getBuildProduct()<1||projectBuild.getBuildProduct()==null){
			return new ArrayList<ProjectBuild>();
		}
		return buildService.findListBuild(projectBuild);
	}
	
	@RequestMapping(value = "/copy",method = RequestMethod.POST)
	public String copySave(QualityBug bug, SystemAction systemAction, HttpServletRequest request){
		bug.setBugConfirmed(0);
		bug.setBugStatus("1");
		bug.setDeleted(0);
		bug.setBugOpenedDate(new Date());
		if(bug.getStoryId()!=null){
			bug.setStoryVersion(storyService.findStory(bug.getStoryId()).getStoryVersion());
		}
		bug.setBugOpenedBy(UserUtils.getUserId() != null?UserUtils.getUserId():"0");
		bug = bugService.addBug(bug);

		LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
				, LogUtil.LogAction.OPENED
				,String.valueOf(bug.getBugId())
				,UserUtils.getUserId()
				,String.valueOf(bug.getProductId())
				,String.valueOf(bug.getProjectId())
				,null
				,null
				,systemAction.getActionComment());
		return "redirect:"+"/a/quality/bug";
	}
	
	@RequestMapping(value = "/save")
	public String save(QualityBug bug,SystemAction systemAction,@RequestParam(value = "file", required = false)MultipartFile[] file,
			String[] title, HttpServletRequest request) throws IOException {

		if(!StringUtil.isBlank(bug.getBugAssignedTo())){
			bug.setBugAssignedDate(new Date());
		}
		bug.setBugConfirmed(0);
		bug.setDeleted(0);
		bug.setBugOpenedDate(new Date());
		bug.setBugOpenedBy(UserUtils.getUserId() != null?UserUtils.getUserId():"0");
		QualityBug qbug=bugService.addBug(bug);

		uploads(file,qbug.getBugId(), ProfileType.BUG,title);


		LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
				, LogUtil.LogAction.OPENED
				,String.valueOf(qbug.getBugId())
				,UserUtils.getUserId()
				,String.valueOf(bug.getProductId())
				,String.valueOf(bug.getProjectId())
				,null
				,null
				,systemAction.getActionComment());
		return "redirect:"+"/a/quality/bug";
	}

	@RequestMapping("/projectFindList")
	public String projectFindList(Model model, Integer start, Integer limit,String order, String ordertype, HttpServletRequest request) {
		Integer projectId = Integer.parseInt(CookieUtils.getCookie(request, TaskAction.COOKIE_PROJECT_ID));
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
		for(QualityBug bug:list){
			LogUtil.logWithComment(LogUtil.LogOperateObject.BUG
					, LogUtil.LogAction.DELETED
					,String.valueOf(bug.getBugId())
					,UserUtils.getUserId()
					,String.valueOf(bug.getProductId())
					,String.valueOf(bug.getProjectId())
					,null
					,null
					,null);
		}
		map.put("status", "success");
	    map.put("info", "删除成功");
	    return map;
	}
	
	
	//批量删除（软） 产品下面的计划、发布关联BUG表上使用的
		@ResponseBody
		@RequestMapping(value="/batchDelBugStory")
		public Map bctchBugDelStory(String ids)
		{		
			Map<String,String> map = new HashMap<String,String>();
			if(ids == null){
				map.put("status", "fail");
			    map.put("info", "删除失败");
				return map;
			}
			for(String s : ids.split(",")){			
				QualityBug bug= new QualityBug();
				bug.setBugId(Integer.valueOf(s));
				bug.setPlanId(0);
				bugService.updateBug(bug);
			}	
			
			map.put("status", "success");
		    map.put("info", "删除成功");
		    return map;
		}
	
	//产品计划、发布关联BUG下面的搜索按钮使用
	@RequestMapping("/linkBug")
    public String bugAction(ProductPlan plan,QualityBug bug, String groupOperate, Model model, HttpServletRequest request, HttpServletResponse response){
    	String queryString = request.getQueryString();
   /*    if(queryString!=null&&!queryString.contains("choose")){
            return "redirect:/product/story?choose=1&"+queryString;
        }*/
        return "/product/plan/forword/noRelateBug?planId"+plan.getPlanId();
    }
	
	   @RequestMapping("/searchBug")
	    public String   SearchAction(@CookieValue String cookieProductId, int start, int pagesize, ProductStory story, String choose, String groupOperate, SearchInfos searchInfos, String order, String ordertype, Model model, HttpServletRequest request){
	        
	    	story.setProductId(Integer.parseInt(cookieProductId));

	    	/*if (story.getModuleId()==-1) {
	    		story.setModuleId(null);
			}*/
	    	Pager<ProductStory> p = storyService.findStoryPager(start,pagesize,story, StoryUtil.getStatusCondition(choose),searchInfos,groupOperate,order,"asc".equals(ordertype)?true:false);
	        model.addAttribute("storyList",p);
	        return "product/data/tabledata.pagelet";
	    }
	   
	   
	    @ResponseBody
	    @RequestMapping("/ajaxUpdate")
	    public Map deleteRel(QualityBug bug) {
	    	
	    	
	    	bugService.updateBug(bug);
	        Map<String, String> map = new HashMap<String, String>();
	        map.put("status", "success");
	        map.put("info", "成功");

	        return map;
	    }
	    
	    @ResponseBody
	    @RequestMapping("/updateBatch")
	    public boolean updateBatch(@RequestBody QualityBug[] bugs) {
	       /* List<ProductStory> productStories = new ArrayList<ProductStory>();
	        if (stories != null && stories.length > 0) {
	            productStories = Arrays.asList(stories);
	        }
	        return storyService.updateBatchStory(productStories);*/

	    	if(bugs.length==0||bugs==null){
	    		return false;
	    	}
	    	for (QualityBug bug : bugs) {
				bugService.updateBug(bug);
			}
	    	return true;
	    }

	@RequestMapping("toBug")
	public String toBug(Integer[] ids, Model model){
		StringBuffer bugStep = new StringBuffer("");
		for(Integer id : ids){
			QualityCaseStep step = caseStepService.findById(id);
			bugStep.append("[步骤]<br>");
			bugStep.append(step.getCaseStepDesc()+"<br>");
			bugStep.append("[期望]<br>");
			bugStep.append(step.getCaseStepExpect()+"<br>");
		}
		List<Project> projects = projectService.findProjectList(null,null,null);
		List<OrgUser> orgUsers = userService.findUserList(null);
		model.addAttribute("bugStep",bugStep);
		model.addAttribute("projectList",projects);
		model.addAttribute("userList",orgUsers);
		return "/testManagement/page/proposeBug.page";
	}
	
}
