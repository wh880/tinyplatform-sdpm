package org.tinygroup.sdpm.dto;

import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;

import java.util.List;

/**
 * Created by Hulk on 2015/11/24.
 */
public class UploadProfile {

    MultipartFile[] newUploadFile;
    String[] newUploadFileTitle;

    List<MultipartFile> updateFileList;
    List<SystemProfile> updateProfileList;


    public MultipartFile[] getNewUploadFile() {
        return newUploadFile;
    }

    public void setNewUploadFile(MultipartFile[] newUploadFile) {
        this.newUploadFile = newUploadFile;
    }

    public String[] getNewUploadFileTitle() {
        return newUploadFileTitle;
    }

    public void setNewUploadFileTitle(String[] newUploadFileTitle) {
        this.newUploadFileTitle = newUploadFileTitle;
    }

    public List<SystemProfile> getUpdateProfileList() {
        return updateProfileList;
    }

    public void setUpdateProfileList(List<SystemProfile> updateProfileList) {
        this.updateProfileList = updateProfileList;
    }

    public List<MultipartFile> getUpdateFileList() {
        return updateFileList;
    }

    public void setUpdateFileList(List<MultipartFile> updateFileList) {
        this.updateFileList = updateFileList;
    }
}
