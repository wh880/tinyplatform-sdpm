package org.tinygroup.sdpm.service.service.inter;

import org.tinygroup.sdpm.service.dao.pojo.Sla;

import java.util.List;

/**
 * Created by Administrator on 2015-09-17.
 */
public interface SlaService {
    /**
     * 根据主键id查找用户
     *
     * @param id 主键
     * @return
     */
    Sla findSla(Integer id);

    /**
     * 根据条件查询List
     *
     * @param sla 用于查询条件
     * @return
     */
    List<Sla> getSlaList(Sla sla);

    /**
     * 新增一个用户
     *
     * @param sla 新增实体类
     * @return
     */
    Sla addSla(Sla sla);

    /**
     * 更新用户
     *
     * @param sla 需要更新的实体类
     * @return
     */
    Sla updateSla(Sla sla);

    /**
     * 根据id进行软删除用户
     *
     * @param id 主键
     * @return
     */
    Integer deleteSla(Integer id);

    /**
     * 根据id进行批量软删除用户
     *
     * @param id 主键
     * @return
     */
    Integer deleteSlaBatch(Integer id);
}
