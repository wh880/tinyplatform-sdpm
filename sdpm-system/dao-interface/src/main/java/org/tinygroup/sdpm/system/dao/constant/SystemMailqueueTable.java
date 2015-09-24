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

package org.tinygroup.sdpm.system.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/** 
 * 邮件队列
 * 
 */
public class SystemMailqueueTable extends Table {

	public static final SystemMailqueueTable SYSTEM_MAILQUEUETABLE = new SystemMailqueueTable();

	/** 
	 * 邮件队列ID
	 * 
	 */
	public final Column MAILQUEUE_ID = new Column(this, "mailqueue_id");

	/** 
	 * 发送给
	 * 
	 */
	public final Column MAILQUEUE_TOLIST = new Column(this, "mailqueue_toList");

	/** 
	 * 抄送给
	 * 
	 */
	public final Column MAILQUEUE_CCLIST = new Column(this, "mailqueue_ccList");

	/** 
	 * 邮箱队列主题
	 * 
	 */
	public final Column MAILQUEUE_SUBJECT = new Column(this, "mailqueue_subject");

	/** 
	 * 队列主体
	 * 
	 */
	public final Column MAILQUEUE_BODY = new Column(this, "mailqueue_body");

	/** 
	 * 添加日期
	 * 
	 */
	public final Column MAILQUEUE_ADDEDDATE = new Column(this, "mailqueue_addedDate");

	/** 
	 * 邮箱队列由谁添加
	 * 
	 */
	public final Column MAILQUEUE_ADDEDBY = new Column(this, "mailqueue_addedBy");

	/** 
	 * 传送时间
	 * 
	 */
	public final Column MAILQUEUE_SENDTIME = new Column(this, "mailqueue_sendTime");

	/** 
	 * 队列状态
	 * 
	 */
	public final Column MAILQUEUE_STATUS = new Column(this, "mailqueue_status");

	/** 
	 * 传送失败理由
	 * 
	 */
	public final Column MAILQUEUE_FAILREASON = new Column(this, "mailqueue_failReason");


	private SystemMailqueueTable() {
		super("system_mailqueue");
	}

}
