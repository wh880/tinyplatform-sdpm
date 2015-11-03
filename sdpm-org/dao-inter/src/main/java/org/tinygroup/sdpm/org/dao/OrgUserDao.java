/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.tinygroup.sdpm.org.dao;

import org.tinygroup.jdbctemplatedslsession.daosupport.BaseDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface OrgUserDao extends BaseDao<OrgUser,String> {
    Pager<OrgUser> getPagerByDeptId(int start, int limit, final Integer deptId, final OrderBy... orderBies);

    int[] softDeleteBatch(List<OrgUser> list);
    
    String getNameById(String id);

    List<OrgUser> getByKeys(String... pk);

    /**
     * 根据项目id查找团队成员list
     *
     * @param
     * @return
     */
    List<OrgUser> getTeamUserByProjectId(Integer projectId);
}
