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
 * 客服回访表
 *
 */
public class ServiceReviewTable extends Table {

    public static final ServiceReviewTable SERVICE_REVIEWTABLE = new ServiceReviewTable();

    /**
     * 回访ID
     *
     */
    public final Column REVIEW_ID = new Column(this, "review_id");

    /**
     * 请求ID
     *
     * 服务请求ID
     */
    public final Column CLIENT_REQUEST_ID = new Column(this, "client_request_id");

    /**
     * 回访描述
     *
     */
    public final Column REVIEW_SPEC = new Column(this, "review_spec");

    /**
     * 联系人
     *
     * 默认为需求提交人或产品客户其他联系人
     */
    public final Column REQUESTER = new Column(this, "requester");

    /**
     * 回访者
     *
     * 回访人account，当前操作者
     */
    public final Column REVIEWER = new Column(this, "reviewer");

    /**
     * 回访时间
     *
     */
    public final Column REVIEW_DATE = new Column(this, "review_date");

    /**
     * 回访结果
     *
     * 0-not
     * pass,
     * 1-pass
     */
    public final Column REVIEW_RESULT = new Column(this, "review_result");

    /**
     * 回访评分
     *
     * 0很不满意，1不满意，2一般，3满意，4非常满意
     */
    public final Column REVIEW_SCORE = new Column(this, "review_score");

    /**
     * 回访类型
     *
     * 0-发放回访，1-拒绝需求回访，2-无修改需求回访
     */
    public final Column REVIEW_TYPE = new Column(this, "review_type");


    private ServiceReviewTable() {
        super("service_review");
    }

}
