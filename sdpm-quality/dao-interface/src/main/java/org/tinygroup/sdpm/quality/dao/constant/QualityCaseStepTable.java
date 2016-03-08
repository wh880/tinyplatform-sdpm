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
 * 测试用例步骤表
 */
public class QualityCaseStepTable extends Table {

    public static final QualityCaseStepTable QUALITY_CASE_STEPTABLE = new QualityCaseStepTable();

    /**
     * 用例步骤编号
     */
    public final Column CASE_STEP_ID = new Column(this, "case_step_id");

    /**
     * 用例编号
     */
    public final Column CASE_ID = new Column(this, "case_id");

    /**
     * 关联用例版本
     */
    public final Column CASE_VERSION = new Column(this, "case_version");

    /**
     * 描述
     */
    public final Column CASE_STEP_DESC = new Column(this, "case_step_desc");

    /**
     * 用例预期
     */
    public final Column CASE_STEP_EXPECT = new Column(this, "case_step_expect");


    private QualityCaseStepTable() {
        super("quality_case_step");
    }

}
