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

package org.tinygroup.sdpm.common.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class CompanyTable extends Table {

	public static final CompanyTable COMPANYTABLE = new CompanyTable();
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 公司名称 */
	public final Column COMPANY_NAME = new Column(this, "company_name");
	/** 公司电话 */
	public final Column COMPANY_PHONE = new Column(this, "company_phone");
	/** 公司传真 */
	public final Column COMPANY_FAX = new Column(this, "company_fax");
	/** 公司地址 */
	public final Column COMPANY_ADDRESS = new Column(this, "company_address");
	/** 公司邮编 */
	public final Column COMPANY_ZIPCODE = new Column(this, "company_zipcode");
	/** 公司网站 */
	public final Column COMPANY_WEBSITE = new Column(this, "company_website");
	/** 公司网络社区 */
	public final Column COMPANY_HOME = new Column(this, "company_home");
	/** 公司微博 */
	public final Column COMPANY_WEIBO = new Column(this, "company_weibo");
	/** 管理员列表 */
	public final Column ADMINS = new Column(this, "admins");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private CompanyTable() {
			super("company");
		}

}
