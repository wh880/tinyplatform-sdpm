package org.tinygroup.sdpm.system.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;

public interface HistoryManager {
	/**
	 * 增加动态
	 * @param SystemHistory
	 * @return
	 */
	SystemHistory add(SystemHistory SystemHistory);
	/**
	 * 修改动态
	 * @param SystemHistory
	 * @return
	 */
	SystemHistory updata(SystemHistory SystemHistory);
	/**
	 * 删除动态
	 * @param SystemHistory
	 * @return
	 */
	Integer delete(SystemHistory SystemHistory);
	/**
	 * 查询动态
	 * @param SystemHistory
	 * @return
	 */
	List<SystemHistory> find(SystemHistory SystemHistory);
}
