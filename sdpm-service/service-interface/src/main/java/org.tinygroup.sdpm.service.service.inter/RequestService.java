package org.tinygroup.sdpm.service.service.inter;

import org.tinygroup.sdpm.service.dao.pojo.Request;

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
    Request findRequest(Integer id);

    /**
     * 根据产品模块id查找请求
     *
     * @param moldeId 主键
     * @return
     */
    Request findRequestByMolde(String moldeId);

    /**
     * 根据条件查询List
     *
     * @param request 用于查询条件
     * @return
     */
    List<Request> getRequestList(Request request);

    /**
     * 新增请求
     *
     * @param request 新增实体类
     * @return
     */
    Request addRequest(Request request);

    /**
     * 更新faq
     *
     * @param request 需要更新的实体类
     * @return
     */
    Request updateRequest(Request request);

    /**
     * 根据id进行软删除请求
     *
     * @param id 主键
     * @return
     */
    Integer deleteRequest(String id);

    /**
     * 根据id进行批量软删除请求
     *
     * @param id 主键
     * @return
     */
    Integer deleteRequestBatch(Integer id);
}

