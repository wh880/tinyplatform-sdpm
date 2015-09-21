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

package org.tinygroup.sdpm.docment.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/** 
 * 文档编辑历史记录
 * 
 */
public class HistorydocTable extends Table {

	public static final HistorydocTable HISTORYDOCTABLE = new HistorydocTable();

	/** 
	 * 历史记录ID
	 * 
	 * 历史记录ID
	 */
	public final Column RECORD_ID = new Column(this, "record_id");
	
	/** 
	 * 文档ID
	 * 
	 * 文档ID，主键，唯一标示
	 */
	public final Column DOC_ID = new Column(this, "doc_id");

	/** 
	 * 编辑时间
	 * 
	 * 编辑时间
	 */
	public final Column REC_TIME = new Column(this, "rec_time");

	/** 
	 * 谁创建或是编辑
	 * 
	 * 谁创建或是编辑
	 */
	public final Column REC_WHO = new Column(this, "rec_who");


		private HistorydocTable() {
			super("historydoc");
		}

}
