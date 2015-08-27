package org.tinygroup.sdpm.menu;

import org.tinygroup.fileresolver.impl.AbstractFileProcessor;
import org.tinygroup.vfs.FileObject;

/**
 * Created by Hulk on 2015/8/27.
 */
public class MenuFileProcessor extends AbstractFileProcessor {
    private static final String MENU_EXT_FILENAME = ".menu.xml";

    public void process() {

    }

    @Override
    protected boolean checkMatch(FileObject fileObject) {
        return fileObject.getFileName().toLowerCase().endsWith(MENU_EXT_FILENAME);
    }

}
