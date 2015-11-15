package org.tinygroup.sdpm.action.org;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.StringUtil;
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
import org.tinygroup.sdpm.util.LogUtil;
import org.tinygroup.sdpm.util.UserUtils;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


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

    /**
     * 修改密码表单
     * @return
     */
    @RequestMapping("/passwordForm")
    public String passwordForm() {
        return "organization/user/updatePassword";
    }
    /**
     * 修改密码表单保存
     * @return
     */
    @RequestMapping("/passwordSave")
    public String passwordSave(String oldPassword,String newPassword,Model model) {
        OrgUser user = UserUtils.getUser();
        if (userService.validatePassword(oldPassword,user.getOrgUserPassword())){
            OrgUser newUser = new OrgUser();
            newUser.setOrgUserId(user.getOrgUserId());
            newUser.setOrgUserPassword(newPassword);
            userService.updateUser(newUser);
            addMessage(model,"修改密码成功！");
        }else {
            addMessage(model,"修改密码失败，原始密码错误！");
        }
        return "organization/user/updatePassword";
    }

    /**
     * 用户新增和编辑页面显示
     *
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value = {"org-user-edit", "organizationAddUser", "org-user-editprofile"}, logical = Logical.OR)
    @RequestMapping("/form")
    public String form(String id, Model model) {
        if (id != null) {
            OrgUser user = userService.findUser(id);
            OrgDept dept = deptService.findDept(user.getOrgDeptId());
            model.addAttribute("user", user);
            model.addAttribute("dept", dept);
        }
        return "organization/user/form";
    }

    /**
     * 增加修改用户的保存
     *
     * @param user
     * @param model
     * @param password1
     * @param password2
     * @return
     */
    @RequiresPermissions(value = {"org-user-edit", "org-user-add", "organizationAddUser"}, logical = Logical.OR)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(OrgUser user, Model model, String password1, String password2) {
        if (StringUtil.isBlank(user.getOrgUserId())) {
            if (password1.equals(password2)) {
                user.setOrgUserPassword(password1);
                OrgUser userTemp = userService.addUser(user);
                LogUtil.logWithComment(LogUtil.LogOperateObject.USER
                        , LogUtil.LogAction.CREATED
                        , String.valueOf(userTemp.getOrgUserId())
                        , UserUtils.getUserId()
                        , null
                        , null
                        , null
                        , null
                        , null);
            } else {
                return "organization/user/addUser.page";
            }
        } else {
            userService.updateUser(user);
            LogUtil.logWithComment(LogUtil.LogOperateObject.USER
                    , LogUtil.LogAction.EDITED
                    , String.valueOf(user.getOrgUserId())
                    , UserUtils.getUserId()
                    , null
                    , null
                    , null
                    , null
                    , null);
        }
        model.addAttribute("user", user);
        return "redirect:" + adminPath + "/org/user/list/";
    }


    /**
     * 用户名唯一性校验
     *
     * @param account
     * @param orgUserId
     * @return
     */
    @RequiresPermissions(value = {"org-user-add", "organizationAddUser"}, logical = Logical.OR)
    @ResponseBody
    @RequestMapping(value = "/usercheck")
    public Map userCheck(@RequestParam("param") String account, String orgUserId) {
        OrgUser user = new OrgUser();
        user.setOrgUserAccount(account);
        List<OrgUser> userList = userService.findUserList(user);
        if (StringUtil.isBlank(orgUserId)) { //新用户
            if (userList.isEmpty()) {
                return resultMap(true, "用户名可用");
            }
        } else { //修改用户信息
            if (userList.size() < 1 || account.equals(userService.findUser(orgUserId).getOrgUserAccount())) {
                return resultMap(true, "用户名可用");
            }
        }
        return resultMap(false, "用户名不可用！");
    }

    /**
     * 用户主页面显示
     */
    @RequiresPermissions("organizationUser")
    @RequestMapping("/list")
    public String list() {
        return "organization/user/index";
    }

    /**
     * 删除页面 跳转
     *
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("org-user-delete")
    @RequestMapping("/delete/page")
    public String deleteData(String id, Model model) {
        model.addAttribute("id", id);
        return "organization/user/delect.pagelet";
    }

    /**
     * 删除用户的提交
     *
     * @param id
     * @param password
     * @return
     */
    @RequiresPermissions("org-user-delete")
    @RequestMapping("/delete")
    public String delete(String id, String password) {
        OrgUser orgUser = userService.findUser(id);
        String userPassword = orgUser.getOrgUserPassword();
        if (userService.validatePassword(password, userPassword)) {
            userService.deleteUser(id);
            LogUtil.logWithComment(LogUtil.LogOperateObject.USER
                    , LogUtil.LogAction.DELETED
                    , String.valueOf(id)
                    , UserUtils.getUserId()
                    , null
                    , null
                    , null
                    , null
                    , null);
        } else {
            return "organization/user/delect.pagelet";
        }
        return "redirect:" + adminPath + "/org/user/list/";
    }

    /**
     * 批量删除功能
     *
     * @param ids
     * @return
     */
    @RequiresPermissions("org-user-batchdel")
    @ResponseBody
    @RequestMapping(value = "/batchDelete")
    public Map batchDelete(String ids) {
        if (ids == null) {
            return resultMap(false, "删除失败");
        }
        List<OrgUser> list = new ArrayList<OrgUser>();
        for (String s : ids.split(",")) {
            OrgUser orgUser = new OrgUser();
            orgUser.setOrgUserId(s);
            orgUser.setOrgUserDeleted(OrgUser.DELETE_YES);
            list.add(orgUser);
        }
        userService.deleteBatchUser(list);
        return resultMap(true, "删除成功");
    }

    /**
     * 用户列表数据的获取
     *
     * @param orgDeptId
     * @param start
     * @param limit
     * @param orgUser
     * @param model
     * @return
     */
    @RequiresPermissions("organizationUser")
    @RequestMapping("/list/data")
    public String listData(Integer orgDeptId, Integer start, Integer limit,
                           OrgUser orgUser, Model model) {
        Pager<OrgUser> pager;
        if (orgDeptId == null || orgDeptId == -1) {
            orgUser.setOrgDeptId(null);
            pager = userService.findUserPager(start, limit, orgUser);
        } else {
            pager = userService.findUserByDeptId(start, limit, orgDeptId);
        }
        model.addAttribute("pager", pager);
        return "organization/user/userTableData.pagelet";
    }

    /**
     * 显示用户档案页面
     *
     * @param id
     * @param orgUser
     * @param model
     * @return
     */
    @RequiresPermissions("org-user-profileAdmin")
    @RequestMapping("/show/profile")
    public String show(String id, OrgUser orgUser, Model model) {
        OrgUser user = userService.findUser(id);
        List<OrgUser> userList = userService.findUserList(orgUser);
        model.addAttribute("userList", userList);
        model.addAttribute("user", user);
        return "organization/user/profileAdmin.page";
    }

    /**
     * 点击用户后页面的用户选择
     *
     * @param orgUser
     * @return
     */
    @ResponseBody
    @RequestMapping("/userList")
    public List<OrgUser> findUser(OrgUser orgUser) {
        List<OrgUser> list = userService.findUserList(orgUser);
        return list;
    }

    /**
     * 用户下的需求
     *
     * @param orgUser
     * @param model
     * @return
     */
    @RequiresPermissions("org-user-storyAdmin")
    @RequestMapping("/story")
    public String storyJump(OrgUser orgUser, Model model) {
        List<OrgUser> userList = userService.findUserList(orgUser);
        model.addAttribute("userList", userList);
        return "/organization/user/storyAdmin.page";
    }

    @RequiresPermissions("org-user-storyAdmin")
    @RequestMapping("/story/search")
    public String storySearchAction(String id, int page, int pagesize, String choose, ProductStory story, String order, Model model) {
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

    /**
     * 用户下的任务
     *
     * @param orgUser
     * @param model
     * @return
     */
    @RequiresPermissions("org-user-taskAdmin")
    @RequestMapping("/task")
    public String taskJump(OrgUser orgUser, Model model) {
        List<OrgUser> userList = userService.findUserList(orgUser);
        model.addAttribute("userList", userList);
        return "/organization/user/taskAdmin.page";
    }

    @RequiresPermissions("org-user-taskAdmin")
    @RequestMapping("/task/search")
    public String taskSearchAction(String id, Integer start, Integer limit, String choose, String order, Model model) {
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
        Pager<ProjectTask> taskPager = taskService.findTaskPager(start, limit, task1, order, false, null);
        List<Integer> projectIdList = new ArrayList<Integer>();
        for (ProjectTask project : taskPager.getRecords()) {
            projectIdList.add(project.getTaskProject());
        }

        model.addAttribute("taskPager", taskPager);
        return "/organization/user/userTaskTable.pagelet";
    }

    /**
     * 用户下的缺陷
     *
     * @param orgUser
     * @param model
     * @return
     */
    @RequiresPermissions("org-user-bugAdmin")
    @RequestMapping("/bug")
    public String bugJump(OrgUser orgUser, Model model) {
        List<OrgUser> userList = userService.findUserList(orgUser);
        model.addAttribute("userList", userList);
        return "/organization/user/bugAdmin.page";
    }

    @RequiresPermissions("org-user-bugAdmin")
    @RequestMapping("/bug/search")
    public String bugSearchAction(String id, Integer start, Integer limit, int page, int pagesize, String choose, ProjectTask task, String order, String ordertype, Model model, HttpServletRequest request) {
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


    /**
     * 用户下的测试
     *
     * @param orgUser
     * @param model
     * @return
     */
    @RequiresPermissions("org-user-testtaskAdmin")
    @RequestMapping("/testtask")
    public String testTaskJump(OrgUser orgUser, Model model) {
        List<OrgUser> userList = userService.findUserList(orgUser);
        model.addAttribute("userList", userList);
        return "/organization/user/testtaskAdmin.page";
    }

    @RequiresPermissions("org-user-testtaskAdmin")
    @RequestMapping("/testtask/search")
    public String testTaskSearchAction(String id, Integer start, Integer limit, int page, int pagesize, String choose, QualityTestTask testTask, String order, String ordertype, Model model, HttpServletRequest request) {
        QualityTestTask testTask1 = new QualityTestTask();
        testTask1.setTesttaskOwner(id);
        Pager<QualityTestTask> testTaskPager = testTaskService.findTestTaskPager(start, limit, testTask1, order, false);
        model.addAttribute("testTaskPager", testTaskPager);

        return "/organization/user/userTesttaskTable.pagelet";
    }

    @RequiresPermissions("org-user-testtaskAdmin")
    @RequestMapping("/testtask1")
    public String testTaskJump2(OrgUser orgUser, Model model) {
        List<OrgUser> userList = userService.findUserList(orgUser);
        model.addAttribute("userList", userList);
        return "/organization/user/testtaskAdmin1.page";
    }

    @RequiresPermissions("org-user-testtaskAdmin")
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

    /***
     * 用户下的动态
     *
     * @param orgUser
     * @param model
     * @return
     */
    @RequiresPermissions("org-user-activeAdmin")
    @RequestMapping("/active")
    public String activeJump(OrgUser orgUser, Model model) {
        List<OrgUser> userList = userService.findUserList(orgUser);
        model.addAttribute("userList", userList);
        return "/organization/user/activeAdmin.page";
    }

    @RequiresPermissions("org-user-activeAdmin")
    @RequestMapping("/active/search")
    public String activeSearchAction(String selDate, String id, Integer start, Integer limit, int page, int pagesize, String choose, SystemAction action, String order, String ordertype, Model model, HttpServletRequest request) {
        SystemAction systemAction = new SystemAction();
        systemAction.setActionActor(id);
        //根据日期来查
        /**
         * 1-今天 2-昨天 3-前天 4-本周 5-上周 6-本月 7-上月 0-所有
         * action_date BETWEEN '2015-10-16 00:00:00' AND '2015-10-16 23:59:59'
         */
        Date startDate = new Date();
        Date endDate = new Date();
        betweenDate(selDate, startDate, endDate);

        if (startDate == null && endDate == null) {
            Pager<SystemAction> actionPager = actionService.findSystemActionPager(start, limit, systemAction, null, null);
            model.addAttribute("actionPager", actionPager);
        } else if (!startDate.equals(endDate)) {
            String startDateStr = DateUtils.formatDate(startDate, "yyyy-MM-dd HH:mm:ss");
            String endDateStr = DateUtils.formatDate(endDate, "yyyy-MM-dd HH:mm:ss");
            Pager<SystemAction> actionPager = actionService.queryBetweenDate(start, limit, systemAction, startDateStr, endDateStr, null, false);
            model.addAttribute("actionPager", actionPager);
        } else {
            Pager<SystemAction> actionPager = actionService.findSystemActionPager(start, limit, systemAction, null, null);
            model.addAttribute("actionPager", actionPager);
        }
        return "/organization/user/userActiveTable.pagelet";
    }

    /**
     * 用户下的项目
     *
     * @param orgUser
     * @param model
     * @return
     */
    @RequiresPermissions("org-user-projectAdmin")
    @RequestMapping("/project")
    public String projectJump(OrgUser orgUser, Model model) {
        List<OrgUser> userList = userService.findUserList(orgUser);
        model.addAttribute("userList", userList);
        return "/organization/user/projectAdmin.page";
    }

    @RequiresPermissions("org-user-projectAdmin")
    @RequestMapping("/project/search")
    public String projectSearchAction(String id, Integer start, Integer limit, int page, int pagesize, String choose, ProjectTask task, String order, String ordertype, Model model, HttpServletRequest request) {
        ProjectTeam team = new ProjectTeam();
        team.setTeamUserId(id);
        List<ProjectTeam> teamList = teamService.findTeamList(team);
        List<Project> projectList = new ArrayList<Project>();
        for (ProjectTeam team1 : teamList) {
            projectList.add(projectService.findProjectById(team1.getProjectId()));
        }
        model.addAttribute("projectList", projectList);
        Integer size = projectList.size();
        model.addAttribute("size", size);
        model.addAttribute("teamList", teamList);
        return "/organization/user/projectAdminTable.pagelet";
    }
}