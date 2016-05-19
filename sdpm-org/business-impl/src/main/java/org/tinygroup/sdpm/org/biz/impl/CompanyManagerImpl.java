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
import org.tinygroup.sdpm.org.biz.inter.CompanyManager;
import org.tinygroup.sdpm.org.dao.OrgCompanyDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgCompany;

import java.util.List;

@Service
public class CompanyManagerImpl implements CompanyManager {
    @Autowired
    private OrgCompanyDao orgCompanyDao;

    public OrgCompany find(Integer id) {
        return orgCompanyDao.getByKey(id);
    }

    public OrgCompany add(OrgCompany orgCompany) {
        return orgCompanyDao.add(orgCompany);
    }

    public OrgCompany update(OrgCompany orgCompany) {
        orgCompanyDao.edit(orgCompany);
        return orgCompany;
    }

    public Integer delete(Integer id) {
        OrgCompany orgCompany = new OrgCompany();
        orgCompany.setOrgCompanyId(id);
        orgCompany.setOrgCompanyDeleted("1");
        return orgCompanyDao.edit(orgCompany);
    }

    public List<OrgCompany> findList() {
        OrgCompany orgCompany = new OrgCompany();
        return orgCompanyDao.query(orgCompany);
    }
}
