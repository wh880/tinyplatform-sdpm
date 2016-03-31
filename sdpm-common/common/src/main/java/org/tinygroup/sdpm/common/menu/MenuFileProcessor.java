package org.tinygroup.sdpm.common.menu;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.mapper.DefaultMapper;
import org.springframework.stereotype.Component;
import org.tinygroup.fileresolver.impl.AbstractFileProcessor;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;
import org.tinygroup.sdpm.common.menu.xstream.MapCustomConverter;
import org.tinygroup.vfs.FileObject;
import org.tinygroup.xstream.XStreamFactory;

import java.util.Map;

/**
 * Created by Hulk on 2015/8/27.
 */
@Component
public class MenuFileProcessor extends AbstractFileProcessor {
    private static final String MENU_EXT_FILENAME = ".menu.xml";
    private static Logger logger = LoggerFactory
            .getLogger(MenuFileProcessor.class);
    private static XStream stream;

    static {
        stream = XStreamFactory.getXStream(MenuManager.XSTREAN_PACKAGE_NAME);
        stream.registerConverter(new MapCustomConverter(new DefaultMapper(MenuFileProcessor.class.getClassLoader())));
        stream.alias("property", Map.Entry.class);
    }

    private MenuManager menuManager;

    public void process() {
        for (FileObject fileObject : deleteList) {
            logger.logMessage(LogLevel.INFO, "正在删除菜单menu文件[{0}]",
                    fileObject.getAbsolutePath());
            Menu menu = (Menu) caches.remove(fileObject.getAbsolutePath());
            if (menu != null) {
                menuManager.removeMenu(menu.getId());
                caches.remove(fileObject.getAbsolutePath());
            }
            logger.logMessage(LogLevel.INFO, "删除逻辑菜单menu文件[{0}]结束",
                    fileObject.getAbsolutePath());
        }
        for (FileObject fileObject : changeList) {
            logger.logMessage(LogLevel.INFO, "正在读取菜单menu文件[{0}]",
                    fileObject.getAbsolutePath());
            Menu oldMenu = (Menu) caches.get(fileObject.getAbsolutePath());
            if (oldMenu != null) {
                menuManager.removeMenu(oldMenu.getId());
            }
            Menu menu = null;
            try {
                menu = (Menu) stream.fromXML(fileObject.getInputStream());
            } catch (Exception e) {
                logger.logMessage(LogLevel.ERROR, "加载逻辑菜单.menu.xml文件[{0}]错误", e, fileObject.getAbsolutePath());
            }
            if (menu != null) {
                menuManager.addMenu(menu, fileObject.getAbsolutePath());
                menuManager.addMenuToParent(menu, fileObject.getAbsolutePath());
                caches.put(fileObject.getAbsolutePath(), menu);
                logger.logMessage(LogLevel.INFO, "读取菜单menu文件[{0}]结束",
                        fileObject.getAbsolutePath());
            }
        }
    }

    @Override
    protected boolean checkMatch(FileObject fileObject) {
        return fileObject.isInPackage() && fileObject.getFileName().toLowerCase().endsWith(MENU_EXT_FILENAME);
    }

    public MenuManager getMenuManager() {
        return menuManager;
    }

    public void setMenuManager(MenuManager menuManager) {
        this.menuManager = menuManager;
    }
}
