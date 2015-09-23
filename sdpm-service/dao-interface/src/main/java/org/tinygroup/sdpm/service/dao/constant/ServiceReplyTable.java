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
 * 客服回复表
 * 
 */
public class ServiceReplyTable extends Table {

	public static final ServiceReplyTable SERVICE_REPLYTABLE = new ServiceReplyTable();

	/** 
	 * 回复ID
	 * 
	 */
	public final Column REPLY_ID = new Column(this, "reply_id");

	/** 
	 * 请求ID
	 * 
	 * 服务请求ID
	 */
	public final Column CLIENT_REQUEST_ID = new Column(this, "client_request_id");

	/** 
	 * 回复意见
	 * 
	 * 0-接受；1-拒绝；2-待定
	 */
	public final Column REPLY_OPINION = new Column(this, "reply_opinion");

	/** 
	 * 回复描述
	 * 
	 */
	public final Column REPLY_SPEC = new Column(this, "reply_spec");

	/** 
	 * 承诺日期
	 * 
	 * 回复意见为“0-接受，”时，应有承诺日期
	 */
	public final Column REPLY_COMMITMENT_DATE = new Column(this, "reply_commitment_date");

	/** 
	 * 回复处理人
	 * 
	 * 可以是客服或转出后的产品组处理人
	 */
	public final Column REPLY_DO_BY = new Column(this, "reply_do_by");

	/** 
	 * 回复处理日期
	 * 
	 */
	public final Column REPLY_DO_DATE = new Column(this, "reply_do_date");

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

	/** 
	 * 是否回复
	 * 
	 * 0-未回复；1-已回复
	 */
	public final Column REPLY_DONE = new Column(this, "reply_done");


	private ServiceReplyTable() {
		super("service_reply");
	}

}
