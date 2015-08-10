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

public class RequestTable extends Table {

	public static final RequestTable REQUESTTABLE = new RequestTable();
	/** 客户请求ID */
	public final Column REQUEST_ID = new Column(this, "request_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 产品ID */
	public final Column PRODUCT_ID = new Column(this, "product_id");
	/** 模块ID */
	public final Column MODULE_ID = new Column(this, "module_id");
	/** 请求逻辑编号 */
	public final Column REQUEST_N_O = new Column(this, "request_n_o");
	/** 请求类型 */
	public final Column REQUEST_TYPE = new Column(this, "request_type");
	/** 请求优先级 */
	public final Column REQUEST_PRE = new Column(this, "request_pre");
	/** 请求标题 */
	public final Column REQUEST_TITLE = new Column(this, "request_title");
	/** 关键字 */
	public final Column REQUEST_KEYWORDS = new Column(this, "request_keywords");
	/** 请求描述 */
	public final Column REQUEST_SPEC = new Column(this, "request_spec");
	/** 异常标记 */
	public final Column REQUEST_IS_ABNORMAL = new Column(this, "request_is_abnormal");
	/** 客户ID */
	public final Column CLIENT_ID = new Column(this, "client_id");
	/** 联系人 */
	public final Column REQUESTER = new Column(this, "requester");
	/** 服务事件提交人 */
	public final Column REQUEST_SUBMIT_BY = new Column(this, "request_submit_by");
	/** 请求提交时间 */
	public final Column REQUEST_SUBMIT_DATE = new Column(this, "request_submit_date");
	/** 请求回复时间 */
	public final Column REQUEST_REPLY_DATE = new Column(this, "request_reply_date");
	/** 请求承诺完成日期 */
	public final Column REQUEST_COMMITMENT_DATE = new Column(this, "request_commitment_date");
	/** 回访人 */
	public final Column REQUEST_REVIEWER = new Column(this, "request_reviewer");
	/** 回访日期 */
	public final Column REQUEST_REVIEW_DATE = new Column(this, "request_review_date");
	/** 请求最后编辑者 */
	public final Column REQUEST_LAST_EDITED_BY = new Column(this, "request_last_edited_by");
	/** 请求最后编辑时间 */
	public final Column REQUEST_LAST_EDIT_DATE = new Column(this, "request_last_edit_date");
	/** 需求发布ID */
	public final Column RELEASE_ID = new Column(this, "release_id");
	/** 请求完成日期 */
	public final Column REQUEST_RELEASE_DATE = new Column(this, "request_release_date");
	/** 关闭人 */
	public final Column REQUEST_CLOSED_BY = new Column(this, "request_closed_by");
	/** 关闭时间 */
	public final Column REQUEST_CLOSE_DATE = new Column(this, "request_close_date");
	/** 请求打开次数 */
	public final Column REQUEST_OPEN_COUNT = new Column(this, "request_open_count");
	/** 请求状态 */
	public final Column REQUEST_STATUS = new Column(this, "request_status");
	/** 转换为 */
	public final Column REQUEST_TRANS_TO = new Column(this, "request_trans_to");
	/** 转出对象ID */
	public final Column REQUEST_TRANS_ID = new Column(this, "request_trans_id");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private RequestTable() {
			super("request");
		}

}
