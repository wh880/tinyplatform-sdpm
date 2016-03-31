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
 * ORG_DIARY_GIT_DETAIL
 *
 * <!-- end-user-doc -->
 * @generated
 */
public class OrgDiaryGitDetailTable extends Table {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final OrgDiaryGitDetailTable ORG_DIARY_GIT_DETAIL_TABLE = new OrgDiaryGitDetailTable();

    /**
     * <!-- begin-user-doc -->
     * ORG_DIARY_GIT_DETAIL_ID
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_DIARY_GIT_DETAIL_ID = new Column(this, "org_diary_git_detail_id");

    /**
     * <!-- begin-user-doc -->
     * 周报id
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_DIARY_ID = new Column(this, "org_diary_id");

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


    public OrgDiaryGitDetailTable() {
        super("org_diary_git_detail");
    }

}
