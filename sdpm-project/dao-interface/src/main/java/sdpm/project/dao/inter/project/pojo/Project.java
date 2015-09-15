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

package sdpm.project.dao.inter.project.pojo;

import java.util.Date;

public class Project {

	/** 项目id */
	private Integer projectId;

	/** PROJECT_ISCAT */
	private String projectIsCat;

	/** PROJECT_ */
	private Integer projectCatId;

	/** 项目类型 */
	private String projectType;

	/** 项目名称PROJECT_NAME */
	private String projectName;

	/** 项目代号 */
	private String projectCode;

	/** 项目开始日期 */
	private Date projectBegin;

	/** 项目结束日期 */
	private Date projectEnd;

	/** 可用工作日 */
	private Integer projectDays;

	/** 项目状态 */
	private String projectStatus;

	/** 优先级 */
	private String projectPri;

	/** 项目描述 */
	private String projectDesc;

	/** 由谁创建 */
	private String projectOpenedBy;

	/** 创建日期 */
	private Date projectOpenedDate;

	/** 项目创建版本 */
	private String projectOpenedVersion;

	/** 关闭人 */
	private String slaClosedBy;

	/** 关闭时间 */
	private Date slaCloseDate;

	/** 项目由谁取消 */
	private String projectCanceledBy;

	/** 项目取消日期 */
	private Date projectCanceledDate;

	/** 产品负责人 */
	private String projectPO;

	/** 项目负责人 */
	private String projectPM;

	/** 测试负责人 */
	private String projectQD;

	/** 项目发布负责人 */
	private String projectRD;

	/** 团队成员 */
	private String projectTeam;

	/** 访问控制 */
	private String projectAcl;

	/** 分组白名单 */
	private String projectWhiteList;

	/** 项目排序 */
	private Integer projectOrder;

	/** 已删除 */
	private String projectDeleted;


	public void setProjectId(Integer projectId){
		this. projectId = projectId;
	}

	public Integer getProjectId(){
		return projectId;
	}

	public void setProjectIsCat(String projectIsCat){
		this. projectIsCat = projectIsCat;
	}

	public String getProjectIsCat(){
		return projectIsCat;
	}

	public void setProjectCatId(Integer projectCatId){
		this. projectCatId = projectCatId;
	}

	public Integer getProjectCatId(){
		return projectCatId;
	}

	public void setProjectType(String projectType){
		this. projectType = projectType;
	}

	public String getProjectType(){
		return projectType;
	}

	public void setProjectName(String projectName){
		this. projectName = projectName;
	}

	public String getProjectName(){
		return projectName;
	}

	public void setProjectCode(String projectCode){
		this. projectCode = projectCode;
	}

	public String getProjectCode(){
		return projectCode;
	}

	public void setProjectBegin(Date projectBegin){
		this. projectBegin = projectBegin;
	}

	public Date getProjectBegin(){
		return projectBegin;
	}

	public void setProjectEnd(Date projectEnd){
		this. projectEnd = projectEnd;
	}

	public Date getProjectEnd(){
		return projectEnd;
	}

	public void setProjectDays(Integer projectDays){
		this. projectDays = projectDays;
	}

	public Integer getProjectDays(){
		return projectDays;
	}

	public void setProjectStatus(String projectStatus){
		this. projectStatus = projectStatus;
	}

	public String getProjectStatus(){
		return projectStatus;
	}

	public void setProjectPri(String projectPri){
		this. projectPri = projectPri;
	}

	public String getProjectPri(){
		return projectPri;
	}

	public void setProjectDesc(String projectDesc){
		this. projectDesc = projectDesc;
	}

	public String getProjectDesc(){
		return projectDesc;
	}

	public void setProjectOpenedBy(String projectOpenedBy){
		this. projectOpenedBy = projectOpenedBy;
	}

	public String getProjectOpenedBy(){
		return projectOpenedBy;
	}

	public void setProjectOpenedDate(Date projectOpenedDate){
		this. projectOpenedDate = projectOpenedDate;
	}

	public Date getProjectOpenedDate(){
		return projectOpenedDate;
	}

	public void setProjectOpenedVersion(String projectOpenedVersion){
		this. projectOpenedVersion = projectOpenedVersion;
	}

	public String getProjectOpenedVersion(){
		return projectOpenedVersion;
	}

	public void setSlaClosedBy(String slaClosedBy){
		this. slaClosedBy = slaClosedBy;
	}

	public String getSlaClosedBy(){
		return slaClosedBy;
	}

	public void setSlaCloseDate(Date slaCloseDate){
		this. slaCloseDate = slaCloseDate;
	}

	public Date getSlaCloseDate(){
		return slaCloseDate;
	}

	public void setProjectCanceledBy(String projectCanceledBy){
		this. projectCanceledBy = projectCanceledBy;
	}

	public String getProjectCanceledBy(){
		return projectCanceledBy;
	}

	public void setProjectCanceledDate(Date projectCanceledDate){
		this. projectCanceledDate = projectCanceledDate;
	}

	public Date getProjectCanceledDate(){
		return projectCanceledDate;
	}

	public void setProjectPO(String projectPO){
		this. projectPO = projectPO;
	}

	public String getProjectPO(){
		return projectPO;
	}

	public void setProjectPM(String projectPM){
		this. projectPM = projectPM;
	}

	public String getProjectPM(){
		return projectPM;
	}

	public void setProjectQD(String projectQD){
		this. projectQD = projectQD;
	}

	public String getProjectQD(){
		return projectQD;
	}

	public void setProjectRD(String projectRD){
		this. projectRD = projectRD;
	}

	public String getProjectRD(){
		return projectRD;
	}

	public void setProjectTeam(String projectTeam){
		this. projectTeam = projectTeam;
	}

	public String getProjectTeam(){
		return projectTeam;
	}

	public void setProjectAcl(String projectAcl){
		this. projectAcl = projectAcl;
	}

	public String getProjectAcl(){
		return projectAcl;
	}

	public void setProjectWhiteList(String projectWhiteList){
		this. projectWhiteList = projectWhiteList;
	}

	public String getProjectWhiteList(){
		return projectWhiteList;
	}

	public void setProjectOrder(Integer projectOrder){
		this. projectOrder = projectOrder;
	}

	public Integer getProjectOrder(){
		return projectOrder;
	}

	public void setProjectDeleted(String projectDeleted){
		this. projectDeleted = projectDeleted;
	}

	public String getProjectDeleted(){
		return projectDeleted;
	}

}
