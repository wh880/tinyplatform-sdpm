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

package org.tinygroup.sdpm.service.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class ReviewTable extends Table {

	public static final ReviewTable REVIEWTABLE = new ReviewTable();
	/** 回访ID */
	public final Column REVIEW_ID = new Column(this, "review_id");
	/** 客户请求ID */
	public final Column REQUEST_ID = new Column(this, "request_id");
	/** 回访描述 */
	public final Column REVIEW_SPEC = new Column(this, "review_spec");
	/** 联系人 */
	public final Column REQUESTER = new Column(this, "requester");
	/** 回访者 */
	public final Column REVIEWER = new Column(this, "reviewer");
	/** 回访时间 */
	public final Column REVIEW_DATE = new Column(this, "review_date");
	/** 回访结果 */
	public final Column REVIEW_RESULT = new Column(this, "review_result");
	/** 回访评分 */
	public final Column REVIEW_SCORE = new Column(this, "review_score");
	/** 回访类型 */
	public final Column REVIEW_TYPE = new Column(this, "review_type");

		private ReviewTable() {
			super("review");
		}

}
