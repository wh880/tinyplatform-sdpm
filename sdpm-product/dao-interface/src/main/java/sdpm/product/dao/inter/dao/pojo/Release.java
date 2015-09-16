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

import java.util.Date;

public class Release {

	/** 发布ID */
	private Integer releaseId;

	/** 产品ID */
	private Integer productId;

	/** 版本 */
	private String build;

	/** 发布名称 */
	private String releaseName;

	/** 发布日期 */
	private Date releaseDate;

	/** 已完成需求 */
	private String releaseStories;

	/** 已解决Bug */
	private String releaseBugs;

	/** 描述 */
	private String releaseDesc;

	/** 已删除 */
	private String deleted;


	public void setReleaseId(Integer releaseId){
		this. releaseId = releaseId;
	}

	public Integer getReleaseId(){
		return releaseId;
	}

	public void setProductId(Integer productId){
		this. productId = productId;
	}

	public Integer getProductId(){
		return productId;
	}

	public void setBuild(String build){
		this. build = build;
	}

	public String getBuild(){
		return build;
	}

	public void setReleaseName(String releaseName){
		this. releaseName = releaseName;
	}

	public String getReleaseName(){
		return releaseName;
	}

	public void setReleaseDate(Date releaseDate){
		this. releaseDate = releaseDate;
	}

	public Date getReleaseDate(){
		return releaseDate;
	}

	public void setReleaseStories(String releaseStories){
		this. releaseStories = releaseStories;
	}

	public String getReleaseStories(){
		return releaseStories;
	}

	public void setReleaseBugs(String releaseBugs){
		this. releaseBugs = releaseBugs;
	}

	public String getReleaseBugs(){
		return releaseBugs;
	}

	public void setReleaseDesc(String releaseDesc){
		this. releaseDesc = releaseDesc;
	}

	public String getReleaseDesc(){
		return releaseDesc;
	}

	public void setDeleted(String deleted){
		this. deleted = deleted;
	}

	public String getDeleted(){
		return deleted;
	}

}
