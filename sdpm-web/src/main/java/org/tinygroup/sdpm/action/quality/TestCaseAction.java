package org.tinygroup.sdpm.action.quality;

import java.io.IOException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.sdpm.action.quality.actionBean.CaseStepResult;
import org.tinygroup.sdpm.action.quality.actionBean.CaseStepResults;
import org.tinygroup.sdpm.action.system.ProfileUtil;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.ProductService;
import org.tinygroup.sdpm.product.service.StoryService;
import org.tinygroup.sdpm.quality.dao.pojo.*;
import org.tinygroup.sdpm.quality.service.inter.CaseStepService;
import org.tinygroup.sdpm.quality.service.inter.TestCaseService;
import org.tinygroup.sdpm.quality.service.inter.TestResultService;
import org.tinygroup.sdpm.quality.service.inter.TestRunService;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.util.ModuleUtil;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by chenpeng15668 on 2015-9-24
 */

@Controller
@RequestMapping("/a/quality/testCase")
public class TestCaseAction extends BaseController {

	@Autowired
	private TestCaseService testCaseService;
	@Autowired
	private CaseStepService caseStepService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private StoryService storyService;
	@Autowired
	private TestResultService testResultService;
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;

	@RequestMapping("")
	public String form(QualityTestCase testCase, HttpServletRequest request,Model model) {
		String queryString = request.getQueryString();
		if (queryString != null && !queryString.contains("status")) {
			return "redirect:/a/quality/testCase?status=tcaseall&"
					+ queryString;
		}
		List<OrgUser> userList = userService.findUserList(new OrgUser());
		List<Product> productList = productService.findProductList(new Product());
		SystemModule module = new SystemModule();
		module.setModuleType("task");
		List<SystemModule> modelList = moduleService.findAllModules(module);
		model.addAttribute("userList", userList);
		model.addAttribute("productList", productList);
		model.addAttribute("modelList", modelList);
		return "testManagement/page/cases.page";
	}

	@RequestMapping("/findPager")
	public String findPager(Integer page, Integer limit, String groupOperate,
			SearchInfos searchInfos, String status, String order,
			String ordertype, QualityTestCase testcase, Model model,
			HttpServletRequest request) {
		boolean asc = true;
		if ("desc".equals(ordertype)) {
			asc = false;
		}
		if (request.getSession().getAttribute("qualityProductId") != null) {
			testcase.setProductId((Integer) request.getSession().getAttribute(
					"qualityProductId"));
		}
		String condition = "";
		if (testcase.getModuleId() != null && testcase.getModuleId() > 0) {

			SystemModule module = new SystemModule();
			module.setModuleId(testcase.getModuleId());
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("in (");
			ModuleUtil.getConditionByModule(stringBuffer, module, moduleService);
			stringBuffer.append(")");
			
			if(!("".equals(condition.trim())||condition==null)){
				condition +=" and ";
			}
			condition +=  NameUtil.resolveNameDesc("moduleId") + " "+ stringBuffer.toString();
		}
		testcase.setModuleId(null);
		Pager<QualityTestCase> casepager = testCaseService.findTestCasePager(limit * (page - 1), limit, testcase, condition, searchInfos, groupOperate, order, asc);
		model.addAttribute("casepager", casepager);
		return "testManagement/data/casesData.pagelet";
	}

	@RequestMapping("/add")
	public String add() {
		return "testManagement/page/proposecase.page";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(QualityTestCase testcase, String[] step,String[] expect, @RequestParam(value = "file", required = false) MultipartFile[] file, String[] title, HttpServletRequest request) throws Exception {
		OrgUser user = (OrgUser) request.getSession().getAttribute("user");
		testcase = testCaseService.addTestCase(testcase);
		caseStepService.batchAdd(insertStep(step, expect, testcase));

		 ProfileUtil profileUtil = new ProfileUtil();

	     profileUtil.uploads(file, testcase.getCaseId(), "story", title);
		
		SystemAction systemAction = new SystemAction();
		systemAction.setActionObjectId(String.valueOf(testcase.getCaseId()));
		systemAction.setActionProduct(String.valueOf(testcase.getProductId()));
		systemAction.setActionObjectType("case");
		systemAction.setActionAction("open");
		systemAction.setActionActor(user != null ? user.getOrgUserId() : "0");
		logService.log(systemAction);
		return "redirect:" + "/a/quality/testCase";
	}

	@RequestMapping(value = "/batchSave", method = RequestMethod.POST)
	public String batchSave(List<QualityTestCase> testcases, Model model) {
		testCaseService.batchUpdateTestCase(testcases);
		model.addAttribute("testcases", testcases);
		return "redirect:" + "/a/quality/testCase";
	}
	
	

	@RequestMapping("/execution")
	public String execution(Integer caseId, Model model) {
		QualityTestCase testcase = testCaseService.findById(caseId);
		QualityCaseStep qualityCaseStep = new QualityCaseStep();
		qualityCaseStep.setCaseId(caseId);
		List<QualityCaseStep> qualityCaseSteps = caseStepService
				.findCaseStepList(qualityCaseStep);
		QualityTestResult qualityTestResult = new QualityTestResult();
		qualityTestResult.setLinkCase(caseId);
		List<QualityTestResult> qualityTestResults = testResultService
				.findTestResultList(qualityTestResult);
		Map<String, List<CaseStepResult>> caseStepResults = new HashMap<String, List<CaseStepResult>>();
		for (QualityTestResult qualityTestResult1 : qualityTestResults) {
			caseStepResults.put(
					String.valueOf(qualityTestResult1.getTestResultId()),
					resolveResult(qualityTestResult1));
		}
		model.addAttribute("testcase", testcase);
		model.addAttribute("caseSteps", qualityCaseSteps);
		model.addAttribute("testResults", qualityTestResults);
		model.addAttribute("stepResults", caseStepResults);
		return "/testManagement/page/tabledemo/execution.pagelet";
	}

	@RequestMapping("/execute")
	public String execute(Integer caseId, CaseStepResults caseStepResults,
			HttpServletRequest request) {
		OrgUser user = (OrgUser) request.getSession().getAttribute("user");
		QualityTestCase testcase = testCaseService.findById(caseId);
		QualityTestResult qualityTestResult = new QualityTestResult();
		qualityTestResult.setLinkCase(caseId);
		qualityTestResult.setTestResultDate(new Date());
		qualityTestResult.setTestResultLastRunner(user != null ? user
				.getOrgUserNickName() : "");
		qualityTestResult.setCaseVersion(testcase.getCaseVersion());
		qualityTestResult.setCaseStepresults(mergeResult(caseStepResults
				.getCaseStepResultList()));
		qualityTestResult.setCaseResult(testResult(caseStepResults
				.getCaseStepResultList()));
		testResultService.add(qualityTestResult);

		SystemAction systemAction = new SystemAction();
		systemAction.setActionObjectId(String.valueOf(caseId));
		systemAction.setActionProduct(String.valueOf(testcase.getProductId()));
		systemAction.setActionObjectType("case");
		systemAction.setActionAction("run");
		systemAction.setActionActor(user != null ? user.getOrgUserId() : "0");
		logService.log(systemAction);
		return "redirect:" + adminPath+"/quality/testCase";
	}

	// 预留，需要新增一个页面
	@RequestMapping("/batchExecution")
	public String batchExecution() {
		return "";
	}

	@RequestMapping("/result")
	public String result(Integer caseId, Model model) {
		execution(caseId, model);
		return "/testManagement/page/tabledemo/result.pagelet";
	}

	@RequestMapping("/edit")
	public String edit(Integer caseId, Model model) {
		QualityTestCase testCase = testCaseService.findById(caseId);
		QualityCaseStep step = new QualityCaseStep();
		step.setCaseId(caseId);
		List<QualityCaseStep> stepList = caseStepService.findCaseStepList(step);
		
		model.addAttribute("testCase", testCase);
		model.addAttribute("stepList", stepList);
		return "/testManagement/page/tabledemo/editioncase.page";
	}
	
	@RequestMapping("/find/{forward}")
	public String findById(@PathVariable(value = "forward") String forward,Integer caseId, Model model) {
		QualityTestCase testCase = testCaseService.findById(caseId);
		model.addAttribute("testCase", testCase);
		if("editRight".equals(forward)){
			return "/testManagement/page/tabledemo/editioncasepaging.pagelet";
		}
		return "";
	}

	@RequestMapping("/copy")
	public String copy(Integer caseId, Model model) {
		QualityTestCase testCase = testCaseService.findById(caseId);
		QualityCaseStep step = new QualityCaseStep();
		step.setCaseId(caseId);
		List<QualityCaseStep> stepList = caseStepService.findCaseStepList(step);
		
		model.addAttribute("stepList", stepList);
		model.addAttribute("testCase", testCase);
		return "/testManagement/page/copyCase.page";
	}

	@RequestMapping(value = "/copySave", method = RequestMethod.POST)
	public String copySave(QualityTestCase testCase, Model model) {
		testCaseService.addTestCase(testCase);
		model.addAttribute("testCase", testCase);
		return "redirect:" + "/a/quality/testCase";
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public String delete(Integer testCaseId, Model model) {
		testCaseService.deleteById(testCaseId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", "success");
		map.put("info", "删除成功");
		return "redirect:" +adminPath+ "/quality/testCase";
	}

	@RequestMapping("/batchDelete")
	public String batchDelete(List<QualityTestCase> testcases, Model model) {
		testCaseService.batchDeleteTestCase(testcases);
		return "redirect:" + "/a/quality/testCase";
	}
	
	@ResponseBody
	@RequestMapping("/batchDeleteAjax")
	public int[] batchDelete(@RequestBody QualityTestCase[] cases) {
		
		List<QualityTestCase>  qualityTestCase = new ArrayList<QualityTestCase>();
    	if(cases!=null&&cases.length>0){
    		qualityTestCase = Arrays.asList(cases);
    	}
    	return testCaseService.batchDeleteTestCase(qualityTestCase);
	}

	@ResponseBody
	@RequestMapping("/ajax/story")
	public List<ProductStory> getStory(ProductStory productStory) {
		return storyService.findStoryList(productStory, null, null);
	}

	@ResponseBody
	@RequestMapping("/ajax/module")
	public List<SystemModule> getModule(SystemModule systemModule) {
		systemModule.setModuleType("story");
		return moduleService.findModules(systemModule);
	}

	private String mergeResult(List<CaseStepResult> caseStepResults) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < caseStepResults.size(); i++) {
			if (!"".equals(result.toString())) {
				result.append(",");
			}
			result.append("s" + (i + 1)).append("{")
					.append("result:" + caseStepResults.get(i).getResult())
					.append(";real:" + caseStepResults.get(i).getReal() + "}");
		}
		return result.toString();
	}

	private String testResult(List<CaseStepResult> caseStepResults) {
		String result = "1";
		for (CaseStepResult c : caseStepResults) {
			if (!"1".equals(c.getResult()) && !"1".equals(result)) {
				result = c.getResult();
			}
		}
		return result;
	}

	private List<CaseStepResult> resolveResult(
			QualityTestResult qualityTestResult) {
		List<CaseStepResult> caseStepResults = new ArrayList<CaseStepResult>();
		String[] stepResults = qualityTestResult.getCaseStepresults()
				.split(",");
		for (String result : stepResults) {
			CaseStepResult caseStepResult = new CaseStepResult();
			int i = result.indexOf("{");
			int j = result.indexOf("}");
			String[] values = result.substring(i+1, j).split(";");
			for (String value : values) {
				String[] r = value.split(":");
				if ("result".endsWith(r[0])) {
					caseStepResult.setResult(r.length>1?r[1]:"");
				} else {
					caseStepResult.setReal(r.length>1?r[1]:"");
				}
			}
			caseStepResults.add(caseStepResult);
		}
		return caseStepResults;
	}

	private List<QualityCaseStep> insertStep(String[] steps, String[] expects,
			QualityTestCase tcase) {
		List<QualityCaseStep> qualityCaseSteps = new ArrayList<QualityCaseStep>();
		for (int i = 0; i < steps.length; i++) {
			QualityCaseStep qualityCaseStep = new QualityCaseStep();
			qualityCaseStep.setCaseId(tcase.getCaseId());
			qualityCaseStep.setCaseVersion(tcase.getCaseVersion());
			qualityCaseStep.setCaseStepDesc(steps[i]);
			qualityCaseStep.setCaseStepExpect(expects[i]);
			qualityCaseSteps.add(qualityCaseStep);
		}
		return qualityCaseSteps;
	}
	
	
	@RequestMapping("/case/viewInfo")
	public String viewInfo(Integer id,Model model){
		QualityTestCase testCase = testCaseService.findById(id);
		model.addAttribute("testCase", testCase);
		return "/testManagement/page/caseInfo.page";
	}
}
