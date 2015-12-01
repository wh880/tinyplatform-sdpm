package org.tinygroup.sdpm.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.HistoryManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;
import org.tinygroup.sdpm.system.service.inter.HistoryService;

import java.util.List;

@Component
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryManager historyManager;

    public List<SystemHistory> findSystemHistory(SystemHistory systemHistory) {
        return historyManager.find(systemHistory);
    }

}
