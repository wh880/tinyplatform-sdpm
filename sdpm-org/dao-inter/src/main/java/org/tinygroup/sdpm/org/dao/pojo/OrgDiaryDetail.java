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

package org.tinygroup.sdpm.org.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * <!-- begin-user-doc -->
 * 每日详情表
 * <p>
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class OrgDiaryDetail implements Serializable {

    /**
     * <!-- begin-user-doc -->
     * 周报详情id
     * <p>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    private Integer orgDetailId;

    /**
     * <!-- begin-user-doc -->
     * 周报id
     * <p>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    private Integer orgDiaryId;

    /**
     * <!-- begin-user-doc -->
     * 用户编号
     * <p>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    private String orgUserId;

    /**
     * <!-- begin-user-doc -->
     * 详情发生时间
     * <p>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    private Date orgDetailDate;

    /**
     * <!-- begin-user-doc -->
     * 详情类型
     * <p>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    private String orgDetailType;

    /**
     * <!-- begin-user-doc -->
     * 详情内容
     * <p>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    private String orgDetailContent;

    /**
     * <!-- begin-user-doc -->
     * 系统日志ID
     * <p>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    private Integer actionId;

    /**
     * 所在星期几
     *
     * @param orgDetailId
     */
    private String effortWeek;

    private String objectType;

    private Integer objectId;

    public void setOrgDetailId(Integer orgDetailId) {
        this.orgDetailId = orgDetailId;
    }

    public Integer getOrgDetailId() {
        return orgDetailId;
    }

    public void setOrgDiaryId(Integer orgDiaryId) {
        this.orgDiaryId = orgDiaryId;
    }

    public Integer getOrgDiaryId() {
        return orgDiaryId;
    }

    public void setOrgUserId(String orgUserId) {
        this.orgUserId = orgUserId;
    }

    public String getOrgUserId() {
        return orgUserId;
    }

    public void setOrgDetailDate(Date orgDetailDate) {
        this.orgDetailDate = orgDetailDate;
    }

    public Date getOrgDetailDate() {
        return orgDetailDate;
    }

    public void setOrgDetailType(String orgDetailType) {
        this.orgDetailType = orgDetailType;
    }

    public String getOrgDetailType() {
        return orgDetailType;
    }

    public void setOrgDetailContent(String orgDetailContent) {
        this.orgDetailContent = orgDetailContent;
    }

    public String getOrgDetailContent() {
        return orgDetailContent;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public Integer getActionId() {
        return actionId;
    }

    public String getEffortWeek() {
        return effortWeek;
    }

    public void setEffortWeek(String effortWeek) {
        this.effortWeek = effortWeek;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }
}
