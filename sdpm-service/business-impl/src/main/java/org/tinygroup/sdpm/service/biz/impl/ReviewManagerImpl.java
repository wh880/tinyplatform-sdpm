package org.tinygroup.sdpm.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.service.biz.inter.ReviewManager;
import org.tinygroup.sdpm.service.dao.ReviewDao;
import org.tinygroup.sdpm.service.dao.pojo.Review;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Service
@Transactional
public class ReviewManagerImpl implements ReviewManager{
    @Autowired
    private ReviewDao reviewDao;
    public Review find(Integer id) { return reviewDao.getByKey(id);}

    public Review findByMolde(String moldeId) {
        return null;
    }

    public List<Review> getList(Review review) {
        return reviewDao.query(review);
    }

    public Review add(Review review) {
        return reviewDao.add(review);
    }

    public Review update(Review review) {
        reviewDao.edit(review);
        return review;
    }
}
