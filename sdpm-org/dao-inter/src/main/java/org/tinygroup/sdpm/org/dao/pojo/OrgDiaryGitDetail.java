/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/gpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tinygroup.sdpm.org.dao.pojo;

import java.io.Serializable;

/**
 * <!-- begin-user-doc -->
 * ORG_DIARY_GIT_DETAIL
 *
 * <!-- end-user-doc -->
 * @generated
 */
public class OrgDiaryGitDetail implements Serializable {

    /**
     * <!-- begin-user-doc -->
     * ORG_DIARY_GIT_DETAIL_ID
     *
     * <!-- end-user-doc -->
     * @generated
     */
    private Integer orgDiaryGitDetailId;

    /**
     * <!-- begin-user-doc -->
     * 周报id
     *
     * <!-- end-user-doc -->
     * @generated
     */
    private Integer orgDiaryId;

    /**
     * <!-- begin-user-doc -->
     * ORG_GIT_COMMIT_ID
     *
     * <!-- end-user-doc -->
     * @generated
     */
    private String orgGitCommitId;

    public Integer getOrgDiaryGitDetailId() {
        return orgDiaryGitDetailId;
    }

    public void setOrgDiaryGitDetailId(Integer orgDiaryGitDetailId) {
        this.orgDiaryGitDetailId = orgDiaryGitDetailId;
    }

    public Integer getOrgDiaryId() {
        return orgDiaryId;
    }

    public void setOrgDiaryId(Integer orgDiaryId) {
        this.orgDiaryId = orgDiaryId;
    }

    public String getOrgGitCommitId() {
        return orgGitCommitId;
    }

    public void setOrgGitCommitId(String orgGitCommitId) {
        this.orgGitCommitId = orgGitCommitId;
    }

}
