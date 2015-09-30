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

package org.tinygroup.sdpm.system.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/** 
 * 假期表
 * 
 */
public class HolidayTable extends Table {

	public static final HolidayTable HOLIDAYTABLE = new HolidayTable();

	/** 
	 * 假期ID
	 * 
	 */
	public final Column HOLIDAY_ID = new Column(this, "holiday_id");

	/** 
	 * 假期名
	 * 
	 */
	public final Column HOLIDAY_NAME = new Column(this, "holiday_name");

	/** 
	 * 假期创建人
	 * 
	 */
	public final Column HOLIDAY_ACCOUNT = new Column(this, "holiday_account");

	/** 
	 * 假期日期
	 * 
	 */
	public final Column HOLIDAY_DATE = new Column(this, "holiday_date");

	/** 
	 * 假期类型
	 * 
	 */
	public final Column HOLIDAY_TYPE = new Column(this, "holiday_type");

	/** 
	 * 删除标志位
	 * 
	 */
	public final Column HOLIDAY_DELETED = new Column(this, "holiday_deleted");

	/** 
	 * 公司ID
	 * 
	 */
	public final Column COMPANY_ID = new Column(this, "company_id");


	private HolidayTable() {
		super("holiday");
	}

}
