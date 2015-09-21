package org.tinygroup.sdpm.system.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemSearch;

public interface SearchManager {
	/**
	 * 添加搜索
	 * @param config
	 * @return
	 */
	SystemSearch add(SystemSearch search);
	
	/**
	 * 删除
	 * @param config
	 * @return
	 */
	int delete(Integer searchId);
	
	/**
	 * 修改
	 * @param config
	 * @return
	 */
	int update(SystemSearch search);
	
	/**
	 * 根据ID查找
	 * @param configId
	 * @return
	 */
	SystemSearch find(Integer searchId);
	
	/**
	 * 根据对象查找
	 * @param config
	 * @return
	 */
	List<SystemSearch> finList(SystemSearch search);
}
