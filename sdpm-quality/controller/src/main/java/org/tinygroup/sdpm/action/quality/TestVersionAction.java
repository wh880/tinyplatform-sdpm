package org.tinygroup.sdpm.action.quality;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.dao.condition.ConditionUtils;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.project.dao.pojo.ProjectProduct;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.inter.BuildService;
import org.tinygroup.sdpm.project.service.inter.ProjectProductService;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.project.service.inter.TeamService;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestRun;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestTask;
import org.tinygroup.sdpm.quality.service.inter.TestCaseService;
import org.tinygroup.sdpm.quality.service.inter.TestRunService;
import org.tinygroup.sdpm.quality.service.inter.TestTaskService;
import org.tinygroup.sdpm.util.CookieUtils;
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.ProductUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenpeng15668 on 2015-9-24
 */
@Controller
@RequestMapping("/a/quality/version")
public class TestVersionAction extends BaseController {

    @Autowired
    private TestTaskService testTaskService;
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

    @ModelAttribute
    public void init(Model model) {
        initSearchBar(model, "测试版本");
    }

    @RequestMapping("")
    public String form(HttpServletRequest request) {
        String queryString = request.getQueryString();
        if (queryString != null && !queryString.contains("status")) {
            if (!queryString.contains("currentPageId")) queryString = queryString + "&currentPageId=5";
            return "redirect:/a/quality/version?status=tvernotest&" + queryString;
        }
        if (StringUtil.isBlank(queryString)) {
            return "redirect:/a/quality/version?status=tvernotest";
        }
        return "/quality/index/version/version.page";
    }

    @RequestMapping("/findPager")
    public String findPager(@CookieValue(ProductUtils.COOKIE_PRODUCT_ID) Integer cookieProductId,
                            Integer start,
                            Integer limit,
                            SearchInfos infos,
                            String groupOperate,
                            String order,
                            String ordertype,
                            String status,
                            QualityTestTask testtask,
                            Model model) {
        ConditionCarrier carrier = new ConditionCarrier();
        testtask.setDeleted(0);
        boolean asc = true;
        if ("desc".equals(ordertype)) {
            asc = false;
        }
        getStatusCondition(status, carrier);
        carrier.putSearch("testTaskSearch", infos, groupOperate);
        testtask.setProductId(cookieProductId);
        Pager<QualityTestTask> verpager = testTaskService.findTestTaskPagerWithConditionCarrier(start, limit, testtask, carrier, order, asc);
        model.addAttribute("verPager", verpager);
        return "/quality/data/version/versionData.pagelet";
    }

    @RequestMapping("/project/findPager")
    public String findPager(HttpServletResponse response, HttpServletRequest request,
                            Integer start, Integer limit, @RequestParam(required = false, defaultValue = "testversionId") String order,
                            @RequestParam(required = false, defaultValue = "desc") String ordertype, Model model) {
        Integer projectId = projectOperate.getCurrentProjectId(request, response);
        if (projectId == null) {
            return redirectProjectForm();
        }
        QualityTestTask testTask = new QualityTestTask();
        testTask.setDeleted(0);
        testTask.setProjectId(projectId);
        boolean asc = true;
        if ("desc".equals(ordertype)) {
            asc = false;
        }
        Pager<QualityTestTask> versionPager = testTaskService.findTestTaskPagerWithConditionCarrier(start, limit, testTask, null, order, asc);
        model.addAttribute("versionPager", versionPager);
        return "project/data/test/tableData.pagelet";
    }

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Map save(QualityTestTask testtask, Model model) {
        if (testtask.getTestversionId() == null) {
            testtask.setDeleted(0);
            testtask = testTaskService.addTestTask(testtask);
            LogUtil.logWithComment(LogUtil.LogOperateObject.TESTTASK,
                    LogUtil.LogAction.OPENED,
                    String.valueOf(testtask.getTestversionId()),
                    userUtils.getUserId(),
                    String.valueOf(testtask.getProductId()),
                    String.valueOf(testtask.getProjectId()),
                    null,
                    null,
                    null
            );
        } else {
            QualityTestTask testTask = testTaskService.findTestTaskById(testtask.getTestversionId());
            testTaskService.updateTestTask(testtask);
            LogUtil.logWithComment(LogUtil.LogOperateObject.TESTTASK,
                    LogUtil.LogAction.EDITED,
                    String.valueOf(testtask.getTestversionId()),
                    userUtils.getUserId(),
                    String.valueOf(testtask.getProductId()),
                    String.valueOf(testtask.getProjectId()),
                    testTask,
                    testtask,
                    null
            );
        }
        model.addAttribute("testtask", testtask);
        Map<String, String> result = new HashMap<String, String>();
        result.put("status", "y");
        return result;
    }

    @RequiresPermissions("tproposeversion")
    @RequestMapping("/add")
    public String add(@CookieValue(value = ProductUtils.COOKIE_PRODUCT_ID, defaultValue = "0") Integer cookieProductId,
                      Integer projectId, Integer buildId, Model model, HttpServletResponse response, HttpServletRequest request) {
        List<Project> projects = projectService.findProjectList(null, null, null);
        ProjectBuild build = new ProjectBuild();
        if (buildId != null && buildId > 0) {
            int productId = buildService.findBuild(buildId).getBuildProduct();
            build.setBuildProduct(productId);
            CookieUtils.setCookie(response, ProductUtils.COOKIE_PRODUCT_ID, String.valueOf(productId));
        } else {
            build.setBuildProduct(cookieProductId);
        }

        build.setBuildDeleted("0");//未删除状态

        List<ProjectBuild> builds = buildService.findListBuild(build);
        model.addAttribute("projectId", projectId);
        model.addAttribute("projectList", projects);
        model.addAttribute("buildList", builds);
        model.addAttribute("currentURL", request.getRequestURL().toString());
        return "/quality/operate/version/proposeversion.page";
    }

    @RequestMapping("/versionInfo")
    public String versionInfo(Integer testversionId,
                              Integer no,
                              HttpServletRequest request,
                              Model model,
                              @CookieValue(value=ProductUtils.COOKIE_PRODUCT_ID)String currentProductId) {
        QualityTestTask testTask = null;
        if (no != null) {
            String result = CookieUtils.getCookie(request, ProductUtils.COOKIE_PRODUCT_ID);
            if (StringUtil.isBlank(result)) {
                return notFoundView();
            }
            Integer cookieProductId = Integer.parseInt(result);
            testTask = new QualityTestTask();
            testTask.setProductId(cookieProductId);
            testTask.setNo(no);
            List<QualityTestTask> testTaskList = testTaskService.findTestTaskList(testTask);
            if (testTaskList.size() == 0) {
                return notFoundView();
            }
            testTask = testTaskList.get(0);
        }
        if (testTask == null || testTask.getTestversionId() == null) {
            testTask = testTaskService.findTestTaskById(testversionId);
        }

        if(testTask.getProductId()!=Integer.parseInt(currentProductId))
        {
            return "redirect:"+adminPath+"/quality/version";
        }

        model.addAttribute("testTask", testTask);
        return "/quality/operate/version/versionSituation.page";
    }

    @RequiresPermissions(value = {"tversionSituation", "tverInfo"}, logical = Logical.OR)
    @RequestMapping("/versionRightInfo")
    public String versionRightInfo(Integer testversionId, Model model) {
        QualityTestTask testTask = testTaskService.findTestTaskById(testversionId);
        model.addAttribute("testTask", testTask);
        return "/quality/rightinfo/version/versionRightInfo.pagelet";
    }

    @ResponseBody
    @RequestMapping("/makeLink")
    public Map makeLink(Integer testversionId, Integer[] ids, Integer[] ves) {
        QualityTestTask testTask = testTaskService.findTestTaskById(testversionId);
        List<QualityTestRun> list = testRunService.findTestRunByTestVersionId(testversionId);
        Map<Integer, QualityTestRun> qualityTestRunMap = new HashMap<Integer, QualityTestRun>();
        for (QualityTestRun qualityTestRun : list) {
            qualityTestRunMap.put(qualityTestRun.getCaseId(), qualityTestRun);
        }

        for (int i = 0; i < ids.length; i++) {
            if (!qualityTestRunMap.containsKey(ids[i])) {
                QualityTestRun run = new QualityTestRun();
                run.setCaseId(ids[i]);
                run.setCaseVersion(ves[i]);
                run.setTestRunStatus("1");
                run.setTaskId(testversionId);
                testRunService.addTestRun(run);
            }
        }

        if ("3".equals(testTask.getTesttaskStatus())) {
            testTask.setTesttaskStatus("1");
        }
        Map<String, String> result = new HashMap<String, String>();
        result.put("status", "y");
        return result;
    }

    @RequiresPermissions("tversionLink")
    @RequestMapping("/linkCase")
    public String linkCase() {
        return "/quality/operate/version/versionLink.page";
    }

    @RequestMapping("/link")
    public String link(@CookieValue(value = ProductUtils.COOKIE_PRODUCT_ID, defaultValue = "0") String cookieProductId,
                       Integer testversionId,
                       Integer start,
                       Integer limit,
                       SearchInfos infos,
                       String groupOperate,
                       String order,
                       String ordertype,
                       Model model) {
        ConditionCarrier carrier = new ConditionCarrier();
        QualityTestRun run = new QualityTestRun();
        run.setTaskId(testversionId);
        List<QualityTestRun> runs = testRunService.findTestRunList(run);
        mergeCondition(runs, false, carrier);
        carrier.putSearch("caseSearch", infos, groupOperate);
        QualityTestCase testCase = new QualityTestCase();
        if (Integer.parseInt(cookieProductId) > 0) {
            testCase.setProductId(Integer.parseInt(cookieProductId));
        }
        testCase.setDeleted(0);
        Pager<QualityTestCase> casePager = testCaseService.findTestCasePagerByConditionCarrier(start, limit, testCase, carrier, order, "asc".equals(ordertype) ? true : false);
        model.addAttribute("casePager", casePager);
        return "/quality/data/version/link.pagelet";
    }

    @RequiresPermissions("tversionedit")
    @RequestMapping("/toEdit")
    public String edit(@CookieValue(ProductUtils.COOKIE_PRODUCT_ID) Integer cookieProductId,
                       Integer testversionId, Model model,HttpServletRequest request,
                       @CookieValue(value=ProductUtils.COOKIE_PRODUCT_ID)String currentProductId) {
        QualityTestTask testTask = testTaskService.findTestTaskById(testversionId);

        if(testTask.getProductId()!=Integer.parseInt(currentProductId))
        {
            return "redirect:"+adminPath+"/quality/version";
        }

        List<ProjectProduct> projectProducts = projectProductService.findProjectByProductId(cookieProductId);
        List<Integer> ids = new ArrayList<Integer>();
        for (ProjectProduct projectProduct : projectProducts) {
            ids.add(projectProduct.getProjectId());
        }
        List<Project> projects = ids.size() > 0 ? projectService.findByProjectList(ids) : new ArrayList<Project>();
        model.addAttribute("testTask", testTask);
        model.addAttribute("projectList", projects);
        model.addAttribute("currentURL", request.getRequestURL().toString());
        return "/quality/operate/version/editionversion.page";
    }

    @ResponseBody
    @RequestMapping("/ajax/build")
    public List<ProjectBuild> getBuild(ProjectBuild projectBuild) {
        if (projectBuild.getBuildProduct() < 1 || projectBuild.getBuildProduct() == null) {
            return new ArrayList<ProjectBuild>();
        }
        projectBuild.setBuildDeleted("0");//未删除状态
        return buildService.findListBuild(projectBuild);
    }

    @ResponseBody
    @RequestMapping("/ajax/user")
    public List<OrgUser> getUser(ProjectTeam projectTeam) {
        List<OrgUser> orgUsers = userService.findTeamUserListByProjectId(projectTeam.getProjectId());
        return orgUsers;
    }

    @ResponseBody
    @RequestMapping("/ajax/delete")
    public Map delete(Integer testversionId) {
        QualityTestTask testTask = new QualityTestTask();
        testTask.setTestversionId(testversionId);
        testTask.setDeleted(1);
        testTaskService.updateTestTask(testTask);
        Map<String, String> result = new HashMap<String, String>();
        result.put("status", "y");
        return result;
    }

    @ResponseBody
    @RequestMapping("/ajax/deleteRun")
    public Map deleteRun(Integer runId) {
        testRunService.deleteTestRun(runId);
        Map<String, String> result = new HashMap<String, String>();
        result.put("status", "success");
        return result;
    }

    @ResponseBody
    @RequestMapping("/ajax/batchDelete")
    public Map batchDelete(String ids) {
        String[] tIds = null;
        if (!StringUtil.isBlank(ids)) {
            tIds = ids.split(",");
        }
        if (tIds != null && tIds.length > 0) {
            for (String id : tIds) {
                QualityTestTask testTask = new QualityTestTask();
                testTask.setTestversionId(Integer.parseInt(id));
                testTask.setDeleted(1);
                testTaskService.updateTestTask(testTask);
            }
        }
        Map<String, String> result = new HashMap<String, String>();
        result.put("status", "y");
        return result;
    }

    @RequiresPermissions(value = {"tvercase", "tverallcase", "tverassign"}, logical = Logical.OR)
    @RequestMapping("/taskToCase")
    public String taskToCase(Integer testversionId, String status, String moduleId, Model model, HttpServletRequest request) {
        QualityTestTask testTask = testTaskService.findTestTaskById(testversionId);
        List<ProjectTeam> projectTeams = teamService.findTeamByProjectId(testTask.getProjectId());
        String[] ids = new String[projectTeams.size()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = projectTeams.get(i).getTeamUserId();
        }
        List<OrgUser> orgUsers = userService.findUserListByIds(ids);
        if (StringUtil.isBlank(moduleId)) {
            request.getSession().removeAttribute("taskToCaseModuleId");
        } else {
            request.getSession().setAttribute("taskToCaseModuleId", moduleId);
        }

        model.addAttribute("testTask", testTask);
        model.addAttribute("userList", orgUsers);
        return "/quality/operate/version/verCase.page";
    }

    @RequestMapping("/taskToCaseData")
    public String taskToCaseData(Integer testversionId, Integer start, String status, Integer moduleId, Integer limit, SearchInfos infos, String groupOperate, String order, String ordertype, Model model, HttpServletRequest request) {
        QualityTestRun run = new QualityTestRun();
        ConditionCarrier carrier = new ConditionCarrier();
        run.setTaskId(testversionId);
        getTaskToCaseCondition(status, carrier);
        carrier.putSearch("caseSearch", infos, groupOperate);
        if (moduleId != null) {
            carrier.putModuleIn("moduleId", String.valueOf(moduleId));
        }
        Pager<QualityTestRun> runsPager = testRunService.findTestRunPager(start, limit, run, carrier, order, "asc".equals(ordertype) ? true : false);
        //增加case删除位判断
        model.addAttribute("runsPager", runsPager);
        return "/quality/data/version/verCaseData.pagelet";
    }

    @ResponseBody
    @RequestMapping("/ajax/assign")
    public Map assign(Integer[] ids, String userId) {
        for (int id : ids) {
            QualityTestRun run = new QualityTestRun();
            run.setTestRunId(id);
            run.setTestRunAssignedTo(userId);
            testRunService.updateTestRun(run);
        }
        Map<String, String> result = new HashMap<String, String>();
        result.put("status", "y");
        return result;
    }

    @RequiresPermissions("tverclose")
    @RequestMapping("/toTaskEnd")
    public String toTaskEnd() {
        return "/quality/modal/version/versionEnd.pagelet";
    }

    @ResponseBody
    @RequestMapping("/taskEnd")
    public Map taskEnd(QualityTestTask testTask, String actionComment) {
        QualityTestTask qualityTestTask = testTaskService.findTestTaskById(testTask.getTestversionId());
        testTask.setTesttaskStatus("3");
        testTaskService.updateTestTask(testTask);
        LogUtil.logWithComment(LogUtil.LogOperateObject.TESTTASK,
                LogUtil.LogAction.CLOSED,
                String.valueOf(qualityTestTask.getTestversionId()),
                userUtils.getUserId(),
                String.valueOf(qualityTestTask.getProductId()),
                String.valueOf(qualityTestTask.getProjectId()),
                qualityTestTask,
                testTask,
                actionComment
        );
        Map<String, String> result = new HashMap<String, String>();
        result.put("status", "y");
        return result;
    }

    private void mergeCondition(List<QualityTestRun> runs, boolean in, ConditionCarrier carrier) {
        if (runs.size() == 0) return;
        List<String> idList = new ArrayList<String>();
        for (QualityTestRun run : runs) {
            idList.add(String.valueOf(run.getCaseId()));
        }
        String[] ids = new String[idList.size()];
        if (in) {
            carrier.putIns("qualityCase.caseId", idList.toArray(ids));
        } else {
            carrier.putNotIns("qualityCase.caseId", idList.toArray(ids));
        }
    }

    private void getStatusCondition(String status, ConditionCarrier carrier) {
        if (StringUtil.isBlank(status)) return;
        if ("tvertest".equals(status)) {
            carrier.put("qualityTestTask.testtaskStatus",
                    ConditionUtils.Operate.EQ.getOperate(),
                    ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                    "3");
        } else {
            carrier.put("qualityTestTask.testtaskStatus",
                    ConditionUtils.Operate.NEQ.getOperate(),
                    ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                    "3");
        }
    }

    private void getTaskToCaseCondition(String status, ConditionCarrier carrier) {
        if (StringUtil.isBlank(status)) return;
        if ("tverallcase".equals(status)) {
            return;
        } else {
            carrier.put("qualityTestRun.testRunAssignedTo",
                    ConditionUtils.Operate.EQ.getOperate(),
                    ConditionUtils.CommonFieldType.FIELD_OPERATE.getCommonField(),
                    userUtils.getUserId());
        }
    }

    /**
     * 判断名称是否已存在
     *
     * @param param
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/judgeTestTaskNameExist")
    public Map judgeTestTaskNameExist(String param, String currentURL, HttpServletRequest request,String testTaskTitle)
    {
        if (param == null)
        {
            return resultMap(false, "请输入测试名称");
        }

        if(currentURL.contains("toEdit")&&param.equals(testTaskTitle))
        {
            return resultMap(true,"");
        }

        String productId = CookieUtils.getCookie(request, ProductUtils.COOKIE_PRODUCT_ID);
        String testTaskName = param;
        QualityTestTask task = new QualityTestTask();
        task.setTesttaskTitle(param);
        task.setProductId(Integer.parseInt(productId));
        task.setDeleted(0);//0表示未删除
        List<QualityTestTask> testTasks = testTaskService.findTestTaskList(task);

        if (testTasks.size() == 0)
        {
            return resultMap(true, "");
        }

        return  resultMap(false,"名称已存在");
    }
}