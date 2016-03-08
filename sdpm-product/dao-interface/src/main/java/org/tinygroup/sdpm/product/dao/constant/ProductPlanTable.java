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

package org.tinygroup.sdpm.product.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * 计划表
 */
public class ProductPlanTable extends Table {

    public static final ProductPlanTable PRODUCT_PLANTABLE = new ProductPlanTable();

    /**
     * 计划ID
     */
    public final Column PLAN_ID = new Column(this, "plan_id");

    /**
     * 公司ID
     */
    public final Column COMPANY_ID = new Column(this, "company_id");

    /**
     * 产品ID
     */
    public final Column PRODUCT_ID = new Column(this, "product_id");

    /**
     * 计划名称
     */
    public final Column PLAN_NAME = new Column(this, "plan_name");

    /**
     * 计划描述
     */
    public final Column PLAN_SPEC = new Column(this, "plan_spec");

    /**
     * 计划开始时间
     */
    public final Column PLAN_BEGIN_DATE = new Column(this, "plan_begin_date");

    /**
     * 计划结束时间
     */
    public final Column PLAN_END_DATE = new Column(this, "plan_end_date");

    /**
     * 已删除
     */
    public final Column DELETED = new Column(this, "deleted");

    public final Column NO = new Column(this, "no");

    private ProductPlanTable() {
        super("product_plan");
    }

}
