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

import java.util.Date;

/**
 * 客服回访表
 *
 */
public class ServiceReview {

    /**
     * 回访ID
     *
     */
    private Integer reviewId;

    /**
     * 请求ID
     *
     * 服务请求ID
     */
    private Integer clientRequestId;

    /**
     * 回访描述
     *
     */
    private String reviewSpec;

    /**
     * 联系人
     *
     * 默认为需求提交人或产品客户其他联系人
     */
    private String requester;

    /**
     * 回访者
     *
     * 回访人account，当前操作者
     */
    private String reviewer;

    /**
     * 回访时间
     *
     */
    private Date reviewDate;

    /**
     * 回访结果
     *
     * 0-not
     * pass,
     * 1-pass
     */
    private Integer reviewResult;

    /**
     * 回访评分
     *
     * 0很不满意，2不满意，3一般，4满意，5非常满意
     */
    private Integer reviewScore;

    /**
     * 回访类型
     *
     * 0-发放回访，1-拒绝需求回访，2-无修改需求回访
     */
    private Integer reviewType;

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getClientRequestId() {
        return clientRequestId;
    }

    public void setClientRequestId(Integer clientRequestId) {
        this.clientRequestId = clientRequestId;
    }

    public String getReviewSpec() {
        return reviewSpec;
    }

    public void setReviewSpec(String reviewSpec) {
        this.reviewSpec = reviewSpec;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public Integer getReviewResult() {
        return reviewResult;
    }

    public void setReviewResult(Integer reviewResult) {
        this.reviewResult = reviewResult;
    }

    public Integer getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(Integer reviewScore) {
        this.reviewScore = reviewScore;
    }

    public Integer getReviewType() {
        return reviewType;
    }

    public void setReviewType(Integer reviewType) {
        this.reviewType = reviewType;
    }

}
