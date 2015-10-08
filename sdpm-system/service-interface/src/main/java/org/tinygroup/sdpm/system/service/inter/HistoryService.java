package org.tinygroup.sdpm.system.service.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;
import org.tinygroup.tinysqldsl.Pager;

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
	/**
	 * 分页查询
	 * @param start
	 * @param limit
	 * @param systemHistory
	 * @param sortName
	 * @param asc
	 * @return
	 */
	Pager<SystemHistory> findByPager(int start,int limit,SystemHistory systemHistory,String	sortName,boolean asc);

}
