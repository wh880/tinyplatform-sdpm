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

package org.tinygroup.sdpm.docment.pojo;

import java.util.Date;

/** 
 * 文档表
 * 
 */
public class Doc {

	/** 
	 * 文档ID
	 * 
	 * 文档ID，主键，唯一标示
	 */
	private Integer docId;

	/** 
	 * 所属产品
	 * 
	 * 所属产品
	 */
	private Integer docProduct;

	/** 
	 * 所属项目
	 * 
	 * 所属项目
	 */
	private Integer docProject;

	/** 
	 * 所属文档库
	 * 
	 * 所属文档库
	 */
	private String docLib;

	/** 
	 * 所属分类
	 * 
	 * 所属分类
	 */
	private String docModule;

	/** 
	 * 文档标题
	 * 
	 * 文档标题
	 */
	private String docTitle;

	/** 
	 * 摘要
	 * 
	 * 摘要
	 */
	private String docDigest;

	/** 
	 * 关键字
	 * 
	 * 关键字
	 */
	private String docKeywords;

	/** 
	 * 文档类型
	 * 
	 * 文档类型
	 */
	private String docType;

	/** 
	 * 文档正文
	 * 
	 * 文档正文
	 */
	private String docContent;

	/** 
	 * 文档url
	 * 
	 * 文档url
	 */
	private String docUrl;

	/** 
	 * 附件链接
	 * 
	 * 附件url，路径
	 */
	private String attachUrl;

	/** 
	 * DOC查阅次数
	 * 
	 * 查阅次数
	 */
	private Integer docViews;

	/** 
	 * DOC由谁添加
	 * 
	 * 由谁添加
	 */
	private String docAddedBy;

	/** 
	 * DOC添加时间
	 * 
	 * 添加时间
	 */
	private Date docAddedDate;

	/** 
	 * 由谁编辑
	 * 
	 * 由谁编辑
	 */
	private String docEditedBy;

	/** 
	 * 文档编辑时间
	 * 
	 * 编辑时间
	 */
	private Date docEditedDate;

	/** 
	 * 文档删除标志
	 * 
	 * 已删除
	 */
	private String docDeleted;


	public void setDocId(Integer docId){
		this. docId = docId;
	}

	public Integer getDocId(){
		return docId;
	}

	public void setDocProduct(Integer docProduct){
		this. docProduct = docProduct;
	}

	public Integer getDocProduct(){
		return docProduct;
	}

	public void setDocProject(Integer docProject){
		this. docProject = docProject;
	}

	public Integer getDocProject(){
		return docProject;
	}

	public void setDocLib(String docLib){
		this. docLib = docLib;
	}

	public String getDocLib(){
		return docLib;
	}

	public void setDocModule(String docModule){
		this. docModule = docModule;
	}

	public String getDocModule(){
		return docModule;
	}

	public void setDocTitle(String docTitle){
		this. docTitle = docTitle;
	}

	public String getDocTitle(){
		return docTitle;
	}

	public void setDocDigest(String docDigest){
		this. docDigest = docDigest;
	}

	public String getDocDigest(){
		return docDigest;
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

	public void setAttachUrl(String attachUrl){
		this. attachUrl = attachUrl;
	}

	public String getAttachUrl(){
		return attachUrl;
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

	public void setDocDeleted(String docDeleted){
		this. docDeleted = docDeleted;
	}

	public String getDocDeleted(){
		return docDeleted;
	}

}
