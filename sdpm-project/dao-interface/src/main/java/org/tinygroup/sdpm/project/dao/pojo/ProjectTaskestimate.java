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

package org.tinygroup.sdpm.project.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务预计
 */
public class ProjectTaskestimate implements Serializable {

    /**
     * 任务预计id
     */
    private Integer taskestimateId;

    /**
     * 任务id
     */
    private Integer taskId;

    /**
     * 任务预计时间
     */
    private Date taskestimateDate;

    /**
     * 任务预计剩余
     */
    private Float taskestimateLeft;

    /**
     * 任务预计消耗
     */
    private Float taskestimateConsumed;

    /**
     * 操作人帐号
     */
    private String taskestimateAccount;

    /**
     * 备用字段
     */
    private String taskestimateWork;

    public Integer getTaskestimateId() {
        return taskestimateId;
    }

    public void setTaskestimateId(Integer taskestimateId) {
        this.taskestimateId = taskestimateId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Date getTaskestimateDate() {
        return taskestimateDate;
    }

    public void setTaskestimateDate(Date taskestimateDate) {
        this.taskestimateDate = taskestimateDate;
    }

    public Float getTaskestimateLeft() {
        return taskestimateLeft;
    }

    public void setTaskestimateLeft(Float taskestimateLeft) {
        this.taskestimateLeft = taskestimateLeft;
    }

    public Float getTaskestimateConsumed() {
        return taskestimateConsumed;
    }

    public void setTaskestimateConsumed(Float taskestimateConsumed) {
        this.taskestimateConsumed = taskestimateConsumed;
    }

    public String getTaskestimateAccount() {
        return taskestimateAccount;
    }

    public void setTaskestimateAccount(String taskestimateAccount) {
        this.taskestimateAccount = taskestimateAccount;
    }

    public String getTaskestimateWork() {
        return taskestimateWork;
    }

    public void setTaskestimateWork(String taskestimateWork) {
        this.taskestimateWork = taskestimateWork;
    }

}
