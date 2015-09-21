package org.tinygroup.sdpm.quality.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.quality.dao.pojo.Bug;

public interface BugManager {
	/**
	* 根据主键ID查询
	 * @param id
	 * @return
	 */
	Bug find(int id);
	
	/**
	 * 根据条件查询d
	 * @param bug
	 * @return
	 */
	List<Bug> findList(Bug type);	
	
	/**
	 * 提Bug
	 * @param bug
	 * @return
	 */
	Bug add(Bug bug);

	/**
	 * 修改
	 * @param bug
	 * @return
	 */
	int update(Bug bug);
}
