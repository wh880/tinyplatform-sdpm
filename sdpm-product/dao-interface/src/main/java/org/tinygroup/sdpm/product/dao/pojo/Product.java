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

package org.tinygroup.sdpm.product.dao.pojo;

import java.io.Serializable;
import java.util.Date;


public class Product implements Serializable {

    public static final Integer ACl_All = 0;
    public static final Integer ACl_TEAM = 1;
    public static final Integer ACl_TEAM_AND_ROLE = 2;
    public static Integer DELETE_YES = 1;
    public static Integer DELETE_NO = 0;
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
    private String productLineName;
    private Integer deleted;
    private Integer activeSum;
    private Integer changeSum;
    private Integer draftSum;
    private Integer closeSum;
    private Integer planCount;
    private Integer releaseCount;
    private Integer bugCount;
    private Integer resolveSum;
    private Integer assignSum;

    public Product() {
        setDeleted(DELETE_NO);
    }

    public String getProductLineName() {
        return productLineName;
    }

    public void setProductLineName(String productLineName) {
        this.productLineName = productLineName;
    }

    public Integer getActiveSum() {
        return activeSum;
    }

    public void setActiveSum(Integer activeSum) {
        this.activeSum = activeSum;
    }

    public Integer getChangeSum() {
        return changeSum;
    }

    public void setChangeSum(Integer changeSum) {
        this.changeSum = changeSum;
    }

    public Integer getDraftSum() {
        return draftSum;
    }

    public void setDraftSum(Integer draftSum) {
        this.draftSum = draftSum;
    }

    public Integer getCloseSum() {
        return closeSum;
    }

    public void setCloseSum(Integer closeSum) {
        this.closeSum = closeSum;
    }

    public Integer getPlanCount() {
        return planCount;
    }

    public void setPlanCount(Integer planCount) {
        this.planCount = planCount;
    }

    public Integer getReleaseCount() {
        return releaseCount;
    }

    public void setReleaseCount(Integer releaseCount) {
        this.releaseCount = releaseCount;
    }

    public Integer getBugCount() {
        return bugCount;
    }

    public void setBugCount(Integer bugCount) {
        this.bugCount = bugCount;
    }

    public Integer getResolveSum() {
        return resolveSum;
    }

    public void setResolveSum(Integer resolveSum) {
        this.resolveSum = resolveSum;
    }

    public Integer getAssignSum() {
        return assignSum;
    }

    public void setAssignSum(Integer assignSum) {
        this.assignSum = assignSum;
    }

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

    @Override
    public boolean equals(Object object) {
        if(this == object){
            return true;
        }
        else if (object != null) {
            if (this.getProductId() == ((Product) object).getProductId()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode(){
        int result = 17;
        return 37*result+productId;
    }

}
