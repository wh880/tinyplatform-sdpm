package org.tinygroup.sdpm.action.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgDept;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.DeptService;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.service.StoryService;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTask;
import org.tinygroup.sdpm.project.service.inter.TaskService;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.sdpm.quality.service.inter.BugService;
import org.tinygroup.tinysqldsl.Pager;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/org/user")
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
        return "redirect:/org/user/list/";
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

        return "redirect:/org/user/list/";
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
    public String listData(Integer orgDeptId, Integer start, Integer limit, OrgUser orgUser, Model model) {
        if (orgDeptId == null || orgDeptId == -1) {
            orgUser.setOrgDeptId(null);
            Pager<OrgUser> pager = userService.findUserPager(start, limit, orgUser);
            model.addAttribute("pager", pager);
        } else {
            Pager<OrgUser> pager = userService.findUserByDeptId(start, limit, orgDeptId);
            model.addAttribute("pager", pager);
        }
        return "organization/user/userTableData.pagelet";
    }

    @RequestMapping("/show/profile")
    public String show(String id, Model model) {
        OrgUser user = userService.findUser(id);
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
    public String storyJump() {

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
    public String taskJump() {
        return "/organization/user/taskAdmin.page";
    }

    @RequestMapping("/task/search")
    public String taskSearchAction(String id, Integer start, Integer limit, int page, int pagesize, String choose, ProjectTask task, String order, String ordertype, Model model, HttpServletRequest request) {
        OrgUser user = userService.findUser(id);
        String account = user.getOrgUserAccount();
        ProjectTask task1 = new ProjectTask();
        if (choose.equals("7")) {
            task1.setTaskCanceledBy(account);
        } else if (choose.equals("4")) {
            task1.setTaskOpenBy(account);
        } else if (choose.equals("5")) {
            task1.setTaskFinishedBy(account);
        } else if (choose.equals("6")) {
            task1.setTaskClosedBy(account);
        } else {
            task1.setTaskAssignedTo(account);
        }
        Pager<ProjectTask> taskPager = taskService.findPagerTask(start, limit, task1, order, false, null, null);
        model.addAttribute("taskPager", taskPager);
        return "/organization/user/userTaskTable.pagelet";
    }

    @RequestMapping("/bug")
    public String bugJump() {
        return "/organization/user/bugAdmin.page";
    }

    @RequestMapping("/bug/search")
    public String bugSearchAction(String id, Integer start, Integer limit, int page, int pagesize, String choose, ProjectTask task, String order, String ordertype, Model model, HttpServletRequest request) {
        OrgUser user = userService.findUser(id);
        String account = user.getOrgUserAccount();
        QualityBug bug = new QualityBug();
        if (choose.equals("6")) {
            bug.setBugClosedBy(account);
        } else if (choose.equals("5")) {
            bug.setBugResolvedBy(account);
        } else if (choose.equals("4")) {
            bug.setBugOpenedBy(account);
        } else {
            bug.setBugAssignedTo(account);
        }
        Pager<QualityBug> bugPager = bugService.findBugListPager(limit * (page - 1), limit, null, bug, order, false);
        model.addAttribute("bugPager", bugPager);
        return "/organization/user/bugAdminTable.pagelet";
    }
}