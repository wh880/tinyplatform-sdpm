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
 * 每日详情表
 *
 * <!-- end-user-doc -->
 * @generated
 */
public class OrgDiaryDetailTable extends Table {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final OrgDiaryDetailTable ORG_DIARY_DETAILTABLE = new OrgDiaryDetailTable();

    /**
     * <!-- begin-user-doc -->
     * 周报详情id
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_DETAIL_ID = new Column(this, "org_detail_id");

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
     * 用户编号
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_USER_ID = new Column(this, "org_user_id");

    /**
     * <!-- begin-user-doc -->
     * 详情发生时间
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_DETAIL_DATE = new Column(this, "org_detail_date");

    /**
     * <!-- begin-user-doc -->
     * 详情类型
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_DETAIL_TYPE = new Column(this, "org_detail_type");

    /**
     * <!-- begin-user-doc -->
     * 详情内容
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_DETAIL_CONTENT = new Column(this, "org_detail_content");

    /**
     * <!-- begin-user-doc -->
     * 系统日志ID
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ACTION_ID = new Column(this, "action_id");


    public OrgDiaryDetailTable() {
        super("org_diary_detail");
    }

}
