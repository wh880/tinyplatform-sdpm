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
 * 模块搜索表
 * 
 */
public class SystemSearchTable extends Table {

	public static final SystemSearchTable SYSTEM_SEARCHTABLE = new SystemSearchTable();

	/** 
	 * 搜索ID
	 * 
	 */
	public final Column SEARCH_ID = new Column(this, "search_id");

	/** 
	 * 搜索对象类型
	 * 
	 */
	public final Column SEARCH_OBJECTTYPE = new Column(this, "search_objectType");

	/** 
	 * 搜索对象ID
	 * 
	 */
	public final Column SEARCH_OBJECTID = new Column(this, "search_objectID");

	/** 
	 * 搜索名称	
	 * 
	 */
	public final Column SEARCH_TITLE = new Column(this, "search_title");

	/** 
	 * 搜索内容
	 * 
	 */
	public final Column SEARCH_CONTENT = new Column(this, "search_content");

	/** 
	 * 搜索添加日期
	 * 
	 */
	public final Column SEARCH_ADDEDDATE = new Column(this, "search_addedDate");

	/** 
	 * 搜索编辑日期
	 * 
	 */
	public final Column SEARCH_EDITEDDATE = new Column(this, "search_editedDate");

	/** 
	 * 已删除
	 * 
	 */
	public final Column DELETED = new Column(this, "deleted");


	private SystemSearchTable() {
		super("system_search");
	}

}
