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
 * faq分类
 */
public class ServiceFaqType implements Serializable {
    public static final Integer DELETE_YES = 1;
    public static final Integer DELETE_NO = 0;

    /**
     * 问题类型id
     */
    private Integer faqTypeId;

    /**
     * 问答类型
     */
    private String faqType;

    /**
     * 父级问题类型id
     */
    private Integer faqParentTypeId;

    /**
     * faq类型创建时间
     */
    private Date faqTypeCreatDay;

    /**
     * 创建人
     */
    private String faqCreatedBy;

    /**
     * 已删除
     */
    private Integer deleted;

    public ServiceFaqType() {
        setDeleted(DELETE_NO);
    }

    public Integer getFaqTypeId() {
        return faqTypeId;
    }

    public void setFaqTypeId(Integer faqTypeId) {
        this.faqTypeId = faqTypeId;
    }

    public String getFaqType() {
        return faqType;
    }

    public void setFaqType(String faqType) {
        this.faqType = faqType;
    }

    public Integer getFaqParentTypeId() {
        return faqParentTypeId;
    }

    public void setFaqParentTypeId(Integer faqParentTypeId) {
        this.faqParentTypeId = faqParentTypeId;
    }

    public Date getFaqTypeCreatDay() {
        return faqTypeCreatDay;
    }

    public void setFaqTypeCreatDay(Date faqTypeCreatDay) {
        this.faqTypeCreatDay = faqTypeCreatDay;
    }

    public String getFaqCreatedBy() {
        return faqCreatedBy;
    }

    public void setFaqCreatedBy(String faqCreatedBy) {
        this.faqCreatedBy = faqCreatedBy;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

}
