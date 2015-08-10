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

public class TodoListTable extends Table {

	public static final TodoListTable TODOLISTTABLE = new TodoListTable();
	/** 待办事项ID */
	public final Column TODO_ID = new Column(this, "todo_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 用户账号 */
	public final Column ACCOUNT = new Column(this, "account");
	/** 待办日期 */
	public final Column TODO_DATE = new Column(this, "todo_date");
	/** 开始时间 */
	public final Column TODO_BEGIN = new Column(this, "todo_begin");
	/** 结束时间 */
	public final Column TODO_END = new Column(this, "todo_end");
	/** 事项类型 */
	public final Column TODO_TYPE = new Column(this, "todo_type");
	/** 事项优先级 */
	public final Column TODO_PRI = new Column(this, "todo_pri");
	/** 事项名称 */
	public final Column TODO_NAME = new Column(this, "todo_name");
	/** 事项描述 */
	public final Column TODO_DESC = new Column(this, "todo_desc");
	/** 事项状态 */
	public final Column TODO_STATUS = new Column(this, "todo_status");

		private TodoListTable() {
			super("todoList");
		}

}
