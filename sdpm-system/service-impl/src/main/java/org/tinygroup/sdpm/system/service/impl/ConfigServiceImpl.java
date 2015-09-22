package org.tinygroup.sdpm.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.ConfigManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemConfig;
import org.tinygroup.sdpm.system.service.inter.ConfigService;
import org.tinygroup.tinysqldsl.Pager;


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

	public int[] updateBatch(List<SystemConfig> configs) {
		return configManager.updateBatch(configs);
	}

	public List<SystemConfig> findConfigList(SystemConfig config, String columnName, boolean asc) {
		return configManager.findList(config, columnName, asc);
	}

	public Pager<SystemConfig> findConfigPager(int start, int limit, SystemConfig config, String columnName,
			boolean asc) {
		return configManager.findPager(start, limit, config, columnName, asc);
	}



}
