package org.tinygroup.sdpm.system.service.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;

public interface HistoryService {
	/**
	 * 增加动态
	 * @param SystemHistory
	 * @return
	 */
	SystemHistory add(SystemHistory systemHistory);
	/**
	 * 修改动态
	 * @param SystemHistory
	 * @return
	 */
	SystemHistory updata(SystemHistory systemHistory);
	/**
	 * 删除动态
	 * @param SystemHistory
	 * @return
	 */
	Integer delete(SystemHistory systemHistory);
	/**
	 * 查询动态
	 * @param SystemHistory
	 * @return
	 */
	List<SystemHistory> find(SystemHistory systemHistory);

}
