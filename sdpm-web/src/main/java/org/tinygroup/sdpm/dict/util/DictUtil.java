package org.tinygroup.sdpm.dict.util;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.dict.Dict;
import org.tinygroup.dict.DictGroup;
import org.tinygroup.dict.DictItem;
import org.tinygroup.dict.DictManager;
import org.tinygroup.dict.impl.DictManagerImpl;
import org.tinygroup.logger.LogLevel;
import org.tinygroup.logger.Logger;
import org.tinygroup.logger.LoggerFactory;
import org.tinygroup.sdpm.dict.impl.DictHandleImpl;
import org.tinygroup.sdpm.dict.impl.SdpmDictLoader;
import org.tinygroup.sdpm.dict.inter.DictHandle;
import org.tinygroup.sdpm.dict.inter.DictNodeEntries;
import org.tinygroup.sdpm.dict.inter.DictNodeEntry;
import org.tinygroup.sdpm.system.dao.pojo.SystemDict;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.impl.DictServiceImpl;
import org.tinygroup.sdpm.system.service.impl.ModuleServiceImpl;
import org.tinygroup.sdpm.system.service.inter.DictService;
import org.tinygroup.sdpm.system.service.inter.ModuleService;

import java.util.*;

/**
 * Created by wangll13383 on 2015/10/15.
 */
public class DictUtil {
    private static final ModuleService moduleService = BeanContainerFactory.getBeanContainer(DictUtil.class.getClassLoader()).getBean(ModuleServiceImpl.class);
    private static final DictService dictService = BeanContainerFactory.getBeanContainer(DictUtil.class.getClassLoader()).getBean(DictServiceImpl.class);
    private static final SdpmDictLoader loader = BeanContainerFactory.getBeanContainer(DictUtil.class.getClassLoader()).getBean(SdpmDictLoader.class);
    private static final DictManager manager = BeanContainerFactory.getBeanContainer(DictUtil.class.getClassLoader()).getBean(DictManagerImpl.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(DictUtil.class);

    public static List<Dict> getDictFromFile(List<DictNodeEntries> dictNodeEntries,Map<String,String> nodeMap){
        List<Dict> dicts = new ArrayList<Dict>();
        mergeDict(dictNodeEntries,dicts,nodeMap);
        insertDict(dictNodeEntries);
        return dicts;
    }

    public static void insertDict(List<DictNodeEntries> dictNodeEntries){
        moduleService.deleteByType("dict");
        dictService.deleteAll();
        for(DictNodeEntries entries : dictNodeEntries){
            int parent =0;
            if(entries.getName()!=null){
                parent = moduleService.add(assembleModule(entries.getName(),entries.getTitle(),"dict",0)).getModuleId();
            }
            mergeDictEntry(entries.getDictNodeEntries(),parent);
        }
    }

    private static void mergeDictEntry(List<DictNodeEntry> dictNodeEntries, int parent){
        if(dictNodeEntries==null)return;
        for (DictNodeEntry dictNodeEntry : dictNodeEntries) {
            int p = 0;
            p = moduleService.add(assembleModule(dictNodeEntry.getName(),dictNodeEntry.getTitle(),"dict",parent)).getModuleId();
            if(dictNodeEntry.getDictItems()!=null) {
                for (org.tinygroup.sdpm.dict.inter.DictItem dictItem : dictNodeEntry.getDictItems()) {
                    dictService.addDict(assembleDict(dictItem.getKey(),dictItem.getValue(),dictItem.getSort(),p,0));
                }
            }
            mergeDictEntry(dictNodeEntry.getChildren(),p);
        }
    }

    public static void mergeDict(List<DictNodeEntries> dictNodeEntries, List<Dict> dicts,Map<String,String> nodeMap){
        if(dictNodeEntries==null)return;
        for(DictNodeEntries entries : dictNodeEntries){
            Dict dict = new Dict();
            dict.setName(entries.getName());
            dict.setDescription(entries.getTitle());
            List<DictGroup> groups = new ArrayList<DictGroup>();
            mergeDictFromFile(entries.getDictNodeEntries(),groups,nodeMap,entries.getName());
            dict.setDictGroupList(groups);
            dicts.add(dict);
        }
    }

    private static void mergeDictFromFile(List<DictNodeEntry> dictNodeEntries, List<DictGroup> groups,Map<String,String> nodeMap,String dictName){
        if(dictNodeEntries==null)return;
        for (DictNodeEntry dictNodeEntry : dictNodeEntries) {
            DictGroup group = null;
            if(dictNodeEntry.getDictItems()!=null) {
                group = new DictGroup();
                group.setName(dictNodeEntry.getName());
                group.setText(dictNodeEntry.getTitle());
                List<DictItem> items = new ArrayList<DictItem>();
                for(org.tinygroup.sdpm.dict.inter.DictItem dictItem : dictNodeEntry.getDictItems()){
                    DictItem item = new DictItem();
                    item.setValue(dictItem.getKey());
                    item.setText(dictItem.getValue());
                    items.add(item);
                }
                group.setItemList(items);
            }
            if(group!=null){
                nodeMap.put(group.getName(),dictName);
                groups.add(group);
            }
            mergeDictFromFile(dictNodeEntry.getChildren(),groups,nodeMap,dictName);

        }
    }

    private static SystemModule assembleModule(String moduleName, String moduleTitle, String moduleType, int moduleParent){
        SystemModule module = new SystemModule();
        module.setModuleName(moduleName);
        module.setModuleType(moduleType);
        module.setModuleParent(moduleParent);
        module.setModuleTitle(moduleTitle);
        return module;
    }

    private static SystemDict assembleDict(String dictKey, String dictValue, int sort, int moduleId,int deleted){
        SystemDict dict = new SystemDict();
        dict.setDictKey(dictKey);
        dict.setDictValue(dictValue);
        dict.setModuleId(moduleId);
        dict.setDeleted(deleted);
        dict.setDictSort(sort>0?sort:null);
        return dict;
    }

    public static List<Dict> mergeDict(Map<String,String> nodeMap){
        List<Dict> dicts = new ArrayList<Dict>();
        List<SystemModule> moduleList = moduleService.findModuleList(assembleModule(null,null,"dict",0));
        for(SystemModule module : moduleList){
            Dict dict = new Dict();
            dict.setName(module.getModuleName());
            dict.setDescription(module.getModuleTitle());
            List<DictGroup> groups = new ArrayList<DictGroup>();
            assemble(module,groups,nodeMap,module.getModuleName());
            dict.setDictGroupList(groups);
            dicts.add(dict);
        }
        return dicts;
    }

    private static void assemble(SystemModule systemModule, List<DictGroup> groups,Map<String,String> nodeMap,String dictName){
        SystemDict dict = assembleDict(null,null,-1,systemModule.getModuleId(),0);
        List<SystemDict> dicts = dictService.findList(dict,"dict_sort",true);
        if(dicts.size()>0){
            DictGroup group = new DictGroup();
            group.setName(systemModule.getModuleName());
            group.setText(systemModule.getModuleTitle());
            List<DictItem> items = new ArrayList<DictItem>();
            for(SystemDict dict1 : dicts){
                DictItem item = new DictItem();
                item.setValue(dict1.getDictKey());
                item.setText(dict1.getDictValue());
                items.add(item);
            }
            group.setItemList(items);
            nodeMap.put(group.getName(),dictName);
            groups.add(group);
        }
        List<SystemModule> moduleList = moduleService.findModuleList(assembleModule(null,null,null,systemModule.getModuleId()));
        for(SystemModule module : moduleList){
            assemble(module, groups,nodeMap,dictName);
        }
    }

    public static void reLoad(){
        loader.setIsInitFromFile(false);
        loader.load(manager);
    }

    public static String getValue(String groupType, String key){
        DictItem item = null;
        try {
            item = loader.getDictItem(manager,groupType,key);
        }catch (RuntimeException r){
            LOGGER.logMessage(LogLevel.ERROR,"找不到字典",r);
            return "";
        }
        if(item == null){
            return "";
        }
        return item.getText();
    }

    public static List<DictItem> getItemList(String groupType){
        DictGroup group = null;
        try{
            group = loader.getDictGroup(manager,groupType);
        }catch (RuntimeException r){
            LOGGER.logMessage(LogLevel.ERROR,"找不到字典",r);
            return new ArrayList<DictItem>();
        }
        if(group == null){
            return new ArrayList<DictItem>();
        }
        return group.getItemList();
    }
}
