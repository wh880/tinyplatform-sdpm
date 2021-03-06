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
 * 客服回复表
 */
public class ServiceReply {

    /**
     * 回复ID
     */
    private Integer replyId;

    /**
     * 请求ID
     * <p>
     * 服务请求ID
     */
    private Integer clientRequestId;

    /**
     * 回复意见
     * <p>
     * 0-接受；1-拒绝；2-待定
     */
    private Integer replyOpinion;

    /**
     * 回复描述
     */
    private String replySpec;

    /**
     * 承诺日期
     * <p>
     * 回复意见为“0-接受，”时，应有承诺日期
     */
    private Date replyCommitmentDate;

    /**
     * 回复处理人
     * <p>
     * 可以是客服或转出后的产品组处理人
     */
    private String replyDoBy;

    /**
     * 回复处理日期
     */
    private Date replyDoDate;

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

    /**
     * 是否回复
     * <p>
     * 0-未回复；1-已回复
     */
    private Integer replyDone;

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public Integer getClientRequestId() {
        return clientRequestId;
    }

    public void setClientRequestId(Integer clientRequestId) {
        this.clientRequestId = clientRequestId;
    }

    public Integer getReplyOpinion() {
        return replyOpinion;
    }

    public void setReplyOpinion(Integer replyOpinion) {
        this.replyOpinion = replyOpinion;
    }

    public String getReplySpec() {
        return replySpec;
    }

    public void setReplySpec(String replySpec) {
        this.replySpec = replySpec;
    }

    public Date getReplyCommitmentDate() {
        return replyCommitmentDate;
    }

    public void setReplyCommitmentDate(Date replyCommitmentDate) {
        this.replyCommitmentDate = replyCommitmentDate;
    }

    public String getReplyDoBy() {
        return replyDoBy;
    }

    public void setReplyDoBy(String replyDoBy) {
        this.replyDoBy = replyDoBy;
    }

    public Date getReplyDoDate() {
        return replyDoDate;
    }

    public void setReplyDoDate(Date replyDoDate) {
        this.replyDoDate = replyDoDate;
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

    public Integer getReplyDone() {
        return replyDone;
    }

    public void setReplyDone(Integer replyDone) {
        this.replyDone = replyDone;
    }

}
