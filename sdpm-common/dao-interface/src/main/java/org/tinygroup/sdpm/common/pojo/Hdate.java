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

public class Hdate {

	/** 记录ID */
	private Integer hdateId;

	/** 公司ID */
	private Integer companyId;

	/** 日期 */
	private Date hdate;

	/** 节假日名称 */
	private String hdateName;

	/** 节假日描述 */
	private String hdateSpec;


	public void setHdateId(Integer hdateId){
		this. hdateId = hdateId;
	}

	public Integer getHdateId(){
		return hdateId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setHdate(Date hdate){
		this. hdate = hdate;
	}

	public Date getHdate(){
		return hdate;
	}

	public void setHdateName(String hdateName){
		this. hdateName = hdateName;
	}

	public String getHdateName(){
		return hdateName;
	}

	public void setHdateSpec(String hdateSpec){
		this. hdateSpec = hdateSpec;
	}

	public String getHdateSpec(){
		return hdateSpec;
	}

}
