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

public class TestPlan {

	/** 测试计划ID */
	private Integer testplanId;

	/** 公司ID */
	private Integer companyId;

	/** 测试计划名称 */
	private String testplanName;

	/** 产品ID */
	private Integer productId;

	/** 项目ID */
	private Integer projectId;

	/** 集成版本ID */
	private Integer buildId;

	/** 负责人 */
	private String testplanOwner;

	/** 测试计划开始日期 */
	private Date testplanBegin;

	/** 测试计划结束日期 */
	private Date testplanEnd;

	/** 测试计划描述 */
	private String testplanDesc;

	/** 测试计划状态 */
	private String testplanStatus;

	/** 删除标记 */
	private Integer deleted;


	public void setTestplanId(Integer testplanId){
		this. testplanId = testplanId;
	}

	public Integer getTestplanId(){
		return testplanId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setTestplanName(String testplanName){
		this. testplanName = testplanName;
	}

	public String getTestplanName(){
		return testplanName;
	}

	public void setProductId(Integer productId){
		this. productId = productId;
	}

	public Integer getProductId(){
		return productId;
	}

	public void setProjectId(Integer projectId){
		this. projectId = projectId;
	}

	public Integer getProjectId(){
		return projectId;
	}

	public void setBuildId(Integer buildId){
		this. buildId = buildId;
	}

	public Integer getBuildId(){
		return buildId;
	}

	public void setTestplanOwner(String testplanOwner){
		this. testplanOwner = testplanOwner;
	}

	public String getTestplanOwner(){
		return testplanOwner;
	}

	public void setTestplanBegin(Date testplanBegin){
		this. testplanBegin = testplanBegin;
	}

	public Date getTestplanBegin(){
		return testplanBegin;
	}

	public void setTestplanEnd(Date testplanEnd){
		this. testplanEnd = testplanEnd;
	}

	public Date getTestplanEnd(){
		return testplanEnd;
	}

	public void setTestplanDesc(String testplanDesc){
		this. testplanDesc = testplanDesc;
	}

	public String getTestplanDesc(){
		return testplanDesc;
	}

	public void setTestplanStatus(String testplanStatus){
		this. testplanStatus = testplanStatus;
	}

	public String getTestplanStatus(){
		return testplanStatus;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
