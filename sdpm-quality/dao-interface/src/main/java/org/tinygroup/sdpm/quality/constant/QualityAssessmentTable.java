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

public class QualityAssessmentTable extends Table {

	public static final QualityAssessmentTable QUALITYASSESSMENTTABLE = new QualityAssessmentTable();
	/** 质量评估ID */
	public final Column QA_ID = new Column(this, "qa_id");
	/** 公司ID */
	public final Column COMPANY_ID = new Column(this, "company_id");
	/** 产品ID */
	public final Column PRODUCT_ID = new Column(this, "product_id");
	/** 模块ID */
	public final Column MODULE_ID = new Column(this, "module_id");
	/** 评估特性 */
	public final Column MODULE_FEATURE = new Column(this, "module_feature");
	/** 质量评估属性 */
	public final Column QA_PROPERTY = new Column(this, "qa_property");
	/** 质量评估值 */
	public final Column QA_FEATURE_VALUE = new Column(this, "qa_feature_value");
	/** 测试充分性 */
	public final Column QA_TEST_COVERAGE = new Column(this, "qa_test_coverage");
	/** 遗留缺陷影响 */
	public final Column QA_OPENBUG_IMPACT = new Column(this, "qa_openbug_impact");
	/** 评估说明 */
	public final Column QA_COMMENT = new Column(this, "qa_comment");
	/** 删除标记 */
	public final Column DELETED = new Column(this, "deleted");

		private QualityAssessmentTable() {
			super("qualityAssessment");
		}

}
