package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.service.biz.inter.FaqTypeManager;
import org.tinygroup.sdpm.service.dao.pojo.ServiceFaqType;
import org.tinygroup.sdpm.service.service.inter.FaqTypeService;

import java.util.List;

/**
 * Created by Administrator on 2015-09-19.
 */
@Component
@Transactional
public class FaqTypeServiceImpl implements FaqTypeService {
    @Autowired
    private FaqTypeManager faqTypeManager;
    @Transactional(readOnly = true)
    public ServiceFaqType findFaqType(Integer id) {
        return faqTypeManager.find(id);
    }
    @Transactional(readOnly = true)
    public List<ServiceFaqType> getFaqTypeList(ServiceFaqType faqType) {
        return faqTypeManager.getList(faqType);
    }

    public ServiceFaqType addFaqType(ServiceFaqType faqType) {
        return faqTypeManager.add(faqType);
    }

    public ServiceFaqType updateFaqType(ServiceFaqType faqType) {
        return faqTypeManager.update(faqType);
    }

    public Integer deleteFaqType(Integer id) {
        return faqTypeManager.deleteTree(id);
    }
}
