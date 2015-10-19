/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p/>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.gnu.org/licenses/gpl.html
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.tinygroup.sdpm.org.service.inter;

import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface UserService {
    /**
     * 根据主键id查找用户
     *
     * @param id 主键
     * @return
     */
    OrgUser findUser(String id);

    /**
     * 根据主键id查找用户
     *
     * @param account 用户名
     * @return
     */
    OrgUser findUserByAccount(String account);

    Pager<OrgUser> findUserPager(Integer start, Integer limit, OrgUser orgUser);

    /**
     * 根据条件查询List
     *
     * @param orgUser 用于查询条件
     * @return
     */
    List<OrgUser> findUserList(OrgUser orgUser);

    /**
     * 根据部门Id查询list
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
    OrgUser addUser(OrgUser orgUser);

    /**
     * 更新用户
     *
     * @param orgUser 需要更新的实体类
     * @return
     */
    OrgUser updateUser(OrgUser orgUser);

    /**
     * 根据id进行软删除用户
     *
     * @param id 主键
     * @return
     */
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
    boolean validatePassword(String plainPassword, String password);

    /**
     * 根据用户id数组查出用户list
     *
     * @param
     * @return
     */
    List<OrgUser> findUserListByIds(String... storyId);
    
    /**
     * 根据id查找名字
     * @param id
     * @return
     */
    String getNameById(String id);

}
