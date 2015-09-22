package org.tinygroup.sdpm.system.service.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.Action;

public interface ActionService {
	/**
	 * 增加动态
	 * @param action
	 * @return
	 */
	Action add(Action action);
	/**
	 * 修改动态
	 * @param action
	 * @return
	 */
	Action updata(Action action);
	/**
	 * 删除动态
	 * @param action
	 * @return
	 */
	Integer delete(Action action);
	/**
	 * 查询动态
	 * @param action
	 * @return
	 */
	List<Action> find(Action action);
}
