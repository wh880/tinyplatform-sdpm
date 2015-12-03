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

package org.tinygroup.sdpm.org.dao.pojo;

import java.io.Serializable;

/**
 * 角色用户分配
 *
 */
public class OrgRoleUser implements Serializable{

    /**
     * 逻辑ID
     *
     */
    private Integer id;

    /**
     * 角色ID
     *
     */
    private Integer orgRoleId;

    /**
     * 用户编号
     *
     */
    private String orgUserId;

    /**
     * 真实姓名
     *
     */
    private String orgUserRealName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgRoleId() {
        return orgRoleId;
    }

    public void setOrgRoleId(Integer orgRoleId) {
        this.orgRoleId = orgRoleId;
    }

    public String getOrgUserId() {
        return orgUserId;
    }

    public void setOrgUserId(String orgUserId) {
        this.orgUserId = orgUserId;
    }

    public String getOrgUserRealName() {
        return orgUserRealName;
    }

    public void setOrgUserRealName(String orgUserRealName) {
        this.orgUserRealName = orgUserRealName;
    }

}
