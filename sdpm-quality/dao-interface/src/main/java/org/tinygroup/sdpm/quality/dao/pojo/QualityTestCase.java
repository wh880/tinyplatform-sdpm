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
 * 测试用例表
 * 
 */
public class QualityTestCase implements Serializable {

	public static int DELETE_YES = 1;
	public static int DELETE_NO = 1;
		
	/**
	 * 创建人名称
	 */
	private String caseOpenedName;
	
	/**
	 * 执行人名称
	 */
	private String caseLastRunnerName;
	
	/** 
	 * 用例编号
	 * 
	 */
	private Integer caseId;

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
	 * CASE_PATH
	 * 
	 */
	private Integer casePath;

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
	 * 用例标题
	 * 
	 */
	private String caseTitle;

	/** 
	 * 前置条件
	 * 
	 */
	private String casePrecondition;

	/** 
	 * 关键词
	 * 
	 */
	private String caseKeywords;

	/** 
	 * 优先级
	 * 
	 */
	private Integer priority;

	/** 
	 * 用例类型
	 * 
	 */
	private String caseType;

	/** 
	 * 适用阶段
	 * 
	 */
	private String caseStage;

	/** 
	 * 执行方式
	 * 
	 */
	private String caseRunway;

	/** 
	 * 由谁编写
	 * 
	 */
	private String caseScriptedBy;

	/** 
	 * 编写日期
	 * 
	 */
	private Date caseScriptedDate;

	/** 
	 * SCRIPTSTATUS
	 * 
	 */
	private String scriptStatus;

	/** 
	 * SCRIPTLOCATION
	 * 
	 */
	private String scriptLocation;

	/** 
	 * 用例状态
	 * 
	 */
	private String caseStatus;

	/** 
	 * 执行频率
	 * 
	 */
	private String caseFrequency;

	/** 
	 * 排序
	 * 
	 */
	private Integer caseOrder;

	/** 
	 * 由谁创建
	 * 
	 */
	private String caseOpenedBy;

	/** 
	 * 创建日期
	 * 
	 */
	private Date caseOpenedDate;

	/** 
	 * 最后修改者
	 * 
	 */
	private String caseLastEditedBy;

	/** 
	 * 最后修改日期
	 * 
	 */
	private Date caseLastEditedDate;

	/** 
	 * 关联用例版本
	 * 
	 */
	private Integer caseVersion;

	/** 
	 * 相关用例
	 * 
	 */
	private Integer linkCase;

	/** 
	 * 来源Bug
	 * 
	 */
	private Integer caseFromBug;

	/** 
	 * 已删除
	 * 
	 */
	private Integer deleted;

	/** 
	 * 最后执行人
	 * 
	 */
	private String caseLastRunner;

	/** 
	 * 最后执行时间
	 * 
	 */
	private Date caseLastRunDate;

	/** 
	 * 用例执行结果
	 * 
	 */
	private String caseLastRunResult;


	public void setCaseId(Integer caseId){
		this. caseId = caseId;
	}

	public Integer getCaseId(){
		return caseId;
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

	public void setCasePath(Integer casePath){
		this. casePath = casePath;
	}

	public Integer getCasePath(){
		return casePath;
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

	public void setCaseTitle(String caseTitle){
		this. caseTitle = caseTitle;
	}

	public String getCaseTitle(){
		return caseTitle;
	}

	public void setCasePrecondition(String casePrecondition){
		this. casePrecondition = casePrecondition;
	}

	public String getCasePrecondition(){
		return casePrecondition;
	}

	public void setCaseKeywords(String caseKeywords){
		this. caseKeywords = caseKeywords;
	}

	public String getCaseKeywords(){
		return caseKeywords;
	}

	public void setPriority(Integer priority){
		this. priority = priority;
	}

	public Integer getPriority(){
		return priority;
	}

	public void setCaseType(String caseType){
		this. caseType = caseType;
	}

	public String getCaseType(){
		return caseType;
	}

	public void setCaseStage(String caseStage){
		this. caseStage = caseStage;
	}

	public String getCaseStage(){
		return caseStage;
	}

	public void setCaseRunway(String caseRunway){
		this. caseRunway = caseRunway;
	}

	public String getCaseRunway(){
		return caseRunway;
	}

	public void setCaseScriptedBy(String caseScriptedBy){
		this. caseScriptedBy = caseScriptedBy;
	}

	public String getCaseScriptedBy(){
		return caseScriptedBy;
	}

	public void setCaseScriptedDate(Date caseScriptedDate){
		this. caseScriptedDate = caseScriptedDate;
	}

	public Date getCaseScriptedDate(){
		return caseScriptedDate;
	}

	public void setScriptStatus(String scriptStatus){
		this. scriptStatus = scriptStatus;
	}

	public String getScriptStatus(){
		return scriptStatus;
	}

	public void setScriptLocation(String scriptLocation){
		this. scriptLocation = scriptLocation;
	}

	public String getScriptLocation(){
		return scriptLocation;
	}

	public void setCaseStatus(String caseStatus){
		this. caseStatus = caseStatus;
	}

	public String getCaseStatus(){
		return caseStatus;
	}

	public void setCaseFrequency(String caseFrequency){
		this. caseFrequency = caseFrequency;
	}

	public String getCaseFrequency(){
		return caseFrequency;
	}

	public void setCaseOrder(Integer caseOrder){
		this. caseOrder = caseOrder;
	}

	public Integer getCaseOrder(){
		return caseOrder;
	}

	public void setCaseOpenedBy(String caseOpenedBy){
		this. caseOpenedBy = caseOpenedBy;
	}

	public String getCaseOpenedBy(){
		return caseOpenedBy;
	}

	public void setCaseOpenedDate(Date caseOpenedDate){
		this. caseOpenedDate = caseOpenedDate;
	}

	public Date getCaseOpenedDate(){
		return caseOpenedDate;
	}

	public void setCaseLastEditedBy(String caseLastEditedBy){
		this. caseLastEditedBy = caseLastEditedBy;
	}

	public String getCaseLastEditedBy(){
		return caseLastEditedBy;
	}

	public void setCaseLastEditedDate(Date caseLastEditedDate){
		this. caseLastEditedDate = caseLastEditedDate;
	}

	public Date getCaseLastEditedDate(){
		return caseLastEditedDate;
	}

	public void setCaseVersion(Integer caseVersion){
		this. caseVersion = caseVersion;
	}

	public Integer getCaseVersion(){
		return caseVersion;
	}

	public void setLinkCase(Integer linkCase){
		this. linkCase = linkCase;
	}

	public Integer getLinkCase(){
		return linkCase;
	}

	public void setCaseFromBug(Integer caseFromBug){
		this. caseFromBug = caseFromBug;
	}

	public Integer getCaseFromBug(){
		return caseFromBug;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

	public void setCaseLastRunner(String caseLastRunner){
		this. caseLastRunner = caseLastRunner;
	}

	public String getCaseLastRunner(){
		return caseLastRunner;
	}

	public void setCaseLastRunDate(Date caseLastRunDate){
		this. caseLastRunDate = caseLastRunDate;
	}

	public Date getCaseLastRunDate(){
		return caseLastRunDate;
	}

	public void setCaseLastRunResult(String caseLastRunResult){
		this. caseLastRunResult = caseLastRunResult;
	}

	public String getCaseLastRunResult(){
		return caseLastRunResult;
	}

	public String getCaseOpenedName() {
		return caseOpenedName;
	}

	public void setCaseOpenedName(String caseOpenedName) {
		this.caseOpenedName = caseOpenedName;
	}

	public String getCaseLastRunnerName() {
		return caseLastRunnerName;
	}

	public void setCaseLastRunnerName(String caseLastRunnerName) {
		this.caseLastRunnerName = caseLastRunnerName;
	}

}
