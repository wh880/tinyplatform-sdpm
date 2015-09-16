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

package org.tinygroup.sdpm.document.dao.org.tinygroup.alm.document.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/** 
 * 附件表
 * 
 */
public class AttachmentTable extends Table {

	public static final AttachmentTable ATTACHMENTTABLE = new AttachmentTable();

	/** 
	 * 附件编号
	 * 
	 * 附件编号
	 */
	public final Column ATTACH_ID = new Column(this, "attach_id");

	/** 
	 * 附件标题
	 * 
	 * 附件标题
	 */
	public final Column ATTACH_NAME = new Column(this, "attach_name");

	/** 
	 * 附件类型
	 * 
	 * 附件类型
	 */
	public final Column ATTACH_TYPE = new Column(this, "attach_type");

	/** 
	 * 附件链接
	 * 
	 * 附件url，路径
	 */
	public final Column ATTACH_URL = new Column(this, "attach_url");


		private AttachmentTable() {
			super("attachment");
		}

}
