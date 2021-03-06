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
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.tinysqldsl.Pager;

import java.util.Date;
import java.util.List;

public interface ProjectDao extends BaseDao<Project, Integer> {
    int[] batchSoftDelete(List<Project> projects);

    /**
     * 查找相关干系人的项目
     * 查询条件OR
     *
     * @param project
     * @return
     */
    List<Project> findListByRelatedUser(Project project);

    /**
     * 获取项目列表，并附带统计信息
     *
     * @param project
     * @return
     */
    List<Project> findListWithStatistics(Project project, Date startDate, Date endDate);

    /**
     * 获取项目分页，并附带统计信息
     *
     * @param start
     * @param limit
     * @param project
     * @param projectIds
     * @param args
     * @return
     */
    Pager<Project> findPageWithStatistics(int start, int limit, final Project project,
                                          final Integer[] projectIds, final OrderBy... args);

    /**
     * 根据storyId查项目
     *
     * @param storyId
     * @return
     */
    List<Project> getProjectByStoryId(Integer storyId);

    /**
     * 根据多个主键查找
     *
     * @param ids
     * @return
     */
    List<Project> findByIds(Integer... ids);

    /**
     * 查找用户所在团队所拥有的项目
     *
     * @param userId OrgUser.Id
     * @param acl    权限控制
     * @return
     */
    List<Project> findListByTeamUserId(String userId, String acl);

    /**
     * 根据输入查询
     *
     * @param condition
     * @param ids
     * @return
     */
    List<Project> projectInCondition(String condition, Integer limit, Integer... ids);

}
