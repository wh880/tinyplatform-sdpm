package org.tinygroup.sdpm.system.biz.impl;

import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.system.biz.inter.ModuleManager;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangll13383 on 2015/10/13.
 */
public class ModuleUtil {

    private static void mergeModuleCondition(StringBuffer condition, int moduleId, ModuleManager moduleManager) {
        SystemModule systemModule = new SystemModule();
        systemModule.setModuleParent(moduleId);
        List<SystemModule> systemModules = moduleManager.findList(systemModule);
        if(systemModules.size()>0){
            for(SystemModule module : systemModules){
                mergeModuleCondition(condition, module.getModuleId(), moduleManager);
            }
        }
        if(condition.toString().endsWith("(")){
            condition.append(" "+moduleId);
        }else{
            condition.append(","+moduleId);
        }
    }

    public static StringBuffer getConditionByModule(StringBuffer condition,SystemModule systemModule,ModuleManager moduleManager){

    	if(systemModule==null){
			return null;
		}
		if(condition==null){
			condition = new StringBuffer();
		}
		List<SystemModule> modules = new ArrayList<SystemModule>();
		if(systemModule.getModuleId()==null){
			modules = moduleManager.findList(systemModule);
        }else{
			modules.add(moduleManager.findById(systemModule.getModuleId()));
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
				getConditionByModule(condition,systemModule,moduleManager);
			}
		}


    	return condition;
    }

    public static String getCondition(Integer moduleId,ModuleManager moduleManager){
        if(moduleId==null||moduleId<1)return "";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("in (");
        mergeModuleCondition(stringBuffer, moduleId, moduleManager);
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    public static String getConditionByRoot(Integer rootId, ModuleManager moduleManager){
        if(rootId==null||rootId<1)return "";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("in (");
        SystemModule systemModule = new SystemModule();
        systemModule.setModuleRoot(rootId);
        List<SystemModule> systemModules = moduleManager.findList(systemModule);
        if(systemModules.size()>0){
            for(SystemModule module : systemModules){
                mergeModuleCondition(stringBuffer, module.getModuleId(), moduleManager);
            }
            stringBuffer.append(")");
        }else{
            return "";
        }
        return stringBuffer.toString();
    }

    public static String getPath(Integer moduleId, String division, ModuleManager moduleManager,String root, boolean openRoot){
        if(moduleId==null||moduleId<1)return "";
        SystemModule module = moduleManager.findById(moduleId);
        if (module == null) {
            return "/";
        }
        if(StringUtil.isBlank(module.getModulePath())||"0,".equals(module.getModulePath())){
            return module.getModuleName();
        }
        String path = mergePath(division,module.getModulePath().substring(2),moduleManager);
        if(openRoot){
            return root+division+("".equals(path)?"":path+division)+module.getModuleName();
        }
        return (StringUtil.isBlank(path)?"":path+division)+module.getModuleName();
    }

    private static String mergePath(String division, String paths, ModuleManager moduleManager){
        String[] path = paths.split(",");
        if(path.length>1){
            return moduleManager.findById(Integer.parseInt(path[0]))
                    .getModuleName()+division+ mergePath(division,paths.substring(path[0].length()+1),moduleManager);
        }else if(path.length>0){
            if(StringUtil.isBlank(path[0])){
                return "";
            }
            return moduleManager.findById(Integer.parseInt(path[0])).getModuleName();
        }
        return "";
    }
}
