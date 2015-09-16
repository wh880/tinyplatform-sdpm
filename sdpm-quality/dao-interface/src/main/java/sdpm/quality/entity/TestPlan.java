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

package sdpm.quality.entity;

import java.util.Date;

public class TestPlan {

	/** 测试计划ID */
	private Integer testplanID;

	/** 任务名称 */
	private String testplanName;

	/** 所属产品 */
	private Integer productID;

	/** 所属项目 */
	private Integer projectID;

	/** 版本 */
	private String build;

	/** 负责人 */
	private String testplanOwner;

	/** 优先级 */
	private Integer priority;

	/** 开始日期 */
	private Date testplanBegin;

	/** 结束日期 */
	private Date testplanEnd;

	/** 描述 */
	private String testplanDesc;

	/** 测试总结 */
	private String testplanReport;

	/** 当前状态 */
	private String testplanStatus;

	/** 已删除 */
	private String deleted;


	public void setTestplanID(Integer testplanID){
		this. testplanID = testplanID;
	}

	public Integer getTestplanID(){
		return testplanID;
	}

	public void setTestplanName(String testplanName){
		this. testplanName = testplanName;
	}

	public String getTestplanName(){
		return testplanName;
	}

	public void setProductID(Integer productID){
		this. productID = productID;
	}

	public Integer getProductID(){
		return productID;
	}

	public void setProjectID(Integer projectID){
		this. projectID = projectID;
	}

	public Integer getProjectID(){
		return projectID;
	}

	public void setBuild(String build){
		this. build = build;
	}

	public String getBuild(){
		return build;
	}

	public void setTestplanOwner(String testplanOwner){
		this. testplanOwner = testplanOwner;
	}

	public String getTestplanOwner(){
		return testplanOwner;
	}

	public void setPriority(Integer priority){
		this. priority = priority;
	}

	public Integer getPriority(){
		return priority;
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

	public void setTestplanReport(String testplanReport){
		this. testplanReport = testplanReport;
	}

	public String getTestplanReport(){
		return testplanReport;
	}

	public void setTestplanStatus(String testplanStatus){
		this. testplanStatus = testplanStatus;
	}

	public String getTestplanStatus(){
		return testplanStatus;
	}

	public void setDeleted(String deleted){
		this. deleted = deleted;
	}

	public String getDeleted(){
		return deleted;
	}

}
