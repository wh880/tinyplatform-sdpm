package org.tinygroup.sdpm.system.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.ArrayUtil;
import org.tinygroup.commons.tools.CollectionUtil;
import org.tinygroup.sdpm.system.biz.inter.ProfileManager;
import org.tinygroup.sdpm.system.dao.SystemProfileDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;

import java.util.List;

@Service
public class ProfileManagerImpl implements ProfileManager {
    @Autowired
    private SystemProfileDao systemProfileDao;

    public SystemProfile add(SystemProfile systemProfile) {
        return systemProfileDao.add(systemProfile);
    }

    public int[] batchAdd(List<SystemProfile> systemProfiles) {
        return systemProfileDao.batchInsert(systemProfiles);
    }

    public List<SystemProfile> find(SystemProfile systemProfile) {
        return systemProfileDao.query(systemProfile);
    }

    public Integer delete(SystemProfile systemProfile) {
        int pk = systemProfile.getFileId();
        return systemProfileDao.deleteByKey(pk);
    }

    public SystemProfile updateSystemProfile(SystemProfile systemProfile) {
        systemProfileDao.edit(systemProfile);
        return systemProfile;
    }


    public Integer batchUpdateSystemProfile(List<SystemProfile> list) {
        if (CollectionUtil.isEmpty(list)) {
            return 0;
        }
        int[] update = systemProfileDao.batchUpdate(list);
        if (ArrayUtil.isEmptyArray(update)) {
            return 0;
        } else {
            return update.length;
        }
    }

    public SystemProfile editFileTitle(SystemProfile systemProfile) {
        systemProfileDao.editTitle(systemProfile);
        return systemProfile;
    }

    public Integer softDelete(Integer id) {
        return systemProfileDao.softDelete(id);
    }

    public SystemProfile findById(Integer id) {
        return systemProfileDao.getByKey(id);
    }

}
