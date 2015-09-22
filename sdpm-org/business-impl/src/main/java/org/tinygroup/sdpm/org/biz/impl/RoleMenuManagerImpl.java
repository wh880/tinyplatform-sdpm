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
package org.tinygroup.sdpm.org.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.org.biz.inter.RoleMenuManager;
import org.tinygroup.sdpm.org.dao.OrgRoleMenuDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu;


@Service
@Transactional
public class RoleMenuManagerImpl implements RoleMenuManager {
    @Autowired
    private OrgRoleMenuDao orgRoleMenuDao;

    public OrgRoleMenu find(Integer id) {
        return orgRoleMenuDao.getByKey(id);
    }

    public OrgRoleMenu add(OrgRoleMenu orgRoleMenu) {
        return orgRoleMenuDao.add(orgRoleMenu);
    }

    public OrgRoleMenu update(OrgRoleMenu orgRoleMenu) {
        orgRoleMenuDao.edit(orgRoleMenu);
        return orgRoleMenu;
    }

    public Integer delete(Integer id) {
        return 0;
    }
}
