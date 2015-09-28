package org.tinygroup.sdpm.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.service.biz.inter.SlaManager;
import org.tinygroup.sdpm.service.dao.ServiceSlaDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceSla;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Service
@Transactional
public class SlaManagerImpl implements SlaManager {
    @Autowired
    private ServiceSlaDao slaDao;

    public ServiceSla find(Integer id) {
        return slaDao.getByKey(id);
    }

    public List<ServiceSla> getList(ServiceSla sla) {
        return slaDao.query(sla);
    }

    public ServiceSla add(ServiceSla sla) {
        return slaDao.add(sla);
    }

    public ServiceSla update(ServiceSla sla) {
        slaDao.edit(sla);
        return sla;
    }

    public Integer delete(Integer id) {
        return slaDao.softDelete(id);
    }

    public Integer deleteBatch(Integer id) {
        return null;
    }

    public List<ServiceSla> getListByClientId(Integer clientId) {
        return slaDao.getListByClientId(clientId);
    }

    public Pager<ServiceSla> findPager(Integer start, Integer limit, ServiceSla sla) {
        return slaDao.queryPager(start, limit, sla);
    }
}
