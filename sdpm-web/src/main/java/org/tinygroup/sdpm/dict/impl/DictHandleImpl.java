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
public class DictHandleImpl extends DictParent implements DictHandle{
    private  List<DictNodeEntries> entriesList = new ArrayList<DictNodeEntries>();
    private final Logger LOGGER = LoggerFactory.getLogger(DictHandleImpl.class);
    private Map<String,String> pathRecord = new ConcurrentHashMap<String, String>();
    public void addDictEntry(DictNodeEntries dictNodeEntries, String filePath) throws Exception {
        if(dictNodeEntries==null)return;
        if(!isRepeat(dictNodeEntries.getName(),filePath)){
            pathRecord.put(dictNodeEntries.getName(),filePath);
            entriesList.add(dictNodeEntries);
        }else{
            throw new Exception("["+dictNodeEntries.getName()+"]已存在："+filePath);
        }
        mergeDictEntry(dictNodeEntries.getDictNodeEntries(),dictNodeEntries.getName(),dictNodeEntries.getName());
    }

    public DictNodeEntry getDict(String key) {
        return null;
    }

    public void insertDictNodeEntry() {
            DictUtil.insertDict(entriesList);
    }

    public void removeDictNodeEntries(DictNodeEntries dictNodeEntries) {
        for(String path : entryLinkMap.get(dictNodeEntries.getName())){
            dictMap.remove(path);
        }
    }

    private void mergeDictEntry(List<DictNodeEntry> dictNodeEntries,String parent,String root) throws Exception {
        if(dictNodeEntries==null)return;
        Map<String,Integer> record = new HashMap<String, Integer>();
        int count = 1;
        for (DictNodeEntry dictNodeEntry : dictNodeEntries) {
            Map<String,String> map = new LinkedHashMap<String, String>();
            if(dictNodeEntry.getDictItems()!=null) {
                for (DictItem dictItem : dictNodeEntry.getDictItems()) {
                    map.put(dictItem.getKey(), dictItem.getValue());
                }
            }
            if(map.size()>0){
                if(!record.containsKey(parent+"/"+dictNodeEntry.getName())) {
                    dictMap.put(parent + "/" + dictNodeEntry.getName(), map);
                    if(!entryMapping.containsKey(dictNodeEntry.getName())){
                        entryMapping.put(dictNodeEntry.getName(),parent + "/" + dictNodeEntry.getName());
                    }else{
                        entryMapping.remove(dictNodeEntry.getName());
                    }

                    record.put(parent + "/" + dictNodeEntry.getName(),count);
                }else{
                    throw new Exception("同级目录不可重名["+root+":"+dictNodeEntry.getName()+"]");
                }
            }
                mergeDictEntry(dictNodeEntry.getChildren(), parent+"/"+dictNodeEntry.getName(), root);
            count++;
        }
    }

    public boolean isRepeat(String key,String filePath){
        if (entryLinkMap.containsKey(key)){
            LOGGER.logMessage(LogLevel.INFO, "正在进行obtain:[{0}]重复判定..", key);
            String path = pathRecord.get(key);
            if(path.contains(".jar")){
                if(filePath.contains(".jar")){
                    return true;
                }else{
                    return false;
                }
            }else{
                if(filePath.contains(".jar")){
                    return false;
                }else{
                    return true;
                }
            }
        }
        return false;
    }
}
