package org.tinygroup.sdpm.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tinygroup.commons.tools.StringUtil;
import org.tinygroup.jdbctemplatedslsession.daosupport.OrderBy;
import org.tinygroup.sdpm.common.util.common.NameUtil;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.dao.condition.ConditionUtils;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.service.biz.inter.RequestManager;
import org.tinygroup.sdpm.service.dao.ServiceRequestDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceRequest;
import org.tinygroup.tinysqldsl.Pager;
import org.tinygroup.tinysqldsl.base.Condition;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Service
public class RequestManagerImpl implements RequestManager {
    @Autowired
    private ServiceRequestDao requestDao;

    public ServiceRequest find(Integer id) {
        return requestDao.getByKey(id);
    }

    public ServiceRequest findByMolde(String moldeId) {
        return null;
    }

    public List<ServiceRequest> getList(ServiceRequest request) {
        return requestDao.query(request);
    }

    public ServiceRequest add(ServiceRequest request) {
        return requestDao.add(request);
    }

    public ServiceRequest update(ServiceRequest request) {
        requestDao.edit(request);
        return request;
    }

    public Integer delete(Integer id) {
        return requestDao.softDelete(id);
    }

    public int[] deleteBatch(List<ServiceRequest> list) {
        return requestDao.softDeleteBatch(list);
    }

    public Pager<ServiceRequest> findPager(Integer start, Integer limit, Integer status, ServiceRequest serviceRequest,
                                           Integer treeId, ConditionCarrier carrier, String order, String ordertype) {

        if (StringUtil.isBlank(order)) {
            return requestDao.queryPagerBy(start, limit, serviceRequest, status, treeId, mergeCondition(carrier));
        }
        return requestDao.queryPagerBy(start, limit, serviceRequest, status, treeId, mergeCondition(carrier), new OrderBy(NameUtil.resolveNameDesc(order), !("desc".equals(ordertype)) ? true : false));
    }

    public Pager<ServiceRequest> findOperationByMe(Integer start, Integer limit, OrgUser user, ServiceRequest serviceRequest, Integer treeId, Integer operation,
                                                   String order, String ordertype) {
        return requestDao.findOperationByMe(start, limit, serviceRequest, user, treeId, operation, (order == null || "".equals(order)) ? null : new OrderBy(NameUtil.resolveNameDesc(order), !("desc".equals(ordertype)) ? true : false));
    }


    public Integer close(ServiceRequest clientRequest) {
        return requestDao.close(clientRequest);
    }

    public Integer saveReply(ServiceRequest clientRequest) {
        return requestDao.saveReply(clientRequest);
    }

    public Integer changeStatus(Integer id) {
        return requestDao.changeStatus(id);
    }

    public int[] updateReply(List<ServiceRequest> list) {
        return requestDao.batchUpdateReply(list);
    }

    public int[] updateReview(List<ServiceRequest> list) {
        return requestDao.batchUpdateReview(list);
    }

    public List<ServiceRequest> requestInCondition(String condition, Integer limit) {
        return requestDao.requestInCondition(condition, limit);
    }

    private Condition mergeCondition(ConditionCarrier carrier) {
        return ConditionUtils.mergeCondition(carrier, null);
    }
}
