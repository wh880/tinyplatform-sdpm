package org.tinygroup.sdpm.system.service.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemAction;

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
}
