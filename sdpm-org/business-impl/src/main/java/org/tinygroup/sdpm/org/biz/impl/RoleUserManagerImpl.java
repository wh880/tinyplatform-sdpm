package org.tinygroup.sdpm.org.biz.impl;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.org.biz.inter.RoleUserManager;
import org.tinygroup.sdpm.org.dao.OrgRoleUserDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015-10-12.
 */
@Service
public class RoleUserManagerImpl implements RoleUserManager {
    @Autowired
    OrgRoleUserDao orgRoleUserDao;

    public OrgRoleUser find(Integer id) {
        return orgRoleUserDao.getByKey(id);
    }

    public List<OrgRoleUser> findUserIds(Integer id) {
        return orgRoleUserDao.getByRoleId(id);
    }

    public List<OrgRoleUser> findListByUserIds(String userId) {
        OrgRoleUser orgRoleUser = new OrgRoleUser();
        orgRoleUser.setOrgUserId(userId);
        return orgRoleUserDao.query(orgRoleUser);
    }

    public OrgRoleUser add(OrgRoleUser orgRoleUser) {
        return orgRoleUserDao.add(orgRoleUser);
    }

    public void addRoleUser(String[] userIds, Integer roleId) {
        orgRoleUserDao.deleteByRoleId(roleId);
        List<OrgRoleUser> list = new ArrayList<OrgRoleUser>();
        for (String userId : userIds) {
            OrgRoleUser t = new OrgRoleUser();
            t.setOrgUserId(userId);
            t.setOrgRoleId(roleId);
            list.add(t);
        }
        if (!list.isEmpty()) {
            orgRoleUserDao.batchInsert(list);
        }
    }

    public Integer batchAddRolesToUser(String userId, String[] roleIds) {
        orgRoleUserDao.deleteByUserId(userId);
        if (StringUtil.isBlank(userId) || ArrayUtils.isEmpty(roleIds)) {
            return 0;
        }
        OrgRoleUser orgRoleUser = new OrgRoleUser();
        orgRoleUser.setOrgUserId(userId);
        List<OrgRoleUser> list = new ArrayList<OrgRoleUser>();
        for (String roleId : roleIds) {
            if (StringUtil.isBlank(roleId)) {
                continue;
            }
            Integer role = Integer.valueOf(roleId);
            OrgRoleUser t = new OrgRoleUser();
            t.setOrgUserId(userId);
            t.setOrgRoleId(role);
            list.add(t);
        }
        Integer len = 0;
        if (!list.isEmpty()) {
            int[] batchInsert = orgRoleUserDao.batchInsert(list);
            if (!ArrayUtils.isEmpty(batchInsert)) {
                len = batchInsert.length;
            }
        }
        return len;
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

    public List<OrgRoleUser> getRolesByIds(String... ids) {
        return orgRoleUserDao.getRolesByIds(ids);
    }
}
