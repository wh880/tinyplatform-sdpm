package org.tinygroup.sdpm.action.system;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.commons.tools.StringUtil;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

/**
 * 文件存储
 */
@Service
public class FileRepository implements ServletContextAware {
    private Logger log = LoggerFactory.getLogger(FileRepository.class);
    private ServletContext ctx;

    public String resolverFilePath(String filePath, String separator) {
        String path = StringUtil.substringAfterLast(filePath, separator);
        return StringUtil.replace(path, "\\", "/");
    }

    public String storeByExt(String path, String ext, MultipartFile file)
            throws IOException {
        String filename = UploadUtils.generateFilename(path, ext);
        File dest = new File(filename);
        dest = UploadUtils.getUniqueFile(dest);
        store(file, dest);
        return filename;
    }

    public String storeByFilename(String filename, MultipartFile file)
            throws IOException {
        File dest = new File(getRealPath(filename));
        store(file, dest);
        return filename;
    }

    public String storeByExt(String path, String ext, File file)
            throws IOException {
        String filename = UploadUtils.generateFilename(path, ext);
        File dest = new File(getRealPath(filename));
        dest = UploadUtils.getUniqueFile(dest);
        store(file, dest);
        return filename;
    }

    public String storeByFilename(String filename, File file)
            throws IOException {
        File dest = new File(getRealPath(filename));
        store(file, dest);
        return filename;
    }

    private void store(MultipartFile file, File dest) throws IOException {
        try {
            UploadUtils.checkDirAndCreate(dest.getParentFile());
            file.transferTo(dest);
        } catch (IOException e) {
            log.error("Transfer file error when upload file", e);
            throw e;
        }
    }

    private void store(File file, File dest) throws IOException {
        try {
            UploadUtils.checkDirAndCreate(dest.getParentFile());
            FileUtils.copyFile(file, dest);
        } catch (IOException e) {
            log.error("Transfer file error when upload file", e);
            throw e;
        }
    }

    public File retrieve(String name) {
        return new File(ctx.getRealPath(name));
    }

    private String getRealPath(String name) {
        String realPath = ctx.getRealPath(name);
        if (realPath == null) {
            realPath = ctx.getRealPath("/") + name;
        }
        return realPath;
    }

    public void setServletContext(ServletContext servletContext) {
        ctx = servletContext;
    }
}
