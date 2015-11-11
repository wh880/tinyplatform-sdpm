package org.tinygroup.sdpm.action.org;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/a/org/privilege")
public class RoleAction extends BaseController {
    @Autowired
    private RoleService roleService;

    /**
     * 增加或编辑时候显示页面
     *
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions(value = {"org-privilege-edit", "organizationAddGroup"}, logical = Logical.OR)
    @RequestMapping("/form")
    public String form(Integer id, Model model) {
        if (id != null) {
            OrgRole role = roleService.findRole(id);
            model.addAttribute("role", role);
        }
        return "organization/privilege/addRoleForm";
    }

    /**
     * 角色新增和编辑的保存
     *
     * @param role
     * @param model
     * @return
     */
    @RequiresPermissions(value = {"org-privilege-edit", "organizationAddGroup"}, logical = Logical.OR)
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(OrgRole role, Model model) {
        if (role.getOrgRoleId() == null) {
            roleService.addRole(role);
        } else {
            roleService.updateRole(role);
        }
        model.addAttribute("role", role);
        return "redirect:" + adminPath + "/org/privilege/list/";
    }


    /**
     * 角色的复制功能
     *
     * @param orgRoleId
     * @param orgRoleName
     * @param orgRoleRemarks
     * @param copyPart
     * @return
     */
    @RequiresPermissions("org-privilege-copy")
    @RequestMapping("/copyRole")
    public String copyRole(Integer orgRoleId, String orgRoleName, String orgRoleRemarks, String[] copyPart) {
        OrgRole orgRole1 = new OrgRole();
        orgRole1.setOrgRoleName(orgRoleName);
        orgRole1.setOrgRoleRemarks(orgRoleRemarks);
        OrgRole orgRole2 = roleService.addRole(orgRole1);
        Integer orgRoleIdNew = orgRole2.getOrgRoleId();
        for (String check : copyPart) {
            if (check.equals("copyPrivilege")) {
                roleService.copyRoleMenu(orgRoleIdNew, orgRoleId);
            } else {
                roleService.copyRoleUser(orgRoleIdNew, orgRoleId);
            }
        }
        return "redirect:" + adminPath + "/org/privilege/list";
    }

    /**
     * 角色的删除功能
     *
     * @param id
     * @return
     */
    @RequiresPermissions("org-privilege-delete")
    @ResponseBody
    @RequestMapping(value = "/delete")
    public Map delete(Integer id) {
        roleService.deleteRole(id);
        return resultMap(true, "删除成功");
    }

    /**
     * 角色主页面
     *
     * @param orgRole
     * @param model
     * @return
     */
    @RequiresPermissions("organizationPrivilege")
    @RequestMapping("/list")
    public String list(OrgRole orgRole, Model model) {
        List<OrgRole> list = roleService.findRoleList(orgRole);
        model.addAttribute("list", list);
        return "organization/privilege/privilege.page";
    }

    /**
     * 角色页面数据
     *
     * @param start
     * @param limit
     * @param orgRole
     * @param model
     * @return
     */
    @RequiresPermissions("organizationPrivilege")
    @RequestMapping("/list/data")
    public String listData(Integer start, Integer limit, OrgRole orgRole, Model model) {
        Pager<OrgRole> pager = roleService.findRolePager(start, limit, orgRole);
        model.addAttribute("pager", pager);
        return "organization/privilege/privilegeTable.pagelet";
    }

}
