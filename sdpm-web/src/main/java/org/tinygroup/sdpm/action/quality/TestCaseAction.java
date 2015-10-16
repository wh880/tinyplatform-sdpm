package org.tinygroup.sdpm.action.quality;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.action.quality.actionBean.CaseStepResult;
import org.tinygroup.sdpm.action.quality.actionBean.CaseStepResults;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.StoryService;
import org.tinygroup.sdpm.quality.dao.pojo.*;
import org.tinygroup.sdpm.quality.service.inter.CaseStepService;
import org.tinygroup.sdpm.quality.service.inter.TestCaseService;
import org.tinygroup.sdpm.quality.service.inter.TestResultService;
import org.tinygroup.sdpm.quality.service.inter.TestRunService;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
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
	@RequestMapping("")
	public String form(QualityTestCase testCase, HttpServletRequest request){
		String queryString = request.getQueryString();
		if(queryString!=null&&!queryString.contains("status")){
			return "redirect:/a/quality/testCase?status=tcaseall&"+queryString;
		}
		return "testManagement/page/cases.page";
	}	
	
	@RequestMapping("/findPager")
	public String findPager(Integer page,Integer limit,String order,String ordertype,QualityTestCase testcase,Model model, HttpServletRequest request){
		boolean asc = true;		
		if("desc".equals(ordertype)){
			asc = false;
		}
		testcase.setProductId((Integer) request.getSession().getAttribute("qualityProductId"));
		Pager<QualityTestCase> casepager = testCaseService.findTestCasePager(limit*(page-1), limit, testcase, order,asc);
		model.addAttribute("casepager",casepager);
		return "testManagement/data/casesData.pagelet";
	}
	
	@RequestMapping("/add")
	public String add(){
		return "testManagement/page/proposecase.page";
	}
	
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(QualityTestCase testcase,String[] step, String[] expect ,HttpServletRequest request){
		OrgUser user = (OrgUser) request.getSession().getAttribute("user");
		testcase = testCaseService.addTestCase(testcase);
		caseStepService.batchAdd(insertStep(step,expect,testcase));

		SystemAction systemAction = new SystemAction();
		systemAction.setActionObjectId(String.valueOf(testcase.getCaseId()));
		systemAction.setActionProduct(String.valueOf(testcase.getProductId()));
		systemAction.setActionObjectType("case");
		systemAction.setActionAction("open");
		systemAction.setActionActor(user != null?user.getOrgUserId():"0");
		logService.log(systemAction);
		return "redirect:"+"/a/quality/testCase";
	}
	
	@RequestMapping(value = "/batchSave",method = RequestMethod.POST)
	public String batchSave(List<QualityTestCase> testcases,Model model){
		testCaseService.batchUpdateTestCase(testcases);
		model.addAttribute("testcases",testcases);
		return "redirect:"+"/a/quality/testCase";
	}
	
	@RequestMapping("/execution")
	public String execution(Integer caseId,Model model){
		QualityTestCase testcase = testCaseService.findById(caseId);
		QualityCaseStep qualityCaseStep = new QualityCaseStep();
		qualityCaseStep.setCaseId(caseId);
		List<QualityCaseStep> qualityCaseSteps = caseStepService.findCaseStepList(qualityCaseStep);
		QualityTestResult qualityTestResult = new QualityTestResult();
		qualityTestResult.setLinkCase(caseId);
		List<QualityTestResult> qualityTestResults = testResultService.findTestResultList(qualityTestResult);
		Map<String,List<CaseStepResult>> caseStepResults = new HashMap<String, List<CaseStepResult>>();
		for(QualityTestResult qualityTestResult1 : qualityTestResults){
			caseStepResults.put(String.valueOf(qualityTestResult1.getTestResultId()),resolveResult(qualityTestResult1));
		}
		model.addAttribute("testcase", testcase);
		model.addAttribute("caseSteps",qualityCaseSteps);
		model.addAttribute("testResults",qualityTestResults);
		model.addAttribute("stepResults",caseStepResults);
		return "/testManagement/page/tabledemo/execution.pagelet";
	}
	@RequestMapping("/execute")
	public String execute(Integer caseId, CaseStepResults caseStepResults,HttpServletRequest request){
		OrgUser user = (OrgUser) request.getSession().getAttribute("user");
		QualityTestCase testcase = testCaseService.findById(caseId);
		QualityTestResult qualityTestResult = new QualityTestResult();
		qualityTestResult.setLinkCase(caseId);
		qualityTestResult.setTestResultDate(new Date());
		qualityTestResult.setTestResultLastRunner(user != null?user.getOrgUserNickName():"");
		qualityTestResult.setCaseVersion(testcase.getCaseVersion());
		qualityTestResult.setCaseStepresults(mergeResult(caseStepResults.getCaseStepResultList()));
		qualityTestResult.setCaseResult(testResult(caseStepResults.getCaseStepResultList()));
		testResultService.add(qualityTestResult);

		SystemAction systemAction = new SystemAction();
		systemAction.setActionObjectId(String.valueOf(caseId));
		systemAction.setActionProduct(String.valueOf(testcase.getProductId()));
		systemAction.setActionObjectType("case");
		systemAction.setActionAction("run");
		systemAction.setActionActor(user != null?user.getOrgUserId():"0");
		logService.log(systemAction);
		return "redirect:"+"/a/quality/testCase";
	}
	
	//预留，需要新增一个页面
	@RequestMapping("/batchExecution")
	public String batchExecution(){
		return "";
	}
	
	@RequestMapping("/result")
	public String result(Integer caseId,Model model){
		execution(caseId,model);
		return "/testManagement/page/tabledemo/result.pagelet";
	}
	
	@RequestMapping("/edit")
	public String edit(Integer caseId,Model model){
		QualityTestCase testCase = testCaseService.findById(caseId);
		model.addAttribute("testCase", testCase);
		return "/testManagement/page/tabledemo/editioncase.page";
	}
	
	@RequestMapping("/copy")
	public String copy(Integer caseId,Model model){
		QualityTestCase testCase = testCaseService.findById(caseId);
		model.addAttribute("testCase", testCase);
		return "/testManagement/page/copyCase.page";
	}
	
	@RequestMapping(value = "/copySave",method = RequestMethod.POST)
	public String copySave(QualityTestCase testCase,Model model){
		testCaseService.addTestCase(testCase);
		model.addAttribute("testCase", testCase);
		return "redirect:"+"/a/quality/testCase";
	}
	
	@RequestMapping("/delete")
	public String delete(Integer testCaseId,Model model){
		testCaseService.deleteById(testCaseId);
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", "success");
		map.put("info", "删除成功");
		return "redirect:"+"/a/quality/testCase";
	}
	
	@RequestMapping("/batchDelete")
	public String batchDelete(List<QualityTestCase> testcases,Model model){
		testCaseService.batchDeleteTestCase(testcases);
		return "redirect:"+"/a/quality/testCase";
	}
	@ResponseBody
	@RequestMapping("/ajax/story")
	public List<ProductStory> getStory(ProductStory productStory){
		return storyService.findStoryList(productStory,null,null);
	}
	@ResponseBody
	@RequestMapping("/ajax/module")
	public List<SystemModule> getModule(SystemModule systemModule){
		systemModule.setModuleType("story");
		return moduleService.findModules(systemModule);
	}

	private String mergeResult(List<CaseStepResult> caseStepResults){
		StringBuffer result = new StringBuffer();
		for(int i =0; i < caseStepResults.size(); i++){
			if(!"".equals(result.toString())){
				result.append(",");
			}
			result.append("s"+(i+1)).append("{").append("result:"+caseStepResults.get(i).getResult()).append(";real:"+caseStepResults.get(i).getReal()+"}");
		}
		return result.toString();
	}

	private String testResult(List<CaseStepResult> caseStepResults){
		String result = "1";
		for (CaseStepResult c : caseStepResults){
			if(c.getResult() != "1" && result != "1"){
				result=c.getResult();
			}
		}
		return result;
	}

	private List<CaseStepResult> resolveResult(QualityTestResult qualityTestResult){
		List<CaseStepResult> caseStepResults = new ArrayList<CaseStepResult>();
		String[] stepResults = qualityTestResult.getCaseStepresults().split(",");
		for(String result : stepResults){
			CaseStepResult caseStepResult = new CaseStepResult();
			int i = result.indexOf("{");
			int j = result.indexOf("}");
			String[] values = result.substring(i,j).split(";");
			for(String value : values){
				if(value.contains("result")){
					caseStepResult.setResult(value.split(":")[1]);
				}else{
					caseStepResult.setReal(value.split(":")[1]);
				}
			}
			caseStepResults.add(caseStepResult);
		}
		return caseStepResults;
	}

	private List<QualityCaseStep> insertStep(String[] steps, String[] expects, QualityTestCase tcase){
		List<QualityCaseStep> qualityCaseSteps = new ArrayList<QualityCaseStep>();
		for(int i =0; i<steps.length; i++){
			QualityCaseStep qualityCaseStep = new QualityCaseStep();
			qualityCaseStep.setCaseId(tcase.getCaseId());
			qualityCaseStep.setCaseVersion(tcase.getCaseVersion());
			qualityCaseStep.setCaseStepDesc(steps[i]);
			qualityCaseStep.setCaseStepExpect(expects[i]);
			qualityCaseSteps.add(qualityCaseStep);
		}
		return qualityCaseSteps;
	}
}
