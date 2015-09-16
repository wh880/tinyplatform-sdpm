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

package sdpm.product.dao.inter.dao.pojo;


public class StorySpec {

	/** 需求描述ID */
	private Integer storySpecId;

	/** 公司ID */
	private Integer companyId;

	/** 需求ID */
	private Integer storyId;

	/** 需求版本 */
	private Integer storyVersion;

	/** 需求标题 */
	private String storyTitle;

	/** 需求描述 */
	private String storySpec;

	/** 验证标准 */
	private String storyVerification;

	/** 已删除 */
	private String deleted;


	public void setStorySpecId(Integer storySpecId){
		this. storySpecId = storySpecId;
	}

	public Integer getStorySpecId(){
		return storySpecId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
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

	public void setStoryTitle(String storyTitle){
		this. storyTitle = storyTitle;
	}

	public String getStoryTitle(){
		return storyTitle;
	}

	public void setStorySpec(String storySpec){
		this. storySpec = storySpec;
	}

	public String getStorySpec(){
		return storySpec;
	}

	public void setStoryVerification(String storyVerification){
		this. storyVerification = storyVerification;
	}

	public String getStoryVerification(){
		return storyVerification;
	}

	public void setDeleted(String deleted){
		this. deleted = deleted;
	}

	public String getDeleted(){
		return deleted;
	}

}
