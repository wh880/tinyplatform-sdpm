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

public class ActionTable extends Table {

	public static final ActionTable ACTIONTABLE = new ActionTable();
	/** 动作ID */
	public final Column ACTION_ID = new Column(this, "action_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 关联对象类型 */
	public final Column OBJECT_TYPE = new Column(this, "object_type");
	/** 关联对象ID */
	public final Column OBJECT_ID = new Column(this, "object_id");
	/** 产品ID列表 */
	public final Column PRODUCT_LIST = new Column(this, "product_list");
	/** 项目ID */
	public final Column PROJECT_ID = new Column(this, "project_id");
	/** 操作者 */
	public final Column ACTOR = new Column(this, "actor");
	/** 操作 */
	public final Column ACTION = new Column(this, "action");
	/** 操作时间 */
	public final Column ACTION_DATE = new Column(this, "action_date");
	/** 备注 */
	public final Column ACTION_COMMENT = new Column(this, "action_comment");
	/** 扩展字段 */
	public final Column ACTION_EXTRA = new Column(this, "action_extra");

		private ActionTable() {
			super("action");
		}

}
