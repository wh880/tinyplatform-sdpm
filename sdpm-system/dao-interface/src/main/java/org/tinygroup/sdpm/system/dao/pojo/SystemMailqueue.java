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

package org.tinygroup.sdpm.system.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/** 
 * 邮件队列
 * 
 */
public class SystemMailqueue implements Serializable{

	/** 
	 * 邮件队列ID
	 * 
	 */
	private Integer mailqueueId;

	/** 
	 * 发送给
	 * 
	 */
	private String mailqueueToList;

	/** 
	 * 抄送给
	 * 
	 */
	private String mailqueueCcList;

	/** 
	 * 邮箱队列主题
	 * 
	 */
	private String mailqueueSubject;

	/** 
	 * 队列主体
	 * 
	 */
	private String mailqueueBody;

	/** 
	 * 添加日期
	 * 
	 */
	private Date mailqueueAddedDate;

	/** 
	 * 邮箱队列由谁添加
	 * 
	 */
	private String mailqueueAddedBy;

	/** 
	 * 传送时间
	 * 
	 */
	private Date mailqueueSendTime;

	/** 
	 * 队列状态
	 * 
	 */
	private String mailqueueStatus;

	/** 
	 * 传送失败理由
	 * 
	 */
	private String mailqueueFailReason;


	public void setMailqueueId(Integer mailqueueId){
		this. mailqueueId = mailqueueId;
	}

	public Integer getMailqueueId(){
		return mailqueueId;
	}

	public void setMailqueueToList(String mailqueueToList){
		this. mailqueueToList = mailqueueToList;
	}

	public String getMailqueueToList(){
		return mailqueueToList;
	}

	public void setMailqueueCcList(String mailqueueCcList){
		this. mailqueueCcList = mailqueueCcList;
	}

	public String getMailqueueCcList(){
		return mailqueueCcList;
	}

	public void setMailqueueSubject(String mailqueueSubject){
		this. mailqueueSubject = mailqueueSubject;
	}

	public String getMailqueueSubject(){
		return mailqueueSubject;
	}

	public void setMailqueueBody(String mailqueueBody){
		this. mailqueueBody = mailqueueBody;
	}

	public String getMailqueueBody(){
		return mailqueueBody;
	}

	public void setMailqueueAddedDate(Date mailqueueAddedDate){
		this. mailqueueAddedDate = mailqueueAddedDate;
	}

	public Date getMailqueueAddedDate(){
		return mailqueueAddedDate;
	}

	public void setMailqueueAddedBy(String mailqueueAddedBy){
		this. mailqueueAddedBy = mailqueueAddedBy;
	}

	public String getMailqueueAddedBy(){
		return mailqueueAddedBy;
	}

	public void setMailqueueSendTime(Date mailqueueSendTime){
		this. mailqueueSendTime = mailqueueSendTime;
	}

	public Date getMailqueueSendTime(){
		return mailqueueSendTime;
	}

	public void setMailqueueStatus(String mailqueueStatus){
		this. mailqueueStatus = mailqueueStatus;
	}

	public String getMailqueueStatus(){
		return mailqueueStatus;
	}

	public void setMailqueueFailReason(String mailqueueFailReason){
		this. mailqueueFailReason = mailqueueFailReason;
	}

	public String getMailqueueFailReason(){
		return mailqueueFailReason;
	}

}
