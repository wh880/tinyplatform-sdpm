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

package org.tinygroup.sdpm.project.dao.pojo;

import java.util.Date;

/** 
 * 燃尽图
 * 
 */
public class Burn {

	/** 
	 * 项目id
	 * 
	 */
	private Integer projectId;

	/** 
	 * 日期
	 * 
	 */
	private Date burnDate;

	/** 
	 * 剩余
	 * 
	 */
	private Float burnLeft;

	/** 
	 * 消耗
	 * 
	 */
	private Float burnConsumed;


	public void setProjectId(Integer projectId){
		this. projectId = projectId;
	}

	public Integer getProjectId(){
		return projectId;
	}

	public void setBurnDate(Date burnDate){
		this. burnDate = burnDate;
	}

	public Date getBurnDate(){
		return burnDate;
	}

	public void setBurnLeft(Float burnLeft){
		this. burnLeft = burnLeft;
	}

	public Float getBurnLeft(){
		return burnLeft;
	}

	public void setBurnConsumed(Float burnConsumed){
		this. burnConsumed = burnConsumed;
	}

	public Float getBurnConsumed(){
		return burnConsumed;
	}

}
