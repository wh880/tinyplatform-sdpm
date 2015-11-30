
package org.tinygroup.sdpm.system.biz.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.system.biz.inter.ConfigManager;
import org.tinygroup.sdpm.system.dao.SystemConfigDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemConfig;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;


@Service
@Transactional
public class ConfigManagerImpl implements ConfigManager{
    @Autowired
    private SystemConfigDao configDao;

    public SystemConfig add(SystemConfig config){
        return configDao.add(config);
    }

    public int delete(Integer configId){
        SystemConfig config=new SystemConfig();
        config.setConfigId(configId);
        config.setDeleted(SystemConfig.DELETE_YES);
        return  configDao.edit(config);
    }

    public int update(SystemConfig config){
        return configDao.edit(config);
    }

    public  SystemConfig find(Integer configId){
        return  configDao.getByKey(configId);
    }

	public int[] updateBatch(List<SystemConfig> configs) {
		
		return configDao.batchUpdate(configs);
	}

	public List<SystemConfig> findList(SystemConfig config, String columnName, boolean asc) {
		
		return configDao.query(config, new OrderBy(columnName, asc));
	}

	public Pager<SystemConfig> findPager(int start, int limit, SystemConfig config, String columnName,
			boolean asc) {
		
		return configDao.queryPager(start, limit, config,  new OrderBy(columnName, asc));
	}



}
