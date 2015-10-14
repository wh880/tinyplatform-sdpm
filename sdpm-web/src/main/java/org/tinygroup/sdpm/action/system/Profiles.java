package org.tinygroup.sdpm.action.system;

import java.util.ArrayList;
import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemProfile;

public class Profiles {
     private List<SystemProfile> profiles ;

	public List<SystemProfile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<SystemProfile> profiles) {
		this.profiles = profiles;
	}
}
