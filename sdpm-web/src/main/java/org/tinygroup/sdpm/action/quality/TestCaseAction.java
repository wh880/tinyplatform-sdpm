package org.tinygroup.sdpm.action.quality;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.action.quality.actionBean.CaseStepResult;
import org.tinygroup.sdpm.action.quality.actionBean.CaseStepResults;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.StoryService;
import org.tinygroup.sdpm.quality.dao.pojo.*;
import org.tinygroup.sdpm.quality.service.inter.*;
import org.tinygroup.sdpm.system.dao.pojo.ProfileType;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.ModuleUtil;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
	private TestRunService testRunService;
	@Autowired
	private TestTaskService testTaskService;
	@Autowired
	private ProfileService profileService;


	@RequestMapping("")
	public String form(QualityTestCase testCase, HttpServletRequest request,Model model) {
		String queryString = request.getQueryString();
		if (queryString == null || !queryString.contains("status")) {
			return "redirect:/a/quality/testCase?currentPageId=5&status=tcaseall"
					+ (queryString==null?"":"&"+queryString);
		}
		return "testManagement/page/cases.page";
	}

	@RequestMapping("/findPager")
	public String findPager(@CookieValue Integer qualityProductId,Integer start, Integer limit, String groupOperate,
							SearchInfos searchInfos, String status, String order,
							String ordertype, QualityTestCase testcase, Model model,
							HttpServletRequest request) {
		boolean asc = true;
		if ("desc".equals(ordertype)) {
			asc = false;
		}
		testcase.setProductId(qualityProductId);

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
		testcase.setDeleted(0);
		Pager<QualityTestCase> casepager = null;
		if("tcaseneedchange".equals(status)){
			casepager = testCaseService.findStoryChangedCase(start,limit,testcase,condition,order,"asc".equals(ordertype)?true:false);
		}else if("tcaseall".equals(status)){
			casepager = testCaseService.findTestCasePager(start, limit, testcase, condition, searchInfos, groupOperate, order, asc);
		}
		model.addAttribute("casepager", casepager);
		return "testManagement/data/casesData.pagelet";
	}

	@RequestMapping("/add")
	public String add() {
		return "testManagement/page/proposecase.page";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(QualityTestCase testcase,String comment, String[] step,String[] expect, @RequestParam(value = "file", required = false) MultipartFile[] file, String[] title, HttpServletRequest request) throws Exception {
		if(testcase.getCaseId()==null||testcase.getCaseId()<1) {
			testcase.setCaseOpenedBy(UserUtils.getUserId());
			if (testcase.getStoryId()!=null&&testcase.getStoryId() > 0) {
				testcase.setStoryVersion(storyService.findStory(testcase.getStoryId()).getStoryVersion());
			}
			testcase.setCaseStatus("1");
			testcase.setDeleted(0);
			testcase.setCaseVersion(1);
			testcase = testCaseService.addTestCase(testcase);
			insertStep(step, expect, testcase);

			LogUtil.logWithComment(LogUtil.LogOperateObject.CASE,
					LogUtil.LogAction.OPENED,
					String.valueOf(testcase.getCaseId()),
					UserUtils.getUserId(),
					String.valueOf(testcase.getProductId()),
					null,
					null,
					null,
					null
			);
		}else{
			QualityTestCase testCase = testCaseService.findById(testcase.getCaseId());
			QualityCaseStep step1 = new QualityCaseStep();
			step1.setCaseId(testcase.getCaseId());
			List<QualityCaseStep> steps = caseStepService.findCaseStepList(step1);
			if(isCaseModify(step,expect,steps)){
				testcase.setCaseVersion(testCase.getCaseVersion()+1);
				insertStep(step, expect, testcase);
			}
			testcase.setCaseLastEditedBy(UserUtils.getUserId());
			testCaseService.updateTestCase(testcase);
			LogUtil.logWithComment(LogUtil.LogOperateObject.CASE,
					LogUtil.LogAction.EDITED,
					String.valueOf(testcase.getCaseId()),
					UserUtils.getUserId(),
					String.valueOf(testcase.getProductId()),
					null,
					testCase,
					testcase,
					comment
			);
		}
		uploads(file, testcase.getCaseId(), ProfileType.TESTCASE, title);
		return "redirect:" + "/a/quality/testCase";
	}

	@RequestMapping(value = "/batchSave", method = RequestMethod.POST)
	public String batchSave(List<QualityTestCase> testcases, Model model) {
		testCaseService.batchUpdateTestCase(testcases);
		model.addAttribute("testcases", testcases);
		return "redirect:" + "/a/quality/testCase";
	}
	
	

	@RequestMapping("/execution")
	public String execution(Integer caseId,String caseVersion, Model model) {
		QualityTestCase testcase = testCaseService.findById(caseId);
		QualityCaseStep qualityCaseStep = new QualityCaseStep();
		qualityCaseStep.setCaseId(caseId);
		qualityCaseStep.setCaseVersion(StringUtil.isBlank(caseVersion)?testcase.getCaseVersion():Integer.parseInt(caseVersion));
		List<QualityCaseStep> stepList = caseStepService.findCaseStepList(qualityCaseStep);
		QualityTestResult qualityTestResult = new QualityTestResult();
		qualityTestResult.setLinkCase(caseId);
		List<QualityTestResult> qualityTestResults = testResultService
				.findTestResultList(qualityTestResult);
		Map<String, List<CaseStepResult>> caseStepResults = new HashMap<String, List<CaseStepResult>>();
		Map<String, List<QualityCaseStep>> caseSteps= new HashMap<String, List<QualityCaseStep>>();
		for (QualityTestResult qualityTestResult1 : qualityTestResults) {
			qualityCaseStep.setCaseVersion(qualityTestResult1.getCaseVersion());
			List<QualityCaseStep> caseStepList = caseStepService.findCaseStepList(qualityCaseStep);
			caseSteps.put(String.valueOf(qualityTestResult1.getTestResultId()),caseStepList);
			caseStepResults.put(
					String.valueOf(qualityTestResult1.getTestResultId()),
					resolveResult(qualityTestResult1));
		}

		model.addAttribute("testcase", testcase);
		model.addAttribute("stepList", stepList);
		model.addAttribute("caseSteps", caseSteps);
		model.addAttribute("testResults", qualityTestResults);
		model.addAttribute("stepResults", caseStepResults);
		return "/testManagement/page/tabledemo/execution.pagelet";
	}

	@RequestMapping("/execute")
	public String execute(Integer caseId, CaseStepResults caseStepResults,String resultType, String from,Integer runId,
			HttpServletRequest request){
		QualityTestRun run = null;
		if(!StringUtil.isBlank(from)){
			run = testRunService.findRunById(runId);
		}
		QualityTestCase old = testCaseService.findById(caseId);
		QualityTestCase testCase = new QualityTestCase();
		testCase.setCaseId(caseId);
		QualityTestResult qualityTestResult = new QualityTestResult();
		qualityTestResult.setLinkCase(caseId);
		qualityTestResult.setTestResultDate(new Date());
		qualityTestResult.setTestResultLastRunner(UserUtils.getUserId());
		qualityTestResult.setCaseVersion(old.getCaseVersion());
		if(caseStepResults!=null&&caseStepResults.getCaseStepResultList()!=null&&caseStepResults.getCaseStepResultList().size()>0) {
			qualityTestResult.setCaseStepresults(mergeResult(caseStepResults
					.getCaseStepResultList()));
			String result = testResult(caseStepResults
					.getCaseStepResultList());
			qualityTestResult.setCaseResult(result);
			testCase.setCaseLastRunResult(result);
			mergeRun(run,result);
			if("3".equals(result)){
				testCase.setCaseStatus("2");
				if(run!=null){
					run.setTestRunStatus("2");
				}
			}else{
				testCase.setCaseStatus("1");
				if(run!=null){
					run.setTestRunStatus("1");
				}
			}
		}
		if(!StringUtil.isBlank(resultType)){
			String result = "pass".equals(resultType)?"1":"2";
			qualityTestResult.setCaseResult(result);
			testCase.setCaseLastRunResult(result);
			mergeRun(run,result);
		}
		testCase.setCaseLastRunDate(new Date());
		if(run!=null){
			run.setTestRunLastRunner(UserUtils.getUserId());
			qualityTestResult.setTestresultRun(run.getTestRunId());
			testRunService.updateTestRun(run);
		}
		testCase.setCaseLastRunner(UserUtils.getUserId());
		testCaseService.updateTestCase(testCase);
		testResultService.add(qualityTestResult);
		LogUtil.logWithComment(LogUtil.LogOperateObject.CASE,
				LogUtil.LogAction.RUN,
				String.valueOf(old.getCaseId()),
				UserUtils.getUserId(),
				String.valueOf(old.getProductId()),
				null,
				old,
				testCase,
				null
		);
		if(run!=null)return "redirect:" + adminPath+"/quality/version/taskToCase?testversionId="+run.getTaskId()+"&currentPageId=5&status=tverallcase";
		return "redirect:" + adminPath+"/quality/testCase";
	}

	@RequestMapping("group")
	public String group(QualityTestCase qualityTestCase,Model model){
		Map<String, ProductStory> storyMap = new HashMap<String, ProductStory>();
		Map<String, List<QualityTestCase>> casesMap = new HashMap<String, List<QualityTestCase>>();
		List<QualityTestCase> cases = testCaseService.findTestCaseList(qualityTestCase);
		for(QualityTestCase testCase : cases){
			if(!storyMap.containsKey(testCase.getStoryId())){
				ProductStory story = storyService.findStory(testCase.getStoryId());
				storyMap.put(String.valueOf(story.getStoryId()),story);
			}
			if(!casesMap.containsKey(testCase.getStoryId())){
				casesMap.put(String.valueOf(testCase.getStoryId()),new ArrayList<QualityTestCase>());
				casesMap.get(String.valueOf(testCase.getStoryId())).add(testCase);
			}else{
				casesMap.get(String.valueOf(testCase.getStoryId())).add(testCase);
			}
		}

		model.addAttribute("storyMap",storyMap);
		model.addAttribute("casesMap",casesMap);
		return "/testManagement/page/group.pagelet";
	}

	// 预留，需要新增一个页面
	@RequestMapping("/batchExecution")
	public String batchExecution() {
		return "";
	}

	@RequestMapping("/result")
	public String result(Integer caseId, Model model) {
		execution(caseId,null,model);
		return "/testManagement/page/tabledemo/result.pagelet";
	}

	@ResponseBody
	@RequestMapping("addComment")
	public Map recordComment(String comment, int caseId){
		QualityTestCase testCase = testCaseService.findById(caseId);
		LogUtil.logWithComment(LogUtil.LogOperateObject.CASE
				, LogUtil.LogAction.COMMENTED
				,String.valueOf(caseId)
				,UserUtils.getUserId()
				,String.valueOf(testCase.getProductId())
				,null
				,null
				,null
				,comment);
		Map<String,String> result = new HashMap<String, String>();
		result.put("status","success");
		return result;
	}

	@RequestMapping("/edit")
	public String edit(Integer caseId, Model model) {
		QualityTestCase testCase = testCaseService.findById(caseId);
		QualityCaseStep step = new QualityCaseStep();
		step.setCaseId(caseId);
		step.setCaseVersion(testCase.getCaseVersion());
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
		step.setCaseVersion(testCase.getCaseVersion());
		List<QualityCaseStep> stepList = caseStepService.findCaseStepList(step);

		model.addAttribute("stepList", stepList);
		model.addAttribute("testCase", testCase);
		return "/testManagement/page/copyCase.page";
	}


	@ResponseBody
	@RequestMapping("/delete")
	public Map delete(Integer id) {
		testCaseService.deleteById(id);
		Map<String, String> map = new HashMap<String, String>();
		map.put("status", "success");
		map.put("info", "删除成功");
		return map;
	}

	@ResponseBody
	@RequestMapping("/batchDelete")
	public Map batchDelete(String ids, Model model) {
		String[] cId =  ids.split(",");
		for(String id : cId){
			QualityTestCase testCase = new QualityTestCase();
			testCase.setCaseId(Integer.parseInt(id));
			testCase.setDeleted(1);
			testCaseService.updateTestCase(testCase);
		}
		return resultMap(true,"删除成功");
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
		if(!(productStory.getProductId()>0))return new ArrayList<ProductStory>();
		return storyService.findStoryList(productStory, null, null);
	}

	@ResponseBody
	@RequestMapping("/ajax/module")
	public List<SystemModule> getModule(SystemModule systemModule) {
		if(!(systemModule.getModuleRoot()>0))return new ArrayList<SystemModule>();
		systemModule.setModuleType("story");
		List<SystemModule> result = moduleService.findModules(systemModule);
		for(SystemModule module : result){
			module.setModuleName(ModuleUtil.getPath(module.getModuleId(),"/",moduleService,null,false));
		}
		return result;
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

	@RequestMapping("/chooseToBug")
	public String chooseTobug(Integer runId,Integer caseId,Integer caseVersion, Model model) {
		QualityTestCase testcase = testCaseService.findById(caseId);
		QualityCaseStep qualityCaseStep = new QualityCaseStep();
		qualityCaseStep.setCaseId(caseId);
		qualityCaseStep.setCaseVersion(caseVersion);
		List<QualityCaseStep> stepList = caseStepService.findCaseStepList(qualityCaseStep);
		QualityTestResult qualityTestResult = new QualityTestResult();
		qualityTestResult.setLinkCase(caseId);
		qualityTestResult.setCaseVersion(caseVersion);
		qualityTestResult.setTestresultRun(runId);
		List<QualityTestResult> qualityTestResults = testResultService
				.findTestResultList(qualityTestResult);
		List<CaseStepResult> caseStepResults = new ArrayList<CaseStepResult>();
		Map<String, List<QualityCaseStep>> caseSteps= new HashMap<String, List<QualityCaseStep>>();
		if(qualityTestResults.size()>0) {
			caseSteps.put(String.valueOf(qualityTestResults.get(0).getTestResultId()), stepList);
			caseStepResults = resolveResult(qualityTestResults.get(0));
		}
		model.addAttribute("caseId",caseId);
		model.addAttribute("stepList", stepList);
		model.addAttribute("caseSteps", caseSteps);
		model.addAttribute("stepResults", caseStepResults);
		return "/testManagement/page/caseToBug.pagelet";
	}

	private String testResult(List<CaseStepResult> caseStepResults) {
		if(caseStepResults==null||caseStepResults.size()<1)return "0";
		String result = "1";
		for (CaseStepResult c : caseStepResults) {
			if ("2".equals(c.getResult())) {
				result = c.getResult();
			}else if("3".equals(c.getResult())){
				result = "2".equals(result)?"2":"3";
			}
		}
		return result;
	}

	private List<CaseStepResult> resolveResult(
			QualityTestResult qualityTestResult) {
		if(StringUtil.isBlank(qualityTestResult.getCaseStepresults()))return new ArrayList<CaseStepResult>();
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

	private void insertStep(String[] steps, String[] expects,
			QualityTestCase tcase) {
		if(steps==null||steps.length<1||StringUtil.isBlank(steps[0]))return;
		List<QualityCaseStep> qualityCaseSteps = new ArrayList<QualityCaseStep>();
		for (int i = 0; i < steps.length; i++) {
			QualityCaseStep qualityCaseStep = new QualityCaseStep();
			qualityCaseStep.setCaseId(tcase.getCaseId());
			qualityCaseStep.setCaseVersion(tcase.getCaseVersion());
			qualityCaseStep.setCaseStepDesc(steps[i]);
			qualityCaseStep.setCaseStepExpect(expects[i]);
			qualityCaseSteps.add(qualityCaseStep);
		}
		for(QualityCaseStep step : qualityCaseSteps){
			caseStepService.addCaseStep(step);
		}
	}
	
	
	@RequestMapping("/case/viewInfo")
	public String viewInfo(Integer id,Integer version,Model model){
		QualityTestCase testCase = testCaseService.findById(id);
		QualityCaseStep step = new QualityCaseStep();
		step.setCaseId(id);
		if(version!=null&&version>0){
			step.setCaseVersion(version);
		}else{
			step.setCaseVersion(testCase.getCaseVersion());
		}
		List<QualityCaseStep> stepList = caseStepService.findCaseStepList(step);
		SystemProfile systemProfile = new SystemProfile();
		systemProfile.setFileObjectType(ProfileType.TESTCASE.toString());
		systemProfile.setFileDeleted("0");
		systemProfile.setFileObjectId(testCase.getCaseId());
		List<SystemProfile> list = profileService.find(systemProfile);
		model.addAttribute("file",list);
		model.addAttribute("testCase", testCase);
		model.addAttribute("stepList", stepList);
		return "/testManagement/page/caseInfo.page";
	}

	@RequestMapping("/case/rightInfo")
	public String rightInfo(Integer caseId,Model model){
		QualityTestCase testCase = testCaseService.findById(caseId);
		model.addAttribute("testCase", testCase);
		return "/testManagement/page/tabledemo/caseEditInfo.pagelet";
	}

	public boolean isCaseModify(String[] step, String[] expect,List<QualityCaseStep> steps){
		if(step==null){
			return (steps.size()<1)?false:true;
		}else if(step.length>0&&step.length == steps.size()){
			for(int i=0; i<step.length; i++){
				QualityCaseStep caseStep = steps.get(i);
				if(!StringUtil.equals(caseStep.getCaseStepDesc(),step[i])||
						!StringUtil.equals(caseStep.getCaseStepExpect(),expect[i])){
					return true;
				}
			}
			return false;
		}
		return true;
	}

	private void mergeRun(QualityTestRun run, String result){
		if(run!=null){
			boolean haveNull = false;
			boolean isHaveBlock = false;
			run.setTestRunLastRunDate(new Date());
			run.setTestRunLastRunResult(result);
			QualityTestRun testRun = new QualityTestRun();
			testRun.setTaskId(run.getTaskId());
			List<QualityTestRun> testRuns = testRunService.findTestRunList(testRun);
			for(QualityTestRun run1 : testRuns){
				if(!run1.getTestRunId().equals(run.getTestRunId())){
					if(StringUtil.isBlank(run1.getTestRunLastRunResult())){
						haveNull = true;
					}else{
						isHaveBlock = "3".equals(run1.getTestRunLastRunResult());
					}

				}
			}
			isHaveBlock = "3".equals(run.getTestRunLastRunResult());
			QualityTestTask testTask = testTaskService.findById(run.getTaskId());
			if(haveNull){
				testTask.setTesttaskStatus("2");
			}else{
				if(isHaveBlock){
					testTask.setTesttaskStatus("4");
				}else{
					testTask.setTesttaskStatus("3");
				}
			}
			testTaskService.updateTestTask(testTask);
		}
	}

}
