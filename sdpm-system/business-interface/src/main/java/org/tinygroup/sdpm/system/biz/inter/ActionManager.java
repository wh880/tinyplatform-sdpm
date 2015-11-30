package org.tinygroup.sdpm.system.biz.inter;

import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;

import java.util.List;

public interface ActionManager {
	/**
	 * 增加动态
	 * @param SystemAction
	 * @return
	 */
	SystemAction add(SystemAction systemAction);
	/**
	 * 修改动态
	 * @param SystemAction
	 * @return
	 */
	SystemAction updata(SystemAction systemAction);
	/**
	 * 删除动态
	 * @param SystemAction
	 * @return
	 */
	Integer delete(SystemAction systemAction);
	/**
	 * 查询动态
	 * @param SystemAction
	 * @return
	 */
	List<SystemAction> find(SystemAction systemAction,String orderBy,boolean asc);
	/**
	 * 分页查询
	 * @param start
	 * @param limit
	 * @param systemAction
	 * @param sortName
	 * @param asc
	 * @return
	 */
	 Pager<SystemAction> findByPage(int start, int limit,SystemAction systemAction, String order, String ordertype) ;
	 
	 /**
	  * 自定义条件查询
	  * @param start
	  * @param limit
	  * @param condition
	  * @param systemAction
	  * @param orderBies
	  * @return
	  */
	 Pager<SystemAction> queryPager(int start,int limit ,Condition condition,SystemAction systemAction , String order,String ordertype);
	/**
	 * 通过时间段查询
	 * @param start
	 * @param limit
	 * @param action
	 * @param startDate
	 * @param endDate
	 * @param orderArgs
	 * @return
	 */
	Pager<SystemAction> queryBetweenDate(int start,int limit,SystemAction action,String startDate,String endDate,
			String sortName,boolean asc);
}
