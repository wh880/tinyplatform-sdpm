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
 * 文档表
 * <p>
 * wendang
 */
public class DocumentDocTable extends Table {

    public static final DocumentDocTable DOCUMENT_DOCTABLE = new DocumentDocTable();

    /**
     * 文档ID
     * <p>
     * 文档ID，主键，唯一标示
     */
    public final Column DOC_ID = new Column(this, "doc_id");

    /**
     * 所属产品
     * <p>
     * 所属产品，存放产品ID作为索引
     */
    public final Column DOC_PRODUCT = new Column(this, "doc_product");

    /**
     * 所属项目
     * <p>
     * 所属项目，存储项目ID作为索引
     */
    public final Column DOC_PROJECT = new Column(this, "doc_project");

    /**
     * 文档库ID
     * <p>
     * 文档库ID
     */
    public final Column DOC_LIB_ID = new Column(this, "doc_lib_id");

    /**
     * 所属分类
     * <p>
     * 所属分类
     */
    public final Column DOC_MODULE = new Column(this, "doc_module");

    /**
     * 文档标题
     * <p>
     * 文档标题
     */
    public final Column DOC_TITLE = new Column(this, "doc_title");

    /**
     * 摘要
     * <p>
     * 摘要
     */
    public final Column DOC_DIGEST = new Column(this, "doc_digest");

    /**
     * 关键字
     * <p>
     * 关键字
     */
    public final Column DOC_KEYWORDS = new Column(this, "doc_keywords");

    /**
     * 文档类型
     * <p>
     * 文档类型
     */
    public final Column DOC_TYPE = new Column(this, "doc_type");

    /**
     * 文档正文
     * <p>
     * 文档正文
     */
    public final Column DOC_CONTENT = new Column(this, "doc_content");

    /**
     * 文档url
     * <p>
     * 文档url
     */
    public final Column DOC_URL = new Column(this, "doc_url");

    /**
     * 附件
     * <p>
     * 放文档附件路径吧，标准字段类型根本没有存储二进制文件的类型。有点遗憾。
     */
    public final Column DOC_ATTACH = new Column(this, "doc_attach");

    /**
     * DOC查阅次数
     * <p>
     * 查阅次数
     */
    public final Column DOC_VIEWS = new Column(this, "doc_views");

    /**
     * DOC由谁添加
     * <p>
     * 由谁添加
     */
    public final Column DOC_ADDED_BY = new Column(this, "doc_added_by");

    /**
     * DOC添加时间
     * <p>
     * 添加时间
     */
    public final Column DOC_ADDED_DATE = new Column(this, "doc_added_date");

    /**
     * 由谁编辑
     * <p>
     * 由谁编辑
     */
    public final Column DOC_EDITED_BY = new Column(this, "doc_edited_by");

    /**
     * 文档编辑时间
     * <p>
     * 编辑时间
     */
    public final Column DOC_EDITED_DATE = new Column(this, "doc_edited_date");

    /**
     * 文档删除标志
     * <p>
     * 已删除
     */
    public final Column DOC_DELETED = new Column(this, "doc_deleted");


    private DocumentDocTable() {
        super("document_doc");
    }

}
