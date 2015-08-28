package org.tinygroup.sdpm.menu;

import java.util.List;

/**
 * Created by Hulk on 2015/8/27.
 */
public interface MenuManager {
    String XSTREAN_PACKAGE_NAME = "menu";
    void addMenu(Menu menu);
    void removeMenu(String name);
    List<Menu> getMenus();
}
