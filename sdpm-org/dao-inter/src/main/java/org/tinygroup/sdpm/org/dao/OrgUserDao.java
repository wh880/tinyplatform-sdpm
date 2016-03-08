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
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface OrgUserDao extends BaseDao<OrgUser, String> {
    Pager<OrgUser> getPagerByDeptId(int start, int limit, final Integer deptId, final OrderBy... orderBies);

    int[] softDeleteBatch(List<OrgUser> list);

    String getNameById(String id);

    List<OrgUser> getByKeys(String... pk);

    /**
     * 查找领导的直接下属
     *
     * @param
     * @return
     */
    List<OrgUser> getDirectStaffByLeader(String leaderUserId);


    List<OrgUser> getDirectStaffByLeaderAndSelf(String leaderUserId);

    /**
     * 根据项目id查找团队成员list
     *
     * @param
     * @return
     */
    List<OrgUser> getTeamUserByProjectId(Integer projectId);

    /**
     * 根据名称条件查询
     *
     * @param condition
     * @return
     */
    List<OrgUser> userInCondition(String condition, Integer limit, String... ids);

    /**
     * 根据一组userID进行查询
     *
     * @param list
     * @return
     */
    List<OrgUser> getListById(List<String> list);

    /**
     * 获取用户相关白名单用户
     */
    List<OrgUser> getWhiteListById(String userAccount);
}
