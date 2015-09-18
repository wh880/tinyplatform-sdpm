package org.tinygroup.sdpm.action.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;

/**
 * 用户管理控制器
 * Created by Hulk on 2015/9/16.
 */
@Controller
public class UserAction {
    @Autowired
    private UserService userService;

    public String form(OrgUser user, Model model) {
        if (StringUtil.isBlank(user.getOrgUserId())) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }
        model.addAttribute("user", user);
        return "organization/user/add";
    }
}
