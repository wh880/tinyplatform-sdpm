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

        if (choose.equals(3) || choose.equals(null)) {
            story.setStoryAssignedTo(id);
            Pager<ProductStory> p1 = storyService.findStoryPager(pagesize * (page - 1), pagesize, story, null, null, null, order, "asc".equals(ordertype) ? true : false);
            model.addAttribute("storyList", p1);

        } else if (choose.equals(4)) {
            story.setStoryOpenedBy(id);
            Pager<ProductStory> p2 = storyService.findStoryPager(pagesize * (page - 1), pagesize, story, null, null, null, order, "asc".equals(ordertype) ? true : false);
            model.addAttribute("storyList", p2);

        } else if (choose.equals(5)) {
            story.setStoryReviewedBy(id);
            Pager<ProductStory> p3 = storyService.findStoryPager(pagesize * (page - 1), pagesize, story, null, null, null, order, "asc".equals(ordertype) ? true : false);
            model.addAttribute("storyList", p3);

        } else {
            story.setStoryClosedBy(id);
            Pager<ProductStory> p4 = storyService.findStoryPager(pagesize * (page - 1), pagesize, story, null, null, null, order, "asc".equals(ordertype) ? true : false);
            model.addAttribute("storyList", p4);

        }
        return "organization/user/userStoryTable.pagelet";
    }


}
