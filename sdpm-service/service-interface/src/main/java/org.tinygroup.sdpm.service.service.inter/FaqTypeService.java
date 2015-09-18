package org.tinygroup.sdpm.service.service.inter;

import org.tinygroup.sdpm.service.dao.pojo.FaqType;

import java.util.List;

/**
 * Created by Administrator on 2015-09-17.
 */
public interface FaqTypeService {
    /**
     * 根据主键id查找faqtype
     *
     * @param id 主键
     * @return
     */
    FaqType findFaqType(String id);

    /**
     * 根据条件查询List
     *
     * @param faqType 用于查询条件
     * @return
     */
    List<FaqType> getFaqTypeList(FaqType faqType);

    /**
     * 新增有一faqtype
     *
     * @param faqType 新增实体类
     * @return
     */
    FaqType addFaqType(FaqType faqType);

    /**
     * 更新faqtype
     *
     * @param faqType 需要更新的实体类
     * @return
     */
    FaqType updateFaqType(FaqType faqType);

    /**
     * 根据id进行删除faqtype
     *
     * @param id 主键
     * @return
     */
    List<FaqType> deleteFaqType(Integer id);
}
