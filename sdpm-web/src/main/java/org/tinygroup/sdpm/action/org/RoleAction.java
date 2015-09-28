package org.tinygroup.sdpm.action.org;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.org.service.inter.RoleService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/org/privilege")
public class RoleAction extends BaseController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/form")
    public String form(Integer id, Model model) {
        if (id != null) {
            OrgRole role = roleService.findRole(id);
            model.addAttribute("role", role);
        }
        return "organization/privilege/addGroup.page";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(OrgRole role, Model model) {
        if (role.getOrgRoleId() == null) {
            roleService.addRole(role);
        } else {
            roleService.updateRole(role);
        }
        model.addAttribute("role", role);
        return "redirect:/org/privilege/list/";
    }

    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map delete(Integer id) {
        roleService.deleteRole(id);
        Map<String, String> map = new HashMap<String, String>();
        map.put("status", "y");
        map.put("info", "删除成功");
        return map;
    }

    @RequestMapping("/list")
    public String list(OrgRole orgRole, Model model) {
        List<OrgRole> list = roleService.findRoleList(orgRole);
        model.addAttribute("list", list);
        return "organization/privilege/privilege.page";
    }

    @RequestMapping("/list/data")
    public String listData(Integer start, Integer limit, OrgRole orgRole, Model model) {
        Pager<OrgRole> pager = roleService.findRolePager(start, limit, orgRole);
        model.addAttribute("pager", pager);
        return "organization/privilege/privilegeTable.pagelet";
    }

}
