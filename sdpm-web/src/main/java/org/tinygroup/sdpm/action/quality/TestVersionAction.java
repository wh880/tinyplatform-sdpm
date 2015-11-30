package org.tinygroup.sdpm.action.quality;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.ComplexSearch.SqlUtil;
import org.tinygroup.sdpm.common.web.BaseController;
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
import org.tinygroup.sdpm.util.*;
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
    public String form(HttpServletRequest request, String get, Model model) {
        String queryString = request.getQueryString();
        if (queryString != null && !queryString.contains("status")) {
            if (!queryString.contains("currentPageId")) queryString = queryString + "&currentPageId=5";
            return "redirect:/a/quality/version?status=tvernotest&" + queryString;
        }
        if (StringUtil.isBlank(queryString)) {
            return "redirect:/a/quality/version?status=tvernotest&currentPageId=5";
        }
        return "/testManagement/page/version.page";
    }

    @RequestMapping("/findPager")
    public String findPager(@CookieValue Integer qualityProductId, Integer start, Integer limit, SearchInfos infos, String groupOperate, String order, String ordertype, String status, QualityTestTask testtask, Model model, HttpServletRequest request) {
        testtask.setDeleted(0);
        boolean asc = true;
        if ("desc".equals(ordertype)) {
            asc = false;
        }
        String conditions = mergeCondition(getStatusCondition(status), SqlUtil.toSql(infos.getInfos(), groupOperate));
        testtask.setProductId(qualityProductId);
        Pager<QualityTestTask> verpager = testTaskService.findTestTaskPager(start, limit, testtask, conditions, order, asc);
        model.addAttribute("verPager", verpager);
        return "/testManagement/data/versionData.pagelet";
    }

    @RequestMapping("/project/findPager")
    public String findPager(HttpServletResponse response, HttpServletRequest request,
                            Integer start, Integer limit, String order, String ordertype, Model model) {
        Integer projectId = ProjectUtils.getCurrentProjectId(request, response);
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
        Pager<QualityTestTask> versionPager = testTaskService.findTestTaskPager(start, limit, testTask, null, order, asc);
        model.addAttribute("versionPager", versionPager);
        return "project/test/tableData.pagelet";
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
                    UserUtils.getUserId(),
                    String.valueOf(testtask.getProductId()),
                    String.valueOf(testtask.getProjectId()),
                    null,
                    null,
                    null
            );
        } else {
            QualityTestTask testTask = testTaskService.findById(testtask.getTestversionId());
            testTaskService.updateTestTask(testtask);
            LogUtil.logWithComment(LogUtil.LogOperateObject.TESTTASK,
                    LogUtil.LogAction.EDITED,
                    String.valueOf(testtask.getTestversionId()),
                    UserUtils.getUserId(),
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
    public String add(@CookieValue(value = "qualityProductId", defaultValue = "0") Integer qualityProductId, Integer buildId, Model model, HttpServletRequest request, HttpServletResponse response) {
        List<Project> projects = projectService.findProjectList(null, null, null);
        ProjectBuild build = new ProjectBuild();
        if (buildId != null && buildId > 0) {
            int productId = buildService.findBuild(buildId).getBuildProduct();
            build.setBuildProduct(productId);
            CookieUtils.setCookie(response, "qualityProductId", String.valueOf(productId));
        } else {
            build.setBuildProduct(qualityProductId);
        }

        List<ProjectBuild> builds = buildService.findListBuild(build);
        List<OrgUser> users = userService.findUserList(null);
        model.addAttribute("projectList", projects);
        model.addAttribute("buildList", builds);
        model.addAttribute("userList", users);
        return "/testManagement/page/proposeversion.page";
    }

    @RequestMapping("/versionInfo")
    public String versionInfo(Integer testversionId,
                              Integer no,
                              HttpServletRequest request,
                              Model model) {
        QualityTestTask testTask = null;
        if (no != null) {
            Integer qualityProductId = Integer.parseInt(CookieUtils.getCookie(request, "qualityProductId"));
            if (qualityProductId == null) {
                return notFoundView();
            }
            testTask = new QualityTestTask();
            testTask.setProductId(qualityProductId);
            testTask.setNo(no);
            List<QualityTestTask> testTaskList = testTaskService.findTestTaskList(testTask);
            if (testTaskList.size() == 0) {
                return notFoundView();
            }
            testTask = testTaskList.get(0);
        }
        if (testTask == null || testTask.getTestversionId() == null) {
            testTask = testTaskService.findById(testversionId);
        }
        model.addAttribute("testTask", testTask);
        return "/testManagement/page/versionSituation.page";
    }

    @RequiresPermissions(value = {"tversionSituation", "tverInfo"}, logical = Logical.OR)
    @RequestMapping("/versionRightInfo")
    public String versionRightInfo(Integer testversionId, Model model) {
        QualityTestTask testTask = testTaskService.findById(testversionId);
        model.addAttribute("testTask", testTask);
        return "/testManagement/page/tabledemo/versionRightInfo.pagelet";
    }

    @ResponseBody
    @RequestMapping("/makeLink")
    public Map makeLink(Integer testversionId, Integer[] ids, Integer[] ves) {

        for (int i = 0; i < ids.length; i++) {
            QualityTestRun run = new QualityTestRun();
            run.setCaseId(ids[i]);
            run.setCaseVersion(ves[i]);
            run.setTestRunStatus("1");
            run.setTaskId(testversionId);
            testRunService.add(run);
        }
        Map<String, String> result = new HashMap<String, String>();
        result.put("status", "y");
        return result;
    }

    @RequiresPermissions("tversionLink")
    @RequestMapping("/linkCase")
    public String linkCase(int testversionId, Model model) {
        return "/testManagement/page/versionLink.page";
    }

    @RequestMapping("/link")
    public String link(@CookieValue(value = "qualityProductId", defaultValue = "0") String cookieProductId, Integer testversionId, Integer start, Integer limit, SearchInfos infos, String groupOperate, String order, String ordertype, Model model) {
        QualityTestRun run = new QualityTestRun();
        run.setTaskId(testversionId);
        List<QualityTestRun> runs = testRunService.findTestRunList(run);
        String conditions = mergeCondition(mergeCondition(runs, false), SqlUtil.toSql(infos.getInfos(), groupOperate));
        QualityTestCase testCase = new QualityTestCase();
        if (Integer.parseInt(cookieProductId) > 0) {
            testCase.setProductId(Integer.parseInt(cookieProductId));
        }
        testCase.setDeleted(0);
        Pager<QualityTestCase> casePager = testCaseService.findTestCasePager(start, limit, testCase, conditions, null, null, order, "asc".equals(ordertype) ? true : false);
        model.addAttribute("casePager", casePager);
        return "/testManagement/data/link.pagelet";
    }

    @RequiresPermissions("tversionedit")
    @RequestMapping("/toEdit")
    public String edit(@CookieValue Integer qualityProductId, Integer testversionId, Model model, HttpServletRequest request) {
        QualityTestTask testTask = testTaskService.findById(testversionId);
        List<ProjectProduct> projectProducts = projectProductService.findProjects(qualityProductId);
        List<Integer> ids = new ArrayList<Integer>();
        for (ProjectProduct projectProduct : projectProducts) {
            ids.add(projectProduct.getProjectId());
        }
        List<Project> projects = ids.size() > 0 ? projectService.findByProjectList(ids) : new ArrayList<Project>();
        model.addAttribute("testTask", testTask);
        model.addAttribute("projectList", projects);
        return "/testManagement/page/tabledemo/editionversion.page";
    }

    @ResponseBody
    @RequestMapping("/ajax/build")
    public List<ProjectBuild> getBuild(ProjectBuild projectBuild) {
        if (projectBuild.getBuildProduct() < 1 || projectBuild.getBuildProduct() == null) {
            return new ArrayList<ProjectBuild>();
        }
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
        testRunService.delete(runId);
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
    public String taskToCase(Integer testversionId, String moduleId, Model model, HttpServletRequest request) {
        QualityTestTask testTask = testTaskService.findById(testversionId);
        List<ProjectTeam> projectTeams = teamService.findTeamByProjectId(testTask.getProjectId());
        String[] ids = new String[projectTeams.size()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = projectTeams.get(i).getTeamUserId();
        }
        List<OrgUser> orgUsers = userService.findUserListByIds(ids);
//        String queryString = request.getQueryString();
        if (StringUtil.isBlank(moduleId)) {
            request.getSession().removeAttribute("taskToCaseModuleId");
        } else {
            request.getSession().setAttribute("taskToCaseModuleId", moduleId);
        }

        model.addAttribute("testTask", testTask);
        model.addAttribute("userList", orgUsers);
        return "/testManagement/page/tabledemo/verCase.page";
    }

    @RequestMapping("/taskToCaseData")
    public String taskToCaseData(Integer testversionId, Integer start, String status, Integer moduleId, Integer limit, SearchInfos infos, String groupOperate, String order, String ordertype, Model model, HttpServletRequest request) {
        QualityTestRun run = new QualityTestRun();
        run.setTaskId(testversionId);
        String conditions = mergeCondition(getTaskToCaseCondition(status), SqlUtil.toSql(infos.getInfos(), groupOperate));
        if (moduleId != null) {
            String moduleCondition = ModuleUtil.getCondition(moduleId);
            conditions = mergeCondition(conditions, StringUtil.isBlank(moduleCondition) ? "" : (" module_id " + moduleCondition));
        }
        Pager<QualityTestRun> runsPager = testRunService.findTestRunPager(start, limit, run, conditions, order, "asc".equals(ordertype) ? true : false);
        //增加case删除位判断
        model.addAttribute("runsPager", runsPager);
        return "/testManagement/data/verCaseData.pagelet";
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
        return "/testManagement/page/versionEnd.pagelet";
    }

    @ResponseBody
    @RequestMapping("/taskEnd")
    public Map taskEnd(QualityTestTask testTask, String actionComment) {
        QualityTestTask qualityTestTask = testTaskService.findById(testTask.getTestversionId());
        testTask.setTesttaskStatus("3");
        testTaskService.updateTestTask(testTask);
        LogUtil.logWithComment(LogUtil.LogOperateObject.TESTTASK,
                LogUtil.LogAction.CLOSED,
                String.valueOf(qualityTestTask.getTestversionId()),
                UserUtils.getUserId(),
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

    private String mergeCondition(List<QualityTestRun> runs, boolean in) {
        if (!(runs.size() > 0)) return "";
        StringBuffer sb = new StringBuffer();
        if (in) {
            sb.append(" case_id in (");
        } else {
            sb.append(" case_id not in (");
        }
        for (QualityTestRun run : runs) {
            if (!sb.toString().endsWith("(")) sb.append(",");
            sb.append(run.getCaseId());
        }
        sb.append(")");
        return sb.toString();
    }

    private String getStatusCondition(String status) {
        if (StringUtil.isBlank(status)) return "";
        if ("tvertest".equals(status)) {
            return " testtask_status = '3' ";
        } else {
            return " testtask_status <> '3' ";
        }
    }

    private String getTaskToCaseCondition(String status) {
        if (StringUtil.isBlank(status)) return "";
        if ("tverallcase".equals(status)) {
            return "";
        } else {
            return " test_run_assigned_to = '" + UserUtils.getUserId() + "' ";
        }
    }

    private String mergeCondition(String condition1, String condition2) {
        String operate = "";
        if (!StringUtil.isBlank(condition1) && !StringUtil.isBlank(condition2)) {
            operate = " and ";
        }
        return (StringUtil.isBlank(condition1) ? "" : condition1) + operate + (StringUtil.isBlank(condition2) ? "" : condition2);
    }
}
