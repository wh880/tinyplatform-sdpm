package org.tinygroup.sdpm.system.biz.inter;

import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

public interface HistoryManager {
    /**
     * 增加动态
     *
     * @param systemHistory
     * @return
     */
    SystemHistory add(SystemHistory systemHistory);

    /**
     * 修改动态
     *
     * @param systemHistory
     * @return
     */
    SystemHistory updata(SystemHistory systemHistory);

    /**
     * 删除动态
     *
     * @param systemHistory
     * @return
     */
    Integer delete(SystemHistory systemHistory);

    /**
     * 查询动态
     *
     * @param systemHistory
     * @return
     */
    List<SystemHistory> find(SystemHistory systemHistory);

    /**
     * 分页查询
     *
     * @param start
     * @param limit
     * @param systemHistory
     * @param SortName
     * @param asc
     * @return
     */
    Pager<SystemHistory> findByPager(int start, int limit, SystemHistory systemHistory, String sortName, boolean asc);
}
