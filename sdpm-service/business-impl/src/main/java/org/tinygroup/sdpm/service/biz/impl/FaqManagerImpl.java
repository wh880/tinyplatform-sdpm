package org.tinygroup.sdpm.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.sdpm.service.biz.inter.FaqManager;
import org.tinygroup.sdpm.service.dao.ServiceFaqDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceFaq;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Service
public class FaqManagerImpl implements FaqManager {
    @Autowired
    private ServiceFaqDao faqDao;

    public ServiceFaq find(Integer id) {
        return faqDao.getByKey(id);
    }

    public List<ServiceFaq> getList(ServiceFaq faq) {
        return faqDao.query(faq);
    }

    /*分页显示调用的方法 */
    public Pager<ServiceFaq> getpage(Integer start, Integer limit, ServiceFaq serviceFaq) {
        /*ServiceFaq faq = new ServiceFaq();*/
        return faqDao.queryPager(start, limit, serviceFaq);
    }

    public ServiceFaq add(ServiceFaq faq) {
        return faqDao.add(faq);
    }

    public ServiceFaq update(ServiceFaq faq) {
        faqDao.edit(faq);
        return faq;
    }

    /*点击问题进去，里面的删除，在原有的基础上直接修改*/
    public Integer delete(Integer id) {
       /* ServiceFaq faq = new ServiceFaq();
       faq.setFaqId(id);
        faq.setDeleted(id);
        return faqDao.edit(faq);*/
        return faqDao.softDelete(id);
    }


    public Pager<ServiceFaq> findUserListByDeptId(Integer start, Integer limit, Integer deptId) {
        return faqDao.getPagerByDeptId(start, limit, deptId);
    }

    public Pager<ServiceFaq> search(Integer start, Integer limit, ServiceFaq faq, String faqQuestion) {
        return faqDao.searchPager(start, limit, faq, faqQuestion);
    }

}
