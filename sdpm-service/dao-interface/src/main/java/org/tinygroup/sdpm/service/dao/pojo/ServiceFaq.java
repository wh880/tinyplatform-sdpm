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
import java.util.Date;

/**
 * 问答表
 */
public class ServiceFaq implements Serializable {
    public static final Integer DELETE_YES = 1;
    public static final Integer DELETE_NO = 0;

    /**
     * 问答ID
     */
    private Integer faqId;

    /**
     * 问题描述
     */
    private String faqQuestion;

    /**
     * 解答
     */
    private String faqAnswer;

    /**
     * 已删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private Date faqCreateDate;

    /**
     * 创建人
     */
    private String faqCreatedBy;

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 关键字
     */
    private String faqKeywords;

    /**
     * 来源ID
     */
    private Integer faqSourceId;

    /**
     * FAQ来源
     */
    private String faqSource;

    /**
     * 回复者
     */
    private String faqRepliedBy;

    /**
     * FAQ_REPLY_DATE
     */
    private Date faqReplyDate;

    /**
     * 问题类型id
     */
    private Integer faqTypeId;

    public ServiceFaq() {
        setDeleted(DELETE_NO);
    }

    public Integer getFaqId() {
        return faqId;
    }

    public void setFaqId(Integer faqId) {
        this.faqId = faqId;
    }

    public String getFaqQuestion() {
        return faqQuestion;
    }

    public void setFaqQuestion(String faqQuestion) {
        this.faqQuestion = faqQuestion;
    }

    public String getFaqAnswer() {
        return faqAnswer;
    }

    public void setFaqAnswer(String faqAnswer) {
        this.faqAnswer = faqAnswer;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Date getFaqCreateDate() {
        return faqCreateDate;
    }

    public void setFaqCreateDate(Date faqCreateDate) {
        this.faqCreateDate = faqCreateDate;
    }

    public String getFaqCreatedBy() {
        return faqCreatedBy;
    }

    public void setFaqCreatedBy(String faqCreatedBy) {
        this.faqCreatedBy = faqCreatedBy;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getFaqKeywords() {
        return faqKeywords;
    }

    public void setFaqKeywords(String faqKeywords) {
        this.faqKeywords = faqKeywords;
    }

    public Integer getFaqSourceId() {
        return faqSourceId;
    }

    public void setFaqSourceId(Integer faqSourceId) {
        this.faqSourceId = faqSourceId;
    }

    public String getFaqSource() {
        return faqSource;
    }

    public void setFaqSource(String faqSource) {
        this.faqSource = faqSource;
    }

    public String getFaqRepliedBy() {
        return faqRepliedBy;
    }

    public void setFaqRepliedBy(String faqRepliedBy) {
        this.faqRepliedBy = faqRepliedBy;
    }

    public Date getFaqReplyDate() {
        return faqReplyDate;
    }

    public void setFaqReplyDate(Date faqReplyDate) {
        this.faqReplyDate = faqReplyDate;
    }

    public Integer getFaqTypeId() {
        return faqTypeId;
    }

    public void setFaqTypeId(Integer faqTypeId) {
        this.faqTypeId = faqTypeId;
    }

}
