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

package org.tinygroup.sdpm.system.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 日志表
 *
 */
public class SystemEffort implements Serializable {

    /**
     * 日志编号
     *
     */
    private Integer effortId;

    /**
     * 所属项目
     *
     */
    private Integer effortProject;

    /**
     * 所属产品
     *
     */
    private String effortProduct;

    /**
     * 工作内容
     *
     */
    private String effortWork;

    /**
     * 耗时
     *
     */
    private Float effortConsumed;

    /**
     * 日期
     *
     */
    private Date effortDate;

    /**
     * 剩余
     *
     */
    private Float effortLeft;

    /**
     * 开始
     *
     */
    private String effortBegin;

    /**
     * 已关闭
     *
     */
    private String effortEnd;

    /**
     * 对象ID
     *
     */
    private Integer effortObjectId;

    /**
     * 日志对象
     *
     */
    private String effortObjectType;

    /**
     * 登记人
     *
     */
    private String effortAccount;

    public Integer getEffortId() {
        return effortId;
    }

    public void setEffortId(Integer effortId) {
        this.effortId = effortId;
    }

    public Integer getEffortProject() {
        return effortProject;
    }

    public void setEffortProject(Integer effortProject) {
        this.effortProject = effortProject;
    }

    public String getEffortProduct() {
        return effortProduct;
    }

    public void setEffortProduct(String effortProduct) {
        this.effortProduct = effortProduct;
    }

    public String getEffortWork() {
        return effortWork;
    }

    public void setEffortWork(String effortWork) {
        this.effortWork = effortWork;
    }

    public Float getEffortConsumed() {
        return effortConsumed;
    }

    public void setEffortConsumed(Float effortConsumed) {
        this.effortConsumed = effortConsumed;
    }

    public Date getEffortDate() {
        return effortDate;
    }

    public void setEffortDate(Date effortDate) {
        this.effortDate = effortDate;
    }

    public Float getEffortLeft() {
        return effortLeft;
    }

    public void setEffortLeft(Float effortLeft) {
        this.effortLeft = effortLeft;
    }

    public String getEffortBegin() {
        return effortBegin;
    }

    public void setEffortBegin(String effortBegin) {
        this.effortBegin = effortBegin;
    }

    public String getEffortEnd() {
        return effortEnd;
    }

    public void setEffortEnd(String effortEnd) {
        this.effortEnd = effortEnd;
    }

    public Integer getEffortObjectId() {
        return effortObjectId;
    }

    public void setEffortObjectId(Integer effortObjectId) {
        this.effortObjectId = effortObjectId;
    }

    public String getEffortObjectType() {
        return effortObjectType;
    }

    public void setEffortObjectType(String effortObjectType) {
        this.effortObjectType = effortObjectType;
    }

    public String getEffortAccount() {
        return effortAccount;
    }

    public void setEffortAccount(String effortAccount) {
        this.effortAccount = effortAccount;
    }

}
