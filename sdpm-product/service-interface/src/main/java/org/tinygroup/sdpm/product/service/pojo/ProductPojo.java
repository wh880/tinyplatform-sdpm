/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.tinygroup.sdpm.product.service.pojo;

import java.util.Date;

/** 
 * 产品表
 * 
 */
public class ProductPojo {

	/** 
	 * 产品ID
	 * 
	 */
	private Integer productId;

	/** 
	 * 公司ID
	 * 
	 */
	private Integer companyId;

	/** 
	 * 部门ID
	 * 
	 */
	private Integer deptId;

	/** 
	 * 产品线ID
	 * 
	 */
	private Integer productLineId;

	/** 
	 * 产品名称
	 * 
	 */
	private String productName;

	/** 
	 * 产品代号
	 * 
	 */
	private String productCode;

	/** 
	 * 产品序号
	 * 
	 */
	private Integer productOrder;

	/** 
	 * 产品状态
	 * 
	 */
	private String productStatus;

	/** 
	 * 产品描述
	 * 
	 */
	private String productDesc;

	/** 
	 * 产品经理
	 * 
	 */
	private String productOwner;

	/** 
	 * 产品质量经理
	 * 
	 */
	private String productQualityManager;

	/** 
	 * 产品交付经理
	 * 
	 */
	private String productDeliveryManager;

	/** 
	 * 权限模式
	 * 
	 * 本部门范围：0-open公开；1-custom自定义（产品/项目团队和白名单可访问）；2-private私有（产品/项目团队成员才可访问）
	 */
	private Integer acl;

	/** 
	 * 白名单
	 * 
	 */
	private String productWhiteList;

	/** 
	 * 创建者
	 * 
	 */
	private String productCreatedBy;

	/** 
	 * 创建日期
	 * 
	 */
	private Date productCreatedDate;

	/** 
	 * 创建版本
	 * 
	 */
	private String productCreatedVersion;

	/** 
	 * 已删除
	 * 
	 */
	private Integer deleted;


	public void setProductId(Integer productId){
		this. productId = productId;
	}

	public Integer getProductId(){
		return productId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setDeptId(Integer deptId){
		this. deptId = deptId;
	}

	public Integer getDeptId(){
		return deptId;
	}

	public void setProductLineId(Integer productLineId){
		this. productLineId = productLineId;
	}

	public Integer getProductLineId(){
		return productLineId;
	}

	public void setProductName(String productName){
		this. productName = productName;
	}

	public String getProductName(){
		return productName;
	}

	public void setProductCode(String productCode){
		this. productCode = productCode;
	}

	public String getProductCode(){
		return productCode;
	}

	public void setProductOrder(Integer productOrder){
		this. productOrder = productOrder;
	}

	public Integer getProductOrder(){
		return productOrder;
	}

	public void setProductStatus(String productStatus){
		this. productStatus = productStatus;
	}

	public String getProductStatus(){
		return productStatus;
	}

	public void setProductDesc(String productDesc){
		this. productDesc = productDesc;
	}

	public String getProductDesc(){
		return productDesc;
	}

	public void setProductOwner(String productOwner){
		this. productOwner = productOwner;
	}

	public String getProductOwner(){
		return productOwner;
	}

	public void setProductQualityManager(String productQualityManager){
		this. productQualityManager = productQualityManager;
	}

	public String getProductQualityManager(){
		return productQualityManager;
	}

	public void setProductDeliveryManager(String productDeliveryManager){
		this. productDeliveryManager = productDeliveryManager;
	}

	public String getProductDeliveryManager(){
		return productDeliveryManager;
	}

	public void setAcl(Integer acl){
		this. acl = acl;
	}

	public Integer getAcl(){
		return acl;
	}

	public void setProductWhiteList(String productWhiteList){
		this. productWhiteList = productWhiteList;
	}

	public String getProductWhiteList(){
		return productWhiteList;
	}

	public void setProductCreatedBy(String productCreatedBy){
		this. productCreatedBy = productCreatedBy;
	}

	public String getProductCreatedBy(){
		return productCreatedBy;
	}

	public void setProductCreatedDate(Date productCreatedDate){
		this. productCreatedDate = productCreatedDate;
	}

	public Date getProductCreatedDate(){
		return productCreatedDate;
	}

	public void setProductCreatedVersion(String productCreatedVersion){
		this. productCreatedVersion = productCreatedVersion;
	}

	public String getProductCreatedVersion(){
		return productCreatedVersion;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
