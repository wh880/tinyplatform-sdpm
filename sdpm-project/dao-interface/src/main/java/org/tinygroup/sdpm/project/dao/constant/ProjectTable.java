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
 * 项目
 */
public class ProjectTable extends Table {

    public static final ProjectTable PROJECTTABLE = new ProjectTable();

    /**
     * 项目id
     */
    public final Column PROJECT_ID = new Column(this, "project_id");

    /**
     * 是否作为目录
     * <p>
     * 0-false,1-true
     */
    public final Column PROJECT_IS_CAT = new Column(this, "project_is_cat");

    /**
     * 目录id
     */
    public final Column PROJECT_CAT_ID = new Column(this, "project_cat_id");

    /**
     * 项目类型
     * <p>
     * 0-长期项目，1-短期项目，2-运维项目
     */
    public final Column PROJECT_TYPE = new Column(this, "project_type");

    /**
     * 项目名称
     */
    public final Column PROJECT_NAME = new Column(this, "project_name");

    /**
     * 项目代号
     */
    public final Column PROJECT_CODE = new Column(this, "project_code");

    /**
     * 项目开始日期
     */
    public final Column PROJECT_BEGIN = new Column(this, "project_begin");

    /**
     * 项目结束日期
     */
    public final Column PROJECT_END = new Column(this, "project_end");

    /**
     * 可用工作日
     */
    public final Column PROJECT_DAYS = new Column(this, "project_days");

    /**
     * 项目状态
     */
    public final Column PROJECT_STATUS = new Column(this, "project_status");

    /**
     * 项目所处阶段
     * <p>
     * 0-未开始，1-进行中，2-已挂起，3-已完成
     */
    public final Column PROJECT_STATGE = new Column(this, "project_statge");

    /**
     * 优先级
     * <p>
     * 1，2，3，4
     * 递增
     */
    public final Column PROJECT_PRI = new Column(this, "project_pri");

    /**
     * 项目描述
     */
    public final Column PROJECT_DESC = new Column(this, "project_desc");

    /**
     * 由谁创建
     */
    public final Column PROJECT_OPENED_BY = new Column(this, "project_opened_by");

    /**
     * 创建日期
     */
    public final Column PROJECT_OPENED_DATE = new Column(this, "project_opened_date");

    /**
     * 项目创建版本
     */
    public final Column PROJECT_OPENED_VERSION = new Column(this, "project_opened_version");

    /**
     * 项目由谁关闭
     */
    public final Column PROJECT_CLOSE_BY = new Column(this, "project_close_by");

    /**
     * 项目关闭日期
     */
    public final Column PROJECT_CLOSE_DATE = new Column(this, "project_close_date");

    /**
     * 项目由谁取消
     */
    public final Column PROJECT_CANCELED_BY = new Column(this, "project_canceled_by");

    /**
     * 项目取消日期
     */
    public final Column PROJECT_CANCELED_DATE = new Column(this, "project_canceled_date");

    /**
     * 产品负责人
     */
    public final Column PROJECT_PO = new Column(this, "project_PO");

    /**
     * 项目负责人
     */
    public final Column PROJECT_PM = new Column(this, "project_PM");

    /**
     * 测试负责人
     */
    public final Column PROJECT_QD = new Column(this, "project_QD");

    /**
     * 项目发布负责人
     */
    public final Column PROJECT_RD = new Column(this, "project_RD");

    /**
     * 团队成员
     */
    public final Column PROJECT_TEAM = new Column(this, "project_team");

    /**
     * 访问控制
     * <p>
     * 0-open,1-private,2-custom
     */
    public final Column PROJECT_ACL = new Column(this, "project_acl");

    /**
     * 分组白名单
     */
    public final Column PROJECT_WHITE_LIST = new Column(this, "project_white_list");

    /**
     * 项目排序
     */
    public final Column PROJECT_ORDER = new Column(this, "project_order");

    /**
     * 已删除
     * <p>
     * 0-未删除，1-删除
     */
    public final Column PROJECT_DELETED = new Column(this, "project_deleted");


    public ProjectTable() {
        super("project");
    }

}
