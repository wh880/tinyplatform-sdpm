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
 * <!-- begin-user-doc --> ORG_GIT_INFO_COMMIT_INFO
 * 
 * <!-- end-user-doc -->
 * 
 * @generated
 */
public class OrgGitCommitInfo implements Serializable, Comparable<OrgGitCommitInfo> {

	/**
	 * <!-- begin-user-doc --> ORG_GIT_COMMIT_ID
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private String orgGitCommitId;

	/**
	 * <!-- begin-user-doc --> GIT_COMMIT_TIME
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private Date orgGitCommitTime;

	/**
	 * <!-- begin-user-doc --> GIT_REPOSITORY_NAME
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private String orgGitRepositoryName;

	/**
	 * <!-- begin-user-doc --> GIT_AUTHOR_ID
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private String orgGitAuthorId;

	/**
	 * <!-- begin-user-doc --> GIT_COMMIT_MESSAGE
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private String orgGitCommitMessage;

	/**
	 * <!-- begin-user-doc --> GIT_COMMIT_URL
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private String orgGitCommitUrl;

	/**
	 * <!-- begin-user-doc --> GIT_TYPE
	 * 
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private String orgGitType;
	/**
	 * 获取当前日期
	 */
	private String week;
	/**
	 * 超链接的文本
	 */
	private String urlText;
	
	public String getUrlText() {
		return urlText;
	}

	public void setUrlText(String urlText) {
		this.urlText = urlText;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public void setOrgGitCommitId(String orgGitCommitId) {
		this.orgGitCommitId = orgGitCommitId;
	}

	public String getOrgGitCommitId() {
		return orgGitCommitId;
	}

	public void setOrgGitCommitTime(Date orgGitCommitTime) {
		this.orgGitCommitTime = orgGitCommitTime;
	}

	public Date getOrgGitCommitTime() {
		return orgGitCommitTime;
	}

	public void setOrgGitRepositoryName(String orgGitRepositoryName) {
		this.orgGitRepositoryName = orgGitRepositoryName;
	}

	public String getOrgGitRepositoryName() {
		return orgGitRepositoryName;
	}

	public void setOrgGitAuthorId(String orgGitAuthorId) {
		this.orgGitAuthorId = orgGitAuthorId;
	}

	public String getOrgGitAuthorId() {
		return orgGitAuthorId;
	}

	public void setOrgGitCommitMessage(String orgGitCommitMessage) {
		this.orgGitCommitMessage = orgGitCommitMessage;
	}

	public String getOrgGitCommitMessage() {
		return orgGitCommitMessage;
	}

	public void setOrgGitCommitUrl(String orgGitCommitUrl) {
		this.orgGitCommitUrl = orgGitCommitUrl;
	}

	public String getOrgGitCommitUrl() {
		return orgGitCommitUrl;
	}

	public void setOrgGitType(String orgGitType) {
		this.orgGitType = orgGitType;
	}

	public String getOrgGitType() {
		return orgGitType;
	}

	@Override
	public int compareTo(OrgGitCommitInfo o) {
		if (o == null) {
			return 0;
		}else{
			return o.getOrgGitCommitTime().compareTo(this.getOrgGitCommitTime());
		}
	}

}
