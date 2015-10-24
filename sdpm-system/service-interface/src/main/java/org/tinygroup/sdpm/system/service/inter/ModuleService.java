package org.tinygroup.sdpm.system.service.inter;

import org.tinygroup.sdpm.system.dao.pojo.SystemModule;

import java.util.List;

public interface ModuleService {

    /**
     * 编辑
     *
     * @param SystemModule
     * @return
     */
    SystemModule edit(SystemModule systemModule);

    /**
     * 删除ById
     *
     * @param id
     */
    int deleteById(int id);

    /**
     * 根据根节点进行查询
     *
     * @param root
     * @return
     */
    List<SystemModule> findModules(SystemModule systemModule);

    /**
     * 根据根节点进行递归查询
     *
     * @param root
     * @return
     */
    List<SystemModule> findAllModules(SystemModule systemModule);

    /**
     * 通过对象进行删除
     *
     * @param SystemModule
     * @return
     */
    int delete(SystemModule systemModule);

    /**
     * 通过ID查询对象
     *
     * @param id
     * @return
     */
    SystemModule findById(int id);

    /**
     * 添加模块
     *
     * @param systemModule
     * @return
     */
    SystemModule add(SystemModule systemModule);

    /**
     * 编辑title ang name
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
    int batchDelete(Integer... ids);

    /**
     * 根据条件查询LIST
     *
     * @param systemModule
     * @return
     */
    List<SystemModule> findModuleList(SystemModule systemModule);

    /**
     * 删除联动编辑Dict
     *
     * @param id
     * @return
     */
    public int deleteAndEdit(Integer id);

    int deleteByType(String type);
}
