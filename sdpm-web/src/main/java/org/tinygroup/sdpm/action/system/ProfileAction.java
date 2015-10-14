package org.tinygroup.sdpm.action.system;

import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.weblayer.WebContext;
@Controller
@RequestMapping("system/profile")
public class ProfileAction extends BaseController{
	 private static final String UPLOAD_PATH = "/root/develop/sdpm";
	    @Autowired
	    private FileRepository fileRepository;
	    @Autowired
	    private ProfileService profileService;
	    @RequestMapping("upload")
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
        profileService.add(profile);
        
	}
	
}
