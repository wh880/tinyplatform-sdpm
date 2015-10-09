package org.tinygroup.sdpm.service.biz.inter;

import org.tinygroup.sdpm.service.dao.pojo.ServiceReview;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
public interface ReviewManager {
    /**
     * 根据主键id查找回访中的请求
     *
     * @param id 主键
     * @return
     */
    ServiceReview find(Integer id);

    /**
     * 根据产品模块id回访中的请求
     *
     * @param moldeId 主键
     * @return
     */
    ServiceReview findByMolde(String moldeId);

    /**
     * 根据条件查询List
     *
     * @param review 用于查询条件
     * @return
     */
    List<ServiceReview> getList(ServiceReview review);

    /**
     * 将请求指派成员进行回访
     *
     * @param review 新增实体类
     * @return
     */
    ServiceReview add(ServiceReview review);

    /**
     * 更新faq
     *
     * @param review 需要更新的实体类
     * @return
     */
    ServiceReview update(ServiceReview review);
    /**
     * 根据请求ID找到回访记录
     *
     * @param
     * @return
     */
    ServiceReview findByRequestId(Integer id);

    /**
     * 指派回访
     *
     * @param
     * @return
     */
    int[] updateReview(List<ServiceReview> list, String name);
}

