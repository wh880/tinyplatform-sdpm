/**
 * Copyright (c) 1997-2013, www.tinygroup.org (luo_guo@icloud.com).
 * <p>
 * Licensed under the GPL, Version 3.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/gpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.tinygroup.sdpm.org.wrapper;

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

@Component("roleMenuService")
public class RoleMenuServiceImplWrapper implements org.tinygroup.sdpm.org.service.inter.RoleMenuService {
    @Autowired
    CEPCore cepcore;

    public CEPCore getCore() {
        return cepcore;
    }

    public void setCore(CEPCore cepcore) {
        this.cepcore = cepcore;
    }

    private Event getEvent(String serviceId, Context context) throws Exception {
        Event event = new Event();
        event.setEventId(UUID.randomUUID().toString());
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setContext(context);
        serviceRequest.setServiceId(serviceId);
        event.setServiceRequest(serviceRequest);
        return event;
    }

    public org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu findRoleMenu(java.lang.Integer id) {
        String serviceId = "org_findRoleMenu";

        try {
            Context context = new ContextImpl();
            context.put("id", id);

            return callServiceAndCallBack(serviceId, context);
        } catch (Exception e) {
            throw new RuntimeException(String.format("服务[%s]发生异常", serviceId), e);
        }
    }

    public org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu addRoleMenu(org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu orgRoleMenu) {
        String serviceId = "org_addRoleMenu";

        try {
            Context context = new ContextImpl();
            context.put("orgRoleMenu", orgRoleMenu);

            return callServiceAndCallBack(serviceId, context);
        } catch (Exception e) {
            throw new RuntimeException(String.format("服务[%s]发生异常", serviceId), e);
        }
    }

    public org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu updateRoleMenu(org.tinygroup.sdpm.org.dao.pojo.OrgRoleMenu orgRoleMenu) {
        String serviceId = "org_updateRoleMenu";

        try {
            Context context = new ContextImpl();
            context.put("orgRoleMenu", orgRoleMenu);

            return callServiceAndCallBack(serviceId, context);
        } catch (Exception e) {
            throw new RuntimeException(String.format("服务[%s]发生异常", serviceId), e);
        }
    }

    public java.lang.Integer deleteRoleMenu(java.lang.Integer id) {
        String serviceId = "org_deleteRoleMenu";

        try {
            Context context = new ContextImpl();
            context.put("id", id);

            return callServiceAndCallBack(serviceId, context);
        } catch (Exception e) {
            throw new RuntimeException(String.format("服务[%s]发生异常", serviceId), e);
        }
    }

    private <T> T callServiceAndCallBack(String serviceId, Context context) throws Exception {
        Event event = getEvent(serviceId, context);
        cepcore.process(event);
        ServiceInfo info = cepcore.getServiceInfo(serviceId);
        List<Parameter> resultsParam = info.getResults();
        if (resultsParam == null || resultsParam.size() == 0) {
            return null;
        }
        return event.getServiceRequest().getContext()
                .get(resultsParam.get(0).getName());
    }

}