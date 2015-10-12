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

import org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu;

import java.util.List;

public interface RoleMenuService {
    /**
     * 根据主键id查找角色菜单
     *
     * @param id 主键
     * @return
     */
    OrgRoleMenu findRoleMenu(Integer id);

    /**
     * 根据角色ID查找菜单
     *
     * @param roleId
     * @return
     */
    List<OrgRoleMenu> findMenuByRoleId(Integer roleId);


    OrgRoleMenu findRoleMenuId(String id);

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
     * @param roleId
     */
    void batchDeleteRoleMenu(List<OrgRoleMenu> orgRoleMenuList);
}
