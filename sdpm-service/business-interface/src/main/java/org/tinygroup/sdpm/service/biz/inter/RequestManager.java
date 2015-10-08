package org.tinygroup.sdpm.service.biz.inter;

import org.tinygroup.sdpm.service.dao.pojo.ServiceRequest;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
public interface RequestManager {
    /**
     * 根据主键id查找请求
     *
     * @param id 主键
     * @return
     */
    ServiceRequest find(Integer id);

    /**
     * 根据产品模块id查找请求
     *
     * @param moldeId 主键
     * @return
     */
    ServiceRequest findByMolde(String moldeId);

    /**
     * 根据条件查询List
     *
     * @param request 用于查询条件
     * @return
     */
    List<ServiceRequest> getList(ServiceRequest request);

    /**
     * 新增请求
     *
     * @param request 新增实体类
     * @return
     */
    ServiceRequest add(ServiceRequest request);

    /**
     * 更新faq
     *
     * @param request 需要更新的实体类
     * @return
     */
    ServiceRequest update(ServiceRequest request);

    /**
     * 根据id进行软删除请求
     *
     * @param id 主键
     * @return
     */
    Integer delete(Integer id);

    /**
     * 根据id进行批量软删除请求
     *
     * @param id 主键
     * @return
     */
    Integer deleteBatch(Integer... id);

    /**
     * 分页查找所有请求
     *
     * @return
     */
    Pager<ServiceRequest> findPager(Integer start, Integer limit, Integer status, ServiceRequest serviceRequest);

    /**
     * 关闭请求
     *
     * @return
     */
    Integer close(ServiceRequest clientRequest);

    /**
     * 保存回复
     *
     * @return
     */
    Integer saveReply(ServiceRequest clientRequest);

    /**
     * 根据status改变request表中的状态
     *
     * @param
     * @return
     */
    Integer changeStatus(Integer id);
}

