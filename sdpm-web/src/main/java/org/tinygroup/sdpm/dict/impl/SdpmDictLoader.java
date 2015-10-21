package org.tinygroup.sdpm.dict.impl;

import org.tinygroup.dict.Dict;
import org.tinygroup.dict.DictGroup;
import org.tinygroup.dict.DictItem;
import org.tinygroup.dict.DictManager;
import org.tinygroup.dict.impl.AbstractDictLoader;
import org.tinygroup.sdpm.dict.inter.DictHandle;
import org.tinygroup.sdpm.dict.util.DictUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangll13383 on 2015/10/17.
 */
public class SdpmDictLoader extends AbstractDictLoader{

    private boolean isInitFromFile = true;
    private DictHandle dictHandle;
    private Map<String,String> nodeMap = new HashMap<String, String>();

    public  boolean isInitFromFile() {
        return isInitFromFile;
    }

    public  void setIsInitFromFile(boolean isInitFromFile) {
        this.isInitFromFile = isInitFromFile;
    }

    public void setDictHandle(DictHandle dictHandle) {
        this.dictHandle = dictHandle;
    }

    public void load(DictManager dictManager) {
        if(isInitFromFile){
            loadDictFromFile(dictManager);
        }else{
            loadDictFromDB(dictManager);
        }
    }

    public void loadDictFromFile(DictManager dictManager){
        List<Dict> dicts = DictUtil.getDictFromFile(dictHandle.getDictNodeEntries(),nodeMap);
        for(Dict dict : dicts){
            this.putDict(dict.getName(),dict,dictManager);
        }

    }

    public void loadDictFromDB(DictManager dictManager){
        List<Dict> dicts = DictUtil.mergeDict(nodeMap);
        for(Dict dict : dicts){
            this.putDict(dict.getName(),dict,dictManager);
        }
    }

    public DictItem getDictItem(DictManager manager,String groupType, String key){
        Dict dict = manager.getDict(nodeMap.get(groupType),null);
        for(DictGroup group : dict.getDictGroupList()){
            if(group.getName().equals(groupType)){
                for(DictItem item : group.getItemList()){
                    if(item.getValue().equals(key)){
                        return item;
                    }
                }
                return null;
            }
        }
        return null;
    }

    public DictGroup getDictGroup(DictManager manager,String groupType){
        Dict dict = manager.getDict(nodeMap.get(groupType),null);
        for(DictGroup group : dict.getDictGroupList()){
            if(group.getName().equals(groupType)){
                return group;
            }
        }
        return null;
    }

    public Dict getDict(DictManager manager,String dictType){
        return manager.getDict(dictType,null);
    }

}
