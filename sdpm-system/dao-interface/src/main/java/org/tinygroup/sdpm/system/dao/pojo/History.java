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

/** 
 * 操作历史表
 * 
 */
public class History implements Serializable{

	/** 
	 * 操作历史ID
	 * 
	 */
	private Integer historyId;

	/** 
	 * 操作ID
	 * 
	 */
	private Integer historyAction;

	/** 
	 * 所属领域
	 * 
	 */
	private String historyField;

	/** 
	 * 当前历史
	 * 
	 */
	private String historyNew;

	/** 
	 * 上一条历史
	 * 
	 */
	private String historyOld;

	/** 
	 * 对比
	 * 
	 */
	private String historyDiff;


	public void setHistoryId(Integer historyId){
		this. historyId = historyId;
	}

	public Integer getHistoryId(){
		return historyId;
	}

	public void setHistoryAction(Integer historyAction){
		this. historyAction = historyAction;
	}

	public Integer getHistoryAction(){
		return historyAction;
	}

	public void setHistoryField(String historyField){
		this. historyField = historyField;
	}

	public String getHistoryField(){
		return historyField;
	}

	public void setHistoryNew(String historyNew){
		this. historyNew = historyNew;
	}

	public String getHistoryNew(){
		return historyNew;
	}

	public void setHistoryOld(String historyOld){
		this. historyOld = historyOld;
	}

	public String getHistoryOld(){
		return historyOld;
	}

	public void setHistoryDiff(String historyDiff){
		this. historyDiff = historyDiff;
	}

	public String getHistoryDiff(){
		return historyDiff;
	}

}
