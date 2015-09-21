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
 * 日志表单
 * 
 */
public class EffortTable extends Table {

	public static final EffortTable EFFORTTABLE = new EffortTable();

	/** 
	 * 日志编号
	 * 
	 */
	public final Column EFFORT_ID = new Column(this, "effort_id");

	/** 
	 * 日志对象
	 * 
	 */
	public final Column EFFORT_OBJECTTYPE = new Column(this, "effort_objectType");

	/** 
	 * 对象ID
	 * 
	 */
	public final Column EFFORT_OBJECTID = new Column(this, "effort_objectID");

	/** 
	 * 所属产品
	 * 
	 */
	public final Column EFFORT_PRODUCT = new Column(this, "effort_product");

	/** 
	 * 所属项目
	 * 
	 */
	public final Column EFFORT_PROJECT = new Column(this, "effort_project");

	/** 
	 * 登记人
	 * 
	 */
	public final Column EFFORT_ACCOUNT = new Column(this, "effort_account");

	/** 
	 * 工作内容
	 * 
	 */
	public final Column EFFORT_WORK = new Column(this, "effort_work");

	/** 
	 * 日期
	 * 
	 */
	public final Column EFFORT_DATE = new Column(this, "effort_date");

	/** 
	 * 剩余
	 * 
	 */
	public final Column EFFORT_LEFT = new Column(this, "effort_left");

	/** 
	 * 耗时
	 * 
	 */
	public final Column EFFORT_CONSUMED = new Column(this, "effort_consumed");

	/** 
	 * 开始
	 * 
	 */
	public final Column EFFORT_BEGIN = new Column(this, "effort_begin");

	/** 
	 * 已关闭
	 * 
	 */
	public final Column EFFORT_END = new Column(this, "effort_end");


		private EffortTable() {
			super("effort");
		}

}
