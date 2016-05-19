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
 * 缺陷表
 */
public class QualityBug implements Serializable {
    public static final String STATUS_ACTIVE = "1"; //激活
    public static final String STATUS_RESOLVED = "2"; //已解决
    public static final String STATUS_CLOSED = "3";  //已关闭

    private Integer releaseId;
    /**
     * Bug编号
     */
    private Integer bugId;

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 模块ID
     */
    private Integer moduleId;

    /**
     * 项目id
     */
    private Integer projectId;

    /**
     * 计划ID
     */
    private Integer planId;

    /**
     * 需求ID
     */
    private Integer storyId;

    /**
     * 需求版本
     */
    private Integer storyVersion;

    /**
     * 任务id
     */
    private Integer taskId;

    /**
     * 转任务
     */
    private Integer toTaskId;

    /**
     * 转需求
     */
    private Integer toStoryId;

    /**
     * Bug标题
     */
    private String bugTitle;

    /**
     * 关键词
     */
    private String bugKeywords;

    /**
     * 严重程度
     */
    private Integer bugSeverity;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * Bug类型
     */
    private String bugType;

    /**
     * 操作系统
     */
    private String operatingSystem;

    /**
     * 浏览器
     */
    private String browser;

    /**
     * 硬件平台
     */
    private String hardware;

    /**
     * 如何发现
     */
    private String bugFound;

    /**
     * 重现步骤
     */
    private String bugSteps;

    /**
     * Bug状态
     */
    private String bugStatus;

    /**
     * 是否确认
     */
    private Integer bugConfirmed;

    /**
     * 激活次数
     */
    private Integer bugActivatedCount;

    /**
     * 抄送给
     */
    private String bugMailto;

    /**
     * 由谁创建
     */
    private String bugOpenedBy;

    /**
     * 创建日期
     */
    private Date bugOpenedDate;

    /**
     * 影响版本
     */
    private String bugOpenedBuild;

    /**
     * 指派给
     */
    private String bugAssignedTo;

    /**
     * 指派日期
     */
    private Date bugAssignedDate;

    /**
     * 解决者
     */
    private String bugResolvedBy;

    /**
     * 解决方案
     */
    private String bugResolution;

    /**
     * 解决版本
     */
    private String bugResolvedBuild;

    /**
     * 解决日期
     */
    private Date bugResolvedDate;

    /**
     * 由谁关闭
     */
    private String bugClosedBy;

    /**
     * 关闭日期
     */
    private Date bugClosedDate;

    /**
     * 重复Bug的ID
     */
    private Integer bugDuplicateBug;

    /**
     * 相关Bug
     */
    private String linkBug;

    /**
     * 相关用例
     */
    private Integer linkCase;

    /**
     * 关联用例版本
     */
    private Integer caseVersion;

    /**
     * BUG_RESULT
     */
    private Integer bugResult;

    /**
     * BUG_REPO
     */
    private Integer bugRepo;

    /**
     * BUG_ENTRY
     */
    private String bugEntry;

    /**
     * 来源用例
     */
    private Integer bugFromCase;

    /**
     * BUG_LINES
     */
    private String bugLines;

    /**
     * BUG_V1
     */
    private String bugV1;

    /**
     * BUG_V2
     */
    private String bugV2;

    /**
     * BUG_REPOTYPE
     */
    private String bugRepoType;

    /**
     * 测试任务编号
     */
    private Integer testtask;

    /**
     * 最后修改者
     */
    private String bugLastEditedBy;

    /**
     * 最后修改日期
     */
    private Date bugLastEditedDate;

    /**
     * 已删除
     */
    private Integer deleted;


    private String productName;

    private String moduleName;

    private String planName;

    private String resolveBuild;

    private String fromCase;

    private String linkStoryName;

    private String toTaskName;

    private String linkCaseTitle;

    private String linkBugTitle;

    private String projectName;

    private String linkTaskName;

    private String toStoryTitle;

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

    public String getResolveBuild() {
        return resolveBuild;
    }

    public void setResolveBuild(String resolveBuild) {
        this.resolveBuild = resolveBuild;
    }

    public String getFromCase() {
        return fromCase;
    }

    public void setFromCase(String fromCase) {
        this.fromCase = fromCase;
    }

    public String getLinkCaseTitle() {
        return linkCaseTitle;
    }

    public void setLinkCaseTitle(String linkCaseTitle) {
        this.linkCaseTitle = linkCaseTitle;
    }

    public String getLinkBugTitle() {
        return linkBugTitle;
    }

    public void setLinkBugTitle(String linkBugTitle) {
        this.linkBugTitle = linkBugTitle;
    }

    public String getLinkTaskName() {
        return linkTaskName;
    }

    public void setLinkTaskName(String linkTaskName) {
        this.linkTaskName = linkTaskName;
    }

    public String getToStoryTitle() {
        return toStoryTitle;
    }

    public void setToStoryTitle(String toStoryTitle) {
        this.toStoryTitle = toStoryTitle;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLinkStoryName() {
        return linkStoryName;
    }

    public void setLinkStoryName(String linkStoryName) {
        this.linkStoryName = linkStoryName;
    }

    public String getToTaskName() {
        return toTaskName;
    }

    public void setToTaskName(String toTaskName) {
        this.toTaskName = toTaskName;
    }

    public Integer getBugId() {
        return bugId;
    }

    public void setBugId(Integer bugId) {
        this.bugId = bugId;
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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
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

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getToTaskId() {
        return toTaskId;
    }

    public void setToTaskId(Integer toTaskId) {
        this.toTaskId = toTaskId;
    }

    public Integer getToStoryId() {
        return toStoryId;
    }

    public void setToStoryId(Integer toStoryId) {
        this.toStoryId = toStoryId;
    }

    public String getBugTitle() {
        return bugTitle;
    }

    public void setBugTitle(String bugTitle) {
        this.bugTitle = bugTitle;
    }

    public String getBugKeywords() {
        return bugKeywords;
    }

    public void setBugKeywords(String bugKeywords) {
        this.bugKeywords = bugKeywords;
    }

    public Integer getBugSeverity() {
        return bugSeverity;
    }

    public void setBugSeverity(Integer bugSeverity) {
        this.bugSeverity = bugSeverity;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getBugType() {
        return bugType;
    }

    public void setBugType(String bugType) {
        this.bugType = bugType;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getHardware() {
        return hardware;
    }

    public void setHardware(String hardware) {
        this.hardware = hardware;
    }

    public String getBugFound() {
        return bugFound;
    }

    public void setBugFound(String bugFound) {
        this.bugFound = bugFound;
    }

    public String getBugSteps() {
        return bugSteps;
    }

    public void setBugSteps(String bugSteps) {
        this.bugSteps = bugSteps;
    }

    public String getBugStatus() {
        return bugStatus;
    }

    public void setBugStatus(String bugStatus) {
        this.bugStatus = bugStatus;
    }

    public Integer getBugConfirmed() {
        return bugConfirmed;
    }

    public void setBugConfirmed(Integer bugConfirmed) {
        this.bugConfirmed = bugConfirmed;
    }

    public Integer getBugActivatedCount() {
        return bugActivatedCount;
    }

    public void setBugActivatedCount(Integer bugActivatedCount) {
        this.bugActivatedCount = bugActivatedCount;
    }

    public String getBugMailto() {
        return bugMailto;
    }

    public void setBugMailto(String bugMailto) {
        this.bugMailto = bugMailto;
    }

    public String getBugOpenedBy() {
        return bugOpenedBy;
    }

    public void setBugOpenedBy(String bugOpenedBy) {
        this.bugOpenedBy = bugOpenedBy;
    }

    public Date getBugOpenedDate() {
        return bugOpenedDate;
    }

    public void setBugOpenedDate(Date bugOpenedDate) {
        this.bugOpenedDate = bugOpenedDate;
    }

    public String getBugOpenedBuild() {
        return bugOpenedBuild;
    }

    public void setBugOpenedBuild(String bugOpenedBuild) {
        this.bugOpenedBuild = bugOpenedBuild;
    }

    public String getBugAssignedTo() {
        return bugAssignedTo;
    }

    public void setBugAssignedTo(String bugAssignedTo) {
        this.bugAssignedTo = bugAssignedTo;
    }

    public Date getBugAssignedDate() {
        return bugAssignedDate;
    }

    public void setBugAssignedDate(Date bugAssignedDate) {
        this.bugAssignedDate = bugAssignedDate;
    }

    public String getBugResolvedBy() {
        return bugResolvedBy;
    }

    public void setBugResolvedBy(String bugResolvedBy) {
        this.bugResolvedBy = bugResolvedBy;
    }

    public String getBugResolution() {
        return bugResolution;
    }

    public void setBugResolution(String bugResolution) {
        this.bugResolution = bugResolution;
    }

    public String getBugResolvedBuild() {
        return bugResolvedBuild;
    }

    public void setBugResolvedBuild(String bugResolvedBuild) {
        this.bugResolvedBuild = bugResolvedBuild;
    }

    public Date getBugResolvedDate() {
        return bugResolvedDate;
    }

    public void setBugResolvedDate(Date bugResolvedDate) {
        this.bugResolvedDate = bugResolvedDate;
    }

    public String getBugClosedBy() {
        return bugClosedBy;
    }

    public void setBugClosedBy(String bugClosedBy) {
        this.bugClosedBy = bugClosedBy;
    }

    public Date getBugClosedDate() {
        return bugClosedDate;
    }

    public void setBugClosedDate(Date bugClosedDate) {
        this.bugClosedDate = bugClosedDate;
    }

    public Integer getBugDuplicateBug() {
        return bugDuplicateBug;
    }

    public void setBugDuplicateBug(Integer bugDuplicateBug) {
        this.bugDuplicateBug = bugDuplicateBug;
    }

    public String getLinkBug() {
        return linkBug;
    }

    public void setLinkBug(String linkBug) {
        this.linkBug = linkBug;
    }

    public Integer getLinkCase() {
        return linkCase;
    }

    public void setLinkCase(Integer linkCase) {
        this.linkCase = linkCase;
    }

    public Integer getCaseVersion() {
        return caseVersion;
    }

    public void setCaseVersion(Integer caseVersion) {
        this.caseVersion = caseVersion;
    }

    public Integer getBugResult() {
        return bugResult;
    }

    public void setBugResult(Integer bugResult) {
        this.bugResult = bugResult;
    }

    public Integer getBugRepo() {
        return bugRepo;
    }

    public void setBugRepo(Integer bugRepo) {
        this.bugRepo = bugRepo;
    }

    public String getBugEntry() {
        return bugEntry;
    }

    public void setBugEntry(String bugEntry) {
        this.bugEntry = bugEntry;
    }

    public Integer getBugFromCase() {
        return bugFromCase;
    }

    public void setBugFromCase(Integer bugFromCase) {
        this.bugFromCase = bugFromCase;
    }

    public String getBugLines() {
        return bugLines;
    }

    public void setBugLines(String bugLines) {
        this.bugLines = bugLines;
    }

    public String getBugV1() {
        return bugV1;
    }

    public void setBugV1(String bugV1) {
        this.bugV1 = bugV1;
    }

    public String getBugV2() {
        return bugV2;
    }

    public void setBugV2(String bugV2) {
        this.bugV2 = bugV2;
    }

    public String getBugRepoType() {
        return bugRepoType;
    }

    public void setBugRepoType(String bugRepoType) {
        this.bugRepoType = bugRepoType;
    }

    public Integer getTesttask() {
        return testtask;
    }

    public void setTesttask(Integer testtask) {
        this.testtask = testtask;
    }

    public String getBugLastEditedBy() {
        return bugLastEditedBy;
    }

    public void setBugLastEditedBy(String bugLastEditedBy) {
        this.bugLastEditedBy = bugLastEditedBy;
    }

    public Date getBugLastEditedDate() {
        return bugLastEditedDate;
    }

    public void setBugLastEditedDate(Date bugLastEditedDate) {
        this.bugLastEditedDate = bugLastEditedDate;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getReleaseId() {
        return releaseId;
    }

    public void setReleaseId(Integer releaseId) {
        this.releaseId = releaseId;
    }

}
