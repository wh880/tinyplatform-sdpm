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

package org.tinygroup.sdpm.org.dao;

import org.tinygroup.jdbctemplatedslsession.daosupport.BaseDao;
import org.tinygroup.sdpm.org.dao.pojo.OrgDiaryWhiteList;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 *
 * @generated
 */
public interface OrgDiaryWhiteListDao extends BaseDao<OrgDiaryWhiteList, Integer> {
    List<OrgUser> findUserListByAccount(String userAccount);

    /**
     * 查找在白名单表是否存在两人的关系
     *
     * @param firstAccount
     * @param secondAccount
     * @return
     */
    List<OrgDiaryWhiteList> findOneByAccounts(String firstAccount, String secondAccount);

    /**
     * 删除一对白名单关系
     *
     * @param firstAccount
     * @param secondAccout
     * @return
     */
    Integer deleteByAccounts(String firstAccount, String secondAccout);

}
