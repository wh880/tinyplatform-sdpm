package org.tinygroup.sdpm.statistic.dao.pojo;

import java.io.Serializable;

/**
 * Created by MCK on 2015/10/23.
 */
public class QualityBugSta implements Serializable {
    /**
     * Bug创建用户Id
     */
    private String userId;
    /**
     * 重复bug状态数
     */
    private Integer repeatBug;
    /**
     * 设计如此状态数
     */
    private Integer designEd;
    /**
     * 外部原因状态数
     */
    private Integer externalCause;
    /**
     * 已解决状态数
     */
    private Integer solved;
    /**
     * 无法重现重现状态数
     */
    private Integer notReproduce;
    /**
     * 延期解决状态数
     */
    private Integer delayResolved;
    /**
     * 未解决状态数
     */
    private Integer notResolved;
    /**
     * bug总数
     */
    private Integer bugNum;
    /**
     * 转需求数
     */
    private Integer toStory;
    /**
     * bug创建是时间
     */
    private String bugCreateDate;
    /**
     * bug所属项目
     */
    private Integer projectId;
    /**
     * bug所属产品
     */
    private Integer productId;

    public Integer getToStory() {
        return toStory;
    }

    public void setToStory(Integer toStory) {
        this.toStory = toStory;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public Integer getBugNum() {
        return bugNum;
    }

    public void setBugNum(Integer bugNum) {
        this.bugNum = bugNum;
    }


    public String getBugCreateDate() {
        return bugCreateDate;
    }

    public void setBugCreateDate(String bugCreateDate) {
        this.bugCreateDate = bugCreateDate;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getRepeatBug() {
        return repeatBug;
    }

    public void setRepeatBug(Integer repeatBug) {
        this.repeatBug = repeatBug;
    }

    public Integer getDesignEd() {
        return designEd;
    }

    public void setDesignEd(Integer designEd) {
        this.designEd = designEd;
    }

    public Integer getExternalCause() {
        return externalCause;
    }

    public void setExternalCause(Integer externalCause) {
        this.externalCause = externalCause;
    }

    public Integer getSolved() {
        return solved;
    }

    public void setSolved(Integer solved) {
        this.solved = solved;
    }

    public Integer getNotReproduce() {
        return notReproduce;
    }

    public void setNotReproduce(Integer notReproduce) {
        this.notReproduce = notReproduce;
    }

    public Integer getDelayResolved() {
        return delayResolved;
    }

    public void setDelayResolved(Integer delayResolved) {
        this.delayResolved = delayResolved;
    }

    public Integer getNotResolved() {
        return notResolved;
    }

    public void setNotResolved(Integer notResolved) {
        this.notResolved = notResolved;
    }

}
