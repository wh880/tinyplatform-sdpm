package org.tinygroup.sdpm.system.biz.inter;

import java.util.Date;
import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;
import org.tinygroup.tinysqldsl.Pager;

public interface EffortManager {
	/**
	 * 添加日志
	 * @param SystemEffort
	 * @return
	 */
	SystemEffort add(SystemEffort SystemEffort);
	
	/**
	 * 更新日志
	 * @param SystemEffort
	 * @return
	 */
	SystemEffort updata(SystemEffort SystemEffort);
	/**
	 * 查询所有的日志
	 * @param SystemEffort
	 * @return
	 */
	List<SystemEffort> find(SystemEffort SystemEffort);
	/**
	 * 通过当前时间查询
	 * @param date
	 * @return
	 */
	
	List<SystemEffort> findByDate(Date date);
	/**
	 * 删除日志
	 * @param SystemEffort
	 * @return
	 */
	
	
	Integer delete(SystemEffort SystemEffort);
	/**
	 * 通过时间段查询
	 * @param begindate
	 * @param enddate
	 * @return
	 */
	List<SystemEffort> findBetweenDate(Date begindate,Date enddate);
	/**
	 * 根据登记人查询
	 * @param account
	 * @return
	 */
	 List<SystemEffort> findByAccount(String account);
	 /**
	  * 通过projectID查询所有数据
	  * @param projectId
	  * @return
	  */
	 List<SystemEffort> findByProject(int projectId);
	 /**
	  * 分页查询
	  * @param start
	  * @param limit
	  * @param SystemEffort
	  * @return
	  */
	 Pager<SystemEffort> findByPage(int start,int limit,SystemEffort SystemEffort);
	 
	
}
