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

package org.tinygroup.sdpm.org.dao.pojo;

import java.io.Serializable;
import java.util.Date;

/** 
 * <!-- begin-user-doc -->
 * ORG_GIT_INFO_COMMIT_INFO
 * 
 * <!-- end-user-doc -->
 * @generated
 */
public class OrgGitCommitInfo implements Serializable,Comparable<OrgGitCommitInfo>{

	/** 
	 * <!-- begin-user-doc -->
	 * GIT_INFO_ID
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Integer gitCommitId;

	/** 
	 * <!-- begin-user-doc -->
	 * GIT_COMMIT_TIME
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Date gitCommitTime;

	/** 
	 * <!-- begin-user-doc -->
	 * GIT_REPOSITORY_NAME
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private String gitRepositoryName;

	/** 
	 * <!-- begin-user-doc -->
	 * GIT_AUTHOR_NAME
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private String gitAuthorName;

	/** 
	 * <!-- begin-user-doc -->
	 * GIT_COMMIT_MESSAGE
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private String gitCommitMessage;

	/** 
	 * <!-- begin-user-doc -->
	 * GIT_COMMIT_URL
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private String gitCommitUrl;

	/** 
	 * <!-- begin-user-doc -->
	 * GIT_TYPE
	 * 
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private String gitType;

	/**
	 *week 
	 * 
	 */
	private String week;
	
	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public void setGitCommitId(Integer gitCommitId){
		this. gitCommitId = gitCommitId;
	}

	public Integer getGitCommitId(){
		return gitCommitId;
	}

	public void setGitCommitTime(Date gitCommitTime){
		this. gitCommitTime = gitCommitTime;
	}

	public Date getGitCommitTime(){
		return gitCommitTime;
	}

	public void setGitRepositoryName(String gitRepositoryName){
		this. gitRepositoryName = gitRepositoryName;
	}

	public String getGitRepositoryName(){
		return gitRepositoryName;
	}

	public void setGitAuthorName(String gitAuthorName){
		this. gitAuthorName = gitAuthorName;
	}

	public String getGitAuthorName(){
		return gitAuthorName;
	}

	public void setGitCommitMessage(String gitCommitMessage){
		this. gitCommitMessage = gitCommitMessage;
	}

	public String getGitCommitMessage(){
		return gitCommitMessage;
	}

	public void setGitCommitUrl(String gitCommitUrl){
		this. gitCommitUrl = gitCommitUrl;
	}

	public String getGitCommitUrl(){
		return gitCommitUrl;
	}

	public void setGitType(String gitType){
		this. gitType = gitType;
	}

	public String getGitType(){
		return gitType;
	}

	public OrgGitCommitInfo() {}

	@Override
	public int compareTo(OrgGitCommitInfo o) {
		if(o==null){
			return 0;
		}else{
			return -this.gitCommitTime.compareTo(o.getGitCommitTime());
		}
	}

}
