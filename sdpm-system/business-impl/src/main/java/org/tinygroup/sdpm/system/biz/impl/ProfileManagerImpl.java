package org.tinygroup.sdpm.system.biz.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.system.biz.inter.ProfileManager;
import org.tinygroup.sdpm.system.dao.ProfileDao;
import org.tinygroup.sdpm.system.dao.pojo.Profile;
@Service
@Transactional
public class ProfileManagerImpl implements ProfileManager {
	@Autowired
    private ProfileDao profileDao;
	public Profile add(Profile Profile) {
		// TODO Auto-generated method stub
		return profileDao.add(Profile);
	}

	public int[] batchAdd(List<Profile> Profiles) {
		// TODO Auto-generated method stub
		return profileDao.batchInsert(Profiles);
	}

	public List<Profile> find(Profile Profile) {
		// TODO Auto-generated method stub
		return profileDao.query(Profile);
	}

	public Integer delete(Profile Profile) {
		// TODO Auto-generated method stub
		int pk=Profile.getFileId();
		return profileDao.deleteByKey(pk);
	}

	public Profile updataProfile(Profile Profile) {
		// TODO Auto-generated method stub 
		profileDao.edit(Profile);
		return Profile;
	}

}
