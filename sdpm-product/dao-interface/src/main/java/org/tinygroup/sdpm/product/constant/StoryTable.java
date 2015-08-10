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

package org.tinygroup.sdpm.product.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class StoryTable extends Table {

	public static final StoryTable STORYTABLE = new StoryTable();
	/** 需求ID */
	public final Column STORY_ID = new Column(this, "story_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 产品ID */
	public final Column PRODUCT_ID = new Column(this, "product_id");
	/** 模块ID */
	public final Column MODULE_ID = new Column(this, "module_id");
	/** 发布计划ID */
	public final Column PLAN_ID = new Column(this, "plan_id");
	/** 需求发布ID */
	public final Column RELEASE_ID = new Column(this, "release_id");
	/** 父需求ID */
	public final Column STORY_PARENT_ID = new Column(this, "story_parent_id");
	/** 需求来源 */
	public final Column STORY_SOURCE = new Column(this, "story_source");
	/** 需求标题 */
	public final Column STORY_TITLE = new Column(this, "story_title");
	/** 需求关键字 */
	public final Column STORY_KEYWORDS = new Column(this, "story_keywords");
	/** 需求类型 */
	public final Column STORY_TYPE = new Column(this, "story_type");
	/** 需求优先级 */
	public final Column STORY_PRI = new Column(this, "story_pri");
	/** 需求估算 */
	public final Column STORY_ESTIMATE = new Column(this, "story_estimate");
	/** 需求状态 */
	public final Column STORY_STATUS = new Column(this, "story_status");
	/** 邮件列表 */
	public final Column STORY_MAILTO = new Column(this, "story_mailto");
	/** 需求打开者 */
	public final Column STORY_OPENED_BY = new Column(this, "story_opened_by");
	/** 需求打开日期 */
	public final Column STORY_OPENED_DATE = new Column(this, "story_opened_date");
	/** 需求指派 */
	public final Column STORY_ASSIGNED_TO = new Column(this, "story_assigned_to");
	/** 需求指派日期 */
	public final Column STORY_ASSIGNED_DATE = new Column(this, "story_assigned_date");
	/** 需求上次编辑者 */
	public final Column STORY_LAST_EDITED_BY = new Column(this, "story_last_edited_by");
	/** 需求上次编辑日期 */
	public final Column STORY_LAST_EDITED_DATE = new Column(this, "story_last_edited_date");
	/** 需求审核人 */
	public final Column STORY_REVIEWED_BY = new Column(this, "story_reviewed_by");
	/** 需求审核日期 */
	public final Column STORY_REVIEWED_DATE = new Column(this, "story_reviewed_date");
	/** 需求关闭者 */
	public final Column STORY_CLOSED_BY = new Column(this, "story_closed_by");
	/** 需求关闭日期 */
	public final Column STORY_CLOSED_DATE = new Column(this, "story_closed_date");
	/** 需求关闭原因 */
	public final Column STORY_CLOSED_REASON = new Column(this, "story_closed_reason");
	/** STORY_TO_BUG */
	public final Column STORY_TO_BUG = new Column(this, "story_to_bug");
	/** 关联需求 */
	public final Column STORY_LINK_STORIES = new Column(this, "story_link_stories");
	/** 重复需求ID */
	public final Column STORY_DUPLICATE_STORY = new Column(this, "story_duplicate_story");
	/** 需求版本 */
	public final Column STORY_VERSION = new Column(this, "story_version");
	/** 缺陷ID */
	public final Column BUG_ID = new Column(this, "bug_id");
	/** 客户请求ID */
	public final Column REQUEST_ID = new Column(this, "request_id");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private StoryTable() {
			super("story");
		}

}
