package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.service.biz.inter.FaqManager;
import org.tinygroup.sdpm.service.dao.pojo.ServiceFaq;
import org.tinygroup.sdpm.service.service.inter.FaqService;
import org.tinygroup.tinysqldsl.Pager;

/**
 * Created by Administrator on 2015-09-18.
 */
@Component
@Transactional
public class FaqServiceImpl implements FaqService {
    @Autowired
    private FaqManager faqManager;
    @Transactional(readOnly = true)
    public ServiceFaq findFaq(Integer id) {
        return faqManager.find(id);
    }

    /*分页*/
    @Transactional(readOnly = true)
    public Pager<ServiceFaq> getFaqpage(Integer start, Integer limit, ServiceFaq faq) {
        return faqManager.getpage(start, limit, faq);
    }

    public ServiceFaq addFaq(ServiceFaq faq) {
        return faqManager.add(faq);
    }

    public ServiceFaq updateFaq(ServiceFaq faq) {
        return faqManager.update(faq);
    }

    public Integer deleteFaq(Integer id) {
        return faqManager.delete(id);
    }
    @Transactional(readOnly = true)
    public Pager<ServiceFaq> searchFaq(Integer start, Integer limit, ServiceFaq faq, String faqQuestion) {
        return faqManager.search(start, limit, faq, faqQuestion);
    }
}
