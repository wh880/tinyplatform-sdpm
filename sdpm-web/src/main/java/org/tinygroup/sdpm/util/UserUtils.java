package org.tinygroup.sdpm.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.common.menu.Menu;
import org.tinygroup.sdpm.common.menu.MenuManager;
import org.tinygroup.sdpm.org.dao.pojo.OrgRole;
import org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.org.service.inter.RoleService;
import org.tinygroup.sdpm.org.service.inter.UserService;
import org.tinygroup.sdpm.security.Principal;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户工具类
 */
@Service
public class UserUtils {
    public static final String USER_CACHE = "userCache";
    public static final String USER_CACHE_ID_ = "id_";
    public static final String USER_CACHE_LOGIN_NAME_ = "ln";
    public static final String CACHE_ROLE_LIST = "roleList";
    public static final String CACHE_MENU_LIST = "menuList";
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private MenuManager menuManager;

    /**
     * 获取当前用户账号
     *
     * @return 取不到返回 null
     */
    public static String getUserAccount() {
        Principal principal = getPrincipal();
        if (principal != null) {
            return principal.getLoginName();
        }
        return null;
    }

    /**
     * 获取当前用户Id
     *
     * @return 取不到返回 null
     */
    public static String getUserId() {
        Principal principal = getPrincipal();
        if (principal != null) {
            return principal.getId();
        }
        return null;
    }

    /**
     * 获取授权主要对象
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 判断当前用户是否有某个菜单权限
     *
     * @param menuId
     * @return
     */
    public static boolean hasMenu(String menuId) {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null && !StringUtil.isBlank(menuId)) {
            return subject.isPermitted(menuId);
        }
        return false;
    }

    /**
     * 获取当前登录者对象
     */
    public static Principal getPrincipal() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null) {
                return principal;
            }
        } catch (UnavailableSecurityManagerException e) {

        } catch (InvalidSessionException e) {

        }
        return null;
    }

    public static Session getSession() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null) {
                session = subject.getSession();
            }
            if (session != null) {
                return session;
            }
        } catch (InvalidSessionException e) {

        }
        return null;
    }

    // ============== User Cache ==============
    public static Object getCache(String key) {
        return getCache(key, null);
    }

    public static Object getCache(String key, Object defaultValue) {
        Object obj = getSession().getAttribute(key);
        return obj == null ? defaultValue : obj;
    }

    public static void putCache(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static void removeCache(String key) {
        getSession().removeAttribute(key);
    }

    /**
     * 获取当前用户角色列表
     *
     * @return
     */
    public  List<OrgRole> getUserRoleList() {
        List<OrgRole> roleList = (List<OrgRole>) getCache(CACHE_ROLE_LIST);
        if (roleList == null) {
            Principal principal = getPrincipal();
            if (principal == null) {
                return null;
            }
            roleList = roleService.findRoleByUserId(principal.getId());
            putCache(CACHE_ROLE_LIST, roleList);
        }
        return roleList;
    }

    /**
     * 获取当前用户授权菜单
     *
     * @return
     */
    public  List<Menu> getMenuList() {
        List<Menu> menuList = (List<Menu>) getCache(CACHE_MENU_LIST);
        if (menuList == null) {
            List<OrgRoleMenu> roleMenuListByUser = roleService.findRoleMenuListByUser(getUserId());
            menuList = new ArrayList<Menu>();
            for (OrgRoleMenu menu : roleMenuListByUser) {
                Menu m = menuManager.getMenu(menu.getOrgRoleMenuId());
                menuList.add(m);
            }
            putCache(CACHE_MENU_LIST, menuList);
        }
        return menuList;
    }

    /**
     * 清除当前用户缓存
     */
    public void clearCache() {
        removeCache(CACHE_ROLE_LIST);
        removeCache(CACHE_MENU_LIST);
//        userUtils.clearCache(getUser());
    }

    /**
     * 清除指定用户缓存
     *
     * @param user
     */
    public void clearCache(OrgUser user) {
        CacheUtils.remove(USER_CACHE, USER_CACHE_ID_ + user.getOrgUserId());
        CacheUtils.remove(USER_CACHE, USER_CACHE_LOGIN_NAME_ + user.getOrgUserAccount());
    }

    /**
     * 获取当前用户
     *
     * @return 取不到返回 new OrgUser()
     */
    public OrgUser getUser() {
        Principal principal = getPrincipal();
        if (principal != null) {
            OrgUser user = getUserById(principal.getId());
            return user;
        }
        // 如果没有登录，则返回实例化空的User对象。
        return new OrgUser();
    }

    /**
     * 根据ID获取用户
     * 当id为空或者为null返回新对象
     *
     * @param id
     * @return 取不到返回new OrgUser();
     */
    public OrgUser getUserById(String id) {
        OrgUser user = null;
        if (StringUtil.isBlank(id)) {
            return new OrgUser();
        }
        try {
            user = userService.findUser(id);
        } catch (Exception e) {
        }
        if (user == null) {
            return new OrgUser();
        }
        return user;
    }

}
