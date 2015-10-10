package org.tinygroup.sdpm.action.system;

import java.io.File;
import java.io.IOException;

import org.apache.commons.fileupload.DefaultFileItem;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.tinysqldsl.expression.relational.OldOracleJoinBinaryExpression;
import org.tinygroup.weblayer.WebContext;
import org.tinygroup.weblayer.webcontext.parser.fileupload.TinyFileItem;

public class ProfileAction extends BaseController{
	 private static final String UPLOAD_PATH = "/root/develop/sdpm";
	    @Autowired
	    private FileRepository fileRepository;
	public void upload( MultipartFile upfile) throws IOException{
		SystemProfile profile = new SystemProfile();
		String origName = upfile.getOriginalFilename();
        String ext = FilenameUtils.getExtension(origName);
        String fileUrl = fileRepository.storeByExt(UPLOAD_PATH, origName, upfile);
        long size= upfile.getSize();
        profile.setFileTitle(origName);
        profile.setFileExtension(ext);
        profile.setFilePathname(fileUrl);
        profile.setFileSize((int)size);
	}
	
}
