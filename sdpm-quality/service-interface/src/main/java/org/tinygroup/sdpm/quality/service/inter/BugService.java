package org.tinygroup.sdpm.quality.service.inter;

import org.tinygroup.sdpm.quality.dao.pojo.Bug;

public interface BugService {
		
	/**
	 * 根据对象查询
	 * @param bug
	 * @return
	 */
	Bug findByBug(Bug bug);
	/**
	 * 通过对象提Bug
	 * @param bug
	 * @return
	 */
	Bug add(Bug bug);
	/**
	 * 编辑
	 * @param bug
	 * @return
	 */
	int edit(Bug bug);
	
}
