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

import java.math.BigInteger;
import java.util.Date;

public class Request {

	/** 客户请求ID */
	private Integer requestId;

	/** 公司ID */
	private Integer companyId;

	/** 产品ID */
	private Integer productId;

	/** 模块ID */
	private Integer moduleId;

	/** 请求逻辑编号 */
	private String requestNO;

	/** 请求类型 */
	private Integer requestType;

	/** 请求优先级 */
	private Integer requestPre;

	/** 请求标题 */
	private String requestTitle;

	/** 关键字 */
	private String requestKeywords;

	/** 请求描述 */
	private String requestSpec;

	/** 异常标记 */
	private Integer requestIsAbnormal;

	/** 客户ID */
	private Integer clientId;

	/** 联系人 */
	private String requester;

	/** 服务事件提交人 */
	private String requestSubmitBy;

	/** 请求提交时间 */
	private Date requestSubmitDate;

	/** 请求回复时间 */
	private Date requestReplyDate;

	/** 请求承诺完成日期 */
	private Date requestCommitmentDate;

	/** 回访人 */
	private String requestReviewer;

	/** 回访日期 */
	private Date requestReviewDate;

	/** 请求最后编辑者 */
	private String requestLastEditedBy;

	/** 请求最后编辑时间 */
	private Date requestLastEditDate;

	/** 需求发布ID */
	private Integer releaseId;

	/** 请求完成日期 */
	private BigInteger requestReleaseDate;

	/** 关闭人 */
	private String requestClosedBy;

	/** 关闭时间 */
	private Date requestCloseDate;

	/** 请求打开次数 */
	private Integer requestOpenCount;

	/** 请求状态 */
	private Integer requestStatus;

	/** 转换为 */
	private Integer requestTransTo;

	/** 转出对象ID */
	private Integer requestTransId;

	/** 删除标记 */
	private Integer deleted;


	public void setRequestId(Integer requestId){
		this. requestId = requestId;
	}

	public Integer getRequestId(){
		return requestId;
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

	public void setModuleId(Integer moduleId){
		this. moduleId = moduleId;
	}

	public Integer getModuleId(){
		return moduleId;
	}

	public void setRequestNO(String requestNO){
		this. requestNO = requestNO;
	}

	public String getRequestNO(){
		return requestNO;
	}

	public void setRequestType(Integer requestType){
		this. requestType = requestType;
	}

	public Integer getRequestType(){
		return requestType;
	}

	public void setRequestPre(Integer requestPre){
		this. requestPre = requestPre;
	}

	public Integer getRequestPre(){
		return requestPre;
	}

	public void setRequestTitle(String requestTitle){
		this. requestTitle = requestTitle;
	}

	public String getRequestTitle(){
		return requestTitle;
	}

	public void setRequestKeywords(String requestKeywords){
		this. requestKeywords = requestKeywords;
	}

	public String getRequestKeywords(){
		return requestKeywords;
	}

	public void setRequestSpec(String requestSpec){
		this. requestSpec = requestSpec;
	}

	public String getRequestSpec(){
		return requestSpec;
	}

	public void setRequestIsAbnormal(Integer requestIsAbnormal){
		this. requestIsAbnormal = requestIsAbnormal;
	}

	public Integer getRequestIsAbnormal(){
		return requestIsAbnormal;
	}

	public void setClientId(Integer clientId){
		this. clientId = clientId;
	}

	public Integer getClientId(){
		return clientId;
	}

	public void setRequester(String requester){
		this. requester = requester;
	}

	public String getRequester(){
		return requester;
	}

	public void setRequestSubmitBy(String requestSubmitBy){
		this. requestSubmitBy = requestSubmitBy;
	}

	public String getRequestSubmitBy(){
		return requestSubmitBy;
	}

	public void setRequestSubmitDate(Date requestSubmitDate){
		this. requestSubmitDate = requestSubmitDate;
	}

	public Date getRequestSubmitDate(){
		return requestSubmitDate;
	}

	public void setRequestReplyDate(Date requestReplyDate){
		this. requestReplyDate = requestReplyDate;
	}

	public Date getRequestReplyDate(){
		return requestReplyDate;
	}

	public void setRequestCommitmentDate(Date requestCommitmentDate){
		this. requestCommitmentDate = requestCommitmentDate;
	}

	public Date getRequestCommitmentDate(){
		return requestCommitmentDate;
	}

	public void setRequestReviewer(String requestReviewer){
		this. requestReviewer = requestReviewer;
	}

	public String getRequestReviewer(){
		return requestReviewer;
	}

	public void setRequestReviewDate(Date requestReviewDate){
		this. requestReviewDate = requestReviewDate;
	}

	public Date getRequestReviewDate(){
		return requestReviewDate;
	}

	public void setRequestLastEditedBy(String requestLastEditedBy){
		this. requestLastEditedBy = requestLastEditedBy;
	}

	public String getRequestLastEditedBy(){
		return requestLastEditedBy;
	}

	public void setRequestLastEditDate(Date requestLastEditDate){
		this. requestLastEditDate = requestLastEditDate;
	}

	public Date getRequestLastEditDate(){
		return requestLastEditDate;
	}

	public void setReleaseId(Integer releaseId){
		this. releaseId = releaseId;
	}

	public Integer getReleaseId(){
		return releaseId;
	}

	public void setRequestReleaseDate(BigInteger requestReleaseDate){
		this. requestReleaseDate = requestReleaseDate;
	}

	public BigInteger getRequestReleaseDate(){
		return requestReleaseDate;
	}

	public void setRequestClosedBy(String requestClosedBy){
		this. requestClosedBy = requestClosedBy;
	}

	public String getRequestClosedBy(){
		return requestClosedBy;
	}

	public void setRequestCloseDate(Date requestCloseDate){
		this. requestCloseDate = requestCloseDate;
	}

	public Date getRequestCloseDate(){
		return requestCloseDate;
	}

	public void setRequestOpenCount(Integer requestOpenCount){
		this. requestOpenCount = requestOpenCount;
	}

	public Integer getRequestOpenCount(){
		return requestOpenCount;
	}

	public void setRequestStatus(Integer requestStatus){
		this. requestStatus = requestStatus;
	}

	public Integer getRequestStatus(){
		return requestStatus;
	}

	public void setRequestTransTo(Integer requestTransTo){
		this. requestTransTo = requestTransTo;
	}

	public Integer getRequestTransTo(){
		return requestTransTo;
	}

	public void setRequestTransId(Integer requestTransId){
		this. requestTransId = requestTransId;
	}

	public Integer getRequestTransId(){
		return requestTransId;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
