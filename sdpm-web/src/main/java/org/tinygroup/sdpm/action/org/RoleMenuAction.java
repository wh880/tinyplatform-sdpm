package org.tinygroup.sdpm.action.org;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.tinygroup.sdpm.common.util.Collections3;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu;
import org.tinygroup.sdpm.org.service.inter.RoleService;

import java.util.List;

@Controller
@RequestMapping("/a/org/roleMenu")
public class RoleMenuAction extends BaseController {
    @Autowired
    private RoleService roleService;

    /**
     * 保存菜单的权限
     *
     * @param id
     * @param menuId
     * @param parentId
     * @return
     */
    @RequiresPermissions("org-privilege-maintain")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Integer id, String[] menuId, String parentId) {
        roleService.saveRoleMenu(id, parentId, menuId);
        return "redirect:" + adminPath + "/org/roleMenu/show?id=" + id;
    }

    /**
     * 显示角色拥有的权限
     *
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("org-privilege-maintain")
    @RequestMapping("/show")
    public String showMenuIds(@RequestParam(value = "parentId", defaultValue = "0") String parentId, Integer id, Model model) {
        List<OrgRoleMenu> orgRoleMenus = roleService.findMenuByRoleId(id);
        List<String> orgRoleMenuIdList = Collections3.extractToList(orgRoleMenus, "orgRoleMenuId");
        model.addAttribute("orgRoleMenuIdList", orgRoleMenuIdList);
        model.addAttribute("parentId", parentId);
        return "organization/privilege/privilegeMaintain";
    }


}
