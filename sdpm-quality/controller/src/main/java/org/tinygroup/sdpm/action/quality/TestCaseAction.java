package org.tinygroup.sdpm.action.quality;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.action.quality.actionBean.CaseStepResult;
import org.tinygroup.sdpm.action.quality.actionBean.CaseStepResults;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.dto.UploadProfile;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.inter.StoryService;
import org.tinygroup.sdpm.quality.dao.pojo.*;
import org.tinygroup.sdpm.quality.service.inter.*;
import org.tinygroup.sdpm.system.dao.pojo.ProfileType;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.sdpm.util.CookieUtils;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.ModuleUtil;
import org.tinygroup.sdpm.util.ProductUtils;
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
    @Autowired
    private BugService bugService;


    @ModelAttribute
    public void init(Model model) {
        initSearchBar(model, "用例");
    }

    @RequestMapping("")
    public String form(String type, HttpServletRequest request, Model model) {
        String queryString = request.getQueryString();
        if (queryString == null || !queryString.contains("status")) {
            return "redirect:/a/quality/testCase?currentPageId=5&status=tcaseall"
                    + (queryString == null ? "" : "&" + queryString);
        }
        if ("noCase".equals(type)) {
            return "quality/index/case/NoTestCaseStory.page";
        }
        return "quality/index/case/testCase.page";
    }

    @RequestMapping("/findPager")
    public String findPager(@CookieValue(value = ProductUtils.COOKIE_PRODUCT_ID, defaultValue = "0") Integer cookieProductId, Integer start, Integer limit, String groupOperate,
                            SearchInfos searchInfos, String status, @RequestParam(required = false, defaultValue = "caseId") String order,
                            @RequestParam(required = false, defaultValue = "desc") String ordertype, QualityTestCase testcase, Model model,
                            HttpServletRequest request) {
        boolean asc = true;
        if ("desc".equals(ordertype)) {
            asc = false;
        }
        testcase.setProductId(cookieProductId);
        testcase.setDeleted(0);
        ConditionCarrier carrier = new ConditionCarrier();
        if (testcase.getModuleId() != null && testcase.getModuleId() > 0) {
            carrier.putModuleIn("qualityTestCase.moduleId", String.valueOf(testcase.getModuleId()));
            testcase.setModuleId(null);
        }

        Pager<QualityTestCase> casepager = null;
        if ("tcaseneedchange".equals(status)) {
            casepager = testCaseService.findStoryChangedCase(start, limit, testcase, carrier, order, "asc".equals(ordertype) ? true : false);
        } else if ("tcaseall".equals(status)) {
            carrier.putSearch("caseSearch", searchInfos, groupOperate);
            casepager = testCaseService.findTestCasePagerByConditionCarrier(start, limit, testcase, carrier, order, asc);
        }
        model.addAttribute("casepager", casepager);
        return "quality/data/case/casesData.pagelet";
    }

    @RequiresPermissions("add-case")
    @RequestMapping("/add")
    public String add(Integer bugId, Model model) {
        if (bugId != null) {
            model.addAttribute("bug", bugService.findQualityBugById(bugId));
        }
        return "quality/operate/case/proposecase.page";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(QualityTestCase testcase,
                       String comment,
                       String[] step,
                       String[] expect,
                       String lastAddress,
                       String currentAddress,
                       UploadProfile uploadProfile) throws Exception {
        if (testcase.getCaseId() == null || testcase.getCaseId() == 0) {
            testcase.setCaseOpenedBy(userUtils.getUserId());
            if (testcase.getStoryId() != null && testcase.getStoryId() > 0) {
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
                    userUtils.getUserId(),
                    String.valueOf(testcase.getProductId()),
                    null,
                    null,
                    null,
                    null
            );
        } else {
            QualityTestCase testCase = testCaseService.testCase(testcase.getCaseId());
            QualityCaseStep step1 = new QualityCaseStep();
            step1.setCaseId(testcase.getCaseId());
            List<QualityCaseStep> steps = caseStepService.findCaseStepList(step1);
            if (isCaseModify(step, expect, steps)) {
                Integer maxVersion = caseStepService.getCaseMaxVersion(testCase.getCaseId());
                maxVersion = maxVersion == null ? testCase.getCaseVersion() : maxVersion;
                testcase.setCaseVersion(maxVersion + 1);
                insertStep(step, expect, testcase);
            }
            testcase.setCaseLastEditedBy(userUtils.getUserId());
            testCaseService.updateTestCase(testcase);
            LogUtil.logWithComment(LogUtil.LogOperateObject.CASE,
                    LogUtil.LogAction.EDITED,
                    String.valueOf(testcase.getCaseId()),
                    userUtils.getUserId(),
                    String.valueOf(testcase.getProductId()),
                    null,
                    testCase,
                    testcase,
                    comment
            );
        }
        storyJudge(testcase.getStoryId());
        processProfile(uploadProfile, testcase.getCaseId(), ProfileType.TESTCASE);
        if (!StringUtil.isBlank(currentAddress)) {
            return "redirect:" + currentAddress;
        } else {
            if (!StringUtil.isBlank(lastAddress)) {
                return "redirect:" + lastAddress;
            }
        }
        return "redirect:/a/quality/testCase";
    }

    @RequestMapping(value = "/batchSave", method = RequestMethod.POST)
    public String batchSave(List<QualityTestCase> testcases, Model model) {
        testCaseService.batchUpdateTestCase(testcases);
        for (QualityTestCase testCase : testcases) {
            storyJudge(testCase.getStoryId());
        }
        model.addAttribute("testcases", testcases);
        return "redirect:" + "/a/quality/testCase";
    }

    @RequiresPermissions("texecution")
    @RequestMapping("/execution")
    public String execution(Integer caseId, String caseVersion, Model model) {
        QualityTestCase testcase = testCaseService.testCase(caseId);
        QualityCaseStep qualityCaseStep = new QualityCaseStep();
        qualityCaseStep.setCaseId(caseId);
        qualityCaseStep.setCaseVersion(StringUtil.isBlank(caseVersion) ? testcase.getCaseVersion() : Integer.parseInt(caseVersion));
        List<QualityCaseStep> stepList = caseStepService.findCaseStepList(qualityCaseStep);
        QualityTestResult qualityTestResult = new QualityTestResult();
        qualityTestResult.setLinkCase(caseId);
        List<QualityTestResult> qualityTestResults = testResultService
                .findTestResultList(qualityTestResult);
        Map<String, List<CaseStepResult>> caseStepResults = new HashMap<String, List<CaseStepResult>>();
        Map<String, List<QualityCaseStep>> caseSteps = new HashMap<String, List<QualityCaseStep>>();
        for (QualityTestResult qualityTestResult1 : qualityTestResults) {
            qualityCaseStep.setCaseVersion(qualityTestResult1.getCaseVersion());
            List<QualityCaseStep> caseStepList = caseStepService.findCaseStepList(qualityCaseStep);
            caseSteps.put(String.valueOf(qualityTestResult1.getTestResultId()), caseStepList);
            caseStepResults.put(
                    String.valueOf(qualityTestResult1.getTestResultId()),
                    resolveResult(qualityTestResult1));
        }

        model.addAttribute("testcase", testcase);
        model.addAttribute("stepList", stepList);
        model.addAttribute("caseSteps", caseSteps);
        model.addAttribute("testResults", qualityTestResults);
        model.addAttribute("stepResults", caseStepResults);
        return "/quality/modal/case/execution.pagelet";
    }

    @RequestMapping("/execute")
    public String execute(Integer caseId,
                          CaseStepResults caseStepResults,
                          String resultType,
                          String from,
                          Integer runId) {
        QualityTestRun run = null;
        if (!StringUtil.isBlank(from)) {
            run = testRunService.findTestRunById(runId);
        }
        QualityTestCase old = testCaseService.testCase(caseId);
        QualityTestCase testCase = new QualityTestCase();
        testCase.setCaseId(caseId);
        QualityTestResult qualityTestResult = new QualityTestResult();
        qualityTestResult.setLinkCase(caseId);
        qualityTestResult.setTestResultDate(new Date());
        qualityTestResult.setTestResultLastRunner(userUtils.getUserId());
        qualityTestResult.setCaseVersion(old.getCaseVersion());
        if (caseStepResults != null && caseStepResults.getCaseStepResultList() != null && caseStepResults.getCaseStepResultList().size() > 0) {
            qualityTestResult.setCaseStepresults(mergeResult(caseStepResults
                    .getCaseStepResultList()));
            String result = testResult(caseStepResults
                    .getCaseStepResultList());
            qualityTestResult.setCaseResult(result);
            testCase.setCaseLastRunResult(result);
            mergeRun(run, result);
            if ("3".equals(result)) {
                testCase.setCaseStatus("2");
                if (run != null) {
                    run.setTestRunStatus("2");
                }
            } else {
                testCase.setCaseStatus("1");
                if (run != null) {
                    run.setTestRunStatus("1");
                }
            }
        }
        if (!StringUtil.isBlank(resultType)) {
            String result = "pass".equals(resultType) ? "1" : "2";
            qualityTestResult.setCaseResult(result);
            testCase.setCaseLastRunResult(result);
            mergeRun(run, result);
        }
        testCase.setCaseLastRunDate(new Date());
        if (run != null) {
            run.setTestRunLastRunner(userUtils.getUserId());
            qualityTestResult.setTestresultRun(run.getTestRunId());
            testRunService.updateTestRun(run);
        }
        testCase.setCaseLastRunner(userUtils.getUserId());
        testCaseService.updateTestCase(testCase);
        testResultService.addTestResult(qualityTestResult);
        storyJudge(old.getStoryId());
        LogUtil.logWithComment(LogUtil.LogOperateObject.CASE,
                LogUtil.LogAction.RUN,
                String.valueOf(old.getCaseId()),
                userUtils.getUserId(),
                String.valueOf(old.getProductId()),
                null,
                old,
                testCase,
                null
        );
        if (run != null)
            return "redirect:" + adminPath + "/quality/version/taskToCase?testversionId=" + run.getTaskId() + "&currentPageId=5&status=tverallcase";
        return "redirect:" + adminPath + "/quality/testCase";
    }

    @RequestMapping("group")
    public String group(QualityTestCase qualityTestCase, Integer testtaskId, Model model) {
        Map<String, ProductStory> storyMap = new HashMap<String, ProductStory>();
        Map<String, List<QualityTestCase>> casesMap = new HashMap<String, List<QualityTestCase>>();
        Map<String, List<QualityTestRun>> runsMap = new HashMap<String, List<QualityTestRun>>();
        if (testtaskId != null) {
            QualityTestRun run = new QualityTestRun();
            run.setTaskId(testtaskId);
            List<QualityTestRun> runs = testRunService.findTestRunList(run);
            for (QualityTestRun run1 : runs) {
                if (!storyMap.containsKey(run1.getStoryId())) {
                    ProductStory story = storyService.findStory(run1.getStoryId());
                    if (story != null) {
                        storyMap.put(String.valueOf(story.getStoryId()), story);
                    }
                }
                if (!runsMap.containsKey(run1.getStoryId())) {
                    runsMap.put(String.valueOf(run1.getStoryId()), new ArrayList<QualityTestRun>());
                    runsMap.get(String.valueOf(run1.getStoryId())).add(run1);
                } else {
                    runsMap.get(String.valueOf(run1.getStoryId())).add(run1);
                }
            }
            model.addAttribute("runsMap", runsMap);
        } else {
            List<QualityTestCase> cases = testCaseService.findTestCaseList(qualityTestCase);
            for (QualityTestCase testCase : cases) {
                if (!storyMap.containsKey(String.valueOf(testCase.getStoryId()))) {
                    ProductStory story = storyService.findStory(testCase.getStoryId());
                    if (story != null) {
                        storyMap.put(String.valueOf(story.getStoryId()), story);
                    }
                }
                if (!casesMap.containsKey(String.valueOf(testCase.getStoryId()))) {
                    casesMap.put(String.valueOf(testCase.getStoryId()), new ArrayList<QualityTestCase>());
                    casesMap.get(String.valueOf(testCase.getStoryId())).add(testCase);
                } else {
                    casesMap.get(String.valueOf(testCase.getStoryId())).add(testCase);
                }
            }
            model.addAttribute("casesMap", casesMap);
        }

        model.addAttribute("storyMap", storyMap);
        return "/quality/ajax/case/group.pagelet";
    }

    // 预留，需要新增一个页面
    @RequestMapping("/batchExecution")
    public String batchExecution() {
        return "";
    }

    @RequiresPermissions("tresult")
    @RequestMapping("/result")
    public String result(Integer caseId, Model model) {
        execution(caseId, null, model);
        return "/quality/modal/case/result.pagelet";
    }

    @ResponseBody
    @RequestMapping("addComment")
    public Map recordComment(String comment, int caseId) {
        QualityTestCase testCase = testCaseService.testCase(caseId);
        LogUtil.logWithComment(LogUtil.LogOperateObject.CASE
                , LogUtil.LogAction.COMMENTED
                , String.valueOf(caseId)
                , userUtils.getUserId()
                , String.valueOf(testCase.getProductId())
                , null
                , null
                , null
                , comment);
        Map<String, String> result = new HashMap<String, String>();
        result.put("status", "success");
        return result;
    }

    @RequiresPermissions("teditioncase")
    @RequestMapping("/edit")
    public String edit(Integer caseId, Model model,@CookieValue(value=ProductUtils.COOKIE_PRODUCT_ID)String currentProductId) {
        QualityTestCase testCase = testCaseService.testCase(caseId);

        if(testCase.getProductId()!=Integer.parseInt(currentProductId))
        {
            return "redirect:"+adminPath+"/quality/testCase";
        }

        QualityCaseStep step = new QualityCaseStep();
        step.setCaseId(caseId);
        step.setCaseVersion(testCase.getCaseVersion());
        List<QualityCaseStep> stepList = caseStepService.findCaseStepList(step);

        model.addAttribute("testCase", testCase);
        model.addAttribute("stepList", stepList);

        SystemProfile systemProfile = new SystemProfile();
        systemProfile.setFileObjectId(caseId);
        systemProfile.setFileObjectType(ProfileType.TESTCASE.getType());
        List<SystemProfile> fileList = profileService.findSystemProfile(systemProfile);
        model.addAttribute("fileList", fileList);

        return "/quality/operate/case/editioncase.page";
    }

    @RequestMapping("/find/{forward}")
    public String findById(@PathVariable(value = "forward") String forward, Integer caseId, Model model) {
        QualityTestCase testCase = testCaseService.testCase(caseId);
        model.addAttribute("testCase", testCase);
        if ("editRight".equals(forward)) {
            return "/quality/rightinfo/case/editioncasepaging.pagelet";
        }
        return "";
    }

    @RequiresPermissions("tproposecase")
    @RequestMapping("/copy")
    public String copy(Integer caseId, Model model,@CookieValue(value=ProductUtils.COOKIE_PRODUCT_ID)String currentProductId) {
        QualityTestCase testCase = testCaseService.testCase(caseId);

        if(testCase.getProductId()!=Integer.parseInt(currentProductId))
        {
            return "redirect:"+adminPath+"/quality/testCase";
        }

        QualityCaseStep step = new QualityCaseStep();
        step.setCaseId(caseId);
        step.setCaseVersion(testCase.getCaseVersion());
        List<QualityCaseStep> stepList = caseStepService.findCaseStepList(step);

        model.addAttribute("stepList", stepList);
        model.addAttribute("testCase", testCase);
        return "/quality/operate/case/copyCase.page";
    }

    @RequiresPermissions("tcaseinfodelete")
    @ResponseBody
    @RequestMapping("/delete")
    public Map delete(Integer id) {
        testCaseService.deleteById(id);
        storyJudge(testCaseService.testCase(id).getStoryId());
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "success");
        map.put("info", "删除成功");
        return map;
    }

    @ResponseBody
    @RequestMapping("/batchDelete")
    public Map batchDelete(String ids) {
        String[] cId = ids.split(",");
        for (String id : cId) {
            QualityTestCase testCase = new QualityTestCase();
            testCase.setCaseId(Integer.parseInt(id));
            testCase.setDeleted(1);
            testCaseService.updateTestCase(testCase);
            storyJudge(testCase.getStoryId());
        }
        return resultMap(true, "删除成功");
    }

    @ResponseBody
    @RequestMapping("/batchDeleteAjax")
    public int[] batchDelete(@RequestBody QualityTestCase[] cases) {

        List<QualityTestCase> qualityTestCase = new ArrayList<QualityTestCase>();
        if (cases != null && cases.length > 0) {
            qualityTestCase = Arrays.asList(cases);
        }
        return testCaseService.batchDeleteTestCase(qualityTestCase);
    }

    @ResponseBody
    @RequestMapping("/ajax/story")
    public List<ProductStory> getStory(ProductStory productStory) {
        if (productStory.getProductId() < 1) {
            return new ArrayList<ProductStory>();
        }
        if (productStory.getModuleId() == 0) {
            productStory.setModuleId(null);
        }
        return storyService.findStoryListByOrder(productStory, null, null);
    }

    @ResponseBody
    @RequestMapping("/ajax/module")
    public List<SystemModule> getModule(SystemModule systemModule) {
        if (!(systemModule.getModuleRoot() > 0)) return new ArrayList<SystemModule>();
        systemModule.setModuleType("story");
        List<SystemModule> result = moduleService.findModuleList(systemModule);
        for (SystemModule module : result) {
            module.setModuleName(ModuleUtil.getPath(module.getModuleId(), "/", null, false));
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

    @RequiresPermissions("tvertobug")
    @RequestMapping("/chooseToBug")
    public String chooseTobug(Integer runId, Integer caseId, Integer caseVersion, Model model) {
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
        Map<String, List<QualityCaseStep>> caseSteps = new HashMap<String, List<QualityCaseStep>>();
        if (qualityTestResults.size() > 0) {
            caseSteps.put(String.valueOf(qualityTestResults.get(0).getTestResultId()), stepList);
            caseStepResults = resolveResult(qualityTestResults.get(0));
        }
        model.addAttribute("caseId", caseId);
        model.addAttribute("stepList", stepList);
        model.addAttribute("caseSteps", caseSteps);
        model.addAttribute("stepResults", caseStepResults);
        return "/quality/modal/case/caseToBug.pagelet";
    }

    private String testResult(List<CaseStepResult> caseStepResults) {
        if (caseStepResults == null || caseStepResults.size() == 0) return "1";
        String result = "1";
        for (CaseStepResult c : caseStepResults) {
            if ("2".equals(c.getResult())) {
                result = c.getResult();
            } else if ("3".equals(c.getResult())) {
                result = "2".equals(result) ? "2" : "3";
            } else if ("0".equals(c.getResult())) {
                result = "1".equals(result) ? "0" : result;
            }
        }
        return result;
    }

    private List<CaseStepResult> resolveResult(
            QualityTestResult qualityTestResult) {
        if (StringUtil.isBlank(qualityTestResult.getCaseStepresults())) return new ArrayList<CaseStepResult>();
        List<CaseStepResult> caseStepResults = new ArrayList<CaseStepResult>();
        String[] stepResults = qualityTestResult.getCaseStepresults()
                .split(",");
        for (String result : stepResults) {
            CaseStepResult caseStepResult = new CaseStepResult();
            int i = result.indexOf("{");
            int j = result.indexOf("}");
            String[] values = result.substring(i + 1, j).split(";");
            for (String value : values) {
                String[] r = value.split(":");
                if ("result".endsWith(r[0])) {
                    caseStepResult.setResult(r.length > 1 ? r[1] : "");
                } else {
                    caseStepResult.setReal(r.length > 1 ? r[1] : "");
                }
            }
            caseStepResults.add(caseStepResult);
        }
        return caseStepResults;
    }

    private void insertStep(String[] steps, String[] expects,
                            QualityTestCase tcase) {
        if (steps == null || steps.length < 1 || StringUtil.isBlank(steps[0])) return;
        List<QualityCaseStep> qualityCaseSteps = new ArrayList<QualityCaseStep>();
        for (int i = 0; i < steps.length; i++) {
            QualityCaseStep qualityCaseStep = new QualityCaseStep();
            qualityCaseStep.setCaseId(tcase.getCaseId());
            qualityCaseStep.setCaseVersion(tcase.getCaseVersion());
            qualityCaseStep.setCaseStepDesc(steps[i]);
            qualityCaseStep.setCaseStepExpect(expects[i]);
            qualityCaseSteps.add(qualityCaseStep);
        }
        for (QualityCaseStep step : qualityCaseSteps) {
            caseStepService.addCaseStep(step);
        }
    }


    @RequestMapping("/case/viewInfo")
    public String viewInfo(Integer id, Integer version, Model model, Integer no, HttpServletRequest request,
                           @CookieValue(value=ProductUtils.COOKIE_PRODUCT_ID)String currentProductId) {
        QualityTestCase testCase = null;
        if (no != null) {
            String result = CookieUtils.getCookie(request, ProductUtils.COOKIE_PRODUCT_ID);
            if (result == null) {
                return notFoundView();
            }
            Integer cookieProductId = Integer.parseInt(result);
            testCase = new QualityTestCase();
            testCase.setProductId(cookieProductId);
            testCase.setNo(no);
            List<QualityTestCase> caseList = testCaseService.findTestCaseList(testCase);
            if (caseList.size() == 0) {
                return notFoundView();
            }
            testCase = caseList.get(0);
            id = testCase.getCaseId();
        }
        if (testCase == null || testCase.getCaseId() == null) {
            testCase = testCaseService.testCase(id);
        }
        QualityCaseStep step = new QualityCaseStep();
        step.setCaseId(id);
        if (version != null && version > 0) {
            step.setCaseVersion(version);
        } else {
            step.setCaseVersion(testCase.getCaseVersion());
        }

        if(testCase.getProductId()!=Integer.parseInt(currentProductId))
        {
            return "redirect:"+adminPath+"/quality/testCase";
        }

        List<QualityCaseStep> stepList = caseStepService.findCaseStepList(step);
        SystemProfile systemProfile = new SystemProfile();
        systemProfile.setFileObjectType(ProfileType.TESTCASE.toString());
        systemProfile.setFileDeleted("0");
        systemProfile.setFileObjectId(testCase.getCaseId());
        List<SystemProfile> list = profileService.findSystemProfile(systemProfile);
        model.addAttribute("file", list);
        model.addAttribute("testCase", testCase);
        model.addAttribute("stepList", stepList);
        return "/quality/operate/case/caseInfo.page";
    }

    @RequestMapping("/case/rightInfo")
    public String rightInfo(Integer caseId, Model model) {
        QualityTestCase testCase = testCaseService.testCase(caseId);
        QualityBug bug = new QualityBug();
        bug.setDeleted(0);
        bug.setBugFromCase(caseId);
        List<QualityBug> bugList = bugService.findBugList(bug);
        model.addAttribute("bugFromCase", bugList);
        model.addAttribute("testCase", testCase);
        return "/quality/rightinfo/case/caseEditInfo.pagelet";
    }

    public boolean isCaseModify(String[] step, String[] expect, List<QualityCaseStep> steps) {
        if (step == null) {
            return (steps.size() < 1) ? false : true;
        } else if (step.length > 0 && step.length == steps.size()) {
            for (int i = 0; i < step.length; i++) {
                QualityCaseStep caseStep = steps.get(i);
                if (!StringUtil.equals(caseStep.getCaseStepDesc(), step[i]) ||
                        !StringUtil.equals(caseStep.getCaseStepExpect(), expect[i])) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }

    private void mergeRun(QualityTestRun run, String result) {
        if (run != null) {
            boolean haveNull = false;
            boolean isHaveBlock = false;
            run.setTestRunLastRunDate(new Date());
            run.setTestRunLastRunResult(result);
            QualityTestRun testRun = new QualityTestRun();
            testRun.setTaskId(run.getTaskId());
            List<QualityTestRun> testRuns = testRunService.findTestRunList(testRun);
            for (QualityTestRun run1 : testRuns) {
                if (!run1.getTestRunId().equals(run.getTestRunId())) {
                    if (StringUtil.isBlank(run1.getTestRunLastRunResult())) {
                        haveNull = true;
                    } else {
                        isHaveBlock = "3".equals(run1.getTestRunLastRunResult());
                    }

                }
            }
            isHaveBlock = "3".equals(run.getTestRunLastRunResult());
            QualityTestTask testTask = testTaskService.findTestTaskById(run.getTaskId());
            if (haveNull) {
                testTask.setTesttaskStatus("2");
            } else {
                if (isHaveBlock) {
                    testTask.setTesttaskStatus("4");
                } else {
                    testTask.setTesttaskStatus("3");
                }
            }
            testTaskService.updateTestTask(testTask);
        }
    }

    @RequestMapping("caseVersion")
    public String caseVersion() {
        return "/quality/modal/case/allVersion.pagelet";
    }

    @RequestMapping("caseVersionData")
    public String caseVersionData(Integer caseId,
                                  Model model,
                                  Integer start,
                                  Integer limit) {
        QualityTestCase testCase = testCaseService.testCase(caseId);
        Integer maxCaseVersion = caseStepService.getCaseMaxVersion(caseId);
        if (maxCaseVersion == null) {
            maxCaseVersion = 1;
        }
        Map<String, List<QualityCaseStep>> versionStep = new HashMap<String, List<QualityCaseStep>>();
        for (Integer i = maxCaseVersion > (start + limit) ? (start + limit) : maxCaseVersion; i > start; i--) {
            QualityCaseStep step = new QualityCaseStep();
            step.setCaseId(caseId);
            step.setCaseVersion(i);
            List<QualityCaseStep> steps = caseStepService.findCaseStepList(step);
            versionStep.put(i.toString(), steps);
        }
        model.addAttribute("case", testCase);
        model.addAttribute("stepMap", versionStep);
        model.addAttribute("start", start);
        model.addAttribute("maxVersion", maxCaseVersion);
        model.addAttribute("end", maxCaseVersion > (start + limit) ? (start + limit) : maxCaseVersion);
        return "/quality/data/case/allVersionData.pagelet";
    }

    @RequestMapping("versionRollback")
    public String caseVersionRollBack(Integer caseId, Integer caseVersion) {
        QualityTestCase testCase = testCaseService.testCase(caseId);
        testCase.setCaseVersion(caseVersion);
        testCaseService.updateTestCase(testCase);
        return "redirect:/a/quality/testCase/case/viewInfo?id=" + caseId;
    }

    private void storyJudge(Integer storyId) {
        if (storyId != null) {
            ProductStory story = storyService.findStory(storyId);
            QualityTestCase testCase = new QualityTestCase();
            testCase.setStoryId(storyId);
            testCase.setDeleted(0);
            List<QualityTestCase> testCaseList = testCaseService.findTestCaseList(testCase);
            boolean isDone = true;
            for (QualityTestCase testCase1 : testCaseList) {
                if (!QualityTestCase.RESULT_PASS.equals(testCase1.getCaseLastRunResult())) {
                    isDone = false;
                }
            }
            if (isDone) {
                if (!ProductStory.STAGE_IS_TESTED.equals(story.getStoryStage())) {
                    story.setStoryStage(ProductStory.STAGE_IS_TESTED);
                    storyService.updateStory(story);
                }
            } else {
                if (!ProductStory.STAGE_IS_TESTING.equals(story.getStoryStage())) {
                    story.setStoryStage(ProductStory.STAGE_IS_TESTING);
                    storyService.updateStory(story);
                }
            }
        }
    }
}
