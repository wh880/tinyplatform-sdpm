package org.tinygroup.sdpm.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.service.biz.inter.FaqManager;
import org.tinygroup.sdpm.service.dao.FaqDao;
import org.tinygroup.sdpm.service.dao.pojo.Faq;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Service
@Transactional
public class FaqManagerImpl implements FaqManager{
    @Autowired
    private FaqDao faqDao;
    public Faq find(Integer id) {
        return faqDao.getByKey(id);
    }

    public List<Faq> getList(Faq faq) {
        return faqDao.query(faq);
    }

    public Faq add(Faq faq) {
        return faqDao.add(faq);
    }

    public Faq update(Faq faq) {
         faqDao.edit(faq);
        return faq;
    }

    public Integer delete(Integer id) {
        Faq faq=new Faq();
       faq.setFaqId(id);
        faq.setDeleted(id);
        return faqDao.edit(faq);
    }
}
