package org.tinygroup.sdpm.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.service.biz.inter.SlaManager;
import org.tinygroup.sdpm.service.dao.pojo.Sla;
import org.tinygroup.sdpm.service.dao.SlaDao;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Service
@Transactional
public class SlaManagerImpl implements SlaManager{
    @Autowired
    private SlaDao slaDao;
    public Sla find(Integer id) {
        return slaDao.getByKey(id);
    }

    public List<Sla> getList(Sla sla) {
        return slaDao.query(sla);
    }

    public Sla add(Sla sla) {
        return slaDao.add(sla);
    }

    public Sla update(Sla sla) {
        slaDao.edit(sla);
        return sla;
    }

    public Integer delete(Integer id) {
       Sla sla=new Sla();
        sla.setSlaId(id);
        sla.setDeleted(id);
        return slaDao.edit(sla);
    }

    public Integer deleteBatch(Integer id) {
        return  null;
    }
}
