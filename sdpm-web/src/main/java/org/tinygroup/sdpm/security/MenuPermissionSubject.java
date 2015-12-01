package org.tinygroup.sdpm.security;

import org.apache.shiro.authz.AuthorizationException;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.util.UserUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hulk on 2015/11/14.
 */
public class MenuPermissionSubject {
    List<String> menuList;
    Integer projectId;
    Integer productId;

    private MenuPermissionSubject() {
    }

    public static MenuPermissionSubject projectBuilder(Integer projectId) {
        MenuPermissionSubject menuPermissionSubject = new MenuPermissionSubject();
        menuPermissionSubject.setProjectId(projectId);
        return menuPermissionSubject;
    }

    public static MenuPermissionSubject productBuilder(Integer productId) {
        MenuPermissionSubject menuPermissionSubject = new MenuPermissionSubject();
        menuPermissionSubject.setProductId(productId);
        return menuPermissionSubject;
    }

    public boolean isPermitted(String permission) {
        if (!StringUtil.isBlank(permission)) {
            if (getMenuList().contains(permission)) {
                return true;
            } else if (UserUtils.getSubject().isPermitted(permission)) {
                return true;
            }
        }
        return false;
    }

    public void checkPermission(String permission) {
        assertPermissionNull(permission);
        if (!isPermitted(permission)) {
            throw new AuthorizationException("没有菜单权限:" + permission);
        }
    }

    public void checkPermissions(String... permissions) {
        assertPermissionNull(permissions);
        for (String permission : permissions) {
            if (!isPermitted(permission)) {
                throw new AuthorizationException("没有菜单权限:" + permission);
            }
        }
    }

    public void assertPermissionNull(String... permissions) {
        if (ArrayUtil.isEmptyArray(permissions)) {
            throw new AuthorizationException("调用空菜单");
        }
        for (String permission : permissions) {
            if (StringUtil.isBlank(permission)) {
                throw new AuthorizationException("调用空菜单");
            }
        }
    }

    public List<String> getMenuList() {
        if (menuList == null) {
            if (productId != null) {
                menuList = UserUtils.getUserMenuByProduct(productId);
            } else if (productId != null) {
                menuList = UserUtils.getUserMenuByProject(projectId);
            } else {
                menuList = new ArrayList<String>();
            }
        }
        return menuList;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }
}
