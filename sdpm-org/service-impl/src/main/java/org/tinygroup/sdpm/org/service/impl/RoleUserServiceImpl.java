package org.tinygroup.sdpm.org.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.org.biz.inter.RoleUserManager;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser;
import org.tinygroup.sdpm.org.service.inter.RoleUserService;

import java.util.List;

/**
 * Created by Administrator on 2015-10-12.
 */
@Component
public class RoleUserServiceImpl implements RoleUserService {
    @Autowired
    RoleUserManager roleUserManager;

    public OrgRoleUser findRoleUser(Integer id) {
        return roleUserManager.find(id);
    }

    public List<OrgRoleUser> findUserByRoleId(Integer roleId) {
        return roleUserManager.findUserIds(roleId);
    }

    public void addRoleUser(String[] array, Integer roleId) {
        roleUserManager.addRoleUser(array, roleId);
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
}
