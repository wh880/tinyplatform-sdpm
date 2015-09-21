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

package org.tinygroup.sdpm.service.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/** 
 * 客户请求表
 * 
 */
public class RequestTable extends Table {

	public static final RequestTable REQUESTTABLE = new RequestTable();

	/** 
	 * 请求ID
	 * 
	 * 服务请求ID
	 */
	public final Column CLIENT_REQUEST_ID = new Column(this, "client_request_id");

	/** 
	 * 产品ID
	 * 
	 */
	public final Column PRODUCT_ID = new Column(this, "product_id");

	/** 
	 * 模块ID
	 * 
	 */
	public final Column MODULE_ID = new Column(this, "module_id");

	/** 
	 * 请求逻辑编号
	 * 
	 * 服务请求逻辑编号
	 */
	public final Column REQUEST_NO = new Column(this, "request_no");

	/** 
	 * 请求类型
	 * 
	 * 请求类型
	 * Event
	 * type:0-bug(缺陷/纠错,1-req1（适应性）,2-req2（完善或增强）,3-req3（个性化定制）,4-reg4（全新）,
	 * 5-training,6-support,7-question
	 */
	public final Column REQUEST_TYPE = new Column(this, "request_type");

	/** 
	 * 请求优先级
	 * 
	 * 请求优先级
	 * Priority:0-low,1-common,2-urgent
	 */
	public final Column REQUEST_PRE = new Column(this, "request_pre");

	/** 
	 * 请求标题
	 * 
	 */
	public final Column REQUEST_TITLE = new Column(this, "request_title");

	/** 
	 * 关键字
	 * 
	 * 关键词（分隔符）
	 */
	public final Column REQUEST_KEYWORDS = new Column(this, "request_keywords");

	/** 
	 * 请求描述
	 * 
	 */
	public final Column REQUEST_SPEC = new Column(this, "request_spec");

	/** 
	 * 异常标记
	 * 
	 * 0-正常；1-异常；2-重大异常
	 */
	public final Column REQUEST_IS_ABNORMAL = new Column(this, "request_is_abnormal");

	/** 
	 * 客户ID
	 * 
	 */
	public final Column CLIENT_ID = new Column(this, "client_id");

	/** 
	 * 联系人
	 * 
	 * 默认为需求提交人或产品客户其他联系人
	 */
	public final Column REQUESTER = new Column(this, "requester");

	/** 
	 * 服务事件提交人
	 * 
	 * 客户自助提交，此字段信息与requester相同，如客服代录入，可以不一致
	 */
	public final Column REQUEST_SUBMIT_BY = new Column(this, "request_submit_by");

	/** 
	 * 请求提交时间
	 * 
	 */
	public final Column REQUEST_SUBMIT_DATE = new Column(this, "request_submit_date");

	/** 
	 * 请求回复时间
	 * 
	 */
	public final Column REQUEST_REPLY_DATE = new Column(this, "request_reply_date");

	/** 
	 * 请求承诺完成日期
	 * 
	 */
	public final Column REQUEST_COMMITMENT_DATE = new Column(this, "request_commitment_date");

	/** 
	 * 回访人
	 * 
	 */
	public final Column REQUEST_REVIEWER = new Column(this, "request_reviewer");

	/** 
	 * 回访日期
	 * 
	 * 请求回访日期
	 */
	public final Column REQUEST_REVIEW_DATE = new Column(this, "request_review_date");

	/** 
	 * 请求最后编辑者
	 * 
	 */
	public final Column REQUEST_LAST_EDITED_BY = new Column(this, "request_last_edited_by");

	/** 
	 * 请求最后编辑时间
	 * 
	 */
	public final Column REQUEST_LAST_EDIT_DATE = new Column(this, "request_last_edit_date");

	/** 
	 * 请求完成日期
	 * 
	 * 支持类需求-实际完成日期
	 * 修改类需求-实际发布日期
	 */
	public final Column REQUEST_RELEASE_DATE = new Column(this, "request_release_date");

	/** 
	 * 关闭人
	 * 
	 */
	public final Column REQUEST_CLOSED_BY = new Column(this, "request_closed_by");

	/** 
	 * 关闭时间
	 * 
	 */
	public final Column REQUEST_CLOSE_DATE = new Column(this, "request_close_date");

	/** 
	 * 请求打开次数
	 * 
	 * 默认0，经历一次拒绝/关闭再打开加1
	 */
	public final Column REQUEST_OPEN_COUNT = new Column(this, "request_open_count");

	/** 
	 * 请求状态
	 * 
	 * 请求状态0-created新建(用户可见),1-doing处理中(用户可见)，
	 * 2-rejected拒绝（回复后用户可见）,
	 * 3-toProduct转出（回复后用户可见）,
	 * 4-planned已接受【由产品模块需求纳入计划触发/客服直接处理】
	 * （回复后用户可见），
	 * 5-postponed挂起，6-finished已完成【由产品模块需求阶段】
	 * ，7-released已发放(用户可见),8-retrunVisit已回访(用户可见),
	 * 9-reopen【回访不通过】(用户可见)，10-close关闭【状态8，并且review结果是pass】
	 */
	public final Column REQUEST_STATUS = new Column(this, "request_status");

	/** 
	 * 转换为
	 * 
	 * 1-"toReq"转为需求，2-"toBug"转为缺陷，3-"toTask"转为任务
	 */
	public final Column REQUEST_TRANS_TO = new Column(this, "request_trans_to");

	/** 
	 * 转出对象ID
	 * 
	 */
	public final Column REQUEST_TRANS_ID = new Column(this, "request_trans_id");

	/** 
	 * 已删除
	 * 
	 */
	public final Column DELETED = new Column(this, "deleted");

	/** 
	 * 回复描述
	 * 
	 */
	public final Column REPLY_SPEC = new Column(this, "reply_spec");

	/** 
	 * 回复者
	 * 
	 * 客服
	 */
	public final Column REPLIER = new Column(this, "replier");

	/** 
	 * 回复时间
	 * 
	 */
	public final Column REPLY_DATE = new Column(this, "reply_date");


	private RequestTable() {
		super("request");
	}

}
