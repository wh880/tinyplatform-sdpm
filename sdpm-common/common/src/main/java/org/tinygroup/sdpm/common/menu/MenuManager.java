package org.tinygroup.sdpm.common.menu;

import java.util.List;

/**
 * Created by Hulk on 2015/8/27.
 */
public interface MenuManager {
    String XSTREAN_PACKAGE_NAME = "menu";

    void addMenu(Menu menu, String fileName);

    void addScopeMenu(Menu menu, String fileName);

    void addMenuToParent(Menu menu, String fileName);

    void removeMenu(String menuId);

    List<Menu> getChildMenus(String parentId);

    List<Menu> getScopeMenus(String scope);

    Menu getMenu(String menuId);

    List<Menu> getMenus();

}
