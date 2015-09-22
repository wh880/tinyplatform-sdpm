package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.service.biz.inter.FaqTypeManager;
import org.tinygroup.sdpm.service.dao.pojo.FaqType;
import org.tinygroup.sdpm.service.service.inter.FaqTypeService;

import java.util.List;

/**
 * Created by Administrator on 2015-09-19.
 */
@Component("")
public class FaqTypeServiceImpl implements FaqTypeService {
    @Autowired
    private FaqTypeManager faqTypeManager;
    public FaqType findFaqType(Integer id) {
        return faqTypeManager.find(id);
    }

    public List<FaqType> getFaqTypeList(FaqType faqType) {
        return faqTypeManager.getList(faqType);
    }

    public FaqType addFaqType(FaqType faqType) {
        return faqTypeManager.add(faqType);
    }

    public FaqType updateFaqType(FaqType faqType) {
        return faqTypeManager.update(faqType);
    }

    public List<FaqType> deleteFaqType(Integer id) {
        return faqTypeManager.delete(id);
    }
}
