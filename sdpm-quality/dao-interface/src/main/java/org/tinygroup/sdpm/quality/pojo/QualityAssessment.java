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

package org.tinygroup.sdpm.quality.pojo;


public class QualityAssessment {

	/** 质量评估ID */
	private Integer qaId;

	/** 公司ID */
	private Integer companyId;

	/** 产品ID */
	private Integer productId;

	/** 模块ID */
	private Integer moduleId;

	/** 评估特性 */
	private String moduleFeature;

	/** 质量评估属性 */
	private Integer qaProperty;

	/** 质量评估值 */
	private Float qaFeatureValue;

	/** 测试充分性 */
	private String qaTestCoverage;

	/** 遗留缺陷影响 */
	private String qaOpenbugImpact;

	/** 评估说明 */
	private String qaComment;

	/** 删除标记 */
	private Integer deleted;


	public void setQaId(Integer qaId){
		this. qaId = qaId;
	}

	public Integer getQaId(){
		return qaId;
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

	public void setModuleId(Integer moduleId){
		this. moduleId = moduleId;
	}

	public Integer getModuleId(){
		return moduleId;
	}

	public void setModuleFeature(String moduleFeature){
		this. moduleFeature = moduleFeature;
	}

	public String getModuleFeature(){
		return moduleFeature;
	}

	public void setQaProperty(Integer qaProperty){
		this. qaProperty = qaProperty;
	}

	public Integer getQaProperty(){
		return qaProperty;
	}

	public void setQaFeatureValue(Float qaFeatureValue){
		this. qaFeatureValue = qaFeatureValue;
	}

	public Float getQaFeatureValue(){
		return qaFeatureValue;
	}

	public void setQaTestCoverage(String qaTestCoverage){
		this. qaTestCoverage = qaTestCoverage;
	}

	public String getQaTestCoverage(){
		return qaTestCoverage;
	}

	public void setQaOpenbugImpact(String qaOpenbugImpact){
		this. qaOpenbugImpact = qaOpenbugImpact;
	}

	public String getQaOpenbugImpact(){
		return qaOpenbugImpact;
	}

	public void setQaComment(String qaComment){
		this. qaComment = qaComment;
	}

	public String getQaComment(){
		return qaComment;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
