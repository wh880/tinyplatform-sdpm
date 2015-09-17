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
	/** 评论编号 */
	public final Column COMMENT_ID = new Column(this, "comment_id");
	/** 被评论文档ID */
	public final Column COMMENT_DOCID = new Column(this, "comment_docid");
	/** 评论者ID */
	public final Column COMMENT_USERID = new Column(this, "comment_userid");
	/** 评论正文 */
	public final Column COMMENT_TXT = new Column(this, "comment_txt");
	/** 评论时间 */
	public final Column COMMENT_TIME = new Column(this, "comment_time");

		private CommentsTable() {
			super("comments");
		}

}
