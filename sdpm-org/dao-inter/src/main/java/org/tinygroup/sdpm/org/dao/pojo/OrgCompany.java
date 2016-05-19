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
 * 公司表
 */
public class OrgCompany implements Serializable {

    public static final String DELETE_YES = "1";
    public static final String DELETE_NO = "0";


    /**
     * 公司编号
     */
    private Integer orgCompanyId;
    /**
     * 公司名称
     */
    private String orgCompanyName;
    /**
     * 联系电话
     */
    private String orgCompanyPhone;
    /**
     * 传真
     */
    private String orgCompanyFax;
    /**
     * 通讯地址
     */
    private String orgCompanyAddress;
    /**
     * 邮政编码
     */
    private String orgCompanyZipCode;
    /**
     * 官网
     */
    private String orgCompanyWebsite;
    /**
     * 内网
     */
    private String orgCompanyBackyard;
    /**
     * 匿名登录
     */
    private String orgCompanyGuest;
    /**
     * 管理员列表
     */
    private String orgCompanyAdmins;
    /**
     * 删除标志
     */
    private String orgCompanyDeleted;


    /**
     * 公司logo
     */
    private String orgCompanyLogo;

    public OrgCompany() {
        setOrgCompanyId(1);
        setOrgCompanyDeleted(DELETE_NO);
    }

    public Integer getOrgCompanyId() {
        return orgCompanyId;
    }

    public void setOrgCompanyId(Integer orgCompanyId) {
        this.orgCompanyId = orgCompanyId;
    }

    public String getOrgCompanyName() {
        return orgCompanyName;
    }

    public void setOrgCompanyName(String orgCompanyName) {
        this.orgCompanyName = orgCompanyName;
    }

    public String getOrgCompanyPhone() {
        return orgCompanyPhone;
    }

    public void setOrgCompanyPhone(String orgCompanyPhone) {
        this.orgCompanyPhone = orgCompanyPhone;
    }

    public String getOrgCompanyFax() {
        return orgCompanyFax;
    }

    public void setOrgCompanyFax(String orgCompanyFax) {
        this.orgCompanyFax = orgCompanyFax;
    }

    public String getOrgCompanyAddress() {
        return orgCompanyAddress;
    }

    public void setOrgCompanyAddress(String orgCompanyAddress) {
        this.orgCompanyAddress = orgCompanyAddress;
    }

    public String getOrgCompanyZipCode() {
        return orgCompanyZipCode;
    }

    public void setOrgCompanyZipCode(String orgCompanyZipCode) {
        this.orgCompanyZipCode = orgCompanyZipCode;
    }

    public String getOrgCompanyWebsite() {
        return orgCompanyWebsite;
    }

    public void setOrgCompanyWebsite(String orgCompanyWebsite) {
        this.orgCompanyWebsite = orgCompanyWebsite;
    }

    public String getOrgCompanyBackyard() {
        return orgCompanyBackyard;
    }

    public void setOrgCompanyBackyard(String orgCompanyBackyard) {
        this.orgCompanyBackyard = orgCompanyBackyard;
    }

    public String getOrgCompanyGuest() {
        return orgCompanyGuest;
    }

    public void setOrgCompanyGuest(String orgCompanyGuest) {
        this.orgCompanyGuest = orgCompanyGuest;
    }

    public String getOrgCompanyAdmins() {
        return orgCompanyAdmins;
    }

    public void setOrgCompanyAdmins(String orgCompanyAdmins) {
        this.orgCompanyAdmins = orgCompanyAdmins;
    }

    public String getOrgCompanyDeleted() {
        return orgCompanyDeleted;
    }

    public void setOrgCompanyDeleted(String orgCompanyDeleted) {
        this.orgCompanyDeleted = orgCompanyDeleted;
    }

    public String getOrgCompanyLogo() {
        return orgCompanyLogo;
    }

    public void setOrgCompanyLogo(String orgCompanyLogo) {
        this.orgCompanyLogo = orgCompanyLogo;
    }
}
