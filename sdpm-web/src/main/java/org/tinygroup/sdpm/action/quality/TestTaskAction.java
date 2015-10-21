package org.tinygroup.sdpm.action.quality;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.action.quality.util.QualityUtil;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.ComplexSearch.SqlUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.inter.BuildService;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.project.service.inter.TeamService;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestTask;
import org.tinygroup.sdpm.quality.service.inter.TestCaseService;
import org.tinygroup.sdpm.quality.service.inter.TestRunService;
import org.tinygroup.sdpm.quality.service.inter.TestTaskService;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenpeng15668 on 2015-9-24
 */

@Controller
@RequestMapping("/a/quality/version")
public class TestTaskAction extends BaseController {
	
	@Autowired
	private TestTaskService testTaskService;
	@Autowired
	private ProductService productService;
	@Autowired
	private UserService userService;
	@Autowired
	private BuildService buildService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private TestRunService testRunService;
	@Autowired
	private TestCaseService testCaseService;
	@Autowired
	private ProjectProductService projectProductService;
	@Autowired
	private TeamService teamService;
	
	@RequestMapping("")
	public String form(HttpServletRequest request,String get,Model model){
		List<Product> list = (List<Product>) request.getSession().getAttribute("qualityProductList");
		String queryString = request.getQueryString();
		if(list == null|| list.size()==0){
			list = productService.findProductList(new Product(),"productId","desc");
			request.getSession().setAttribute("qualityProductList",list);
		}
		if(null==request.getSession().getAttribute("qualityProductId")||""==request.getSession().getAttribute("qualityProductId")){
			request.getSession().setAttribute("qualityProductId",list.size()>0?list.get(0).getProductId():0);
		}else{
			request.getSession().removeAttribute("qualityProductId");
			request.getSession().setAttribute("qualityProductId",list.size()>0?list.get(0).getProductId():0);
		}

//		if(queryString!=null&&!queryString.contains("status")){
//			if(!queryString.contains("currentPageId"))queryString = queryString+"&currentPageId=5";
//			return "redirect:/a/quality/bug?status=tbugstatus&"+queryString;
//		}
//		if(StringUtil.isBlank(queryString)){
//			return "redirect:/a/quality/bug?status=tbugstatus&currentPageId=5";
//		}
		return "/testManagement/page/version.page";
	}
	
	@RequestMapping("/findPager")
	public String findPager(Integer start, Integer limit, SearchInfos infos, String groupOperate, String order, String ordertype, String status, QualityTestTask testtask, Model model, HttpServletRequest request){
		boolean asc = true;
		if("desc".equals(ordertype)){
			asc = false;
		}
		String conditions = getStatusCondition(status);
		conditions = StringUtil.isBlank(SqlUtil.toSql(infos.getInfos(),groupOperate))?
				conditions:(StringUtil.isBlank(conditions)?SqlUtil.toSql(infos.getInfos(),groupOperate):conditions+" and "+ SqlUtil.toSql(infos.getInfos(),groupOperate));
		testtask.setProductId((Integer) request.getSession().getAttribute("qualityProductId"));
		Pager<QualityTestTask> verpager = testTaskService.findTestTaskPager(start, limit, testtask,conditions, order, asc);
		model.addAttribute("verPager",verpager);
		return "/testManagement/data/versionData.pagelet";
	}
	@ResponseBody
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public Map save(QualityTestTask testtask,Model model){
		if(testtask.getTestversionId() == null){
			testtask = testTaskService.addTestTask(testtask);
			LogUtil.logWithComment(LogUtil.LogOperateObject.TESTTASK,
					LogUtil.LogAction.OPENED,
					String.valueOf(testtask.getTestversionId()),
					UserUtils.getUserId(),
					String.valueOf(testtask.getProductId()),
					String.valueOf(testtask.getProjectId()),
					null,
					null,
					null
					);
		}else{
			testTaskService.updateTestTask(testtask);
		}
		model.addAttribute("testtask",testtask);
		Map<String,String> result = new HashMap<String, String>();
		result.put("status","y");
		return result;
	}
	
	@RequestMapping("/add")
	public String add(Model model,HttpServletRequest request){
		List<Project> projects = projectService.findProjectList(null,null,null);
		ProjectBuild build = new ProjectBuild();
		build.setBuildProduct((Integer) request.getSession().getAttribute("qualityProductId"));
		List<ProjectBuild> builds = buildService.findListBuild(build);
		List<OrgUser> users = userService.findUserList(null);
		model.addAttribute("projectList",projects);
		model.addAttribute("buildList",builds);
		model.addAttribute("userList",users);
		return "/testManagement/page/proposeversion.page";
	}
	
	@RequestMapping("/versionInfo")
	public String versionInfo(int testversionId, Model model){
		QualityTestTask testTask = testTaskService.findById(testversionId);
		model.addAttribute("testTask",testTask);
		return "/testManagement/page/versionSituation.page";
	}

	@RequestMapping("/versionRightInfo")
	public String versionRightInfo(int testversionId, Model model){
		QualityTestTask testTask = testTaskService.findById(testversionId);
		model.addAttribute("testTask",testTask);
		return "/testManagement/page/tabledemo/versionRightInfo.pagelet";
	}
	
	@RequestMapping("/linkCase")
	public String linkCase(int testversionId, Model model){
//		QualityTestRun run = new QualityTestRun();
//		run.setTaskId(testversionId);
//		List<QualityTestRun> runs = testRunService.findTestRunList(run);
//		String notInCondition = mergeCondition(runs);
//		model.addAttribute("count",runs.size());
//		Pager<QualityTestCase> casePager = testCaseService.;
		return "/testManagement/page/versionLink.page";
	}

	@RequestMapping("/link")
	public String link(Integer testversionId,Integer start, Integer limit,SearchInfos infos,String groupOperate, String order, String ordertype, Model model){
		QualityTestRun run = new QualityTestRun();
		run.setTaskId(testversionId);
		List<QualityTestRun> runs = testRunService.findTestRunList(run);
		String notInCondition = mergeCondition(runs);
//		String result = SqlUtil.toSql(infos.getInfos(),groupOperate);
//		String conditions = (StringUtil.isBlank(notInCondition)?"":(StringUtil.isBlank(result)?"":" and "))
//				+(StringUtil.isBlank(result)?"":result);
		Pager<QualityTestCase> casePager = testCaseService.findTestCasePager(start,limit,null,notInCondition,null,null,order,"asc".equals(ordertype)?true:false);
		model.addAttribute("casePager",casePager);
		return "/testManagement/data/link.pagelet";
	}

	@RequestMapping("/toEdit")
	public String edit(Integer testversionId,Model model, HttpServletRequest request){
		QualityTestTask testTask = testTaskService.findById(testversionId);
		List<ProjectProduct> projectProducts = projectProductService.findProjects((Integer) request.getSession().getAttribute("qualityProductId"));
		List<Integer> ids = new ArrayList<Integer>();
		for(ProjectProduct projectProduct : projectProducts){
			ids.add(projectProduct.getProjectId());
		}
		List<Project> projects = projectService.findByProjectList(ids);
		model.addAttribute("testTask",testTask);
		model.addAttribute("projectList",projects);
		return "/testManagement/page/tabledemo/editionversion.page";
	}

	private String getStatusCondition(String status){
		if("tvertest".equals(status)){
			return " testtask_status = 'done' ";
		}else{
			return " testtask_status <> 'done' ";
		}
	}

	@ResponseBody
	@RequestMapping("/ajax/build")
	public List<ProjectBuild> getBuild(ProjectBuild projectBuild){
		if(projectBuild.getBuildProduct()<1||projectBuild.getBuildProduct()==null){
			return null;
		}
		return buildService.findListBuild(projectBuild);
	}

	@ResponseBody
	@RequestMapping("/ajax/user")
	public List<OrgUser> getUser(ProjectTeam projectTeam){
		if(projectTeam.getProjectId()<1||projectTeam.getProjectId()==null){
			return null;
		}
		List<ProjectTeam> teams = teamService.findTeamByProjectId(projectTeam.getProjectId());
		String[] userIds = new String[teams.size()];
		for(int i=0; i<userIds.length; i++){
			userIds[i] = teams.get(i).getTeamUserId();
		}
		List<OrgUser> orgUsers = userService.findUserListByIds(userIds);
		return orgUsers;
	}

	private String mergeCondition(List<QualityTestRun> runs){
		if(!(runs.size()>0))return "";
		StringBuffer sb = new StringBuffer();
		sb.append(" not in (");
		for(QualityTestRun run : runs){
			if(!sb.toString().endsWith("("))sb.append(",");
			sb.append(run.getCaseId());
		}
		sb.append(")");
		return sb.toString();
	}
}
