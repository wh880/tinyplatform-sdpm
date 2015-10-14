package org.tinygroup.sdpm.action.system;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.impl.ProfileServiceImpl;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.weblayer.WebContext;

public class ProfileAction extends BaseController{
	 private static final String UPLOAD_PATH = "E:/file";
	
	    private final FileRepository fileRepository = BeanContainerFactory.getBeanContainer(ProfileAction.class.getClassLoader()).getBean(FileRepository.class);
       //暂时调用实现，后面应调用包装
	    private ProfileService profileService=BeanContainerFactory.getBeanContainer(ProfileAction.class.getClassLoader()).getBean(ProfileServiceImpl.class);
	
	   public void upload( MultipartFile upfile,int id,String type,String title) throws IOException{
		
		String origName = upfile.getOriginalFilename();
        String ext = FilenameUtils.getExtension(origName);
        String fileUrl = fileRepository.storeByExt(UPLOAD_PATH, origName, upfile);
        long size= upfile.getSize();
        SystemProfile profile = new SystemProfile();
        profile.setFileAddedDate(new Date());
        profile.setFileObjectId(id);
        profile.setFileTitle(title);
        profile.setFileObjectType(type);
        profile.setFileExtension(ext);
        profile.setFilePathname(fileUrl);
        profile.setFileSize((int)size);
        profileService.add(profile);
        
	}
	
}
