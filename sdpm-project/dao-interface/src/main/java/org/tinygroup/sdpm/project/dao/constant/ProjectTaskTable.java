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

package org.tinygroup.sdpm.project.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * <!-- begin-user-doc -->
 * 任务
 * <p>
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class ProjectTaskTable extends Table {

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public static final ProjectTaskTable PROJECT_TASKTABLE = new ProjectTaskTable();

    /**
     * <!-- begin-user-doc -->
     * 任务id
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
    public final Column TASK_ID = new Column(this, "task_id");

    /**
     * <!-- begin-user-doc -->
     * 编号
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
    public final Column TASK_NO = new Column(this, "task_no");

    /**
     * <!-- begin-user-doc -->
     * 任务所属项目
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
    public final Column TASK_PROJECT = new Column(this, "task_project");

    /**
     * <!-- begin-user-doc -->
     * 任务相关需求
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
    public final Column TASK_STORY = new Column(this, "task_story");

    /**
     * <!-- begin-user-doc -->
     * 需求版本
     *
     * 需求变更后自动增长
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public final Column TASK_STORY_VERSION = new Column(this, "task_story_version");

    /**
     * <!-- begin-user-doc -->
     * 模块id
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
    public final Column TASK_MOMODULE = new Column(this, "task_module");

    /**
     * <!-- begin-user-doc -->
     * 来源bug
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
    public final Column TASK_FROM_BUG = new Column(this, "task_from_bug");

    /**
     * <!-- begin-user-doc -->
     * 任务名称
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
    public final Column TASK_NAME = new Column(this, "task_name");

    /**
     * <!-- begin-user-doc -->
     * 任务类型
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
    public final Column TASK_TYPE = new Column(this, "task_type");

    /**
     * <!-- begin-user-doc -->
     * 任务优先级
     *
     * 1，2，4，5
     * 递增
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public final Column TASK_PRI = new Column(this, "task_pri");

    /**
     * <!-- begin-user-doc -->
     * 最初预计
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
    public final Column TASK_ESTIMATE = new Column(this, "task_estimate");

    /**
     * <!-- begin-user-doc -->
     * 总消耗
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
    public final Column TASK_CONSUMED = new Column(this, "task_consumed");

    /**
     * <!-- begin-user-doc -->
     * 预计剩余
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
    public final Column TASK_LEFT = new Column(this, "task_left");

    /**
     * <!-- begin-user-doc -->
     * 截止日期
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
    public final Column TASK_DEAD_LINE = new Column(this, "task_dead_line");

    /**
     * <!-- begin-user-doc -->
     * 任务状态
     *
     * 0-等待，1-未开始，2-进行中，3-已完成，4-已暂停，5-已取消,6-已关闭
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public final Column TASK_STATUS = new Column(this, "task_status");

    /**
     * <!-- begin-user-doc -->
     * 抄送给
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
    public final Column TASK_MAILTO = new Column(this, "task_mailto");

    /**
     * <!-- begin-user-doc -->
     * 任务描述
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
    public final Column TASK_DESC = new Column(this, "task_desc");

    /**
     * <!-- begin-user-doc -->
     * 由谁创建
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
    public final Column TASK_OPEN_BY = new Column(this, "task_open_by");

    /**
     * <!-- begin-user-doc -->
     * 创建日期
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
    public final Column TASK_OPENED_DATE = new Column(this, "task_opened_date");

    /**
     * <!-- begin-user-doc -->
     * 指派给
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
    public final Column TASK_ASSIGNED_TO = new Column(this, "task_assigned_to");

    /**
     * <!-- begin-user-doc -->
     * 指派日期
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
    public final Column TASK_ASSIGNED_DATE = new Column(this, "task_assigned_date");

    /**
     * <!-- begin-user-doc -->
     * 预计开始
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
    public final Column TASK_EST_STARED = new Column(this, "task_est_stared");

    /**
     * <!-- begin-user-doc -->
     * 实际开始
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
    public final Column TASK_REAL_STARTED = new Column(this, "task_real_started");

    /**
     * <!-- begin-user-doc -->
     * 由谁完成
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
    public final Column TASK_FINISHED_BY = new Column(this, "task_finished_by");

    /**
     * <!-- begin-user-doc -->
     * 完成时间
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
    public final Column TASK_FINISHED_DATE = new Column(this, "task_finished_date");

    /**
     * <!-- begin-user-doc -->
     * 由谁取消
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
    public final Column TASK_CANCELED_BY = new Column(this, "task_canceled_by");

    /**
     * <!-- begin-user-doc -->
     * 取消时间
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
    public final Column TASK_CANCELED_DATE = new Column(this, "task_canceled_date");

    /**
     * <!-- begin-user-doc -->
     * 由谁关闭
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
    public final Column TASK_CLOSED_BY = new Column(this, "task_closed_by");

    /**
     * <!-- begin-user-doc -->
     * 关闭时间
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
    public final Column TASK_CLOSE_DATE = new Column(this, "task_close_date");

    /**
     * <!-- begin-user-doc -->
     * 关闭原因
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
    public final Column TASK_CLOSED_REASON = new Column(this, "task_closed_reason");

    /**
     * <!-- begin-user-doc -->
     * 最后修改
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
    public final Column TASK_LAST_EDITED_BY = new Column(this, "task_last_edited_by");

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
     *
     * @generated
     */
    public final Column TASK_LAST_EDITED_DATE = new Column(this, "task_last_edited_date");

    /**
     * <!-- begin-user-doc -->
     * 已删除
     *
     * 0-未删除，1-已删除
     * <!-- end-user-doc -->
     * @generated
     */
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public final Column TASK_DELETED = new Column(this, "task_deleted");

    /**
     * <!-- begin-user-doc -->
     * Bug关联任务
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
    public final Column TASK_RELATION_BUG = new Column(this, "task_relation_bug");


    public ProjectTaskTable() {
        super("project_task");
    }

}
