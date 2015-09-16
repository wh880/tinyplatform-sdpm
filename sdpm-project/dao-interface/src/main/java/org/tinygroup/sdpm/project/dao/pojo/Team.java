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

import java.util.Date;

/** 
 * 团队
 * 
 */
public class Team {

	/** 
	 * 逻辑ID
	 * 
	 */
	private Integer id;

	/** 
	 * 项目id
	 * 
	 */
	private Integer projectId;

	/** 
	 * 用户
	 * 
	 */
	private String teamAccount;

	/** 
	 * 角色
	 * 
	 */
	private String teamRole;

	/** 
	 * 加盟日
	 * 
	 */
	private Date teamJoin;

	/** 
	 * 可用工时
	 * 
	 */
	private Integer teamDays;

	/** 
	 * 可用工时
	 * 
	 */
	private Float teamHours;


	public void setId(Integer id){
		this. id = id;
	}

	public Integer getId(){
		return id;
	}

	public void setProjectId(Integer projectId){
		this. projectId = projectId;
	}

	public Integer getProjectId(){
		return projectId;
	}

	public void setTeamAccount(String teamAccount){
		this. teamAccount = teamAccount;
	}

	public String getTeamAccount(){
		return teamAccount;
	}

	public void setTeamRole(String teamRole){
		this. teamRole = teamRole;
	}

	public String getTeamRole(){
		return teamRole;
	}

	public void setTeamJoin(Date teamJoin){
		this. teamJoin = teamJoin;
	}

	public Date getTeamJoin(){
		return teamJoin;
	}

	public void setTeamDays(Integer teamDays){
		this. teamDays = teamDays;
	}

	public Integer getTeamDays(){
		return teamDays;
	}

	public void setTeamHours(Float teamHours){
		this. teamHours = teamHours;
	}

	public Float getTeamHours(){
		return teamHours;
	}

}
