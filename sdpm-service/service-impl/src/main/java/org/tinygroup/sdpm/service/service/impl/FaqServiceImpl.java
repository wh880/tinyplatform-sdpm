package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.service.biz.inter.FaqManager;
import org.tinygroup.sdpm.service.dao.pojo.ServiceFaq;
import org.tinygroup.sdpm.service.service.inter.FaqService;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Component("")
public class FaqServiceImpl implements FaqService{
    @Autowired
    private FaqManager faqManager;

    public ServiceFaq findFaq(Integer id) {
        return faqManager.find(id);
    }

    public List<ServiceFaq> getFaqList(ServiceFaq faq) {
        return faqManager.getList(faq);
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
}
