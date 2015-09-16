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

import java.math.BigInteger;
import java.util.Date;

/** 
 * 文档评论表
 * 
 */
public class Comments {

	/** 
	 * 评论编号
	 * 
	 * 评论编号
	 */
	private BigInteger commentId;

	/** 
	 * 被评论文档ID
	 * 
	 * 被评论文档ID
	 */
	private BigInteger commentDocid;

	/** 
	 * 评论者ID
	 * 
	 * 评论者ID
	 */
	private BigInteger commentUserid;

	/** 
	 * 评论正文
	 * 
	 * 评论正文
	 */
	private String commentTxt;

	/** 
	 * 评论时间
	 * 
	 */
	private Date commentTime;


	public void setCommentId(BigInteger commentId){
		this. commentId = commentId;
	}

	public BigInteger getCommentId(){
		return commentId;
	}

	public void setCommentDocid(BigInteger commentDocid){
		this. commentDocid = commentDocid;
	}

	public BigInteger getCommentDocid(){
		return commentDocid;
	}

	public void setCommentUserid(BigInteger commentUserid){
		this. commentUserid = commentUserid;
	}

	public BigInteger getCommentUserid(){
		return commentUserid;
	}

	public void setCommentTxt(String commentTxt){
		this. commentTxt = commentTxt;
	}

	public String getCommentTxt(){
		return commentTxt;
	}

	public void setCommentTime(Date commentTime){
		this. commentTime = commentTime;
	}

	public Date getCommentTime(){
		return commentTime;
	}

}
