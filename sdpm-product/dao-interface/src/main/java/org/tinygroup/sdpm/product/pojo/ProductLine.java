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

package org.tinygroup.sdpm.product.pojo;

import java.util.Date;

public class ProductLine {

	/** 产品线ID */
	private Integer productLineId;

	/** 公司ID */
	private Integer companyId;

	/** 部门ID */
	private Integer deptId;

	/** 产品线根节点 */
	private Integer productLineRoot;

	/** 产品线父节点 */
	private Integer productLineParent;

	/** 产品线名称 */
	private String productLineName;

	/** 产品线编号 */
	private String productLineCode;

	/** 产品线序号 */
	private Integer productLineOrder;

	/** 产品线描述 */
	private String productLineSpec;

	/** 产品线状态 */
	private String productLineStatus;

	/** 产品经理 */
	private String productOwner;

	/** 质量经理 */
	private String qualityManager;

	/** 交付经理 */
	private String deliveryManager;

	/** 权限模式 */
	private Integer acl;

	/** 产品线白名单 */
	private String productLineWhiteList;

	/** 产品线创建者 */
	private String productLineCreatedBy;

	/** 产品线创建日期 */
	private Date productLineCreatedDate;

	/** 删除标记 */
	private Integer deleted;


	public void setProductLineId(Integer productLineId){
		this. productLineId = productLineId;
	}

	public Integer getProductLineId(){
		return productLineId;
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

	public void setProductLineRoot(Integer productLineRoot){
		this. productLineRoot = productLineRoot;
	}

	public Integer getProductLineRoot(){
		return productLineRoot;
	}

	public void setProductLineParent(Integer productLineParent){
		this. productLineParent = productLineParent;
	}

	public Integer getProductLineParent(){
		return productLineParent;
	}

	public void setProductLineName(String productLineName){
		this. productLineName = productLineName;
	}

	public String getProductLineName(){
		return productLineName;
	}

	public void setProductLineCode(String productLineCode){
		this. productLineCode = productLineCode;
	}

	public String getProductLineCode(){
		return productLineCode;
	}

	public void setProductLineOrder(Integer productLineOrder){
		this. productLineOrder = productLineOrder;
	}

	public Integer getProductLineOrder(){
		return productLineOrder;
	}

	public void setProductLineSpec(String productLineSpec){
		this. productLineSpec = productLineSpec;
	}

	public String getProductLineSpec(){
		return productLineSpec;
	}

	public void setProductLineStatus(String productLineStatus){
		this. productLineStatus = productLineStatus;
	}

	public String getProductLineStatus(){
		return productLineStatus;
	}

	public void setProductOwner(String productOwner){
		this. productOwner = productOwner;
	}

	public String getProductOwner(){
		return productOwner;
	}

	public void setQualityManager(String qualityManager){
		this. qualityManager = qualityManager;
	}

	public String getQualityManager(){
		return qualityManager;
	}

	public void setDeliveryManager(String deliveryManager){
		this. deliveryManager = deliveryManager;
	}

	public String getDeliveryManager(){
		return deliveryManager;
	}

	public void setAcl(Integer acl){
		this. acl = acl;
	}

	public Integer getAcl(){
		return acl;
	}

	public void setProductLineWhiteList(String productLineWhiteList){
		this. productLineWhiteList = productLineWhiteList;
	}

	public String getProductLineWhiteList(){
		return productLineWhiteList;
	}

	public void setProductLineCreatedBy(String productLineCreatedBy){
		this. productLineCreatedBy = productLineCreatedBy;
	}

	public String getProductLineCreatedBy(){
		return productLineCreatedBy;
	}

	public void setProductLineCreatedDate(Date productLineCreatedDate){
		this. productLineCreatedDate = productLineCreatedDate;
	}

	public Date getProductLineCreatedDate(){
		return productLineCreatedDate;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
