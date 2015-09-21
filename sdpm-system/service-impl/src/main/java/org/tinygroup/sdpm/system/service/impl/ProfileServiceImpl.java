package org.tinygroup.sdpm.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.ProfileManager;
import org.tinygroup.sdpm.system.dao.pojo.Profile;
import org.tinygroup.sdpm.system.service.inter.ProfileService;
@Component("profileService")
public class ProfileServiceImpl implements ProfileService {
	@Autowired
    private ProfileManager profileManager;
	public Profile add(Profile Profile) {
		// TODO Auto-generated method stub
		return profileManager.add(Profile);
	}

	public int[] batchAdd(List<Profile> Profiles) {
		// TODO Auto-generated method stub
		return profileManager.batchAdd(Profiles);
	}

	public List<Profile> find(Profile Profile) {
		// TODO Auto-generated method stub
		return profileManager.find(Profile);
	}

	public int delete(Profile Profile) {
		// TODO Auto-generated method stub
		return profileManager.delete(Profile);
	}

	public Profile edit(Profile Profile) {
		// TODO Auto-generated method stub
		return profileManager.updataProfile(Profile);
	}

}
