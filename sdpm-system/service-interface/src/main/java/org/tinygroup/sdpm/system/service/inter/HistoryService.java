package org.tinygroup.sdpm.system.service.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.History;

public interface HistoryService {
	/**
	 * 增加动态
	 * @param History
	 * @return
	 */
	History add(History history);
	/**
	 * 修改动态
	 * @param History
	 * @return
	 */
	History updata(History history);
	/**
	 * 删除动态
	 * @param History
	 * @return
	 */
	Integer delete(History history);
	/**
	 * 查询动态
	 * @param History
	 * @return
	 */
	List<History> find(History history);

}
