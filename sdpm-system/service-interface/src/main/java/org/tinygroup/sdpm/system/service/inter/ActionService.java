package org.tinygroup.sdpm.system.service.inter;

import java.util.Date;
import java.util.List;

import org.tinygroup.tinysqldsl.base.Condition;
import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.tinysqldsl.Pager;

public interface ActionService {
	/**
	 * 增加动态
	 * 
	 * @param SystemAction
	 * @return
	 */
	SystemAction add(SystemAction SystemAction);

	/**
	 * 修改动态
	 * 
	 * @param SystemAction
	 * @return
	 */
	SystemAction updata(SystemAction SystemAction);

	/**
	 * 删除动态
	 * 
	 * @param SystemAction
	 * @return
	 */
	Integer delete(SystemAction SystemAction);

	/**
	 * 查询动态
	 * 
	 * @param SystemAction
	 * @return
	 */
	List<SystemAction> find(SystemAction SystemAction);

	/**
	 * 分页查询
	 * 
	 * @param start
	 * @param limit
	 * @param systemAction
	 * @param sortName
	 * @param ordertype
	 * @return
	 */
	Pager<SystemAction> findSystemActionPager(int page, int pagesize,
			SystemAction action, String order, String ordertype);
	
	/**
	 * 自定义条件查询
	 * @param start
	 * @param limit
	 * @param condition
	 * @param systemAction
	 * @param order
	 * @param ordertype
	 * @return
	 */
	Pager<SystemAction> queryPager(int start, int limit,
			Condition condition, SystemAction systemAction, String order,
			String ordertype);
	/**
	 * 通过日期查询
	 * @param start
	 * @param limit
	 * @param action
	 * @param startDate
	 * @param endDate
	 * @param sortName
	 * @param asc
	 * @return
	 */
	Pager<SystemAction> queryBetweenDate(int start,int limit,SystemAction action,Date startDate,Date endDate,
			String sortName,boolean asc);
}
