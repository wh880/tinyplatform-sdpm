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
import org.tinygroup.sdpm.org.biz.inter.UserManager;
import org.tinygroup.sdpm.org.dao.OrgUserDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Service
@Transactional
public class UserManagerImpl implements UserManager {
    @Autowired
    private OrgUserDao orgUserDao;

    public OrgUser find(String id) {
        return orgUserDao.getByKey(id);
    }

    public Pager<OrgUser> findPager(Integer start, Integer limit, OrgUser orgUser) {
        return orgUserDao.queryPager(start, limit, orgUser);
    }

    public List<OrgUser> findList(OrgUser orgUser) {
        return orgUserDao.query(orgUser);
    }

    public OrgUser add(OrgUser orgUser) {
        return orgUserDao.add(orgUser);
    }

    public OrgUser update(OrgUser orgUser) {
        orgUserDao.edit(orgUser);
        return orgUser;
    }

    public Integer delete(String id) {
        OrgUser orgUser = orgUserDao.getByKey(id);
        // OrgUser orgUser = new OrgUser();
        // orgUser.setOrgUserId(id);
        orgUser.setOrgUserDeleted(OrgUser.DELETE_YES);
        return orgUserDao.edit(orgUser);
    }
}
