package org.tinygroup.sdpm.dict.util;

import org.tinygroup.beancontainer.BeanContainerFactory;
import org.tinygroup.sdpm.dict.inter.DictItem;
import org.tinygroup.sdpm.dict.inter.DictNodeEntries;
import org.tinygroup.sdpm.dict.inter.DictNodeEntry;
import org.tinygroup.sdpm.dict.inter.DictParent;
import org.tinygroup.sdpm.system.dao.pojo.SystemDict;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.impl.DictServiceImpl;
import org.tinygroup.sdpm.system.service.impl.ModuleServiceImpl;
import org.tinygroup.sdpm.system.service.inter.DictService;
import org.tinygroup.sdpm.system.service.inter.ModuleService;
import org.tinygroup.sdpm.util.ModuleUtil;

import java.util.*;

/**
 * Created by wangll13383 on 2015/10/15.
 */
public class DictUtil extends DictParent {
    private static final ModuleService moduleService = BeanContainerFactory.getBeanContainer(DictUtil.class.getClassLoader()).getBean(ModuleServiceImpl.class);
    private static final DictService dictService = BeanContainerFactory.getBeanContainer(DictUtil.class.getClassLoader()).getBean(DictServiceImpl.class);
    public static void insertDict(List<DictNodeEntries> dictNodeEntries){
        for(DictNodeEntries entries : dictNodeEntries){
            int parent =0;
            if(entries.getName()!=null){
                parent = moduleService.add(assembleModule(entries.getName(),entries.getTitle(),"dict",0)).getModuleId();
                moduleService.deleteByType("dict");
                dictService.deleteAll();
            }
            mergeDictEntry(entries.getDictNodeEntries(),parent);
        }
    }

    private static void mergeDictEntry(List<DictNodeEntry> dictNodeEntries, int parent){
        if(dictNodeEntries==null)return;
        for (DictNodeEntry dictNodeEntry : dictNodeEntries) {
            int p = 0;
            if(dictNodeEntry.getDictItems()!=null) {
                p = moduleService.add(assembleModule(dictNodeEntry.getName(),dictNodeEntry.getTitle(),"dict",parent)).getModuleId();
                for (DictItem dictItem : dictNodeEntry.getDictItems()) {
                    dictService.addDict(assembleDict(dictItem.getKey(),dictItem.getValue(),dictItem.getSort(),p,0));
                }
            }
            mergeDictEntry(dictNodeEntry.getChildren(),p);
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
        if(sort>=0){
            dict.setDictSort(sort);
        }
        return dict;
    }

    public static void mergeDict(){
        List<SystemModule> moduleList = moduleService.findModuleList(assembleModule(null,null,"dict",0));
        for(SystemModule module : moduleList){
            entryLink = new ArrayList<String>();
            assemble(module,"");
            entryLinkMap.put(module.getModuleName(),entryLink);
        }
    }

    private static void assemble(SystemModule systemModule, String parent){
        SystemDict dict = assembleDict(null,null,-1,systemModule.getModuleId(),0);
        List<SystemDict> dicts = dictService.findList(dict,"dict_sort",true);
        Map<String,String> keyValue = new LinkedHashMap<String, String>();
        if(dicts!=null){
            for(SystemDict dict1 : dicts){
                keyValue.put(dict1.getDictKey(),dict1.getDictValue());
            }
        }
        if(keyValue.size()>0){
            dictMap.put(("".equals(parent)?"":parent + "/")+systemModule.getModuleName(),keyValue);
            entryLink.add(("".equals(parent)?"":parent + "/")+systemModule.getModuleName());
            if(!entryMapping.containsKey(systemModule.getModuleName())){
                entryMapping.put(systemModule.getModuleName(),("".equals(parent)?"":parent + "/") + systemModule.getModuleName());
            }else{
                entryMapping.remove(systemModule.getModuleName());
            }
        }
        List<SystemModule> moduleList = moduleService.findModuleList(assembleModule(null,null,null,systemModule.getModuleId()));
        for(SystemModule module : moduleList){
            assemble(module,("".equals(parent)?"":parent + "/") + systemModule.getModuleName());
        }
    }

    public static void addDict(SystemDict dict){
        SystemModule module = moduleService.findById(dict.getModuleId());
        String mPath = ModuleUtil.getPath(module.getModuleParent()==0?module.getModuleId():module.getModuleParent(),"/",moduleService,null,false).substring(1);
        for(String path : dictMap.keySet()){
            if(path.contains(mPath)){
                Map map = dictMap.get(mPath);
                map.put(dict.getDictKey(),dict.getDictValue());
                break;
            }
        }
    }

    public static void editDict(SystemDict oldDict, SystemDict newDict){
        SystemModule module = moduleService.findById(oldDict.getModuleId());
        String mPath = ModuleUtil.getPath(module.getModuleParent()==0?module.getModuleId():module.getModuleParent(),"/",moduleService,null,false).substring(1);
        for(String path : dictMap.keySet()){
            if(path.contains(mPath)){
                Map map = dictMap.get(mPath);
                map.remove(oldDict.getDictKey());
                map.put(newDict.getDictKey(),newDict.getDictValue());
                break;
            }
        }
    }

    public static String getValue(String type,String key){
        return dictMap.get(entryMapping.get(type)).get(key);
    }

    public static Map<String,String> getValueMap(String type){
        return dictMap.get(entryMapping.get(type));
    }

    public static String getValue(String root,String type,String key){
        return dictMap.get(type).get(key);
    }
}
