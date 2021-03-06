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
 * 系统日志表
 */
public class SystemAction implements Serializable, Comparable<SystemAction> {

    /**
     * 系统日志ID
     */
    private Integer actionId;

    /**
     * 对象类型
     */
    private String actionObjectType;

    /**
     * 对象ID
     */
    private String actionObjectId;

    /**
     * 所属项目
     */
    private String actionProject;

    /**
     * 所属产品
     */
    private String actionProduct;

    /**
     * 操作者
     */
    private String actionActor;
    /**
     * 动作
     */
    private String actionAction;


    /**
     * 系统日志日期
     */
    private Date actionDate;

    /**
     * 注释
     */
    private String actionComment;

    /**
     * EXTRA
     */
    private String actionExtra;

    /**
     * 是否已读
     */
    private String actionRead;

    /**
     * ACTION_efforted
     */
    private Integer actionEfforted;
    /**
     * 创建者名称
     */
    private String actorName;

    /**
     * 对象名称
     */
    private String objectName;

    private String url;

    /**
     * 所在星期几
     */
    private String actionWeekDay;

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public Integer getActionId() {
        return actionId;
    }

    public void setActionId(Integer actionId) {
        this.actionId = actionId;
    }

    public String getActionObjectType() {
        return actionObjectType;
    }

    public void setActionObjectType(String actionObjectType) {
        this.actionObjectType = actionObjectType;
    }

    public String getActionObjectId() {
        return actionObjectId;
    }

    public void setActionObjectId(String actionObjectId) {
        this.actionObjectId = actionObjectId;
    }

    public String getActionProject() {
        return actionProject;
    }

    public void setActionProject(String actionProject) {
        this.actionProject = actionProject;
    }

    public String getActionProduct() {
        return actionProduct;
    }

    public void setActionProduct(String actionProduct) {
        this.actionProduct = actionProduct;
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

    public String getActionComment() {
        return actionComment;
    }

    public void setActionComment(String actionComment) {
        this.actionComment = actionComment;
    }

    public String getActionExtra() {
        return actionExtra;
    }

    public void setActionExtra(String actionExtra) {
        this.actionExtra = actionExtra;
    }

    public String getActionRead() {
        return actionRead;
    }

    public void setActionRead(String actionRead) {
        this.actionRead = actionRead;
    }

    public Integer getActionEfforted() {
        return actionEfforted;
    }

    public void setActionEfforted(Integer actionEfforted) {
        this.actionEfforted = actionEfforted;
    }

    public String getActionAction() {
        return actionAction;
    }

    public void setActionAction(String actionAction) {
        this.actionAction = actionAction;
    }

    public String getActionWeekDay() {
        return actionWeekDay;
    }

    public void setActionWeekDay(String actionWeekDay) {
        this.actionWeekDay = actionWeekDay;
    }

    @Override
    public int compareTo(SystemAction o) {
        if (o == null)
            return 0;
        else {
            return this.actionDate.compareTo(o.getActionDate());
        }
    }
}
