package org.tinygroup.sdpm.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tinygroup.sdpm.service.biz.inter.RequestManager;
import org.tinygroup.sdpm.service.dao.RequestDao;
import org.tinygroup.sdpm.service.dao.pojo.Request;

import java.util.List;

/**
 * Created by Administrator on 2015-09-18.
 */
@Service
@Transactional
public class RequestManagerImpl implements RequestManager{
    @Autowired
    private RequestDao requestDao;
    public Request find(Integer id) {
        return requestDao.getByKey(id);
    }

    public Request findByMolde(String moldeId) {
        return null;
    }

    public List<Request> getList(Request request) {
        return requestDao.query(request);
    }

    public Request add(Request request) {
        return requestDao.add(request);
    }

    public Request update(Request request) {requestDao.edit(request);return request;}

    public Integer delete(Integer id) {
        Request request=new Request();
        request.setClientId(id);
        request.setDeleted(id);
        return requestDao.deleteByKey(id);
    }

    public Integer deleteBatch(Integer... id) {
       Request request = new Request();


        return requestDao.deleteByKeys(id);
    }
}
