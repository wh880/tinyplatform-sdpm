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

package org.tinygroup.sdpm.service.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/** 
 * faq分类
 * 
 */
public class FaqTypeTable extends Table {

	public static final FaqTypeTable FAQ_TYPETABLE = new FaqTypeTable();

	/** 
	 * 问题类型id
	 * 
	 */
	public final Column FAQ_TYPE_ID = new Column(this, "faq_type_id");

	/** 
	 * 问答类型
	 * 
	 */
	public final Column FAQ_TYPE = new Column(this, "faq_type");

	/** 
	 * 父级问题类型id
	 * 
	 */
	public final Column FAQ_PARENT_TYPE_ID = new Column(this, "faq_parent_type_id");

	/** 
	 * faq类型创建时间
	 * 
	 */
	public final Column FAQ_TYPE_CREATDAY = new Column(this, "faq_type_creatDay");

	/** 
	 * 创建人
	 * 
	 */
	public final Column FAQ_CREATED_BY = new Column(this, "faq_created_by");

	/** 
	 * 已删除
	 * 
	 */
	public final Column DELETED = new Column(this, "deleted");


		private FaqTypeTable() {
			super("faq_type");
		}

}
