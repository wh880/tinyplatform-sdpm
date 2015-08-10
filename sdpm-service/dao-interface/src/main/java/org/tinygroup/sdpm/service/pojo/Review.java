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

package org.tinygroup.sdpm.service.pojo;

import java.util.Date;

public class Review {

	/** 回访ID */
	private Integer reviewId;

	/** 客户请求ID */
	private Integer requestId;

	/** 回访描述 */
	private String reviewSpec;

	/** 联系人 */
	private String requester;

	/** 回访者 */
	private String reviewer;

	/** 回访时间 */
	private Date reviewDate;

	/** 回访结果 */
	private Integer reviewResult;

	/** 回访评分 */
	private Integer reviewScore;

	/** 回访类型 */
	private Integer reviewType;


	public void setReviewId(Integer reviewId){
		this. reviewId = reviewId;
	}

	public Integer getReviewId(){
		return reviewId;
	}

	public void setRequestId(Integer requestId){
		this. requestId = requestId;
	}

	public Integer getRequestId(){
		return requestId;
	}

	public void setReviewSpec(String reviewSpec){
		this. reviewSpec = reviewSpec;
	}

	public String getReviewSpec(){
		return reviewSpec;
	}

	public void setRequester(String requester){
		this. requester = requester;
	}

	public String getRequester(){
		return requester;
	}

	public void setReviewer(String reviewer){
		this. reviewer = reviewer;
	}

	public String getReviewer(){
		return reviewer;
	}

	public void setReviewDate(Date reviewDate){
		this. reviewDate = reviewDate;
	}

	public Date getReviewDate(){
		return reviewDate;
	}

	public void setReviewResult(Integer reviewResult){
		this. reviewResult = reviewResult;
	}

	public Integer getReviewResult(){
		return reviewResult;
	}

	public void setReviewScore(Integer reviewScore){
		this. reviewScore = reviewScore;
	}

	public Integer getReviewScore(){
		return reviewScore;
	}

	public void setReviewType(Integer reviewType){
		this. reviewType = reviewType;
	}

	public Integer getReviewType(){
		return reviewType;
	}

}
