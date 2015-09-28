package org.tinygroup.sdpm.system.service.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemConfig;
import org.tinygroup.sdpm.system.dao.pojo.SystemDict;
import org.tinygroup.tinysqldsl.Pager;

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
	 * 批量编辑
	 * @param dicts
	 * @return
	 */
	int[] updateBatchDict(List<SystemDict> dicts);
	
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
	
	/**
	 * 根据对象查找(分页、排序)
	 * @param start
	 * @param limit
	 * @param dict
	 * @param columnName
	 * @param asc
	 * @return
	 */
	Pager<SystemDict> findDictPager(int start,int limit,SystemDict dict,String columnName,boolean asc);

}
