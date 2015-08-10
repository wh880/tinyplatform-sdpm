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


public class SysDict {

	/** 字典ID */
	private Integer sysdictId;

	/** 公司ID */
	private Integer companyId;

	/** 信息所有者 */
	private String sysdictOwner;

	/** 所属模块 */
	private String sysdictModule;

	/** 分区 */
	private String sysdictSection;

	/** 字典键 */
	private String sysdictKey;

	/** 字典值 */
	private String sysdictValue;


	public void setSysdictId(Integer sysdictId){
		this. sysdictId = sysdictId;
	}

	public Integer getSysdictId(){
		return sysdictId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setSysdictOwner(String sysdictOwner){
		this. sysdictOwner = sysdictOwner;
	}

	public String getSysdictOwner(){
		return sysdictOwner;
	}

	public void setSysdictModule(String sysdictModule){
		this. sysdictModule = sysdictModule;
	}

	public String getSysdictModule(){
		return sysdictModule;
	}

	public void setSysdictSection(String sysdictSection){
		this. sysdictSection = sysdictSection;
	}

	public String getSysdictSection(){
		return sysdictSection;
	}

	public void setSysdictKey(String sysdictKey){
		this. sysdictKey = sysdictKey;
	}

	public String getSysdictKey(){
		return sysdictKey;
	}

	public void setSysdictValue(String sysdictValue){
		this. sysdictValue = sysdictValue;
	}

	public String getSysdictValue(){
		return sysdictValue;
	}

}
