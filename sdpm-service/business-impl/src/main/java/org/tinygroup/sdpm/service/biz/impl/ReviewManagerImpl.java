package org.tinygroup.sdpm.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.sdpm.service.biz.inter.ReviewManager;
import org.tinygroup.sdpm.service.dao.ServiceReviewDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceReview;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Service
public class ReviewManagerImpl implements ReviewManager {
    @Autowired
    private ServiceReviewDao reviewDao;

    public ServiceReview find(Integer id) {
        return reviewDao.getByKey(id);
    }

    public ServiceReview findByMolde(String moldeId) {
        return null;
    }

    public List<ServiceReview> getList(ServiceReview review) {
        return reviewDao.query(review);
    }

    public ServiceReview add(ServiceReview review) {
        return reviewDao.add(review);
    }

    public ServiceReview update(ServiceReview review) {
        reviewDao.edit(review);
        return review;
    }

    public ServiceReview findByRequestId(Integer id) {
        return reviewDao.findByRequestId(id);
    }
}
