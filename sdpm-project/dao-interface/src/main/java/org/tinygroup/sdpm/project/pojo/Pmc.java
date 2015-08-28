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

public class Pmc {

	/** 项目监控ID */
	private Integer pmcId;

	/** 公司ID */
	private Integer companyId;

	/** 项目ID */
	private Integer projectId;

	/** 统计日期 */
	private Date pmcDate;

	/** 剩余工作量 */
	private Float pmcLeft;

	/** 剩余任务数 */
	private Integer pmcTasknum;

	/** 消耗工作量 */
	private Float pmcAc;

	/** 计划值 */
	private Float pmcPv;

	/** 挣值 */
	private Float pmcEv;

	/** 成本偏差 */
	private Float pmcCpi;

	/** 进度偏差 */
	private Float pmcSpi;

	/** 删除标记 */
	private Integer deleted;


	public void setPmcId(Integer pmcId){
		this. pmcId = pmcId;
	}

	public Integer getPmcId(){
		return pmcId;
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

	public void setPmcDate(Date pmcDate){
		this. pmcDate = pmcDate;
	}

	public Date getPmcDate(){
		return pmcDate;
	}

	public void setPmcLeft(Float pmcLeft){
		this. pmcLeft = pmcLeft;
	}

	public Float getPmcLeft(){
		return pmcLeft;
	}

	public void setPmcTasknum(Integer pmcTasknum){
		this. pmcTasknum = pmcTasknum;
	}

	public Integer getPmcTasknum(){
		return pmcTasknum;
	}

	public void setPmcAc(Float pmcAc){
		this. pmcAc = pmcAc;
	}

	public Float getPmcAc(){
		return pmcAc;
	}

	public void setPmcPv(Float pmcPv){
		this. pmcPv = pmcPv;
	}

	public Float getPmcPv(){
		return pmcPv;
	}

	public void setPmcEv(Float pmcEv){
		this. pmcEv = pmcEv;
	}

	public Float getPmcEv(){
		return pmcEv;
	}

	public void setPmcCpi(Float pmcCpi){
		this. pmcCpi = pmcCpi;
	}

	public Float getPmcCpi(){
		return pmcCpi;
	}

	public void setPmcSpi(Float pmcSpi){
		this. pmcSpi = pmcSpi;
	}

	public Float getPmcSpi(){
		return pmcSpi;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
