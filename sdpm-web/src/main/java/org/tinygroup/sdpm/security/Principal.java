package org.tinygroup.sdpm.security;

import org.tinygroup.sdpm.org.dao.pojo.OrgUser;

import java.io.Serializable;

/**
 * 系统认证账号的信息
 * Created by Hulk on 2015/10/13.
 */
public class Principal implements Serializable {
    private String id; // 编号
    private String loginName; // 登录名
    private String name; // 姓名

    public Principal(OrgUser user) {
        this.id = user.getOrgUserId();
        this.loginName = user.getOrgUserAccount();
        this.name = user.getOrgUserRealName();
    }

    public String getId() {
        return id;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getName() {
        return name;
    }

    /**
     * 获取SESSIONID
     */
//    public String getSessionid() {
//        try{
//            return (String) UserUtils.getSession().getId();
//        }catch (Exception e) {
//            return "";
//        }
//    }
    @Override
    public String toString() {
        return "Principal{" +
                "id='" + id + '\'' +
                ", loginName='" + loginName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
