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
import org.tinygroup.sdpm.org.biz.inter.UserManager;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserManager userManager;

    /**
     * 根据主键id查找用户
     *
     * @param id
     * @return
     */
    public OrgUser findUser(String id) {
        return userManager.find(id);
    }

    /**
     * 根据用户名刚查找用户
     *
     * @param account 用户名
     * @return
     */
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
    public Pager<OrgUser> findUserPager(Integer start, Integer limit, OrgUser orgUser) {
        return userManager.findPager(start, limit, orgUser);
    }

    /**
     * 根据条件查询用户List
     *
     * @param orgUser 用于查询条件
     * @return
     */
    public List<OrgUser> findUserList(OrgUser orgUser) {
        return userManager.findList(orgUser);
    }

    /**
     * 根据部门Id查询用户pager
     *
     * @param deptId
     * @return
     */
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
//        String password = orgUser.getOrgUserPassword();
//        orgUser.setOrgUserPassword(userManager.encryptPassword(password));
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
    public boolean validatePassword(String plainPassword, String password) {
        return userManager.validatePassword(plainPassword, password);
    }

    /**
     * 根据用户id数组查出用户list
     *
     * @param userId
     * @return
     */
    public List<OrgUser> findUserListByIds(String[] userId) {
        return userManager.findUserListByIds(userId);
    }

    public List<OrgUser> findTeamUserListByProjectId(Integer projectId) {
        return userManager.findTeamUserListByProjectId(projectId);
    }

    public List<OrgUser> userInCondition(String condition, String[] ids) {
        return userManager.userInCondition(condition, ids);
    }
}