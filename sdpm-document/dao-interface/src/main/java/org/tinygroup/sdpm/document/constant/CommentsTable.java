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

public class CommentsTable extends Table {

	public static final CommentsTable COMMENTSTABLE = new CommentsTable();
	/** 评论ID */
	public final Column COMMENT_ID = new Column(this, "comment_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 文档ID */
	public final Column DOC_ID = new Column(this, "doc_id");
	/** PARENT_ID */
	public final Column PARENT_ID = new Column(this, "parent_id");
	/** 评论内容 */
	public final Column COMMENT_TEXT = new Column(this, "comment_text");
	/** 评论者 */
	public final Column COMMENT_ADDED_BY = new Column(this, "comment_added_by");
	/** 评论时间 */
	public final Column COMMENT_ADDED_DATE = new Column(this, "comment_added_date");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private CommentsTable() {
			super("comments");
		}

}
