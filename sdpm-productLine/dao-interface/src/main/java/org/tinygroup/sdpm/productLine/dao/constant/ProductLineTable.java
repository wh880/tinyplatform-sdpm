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

package org.tinygroup.sdpm.productLine.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * 产品线表
 */
public class ProductLineTable extends Table {

    public static final ProductLineTable PRODUCT_LINETABLE = new ProductLineTable();

    /**
     * 产品线ID
     */
    public final Column PRODUCT_LINE_ID = new Column(this, "product_line_id");

    /**
     * 公司ID
     */
    public final Column COMPANY_ID = new Column(this, "company_id");

    /**
     * 部门ID
     */
    public final Column DEPT_ID = new Column(this, "dept_id");

    /**
     * 产品线根节点
     */
    public final Column PRODUCT_LINE_ROOT = new Column(this, "product_line_root");

    /**
     * 产品线父节点
     */
    public final Column PRODUCT_LINE_PARENT = new Column(this, "product_line_parent");

    /**
     * 产品线名称
     */
    public final Column PRODUCT_LINE_NAME = new Column(this, "product_line_name");

    /**
     * 产品线编号
     */
    public final Column PRODUCT_LINE_CODE = new Column(this, "product_line_code");

    /**
     * 产品线序号
     */
    public final Column PRODUCT_LINE_ORDER = new Column(this, "product_line_order");

    /**
     * 产品线描述
     */
    public final Column PRODUCT_LINE_SPEC = new Column(this, "product_line_spec");

    /**
     * 产品线状态
     */
    public final Column PRODUCT_LINE_STATUS = new Column(this, "product_line_status");

    /**
     * 产品线经理
     */
    public final Column PRODUCT_LINE_OWNER = new Column(this, "product_line_owner");

    /**
     * 产品线质量经理
     */
    public final Column PRODUCT_LINE_QUALITY_MANAGER = new Column(this, "product_line_quality_manager");

    /**
     * 产品线交付经理
     */
    public final Column PRODUCT_LINE_DELIVERY_MANAGER = new Column(this, "product_line_delivery_manager");

    /**
     * 权限模式
     * <p>
     * 本部门范围：0-open公开；1-custom自定义（产品/项目团队和白名单可访问）；2-private私有（产品/项目团队成员才可访问）
     */
    public final Column ACL = new Column(this, "acl");

    /**
     * 产品线白名单
     */
    public final Column PRODUCT_LINE_WHITE_LIST = new Column(this, "product_line_white_list");

    /**
     * 产品线创建者
     */
    public final Column PRODUCT_LINE_CREATED_BY = new Column(this, "product_line_created_by");

    /**
     * 产品线创建日期
     */
    public final Column PRODUCT_LINE_CREATED_DATE = new Column(this, "product_line_created_date");

    /**
     * 已删除
     */
    public final Column DELETED = new Column(this, "deleted");


    private ProductLineTable() {
        super("product_line");
    }

}
