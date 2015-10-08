package org.tinygroup.sdpm.service.service.inter;

import org.tinygroup.sdpm.service.dao.pojo.ServiceReview;

import java.util.List;

/**
 * Created by Administrator on 2015-09-17.
 */
public interface ReviewService {
    /**
     * 根据主键id查找回访中的请求
     *
     * @param id 主键
     * @return
     */
    ServiceReview findReview(Integer id);

    /**
     * 根据产品模块id回访中的请求
     *
     * @param moldeId 主键
     * @return
     */
    ServiceReview findReviewByMolde(String moldeId);

    /**
     * 根据条件查询List
     *
     * @param review 用于查询条件
     * @return
     */
    List<ServiceReview> getReviewList(ServiceReview review);

    /**
     * 将请求指派成员进行回访
     *
     * @param review 新增实体类
     * @return
     */
    ServiceReview addReview(ServiceReview review);

    /**
     * 更新faq
     *
     * @param review 需要更新的实体类
     * @return
     */
    ServiceReview updateReview(ServiceReview review);
    /**
     * 根据请求ID找到回访记录
     *
     * @param
     * @return
     */
    ServiceReview findReviewByRequestId(Integer id);

    /**
     * 根据status改变request表中的状态
     *
     * @param
     * @return
     */
    Integer changeStatus(Integer requestId);
}
