/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/gpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tinygroup.sdpm.service.dao.pojo;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * 客户请求表
 */
public class ServiceRequest implements Serializable {
    public static final Integer DELETE_YES = 1;
    public static final Integer DELETE_NO = 0;
    public static final Integer CREATED = 0;
    public static final Integer DOING = 1;
    public static final Integer REJECTED = 2;
    public static final Integer TOPRODUCT = 3;
    public static final Integer PLANNED = 4;
    public static final Integer POSTPONED = 5;
    public static final Integer FINISHED = 6;
    public static final Integer RELEASED = 7;
    public static final Integer RETURNVISIT = 8;
    public static final Integer REOPEN = 9;
    public static final Integer CLOSE = 10;

    private String clientName;
    /**
     * 请求ID
     * <p>
     * 服务请求ID
     */
    private Integer clientRequestId;

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 模块ID
     */
    private Integer moduleId;

    /**
     * 请求逻辑编号
     * <p>
     * 服务请求逻辑编号
     */
    private String requestNo;

    /**
     * 请求类型
     * <p>
     * 请求类型
     * Event
     * type:0-bug(缺陷/纠错,1-req1（适应性）,2-req2（完善或增强）,3-req3（个性化定制）,4-reg4（全新）,
     * 5-training,6-support,7-question
     */
    private String requestType;

    /**
     * 请求优先级
     * <p>
     * 请求优先级
     * Priority:0-low,1-common,2-urgent
     */
    private Integer requestPre;

    /**
     * 请求标题
     */
    private String requestTitle;

    /**
     * 关键字
     * <p>
     * 关键词（分隔符）
     */
    private String requestKeywords;

    /**
     * 请求描述
     */
    private String requestSpec;

    /**
     * 异常标记
     * <p>
     * 0-正常；1-异常；2-重大异常
     */
    private Integer requestIsAbnormal;

    /**
     * 客户ID
     */
    private Integer clientId;

    /**
     * 联系人
     * <p>
     * 默认为需求提交人或产品客户其他联系人
     */
    private String requester;

    /**
     * 服务事件提交人
     * <p>
     * 客户自助提交，此字段信息与requester相同，如客服代录入，可以不一致
     */
    private String requestSubmitBy;

    /**
     * 请求提交时间
     */
    private Date requestSubmitDate;

    /**
     * 请求回复时间
     */
    private Date requestReplyDate;

    /**
     * 请求承诺完成日期
     */
    private Date requestCommitmentDate;

    /**
     * 回访人
     */
    private String requestReviewer;

    /**
     * 回访日期
     * <p>
     * 请求回访日期
     */
    private Date requestReviewDate;

    /**
     * 请求最后编辑者
     */
    private String requestLastEditedBy;

    /**
     * 请求最后编辑时间
     */
    private Date requestLastEditDate;

    /**
     * 请求完成日期
     * <p>
     * 支持类需求-实际完成日期
     * 修改类需求-实际发布日期
     */
    private BigInteger requestReleaseDate;

    /**
     * 关闭人
     */
    private String requestClosedBy;

    /**
     * 关闭时间
     */
    private Date requestCloseDate;

    /**
     * 请求打开次数
     * <p>
     * 默认0，经历一次拒绝/关闭再打开加1
     */
    private Integer requestOpenCount;

    /**
     * 请求状态
     * <p>
     * 请求状态0-created新建,1-doing处理中，2-rejected拒绝,3-toProduct转出（回复后用户可见）,4-planned已接受【由产品模块需求纳入计划触发/客服直接处理】
     * （回复后用户可见），5-postponed挂起，6-finished已完成【由产品模块需求阶段】，7-released已发放(用户可见),8-retrunVisit已回访(用户可见),
     * 9-reopen【回访不通过】(用户可见)，10-close关闭【状态8，并且review结果是pass】
     */
    private Integer requestStatus;

    /**
     * 转换为
     * <p>
     * 1-"toReq"转为需求，2-"toBug"转为缺陷，3-"toTask"转为任务
     */
    private Integer requestTransTo;

    /**
     * 转出对象ID
     */
    private Integer requestTransId;

    /**
     * 已删除
     */
    private Integer deleted;

    /**
     * 回复描述
     */
    private String replySpec;

    /**
     * 回复者
     * <p>
     * 客服
     */
    private String replier;

    /**
     * 回复时间
     */
    private Date replyDate;

    public ServiceRequest() {
        setDeleted(DELETE_NO);
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Integer getClientRequestId() {
        return clientRequestId;
    }

    public void setClientRequestId(Integer clientRequestId) {
        this.clientRequestId = clientRequestId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Integer getRequestPre() {
        return requestPre;
    }

    public void setRequestPre(Integer requestPre) {
        this.requestPre = requestPre;
    }

    public String getRequestTitle() {
        return requestTitle;
    }

    public void setRequestTitle(String requestTitle) {
        this.requestTitle = requestTitle;
    }

    public String getRequestKeywords() {
        return requestKeywords;
    }

    public void setRequestKeywords(String requestKeywords) {
        this.requestKeywords = requestKeywords;
    }

    public String getRequestSpec() {
        return requestSpec;
    }

    public void setRequestSpec(String requestSpec) {
        this.requestSpec = requestSpec;
    }

    public Integer getRequestIsAbnormal() {
        return requestIsAbnormal;
    }

    public void setRequestIsAbnormal(Integer requestIsAbnormal) {
        this.requestIsAbnormal = requestIsAbnormal;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getRequestSubmitBy() {
        return requestSubmitBy;
    }

    public void setRequestSubmitBy(String requestSubmitBy) {
        this.requestSubmitBy = requestSubmitBy;
    }

    public Date getRequestSubmitDate() {
        return requestSubmitDate;
    }

    public void setRequestSubmitDate(Date requestSubmitDate) {
        this.requestSubmitDate = requestSubmitDate;
    }

    public Date getRequestReplyDate() {
        return requestReplyDate;
    }

    public void setRequestReplyDate(Date requestReplyDate) {
        this.requestReplyDate = requestReplyDate;
    }

    public Date getRequestCommitmentDate() {
        return requestCommitmentDate;
    }

    public void setRequestCommitmentDate(Date requestCommitmentDate) {
        this.requestCommitmentDate = requestCommitmentDate;
    }

    public String getRequestReviewer() {
        return requestReviewer;
    }

    public void setRequestReviewer(String requestReviewer) {
        this.requestReviewer = requestReviewer;
    }

    public Date getRequestReviewDate() {
        return requestReviewDate;
    }

    public void setRequestReviewDate(Date requestReviewDate) {
        this.requestReviewDate = requestReviewDate;
    }

    public String getRequestLastEditedBy() {
        return requestLastEditedBy;
    }

    public void setRequestLastEditedBy(String requestLastEditedBy) {
        this.requestLastEditedBy = requestLastEditedBy;
    }

    public Date getRequestLastEditDate() {
        return requestLastEditDate;
    }

    public void setRequestLastEditDate(Date requestLastEditDate) {
        this.requestLastEditDate = requestLastEditDate;
    }

    public BigInteger getRequestReleaseDate() {
        return requestReleaseDate;
    }

    public void setRequestReleaseDate(BigInteger requestReleaseDate) {
        this.requestReleaseDate = requestReleaseDate;
    }

    public String getRequestClosedBy() {
        return requestClosedBy;
    }

    public void setRequestClosedBy(String requestClosedBy) {
        this.requestClosedBy = requestClosedBy;
    }

    public Date getRequestCloseDate() {
        return requestCloseDate;
    }

    public void setRequestCloseDate(Date requestCloseDate) {
        this.requestCloseDate = requestCloseDate;
    }

    public Integer getRequestOpenCount() {
        return requestOpenCount;
    }

    public void setRequestOpenCount(Integer requestOpenCount) {
        this.requestOpenCount = requestOpenCount;
    }

    public Integer getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(Integer requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Integer getRequestTransTo() {
        return requestTransTo;
    }

    public void setRequestTransTo(Integer requestTransTo) {
        this.requestTransTo = requestTransTo;
    }

    public Integer getRequestTransId() {
        return requestTransId;
    }

    public void setRequestTransId(Integer requestTransId) {
        this.requestTransId = requestTransId;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getReplySpec() {
        return replySpec;
    }

    public void setReplySpec(String replySpec) {
        this.replySpec = replySpec;
    }

    public String getReplier() {
        return replier;
    }

    public void setReplier(String replier) {
        this.replier = replier;
    }

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
    }

}
