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

package org.tinygroup.sdpm.service.pojo;

import java.util.Date;

public class Faq {

	/** 问答ID */
	private Integer faqId;

	/** 公司ID */
	private Integer companyId;

	/** 问答类型 */
	private Integer faqType;

	/** 问题描述 */
	private String faqQuestion;

	/** 解答 */
	private String faqAnswer;

	/** 产品ID */
	private Integer productId;

	/** 关键字 */
	private String faqKeywords;

	/** FAQ来源 */
	private String faqSource;

	/** 来源ID */
	private Integer faqSourceId;

	/** 创建人 */
	private String faqCreatedBy;

	/** 创建时间 */
	private Date faqCreateDate;

	/** 回复者 */
	private String faqRepliedBy;

	/** FAQ_REPLY_DATE */
	private Date faqReplyDate;

	/** 删除标记 */
	private Integer deleted;


	public void setFaqId(Integer faqId){
		this. faqId = faqId;
	}

	public Integer getFaqId(){
		return faqId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setFaqType(Integer faqType){
		this. faqType = faqType;
	}

	public Integer getFaqType(){
		return faqType;
	}

	public void setFaqQuestion(String faqQuestion){
		this. faqQuestion = faqQuestion;
	}

	public String getFaqQuestion(){
		return faqQuestion;
	}

	public void setFaqAnswer(String faqAnswer){
		this. faqAnswer = faqAnswer;
	}

	public String getFaqAnswer(){
		return faqAnswer;
	}

	public void setProductId(Integer productId){
		this. productId = productId;
	}

	public Integer getProductId(){
		return productId;
	}

	public void setFaqKeywords(String faqKeywords){
		this. faqKeywords = faqKeywords;
	}

	public String getFaqKeywords(){
		return faqKeywords;
	}

	public void setFaqSource(String faqSource){
		this. faqSource = faqSource;
	}

	public String getFaqSource(){
		return faqSource;
	}

	public void setFaqSourceId(Integer faqSourceId){
		this. faqSourceId = faqSourceId;
	}

	public Integer getFaqSourceId(){
		return faqSourceId;
	}

	public void setFaqCreatedBy(String faqCreatedBy){
		this. faqCreatedBy = faqCreatedBy;
	}

	public String getFaqCreatedBy(){
		return faqCreatedBy;
	}

	public void setFaqCreateDate(Date faqCreateDate){
		this. faqCreateDate = faqCreateDate;
	}

	public Date getFaqCreateDate(){
		return faqCreateDate;
	}

	public void setFaqRepliedBy(String faqRepliedBy){
		this. faqRepliedBy = faqRepliedBy;
	}

	public String getFaqRepliedBy(){
		return faqRepliedBy;
	}

	public void setFaqReplyDate(Date faqReplyDate){
		this. faqReplyDate = faqReplyDate;
	}

	public Date getFaqReplyDate(){
		return faqReplyDate;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
