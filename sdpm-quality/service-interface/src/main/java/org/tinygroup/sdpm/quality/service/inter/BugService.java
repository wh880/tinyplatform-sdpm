package org.tinygroup.sdpm.quality.service.inter;

import java.util.List;
import org.tinygroup.sdpm.quality.dao.pojo.Bug;

public interface BugService {
		
	/**
	 * 根据对象查询
	 * @param bug
	 * @return
	 */
	List<Bug> findByBug(Bug bug);
	/**
	 * 通过Bug状态查询
	 * @param status
	 * @return
	 */
	List<Bug> findByStatus(String status);
	/**
	 * 通过指派查询
	 * @param assign
	 * @return
	 */
	List<Bug> findByAssignto(String assign);
	/**
	 * 通过创建者查询
	 * @param open
	 * @return
	 */
	List<Bug> findByOpened(String open);
	/**
	 * 通过解决查询
	 * @param resolve
	 * @return
	 */
	List<Bug> findByResolved(String resolve);
	/**
	 * 通过是否确认查询
	 * @param confirm
	 * @return
	 */
	List<Bug> findByConfirmed(Integer confirm);
	
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
