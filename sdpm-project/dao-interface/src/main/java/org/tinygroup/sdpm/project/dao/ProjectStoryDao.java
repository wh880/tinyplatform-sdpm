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
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.project.dao.pojo.ProjectStory;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface ProjectStoryDao extends BaseDao<ProjectStory, Integer> {
    Pager<ProductStory> findStory(Integer projectId, Integer start, Integer limit, boolean isNotInProjectStory, final OrderBy... orderByArgs);

    /**
     * 批量硬删除
     *
     * @param projectId
     * @param storyIds
     * @return
     */
    Integer batchDel(Integer projectId, Integer[] storyIds);

    List<ProjectStory> findByProjectID(Integer projectId);

    Integer deleteByProjectStory(Integer projectId, Integer storyId);

    Pager<ProjectStory> complexQuery(int start, int limit, ProjectStory projectStory, final String condition, final OrderBy... orderBys);

}
