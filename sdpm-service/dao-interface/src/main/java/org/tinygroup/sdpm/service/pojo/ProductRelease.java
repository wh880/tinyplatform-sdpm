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

package org.tinygroup.sdpm.service.pojo;

import java.util.Date;

public class ProductRelease {

	/** 需求发布ID */
	private Integer releaseId;

	/** 公司ID */
	private Integer companyId;

	/** 产品ID */
	private Integer productId;

	/** 发放版本编号 */
	private String releaseName;

	/** 发布版本类型 */
	private Integer releaseType;

	/** 发布计划ID */
	private Integer planId;

	/** 发布对象ACCOUNT */
	private String releaseMailTo;

	/** 发布抄送 */
	private String releaseMailCC;

	/** 发布秘送 */
	private String releaseMailBC;

	/** 发布内容 */
	private String releaseContent;

	/** 关联用户需求单 */
	private String releaseRequest;

	/** 创建人 */
	private String releaseCreateBy;

	/** 创建时间 */
	private Date releaseCreateDate;

	/** RELEASE_PUBLISHER */
	private String releasePublisher;

	/** RELEASE_PUBLISH_DATE */
	private Date releasePublishDate;

	/** 是否需要现场测试 */
	private Integer releaseSpotTest;

	/** 发布包存放路径 */
	private String releasePath;

	/** 删除标记 */
	private Integer deleted;


	public void setReleaseId(Integer releaseId){
		this. releaseId = releaseId;
	}

	public Integer getReleaseId(){
		return releaseId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setProductId(Integer productId){
		this. productId = productId;
	}

	public Integer getProductId(){
		return productId;
	}

	public void setReleaseName(String releaseName){
		this. releaseName = releaseName;
	}

	public String getReleaseName(){
		return releaseName;
	}

	public void setReleaseType(Integer releaseType){
		this. releaseType = releaseType;
	}

	public Integer getReleaseType(){
		return releaseType;
	}

	public void setPlanId(Integer planId){
		this. planId = planId;
	}

	public Integer getPlanId(){
		return planId;
	}

	public void setReleaseMailTo(String releaseMailTo){
		this. releaseMailTo = releaseMailTo;
	}

	public String getReleaseMailTo(){
		return releaseMailTo;
	}

	public void setReleaseMailCC(String releaseMailCC){
		this. releaseMailCC = releaseMailCC;
	}

	public String getReleaseMailCC(){
		return releaseMailCC;
	}

	public void setReleaseMailBC(String releaseMailBC){
		this. releaseMailBC = releaseMailBC;
	}

	public String getReleaseMailBC(){
		return releaseMailBC;
	}

	public void setReleaseContent(String releaseContent){
		this. releaseContent = releaseContent;
	}

	public String getReleaseContent(){
		return releaseContent;
	}

	public void setReleaseRequest(String releaseRequest){
		this. releaseRequest = releaseRequest;
	}

	public String getReleaseRequest(){
		return releaseRequest;
	}

	public void setReleaseCreateBy(String releaseCreateBy){
		this. releaseCreateBy = releaseCreateBy;
	}

	public String getReleaseCreateBy(){
		return releaseCreateBy;
	}

	public void setReleaseCreateDate(Date releaseCreateDate){
		this. releaseCreateDate = releaseCreateDate;
	}

	public Date getReleaseCreateDate(){
		return releaseCreateDate;
	}

	public void setReleasePublisher(String releasePublisher){
		this. releasePublisher = releasePublisher;
	}

	public String getReleasePublisher(){
		return releasePublisher;
	}

	public void setReleasePublishDate(Date releasePublishDate){
		this. releasePublishDate = releasePublishDate;
	}

	public Date getReleasePublishDate(){
		return releasePublishDate;
	}

	public void setReleaseSpotTest(Integer releaseSpotTest){
		this. releaseSpotTest = releaseSpotTest;
	}

	public Integer getReleaseSpotTest(){
		return releaseSpotTest;
	}

	public void setReleasePath(String releasePath){
		this. releasePath = releasePath;
	}

	public String getReleasePath(){
		return releasePath;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
