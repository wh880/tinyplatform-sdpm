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

package org.tinygroup.sdpm.document.dao.pojo;

import java.util.Date;

/** 
 * 文档编辑历史记录
 * 
 */
public class Historydoc {

	/** 
	 * 历史记录ID
	 * 
	 * 历史记录ID
	 */
	private Integer recordId;

	/** 
	 * 文档ID
	 * 
	 * 文档ID，主键，唯一标示
	 */
	private Integer docId;

	/** 
	 * 编辑时间
	 * 
	 * 编辑时间
	 */
	private Date recTime;

	/** 
	 * 谁创建或是编辑
	 * 
	 * 谁创建或是编辑
	 */
	private String recWho;


	public void setRecordId(Integer recordId){
		this. recordId = recordId;
	}

	public Integer getRecordId(){
		return recordId;
	}

	public void setDocId(Integer docId){
		this. docId = docId;
	}

	public Integer getDocId(){
		return docId;
	}

	public void setRecTime(Date recTime){
		this. recTime = recTime;
	}

	public Date getRecTime(){
		return recTime;
	}

	public void setRecWho(String recWho){
		this. recWho = recWho;
	}

	public String getRecWho(){
		return recWho;
	}

}
