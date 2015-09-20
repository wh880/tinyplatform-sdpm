package org.tinygroup.sdpm.system.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemDict;

public interface DictManager {
	/**
	 * 添加字典
	 * @param config
	 * @return
	 */
	SystemDict add(SystemDict dict);
	
	/**
	 * 删除
	 * @param config
	 * @return
	 */
	int delete(Integer dictId);
	
	/**
	 * 修改
	 * @param config
	 * @return
	 */
	int update(SystemDict dict);
	
	/**
	 * 根据ID查找
	 * @param configId
	 * @return
	 */
	SystemDict find(Integer dictId);
	
	/**
	 * 根据对象查找
	 * @param config
	 * @return
	 */
	List<SystemDict> findList(SystemDict dict);
}
