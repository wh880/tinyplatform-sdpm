package org.tinygroup.sdpm.system.dao.impl.util;

import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangll13383 on 2015/11/16.
 */
public class DefaultModuleUtils {

    public static void mergeModuleCondition(StringBuffer condition, int moduleId, boolean withOperate ,ModuleListCallBackFunction moduleList) {
        SystemModule systemModule = new SystemModule();
        systemModule.setModuleParent(moduleId);
        List<SystemModule> systemModules = moduleList.getModuleList(systemModule);
        if(systemModules.size()>0){
            for(SystemModule module : systemModules){
                mergeModuleCondition(condition, module.getModuleId(),withOperate,moduleList);
            }
        }

        boolean judge = condition.toString().endsWith("(");
        if(!withOperate){
            judge = StringUtil.isBlank(condition.toString());
        }

        if(judge){
            condition.append(" "+moduleId);
        }else{
            condition.append(","+moduleId);
        }
    }

    public static StringBuffer getConditionByModule(StringBuffer condition,SystemModule systemModule,ModuleListCallBackFunction moduleList,ModuleCallBackFunction singleModule){

        if(systemModule==null){
            return null;
        }
        if(condition==null){
            condition = new StringBuffer();
        }
        List<SystemModule> modules = new ArrayList<SystemModule>();
        if(systemModule.getModuleId()==null){
            modules = moduleList.getModuleList(systemModule);
        }else{
            modules.add(singleModule.getModule(systemModule.getModuleId()));
        }
        if(modules.size()>0){
            for (SystemModule module : modules) {

                if(condition.toString().endsWith("(")){
                    condition.append(" "+module.getModuleId());
                }else{
                    condition.append(","+module.getModuleId());
                }
                int i = module.getModuleId();
                systemModule.setModuleParent(i);
                systemModule.setModuleId(null);
                getConditionByModule(condition,systemModule,moduleList,singleModule);
            }
        }


        return condition;
    }

    public static String getCondition(Integer moduleId,ModuleListCallBackFunction moduleList){
        if(moduleId==null||moduleId==0)return "";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("in (");
        mergeModuleCondition(stringBuffer, moduleId,true, moduleList);
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    public static String getConditionWithoutOperate(Integer moduleId,ModuleListCallBackFunction moduleList){
        if(moduleId==null||moduleId==0)return "";
        StringBuffer stringBuffer = new StringBuffer();
        mergeModuleCondition(stringBuffer, moduleId,false, moduleList);
        return StringUtil.isBlank(stringBuffer.toString())?"0":stringBuffer.toString();
    }

    public static String getConditionByRoot(Integer rootId,String moduleType, ModuleListCallBackFunction moduleList){
        if(rootId==null||rootId==0)return "";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("in (");
        SystemModule systemModule = new SystemModule();
        systemModule.setModuleRoot(rootId);
        systemModule.setModuleType(moduleType);
        List<SystemModule> systemModules = moduleList.getModuleList(systemModule);
        if(systemModules.size()>0){
            for(SystemModule module : systemModules){
                if(!stringBuffer.toString().endsWith("(")){
                    stringBuffer.append(",");
                }
                stringBuffer.append(module.getModuleId().toString());
            }
            stringBuffer.append(")");
        }else{
            return "in (0)";
        }
        return stringBuffer.toString();
    }

    public static String getConditionByRootWithoutOperate(Integer rootId,String moduleType, ModuleListCallBackFunction moduleList){
        if(rootId==null||rootId==0)return "";
        StringBuffer stringBuffer = new StringBuffer();
        SystemModule systemModule = new SystemModule();
        systemModule.setModuleRoot(rootId);
        systemModule.setModuleType(moduleType);
        List<SystemModule> systemModules = moduleList.getModuleList(systemModule);
        if(systemModules.size()>0){
            for(SystemModule module : systemModules){
                if (!StringUtil.isBlank(stringBuffer.toString())){
                    stringBuffer.append(",");
                }
                stringBuffer.append(module.getModuleId().toString());
            }
        }else{
            return "0";
        }
        return stringBuffer.toString();
    }

    public static String getPath(Integer moduleId, String division, ModuleCallBackFunction singleModule,String root, boolean openRoot){
        if(moduleId==null||moduleId<1)return "";
        SystemModule module = singleModule.getModule(moduleId);
        if (module == null) {
            return "/";
        }
        if(StringUtil.isBlank(module.getModulePath())||"0,".equals(module.getModulePath())){
            if(openRoot){
                return root+division+module.getModuleName();
            }
            return division+module.getModuleName();
        }
        String path = mergePath(division,module.getModulePath().substring(2),singleModule);
        if(openRoot){
            return root+division+(StringUtil.isBlank(path)?"":path+division)+module.getModuleName();
        }
        return division+(StringUtil.isBlank(path)?"":path+division)+module.getModuleName();
    }

    public static String mergePath(String division, String paths, ModuleCallBackFunction singleModule){
        String[] path = paths.split(",");
        if(path.length>1){
            return singleModule.getModule(Integer.parseInt(path[0]))
                    .getModuleName()+division+ mergePath(division,paths.substring(path[0].length()+1),singleModule);
        }else if(path.length>0){
            if(StringUtil.isBlank(path[0])){
                return "";
            }
            return singleModule.getModule(Integer.parseInt(path[0])).getModuleName();
        }
        return "";
    }
}
