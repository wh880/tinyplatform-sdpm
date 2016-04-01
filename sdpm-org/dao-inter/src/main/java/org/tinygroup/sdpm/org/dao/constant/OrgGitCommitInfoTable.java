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

package org.tinygroup.sdpm.org.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * <!-- begin-user-doc -->
 * ORG_GIT_INFO_COMMIT_INFO
 *
 * <!-- end-user-doc -->
 * @generated
 */
public class OrgGitCommitInfoTable extends Table {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final OrgGitCommitInfoTable ORG_GIT_COMMIT_INFO_TABLE = new OrgGitCommitInfoTable();

    /**
     * <!-- begin-user-doc -->
     * ORG_GIT_COMMIT_ID
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_GIT_COMMIT_ID = new Column(this, "org_git_commit_id");

    /**
     * <!-- begin-user-doc -->
     * GIT_COMMIT_TIME
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_GIT_COMMIT_TIME = new Column(this, "org_git_commit_time");

    /**
     * <!-- begin-user-doc -->
     * GIT_REPOSITORY_NAME
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_GIT_REPOSITORY_NAME = new Column(this, "org_git_repository_name");

    /**
     * <!-- begin-user-doc -->
     * GIT_AUTHOR_ID
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_GIT_AUTHOR_EMAIL = new Column(this, "org_git_author_email");

    /**
     * <!-- begin-user-doc -->
     * GIT_COMMIT_MESSAGE
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_GIT_COMMIT_MESSAGE = new Column(this, "org_git_commit_message");

    /**
     * <!-- begin-user-doc -->
     * GIT_COMMIT_URL
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_GIT_COMMIT_URL = new Column(this, "org_git_commit_url");

    /**
     * <!-- begin-user-doc -->
     * GIT_TYPE
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_GIT_TYPE = new Column(this, "org_git_type");


    public OrgGitCommitInfoTable() {
        super("org_git_commit_info");
    }

}
