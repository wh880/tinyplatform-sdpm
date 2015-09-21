package org.tinygroup.sdpm.system.service.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemSearch;

public interface SearchService {
	/**
	 * 添加搜索
	 * @param config
	 * @return
	 */
	SystemSearch addSearch(SystemSearch search);
	
	/**
	 * 删除
	 * @param config
	 * @return
	 */
	int deleteSearch(Integer searchId);
	
	/**
	 * 修改
	 * @param config
	 * @return
	 */
	int updateSearch(SystemSearch search);
	
	/**
	 * 根据ID查找
	 * @param configId
	 * @return
	 */
	SystemSearch findSearch(Integer searchId);
	
	/**
	 * 根据对象查找
	 * @param config
	 * @return
	 */
	List<SystemSearch> findSearchList(SystemSearch search);
}
