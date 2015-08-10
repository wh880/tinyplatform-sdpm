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

package org.tinygroup.sdpm.project.pojo;

import java.util.Date;

public class TeamMember {

	/** 团队成员ID */
	private Integer teamMemberId;

	/** 项目ID */
	private Integer projectId;

	/** 公司ID */
	private Integer companyId;

	/** 用户账户 */
	private String userAccount;

	/** 角色 */
	private String teamRole;

	/** 加入日期 */
	private Date teamJoin;

	/** 离开日期 */
	private Date teamLeave;

	/** 可用工日 */
	private Integer teamDays;

	/** 可用工时/天 */
	private Float teamHours;


	public void setTeamMemberId(Integer teamMemberId){
		this. teamMemberId = teamMemberId;
	}

	public Integer getTeamMemberId(){
		return teamMemberId;
	}

	public void setProjectId(Integer projectId){
		this. projectId = projectId;
	}

	public Integer getProjectId(){
		return projectId;
	}

	public void setCompanyId(Integer companyId){
		this. companyId = companyId;
	}

	public Integer getCompanyId(){
		return companyId;
	}

	public void setUserAccount(String userAccount){
		this. userAccount = userAccount;
	}

	public String getUserAccount(){
		return userAccount;
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

	public void setTeamLeave(Date teamLeave){
		this. teamLeave = teamLeave;
	}

	public Date getTeamLeave(){
		return teamLeave;
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
