package org.tinygroup.sdpm.system.dao.impl;

import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.sdpm.system.dao.SystemModuleDao;
import org.tinygroup.sdpm.system.dao.pojo.SystemModule;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangll13383 on 2015/10/13.
 */
public class ModuleUtil {

    private static void mergeModuleCondition(StringBuffer condition, int moduleId, SystemModuleDao systemModuleDao) {
        SystemModule systemModule = new SystemModule();
        systemModule.setModuleParent(moduleId);
        List<SystemModule> systemModules = systemModuleDao.query(systemModule);
        if(systemModules.size()>0){
            for(SystemModule module : systemModules){
                mergeModuleCondition(condition, module.getModuleId(), systemModuleDao);
            }
        }
        if(condition.toString().endsWith("(")){
            condition.append(" "+moduleId);
        }else{
            condition.append(","+moduleId);
        }
    }

    public static StringBuffer getConditionByModule(StringBuffer condition,SystemModule systemModule,SystemModuleDao systemModuleDao){

    	if(systemModule==null){
			return null;
		}
		if(condition==null){
			condition = new StringBuffer();
		}
		List<SystemModule> modules = new ArrayList<SystemModule>();
		if(systemModule.getModuleId()==null){
			modules = systemModuleDao.query(systemModule);
        }else{
			modules.add(systemModuleDao.getByKey(systemModule.getModuleId()));
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
				getConditionByModule(condition,systemModule,systemModuleDao);
			}
		}


    	return condition;
    }

    public static String getCondition(Integer moduleId,SystemModuleDao systemModuleDao){
        if(moduleId==null||moduleId<1)return "";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("in (");
        mergeModuleCondition(stringBuffer, moduleId, systemModuleDao);
        stringBuffer.append(")");
        return stringBuffer.toString();
    }

    public static String getConditionByRoot(Integer rootId, SystemModuleDao systemModuleDao){
        if(rootId==null||rootId<1)return "";
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("in (");
        SystemModule systemModule = new SystemModule();
        systemModule.setModuleRoot(rootId);
        List<SystemModule> systemModules = systemModuleDao.query(systemModule);
        if(systemModules.size()>0){
            for(SystemModule module : systemModules){
                mergeModuleCondition(stringBuffer, module.getModuleId(), systemModuleDao);
            }
            stringBuffer.append(")");
        }else{
            return "";
        }
        return stringBuffer.toString();
    }

    public static String getPath(Integer moduleId, String division, SystemModuleDao systemModuleDao,String root, boolean openRoot){
        if(moduleId==null||moduleId<1)return "";
        SystemModule module = systemModuleDao.getByKey(moduleId);
        if (module == null) {
            return "/";
        }
        if(StringUtil.isBlank(module.getModulePath())||"0,".equals(module.getModulePath())){
            return module.getModuleName();
        }
        String path = mergePath(division,module.getModulePath().substring(2),systemModuleDao);
        if(openRoot){
            return root+division+("".equals(path)?"":path+division)+module.getModuleName();
        }
        return (StringUtil.isBlank(path)?"":path+division)+module.getModuleName();
    }

    private static String mergePath(String division, String paths, SystemModuleDao systemModuleDao){
        String[] path = paths.split(",");
        if(path.length>1){
            return systemModuleDao.getByKey(Integer.parseInt(path[0]))
                    .getModuleName()+division+ mergePath(division,paths.substring(path[0].length()+1),systemModuleDao);
        }else if(path.length>0){
            if(StringUtil.isBlank(path[0])){
                return "";
            }
            return systemModuleDao.getByKey(Integer.parseInt(path[0])).getModuleName();
        }
        return "";
    }
}
