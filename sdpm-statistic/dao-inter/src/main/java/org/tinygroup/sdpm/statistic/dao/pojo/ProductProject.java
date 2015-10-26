package org.tinygroup.sdpm.statistic.dao.pojo;

import java.io.Serializable;

/**
 * Created by MCK on 2015/10/26.
 */
public class ProductProject implements Serializable{
    /**
     * 产品名
     */
    String productName;
    /**
     * 项目数
     */
    Integer projectNum;
    /**
     * 耗时
     */
    Integer consumedSum;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(Integer projectNum) {
        this.projectNum = projectNum;
    }

    public Integer getConsumedSum() {
        return consumedSum;
    }

    public void setConsumedSum(Integer consumedSum) {
        this.consumedSum = consumedSum;
    }
}
