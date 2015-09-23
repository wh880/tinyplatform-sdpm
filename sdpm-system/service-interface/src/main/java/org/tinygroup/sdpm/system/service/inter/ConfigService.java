package org.tinygroup.sdpm.system.service.inter;

import java.util.List;

import org.aspectj.apache.bcel.generic.InstructionTargeter;
import org.tinygroup.sdpm.system.dao.pojo.SystemConfig;
import org.tinygroup.tinysqldsl.Pager;

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
	int deleteConfig(Integer configId);
	
	/**
	 * 修改
	 * @param config
	 * @return
	 */
	int updateConfig(SystemConfig config);
	/**
	 * 批量编辑
	 * @param config
	 * @return
	 */
	int[] updateBatchConfig(List<SystemConfig> configs);
	
	/**
	 * 根据ID查找
	 * @param configId
	 * @return
	 */
	SystemConfig findConfig(Integer configId);
	/**
	 * 根据对象查找（排序）
	 * @param config
	 * @param columnName
	 * @param asc
	 * @return
	 */
	List<SystemConfig> findConfigList(SystemConfig config,String columnName,boolean asc);
	/**
	 * 根据对象查找(分页、排序)
	 * @param start
	 * @param limit
	 * @param config
	 * @param columnName
	 * @param asc
	 * @return
	 */
	Pager<SystemConfig> findConfigPager(int start,int limit,SystemConfig config,String columnName,boolean asc);
}
