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

package org.tinygroup.sdpm.common.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/** 
 * 附件表
 * 
 */
public class FileTable extends Table {

	public static final FileTable FILETABLE = new FileTable();

	/** 
	 * 附件ID
	 * 
	 */
	public final Column FILE_ID = new Column(this, "file_id");

	/** 
	 * 附件地址
	 * 
	 */
	public final Column FILE_PATHNAME = new Column(this, "file_pathname");

	/** 
	 * 附件名
	 * 
	 */
	public final Column FILE_TITLE = new Column(this, "file_title");

	/** 
	 * 附件扩展名
	 * 
	 */
	public final Column FILE_EXTENSION = new Column(this, "file_extension");

	/** 
	 * 附件大小
	 * 
	 */
	public final Column FILE_SIZE = new Column(this, "file_size");

	/** 
	 * 附件文件类型
	 * 
	 */
	public final Column FILE_OBJECTTYPE = new Column(this, "file_objectType");

	/** 
	 * 附件对象ID
	 * 
	 */
	public final Column FILE_OBJECTID = new Column(this, "file_objectID");

	/** 
	 * 由谁添加
	 * 
	 */
	public final Column FILE_ADDEDBY = new Column(this, "file_addedBy");

	/** 
	 * 附件添加日期
	 * 
	 */
	public final Column FILE_ADDEDDATE = new Column(this, "file_addedDate");

	/** 
	 * 下载次数
	 * 
	 */
	public final Column FILE_DOWNLOADS = new Column(this, "file_downloads");

	/** 
	 * file_extra
	 * 
	 */
	public final Column FILE_EXTRA = new Column(this, "file_extra");

	/** 
	 * 是否删除
	 * 
	 */
	public final Column FILE_DELETED = new Column(this, "file_deleted");


		private FileTable() {
			super("file");
		}

}
