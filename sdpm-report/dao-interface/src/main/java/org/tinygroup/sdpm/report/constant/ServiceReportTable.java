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

package org.tinygroup.sdpm.report.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class ServiceReportTable extends Table {

	public static final ServiceReportTable SERVICEREPORTTABLE = new ServiceReportTable();
	/** 服务报告ID */
	public final Column SR_ID = new Column(this, "sr_id");
	/** 报告标题 */
	public final Column SR_TITLE = new Column(this, "sr_title");
	/** 服务报告类型 */
	public final Column SR_TYPE = new Column(this, "sr_type");
	/** 统计截止日期 */
	public final Column SR_END_DATE = new Column(this, "sr_end_date");
	/** 统计起始日期 */
	public final Column SR_BEGIN_DATE = new Column(this, "sr_begin_date");
	/** 报告制作人 */
	public final Column SR_CREATE_BY = new Column(this, "sr_create_by");
	/** 报告制作日期 */
	public final Column SR_CREATE_DATE = new Column(this, "sr_create_date");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 部门ID */
	public final Column DEPT_ID = new Column(this, "dept_id");
	/** 产品线 */
	public final Column PRODUCTLINE_NAME = new Column(this, "productline_name");
	/** 产品名称 */
	public final Column PRODUCT_NAME = new Column(this, "product_name");
	/** 请求总数 */
	public final Column REQUEST_COUNT = new Column(this, "request_count");
	/** 客户请求数 */
	public final Column REQUEST_COUNT_CLIENT = new Column(this, "request_count_client");
	/** 及时回复请求数 */
	public final Column REQUEST_COUNT_REPLY_INTIME = new Column(this, "request_count_reply_intime");
	/** 承诺修改请求数 */
	public final Column REQUEST_COUNT_COMMIT = new Column(this, "request_count_commit");
	/** 到期请求数 */
	public final Column REQUEST_COUNT_EXPIRED = new Column(this, "request_count_expired");
	/** 实际发放请求数 */
	public final Column REQUEST_COUNT_RELEASED = new Column(this, "request_count_released");
	/** 承诺交付跨度 */
	public final Column REQUEST_COMMIT_SPAN = new Column(this, "request_commit_span");
	/** 实际交付跨度 */
	public final Column REQUEST_RELEASE_SPAN = new Column(this, "request_release_span");
	/** 属于缺陷数量 */
	public final Column REQUEST_COUNT_BUG = new Column(this, "request_count_bug");
	/** 新需求数 */
	public final Column REQUEST_COUNT_N_REQ = new Column(this, "request_count_n_req");
	/** 变更需求数 */
	public final Column REQUEST_COUNT_C_REQ = new Column(this, "request_count_c_req");
	/** 需要回访请求数 */
	public final Column REQUEST_COUNT_NEED_REVIEW = new Column(this, "request_count_need_review");
	/** 按时回访需求数 */
	public final Column REQUEST_COUNT_REVIEWED = new Column(this, "request_count_reviewed");
	/** 回访通过请求数 */
	public final Column REQUEST_COUNT_REVIEWED_PASS = new Column(this, "request_count_reviewed_pass");
	/** 客户反馈请求数 */
	public final Column REQUEST_COUNT_CLIENT_FEED_BACK = new Column(this, "request_count_client_feed_back");
	/** 累计请求数 */
	public final Column REQUEST_COUNT_ADD_UP = new Column(this, "request_count_add_up");
	/** 请求客户评价总分 */
	public final Column REQUEST_SCORE_TOTAL = new Column(this, "request_score_total");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private ServiceReportTable() {
			super("serviceReport");
		}

}
