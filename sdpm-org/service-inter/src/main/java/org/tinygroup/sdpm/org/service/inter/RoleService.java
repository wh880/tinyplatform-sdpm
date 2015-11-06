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

import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;


public interface RoleService {
    /**
     * 根据主键id查找Role
     *
     * @param id 主键
     * @return
     */
    OrgRole findRole(Integer id);

    Pager<OrgRole> findRolePager(Integer start, Integer limit, OrgRole orgRole);

    /**
     * 根据条件查询List
     *
     * @param orgRole 用于查询条件
     * @return
     */
    List<OrgRole> findRoleList(OrgRole orgRole);

    /**
     * 新增有一个Role
     *
     * @param orgRole 新增实体类
     * @return
     */
    OrgRole addRole(OrgRole orgRole);

    /**
     * 更新Role
     *
     * @param orgRole 需要更新的实体类
     * @return
     */
    OrgRole updateRole(OrgRole orgRole);

    /**
     * 根据id进行软删除Role
     *
     * @param id 主键
     * @return
     */
    Integer deleteRole(Integer id);


    /**
     * 找出用户的所有菜单Id
     * @param userId
     * @return
     */
    List<OrgRoleMenu> findRoleMenuListByUser(String userId);

    /**
     * 根据角色ID查找菜单
     *
     * @param roleId
     * @return
     */
    List<OrgRoleMenu> findMenuByRoleId(Integer roleId);


    /**
     * 新增有一个角色菜单
     *
     * @param orgRoleMenu 新增实体类
     * @return
     */
    OrgRoleMenu addRoleMenu(OrgRoleMenu orgRoleMenu);

    /**
     * 更新角色
     *
     * @param orgRoleMenu 需要更新的实体类
     * @return
     */
    OrgRoleMenu updateRoleMenu(OrgRoleMenu orgRoleMenu);

    /**
     * 根据id进行软删除角色菜单
     *
     * @param id 主键
     * @return
     */
    Integer deleteRoleMenu(Integer id);

    void batchAddRoleMenu(List<OrgRoleMenu> orgRoleMenuList);

    /**
     * 根据角色Id删除该角色的权限
     *
     * @param
     */
    void batchDeleteRoleMenu(List<OrgRoleMenu> orgRoleMenuList);

    /**
     * @param orgRoleIdNew
     * @param orgRoleId
     */
    void copyRoleMenu(Integer orgRoleIdNew, Integer orgRoleId);


    /**
     * 根据用户Id查找角色
     *
     * @param userId
     * @return
     */
    List<OrgRole> findRoleByUserId(String userId);

    /**
     * 根据角色id查找用户列表
     *
     * @param roleId
     * @return
     */
    List<OrgRoleUser> findUserByRoleId(Integer roleId);

    /**
     * 批量添加角色成员
     * @param userIds 用户Ids
     * @param roleId 角色id
     */
    void addRoleUser(String[] userIds, Integer roleId);

    /**
     * 批量添加用色用户
     *
     * @param orgRoleUserList
     */
    void batchAddRoleUser(List<OrgRoleUser> orgRoleUserList);

    /**
     * 更新角色用户
     *
     * @param orgRoleUser
     * @return
     */
    OrgRoleUser updateRoleUser(OrgRoleUser orgRoleUser);


    /***
     * 根据id删除用户角色
     *
     * @param id
     * @return
     */
    Integer deleteRoleUser(Integer id);

    /**
     * 角色复制下面的用户复制
     *
     * @param orgRoleIdNew
     * @param orgRoleId
     */
    void copyRoleUser(Integer orgRoleIdNew, Integer orgRoleId);

    List<OrgRole> getRoleByIds(String...ids);

}
