package org.tinygroup.sdpm.statistic.dao.pojo;

import java.io.Serializable;

/**
 * Created by MCK on 2015/10/23.
 */
public class QualityBugCall implements Serializable {
    /**
     * 产品名
     */
    private String productName;
    /**
     * 产品bug数量
     */
    private String productBugNum;
    /**
     * 用户ID
     */
    private String UserId;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBugNum() {
        return productBugNum;
    }

    public void setProductBugNum(String productBugNum) {
        this.productBugNum = productBugNum;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }
}
