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

package org.tinygroup.sdpm.document.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/** 
 * 文档库表
 * 
 * 文档相关的
 */
public class Doclib implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 
	 * 文档库ID
	 * 
	 * 文档库ID
	 */
	private Integer docLibId;

	/** 
	 * 文档库名字
	 * 
	 * 文档库名字
	 */
	private String docLibName;

	/** 
	 * 删除文档库标志
	 * 
	 * 已删除，并不真正删除数据，只是相应比标志位变参而已。
	 */
	private String docLibDeleted;

	/** 
	 * 文档库添加时间
	 * 
	 * 文档库添加时间
	 */
	private Date docLibAddedDate;

	/** 
	 * 文档库名称更新时间
	 * 
	 * 文档库名称更新时间
	 */
	private Date docLibEditedDate;


	public void setDocLibId(Integer docLibId){
		this. docLibId = docLibId;
	}

	public Integer getDocLibId(){
		return docLibId;
	}

	public void setDocLibName(String docLibName){
		this. docLibName = docLibName;
	}

	public String getDocLibName(){
		return docLibName;
	}

	public void setDocLibDeleted(String docLibDeleted){
		this. docLibDeleted = docLibDeleted;
	}

	public String getDocLibDeleted(){
		return docLibDeleted;
	}

	public void setDocLibAddedDate(Date docLibAddedDate){
		this. docLibAddedDate = docLibAddedDate;
	}

	public Date getDocLibAddedDate(){
		return docLibAddedDate;
	}

	public void setDocLibEditedDate(Date docLibEditedDate){
		this. docLibEditedDate = docLibEditedDate;
	}

	public Date getDocLibEditedDate(){
		return docLibEditedDate;
	}

}
