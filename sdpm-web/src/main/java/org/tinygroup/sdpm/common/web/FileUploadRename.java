package org.tinygroup.sdpm.common.web;

import org.tinygroup.weblayer.webcontext.parser.upload.FileUploadReName;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Created by Hulk on 2015/12/16.
 */
public class FileUploadRename implements FileUploadReName {
    private File repository;

    public String reName(String s, HttpServletRequest httpServletRequest) {
        return null;
    }

    public File getRepository() {
        return repository;
    }

    public void setRepository(File file) {
        this.repository = file;
    }
}
