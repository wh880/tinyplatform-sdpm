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

package org.tinygroup.sdpm.product.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/** 
 * 需求表述表
 * 
 */
public class StorySpecTable extends Table {

	public static final StorySpecTable STORYSPECTABLE = new StorySpecTable();

	/** 
	 * 公司ID
	 * 
	 */
	public final Column COMPANY_ID = new Column(this, "company_id");

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
	 * 需求标题
	 * 
	 */
	public final Column STORY_TITLE = new Column(this, "story_title");

	/** 
	 * 需求描述
	 * 
	 */
	public final Column STORY_SPEC = new Column(this, "story_spec");

	/** 
	 * 验证标准
	 * 
	 */
	public final Column STORY_VERIFICATION = new Column(this, "story_verification");


		private StorySpecTable() {
			super("storySpec");
		}

}
