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

public class Comments {

	/** 评论ID */
	private Integer commentId;

	/** 公司ID */
	private Integer companyId;

	/** 文档ID */
	private Integer docId;

	/** PARENT_ID */
	private Integer parentId;

	/** 评论内容 */
	private String commentText;

	/** 评论者 */
	private String commentAddedBy;

	/** 评论时间 */
	private Date commentAddedDate;

	/** 删除标记 */
	private Integer deleted;


	public void setCommentId(Integer commentId){
		this. commentId = commentId;
	}

	public Integer getCommentId(){
		return commentId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setDocId(Integer docId){
		this. docId = docId;
	}

	public Integer getDocId(){
		return docId;
	}

	public void setParentId(Integer parentId){
		this. parentId = parentId;
	}

	public Integer getParentId(){
		return parentId;
	}

	public void setCommentText(String commentText){
		this. commentText = commentText;
	}

	public String getCommentText(){
		return commentText;
	}

	public void setCommentAddedBy(String commentAddedBy){
		this. commentAddedBy = commentAddedBy;
	}

	public String getCommentAddedBy(){
		return commentAddedBy;
	}

	public void setCommentAddedDate(Date commentAddedDate){
		this. commentAddedDate = commentAddedDate;
	}

	public Date getCommentAddedDate(){
		return commentAddedDate;
	}

	public void setDeleted(Integer deleted){
		this. deleted = deleted;
	}

	public Integer getDeleted(){
		return deleted;
	}

}
