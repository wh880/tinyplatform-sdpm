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

import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
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

}
