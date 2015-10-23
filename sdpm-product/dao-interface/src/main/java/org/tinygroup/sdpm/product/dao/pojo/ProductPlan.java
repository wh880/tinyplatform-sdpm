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

package org.tinygroup.sdpm.product.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/** 
 * 计划表
 * 
 */
public class ProductPlan implements Serializable{
	

	/** 
	 * 计划ID
	 * 
	 */
	private Integer planId;

	/** 
	 * 公司ID
	 * 
	 */
	private Integer companyId;

	/** 
	 * 产品ID
	 * 
	 */
	private Integer productId;

	/** 
	 * 计划名称
	 * 
	 */
	private String planName;

	/** 
	 * 计划描述
	 * 
	 */
	private String planSpec;

	/** 
	 * 计划开始时间
	 * 
	 */
	private Date planBeginDate;

	/** 
	 * 计划结束时间
	 * 
	 */
	private Date planEndDate;

	/** 
	 * 已删除
	 * 
	 */
	private Integer deleted;
	/**
	 * 草稿
	 */
	private Integer draft;
	/**
	 * 激活
	 */
	private Integer active;
	/**
	 * 更新
	 */
	private Integer change;
	/**
	 * 已关闭
	 */
	private Integer close;

	public Integer getDraft() {
		return draft;
	}

	public void setDraft(Integer draft) {
		this.draft = draft;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public Integer getChange() {
		return change;
	}

	public void setChange(Integer change) {
		this.change = change;
	}

	public Integer getClose() {
		return close;
	}

	public void setClose(Integer close) {
		this.close = close;
	}

	public void setPlanId(Integer planId){
		this. planId = planId;
	}

	public Integer getPlanId(){
		return planId;
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

	public void setPlanName(String planName){
		this. planName = planName;
	}

	public String getPlanName(){
		return planName;
	}

	public void setPlanSpec(String planSpec){
		this. planSpec = planSpec;
	}

	public String getPlanSpec(){
		return planSpec;
	}

	public void setPlanBeginDate(Date planBeginDate){
		this. planBeginDate = planBeginDate;
	}

	public Date getPlanBeginDate(){
		return planBeginDate;
	}

	public void setPlanEndDate(Date planEndDate){
		this. planEndDate = planEndDate;
	}

	public Date getPlanEndDate(){
		return planEndDate;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
