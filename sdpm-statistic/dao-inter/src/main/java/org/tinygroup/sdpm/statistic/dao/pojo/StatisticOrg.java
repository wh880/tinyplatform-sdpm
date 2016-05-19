package org.tinygroup.sdpm.statistic.dao.pojo;

import java.io.Serializable;
import java.util.Date;

public class StatisticOrg implements Serializable {
    public static final String DELETE_YES = "1";
    public static final String DELETE_NO = "0";

    /**
     * 项目下任务完成进度
     */
    private float percent;
    /**
     * 该项目燃尽图的字符串
     */
    private String burnValue;

    /**
     * 总预计
     */
    private float estimate;

    /**
     * 总花费
     */
    private float consume;
    /**
     * 项目id
     */
    private Integer projectId;
    /**
     * 是否作为目录
     * <p>
     * 0-false,1-true
     */
    private String projectIsCat;
    /**
     * 目录id
     */
    private Integer projectCatId;
    /**
     * 项目类型
     * <p>
     * 0-长期项目，1-短期项目，2-运维项目
     */
    private String projectType;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 项目代号
     */
    private String projectCode;
    /**
     * 项目开始日期
     */
    private Date projectBegin;
    /**
     * 项目结束日期
     */
    private Date projectEnd;
    /**
     * 可用工作日
     */
    private Integer projectDays;
    /**
     * 项目状态
     */
    private String projectStatus;
    /**
     * 项目所处阶段
     * <p>
     * 0-未开始，1-进行中，2-已挂起，3-已完成
     */
    private String projectStatge;
    /**
     * 优先级
     * <p>
     * 1，2，3，4
     * 递增
     */
    private String projectPri;
    /**
     * 项目描述
     */
    private String projectDesc;
    /**
     * 由谁创建
     */
    private String projectOpenedBy;
    /**
     * 创建日期
     */
    private Date projectOpenedDate;
    /**
     * 项目创建版本
     */
    private String projectOpenedVersion;
    /**
     * 项目由谁关闭
     */
    private String projectCloseBy;
    /**
     * 项目关闭日期
     */
    private Date projectCloseDate;
    /**
     * 项目由谁取消
     */
    private String projectCanceledBy;
    /**
     * 项目取消日期
     */
    private Date projectCanceledDate;
    /**
     * 产品负责人
     */
    private String projectPo;
    /**
     * 项目负责人
     */
    private String projectPm;
    /**
     * 测试负责人
     */
    private String projectQd;
    /**
     * 项目发布负责人
     */
    private String projectRd;
    /**
     * 团队成员
     */
    private String projectTeam;
    /**
     * 访问控制
     * <p>
     * 0-open,1-private,2-custom
     */
    private String projectAcl;
//	/**
//	 * 分组白名单
//	 *
//	 */
//	private String projectWhiteList;
    /**
     * 项目排序
     */
    private Integer projectOrder;
    /**
     * 已删除
     * <p>
     * 0-未删除，1-删除
     */

    private String projectDeleted;
    /**
     * 任务数
     */
    private Integer taskNum;

    public Integer getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(Integer taskNum) {
        this.taskNum = taskNum;
    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
    }

    public String getBurnValue() {
        return burnValue;
    }

    public void setBurnValue(String burnValue) {
        this.burnValue = burnValue;
    }

    public float getEstimate() {
        return estimate;
    }

    public void setEstimate(float estimate) {
        this.estimate = estimate;
    }

    public float getConsume() {
        return consume;
    }

    public void setConsume(float consume) {
        this.consume = consume;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectIsCat() {
        return projectIsCat;
    }

    public void setProjectIsCat(String projectIsCat) {
        this.projectIsCat = projectIsCat;
    }

    public Integer getProjectCatId() {
        return projectCatId;
    }

    public void setProjectCatId(Integer projectCatId) {
        this.projectCatId = projectCatId;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Date getProjectBegin() {
        return projectBegin;
    }

    public void setProjectBegin(Date projectBegin) {
        this.projectBegin = projectBegin;
    }

    public Date getProjectEnd() {
        return projectEnd;
    }

    public void setProjectEnd(Date projectEnd) {
        this.projectEnd = projectEnd;
    }

    public Integer getProjectDays() {
        return projectDays;
    }

    public void setProjectDays(Integer projectDays) {
        this.projectDays = projectDays;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getProjectStatge() {
        return projectStatge;
    }

    public void setProjectStatge(String projectStatge) {
        this.projectStatge = projectStatge;
    }

    public String getProjectPri() {
        return projectPri;
    }

    public void setProjectPri(String projectPri) {
        this.projectPri = projectPri;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public String getProjectOpenedBy() {
        return projectOpenedBy;
    }

    public void setProjectOpenedBy(String projectOpenedBy) {
        this.projectOpenedBy = projectOpenedBy;
    }

    public Date getProjectOpenedDate() {
        return projectOpenedDate;
    }

    public void setProjectOpenedDate(Date projectOpenedDate) {
        this.projectOpenedDate = projectOpenedDate;
    }

    public String getProjectOpenedVersion() {
        return projectOpenedVersion;
    }

    public void setProjectOpenedVersion(String projectOpenedVersion) {
        this.projectOpenedVersion = projectOpenedVersion;
    }

    public String getProjectCloseBy() {
        return projectCloseBy;
    }

    public void setProjectCloseBy(String projectCloseBy) {
        this.projectCloseBy = projectCloseBy;
    }

    public Date getProjectCloseDate() {
        return projectCloseDate;
    }

    public void setProjectCloseDate(Date projectCloseDate) {
        this.projectCloseDate = projectCloseDate;
    }

    public String getProjectCanceledBy() {
        return projectCanceledBy;
    }

    public void setProjectCanceledBy(String projectCanceledBy) {
        this.projectCanceledBy = projectCanceledBy;
    }

    public Date getProjectCanceledDate() {
        return projectCanceledDate;
    }

    public void setProjectCanceledDate(Date projectCanceledDate) {
        this.projectCanceledDate = projectCanceledDate;
    }

    public String getProjectPo() {
        return projectPo;
    }

    public void setProjectPo(String projectPo) {
        this.projectPo = projectPo;
    }

    public String getProjectPm() {
        return projectPm;
    }

    public void setProjectPm(String projectPm) {
        this.projectPm = projectPm;
    }

    public String getProjectQd() {
        return projectQd;
    }

    public void setProjectQd(String projectQd) {
        this.projectQd = projectQd;
    }

    public String getProjectRd() {
        return projectRd;
    }

    public void setProjectRd(String projectRd) {
        this.projectRd = projectRd;
    }

    public String getProjectTeam() {
        return projectTeam;
    }

    public void setProjectTeam(String projectTeam) {
        this.projectTeam = projectTeam;
    }

    public String getProjectAcl() {
        return projectAcl;
    }

    public void setProjectAcl(String projectAcl) {
        this.projectAcl = projectAcl;
    }

    public Integer getProjectOrder() {
        return projectOrder;
    }

    public void setProjectOrder(Integer projectOrder) {
        this.projectOrder = projectOrder;
    }

    public String getProjectDeleted() {
        return projectDeleted;
    }

    public void setProjectDeleted(String projectDeleted) {
        this.projectDeleted = projectDeleted;
    }

}
