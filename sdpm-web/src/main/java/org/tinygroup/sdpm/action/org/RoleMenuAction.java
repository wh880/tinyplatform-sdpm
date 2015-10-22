package org.tinygroup.sdpm.action.org;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu;
import org.tinygroup.sdpm.org.service.inter.RoleMenuService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/a/org/roleMenu")
public class RoleMenuAction extends BaseController {
    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 权限管理保存
     *
     * @param id
     * @param ids
     * @return
     */
    @RequiresPermissions("org-privilege-maintain")
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Map<String, String> save(Integer id, String[] ids) {
        List<OrgRoleMenu> list = new ArrayList<OrgRoleMenu>();
        List<OrgRoleMenu> orgRoleMenus = roleMenuService.findMenuByRoleId(id);
        roleMenuService.batchDeleteRoleMenu(orgRoleMenus);
        for (String i : ids) {
                OrgRoleMenu orgRoleMenu = new OrgRoleMenu();
                orgRoleMenu.setOrgRoleId(id);
                orgRoleMenu.setOrgRoleMenuId(i);
                list.add(orgRoleMenu);
        }
        if (!list.isEmpty()) {
            roleMenuService.batchAddRoleMenu(list);
        }
        return resultMap(true, "保存成功！");
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
    public String showMenuIds(Integer id, Model model) {
        List<OrgRoleMenu> orgRoleMenus = roleMenuService.findMenuByRoleId(id);
        model.addAttribute("orgRoleMenus", initMenuIdList(orgRoleMenus));
        return "organization/privilege/privilegeMaintain.page";
    }

    /**
     * 对树的权限勾选显示
     * @param orgRoleMenuList
     * @return
     */
    private List<String> initMenuIdList(List<OrgRoleMenu> orgRoleMenuList) {
        List<String> list = new ArrayList<String>();
        if (orgRoleMenuList != null && !orgRoleMenuList.isEmpty()) {
            for (OrgRoleMenu orgRoleMenu : orgRoleMenuList) {
                list.add(orgRoleMenu.getOrgRoleMenuId());
            }
        }
        return list;
    }

}
