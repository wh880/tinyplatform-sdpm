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

package org.tinygroup.sdpm.product.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * 需求表
 *
 */
public class ProductStoryTable extends Table {

    public static final ProductStoryTable PRODUCT_STORYTABLE = new ProductStoryTable();

    /**
     * 需求ID
     *
     */
    public final Column STORY_ID = new Column(this, "story_id");

    /**
     * 公司ID
     *
     */
    public final Column COMPANY_ID = new Column(this, "company_id");

    /**
     * 产品ID
     *
     */
    public final Column PRODUCT_ID = new Column(this, "product_id");

    /**
     * 父需求ID
     *
     */
    public final Column STORY_PARENT_ID = new Column(this, "story_parent_id");

    /**
     * 模块ID
     *
     */
    public final Column MODULE_ID = new Column(this, "module_id");

    /**
     * 计划ID
     *
     */
    public final Column PLAN_ID = new Column(this, "plan_id");

    /**
     * 需求状态
     *
     * 0-created新建；1-accepted评审通过（接受，并纳入计划）；2-rejected评审不通过（拒绝，属终点状态）；3-canceled取消（无效或重复，属终点状态）；4-postponed待明确（需要重新评审），5-changed已变更（需要重新评审）；6-developing开发中（关联到项目）7-finished完成（通过测试验收，可发布），8-released已发布（创建了产品发布），9-closed关闭（终点状态）
     */
    public final Column STORY_STATUS = new Column(this, "story_status");

    /**
     * 需求来源
     *
     */
    public final Column STORY_SOURCE = new Column(this, "story_source");

    /**
     * 来源Bug
     *
     */
    public final Column STORY_FROM_BUG = new Column(this, "story_from_bug");

    /**
     * 需求标题
     *
     */
    public final Column STORY_TITLE = new Column(this, "story_title");

    /**
     * 需求关键字
     *
     */
    public final Column STORY_KEYWORDS = new Column(this, "story_keywords");

    /**
     * 需求类型
     *
     */
    public final Column STORY_TYPE = new Column(this, "story_type");

    /**
     * 需求优先级
     *
     */
    public final Column STORY_PRI = new Column(this, "story_pri");

    /**
     * 预计工时
     *
     */
    public final Column STORY_ESTIMATE = new Column(this, "story_estimate");

    /**
     * 所处阶段
     *
     */
    public final Column STORY_STAGE = new Column(this, "story_stage");

    /**
     * 邮件列表
     *
     */
    public final Column STORY_MAILTO = new Column(this, "story_mailto");

    /**
     * 由谁创建
     *
     */
    public final Column STORY_OPENED_BY = new Column(this, "story_opened_by");

    /**
     * 创建日期
     *
     */
    public final Column STORY_OPENED_DATE = new Column(this, "story_opened_date");

    /**
     * 需求指派
     *
     */
    public final Column STORY_ASSIGNED_TO = new Column(this, "story_assigned_to");

    /**
     * 需求指派日期
     *
     */
    public final Column STORY_ASSIGNED_DATE = new Column(this, "story_assigned_date");

    /**
     * 需求上次编辑者
     *
     */
    public final Column STORY_LAST_EDITED_BY = new Column(this, "story_last_edited_by");

    /**
     * 需求上次编辑日期
     *
     */
    public final Column STORY_LAST_EDITED_DATE = new Column(this, "story_last_edited_date");

    /**
     * 需求审核人
     *
     */
    public final Column STORY_REVIEWED_BY = new Column(this, "story_reviewed_by");

    /**
     * 需求审核日期
     *
     */
    public final Column STORY_REVIEWED_DATE = new Column(this, "story_reviewed_date");

    /**
     * 需求关闭者
     *
     */
    public final Column STORY_CLOSED_BY = new Column(this, "story_closed_by");

    /**
     * 需求关闭日期
     *
     */
    public final Column STORY_CLOSED_DATE = new Column(this, "story_closed_date");

    /**
     * 需求关闭原因
     *
     */
    public final Column STORY_CLOSED_REASON = new Column(this, "story_closed_reason");

    /**
     * 待定（关联bug）
     *
     */
    public final Column TO_BUG = new Column(this, "to_bug");

    /**
     * 关联需求
     *
     */
    public final Column STORY_LINK_STORIES = new Column(this, "story_link_stories");

    /**
     * 细分需求
     *
     */
    public final Column STORY_CHILD_STORIES = new Column(this, "story_child_stories");

    /**
     * 重复需求ID
     *
     */
    public final Column STORY_DUPLICATE_STORY = new Column(this, "story_duplicate_story");

    /**
     * 需求版本
     *
     */
    public final Column STORY_VERSION = new Column(this, "story_version");

    /**
     * 版本id
     *
     */
    public final Column BUILD_ID = new Column(this, "build_id");

    /**
     * 请求ID
     *
     * 服务请求ID
     */
    public final Column CLIENT_REQUEST_ID = new Column(this, "client_request_id");

    /**
     * 已删除
     *
     */
    public final Column DELETED = new Column(this, "deleted");

    public final Column NO = new Column(this, "no");

    public ProductStoryTable() {
        super("product_story");
    }

}
