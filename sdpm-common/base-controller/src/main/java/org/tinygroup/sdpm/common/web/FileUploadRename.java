package org.tinygroup.sdpm.common.web;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.tinygroup.sdpm.util.UploadUtils;
import org.tinygroup.weblayer.webcontext.parser.upload.FileUploadReName;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 文件上传重命名
 * Created by Hulk on 2015/12/16.
 */
public class FileUploadRename implements FileUploadReName {
    /**
     * 上传路径
     */
    @Value("${userfiles.basedir}")
    protected String UPLOAD_PATH;

    private File repository;

    public String reName(String localFileName, HttpServletRequest request) {
        String ext = FilenameUtils.getExtension(localFileName);
        String fileName = UploadUtils.generateFilename(UPLOAD_PATH, ext);
        File destination = new File(fileName);
        UploadUtils.checkDirAndCreate(destination.getParentFile());
        return fileName;
    }

    public File getRepository() {
        return repository;
    }

    public void setRepository(File file) {
        this.repository = file;
    }
}
