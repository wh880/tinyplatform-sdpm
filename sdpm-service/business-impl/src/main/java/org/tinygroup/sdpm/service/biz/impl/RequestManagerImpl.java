package org.tinygroup.sdpm.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.service.biz.inter.RequestManager;
import org.tinygroup.sdpm.service.dao.ServiceRequestDao;
import org.tinygroup.sdpm.service.dao.pojo.ServiceRequest;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Service
@Transactional
public class RequestManagerImpl implements RequestManager{
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
        ServiceRequest request = new ServiceRequest();
        request.setClientId(id);
        request.setDeleted(id);
        return requestDao.deleteByKey(id);
    }

    public Integer deleteBatch(Integer... id) {
        ServiceRequest request = new ServiceRequest();


        return requestDao.deleteByKeys(id);
    }
}
