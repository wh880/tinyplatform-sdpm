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
 * 服务级别协议表
 * 
 */
public class ServiceSla {

	public static Integer DELETE_YES=1;
	public static Integer DELETE_NO=0;
	/**
	 * 服务协议ID
	 *
	 */
	private Integer slaId;
	/**
	 * 服务级别
	 *
	 * 服务级别Service
	 * Level，保留字段，未今后分级服务提供信息；
	 * 0基础
	 * 1潜力
	 * 2核心
	 * 3vip
	 */
	private Integer serviceLevel;
	/*关联客户表的客户名称*/
	private  Integer  serviceName;
	/**
	 * 服务期限
	 *
	 * 服务有效期Service
	 * Deadline
	 */
	private Date serviceDeadline;
	/**
	 * 客户ID
	 *
	 */
	private Integer clientId;
	/**
	 * 产品ID
	 *
	 */
	private Integer productId;
	/**
	 * 服务协议标题
	 *
	 */
	private String slaTitle;
	/**
	 * 服务协议表述
	 *
	 */
	private String slaSpec;
	/**
	 * 服务响应时限
	 *
	 * 单位为小时（不含节假日）
	 */
	private Integer serviceReplyTimeLimit;
	/**
	 * 需求回访时限
	 *
	 * 单位工作日
	 */
	private Integer serviceReviewTimeLimit;
	/**
	 * 服务工时上限
	 *
	 */
	private Float serviceEffortLimit;
	/**
	 * 服务请求数上限
	 *
	 */
	private Integer serviceRequestLimit;
	/**
	 * 现场服务次数上限
	 *
	 */
	private Integer serviceTsOnsiteLimit;
	/**
	 * 服务专员
	 *
	 * 可以多人，间隔符英文,
	 */
	private String serviceSpecialist;
	/**
	 * 服务级别协议
	 *
	 * 0新建，1生效，2到期，3作废，4关闭
	 */
	private Integer slaStatus;
	/**
	 * 创建人
	 *
	 */
	private String slaCreatedBy;
	/**
	 * 创建时间
	 *
	 */
	private Date slaCreateDate;
	/**
	 * 批准人
	 *
	 */
	private String slaReviewedBy;
	/**
	 * 批准时间
	 *
	 */
	private Date slaReviewDate;
	/**
	 * 关闭人
	 *
	 */
	private String slaClosedBy;
	/**
	 * 关闭时间
	 *
	 */
	private Date slaCloseDate;
	/**
	 * 打开次数
	 *
	 * 默认0，审核通过后编辑再审核+1
	 */
	private Integer slaOpenCount;
	/**
	 * 已删除
	 *
	 */
	private Integer deleted;
	/**
	 * 产品版本
	 *
	 */
	private String cilentProductVision;

	public ServiceSla() {
		setDeleted(DELETE_NO);
	}

	public Integer getSlaId() {
		return slaId;
	}

	public void setSlaId(Integer slaId){
		this. slaId = slaId;
	}

	public Integer getServiceLevel() {
		return serviceLevel;
	}

	public void setServiceLevel(Integer serviceLevel){
		this. serviceLevel = serviceLevel;
	}

	public Date getServiceDeadline() {
		return serviceDeadline;
	}

	public void setServiceDeadline(Date serviceDeadline){
		this. serviceDeadline = serviceDeadline;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId){
		this. clientId = clientId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId){
		this. productId = productId;
	}

	public String getSlaTitle() {
		return slaTitle;
	}

	public void setSlaTitle(String slaTitle){
		this. slaTitle = slaTitle;
	}

	public String getSlaSpec() {
		return slaSpec;
	}

	public void setSlaSpec(String slaSpec){
		this. slaSpec = slaSpec;
	}

	public Integer getServiceReplyTimeLimit() {
		return serviceReplyTimeLimit;
	}

	public void setServiceReplyTimeLimit(Integer serviceReplyTimeLimit){
		this. serviceReplyTimeLimit = serviceReplyTimeLimit;
	}

	public Integer getServiceReviewTimeLimit() {
		return serviceReviewTimeLimit;
	}

	public void setServiceReviewTimeLimit(Integer serviceReviewTimeLimit){
		this. serviceReviewTimeLimit = serviceReviewTimeLimit;
	}

	public Float getServiceEffortLimit() {
		return serviceEffortLimit;
	}

	public void setServiceEffortLimit(Float serviceEffortLimit){
		this. serviceEffortLimit = serviceEffortLimit;
	}

	public Integer getServiceRequestLimit() {
		return serviceRequestLimit;
	}

	public void setServiceRequestLimit(Integer serviceRequestLimit){
		this. serviceRequestLimit = serviceRequestLimit;
	}

	public Integer getServiceTsOnsiteLimit() {
		return serviceTsOnsiteLimit;
	}

	public void setServiceTsOnsiteLimit(Integer serviceTsOnsiteLimit){
		this. serviceTsOnsiteLimit = serviceTsOnsiteLimit;
	}

	public String getServiceSpecialist() {
		return serviceSpecialist;
	}

	public void setServiceSpecialist(String serviceSpecialist){
		this. serviceSpecialist = serviceSpecialist;
	}

	public Integer getSlaStatus() {
		return slaStatus;
	}

	public void setSlaStatus(Integer slaStatus){
		this. slaStatus = slaStatus;
	}

	public String getSlaCreatedBy() {
		return slaCreatedBy;
	}

	public void setSlaCreatedBy(String slaCreatedBy){
		this. slaCreatedBy = slaCreatedBy;
	}

	public Date getSlaCreateDate() {
		return slaCreateDate;
	}

	public void setSlaCreateDate(Date slaCreateDate){
		this. slaCreateDate = slaCreateDate;
	}

	public String getSlaReviewedBy() {
		return slaReviewedBy;
	}

	public void setSlaReviewedBy(String slaReviewedBy){
		this. slaReviewedBy = slaReviewedBy;
	}

	public Date getSlaReviewDate() {
		return slaReviewDate;
	}

	public void setSlaReviewDate(Date slaReviewDate){
		this. slaReviewDate = slaReviewDate;
	}

	public String getSlaClosedBy() {
		return slaClosedBy;
	}

	public void setSlaClosedBy(String slaClosedBy){
		this. slaClosedBy = slaClosedBy;
	}

	public Date getSlaCloseDate() {
		return slaCloseDate;
	}

	public void setSlaCloseDate(Date slaCloseDate){
		this. slaCloseDate = slaCloseDate;
	}

	public Integer getSlaOpenCount() {
		return slaOpenCount;
	}

	public void setSlaOpenCount(Integer slaOpenCount){
		this. slaOpenCount = slaOpenCount;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public String getCilentProductVision() {
		return cilentProductVision;
	}

	public void setCilentProductVision(String cilentProductVision){
		this. cilentProductVision = cilentProductVision;
	}

}
