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
 * 周报表
 *
 * <!-- end-user-doc -->
 * @generated
 */
public class OrgDiaryTable extends Table {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final OrgDiaryTable ORG_DIARYTABLE = new OrgDiaryTable();

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
     * 周报开始时间
     *
     * 每周开始的第一天
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_DIARY_BEGIN_DATE = new Column(this, "org_diary_begin_date");

    /**
     * <!-- begin-user-doc -->
     * 周报结束时间
     *
     * 每周最后一天
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_DIARY_END_DATE = new Column(this, "org_diary_end_date");

    /**
     * <!-- begin-user-doc -->
     * 周报年
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_DIARY_YEAR = new Column(this, "org_diary_year");

    /**
     * <!-- begin-user-doc -->
     * 周报周数
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_DIARY_WEEK = new Column(this, "org_diary_week");

    /**
     * <!-- begin-user-doc -->
     * 周报创建日期
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_DIARY_CREATE_DATE = new Column(this, "org_diary_create_date");

    /**
     * <!-- begin-user-doc -->
     * 每周总结
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_DIARY_SUMMARY = new Column(this, "org_diary_summary");

    /**
     * <!-- begin-user-doc -->
     * 周报状态
     *
     * 正常-0
     * 删除-1
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_DIARY_STATUS = new Column(this, "org_diary_status");

    /**
     * <!-- begin-user-doc -->
     * 最后修改日期
     *
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public final Column ORG_DIARY_MODIFY_DATE = new Column(this, "org_diary_modify_date");


    public OrgDiaryTable() {
        super("org_diary");
    }

}
