package org.tinygroup.sdpm.system.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.system.biz.inter.ProfileManager;
import org.tinygroup.sdpm.system.dao.SystemProfileDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
@Service
@Transactional
public class ProfileManagerImpl implements ProfileManager {
	@Autowired
    private SystemProfileDao systemProfileDao;
	public SystemProfile add(SystemProfile systemProfile) {
		// TODO Auto-generated method stub
		return systemProfileDao.add(systemProfile);
	}

	public int[] batchAdd(List<SystemProfile> systemProfiles) {
		// TODO Auto-generated method stub
		return systemProfileDao.batchInsert(systemProfiles);
	}

	public List<SystemProfile> find(SystemProfile systemProfile) {
		// TODO Auto-generated method stub
		return systemProfileDao.query(systemProfile);
	}

	public Integer delete(SystemProfile systemProfile) {
		// TODO Auto-generated method stub
		int pk=systemProfile.getFileId();
		return systemProfileDao.deleteByKey(pk);
	}

	public SystemProfile updataSystemProfile(SystemProfile systemProfile) {
		// TODO Auto-generated method stub 
		systemProfileDao.edit(systemProfile);
		return systemProfile;
	}

	public SystemProfile editFileTitle(SystemProfile systemProfile) {
		// TODO Auto-generated method stub
		systemProfileDao.editTitle(systemProfile);
		return systemProfile;
	}

	public Integer softDelete(Integer id) {
		// TODO Auto-generated method stub
		return systemProfileDao.softDelete(id);
	}

	public SystemProfile findById(Integer id) {
		// TODO Auto-generated method stub
		return systemProfileDao.getByKey(id);
	}

}
