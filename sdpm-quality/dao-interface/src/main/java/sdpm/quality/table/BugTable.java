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

package sdpm.quality.table;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class BugTable extends Table {

	public static final BugTable BUGTABLE = new BugTable();
	/** Bug编号 */
	public final Column BUG_ID = new Column(this, "Bug_ID");
	/** 所属产品 */
	public final Column PRODUCT_ID = new Column(this, "product_ID");
	/** 所属模块 */
	public final Column MODULE_ID = new Column(this, "module_ID");
	/** 所属项目 */
	public final Column PROJECT_ID = new Column(this, "project_ID");
	/** 所属计划 */
	public final Column PLAN_ID = new Column(this, "plan_ID");
	/** 相关需求 */
	public final Column STORY_ID = new Column(this, "story_ID");
	/** 需求版本 */
	public final Column STORY_VERSION = new Column(this, "story_Version");
	/** 相关任务 */
	public final Column TASK_ID = new Column(this, "task_ID");
	/** 转任务 */
	public final Column TOTASK_ID = new Column(this, "toTask_ID");
	/** 转需求 */
	public final Column TOSTORY_ID = new Column(this, "toStory_ID");
	/** Bug标题 */
	public final Column BUG_TITLE = new Column(this, "Bug_title");
	/** 关键词 */
	public final Column KEYWORDS = new Column(this, "keywords");
	/** 严重程度 */
	public final Column BUG_SEVERITY = new Column(this, "Bug_severity");
	/** 优先级 */
	public final Column PRIORITY = new Column(this, "priority");
	/** Bug类型 */
	public final Column BUG_TYPE = new Column(this, "Bug_type");
	/** 操作系统 */
	public final Column OS = new Column(this, "os");
	/** 浏览器 */
	public final Column BROWSER = new Column(this, "browser");
	/** 硬件平台 */
	public final Column HARDWARE = new Column(this, "hardware");
	/** 如何发现 */
	public final Column BUG_FOUND = new Column(this, "Bug_found");
	/** 重现步骤 */
	public final Column BUG_STEPS = new Column(this, "Bug_steps");
	/** Bug状态 */
	public final Column BUG_STATUS = new Column(this, "Bug_status");
	/** 是否确认 */
	public final Column CONFIRMED = new Column(this, "confirmed");
	/** 激活次数 */
	public final Column BUG_ACTIVATEDCOUNT = new Column(this, "Bug_activatedCount");
	/** 抄送给 */
	public final Column BUG_MAILTO = new Column(this, "Bug_mailto");
	/** 由谁创建 */
	public final Column OPENEDBY = new Column(this, "openedBy");
	/** 创建日期 */
	public final Column OPENEDDATE = new Column(this, "openedDate");
	/** 影响版本 */
	public final Column BUG_OPENEDBUILD = new Column(this, "Bug_openedBuild");
	/** 指派给 */
	public final Column BUG_ASSIGNEDTO = new Column(this, "Bug_assignedTo");
	/** 指派日期 */
	public final Column BUG_ASSIGNEDDATE = new Column(this, "Bug_assignedDate");
	/** 解决者 */
	public final Column BUG_RESOLVEDBY = new Column(this, "Bug_resolvedBy");
	/** 解决方案 */
	public final Column BUG_RESOLUTION = new Column(this, "Bug_resolution");
	/** 解决版本 */
	public final Column BUG_RESOLVEDBUILD = new Column(this, "Bug_resolvedBuild");
	/** 解决日期 */
	public final Column BUG_RESOLVEDDATE = new Column(this, "Bug_resolvedDate");
	/** 由谁关闭 */
	public final Column BUG_CLOSEDBY = new Column(this, "Bug_closedBy");
	/** 关闭日期 */
	public final Column BUG_CLOSEDDATE = new Column(this, "Bug_closedDate");
	/** 重复ID */
	public final Column BUG_DUPLICATEBUG = new Column(this, "Bug_duplicateBug");
	/** 相关Bug */
	public final Column BUG_LINK = new Column(this, "Bug_link");
	/** 相关用例 */
	public final Column CASE_LINK = new Column(this, "case_link");
	/** 关联用例版本 */
	public final Column CASE_VERSION = new Column(this, "case_version");
	/** RESULT */
	public final Column RESULT = new Column(this, "result");
	/** REPO */
	public final Column REPO = new Column(this, "repo");
	/** ENTRY */
	public final Column ENTRY = new Column(this, "entry");
	/** LINES */
	public final Column LINES = new Column(this, "lines");
	/** V1 */
	public final Column V1 = new Column(this, "v1");
	/** V2 */
	public final Column V2 = new Column(this, "v2");
	/** REPOTYPE */
	public final Column REPOTYPE = new Column(this, "repoType");
	/** TESTTASK */
	public final Column TESTTASK = new Column(this, "testtask");
	/** 最后修改者 */
	public final Column BUG_LASTEDITEDBY = new Column(this, "Bug_lastEditedBy");
	/** 最后修改日期 */
	public final Column BUG_LASTEDITEDDATE = new Column(this, "Bug_lastEditedDate");
	/** 已删除 */
	public final Column DELETED = new Column(this, "deleted");

		private BugTable() {
			super("bug");
		}

}
