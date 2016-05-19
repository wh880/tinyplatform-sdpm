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
 * 假期表
 */
public class Holiday implements Serializable {
    public static final int DELETE_YES = 1;
    public static final int DELETE_NO = 0;
    /**
     * 假期ID
     */
    private Integer holidayId;

    /**
     * 假期名
     */
    private String holidayName;

    /**
     * 假期创建人
     */
    private String holidayAccount;

    /**
     * 假期日期
     */
    private String holidayDate;

    /**
     * 假期类型
     */
    private String holidayType;

    /**
     * 删除标志位
     */
    private Integer holidayDeleted;

    /**
     * 公司ID
     */
    private Integer companyId;

    /**
     * 假期描述
     */
    private String holidayDetail;

    /**
     * 假期备注
     */
    private String hoilidayRemark;

    /**
     * 动作
     */
    private String actionAction;
    /**
     * 操作人ID
     */
    private String actionActor;
    /**
     * 操作时间
     */
    private Date actionDate;


    public String getActionAction() {
        return actionAction;
    }

    public void setActionAction(String actionAction) {
        this.actionAction = actionAction;
    }

    public String getActionActor() {
        return actionActor;
    }

    public void setActionActor(String actionActor) {
        this.actionActor = actionActor;
    }

    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    public Integer getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Integer holidayId) {
        this.holidayId = holidayId;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    public String getHolidayAccount() {
        return holidayAccount;
    }

    public void setHolidayAccount(String holidayAccount) {
        this.holidayAccount = holidayAccount;
    }

    public String getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(String holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getHolidayType() {
        return holidayType;
    }

    public void setHolidayType(String holidayType) {
        this.holidayType = holidayType;
    }

    public Integer getHolidayDeleted() {
        return holidayDeleted;
    }

    public void setHolidayDeleted(Integer holidayDeleted) {
        this.holidayDeleted = holidayDeleted;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getHolidayDetail() {
        return holidayDetail;
    }

    public void setHolidayDetail(String holidayDetail) {
        this.holidayDetail = holidayDetail;
    }

    public String getHoilidayRemark() {
        return hoilidayRemark;
    }

    public void setHoilidayRemark(String hoilidayRemark) {
        this.hoilidayRemark = hoilidayRemark;
    }

}
