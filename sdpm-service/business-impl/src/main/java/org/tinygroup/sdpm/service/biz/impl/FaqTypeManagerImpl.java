package org.tinygroup.sdpm.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.sdpm.service.biz.inter.FaqTypeManager;
import org.tinygroup.sdpm.service.dao.ServiceFaqTypeDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceFaqType;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Service
public class FaqTypeManagerImpl implements FaqTypeManager {
    @Autowired
    private ServiceFaqTypeDao faqTypeDao;

    public ServiceFaqType find(Integer id) {
        return faqTypeDao.getByKey(id);
    }

    public List<ServiceFaqType> getList(ServiceFaqType faqType) {
        return faqTypeDao.query(faqType);
    }

    public ServiceFaqType add(ServiceFaqType faqType) {
        return faqTypeDao.add(faqType);
    }

    public ServiceFaqType update(ServiceFaqType faqType) {
        faqTypeDao.edit(faqType);
        return faqType;
    }

    public List<ServiceFaqType> delete(Integer id) {
        ServiceFaqType faqType = new ServiceFaqType();
        faqType.setFaqTypeId(id);
        faqType.setDeleted(id);
        return faqTypeDao.query(faqType);
    }

    public Integer deleteTree(Integer id) {
        return faqTypeDao.deleteByKey(id);
    }
}
