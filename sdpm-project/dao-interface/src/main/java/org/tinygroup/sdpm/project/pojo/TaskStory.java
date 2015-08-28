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

package org.tinygroup.sdpm.project.pojo;


public class TaskStory {

	/** 任务需求ID */
	private Integer taskStoryId;

	/** 任务ID */
	private Integer taskId;

	/** 需求ID */
	private Integer storyId;

	/** 需求版本 */
	private Integer storyVersion;

	/** 公司ID */
	private Integer companyId;


	public void setTaskStoryId(Integer taskStoryId){
		this. taskStoryId = taskStoryId;
	}

	public Integer getTaskStoryId(){
		return taskStoryId;
	}

	public void setTaskId(Integer taskId){
		this. taskId = taskId;
	}

	public Integer getTaskId(){
		return taskId;
	}

	public void setStoryId(Integer storyId){
		this. storyId = storyId;
	}

	public Integer getStoryId(){
		return storyId;
	}

	public void setStoryVersion(Integer storyVersion){
		this. storyVersion = storyVersion;
	}

	public Integer getStoryVersion(){
		return storyVersion;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

}
