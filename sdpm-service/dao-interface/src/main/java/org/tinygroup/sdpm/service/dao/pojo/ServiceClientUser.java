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

package org.tinygroup.sdpm.service.dao.pojo;

import java.io.Serializable;

/**
 * 客户联系人信息表
 */
public class ServiceClientUser implements Serializable {
    public static final Integer DELETE_YES = 1;
    public static final Integer DELETE_NO = 0;
    /**
     * 逻辑ID
     */
    private Integer id;
    /**
     * 客户ID
     */
    private Integer clientId;
    /**
     * 客户联系人
     */
    private String userAccount;
    /**
     * 客户联系电话
     */
    private String userPhone;
    /**
     * 联系人职务
     */
    private String userPost;
    /**
     * 已删除
     */
    private Integer deleted;

    public ServiceClientUser() {
        setDeleted(DELETE_NO);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserPost() {
        return userPost;
    }

    public void setUserPost(String userPost) {
        this.userPost = userPost;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

}
