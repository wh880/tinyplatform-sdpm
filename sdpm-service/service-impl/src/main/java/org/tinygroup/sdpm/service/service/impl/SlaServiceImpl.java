package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.service.biz.inter.SlaManager;
import org.tinygroup.sdpm.service.dao.pojo.ServiceSla;
import org.tinygroup.sdpm.service.service.inter.SlaService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by Administrator on 2015-09-19.
 */
@Component
@Transactional
public class SlaServiceImpl implements SlaService {
    @Autowired
    private SlaManager slaManager;
    @Transactional(readOnly = true)
    public ServiceSla findSla(Integer id) {
        return slaManager.find(id);
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
    @Transactional(readOnly = true)
    public Pager<ServiceSla> findSlaPager(Integer start, Integer limit, ServiceSla sla, Integer treeId, String groupOperate, SearchInfos searchInfos, String order, String orderType) {
        return slaManager.findPager(start, limit, sla, treeId, groupOperate, searchInfos, order, orderType);
    }
    @Transactional(readOnly = true)
    public List<ServiceSla> findSlaBySlaId(Integer id) {
        return slaManager.getListByClientId(id);
    }

    public int[] deleteBatchSla(List<ServiceSla> list) {
        return slaManager.deleteBatch(list);
    }
}
