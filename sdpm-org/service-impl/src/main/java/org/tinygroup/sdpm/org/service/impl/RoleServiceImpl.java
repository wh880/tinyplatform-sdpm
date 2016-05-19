package org.tinygroup.sdpm.org.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.sdpm.common.menu.Menu;
import org.tinygroup.sdpm.common.menu.MenuManager;
import org.tinygroup.sdpm.common.util.Collections3;
import org.tinygroup.sdpm.org.biz.inter.RoleManager;
import org.tinygroup.sdpm.org.biz.inter.RoleMenuManager;
import org.tinygroup.sdpm.org.biz.inter.RoleUserManager;
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser;
import org.tinygroup.sdpm.org.service.inter.RoleService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private MenuManager menuManager;

    @Autowired
    private RoleManager roleManager;

    @Autowired
    private RoleMenuManager roleMenuManager;

    @Autowired
    private RoleUserManager roleUserManager;
    @Transactional(readOnly = true)
    public OrgRole findRole(Integer id) {
        return roleManager.find(id);
    }
    @Transactional(readOnly = true)
    public Pager<OrgRole> findRolePager(Integer start, Integer limit, OrgRole orgRole) {
        return roleManager.findPager(start, limit, orgRole);
    }
    @Transactional(readOnly = true)
    public List<OrgRole> findRoleList(OrgRole orgRole) {
        return roleManager.findList(orgRole);
    }

    public OrgRole addRole(OrgRole orgRole) {
        return roleManager.add(orgRole);
    }

    public OrgRole updateRole(OrgRole orgRole) {
        return roleManager.update(orgRole);
    }

    public Integer deleteRole(Integer id) {
        return roleManager.delete(id);
    }
    @Transactional(readOnly = true)
    public List<OrgRoleMenu> findRoleMenuListByUser(String userId) {
        return roleMenuManager.findMenuListByUser(userId);
    }
    @Transactional(readOnly = true)
    public List<OrgRoleMenu> findMenuByRoleId(Integer roleId) {
        return roleMenuManager.findMenuIds(roleId);
    }

    public Integer batchAddRoleMenu(List<OrgRoleMenu> orgRoleMenuList) {
        return roleMenuManager.batchAdd(orgRoleMenuList);
    }

    public Integer saveRoleMenu(Integer roleId, String parentMenuId, String[] newMenuIds) {
        List<OrgRoleMenu> orgRoleMenus = findMenuByRoleId(roleId);
        if (!CollectionUtil.isEmpty(orgRoleMenus)) {
            List<Menu> allChildMenus = menuManager.getAllChildMenus(parentMenuId);
            List<String> menuIdList = Collections3.extractToList(allChildMenus, "id");//所有的菜单
            List<String> roleMenuIdList = Collections3.extractToList(orgRoleMenus, "orgRoleMenuId");//当前角色已经拥有的菜单
            List<String> currentParentRoleMenuIdList = Collections3.intersection(menuIdList, roleMenuIdList);//当前父级菜单已存在菜单，需要请清除
            List<OrgRoleMenu> delOrgRoleMenus = new ArrayList<OrgRoleMenu>();
            assembleOrgRoleMenuList(delOrgRoleMenus, currentParentRoleMenuIdList.toArray(new String[0]), roleId);
            batchDeleteRoleMenu(delOrgRoleMenus);
        }
        List<OrgRoleMenu> newOrgRoleMenus = new ArrayList<OrgRoleMenu>();
        assembleOrgRoleMenuList(newOrgRoleMenus, newMenuIds, roleId);

        return batchAddRoleMenu(newOrgRoleMenus);
    }

    private void assembleOrgRoleMenuList(final List<OrgRoleMenu> newOrgRoleMenus, String[] menuIds, Integer roleId) {
        if (ArrayUtil.isEmptyArray(menuIds)) {
            return;
        }
        for (String newMenuId : menuIds) {
            OrgRoleMenu orgRoleMenu = new OrgRoleMenu();
            orgRoleMenu.setOrgRoleId(roleId);
            orgRoleMenu.setOrgRoleMenuId(newMenuId);
            newOrgRoleMenus.add(orgRoleMenu);
        }

    }

    public void batchDeleteRoleMenu(List<OrgRoleMenu> orgRoleMenuList) {
        roleMenuManager.batchDelete(orgRoleMenuList);
    }

    public void copyRoleMenu(Integer orgRoleIdNew, Integer orgRoleId) {
        List<OrgRoleMenu> orgRoleMenuList = roleMenuManager.findMenuIds(orgRoleId);
        for (OrgRoleMenu roleMenu : orgRoleMenuList) {
            roleMenu.setOrgRoleId(orgRoleIdNew);
            roleMenu.setId(null);
        }
        if (!CollectionUtil.isEmpty(orgRoleMenuList)) {
            roleMenuManager.batchAdd(orgRoleMenuList);
        }
    }
    @Transactional(readOnly = true)
    public List<OrgRole> findRoleByUserId(String userId) {
        List<OrgRoleUser> orgRoleUserList = roleUserManager.findListByUserIds(userId);
        List<OrgRole> roleList = new ArrayList<OrgRole>();
        for (OrgRoleUser orgRoleUser : orgRoleUserList) {
            OrgRole role = findRole(orgRoleUser.getOrgRoleId());
            roleList.add(role);
        }
        return roleList;
    }
    @Transactional(readOnly = true)
    public List<OrgRoleUser> findUserByRoleId(Integer roleId) {
        return roleUserManager.findUserIds(roleId);
    }

    public void addRoleUser(String[] userIds, Integer roleId) {
        if (ArrayUtil.isEmptyArray(userIds)) {
            return;
        }
        roleUserManager.addRoleUser(userIds, roleId);
    }

    public void batchAddRolesToUser(String userId, String[] roleIds) {
        if (ArrayUtil.isEmptyArray(roleIds)) {
            return;
        }
        roleUserManager.batchAddRolesToUser(userId, roleIds);

    }

    public void batchAddRoleUser(List<OrgRoleUser> orgRoleUserList) {
        roleUserManager.batchAdd(orgRoleUserList);
    }

    public OrgRoleUser updateRoleUser(OrgRoleUser orgRoleUser) {
        return roleUserManager.update(orgRoleUser);
    }

    public Integer deleteRoleUser(Integer id) {
        return roleUserManager.delete(id);
    }

    public void copyRoleUser(Integer orgRoleIdNew, Integer orgRoleId) {
        List<OrgRoleUser> orgRoleUserList = roleUserManager.findUserIds(orgRoleId);
        for (OrgRoleUser roleUser : orgRoleUserList) {
            roleUser.setOrgRoleId(orgRoleIdNew);
            roleUser.setId(null);
        }
        if (!CollectionUtils.isEmpty(orgRoleUserList)) {
            roleUserManager.batchAdd(orgRoleUserList);
        }
    }
    @Transactional(readOnly = true)
    public List<OrgRole> getRoleByIds(String[] ids) {
        if (ArrayUtil.isEmptyArray(ids)) {
            return new ArrayList<OrgRole>();
        }
        return roleManager.getRolesByIds(ids);
    }

    public List<OrgRole> findSystemRoles() {
        OrgRole role = new OrgRole();
        role.setDeleted(0);
        role.setOrgRoleType(OrgRole.ROLE_TYPE_SYS);
        return findRoleList(role);
    }
    @Transactional(readOnly = true)
    public List<OrgRole> roleInCondition(String condition, String type, Integer limit) {
        return roleManager.roleInCondition(condition, type, limit);
    }

}