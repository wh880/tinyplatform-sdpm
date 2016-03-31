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

package org.tinygroup.sdpm.product.dao;

import org.tinygroup.jdbctemplatedslsession.daosupport.BaseDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.StoryCount;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.select.Join;

import java.util.List;

public interface ProductStoryDao extends BaseDao<ProductStory, Integer> {

    Pager<ProductStory> complexQuery(int start, int limit, ProductStory productStory, final Boolean ignoreDelete, final Condition condition, final OrderBy... orderBys);

    Pager<ProductStory> complexQueryRel(int start, int limit, ProductStory productStory, final Condition condition, final OrderBy... orderBys);

    Integer softDelete(Integer id);

    List<ProductStory> getByKeys(boolean withSpec, ProductStory story, Integer... id);

    List<StoryCount> modelStoryCount(ProductStory story);

    Integer getCount(ProductStory story, Join join, Condition... condition);

    List<StoryCount> productStoryCount(ProductStory t);

    List<StoryCount> planStoryCount(ProductStory story, ProductPlan plan);

    List<StoryCount> fieldStoryCount(ProductStory t, String field);

    int[] batchUpdateDel(List<ProductStory> ids);

    List<StoryCount> userStoryCount(ProductStory t, String field);

    Integer countStatus(int productId, int status);

    ProductStory getReleteStoryByKey(Integer pk);

    List<ProductStory> findpNameBysId(Integer id);

    Pager<ProductStory> projectLinkedStory(int start, int limit, ProductStory productStory, final Condition condition, final OrderBy... orderBys);

    Integer getMaxNo(Integer productId);

    Integer batchDelete(Integer... ids);

    Integer deleteStoryByProduct(Integer productId);

    Integer deleteStoryByPlan(Integer planId);

    /**
     * 根据输入名称查询
     *
     * @param condition
     * @param productId
     * @return
     */
    List<ProductStory> storyInCondition(String condition, Integer limit, Integer productId, Integer... ids);

    ProductStory findStoryByStoryId(Integer storyId);

}
