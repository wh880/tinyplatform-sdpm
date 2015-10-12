/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p/>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.gnu.org/licenses/gpl.html
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tinygroup.sdpm.org.dao.constant;

import org.tinygroup.tinysqldsl.base.Column;
import org.tinygroup.tinysqldsl.base.Table;

/**
 * 角色用户分配
 *
 */
public class OrgRoleUserTable extends Table {

    public static final OrgRoleUserTable ORG_ROLE_USERTABLE = new OrgRoleUserTable();

    /**
     * 逻辑ID
     *
     */
    public final Column ID = new Column(this, "id");

    /**
     * 角色ID
     *
     */
    public final Column ORG_ROLE_ID = new Column(this, "org_role_id");

    /**
     * 用户编号
     *
     */
    public final Column ORG_USER_ID = new Column(this, "org_user_id");

    /**
     * 真实姓名
     *
     */
    public final Column ORG_USER_REAL_NAME = new Column(this, "org_user_real_name");


    private OrgRoleUserTable() {
        super("org_role_User");
    }

}
