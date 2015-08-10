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

package org.tinygroup.sdpm.project.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class BuildRangeTable extends Table {

	public static final BuildRangeTable BUILDRANGETABLE = new BuildRangeTable();
	/** 集成范围ID */
	public final Column BUILD_RANGE_ID = new Column(this, "build_range_id");
	/** 集成版本ID */
	public final Column BUILD_ID = new Column(this, "build_id");
	/** 关联对象ID */
	public final Column OBJECT_ID = new Column(this, "object_id");
	/** 关联对象类型 */
	public final Column OBJECT_TYPE = new Column(this, "object_type");

		private BuildRangeTable() {
			super("buildRange");
		}

}
