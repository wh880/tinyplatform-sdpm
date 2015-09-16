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

package sdpm.project.dao.inter.projectproduct.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

public class ProjectproductTable extends Table {

	public static final ProjectproductTable PROJECTPRODUCTTABLE = new ProjectproductTable();
	/** 逻辑ID */
	public final Column ID = new Column(this, "id");
	/** 项目id */
	public final Column PROJECT_ID = new Column(this, "project_id");
	/** 产品ID */
	public final Column PRODUCT_ID = new Column(this, "product_id");

		private ProjectproductTable() {
			super("projectproduct");
		}

}
