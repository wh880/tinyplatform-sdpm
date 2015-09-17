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

package org.tinygroup.sdpm.service.dao.pojo;

import java.util.Date;

/** 
 * faq分类
 * 
 */
public class FaqType {

	/** 
	 * 问题类型id
	 * 
	 */
	private Integer faqTypeId;

	/** 
	 * 问答类型
	 * 
	 */
	private Integer faqType;

	/** 
	 * 父级问题类型id
	 * 
	 */
	private Integer faqParentTypeId;

	/** 
	 * faq类型创建时间
	 * 
	 */
	private Date faqTypeCreatDay;

	/** 
	 * 创建人
	 * 
	 */
	private String faqCreatedBy;

	/** 
	 * 已删除
	 * 
	 */
	private Integer deleted;


	public void setFaqTypeId(Integer faqTypeId){
		this. faqTypeId = faqTypeId;
	}

	public Integer getFaqTypeId(){
		return faqTypeId;
	}

	public void setFaqType(Integer faqType){
		this. faqType = faqType;
	}

	public Integer getFaqType(){
		return faqType;
	}

	public void setFaqParentTypeId(Integer faqParentTypeId){
		this. faqParentTypeId = faqParentTypeId;
	}

	public Integer getFaqParentTypeId(){
		return faqParentTypeId;
	}

	public void setFaqTypeCreatDay(Date faqTypeCreatDay){
		this. faqTypeCreatDay = faqTypeCreatDay;
	}

	public Date getFaqTypeCreatDay(){
		return faqTypeCreatDay;
	}

	public void setFaqCreatedBy(String faqCreatedBy){
		this. faqCreatedBy = faqCreatedBy;
	}

	public String getFaqCreatedBy(){
		return faqCreatedBy;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}