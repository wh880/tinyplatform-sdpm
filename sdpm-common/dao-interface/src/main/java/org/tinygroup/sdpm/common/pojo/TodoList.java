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

public class TodoList {

	/** 待办事项ID */
	private Integer todoId;

	/** 公司ID */
	private Integer companyId;

	/** 用户账号 */
	private String account;

	/** 待办日期 */
	private Date todoDate;

	/** 开始时间 */
	private Integer todoBegin;

	/** 结束时间 */
	private Integer todoEnd;

	/** 事项类型 */
	private String todoType;

	/** 事项优先级 */
	private Integer todoPri;

	/** 事项名称 */
	private String todoName;

	/** 事项描述 */
	private String todoDesc;

	/** 事项状态 */
	private String todoStatus;


	public void setTodoId(Integer todoId){
		this. todoId = todoId;
	}

	public Integer getTodoId(){
		return todoId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setAccount(String account){
		this. account = account;
	}

	public String getAccount(){
		return account;
	}

	public void setTodoDate(Date todoDate){
		this. todoDate = todoDate;
	}

	public Date getTodoDate(){
		return todoDate;
	}

	public void setTodoBegin(Integer todoBegin){
		this. todoBegin = todoBegin;
	}

	public Integer getTodoBegin(){
		return todoBegin;
	}

	public void setTodoEnd(Integer todoEnd){
		this. todoEnd = todoEnd;
	}

	public Integer getTodoEnd(){
		return todoEnd;
	}

	public void setTodoType(String todoType){
		this. todoType = todoType;
	}

	public String getTodoType(){
		return todoType;
	}

	public void setTodoPri(Integer todoPri){
		this. todoPri = todoPri;
	}

	public Integer getTodoPri(){
		return todoPri;
	}

	public void setTodoName(String todoName){
		this. todoName = todoName;
	}

	public String getTodoName(){
		return todoName;
	}

	public void setTodoDesc(String todoDesc){
		this. todoDesc = todoDesc;
	}

	public String getTodoDesc(){
		return todoDesc;
	}

	public void setTodoStatus(String todoStatus){
		this. todoStatus = todoStatus;
	}

	public String getTodoStatus(){
		return todoStatus;
	}

}
