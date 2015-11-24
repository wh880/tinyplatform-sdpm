package org.tinygroup.sdpm.dto;

import org.springframework.web.multipart.MultipartFile;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;

import java.util.List;

/**
 * Created by Hulk on 2015/11/24.
 */
public class UploadProfile {
    List<MultipartFile> updateFileList;
    List<SystemProfile> updateProfileList;

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
