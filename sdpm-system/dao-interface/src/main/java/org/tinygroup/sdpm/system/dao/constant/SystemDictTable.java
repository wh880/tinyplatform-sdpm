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
 * 数据字典
 *
 */
public class SystemDictTable extends Table {

    public static final SystemDictTable SYSTEM_DICTTABLE = new SystemDictTable();

    /**
     * 字典项ID
     *
     */
    public final Column DICT_ID = new Column(this, "dict_id");

    /**
     * 字典key值
     *
     */
    public final Column DICT_KEY = new Column(this, "dict_key");

    /**
     * 字典value值
     *
     */
    public final Column DICT_VALUE = new Column(this, "dict_value");

    /**
     * 字典项序号
     *
     */
    public final Column DICT_SORT = new Column(this, "dict_sort");

    /**
     * 模块ID
     *
     */
    public final Column MODULE_ID = new Column(this, "module_id");

    /**
     * 已删除
     *
     */
    public final Column DELETED = new Column(this, "deleted");


    private SystemDictTable() {
        super("system_dict");
    }

}
