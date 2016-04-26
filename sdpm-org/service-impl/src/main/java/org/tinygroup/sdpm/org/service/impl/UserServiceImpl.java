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
package org.tinygroup.sdpm.org.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.org.biz.inter.UserManager;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Component
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserManager userManager;

    /**
     * 根据主键id查找用户
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public OrgUser findUser(String id) {
        if (StringUtil.isBlank(id)) {
            return null;
        }
        return userManager.find(id);
    }

    /**
     * 根据用户名刚查找用户
     *
     * @param account 用户名
     * @return
     */
    @Transactional(readOnly = true)
    public OrgUser findUserByAccount(String account) {
        return userManager.findUserByAccount(account);
    }

    /**
     * 对象查询用户pager
     *
     * @param start
     * @param limit
     * @param orgUser
     * @return
     */
    @Transactional(readOnly = true)
    public Pager<OrgUser> findUserPager(Integer start, Integer limit, OrgUser orgUser) {
        return userManager.findPager(start, limit, orgUser);
    }

    /**
     * 根据条件查询用户List
     *
     * @param orgUser 用于查询条件
     * @return
     */
    @Transactional(readOnly = true)
    public List<OrgUser> findUserList(OrgUser orgUser) {
        return userManager.findList(orgUser);
    }

    /**
     * 根据部门Id查询用户pager
     *
     * @param deptId
     * @return
     */
    @Transactional(readOnly = true)
    public Pager<OrgUser> findUserByDeptId(Integer start, Integer limit, Integer deptId) {
        return userManager.findUserListByDeptId(start, limit, deptId);
    }

    /**
     * 新增有一个用户
     *
     * @param orgUser 新增实体类
     * @return
     */
    public OrgUser addUser(OrgUser orgUser) {
        String password = orgUser.getOrgUserPassword();
        orgUser.setOrgUserPassword(userManager.encryptPassword(password));
        return userManager.add(orgUser);
    }

    /**
     * 更新用户
     *
     * @param orgUser 需要更新的实体类
     * @return
     */
    public OrgUser updateUser(OrgUser orgUser) {
        if (!StringUtil.isBlank(orgUser.getOrgUserPassword())) {
            String password = orgUser.getOrgUserPassword();
            orgUser.setOrgUserPassword(userManager.encryptPassword(password));
        }
        return userManager.update(orgUser);
    }

    /**
     * 根据id进行软删除用户
     *
     * @param id 主键
     * @return
     */
    public Integer deleteUser(String id) {
        return userManager.delete(id);
    }

    /**
     * 批量删除用户
     *
     * @param list
     * @return
     */
    public int[] deleteBatchUser(List<OrgUser> list) {
        return userManager.deleteBatch(list);
    }

    /**
     * 验证密码
     *
     * @param plainPassword 明文密码
     * @param password      密文密码
     * @return 验证成功返回true
     */
    public Boolean validatePassword(String plainPassword, String password) {
        if (StringUtil.isBlank(plainPassword) || StringUtil.isBlank(password)) {
            return false;
        }
        return userManager.validatePassword(plainPassword, password);
    }

    /**
     * 根据用户id数组查出用户list
     *
     * @param userId
     * @return
     */
    @Transactional(readOnly = true)
    public List<OrgUser> findUserListByIds(String[] userId) {
        return userManager.findUserListByIds(userId);
    }
    @Transactional(readOnly = true)
    public List<OrgUser> findTeamUserListByProjectId(Integer projectId) {
        return userManager.findTeamUserListByProjectId(projectId);
    }
    @Transactional(readOnly = true)
    public List<OrgUser> userInCondition(String condition, Integer limit, String[] ids) {
        return userManager.userInCondition(condition, limit, ids);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrgUser> findOrgUserListSubordinate(String userId) {
        return userManager.getDirectStaffByLeader(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrgUser> findOrgUserListSubordinateAndSelf(String userId) {
        return userManager.getDirectStaffByLeaderAndSelf(userId);
    }

    @Override
    public List<OrgUser> findAllSubordinate(String userId) {
        return userManager.getAllStaffByLeader(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrgUser> findWhiteUser(String userAccount) {
        return userManager.getWhiteUserList(userAccount);
    }
}