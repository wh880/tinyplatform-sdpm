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
 * 发布表
 * 
 */
public class ProductReleaseTable extends Table {

	public static final ProductReleaseTable PRODUCT_RELEASETABLE = new ProductReleaseTable();

	/** 
	 * 发布ID
	 * 
	 */
	public final Column RELEASE_ID = new Column(this, "release_id");

	/** 
	 * 产品ID
	 * 
	 */
	public final Column PRODUCT_ID = new Column(this, "product_id");

	/** 
	 * 版本id
	 * 
	 */
	public final Column BUILD_ID = new Column(this, "build_id");

	/** 
	 * 发布名称
	 * 
	 */
	public final Column RELEASE_NAME = new Column(this, "release_name");

	/** 
	 * 发布日期
	 * 
	 */
	public final Column RELEASE_DATE = new Column(this, "release_date");

	/** 
	 * 已完成需求
	 * 
	 */
	public final Column RELEASE_STORIES = new Column(this, "release_stories");

	/** 
	 * 已解决Bug
	 * 
	 */
	public final Column RELEASE_BUGS = new Column(this, "release_bugs");

	/** 
	 * 描述
	 * 
	 */
	public final Column RELEASE_DESC = new Column(this, "release_desc");

	/** 
	 * 已删除
	 * 
	 */
	public final Column DELETED = new Column(this, "deleted");


	private ProductReleaseTable() {
		super("product_release");
	}

}
