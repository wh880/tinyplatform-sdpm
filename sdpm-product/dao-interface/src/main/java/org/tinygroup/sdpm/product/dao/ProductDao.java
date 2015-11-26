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
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.product.dao.pojo.Product;
import org.tinygroup.sdpm.product.dao.pojo.ProductAndLine;
import org.tinygroup.sdpm.product.dao.pojo.ProductPlan;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;

public interface ProductDao extends BaseDao<Product,Integer> {
	
	Integer softDelete(Integer id);
	
	List<Product> getByKeys(Integer... id);
	
	List<ProductAndLine> getProductAndLine(Product t);
	
	List<String> getProductNameByLineId(Integer productLineId);

	List<Product> getProductByUser(String userId,Integer delete,Integer productLineId);

	List<Product> getProductByUserWithCount(String userId,Integer delete,boolean noRole);

	List<Product> queryWithCount(Product product);

	List<Product> getProductByUserAndProductLineWithCount(String userId,Integer productLineId,Integer delete);

	/**
	 * 根据输入名称查询
	 * @param condition
	 * @return
	 */
	List<Product> productInCondition(String condition,Integer ...ids);
}
