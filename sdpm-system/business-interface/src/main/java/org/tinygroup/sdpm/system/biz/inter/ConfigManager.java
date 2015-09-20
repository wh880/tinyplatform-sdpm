package org.tinygroup.sdpm.system.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemConfig;

public interface ConfigManager {
	
	/**
	 * 添加配置
	 * @param config
	 * @return
	 */
	SystemConfig add(SystemConfig config);
	
	/**
	 * 删除
	 * @param config
	 * @return
	 */
	int delete(Integer configId);
	
	/**
	 * 修改
	 * @param config
	 * @return
	 */
	int update(SystemConfig config);
	
	/**
	 * 根据ID查找
	 * @param configId
	 * @return
	 */
	SystemConfig find(Integer configId);
	
	/**
	 * 根据对象查找
	 * @param config
	 * @return
	 */
	List<SystemConfig> findList(SystemConfig config);
}
