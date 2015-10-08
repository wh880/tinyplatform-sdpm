package org.tinygroup.sdpm.system.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemAction;
import org.tinygroup.tinysqldsl.Pager;

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
	List<SystemAction> find(SystemAction systemAction);
	/**
	 * 分页查询
	 * @param start
	 * @param limit
	 * @param systemAction
	 * @param sortName
	 * @param asc
	 * @return
	 */
	public Pager<SystemAction> findByPage(int start, int limit,SystemAction systemAction, String sortName, boolean asc) ;
}
