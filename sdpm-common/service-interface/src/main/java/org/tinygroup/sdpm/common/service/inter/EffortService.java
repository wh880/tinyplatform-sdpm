package org.tinygroup.sdpm.common.service.inter;

import java.util.Date;
import java.util.List;

import org.tinygroup.sdpm.common.dao.pojo.Effort;

public interface EffortService {
	/**
	 * 
	 * @param 添加日志
	 * @return
	 */
	 Effort add(Effort effort);
	/**
	 * 根据日期查询，返回日志信息List
	 * @param date
	 * @return
	 */
	 List<Effort> findByDate(Date date);
	 
	/**
	 * 保存日志
	 * @param effort
	 * @return
	 */
	
	 Effort save(Effort effort);
	/**
	 * 根据登记人查询
	 * @param account
	 * @return
	 */
	 List<Effort> findByAccount(String account);
	/**
	 * 查询所有字段
	 * @return
	 */
	 List<Effort> find(Effort effort);
	 /**
	  * 根据时间段查询
	  * @param lastdate
	  * @param nowdate
	  * @return
	  */
	 List<Effort> findBetweenDate(Date begindate,Date enddate);
	
}
