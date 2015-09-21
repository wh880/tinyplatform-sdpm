package org.tinygroup.sdpm.system.service.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemConfig;

public interface ConfigService {
	
	/**
	 * 添加配置
	 * @param config
	 * @return
	 */
	SystemConfig addConfig(SystemConfig config);
	
	/**
	 * 删除
	 * @param config
	 * @return
	 */
	int deleteConfig(SystemConfig config);
	
	/**
	 * 修改
	 * @param config
	 * @return
	 */
	int updateConfig(SystemConfig config);
	
	/**
	 * 根据ID查找
	 * @param configId
	 * @return
	 */
	SystemConfig findConfig(Integer configId);
	
	/**
	 * 根据对象查找
	 * @param config
	 * @return
	 */
	List<SystemConfig> findConfigList(SystemConfig config);
}
