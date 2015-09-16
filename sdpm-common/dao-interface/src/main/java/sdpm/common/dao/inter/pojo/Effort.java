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

package sdpm.common.dao.inter.pojo;

import java.util.Date;

public class Effort {

	/** 日志编号 */
	private Integer effortId;

	/** 日志对象 */
	private String effortObjectType;

	/** 对象ID */
	private Integer effortObjectID;

	/** 日志下的对应的产品 */
	private String effortProduct;

	/** 日志下的项目对象 */
	private Integer effortProject;

	/** 登记人 */
	private String effortAccount;

	/** 工作内容 */
	private String effortWork;

	/** 日期 */
	private Date effortDate;

	/** 剩余 */
	private Float effortLeft;

	/** 耗时 */
	private Float effortConsumed;

	/** 开始 */
	private Integer effortBegin;

	/** 已关闭 */
	private Integer effortEnd;


	public void setEffortId(Integer effortId){
		this. effortId = effortId;
	}

	public Integer getEffortId(){
		return effortId;
	}

	public void setEffortObjectType(String effortObjectType){
		this. effortObjectType = effortObjectType;
	}

	public String getEffortObjectType(){
		return effortObjectType;
	}

	public void setEffortObjectID(Integer effortObjectID){
		this. effortObjectID = effortObjectID;
	}

	public Integer getEffortObjectID(){
		return effortObjectID;
	}

	public void setEffortProduct(String effortProduct){
		this. effortProduct = effortProduct;
	}

	public String getEffortProduct(){
		return effortProduct;
	}

	public void setEffortProject(Integer effortProject){
		this. effortProject = effortProject;
	}

	public Integer getEffortProject(){
		return effortProject;
	}

	public void setEffortAccount(String effortAccount){
		this. effortAccount = effortAccount;
	}

	public String getEffortAccount(){
		return effortAccount;
	}

	public void setEffortWork(String effortWork){
		this. effortWork = effortWork;
	}

	public String getEffortWork(){
		return effortWork;
	}

	public void setEffortDate(Date effortDate){
		this. effortDate = effortDate;
	}

	public Date getEffortDate(){
		return effortDate;
	}

	public void setEffortLeft(Float effortLeft){
		this. effortLeft = effortLeft;
	}

	public Float getEffortLeft(){
		return effortLeft;
	}

	public void setEffortConsumed(Float effortConsumed){
		this. effortConsumed = effortConsumed;
	}

	public Float getEffortConsumed(){
		return effortConsumed;
	}

	public void setEffortBegin(Integer effortBegin){
		this. effortBegin = effortBegin;
	}

	public Integer getEffortBegin(){
		return effortBegin;
	}

	public void setEffortEnd(Integer effortEnd){
		this. effortEnd = effortEnd;
	}

	public Integer getEffortEnd(){
		return effortEnd;
	}

}
