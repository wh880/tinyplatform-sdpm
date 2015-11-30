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

package org.tinygroup.sdpm.document.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * 文档库表
 *
 * 文档相关的
 */
public class DocumentDoclibTable extends Table {

    public static final DocumentDoclibTable DOCUMENT_DOCLIBTABLE = new DocumentDoclibTable();

    /**
     * 文档库ID
     *
     * 文档库ID
     */
    public final Column DOC_LIB_ID = new Column(this, "doc_lib_id");

    /**
     * 文档库名字
     *
     * 文档库名字
     */
    public final Column DOC_LIB_NAME = new Column(this, "doc_lib_name");

    /**
     * 删除文档库标志
     *
     * 已删除，并不真正删除数据，只是相应比标志位变参而已。
     */
    public final Column DOC_LIB_DELETED = new Column(this, "doc_lib_deleted");

    /**
     * 文档库添加时间
     *
     * 文档库添加时间
     */
    public final Column DOC_LIB_ADDED_DATE = new Column(this, "doc_lib_added_date");

    /**
     * 文档库名称更新时间
     *
     * 文档库名称更新时间
     */
    public final Column DOC_LIB_EDITED_DATE = new Column(this, "doc_lib_edited_date");


    private DocumentDoclibTable() {
        super("document_doclib");
    }

}
