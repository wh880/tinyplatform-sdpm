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

package org.tinygroup.sdpm.project.dao;

import org.tinygroup.jdbctemplatedslsession.daosupport.BaseDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.project.dao.pojo.Project;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface ProjectDao extends BaseDao<Project,Integer> {
    /**
     * 查询
     *
     * @param start
     * @param limit
     * @param project
     * @param orderBies
     * @return
     */
    public Pager<Project> querytAll(int start, int limit, final Project project, final OrderBy... orderBies);

    /**
     * 之前用于根据项目统计花费时间
     * @param project
     * @return
     */
    public Project getTime(Project project);

    /**
     * 统计花费时间用了groupby
     * @param start
     * @param limit
     * @param project
     * @param orderBies
     * @return
     */
    public Pager<Project> tquerytAll(int start, int limit, final Project project, final OrderBy... orderBies);

    /**
     * 根据storyId查项目
     * @param storyId
     * @return
     */
    List<Project> getProjectByStoryId(Integer storyId);

    /**
     * 根据自定义条件查询
     *
     * @param condition
     * @return
     */
    public List<Project> findByCondition(String condition);
}
