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
 * 测试任务表
 */
public class QualityTestTaskTable extends Table {

    public static final QualityTestTaskTable QUALITY_TEST_TASKTABLE = new QualityTestTaskTable();

    /**
     * 测试版本编号
     */
    public final Column TESTVERSION_ID = new Column(this, "testversion_id");

    /**
     * 任务名称
     */
    public final Column TESTTASK_TITLE = new Column(this, "testtask_title");

    /**
     * 产品ID
     */
    public final Column PRODUCT_ID = new Column(this, "product_id");

    /**
     * 项目id
     */
    public final Column PROJECT_ID = new Column(this, "project_id");

    /**
     * 版本名称
     */
    public final Column BUILD_NAME = new Column(this, "build_name");

    /**
     * 负责人
     */
    public final Column TESTTASK_OWNER = new Column(this, "testtask_owner");

    /**
     * 优先级
     */
    public final Column PRIORITY = new Column(this, "priority");

    /**
     * 开始日期
     */
    public final Column TESTTASK_BEGIN = new Column(this, "testtask_begin");

    /**
     * 结束日期
     */
    public final Column TESTTASK_END = new Column(this, "testtask_end");

    /**
     * 描述
     */
    public final Column TESTTASK_DESC = new Column(this, "testtask_desc");

    /**
     * 测试总结
     */
    public final Column TESTTASK_REPORT = new Column(this, "testtask_report");

    /**
     * 当前状态
     */
    public final Column TESTTASK_STATUS = new Column(this, "testtask_status");

    /**
     * 已删除
     */
    public final Column DELETED = new Column(this, "deleted");

    public final Column NO = new Column(this, "no");


    private QualityTestTaskTable() {
        super("quality_test_task");
    }

}
