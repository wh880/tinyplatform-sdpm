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

package org.tinygroup.sdpm.org.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/** 
 * 公司表
 * 
 */
public class OrgCompanyTable extends Table {

	public static final OrgCompanyTable ORG_COMPANYTABLE = new OrgCompanyTable();

	/** 
	 * 公司编号
	 * 
	 */
	public final Column ORG_COMPANY_ID = new Column(this, "org_company_id");

	/** 
	 * 公司名称 
	 * 
	 */
	public final Column ORG_COMPANY_NAME = new Column(this, "org_company_name");

	/** 
	 * 联系电话 
	 * 
	 */
	public final Column ORG_COMPANY_PHONE = new Column(this, "org_company_phone");

	/** 
	 * 传真
	 * 
	 */
	public final Column ORG_COMPANY_FAX = new Column(this, "org_company_fax");

	/** 
	 * 通讯地址
	 * 
	 */
	public final Column ORG_COMPANY_ADDRESS = new Column(this, "org_company_address");

	/** 
	 * 邮政编码
	 * 
	 */
	public final Column ORG_COMPANY_ZIP_CODE = new Column(this, "org_company_zip_code");

	/** 
	 * 官网
	 * 
	 */
	public final Column ORG_COMPANY_WEBSITE = new Column(this, "org_company_website");

	/** 
	 * 内网
	 * 
	 */
	public final Column ORG_COMPANY_BACKYARD = new Column(this, "org_company_backyard");

	/** 
	 * 匿名登录
	 * 
	 */
	public final Column ORG_COMPANY_GUEST = new Column(this, "org_company_guest");

	/** 
	 * 管理员列表
	 * 
	 */
	public final Column ORG_COMPANY_ADMINS = new Column(this, "org_company_admins");

	/** 
	 * 删除标志 
	 * 
	 */
	public final Column ORG_COMPANY_DELETED = new Column(this, "org_company_deleted");


	private OrgCompanyTable() {
		super("org_company");
	}

}
