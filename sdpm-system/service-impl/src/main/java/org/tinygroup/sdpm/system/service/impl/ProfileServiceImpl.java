package org.tinygroup.sdpm.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.sdpm.system.biz.inter.ProfileManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ProfileService;

import java.util.List;

@Component
@Transactional
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileManager profileManager;

    public SystemProfile addSystemProfile(SystemProfile systemProfile) {
        return profileManager.add(systemProfile);
    }

    public Integer batchAddSystemProfile(List<SystemProfile> systemProfiles) {
        int[] len = profileManager.batchAdd(systemProfiles);
        if (ArrayUtil.isEmptyArray(len)) {
            return 0;
        }
        return len.length;
    }

    public Integer batchUpdateSystemProfile(List<SystemProfile> list) {
        return profileManager.batchUpdateSystemProfile(list);
    }
    @Transactional(readOnly = true)
    public List<SystemProfile> findSystemProfile(SystemProfile systemProfile) {
        return profileManager.find(systemProfile);
    }

    public Integer deleteSystemProfile(SystemProfile systemProfile) {
        return profileManager.delete(systemProfile);
    }

    public SystemProfile editSystemProfile(SystemProfile systemProfile) {
        return profileManager.updateSystemProfile(systemProfile);
    }

    public SystemProfile editSystemProfileTitle(SystemProfile systemProfile) {
        return profileManager.editFileTitle(systemProfile);
    }

    public Integer softDeleteSystemProfile(Integer id) {
        return profileManager.softDelete(id);
    }
    @Transactional(readOnly = true)
    public SystemProfile findSystemProfileById(Integer id) {
        return profileManager.findById(id);
    }

}
