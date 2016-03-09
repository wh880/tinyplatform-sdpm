package org.tinygroup.sdpm.system.service.inter;

import org.tinygroup.sdpm.system.dao.pojo.SystemModule;

import java.util.List;

public interface ModuleService {

    /**
     * 编辑
     *
     * @param systemModule
     * @return
     */
    SystemModule editModule(SystemModule systemModule);

    /**
     * 删除ById
     *
     * @param id
     */
    Integer deleteModuleById(Integer id);


    /**
     * 根据条件查询LIST
     *
     * @param systemModule
     * @return
     */
    List<SystemModule> findModuleList(SystemModule systemModule);

    /**
     * 根据根节点进行递归查询
     *
     * @param systemModule
     * @return
     */
    List<SystemModule> findAllModules(SystemModule systemModule);

    /**
     * 通过对象进行删除
     *
     * @param systemModule
     * @return
     */
    Integer delete(SystemModule systemModule);

    /**
     * 通过ID查询对象
     *
     * @param id
     * @return
     */
    SystemModule findById(Integer id);

    /**
     * 添加模块
     *
     * @param systemModule
     * @return
     */
    SystemModule addSystemModule(SystemModule systemModule);

    /**
     * 编辑模块内容
     *
     * @param systemModule
     * @return
     */
    SystemModule editNameAndTitle(SystemModule systemModule);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    Integer batchDeleteSystemModule(Integer... ids);

    /**
     * 根据类型删除模块-主要用于字典初始化
     *
     * @param type
     * @return
     */
    Integer deleteSystemModuleByType(String type);
}
