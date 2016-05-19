package org.tinygroup.sdpm.system.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.system.biz.inter.DictManager;
import org.tinygroup.sdpm.system.dao.SystemDictDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemDict;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Service
public class DictManagerImpl implements DictManager {

    @Autowired
    private SystemDictDao systemDictDao;

    public SystemDict add(SystemDict dict) {

        return systemDictDao.add(dict);
    }

    public int delete(Integer dictId) {

        SystemDict systemDict = new SystemDict();
        systemDict.setDictId(dictId);
        systemDict.setDeleted(SystemDict.DELETE_YES);
        return systemDictDao.edit(systemDict);
    }

    public int update(SystemDict dict) {

        return systemDictDao.edit(dict);
    }

    public SystemDict find(Integer dictId) {

        return systemDictDao.getByKey(dictId);
    }

    public int[] updateBatch(List<SystemDict> dicts) {

        return systemDictDao.batchUpdate(dicts);
    }

    public List<SystemDict> findList(SystemDict dict) {
        List<SystemDict> list = systemDictDao.query(dict);
        if (list.size() > 0) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                if (list.get(i).getDeleted() == 1) {
                    list.remove(i);
                }

            }
        }
        return list;
    }

    public Pager<SystemDict> findPager(int start, int limit, SystemDict dict, String columnName,
                                       boolean asc) {
        Pager<SystemDict> pagerDict = systemDictDao.queryPager(start, limit, dict, new OrderBy(columnName, asc));

        return pagerDict;
    }

    public List<SystemDict> findList(SystemDict dict, String columnName, boolean asc) {
        return systemDictDao.query(dict, new OrderBy(columnName, asc));
    }

    public int bechDelete(Integer... ids) {
        return systemDictDao.deleteByKeys(ids);
    }

    public void deleteAll() {
        systemDictDao.deleteAll();
    }


}
