package org.tinygroup.sdpm.product.service.pojo;

import java.util.Date;

/**
 * Created by wangjie14929 on 2015-09-18.
 */
public class ReleasePojo {

    /**
     * 产品ID
     */
    private Integer productId;

    /**
     * 公司ID
     */
    private Integer companyId;

    /**
     * 部门ID
     */
    private Integer deptId;

    /**
     * 产品线ID
     */
    private Integer productLineId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品代号
     */
    private String productCode;

    /**
     * 产品序号
     */
    private Integer productOrder;

    /**
     * 产品状态
     */
    private String productStatus;

    /**
     * 产品描述
     */
    private String productDesc;

    /**
     * 产品经理
     */
    private String productOwner;

    /**
     * 产品质量经理
     */
    private String productQualityManager;

    /**
     * 产品交付经理
     */
    private String productDeliveryManager;

    /**
     * 权限模式
     * <p>
     * 本部门范围：0-open公开；1-custom自定义（产品/项目团队和白名单可访问）；2-private私有（产品/项目团队成员才可访问）
     */
    private Integer acl;

    /**
     * 白名单
     */
    private String productWhiteList;

    /**
     * 创建者
     */
    private String productCreatedBy;

    /**
     * 创建日期
     */
    private Date productCreatedDate;

    /**
     * 创建版本
     */
    private String productCreatedVersion;

    /**
     * 已删除
     */
    private Integer deleted;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getProductLineId() {
        return productLineId;
    }

    public void setProductLineId(Integer productLineId) {
        this.productLineId = productLineId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Integer getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(Integer productOrder) {
        this.productOrder = productOrder;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductOwner() {
        return productOwner;
    }

    public void setProductOwner(String productOwner) {
        this.productOwner = productOwner;
    }

    public String getProductQualityManager() {
        return productQualityManager;
    }

    public void setProductQualityManager(String productQualityManager) {
        this.productQualityManager = productQualityManager;
    }

    public String getProductDeliveryManager() {
        return productDeliveryManager;
    }

    public void setProductDeliveryManager(String productDeliveryManager) {
        this.productDeliveryManager = productDeliveryManager;
    }

    public Integer getAcl() {
        return acl;
    }

    public void setAcl(Integer acl) {
        this.acl = acl;
    }

    public String getProductWhiteList() {
        return productWhiteList;
    }

    public void setProductWhiteList(String productWhiteList) {
        this.productWhiteList = productWhiteList;
    }

    public String getProductCreatedBy() {
        return productCreatedBy;
    }

    public void setProductCreatedBy(String productCreatedBy) {
        this.productCreatedBy = productCreatedBy;
    }

    public Date getProductCreatedDate() {
        return productCreatedDate;
    }

    public void setProductCreatedDate(Date productCreatedDate) {
        this.productCreatedDate = productCreatedDate;
    }

    public String getProductCreatedVersion() {
        return productCreatedVersion;
    }

    public void setProductCreatedVersion(String productCreatedVersion) {
        this.productCreatedVersion = productCreatedVersion;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }


}
