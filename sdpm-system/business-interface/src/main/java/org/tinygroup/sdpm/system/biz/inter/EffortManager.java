package org.tinygroup.sdpm.system.biz.inter;

import java.util.Date;
import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.Effort;
import org.tinygroup.tinysqldsl.Pager;

public interface EffortManager {
	/**
	 * 添加日志
	 * @param effort
	 * @return
	 */
	Effort add(Effort effort);
	
	/**
	 * 更新日志
	 * @param effort
	 * @return
	 */
	Effort updata(Effort effort);
	/**
	 * 查询所有的日志
	 * @param effort
	 * @return
	 */
	List<Effort> find(Effort effort);
	/**
	 * 通过当前时间查询
	 * @param date
	 * @return
	 */
	
	List<Effort> findByDate(Date date);
	/**
	 * 删除日志
	 * @param effort
	 * @return
	 */
	
	
	Integer delete(Effort effort);
	/**
	 * 通过时间段查询
	 * @param begindate
	 * @param enddate
	 * @return
	 */
	List<Effort> findBetweenDate(Date begindate,Date enddate);
	/**
	 * 根据登记人查询
	 * @param account
	 * @return
	 */
	 List<Effort> findByAccount(String account);
	 /**
	  * 通过projectID查询所有数据
	  * @param projectId
	  * @return
	  */
	 List<Effort> findByProject(int projectId);
	 /**
	  * 分页查询
	  * @param start
	  * @param limit
	  * @param effort
	  * @return
	  */
	 Pager<Effort> findByPage(int start,int limit,Effort effort);
	 
	
}
