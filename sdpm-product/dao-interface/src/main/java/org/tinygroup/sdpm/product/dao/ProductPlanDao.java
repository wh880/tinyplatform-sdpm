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

import java.util.List;

public interface ProductPlanDao extends BaseDao<ProductPlan, Integer> {

    Integer softDelete(Integer id);

    List<ProductPlan> getByKeys(Integer... id);

    int[] batchUpdateDel(List<ProductPlan> ids);

    /**
     * 统计查询
     * @param productPlan
     * @return
     */
    List<ProductPlan> statisticQuery(ProductPlan productPlan, boolean isOverdue, OrderBy... orderArgs);

    Integer getMaxNo(Integer productId);

    Integer batchDelete(Integer... ids);

    Integer deletePlanByProduct(Integer productId);
}
