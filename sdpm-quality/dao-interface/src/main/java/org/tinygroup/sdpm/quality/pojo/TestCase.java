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

public class TestCase {

	/** 关联用例ID */
	private Integer caseId;

	/** 公司ID */
	private Integer companyId;

	/** 产品ID */
	private Integer productId;

	/** 需求ID */
	private Integer storyId;

	/** 需求版本 */
	private Integer storyVersion;

	/** 模块ID */
	private Integer moduleId;

	/** 用例标题 */
	private String caseTitle;

	/** 前置条件 */
	private String casePrecondition;

	/** 关键字 */
	private String caseKeywords;

	/** 优先级 */
	private Integer casePri;

	/** 用例类型 */
	private String caseType;

	/** 使用阶段 */
	private String caseStage;

	/** 用例状态 */
	private Integer caseStatus;

	/** 执行次数 */
	private Integer caseRun;

	/** 用例序号 */
	private Integer caseOrder;

	/** 关联用例版本 */
	private Integer caseVersion;

	/** 关联用例ID */
	private String linkcaseId;

	/** 建自缺陷ID */
	private Integer fromBugId;

	/** 用例创建人 */
	private String caseOpenedBy;

	/** 用例创建日期 */
	private Date caseOpenedDate;

	/** 用例最后编辑者 */
	private String caseLastEditedBy;

	/** 用例最后编辑日期 */
	private Date caseLastEditedDate;

	/** 用例最后执行者 */
	private String caseLastRunner;

	/** 用例最后执行日期 */
	private Date caseLastRunDate;

	/** 用例最后执行结果 */
	private Integer caseLastRunResult;

	/** 删除标记 */
	private Integer deleted;


	public void setCaseId(Integer caseId){
		this. caseId = caseId;
	}

	public Integer getCaseId(){
		return caseId;
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

	public void setModuleId(Integer moduleId){
		this. moduleId = moduleId;
	}

	public Integer getModuleId(){
		return moduleId;
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

	public void setCasePri(Integer casePri){
		this. casePri = casePri;
	}

	public Integer getCasePri(){
		return casePri;
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

	public void setCaseStatus(Integer caseStatus){
		this. caseStatus = caseStatus;
	}

	public Integer getCaseStatus(){
		return caseStatus;
	}

	public void setCaseRun(Integer caseRun){
		this. caseRun = caseRun;
	}

	public Integer getCaseRun(){
		return caseRun;
	}

	public void setCaseOrder(Integer caseOrder){
		this. caseOrder = caseOrder;
	}

	public Integer getCaseOrder(){
		return caseOrder;
	}

	public void setCaseVersion(Integer caseVersion){
		this. caseVersion = caseVersion;
	}

	public Integer getCaseVersion(){
		return caseVersion;
	}

	public void setLinkcaseId(String linkcaseId){
		this. linkcaseId = linkcaseId;
	}

	public String getLinkcaseId(){
		return linkcaseId;
	}

	public void setFromBugId(Integer fromBugId){
		this. fromBugId = fromBugId;
	}

	public Integer getFromBugId(){
		return fromBugId;
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

	public void setCaseLastRunResult(Integer caseLastRunResult){
		this. caseLastRunResult = caseLastRunResult;
	}

	public Integer getCaseLastRunResult(){
		return caseLastRunResult;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
