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

import org.tinygroup.sdpm.org.dao.pojo.OrgCompany;

import java.util.List;

public interface CompanyManager {
    /**
     * 根据主键id查找公司
     *
     * @param id 主键
     * @return
     */
    OrgCompany find(Integer id);

    /**
     * 新增一个公司
     *
     * @param orgCompany 新增实体类
     * @return
     */
    OrgCompany add(OrgCompany orgCompany);

    /**
     * 更新公司
     *
     * @param orgCompany 需要更新的实体类
     * @return
     */
    OrgCompany update(OrgCompany orgCompany);

    /**
     * 根据id进行软删除公司
     * <p>
     * ram id 主键
     *
     * @return
     */
    Integer delete(Integer id);

    /**
     * 查找表中的公司记录
     *
     * @return
     */
    List<OrgCompany> findList();


}
