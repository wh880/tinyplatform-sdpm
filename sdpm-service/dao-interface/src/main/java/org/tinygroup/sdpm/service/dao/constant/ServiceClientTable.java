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
 * 客户信息表
 */
public class ServiceClientTable extends Table {

    public static final ServiceClientTable SERVICE_CLIENTTABLE = new ServiceClientTable();

    /**
     * 客户ID
     */
    public final Column CLIENT_ID = new Column(this, "client_id");

    /**
     * 客户名称
     */
    public final Column CLIENT_NAME = new Column(this, "client_name");

    /**
     * 客户描述
     */
    public final Column CLIENT_SPEC = new Column(this, "client_spec");

    /**
     * 客户编号
     */
    public final Column CLIENT_N_O = new Column(this, "client_n_o");

    /**
     * 客户单位/部门ID
     */
    public final Column CLIENT_DEPT_ID = new Column(this, "client_dept_id");

    /**
     * 客户登记人
     */
    public final Column CLIENT_CREATED_BY = new Column(this, "client_created_by");

    /**
     * 客户登记时间
     */
    public final Column CLIENT_CREATE_DATE = new Column(this, "client_create_date");

    /**
     * 客户状态
     * <p>
     * 0-停止；1-活跃
     */
    public final Column CLIENT_STATUS = new Column(this, "client_status");

    /**
     * 客户联系电话
     */
    public final Column USER_PHONE = new Column(this, "user_phone");

    /**
     * 客户联系人
     */
    public final Column USER_ACCOUNT = new Column(this, "user_account");

    /**
     * 联系人职务
     */
    public final Column USER_POST = new Column(this, "user_post");

    /**
     * 已删除
     */
    public final Column DELETED = new Column(this, "deleted");


    private ServiceClientTable() {
        super("service_client");
    }

}
