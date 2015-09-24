package org.tinygroup.sdpm.service.biz.inter;

import org.tinygroup.sdpm.service.dao.pojo.ServiceFaq;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
public interface FaqManager { /**
 * 根据主键id查找faq
 *
 * @param id 主键
 * @return
 */
ServiceFaq find(Integer id);

    /**
     * 根据条件查询List
     *
     * @param faq 用于查询条件
     * @return
     */
    List<ServiceFaq> getList(ServiceFaq faq);

    /**
     * 新增一个faq
     *
     * @param faq 新增实体类
     * @return
     */
    ServiceFaq add(ServiceFaq faq);

    /**
     * 更新faq
     *
     * @param faq 需要更新的实体类
     * @return
     */
    ServiceFaq update(ServiceFaq faq);

    /**
     * 根据id进行软删除faq
     *
     * @param id 主键
     * @return
     */
    Integer delete(Integer id);
}
