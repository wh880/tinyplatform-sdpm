package org.tinygroup.sdpm.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.ConfigManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemConfig;


@Component("configService")
public class ConfigServiceImpl implements ConfigService {
	
	@Autowired
	private ConfigManager configManager;
	
	public SystemConfig addConfig(SystemConfig config) {

		return configManager.add(config);
	}

	public int deleteConfig(Integer configId) {
		
		return configManager.delete(configId);
	}

	public int updateConfig(SystemConfig config) {

		return configManager.update(config);
	}

	public SystemConfig findConfig(Integer configId) {
		
		return configManager.find(configId);
	}

	public List<SystemConfig> findConfigList(SystemConfig config) {
		
		return configManager.findList(config);
	}


}
