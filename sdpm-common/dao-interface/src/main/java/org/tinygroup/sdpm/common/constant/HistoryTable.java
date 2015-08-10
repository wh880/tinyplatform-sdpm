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

public class HistoryTable extends Table {

	public static final HistoryTable HISTORYTABLE = new HistoryTable();
	/** 历史信息ID */
	public final Column HISTORY_ID = new Column(this, "history_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 动作ID */
	public final Column ACTION_ID = new Column(this, "action_id");
	/** 操作字段 */
	public final Column HISTORY_FIELD = new Column(this, "history_field");
	/** 旧值 */
	public final Column HISTORY_OLD = new Column(this, "history_old");
	/** 新值 */
	public final Column HISTORY_NEW = new Column(this, "history_new");
	/** 差别 */
	public final Column HISTORY_DIFF = new Column(this, "history_diff");

		private HistoryTable() {
			super("history");
		}

}
