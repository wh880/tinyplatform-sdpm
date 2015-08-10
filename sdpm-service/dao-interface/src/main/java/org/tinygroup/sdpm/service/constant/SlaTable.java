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

public class SlaTable extends Table {

	public static final SlaTable SLATABLE = new SlaTable();
	/** 服务协议ID */
	public final Column SLA_ID = new Column(this, "sla_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 服务级别 */
	public final Column SERVICE_LEVEL = new Column(this, "service_level");
	/** 服务期限 */
	public final Column SERVICE_DEADLINE = new Column(this, "service_deadline");
	/** 客户ID */
	public final Column CLIENT_ID = new Column(this, "client_id");
	/** 产品ID */
	public final Column PRODUCT_ID = new Column(this, "product_id");
	/** 服务协议标题 */
	public final Column SLA_TITLE = new Column(this, "sla_title");
	/** 服务协议表述 */
	public final Column SLA_SPEC = new Column(this, "sla_spec");
	/** 服务响应时限 */
	public final Column SERVICE_REPLY_TIME_LIMIT = new Column(this, "service_reply_time_limit");
	/** 需求回访时限 */
	public final Column SERVICE_REVIEW_TIME_LIMIT = new Column(this, "service_review_time_limit");
	/** 服务工时上限 */
	public final Column SERVICE_EFFORT_LIMIT = new Column(this, "service_effort_limit");
	/** 服务请求数上限 */
	public final Column SERVICE_REQUEST_LIMIT = new Column(this, "service_request_limit");
	/** 现场服务次数上限 */
	public final Column SERVICE_TS_ONSITE_LIMIT = new Column(this, "service_ts_onsite_limit");
	/** 服务专员 */
	public final Column SERVICE_SPECIALIST = new Column(this, "service_specialist");
	/** 服务级别协议 */
	public final Column SLA_STATUS = new Column(this, "sla_status");
	/** 创建人 */
	public final Column SLA_CREATED_BY = new Column(this, "sla_created_by");
	/** 创建时间 */
	public final Column SLA_CREATE_DATE = new Column(this, "sla_create_date");
	/** 批准人 */
	public final Column SLA_REVIEWED_BY = new Column(this, "sla_reviewed_by");
	/** 批准时间 */
	public final Column SLA_REVIEW_DATE = new Column(this, "sla_review_date");
	/** 关闭人 */
	public final Column SLA_CLOSED_BY = new Column(this, "sla_closed_by");
	/** 关闭时间 */
	public final Column SLA_CLOSE_DATE = new Column(this, "sla_close_date");
	/** 打开次数 */
	public final Column SLA_OPEN_COUNT = new Column(this, "sla_open_count");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private SlaTable() {
			super("sla");
		}

}
