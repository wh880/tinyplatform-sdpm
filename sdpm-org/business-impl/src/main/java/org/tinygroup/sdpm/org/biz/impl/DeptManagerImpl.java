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
import org.tinygroup.sdpm.org.biz.inter.DeptManager;
import org.tinygroup.sdpm.org.dao.OrgDeptDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgDept;

import java.util.List;

@Service
public class DeptManagerImpl implements DeptManager {
    @Autowired
    private OrgDeptDao orgDeptDao;

    public OrgDept find(Integer id) {
        return orgDeptDao.getByKey(id);
    }

    public List<OrgDept> findList(OrgDept orgDept) {
        return orgDeptDao.query(orgDept);
    }

    public OrgDept add(OrgDept orgDept) {
        return orgDeptDao.add(orgDept);
    }

    public OrgDept update(OrgDept orgDept) {
        orgDeptDao.edit(orgDept);
        return orgDept;
    }

    public Integer delete(Integer id) {
        OrgDept orgDept = orgDeptDao.getByKey(id);
        orgDept.setOrgDeptParent(-2);
//        return orgDeptDao.deleteByKey(id);
        return orgDeptDao.edit(orgDept);
    }
}
