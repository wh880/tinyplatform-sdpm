package org.tinygroup.sdpm.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.system.biz.inter.DictManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemDict;
import org.tinygroup.sdpm.system.service.inter.DictService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

@Component
public class DictServiceImpl implements DictService {

    @Autowired
    private DictManager dictManager;

    public SystemDict addDict(SystemDict dict) {

        return dictManager.add(dict);
    }

    public int deleteDict(Integer dictId) {

        return dictManager.delete(dictId);
    }

    public int updateDict(SystemDict dict) {

        return dictManager.update(dict);
    }

    public SystemDict findDict(Integer dictId) {

        return dictManager.find(dictId);
    }

    public List<SystemDict> findDictList(SystemDict dict) {

        return dictManager.findList(dict);
    }

    public Pager<SystemDict> findDictPager(int start, int limit, SystemDict dict, String columnName, boolean asc) {

        return dictManager.findPager(start, limit, dict, columnName, asc);
    }

    public int[] updateBatchDict(List<SystemDict> dicts) {

        return dictManager.updateBatch(dicts);
    }

    public int batchDelete(Integer... ids) {
        return dictManager.bechDelete(ids);
    }

    public void deleteAllDict() {
        dictManager.deleteAll();
    }

    public List<SystemDict> findDictListByOder(SystemDict dict, String columnName, boolean asc) {
        return dictManager.findList(dict, columnName, asc);
    }


}
