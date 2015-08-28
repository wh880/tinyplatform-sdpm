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

public class Program {

	/** 项目集ID */
	private Integer programId;

	/** 项目组合ID */
	private Integer portfolioId;

	/** 公司ID */
	private Integer companyId;

	/** 部门ID */
	private Integer deptId;

	/** 父项目集ID */
	private Integer programParent;

	/** 项目集名称 */
	private String programName;

	/** 项目集代号 */
	private String programCode;

	/** 项目集序号 */
	private Integer programOrder;

	/** 项目集开始日期 */
	private Date programBegin;

	/** 项目集结束日期 */
	private Date programEnd;

	/** 项目集持续工作日 */
	private Integer programDays;

	/** 项目集状态 */
	private Integer programStatus;

	/** 项目集阶段 */
	private String programStage;

	/** 项目集优先级 */
	private Integer programPri;

	/** 项目集描述 */
	private String programDesc;

	/** 项目集目标 */
	private String programGoal;

	/** 项目集创建者 */
	private String programOpenedBy;

	/** 项目集创建日期 */
	private Integer programOpenedDate;

	/** 项目集关闭者 */
	private String programClosedBy;

	/** 项目集关闭日期 */
	private Integer programClosedDate;

	/** 项目集取消人 */
	private String programCanceledBy;

	/** 项目集取消日期 */
	private Integer programCanceledDate;

	/** 项目部经理 */
	private String programManager;

	/** 权限模式 */
	private Integer acl;

	/** 项目集白名单 */
	private String programWhitelist;

	/** 删除标记 */
	private Integer deleted;


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

	public void setProgramParent(Integer programParent){
		this. programParent = programParent;
	}

	public Integer getProgramParent(){
		return programParent;
	}

	public void setProgramName(String programName){
		this. programName = programName;
	}

	public String getProgramName(){
		return programName;
	}

	public void setProgramCode(String programCode){
		this. programCode = programCode;
	}

	public String getProgramCode(){
		return programCode;
	}

	public void setProgramOrder(Integer programOrder){
		this. programOrder = programOrder;
	}

	public Integer getProgramOrder(){
		return programOrder;
	}

	public void setProgramBegin(Date programBegin){
		this. programBegin = programBegin;
	}

	public Date getProgramBegin(){
		return programBegin;
	}

	public void setProgramEnd(Date programEnd){
		this. programEnd = programEnd;
	}

	public Date getProgramEnd(){
		return programEnd;
	}

	public void setProgramDays(Integer programDays){
		this. programDays = programDays;
	}

	public Integer getProgramDays(){
		return programDays;
	}

	public void setProgramStatus(Integer programStatus){
		this. programStatus = programStatus;
	}

	public Integer getProgramStatus(){
		return programStatus;
	}

	public void setProgramStage(String programStage){
		this. programStage = programStage;
	}

	public String getProgramStage(){
		return programStage;
	}

	public void setProgramPri(Integer programPri){
		this. programPri = programPri;
	}

	public Integer getProgramPri(){
		return programPri;
	}

	public void setProgramDesc(String programDesc){
		this. programDesc = programDesc;
	}

	public String getProgramDesc(){
		return programDesc;
	}

	public void setProgramGoal(String programGoal){
		this. programGoal = programGoal;
	}

	public String getProgramGoal(){
		return programGoal;
	}

	public void setProgramOpenedBy(String programOpenedBy){
		this. programOpenedBy = programOpenedBy;
	}

	public String getProgramOpenedBy(){
		return programOpenedBy;
	}

	public void setProgramOpenedDate(Integer programOpenedDate){
		this. programOpenedDate = programOpenedDate;
	}

	public Integer getProgramOpenedDate(){
		return programOpenedDate;
	}

	public void setProgramClosedBy(String programClosedBy){
		this. programClosedBy = programClosedBy;
	}

	public String getProgramClosedBy(){
		return programClosedBy;
	}

	public void setProgramClosedDate(Integer programClosedDate){
		this. programClosedDate = programClosedDate;
	}

	public Integer getProgramClosedDate(){
		return programClosedDate;
	}

	public void setProgramCanceledBy(String programCanceledBy){
		this. programCanceledBy = programCanceledBy;
	}

	public String getProgramCanceledBy(){
		return programCanceledBy;
	}

	public void setProgramCanceledDate(Integer programCanceledDate){
		this. programCanceledDate = programCanceledDate;
	}

	public Integer getProgramCanceledDate(){
		return programCanceledDate;
	}

	public void setProgramManager(String programManager){
		this. programManager = programManager;
	}

	public String getProgramManager(){
		return programManager;
	}

	public void setAcl(Integer acl){
		this. acl = acl;
	}

	public Integer getAcl(){
		return acl;
	}

	public void setProgramWhitelist(String programWhitelist){
		this. programWhitelist = programWhitelist;
	}

	public String getProgramWhitelist(){
		return programWhitelist;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
