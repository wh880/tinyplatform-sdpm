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

public class HdateTable extends Table {

	public static final HdateTable HDATETABLE = new HdateTable();
	/** 记录ID */
	public final Column HDATE_ID = new Column(this, "hdate_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 日期 */
	public final Column HDATE = new Column(this, "hdate");
	/** 节假日名称 */
	public final Column HDATE_NAME = new Column(this, "hdate_name");
	/** 节假日描述 */
	public final Column HDATE_SPEC = new Column(this, "hdate_spec");

		private HdateTable() {
			super("hdate");
		}

}
