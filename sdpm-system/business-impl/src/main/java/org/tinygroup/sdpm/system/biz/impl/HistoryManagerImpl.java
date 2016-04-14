package org.tinygroup.sdpm.system.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.system.biz.inter.HistoryManager;
import org.tinygroup.sdpm.system.dao.SystemHistoryDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemHistory;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Service
public class HistoryManagerImpl implements HistoryManager {
    @Autowired
    private SystemHistoryDao histroyDao;

    public SystemHistory add(SystemHistory systemHistory) {
        // TODO Auto-generated method stub
        return histroyDao.add(systemHistory);
    }

    public SystemHistory updata(SystemHistory systemHistory) {
        // TODO Auto-generated method stub
        histroyDao.edit(systemHistory);
        return systemHistory;
    }

    public Integer delete(SystemHistory systemHistory) {
        // TODO Auto-generated method stub
        int pk = systemHistory.getHistoryId();
        return histroyDao.deleteByKey(pk);
    }

    public List<SystemHistory> find(SystemHistory systemHistory) {
        // TODO Auto-generated method stub
        return histroyDao.query(systemHistory);
    }

    public Pager<SystemHistory> findByPager(int start, int limit,
                                            SystemHistory systemHistory, String sortName, boolean asc) {
        // TODO Auto-generated method stub
        if (StringUtil.isBlank(sortName)) {
            return histroyDao.queryPager(start, limit, systemHistory);
        }
        OrderBy orderBy = new OrderBy(sortName, asc);
        return histroyDao.queryPager(start, limit, systemHistory, orderBy);
    }


}
