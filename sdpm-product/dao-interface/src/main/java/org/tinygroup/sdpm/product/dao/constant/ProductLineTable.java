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

public class ProductLineTable extends Table {

	public static final ProductLineTable PRODUCTLINETABLE = new ProductLineTable();
	/** 产品线ID */
	public final Column PRODUCTLINE_ID = new Column(this, "productLine_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 部门ID */
	public final Column DEPT_ID = new Column(this, "dept_id");
	/** 产品线根节点 */
	public final Column PRODUCTLINE_ROOT = new Column(this, "productLine_root");
	/** 产品线父节点 */
	public final Column PRODUCTLINE_PARENT = new Column(this, "productLine_parent");
	/** 产品线名称 */
	public final Column PRODUCTLINE_NAME = new Column(this, "productLine_name");
	/** 产品线编号 */
	public final Column PRODUCTLINE_CODE = new Column(this, "productLine_code");
	/** 产品线序号 */
	public final Column PRODUCTLINE_ORDER = new Column(this, "productLine_order");
	/** 产品线描述 */
	public final Column PRODUCTLINE_SPEC = new Column(this, "productLine_spec");
	/** 产品线状态 */
	public final Column PRODUCTLINE_STATUS = new Column(this, "productLine_status");
	/** 产品线经理 */
	public final Column PRODUCTLINE_OWNER = new Column(this, "productLine_owner");
	/** 产品线质量经理 */
	public final Column PRODUCTLINE_QUALITY_MANAGER = new Column(this, "productLine_quality_manager");
	/** 产品线交付经理 */
	public final Column PRODUCTLINE_DELIVERY_MANAGER = new Column(this, "productLine_delivery_manager");
	/** 权限模式 */
	public final Column ACL = new Column(this, "acl");
	/** 产品线白名单 */
	public final Column PRODUCTLINE_WHITELIST = new Column(this, "productLine_whiteList");
	/** 产品线创建者 */
	public final Column PRODUCTLINE_CREATEDBY = new Column(this, "productLine_createdBy");
	/** 产品线创建日期 */
	public final Column PRODUCTLINE_CREATEDDATE = new Column(this, "productLine_createdDate");
	/** 已删除 */
	public final Column DELETED = new Column(this, "deleted");

		private ProductLineTable() {
			super("productLine");
		}

}
