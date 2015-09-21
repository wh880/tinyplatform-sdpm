package org.tinygroup.sdpm.service.biz.inter;

import org.tinygroup.sdpm.service.dao.pojo.Review;

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
    Review find(Integer id);

    /**
     * 根据产品模块id回访中的请求
     *
     * @param moldeId 主键
     * @return
     */
    Review findByMolde(String moldeId);

    /**
     * 根据条件查询List
     *
     * @param review 用于查询条件
     * @return
     */
    List<Review> getList(Review review);

    /**
     * 将请求指派成员进行回访
     *
     * @param review 新增实体类
     * @return
     */
    Review add(Review review);

    /**
     * 更新faq
     *
     * @param review 需要更新的实体类
     * @return
     */
    Review update(Review review);
}

