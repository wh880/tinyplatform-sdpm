package org.tinygroup.sdpm.service.service.inter;

import org.tinygroup.sdpm.service.dao.pojo.Faq;

import java.util.List;

/**
 * Created by Administrator on 2015-09-17.
 */
public interface FaqService {
    /**
     * 根据主键id查找faq
     *
     * @param id 主键
     * @return
     */
    Faq findFaq(String id);

    /**
     * 根据条件查询List
     *
     * @param faq 用于查询条件
     * @return
     */
    List<Faq> getFaqList(Faq faq);

    /**
     * 新增一个faq
     *
     * @param faq 新增实体类
     * @return
     */
    Faq addFaq(Faq faq);

    /**
     * 更新faq
     *
     * @param faq 需要更新的实体类
     * @return
     */
    Faq updateFaq(Faq faq);

    /**
     * 根据id进行软删除faq
     *
     * @param id 主键
     * @return
     */
    Integer deleteFaq(String id);
}