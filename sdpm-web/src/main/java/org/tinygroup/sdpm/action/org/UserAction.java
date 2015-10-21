package org.tinygroup.sdpm.action.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.common.util.DateUtils;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgDept;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.DeptService;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.StoryService;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;
import org.tinygroup.sdpm.project.service.inter.ProjectService;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.sdpm.project.service.inter.TeamService;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestCase;
import org.tinygroup.sdpm.quality.dao.pojo.QualityTestTask;
import org.tinygroup.sdpm.quality.service.inter.BugService;
import org.tinygroup.sdpm.quality.service.inter.TestCaseService;
import org.tinygroup.sdpm.quality.service.inter.TestTaskService;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.sdpm.system.service.inter.ActionService;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
@RequestMapping("/a/org/user")
public class UserAction extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private DeptService deptService;
    @Autowired
    private StoryService storyService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private BugService bugService;
    @Autowired
    private TestCaseService testCaseService;
    @Autowired
    private TestTaskService testTaskService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ActionService actionService;


    @RequestMapping("/form")
    public String form(String id, Model model) {
        if (id != null) {
            OrgUser user = userService.findUser(id);
            OrgDept dept = deptService.findDept(user.getOrgDeptId());
            model.addAttribute("user", user);
            model.addAttribute("dept", dept);
        }
        return "organization/user/addUser.page";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(OrgUser user, Model model, String password1, String password2) {
        if (StringUtil.isBlank(user.getOrgUserId())) {
            if (password1.equals(password2)) {
                user.setOrgUserPassword(password1);
                userService.addUser(user);
            } else {
                return "organization/user/addUser.page";
            }
        } else {
            userService.updateUser(user);
        }
        model.addAttribute("user", user);
        return "redirect:" + adminPath + "/org/user/list/";
    }

    @RequestMapping("/list")
    public String list(OrgUser orgUser, Model model) {
//        List<OrgUser> list = userService.findUserList(orgUser);
//        model.addAttribute("list", list);
        return "organization/user/user.page";
    }

    @RequestMapping("/delete/page")
    public String deleteData(String id, Model model) {
        model.addAttribute("id", id);
        return "organization/user/delect.pagelet";
    }

    @RequestMapping("/delete")
    public String delete(String id, String password) {
        OrgUser orgUser = userService.findUser(id);
        String password1 = orgUser.getOrgUserPassword();
        if (userService.validatePassword(password, password1)) {
            userService.deleteUser(id);
        } else {
            return "organization/user/delect.pagelet";
        }

        return "redirect:" + adminPath + "/org/user/list/";
    }

    @ResponseBody
    @RequestMapping(value = "/batchDelete")
    public Map batchDelete(String ids) {
        Map<String, String> map = new HashMap<String, String>();
        if (ids == null) {
            map.put("status", "n");
            map.put("info", "删除失败");
            return map;
        }
        List<OrgUser> list = new ArrayList<OrgUser>();
        for (String s : ids.split(",")) {
            OrgUser orgUser = new OrgUser();
            orgUser.setOrgUserId(s);
            orgUser.setOrgUserDeleted(OrgUser.DELETE_YES);
            list.add(orgUser);
        }
        userService.deleteBatchUser(list);
        map.put("status", "y");
        map.put("info", "删除成功");
        return map;
    }

    @RequestMapping("/list/data")
    public String listData(Integer orgDeptId, Integer start, Integer limit, OrgUser orgUser, Model model,
                           String groupOperate, SearchInfos searchInfos
    ) {
        if (orgDeptId == null || orgDeptId == -1) {
            orgUser.setOrgDeptId(null);
            Pager<OrgUser> pager = userService.findUserPager(start, limit, orgUser, groupOperate, searchInfos);
            model.addAttribute("pager", pager);
        } else {
            Pager<OrgUser> pager = userService.findUserByDeptId(start, limit, orgDeptId);
            model.addAttribute("pager", pager);
        }
        return "organization/user/userTableData.pagelet";
    }

    @RequestMapping("/show/profile")
    public String show(String id, OrgUser orgUser, Model model) {
        OrgUser user = userService.findUser(id);
        List<OrgUser> userList = userService.findUserList(orgUser);
        model.addAttribute("userList", userList);
        model.addAttribute("user", user);
        return "organization/user/profileAdmin.page";
    }

    @ResponseBody
    @RequestMapping("/userList")
    public List<OrgUser> findUser(OrgUser orgUser) {

        List<OrgUser> list = userService.findUserList(orgUser);

        return list;
    }

    @RequestMapping("/story")
    public String storyJump(OrgUser orgUser, Model model) {
        List<OrgUser> userList = userService.findUserList(orgUser);
        model.addAttribute("userList", userList);
        return "/organization/user/storyAdmin.page";
    }

    @RequestMapping("/story/search")
    public String storySearchAction(String id, int page, int pagesize, String choose, ProductStory story, String order, String ordertype, Model model, HttpServletRequest request) {

        if (choose.equals("6")) {
            story.setStoryClosedBy(id);
            Pager<ProductStory> p4 = storyService.findStoryPager(pagesize * (page - 1), pagesize, story, null, null, null, order, false);

            model.addAttribute("storyList", p4);

        } else if (choose.equals("4")) {
            story.setStoryOpenedBy(id);
            Pager<ProductStory> p2 = storyService.findStoryPager(pagesize * (page - 1), pagesize, story, null, null, null, order, false);
            model.addAttribute("storyList", p2);

        } else if (choose.equals("5")) {
            story.setStoryReviewedBy(id);
            Pager<ProductStory> p3 = storyService.findStoryPager(pagesize * (page - 1), pagesize, story, null, null, null, order, false);
            model.addAttribute("storyList", p3);

        } else {
            story.setStoryAssignedTo(id);
            Pager<ProductStory> p1 = storyService.findStoryPager(pagesize * (page - 1), pagesize, story, null, null, null, order, false);
            model.addAttribute("storyList", p1);

        }
        return "organization/user/userStoryTable.pagelet";
    }

    @RequestMapping("/task")
    public String taskJump(OrgUser orgUser, Model model) {
        List<OrgUser> userList = userService.findUserList(orgUser);
        model.addAttribute("userList", userList);
        return "/organization/user/taskAdmin.page";
    }

    @RequestMapping("/task/search")
    public String taskSearchAction(String id, Integer start, Integer limit, int page, int pagesize, String choose, ProjectTask task, String order, String ordertype, Model model, HttpServletRequest request) {
        ProjectTask task1 = new ProjectTask();
        if (choose.equals("7")) {
            task1.setTaskCanceledBy(id);
        } else if (choose.equals("4")) {
            task1.setTaskOpenBy(id);
        } else if (choose.equals("5")) {
            task1.setTaskFinishedBy(id);
        } else if (choose.equals("6")) {
            task1.setTaskClosedBy(id);
        } else {
            task1.setTaskAssignedTo(id);
        }
        Pager<ProjectTask> taskPager = taskService.findPagerTask(start, limit, task1, order, false, null, null);
        List<Integer> projectIdList = new ArrayList<Integer>();
        for (ProjectTask project : taskPager.getRecords()) {
            projectIdList.add(project.getTaskProject());
        }
        List<Project> projectList = projectService.findByProjectList(projectIdList);

        model.addAttribute("taskPager", taskPager);
        return "/organization/user/userTaskTable.pagelet";
    }

    @RequestMapping("/bug")
    public String bugJump(OrgUser orgUser, Model model) {
        List<OrgUser> userList = userService.findUserList(orgUser);
        model.addAttribute("userList", userList);
        return "/organization/user/bugAdmin.page";
    }

    @RequestMapping("/bug/search")
    public String bugSearchAction(String id, Integer start, Integer limit, int page, int pagesize, String choose, ProjectTask task, String order, String ordertype, Model model, HttpServletRequest request) {
        OrgUser user = userService.findUser(id);
        String account = user.getOrgUserAccount();
        QualityBug bug = new QualityBug();
        if (choose.equals("6")) {
            bug.setBugClosedBy(id);
        } else if (choose.equals("5")) {
            bug.setBugResolvedBy(id);
        } else if (choose.equals("4")) {
            bug.setBugOpenedBy(id);
        } else {
            bug.setBugAssignedTo(id);
        }
        Pager<QualityBug> bugPager = bugService.findBugListPager(limit * (page - 1), limit, null, bug, order, false);
        model.addAttribute("bugPager", bugPager);
        return "/organization/user/bugAdminTable.pagelet";
    }

    @RequestMapping("/testtask")
    public String testTaskJump(OrgUser orgUser, Model model) {
        List<OrgUser> userList = userService.findUserList(orgUser);
        model.addAttribute("userList", userList);
        return "/organization/user/testtaskAdmin.page";
    }

    @RequestMapping("/testtask/search")
    public String testTaskSearchAction(String id, Integer start, Integer limit, int page, int pagesize, String choose, QualityTestTask testTask, String order, String ordertype, Model model, HttpServletRequest request) {
        OrgUser user = userService.findUser(id);
        String account = user.getOrgUserAccount();
        QualityTestTask testTask1 = new QualityTestTask();
        testTask1.setTesttaskOwner(account);
        Pager<QualityTestTask> testTaskPager = testTaskService.findTestTaskPager(start, limit, testTask1, order, false);
        model.addAttribute("testTaskPager", testTaskPager);

        return "/organization/user/userTesttaskTable.pagelet";
    }

    @RequestMapping("/testtask1")
    public String testTaskJump2(OrgUser orgUser, Model model) {
        List<OrgUser> userList = userService.findUserList(orgUser);
        model.addAttribute("userList", userList);
        return "/organization/user/testtaskAdmin1.page";
    }

    @RequestMapping("/testtask1/search")
    public String testCaseSearchAction(String id, Integer start, Integer limit, int page, int pagesize, String choose, QualityTestCase testCase, String order, String ordertype, Model model, HttpServletRequest request) {
        QualityTestCase testCase1 = new QualityTestCase();
        if (choose.equals("4")) {
            testCase1.setCaseScriptedBy(id);
        }
        if (choose.equals("5")) {
            testCase1.setCaseOpenedBy(id);
        }
        Pager<QualityTestCase> testCasePager = testCaseService.findTestCasePager(start, limit, testCase1, order, false);
        model.addAttribute("testCasePager", testCasePager);
        return "/organization/user/userTestCaseTable.pagelet";
    }

    @RequestMapping("/active")
    public String activeJump(OrgUser orgUser, Model model) {
        List<OrgUser> userList = userService.findUserList(orgUser);
        model.addAttribute("userList", userList);
        return "/organization/user/activeAdmin.page";
    }

    @RequestMapping("/active/search")
    public String activeSearchAction(String selDate, String id, Integer start, Integer limit, int page, int pagesize, String choose, SystemAction action, String order, String ordertype, Model model, HttpServletRequest request) {
        SystemAction systemAction = new SystemAction();
        systemAction.setActionActor(id);
        //根据日期来查
        /**
         * 1-今天 2-昨天 3-前天 4-本周 5-上周 6-本月 7-上月 0-所有
         * action_date BETWEEN '2015-10-16 00:00:00' AND '2015-10-16 23:59:59'
         */
        Date date = new Date();
        Date startDate;
        Date endDate;

        if ("1".equals(selDate)) {
            startDate = DateUtils.getDateStart(date);
            endDate = DateUtils.getDateEnd(date);
        } else if ("2".equals(selDate)) {
            startDate = DateUtils.addDays(DateUtils.getDateStart(date), -1);
            endDate = DateUtils.addDays(DateUtils.getDateEnd(date), -1);
        } else if ("3".equals(selDate)) {
            startDate = DateUtils.addDays(DateUtils.getDateStart(date), -2);
            endDate = DateUtils.addDays(DateUtils.getDateEnd(date), -2);
        } else if ("4".equals(selDate)) {
            startDate = DateUtils.getFirstDayOfWeek(date);
            endDate = DateUtils.getLastDayOfWeek(date);
        } else if ("5".equals(selDate)) {
            startDate = DateUtils.addDays(DateUtils.getFirstDayOfWeek(date), -7);
            endDate = DateUtils.addDays(DateUtils.getLastDayOfWeek(date), -7);
        } else if ("6".equals(selDate)) {
            startDate = DateUtils.getFirstDayOfMonth(date);
            endDate = DateUtils.getLastDayOfMonth(date);
        } else if ("7".equals(selDate)) {
            startDate = DateUtils.getFirstDayOfMonth(DateUtils.addMonths(date, -1));
            endDate = DateUtils.getLastDayOfMonth(DateUtils.addMonths(date, -1));
        } else {
            startDate = null;
            endDate = null;
        }
        if (startDate == null && endDate == null) {
            Pager<SystemAction> actionPager = actionService.findSystemActionPager(start, limit, systemAction, null, null);
            model.addAttribute("actionPager", actionPager);
        } else {
            String startDateStr = DateUtils.formatDate(startDate, "yyyy-MM-dd HH:mm:ss");
            String endDateStr = DateUtils.formatDate(endDate, "yyyy-MM-dd HH:mm:ss");
            Pager<SystemAction> actionPager = actionService.queryBetweenDate(start, limit, systemAction, startDateStr, endDateStr, null, false);
            model.addAttribute("actionPager", actionPager);
        }
        return "/organization/user/userActiveTable.pagelet";
    }

    @RequestMapping("/project")
    public String projectJump(OrgUser orgUser, Model model) {
        List<OrgUser> userList = userService.findUserList(orgUser);
        model.addAttribute("userList", userList);
        return "/organization/user/projectAdmin.page";
    }

    @RequestMapping("/project/search")
    public String projectSearchAction(String id, Integer start, Integer limit, int page, int pagesize, String choose, ProjectTask task, String order, String ordertype, Model model, HttpServletRequest request) {
        ProjectTeam team = new ProjectTeam();
        team.setTeamUserId(id);
        List<ProjectTeam> teamList = teamService.findTeamList(team);
        List<Project> projectList = new ArrayList<Project>();
        for (ProjectTeam team1 : teamList) {
            projectList.add(projectService.findById(team1.getProjectId()));
        }
        model.addAttribute("projectList", projectList);
        Integer size = projectList.size();
        model.addAttribute("size", size);
        model.addAttribute("teamList", teamList);
        return "/organization/user/projectAdminTable.pagelet";
    }
}