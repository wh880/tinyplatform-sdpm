package org.tinygroup.sdpm.common.docTemplate.impl;

import org.tinygroup.sdpm.common.docTemplate.inter.DocTemplateResolver;
import org.tinygroup.template.TemplateEngine;
import org.tinygroup.template.loader.FileObjectResourceLoader;
import org.tinygroup.vfs.FileObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangll13383 on 2015/11/18.
 */
public class DocTemplateResolverImpl implements DocTemplateResolver {
    private static Map<String, FileObject> docTemplateMap = new HashMap<String, FileObject>();
    private TemplateEngine docTemplateEngine;

    public TemplateEngine getDocTemplateEngine() {
        return docTemplateEngine;
    }

    public void setDocTemplateEngine(TemplateEngine docTemplateEngine) {
        this.docTemplateEngine = docTemplateEngine;
    }

    public void putDocTemplate(FileObject fileObject) {
        FileObjectResourceLoader loader = new FileObjectResourceLoader("xml", "layout", null, fileObject.getParent().getAbsolutePath());
        docTemplateEngine.addResourceLoader(loader);
        String[] name = fileObject.getFileName().split("\\.");
        docTemplateMap.put(name[0], fileObject);
    }

    public FileObject getDocTemplate(String type) {
        return docTemplateMap.get(type);
    }

    public void removeDocTemplate(FileObject fileObject) {
        String[] name = fileObject.getFileName().split(".");
        docTemplateMap.remove(name);
    }
}
