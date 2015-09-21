
package org.tinygroup.sdpm.system.biz.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.sdpm.system.dao.SystemConfigDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemConfig;


@Service
@Transactionala
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

    public 	List<SystemConfig> findList(SystemConfig config){
         return  configDao.query(config);
    }



}
