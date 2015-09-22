package org.tinygroup.sdpm.system.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemSearch;
import org.tinygroup.tinysqldsl.Pager;

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
	 * 批量删除
	 * @param searches
	 * @return
	 */
	int[] updateBatch(List<SystemSearch> searches);
	
	/**
	 * 根据ID查找
	 * @param configId
	 * @return
	 */
	SystemSearch find(Integer searchId);
	
	/**
	 * 根据对象查询（排序）
	 * @param search
	 * @param columnName
	 * @param asc
	 * @return
	 */
	List<SystemSearch> findList(SystemSearch search,String columnName,boolean asc);
	/**
	 * 分页查询
	 * @param start
	 * @param limit
	 * @param search
	 * @param columnName
	 * @param asc
	 * @return
	 */
	Pager<SystemSearch> findPager(int start,int limit,SystemSearch search,String columnName,boolean asc);
	
}
