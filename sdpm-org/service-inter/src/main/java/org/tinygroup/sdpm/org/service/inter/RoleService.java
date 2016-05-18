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
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleUser;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;


public interface RoleService {
    String CACHE_ROLE_LIST = "roleList";
    String CACHE_ROLE_ID = "roleId";

    /**
     * 根据主键id查找Role
     *
     * @param id 主键
     * @return
     */
    OrgRole findRole(Integer id);

    /**
     * 分页查询
     *
     * @param start
     * @param limit
     * @param orgRole
     * @return
     */
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
    @CacheRemove(removeKeys = "${orgRole?.orgRoleId}", group = CACHE_ROLE_ID, removeGroups = {CACHE_ROLE_ID, CACHE_ROLE_LIST})
    OrgRole updateRole(OrgRole orgRole);

    /**
     * 根据id进行软删除Role
     *
     * @param id 主键
     * @return
     */
    @CacheRemove(removeKeys = "${id}", group = CACHE_ROLE_ID, removeGroups = {CACHE_ROLE_ID, CACHE_ROLE_LIST})
    Integer deleteRole(Integer id);

    /**
     * 保存角色在当前模块的菜单，若数据库已经存在，则清除原有的模块的数据后再新增数据。
     *
     * @param roleId
     * @param parentMenuId 当前模块的id
     * @param newMenuIds   当前模块中需要保存的菜单
     * @return
     */
    Integer saveRoleMenu(Integer roleId, String parentMenuId, String[] newMenuIds);

    /**
     * 找出用户的所有菜单Id
     *
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


    Integer batchAddRoleMenu(List<OrgRoleMenu> orgRoleMenuList);

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
     *
     * @param userIds 用户Ids
     * @param roleId  角色id
     */
    void addRoleUser(String[] userIds, Integer roleId);

    /**
     * 批量添加角色成员
     *
     * @param userId  用户Id
     * @param roleIds 角色ids
     */
    void batchAddRolesToUser(String userId, String[] roleIds);

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

    /**
     * 根据ids查询
     *
     * @param ids
     * @return
     */
    List<OrgRole> getRoleByIds(String[] ids);

    /**
     * 查询系统角色
     *
     * @return
     */
    @CacheGet(key = "systemRoleList", group = CACHE_ROLE_LIST)
    List<OrgRole> findSystemRoles();

    /**
     * 根据输入查询角色-可分类型
     *
     * @param condition
     * @param type
     * @return
     */
    List<OrgRole> roleInCondition(String condition, String type, Integer limit);

}
