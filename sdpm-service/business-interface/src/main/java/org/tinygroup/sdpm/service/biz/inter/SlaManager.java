package org.tinygroup.sdpm.service.biz.inter;

import org.tinygroup.sdpm.common.util.ComplexSearch.SearchInfos;
import org.tinygroup.sdpm.service.dao.pojo.ServiceSla;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
public interface SlaManager {
    /**
     * 根据主键id查找用户
     *
     * @param id 主键
     * @return
     */
    ServiceSla find(Integer id);

    /**
     * 根据条件查询List
     *
     * @param sla 用于查询条件
     * @return
     */
    List<ServiceSla> getList(ServiceSla sla);

    /**
     * 新增一个用户
     *
     * @param sla 新增实体类
     * @return
     */
    ServiceSla add(ServiceSla sla);

    /**
     * 更新用户
     *
     * @param sla 需要更新的实体类
     * @return
     */
    ServiceSla update(ServiceSla sla);

    /**
     * 根据id进行软删除用户
     *
     * @param id 主键
     * @return
     */
    Integer delete(Integer id);

    /**
     * 根据id进行批量软删除用户
     *
     * @param id 主键
     * @return
     */
    Integer deleteBatch(Integer id);

    /**
     * 根据客户id条件查询List
     *
     * @param clientId 用于查询条件
     * @return
     */
    List<ServiceSla> getListByClientId(Integer clientId);

    Pager<ServiceSla> findPager(Integer start, Integer limit, ServiceSla serviceClient, Integer treeId, String groupOperate, SearchInfos searchInfos, String order, String orderType);
    ServiceSla judgeClient(String clientName);
    int[] deleteBatch(List<ServiceSla> list);
}

