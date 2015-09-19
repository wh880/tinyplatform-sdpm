package org.tinygroup.sdpm.action.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;

/**
 * 用户管理控制器
 * Created by Hulk on 2015/9/16.
 */
@Controller
@RequestMapping("/org/user/")
public class UserAction {
    @Autowired
    private UserService userService;

    @RequestMapping("/form")
    public String form(String id, Model model) {
        if (!StringUtil.isBlank(id)) {
            OrgUser user = userService.findUser(id);
            model.addAttribute("user", user);
        }
        return "organization/common/addUser.page";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(OrgUser user, Model model) {
        if (StringUtil.isBlank(user.getOrgUserId())) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }
        model.addAttribute("user", user);
        return "organization/common/addUser.page";
    }
}
