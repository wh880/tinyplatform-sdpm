package org.tinygroup.sdpm.statistic.dao.pojo;

import java.io.Serializable;

/**
 * Created by MCK on 2015/10/23.
 */
public class Assigned implements Serializable{
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 用户需解决Bug数
     */
    private String bugNum;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBugNum() {
        return bugNum;
    }

    public void setBugNum(String bugNum) {
        this.bugNum = bugNum;
    }
}
