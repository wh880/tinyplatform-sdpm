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

package org.tinygroup.sdpm.quality.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/** 
 * 缺陷表
 * 
 */
public class QualityBug implements Serializable {

	/** 
	 * Bug编号
	 * 
	 */
	private Integer bugId;

	/** 
	 * 产品ID
	 * 
	 */
	private Integer productId;

	/** 
	 * 模块ID
	 * 
	 */
	private Integer moduleId;

	/** 
	 * 项目id
	 * 
	 */
	private Integer projectId;

	/** 
	 * 计划ID
	 * 
	 */
	private Integer planId;

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

	/** 
	 * 任务id
	 * 
	 */
	private Integer taskId;

	/** 
	 * 转任务
	 * 
	 */
	private Integer toTaskId;

	/** 
	 * 转需求
	 * 
	 */
	private Integer toStoryId;

	/** 
	 * Bug标题
	 * 
	 */
	private String bugTitle;

	/** 
	 * 关键词
	 * 
	 */
	private String bugKeywords;

	/** 
	 * 严重程度
	 * 
	 */
	private Integer bugSeverity;

	/** 
	 * 优先级
	 * 
	 */
	private Integer priority;

	/** 
	 * Bug类型
	 * 
	 */
	private String bugType;

	/** 
	 * 操作系统
	 * 
	 */
	private String operatingSystem;

	/** 
	 * 浏览器
	 * 
	 */
	private String browser;

	/** 
	 * 硬件平台
	 * 
	 */
	private String hardware;

	/** 
	 * 如何发现
	 * 
	 */
	private String bugFound;

	/** 
	 * 重现步骤
	 * 
	 */
	private String bugSteps;

	/** 
	 * Bug状态
	 * 
	 */
	private String bugStatus;

	/** 
	 * 是否确认
	 * 
	 */
	private Integer bugConfirmed;

	/** 
	 * 激活次数
	 * 
	 */
	private Integer bugActivatedCount;

	/** 
	 * 抄送给
	 * 
	 */
	private String bugMailto;

	/** 
	 * 由谁创建
	 * 
	 */
	private String bugOpenedBy;

	/** 
	 * 创建日期
	 * 
	 */
	private Date bugOpenedDate;

	/** 
	 * 影响版本
	 * 
	 */
	private String bugOpenedBuild;

	/** 
	 * 指派给
	 * 
	 */
	private String bugAssignedTo;

	/** 
	 * 指派日期
	 * 
	 */
	private Date bugAssignedDate;

	/** 
	 * 解决者
	 * 
	 */
	private String bugResolvedBy;

	/** 
	 * 解决方案
	 * 
	 */
	private String bugResolution;

	/** 
	 * 解决版本
	 * 
	 */
	private String bugResolvedBuild;

	/** 
	 * 解决日期
	 * 
	 */
	private Date bugResolvedDate;

	/** 
	 * 由谁关闭
	 * 
	 */
	private String bugClosedBy;

	/** 
	 * 关闭日期
	 * 
	 */
	private Date bugClosedDate;

	/** 
	 * 重复Bug的ID
	 * 
	 */
	private Integer bugDuplicateBug;

	/** 
	 * 相关Bug
	 * 
	 */
	private String linkBug;

	/** 
	 * 相关用例
	 * 
	 */
	private Integer linkCase;

	/** 
	 * 关联用例版本
	 * 
	 */
	private Integer caseVersion;

	/** 
	 * BUG_RESULT
	 * 
	 */
	private Integer bugResult;

	/** 
	 * BUG_REPO
	 * 
	 */
	private Integer bugRepo;

	/** 
	 * BUG_ENTRY
	 * 
	 */
	private String bugEntry;

	/** 
	 * BUG_LINES
	 * 
	 */
	private String bugLines;

	/** 
	 * BUG_V1
	 * 
	 */
	private String bugV1;

	/** 
	 * BUG_V2
	 * 
	 */
	private String bugV2;

	/** 
	 * BUG_REPOTYPE
	 * 
	 */
	private String bugRepoType;

	/** 
	 * 测试任务编号
	 * 
	 */
	private Integer testtask;

	/** 
	 * 最后修改者
	 * 
	 */
	private String bugLastEditedBy;

	/** 
	 * 最后修改日期
	 * 
	 */
	private Date bugLastEditedDate;

	/** 
	 * 已删除
	 * 
	 */
	private Integer deleted;


	public void setBugId(Integer bugId){
		this. bugId = bugId;
	}

	public Integer getBugId(){
		return bugId;
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

	public void setProjectId(Integer projectId){
		this. projectId = projectId;
	}

	public Integer getProjectId(){
		return projectId;
	}

	public void setPlanId(Integer planId){
		this. planId = planId;
	}

	public Integer getPlanId(){
		return planId;
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

	public void setTaskId(Integer taskId){
		this. taskId = taskId;
	}

	public Integer getTaskId(){
		return taskId;
	}

	public void setToTaskId(Integer toTaskId){
		this. toTaskId = toTaskId;
	}

	public Integer getToTaskId(){
		return toTaskId;
	}

	public void setToStoryId(Integer toStoryId){
		this. toStoryId = toStoryId;
	}

	public Integer getToStoryId(){
		return toStoryId;
	}

	public void setBugTitle(String bugTitle){
		this. bugTitle = bugTitle;
	}

	public String getBugTitle(){
		return bugTitle;
	}

	public void setBugKeywords(String bugKeywords){
		this. bugKeywords = bugKeywords;
	}

	public String getBugKeywords(){
		return bugKeywords;
	}

	public void setBugSeverity(Integer bugSeverity){
		this. bugSeverity = bugSeverity;
	}

	public Integer getBugSeverity(){
		return bugSeverity;
	}

	public void setPriority(Integer priority){
		this. priority = priority;
	}

	public Integer getPriority(){
		return priority;
	}

	public void setBugType(String bugType){
		this. bugType = bugType;
	}

	public String getBugType(){
		return bugType;
	}

	public void setOperatingSystem(String operatingSystem){
		this. operatingSystem = operatingSystem;
	}

	public String getOperatingSystem(){
		return operatingSystem;
	}

	public void setBrowser(String browser){
		this. browser = browser;
	}

	public String getBrowser(){
		return browser;
	}

	public void setHardware(String hardware){
		this. hardware = hardware;
	}

	public String getHardware(){
		return hardware;
	}

	public void setBugFound(String bugFound){
		this. bugFound = bugFound;
	}

	public String getBugFound(){
		return bugFound;
	}

	public void setBugSteps(String bugSteps){
		this. bugSteps = bugSteps;
	}

	public String getBugSteps(){
		return bugSteps;
	}

	public void setBugStatus(String bugStatus){
		this. bugStatus = bugStatus;
	}

	public String getBugStatus(){
		return bugStatus;
	}

	public void setBugConfirmed(Integer bugConfirmed){
		this. bugConfirmed = bugConfirmed;
	}

	public Integer getBugConfirmed(){
		return bugConfirmed;
	}

	public void setBugActivatedCount(Integer bugActivatedCount){
		this. bugActivatedCount = bugActivatedCount;
	}

	public Integer getBugActivatedCount(){
		return bugActivatedCount;
	}

	public void setBugMailto(String bugMailto){
		this. bugMailto = bugMailto;
	}

	public String getBugMailto(){
		return bugMailto;
	}

	public void setBugOpenedBy(String bugOpenedBy){
		this. bugOpenedBy = bugOpenedBy;
	}

	public String getBugOpenedBy(){
		return bugOpenedBy;
	}

	public void setBugOpenedDate(Date bugOpenedDate){
		this. bugOpenedDate = bugOpenedDate;
	}

	public Date getBugOpenedDate(){
		return bugOpenedDate;
	}

	public void setBugOpenedBuild(String bugOpenedBuild){
		this. bugOpenedBuild = bugOpenedBuild;
	}

	public String getBugOpenedBuild(){
		return bugOpenedBuild;
	}

	public void setBugAssignedTo(String bugAssignedTo){
		this. bugAssignedTo = bugAssignedTo;
	}

	public String getBugAssignedTo(){
		return bugAssignedTo;
	}

	public void setBugAssignedDate(Date bugAssignedDate){
		this. bugAssignedDate = bugAssignedDate;
	}

	public Date getBugAssignedDate(){
		return bugAssignedDate;
	}

	public void setBugResolvedBy(String bugResolvedBy){
		this. bugResolvedBy = bugResolvedBy;
	}

	public String getBugResolvedBy(){
		return bugResolvedBy;
	}

	public void setBugResolution(String bugResolution){
		this. bugResolution = bugResolution;
	}

	public String getBugResolution(){
		return bugResolution;
	}

	public void setBugResolvedBuild(String bugResolvedBuild){
		this. bugResolvedBuild = bugResolvedBuild;
	}

	public String getBugResolvedBuild(){
		return bugResolvedBuild;
	}

	public void setBugResolvedDate(Date bugResolvedDate){
		this. bugResolvedDate = bugResolvedDate;
	}

	public Date getBugResolvedDate(){
		return bugResolvedDate;
	}

	public void setBugClosedBy(String bugClosedBy){
		this. bugClosedBy = bugClosedBy;
	}

	public String getBugClosedBy(){
		return bugClosedBy;
	}

	public void setBugClosedDate(Date bugClosedDate){
		this. bugClosedDate = bugClosedDate;
	}

	public Date getBugClosedDate(){
		return bugClosedDate;
	}

	public void setBugDuplicateBug(Integer bugDuplicateBug){
		this. bugDuplicateBug = bugDuplicateBug;
	}

	public Integer getBugDuplicateBug(){
		return bugDuplicateBug;
	}

	public void setLinkBug(String linkBug){
		this. linkBug = linkBug;
	}

	public String getLinkBug(){
		return linkBug;
	}

	public void setLinkCase(Integer linkCase){
		this. linkCase = linkCase;
	}

	public Integer getLinkCase(){
		return linkCase;
	}

	public void setCaseVersion(Integer caseVersion){
		this. caseVersion = caseVersion;
	}

	public Integer getCaseVersion(){
		return caseVersion;
	}

	public void setBugResult(Integer bugResult){
		this. bugResult = bugResult;
	}

	public Integer getBugResult(){
		return bugResult;
	}

	public void setBugRepo(Integer bugRepo){
		this. bugRepo = bugRepo;
	}

	public Integer getBugRepo(){
		return bugRepo;
	}

	public void setBugEntry(String bugEntry){
		this. bugEntry = bugEntry;
	}

	public String getBugEntry(){
		return bugEntry;
	}

	public void setBugLines(String bugLines){
		this. bugLines = bugLines;
	}

	public String getBugLines(){
		return bugLines;
	}

	public void setBugV1(String bugV1){
		this. bugV1 = bugV1;
	}

	public String getBugV1(){
		return bugV1;
	}

	public void setBugV2(String bugV2){
		this. bugV2 = bugV2;
	}

	public String getBugV2(){
		return bugV2;
	}

	public void setBugRepoType(String bugRepoType){
		this. bugRepoType = bugRepoType;
	}

	public String getBugRepoType(){
		return bugRepoType;
	}

	public void setTesttask(Integer testtask){
		this. testtask = testtask;
	}

	public Integer getTesttask(){
		return testtask;
	}

	public void setBugLastEditedBy(String bugLastEditedBy){
		this. bugLastEditedBy = bugLastEditedBy;
	}

	public String getBugLastEditedBy(){
		return bugLastEditedBy;
	}

	public void setBugLastEditedDate(Date bugLastEditedDate){
		this. bugLastEditedDate = bugLastEditedDate;
	}

	public Date getBugLastEditedDate(){
		return bugLastEditedDate;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
