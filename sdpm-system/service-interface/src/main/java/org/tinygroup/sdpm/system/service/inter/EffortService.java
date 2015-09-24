package org.tinygroup.sdpm.system.service.inter;

import java.util.Date;
import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemEffort;
import org.tinygroup.tinysqldsl.Pager;

public interface EffortService {
	
	/**
	 * 根据日期查询，返回日志信息List
	 * @param date
	 * @return
	 */
	 List<SystemEffort> findByDate(Date date);
	 
	/**
	 * 保存日志
	 * @param SystemEffort
	 * @return
	 */
	
	 SystemEffort save(SystemEffort systemEffort);
	/**
	 * 根据登记人查询
	 * @param account
	 * @return
	 */
	 List<SystemEffort> findByAccount(String account);
	/**
	 * 查询所有字段
	 * @return
	 */
	 List<SystemEffort> find(SystemEffort systemEffort);
	 /**
	  * 根据时间段查询
	  * @param lastdate
	  * @param nowdate
	  * @return
	  */

	 List<SystemEffort> findBetweenDate(Date begindate,Date enddate);
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
	 Pager<SystemEffort> findByPage(int start,int limit,SystemEffort systemEffort);
	 
	
}
