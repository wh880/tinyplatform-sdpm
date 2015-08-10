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

import java.util.Date;

public class Project {

	/** 项目ID */
	private Integer projectId;

	/** 项目集ID */
	private Integer programId;

	/** 项目组合ID */
	private Integer portfolioId;

	/** 公司ID */
	private Integer companyId;

	/** 部门ID */
	private Integer deptId;

	/** 项目类型 */
	private Integer projectType;

	/** 父项目ID */
	private Integer projectParent;

	/** 项目名称 */
	private String projectName;

	/** 项目代号 */
	private String projectCode;

	/** 项目序号 */
	private Integer projectOrder;

	/** 项目开始日期 */
	private Date projectBegin;

	/** 项目结束日期 */
	private Date projectEnd;

	/** 可用工作日 */
	private Integer projectDays;

	/** 项目状态 */
	private String projectStatus;

	/** 项目阶段 */
	private String projectStage;

	/** 项目优先级 */
	private Integer projectPri;

	/** 项目描述 */
	private String projectDesc;

	/** 项目目标 */
	private String projectGoal;

	/** 项目创建人 */
	private String projectOpenedBy;

	/** 项目创建日期 */
	private Integer projectOpenedDate;

	/** 项目关闭人 */
	private String projectClosedBy;

	/** 项目关闭日期 */
	private Integer projectClosedDate;

	/** 项目取消人 */
	private String projectCanceledBy;

	/** 项目取消日期 */
	private Integer projectCanceledDate;

	/** 项目经理 */
	private String projectManager;

	/** 质量经理 */
	private String qualityManager;

	/** 交付经理 */
	private String deliveryManager;

	/** 项目组名 */
	private String projectTeam;

	/** 权限模式 */
	private Integer acl;

	/** PROJECT_WHITELIST */
	private String projectWhitelist;

	/** 删除标记 */
	private Integer deleted;


	public void setProjectId(Integer projectId){
		this. projectId = projectId;
	}

	public Integer getProjectId(){
		return projectId;
	}

	public void setProgramId(Integer programId){
		this. programId = programId;
	}

	public Integer getProgramId(){
		return programId;
	}

	public void setPortfolioId(Integer portfolioId){
		this. portfolioId = portfolioId;
	}

	public Integer getPortfolioId(){
		return portfolioId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setDeptId(Integer deptId){
		this. deptId = deptId;
	}

	public Integer getDeptId(){
		return deptId;
	}

	public void setProjectType(Integer projectType){
		this. projectType = projectType;
	}

	public Integer getProjectType(){
		return projectType;
	}

	public void setProjectParent(Integer projectParent){
		this. projectParent = projectParent;
	}

	public Integer getProjectParent(){
		return projectParent;
	}

	public void setProjectName(String projectName){
		this. projectName = projectName;
	}

	public String getProjectName(){
		return projectName;
	}

	public void setProjectCode(String projectCode){
		this. projectCode = projectCode;
	}

	public String getProjectCode(){
		return projectCode;
	}

	public void setProjectOrder(Integer projectOrder){
		this. projectOrder = projectOrder;
	}

	public Integer getProjectOrder(){
		return projectOrder;
	}

	public void setProjectBegin(Date projectBegin){
		this. projectBegin = projectBegin;
	}

	public Date getProjectBegin(){
		return projectBegin;
	}

	public void setProjectEnd(Date projectEnd){
		this. projectEnd = projectEnd;
	}

	public Date getProjectEnd(){
		return projectEnd;
	}

	public void setProjectDays(Integer projectDays){
		this. projectDays = projectDays;
	}

	public Integer getProjectDays(){
		return projectDays;
	}

	public void setProjectStatus(String projectStatus){
		this. projectStatus = projectStatus;
	}

	public String getProjectStatus(){
		return projectStatus;
	}

	public void setProjectStage(String projectStage){
		this. projectStage = projectStage;
	}

	public String getProjectStage(){
		return projectStage;
	}

	public void setProjectPri(Integer projectPri){
		this. projectPri = projectPri;
	}

	public Integer getProjectPri(){
		return projectPri;
	}

	public void setProjectDesc(String projectDesc){
		this. projectDesc = projectDesc;
	}

	public String getProjectDesc(){
		return projectDesc;
	}

	public void setProjectGoal(String projectGoal){
		this. projectGoal = projectGoal;
	}

	public String getProjectGoal(){
		return projectGoal;
	}

	public void setProjectOpenedBy(String projectOpenedBy){
		this. projectOpenedBy = projectOpenedBy;
	}

	public String getProjectOpenedBy(){
		return projectOpenedBy;
	}

	public void setProjectOpenedDate(Integer projectOpenedDate){
		this. projectOpenedDate = projectOpenedDate;
	}

	public Integer getProjectOpenedDate(){
		return projectOpenedDate;
	}

	public void setProjectClosedBy(String projectClosedBy){
		this. projectClosedBy = projectClosedBy;
	}

	public String getProjectClosedBy(){
		return projectClosedBy;
	}

	public void setProjectClosedDate(Integer projectClosedDate){
		this. projectClosedDate = projectClosedDate;
	}

	public Integer getProjectClosedDate(){
		return projectClosedDate;
	}

	public void setProjectCanceledBy(String projectCanceledBy){
		this. projectCanceledBy = projectCanceledBy;
	}

	public String getProjectCanceledBy(){
		return projectCanceledBy;
	}

	public void setProjectCanceledDate(Integer projectCanceledDate){
		this. projectCanceledDate = projectCanceledDate;
	}

	public Integer getProjectCanceledDate(){
		return projectCanceledDate;
	}

	public void setProjectManager(String projectManager){
		this. projectManager = projectManager;
	}

	public String getProjectManager(){
		return projectManager;
	}

	public void setQualityManager(String qualityManager){
		this. qualityManager = qualityManager;
	}

	public String getQualityManager(){
		return qualityManager;
	}

	public void setDeliveryManager(String deliveryManager){
		this. deliveryManager = deliveryManager;
	}

	public String getDeliveryManager(){
		return deliveryManager;
	}

	public void setProjectTeam(String projectTeam){
		this. projectTeam = projectTeam;
	}

	public String getProjectTeam(){
		return projectTeam;
	}

	public void setAcl(Integer acl){
		this. acl = acl;
	}

	public Integer getAcl(){
		return acl;
	}

	public void setProjectWhitelist(String projectWhitelist){
		this. projectWhitelist = projectWhitelist;
	}

	public String getProjectWhitelist(){
		return projectWhitelist;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
