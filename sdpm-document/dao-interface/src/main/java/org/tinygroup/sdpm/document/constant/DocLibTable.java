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

package org.tinygroup.sdpm.document.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class DocLibTable extends Table {

	public static final DocLibTable DOCLIBTABLE = new DocLibTable();
	/** 文档库ID */
	public final Column DOC_LIBID = new Column(this, "doc_libid");
	/** 文档库名字 */
	public final Column DOC_LIBNAME = new Column(this, "doc_libname");
	/** 删除文档库标志 */
	public final Column DOC_LIB_DELETED = new Column(this, "doc_lib_deleted");
	/** 文档库添加时间 */
	public final Column DOC_LIB_ADDTIME = new Column(this, "doc_lib_addtime");
	/** 文档库名称更新时间 */
	public final Column DOC_LIB_UPDTIME = new Column(this, "doc_lib_updtime");

		private DocLibTable() {
			super("docLib");
		}

}
