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

package org.tinygroup.sdpm.system.dao.pojo;

import java.io.Serializable;


/** 
 * 数据字典
 * 
 */
public class SystemDict implements Serializable{
	public static Integer DELETE_YES = 1;
	public static Integer DELETE_NO = 0;

	/** 
	 * 字典项ID
	 * 
	 */
	private Integer dictId;

	/** 
	 * 字典key值
	 * 
	 */
	private String dictKey;

	/** 
	 * 字典value值
	 * 
	 */
	private String dictValue;

	/** 
	 * 字典项序号
	 * 
	 */
	private Integer dictSort;

	/** 
	 * 模块ID
	 * 
	 */
	private Integer moduleId;

	/** 
	 * 已删除
	 * 
	 */
	private Integer deleted;


	public void setDictId(Integer dictId){
		this. dictId = dictId;
	}

	public Integer getDictId(){
		return dictId;
	}

	public void setDictKey(String dictKey){
		this. dictKey = dictKey;
	}

	public String getDictKey(){
		return dictKey;
	}

	public void setDictValue(String dictValue){
		this. dictValue = dictValue;
	}

	public String getDictValue(){
		return dictValue;
	}

	public void setDictSort(Integer dictSort){
		this. dictSort = dictSort;
	}

	public Integer getDictSort(){
		return dictSort;
	}

	public void setModuleId(Integer moduleId){
		this. moduleId = moduleId;
	}

	public Integer getModuleId(){
		return moduleId;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
