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

import java.io.Serializable;
import java.util.Date;

/** 
 * 项目
 * 
 */
public class Project implements Serializable{

	public static String DELETE_YES = "1";
	public static String DELETE_NO = "0";

	/**
	 * 项目下任务完成进度
	 */
	private float percent;
	/**
	 * 项目id
	 *
	 */
	private Integer projectId;
	/**
	 * 是否作为目录
	 * <p/>
	 * 0-false,1-true
	 */
	private String projectIsCat;
	/**
	 * 目录id
	 */
	private Integer projectCatId;
	/**
	 * 项目类型
	 *
	 * 0-长期项目，1-短期项目，2-运维项目
	 */
	private String projectType;
	/**
	 * 项目名称
	 */
	private String projectName;
	/**
	 * 项目代号
	 */
	private String projectCode;
	/**
	 * 项目开始日期
	 */
	private Date projectBegin;
	/**
	 * 项目结束日期
	 */
	private Date projectEnd;
	/**
	 * 可用工作日
	 */
	private Integer projectDays;
	/**
	 * 项目状态
	 */
	private String projectStatus;
	/**
	 * 项目所处阶段
	 *
	 * 0-未开始，1-进行中，2-已挂起，3-已完成
	 */
	private String projectStatge;
	/**
	 * 优先级
	 *
	 * 1，2，3，4
	 * 递增
	 */
	private String projectPri;
	/**
	 * 项目描述
	 */
	private String projectDesc;
	/**
	 * 由谁创建
	 */
	private String projectOpenedBy;
	/**
	 * 创建日期
	 */
	private Date projectOpenedDate;
	/**
	 * 项目创建版本
	 */
	private String projectOpenedVersion;
	/**
	 * 项目由谁关闭
	 */
	private String projectCloseBy;
	/**
	 * 项目关闭日期
	 */
	private Date projectCloseDate;
	/**
	 * 项目由谁取消
	 */
	private String projectCanceledBy;
	/**
	 * 项目取消日期
	 */
	private Date projectCanceledDate;
	/**
	 * 产品负责人
	 *
	 */
	private String projectPO;
	/**
	 * 项目负责人
	 *
	 */
	private String projectPM;
	/**
	 * 测试负责人
	 *
	 */
	private String projectQD;
	/**
	 * 项目发布负责人
	 *
	 */
	private String projectRD;
	/**
	 * 团队成员
	 */
	private String projectTeam;
	/**
	 * 访问控制
	 *
	 * 0-open,1-private,2-custom
	 */
	private String projectAcl;
	/**
	 * 分组白名单
	 *
	 */
	private String projectWhiteList;
	/**
	 * 项目排序
	 *
	 */
	private Integer projectOrder;
	/**
	 * 已删除
	 *
	 * 0-未删除，1-删除
	 */
	private String projectDeleted;

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId){
		this. projectId = projectId;
	}

	public String getProjectIsCat() {
		return projectIsCat;
	}

	public void setProjectIsCat(String projectIsCat){
		this. projectIsCat = projectIsCat;
	}

	public Integer getProjectCatId() {
		return projectCatId;
	}

	public void setProjectCatId(Integer projectCatId){
		this. projectCatId = projectCatId;
	}

	public String getProjectType() {
		return projectType;
	}

	public void setProjectType(String projectType){
		this. projectType = projectType;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName){
		this. projectName = projectName;
	}

	public String getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(String projectCode){
		this. projectCode = projectCode;
	}

	public Date getProjectBegin() {
		return projectBegin;
	}

	public void setProjectBegin(Date projectBegin){
		this. projectBegin = projectBegin;
	}

	public Date getProjectEnd() {
		return projectEnd;
	}

	public void setProjectEnd(Date projectEnd){
		this. projectEnd = projectEnd;
	}

	public Integer getProjectDays() {
		return projectDays;
	}

	public void setProjectDays(Integer projectDays){
		this. projectDays = projectDays;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus){
		this. projectStatus = projectStatus;
	}

	public String getProjectStatge() {
		return projectStatge;
	}

	public void setProjectStatge(String projectStatge){
		this. projectStatge = projectStatge;
	}

	public String getProjectPri() {
		return projectPri;
	}

	public void setProjectPri(String projectPri){
		this. projectPri = projectPri;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc){
		this. projectDesc = projectDesc;
	}

	public String getProjectOpenedBy() {
		return projectOpenedBy;
	}

	public void setProjectOpenedBy(String projectOpenedBy){
		this. projectOpenedBy = projectOpenedBy;
	}

	public Date getProjectOpenedDate() {
		return projectOpenedDate;
	}

	public void setProjectOpenedDate(Date projectOpenedDate){
		this. projectOpenedDate = projectOpenedDate;
	}

	public String getProjectOpenedVersion() {
		return projectOpenedVersion;
	}

	public void setProjectOpenedVersion(String projectOpenedVersion){
		this. projectOpenedVersion = projectOpenedVersion;
	}

	public String getProjectCloseBy() {
		return projectCloseBy;
	}

	public void setProjectCloseBy(String projectCloseBy){
		this. projectCloseBy = projectCloseBy;
	}

	public Date getProjectCloseDate() {
		return projectCloseDate;
	}

	public void setProjectCloseDate(Date projectCloseDate){
		this. projectCloseDate = projectCloseDate;
	}

	public String getProjectCanceledBy() {
		return projectCanceledBy;
	}

	public void setProjectCanceledBy(String projectCanceledBy){
		this. projectCanceledBy = projectCanceledBy;
	}

	public Date getProjectCanceledDate() {
		return projectCanceledDate;
	}

	public void setProjectCanceledDate(Date projectCanceledDate){
		this. projectCanceledDate = projectCanceledDate;
	}

	public String getProjectPO() {
		return projectPO;
	}

	public void setProjectPO(String projectPO){
		this. projectPO = projectPO;
	}

	public String getProjectPM() {
		return projectPM;
	}

	public void setProjectPM(String projectPM){
		this. projectPM = projectPM;
	}

	public String getProjectQD() {
		return projectQD;
	}

	public void setProjectQD(String projectQD){
		this. projectQD = projectQD;
	}

	public String getProjectRD() {
		return projectRD;
	}

	public void setProjectRD(String projectRD){
		this. projectRD = projectRD;
	}

	public String getProjectTeam() {
		return projectTeam;
	}

	public void setProjectTeam(String projectTeam){
		this. projectTeam = projectTeam;
	}

	public String getProjectAcl() {
		return projectAcl;
	}

	public void setProjectAcl(String projectAcl){
		this. projectAcl = projectAcl;
	}

	public String getProjectWhiteList() {
		return projectWhiteList;
	}

	public void setProjectWhiteList(String projectWhiteList){
		this. projectWhiteList = projectWhiteList;
	}

	public Integer getProjectOrder() {
		return projectOrder;
	}

	public void setProjectOrder(Integer projectOrder){
		this. projectOrder = projectOrder;
	}

	public String getProjectDeleted() {
		return projectDeleted;
	}

	public void setProjectDeleted(String projectDeleted){
		this. projectDeleted = projectDeleted;
	}

}
