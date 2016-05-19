/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/gpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tinygroup.sdpm.org.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.security.Digests;
import org.tinygroup.sdpm.common.util.Encodes;
import org.tinygroup.sdpm.org.biz.inter.UserManager;
import org.tinygroup.sdpm.org.dao.OrgUserDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserManagerImpl implements UserManager {
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    @Autowired
    private OrgUserDao orgUserDao;

    public OrgUser find(String id) {
        if (StringUtil.isBlank(id)) {
            return null;
        }
        try {
            return orgUserDao.getByKey(id);
        } catch (Exception e) {
            return null;
        }
    }

    public OrgUser findUserByAccount(String account) {
        OrgUser orgUser = new OrgUser();
        orgUser.setOrgUserAccount(account);
        List<OrgUser> list = orgUserDao.query(orgUser);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    public Pager<OrgUser> findPager(Integer start, Integer limit, OrgUser orgUser) {
        return orgUserDao.queryPager(start, limit, orgUser);
    }

    public List<OrgUser> findList(OrgUser orgUser) {
        return orgUserDao.query(orgUser);
    }

    public Pager<OrgUser> findUserListByDeptId(Integer start, Integer limit, Integer deptId) {
        return orgUserDao.getPagerByDeptId(start, limit, deptId);
    }

    public OrgUser add(OrgUser orgUser) {
        return orgUserDao.add(orgUser);
    }

    public OrgUser update(OrgUser orgUser) {
        orgUserDao.edit(orgUser);
        return orgUser;
    }

    public Integer delete(String id) {
        OrgUser orgUser = orgUserDao.getByKey(id);
        // OrgUser orgUser = new OrgUser();
        // orgUser.setOrgUserId(id);
        orgUser.setOrgUserDeleted(OrgUser.DELETE_YES);
        return orgUserDao.edit(orgUser);
    }

    public int[] deleteBatch(List<OrgUser> list) {
        return orgUserDao.softDeleteBatch(list);
    }

    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    public String encryptPassword(String plainPassword) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
    }

    /**
     * 验证密码
     *
     * @param plainPassword 明文密码
     * @param password      密文密码
     * @return 验证成功返回true
     */
    public boolean validatePassword(String plainPassword, String password) {
        byte[] salt = Encodes.decodeHex(password.substring(0, 16));
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword));
    }

    public String getNameById(String id) {
        return orgUserDao.getNameById(id);
    }

    public List<OrgUser> findUserListByIds(String... userId) {
        return orgUserDao.getByKeys(userId);
    }

    public List<OrgUser> findTeamUserListByProjectId(Integer projectId) {
        if (projectId == null) {
            return new ArrayList<OrgUser>();
        }
        return orgUserDao.getTeamUserByProjectId(projectId);
    }

    public List<OrgUser> userInCondition(String condition, Integer limit, String[] ids) {
        return orgUserDao.userInCondition(condition, limit, ids);
    }

    public List<OrgUser> getDirectStaffByLeader(String leaderUserId) {
        return orgUserDao.getDirectStaffByLeader(leaderUserId);
    }

    @Override
    public List<OrgUser> getDirectStaffByLeaderAndSelf(String leaderUserId) {
        return orgUserDao.getDirectStaffByLeaderAndSelf(leaderUserId);
    }

    public List<OrgUser> getAllStaffByLeader(String leaderUserId) {
        List<OrgUser> staffList = new ArrayList<OrgUser>();
        getAllStaffByLeader(staffList, leaderUserId);
        return staffList;
    }

    private void getAllStaffByLeader(List<OrgUser> staffList, String leaderUserId) {
        if (staffList == null) {
            staffList = new ArrayList<OrgUser>();
        }
        List<OrgUser> directStaffByLeader = getDirectStaffByLeader(leaderUserId);
        if (directStaffByLeader != null) {
            staffList.addAll(directStaffByLeader);
            for (OrgUser orgUser : directStaffByLeader) {
                getAllStaffByLeader(staffList, orgUser.getOrgUserId());
            }
        }
    }

    @Override
    public List<OrgUser> getUserListById(List<String> list) {
        if (list == null) {
            list = new ArrayList<String>();
        }
        return orgUserDao.getListById(list);
    }

    @Override
    public List<OrgUser> getWhiteUserList(String userAccount) {
        return orgUserDao.getWhiteListById(userAccount);
    }
}
