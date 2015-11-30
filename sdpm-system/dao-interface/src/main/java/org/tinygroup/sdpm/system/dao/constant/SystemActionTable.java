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

package org.tinygroup.sdpm.system.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * 系统日志表
 *
 */
public class SystemActionTable extends Table {

    public static final SystemActionTable SYSTEM_ACTIONTABLE = new SystemActionTable();

    /**
     * 系统日志ID
     *
     */
    public final Column ACTION_ID = new Column(this, "action_id");

    /**
     * 对象类型
     *
     */
    public final Column ACTION_OBJECT_TYPE = new Column(this, "action_object_type");

    /**
     * 对象ID
     *
     */
    public final Column ACTION_OBJECT_ID = new Column(this, "action_object_id");

    /**
     * 所属项目
     *
     */
    public final Column ACTION_PROJECT = new Column(this, "action_project");

    /**
     * 所属产品
     *
     */
    public final Column ACTION_PRODUCT = new Column(this, "action_product");
    /**
     * 动作
     */
    public final Column ACTION_ACTION = new Column(this, "action_action");

    /**
     * 操作者
     *
     */
    public final Column ACTION_ACTOR = new Column(this, "action_actor");

    /**
     * 系统日志日期
     *
     */
    public final Column ACTION_DATE = new Column(this, "action_date");

    /**
     * 注释
     *
     */
    public final Column ACTION_COMMENT = new Column(this, "action_comment");

    /**
     * EXTRA
     *
     */
    public final Column ACTION_EXTRA = new Column(this, "action_extra");

    /**
     * 是否已读
     *
     */
    public final Column ACTION_READ = new Column(this, "action_read");

    /**
     * ACTION_efforted
     *
     */
    public final Column ACTION_EFFORTED = new Column(this, "action_efforted");


    private SystemActionTable() {
        super("system_action");
    }

}
