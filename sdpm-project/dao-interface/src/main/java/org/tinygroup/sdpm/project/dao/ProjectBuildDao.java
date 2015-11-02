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
import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.sdpm.project.dao.pojo.ProjectBuild;
import org.tinygroup.sdpm.quality.dao.pojo.QualityBug;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface ProjectBuildDao extends BaseDao<ProjectBuild, Integer> {

     Integer softDelete(ProjectBuild build);

     int[] batchUpdateDel(List<ProjectBuild> builds);
    
	List<ProductAndLine> getProductLineTree(ProductLine t);

     Pager<ProductStory> findBuildStorys(int start, int limit, Integer buildId, OrderBy... orderArgs);

     Pager<ProductStory> findNoBuildStorys(int start, int limit, final String condition, Integer buildId, final OrderBy... orderBies);

     Integer deletereleate(Integer storyId, Integer buildId);

     Integer releateReq(Integer storyId, Integer buildId);

     Pager<QualityBug> findBuildBugs(int start, int limit, Integer buildId, final OrderBy... orderBies);

     Pager<QualityBug> findnoBuildBugs(int start, int limit, final String condition, Integer buildId, final OrderBy... orderBies);

     Integer deletereleateBug(Integer bugId, Integer buildId);

     Integer releateBug(Integer bugId, Integer buildId);

     Pager<QualityBug> findBuildLegacyBugs(int start, int limit, Integer buildId, final OrderBy... orderBies);
}
