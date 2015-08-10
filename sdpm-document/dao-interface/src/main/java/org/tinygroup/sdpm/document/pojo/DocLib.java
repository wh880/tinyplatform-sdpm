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


public class DocLib {

	/** 文档库ID */
	private Integer doclibId;

	/** 公司ID */
	private Integer companyId;

	/** 文档库 */
	private String doclibName;

	/** 删除标记 */
	private Integer deleted;


	public void setDoclibId(Integer doclibId){
		this. doclibId = doclibId;
	}

	public Integer getDoclibId(){
		return doclibId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setDoclibName(String doclibName){
		this. doclibName = doclibName;
	}

	public String getDoclibName(){
		return doclibName;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
