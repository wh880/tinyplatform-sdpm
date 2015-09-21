package org.tinygroup.sdpm.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.sdpm.service.biz.inter.RequestManager;
import org.tinygroup.sdpm.service.dao.pojo.Request;
import org.tinygroup.sdpm.service.service.inter.RequestService;

import java.util.List;

/**
 * Created by Administrator on 2015-09-19.
 */
@Component("requestService")
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestManager requestManager;
    public Request findRequest(Integer id) {
        return requestManager.find(id);
    }

    public Request findRequestByMolde(String moldeId) {
        return requestManager.findByMolde(moldeId);
    }

    public List<Request> getRequestList(Request request) {
        return requestManager.getList(request);
    }

    public Request addRequest(Request request) {
        return requestManager.add(request);
    }

    public Request updateRequest(Request request) {
        return requestManager.update(request);
    }

    public Integer deleteRequest(Integer id) {
        return requestManager.delete(id);
    }

    public Integer deleteRequestBatch(Integer... id) {
        return requestManager.deleteBatch(id);
    }
}
