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

/** 
 * 项目需求
 * 
 */
public class Projectstory {

	/** 
	 * 逻辑ID
	 * 
	 */
	private Integer id;

	/** 
	 * 项目id
	 * 
	 */
	private Integer projectId;

	/** 
	 * 产品ID
	 * 
	 */
	private Integer productId;

	/** 
	 * 需求ID
	 * 
	 */
	private Integer storyId;

	/** 
	 * 需求版本
	 * 
	 */
	private Integer storyVersion;


	public void setId(Integer id){
		this. id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setProjectId(Integer projectId){
		this. projectId = projectId;
	}

	public Integer getProjectId(){
		return projectId;
	}

	public void setProductId(Integer productId){
		this. productId = productId;
	}

	public Integer getProductId(){
		return productId;
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

}
