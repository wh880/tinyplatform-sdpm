package org.tinygroup.sdpm.action.org;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.sdpm.common.util.Collections3;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.RoleService;
import org.tinygroup.sdpm.org.service.inter.UserService;

import java.util.List;

/**
 * Created by Administrator on 2015-10-12.
 */

@Controller
@RequestMapping("/a/org/roleUser")
public class RoleUserAction extends BaseController {
    @Autowired
    RoleService roleService;
    @Autowired
    UserService userService;

    /**
     * 显示角色的用户
     *
     * @param roleId
     * @param model
     * @return
     */
    @RequiresPermissions("org-privilege-user")
    @RequestMapping("/show")
    public String showUser(Integer roleId, Model model) {
        List<OrgUser> userList = userService.findUserList(null);
        List<OrgRoleUser> linkList = roleService.findUserByRoleId(roleId);
        List<String> idList = Collections3.extractToList(linkList, "orgUserId");
        OrgRole role = roleService.findRole(roleId);
        model.addAttribute("userIdList", idList);
        model.addAttribute("role", role);
        model.addAttribute("userList", userList);
        model.addAttribute("roleId", roleId);//角色id
        return "organization/privilege/member";
    }

    /**
     * 保存角色用户
     *
     * @param roleId
     * @param array
     * @return
     */
    @RequiresPermissions("org-privilege-user")
    @RequestMapping("/save")
    public String save(Integer roleId, String[] array) {
        if (!ArrayUtil.isEmptyArray(array) && roleId != null) {
            roleService.addRoleUser(array, roleId);
        }
        return "redirect:" + adminPath + "/org/privilege/list";
    }
}