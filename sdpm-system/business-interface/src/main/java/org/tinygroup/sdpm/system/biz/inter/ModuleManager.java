package org.tinygroup.sdpm.system.biz.inter;

import org.tinygroup.sdpm.system.dao.pojo.SystemModule;

import java.util.List;

public interface ModuleManager {
    /**
     * 编辑
     *
     * @param systemModule
     * @return
     */
    SystemModule edit(SystemModule systemModule);

    /**
     * 编辑代码和中文名
     *
     * @param systemModule
     * @return
     */
    SystemModule editNameAndTitle(SystemModule systemModule);

    /**
     * 删除ById
     *
     * @param id
     */
    Integer deleteById(Integer id);

    /**
     * 根据根节点进行查询
     *
     * @param systemModule
     * @return
     */
    List<SystemModule> findByModules(SystemModule systemModule);

    /**
     * 根据根节点递归进行查询
     *
     * @param systemModule
     * @param list
     * @return
     */
    List<SystemModule> findModules(SystemModule systemModule, List<SystemModule> list);

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
    SystemModule add(SystemModule systemModule);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    Integer batchDelete(Integer... ids);

    /**
     * 根据条件查询list
     *
     * @param systemModule
     * @return
     */
    List<SystemModule> findList(SystemModule systemModule);

    /**
     * 删除module连带编辑字典
     *
     * @param id
     * @return
     */
    Integer deleteAndEdit(Integer id);

    Integer deleteByType(String type);
}
