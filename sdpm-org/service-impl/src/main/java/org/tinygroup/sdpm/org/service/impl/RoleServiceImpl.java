package org.tinygroup.sdpm.org.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleManager roleManager;

    @Autowired
    private RoleMenuManager roleMenuManager;

    @Autowired
    private RoleUserManager roleUserManager;

    public OrgRole findRole(Integer id) {
        return roleManager.find(id);
    }

    public Pager<OrgRole> findRolePager(Integer start, Integer limit, OrgRole orgRole) {
        return roleManager.findPager(start, limit, orgRole);
    }

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

    public List<OrgRoleMenu> findRoleMenuListByUser(String userId) {
        return roleMenuManager.findMenuListByUser(userId);
    }

    public List<OrgRoleMenu> findMenuByRoleId(Integer roleId) {
        return roleMenuManager.findMenuIds(roleId);
    }

    public OrgRoleMenu findRoleMenuId(String id) {
        return roleMenuManager.findId(id);
    }

    public OrgRoleMenu addRoleMenu(OrgRoleMenu orgRoleMenu) {
        return roleMenuManager.add(orgRoleMenu);
    }

    public void batchAddRoleMenu(List<OrgRoleMenu> orgRoleMenuList) {
        roleMenuManager.batchAdd(orgRoleMenuList);
    }

    public OrgRoleMenu updateRoleMenu(OrgRoleMenu orgRoleMenu) {
        return roleMenuManager.update(orgRoleMenu);
    }

    public Integer deleteRoleMenu(Integer id) {
        return roleMenuManager.delete(id);
    }

    public void batchDeleteRoleMenu(List<OrgRoleMenu> orgRoleMenuList) {
        roleMenuManager.batchDelete(orgRoleMenuList);
    }

    public void copyRoleMenu(Integer orgRoleIdNew, Integer orgRoleId) {
        List<OrgRoleMenu> orgRoleMenuList = roleMenuManager.findMenuIds(orgRoleId);
        for (OrgRoleMenu roleMenu : orgRoleMenuList) {
            roleMenu.setOrgRoleId(orgRoleIdNew);
        }
        if (orgRoleMenuList != null || !orgRoleMenuList.isEmpty()) {
            roleMenuManager.batchAdd(orgRoleMenuList);
        }
    }

    public List<OrgRole> findRoleByUserId(String userId) {
        List<OrgRoleUser> orgRoleUserList = roleUserManager.findListByUserIds(userId);
        List<OrgRole> roleList = new ArrayList<OrgRole>();
        for (OrgRoleUser orgRoleUser : orgRoleUserList) {
            OrgRole role = findRole(orgRoleUser.getOrgRoleId());
            roleList.add(role);
        }
        return roleList;
    }



    public List<OrgRoleUser> findUserByRoleId(Integer roleId) {
        return roleUserManager.findUserIds(roleId);
    }

    public void addRoleUser(String[] userIds, Integer roleId) {
        roleUserManager.addRoleUser(userIds, roleId);
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
        }
        if (orgRoleUserList != null || !orgRoleUserList.isEmpty()) {
            roleUserManager.batchAdd(orgRoleUserList);
        }
    }

    public List<OrgRole> getRoleByIds(String... ids) {
        return roleManager.getRolesByIds(ids);
    }

}