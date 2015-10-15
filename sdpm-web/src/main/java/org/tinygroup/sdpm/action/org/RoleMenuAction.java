package org.tinygroup.sdpm.action.org;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/a/org/roleMenu")
public class RoleMenuAction extends BaseController {
    @Autowired
    private RoleMenuService roleMenuService;

    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Map<String, String> save(Integer id, String[] ids) {
        List<OrgRoleMenu> list = new ArrayList<OrgRoleMenu>();
        List<OrgRoleMenu> orgRoleMenus = roleMenuService.findMenuByRoleId(id);
        roleMenuService.batchDeleteRoleMenu(orgRoleMenus);
        // List<String> orgRoleMenusIds = initMenuIdList(orgRoleMenus);
        for (String i : ids) {

//            if (orgRoleMenusIds.contains(i)) {
//                continue;
//            } else {
                OrgRoleMenu orgRoleMenu = new OrgRoleMenu();
                orgRoleMenu.setOrgRoleId(id);
                orgRoleMenu.setOrgRoleMenuId(i);
                list.add(orgRoleMenu);
            // }
        }
        if (!list.isEmpty()) {
            roleMenuService.batchAddRoleMenu(list);
        }

        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "y");
        map.put("info", "保存成功！");
        return map;

    }

    @RequestMapping("/show")
    public String showMenuIds(Integer id, Model model) {
        List<OrgRoleMenu> orgRoleMenus = roleMenuService.findMenuByRoleId(id);
        model.addAttribute("orgRoleMenus", initMenuIdList(orgRoleMenus));
        return "organization/privilege/privilegeMaintain.page";
    }

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
