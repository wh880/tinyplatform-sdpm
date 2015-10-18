package org.tinygroup.sdpm.dict.impl;

import org.tinygroup.logger.LogLevel;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;
import org.tinygroup.sdpm.dict.inter.*;
import org.tinygroup.sdpm.dict.util.DictUtil;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangll13383 on 2015/10/15.
 */
public class DictHandleImpl implements DictHandle{

    private static List<DictNodeEntries> entriesList = new ArrayList<DictNodeEntries>();
    private final Logger LOGGER = LoggerFactory.getLogger(DictHandleImpl.class);
    private Map<String,String> pathRecord = new ConcurrentHashMap<String, String>();
    public void addDictEntry(DictNodeEntries dictNodeEntries, String filePath) throws Exception {
        if(entriesList.contains(dictNodeEntries)){
            throw new Exception("["+dictNodeEntries.getName()+"]已存在:"+filePath);
        }
        entriesList.add(dictNodeEntries);
    }

    public DictNodeEntry getDict(String key) {
        return null;
    }

    public void insertDictNodeEntry() {
            DictUtil.insertDict(entriesList);
    }

    public List<DictNodeEntries> getDictNodeEntries() {
        return entriesList;
    }

    public void removeDictNodeEntries(DictNodeEntries dictNodeEntries) {
        entriesList.remove(dictNodeEntries);
    }




}
