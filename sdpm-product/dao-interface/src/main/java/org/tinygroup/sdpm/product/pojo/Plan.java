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

package org.tinygroup.sdpm.product.pojo;

import java.util.Date;

public class Plan {

	/** 发布计划ID */
	private Integer planId;

	/** 公司ID */
	private Integer companyId;

	/** 产品ID */
	private Integer productId;

	/** 规划版本名称 */
	private String planName;

	/** 规划描述 */
	private String planSpec;

	/** 规划开始时间 */
	private Date planBeginDate;

	/** 规划结束时间 */
	private Date planEndDate;

	/** 集成版本ID */
	private Integer buildId;

	/** 发放日期 */
	private Date planReleaseDate;

	/** 版本需求列表 */
	private String planStories;

	/** 版本缺陷列表 */
	private String planBugs;

	/** 规划投入 */
	private Float planEffort;

	/** 删除标记 */
	private Integer deleted;


	public void setPlanId(Integer planId){
		this. planId = planId;
	}

	public Integer getPlanId(){
		return planId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setProductId(Integer productId){
		this. productId = productId;
	}

	public Integer getProductId(){
		return productId;
	}

	public void setPlanName(String planName){
		this. planName = planName;
	}

	public String getPlanName(){
		return planName;
	}

	public void setPlanSpec(String planSpec){
		this. planSpec = planSpec;
	}

	public String getPlanSpec(){
		return planSpec;
	}

	public void setPlanBeginDate(Date planBeginDate){
		this. planBeginDate = planBeginDate;
	}

	public Date getPlanBeginDate(){
		return planBeginDate;
	}

	public void setPlanEndDate(Date planEndDate){
		this. planEndDate = planEndDate;
	}

	public Date getPlanEndDate(){
		return planEndDate;
	}

	public void setBuildId(Integer buildId){
		this. buildId = buildId;
	}

	public Integer getBuildId(){
		return buildId;
	}

	public void setPlanReleaseDate(Date planReleaseDate){
		this. planReleaseDate = planReleaseDate;
	}

	public Date getPlanReleaseDate(){
		return planReleaseDate;
	}

	public void setPlanStories(String planStories){
		this. planStories = planStories;
	}

	public String getPlanStories(){
		return planStories;
	}

	public void setPlanBugs(String planBugs){
		this. planBugs = planBugs;
	}

	public String getPlanBugs(){
		return planBugs;
	}

	public void setPlanEffort(Float planEffort){
		this. planEffort = planEffort;
	}

	public Float getPlanEffort(){
		return planEffort;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
