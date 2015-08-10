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

package org.tinygroup.sdpm.common.pojo;

import java.util.Date;

public class Extension {

	/** 扩展ID */
	private Integer extensionId;

	/** 公司ID */
	private Integer companyId;

	/** 扩展名称 */
	private String extensionName;

	/** 扩展编号 */
	private String extensionCode;

	/** 扩展版本 */
	private String extensionVersion;

	/** 扩展作者 */
	private String extensionAuthor;

	/** 扩展描述 */
	private String extensionDesc;

	/** 扩展授权 */
	private String extensionLicense;

	/** 扩展类型 */
	private String extensionType;

	/** 扩展网站 */
	private String extensionSite;

	/** 支持系统版本列表 */
	private String extensionSystemversion;

	/** 安装日期 */
	private Date extensionInstalleddate;

	/** 扩展文件 */
	private String extensionFiles;

	/** 状态 */
	private String extensionStatus;


	public void setExtensionId(Integer extensionId){
		this. extensionId = extensionId;
	}

	public Integer getExtensionId(){
		return extensionId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setExtensionName(String extensionName){
		this. extensionName = extensionName;
	}

	public String getExtensionName(){
		return extensionName;
	}

	public void setExtensionCode(String extensionCode){
		this. extensionCode = extensionCode;
	}

	public String getExtensionCode(){
		return extensionCode;
	}

	public void setExtensionVersion(String extensionVersion){
		this. extensionVersion = extensionVersion;
	}

	public String getExtensionVersion(){
		return extensionVersion;
	}

	public void setExtensionAuthor(String extensionAuthor){
		this. extensionAuthor = extensionAuthor;
	}

	public String getExtensionAuthor(){
		return extensionAuthor;
	}

	public void setExtensionDesc(String extensionDesc){
		this. extensionDesc = extensionDesc;
	}

	public String getExtensionDesc(){
		return extensionDesc;
	}

	public void setExtensionLicense(String extensionLicense){
		this. extensionLicense = extensionLicense;
	}

	public String getExtensionLicense(){
		return extensionLicense;
	}

	public void setExtensionType(String extensionType){
		this. extensionType = extensionType;
	}

	public String getExtensionType(){
		return extensionType;
	}

	public void setExtensionSite(String extensionSite){
		this. extensionSite = extensionSite;
	}

	public String getExtensionSite(){
		return extensionSite;
	}

	public void setExtensionSystemversion(String extensionSystemversion){
		this. extensionSystemversion = extensionSystemversion;
	}

	public String getExtensionSystemversion(){
		return extensionSystemversion;
	}

	public void setExtensionInstalleddate(Date extensionInstalleddate){
		this. extensionInstalleddate = extensionInstalleddate;
	}

	public Date getExtensionInstalleddate(){
		return extensionInstalleddate;
	}

	public void setExtensionFiles(String extensionFiles){
		this. extensionFiles = extensionFiles;
	}

	public String getExtensionFiles(){
		return extensionFiles;
	}

	public void setExtensionStatus(String extensionStatus){
		this. extensionStatus = extensionStatus;
	}

	public String getExtensionStatus(){
		return extensionStatus;
	}

}
