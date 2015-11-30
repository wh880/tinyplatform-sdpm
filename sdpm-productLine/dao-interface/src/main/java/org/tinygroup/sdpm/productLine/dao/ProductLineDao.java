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

package org.tinygroup.sdpm.productLine.dao;

import org.tinygroup.jdbctemplatedslsession.daosupport.BaseDao;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.productLine.dao.pojo.ProductLine;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;

import java.util.List;

public interface ProductLineDao extends BaseDao<ProductLine,Integer> {
	
	Integer softDelete(Integer id);
	
	Pager<ProductLine> findList(int start, int limit, Condition condition, ProductLine productLine , final OrderBy... orderArgs);

	Pager<ProductLine> findList(int start, int limit, Condition condition, ProductLine productLine ,Integer[] ids, final OrderBy... orderArgs);
	
	List<ProductLine> getByKeys(Integer...ids);

	List<ProductLine> getUserProductLines(String userId);

	List<ProductLine> lineInCondition(String condition, Integer ...ids);
}
