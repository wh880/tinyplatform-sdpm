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

package org.tinygroup.sdpm.service.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * 问答表
 *
 */
public class ServiceFaqTable extends Table {

    public static final ServiceFaqTable SERVICE_FAQTABLE = new ServiceFaqTable();

    /**
     * 问答ID
     *
     */
    public final Column FAQ_ID = new Column(this, "faq_id");

    /**
     * 问题描述
     *
     */
    public final Column FAQ_QUESTION = new Column(this, "faq_question");

    /**
     * 解答
     *
     */
    public final Column FAQ_ANSWER = new Column(this, "faq_answer");

    /**
     * 已删除
     *
     */
    public final Column DELETED = new Column(this, "deleted");

    /**
     * 创建时间
     *
     */
    public final Column FAQ_CREATE_DATE = new Column(this, "faq_create_date");

    /**
     * 创建人
     *
     */
    public final Column FAQ_CREATED_BY = new Column(this, "faq_created_by");

    /**
     * 产品ID
     *
     */
    public final Column PRODUCT_ID = new Column(this, "product_id");

    /**
     * 关键字
     *
     */
    public final Column FAQ_KEYWORDS = new Column(this, "faq_keywords");

    /**
     * 来源ID
     *
     */
    public final Column FAQ_SOURCE_ID = new Column(this, "faq_source_id");

    /**
     * FAQ来源
     *
     */
    public final Column FAQ_SOURCE = new Column(this, "faq_source");

    /**
     * 回复者
     *
     */
    public final Column FAQ_REPLIED_BY = new Column(this, "faq_replied_by");

    /**
     * FAQ_REPLY_DATE
     *
     */
    public final Column FAQ_REPLY_DATE = new Column(this, "faq_reply_date");

    /**
     * 问题类型id
     */
    public final Column FAQ_TYPE_ID = new Column(this, "faq_type_id");


    private ServiceFaqTable() {
        super("service_faq");
    }

}
