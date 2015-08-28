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

package org.tinygroup.sdpm.project.pojo;

import java.util.Date;

public class Build {

	/** 集成版本ID */
	private Integer buildId;

	/** 公司ID */
	private Integer companyId;

	/** 产品ID */
	private Integer productId;

	/** 项目ID */
	private Integer projectId;

	/** 集成版本名称 */
	private String buildName;

	/** 集成描述 */
	private String buildSpec;

	/** 配置库路径 */
	private String buildScmPath;

	/** 文件路径 */
	private String buildFilePath;

	/** 集成日期 */
	private Date buildDate;

	/** 集成者 */
	private String builder;

	/** 删除标记 */
	private Integer deleted;


	public void setBuildId(Integer buildId){
		this. buildId = buildId;
	}

	public Integer getBuildId(){
		return buildId;
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

	public void setBuildName(String buildName){
		this. buildName = buildName;
	}

	public String getBuildName(){
		return buildName;
	}

	public void setBuildSpec(String buildSpec){
		this. buildSpec = buildSpec;
	}

	public String getBuildSpec(){
		return buildSpec;
	}

	public void setBuildScmPath(String buildScmPath){
		this. buildScmPath = buildScmPath;
	}

	public String getBuildScmPath(){
		return buildScmPath;
	}

	public void setBuildFilePath(String buildFilePath){
		this. buildFilePath = buildFilePath;
	}

	public String getBuildFilePath(){
		return buildFilePath;
	}

	public void setBuildDate(Date buildDate){
		this. buildDate = buildDate;
	}

	public Date getBuildDate(){
		return buildDate;
	}

	public void setBuilder(String builder){
		this. builder = builder;
	}

	public String getBuilder(){
		return builder;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
