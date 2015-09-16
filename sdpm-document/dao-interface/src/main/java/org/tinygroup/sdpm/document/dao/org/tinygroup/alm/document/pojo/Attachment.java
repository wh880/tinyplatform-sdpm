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

package org.tinygroup.sdpm.document.dao.org.tinygroup.alm.document.pojo;

/** 
 * 附件表
 * 
 */
public class Attachment {

	/** 
	 * 附件编号
	 * 
	 * 附件编号
	 */
	private Integer attachId;

	/** 
	 * 附件标题
	 * 
	 * 附件标题
	 */
	private String attachName;

	/** 
	 * 附件类型
	 * 
	 * 附件类型
	 */
	private Integer attachType;

	/** 
	 * 附件链接
	 * 
	 * 附件url，路径
	 */
	private String attachUrl;


	public void setAttachId(Integer attachId){
		this. attachId = attachId;
	}

	public Integer getAttachId(){
		return attachId;
	}

	public void setAttachName(String attachName){
		this. attachName = attachName;
	}

	public String getAttachName(){
		return attachName;
	}

	public void setAttachType(Integer attachType){
		this. attachType = attachType;
	}

	public Integer getAttachType(){
		return attachType;
	}

	public void setAttachUrl(String attachUrl){
		this. attachUrl = attachUrl;
	}

	public String getAttachUrl(){
		return attachUrl;
	}

}
