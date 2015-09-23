package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.service.biz.inter.RequestManager;
import org.tinygroup.sdpm.service.dao.pojo.ServiceRequest;
import org.tinygroup.sdpm.service.service.inter.RequestService;

import java.util.List;

/**
 * Created by Administrator on 2015-09-19.
 */
@Component
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestManager requestManager;

    public ServiceRequest findRequest(Integer id) {
        return requestManager.find(id);
    }

    public ServiceRequest findRequestByMolde(String moldeId) {
        return requestManager.findByMolde(moldeId);
    }

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

    public Integer deleteRequestBatch(Integer... id) {
        return requestManager.deleteBatch(id);
    }
}
