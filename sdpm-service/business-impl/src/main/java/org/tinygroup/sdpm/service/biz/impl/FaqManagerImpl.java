package org.tinygroup.sdpm.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.service.biz.inter.FaqManager;
import org.tinygroup.sdpm.service.dao.ServiceFaqDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceFaq;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Service
@Transactional
public class FaqManagerImpl implements FaqManager{
    @Autowired
    private ServiceFaqDao faqDao;

    public ServiceFaq find(Integer id) {
        return faqDao.getByKey(id);
    }

    public List<ServiceFaq> getList(ServiceFaq faq) {
        return faqDao.query(faq);
    }
     /*分页显示调用的方法 */
    /*public Pager<ServiceFaq> getpage(int start, int limit) {
        return faqDao.queryPager( start,  limit);
    }*/

    public ServiceFaq add(ServiceFaq faq) {
        return faqDao.add(faq);
    }

    public ServiceFaq update(ServiceFaq faq) {
         faqDao.edit(faq);
        return faq;
    }

    public Integer delete(Integer id) {
        ServiceFaq faq = new ServiceFaq();
       faq.setFaqId(id);
        faq.setDeleted(id);
        return faqDao.edit(faq);
    }


    /*查询问题总条数*/
    public Integer selectcount(Integer id) {
        return faqDao.SelectByKey();
    }

}
