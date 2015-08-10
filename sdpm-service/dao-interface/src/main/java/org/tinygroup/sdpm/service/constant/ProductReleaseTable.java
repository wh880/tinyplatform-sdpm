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

package org.tinygroup.sdpm.service.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class ProductReleaseTable extends Table {

	public static final ProductReleaseTable PRODUCTRELEASETABLE = new ProductReleaseTable();
	/** 需求发布ID */
	public final Column RELEASE_ID = new Column(this, "release_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 产品ID */
	public final Column PRODUCT_ID = new Column(this, "product_id");
	/** 发放版本编号 */
	public final Column RELEASE_NAME = new Column(this, "release_name");
	/** 发布版本类型 */
	public final Column RELEASE_TYPE = new Column(this, "release_type");
	/** 发布计划ID */
	public final Column PLAN_ID = new Column(this, "plan_id");
	/** 发布对象ACCOUNT */
	public final Column RELEASE_MAIL_TO = new Column(this, "release_mail_to");
	/** 发布抄送 */
	public final Column RELEASE_MAIL_C_C = new Column(this, "release_mail_c_c");
	/** 发布秘送 */
	public final Column RELEASE_MAIL_B_C = new Column(this, "release_mail_b_c");
	/** 发布内容 */
	public final Column RELEASE_CONTENT = new Column(this, "release_content");
	/** 关联用户需求单 */
	public final Column RELEASE_REQUEST = new Column(this, "release_request");
	/** 创建人 */
	public final Column RELEASE_CREATE_BY = new Column(this, "release_create_by");
	/** 创建时间 */
	public final Column RELEASE_CREATE_DATE = new Column(this, "release_create_date");
	/** RELEASE_PUBLISHER */
	public final Column RELEASE_PUBLISHER = new Column(this, "release_publisher");
	/** RELEASE_PUBLISH_DATE */
	public final Column RELEASE_PUBLISH_DATE = new Column(this, "release_publish_date");
	/** 是否需要现场测试 */
	public final Column RELEASE_SPOT_TEST = new Column(this, "release_spot_test");
	/** 发布包存放路径 */
	public final Column RELEASE_PATH = new Column(this, "release_path");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private ProductReleaseTable() {
			super("productRelease");
		}

}
