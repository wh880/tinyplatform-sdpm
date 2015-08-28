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

public class Risk {

	/** 风险ID */
	private Integer riskId;

	/** 公司ID */
	private Integer companyId;

	/** 项目ID */
	private Integer projectId;

	/** 风险识别人 */
	private Float riskOpenedBy;

	/** 风险识别日期 */
	private Date riskOpenDate;

	/** 风险标题 */
	private String riskTitle;

	/** 风险描述 */
	private String riskSpec;

	/** 风险分类 */
	private String riskCategory;

	/** 风险可能性 */
	private Float riskProbability;

	/** 风险影响 */
	private Float riskImpact;

	/** 风险值 */
	private Float riskExposure;

	/** 风险预期阶段 */
	private String riskExpPhase;

	/** 风险规避计划 */
	private String riskAvoidanceAP;

	/** 风险缓解计划 */
	private String riskMitigationAP;

	/** 风险承受计划 */
	private String riskContingencyAP;

	/** 风险计划结果 */
	private String riskActionResult;

	/** 风险责任人 */
	private String riskOwner;

	/** 风险状态 */
	private Integer riskStatus;

	/** 风险跟踪触发 */
	private Integer riskCheckTrigger;

	/** 风险最后编辑者 */
	private String riskLastEditedBy;

	/** 风险计划日期 */
	private Date riskPlanDate;

	/** 风险最后编辑日期 */
	private Date riskLastEditDate;

	/** 风险关闭者 */
	private String riskClosedBy;

	/** 风险关闭日期 */
	private Date riskCloseDate;

	/** 删除标记 */
	private Integer deleted;


	public void setRiskId(Integer riskId){
		this. riskId = riskId;
	}

	public Integer getRiskId(){
		return riskId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setProjectId(Integer projectId){
		this. projectId = projectId;
	}

	public Integer getProjectId(){
		return projectId;
	}

	public void setRiskOpenedBy(Float riskOpenedBy){
		this. riskOpenedBy = riskOpenedBy;
	}

	public Float getRiskOpenedBy(){
		return riskOpenedBy;
	}

	public void setRiskOpenDate(Date riskOpenDate){
		this. riskOpenDate = riskOpenDate;
	}

	public Date getRiskOpenDate(){
		return riskOpenDate;
	}

	public void setRiskTitle(String riskTitle){
		this. riskTitle = riskTitle;
	}

	public String getRiskTitle(){
		return riskTitle;
	}

	public void setRiskSpec(String riskSpec){
		this. riskSpec = riskSpec;
	}

	public String getRiskSpec(){
		return riskSpec;
	}

	public void setRiskCategory(String riskCategory){
		this. riskCategory = riskCategory;
	}

	public String getRiskCategory(){
		return riskCategory;
	}

	public void setRiskProbability(Float riskProbability){
		this. riskProbability = riskProbability;
	}

	public Float getRiskProbability(){
		return riskProbability;
	}

	public void setRiskImpact(Float riskImpact){
		this. riskImpact = riskImpact;
	}

	public Float getRiskImpact(){
		return riskImpact;
	}

	public void setRiskExposure(Float riskExposure){
		this. riskExposure = riskExposure;
	}

	public Float getRiskExposure(){
		return riskExposure;
	}

	public void setRiskExpPhase(String riskExpPhase){
		this. riskExpPhase = riskExpPhase;
	}

	public String getRiskExpPhase(){
		return riskExpPhase;
	}

	public void setRiskAvoidanceAP(String riskAvoidanceAP){
		this. riskAvoidanceAP = riskAvoidanceAP;
	}

	public String getRiskAvoidanceAP(){
		return riskAvoidanceAP;
	}

	public void setRiskMitigationAP(String riskMitigationAP){
		this. riskMitigationAP = riskMitigationAP;
	}

	public String getRiskMitigationAP(){
		return riskMitigationAP;
	}

	public void setRiskContingencyAP(String riskContingencyAP){
		this. riskContingencyAP = riskContingencyAP;
	}

	public String getRiskContingencyAP(){
		return riskContingencyAP;
	}

	public void setRiskActionResult(String riskActionResult){
		this. riskActionResult = riskActionResult;
	}

	public String getRiskActionResult(){
		return riskActionResult;
	}

	public void setRiskOwner(String riskOwner){
		this. riskOwner = riskOwner;
	}

	public String getRiskOwner(){
		return riskOwner;
	}

	public void setRiskStatus(Integer riskStatus){
		this. riskStatus = riskStatus;
	}

	public Integer getRiskStatus(){
		return riskStatus;
	}

	public void setRiskCheckTrigger(Integer riskCheckTrigger){
		this. riskCheckTrigger = riskCheckTrigger;
	}

	public Integer getRiskCheckTrigger(){
		return riskCheckTrigger;
	}

	public void setRiskLastEditedBy(String riskLastEditedBy){
		this. riskLastEditedBy = riskLastEditedBy;
	}

	public String getRiskLastEditedBy(){
		return riskLastEditedBy;
	}

	public void setRiskPlanDate(Date riskPlanDate){
		this. riskPlanDate = riskPlanDate;
	}

	public Date getRiskPlanDate(){
		return riskPlanDate;
	}

	public void setRiskLastEditDate(Date riskLastEditDate){
		this. riskLastEditDate = riskLastEditDate;
	}

	public Date getRiskLastEditDate(){
		return riskLastEditDate;
	}

	public void setRiskClosedBy(String riskClosedBy){
		this. riskClosedBy = riskClosedBy;
	}

	public String getRiskClosedBy(){
		return riskClosedBy;
	}

	public void setRiskCloseDate(Date riskCloseDate){
		this. riskCloseDate = riskCloseDate;
	}

	public Date getRiskCloseDate(){
		return riskCloseDate;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
