package org.tinygroup.sdpm.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.service.biz.inter.FaqTypeManager;
import org.tinygroup.sdpm.service.dao.pojo.FaqType;
import org.tinygroup.sdpm.service.dao.FaqTypeDao;
import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Service
@Transactional
public class FaqTypeManagerImpl implements FaqTypeManager{
    @Autowired
    private FaqTypeDao faqTypeDao;
    public FaqType find(Integer id) {
        return faqTypeDao.getByKey(id);
    }

    public List<FaqType> getList(FaqType faqType) {
        return faqTypeDao.query(faqType);
    }

    public FaqType add(FaqType faqType) {
        return faqTypeDao.add(faqType);
    }

    public FaqType update(FaqType faqType) {
         faqTypeDao.edit(faqType);
        return faqType;
    }

    public List<FaqType> delete(Integer id) {
        FaqType faqType=new FaqType();
        faqType.setFaqTypeId(id);
        faqType.setDeleted(id);
        return faqTypeDao.query(faqType);
    }
}
