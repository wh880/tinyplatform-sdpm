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
 * 测试用例表
 */
public class QualityTestCase implements Serializable {

    public static final int DELETE_YES = 1;
    public static final int DELETE_NO = 1;

    public static final String RESULT_NA = "0";
    public static final String RESULT_PASS = "1";
    public static final String RESULT_BLOCK = "3";
    public static final String RESULT_FAULT = "2";

    /**
     * 创建人名称
     */
    private String caseOpenedName;

    /**
     * 执行人名称
     */
    private String caseLastRunnerName;

    /**
     * 用例编号
     */
    private Integer caseId;

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 模块ID
     */
    private Integer moduleId;

    /**
     * CASE_PATH
     */
    private Integer casePath;

    /**
     * 需求ID
     */
    private Integer storyId;

    /**
     * 需求版本
     */
    private Integer storyVersion;

    /**
     * 用例标题
     */
    private String caseTitle;

    /**
     * 前置条件
     */
    private String casePrecondition;

    /**
     * 关键词
     */
    private String caseKeywords;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 用例类型
     */
    private String caseType;
    /**
     * 适用阶段
     */
    private String caseStage;

    /**
     * 执行方式
     */
    private String caseRunway;

    /**
     * 由谁编写
     */
    private String caseScriptedBy;

    /**
     * 编写日期
     */
    private Date caseScriptedDate;

    /**
     * SCRIPTSTATUS
     */
    private String scriptStatus;

    /**
     * SCRIPTLOCATION
     */
    private String scriptLocation;

    /**
     * 用例状态
     */
    private String caseStatus;

    /**
     * 执行频率
     */
    private String caseFrequency;

    /**
     * 排序
     */
    private Integer caseOrder;

    /**
     * 由谁创建
     */
    private String caseOpenedBy;

    /**
     * 创建日期
     */
    private Date caseOpenedDate;

    /**
     * 最后修改者
     */
    private String caseLastEditedBy;

    /**
     * 最后修改日期
     */
    private Date caseLastEditedDate;

    /**
     * 关联用例版本
     */
    private Integer caseVersion;

    /**
     * 相关用例
     */
    private Integer linkCase;

    /**
     * 来源Bug
     */
    private Integer caseFromBug;

    /**
     * 已删除
     */
    private Integer deleted;

    /**
     * 最后执行人
     */
    private String caseLastRunner;

    /**
     * 最后执行时间
     */
    private Date caseLastRunDate;

    /**
     * 用例执行结果
     */
    private String caseLastRunResult;

    private String moduleName;

    private String storyTitle;

    private String caseFromBugTitle;

    private String linkCaseTile;

    private Integer no;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getStoryTitle() {
        return storyTitle;
    }

    public void setStoryTitle(String storyTitle) {
        this.storyTitle = storyTitle;
    }

    public String getCaseFromBugTitle() {
        return caseFromBugTitle;
    }

    public void setCaseFromBugTitle(String caseFromBugTitle) {
        this.caseFromBugTitle = caseFromBugTitle;
    }

    public String getLinkCaseTile() {
        return linkCaseTile;
    }

    public void setLinkCaseTile(String linkCaseTile) {
        this.linkCaseTile = linkCaseTile;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Integer getCasePath() {
        return casePath;
    }

    public void setCasePath(Integer casePath) {
        this.casePath = casePath;
    }

    public Integer getStoryId() {
        return storyId;
    }

    public void setStoryId(Integer storyId) {
        this.storyId = storyId;
    }

    public Integer getStoryVersion() {
        return storyVersion;
    }

    public void setStoryVersion(Integer storyVersion) {
        this.storyVersion = storyVersion;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getCasePrecondition() {
        return casePrecondition;
    }

    public void setCasePrecondition(String casePrecondition) {
        this.casePrecondition = casePrecondition;
    }

    public String getCaseKeywords() {
        return caseKeywords;
    }

    public void setCaseKeywords(String caseKeywords) {
        this.caseKeywords = caseKeywords;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getCaseType() {
        return caseType;
    }

    public void setCaseType(String caseType) {
        this.caseType = caseType;
    }

    public String getCaseStage() {
        return caseStage;
    }

    public void setCaseStage(String caseStage) {
        this.caseStage = caseStage;
    }

    public String getCaseRunway() {
        return caseRunway;
    }

    public void setCaseRunway(String caseRunway) {
        this.caseRunway = caseRunway;
    }

    public String getCaseScriptedBy() {
        return caseScriptedBy;
    }

    public void setCaseScriptedBy(String caseScriptedBy) {
        this.caseScriptedBy = caseScriptedBy;
    }

    public Date getCaseScriptedDate() {
        return caseScriptedDate;
    }

    public void setCaseScriptedDate(Date caseScriptedDate) {
        this.caseScriptedDate = caseScriptedDate;
    }

    public String getScriptStatus() {
        return scriptStatus;
    }

    public void setScriptStatus(String scriptStatus) {
        this.scriptStatus = scriptStatus;
    }

    public String getScriptLocation() {
        return scriptLocation;
    }

    public void setScriptLocation(String scriptLocation) {
        this.scriptLocation = scriptLocation;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public String getCaseFrequency() {
        return caseFrequency;
    }

    public void setCaseFrequency(String caseFrequency) {
        this.caseFrequency = caseFrequency;
    }

    public Integer getCaseOrder() {
        return caseOrder;
    }

    public void setCaseOrder(Integer caseOrder) {
        this.caseOrder = caseOrder;
    }

    public String getCaseOpenedBy() {
        return caseOpenedBy;
    }

    public void setCaseOpenedBy(String caseOpenedBy) {
        this.caseOpenedBy = caseOpenedBy;
    }

    public Date getCaseOpenedDate() {
        return caseOpenedDate;
    }

    public void setCaseOpenedDate(Date caseOpenedDate) {
        this.caseOpenedDate = caseOpenedDate;
    }

    public String getCaseLastEditedBy() {
        return caseLastEditedBy;
    }

    public void setCaseLastEditedBy(String caseLastEditedBy) {
        this.caseLastEditedBy = caseLastEditedBy;
    }

    public Date getCaseLastEditedDate() {
        return caseLastEditedDate;
    }

    public void setCaseLastEditedDate(Date caseLastEditedDate) {
        this.caseLastEditedDate = caseLastEditedDate;
    }

    public Integer getCaseVersion() {
        return caseVersion;
    }

    public void setCaseVersion(Integer caseVersion) {
        this.caseVersion = caseVersion;
    }

    public Integer getLinkCase() {
        return linkCase;
    }

    public void setLinkCase(Integer linkCase) {
        this.linkCase = linkCase;
    }

    public Integer getCaseFromBug() {
        return caseFromBug;
    }

    public void setCaseFromBug(Integer caseFromBug) {
        this.caseFromBug = caseFromBug;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getCaseLastRunner() {
        return caseLastRunner;
    }

    public void setCaseLastRunner(String caseLastRunner) {
        this.caseLastRunner = caseLastRunner;
    }

    public Date getCaseLastRunDate() {
        return caseLastRunDate;
    }

    public void setCaseLastRunDate(Date caseLastRunDate) {
        this.caseLastRunDate = caseLastRunDate;
    }

    public String getCaseLastRunResult() {
        return caseLastRunResult;
    }

    public void setCaseLastRunResult(String caseLastRunResult) {
        this.caseLastRunResult = caseLastRunResult;
    }

    public String getCaseOpenedName() {
        return caseOpenedName;
    }

    public void setCaseOpenedName(String caseOpenedName) {
        this.caseOpenedName = caseOpenedName;
    }

    public String getCaseLastRunnerName() {
        return caseLastRunnerName;
    }

    public void setCaseLastRunnerName(String caseLastRunnerName) {
        this.caseLastRunnerName = caseLastRunnerName;
    }

}
