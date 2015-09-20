package org.tinygroup.sdpm.system.service;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemDict;

public interface DictService {
	/**
	 * 添加字典
	 * @param config
	 * @return
	 */
	SystemDict addDict(SystemDict dict);
	
	/**
	 * 删除
	 * @param config
	 * @return
	 */
	int deleteDict(Integer dictId);
	
	/**
	 * 修改
	 * @param config
	 * @return
	 */
	int updateDict(SystemDict dict);
	
	/**
	 * 根据ID查找
	 * @param configId
	 * @return
	 */
	SystemDict findDict(Integer dictId);
	
	/**
	 * 根据对象查找
	 * @param config
	 * @return
	 */
	List<SystemDict> findDictList(SystemDict dict);
}
