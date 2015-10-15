package org.tinygroup.sdpm.common.log.obtain.processor;

import com.thoughtworks.xstream.XStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.tinygroup.fileresolver.impl.AbstractFileProcessor;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.sdpm.common.log.obtain.inter.ObtainHandle;
import org.tinygroup.sdpm.common.log.obtain.inter.Obtains;
import org.tinygroup.vfs.FileObject;
import org.tinygroup.xstream.XStreamFactory;

/**
 * Created by wangll13383 on 2015/9/16.
 */
public class ObtainFileProcessor extends AbstractFileProcessor {

    private static final String OBTAIN_EXT_FILE_NAME = ".obtain.xml";
    @Autowired
    private ObtainHandle obtainHandleImpl;

//    public ObtainHandle getObtainHandle() {
//        return obtainHandle;
//    }
//
//    public void setObtainHandle(ObtainHandle obtainHandle) {
//        this.obtainHandle = obtainHandle;
//    }

    public void process() {
        XStream stream = XStreamFactory
                .getXStream(ObtainHandle.OBTAIN_XSTREAM);
        for (FileObject fileObject : deleteList) {
            LOGGER.logMessage(LogLevel.INFO, "正在移除obtain文件[{0}]",
                    fileObject.getAbsolutePath());
            Obtains obtains = (Obtains)caches.get(fileObject.getAbsolutePath());
            if(obtains!=null){
                obtainHandleImpl.removeObtains(obtains);
                caches.remove(fileObject.getAbsolutePath());
            }            LOGGER.logMessage(LogLevel.INFO, "移除obtain文件[{0}]结束",
                    fileObject.getAbsolutePath());
        }
        for (FileObject fileObject : changeList) {
            LOGGER.logMessage(LogLevel.INFO, "正在加载obtain文件[{0}]",
                    fileObject.getAbsolutePath());
            Obtains oldObtains = (Obtains)caches.get(fileObject.getAbsolutePath());
            if(oldObtains!=null){
                obtainHandleImpl.removeObtains(oldObtains);
            }
            Obtains obtains = (Obtains) stream
                    .fromXML(fileObject.getInputStream());
            obtainHandleImpl.addObtain(obtains,fileObject.getAbsolutePath());
            caches.put(fileObject.getAbsolutePath(), obtains);
            LOGGER.logMessage(LogLevel.INFO, "加载obtain文件[{0}]结束",
                    fileObject.getAbsolutePath());
        }
    }

    @Override
    protected boolean checkMatch(FileObject fileObject) {
        return fileObject.getFileName().endsWith(OBTAIN_EXT_FILE_NAME) || fileObject.getFileName().endsWith(".obtain");
    }

}
