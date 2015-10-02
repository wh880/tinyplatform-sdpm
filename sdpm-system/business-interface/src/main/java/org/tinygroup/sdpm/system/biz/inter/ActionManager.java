package org.tinygroup.sdpm.system.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemAction;

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
}
