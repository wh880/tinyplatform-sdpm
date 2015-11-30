package org.tinygroup.sdpm.system.service.inter;

import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;

import java.util.List;

public interface HistoryService {
    /**
     * 查找历史记录列表
     *
     * @param systemHistory
     * @return
     */
    List<SystemHistory> findSystemHistory(SystemHistory systemHistory);

}
