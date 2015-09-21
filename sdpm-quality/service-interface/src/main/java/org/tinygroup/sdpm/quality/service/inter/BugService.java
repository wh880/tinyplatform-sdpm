package org.tinygroup.sdpm.quality.service.inter;

import java.util.List;
import org.tinygroup.sdpm.quality.dao.pojo.Bug;
import org.tinygroup.tinysqldsl.Pager;

public interface BugService {
		
	/**
	* 根据主键ID查询
	 * @param id
	 * @return
	 */
	Bug findById(int id);
	
	/**
	 * 根据条件查询d
	 * @param bug
	 * @return
	 */
	List<Bug> findBugList(Bug type);	
	
	/**
	 * 提Bug
	 * @param bug
	 * @return
	 */
	Bug addBug(Bug bug);

	/**
	 * 修改
	 * @param bug
	 * @return
	 */
	int updateBug(Bug bug);
	
	
}
