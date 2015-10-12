package org.tinygroup.sdpm.action.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.RoleUserService;
import org.tinygroup.sdpm.org.service.inter.UserService;

import java.util.List;

/**
 * Created by Administrator on 2015-10-12.
 */

@Controller
@RequestMapping("/org/roleUser")
public class RoleUserAction extends BaseController {
    @Autowired
    RoleUserService roleUserService;
    @Autowired
    UserService userService;

    @RequestMapping("/show")
    public String showUser(Integer id, Model model) {
        List<OrgUser> userList = userService.findUserList(new OrgUser());
        // Integer roleId = Integer.parseInt(CookieUtils.getCookie(request, "cookie_roleId"));
        List<OrgRoleUser> linkList = roleUserService.findUserByRoleId(id);
        String linked = "";
        for (OrgRoleUser p : linkList) {
            if (linked != "") {
                linked = linked + "," + p.getOrgUserId();
            } else {
                linked = linked + p.getOrgUserId();
            }
        }
        model.addAttribute("linkString", linked);
        model.addAttribute("userList", userList);
        return "organization/privilege/groupMaintain.page";
    }

    @RequestMapping("/save")
    public String save(Integer id, String[] array, Model model) {
        roleUserService.addRoleUser(array, id);
        return "redirect:/org/roleUser/show";
    }
}
