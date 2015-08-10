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

package org.tinygroup.sdpm.common.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class MessageTable extends Table {

	public static final MessageTable MESSAGETABLE = new MessageTable();
	/** 消息ID */
	public final Column MESSAGE_ID = new Column(this, "message_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 消息发送者 */
	public final Column MESSAGE_SENDER = new Column(this, "message_sender");
	/** 消息接收者 */
	public final Column MESSAGE_RECEIVER = new Column(this, "message_receiver");
	/** 消息发送时间 */
	public final Column MESSAGE_SENDDATE = new Column(this, "message_senddate");
	/** 消息接收时间 */
	public final Column MESSAGE_RECEIVEDATE = new Column(this, "message_receivedate");
	/** 消息内容 */
	public final Column MESSAGE_CONTENT = new Column(this, "message_content");
	/** 消息类型 */
	public final Column MESSAGE_TYPE = new Column(this, "message_type");
	/** 消息级别 */
	public final Column MESSAGE_LEVEL = new Column(this, "message_level");
	/** 消息标志 */
	public final Column MESSAGE_FLAG = new Column(this, "message_flag");

		private MessageTable() {
			super("message");
		}

}
