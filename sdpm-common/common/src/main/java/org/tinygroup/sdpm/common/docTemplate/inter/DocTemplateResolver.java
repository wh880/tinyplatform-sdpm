package org.tinygroup.sdpm.common.docTemplate.inter;

import org.tinygroup.vfs.FileObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangll13383 on 2015/11/18.
 */
public interface DocTemplateResolver {
    String RELEASE = "release";

    void putDocTemplate(FileObject fileObject);

    FileObject getDocTemplate(String type);

    void removeDocTemplate(FileObject fileObject);
}
