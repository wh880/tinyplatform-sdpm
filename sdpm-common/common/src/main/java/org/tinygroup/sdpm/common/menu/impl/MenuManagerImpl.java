package org.tinygroup.sdpm.common.menu.impl;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;
import org.tinygroup.sdpm.common.menu.Menu;
import org.tinygroup.sdpm.common.menu.MenuManager;

import java.util.*;

public class MenuManagerImpl implements MenuManager {

    /**
     * 所有菜单的Map
     */
    private static Map<String, Menu> menuMap = new HashMap<String, Menu>();
    private static Map<String, List<String>> scopeMap = new HashMap<String, List<String>>();
    private static Logger logger = LoggerFactory.getLogger(MenuManagerImpl.class);

    {
        Menu root = new Menu();
        root.setId("0");
        root.setName("首页");
        root.setChildMenus(new ArrayList<Menu>());
        addMenu(root, "init");
    }

    public void addMenu(Menu menu, String fileName) {
        String menuId = menu.getId();
        if (menuId == null || menuId.isEmpty()) {
            logger.logMessage(LogLevel.ERROR, "菜单文件{}加载错误，菜单无id", fileName);
            throw new RuntimeException("菜单文件加载错误，菜单无id。");
        }
        if (menuMap.containsKey(menuId)) {
            logger.logMessage(LogLevel.ERROR, "菜单:[id=\"{}\"]已存在,重复定义在文件{}", menuId, fileName);
            throw new RuntimeException("菜单文件加载错误，id重复定义。");
        }
        logger.logMessage(LogLevel.DEBUG, "开始添加菜单:[id=\"{}\"]", menuId);
        menuMap.put(menuId, menu);
        addScopeMenu(menu, fileName);
        List<Menu> childMenus = menu.getChildMenus();
        if (childMenus != null) {
            for (Menu child : childMenus) {
                child.setParentId(menu.getId());
                addMenu(child, fileName);
            }
        }
        logger.logMessage(LogLevel.DEBUG, "完成添加菜单:[id=\"{}\"]", menuId, menu.getName());
    }

    public void addScopeMenu(Menu menu, String fileName) {
        if (menu.getScope() == null) {
            return;
        }
        logger.logMessage(LogLevel.DEBUG, "开始添加菜单:[id=\"{}\"的所属聚合]", menu.getId());
        for (String m : menu.getScope().split(",")) {
            List<String> scopeList = scopeMap.get(m);
            if (scopeList == null) {
                scopeList = new ArrayList<String>();
                scopeMap.put(m, scopeList);
                logger.logMessage(LogLevel.DEBUG, "新增聚合分类{}", m);
            }
            scopeList.add(menu.getId());
            logger.logMessage(LogLevel.DEBUG, "菜单:[id=\"{}\"]添加了所属聚合{}", menu.getId(), m);
        }
        logger.logMessage(LogLevel.DEBUG, "结束添加菜单:[id=\"{}\"的所属聚合]", menu.getId());
    }

    public void addMenuToParent(Menu menu, String fileName) {
        logger.logMessage(LogLevel.DEBUG, "开始添加菜单到父节点:[id=\"{}\"]", menu.getId());
        if (menu.getParentId() != null) {
            Menu parentMenu = menuMap.get(menu.getParentId());
            if (parentMenu != null && parentMenu.getChildMenus() != null) {
                parentMenu.getChildMenus().add(menu);
            }
        }
        logger.logMessage(LogLevel.DEBUG, "结束添加菜单到父节点:[id=\"{}\"]", menu.getId());
    }

    public void removeMenu(String menuId) {
        logger.logMessage(LogLevel.DEBUG, "删除菜单id=\"{}\"", menuId);
        if (!menuMap.containsKey(menuId)) {
            logger.logMessage(LogLevel.WARN, "菜单menuId=\"{}\"不存在,无须删除", menuId);
        } else {
            menuMap.remove(menuId);
        }
        logger.logMessage(LogLevel.DEBUG, "删除菜单menuId=\"{}\"完毕", menuId);
    }

    public List<Menu> getChildMenus(String parentId) {
        List<Menu> childMenus = getChildren(parentId);
        return findShow(childMenus);
    }

    public List<Menu> getAllChildMenus(String parentId) {
        List<Menu> childMenus = new ArrayList<Menu>();
        getChildren(parentId, childMenus);
        return childMenus;
    }

    public List<Menu> getAllChildMenusWithoutParent(String parentId) {
        List<Menu> childMenus = new ArrayList<Menu>();
        getChildrenWithoutParent(parentId, childMenus);
        return childMenus;
    }

    public List<Menu> getScopeMenus(String scope) {
        if (StringUtil.isBlank(scope)) {
            logger.logMessage(LogLevel.ERROR, "获取聚合菜单名称为空");
            return null;
        }
        List<String> scopeIdList = scopeMap.get(scope);
        if (scopeIdList == null) {
            return null;
        }
        List<Menu> menuList = new ArrayList<Menu>();
        for (String s : scopeIdList) {
            menuList.add(menuMap.get(s));
        }
        Collections.sort(menuList);
        return menuList;
    }

    public Menu getMenu(String menuId) {
        return menuMap.get(menuId);
    }

    public List<Menu> getMenus() {
        return new ArrayList<Menu>(menuMap.values());
    }


    /**
     * 获取某一级的所有子菜单，并进行排序
     *
     * @param parentId
     * @return
     */
    private List<Menu> getChildren(String parentId) {
        Menu menu = menuMap.get(parentId);
        List<Menu> childMenus = null;
        if (menu != null) {
            childMenus = menu.getChildMenus();
            if (childMenus != null) {
                Collections.sort(childMenus);
            }
        }
        return childMenus;
    }

    /**
     * 递归调用找出子菜单。
     *
     * @param parentId
     * @return
     */
    private void getChildren(String parentId, List<Menu> childList) {
        Menu menu = menuMap.get(parentId);
        if (menu != null) {
            List<Menu> childMenus = menu.getChildMenus();
            if (childMenus != null) {
                childList.addAll(childMenus);
                for (Menu m : childMenus) {
                    getChildren(m.getId(), childList);
                }
            }
        }
    }

    /**
     * 递归调用找出子菜单。
     *
     * @param parentId
     * @return
     */
    private void getChildrenWithoutParent(String parentId, List<Menu> childList) {
        Menu parentMenu = menuMap.get(parentId);
        if (parentMenu != null) {
            List<Menu> childMenus = parentMenu.getChildMenus();
            if (childMenus != null) {
                for (Menu m : childMenus) {
                    if (CollectionUtil.isEmpty(m.getChildMenus())) {
                        childList.add(m);
                    } else {
                        getChildrenWithoutParent(m.getId(), childList);
                    }
                }
            }
        }
    }


    public List<Menu> findShow(List<Menu> list) {
        if (CollectionUtil.isEmpty(list)) {
            return new ArrayList<Menu>();
        }
        Predicate<Menu> predicate = new Predicate<Menu>() {
            public boolean apply(Menu menu) {
                if (StringUtil.isBlank(menu.getIsShow())) {
                    return true;
                }
                return Menu.IS_SHOW_YES.equals(menu.getIsShow());
            }
        };
        return new ArrayList<Menu>(Collections2.filter(list, predicate));
    }
}
