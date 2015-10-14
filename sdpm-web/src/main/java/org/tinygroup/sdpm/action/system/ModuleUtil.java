package org.tinygroup.sdpm.action.system;

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
        if(!condition.toString().contains("in")){
            condition.append(" in (");
        }
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
        mergeModuleContidion(stringBuffer,moduleId,moduleService);
        stringBuffer.append(")");
        return stringBuffer.toString();
    }
}
