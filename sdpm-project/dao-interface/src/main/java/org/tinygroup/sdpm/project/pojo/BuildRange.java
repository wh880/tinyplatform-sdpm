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


public class BuildRange {

	/** 集成范围ID */
	private Integer buildRangeId;

	/** 集成版本ID */
	private Integer buildId;

	/** 关联对象ID */
	private Integer objectId;

	/** 关联对象类型 */
	private Integer objectType;


	public void setBuildRangeId(Integer buildRangeId){
		this. buildRangeId = buildRangeId;
	}

	public Integer getBuildRangeId(){
		return buildRangeId;
	}

	public void setBuildId(Integer buildId){
		this. buildId = buildId;
	}

	public Integer getBuildId(){
		return buildId;
	}

	public void setObjectId(Integer objectId){
		this. objectId = objectId;
	}

	public Integer getObjectId(){
		return objectId;
	}

	public void setObjectType(Integer objectType){
		this. objectType = objectType;
	}

	public Integer getObjectType(){
		return objectType;
	}

}
