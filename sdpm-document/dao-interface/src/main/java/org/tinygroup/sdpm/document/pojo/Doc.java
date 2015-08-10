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

package org.tinygroup.sdpm.document.pojo;

import java.util.Date;

public class Doc {

	/** 文档ID */
	private Integer docId;

	/** 公司ID */
	private Integer companyId;

	/** 产品ID */
	private Integer productId;

	/** 项目ID */
	private Integer projectId;

	/** 文档库 */
	private String doclibName;

	/** 模块ID */
	private Integer moduleId;

	/** 文档标题 */
	private String docTitle;

	/** 文档摘要 */
	private String docAbstract;

	/** 文档关键字 */
	private String docKeywords;

	/** 文档类型 */
	private String docType;

	/** 文档内容 */
	private String docContent;

	/** 文档超链接 */
	private String docUrl;

	/** 文档查看数 */
	private Integer docViews;

	/** 加入者 */
	private String docAddedBy;

	/** 加入时间 */
	private Date docAddedDate;

	/** 修改者 */
	private String docEditedBy;

	/** 修改时间 */
	private Date docEditedDate;

	/** 文档得分 */
	private Integer docScore;

	/** 文档转发数 */
	private Integer docShare;

	/** 删除标记 */
	private Integer deleted;


	public void setDocId(Integer docId){
		this. docId = docId;
	}

	public Integer getDocId(){
		return docId;
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

	public void setProjectId(Integer projectId){
		this. projectId = projectId;
	}

	public Integer getProjectId(){
		return projectId;
	}

	public void setDoclibName(String doclibName){
		this. doclibName = doclibName;
	}

	public String getDoclibName(){
		return doclibName;
	}

	public void setModuleId(Integer moduleId){
		this. moduleId = moduleId;
	}

	public Integer getModuleId(){
		return moduleId;
	}

	public void setDocTitle(String docTitle){
		this. docTitle = docTitle;
	}

	public String getDocTitle(){
		return docTitle;
	}

	public void setDocAbstract(String docAbstract){
		this. docAbstract = docAbstract;
	}

	public String getDocAbstract(){
		return docAbstract;
	}

	public void setDocKeywords(String docKeywords){
		this. docKeywords = docKeywords;
	}

	public String getDocKeywords(){
		return docKeywords;
	}

	public void setDocType(String docType){
		this. docType = docType;
	}

	public String getDocType(){
		return docType;
	}

	public void setDocContent(String docContent){
		this. docContent = docContent;
	}

	public String getDocContent(){
		return docContent;
	}

	public void setDocUrl(String docUrl){
		this. docUrl = docUrl;
	}

	public String getDocUrl(){
		return docUrl;
	}

	public void setDocViews(Integer docViews){
		this. docViews = docViews;
	}

	public Integer getDocViews(){
		return docViews;
	}

	public void setDocAddedBy(String docAddedBy){
		this. docAddedBy = docAddedBy;
	}

	public String getDocAddedBy(){
		return docAddedBy;
	}

	public void setDocAddedDate(Date docAddedDate){
		this. docAddedDate = docAddedDate;
	}

	public Date getDocAddedDate(){
		return docAddedDate;
	}

	public void setDocEditedBy(String docEditedBy){
		this. docEditedBy = docEditedBy;
	}

	public String getDocEditedBy(){
		return docEditedBy;
	}

	public void setDocEditedDate(Date docEditedDate){
		this. docEditedDate = docEditedDate;
	}

	public Date getDocEditedDate(){
		return docEditedDate;
	}

	public void setDocScore(Integer docScore){
		this. docScore = docScore;
	}

	public Integer getDocScore(){
		return docScore;
	}

	public void setDocShare(Integer docShare){
		this. docShare = docShare;
	}

	public Integer getDocShare(){
		return docShare;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
