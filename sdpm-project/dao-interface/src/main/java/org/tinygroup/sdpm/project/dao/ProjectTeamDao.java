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

package org.tinygroup.sdpm.project.dao;

import org.tinygroup.jdbctemplatedslsession.daosupport.BaseDao;
import org.tinygroup.sdpm.project.dao.pojo.ProjectTeam;

import java.util.List;

public interface ProjectTeamDao extends BaseDao<ProjectTeam, Integer> {

    List<ProjectTeam> findByProjectId(Integer projectId);

    List<ProjectTeam> findByProductId(Integer productId);

    List<String> getMenuByUserId(Integer projectId, Integer productId ,String userId);

    List<Integer> getRoleIdListByUserId(Integer projectId, Integer productId, String userId);

}
