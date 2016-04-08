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
package org.tinygroup.sdpm.org.service.inter;

import org.tinygroup.aopcache.annotation.CacheGet;
import org.tinygroup.aopcache.annotation.CacheRemove;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface UserService {
    String CACHE_USER_ID = "userId";
    String CACHE_USER_ACCOUNT = "userAccount";
    String CACHE_USER_LIST = "userList";

    /**
     * 根据主键id查找用户
     *
     * @param id
     * @return
     */
    @CacheGet(key = "${id}", group = CACHE_USER_ID)
    OrgUser findUser(String id);

    /**
     * 根据用户名刚查找用户
     *
     * @param account 用户名
     * @return
     */
    @CacheGet(key = "${account}", group = CACHE_USER_ACCOUNT)
    OrgUser findUserByAccount(String account);

    /**
     * 对象查询用户pager
     *
     * @param start
     * @param limit
     * @param orgUser
     * @return
     */
    Pager<OrgUser> findUserPager(Integer start, Integer limit, OrgUser orgUser);

    /**
     * 根据条件查询用户List
     *
     * @param orgUser 用于查询条件
     * @return
     */
//    @CacheGet(key = "list-${orgUser?.orgUserId}-${orgUser?.orgUserAccount}", group = CACHE_USER_LIST)
    List<OrgUser> findUserList(OrgUser orgUser);

    /**
     * 根据部门Id查询用户pager
     *
     * @param deptId
     * @return
     */
    Pager<OrgUser> findUserByDeptId(Integer start, Integer limit, Integer deptId);

    /**
     * 新增有一个用户
     *
     * @param orgUser 新增实体类
     * @return
     */
    @CacheRemove(removeGroups = {CACHE_USER_LIST})
    OrgUser addUser(OrgUser orgUser);

    /**
     * 更新用户
     *
     * @param orgUser 需要更新的实体类
     * @return
     */
    @CacheRemove(removeKeys = "${orgUser?.orgUserId}", group = CACHE_USER_ID, removeGroups = {CACHE_USER_ACCOUNT, CACHE_USER_LIST})
    OrgUser updateUser(OrgUser orgUser);

    /**
     * 根据id进行软删除用户
     *
     * @param id 主键
     * @return
     */
    @CacheRemove(removeKeys = "${id}", group = CACHE_USER_ID, removeGroups = {CACHE_USER_ID, CACHE_USER_ACCOUNT, CACHE_USER_LIST})
    Integer deleteUser(String id);

    /**
     * 批量删除用户
     *
     * @param list
     * @return
     */
    int[] deleteBatchUser(List<OrgUser> list);

    /**
     * 验证密码
     *
     * @param plainPassword 明文密码
     * @param password      密文密码
     * @return 验证成功返回true
     */
    Boolean validatePassword(String plainPassword, String password);

    /**
     * 根据用户id数组查出用户list
     *
     * @param
     * @return
     */
    List<OrgUser> findUserListByIds(String[] userId);

    /**
     * 根据项目id查找团队成员list
     *
     * @param
     * @return
     */
    List<OrgUser> findTeamUserListByProjectId(Integer projectId);

    /**
     * 根据名称条件查询
     *
     * @param condition
     * @return
     */
    List<OrgUser> userInCondition(String condition, Integer limit, String[] ids);

    /**
     * 查询直接下级
     */
    List<OrgUser> findOrgUserListSubordinate(String userId);

    /**
     * 查询直接下级与自己
     */
    List<OrgUser> findOrgUserListSubordinateAndSelf(String userId);

    /**
     * 查询所有的下级
     *
     * @param userId
     * @return
     */
    List<OrgUser> findAllSubordinate(String userId);

    /**
     * 查询所有的白名单用户
     */
    List<OrgUser> findWhiteUser(String userAccount);
}
