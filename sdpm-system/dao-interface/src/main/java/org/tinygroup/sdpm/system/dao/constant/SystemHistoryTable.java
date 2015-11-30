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
 * 操作历史表
 *
 */
public class SystemHistoryTable extends Table {

    public static final SystemHistoryTable SYSTEM_HISTORYTABLE = new SystemHistoryTable();

    /**
     * 操作历史ID
     *
     */
    public final Column HISTORY_ID = new Column(this, "history_id");

    /**
     * 操作ID
     *
     */
    public final Column HISTORY_ACTION = new Column(this, "history_action");

    /**
     * 所属领域
     *
     */
    public final Column HISTORY_FIELD = new Column(this, "history_field");

    /**
     * 当前历史
     *
     */
    public final Column HISTORY_NEW = new Column(this, "history_new");

    /**
     * 上一条历史
     *
     */
    public final Column HISTORY_OLD = new Column(this, "history_old");

    /**
     * 对比
     *
     */
    public final Column HISTORY_DIFF = new Column(this, "history_diff");


    private SystemHistoryTable() {
        super("system_history");
    }

}
