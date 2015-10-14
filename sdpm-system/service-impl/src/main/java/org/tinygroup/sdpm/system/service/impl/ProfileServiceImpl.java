package org.tinygroup.sdpm.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.ProfileManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
@Component
public class ProfileServiceImpl implements ProfileService {
	@Autowired
    private ProfileManager profileManager;
	public SystemProfile add(SystemProfile systemProfile) {
		// TODO Auto-generated method stub
		return profileManager.add(systemProfile);
	}

	public int[] batchAdd(List<SystemProfile> systemProfiles) {
		// TODO Auto-generated method stub
		return profileManager.batchAdd(systemProfiles);
	}

	public List<SystemProfile> find(SystemProfile systemProfile) {
		// TODO Auto-generated method stub
		return profileManager.find(systemProfile);
	}

	public int delete(SystemProfile systemProfile) {
		// TODO Auto-generated method stub
		return profileManager.delete(systemProfile);
	}

	public SystemProfile edit(SystemProfile systemProfile) {
		// TODO Auto-generated method stub
		return profileManager.updataSystemProfile(systemProfile);
	}

}
