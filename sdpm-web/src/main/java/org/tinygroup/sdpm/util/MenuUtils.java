package org.tinygroup.sdpm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.sdpm.common.menu.Menu;
import org.tinygroup.sdpm.common.menu.MenuManager;
import org.tinygroup.sdpm.common.util.Collections3;

import java.util.List;

/**
 * 菜单管理器
 * Created by Hulk on 2015/11/11.
 */
@Component
public class MenuUtils {
    @Autowired
    private MenuManager menuManager;
    @Autowired
    private UserUtils userUtils;

    public Menu getMenu(String menuId) {
        if (userUtils.hasMenu(menuId)) {
            return menuManager.getMenu(menuId);
        } else {
            return null;
        }
    }

    /**
     * 获取一级菜单，带权限过滤
     *
     * @param parentId
     * @return
     */
    public List<Menu> getChildMenus(String parentId) {
        List<Menu> childMenus = getChildMenusWithoutPermissionFilter(parentId);
        filterMenu(childMenus);
        return childMenus;
    }

    /**
     * 获取一级菜单中的叶子节点菜单，不带权限过滤
     *
     * @param parentId
     * @return
     */
    public List<Menu> getChildLeafMenus(String parentId) {
        List<Menu> childMenus = getChildMenusWithoutPermissionFilter(parentId);
        if (!CollectionUtil.isEmpty(childMenus)) {
            for (int i = 0; i < childMenus.size(); i++) {
                Menu menu = childMenus.get(i);
                if (!CollectionUtil.isEmpty(menu.getChildMenus())) {
                    childMenus.remove(i);
                    i--;
                }
            }
        }
        return childMenus;
    }

    public List<Menu> getAllChildMenus(String parentId) {
        List<Menu> allChildMenus = menuManager.getAllChildMenus(parentId);
        filterMenu(allChildMenus);
        return allChildMenus;
    }

    public List<Menu> getChildMenusWithoutPermissionFilter(String parentId) {
        List<Menu> childMenus = menuManager.getChildMenus(parentId);
        return childMenus;
    }

    public List<Menu> getAllChildMenusWithoutPermissionFilter(String parentId) {
        List<Menu> allChildMenus = menuManager.getAllChildMenus(parentId);
        return allChildMenus;
    }

    public List<Menu> getAllChildMenusWithoutParent(String parentId) {
        List<Menu> childMenus = menuManager.getAllChildMenusWithoutParent(parentId);
        filterMenu(childMenus);
        return childMenus;
    }

    public List<Menu> getScopeMenus(String scope) {
        List<Menu> scopeMenus = menuManager.getScopeMenus(scope);
        filterMenu(scopeMenus);
        return scopeMenus;
    }

    private void filterMenu(final List<Menu> menuList) {
        if (Collections3.isEmpty(menuList)) {
            return;
        }
        for (int i = 0; i < menuList.size(); i++) {
            Menu menu = menuList.get(i);
            if (!userUtils.hasMenu(menu.getId())) {
                menuList.remove(i);
                i--;
            }
        }
    }

}
