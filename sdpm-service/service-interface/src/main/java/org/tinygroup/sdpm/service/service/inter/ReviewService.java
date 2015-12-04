package org.tinygroup.sdpm.service.service.inter;

import org.tinygroup.sdpm.service.dao.pojo.ServiceReview;

/**
 * Created by Administrator on 2015-09-17.
 */
public interface ReviewService {

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
    ServiceReview updateServiceReview(ServiceReview review);

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
