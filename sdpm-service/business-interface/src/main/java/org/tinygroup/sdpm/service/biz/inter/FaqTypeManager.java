package org.tinygroup.sdpm.service.biz.inter;

import org.tinygroup.sdpm.service.dao.pojo.ServiceFaqType;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
public interface FaqTypeManager {
    /**
     * 根据主键id查找faqtype
     *
     * @param id 主键
     * @return
     */
    ServiceFaqType find(Integer id);

    /**
     * 根据条件查询List
     *
     * @param faqType 用于查询条件
     * @return
     */
    List<ServiceFaqType> getList(ServiceFaqType faqType);

    /**
     * 新增有一faqtype
     *
     * @param faqType 新增实体类
     * @return
     */
    ServiceFaqType add(ServiceFaqType faqType);

    /**
     * 更新faqtype
     *
     * @param faqType 需要更新的实体类
     * @return
     */
    ServiceFaqType update(ServiceFaqType faqType);

    /**
     * 根据id进行删除faqtype
     *
     * @param id 主键
     * @return
     */
    List<ServiceFaqType> delete(Integer id);

    Integer deleteTree(Integer id);
}

