package org.tinygroup.sdpm.dict.processor;

import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.tinygroup.fileresolver.impl.AbstractFileProcessor;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.sdpm.dict.inter.DictHandle;
import org.tinygroup.sdpm.dict.inter.DictNodeEntries;
import org.tinygroup.sdpm.dict.util.DictUtil;
import org.tinygroup.vfs.FileObject;
import org.tinygroup.xstream.XStreamFactory;

/**
 * Created by wangll13383 on 2015/10/15.
 */
public class DictFileProcessor extends AbstractFileProcessor {
    private static final String DICT_EXT_FILE_NAME = ".dict.xml";
    @Autowired
    private DictHandle dictHandle;
    private boolean isScan =false;

    public boolean isScan() {
        return isScan;
    }

    public void setScan(boolean isScan) {
        this.isScan = isScan;
    }

    @Override
    protected boolean checkMatch(FileObject fileObject) {
        return fileObject.getFileName().endsWith(DICT_EXT_FILE_NAME) || fileObject.getFileName().endsWith(".dict");
    }

    public void process() {
        if(isScan) {
            XStream stream = XStreamFactory
                    .getXStream(DictHandle.DICT_XSTREAM);
            for (FileObject fileObject : deleteList) {
                LOGGER.logMessage(LogLevel.INFO, "正在移除dict文件[{0}]",
                        fileObject.getAbsolutePath());
                DictNodeEntries dictNodeEntries = (DictNodeEntries) caches.get(fileObject.getAbsolutePath());
                if (dictNodeEntries != null) {
                    dictHandle.removeDictNodeEntries(dictNodeEntries);
                    caches.remove(fileObject.getAbsolutePath());
                }
                LOGGER.logMessage(LogLevel.INFO, "移除dict文件[{0}]结束",
                        fileObject.getAbsolutePath());
            }
            for (FileObject fileObject : changeList) {
                LOGGER.logMessage(LogLevel.INFO, "正在加载dict文件[{0}]",
                        fileObject.getAbsolutePath());
                DictNodeEntries dictNodeEntries = (DictNodeEntries) caches.get(fileObject.getAbsolutePath());
                if (dictNodeEntries != null) {
                    dictHandle.removeDictNodeEntries(dictNodeEntries);
                }

                dictNodeEntries = (DictNodeEntries) stream
                        .fromXML(fileObject.getInputStream());
                try {
                    dictHandle.addDictEntry(dictNodeEntries, fileObject.getAbsolutePath());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                caches.put(fileObject.getAbsolutePath(), dictNodeEntries);
                LOGGER.logMessage(LogLevel.INFO, "加载dict文件[{0}]结束",
                        fileObject.getAbsolutePath());
            }
            dictHandle.insertDictNodeEntry();
        }else{
            DictUtil.mergeDict();
        }
    }
}
