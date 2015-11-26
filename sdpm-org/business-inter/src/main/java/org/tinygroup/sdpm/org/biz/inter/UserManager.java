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
package org.tinygroup.sdpm.org.biz.inter;

import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface UserManager {
    /**
     * 根据主键id查找用户
     *
     * @param id 主键
     * @return
     */
    OrgUser find(String id);

    /**
     * 根据主键id查找用户
     *
     * @param account 用户名
     * @return
     */
    OrgUser findUserByAccount(String account);

    /**
     * 根据主键id查找用户
     *
     * @return
     */
    Pager<OrgUser> findPager(Integer start, Integer limit, OrgUser orgUser);

    /**
     * 根据条件查询List
     *
     * @param orgUser 用于查询条件
     * @return
     */
    List<OrgUser> findList(OrgUser orgUser);


    /**
     * 根据部门查询用户list
     *
     * @param deptId
     * @return
     */
    Pager<OrgUser> findUserListByDeptId(Integer start, Integer limit, Integer deptId);

    /**
     * 新增有一个用户
     *
     * @param orgUser 新增实体类
     * @return
     */
    OrgUser add(OrgUser orgUser);

    /**
     * 更新用户
     *
     * @param orgUser 需要更新的实体类
     * @return
     */
    OrgUser update(OrgUser orgUser);

    /**
     * 根据id进行软删除用户
     *
     * @param id 主键
     * @return
     */
    Integer delete(String id);

    /**
     * 批量删除用户
     *
     * @param list
     * @return
     */
    int[] deleteBatch(List<OrgUser> list);

    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     */
    String encryptPassword(String plainPassword);

    /**
     * 验证密码
     *
     * @param plainPassword 明文密码
     * @param password      密文密码
     * @return 验证成功返回true
     */
    boolean validatePassword(String plainPassword, String password);

    /**
     * 根据id查找名字
     *
     * @param id
     * @return
     */
    String getNameById(String id);

    /**
     * 根据用户id数组查出用户list
     *
     * @param
     * @return
     */
    List<OrgUser> findUserListByIds(String... userId);

    /**
     * 根据项目id查找团队成员list
     *
     * @param
     * @return
     */
    List<OrgUser> findTeamUserListByProjectId(Integer projectId);

    /**
     * 根据名称条件查询
     * @param condition
     * @return
     */
    List<OrgUser> userInCondition(String condition,String ...ids);

}
