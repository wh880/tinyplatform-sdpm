package org.tinygroup.sdpm.system.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemDict;
import org.tinygroup.tinysqldsl.Pager;

public interface DictManager {
	
	public static int DELETE_YES = 1;
	public static int DELETE_NO = 0;
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
	 * 批量编辑
	 * @param dicts
	 * @return
	 */
	int[] updateBatch(List<SystemDict> dicts);
	
	/**
	 * 根据ID查找
	 * @param configId
	 * @return
	 */
	SystemDict find(Integer dictId);
	
	/**
	 * 根据对象查找（排序）
	 * @param config
	 * @return
	 */
	List<SystemDict> findList(SystemDict dict);
	/**
	 * 分页查询
	 * @param start
	 * @param limit
	 * @param dict
	 * @param columnName
	 * @param asc
	 * @return
	 */
	Pager<SystemDict> findPager(int start,int limit,SystemDict dict,String columnName,boolean asc);

	List<SystemDict> findList(SystemDict dict,String columnName,boolean asc);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	int bechDelete(Integer...ids);

	void deleteAll();
}
