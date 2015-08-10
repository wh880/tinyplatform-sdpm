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


public class History {

	/** 历史信息ID */
	private Integer historyId;

	/** 公司ID */
	private Integer companyId;

	/** 动作ID */
	private Integer actionId;

	/** 操作字段 */
	private String historyField;

	/** 旧值 */
	private String historyOld;

	/** 新值 */
	private String historyNew;

	/** 差别 */
	private String historyDiff;


	public void setHistoryId(Integer historyId){
		this. historyId = historyId;
	}

	public Integer getHistoryId(){
		return historyId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setActionId(Integer actionId){
		this. actionId = actionId;
	}

	public Integer getActionId(){
		return actionId;
	}

	public void setHistoryField(String historyField){
		this. historyField = historyField;
	}

	public String getHistoryField(){
		return historyField;
	}

	public void setHistoryOld(String historyOld){
		this. historyOld = historyOld;
	}

	public String getHistoryOld(){
		return historyOld;
	}

	public void setHistoryNew(String historyNew){
		this. historyNew = historyNew;
	}

	public String getHistoryNew(){
		return historyNew;
	}

	public void setHistoryDiff(String historyDiff){
		this. historyDiff = historyDiff;
	}

	public String getHistoryDiff(){
		return historyDiff;
	}

}
