package org.tinygroup.sdpm.system.service.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemModule;

public interface ModuleService {
	
	/**
	 * 编辑
	 * @param SystemModule
	 * @return
	 */
	 SystemModule edit(SystemModule systemModule);
	 
	/**
	 * 删除ById
	 * @param id
	 */
	 int deleteById(int id);
	
	/**
	 * 根据根节点进行查询
	 * @param root
	 * @return
	 */
	 List<SystemModule> findModules(SystemModule systemModule);
	 /**
	  * 通过对象进行删除
	  * @param SystemModule
	  * @return
	  */
	 int delete(SystemModule systemModule);
	 /**
	  * 通过ID查询对象
	  * @param id
	  * @return
	  */
	 SystemModule findById(int id);
	 /**
	  * 添加模块
	  * @param systemModule
	  * @return
	  */
	 SystemModule add(SystemModule systemModule);
	 /**
	  * 编辑title ang name
	  * @param systemModule
	  * @return
	  */
	 SystemModule eidtNameAndTiele(SystemModule systemModule);
	
	 
	 
}
