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

package org.tinygroup.sdpm.project.dao.pojo;

/** 
 * TASKRELATION
 * 
 */
public class ProjectTaskrelation {

	/**
	 * 逻辑ID
	 */
	private Integer id;

	/**
	 * 前置任务
	 */
	private Integer perTask;

	/**
	 * 前置条件
	 * <p/>
	 * 0-开始后，1-完成后
	 */
	private String taskrelationConditon;

	/**
	 * 后置任务
	 */
	private Integer afterTask;

	/**
	 * 动作
	 * <p/>
	 * 0-才能开始，1-才能完成
	 */
	private String taskrelationAction;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPerTask() {
		return perTask;
	}

	public void setPerTask(Integer perTask) {
		this.perTask = perTask;
	}

	public String getTaskrelationConditon() {
		return taskrelationConditon;
	}

	public void setTaskrelationConditon(String taskrelationConditon) {
		this.taskrelationConditon = taskrelationConditon;
	}

	public Integer getAfterTask() {
		return afterTask;
	}

	public void setAfterTask(Integer afterTask) {
		this.afterTask = afterTask;
	}

	public String getTaskrelationAction() {
		return taskrelationAction;
	}

	public void setTaskrelationAction(String taskrelationAction) {
		this.taskrelationAction = taskrelationAction;
	}

}
