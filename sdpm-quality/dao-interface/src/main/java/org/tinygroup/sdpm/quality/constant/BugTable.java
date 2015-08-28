/**
 *  Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 *
 *  Licensed under the GPL, Version 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.gnu.org/licenses/gpl.html
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.tinygroup.sdpm.quality.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class BugTable extends Table {

	public static final BugTable BUGTABLE = new BugTable();
	/** 缺陷ID */
	public final Column BUG_ID = new Column(this, "bug_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 产品ID */
	public final Column PRODUCT_ID = new Column(this, "product_id");
	/** 需求ID */
	public final Column STORY_ID = new Column(this, "story_id");
	/** 需求版本 */
	public final Column STORY_VERSION = new Column(this, "story_version");
	/** 项目ID */
	public final Column PROJECT_ID = new Column(this, "project_id");
	/** 转为需求ID */
	public final Column TO_STORY_ID = new Column(this, "to_story_id");
	/** 任务ID */
	public final Column TASK_ID = new Column(this, "task_id");
	/** 转为任务ID */
	public final Column TO_TASK_ID = new Column(this, "to_task_id");
	/** 缺陷标题 */
	public final Column BUG_TITLE = new Column(this, "bug_title");
	/** 模块ID */
	public final Column MODULE_ID = new Column(this, "module_id");
	/** 关键字 */
	public final Column BUG_KEYWORDS = new Column(this, "bug_keywords");
	/** 严重级别 */
	public final Column BUG_SEVERITY = new Column(this, "bug_severity");
	/** 优先级 */
	public final Column BUG_PRI = new Column(this, "bug_pri");
	/** 缺陷类型 */
	public final Column BUG_TYPE = new Column(this, "bug_type");
	/** 操作系统 */
	public final Column BUG_OS = new Column(this, "bug_os");
	/** 浏览器 */
	public final Column BUG_BROWSER = new Column(this, "bug_browser");
	/** 硬件 */
	public final Column BUG_HARDWARE = new Column(this, "bug_hardware");
	/** 重现步骤 */
	public final Column BUG_STEPS = new Column(this, "bug_steps");
	/** 缺陷状态 */
	public final Column BUG_STATUS = new Column(this, "bug_status");
	/** 是否确认 */
	public final Column BUG_CONFIRMED = new Column(this, "bug_confirmed");
	/** 打开次数 */
	public final Column BUG_OPEN_COUNT = new Column(this, "bug_open_count");
	/** 邮件列表 */
	public final Column BUG_MAILTO = new Column(this, "bug_mailto");
	/** 缺陷创建人 */
	public final Column BUG_OPENED_BY = new Column(this, "bug_opened_by");
	/** 创建日期 */
	public final Column BUG_OPENED_DATE = new Column(this, "bug_opened_date");
	/** 影响版本 */
	public final Column BUG_OPENED_BUILD = new Column(this, "bug_opened_build");
	/** 指派给 */
	public final Column BUG_ASSIGNED_TO = new Column(this, "bug_assigned_to");
	/** 指派日期 */
	public final Column BUG_ASSIGNED_DATE = new Column(this, "bug_assigned_date");
	/** 缺陷解决者 */
	public final Column BUG_RESOLVED_BY = new Column(this, "bug_resolved_by");
	/** 解决方案 */
	public final Column BUG_RESOLUTION = new Column(this, "bug_resolution");
	/** 解决版本 */
	public final Column BUG_RESOLVED_BUILD = new Column(this, "bug_resolved_build");
	/** 解决日期 */
	public final Column BUG_RESOLVED_DATE = new Column(this, "bug_resolved_date");
	/** 缺陷关闭者 */
	public final Column BUG_CLOSED_BY = new Column(this, "bug_closed_by");
	/** 缺陷关闭日期 */
	public final Column BUG_CLOSED_DATE = new Column(this, "bug_closed_date");
	/** 重复缺陷ID */
	public final Column BUG_DUPLICATE_BUG = new Column(this, "bug_duplicate_bug");
	/** 关联缺陷 */
	public final Column BUG_LINK_BUG = new Column(this, "bug_link_bug");
	/** 缺陷最后编辑者 */
	public final Column BUG_LAST_EDITED_BY = new Column(this, "bug_last_edited_by");
	/** 缺陷最后编辑日期 */
	public final Column BUG_LAST_EDITED_DATE = new Column(this, "bug_last_edited_date");
	/** 关联用例ID */
	public final Column CASE_ID = new Column(this, "case_id");
	/** 关联用例版本 */
	public final Column CASE_VERSION = new Column(this, "case_version");
	/** 缺陷来源 */
	public final Column BUG_SOURCE = new Column(this, "bug_source");
	/** 缺陷起源 */
	public final Column BUG_ORIGIN = new Column(this, "bug_origin");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private BugTable() {
			super("bug");
		}

}
