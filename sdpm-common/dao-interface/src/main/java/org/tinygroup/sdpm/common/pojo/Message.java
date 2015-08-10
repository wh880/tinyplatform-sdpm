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

package org.tinygroup.sdpm.common.pojo;

import java.util.Date;

public class Message {

	/** 消息ID */
	private Integer messageId;

	/** 公司ID */
	private Integer companyId;

	/** 消息发送者 */
	private String messageSender;

	/** 消息接收者 */
	private String messageReceiver;

	/** 消息发送时间 */
	private Date messageSenddate;

	/** 消息接收时间 */
	private Date messageReceivedate;

	/** 消息内容 */
	private String messageContent;

	/** 消息类型 */
	private String messageType;

	/** 消息级别 */
	private String messageLevel;

	/** 消息标志 */
	private String messageFlag;


	public void setMessageId(Integer messageId){
		this. messageId = messageId;
	}

	public Integer getMessageId(){
		return messageId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setMessageSender(String messageSender){
		this. messageSender = messageSender;
	}

	public String getMessageSender(){
		return messageSender;
	}

	public void setMessageReceiver(String messageReceiver){
		this. messageReceiver = messageReceiver;
	}

	public String getMessageReceiver(){
		return messageReceiver;
	}

	public void setMessageSenddate(Date messageSenddate){
		this. messageSenddate = messageSenddate;
	}

	public Date getMessageSenddate(){
		return messageSenddate;
	}

	public void setMessageReceivedate(Date messageReceivedate){
		this. messageReceivedate = messageReceivedate;
	}

	public Date getMessageReceivedate(){
		return messageReceivedate;
	}

	public void setMessageContent(String messageContent){
		this. messageContent = messageContent;
	}

	public String getMessageContent(){
		return messageContent;
	}

	public void setMessageType(String messageType){
		this. messageType = messageType;
	}

	public String getMessageType(){
		return messageType;
	}

	public void setMessageLevel(String messageLevel){
		this. messageLevel = messageLevel;
	}

	public String getMessageLevel(){
		return messageLevel;
	}

	public void setMessageFlag(String messageFlag){
		this. messageFlag = messageFlag;
	}

	public String getMessageFlag(){
		return messageFlag;
	}

}
