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
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface ProjectBuildDao extends BaseDao<ProjectBuild, Integer> {
    /**
     * 根据产品id进行软删删除
     *
     * @param productId
     * @return
     */
    Integer deleteBuildByProductId(Integer productId);

    Integer softDelete(ProjectBuild build);

    int[] batchUpdateDel(List<ProjectBuild> builds);

    Pager<ProductStory> findBuildStoryList(int start, int limit, Integer buildId, OrderBy... orderArgs);

    List<ProjectBuild> getBuildByKeys(String... ids);

    List<ProjectBuild> getBuildByProducts(Integer... ids);

    /**
     * 根据输入名称和产品查询
     * @param condition
     * @param productId
     * @return
     */
    List<ProjectBuild> buildInCondition(String condition, Integer productId, Integer projectId);
}
