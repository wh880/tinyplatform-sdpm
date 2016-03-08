package org.tinygroup.sdpm.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.ConfigManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemConfig;
import org.tinygroup.sdpm.system.service.inter.ConfigService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by wangll13383 on 2015/12/18.
 */
@Component
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    ConfigManager configManager;

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

    public List<SystemConfig> findConfigList() {
        return configManager.findList(null, null, null);
    }

    public Pager<SystemConfig> findConfigPager(int start, int limit, SystemConfig config, String columnName, Boolean asc) {
        return configManager.findPager(start, limit, config, columnName, asc);
    }

    public SystemConfig getConfigBySection(String section) {
        return configManager.getConfigBySection(section);
    }
}
