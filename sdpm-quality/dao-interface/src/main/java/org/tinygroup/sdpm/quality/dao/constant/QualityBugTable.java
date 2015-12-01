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

package org.tinygroup.sdpm.quality.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * 缺陷表
 *
 */
public class QualityBugTable extends Table {

    public static final QualityBugTable QUALITY_BUGTABLE = new QualityBugTable();

    /**
     * Bug编号
     *
     */
    public final Column BUG_ID = new Column(this, "bug_id");

    /**
     * 产品ID
     *
     */
    public final Column PRODUCT_ID = new Column(this, "product_id");

    /**
     * 模块ID
     *
     */
    public final Column MODULE_ID = new Column(this, "module_id");

    /**
     * 项目id
     *
     */
    public final Column PROJECT_ID = new Column(this, "project_id");

    /**
     * 计划ID
     *
     */
    public final Column PLAN_ID = new Column(this, "plan_id");

    /**
     * 需求ID
     *
     */
    public final Column STORY_ID = new Column(this, "story_id");

    /**
     * 需求版本
     *
     */
    public final Column STORY_VERSION = new Column(this, "story_version");

    /**
     * 任务id
     *
     */
    public final Column TASK_ID = new Column(this, "task_id");

    /**
     * 转任务
     *
     */
    public final Column TO_TASK_ID = new Column(this, "to_task_id");

    /**
     * 转需求
     *
     */
    public final Column TO_STORY_ID = new Column(this, "to_story_id");

    /**
     * Bug标题
     *
     */
    public final Column BUG_TITLE = new Column(this, "bug_title");

    /**
     * 关键词
     *
     */
    public final Column BUG_KEYWORDS = new Column(this, "bug_keywords");

    /**
     * 严重程度
     *
     */
    public final Column BUG_SEVERITY = new Column(this, "bug_severity");

    /**
     * 优先级
     *
     */
    public final Column PRIORITY = new Column(this, "priority");

    /**
     * Bug类型
     *
     */
    public final Column BUG_TYPE = new Column(this, "bug_type");

    /**
     * 操作系统
     *
     */
    public final Column OPERATING_SYSTEM = new Column(this, "operating_system");

    /**
     * 浏览器
     *
     */
    public final Column BROWSER = new Column(this, "browser");

    /**
     * 硬件平台
     *
     */
    public final Column HARDWARE = new Column(this, "hardware");

    /**
     * 如何发现
     *
     */
    public final Column BUG_FOUND = new Column(this, "bug_found");

    /**
     * 重现步骤
     *
     */
    public final Column BUG_STEPS = new Column(this, "bug_steps");

    /**
     * Bug状态
     *
     */
    public final Column BUG_STATUS = new Column(this, "bug_status");

    /**
     * 是否确认
     *
     */
    public final Column BUG_CONFIRMED = new Column(this, "bug_confirmed");

    /**
     * 激活次数
     *
     */
    public final Column BUG_ACTIVATED_COUNT = new Column(this, "bug_activated_count");

    /**
     * 抄送给
     *
     */
    public final Column BUG_MAILTO = new Column(this, "bug_mailto");

    /**
     * 由谁创建
     *
     */
    public final Column BUG_OPENED_BY = new Column(this, "bug_opened_by");

    /**
     * 创建日期
     *
     */
    public final Column BUG_OPENED_DATE = new Column(this, "bug_opened_date");

    /**
     * 影响版本
     *
     */
    public final Column BUG_OPENED_BUILD = new Column(this, "bug_opened_build");

    /**
     * 指派给
     *
     */
    public final Column BUG_ASSIGNED_TO = new Column(this, "bug_assigned_to");

    /**
     * 指派日期
     *
     */
    public final Column BUG_ASSIGNED_DATE = new Column(this, "bug_assigned_date");

    /**
     * 解决者
     *
     */
    public final Column BUG_RESOLVED_BY = new Column(this, "bug_resolved_by");

    /**
     * 解决方案
     *
     */
    public final Column BUG_RESOLUTION = new Column(this, "bug_resolution");

    /**
     * 解决版本
     *
     */
    public final Column BUG_RESOLVED_BUILD = new Column(this, "bug_resolved_build");

    /**
     * 解决日期
     *
     */
    public final Column BUG_RESOLVED_DATE = new Column(this, "bug_resolved_date");

    /**
     * 由谁关闭
     *
     */
    public final Column BUG_CLOSED_BY = new Column(this, "bug_closed_by");

    /**
     * 关闭日期
     *
     */
    public final Column BUG_CLOSED_DATE = new Column(this, "bug_closed_date");

    /**
     * 重复Bug的ID
     *
     */
    public final Column BUG_DUPLICATE_BUG = new Column(this, "bug_duplicate_bug");

    /**
     * 相关Bug
     *
     */
    public final Column LINK_BUG = new Column(this, "link_bug");

    /**
     * 相关用例
     *
     */
    public final Column LINK_CASE = new Column(this, "link_case");

    /**
     * 关联用例版本
     *
     */
    public final Column CASE_VERSION = new Column(this, "case_version");

    /**
     * BUG_RESULT
     *
     */
    public final Column BUG_RESULT = new Column(this, "bug_result");

    /**
     * BUG_REPO
     *
     */
    public final Column BUG_REPO = new Column(this, "bug_repo");

    /**
     * BUG_ENTRY
     *
     */
    public final Column BUG_ENTRY = new Column(this, "bug_entry");

    /**
     * 来源用例
     *
     */
    public final Column BUG_FROM_CASE = new Column(this, "bug_from_case");

    /**
     * BUG_LINES
     *
     */
    public final Column BUG_LINES = new Column(this, "bug_lines");

    /**
     * BUG_V1
     *
     */
    public final Column BUG_V1 = new Column(this, "bug_v1");

    /**
     * BUG_V2
     *
     */
    public final Column BUG_V2 = new Column(this, "bug_v2");

    /**
     * BUG_REPOTYPE
     *
     */
    public final Column BUG_REPO_TYPE = new Column(this, "bug_repo_type");

    /**
     * 测试任务编号
     *
     */
    public final Column TESTTASK = new Column(this, "testtask");

    /**
     * 最后修改者
     *
     */
    public final Column BUG_LAST_EDITED_BY = new Column(this, "bug_last_edited_by");

    /**
     * 最后修改日期
     *
     */
    public final Column BUG_LAST_EDITED_DATE = new Column(this, "bug_last_edited_date");

    /**
     * 已删除
     *
     */
    public final Column DELETED = new Column(this, "deleted");

    public final Column NO = new Column(this, "no");


    public QualityBugTable() {
        super("quality_bug");
    }

}
