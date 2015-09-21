package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.tinygroup.sdpm.service.biz.inter.ReviewManager;
import org.tinygroup.sdpm.service.dao.pojo.Review;
import org.tinygroup.sdpm.service.service.inter.ReviewService;

import java.util.List;

/**
 * Created by Administrator on 2015-09-19.
 */
@Component("reviewService")
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewManager reviewManager;
    public Review findReview(Integer id) {
        return reviewManager.find(id);
    }

    public Review findReviewByMolde(String moldeId) {
        return reviewManager.findByMolde(moldeId);
    }

    public List<Review> getReviewList(Review review) {
        return reviewManager.getList(review);
    }

    public Review addReview(Review review) {
        return reviewManager.add(review);
    }

    public Review updateReview(Review review) {
        return reviewManager.update(review);
    }
}
