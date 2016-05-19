package org.tinygroup.sdpm.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.dao.complexsearch.SearchInfos;
import org.tinygroup.sdpm.dao.complexsearch.SqlUtil;
import org.tinygroup.sdpm.service.biz.inter.SlaManager;
import org.tinygroup.sdpm.service.dao.ServiceSlaDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceSla;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;

import java.util.List;

import static org.tinygroup.tinysqldsl.base.FragmentSql.fragmentCondition;

/**
 * Created by Administrator on 2015-09-18.
 */
@Service
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

    public Pager<ServiceSla> findPager(Integer start, Integer limit, ServiceSla sla, Integer treeId, String groupOperate, SearchInfos searchInfos, String order, String orderType) {
        String condition = searchInfos != null ? SqlUtil.toSql(searchInfos.getInfos(), groupOperate) : "";
        Condition condition1 = null;
        if (!StringUtil.isBlank(condition)) {
            condition1 = fragmentCondition(condition);
        }
        return slaDao.queryPagerTree(start, limit, sla, treeId, condition1, (order == null || "".equals(order)) ? null : new OrderBy(NameUtil.resolveNameDesc(order), !("desc".equals(orderType)) ? true : false));
    }

    public ServiceSla judgeClient(String clientName) {
        return slaDao.judge(clientName);
    }

    public int[] deleteBatch(List<ServiceSla> list) {
        return slaDao.softDeleteBatch(list);
    }

}
