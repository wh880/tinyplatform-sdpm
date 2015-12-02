package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.service.biz.inter.SlaManager;
import org.tinygroup.sdpm.service.dao.pojo.ServiceSla;
import org.tinygroup.sdpm.service.service.inter.SlaService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by Administrator on 2015-09-19.
 */
@Component("")
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

    public Pager<ServiceSla> findSlaPager(Integer start, Integer limit, ServiceSla sla, Integer treeId, String groupOperate, SearchInfos searchInfos, String order, String orderType) {
        return slaManager.findPager(start, limit, sla, treeId, groupOperate, searchInfos, order, orderType);
    }

    /*2015/9/29,实现协议里面，点击客户ID，页面数据显示，新增的方法*/
    public List<ServiceSla> findSlaBySlaId(Integer id) {
        return slaManager.getListByClientId(id);
    }

    public ServiceSla judgeClient(String clientName) {
        return slaManager.judgeClient(clientName);
    }

    public int[] deleteBatchSla(List<ServiceSla> list) {
        return slaManager.deleteBatch(list);
    }
}
