package org.tinygroup.sdpm.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tinygroup.cepcore.CEPCore;
import org.tinygroup.context.Context;
import org.tinygroup.context.impl.ContextImpl;
import org.tinygroup.event.Event;
import org.tinygroup.event.Parameter;
import org.tinygroup.event.ServiceInfo;
import org.tinygroup.event.ServiceRequest;

import java.util.List;
import java.util.UUID;

/**
 * Created by wangll13383 on 2015/9/28.
 */
@Component
public class ServiceUtil {
    @Autowired
    CEPCore cepcore;

    private Event getEvent(String serviceId, Context context) throws Exception{
        Event event = new Event();
        event.setEventId(UUID.randomUUID().toString());
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setContext(context);
        serviceRequest.setServiceId(serviceId);
        event.setServiceRequest(serviceRequest);
        return event;
    }

    public Object callService(String serviceId ,CallBackFunction callBackFunction) {
        try{
            return callServiceAndCallBack(serviceId,callBackFunction.getContext());
        }catch(Exception e){
            throw new RuntimeException(String.format("服务[%s]发生异常",serviceId),e);
        }
    }

    private <T> T callServiceAndCallBack(String serviceId,Context context) throws Exception{
        Event event = getEvent(serviceId,context);
        cepcore.process(event);
        ServiceInfo info = cepcore.getServiceInfo(serviceId);
        List<Parameter> resultsParam = info.getResults();
        if (resultsParam==null||resultsParam.size() == 0) {
            return null;
        }
        return event.getServiceRequest().getContext()
                .get(resultsParam.get(0).getName());
    }
}
