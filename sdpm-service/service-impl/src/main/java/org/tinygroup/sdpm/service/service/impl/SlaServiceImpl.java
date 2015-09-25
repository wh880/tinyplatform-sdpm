package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.service.biz.inter.SlaManager;
import org.tinygroup.sdpm.service.dao.pojo.ServiceSla;
import org.tinygroup.sdpm.service.service.inter.SlaService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by Administrator on 2015-09-19.
 */@Component("")
public class SlaServiceImpl implements SlaService {
    @Autowired
    private SlaManager slaManager;

    public ServiceSla findSla(Integer id) {
        return slaManager.find(id);
    }

    public List<ServiceSla> getSlaList(ServiceSla sla) {
        return slaManager.getList(sla);
    }

    public ServiceSla addSla(ServiceSla sla) {
        return slaManager.add(sla);
    }

    public ServiceSla updateSla(ServiceSla sla) {
        return slaManager.update(sla);
    }

    public Integer deleteSla(Integer id) {
        return slaManager.delete(id);
    }

    public Integer deleteSlaBatch(Integer id) {
        return slaManager.deleteBatch(id);
    }

    public Pager<ServiceSla> findSlaPager(Integer start, Integer limit, ServiceSla sla) {
        return slaManager.findPager(start, limit, sla);
    }
}
