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

public class Attachment {

	/** 附件ID */
	private Integer fileId;

	/** 公司ID */
	private Integer companyId;

	/** 附件路径 */
	private String filePathname;

	/** 附件标题 */
	private String fileTitle;

	/** 附件扩展名 */
	private String fileExtension;

	/** 附件大小 */
	private Integer fileSize;

	/** 附件对象类型 */
	private String fileObjectType;

	/** 附件对象ID */
	private Integer fileObjectId;

	/** 附件加入者 */
	private String fileAddedBy;

	/** 附件加入时间 */
	private Date fileAddedDate;

	/** 附件下载数 */
	private Integer fileDownloads;

	/** 扩展字段 */
	private String fileExtra;

	/** 删除标记 */
	private Integer deleted;


	public void setFileId(Integer fileId){
		this. fileId = fileId;
	}

	public Integer getFileId(){
		return fileId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setFilePathname(String filePathname){
		this. filePathname = filePathname;
	}

	public String getFilePathname(){
		return filePathname;
	}

	public void setFileTitle(String fileTitle){
		this. fileTitle = fileTitle;
	}

	public String getFileTitle(){
		return fileTitle;
	}

	public void setFileExtension(String fileExtension){
		this. fileExtension = fileExtension;
	}

	public String getFileExtension(){
		return fileExtension;
	}

	public void setFileSize(Integer fileSize){
		this. fileSize = fileSize;
	}

	public Integer getFileSize(){
		return fileSize;
	}

	public void setFileObjectType(String fileObjectType){
		this. fileObjectType = fileObjectType;
	}

	public String getFileObjectType(){
		return fileObjectType;
	}

	public void setFileObjectId(Integer fileObjectId){
		this. fileObjectId = fileObjectId;
	}

	public Integer getFileObjectId(){
		return fileObjectId;
	}

	public void setFileAddedBy(String fileAddedBy){
		this. fileAddedBy = fileAddedBy;
	}

	public String getFileAddedBy(){
		return fileAddedBy;
	}

	public void setFileAddedDate(Date fileAddedDate){
		this. fileAddedDate = fileAddedDate;
	}

	public Date getFileAddedDate(){
		return fileAddedDate;
	}

	public void setFileDownloads(Integer fileDownloads){
		this. fileDownloads = fileDownloads;
	}

	public Integer getFileDownloads(){
		return fileDownloads;
	}

	public void setFileExtra(String fileExtra){
		this. fileExtra = fileExtra;
	}

	public String getFileExtra(){
		return fileExtra;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
