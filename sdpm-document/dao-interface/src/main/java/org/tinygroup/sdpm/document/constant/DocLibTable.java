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
	public final Column DOCLIB_ID = new Column(this, "doclib_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 文档库 */
	public final Column DOCLIB_NAME = new Column(this, "doclib_name");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private DocLibTable() {
			super("docLib");
		}

}
