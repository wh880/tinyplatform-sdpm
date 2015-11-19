package org.tinygroup.sdpm.common.docTemplate.impl;

import org.tinygroup.fileresolver.impl.AbstractFileProcessor;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.sdpm.common.docTemplate.inter.DocTemplateResolver;
import org.tinygroup.vfs.FileObject;

/**
 * Created by wangll13383 on 2015/11/18.
 */
public class DocTemplateFileProcessor extends AbstractFileProcessor {
    private static final String DOC_TEMPLATE__EXT_FILE_NAME = ".doc.xml";

    private DocTemplateResolver docTemplateResolver;

    public DocTemplateResolver getDocTemplateResolver() {
        return docTemplateResolver;
    }

    public void setDocTemplateResolver(DocTemplateResolver docTemplateResolver) {
        this.docTemplateResolver = docTemplateResolver;
    }

    public void process() {

        for (FileObject fileObject : deleteList) {
            LOGGER.logMessage(LogLevel.INFO, "正在移除docTemplate文件[{0}]",
                    fileObject.getAbsolutePath());
            FileObject file = (FileObject) caches.get(fileObject.getAbsolutePath());
            if (file != null) {
                docTemplateResolver.removeDocTemplate(file);
                caches.remove(fileObject.getAbsolutePath());
            }
            LOGGER.logMessage(LogLevel.INFO, "移除docTemplate文件[{0}]结束",
                    fileObject.getAbsolutePath());
        }
        for (FileObject fileObject : changeList) {
            LOGGER.logMessage(LogLevel.INFO, "正在加载docTemplate文件[{0}]",
                    fileObject.getAbsolutePath());
            FileObject file = (FileObject) caches.get(fileObject.getAbsolutePath());
            if (file != null) {
                docTemplateResolver.removeDocTemplate(file);
            }
            docTemplateResolver.putDocTemplate(fileObject);
            caches.put(fileObject.getAbsolutePath(), file);
            LOGGER.logMessage(LogLevel.INFO, "加载docTemplate文件[{0}]结束",
                    fileObject.getAbsolutePath());
        }
    }

    @Override
    protected boolean checkMatch(FileObject fileObject) {
        return fileObject.isInPackage() && fileObject.getFileName().endsWith(DOC_TEMPLATE__EXT_FILE_NAME);
    }
}
