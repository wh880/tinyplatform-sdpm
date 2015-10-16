package org.tinygroup.sdpm.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.tinygroup.sdpm.common.util.Encodes;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.RoleService;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.security.filter.UsernamePasswordToken;
import org.tinygroup.sdpm.util.LogUtil;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Hulk on 2015/10/12.
 */
public class SystemRealm extends AuthorizingRealm {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Principal principal = (Principal) getAvailablePrincipal(principals);
        OrgUser user = userService.findUserByAccount(principal.getLoginName());
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            // 添加用户权限
            info.addStringPermission("user");
            List<OrgRoleMenu> roleMenuListByUser = roleService.findRoleMenuListByUser(user.getOrgUserId());
            if (roleMenuListByUser != null) {
                // 添加用户角色所有的菜单权限
                for (OrgRoleMenu menu : roleMenuListByUser) {
                    info.addStringPermission(menu.getOrgRoleMenuId());
                }
            }
            // 记录登录日志
            LogUtil.log(LogUtil.LogOperateObject.USER, LogUtil.LogAction.LOGIN, user.getOrgUserId(), user.getOrgUserId());
            return info;
        } else {
            return null;
        }
    }

    /**
     * 认证回调函数, 登录时调用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        // 校验用户名密码
        OrgUser user = userService.findUserByAccount(token.getUsername());
        if (user != null) {
            if (OrgUser.DELETE_YES.equals(user.getOrgUserDeleted())) {
                throw new AuthenticationException("msg:该已帐号禁止登录.");
            }
            byte[] salt = Encodes.decodeHex(user.getOrgUserPassword().substring(0, 16));
            return new SimpleAuthenticationInfo(new Principal(user),
                    user.getOrgUserPassword().substring(16), ByteSource.Util.bytes(salt), getName());
        } else {
            return null;
        }
    }

    /**
     * 设定密码校验的Hash算法与迭代次数
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(HASH_ALGORITHM);
        matcher.setHashIterations(HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }

}
