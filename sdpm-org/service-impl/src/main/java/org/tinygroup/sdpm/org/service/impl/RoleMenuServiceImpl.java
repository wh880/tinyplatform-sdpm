
package org.tinygroup.sdpm.org.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.org.biz.inter.RoleMenuManager;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu;
import org.tinygroup.sdpm.org.service.inter.RoleMenuService;

import java.util.List;

@Component
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuManager roleMenuManager;

    public OrgRoleMenu findRoleMenu(Integer id) {
        return roleMenuManager.find(id);
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
}