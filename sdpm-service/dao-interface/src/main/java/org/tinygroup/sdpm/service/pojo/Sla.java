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

public class Sla {

	/** 服务协议ID */
	private Integer slaId;

	/** 公司ID */
	private Integer companyId;

	/** 服务级别 */
	private Integer serviceLevel;

	/** 服务期限 */
	private Date serviceDeadline;

	/** 客户ID */
	private Integer clientId;

	/** 产品ID */
	private Integer productId;

	/** 服务协议标题 */
	private String slaTitle;

	/** 服务协议表述 */
	private String slaSpec;

	/** 服务响应时限 */
	private Integer serviceReplyTimeLimit;

	/** 需求回访时限 */
	private Integer serviceReviewTimeLimit;

	/** 服务工时上限 */
	private Float serviceEffortLimit;

	/** 服务请求数上限 */
	private Integer serviceRequestLimit;

	/** 现场服务次数上限 */
	private Integer serviceTsOnsiteLimit;

	/** 服务专员 */
	private String serviceSpecialist;

	/** 服务级别协议 */
	private Integer slaStatus;

	/** 创建人 */
	private String slaCreatedBy;

	/** 创建时间 */
	private Date slaCreateDate;

	/** 批准人 */
	private String slaReviewedBy;

	/** 批准时间 */
	private Date slaReviewDate;

	/** 关闭人 */
	private String slaClosedBy;

	/** 关闭时间 */
	private Date slaCloseDate;

	/** 打开次数 */
	private Integer slaOpenCount;

	/** 删除标记 */
	private Integer deleted;


	public void setSlaId(Integer slaId){
		this. slaId = slaId;
	}

	public Integer getSlaId(){
		return slaId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setServiceLevel(Integer serviceLevel){
		this. serviceLevel = serviceLevel;
	}

	public Integer getServiceLevel(){
		return serviceLevel;
	}

	public void setServiceDeadline(Date serviceDeadline){
		this. serviceDeadline = serviceDeadline;
	}

	public Date getServiceDeadline(){
		return serviceDeadline;
	}

	public void setClientId(Integer clientId){
		this. clientId = clientId;
	}

	public Integer getClientId(){
		return clientId;
	}

	public void setProductId(Integer productId){
		this. productId = productId;
	}

	public Integer getProductId(){
		return productId;
	}

	public void setSlaTitle(String slaTitle){
		this. slaTitle = slaTitle;
	}

	public String getSlaTitle(){
		return slaTitle;
	}

	public void setSlaSpec(String slaSpec){
		this. slaSpec = slaSpec;
	}

	public String getSlaSpec(){
		return slaSpec;
	}

	public void setServiceReplyTimeLimit(Integer serviceReplyTimeLimit){
		this. serviceReplyTimeLimit = serviceReplyTimeLimit;
	}

	public Integer getServiceReplyTimeLimit(){
		return serviceReplyTimeLimit;
	}

	public void setServiceReviewTimeLimit(Integer serviceReviewTimeLimit){
		this. serviceReviewTimeLimit = serviceReviewTimeLimit;
	}

	public Integer getServiceReviewTimeLimit(){
		return serviceReviewTimeLimit;
	}

	public void setServiceEffortLimit(Float serviceEffortLimit){
		this. serviceEffortLimit = serviceEffortLimit;
	}

	public Float getServiceEffortLimit(){
		return serviceEffortLimit;
	}

	public void setServiceRequestLimit(Integer serviceRequestLimit){
		this. serviceRequestLimit = serviceRequestLimit;
	}

	public Integer getServiceRequestLimit(){
		return serviceRequestLimit;
	}

	public void setServiceTsOnsiteLimit(Integer serviceTsOnsiteLimit){
		this. serviceTsOnsiteLimit = serviceTsOnsiteLimit;
	}

	public Integer getServiceTsOnsiteLimit(){
		return serviceTsOnsiteLimit;
	}

	public void setServiceSpecialist(String serviceSpecialist){
		this. serviceSpecialist = serviceSpecialist;
	}

	public String getServiceSpecialist(){
		return serviceSpecialist;
	}

	public void setSlaStatus(Integer slaStatus){
		this. slaStatus = slaStatus;
	}

	public Integer getSlaStatus(){
		return slaStatus;
	}

	public void setSlaCreatedBy(String slaCreatedBy){
		this. slaCreatedBy = slaCreatedBy;
	}

	public String getSlaCreatedBy(){
		return slaCreatedBy;
	}

	public void setSlaCreateDate(Date slaCreateDate){
		this. slaCreateDate = slaCreateDate;
	}

	public Date getSlaCreateDate(){
		return slaCreateDate;
	}

	public void setSlaReviewedBy(String slaReviewedBy){
		this. slaReviewedBy = slaReviewedBy;
	}

	public String getSlaReviewedBy(){
		return slaReviewedBy;
	}

	public void setSlaReviewDate(Date slaReviewDate){
		this. slaReviewDate = slaReviewDate;
	}

	public Date getSlaReviewDate(){
		return slaReviewDate;
	}

	public void setSlaClosedBy(String slaClosedBy){
		this. slaClosedBy = slaClosedBy;
	}

	public String getSlaClosedBy(){
		return slaClosedBy;
	}

	public void setSlaCloseDate(Date slaCloseDate){
		this. slaCloseDate = slaCloseDate;
	}

	public Date getSlaCloseDate(){
		return slaCloseDate;
	}

	public void setSlaOpenCount(Integer slaOpenCount){
		this. slaOpenCount = slaOpenCount;
	}

	public Integer getSlaOpenCount(){
		return slaOpenCount;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
