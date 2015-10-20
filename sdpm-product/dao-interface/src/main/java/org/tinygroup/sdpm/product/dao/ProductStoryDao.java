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

package org.tinygroup.sdpm.product.dao;

import java.util.List;

import org.tinygroup.jdbctemplatedslsession.daosupport.BaseDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.product.dao.pojo.ProductStory;
import org.tinygroup.sdpm.product.dao.pojo.StoryCount;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.tinysqldsl.select.Join;

public interface ProductStoryDao extends BaseDao<ProductStory,Integer> {
	
	Pager<ProductStory> complexQuery(int start, int limit, ProductStory productStory, final String condition, final OrderBy... orderBys);
	
	Pager<ProductStory> complexQueryRel(int start, int limit, ProductStory productStory, final String condition, final OrderBy... orderBys);

	
	Integer softDelete(Integer id);
	
	List<ProductStory> getByKeys(Integer... id);
	
	List<StoryCount> modelStoryCount(ProductStory story);
	
	int getCount(ProductStory story,Join join,Condition... condition);
	
	List<StoryCount> productStoryCount(ProductStory t);
	
	List<StoryCount> planStoryCount(ProductStory story,ProductPlan plan);
	
	List<StoryCount> fieldStoryCount(ProductStory t,String field);
	
	int[] batchUpdateDel(List<ProductStory> ids);
	
	List<StoryCount> userStoryCount(ProductStory t,String field);

	/**
	 * 计算状态位
	 * @param productId
	 * @param status
     * @return
     */
	int countStatus(int productId,int status);

}
