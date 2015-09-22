package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.service.biz.inter.FaqManager;
import org.tinygroup.sdpm.service.dao.pojo.Faq;
import org.tinygroup.sdpm.service.service.inter.FaqService;


import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Component("")
public class FaqServiceImpl implements FaqService{
    @Autowired
    private FaqManager faqManager;
    public Faq findFaq(Integer id) {
        return faqManager.find(id);
    }

    public List<Faq> getFaqList(Faq faq) {
        return faqManager.getList(faq);
    }

    public Faq addFaq(Faq faq) {
        return faqManager.add(faq);
    }

    public Faq updateFaq(Faq faq) {
        return faqManager.update(faq);
    }

    public Integer deleteFaq(Integer id) {
        return faqManager.delete(id);
    }
}
