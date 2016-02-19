package org.tinygroup.sdpm;

import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.springmvc.multipart.TinyMultipartFile;

import java.util.List;

/**
 * Created by Hulk on 2015/11/24.
 */
public class UploadProfile {

    TinyMultipartFile[] newUploadFile;
    String[] newUploadFileTitle;

    List<TinyMultipartFile> updateFileList;
    List<SystemProfile> updateProfileList;


    public TinyMultipartFile[] getNewUploadFile() {
        return newUploadFile;
    }

    public void setNewUploadFile(TinyMultipartFile[] newUploadFile) {
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

    public List<TinyMultipartFile> getUpdateFileList() {
        return updateFileList;
    }

    public void setUpdateFileList(List<TinyMultipartFile> updateFileList) {
        this.updateFileList = updateFileList;
    }
}
