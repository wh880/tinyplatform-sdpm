package org.tinygroup.sdpm.system.service.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemConfig;
import org.tinygroup.sdpm.system.dao.pojo.SystemDict;
import org.tinygroup.sdpm.system.dao.pojo.SystemSearch;
import org.tinygroup.tinysqldsl.Pager;

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
	 * 批量编辑
	 * @param dicts
	 * @return
	 */
	int[] updateBatchSearch(List<SystemSearch> searchs);
	
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
	List<SystemSearch> findSearchList(SystemSearch search,String columnName,boolean asc);
	
	/**
	 * 根据对象查找(分页、排序)
	 * @param start
	 * @param limit
	 * @param search
	 * @param columnName
	 * @param asc
	 * @return
	 */
	Pager<SystemSearch> findSearchPager(int start,int limit,SystemSearch search,String columnName,boolean asc);

}
