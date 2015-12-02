package org.tinygroup.sdpm.common.service.inter;

import org.tinygroup.sdpm.dao.pojo.SysModule;

import java.util.List;

public interface SysModuleService {

    /**
     * 编辑
     *
     * @param sysModule
     * @return
     */
    SysModule edit(SysModule sysModule);

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
    List<SysModule> findByRoot(int root);

    /**
     * 通过对象进行删除
     *
     * @param sysModule
     * @return
     */
    int delete(SysModule sysModule);


}
