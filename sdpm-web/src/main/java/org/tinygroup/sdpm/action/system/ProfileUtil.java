package org.tinygroup.sdpm.action.system;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.sdpm.common.web.BaseController;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.impl.ProfileServiceImpl;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
import org.tinygroup.sdpm.util.UserUtils;

public class ProfileUtil extends BaseController{
	 private static final String UPLOAD_PATH = "E:/file";
	
	    private final FileRepository fileRepository = BeanContainerFactory.getBeanContainer(ProfileUtil.class.getClassLoader()).getBean(FileRepository.class);
       //暂时调用实现，后面应调用包装
	    private ProfileService profileService=BeanContainerFactory.getBeanContainer(ProfileUtil.class.getClassLoader()).getBean(ProfileServiceImpl.class);
	   /**
	    * 添加单个附件
	    * @param upfile
	    * @param id
	    * @param type
	    * @param title
	    * @throws IOException
	    */
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
        profile.setFileAddedBy(UserUtils.getUserAccount());
        profile.setFileDeleted(String.valueOf(0));
        profileService.add(profile);
        
	}
	  /**
	   * 添加多个附件
	   * @param upfiles
	   * @param id
	   * @param type
	   * @param title
	   * @throws IOException
	   */
	   public void uploads(MultipartFile[] upfiles,int id,String type,String[] title) throws IOException{
		   for(int i=0,n=upfiles.length;i<n;i++){
			   if(!upfiles[i].isEmpty()&&upfiles[i].getSize()>0)
			   upload(upfiles[i], id, type, title[i]);
		   }
	   }
	   /**
	    * 添加附件没有标题
	    * @param upfile
	    * @param id
	    * @param type
	 * @throws IOException 
	    */
	   public void uploadNoTitle(MultipartFile upfile,int id,String type) throws IOException{
		   String origName = upfile.getOriginalFilename();
	        String ext = FilenameUtils.getExtension(origName);
	        String fileUrl = fileRepository.storeByExt(UPLOAD_PATH, origName, upfile);
	        long size= upfile.getSize();
	        SystemProfile profile = new SystemProfile();
	        profile.setFileAddedDate(new Date());
	        profile.setFileObjectId(id); 
	        profile.setFileObjectType(type);
	        profile.setFileExtension(ext);
	        profile.setFilePathname(fileUrl);
	        profile.setFileSize((int)size);
	        profile.setFileAddedBy(UserUtils.getUserAccount());
	        profile.setFileDeleted(String.valueOf(0));
	        profileService.add(profile);
	   }
	   /**
	    * 添加多个无标题附件附件
	    * @param upfile
	    * @param id
	    * @param type
	    */
	   public void uploadNoTitles(MultipartFile[] upfile,int id,String type){
		   for(int i=0,n=upfile.length;i<n;i++){
			   if(!upfile[i].isEmpty()&&upfile[i].getSize()>0){
				   try {
					uploadNoTitle(upfile[i], id, type);
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			   }
		   }
	   }
	
}
