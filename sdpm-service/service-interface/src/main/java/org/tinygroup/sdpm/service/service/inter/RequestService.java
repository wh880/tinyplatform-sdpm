package org.tinygroup.sdpm.service.service.inter;

import org.tinygroup.sdpm.service.dao.pojo.ServiceRequest;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by Administrator on 2015-09-17.
 */
public interface RequestService {
    /**
     * 根据主键id查找请求
     *
     * @param id 主键
     * @return
     */
    ServiceRequest findRequest(Integer id);

    /**
     * 根据产品模块id查找请求
     *
     * @param moldeId 主键
     * @return
     */
    ServiceRequest findRequestByMolde(String moldeId);

    /**
     * 根据条件查询List
     *
     * @param request 用于查询条件
     * @return
     */
    List<ServiceRequest> getRequestList(ServiceRequest request);

    /**
     * 新增请求
     *
     * @param request 新增实体类
     * @return
     */
    ServiceRequest addRequest(ServiceRequest request);

    /**
     * 更新faq
     *
     * @param request 需要更新的实体类
     * @return
     */
    ServiceRequest updateRequest(ServiceRequest request);

    /**
     * 根据id进行软删除请求
     *
     * @param id 主键
     * @return
     */
    Integer deleteRequest(Integer id);

    /**
     * 根据id进行批量软删除请求
     *
     * @param id 主键
     * @return
     */
    Integer deleteRequestBatch(Integer... id);

    /**
     * 查找数据库的所有记录
     *
     * @return
     */
    Pager<ServiceRequest> findRequestPager(Integer start, Integer limit, Integer status,ServiceRequest clientRequest);

    /**
     * 关闭请求
     *
     * @return
     */
    Integer closeRequest(ServiceRequest clientRequest);
    /**
     * 保存回复
     *
     * @return
     */
    Integer saveReply(ServiceRequest clientRequest);

    int[] updateReply(List<ServiceRequest> list);

    int[] updateReview(List<ServiceRequest> list);
}

