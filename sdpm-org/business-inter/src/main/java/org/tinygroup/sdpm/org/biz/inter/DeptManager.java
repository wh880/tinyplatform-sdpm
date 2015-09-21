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

import org.tinygroup.sdpm.org.dao.pojo.OrgDept;

import java.util.List;

public interface DeptManager {
    /**
     * 根据主键id查找部门
     *
     * @param id 主键
     * @return
     */
    OrgDept find(Integer id);

    /**
     * 根据条件查询List
     *
     * @param orgDept 用于查询条件
     * @return
     */
    List<OrgDept> findList(OrgDept orgDept);

    /**
     * 新增有一个部门
     *
     * @param orgDept 新增实体类
     * @return
     */
    OrgDept add(OrgDept orgDept);

    /**
     * 更新部门
     *
     * @param orgDept 需要更新的实体类
     * @return
     */
    OrgDept update(OrgDept orgDept);

    /**
     * 根据id进行软删除部门
     *
     * @param id 主键
     * @return
     */
    Integer delete(Integer id);

}
