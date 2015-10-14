package org.tinygroup.sdpm.util;

import org.tinygroup.sdpm.system.dao.pojo.SystemModule;
import org.tinygroup.sdpm.system.service.inter.ModuleService;

import java.util.List;

/**
 * Created by wangll13383 on 2015/10/13.
 */
public class ModuleUtil {

    private static void mergeModuleContidion(StringBuffer condition,int moduleId , ModuleService moduleService){
        SystemModule systemModule = new SystemModule();
        systemModule.setModuleParent(moduleId);
        List<SystemModule> systemModules = moduleService.findModuleList(systemModule);
        if(systemModules.size()>0){
            for(SystemModule module : systemModules){

                mergeModuleContidion(condition,module.getModuleId(),moduleService);
            }
        }
        if(condition.toString().endsWith("(")){
            condition.append(" "+moduleId);
        }else{
            condition.append(","+moduleId);
        }
    }

    public static String getCondition(int moduleId,ModuleService moduleService){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("in (");
        mergeModuleContidion(stringBuffer,moduleId,moduleService);
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    public static String getConditionByRoot(int rootId, ModuleService moduleService){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("in (");
        SystemModule systemModule = new SystemModule();
        systemModule.setModuleRoot(rootId);
        List<SystemModule> systemModules = moduleService.findModuleList(systemModule);
        if(systemModules.size()>0){
            for(SystemModule module : systemModules){
                mergeModuleContidion(stringBuffer,module.getModuleId(),moduleService);
            }
        }
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    public static String getPath(int moduleId, String division, ModuleService moduleService,String root, boolean openRoot){
        SystemModule module = moduleService.findById(moduleId);
        if(openRoot){
            return root+division+mergePath(division,module.getModulePath().substring(1),moduleService)+division+module.getModuleName();
        }
        return mergePath(division,module.getModulePath().substring(2),moduleService)+division+module.getModuleName();
    }

    private static String mergePath(String division, String paths, ModuleService moduleService){
        String[] path = paths.split(",");
        if(path.length>1){
            return moduleService.findById(Integer.parseInt(path[0])).getModuleName()+division+ mergePath(division,paths.substring(path[0].length()+1),moduleService);
        }else{
            return moduleService.findById(Integer.parseInt(path[0])).getModuleName();
        }
    }
}
