package org.tinygroup.sdpm.action.org;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.org.service.inter.RoleService;
import org.tinygroup.sdpm.system.dao.pojo.SystemConfig;
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
     * @param roleId
     * @param model
     * @return
     */
    @RequiresPermissions(value = {"org-privilege-edit", "organizationAddGroup"}, logical = Logical.OR)
    @RequestMapping("/form")
    public String form(Integer roleId, Integer copyRoleId, Model model) {
        if (roleId != null) {
            OrgRole role = roleService.findRole(roleId);
            model.addAttribute("role", role);
        }
        if (copyRoleId != null) {
            OrgRole role = roleService.findRole(copyRoleId);
            role.setOrgRoleId(null);
            model.addAttribute("role", role);
            model.addAttribute("copyRoleId", copyRoleId);
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
     * 角色的复制表单（表单）
     *
     * @param copyRoleId
     * @param model
     * @return
     */
    @RequiresPermissions("org-privilege-copy")
    @RequestMapping(value = "/copyRole", method = RequestMethod.GET)
    public String copyRoleForm(Integer copyRoleId, Model model) {
        OrgRole role = roleService.findRole(copyRoleId);
        model.addAttribute("role", role);
        return "organization/privilege/roleCopy";
    }

    /**
     * 角色的复制表单提交（弹出框中提交）
     *
     * @param role
     * @param copyPart
     * @return
     */
    @ResponseBody
    @RequiresPermissions("org-privilege-copy")
    @RequestMapping(value = "/copyRole", method = RequestMethod.POST)
    public Map<String, String> copyRole(Integer copyRoleId, OrgRole role, String[] copyPart) {
        role = roleService.addRole(role);
        Integer orgRoleIdNew = role.getOrgRoleId();
        for (String check : copyPart) {
            if (check.equals("copyPrivilege")) {
                roleService.copyRoleMenu(orgRoleIdNew, copyRoleId);
            } else {
                roleService.copyRoleUser(orgRoleIdNew, copyRoleId);
            }
        }
        return resultMap(true, "复制角色成功！");
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
        return "organization/privilege/privilege";
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

    @ResponseBody
    @RequestMapping("ajax/roleInCondition")
    public List<OrgRole> roleInCondition(String key, String initKey, String type) {
        if (initKey != null) {
            return roleService.getRoleByIds(initKey.split(","));
        }
        return roleService.roleInCondition(key, type, Integer.parseInt(configService.getConfigBySection(SystemConfig.SEARCH_CONFIG).getConfigKey()));
    }

    /**
     * 角色名唯一性校验
     * @param roleName
     * @param orgRoleId
     * @return
     */
    @RequiresPermissions("organizationAddGroup")
    @ResponseBody
    @RequestMapping(value = "/roleNameCheck")
    public Map roleNameCheck(@RequestParam("param") String roleName, String orgRoleId) {
        OrgRole role = new OrgRole();
        role.setOrgRoleName(roleName);
        role.setDeleted(0);
        List<OrgRole> roleList = roleService.findRoleList(role);
        if (StringUtil.isBlank(orgRoleId)) { //新角色
            if (roleList.isEmpty()) {
                return resultMap(true, "角色名可用");
            }
        } else { //修改用户信息
            if (roleList.size() < 1 || roleName.equals(roleService.findRole(Integer.valueOf(orgRoleId)).getOrgRoleName())) {
                return resultMap(true, "角色名可用");
            }
        }
        return resultMap(false, "角色名不可用！");
    }


}