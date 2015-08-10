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


public class CaseStep {

	/** 用例步骤ID */
	private Integer caseStepId;

	/** 公司ID */
	private Integer companyId;

	/** 关联用例ID */
	private Integer caseId;

	/** 关联用例版本 */
	private Integer caseVersion;

	/** 用例步骤描述 */
	private String caseStepDesc;

	/** 用例步骤预期 */
	private String caseStepExpect;

	/** 删除标记 */
	private Integer deleted;


	public void setCaseStepId(Integer caseStepId){
		this. caseStepId = caseStepId;
	}

	public Integer getCaseStepId(){
		return caseStepId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
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

	public void setCaseStepDesc(String caseStepDesc){
		this. caseStepDesc = caseStepDesc;
	}

	public String getCaseStepDesc(){
		return caseStepDesc;
	}

	public void setCaseStepExpect(String caseStepExpect){
		this. caseStepExpect = caseStepExpect;
	}

	public String getCaseStepExpect(){
		return caseStepExpect;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
