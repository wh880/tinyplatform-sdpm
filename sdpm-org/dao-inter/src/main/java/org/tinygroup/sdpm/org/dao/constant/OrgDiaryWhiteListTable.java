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
 * 周报白名单表
 * <p>
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class OrgDiaryWhiteListTable extends Table {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public static final OrgDiaryWhiteListTable ORG_DIARY_WHITE_LISTTABLE = new OrgDiaryWhiteListTable();

    /**
     * <!-- begin-user-doc -->
     * 白名单ID
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public final Column ORG_DIARY_WHITE_LIST_ID = new Column(this, "org_diary_white_list_id");

    /**
     * <!-- begin-user-doc -->
     * 白名单甲方账号
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public final Column ORG_DIARY_WHITE_LIST_FIRST_ACCOUNT = new Column(this, "org_diary_white_list_first_account");

    /**
     * <!-- begin-user-doc -->
     * 白名单乙方账号
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public final Column ORG_DIARY_WHITE_LIST_SECOND_ACCOUNT = new Column(this, "org_diary_white_list_second_account");


    private OrgDiaryWhiteListTable() {
        super("org_diary_white_list");
    }

}
