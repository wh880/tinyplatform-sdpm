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

package org.tinygroup.sdpm.quality.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/** 
 * 缺陷表
 * 
 */
public class BugTable extends Table {

	public static final BugTable BUGTABLE = new BugTable();

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
	public final Column TOTASK_ID = new Column(this, "toTask_id");

	/** 
	 * 转需求
	 * 
	 */
	public final Column TOSTORY_ID = new Column(this, "toStory_id");

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
	public final Column OPERATINGSYSTEM = new Column(this, "operatingSystem");

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
	public final Column BUG_ACTIVATEDCOUNT = new Column(this, "bug_activatedCount");

	/** 
	 * 抄送给
	 * 
	 */
	public final Column BUG_MAILTO = new Column(this, "bug_mailto");

	/** 
	 * 由谁创建
	 * 
	 */
	public final Column BUG_OPENEDBY = new Column(this, "bug_openedBy");

	/** 
	 * 创建日期
	 * 
	 */
	public final Column BUG_OPENEDDATE = new Column(this, "bug_openedDate");

	/** 
	 * 影响版本
	 * 
	 */
	public final Column BUG_OPENEDBUILD = new Column(this, "bug_openedBuild");

	/** 
	 * 指派给
	 * 
	 */
	public final Column BUG_ASSIGNEDTO = new Column(this, "bug_assignedTo");

	/** 
	 * 指派日期
	 * 
	 */
	public final Column BUG_ASSIGNEDDATE = new Column(this, "bug_assignedDate");

	/** 
	 * 解决者
	 * 
	 */
	public final Column BUG_RESOLVEDBY = new Column(this, "bug_resolvedBy");

	/** 
	 * 解决方案
	 * 
	 */
	public final Column BUG_RESOLUTION = new Column(this, "bug_resolution");

	/** 
	 * 解决版本
	 * 
	 */
	public final Column BUG_RESOLVEDBUILD = new Column(this, "bug_resolvedBuild");

	/** 
	 * 解决日期
	 * 
	 */
	public final Column BUG_RESOLVEDDATE = new Column(this, "bug_resolvedDate");

	/** 
	 * 由谁关闭
	 * 
	 */
	public final Column BUG_CLOSEDBY = new Column(this, "bug_closedBy");

	/** 
	 * 关闭日期
	 * 
	 */
	public final Column BUG_CLOSEDDATE = new Column(this, "bug_closedDate");

	/** 
	 * 重复Bug的ID
	 * 
	 */
	public final Column BUG_DUPLICATEBUG = new Column(this, "bug_duplicateBug");

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
	 * RESULT
	 * 
	 */
	public final Column RESULT = new Column(this, "result");

	/** 
	 * REPO
	 * 
	 */
	public final Column REPO = new Column(this, "repo");

	/** 
	 * ENTRY
	 * 
	 */
	public final Column ENTRY = new Column(this, "entry");

	/** 
	 * LINES
	 * 
	 */
	public final Column LINES = new Column(this, "lines");

	/** 
	 * V1
	 * 
	 */
	public final Column V1 = new Column(this, "v1");

	/** 
	 * V2
	 * 
	 */
	public final Column V2 = new Column(this, "v2");

	/** 
	 * REPOTYPE
	 * 
	 */
	public final Column REPOTYPE = new Column(this, "repoType");

	/** 
	 * 测试任务编号
	 * 
	 */
	public final Column TESTTASK = new Column(this, "testtask");

	/** 
	 * 最后修改者
	 * 
	 */
	public final Column BUG_LASTEDITEDBY = new Column(this, "bug_lastEditedBy");

	/** 
	 * 最后修改日期
	 * 
	 */
	public final Column BUG_LASTEDITEDDATE = new Column(this, "bug_lastEditedDate");

	/** 
	 * 已删除
	 * 
	 */
	public final Column DELETED = new Column(this, "deleted");


		private BugTable() {
			super("bug");
		}

}
