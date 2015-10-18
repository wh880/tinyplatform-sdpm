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
package org.tinygroup.sdpm.org.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.org.biz.inter.UserManager;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserManager userManager;

    public OrgUser findUser(String id) {
        return userManager.find(id);
    }

    public OrgUser findUserByAccount(String account) {
        return userManager.findUserByAccount(account);
    }

    public Pager<OrgUser> findUserPager(Integer start, Integer limit, OrgUser orgUser) {
        return userManager.findPager(start, limit, orgUser);
    }

    public List<OrgUser> findUserList(OrgUser orgUser) {
        return userManager.findList(orgUser);
    }

    public Pager<OrgUser> findUserByDeptId(Integer start, Integer limit, Integer deptId) {
        return userManager.findUserListByDeptId(start, limit, deptId);
    }

    public OrgUser addUser(OrgUser orgUser) {
        String password = orgUser.getOrgUserPassword();
        orgUser.setOrgUserPassword(userManager.encryptPassword(password));
        return userManager.add(orgUser);
    }

    public OrgUser updateUser(OrgUser orgUser) {
//        String password=orgUser.getOrgUserPassword();
//        orgUser.setOrgUserPassword(userManager.encryptPassword(password));
        return userManager.update(orgUser);
    }

    public Integer deleteUser(String id) {
        return userManager.delete(id);
    }

    public int[] deleteBatchUser(List<OrgUser> list) {
        return userManager.deleteBatch(list);
    }

    public boolean validatePassword(String plainPassword, String password) {
        return userManager.validatePassword(plainPassword, password);
    }


    public List<OrgUser> findUserListByIds(String[] userIds) {
        List<OrgUser> userList = new ArrayList<OrgUser>();
        for (String id : userIds) {
            userList.add(userManager.find(id));
        }
        return userList;
    }

	public String getNameById(String id) {

		return userManager.getNameById(id);
	}
}