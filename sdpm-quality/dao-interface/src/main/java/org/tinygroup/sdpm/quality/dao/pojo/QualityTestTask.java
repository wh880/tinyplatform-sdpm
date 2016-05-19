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

package org.tinygroup.sdpm.quality.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 测试任务表
 */
public class QualityTestTask implements Serializable {

    public static final int DELETE_YES = 1;
    public static final int DELETE_NO = 1;

    /**
     * 测试版本编号
     */
    private Integer testversionId;

    /**
     * 任务名称
     */
    private String testtaskTitle;

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 版本名称
     */
    private String buildName;

    /**
     * 负责人
     */
    private String testtaskOwner;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 开始日期
     */
    private Date testtaskBegin;

    /**
     * 结束日期
     */
    private Date testtaskEnd;

    /**
     * 描述
     */
    private String testtaskDesc;

    /**
     * 测试总结
     */
    private String testtaskReport;

    /**
     * 当前状态
     */
    private String testtaskStatus;

    /**
     * 已删除
     */
    private Integer deleted;

    private String searchBuildName;
    private String projectName;

    private Integer no;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getSearchBuildName() {
        return searchBuildName;
    }

    public void setSearchBuildName(String searchBuildName) {
        this.searchBuildName = searchBuildName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getTestversionId() {
        return testversionId;
    }

    public void setTestversionId(Integer testversionId) {
        this.testversionId = testversionId;
    }

    public String getTesttaskTitle() {
        return testtaskTitle;
    }

    public void setTesttaskTitle(String testtaskTitle) {
        this.testtaskTitle = testtaskTitle;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getTesttaskOwner() {
        return testtaskOwner;
    }

    public void setTesttaskOwner(String testtaskOwner) {
        this.testtaskOwner = testtaskOwner;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getTesttaskBegin() {
        return testtaskBegin;
    }

    public void setTesttaskBegin(Date testtaskBegin) {
        this.testtaskBegin = testtaskBegin;
    }

    public Date getTesttaskEnd() {
        return testtaskEnd;
    }

    public void setTesttaskEnd(Date testtaskEnd) {
        this.testtaskEnd = testtaskEnd;
    }

    public String getTesttaskDesc() {
        return testtaskDesc;
    }

    public void setTesttaskDesc(String testtaskDesc) {
        this.testtaskDesc = testtaskDesc;
    }

    public String getTesttaskReport() {
        return testtaskReport;
    }

    public void setTesttaskReport(String testtaskReport) {
        this.testtaskReport = testtaskReport;
    }

    public String getTesttaskStatus() {
        return testtaskStatus;
    }

    public void setTesttaskStatus(String testtaskStatus) {
        this.testtaskStatus = testtaskStatus;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

}
