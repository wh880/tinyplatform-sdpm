package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.service.biz.inter.RequestManager;
import org.tinygroup.sdpm.service.biz.inter.ReviewManager;
import org.tinygroup.sdpm.service.dao.pojo.ServiceReview;
import org.tinygroup.sdpm.service.service.inter.ReviewService;

import java.util.List;

/**
 * Created by Administrator on 2015-09-19.
 */
@Component
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewManager reviewManager;
    @Autowired
    private RequestManager requestManager;
    public ServiceReview findReview(Integer id) {
        return reviewManager.find(id);
    }

    public ServiceReview findReviewByMolde(String moldeId) {
        return reviewManager.findByMolde(moldeId);
    }

    public List<ServiceReview> getReviewList(ServiceReview review) {
        return reviewManager.getList(review);
    }

    public ServiceReview addReview(ServiceReview review) {
        return reviewManager.add(review);
    }

    public ServiceReview updateReview(ServiceReview review) {
        return reviewManager.update(review);
    }

    public ServiceReview findReviewByRequestId(Integer id) {
        return reviewManager.findByRequestId(id);
    }

    public Integer changeStatus(Integer requestId) {
        return requestManager.changeStatus(requestId);
    }

    public int[] updateReview(List<ServiceReview> list, String name) {
        return reviewManager.updateReview(list, name);
    }
}
