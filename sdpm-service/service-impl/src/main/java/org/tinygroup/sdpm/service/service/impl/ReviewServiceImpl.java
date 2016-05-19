package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.service.biz.inter.RequestManager;
import org.tinygroup.sdpm.service.biz.inter.ReviewManager;
import org.tinygroup.sdpm.service.dao.pojo.ServiceReview;
import org.tinygroup.sdpm.service.service.inter.ReviewService;

/**
 * Created by Administrator on 2015-09-19.
 */
@Component
@Transactional
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewManager reviewManager;
    @Autowired
    private RequestManager requestManager;

    public ServiceReview addReview(ServiceReview review) {
        return reviewManager.add(review);
    }

    public ServiceReview updateServiceReview(ServiceReview review) {
        return reviewManager.update(review);
    }
    @Transactional(readOnly = true)
    public ServiceReview findReviewByRequestId(Integer id) {
        return reviewManager.findByRequestId(id);
    }

    public Integer changeStatus(Integer requestId) {
        return requestManager.changeStatus(requestId);
    }

}
