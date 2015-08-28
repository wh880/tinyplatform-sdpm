package org.tinygroup.sdpm.menu;

import com.thoughtworks.xstream.XStream;
import org.tinygroup.fileresolver.impl.AbstractFileProcessor;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;
import org.tinygroup.vfs.FileObject;
import org.tinygroup.xstream.XStreamFactory;

/**
 * Created by Hulk on 2015/8/27.
 */
public class MenuFileProcessor extends AbstractFileProcessor {
    private static final String MENU_EXT_FILENAME = ".menu.xml";
    private static Logger logger = LoggerFactory
            .getLogger(MenuFileProcessor.class);
    private MenuManager menuManager;

    public void process() {
        {
            XStream stream = XStreamFactory.getXStream(MenuManager.XSTREAN_PACKAGE_NAME);
            for (FileObject fileObject : deleteList) {
                logger.logMessage(LogLevel.INFO, "正在删除菜单menu文件[{0}]",
                        fileObject.getAbsolutePath());
                Menu menu = (Menu) caches.remove(fileObject.getAbsolutePath());
                if (menu != null) {
                    menuManager.removeMenu(menu.getName());
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
                    menuManager.removeMenu(oldMenu.getName());
                }
                Menu menu = (Menu) stream.fromXML(fileObject.getInputStream());
                menuManager.addMenu(menu);
                caches.put(fileObject.getAbsolutePath(), menu);
                logger.logMessage(LogLevel.INFO, "读取菜单menu文件[{0}]结束",
                        fileObject.getAbsolutePath());
            }
        }

    }

    @Override
    protected boolean checkMatch(FileObject fileObject) {
        return fileObject.getFileName().toLowerCase().endsWith(MENU_EXT_FILENAME);
    }

}
