package org.tinygroup.sdpm.system.service.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.tinysqldsl.Pager;

public interface ActionService {
	/**
	 * 增加动态
	 * @param SystemAction
	 * @return
	 */
	SystemAction add(SystemAction SystemAction);
	/**
	 * 修改动态
	 * @param SystemAction
	 * @return
	 */
	SystemAction updata(SystemAction SystemAction);
	/**
	 * 删除动态
	 * @param SystemAction
	 * @return
	 */
	Integer delete(SystemAction SystemAction);
	/**
	 * 查询动态
	 * @param SystemAction
	 * @return
	 */
	List<SystemAction> find(SystemAction SystemAction);
	/**
	 * 分页查询
	 * @param start
	 * @param limit
	 * @param systemAction
	 * @param sortName
	 * @param asc
	 * @return
	 */
	Pager<SystemAction> findByPager(int start,int limit,SystemAction systemAction,String sortName ,boolean asc);
}
