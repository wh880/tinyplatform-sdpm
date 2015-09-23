package org.tinygroup.sdpm.system.biz.inter;

import java.util.List;

import org.tinygroup.sdpm.system.dao.pojo.SystemModule;

public interface ModuleManager {
	/**
	 * 编辑
	 * @param SystemModule
	 * @return
	 */
	 SystemModule edit(SystemModule SystemModule);
	 
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
	 List<SystemModule> findByRoot(int root);
	 /**
	  * 通过对象进行删除
	  * @param SystemModule
	  * @return
	  */
	 int delete(SystemModule SystemModule);
	 /**
	  * 通过ID查询对象
	  * @param id
	  * @return
	  */
	 SystemModule findById(int id);
}
