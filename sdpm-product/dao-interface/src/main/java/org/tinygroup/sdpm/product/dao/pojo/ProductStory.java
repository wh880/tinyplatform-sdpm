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

package org.tinygroup.sdpm.product.dao.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/** 
 * 需求表
 * 
 */
public class ProductStory implements Serializable{
	
	public ProductStory() {
		super();
	}

	private Integer releaseId;
	
	/**
	 * 抄送人
	 */
	private List<String> mailto;

	public List<String> getMailto() {
		return mailto;
	}

	public void setMailto(List<String> mailto) {
		this.mailto = mailto;
	}

	/**
	 * 关联任务的数量，用于前台显示
	 */
	private Integer taskNumber;
	/**
	 * 用于项目下需求模块关联需求列表显示所属项目
	 */
	private String productName;
	/**
	 * 用于项目下需求模块关联需求列表显示所属计划
	 */
	private String planName;
	
	/**
	 * 模块名称
	 */
	private String moduleTitle;
	
	/**
	 * 创建人名称
	 */
	private String storyOpenedName;
	
	/**
	 * 指派人名称
	 */
	private String storyAssignedName;
	
	public String getStoryOpenedName() {
		return storyOpenedName;
	}

	public void setStoryOpenedName(String storyOpenedName) {
		this.storyOpenedName = storyOpenedName;
	}

	public String getStoryAssignedName() {
		return storyAssignedName;
	}

	public void setStoryAssignedName(String storyAssignedName) {
		this.storyAssignedName = storyAssignedName;
	}

	/**
	 * 需求ID
	 *
	 */
	private Integer storyId;
	/**
	 * 公司ID
	 */
	private Integer companyId;
	/**
	 * 产品ID
	 *
	 */
	private Integer productId;
	/**
	 * 父需求ID
	 *
	 */
	private Integer storyParentId;
	/**
	 * 模块ID
	 *
	 */
	private Integer moduleId;
	/**
	 * 计划ID
	 *
	 */
	private Integer planId;
	/**
	 * 需求状态
	 *
	 * 0-created新建；1-accepted评审通过（接受，并纳入计划）；2-rejected评审不通过（拒绝，属终点状态）；3-canceled取消（无效或重复，属终点状态）；4-postponed待明确（需要重新评审），5-changed已变更（需要重新评审）；6-developing开发中（关联到项目）7-finished完成（通过测试验收，可发布），8-released已发布（创建了产品发布），9-closed关闭（终点状态）
	 */
	private String storyStatus;
	/**
	 * 需求来源
	 *
	 */
	private String storySource;
	/**
	 * 来源Bug
	 *
	 */
	private Integer storyFromBug;
	/**
	 * 需求标题
	 *
	 */
	private String storyTitle;
	/**
	 * 需求关键字
	 *
	 */
	private String storyKeywords;
	/**
	 * 需求类型
	 *
	 */
	private String storyType;
	/**
	 * 需求优先级
	 *
	 */
	private Integer storyPri;
	/**
	 * 预计工时
	 *
	 */
	private Float storyEstimate;
	/**
	 * 所处阶段
	 *
	 */
	private String storyStage;
	/**
	 * 邮件列表
	 *
	 */
	private String storyMailto;
	/**
	 * 由谁创建
	 *
	 */
	private String storyOpenedBy;
	/**
	 * 创建日期
	 *
	 */
	private Date storyOpenedDate;
	/**
	 * 需求指派
	 *
	 */
	private String storyAssignedTo;
	/**
	 * 需求指派日期
	 *
	 */
	private Date storyAssignedDate;
	/**
	 * 需求上次编辑者
	 *
	 */
	private String storyLastEditedBy;
	/**
	 * 需求上次编辑日期
	 *
	 */
	private Date storyLastEditedDate;
	/**
	 * 需求审核人
	 *
	 */
	private String storyReviewedBy;
	/**
	 * 需求审核日期
	 *
	 */
	private Date storyReviewedDate;
	/**
	 * 需求关闭者
	 *
	 */
	private String storyClosedBy;
	/**
	 * 需求关闭日期
	 *
	 */
	private Date storyClosedDate;
	/**
	 * 需求关闭原因
	 *
	 */
	private String storyClosedReason;
	/**
	 * 待定（关联bug）
	 *
	 */
	private Integer toBug;
	/**
	 * 关联需求
	 *
	 */
	private String storyLinkStories;
	/**
	 * 细分需求
	 *
	 */
	private String storyChildStories;
	/**
	 * 重复需求ID
	 *
	 */
	private Integer storyDuplicateStory;
	/**
	 * 需求版本
	 *
	 */
	private Integer storyVersion;
	/**
	 * 版本id
	 *
	 */
	private Integer buildId;
	/**
	 * 请求ID
	 *
	 * 服务请求ID
	 */
	private Integer clientRequestId;
	/**
	 * 已删除
	 *
	 */
	private Integer deleted;

	public Integer getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(Integer taskNumber) {
		this.taskNumber = taskNumber;
	}

	public Integer getStoryId() {
		return storyId;
	}

	public void setStoryId(Integer storyId){
		this. storyId = storyId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId){
		this. productId = productId;
	}

	public Integer getStoryParentId() {
		return storyParentId;
	}

	public void setStoryParentId(Integer storyParentId){
		this. storyParentId = storyParentId;
	}

	public Integer getModuleId() {
		return moduleId;
	}

	public void setModuleId(Integer moduleId){
		this. moduleId = moduleId;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId){
		this. planId = planId;
	}

	public String getStoryStatus() {
		return storyStatus;
	}

	public void setStoryStatus(String storyStatus){
		this. storyStatus = storyStatus;
	}

	public String getStorySource() {
		return storySource;
	}

	public void setStorySource(String storySource){
		this. storySource = storySource;
	}

	public Integer getStoryFromBug() {
		return storyFromBug;
	}

	public void setStoryFromBug(Integer storyFromBug){
		this. storyFromBug = storyFromBug;
	}

	public String getStoryTitle() {
		return storyTitle;
	}

	public void setStoryTitle(String storyTitle){
		this. storyTitle = storyTitle;
	}

	public String getStoryKeywords() {
		return storyKeywords;
	}

	public void setStoryKeywords(String storyKeywords){
		this. storyKeywords = storyKeywords;
	}

	public String getStoryType() {
		return storyType;
	}

	public void setStoryType(String storyType){
		this. storyType = storyType;
	}

	public Integer getStoryPri() {
		return storyPri;
	}

	public void setStoryPri(Integer storyPri){
		this. storyPri = storyPri;
	}

	public Float getStoryEstimate() {
		return storyEstimate;
	}

	public void setStoryEstimate(Float storyEstimate){
		this. storyEstimate = storyEstimate;
	}

	public String getStoryStage() {
		return storyStage;
	}

	public void setStoryStage(String storyStage){
		this. storyStage = storyStage;
	}

	public String getStoryMailto() {
		return storyMailto;
	}

	public void setStoryMailto(String storyMailto){
		this. storyMailto = storyMailto;
	}

	public String getStoryOpenedBy() {
		return storyOpenedBy;
	}

	public void setStoryOpenedBy(String storyOpenedBy){
		this. storyOpenedBy = storyOpenedBy;
	}

	public Date getStoryOpenedDate() {
		return storyOpenedDate;
	}

	public void setStoryOpenedDate(Date storyOpenedDate){
		this. storyOpenedDate = storyOpenedDate;
	}

	public String getStoryAssignedTo() {
		return storyAssignedTo;
	}

	public void setStoryAssignedTo(String storyAssignedTo){
		this. storyAssignedTo = storyAssignedTo;
	}

	public Date getStoryAssignedDate() {
		return storyAssignedDate;
	}

	public void setStoryAssignedDate(Date storyAssignedDate){
		this. storyAssignedDate = storyAssignedDate;
	}

	public String getStoryLastEditedBy() {
		return storyLastEditedBy;
	}

	public void setStoryLastEditedBy(String storyLastEditedBy){
		this. storyLastEditedBy = storyLastEditedBy;
	}

	public Date getStoryLastEditedDate() {
		return storyLastEditedDate;
	}

	public void setStoryLastEditedDate(Date storyLastEditedDate){
		this. storyLastEditedDate = storyLastEditedDate;
	}

	public String getStoryReviewedBy() {
		return storyReviewedBy;
	}

	public void setStoryReviewedBy(String storyReviewedBy){
		this. storyReviewedBy = storyReviewedBy;
	}

	public Date getStoryReviewedDate() {
		return storyReviewedDate;
	}

	public void setStoryReviewedDate(Date storyReviewedDate){
		this. storyReviewedDate = storyReviewedDate;
	}

	public String getStoryClosedBy() {
		return storyClosedBy;
	}

	public void setStoryClosedBy(String storyClosedBy){
		this. storyClosedBy = storyClosedBy;
	}

	public Date getStoryClosedDate() {
		return storyClosedDate;
	}

	public void setStoryClosedDate(Date storyClosedDate){
		this. storyClosedDate = storyClosedDate;
	}

	public String getStoryClosedReason() {
		return storyClosedReason;
	}

	public void setStoryClosedReason(String storyClosedReason){
		this. storyClosedReason = storyClosedReason;
	}

	public Integer getToBug() {
		return toBug;
	}

	public void setToBug(Integer toBug){
		this. toBug = toBug;
	}

	public String getStoryLinkStories() {
		return storyLinkStories;
	}

	public void setStoryLinkStories(String storyLinkStories){
		this. storyLinkStories = storyLinkStories;
	}

	public String getStoryChildStories() {
		return storyChildStories;
	}

	public void setStoryChildStories(String storyChildStories){
		this. storyChildStories = storyChildStories;
	}

	public Integer getStoryDuplicateStory() {
		return storyDuplicateStory;
	}

	public void setStoryDuplicateStory(Integer storyDuplicateStory){
		this. storyDuplicateStory = storyDuplicateStory;
	}

	public Integer getStoryVersion() {
		return storyVersion;
	}

	public void setStoryVersion(Integer storyVersion){
		this. storyVersion = storyVersion;
	}

	public Integer getBuildId() {
		return buildId;
	}

	public void setBuildId(Integer buildId){
		this. buildId = buildId;
	}

	public Integer getClientRequestId() {
		return clientRequestId;
	}

	public void setClientRequestId(Integer clientRequestId){
		this. clientRequestId = clientRequestId;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getModuleTitle() {
		return moduleTitle;
	}

	public void setModuleTitle(String moduleTitle) {
		this.moduleTitle = moduleTitle;
	}

	public Integer getReleaseId() {
		return releaseId;
	}

	public void setReleaseId(Integer releaseId) {
		this.releaseId = releaseId;
	}
}
