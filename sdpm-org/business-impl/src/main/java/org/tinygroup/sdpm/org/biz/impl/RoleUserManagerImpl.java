package org.tinygroup.sdpm.org.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.org.biz.inter.RoleUserManager;
import org.tinygroup.sdpm.org.dao.OrgRoleUserDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-10-12.
 */
@Service
@Transactional
public class RoleUserManagerImpl implements RoleUserManager {
    @Autowired
    OrgRoleUserDao orgRoleUserDao;

    public OrgRoleUser find(Integer id) {
        return orgRoleUserDao.getByKey(id);
    }

    public List<OrgRoleUser> findUserIds(Integer id) {
        return orgRoleUserDao.getByRoleId(id);
    }

    public OrgRoleUser add(OrgRoleUser orgRoleUser) {
        return orgRoleUserDao.add(orgRoleUser);
    }

    public void addRoleUser(String[] array, Integer roleId) {
        orgRoleUserDao.deleteByRoleId(roleId);
        List<OrgRoleUser> list = new ArrayList<OrgRoleUser>();
        for (String userId : array) {
            OrgRoleUser t = new OrgRoleUser();
            t.setOrgUserId(userId);
            t.setOrgRoleId(roleId);
            list.add(t);
        }
        if (list != null || !list.isEmpty()) {
            orgRoleUserDao.batchInsert(list);
        }
    }

    public OrgRoleUser update(OrgRoleUser orgRoleUser) {
        orgRoleUserDao.edit(orgRoleUser);
        return orgRoleUser;
    }

    public Integer delete(Integer id) {
        return 0;
    }

    public void batchAdd(List<OrgRoleUser> orgRoleUserList) {
        orgRoleUserDao.batchInsert(orgRoleUserList);
    }
}
