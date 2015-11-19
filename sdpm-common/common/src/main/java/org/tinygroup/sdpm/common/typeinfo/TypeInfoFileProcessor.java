package org.tinygroup.sdpm.common.typeinfo;

import com.thoughtworks.xstream.XStream;
import org.tinygroup.fileresolver.impl.AbstractFileProcessor;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.vfs.FileObject;
import org.tinygroup.xstream.XStreamFactory;

/**
 * Created by wangll13383 on 2015/9/6.
 */
public class TypeInfoFileProcessor extends AbstractFileProcessor {
    private static final String TYPEINFO_EXT_FILE_NAME = ".typeinfo.xml";

    private TypeInfoResolver typeInfoResolver;

    public TypeInfoResolver getTypeInfoResolver() {
        return typeInfoResolver;
    }

    public void setTypeInfoResolver(TypeInfoResolver typeInfoResolver) {
        this.typeInfoResolver = typeInfoResolver;
    }

    public void process() {
        XStream stream = XStreamFactory
                .getXStream(TypeInfoResolverImpl.TYPEINFO_XSTREAM_);
        for (FileObject fileObject : deleteList) {
            LOGGER.logMessage(LogLevel.INFO, "正在移除typeInfo文件[{0}]",
                    fileObject.getAbsolutePath());
            TypeInfos typeinfos = (TypeInfos) caches.get(fileObject.getAbsolutePath());
            if (typeinfos != null) {
                typeInfoResolver.removeTypeInfo(typeinfos);
                caches.remove(fileObject.getAbsolutePath());
            }
            LOGGER.logMessage(LogLevel.INFO, "移除typeInfo文件[{0}]结束",
                    fileObject.getAbsolutePath());
        }
        for (FileObject fileObject : changeList) {
            LOGGER.logMessage(LogLevel.INFO, "正在加载typeInfo文件[{0}]",
                    fileObject.getAbsolutePath());
            TypeInfos oldTypeinfos = (TypeInfos) caches.get(fileObject.getAbsolutePath());
            if (oldTypeinfos != null) {
                typeInfoResolver.removeTypeInfo(oldTypeinfos);
            }
            TypeInfos typeinfos = (TypeInfos) stream
                    .fromXML(fileObject.getInputStream());
            typeInfoResolver.addTypeInfo(typeinfos, fileObject.getAbsolutePath());
            caches.put(fileObject.getAbsolutePath(), typeinfos);
            LOGGER.logMessage(LogLevel.INFO, "加载typeInfo文件[{0}]结束",
                    fileObject.getAbsolutePath());
        }
    }

    @Override
    protected boolean checkMatch(FileObject fileObject) {
        return fileObject.isInPackage() && fileObject.getFileName().endsWith(TYPEINFO_EXT_FILE_NAME);
    }
}
