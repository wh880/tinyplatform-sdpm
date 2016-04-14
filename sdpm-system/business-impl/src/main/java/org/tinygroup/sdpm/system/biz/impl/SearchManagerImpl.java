package org.tinygroup.sdpm.system.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.system.biz.inter.SearchManager;
import org.tinygroup.sdpm.system.dao.SystemSearchDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemSearch;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Service
public class SearchManagerImpl implements SearchManager {
    @Autowired
    private SystemSearchDao searchDao;

    public SystemSearch add(SystemSearch search) {
        return searchDao.add(search);
    }

    public int delete(Integer searchId) {
        SystemSearch search = new SystemSearch();
        search.setSearchId(searchId);
        search.setDeleted(SystemSearch.DELETE_YES);
        return searchDao.edit(search);
    }

    public int update(SystemSearch search) {
        return searchDao.edit(search);
    }

    public SystemSearch find(Integer searchId) {
        return searchDao.getByKey(searchId);
    }

    public int[] updateBatch(List<SystemSearch> searches) {

        return searchDao.batchUpdate(searches);
    }

    public List<SystemSearch> findList(SystemSearch search, String columnName, boolean asc) {

        return searchDao.query(search, new OrderBy(columnName, asc));
    }

    public Pager<SystemSearch> findPager(int start, int limit, SystemSearch search, String columnName,
                                         boolean asc) {

        return searchDao.queryPager(start, limit, search, new OrderBy(columnName, asc));
    }


}

