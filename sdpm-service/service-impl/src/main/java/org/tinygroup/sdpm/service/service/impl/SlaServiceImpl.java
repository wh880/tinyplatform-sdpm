package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.service.biz.inter.SlaManager;
import org.tinygroup.sdpm.service.dao.pojo.Sla;
import org.tinygroup.sdpm.service.service.inter.SlaService;

import java.util.List;

/**
 * Created by Administrator on 2015-09-19.
 */@Component("")
public class SlaServiceImpl implements SlaService {
    @Autowired
    private SlaManager slaManager;
    public Sla findSla(Integer id) {
        return slaManager.find(id);
    }

    public List<Sla> getSlaList(Sla sla) {
        return slaManager.getList(sla);
    }

    public Sla addSla(Sla sla) {
        return slaManager.add(sla);
    }

    public Sla updateSla(Sla sla) {
        return slaManager.update(sla);
    }

    public Integer deleteSla(Integer id) {
        return slaManager.delete(id);
    }

    public Integer deleteSlaBatch(Integer id) {
        return slaManager.deleteBatch(id);
    }
}
