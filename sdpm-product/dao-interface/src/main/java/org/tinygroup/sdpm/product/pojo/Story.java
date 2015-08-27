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

public class Story {

	/** 需求ID */
	private Integer storyId;

	/** 公司ID */
	private Integer companyId;

	/** 产品ID */
	private Integer productId;

	/** 模块ID */
	private Integer moduleId;

	/** 发布计划ID */
	private Integer planId;

	/** 需求发布ID */
	private Integer releaseId;

	/** 父需求ID */
	private Integer storyParentId;

	/** 需求来源 */
	private String storySource;

	/** 需求标题 */
	private String storyTitle;

	/** 需求关键字 */
	private String storyKeywords;

	/** 需求类型 */
	private String storyType;

	/** 需求优先级 */
	private Integer storyPri;

	/** 需求估算 */
	private Float storyEstimate;

	/** 需求状态 */
	private String storyStatus;

	/** 邮件列表 */
	private String storyMailto;

	/** 需求打开者 */
	private String storyOpenedBy;

	/** 需求打开日期 */
	private Date storyOpenedDate;

	/** 需求指派 */
	private String storyAssignedTo;

	/** 需求指派日期 */
	private Date storyAssignedDate;

	/** 需求上次编辑者 */
	private String storyLastEditedBy;

	/** 需求上次编辑日期 */
	private Date storyLastEditedDate;

	/** 需求审核人 */
	private String storyReviewedBy;

	/** 需求审核日期 */
	private Date storyReviewedDate;

	/** 需求关闭者 */
	private String storyClosedBy;

	/** 需求关闭日期 */
	private Date storyClosedDate;

	/** 需求关闭原因 */
	private String storyClosedReason;

	/** STORY_TO_BUG */
	private Integer storyToBug;

	/** 关联需求 */
	private String storyLinkStories;

	/** 重复需求ID */
	private Integer storyDuplicateStory;

	/** 需求版本 */
	private Integer storyVersion;

	/** 缺陷ID */
	private Integer bugId;

	/** 客户请求ID */
	private Integer requestId;

	/** 删除标记 */
	private Integer deleted;


	public void setStoryId(Integer storyId){
		this. storyId = storyId;
	}

	public Integer getStoryId(){
		return storyId;
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

	public void setModuleId(Integer moduleId){
		this. moduleId = moduleId;
	}

	public Integer getModuleId(){
		return moduleId;
	}

	public void setPlanId(Integer planId){
		this. planId = planId;
	}

	public Integer getPlanId(){
		return planId;
	}

	public void setReleaseId(Integer releaseId){
		this. releaseId = releaseId;
	}

	public Integer getReleaseId(){
		return releaseId;
	}

	public void setStoryParentId(Integer storyParentId){
		this. storyParentId = storyParentId;
	}

	public Integer getStoryParentId(){
		return storyParentId;
	}

	public void setStorySource(String storySource){
		this. storySource = storySource;
	}

	public String getStorySource(){
		return storySource;
	}

	public void setStoryTitle(String storyTitle){
		this. storyTitle = storyTitle;
	}

	public String getStoryTitle(){
		return storyTitle;
	}

	public void setStoryKeywords(String storyKeywords){
		this. storyKeywords = storyKeywords;
	}

	public String getStoryKeywords(){
		return storyKeywords;
	}

	public void setStoryType(String storyType){
		this. storyType = storyType;
	}

	public String getStoryType(){
		return storyType;
	}

	public void setStoryPri(Integer storyPri){
		this. storyPri = storyPri;
	}

	public Integer getStoryPri(){
		return storyPri;
	}

	public void setStoryEstimate(Float storyEstimate){
		this. storyEstimate = storyEstimate;
	}

	public Float getStoryEstimate(){
		return storyEstimate;
	}

	public void setStoryStatus(String storyStatus){
		this. storyStatus = storyStatus;
	}

	public String getStoryStatus(){
		return storyStatus;
	}

	public void setStoryMailto(String storyMailto){
		this. storyMailto = storyMailto;
	}

	public String getStoryMailto(){
		return storyMailto;
	}

	public void setStoryOpenedBy(String storyOpenedBy){
		this. storyOpenedBy = storyOpenedBy;
	}

	public String getStoryOpenedBy(){
		return storyOpenedBy;
	}

	public void setStoryOpenedDate(Date storyOpenedDate){
		this. storyOpenedDate = storyOpenedDate;
	}

	public Date getStoryOpenedDate(){
		return storyOpenedDate;
	}

	public void setStoryAssignedTo(String storyAssignedTo){
		this. storyAssignedTo = storyAssignedTo;
	}

	public String getStoryAssignedTo(){
		return storyAssignedTo;
	}

	public void setStoryAssignedDate(Date storyAssignedDate){
		this. storyAssignedDate = storyAssignedDate;
	}

	public Date getStoryAssignedDate(){
		return storyAssignedDate;
	}

	public void setStoryLastEditedBy(String storyLastEditedBy){
		this. storyLastEditedBy = storyLastEditedBy;
	}

	public String getStoryLastEditedBy(){
		return storyLastEditedBy;
	}

	public void setStoryLastEditedDate(Date storyLastEditedDate){
		this. storyLastEditedDate = storyLastEditedDate;
	}

	public Date getStoryLastEditedDate(){
		return storyLastEditedDate;
	}

	public void setStoryReviewedBy(String storyReviewedBy){
		this. storyReviewedBy = storyReviewedBy;
	}

	public String getStoryReviewedBy(){
		return storyReviewedBy;
	}

	public void setStoryReviewedDate(Date storyReviewedDate){
		this. storyReviewedDate = storyReviewedDate;
	}

	public Date getStoryReviewedDate(){
		return storyReviewedDate;
	}

	public void setStoryClosedBy(String storyClosedBy){
		this. storyClosedBy = storyClosedBy;
	}

	public String getStoryClosedBy(){
		return storyClosedBy;
	}

	public void setStoryClosedDate(Date storyClosedDate){
		this. storyClosedDate = storyClosedDate;
	}

	public Date getStoryClosedDate(){
		return storyClosedDate;
	}

	public void setStoryClosedReason(String storyClosedReason){
		this. storyClosedReason = storyClosedReason;
	}

	public String getStoryClosedReason(){
		return storyClosedReason;
	}

	public void setStoryToBug(Integer storyToBug){
		this. storyToBug = storyToBug;
	}

	public Integer getStoryToBug(){
		return storyToBug;
	}

	public void setStoryLinkStories(String storyLinkStories){
		this. storyLinkStories = storyLinkStories;
	}

	public String getStoryLinkStories(){
		return storyLinkStories;
	}

	public void setStoryDuplicateStory(Integer storyDuplicateStory){
		this. storyDuplicateStory = storyDuplicateStory;
	}

	public Integer getStoryDuplicateStory(){
		return storyDuplicateStory;
	}

	public void setStoryVersion(Integer storyVersion){
		this. storyVersion = storyVersion;
	}

	public Integer getStoryVersion(){
		return storyVersion;
	}

	public void setBugId(Integer bugId){
		this. bugId = bugId;
	}

	public Integer getBugId(){
		return bugId;
	}

	public void setRequestId(Integer requestId){
		this. requestId = requestId;
	}

	public Integer getRequestId(){
		return requestId;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}