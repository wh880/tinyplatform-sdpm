/*
package org.tinygroup.sdpm.system.biz.impl;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.SavepointManager;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.system.dao.SystemSearchDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemSearch;

@Service
@Transactional
public class SearchManagerImpl {
    @Autowired
    private SystemSearchDao searchDao;

    public  SystemSearch add(SystemSearch search){
        return searchDao.add(search);
    }

    public  int delete(SystemSearch searchId){
        SystemSearch search=new SystemSearch();
        search.setSearchId(searchId);
        search.setDeleted(SystemSearch.DELETE_YES);
        return searchDao.edit(search);
    }

    public  int update(SystemSearch search){
        return searchDao.edit(search);
    }

    public SystemSearch find(Integer searchId){
        return searchDao.getByKey(searchId);
    }

    public List<SystemSearch> finList(SystemSearch search){
        return  searchDao.query(search);
    }
}
*/
