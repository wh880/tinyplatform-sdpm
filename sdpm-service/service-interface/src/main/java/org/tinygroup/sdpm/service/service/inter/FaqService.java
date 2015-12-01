package org.tinygroup.sdpm.service.service.inter;

import org.tinygroup.sdpm.service.dao.pojo.ServiceFaq;
import org.tinygroup.tinysqldsl.Pager;

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
    ServiceFaq findFaq(Integer id);

    /**
     * 根据条件查询List
     *
     * @param faq 用于查询条件
     * @return
     */
    List<ServiceFaq> getFaqList(ServiceFaq faq);

    /**
     * 分页
     *
     * @param faq 用于查询条件
     * @return
     */
    Pager<ServiceFaq> getFaqpage(Integer start, Integer limit, ServiceFaq faq);

    /**
     * 新增一个faq
     *
     * @param faq 新增实体类
     * @return
     */
    ServiceFaq addFaq(ServiceFaq faq);

    /**
     * 更新faq
     *
     * @param faq 需要更新的实体类
     * @return
     */
    ServiceFaq updateFaq(ServiceFaq faq);

    /**
     * 根据id进行软删除faq
     *
     * @param id 主键
     * @return
     */
    Integer deleteFaq(Integer id);

    /**
     * 查询faq中问题的数目
     *
     * @param id 主键
     * @return
     */
   /* Integer selectcount(Integer id);*/
    Pager<ServiceFaq> findUserByDeptId(Integer start, Integer limit, Integer deptId);

    /**
     * 搜索Faq
     *
     * @param
     * @return
     */
    Pager<ServiceFaq> searchFaq(Integer start, Integer limit, ServiceFaq faq, String faqQuestion);
}
