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

package org.tinygroup.sdpm.product.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/** 
 * 产品表
 * 
 */
public class ProductTable extends Table {

	public static final ProductTable PRODUCTTABLE = new ProductTable();

	/** 
	 * 产品ID
	 * 
	 */
	public final Column PRODUCT_ID = new Column(this, "product_id");

	/** 
	 * 公司ID
	 * 
	 */
	public final Column COMPANY_ID = new Column(this, "company_id");

	/** 
	 * 部门ID
	 * 
	 */
	public final Column DEPT_ID = new Column(this, "dept_id");

	/** 
	 * 产品线ID
	 * 
	 */
	public final Column PRODUCTLINE_ID = new Column(this, "productLine_id");

	/** 
	 * 产品名称
	 * 
	 */
	public final Column PRODUCT_NAME = new Column(this, "product_name");

	/** 
	 * 产品代号
	 * 
	 */
	public final Column PRODUCT_CODE = new Column(this, "product_code");

	/** 
	 * 产品序号
	 * 
	 */
	public final Column PRODUCT_ORDER = new Column(this, "product_order");

	/** 
	 * 产品状态
	 * 
	 */
	public final Column PRODUCT_STATUS = new Column(this, "product_status");

	/** 
	 * 产品描述
	 * 
	 */
	public final Column PRODUCT_DESC = new Column(this, "product_desc");

	/** 
	 * 产品经理
	 * 
	 */
	public final Column PRODUCT_OWNER = new Column(this, "product_owner");

	/** 
	 * 产品质量经理
	 * 
	 */
	public final Column PRODUCT_QUALITY_MANAGER = new Column(this, "product_quality_manager");

	/** 
	 * 产品交付经理
	 * 
	 */
	public final Column PRODUCT_DELIVERY_MANAGER = new Column(this, "product_delivery_manager");

	/** 
	 * 权限模式
	 * 
	 * 本部门范围：0-open公开；1-custom自定义（产品/项目团队和白名单可访问）；2-private私有（产品/项目团队成员才可访问）
	 */
	public final Column ACL = new Column(this, "acl");

	/** 
	 * 白名单
	 * 
	 */
	public final Column PRODUCT_WHITELIST = new Column(this, "product_whiteList");

	/** 
	 * 创建者
	 * 
	 */
	public final Column PRODUCT_CREATEDBY = new Column(this, "product_createdBy");

	/** 
	 * 创建日期
	 * 
	 */
	public final Column PRODUCT_CREATEDDATE = new Column(this, "product_createdDate");

	/** 
	 * 创建版本
	 * 
	 */
	public final Column PRODUCT_CREATEDVERSION = new Column(this, "product_createdVersion");

	/** 
	 * 已删除
	 * 
	 */
	public final Column DELETED = new Column(this, "deleted");


		private ProductTable() {
			super("product");
		}

}
