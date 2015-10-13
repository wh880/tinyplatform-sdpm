package org.tinygroup.sdpm.service.service.inter;

import org.tinygroup.sdpm.service.dao.pojo.ServiceSla;
import org.tinygroup.tinysqldsl.Pager;

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
    ServiceSla findSla(Integer id);

    /**
     * 根据条件查询List
     *
     * @param sla 用于查询条件
     * @return
     */
    List<ServiceSla> getSlaList(ServiceSla sla);

    /**
     * 新增一个用户
     *
     * @param sla 新增实体类
     * @return
     */
    ServiceSla addSla(ServiceSla sla);

    /**
     * 更新用户
     *
     * @param sla 需要更新的实体类
     * @return
     */
    ServiceSla updateSla(ServiceSla sla);

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

    /**
     * 查找数据库的所有记录
     *
     * @return
     */
    Pager<ServiceSla> findSlaPager(Integer start, Integer limit, ServiceSla sla);

    Pager<ServiceSla> findSlaPager2(Integer start, Integer limit, ServiceSla sla);
    /**
     * 2015/9/29,实现协议里面，点击客户ID，页面数据显示，新增的方法
     *
     * @return
     */
    List<ServiceSla> findSlaBySlaId(Integer id);
}
