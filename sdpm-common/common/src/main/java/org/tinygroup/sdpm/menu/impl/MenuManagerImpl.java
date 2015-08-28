package org.tinygroup.sdpm.menu.impl;

import org.tinygroup.logger.LogLevel;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;
import org.tinygroup.sdpm.menu.Menu;
import org.tinygroup.sdpm.menu.MenuManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuManagerImpl implements MenuManager {

    /**
     * 所有菜单的数组
     */
    private List<Menu> menus = new ArrayList<Menu>();
    /**
     * 所有菜单的Map
     */
    private Map<String, Menu> menuMap = new HashMap<String, Menu>();
    private static Logger logger = LoggerFactory.getLogger(MenuManagerImpl.class);

    public void addMenu(Menu menu) {
        String menuName = menu.getName();
        logger.logMessage(LogLevel.DEBUG, "添加菜单:{}", menuName);
        if (menuMap.containsKey(menuName)) {
            logger.logMessage(LogLevel.WARN, "菜单:{}已存在,删除已有菜单", menuName);
            removeMenu(menuName);
        }
        menus.add(menu);
        menuMap.put(menu.getName(), menu);
        logger.logMessage(LogLevel.WARN, "菜单:{}添加完毕", menuName);

    }

    public void removeMenu(String name) {
        logger.logMessage(LogLevel.DEBUG, "删除菜单:{}", name);
        if (!menuMap.containsKey(name)) {
            logger.logMessage(LogLevel.WARN, "菜单:{}不存在,无须删除", name);
        } else {
            menus.remove(menuMap.remove(name));
        }
        logger.logMessage(LogLevel.DEBUG, "删除菜单:{}完毕", name);
    }

    public List<Menu> getMenus() {
        return menus;
    }

}
