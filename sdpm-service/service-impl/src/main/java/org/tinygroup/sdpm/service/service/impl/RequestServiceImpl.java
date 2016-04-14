package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.dao.condition.ConditionCarrier;
import org.tinygroup.sdpm.org.dao.pojo.OrgUser;
import org.tinygroup.sdpm.service.biz.inter.RequestManager;
import org.tinygroup.sdpm.service.dao.pojo.ServiceRequest;
import org.tinygroup.sdpm.service.service.inter.RequestService;
import org.tinygroup.tinysqldsl.Pager;

import java.util.List;

/**
 * Created by Administrator on 2015-09-19.
 */
@Component
@Transactional
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestManager requestManager;
    @Transactional(readOnly = true)
    public ServiceRequest findRequest(Integer id) {
        return requestManager.find(id);
    }
    @Transactional(readOnly = true)
    public List<ServiceRequest> getRequestList(ServiceRequest request) {
        return requestManager.getList(request);
    }

    public ServiceRequest addRequest(ServiceRequest request) {
        return requestManager.add(request);
    }

    public ServiceRequest updateRequest(ServiceRequest request) {
        return requestManager.update(request);
    }

    public Integer deleteRequest(Integer id) {
        return requestManager.delete(id);
    }

    @Transactional(readOnly = true)
    public Pager<ServiceRequest> findRequestPager(Integer start, Integer limit, Integer status, ServiceRequest clientRequest, Integer treeId, ConditionCarrier carrier, String order, String ordertype) {
        return requestManager.findPager(start, limit, status, clientRequest, treeId, carrier, order, ordertype);
    }
    @Transactional(readOnly = true)
    public Pager<ServiceRequest> findOperationByMe(Integer start, Integer limit, OrgUser user, ServiceRequest clientRequest, Integer treeId, Integer operation, String order, String ordertype) {
        return requestManager.findOperationByMe(start, limit, user, clientRequest, treeId, operation, order, ordertype);
    }

    public Integer closeRequest(ServiceRequest clientRequest) {
        return requestManager.close(clientRequest);
    }

    public Integer saveReply(ServiceRequest clientRequest) {
        return requestManager.saveReply(clientRequest);
    }

    public int[] updateReply(List<ServiceRequest> list) {
        return requestManager.updateReply(list);
    }

    public int[] updateServiceRequest(List<ServiceRequest> list) {
        return requestManager.updateReview(list);
    }

    public int[] deleteBatchRequest(List<ServiceRequest> list) {
        return requestManager.deleteBatch(list);
    }
    @Transactional(readOnly = true)
    public List<ServiceRequest> requestInCondition(String condition, Integer limit) {
        return requestManager.requestInCondition(condition, limit);
    }

}
