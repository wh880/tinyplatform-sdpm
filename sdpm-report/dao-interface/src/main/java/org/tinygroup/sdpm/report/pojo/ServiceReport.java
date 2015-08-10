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

package org.tinygroup.sdpm.report.pojo;

import java.util.Date;

public class ServiceReport {

	/** 服务报告ID */
	private Integer srId;

	/** 报告标题 */
	private String srTitle;

	/** 服务报告类型 */
	private Integer srType;

	/** 统计截止日期 */
	private Date srEndDate;

	/** 统计起始日期 */
	private Date srBeginDate;

	/** 报告制作人 */
	private String srCreateBy;

	/** 报告制作日期 */
	private Date srCreateDate;

	/** 公司ID */
	private Integer companyId;

	/** 部门ID */
	private Integer deptId;

	/** 产品线 */
	private String productlineName;

	/** 产品名称 */
	private String productName;

	/** 请求总数 */
	private Integer requestCount;

	/** 客户请求数 */
	private Integer requestCountClient;

	/** 及时回复请求数 */
	private Integer requestCountReplyIntime;

	/** 承诺修改请求数 */
	private Integer requestCountCommit;

	/** 到期请求数 */
	private Integer requestCountExpired;

	/** 实际发放请求数 */
	private Integer requestCountReleased;

	/** 承诺交付跨度 */
	private Integer requestCommitSpan;

	/** 实际交付跨度 */
	private Integer requestReleaseSpan;

	/** 属于缺陷数量 */
	private Integer requestCountBug;

	/** 新需求数 */
	private Integer requestCountNReq;

	/** 变更需求数 */
	private Integer requestCountCReq;

	/** 需要回访请求数 */
	private Integer requestCountNeedReview;

	/** 按时回访需求数 */
	private Integer requestCountReviewed;

	/** 回访通过请求数 */
	private Integer requestCountReviewedPass;

	/** 客户反馈请求数 */
	private Integer requestCountClientFeedBack;

	/** 累计请求数 */
	private Integer requestCountAddUp;

	/** 请求客户评价总分 */
	private Integer requestScoreTotal;

	/** 删除标记 */
	private Integer deleted;


	public void setSrId(Integer srId){
		this. srId = srId;
	}

	public Integer getSrId(){
		return srId;
	}

	public void setSrTitle(String srTitle){
		this. srTitle = srTitle;
	}

	public String getSrTitle(){
		return srTitle;
	}

	public void setSrType(Integer srType){
		this. srType = srType;
	}

	public Integer getSrType(){
		return srType;
	}

	public void setSrEndDate(Date srEndDate){
		this. srEndDate = srEndDate;
	}

	public Date getSrEndDate(){
		return srEndDate;
	}

	public void setSrBeginDate(Date srBeginDate){
		this. srBeginDate = srBeginDate;
	}

	public Date getSrBeginDate(){
		return srBeginDate;
	}

	public void setSrCreateBy(String srCreateBy){
		this. srCreateBy = srCreateBy;
	}

	public String getSrCreateBy(){
		return srCreateBy;
	}

	public void setSrCreateDate(Date srCreateDate){
		this. srCreateDate = srCreateDate;
	}

	public Date getSrCreateDate(){
		return srCreateDate;
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

	public void setProductlineName(String productlineName){
		this. productlineName = productlineName;
	}

	public String getProductlineName(){
		return productlineName;
	}

	public void setProductName(String productName){
		this. productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setRequestCount(Integer requestCount){
		this. requestCount = requestCount;
	}

	public Integer getRequestCount(){
		return requestCount;
	}

	public void setRequestCountClient(Integer requestCountClient){
		this. requestCountClient = requestCountClient;
	}

	public Integer getRequestCountClient(){
		return requestCountClient;
	}

	public void setRequestCountReplyIntime(Integer requestCountReplyIntime){
		this. requestCountReplyIntime = requestCountReplyIntime;
	}

	public Integer getRequestCountReplyIntime(){
		return requestCountReplyIntime;
	}

	public void setRequestCountCommit(Integer requestCountCommit){
		this. requestCountCommit = requestCountCommit;
	}

	public Integer getRequestCountCommit(){
		return requestCountCommit;
	}

	public void setRequestCountExpired(Integer requestCountExpired){
		this. requestCountExpired = requestCountExpired;
	}

	public Integer getRequestCountExpired(){
		return requestCountExpired;
	}

	public void setRequestCountReleased(Integer requestCountReleased){
		this. requestCountReleased = requestCountReleased;
	}

	public Integer getRequestCountReleased(){
		return requestCountReleased;
	}

	public void setRequestCommitSpan(Integer requestCommitSpan){
		this. requestCommitSpan = requestCommitSpan;
	}

	public Integer getRequestCommitSpan(){
		return requestCommitSpan;
	}

	public void setRequestReleaseSpan(Integer requestReleaseSpan){
		this. requestReleaseSpan = requestReleaseSpan;
	}

	public Integer getRequestReleaseSpan(){
		return requestReleaseSpan;
	}

	public void setRequestCountBug(Integer requestCountBug){
		this. requestCountBug = requestCountBug;
	}

	public Integer getRequestCountBug(){
		return requestCountBug;
	}

	public void setRequestCountNReq(Integer requestCountNReq){
		this. requestCountNReq = requestCountNReq;
	}

	public Integer getRequestCountNReq(){
		return requestCountNReq;
	}

	public void setRequestCountCReq(Integer requestCountCReq){
		this. requestCountCReq = requestCountCReq;
	}

	public Integer getRequestCountCReq(){
		return requestCountCReq;
	}

	public void setRequestCountNeedReview(Integer requestCountNeedReview){
		this. requestCountNeedReview = requestCountNeedReview;
	}

	public Integer getRequestCountNeedReview(){
		return requestCountNeedReview;
	}

	public void setRequestCountReviewed(Integer requestCountReviewed){
		this. requestCountReviewed = requestCountReviewed;
	}

	public Integer getRequestCountReviewed(){
		return requestCountReviewed;
	}

	public void setRequestCountReviewedPass(Integer requestCountReviewedPass){
		this. requestCountReviewedPass = requestCountReviewedPass;
	}

	public Integer getRequestCountReviewedPass(){
		return requestCountReviewedPass;
	}

	public void setRequestCountClientFeedBack(Integer requestCountClientFeedBack){
		this. requestCountClientFeedBack = requestCountClientFeedBack;
	}

	public Integer getRequestCountClientFeedBack(){
		return requestCountClientFeedBack;
	}

	public void setRequestCountAddUp(Integer requestCountAddUp){
		this. requestCountAddUp = requestCountAddUp;
	}

	public Integer getRequestCountAddUp(){
		return requestCountAddUp;
	}

	public void setRequestScoreTotal(Integer requestScoreTotal){
		this. requestScoreTotal = requestScoreTotal;
	}

	public Integer getRequestScoreTotal(){
		return requestScoreTotal;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
