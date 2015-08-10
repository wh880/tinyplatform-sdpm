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

public class DocTable extends Table {

	public static final DocTable DOCTABLE = new DocTable();
	/** 文档ID */
	public final Column DOC_ID = new Column(this, "doc_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 产品ID */
	public final Column PRODUCT_ID = new Column(this, "product_id");
	/** 项目ID */
	public final Column PROJECT_ID = new Column(this, "project_id");
	/** 文档库 */
	public final Column DOCLIB_NAME = new Column(this, "doclib_name");
	/** 模块ID */
	public final Column MODULE_ID = new Column(this, "module_id");
	/** 文档标题 */
	public final Column DOC_TITLE = new Column(this, "doc_title");
	/** 文档摘要 */
	public final Column DOC_ABSTRACT = new Column(this, "doc_abstract");
	/** 文档关键字 */
	public final Column DOC_KEYWORDS = new Column(this, "doc_keywords");
	/** 文档类型 */
	public final Column DOC_TYPE = new Column(this, "doc_type");
	/** 文档内容 */
	public final Column DOC_CONTENT = new Column(this, "doc_content");
	/** 文档超链接 */
	public final Column DOC_URL = new Column(this, "doc_url");
	/** 文档查看数 */
	public final Column DOC_VIEWS = new Column(this, "doc_views");
	/** 加入者 */
	public final Column DOC_ADDED_BY = new Column(this, "doc_added_by");
	/** 加入时间 */
	public final Column DOC_ADDED_DATE = new Column(this, "doc_added_date");
	/** 修改者 */
	public final Column DOC_EDITED_BY = new Column(this, "doc_edited_by");
	/** 修改时间 */
	public final Column DOC_EDITED_DATE = new Column(this, "doc_edited_date");
	/** 文档得分 */
	public final Column DOC_SCORE = new Column(this, "doc_score");
	/** 文档转发数 */
	public final Column DOC_SHARE = new Column(this, "doc_share");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private DocTable() {
			super("doc");
		}

}
