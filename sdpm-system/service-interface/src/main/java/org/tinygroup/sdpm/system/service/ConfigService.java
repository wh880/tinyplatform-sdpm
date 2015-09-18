package org.tinygroup.sdpm.system.service;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.Config;

public interface ConfigService {
	
	/**
	 * 添加配置
	 * @param config
	 * @return
	 */
	Config add(Config config);
	
	/**
	 * 删除
	 * @param config
	 * @return
	 */
	int delete(Config config);
	
	/**
	 * 修改
	 * @param config
	 * @return
	 */
	int update(Config config);
	
	/**
	 * 根据ID查找
	 * @param configId
	 * @return
	 */
	Config findById(Integer configId);
	
	/**
	 * 根据对象查找
	 * @param config
	 * @return
	 */
	List<Config> findConfig(Config config);
}
