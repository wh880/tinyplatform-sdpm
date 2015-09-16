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
 * 文档表
 * 
 */
public class DocTable extends Table {

	public static final DocTable DOCTABLE = new DocTable();

	/** 
	 * 文档ID
	 * 
	 * 文档ID，主键，唯一标示
	 */
	public final Column DOC_ID = new Column(this, "doc_id");

	/** 
	 * 所属产品
	 * 
	 * 所属产品
	 */
	public final Column DOC_PRODUCT = new Column(this, "doc_product");

	/** 
	 * 所属项目
	 * 
	 * 所属项目
	 */
	public final Column DOC_PROJECT = new Column(this, "doc_project");

	/** 
	 * 所属文档库
	 * 
	 * 所属文档库
	 */
	public final Column DOC_LIB = new Column(this, "doc_lib");

	/** 
	 * 所属分类
	 * 
	 * 所属分类
	 */
	public final Column DOC_MODULE = new Column(this, "doc_module");

	/** 
	 * 文档标题
	 * 
	 * 文档标题
	 */
	public final Column DOC_TITLE = new Column(this, "doc_title");

	/** 
	 * 摘要
	 * 
	 * 摘要
	 */
	public final Column DOC_DIGEST = new Column(this, "doc_digest");

	/** 
	 * 关键字
	 * 
	 * 关键字
	 */
	public final Column DOC_KEYWORDS = new Column(this, "doc_keywords");

	/** 
	 * 文档类型
	 * 
	 * 文档类型
	 */
	public final Column DOC_TYPE = new Column(this, "doc_type");

	/** 
	 * 文档正文
	 * 
	 * 文档正文
	 */
	public final Column DOC_CONTENT = new Column(this, "doc_content");

	/** 
	 * 文档url
	 * 
	 * 文档url
	 */
	public final Column DOC_URL = new Column(this, "doc_url");

	/** 
	 * DOC查阅次数
	 * 
	 * 查阅次数
	 */
	public final Column DOC_VIEWS = new Column(this, "doc_views");

	/** 
	 * DOC由谁添加
	 * 
	 * 由谁添加
	 */
	public final Column DOC_ADDEDBY = new Column(this, "doc_addedBy");

	/** 
	 * DOC添加时间
	 * 
	 * 添加时间
	 */
	public final Column DOC_ADDEDDATE = new Column(this, "doc_addedDate");

	/** 
	 * 由谁编辑
	 * 
	 * 由谁编辑
	 */
	public final Column DOC_EDITEDBY = new Column(this, "doc_editedBy");

	/** 
	 * 文档编辑时间
	 * 
	 * 编辑时间
	 */
	public final Column DOC_EDITEDDATE = new Column(this, "doc_editedDate");

	/** 
	 * 文档删除标志
	 * 
	 * 已删除,enum('0','1')
	 */
	public final Column DOC_DELETED = new Column(this, "doc_deleted");


		private DocTable() {
			super("doc");
		}

}
