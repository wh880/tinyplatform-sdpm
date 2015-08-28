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

package org.tinygroup.sdpm.quality.pojo;

import java.util.Date;

public class Bug {

	/** 缺陷ID */
	private Integer bugId;

	/** 公司ID */
	private Integer companyId;

	/** 产品ID */
	private Integer productId;

	/** 需求ID */
	private Integer storyId;

	/** 需求版本 */
	private Integer storyVersion;

	/** 项目ID */
	private Integer projectId;

	/** 转为需求ID */
	private Integer toStoryId;

	/** 任务ID */
	private Integer taskId;

	/** 转为任务ID */
	private Integer toTaskId;

	/** 缺陷标题 */
	private String bugTitle;

	/** 模块ID */
	private Integer moduleId;

	/** 关键字 */
	private String bugKeywords;

	/** 严重级别 */
	private Integer bugSeverity;

	/** 优先级 */
	private Integer bugPri;

	/** 缺陷类型 */
	private String bugType;

	/** 操作系统 */
	private String bugOs;

	/** 浏览器 */
	private String bugBrowser;

	/** 硬件 */
	private String bugHardware;

	/** 重现步骤 */
	private String bugSteps;

	/** 缺陷状态 */
	private Integer bugStatus;

	/** 是否确认 */
	private Integer bugConfirmed;

	/** 打开次数 */
	private Integer bugOpenCount;

	/** 邮件列表 */
	private String bugMailto;

	/** 缺陷创建人 */
	private String bugOpenedBy;

	/** 创建日期 */
	private Date bugOpenedDate;

	/** 影响版本 */
	private String bugOpenedBuild;

	/** 指派给 */
	private String bugAssignedTo;

	/** 指派日期 */
	private Date bugAssignedDate;

	/** 缺陷解决者 */
	private String bugResolvedBy;

	/** 解决方案 */
	private String bugResolution;

	/** 解决版本 */
	private String bugResolvedBuild;

	/** 解决日期 */
	private Date bugResolvedDate;

	/** 缺陷关闭者 */
	private String bugClosedBy;

	/** 缺陷关闭日期 */
	private Date bugClosedDate;

	/** 重复缺陷ID */
	private Integer bugDuplicateBug;

	/** 关联缺陷 */
	private String bugLinkBug;

	/** 缺陷最后编辑者 */
	private String bugLastEditedBy;

	/** 缺陷最后编辑日期 */
	private Date bugLastEditedDate;

	/** 关联用例ID */
	private Integer caseId;

	/** 关联用例版本 */
	private Integer caseVersion;

	/** 缺陷来源 */
	private String bugSource;

	/** 缺陷起源 */
	private String bugOrigin;

	/** 删除标记 */
	private Integer deleted;


	public void setBugId(Integer bugId){
		this. bugId = bugId;
	}

	public Integer getBugId(){
		return bugId;
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

	public void setProjectId(Integer projectId){
		this. projectId = projectId;
	}

	public Integer getProjectId(){
		return projectId;
	}

	public void setToStoryId(Integer toStoryId){
		this. toStoryId = toStoryId;
	}

	public Integer getToStoryId(){
		return toStoryId;
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

	public void setBugTitle(String bugTitle){
		this. bugTitle = bugTitle;
	}

	public String getBugTitle(){
		return bugTitle;
	}

	public void setModuleId(Integer moduleId){
		this. moduleId = moduleId;
	}

	public Integer getModuleId(){
		return moduleId;
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

	public void setBugPri(Integer bugPri){
		this. bugPri = bugPri;
	}

	public Integer getBugPri(){
		return bugPri;
	}

	public void setBugType(String bugType){
		this. bugType = bugType;
	}

	public String getBugType(){
		return bugType;
	}

	public void setBugOs(String bugOs){
		this. bugOs = bugOs;
	}

	public String getBugOs(){
		return bugOs;
	}

	public void setBugBrowser(String bugBrowser){
		this. bugBrowser = bugBrowser;
	}

	public String getBugBrowser(){
		return bugBrowser;
	}

	public void setBugHardware(String bugHardware){
		this. bugHardware = bugHardware;
	}

	public String getBugHardware(){
		return bugHardware;
	}

	public void setBugSteps(String bugSteps){
		this. bugSteps = bugSteps;
	}

	public String getBugSteps(){
		return bugSteps;
	}

	public void setBugStatus(Integer bugStatus){
		this. bugStatus = bugStatus;
	}

	public Integer getBugStatus(){
		return bugStatus;
	}

	public void setBugConfirmed(Integer bugConfirmed){
		this. bugConfirmed = bugConfirmed;
	}

	public Integer getBugConfirmed(){
		return bugConfirmed;
	}

	public void setBugOpenCount(Integer bugOpenCount){
		this. bugOpenCount = bugOpenCount;
	}

	public Integer getBugOpenCount(){
		return bugOpenCount;
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

	public void setBugLinkBug(String bugLinkBug){
		this. bugLinkBug = bugLinkBug;
	}

	public String getBugLinkBug(){
		return bugLinkBug;
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

	public void setCaseId(Integer caseId){
		this. caseId = caseId;
	}

	public Integer getCaseId(){
		return caseId;
	}

	public void setCaseVersion(Integer caseVersion){
		this. caseVersion = caseVersion;
	}

	public Integer getCaseVersion(){
		return caseVersion;
	}

	public void setBugSource(String bugSource){
		this. bugSource = bugSource;
	}

	public String getBugSource(){
		return bugSource;
	}

	public void setBugOrigin(String bugOrigin){
		this. bugOrigin = bugOrigin;
	}

	public String getBugOrigin(){
		return bugOrigin;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
