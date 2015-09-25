package org.tinygroup.sdpm.action.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * 用户管理控制器
 * Created by Hulk on 2015/9/16.
 */
@Controller
@RequestMapping("/org/user")
public class UserAction extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("/form")
    public String form(String id, Model model) {
        if (id != null) {
            OrgUser user = userService.findUser(id);
            model.addAttribute("user", user);
        }
        return "organization/user/addUser.page";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(OrgUser user, Model model) {
        if (StringUtil.isBlank(user.getOrgUserId())) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }
        model.addAttribute("user", user);
        return "redirect:/org/user/list/";
    }

    @RequestMapping("/list")
    public String list(OrgUser orgUser, Model model) {
        List<OrgUser> list = userService.findUserList(orgUser);
        model.addAttribute("list", list);
        return "organization/user/user.page";
    }

    @RequestMapping("/delete/page")
    public String deleteData(String id, Model model) {
        model.addAttribute("id", id);
        return "organization/user/delect.pagelet";
    }

    @RequestMapping("/delete")
    public String delete(String id) {
        userService.deleteUser(id);
        return "redirect:/org/user/list/";
    }
    @RequestMapping("/list/data")
    public String listData(Integer start, Integer limit, OrgUser orgUser, Model model) {
        Pager<OrgUser> pager = userService.findUserPager(start, limit, orgUser);
        model.addAttribute("pager", pager);
        return "organization/user/userTableData.pagelet";
    }
}
