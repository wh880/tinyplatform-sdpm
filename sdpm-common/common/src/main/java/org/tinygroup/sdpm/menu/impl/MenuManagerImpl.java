package org.tinygroup.sdpm.menu.impl;

import org.tinygroup.logger.LogLevel;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;
import org.tinygroup.sdpm.menu.Menu;
import org.tinygroup.sdpm.menu.MenuManager;

import java.util.*;

public class MenuManagerImpl implements MenuManager {

    /**
     * 所有菜单的Map
     */
    private static Map<String, Menu> menuMap = new HashMap<String, Menu>();
    private static Logger logger = LoggerFactory.getLogger(MenuManagerImpl.class);

    public void addMenu(Menu menu, String fileName) {
        String menuId = menu.getId();
        logger.logMessage(LogLevel.DEBUG, "添加菜单:[id:{},name:{}]", menuId, menu.getName());
        if (menuMap.containsKey(menuId)) {
            logger.logMessage(LogLevel.ERROR, "菜单:[id:{},name:{}]已存在,重复定义在文件{}", menuId, menu.getName(), fileName);
            return;
        }
        menuMap.put(menu.getName(), menu);
        logger.logMessage(LogLevel.WARN, "菜单:[id:{},name:{}]", menuId, menu.getName());
        for (Menu child : menu.getChildMenus()) {
            child.setParentId(menu.getId());
            addMenu(child, fileName);
        }
    }

    public void removeMenu(String menuId) {
        logger.logMessage(LogLevel.DEBUG, "删除菜单id:{}", menuId);
        if (!menuMap.containsKey(menuId)) {
            logger.logMessage(LogLevel.WARN, "菜单menuId:{}不存在,无须删除", menuId);
        } else {
            menuMap.remove(menuId);
        }
        logger.logMessage(LogLevel.DEBUG, "删除菜单menuId:{}完毕", menuId);
    }

    public List<Menu> getChildMenus(String parentId) {
        Menu menu = menuMap.get(parentId);
        if (menu != null) {
            return menu.getChildMenus();
        }
        return null;
    }

    public Menu getMenu(String menuId) {
        return menuMap.get(menuId);
    }

    public List<Menu> getMenus() {
        return new ArrayList<Menu>(menuMap.values());
    }

}
