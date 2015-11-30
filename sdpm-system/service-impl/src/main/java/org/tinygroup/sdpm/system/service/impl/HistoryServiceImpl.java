package org.tinygroup.sdpm.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.HistoryManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;
import org.tinygroup.sdpm.system.service.inter.HistoryService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Component
public class HistoryServiceImpl implements HistoryService {
    @Autowired
    private HistoryManager historyManager;

    public SystemHistory add(SystemHistory systemHistory) {
        // TODO Auto-generated method stub
        return historyManager.add(systemHistory);
    }

    public SystemHistory updata(SystemHistory systemHistory) {
        // TODO Auto-generated method stub
        return historyManager.updata(systemHistory);
    }

    public Integer delete(SystemHistory systemHistory) {
        // TODO Auto-generated method stub
        return historyManager.delete(systemHistory);
    }

    public List<SystemHistory> findSystemHistory(SystemHistory systemHistory) {
        // TODO Auto-generated method stub
        return historyManager.find(systemHistory);
    }

    public Pager<SystemHistory> findByPager(int start, int limit,
                                            SystemHistory systemHistory, String sortName, boolean asc) {
        // TODO Auto-generated method stub
        return historyManager.findByPager(start, limit, systemHistory, sortName, asc);
    }


}
